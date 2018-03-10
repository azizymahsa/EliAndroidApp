package com.eligasht.reservation.views.activities.pack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.pack.LstAvailableDate;
import com.eligasht.reservation.models.model.pack.LstProwPrice;
import com.eligasht.reservation.models.model.pack.PRowXfer;
import com.eligasht.reservation.models.model.pack.PackageRoomNoToRequest;
import com.eligasht.reservation.models.model.pack.SearchXPackageResult;
import com.eligasht.reservation.models.model.pack.call.PackageListReq;
import com.eligasht.reservation.models.model.pack.call.PackageRequestModel;
import com.eligasht.reservation.models.model.pack.filter.AmenityFilter;
import com.eligasht.reservation.models.model.pack.filter.DegreeFilter;
import com.eligasht.reservation.models.model.pack.filter.FilterPackTools;
import com.eligasht.reservation.models.model.pack.filter.HotelTypeFilter;
import com.eligasht.reservation.models.model.pack.filter.PlaceFilter;
import com.eligasht.reservation.models.model.pack.filter.PriceFilter;
import com.eligasht.reservation.models.model.pack.response.PackageListRes;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.views.activities.hotel.activity.SelectHotelActivity;
import com.eligasht.reservation.views.adapters.pack.LstAvailableDateAdapter;
import com.eligasht.reservation.views.adapters.pack.PRowXferAdapter;
import com.eligasht.reservation.views.components.SimpleRecycleView;
import com.eligasht.reservation.views.dialogs.FilterPackageDialog;
import com.eligasht.reservation.views.dialogs.SortDialogPackage;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.Collections;

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
    private ArrayList<HotelTypeFilter> hotelTypeFilters;
    private ArrayList<DegreeFilter> degreeFilters;
    private ArrayList<AmenityFilter> amenityFilters;
    private RelativeLayout rlLoading;
    private RelativeLayout rlRoot;
    private ViewGroup layout_availabel_date;
    private String departureFrom;
    private String departureTo;
    private String country;
    private String culture;
    private String roomList;
    private String cityName,PreferedAir;
    private TextView toolbar_title,tvLoading;
    private TextView toolbar_date;
    private FancyButton btnBack;
    private ViewGroup layout_sort;
    private ViewGroup llFilter;
    private FancyButton btn_next_day;
    private FancyButton btn_previous_day;
    private RelativeLayout error_layout;
    private TextView txt_error;
    private FancyButton btnHome;
    private FancyButton btnOk;
    private TextView txtFilter;
    private TextView txtIconFilter;
    private TextView txtNotFoundResualt;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pack);
        Utility.setAnimLoading(this);


        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

            window.setStatusBarColor(ContextCompat.getColor(SearchPackActivity.this
                    ,R.color.colorPrimaryDark));
        }
        initViews();
        service = ServiceGenerator.createService(ClientService.class);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            country = bundle.getString("Country");
            departureFrom = bundle.getString("DepartureFrom");
            departureTo = bundle.getString("DepartureTo");
            roomList = bundle.getString("RoomList");
            culture = getString(R.string.culture);
            cityName = bundle.getString("CityName");

            getPackages(country
                    , departureFrom
                    , departureTo
                    , roomList
                    , culture,PreferedAir);


        }
    }
    //send request for get package
    private void getPackages(String country, String departureFrom, String departureTo, String roomList, String culture,String PreferedAir) {





        toolbar_title.setText(getString(R.string.Tur) + cityName);
        toolbar_date.setText(SingletonDate.getInstance().getStartDate().getDescription() + " - " + SingletonDate.getInstance().getEndDate().getDescription());
        goneView(layout_availabel_date, R.anim.slide_out_top);
        goneView(txtNotFoundResualt, R.anim.slide_out_top);
        showLoading();
        PackageListReq packageListReq = new PackageListReq();

        packageListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        packageListReq.setCountry(country);
        packageListReq.setRoomList(roomList);
        packageListReq.setDepartureFrom(Utility.convertNumbersToEnglish(departureFrom));
        packageListReq.setDepartureTo(Utility.convertNumbersToEnglish(departureTo));
        packageListReq.setCulture(culture);
        packageListReq.setPreferedAir(PreferedAir);


        Call<PackageListRes> call = service.getPackageListResult(new PackageRequestModel(packageListReq));
        call.enqueue(new Callback<PackageListRes>() {
            @Override
            public void onResponse(Call<PackageListRes> call, Response<PackageListRes> response) {
                hideLoading();

                if (response == null || response.body() == null) {
                    rcl_package.showText();
                    txt_error.setText(R.string.ErrorServer);
                    error_layout.setVisibility(View.VISIBLE);
                }


                SearchXPackageResult searchXPackageResult = response.body().getSearchXPackageResult();

                if (searchXPackageResult == null) {
                    rcl_package.showText();
                    if (!Utility.isNetworkAvailable(SearchPackActivity.this)){

                        txt_error.setText(R.string.InternetError);

                    }else{

                        txt_error.setText(R.string.ErrorServer);

                    }
                    error_layout.setVisibility(View.VISIBLE);
                    return;
                }

                if (!ValidationTools.isEmptyOrNull(searchXPackageResult.getError())) {
                    rcl_package.showText();
                    txt_error.setText(response.body().getSearchXPackageResult().getError().get(0).getDetailedMessage());
                    error_layout.setVisibility(View.VISIBLE);
                    return;
                }

                if (ValidationTools.isEmptyOrNull(searchXPackageResult.getPRowXfers())) {
                    rcl_package.showText();
                    txt_error.setText(R.string.NoResult);
                    error_layout.setVisibility(View.VISIBLE);
                    return;
                }

                pRowXfers = searchXPackageResult.getPRowXfers();
                priceFilters = FilterPackTools.getPriceFilters(pRowXfers);
                placeFilters = FilterPackTools.getPlaceFilters(pRowXfers);
                hotelTypeFilters = FilterPackTools.getHotelTypeFilters(pRowXfers);
                degreeFilters = FilterPackTools.getDegreeFilters(pRowXfers);
                amenityFilters = FilterPackTools.getAmenityFilters(pRowXfers);
                showList();


            }

            @Override
            public void onFailure(Call<PackageListRes> call, Throwable t) {
                hideLoading();
                rcl_package.showText();
                txt_error.setText(R.string.ErrorServer);
                error_layout.setVisibility(View.VISIBLE);
            }
        });
    }


    private void initViews() {

        toolbar_title = findViewById(R.id.tvTitle);
        toolbar_date = findViewById(R.id.tvDate);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        layout_sort = findViewById(R.id.llSort);
        btnHome = findViewById(R.id.btnHome);
        llFilter = findViewById(R.id.llFilter);
        btn_previous_day = findViewById(R.id.btnLastDays);
        btn_next_day = findViewById(R.id.btnNextDays);
        tvLoading = findViewById(R.id.tvLoading);
        Utility.loadingText(tvLoading,Prefs.getString("P",""));


        layout_availabel_date = findViewById(R.id.layout_availabel_date);
        error_layout = findViewById(R.id.elNotFound);
        txt_error = findViewById(R.id.tvAlert);
        btnOk = findViewById(R.id.btnOk);
        error_layout.setVisibility(View.GONE);
        txtFilter = findViewById(R.id.tvFilter);
        txtIconFilter = findViewById(R.id.tvFilterIcon);
        txtNotFoundResualt = findViewById(R.id.txt_not_found_resualt);

        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        rcl_available_date = findViewById(R.id.rcl_available_date);
        rcl_package = findViewById(R.id.rcl_package);
        rcl_package.setLayoutManager(new LinearLayoutManager(this));
        rcl_available_date.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcl_available_date.hideLoading();
        rcl_available_date.setVisibility(View.GONE);
        layout_availabel_date.setVisibility(View.GONE);
        txtNotFoundResualt.setVisibility(View.GONE);

        btnHome.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        llFilter.setOnClickListener(this);
        layout_sort.setOnClickListener(this);
        btn_previous_day.setOnClickListener(this);
        btn_next_day.setOnClickListener(this);
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHome:
     /*           Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                finish();*/

                Intent intent = new Intent("sendFinish");

                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);



                break;
            case R.id.btnOk:
                onBackPressed();
                break;
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.llSort:
                if (ValidationTools.isEmptyOrNull(pRowXfers)) {
                    Toast.makeText(this, R.string.PackgeNoFound, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pRowXfers.size() == 1) {
                    Toast.makeText(this, R.string.OnlyOne, Toast.LENGTH_SHORT).show();
                    return;
                }
                SortDialogPackage dialogPackage = new SortDialogPackage(this, this);
                break;

            case R.id.btnNextDays:
                if (SingletonDate.getInstance().getStartDate().addOneDay()) {

                    departureFrom = SingletonDate.getInstance().getStartDate().getFullGeo();
                    getPackages(country, departureFrom, departureTo, roomList, culture,PreferedAir);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.datePickerError,
                            Toast.LENGTH_SHORT).show();

                }


                break;
            case R.id.btnLastDays:
                if (SingletonDate.getInstance().getStartDate().minusOneDay()) {
                    departureFrom =SingletonDate.getInstance().getStartDate().getFullGeo();
                    getPackages(country, departureFrom, departureTo, roomList, culture,PreferedAir);

                } else {
                    Toast.makeText(getApplicationContext(), R.string.DatePickerError2,
                            Toast.LENGTH_SHORT).show();

                }


                break;

            case R.id.llFilter:
                if (ValidationTools.isEmptyOrNull(pRowXfers)) {
                    return;
                }
                FilterPackageDialog filterPackageDialog = new FilterPackageDialog(SearchPackActivity.this);
                filterPackageDialog.setPrices(priceFilters);
                filterPackageDialog.setDegrees(degreeFilters);
                filterPackageDialog.setPlaces(placeFilters);
                filterPackageDialog.setHotelTypess(hotelTypeFilters);
                filterPackageDialog.setAmenities(amenityFilters);
                filterPackageDialog.setOnFiltePackageListener(new FilterPackageDialog.OnFiltePackageListener() {
                    @Override
                    public void onConfirm(ArrayList<DegreeFilter> degreeFiltersSelected,
                                          ArrayList<PriceFilter> priceFiltersSelected,
                                          ArrayList<PlaceFilter> placeFiltersSelected,
                                          ArrayList<HotelTypeFilter> hotelTypeFiltersSelected,
                                          ArrayList<AmenityFilter> amenityFiltersSelected) {

                        if (!ValidationTools.isEmptyOrNull(degreeFiltersSelected)
                                || !ValidationTools.isEmptyOrNull(priceFiltersSelected)
                                || !ValidationTools.isEmptyOrNull(placeFiltersSelected)
                                || !ValidationTools.isEmptyOrNull(hotelTypeFiltersSelected)
                                || !ValidationTools.isEmptyOrNull(amenityFiltersSelected)) {

                            txtFilter.setTextColor(ContextCompat.getColor(SearchPackActivity.this, R.color.red));
                            txtIconFilter.setTextColor(ContextCompat.getColor(SearchPackActivity.this, R.color.red));
                        } else {
                            txtFilter.setTextColor(Color.parseColor("#4D4D4D"));
                            txtIconFilter.setTextColor(Color.parseColor("#4D4D4D"));
                        }

                        if (pRowXferAdapter != null) {
                            pRowXferAdapter.filter(degreeFiltersSelected, priceFiltersSelected, placeFiltersSelected, hotelTypeFiltersSelected, amenityFiltersSelected);
                        }


                    }
                });
                filterPackageDialog.show();
                break;

        }
    }


    public void showLoading() {
      Window  window = getWindow();


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

            window.setStatusBarColor(ContextCompat.getColor(SearchPackActivity.this,R.color.hf));
        }

        new InitUi().Loading(SearchPackActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);
        rcl_package.showLoading();
    }


    public void hideLoading() {
        Window  window = getWindow();


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

            window.setStatusBarColor(ContextCompat.getColor(SearchPackActivity.this,R.color.colorPrimaryDark));
        }



        new InitUi().Loading(SearchPackActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);
        rcl_package.hideLoading();
    }

    private void showList() {
        pRowXferAdapter = new PRowXferAdapter(this, pRowXfers,toolbar_date).setListener(this);
        rcl_package.showList(pRowXferAdapter);

        ArrayList<LstAvailableDate> lstAvailableDates = new ArrayList<>();

        for (PRowXfer pRowXfer : pRowXfers) {
            if (!ValidationTools.isEmptyOrNull(pRowXfer.getLstAvailableDates())) {
                lstAvailableDates.addAll(pRowXfer.getLstAvailableDates());
            }
        }
        Collections.reverse(lstAvailableDates);

        if (ValidationTools.isEmptyOrNull(lstAvailableDates)) {
            return;
        }

        visibileView(layout_availabel_date, R.anim.slide_in_top);

        LstAvailableDateAdapter lstAvailableDateAdapter = new LstAvailableDateAdapter(this, lstAvailableDates).setListener(new LstAvailableDateAdapter.ListenerLstAvailableDateAdapter() {
            @Override
            public void onClickLstAvailableDateItem(LstAvailableDate lstAvailableDate) {
                long milis = DateUtil.getMiliSecondFromJSONDate(lstAvailableDate.getDepartDate());
                departureFrom = DateUtil.getDateTime(String.valueOf(milis), "yyyy/MM/dd");
                Log.e("PackageTest", departureFrom);
                Log.e("PackageTest2", milis+"");
                Log.e("PackageTest3", lstAvailableDate.getPackID()+"");


                getPackages(country, departureFrom, departureTo, roomList, culture,String.valueOf(lstAvailableDate.getPackID()));
            }
        });

        int indexSeletedItem = lstAvailableDateAdapter.getIndexSelectedItem();
        long departMilis = DateUtil.getMiliSecondGregorianDateTime(departureFrom, "yyyy/MM/dd");

        if (departMilis != DateUtil.getMiliSecondFromJSONDate(lstAvailableDates.get(indexSeletedItem).getDepartDate())) {
            departureFrom = DateUtil.getDateTime(String.valueOf(DateUtil.getMiliSecondFromJSONDate(lstAvailableDates.get(indexSeletedItem).getDepartDate())), "yyyy/MM/dd");
            visibileView(txtNotFoundResualt, R.anim.slide_in_top);
        } else {
            goneView(txtNotFoundResualt, R.anim.slide_out_top);
        }


        long departureToMilis = DateUtil.getMiliSecondFromJSONDate(String.valueOf(pRowXfers.get(0).getXferList().getXFlightsList().get(1).getFltArrDate()));
        departureTo = DateUtil.getDateTime(String.valueOf(departureToMilis), "yyyy/MM/dd");

        String date = DateUtil.getShortStringDate(departureFrom, "yyyy/MM/dd", true) + " - " + DateUtil.getShortStringDate(departureTo, "yyyy/MM/dd", true);
        toolbar_date.setText(date);

        rcl_available_date.showList(lstAvailableDateAdapter);
        rcl_available_date.setVisibility(View.VISIBLE);
        rcl_available_date.getRecyclerView().scrollToPosition(indexSeletedItem - 2);

    }

    //onclick in booking ever pack
    @Override
    public void onClickPackageBookingItem(PRowXfer pack) {
        Intent intent = new Intent(this, PassengerPackageActivity.class);

        Prefs.putString("Rooms", roomList);
        Prefs.putString("PackRow_ID", pack.getPackRowID().toString());
        Prefs.putString("PackXfer_IDs", pack.getXFerIDs());
        Prefs.putString("Flt_IDs", pack.getFltIDs());
        intent.putExtra("PackageRoomNoToRequest", new GsonBuilder().create().toJson(getPackageRoomNoToRequest(pack.getLstProwPrices())));
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

    //get list of ever passenger with id and room's number
    private ArrayList<PackageRoomNoToRequest> getPackageRoomNoToRequest(ArrayList<LstProwPrice> lstProwPriceArrayList) {
        ArrayList<PackageRoomNoToRequest> packageRoomNoToRequests = new ArrayList<>();
        if (ValidationTools.isEmptyOrNull(lstProwPriceArrayList)) {
            Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show();
            return packageRoomNoToRequests;
        }

        for (LstProwPrice lstProwPrice : lstProwPriceArrayList) {
            if (lstProwPrice.isChecked()) {
                for (int i = 0; i < lstProwPrice.getAdlCount(); i++) {
                    packageRoomNoToRequests.add(new PackageRoomNoToRequest("adl", lstProwPrice.getPackRowRoomTypeID(), lstProwPrice.getRoomNo()));
                }
            }
        }

        for (LstProwPrice lstProwPrice : lstProwPriceArrayList) {
            if (lstProwPrice.isChecked()) {
                for (int i = 0; i < (lstProwPrice.getChdNBCount() + lstProwPrice.getChdWBCount()); i++) {
                    packageRoomNoToRequests.add(new PackageRoomNoToRequest("chl", lstProwPrice.getPackRowRoomTypeID(), lstProwPrice.getRoomNo()));
                }

            }
        }

        for (LstProwPrice lstProwPrice : lstProwPriceArrayList) {
            if (lstProwPrice.isChecked()) {
                for (int i = 0; i < lstProwPrice.getInfCount(); i++) {
                    packageRoomNoToRequests.add(new PackageRoomNoToRequest("inf", lstProwPrice.getPackRowRoomTypeID(), lstProwPrice.getRoomNo()));
                }

            }
        }

        return packageRoomNoToRequests;
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

    private void visibileView(View view, int animId) {
        if (view.getVisibility() != View.VISIBLE) {
            Utility.startAnim(this, view, animId);
            view.setVisibility(View.VISIBLE);
        }
    }

    private void goneView(View view, int animId) {
        if (view.getVisibility() == View.VISIBLE) {
            Utility.startAnim(this, view, animId);
            view.setVisibility(View.GONE);
        }
    }
}
