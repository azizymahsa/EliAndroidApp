package com.eligasht.reservation.views.test;

import com.eligasht.reservation.views.TestConst;
import com.eligasht.reservation.views.ui.AppStartup;
import com.eligasht.reservation.views.ui.BaseTest;
import com.eligasht.reservation.views.ui.Flight;
import com.eligasht.reservation.views.ui.ForgetPassword;
import com.eligasht.reservation.views.ui.Hotel;
import com.eligasht.reservation.views.ui.Login;

/**
 * Created by Ahmad.nemati on 4/17/2018.
 */
public class SampleTest extends BaseTest {
    @Override
    public void runTest() {
        AppStartup.newInstance().runTest();
      //  ForgetPassword.newInstance().runTest();
       // pressBack(TestConst.Forgetpassword_Back);
      //  Login.newInstance().runTest();
        Hotel.newInstance().runTest();
    //    pressBack(TestConst.Login_Back);
     //   Flight.newInstance().runTest();
      //  pressBack(TestConst.Flight_Back);
        sleep(5000);
        
    }
}
