package com.eligasht.reservation.views.ui;


import android.os.Bundle;
import android.support.test.espresso.ViewInteraction;
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
import static android.support.test.espresso.Espresso.pressBack;
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


public class Insurance extends BaseTest {
    public static Insurance newInstance() {
       return new Insurance();
    }

    @Override
    public void runTest() {

        doClick(R.id.btnMenu);
        sleep(750);
        doClick(R.id.btnInsurance);
        sleep(750);
        doClick(R.id.txtCity);
        doReplaceAndCloseKeyboard(R.id.searchtxt, "ترکیه");
        sleep(50);
        doClickWithIndex(R.id.text1, 0);
        doClick(R.id.layout_during_travel);
        doClick(R.id.btnOk);
        doClick(R.id.layout_passenger);
        doClick(R.id.layout_birthday);
        doClick(R.id.ok);
        doClick(R.id.btn_confirm);
        doClick(R.id.btnSearchInsurance);
        sleep(50);
        doClickWithIndex(R.id.btn_details, 0);
        pressBack(1);
        doClickWithIndex(R.id.btn_buy, 0);
        sleep(1000);
        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.mardS), withText("مرد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatRadioButton.perform(scrollTo(), click());

        doScrollAndREplaceAndCloseKeyboard(R.id.txtnameP, "احمد");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtfamilyP, "نعمتی");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtmobileP, "09375272862");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtkodemeliP, "0480747450");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtemeliP, "nemati@nemati.com");
        closeSoftKeyboard();


        doClickAndScroll(R.id.btn_next_partnerInfo);


        doClickAndScroll(R.id.mard);
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnamem, "ahmad");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtfamilym, "nemati");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnumber_passport, "d123456");
        doClickAndScroll(R.id.txtexp_passport);
        doClick(R.id.ok);
        doClickAndScroll(R.id.btn_nextm);
        doCloseSoftKeyborad(R.id.txtnumber_passport);
        sleep(200);
        pressBack(4);


    }


}
