package com.eligasht.reservation.views.ui;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.eligasht.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest10 {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void splashActivityTest10() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.linearLayout_mabda),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.citySearch),
                                        2),
                                1),
                        isDisplayed()));
        linearLayout.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.searchtxt),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("تهران"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.text1), withText("تهران ( همه فرودگاه ها ),نزدیک تهران, ایران"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.llContentLayout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.linearLayout_maghsad),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.citySearch),
                                        2),
                                2),
                        isDisplayed()));
        linearLayout2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.searchtxt),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("استان"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.searchtxt), withText("استان"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("استانبول"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.searchtxt), withText("استانبول"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.text1), withText("استانبول ( همه فرودگاه ها ),نزدیک استانبول, ترکیه"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.llContentLayout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView500 = onView(
                allOf(withId(R.id.btnOne), withText("رفت"),
                        childAtPosition(
                                allOf(withId(R.id.llButton),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatTextView500.perform(click());

        ViewInteraction appCompatTextView200 = onView(
                allOf(withId(R.id.btntwo), withText("رفت و برگشت"),
                        childAtPosition(
                                allOf(withId(R.id.llButton),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatTextView200.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.txtOption),
                        childAtPosition(
                                allOf(withId(R.id.citySearch),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction relativeLayout2 = onView(
                allOf(withId(R.id.txtOption),
                        childAtPosition(
                                allOf(withId(R.id.citySearch),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        relativeLayout2.perform(click());

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.linear_tarikh_az_picker),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cvDate),
                                        0),
                                1),
                        isDisplayed()));
        linearLayout3.perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.rootView),
                        childAtPosition(
                                allOf(withId(R.id.calendar_rv),
                                        childAtPosition(
                                                withClassName(is("android.support.percent.PercentRelativeLayout")),
                                                8)),
                                39),
                        isDisplayed()));
        frameLayout.perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction frameLayout2 = onView(
                allOf(withId(R.id.rootView),
                        childAtPosition(
                                allOf(withId(R.id.calendar_rv),
                                        childAtPosition(
                                                withClassName(is("android.support.percent.PercentRelativeLayout")),
                                                8)),
                                41),
                        isDisplayed()));
        frameLayout2.perform(click());

        ViewInteraction iRANSansMe = onView(
                allOf(withId(R.id.accept), withText("تایید"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                9),
                        isDisplayed()));
        iRANSansMe.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnPlusB), withText("\uE891"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnPlusB), withText("\uE891"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btnPlusB), withText("\uE891"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btnPlusK), withText("\uE891"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btnPlusN), withText("\uE891"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                2),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.searchPlan), withText("جستجو"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onData(anything()).inAdapterView(withId(R.id.lvExp)).atPosition(0).perform(click());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  onData(anything()).inAdapterView(withId(R.id.lvExp)).onChildView(withId(R.id.txt_economi)).atPosition(0).perform(click());
        //  onData(anything()).onChildView(withId(R.id.btnSelect)).atPosition(0).perform(click());

//        DataInteraction appCompatButton60 = onData(
//               withId(R.id.llCounter)
//                       ).inAdapterView(withId(R.id.lvExp)).atPosition(0);
//        appCompatButton60.perform(click());

        DataInteraction frameLayout6 = onData(anything())
                .inAdapterView(allOf(withId(R.id.lvExp),
                        childAtPosition(
                                withId(R.id.linear_expand),
                                0)))
                .atPosition(2);
        frameLayout6.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.mardS), withText("مرد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatRadioButton.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.txtnameP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatEditText5.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.txtnameP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatEditText6.perform(scrollTo(), replaceText("احمد"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.txtnameP), withText("احمد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatEditText7.perform(pressImeActionButton());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.txtfamilyP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText8.perform(scrollTo(), replaceText("نعم"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.txtfamilyP), withText("نعم"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText9.perform(scrollTo(), replaceText("نعمتی"));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.txtfamilyP), withText("نعمتی"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1),
                        isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.txtfamilyP), withText("نعمتی"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText11.perform(pressImeActionButton());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.txtmobileP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText12.perform(scrollTo(), replaceText("0"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.txtmobileP), withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText13.perform(scrollTo(), replaceText("0937527"));

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.txtmobileP), withText("0937527"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatEditText14.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.txtmobileP), withText("0937527"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText15.perform(scrollTo(), replaceText("09375272841"));

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.txtmobileP), withText("09375272841"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatEditText16.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.txtmobileP), withText("09375272841"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText17.perform(pressImeActionButton());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.txtkodemeliP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatEditText18.perform(scrollTo(), replaceText("0480747"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.txtkodemeliP), withText("0480747"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatEditText19.perform(scrollTo(), replaceText("0480747485"));

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.txtkodemeliP), withText("0480747485"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatEditText20.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.txtkodemeliP), withText("0480747485"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatEditText21.perform(pressImeActionButton());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.txtemeliP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1)));
        appCompatEditText22.perform(scrollTo(), replaceText("n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.txtemeliP), withText("n"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1)));
        appCompatEditText23.perform(scrollTo(), replaceText("ne@n"));

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.txtemeliP), withText("ne@n"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatEditText24.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.txtemeliP), withText("ne@n"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1)));
        appCompatEditText25.perform(scrollTo(), replaceText("ne@ne.com"));

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.txtemeliP), withText("ne@ne.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatEditText26.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText27 = onView(
                allOf(withId(R.id.txtemeliP), withText("ne@ne.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1)));
        appCompatEditText27.perform(pressImeActionButton());

        ViewInteraction linearLayout5 = onView(
                allOf(withId(R.id.btn_next_partnerInfo),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll_partner),
                                        0),
                                6)));
        linearLayout5.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withId(R.id.mard), withText("مرد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatRadioButton2.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText28 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText28.perform(scrollTo(), replaceText("ahmad"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText29 = onView(
                allOf(withId(R.id.txtnamem), withText("ahmad"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText29.perform(pressImeActionButton());

        ViewInteraction appCompatEditText30 = onView(
                allOf(withId(R.id.txtfamilym),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText30.perform(scrollTo(), replaceText("n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText31 = onView(
                allOf(withId(R.id.txtfamilym), withText("n"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText31.perform(scrollTo(), replaceText("nemati"));

        ViewInteraction appCompatEditText32 = onView(
                allOf(withId(R.id.txtfamilym), withText("nemati"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatEditText32.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText33 = onView(
                allOf(withId(R.id.txtfamilym), withText("nemati"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText33.perform(pressImeActionButton());

        ViewInteraction appCompatEditText34 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText34.perform(scrollTo(), replaceText("d1"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText35 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d1"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText35.perform(scrollTo(), replaceText("d123456"));

        ViewInteraction appCompatEditText36 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d123456"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatEditText36.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatEditText37 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d123456"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText37.perform(pressImeActionButton());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html


        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.txttavalodm),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                1)));
        appCompatTextView4.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton6.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.txtexp_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_expdate),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                7)),
                                1)));
        appCompatTextView5.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton7.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout6 = onView(
                allOf(withId(R.id.btn_nextm),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrolMosafer),
                                        0),
                                11)));
        linearLayout6.perform(scrollTo(), click());

//        ViewInteraction appCompatEditText38 = onView(
//                allOf(withId(R.id.txtnumber_passport), withText("d123456"),
//                        childAtPosition(
//                                allOf(withId(R.id.linear_number_passport),
//                                        childAtPosition(
//                                                withClassName(is("android.widget.LinearLayout")),
//                                                4)),
//                                1)));
//        appCompatEditText38.perform(scrollTo(), replaceText(""));

        ViewInteraction appCompatEditText39 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatEditText39.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html

        ViewInteraction appCompatRadioButton3 = onView(
                allOf(withId(R.id.mard), withText("مرد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatRadioButton3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText100 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText100.perform(scrollTo(), click());

        ViewInteraction appCompatEditText41 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText41.perform(scrollTo(), replaceText("ahmad"), closeSoftKeyboard());

        ViewInteraction appCompatEditText42 = onView(
                allOf(withId(R.id.txtnamem), withText("ahmad"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText42.perform(pressImeActionButton());

        ViewInteraction appCompatEditText43 = onView(
                allOf(withId(R.id.txtfamilym),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText43.perform(scrollTo(), replaceText("nemati"), closeSoftKeyboard());

        ViewInteraction appCompatEditText44 = onView(
                allOf(withId(R.id.txtfamilym), withText("nemati"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText44.perform(pressImeActionButton());

        ViewInteraction appCompatEditText45 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText45.perform(scrollTo(), replaceText("d123456"), closeSoftKeyboard());

        ViewInteraction appCompatEditText46 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d123456"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText46.perform(pressImeActionButton());

        ViewInteraction appCompatTextView6 = onView(
                allOf(withId(R.id.txttavalodm),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                1)));
        appCompatTextView6.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatTextView7 = onView(
                allOf(withId(R.id.txtexp_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_expdate),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                7)),
                                1)));
        appCompatTextView7.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction linearLayout7 = onView(
                allOf(withId(R.id.btn_nextm),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrolMosafer),
                                        0),
                                11)));
        linearLayout7.perform(scrollTo(), click());

//        ViewInteraction appCompatEditText47 = onView(
//                allOf(withId(R.id.txtnumber_passport), withText("d123456"),
//                        childAtPosition(
//                                allOf(withId(R.id.linear_number_passport),
//                                        childAtPosition(
//                                                withClassName(is("android.widget.LinearLayout")),
//                                                4)),
//                                1)));
//        appCompatEditText47.perform(scrollTo(), replaceText(""));

        ViewInteraction appCompatEditText48 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatEditText48.perform(closeSoftKeyboard());

        ViewInteraction appCompatRadioButton4 = onView(
                allOf(withId(R.id.mard), withText("مرد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatRadioButton4.perform(scrollTo(), click());

        ViewInteraction appCompatEditText49 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText49.perform(scrollTo(), click());

        ViewInteraction appCompatEditText251 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText251.perform(scrollTo(), replaceText("ahmad"), closeSoftKeyboard());

        ViewInteraction appCompatEditText51 = onView(
                allOf(withId(R.id.txtnamem), withText("ahmad"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText51.perform(pressImeActionButton());

        ViewInteraction appCompatEditText52 = onView(
                allOf(withId(R.id.txtfamilym),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText52.perform(scrollTo(), replaceText("nemati"), closeSoftKeyboard());

        ViewInteraction appCompatEditText53 = onView(
                allOf(withId(R.id.txtfamilym), withText("nemati"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText53.perform(pressImeActionButton());

        ViewInteraction appCompatEditText54 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText54.perform(scrollTo(), replaceText("d123456"), closeSoftKeyboard());

        ViewInteraction appCompatEditText55 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d123456"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText55.perform(pressImeActionButton());

        ViewInteraction appCompatTextView8 = onView(
                allOf(withId(R.id.txttavalodm),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                1)));
        appCompatTextView8.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction appCompatTextView9 = onView(
                allOf(withId(R.id.txtexp_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_expdate),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                7)),
                                1)));
        appCompatTextView9.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton11.perform(click());

        ViewInteraction linearLayout8 = onView(
                allOf(withId(R.id.btn_nextm),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrolMosafer),
                                        0),
                                11)));
        linearLayout8.perform(scrollTo(), click());



        ViewInteraction appCompatEditText57 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatEditText57.perform(closeSoftKeyboard());

        ViewInteraction appCompatRadioButton5 = onView(
                allOf(withId(R.id.mard), withText("مرد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatRadioButton5.perform(scrollTo(), click());

        ViewInteraction appCompatEditText58 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText58.perform(scrollTo(), click());

        ViewInteraction appCompatEditText59 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText59.perform(scrollTo(), replaceText("ahmad"), closeSoftKeyboard());

        ViewInteraction appCompatEditText60 = onView(
                allOf(withId(R.id.txtnamem), withText("ahmad"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText60.perform(pressImeActionButton());

        ViewInteraction appCompatEditText61 = onView(
                allOf(withId(R.id.txtfamilym),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText61.perform(scrollTo(), replaceText("nemati"), closeSoftKeyboard());

        ViewInteraction appCompatEditText62 = onView(
                allOf(withId(R.id.txtfamilym), withText("nemati"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText62.perform(pressImeActionButton());

        ViewInteraction appCompatEditText63 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText63.perform(scrollTo(), replaceText("d123456"), closeSoftKeyboard());

        ViewInteraction appCompatEditText64 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d123456"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText64.perform(pressImeActionButton());

        ViewInteraction appCompatTextView10 = onView(
                allOf(withId(R.id.txttavalodm),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                1)));
        appCompatTextView10.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatTextView11 = onView(
                allOf(withId(R.id.txtexp_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_expdate),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                7)),
                                1)));
        appCompatTextView11.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton13.perform(click());

        ViewInteraction linearLayout9 = onView(
                allOf(withId(R.id.btn_nextm),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrolMosafer),
                                        0),
                                11)));
        linearLayout9.perform(scrollTo(), click());



        ViewInteraction appCompatEditText66 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatEditText66.perform(closeSoftKeyboard());

        ViewInteraction appCompatRadioButton6 = onView(
                allOf(withId(R.id.mard), withText("مرد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatRadioButton6.perform(scrollTo(), click());

        ViewInteraction appCompatRadioButton7 = onView(
                allOf(withId(R.id.zan), withText("زن"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0)));
        appCompatRadioButton7.perform(scrollTo(), click());

        ViewInteraction appCompatEditText67 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText67.perform(scrollTo(), click());

        ViewInteraction appCompatEditText68 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText68.perform(scrollTo(), replaceText("ahmad"), closeSoftKeyboard());

        ViewInteraction appCompatEditText69 = onView(
                allOf(withId(R.id.txtnamem), withText("ahmad"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText69.perform(pressImeActionButton());

        ViewInteraction appCompatEditText70 = onView(
                allOf(withId(R.id.txtfamilym),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText70.perform(scrollTo(), replaceText("nemati"), closeSoftKeyboard());

        ViewInteraction appCompatEditText71 = onView(
                allOf(withId(R.id.txtfamilym), withText("nemati"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText71.perform(pressImeActionButton());

        ViewInteraction appCompatEditText72 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText72.perform(scrollTo(), replaceText("d123456"), closeSoftKeyboard());

        ViewInteraction appCompatEditText73 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d123456"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText73.perform(pressImeActionButton());

        ViewInteraction appCompatTextView12 = onView(
                allOf(withId(R.id.txttavalodm),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                1)));
        appCompatTextView12.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton14.perform(click());

        ViewInteraction appCompatTextView13 = onView(
                allOf(withId(R.id.txtexp_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_expdate),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                7)),
                                1)));
        appCompatTextView13.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton15.perform(click());

        ViewInteraction linearLayout10 = onView(
                allOf(withId(R.id.btn_nextm),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrolMosafer),
                                        0),
                                11)));
        linearLayout10.perform(scrollTo(), click());


        ViewInteraction appCompatEditText75 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatEditText75.perform(closeSoftKeyboard());

        ViewInteraction appCompatRadioButton8 = onView(
                allOf(withId(R.id.zan), withText("زن"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0)));
        appCompatRadioButton8.perform(scrollTo(), click());

        ViewInteraction appCompatEditText76 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText76.perform(scrollTo(), click());

        ViewInteraction appCompatEditText77 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText77.perform(scrollTo(), replaceText("ahmad"), closeSoftKeyboard());

        ViewInteraction appCompatEditText78 = onView(
                allOf(withId(R.id.txtnamem), withText("ahmad"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText78.perform(pressImeActionButton());

        ViewInteraction appCompatEditText79 = onView(
                allOf(withId(R.id.txtfamilym),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText79.perform(scrollTo(), replaceText("nematiii"), closeSoftKeyboard());

        ViewInteraction appCompatEditText80 = onView(
                allOf(withId(R.id.txtfamilym), withText("nematiii"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText80.perform(pressImeActionButton());

        ViewInteraction appCompatEditText81 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText81.perform(scrollTo(), replaceText("d123456"), closeSoftKeyboard());

        ViewInteraction appCompatEditText82 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d123456"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText82.perform(pressImeActionButton());

        ViewInteraction appCompatTextView14 = onView(
                allOf(withId(R.id.txttavalodm),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                1)));
        appCompatTextView14.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton16.perform(click());

        ViewInteraction appCompatTextView15 = onView(
                allOf(withId(R.id.txtexp_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_expdate),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                7)),
                                1)));
        appCompatTextView15.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton17 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton17.perform(click());

        ViewInteraction linearLayout11 = onView(
                allOf(withId(R.id.btn_nextm),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrolMosafer),
                                        0),
                                11)));
        linearLayout11.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        onData(withId(R.id.btnAddsabad))
//                .inAdapterView(withId(R.id.listKhadamat))
//                .atPosition(0)
//                .perform(click());

        onView(withIndex(withId(R.id.btnAddsabad), 0)).perform(click());
        onView(withIndex(withId(R.id.btnAddsabad), 1)).perform(click());
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        onData(withId(R.id.btnAddsabad))
//                .inAdapterView(withId(R.id.listKhadamat))
//                .atPosition(1)
//                .perform(click());


//        ViewInteraction relativeLayout3 = onView(
//                allOf(withId(R.id.btnAddsabad),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.LinearLayout")),
//                                        2),
//                                2),
//                        isDisplayed()));
//        relativeLayout3.perform(click());
//
//        ViewInteraction relativeLayout4 = onView(
//                allOf(withId(R.id.btnAddsabad),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.LinearLayout")),
//                                        2),
//                                2),
//                        isDisplayed()));
//        relativeLayout4.perform(click());

        ViewInteraction linearLayout12 = onView(
                allOf(withId(R.id.btn_taeed_khadamat),
                        childAtPosition(
                                allOf(withId(R.id.linear_list_khadamat),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        linearLayout12.perform(click());

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }
}
