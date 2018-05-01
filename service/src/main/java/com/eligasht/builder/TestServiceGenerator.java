package com.eligasht.builder;

import android.content.Context;
import android.util.Log;

import com.eligasht.service.R;
import com.eligasht.service.api.RetroClient;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.model.BaseModel;
import com.eligasht.service.model.error.Error;
import com.eligasht.service.model.test.markdown.ServiceTestModel;
import com.eligasht.service.model.test.entity.TestRes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ahmad.nemati on 4/25/2018.
 */
public class TestServiceGenerator {
    private Response<?> response;
    private ServiceTestModel serviceTestModel;
    private ServiceTestModel primaryTestModel;

    public static TestServiceGenerator newInstance(Response<?> response, ServiceTestModel serviceTestModel) {
        return new TestServiceGenerator(response, serviceTestModel);
    }

    public TestServiceGenerator(Response<?> response, ServiceTestModel serviceTestModel) {
        this.response = response;
        this.primaryTestModel = new ServiceTestModel();
        primaryTestModel.update(serviceTestModel);
        this.serviceTestModel = serviceTestModel;
    }

    private void checkWaitTime() {
        long tx = response.raw().networkResponse().sentRequestAtMillis();
        long rx = response.raw().networkResponse().receivedResponseAtMillis();
        int sec = (int) ((rx - tx) / 1000);
        serviceTestModel.setTryTime(String.valueOf(sec == 0 ? "1" : sec));
    }

    private void checkTotalTime() {
        serviceTestModel.setTotalCall(serviceTestModel.getTotalCall() + 1);
    }

    private void checkSize() {
        int size = (int) (response.raw().body().contentLength() / 1000);
        serviceTestModel.setSize(size == 0 ? "1" : String.valueOf(size));
    }

    private void checkMessageAndStatusCode() {
        if (!response.isSuccessful()) {
            serviceTestModel.setStatusCode(response.code());
            serviceTestModel.setMessage(response.message());
            serviceTestModel.setClose(false);
            return;
        }

        Error error = getErrorFromBaseClass();
        if (error == null) {
            serviceTestModel.setMessage(response.message());
            serviceTestModel.setStatusCode(response.code());
            serviceTestModel.setClose(true);
            return;
        }

        serviceTestModel.setMessage(error.getMessage());
        serviceTestModel.setStatusCode(error.getCode());
        serviceTestModel.setClose(false);

    }

    private void issuePerform() {

//        if (serviceTestModel.getClose() == primaryTestModel.getClose())
//            return;

        sendIssue();


    }

    private Retrofit getRetrofit() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://192.168.115.93/api/v4/projects/4/")
                .client(SingletonService.getInstance().getOkHttpClient())
                .build();
        return retrofit;
    }

    public static String toPrettyFormat(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }


    private void sendIssue() {
        Retrofit retrofit = getRetrofit();
        RetroClient retroClient = retrofit.create(RetroClient.class);
        StringBuilder des = new StringBuilder();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        des

                .append("Request Body")
                .append("\n")
                .append("```")
                .append("\n")
                .append(toPrettyFormat(bodyToString(response.raw().request().body())))
                .append("\n")
                .append("```")
                .append("\n")
                .append("Response Body")
                .append("\n")
                .append("```")
                .append("\n")
                .append(toPrettyFormat(gson.toJson(response.body())))
                .append("\n")
                .append("```");


        StringBuilder label = new StringBuilder();
        label.append(serviceTestModel.getStatusCode());
        retroClient.issueTrack(serviceTestModel.getId(), serviceTestModel.getClose() ? "close" : "reopen", label.toString(), des.toString())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<TestRes>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<TestRes> value) {
                        Log.e("Ready", "Ready");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }


    public ServiceTestModel build() {
        checkMessageAndStatusCode();
        checkWaitTime();
        checkSize();
        checkTotalTime();
        issuePerform();
        return serviceTestModel;
    }

    private Error getErrorFromBaseClass() {
        Field[] fields = response.body().getClass().getFields();
        for (Field field : fields) {
            try {
                Log.e("Type", field.getType().getName());
                BaseModel baseModel = new BaseModel();
                baseModel = (BaseModel) field.get(response.body());
                return baseModel.getErrors().get(0);
            } catch (Exception e) {

            }
        }
        return null;
    }


}
