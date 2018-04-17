package com.eligasht.reservation.views.ui;

import android.os.Bundle;

import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;

/**
 * Created by Ahmad.nemati on 4/17/2018.
 */
public class Contactus extends BaseTest {

    public static Contactus newInstance() {
        return new Contactus();
    }
    @Override
    public void runTest() {
        doClick(R.id.btnMenu);
        doClick(R.id.btnContactUs);
        sleep(1000);
        pressBack(TestConst.Contactus_Back);

    }
}
