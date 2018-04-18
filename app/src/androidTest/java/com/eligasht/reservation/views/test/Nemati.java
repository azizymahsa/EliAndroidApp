package com.eligasht.reservation.views.test;

import com.eligasht.R;
import com.eligasht.reservation.views.ui.AppStartup;
import com.eligasht.reservation.views.ui.BaseTest;
import com.eligasht.reservation.views.ui.Hotel;
import com.eligasht.reservation.views.ui.HotelF;

/**
 * Created by Ahmad.nemati on 4/18/2018.
 */
public class Nemati extends BaseTest {
    @Override
    public void runTest() {
        AppStartup.newInstance().runTest();
        Hotel.newInstance().runTest();
        sleep(100000);


    }
}
