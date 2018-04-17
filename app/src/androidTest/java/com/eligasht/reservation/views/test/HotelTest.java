package com.eligasht.reservation.views.test;
import com.eligasht.reservation.views.ui.AppStartup;
import com.eligasht.reservation.views.ui.BaseTest;
import com.eligasht.reservation.views.ui.Hotel;
/**
 * Created by Reza Nejati on 17,April,2018
 */
public class HotelTest extends BaseTest {
    @Override
    public void runTest() {
        AppStartup.newInstance().runTest();
        Hotel.newInstance().runTest();
        sleep(5000);

    }
}