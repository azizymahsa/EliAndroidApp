package com.eligasht.reservation.base;

import com.eligasht.reservation.conf.APIConf;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.service.generator.SingletonService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 */


public abstract class BaseAPI {
    private final String TAG = "__" + this.getClass().getSimpleName().toUpperCase().toString();

    protected Retrofit retrofit;
    protected Call call;
    // The result / whether is success or failed TODO: put it in try catch if response failed

    /**
     * Class constructor
     */
    public BaseAPI() {
        buildUri();
    }


    /**
     * To build URI
     *
     * @return Retrofit object
     */
    protected Retrofit buildUri() {
        onBuildUri();

        retrofit = new Retrofit.Builder()
                .baseUrl(APIConf.CORE_REST_API_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(SingletonService.getInstance().getOkHttpClient())
                .build();


        return retrofit;
    }

    private OkHttpClient getRequestHeader() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }

    /**
     * On build URI
     */
    protected abstract void onBuildUri();

    /**
     * Execute request
     */
    protected abstract void execute();

    /**
     * On Before Execute
     */
    protected abstract void onBeforeExecute();

    /**
     * On After Execute
     */
    protected abstract void onAfterExecute();

    /**
     * send request to server
     */
    protected void send() {
        onBeforeExecute();
        execute();
        onAfterExecute();

    }
}
