package com.eligasht.reservation.views.ui;


import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;

import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;

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


public class Login extends BaseTest {
    public static Login newInstance() {
        Login fragment = new Login();
        return fragment;
    }
    @Override
    public void runTest() {
        onView(withId(R.id.btnMenu)).perform(click());
        sleep(1000);
        doClick(R.id.lottieUserMenu);
        doClick(R.id.txt_email);
        onView(withId(R.id.txt_email)).perform(replaceText(TestConst.Email), closeSoftKeyboard());
        onView(withId(R.id.txt_email)).perform(pressImeActionButton());
        onView(withId(R.id.txt_password)).perform(replaceText(TestConst.Pass), closeSoftKeyboard());
        onView(withId(R.id.txt_password)).perform(pressImeActionButton());
        onView(withId(R.id.btnLogIn)).perform(click());
        sleep(3000);
    }
}
