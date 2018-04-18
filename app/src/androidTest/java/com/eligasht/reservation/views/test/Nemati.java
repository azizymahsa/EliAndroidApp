package com.eligasht.reservation.views.test;

import com.eligasht.R;
import com.eligasht.reservation.views.ui.AppStartup;
import com.eligasht.reservation.views.ui.BaseTest;
import com.eligasht.reservation.views.ui.Hotel;
import com.eligasht.reservation.views.ui.HotelF;
import com.eligasht.reservation.views.ui.Insurance;
import com.eligasht.reservation.views.ui.Package;

/**
 * Created by Ahmad.nemati on 4/18/2018.
 */
public class Nemati extends BaseTest {
    @Override
    public void runTest() {
        AppStartup.newInstance().runTest();
        Package.newInstance().runTest();
        sleep(5000);



    }
}
