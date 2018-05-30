package com.eligasht.reservation.views.ui;

import android.os.Bundle;

import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;

/**
 * Created by Ahmad.nemati on 5/26/2018.
 */
public class Message extends BaseTest {

    public static Message newInstance() {
        return new Message();
    }
    @Override
    public void runTest() {
        doClick(R.id.btnMenu);
        sleep(100);
        doClick(R.id.btn_message);
        sleep(2000);
        pressBack(TestConst.Aboutus_Back);
    }
}
