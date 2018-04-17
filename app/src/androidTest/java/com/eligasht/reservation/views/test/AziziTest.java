package com.eligasht.reservation.views.test;

import com.eligasht.reservation.views.TestConst;
import com.eligasht.reservation.views.ui.AppStartup;
import com.eligasht.reservation.views.ui.BaseTest;
import com.eligasht.reservation.views.ui.Flight;
import com.eligasht.reservation.views.ui.ForgetPassword;

public class AziziTest extends BaseTest {
    @Override
    public void runTest() {
        AppStartup.newInstance().runTest();
     //   ContactUs.newInstance().runTest();

        sleep(5000);

    }
}
