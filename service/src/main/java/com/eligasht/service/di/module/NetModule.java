package com.eligasht.service.di.module;

import com.eligasht.service.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

@Module
public class NetModule {
    private String mBaseUrl;

    public NetModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }


    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addNetworkInterceptor(new AddHeaderInterceptor());
        client.connectTimeout(60, TimeUnit.SECONDS);
        client.readTimeout(60, TimeUnit.SECONDS);
        client.writeTimeout(60, TimeUnit.SECONDS);
        return client.build();
    }
    public class AddHeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("Authorization", "bearer rpgB0g3q01GR7Hk7ua5MB9nn_WDFpzv6NHPj14HB00T-ceACFqbXgg7vYYIBOl70r3Vl-mK95q0Qno1T-Gei_LLHDkSwMdIcHmQ1kTWAbGp0k27cl1I_hzZ7clRlPHemXewXNd31mxmORFr-clnP6oqfyV8uF8wm53xkRkvZEUmckqHXjEEdpvPxHiEvE2YTip6Rv_eHbYdMpU5wRTSZ0gZcIvzC_5pfzaRiOH-zGoEGBbcDUvAoKj5_TIlLRf_e");

            return chain.proceed(builder.build());
        }
    }
    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        
    }

}

