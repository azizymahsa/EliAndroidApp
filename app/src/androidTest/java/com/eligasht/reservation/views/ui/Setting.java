package com.eligasht.reservation.views.ui;

import android.os.Bundle;

import com.eligasht.R;

/**
 * Created by Ahmad.nemati on 4/17/2018.
 */
public class Setting extends BaseTest {
    public static Setting newInstance() {
        return new Setting();
    }

    @Override
    public void runTest() {
        doClick(R.id.btnMenu);
        doClick(R.id.btn_setting);
        doClick(R.id.languageSpinner);
        doClickItemInSpinner(1);
        doClick(R.id.tvConfirm);
        AppStartup.newInstance().runTest();
    }
}
