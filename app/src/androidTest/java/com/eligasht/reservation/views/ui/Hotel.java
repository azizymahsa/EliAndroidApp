package com.eligasht.reservation.views.ui;
import com.eligasht.R;
import com.eligasht.reservation.views.TestConst;
import com.eligasht.service.helper.Const;
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
        sleep(500);
        doClickWithIndex(R.id.llLayout, 0);
        sleep(500);
        doClick(R.id.cvRoom);
        sleep(500);
        doClickWithIndex(R.id.btnPlusAdt,0);
        doClickWithIndex(R.id.btnPlusAdt,0);
        sleep(500);
        doClick(R.id.btn_add);
        sleep(500);
        doClickWithIndex(R.id.btnPlusCh,1);
        sleep(500);
        doClickWithIndex(R.id.spn_range,0);
        sleep(500);
        doClickItemInSpinner(1);
        doClick(R.id.btn_confirm);
        sleep(200);


        doClick(R.id.searchHotel);
        sleep(500);
        doClickWithIndex(R.id.rlListItem,0);
        doClickTab(R.id.tab_layout,1);
        doClickTab(R.id.tab_layout,2);
        doClickTab(R.id.tab_layout,3);
        doClickTab(R.id.tab_layout,1);
        doClickTab(R.id.tab_layout,0);

    }
}
