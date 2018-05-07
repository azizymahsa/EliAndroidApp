package com.eligasht.reservation.views.ui;

import android.support.test.espresso.DataInteraction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;
import com.eligasht.service.helper.Const;
import com.eligasht.service.model.test.TransferClickEvenBus;

import org.greenrobot.eventbus.ThreadMode;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Reza Nejati on 18,April,2018
 */
public class HotelF extends BaseTest {
    public static HotelF newInstance() {
        HotelF hotelFlight = new HotelF();
        return hotelFlight;
    }

    @Override
    public void runTest() {
        doClick(R.id.btnMenu);
        doClick(R.id.btnHotelFlight);
        sleep(750);

        doClick(R.id.linearLayout_mabda);
        sleep(1000);
        doReplaceAndCloseKeyboard(R.id.searchtxt, TestConst.Dest);
        doClickWithIndex(R.id.text1, 0);
        doClick(R.id.linearLayout_maghsad);
        doReplaceAndCloseKeyboard(R.id.searchtxt, TestConst.Origin);
        doClickWithIndex(R.id.text1, 1);


       /* doClick(R.id.cvRoom);
        doClickWithIndex(R.id.btnPlusAdt, 0);
        doClickWithIndex(R.id.btnPlusAdt, 0);
        doClick(R.id.btn_add);
        doClickWithIndex(R.id.btnPlusCh, 1);
        doClickWithIndex(R.id.spn_range, 0);
        doClickItemInSpinner(1);
       ;*/
        doClick(R.id.llRaft);
        sleep(500);
        doClickWithIndex(R.id.rootView, 39);
        sleep(500);
        doClickWithIndex(R.id.rootView, 41);
        doClick(R.id.accept);
        sleep(1000);
        //   doClick(R.id.btn_confirm);
        doClick(R.id.searchHotel);
        sleep(1500);
        doClickWithIndex(R.id.btnChange, 0);
        sleep(1000);
        try {

        }catch (Exception e){

        }

        onData(anything()).inAdapterView(withId(R.id.lvExp)).atPosition(0).perform(click());
        DataInteraction frameLayout6 = onData(anything())
                .inAdapterView(allOf(withId(R.id.lvExp),
                        childAtPosition(
                                withId(R.id.linear_expand),
                                0)))
                .atPosition(2);
        frameLayout6.perform(click());

        doClickWithIndex(R.id.rlListItem, 0);


        doClickTab(R.id.tab_layout, 1);
        doClickTab(R.id.tab_layout, 2);
        doClickTab(R.id.tab_layout, 3);
        doClickTab(R.id.tab_layout, 1);
        doClickTab(R.id.tab_layout, 0);


        doClickTab(R.id.tab_layout, 3);
        try {
            doClickWithIndex(R.id.btnPolicy, 0);



        }catch (Exception e){
            pressBack(2);
            return;


        }


        doClick(R.id.btnOk);
        doClickWithIndex(R.id.llSelectHotel, 0);
        sleep(2000);
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

        try {

            doClickWithIndex(R.id.btnAddsabad, 0);
            try{
                sleep(500);

                doClick(R.id.btnCal);
                sleep(500);

            }catch (Exception e){

            }
            doClickWithIndex(R.id.btnAddsabad, 1);
            try{
                doClick(R.id.btnCal);

            }catch (Exception e){

            }
            doClickWithIndex(R.id.btnAddsabad, 2);
            try{
                doClick(R.id.btnCal);

            }catch (Exception e){

            }
            doClickWithIndexInScroll(R.id.btnAddsabad, 3);
            try{
                doClick(R.id.btnCal);

            }catch (Exception e){

            }
            doClickWithIndexInScroll(R.id.btnAddsabad, 4);
            try{
                doClick(R.id.btnCal);

            }catch (Exception e){

            }
            doClickWithIndexInScroll(R.id.btnAddsabad, 6);
            try{
                doClick(R.id.btnCal);

            }catch (Exception e){

            }
            doClickWithIndexInScroll(R.id.btnAddsabad, 7);
            try{
                doClick(R.id.btnCal);

            }catch (Exception e){

            }

            sleep(150);
            EventBus.getDefault().post(new TransferClickEvenBus(23));
        } catch (Exception e) {
        }
        doClick(R.id.btn_taeed_khadamat);
        pressBack(4);


    }



}