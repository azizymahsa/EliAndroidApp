package com.eligasht.reservation.views.ui;

import android.os.Bundle;

import com.eligasht.R;

/**
 * Created by Ahmad.nemati on 4/17/2018.
 */
public class Profile extends BaseTest {

    public static Profile newInstance() {
        Profile fragment = new Profile();
        return fragment;
    }

    @Override
    public void runTest() {
        doClick(R.id.btnMenu);
        doClick(R.id.lottieUserMenu);
        doClickTab(R.id.tab_layout, 1);
        doClickTab(R.id.tab_layout, 0);
        doClickTab(R.id.tab_layout, 2);
        doReplace(R.id.edt_name_fa, "احمد");
        doPressImeActionButton(R.id.edt_name_fa);
        doReplace(R.id.edt_last_name_fa, "نعمتی");
        doPressImeActionButton(R.id.edt_last_name_fa);
        doReplace(R.id.edt_name_En, "ahmad");
        doPressImeActionButton(R.id.edt_name_En);
        doReplace(R.id.edt_last_name_En, "nemati");
        doPressImeActionButton(R.id.edt_last_name_En);
        doReplace(R.id.edt_code_meli, "0480747480");
        doPressImeActionButton(R.id.edt_code_meli);
        doClick(R.id.rlBirthday);
        doClick(R.id.ok);
        doScrollAndClickInScrollView(R.id.edt_home_phone);
        doReplace(R.id.edt_home_phone, "44046374");
        doScrollAndClickInScrollView(R.id.edt_mobile);
        doReplace(R.id.edt_mobile, "09375274345");
        doPressImeActionButton(R.id.edt_mobile);
        doScrollAndClickInScrollView(R.id.edt_address);
        doReplace(R.id.edt_address, "خیابان ازادی جنب بانک ملت کوچه اول پلاک ۲۰");
        doScrollAndClickInScrollView(R.id.chB_man);
        doClick(R.id.btnSaveInfo);
        sleep(3000);
    }
}
