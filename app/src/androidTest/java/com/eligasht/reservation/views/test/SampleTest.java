package com.eligasht.reservation.views.test;

import com.eligasht.reservation.views.TestConst;
import com.eligasht.reservation.views.ui.AppStartup;
import com.eligasht.reservation.views.ui.BaseTest;
import com.eligasht.reservation.views.ui.LoginAndEditProfile;

/**
 * Created by Ahmad.nemati on 4/17/2018.
 */
public class SampleTest extends BaseTest {
    @Override
    public void runTest() {
        AppStartup.newInstance().runTest();
        LoginAndEditProfile.newInstance().runTest();
        pressBack(TestConst.Login_Back);
        sleep(5000);

    }
}
