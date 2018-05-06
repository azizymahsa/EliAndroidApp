package com.eligasht.reservation.views.test;

import com.eligasht.reservation.views.ui.Aboutus;
import com.eligasht.reservation.views.ui.AppStartup;
import com.eligasht.reservation.views.ui.BaseTest;
import com.eligasht.reservation.views.ui.Contactus2;
import com.eligasht.reservation.views.ui.Flight;
import com.eligasht.reservation.views.ui.ForgetPassword;
import com.eligasht.reservation.views.ui.Hotel;
import com.eligasht.reservation.views.ui.HotelF;
import com.eligasht.reservation.views.ui.Insurance;
import com.eligasht.reservation.views.ui.LoginAndEditProfile;
import com.eligasht.reservation.views.ui.Package;
import com.eligasht.reservation.views.ui.Setting;
import com.eligasht.reservation.views.ui.TermsAndCond;

/**
 * Created by Ahmad.nemati on 4/17/2018.
 */
public class FullTest extends BaseTest {
    @Override
    public void runTest() {
        AppStartup.newInstance().runTest();
      //  ForgetPassword.newInstance().runTest();
     //   LoginAndEditProfile.newInstance().runTest();
        Flight.newInstance().runTest();
        Hotel.newInstance().runTest();
        HotelF.newInstance().runTest();
        Package.newInstance().runTest();
        Insurance.newInstance().runTest();
        TermsAndCond.newInstance().runTest();
        Setting.newInstance().runTest();
        Contactus2.newInstance().runTest();
        Aboutus.newInstance().runTest();
    }
}
