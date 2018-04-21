package com.eligasht.service.di.component;

import com.eligasht.ServiceApplication;
import com.eligasht.service.di.module.AppModule;
import com.eligasht.service.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
    OkHttpClient okhttp();

}

