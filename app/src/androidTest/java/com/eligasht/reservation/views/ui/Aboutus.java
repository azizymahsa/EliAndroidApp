package com.eligasht.reservation.views.ui;

import android.os.Bundle;

import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;

/**
 * Created by Ahmad.nemati on 4/17/2018.
 */
public class Aboutus extends BaseTest {

    public static Aboutus newInstance() {
        return new Aboutus();
    }

    @Override
    public void runTest() {
        doClick(R.id.btnMenu);
        doClick(R.id.btnAbout);
        sleep(1000);
        pressBack(TestConst.Aboutus_Back);
    }
}
