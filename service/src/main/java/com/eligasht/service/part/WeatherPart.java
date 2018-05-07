package com.eligasht.service.part;

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
import io.reactivex.disposables.Disposable;
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
        String q = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text%3D%22ist%22) and u=%27%22 + Currency + %22%27&format=json";
        q = q.replace("ist", cityName);

        Retrofit retrofit = getRetrofit();
        RetroClient retroClient = retrofit.create(RetroClient.class);
        retroClient.yahooWeather(q).subscribe(new Observer<Response<WeatherApi>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<WeatherApi> testResResponse) {
                listener.onReady(testResResponse.body());
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.getMessage());
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
