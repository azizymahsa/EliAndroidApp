package com.eligasht.reservation.views.ui;


import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;


public class ContactUs extends BaseTest {

    public static ContactUs newInstance() {
        ContactUs fragment = new ContactUs();
        return fragment;
    }
    @Override
    public void runTest() {
        doClick(R.id.btnMenu);
        doClick(R.id.btnContactUs);
        sleep(2500);
        doClick(R.id.btnBack);
        doClick(R.id.btnMenu);
       /* doClick(R.id.edit_email_resetPass);
        doReplace(R.id.edit_email_resetPass, TestConst.Email_Forget);
        doPressImeActionButton(R.id.edit_email_resetPass);
        doClick(R.id.btnResetPassword);*/
        sleep(2500);
    }
}
