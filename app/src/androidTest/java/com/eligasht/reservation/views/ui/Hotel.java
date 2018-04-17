package com.eligasht.reservation.views.ui;
import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;
import com.eligasht.service.helper.Const;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
/**
 * Created by Reza Nejati on 17,April,2018
 */
public class Hotel extends BaseTest {

    public static Hotel newInstance() {
        Hotel hotel = new Hotel();
        return hotel;
    }

    @Override
    public void runTest() {
        doClick(R.id.btnMenu);
        doClick(R.id.btnHotel);
        sleep(500);
        doClick(R.id.citySearch);
        doReplaceAndCloseKeyboard(R.id.searchtxt, TestConst.Dest);
        doClickWithIndex(R.id.llLayout, 0);
        doClick(R.id.cvRoom);
        doClickWithIndex(R.id.btnPlusAdt,0);
        doClickWithIndex(R.id.btnPlusAdt,0);
        doClick(R.id.btn_add);
        doClickWithIndex(R.id.btnPlusCh,1);
        doClickWithIndex(R.id.spn_range,0);
        doClickItemInSpinner(1);
        doClick(R.id.btn_confirm);


        doClick(R.id.searchHotel);
        doClickWithIndex(R.id.rlListItem,0);
        doClickTab(R.id.tab_layout,1);
        doClickTab(R.id.tab_layout,2);
        doClickTab(R.id.tab_layout,3);
        doClickTab(R.id.tab_layout,1);
        doClickTab(R.id.tab_layout,0);
        sleep(500);
        doScrollAndClickInScrollView(R.id.btnSortComment);
        doClickWithIndex(R.id.expand_text_view,0);
        sleep(1000);
        doScrollAndClickInScrollView(R.id.btnComment);
        doClick(R.id.btnOk);
        doClick(R.id.btnToComment);
        doReplaceAndCloseKeyboard(R.id.etName,"نام");
        doReplaceAndCloseKeyboard(R.id.etMail,"mail@test.com");
        doReplaceAndCloseKeyboard(R.id.etTitle,"عنوان");
        doReplaceAndCloseKeyboard(R.id.etMessage,"متن");
        doClick(R.id.cbIsRecommended);
        doClick(R.id.cbSubmitName);
        doClick(R.id.btnConfirm);
        doClick(R.id.btnOk);
        doClickTab(R.id.tab_layout,3);
        doClickWithIndex(R.id.btnPolicy,0);
        doClick(R.id.btnOk);
        doClickWithIndex(R.id.llSelectHotel,0);

        sleep(1000);


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

       // doClickWithIndex(R.id.image,0);

        // doClickTab(R.id.tab_layout,0);



    }
}