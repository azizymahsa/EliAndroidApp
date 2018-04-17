package com.eligasht.reservation.views.test;

import com.eligasht.reservation.views.ui.Aboutus;
import com.eligasht.reservation.views.ui.AppStartup;
import com.eligasht.reservation.views.ui.BaseTest;
import com.eligasht.reservation.views.ui.Contactus2;
import com.eligasht.reservation.views.ui.Flight;
import com.eligasht.reservation.views.ui.ForgetPassword;
import com.eligasht.reservation.views.ui.TermsAndCond;

/**
 * Created by Ahmad.nemati on 4/17/2018.
 */
public class NematiTest extends BaseTest {
    @Override
    public void runTest() {
        AppStartup.newInstance().runTest();
        ForgetPassword.newInstance().runTest();
        Flight.newInstance().runTest();
        TermsAndCond.newInstance().runTest();
        Aboutus.newInstance().runTest();
        Contactus2.newInstance().runTest();
        Aboutus.newInstance().runTest();
    }
}
