package com.eligasht.reservation.views.ui;

import android.support.annotation.IdRes;
import android.support.test.espresso.action.ViewActions;
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
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

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
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

/**
 * Created by Ahmad.nemati on 4/16/2018.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class BaseTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    public static Matcher<View> childAtPosition(
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

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {

        }
    }

    public void pressBack(int count) {
        for (int i = 0; i < count; i++) {
            onView(isRoot()).perform(ViewActions.pressBack());
        }

    }

    public void pause() {
        sleep(5000);
    }

    protected void doClickAndScroll(@IdRes int view) {
        onView(withId(view)).perform(ViewActions.scrollTo(), ViewActions.click());
    }

    protected void doClick(@IdRes int view) {
        onView(withId(view)).perform(ViewActions.click());
    }

    protected void doScroll(@IdRes int view) {
        onView(withId(view)).perform(ViewActions.scrollTo());
    }


    protected void doCloseSoftKeyborad(@IdRes int view) {
        onView(withId(view)).perform(ViewActions.closeSoftKeyboard());
    }

    protected void doPressImeActionButton(@IdRes int view) {
        onView(withId(view)).perform(ViewActions.pressImeActionButton());
    }

    protected void doReplace(@IdRes int view, String text) {
        onView(withId(view)).perform(replaceText(text));
    }


    protected void doReplaceAndCloseKeyboard(@IdRes int view, String text) {
        onView(withId(view)).perform(replaceText(text), closeSoftKeyboard());
    }

    protected void doScrollAndREplaceAndCloseKeyboard(@IdRes int view, String text) {
        onView(withId(view)).perform(scrollTo(), replaceText(text), closeSoftKeyboard());
    }


    protected void doClickWithIndex(@IdRes int view, int index) {
        onView(withIndex(withId(view), index)).perform(click());
    }


    protected void login() {
        Login login = new Login();
        login.loginTest();
    }
}
