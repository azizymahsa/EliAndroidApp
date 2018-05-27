package com.eligasht.reservation.views.ui;


import android.os.Bundle;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.eligasht.R;
import com.eligasht.service.helper.Const;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


public class Package extends BaseTest {

    public static Package newInstance() {
        return new Package();
    }

    @Override
    public void runTest() {
        sleep(500);
        doClick(R.id.btnMenu);
        sleep(750);
        doClick(R.id.btnPackage);
        sleep(2000);
        doClick(R.id.txtCity);
        sleep(1500);
        doClickWithIndex(R.id.text1, 0);
        doClick(R.id.layout_room);
        pressBack(1);
        doClick(R.id.btnSearchPackage);
        sleep(6000);
        doClickWithIndex(R.id.ivBigImage, 0);
        sleep(500);
        doClickTab(R.id.tab_layout, 1);
        sleep(500);
        doClickTab(R.id.tab_layout, 0);
        sleep(500);
        doClickTab(R.id.tab_layout, 2);
        sleep(500);
        pressBack(2);


    }


}
