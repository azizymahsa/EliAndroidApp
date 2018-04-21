package com.eligasht.reservation.views.test;
import com.eligasht.reservation.views.ui.AppStartup;
import com.eligasht.reservation.views.ui.BaseTest;
import com.eligasht.reservation.views.ui.Hotel;
import com.eligasht.reservation.views.ui.HotelF;
/**
 * Created by Reza Nejati on 18,April,2018
 */
public class HotelFlight extends BaseTest {
    @Override
    public void runTest() {
        AppStartup.newInstance().runTest();
        HotelF.newInstance().runTest();
        sleep(5000);

    }
}
