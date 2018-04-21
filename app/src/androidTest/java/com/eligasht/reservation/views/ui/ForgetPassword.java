package com.eligasht.reservation.views.ui;


import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;

import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;
import com.eligasht.reservation.views.ui.BaseTest;
import com.eligasht.reservation.views.ui.SplashActivity;
import com.eligasht.service.generator.SingletonService;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


public class ForgetPassword extends BaseTest {

    public static ForgetPassword newInstance() {
       return new ForgetPassword();
    }
    @Override
    public void runTest() {
      try {
          doClick(R.id.btnMenu);
      }
      catch (Exception e)
      {
          sleep(10000);
          doClick(R.id.btnMenu);
      }
        doClick(R.id.lottieUserMenu);
        if (SingletonService.getInstance().getContext().getResources().getBoolean(R.bool.isTablet))
            pressBack(1);
        doClick(R.id.layout_reset_password);
        doClick(R.id.edit_email_resetPass);
        doReplace(R.id.edit_email_resetPass, TestConst.Email_Forget);
        doPressImeActionButton(R.id.edit_email_resetPass);
        doClick(R.id.btnResetPassword);
        sleep(2500);
        doClick(R.id.llHome);
    }
}
