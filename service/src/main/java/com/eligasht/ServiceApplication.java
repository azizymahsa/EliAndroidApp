package com.eligasht;

import android.app.Application;

import com.eligasht.service.di.component.DaggerNetComponent;
import com.eligasht.service.di.component.NetComponent;
import com.eligasht.service.di.module.AppModule;
import com.eligasht.service.di.module.NetModule;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.helper.Const;

/**
 * Created by Ahmad.nemati on 3/26/2018.
 */

public class ServiceApplication extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        SingletonService.getInstance().setContext(this);
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Const.BASEURL))
                .build();

        SingletonService.getInstance().setNetComponent(mNetComponent).inject();
    }


}
