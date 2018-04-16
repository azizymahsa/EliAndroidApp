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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest4 {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void splashActivityTest4() {
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
            Thread.sleep(150);
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
            Thread.sleep(150);
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
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
            Thread.sleep(150);
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
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.btnOne), withText("رفت"),
                        childAtPosition(
                                allOf(withId(R.id.llButton),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatTextView3.perform(click());
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.btntwo), withText("رفت و برگشت"),
                        childAtPosition(
                                allOf(withId(R.id.llButton),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction iRANSansMe = onView(
                allOf(withId(R.id.accept), withText("تایید"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                9),
                        isDisplayed()));
        iRANSansMe.perform(click());

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnPlusB), withText("\uE891"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnPlusB), withText("\uE891"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btnPlusK), withText("\uE891"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btnPlusN), withText("\uE891"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.searchPlan), withText("جستجو"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView5.perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onData(anything()).inAdapterView(withId(R.id.lvExp)).atPosition(0).perform(click());
        try {
            Thread.sleep(2000);
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



//         onData(
//                allOf(withId(R.id.txt_economi), withText("اکونومی"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.linear_all),
//                                        2),
//                                1)
//                       )).perform(scrollTo(),click());




        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText35 = onView(
                allOf(withId(R.id.txtnameP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatEditText35.perform(scrollTo(), click());

        ViewInteraction appCompatEditText42 = onView(
                allOf(withId(R.id.txtnameP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatEditText42.perform(scrollTo(), replaceText("ا"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.txtnameP), withText("ا"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatEditText5.perform(scrollTo(), replaceText(""));

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.txtnameP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.txtnameP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatEditText7.perform(scrollTo(), replaceText("احم"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.txtnameP), withText("احم"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatEditText8.perform(scrollTo(), replaceText("احمد"));

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.txtnameP), withText("احمد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText9.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.txtnameP), withText("احمد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatEditText10.perform(pressImeActionButton());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.txtnameP), withText("احمد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatEditText11.perform(scrollTo(), replaceText("احمدنع"));

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.txtnameP), withText("احمدنع"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText12.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.txtfamilyP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText13.perform(scrollTo(), replaceText("نعمتی"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.txtfamilyP), withText("نعمتی"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText14.perform(pressImeActionButton());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.txtmobileP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText15.perform(scrollTo(), replaceText("09375274"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.txtmobileP), withText("09375274"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText16.perform(scrollTo(), replaceText("09375274741"));

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.txtmobileP), withText("09375274741"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatEditText17.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.txtmobileP), withText("09375274741"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText18.perform(pressImeActionButton());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.txtkodemeliP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatEditText19.perform(scrollTo(), replaceText("0"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.txtkodemeliP), withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatEditText20.perform(scrollTo(), replaceText("048074"));

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.txtkodemeliP), withText("048074"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatEditText21.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.txtkodemeliP), withText("048074"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatEditText22.perform(scrollTo(), replaceText("0480747458"));

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.txtkodemeliP), withText("0480747458"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatEditText23.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.txtkodemeliP), withText("0480747458"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatEditText24.perform(pressImeActionButton());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.txtemeliP),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1)));
        appCompatEditText25.perform(scrollTo(), replaceText("n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.txtemeliP), withText("n"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1)));
        appCompatEditText26.perform(scrollTo(), replaceText("ne@ne.com"));

        ViewInteraction appCompatEditText27 = onView(
                allOf(withId(R.id.txtemeliP), withText("ne@ne.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatEditText27.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText28 = onView(
                allOf(withId(R.id.txtemeliP), withText("ne@ne.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1)));
        appCompatEditText28.perform(pressImeActionButton());

        ViewInteraction linearLayout30 = onView(
                allOf(withId(R.id.btn_next_partnerInfo),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scroll_partner),
                                        0),
                                6)));
        linearLayout30.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText29 = onView(
                allOf(withId(R.id.txtnamem),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText29.perform(scrollTo(), replaceText("ahmad"), closeSoftKeyboard());

        ViewInteraction appCompatEditText30 = onView(
                allOf(withId(R.id.txtnamem), withText("ahmad"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatEditText30.perform(pressImeActionButton());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText31 = onView(
                allOf(withId(R.id.txtfamilym),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText31.perform(scrollTo(), replaceText("n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText32 = onView(
                allOf(withId(R.id.txtfamilym), withText("n"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText32.perform(scrollTo(), replaceText("nemati"));

        ViewInteraction appCompatEditText33 = onView(
                allOf(withId(R.id.txtfamilym), withText("nemati"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatEditText33.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText34 = onView(
                allOf(withId(R.id.txtfamilym), withText("nemati"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatEditText34.perform(pressImeActionButton());

        ViewInteraction appCompatEditText350 = onView(
                allOf(withId(R.id.txtnumber_passport),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText350.perform(scrollTo(), replaceText("d"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText36 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText36.perform(scrollTo(), replaceText("d12345"));

        ViewInteraction appCompatEditText37 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d12345"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatEditText37.perform(closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText38 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d12345"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText38.perform(pressImeActionButton());

        ViewInteraction appCompatTextView40 = onView(
                allOf(withId(R.id.txttavalodm),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        6),
                                1)));
        appCompatTextView40.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton200 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton200.perform(click());


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatTextView400 = onData(
                allOf(withId(R.id.txtexp_passport),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        8),
                                1)));
        appCompatTextView400.perform(scrollTo(), click());


        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton32 = onView(
                allOf(withId(R.id.ok), withText("OK"),
                        childAtPosition(
                                allOf(withId(R.id.done_background),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton32.perform(scrollTo(),click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout4 = onView(
                allOf(withId(R.id.btn_nextm),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrolMosafer),
                                        0),
                                11)));
        linearLayout4.perform(scrollTo(), click());

        ViewInteraction fancyButton = onView(
                allOf(withId(R.id.btnOk), withText("باشه"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.custom),
                                        0),
                                2),
                        isDisplayed()));
        fancyButton.perform(click());

        ViewInteraction appCompatEditText39 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d12345"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText39.perform(scrollTo(), click());

        ViewInteraction appCompatEditText40 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d12345"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText40.perform(scrollTo(), replaceText("d123456"));

        ViewInteraction appCompatEditText41 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d123456"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1),
                        isDisplayed()));
        appCompatEditText41.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText420 = onView(
                allOf(withId(R.id.txtnumber_passport), withText("d123456"),
                        childAtPosition(
                                allOf(withId(R.id.linear_number_passport),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatEditText420.perform(pressImeActionButton());

        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withId(R.id.mard), withText("مرد"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatRadioButton2.perform(scrollTo(), click());

        ViewInteraction linearLayout5 = onView(
                allOf(withId(R.id.btn_nextm),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrolMosafer),
                                        0),
                                11)));
        linearLayout5.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.btnAddsabad),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                2),
                        isDisplayed()));
        relativeLayout.perform(click());

        DataInteraction linearLayout6 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listKhadamat),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(1);
        linearLayout6.perform(scrollTo(), click());

        ViewInteraction relativeLayout2 = onView(
                allOf(withId(R.id.btnAddsabad),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                2),
                        isDisplayed()));
        relativeLayout2.perform(click());

        ViewInteraction linearLayout7 = onView(
                allOf(withId(R.id.btn_taeed_khadamat),
                        childAtPosition(
                                allOf(withId(R.id.linear_list_khadamat),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        linearLayout7.perform(click());

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
}
