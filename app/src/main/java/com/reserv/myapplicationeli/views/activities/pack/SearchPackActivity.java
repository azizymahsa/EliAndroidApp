package com.reserv.myapplicationeli.views.activities.pack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.model.pack.LstAvailableDate;
import com.reserv.myapplicationeli.models.model.pack.PRowXfer;
import com.reserv.myapplicationeli.models.model.pack.SearchXPackageResult;
import com.reserv.myapplicationeli.models.model.pack.call.PackageListReq;
import com.reserv.myapplicationeli.models.model.pack.call.PackageRequestModel;
import com.reserv.myapplicationeli.models.model.pack.filter.AmenityFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.DegreeFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.FilterPackTools;
import com.reserv.myapplicationeli.models.model.pack.filter.PlaceFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.PriceFilter;
import com.reserv.myapplicationeli.models.model.pack.response.PackageListRes;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.datetools.DateUtil;
import com.reserv.myapplicationeli.views.adapters.pack.LstAvailableDateAdapter;
import com.reserv.myapplicationeli.views.adapters.pack.PRowXferAdapter;
import com.reserv.myapplicationeli.views.components.SimpleRecycleView;
import com.reserv.myapplicationeli.views.dialogs.FilterPackageDialog;
import com.reserv.myapplicationeli.views.dialogs.SortDialogPackage;
import com.reserv.myapplicationeli.views.ui.InitUi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class SearchPackActivity extends BaseActivity implements View.OnClickListener, PRowXferAdapter.ListenerSearchPackAdapter, SortDialogPackage.SortHotelDialogListener {

    public SimpleRecycleView rcl_package;
    public SimpleRecycleView rcl_available_date;
    public PRowXferAdapter pRowXferAdapter;
    private ClientService service;
    private ArrayList<PRowXfer> pRowXfers;
    private ArrayList<PriceFilter> priceFilters;
    private ArrayList<PlaceFilter> placeFilters;
    private ArrayList<DegreeFilter> degreeFilters;
    private ArrayList<AmenityFilter> amenityFilters;
    private RelativeLayout rlLoading;
    private RelativeLayout rlRoot;
    private HorizontalScrollView scroll_availabel_date;
    private String departureFrom;
    private String departureTo;
    private String country;
    private String culture;
    private String roomList;
    private String cityName;
    private TextView toolbar_title;
    private TextView toolbar_date;
    private TextView txt_comin_soon;
    private FancyButton btnBack;
    private ViewGroup layout_sort;
    private ViewGroup llFilter;
    private FancyButton btn_next_day;
    private FancyButton btn_previous_day;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pack);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        }
        initViews();
        initParam();
        service = ServiceGenerator.createService(ClientService.class);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            country = bundle.getString("Country");
            departureFrom = bundle.getString("DepartureFrom");
            departureTo = bundle.getString("DepartureTo");
            roomList = bundle.getString("RoomList");
            culture = bundle.getString("Culture");
            cityName = bundle.getString("CityName");

            getPackages(country
                    , departureFrom
                    , departureTo
                    , roomList
                    , culture);


        }


        //    InitUi.Toolbar(this, false, R.color.add_room_color, " تور " + cityName + "\n" + date );

    }

    private void getPackages(String country, String departureFrom, String departureTo, String roomList, String culture) {
        String date = DateUtil.getShortStringDate(departureFrom, "yyyy-MM-dd", true) + " - " + DateUtil.getShortStringDate(departureTo, "yyyy-MM-dd", true);
        toolbar_title.setText(" تور " + cityName);
        toolbar_date.setText(date);
        showLoading();
        PackageListReq packageListReq = new PackageListReq();

        packageListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        packageListReq.setCountry(country);
        packageListReq.setRoomList(roomList);
        packageListReq.setDepartureFrom(departureFrom);
        packageListReq.setDepartureTo(departureTo);
        packageListReq.setCulture(culture);
        Log.e("roomlist", roomList);

        Log.e("req_pack", new GsonBuilder().create().toJson(new PackageRequestModel(packageListReq)));
        Call<PackageListRes> call = service.getPackageListResult(new PackageRequestModel(packageListReq));
        call.enqueue(new Callback<PackageListRes>() {
            @Override
            public void onResponse(Call<PackageListRes> call, Response<PackageListRes> response) {
                hideLoading();

                if (response == null || response.body() == null) {
                    rcl_package.showText();
                }
                SearchXPackageResult searchXPackageResult = response.body().getSearchXPackageResult();
                if (searchXPackageResult == null || ValidationTools.isEmptyOrNull(searchXPackageResult.getPRowXfers())) {
                    rcl_package.showText();
                    if (response.body().getSearchXPackageResult().getError() != null) {
                        Toast.makeText(SearchPackActivity.this, response.body().getSearchXPackageResult().getError().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                if (searchXPackageResult.getPRowXfers() != null) {
                    pRowXfers = searchXPackageResult.getPRowXfers();
                    priceFilters = FilterPackTools.getPriceFilters(pRowXfers);
                    placeFilters = FilterPackTools.getPlaceFilters(pRowXfers);
                    degreeFilters = FilterPackTools.getDegreeFilters(pRowXfers);
                    amenityFilters = FilterPackTools.getAmenityFilters(pRowXfers);
                    showList();
                } else {
                    rcl_package.showText();
                }

            }

            @Override
            public void onFailure(Call<PackageListRes> call, Throwable t) {
                hideLoading();
                Toast.makeText(SearchPackActivity.this, "خطا در ارتباط", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void initParam() {
    }

    private void initViews() {

        toolbar_title = findViewById(R.id.tvTitle);
        toolbar_date = findViewById(R.id.tvDate);
        btnBack = findViewById(R.id.btnBack);
        txt_comin_soon = findViewById(R.id.txt_comin_soon);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        layout_sort = findViewById(R.id.llSort);
        llFilter = findViewById(R.id.llFilter);
        btn_previous_day = findViewById(R.id.btnLastDays);
        btn_next_day = findViewById(R.id.btnNextDays);
        scroll_availabel_date = findViewById(R.id.scroll_availabel_date);


        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        rcl_available_date = (SimpleRecycleView) findViewById(R.id.rcl_available_date);
        rcl_package = (SimpleRecycleView) findViewById(R.id.rcl_package);
        rcl_package.setLayoutManager(new LinearLayoutManager(this));
        rcl_available_date.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcl_available_date.hideLoading();
        rcl_available_date.setVisibility(View.GONE);
        txt_comin_soon.setVisibility(View.GONE);
        rcl_available_date.setNestedScrollingEnabled(false);

        btnBack.setOnClickListener(this);
        llFilter.setOnClickListener(this);
        layout_sort.setOnClickListener(this);
        btn_previous_day.setOnClickListener(this);
        btn_next_day.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.llSort:
                if (ValidationTools.isEmptyOrNull(pRowXfers)) {
                    Toast.makeText(this, "موردی یافت نشد .", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pRowXfers.size() == 1) {
                    Toast.makeText(this, "فقط یه مورد جهت نمایش وجود دارد .", Toast.LENGTH_SHORT).show();
                    return;
                }
                SortDialogPackage dialogPackage = new SortDialogPackage(this, this);
                break;

            case R.id.btnNextDays:
                if (DateUtil.getMiliSecondGregorianDateTime(departureFrom, "yyyy-MM-dd") + 86400000 > DateUtil.getMiliSecondGregorianDateTime(departureTo, "yyyy-MM-dd")) {
                    Toast.makeText(this, "تاریخ رفت نمی تواند بعد از تاریخ برگشت باشد .", Toast.LENGTH_SHORT).show();
                    return;
                }

                long milis = DateUtil.getMiliSecondGregorianDateTime(departureFrom, "yyyy-MM-dd") + 86400000;
                departureFrom = DateUtil.getDateTime(String.valueOf(milis), "yyyy-MM-dd");
                getPackages(country, departureFrom, departureTo, roomList, culture);
                break;
            case R.id.btnLastDays:
                if (DateUtil.getMiliSecondGregorianDateTime(departureFrom, "yyyy-MM-dd") - 86400000 < System.currentTimeMillis()) {
                    Toast.makeText(this, "تاریخ رفت نمی تواند قبل از امروز باشد .", Toast.LENGTH_SHORT).show();
                    return;
                }
                long _milis = DateUtil.getMiliSecondGregorianDateTime(departureFrom, "yyyy-MM-dd") - 86400000;
                departureFrom = DateUtil.getDateTime(String.valueOf(_milis), "yyyy-MM-dd");
                getPackages(country, departureFrom, departureTo, roomList, culture);

                break;

            case R.id.llFilter:
                if (ValidationTools.isEmptyOrNull(pRowXfers)) {
                    return;
                }
                FilterPackageDialog filterPackageDialog = new FilterPackageDialog(SearchPackActivity.this);
                filterPackageDialog.setPrices(priceFilters);
                filterPackageDialog.setDegrees(degreeFilters);
                filterPackageDialog.setPlaces(placeFilters);
                filterPackageDialog.setAmenities(amenityFilters);
                filterPackageDialog.setOnFiltePackageListener(new FilterPackageDialog.OnFiltePackageListener() {
                    @Override
                    public void onConfirm(ArrayList<DegreeFilter> degreeFiltersSelected,
                                          ArrayList<PriceFilter> priceFiltersSelected,
                                          ArrayList<PlaceFilter> placeFiltersSelected,
                                          ArrayList<AmenityFilter> amenityFiltersSelected) {


                        if (pRowXferAdapter != null) {
                            pRowXferAdapter.filter(degreeFiltersSelected, priceFiltersSelected, placeFiltersSelected, amenityFiltersSelected);
                        }
                    }
                });
                filterPackageDialog.show();
                break;
        }
    }


    public void showLoading() {
        new InitUi().Loading(SearchPackActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);
        rcl_package.showLoading();
    }


    public void hideLoading() {
        new InitUi().Loading(SearchPackActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);
        rcl_package.hideLoading();
    }

    private void showList() {
        pRowXferAdapter = new PRowXferAdapter(this, pRowXfers).setListener(this);
        rcl_package.showList(pRowXferAdapter);

        ArrayList<LstAvailableDate> lstAvailableDates = new ArrayList<>();
        for (PRowXfer pRowXfer : pRowXfers) {
            if (!ValidationTools.isEmptyOrNull(pRowXfer.getLstAvailableDates())) {
                lstAvailableDates.addAll(pRowXfer.getLstAvailableDates());
            }
        }

        if (ValidationTools.isEmptyOrNull(lstAvailableDates)) {
            return;
        }


        LstAvailableDateAdapter lstAvailableDateAdapter = new LstAvailableDateAdapter(this, lstAvailableDates).setListener(new LstAvailableDateAdapter.ListenerLstAvailableDateAdapter() {
            @Override
            public void onClickLstAvailableDateItem(LstAvailableDate lstAvailableDate) {
                long milis = DateUtil.getMiliSecondFromJSONDate(lstAvailableDate.getDepartDate());
                departureFrom = DateUtil.getDateTime(String.valueOf(milis), "yyyy-MM-dd");
                getPackages(country, departureFrom, departureTo, roomList, culture);
            }
        });

        int indexSeletedItem = getIndexSelectedItem(lstAvailableDates);

        rcl_available_date.showList(lstAvailableDateAdapter);
        rcl_available_date.setVisibility(View.VISIBLE);

        scroll_availabel_date.post(new Runnable() {
            @Override
            public void run() {
                scroll_availabel_date.fullScroll(View.FOCUS_RIGHT);
            }
        });

        if(indexSeletedItem == 0 ){
            txt_comin_soon.setVisibility(View.VISIBLE);
        }else{
            txt_comin_soon.setVisibility(View.GONE);
        }

    }

    private int getIndexSelectedItem(ArrayList<LstAvailableDate> lstAvailableDates) {


        if (ValidationTools.isEmptyOrNull(lstAvailableDates)) {
            return 0;
        }

        for (int i = 0; i < lstAvailableDates.size(); i++) {
            if (lstAvailableDates.get(i).getIsSelected()) {
                return i;
            }
        }

        return 0;
    }

    @Override
    public void onClickPackageBookingItem(PRowXfer pack) {
        Intent intent = new Intent(this, PassengerPackageActivity.class);
        Prefs.putString("Rooms", roomList);
        Prefs.putString("PackRow_ID", pRowXfers.get(0).getPackRowID().toString());
        Prefs.putString("PackXfer_IDs", pRowXfers.get(0).getXFerIDs());
        Prefs.putString("Flt_IDs", pRowXfers.get(0).getFltIDs());
        Prefs.putInt("PackRoomType_ID", pRowXfers.get(0).getLstProwPrices().get(0).getPackRowRoomTypeID());
        Prefs.putInt("Room_No", pRowXfers.get(0).getLstProwPrices().get(0).getRoomNo());
        startActivity(intent);
    }

    @Override
    public void onFilterListChange(ArrayList<PRowXfer> filtertemList) {
        if (ValidationTools.isEmptyOrNull(filtertemList)) {
            rcl_package.showText();
        } else {
            rcl_package.showList(pRowXferAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onReturnValue(int type) {
        if (pRowXferAdapter != null) {
            pRowXferAdapter.sort(type);
        }
    }
}
