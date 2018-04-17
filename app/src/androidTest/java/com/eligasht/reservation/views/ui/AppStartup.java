package com.eligasht.reservation.views.ui;

import android.os.Bundle;

import com.eligasht.service.helper.Const;

/**
 * Created by Ahmad.nemati on 4/17/2018.
 */
public class AppStartup extends BaseTest {

    public static AppStartup newInstance() {
        AppStartup fragment = new AppStartup();
        return fragment;
    }
    @Override
    public void runTest() {
        sleep(Const.MOCK ? 7000 : 10000);
    }
}
