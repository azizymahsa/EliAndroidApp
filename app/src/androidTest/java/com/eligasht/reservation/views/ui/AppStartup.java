package com.eligasht.reservation.views.ui;

import android.os.Bundle;

import com.eligasht.reservation.tools.Prefs;
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
        Prefs.remove("WEB_USER");
        sleep(Const.MOCK ? 7000 : 10000);
    }
}
