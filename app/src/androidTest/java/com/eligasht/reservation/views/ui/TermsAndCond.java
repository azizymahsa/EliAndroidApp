package com.eligasht.reservation.views.ui;

import android.os.Bundle;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;

/**
 * Created by Ahmad.nemati on 4/17/2018.
 */
public class TermsAndCond extends BaseTest {
    public static TermsAndCond newInstance() {
        return new TermsAndCond();
    }

    @Override
    public void runTest() {
        doClick(R.id.btnMenu);
        sleep(750);
        doClick(R.id.btn_condition);
        sleep(3000);
        pressBack(TestConst.Terms_Back);
    }
}
