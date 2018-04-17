package com.eligasht.reservation.views.test;

import com.eligasht.reservation.views.ui.Aboutus;
import com.eligasht.reservation.views.ui.AppStartup;
import com.eligasht.reservation.views.ui.BaseTest;
import com.eligasht.reservation.views.ui.Contactus;
import com.eligasht.reservation.views.ui.Flight;
import com.eligasht.reservation.views.ui.ForgetPassword;
import com.eligasht.reservation.views.ui.LoginAndEditProfile;

/**
 * Created by Ahmad.nemati on 4/17/2018.
 */
public class NematiTest extends BaseTest {
    @Override
    public void runTest() {
        AppStartup.newInstance().runTest();
        ForgetPassword.newInstance().runTest();
        LoginAndEditProfile.newInstance().runTest();
        Contactus.newInstance().runTest();
        Aboutus.newInstance().runTest();
        Flight.newInstance().runTest();
    }
}
