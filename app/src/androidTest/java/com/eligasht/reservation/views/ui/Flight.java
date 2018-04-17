package com.eligasht.reservation.views.ui;


import android.os.Bundle;
import android.support.test.espresso.DataInteraction;

import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;
import com.eligasht.service.helper.Const;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;


public class Flight extends BaseTest {

    public static Flight newInstance() {
        return new Flight();
    }

    @Override
    public void runTest() {
        doClick(R.id.linearLayout_mabda);
        doReplaceAndCloseKeyboard(R.id.searchtxt, TestConst.Origin);
        sleep(Const.MOCK ? 0 : 2000);
        doClickWithIndex(R.id.text1, 0);
        doClick(R.id.linearLayout_maghsad);
        doReplaceAndCloseKeyboard(R.id.searchtxt, TestConst.Dest);
        sleep(Const.MOCK ? 0 : 2000);
        doClickWithIndex(R.id.text1, 1);
        doClick(R.id.btnOne);
        doClick(R.id.btntwo);
        doClick(R.id.txtOption);
        doClick(R.id.txtOption);
        doClick(R.id.linear_tarikh_az_picker);
        sleep(500);
        doClickWithIndex(R.id.rootView, 39);
        sleep(500);
        doClickWithIndex(R.id.rootView, 41);
        doClick(R.id.accept);
        doClick(R.id.btnPlusB);
        doClick(R.id.btnPlusB);
        doClick(R.id.btnPlusB);
        doClick(R.id.btnPlusK);
        doClick(R.id.btnPlusN);
        doClick(R.id.searchPlan);
        sleep(Const.MOCK ? 1000 : 20000);
        onData(anything()).inAdapterView(withId(R.id.lvExp)).atPosition(0).perform(click());
        DataInteraction frameLayout6 = onData(anything())
                .inAdapterView(allOf(withId(R.id.lvExp),
                        childAtPosition(
                                withId(R.id.linear_expand),
                                0)))
                .atPosition(2);
        frameLayout6.perform(click());
        doClickAndScroll(R.id.mardS);
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
        doClickAndScroll(R.id.txttavalodm);
        doClick(R.id.ok);
        doClickAndScroll(R.id.txtexp_passport);
        doClick(R.id.ok);
        doClickAndScroll(R.id.btn_nextm);
        doCloseSoftKeyborad(R.id.txtnumber_passport);


        doClickAndScroll(R.id.mard);
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnamem, "ali");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtfamilym, "nasiri");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnumber_passport, "d123456");
        doClickAndScroll(R.id.txttavalodm);

        doClick(R.id.ok);

        doClickAndScroll(R.id.txtexp_passport);
        doClick(R.id.ok);
        doClickAndScroll(R.id.btn_nextm);
        doCloseSoftKeyborad(R.id.txtnumber_passport);


        doClickAndScroll(R.id.zan);
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnamem, "reza");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtfamilym, "nejati");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnumber_passport, "d123456");
        doClickAndScroll(R.id.txttavalodm);

        doClick(R.id.ok);

        doClickAndScroll(R.id.txtexp_passport);

        doClick(R.id.ok);
        doClickAndScroll(R.id.btn_nextm);
        doCloseSoftKeyborad(R.id.txtnumber_passport);


        doClickAndScroll(R.id.mard);
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnamem, "mahsa");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtfamilym, "azizi");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnumber_passport, "d123456");
        doClickAndScroll(R.id.txttavalodm);

        doClick(R.id.ok);

        doClickAndScroll(R.id.txtexp_passport);

        doClick(R.id.ok);
        doClickAndScroll(R.id.btn_nextm);
        doCloseSoftKeyborad(R.id.txtnumber_passport);


        doClickAndScroll(R.id.mard);
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnamem, "ali");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtfamilym, "farhadi");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnumber_passport, "d123456");
        doClickAndScroll(R.id.txttavalodm);

        doClick(R.id.ok);
        doClickAndScroll(R.id.txtexp_passport);
        doClick(R.id.ok);
        doClickAndScroll(R.id.btn_nextm);
        doCloseSoftKeyborad(R.id.txtnumber_passport);


        doClickAndScroll(R.id.mard);
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnamem, "reza");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtfamilym, "nejati");
        doScrollAndREplaceAndCloseKeyboard(R.id.txtnumber_passport, "d123456");
        doClickAndScroll(R.id.txttavalodm);
        doClick(R.id.ok);
        doClickAndScroll(R.id.txtexp_passport);
        doClick(R.id.ok);
        doClickAndScroll(R.id.btn_nextm);
        doCloseSoftKeyborad(R.id.txtnumber_passport);
        sleep(Const.MOCK ? 0 : 5000);


        try {
            doClickWithIndex(R.id.btnAddsabad, 0);
            doClickWithIndex(R.id.btnAddsabad, 1);
            doClickWithIndex(R.id.btnAddsabad, 2);
        } catch (Exception e) {

        }


        doClick(R.id.btn_taeed_khadamat);
        sleep(Const.MOCK ? 2000 : 5000);
        pressBack(TestConst.Flight_Back);
    }
}
