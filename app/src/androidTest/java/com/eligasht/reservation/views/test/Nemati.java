package com.eligasht.reservation.views.test;

import com.eligasht.reservation.views.ui.AppStartup;
import com.eligasht.reservation.views.ui.BaseTest;

/**
 * Created by Ahmad.nemati on 4/18/2018.
 */
public class Nemati extends BaseTest {
    @Override
    public void runTest() {
        AppStartup.newInstance().runTest();
       // HotelF2.newInstance().runTest();
        sleep(100000);

    }
}
