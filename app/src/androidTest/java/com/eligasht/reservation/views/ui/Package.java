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

        doClick(R.id.btnMenu);
        doClick(R.id.btnPackage);
        sleep(Const.MOCK ? 750 : 3000);
        doClick(R.id.txtCity);
        doClickWithIndex(R.id.text1, 1);
        doClick(R.id.layout_room);
        pressBack(1);
        doClick(R.id.btnSearchPackage);
        sleep(Const.MOCK ? 200 : 4000);
        doClickWithIndex(R.id.ivBigImage, 0);
        sleep(Const.MOCK ? 200 : 3000);
        doClickTab(R.id.tab_layout, 1);
        sleep(500);
        doClickTab(R.id.tab_layout, 0);
        sleep(500);
        doClickTab(R.id.tab_layout, 2);
        sleep(500);
        pressBack(2);


        //  doClickWithIndexInScroll(R.id.packageBooking, 0);



//        doClickAndScroll(R.id.mardS);
//        doScrollAndREplaceAndCloseKeyboard(R.id.txtnameP, "احمد");
//        doScrollAndREplaceAndCloseKeyboard(R.id.txtfamilyP, "نعمتی");
//        doScrollAndREplaceAndCloseKeyboard(R.id.txtmobileP, "09375272862");
//        doScrollAndREplaceAndCloseKeyboard(R.id.txtkodemeliP, "0480747450");
//        doScrollAndREplaceAndCloseKeyboard(R.id.txtemeliP, "nemati@nemati.com");
//        closeSoftKeyboard();
//
//
//        doClickAndScroll(R.id.btn_next_partnerInfo);
//
//
//        doClickAndScroll(R.id.mard);
//        doScrollAndREplaceAndCloseKeyboard(R.id.txtnamem, "ahmad");
//        doScrollAndREplaceAndCloseKeyboard(R.id.txtfamilym, "nemati");
//        doScrollAndREplaceAndCloseKeyboard(R.id.txtnumber_passport, "d123456");
//        doClickAndScroll(R.id.txttavalodm);
//        doClick(R.id.ok);
//        doClickAndScroll(R.id.txtexp_passport);
//        doClick(R.id.ok);
//        doClickAndScroll(R.id.btn_nextm);
//        doCloseSoftKeyborad(R.id.txtnumber_passport);
//
//        sleep(Const.MOCK ? 200 : 3000);
        //pressBack(4);

    }


}
