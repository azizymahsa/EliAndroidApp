package com.eligasht.reservation.views.ui;


import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static android.support.test.espresso.Espresso.onView;


public class LoginAndEditProfile extends BaseTest {
    public static LoginAndEditProfile newInstance() {
        return new LoginAndEditProfile();
    }

    @Override
    public void runTest() {
        doClick(R.id.btnMenu);
        sleep(750);
        doClick(R.id.lottieUserMenu);
        doClick(R.id.txt_email);
        doReplaceAndCloseKeyboard(R.id.txt_email, TestConst.Email);
        doPressImeActionButton(R.id.txt_email);
        doReplaceAndCloseKeyboard(R.id.txt_password, TestConst.Pass);
        doPressImeActionButton(R.id.txt_password);
        doClick(R.id.btnLogIn);
        sleep(3000);
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
        doScrollAndClickInScrollView(R.id.chB_woman);
        closeSoftKeyboard();
        doClick(R.id.btnSaveInfo);
        sleep(3000);
        pressBack(TestConst.LoginAndProfile_Back);
    }
}
