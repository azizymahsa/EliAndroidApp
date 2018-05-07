package com.eligasht.service.part;

import android.util.Log;

import com.eligasht.service.api.RetroClient;
import com.eligasht.service.generator.ServiceGenerator;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.login.request.LoginRequestModel;
import com.eligasht.service.model.login.response.LoginResponse;
import com.eligasht.service.model.test.entity.TestRes;
import com.eligasht.service.model.weather.response.WeatherApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ahmad.nemati on 5/7/2018.
 */
public class WeatherPart extends BasePart {
    public WeatherPart(ServiceGenerator serviceGenerator) {
        super(serviceGenerator);
    }

    @Override
    protected BasePart getPart() {
        return this;
    }

    public void getWeatherByCity(OnServiceStatus<WeatherApi> listener, String cityName) {
        String q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"ist\") and u='\"   Currency   \"'";
        q = q.replace("ist", cityName);
        Log.e("qqqqq", q);

        Retrofit retrofit = getRetrofit();
        RetroClient retroClient = retrofit.create(RetroClient.class);
        retroClient.yahooWeather(q,"json")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<WeatherApi>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<WeatherApi> testResResponse) {
                Log.e("Stat",testResResponse.toString());
                Log.e("Code", String.valueOf(testResResponse.code()));
                listener.onReady(testResResponse.body());
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private Retrofit getRetrofit() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://query.yahooapis.com/v1/public/")
                .client(SingletonService.getInstance().getOkHttpClient())
                .build();
        return retrofit;
    }
}
