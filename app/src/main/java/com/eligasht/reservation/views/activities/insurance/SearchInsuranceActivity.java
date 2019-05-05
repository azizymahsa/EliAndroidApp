package com.eligasht.reservation.views.activities.insurance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.base.ServiceType;
import com.eligasht.reservation.base.SingletonAnalysis;
import com.eligasht.reservation.base.SingletonTimer;
import com.eligasht.reservation.models.model.insurance.BirthDateList;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.adapters.insurance.InsurancPlanAdapter;
import com.eligasht.reservation.views.picker.global.model.CustomDate;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.newModel.insurance.request.searchInsurance.RequestInsuranceSearchResultViewModel;
import com.eligasht.service.model.newModel.insurance.response.Insurance;
import com.eligasht.service.model.newModel.insurance.response.ResponseInsuranceSearchResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by elham.bonyani on 1/15/2018.
 */

public class SearchInsuranceActivity extends BaseActivity implements View.OnClickListener,  InsurancPlanAdapter.ListenerInsurancPlanAdapter, OnServiceStatus<ResponseInsuranceSearchResult> {//TravelInsurancAdapter.ListenerTravelInsurancAdapter,

    private List<com.eligasht.service.model.insurance.request.SearchInsurance.BirthDateList> birthDateLists;
    private int passCount;
    private RecyclerView rclTravelInsurance;
    private RecyclerView rclInsurancePlans;
    private TextView txt;
    private ClientService service;
    private String departureDate;
    private String returnDate;
    private String countryCode;
    private String countryName;
    private String culture;
    private int accomodationDays;
    private Gson gson;

    private List<com.eligasht.service.model.newModel.insurance.response.Insurance> Insurances;
    private List<com.eligasht.service.model.newModel.insurance.response.TravelInsuranceCoverage> travelInsuranceCoverages;//detailInsurance

    private com.eligasht.service.model.newModel.insurance.response.InsuranceSearchResult insurancePlan;
    private RelativeLayout error_layout;
    private TextView txt_error;
    private FancyButton btnOk;
    private RelativeLayout rlLoading2, rlRoot;
    private String birthDateString="";


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_insurance);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

            window.setStatusBarColor(ContextCompat.getColor(SearchInsuranceActivity.this
                    , R.color.colorPrimaryDark));
        }
        initViews();
        service = ServiceGenerator.createService(ClientService.class);
        Bundle bundle = getIntent().getExtras();
        gson = new GsonBuilder().create();

        if (bundle != null) {

            birthDateLists = gson.fromJson(bundle.getString("BirthDateList"), new TypeToken<List<BirthDateList>>() {
            }.getType());
            passCount = birthDateLists.size();

            countryCode = bundle.getString("CountryCode");
            countryName = bundle.getString("CountryName");
            departureDate = bundle.getString("DepartureDate");
            accomodationDays = bundle.getInt("AccomodationDays");
            culture = bundle.getString("Culture");
            birthDateString=bundle.getString("BirthDateListString");

            CustomDate startDate = SingletonDate.getInstance().getEndDate();
            startDate.addDay(accomodationDays);
            returnDate = startDate.getFullGeo();
            startDate.addDay(-accomodationDays);
            SingletonAnalysis.getInstance().logTransfer(ServiceType.INSURANCE, "", countryName);

        }

        showInsurance();
    }

    @Override
    public boolean needTerminate() {
        return true;
    }

    @Override
    public void onReady(ResponseInsuranceSearchResult responseSearchInsurance) {
        Log.d("responseSearchInsurance: ",new Gson().toJson(responseSearchInsurance));
       /* {
            Gson gson2 = new Gson();
            gson2.toJson(responseSearchInsurance);
            Log.e("onResponse", gson2.toJson(responseSearchInsurance));*/
           /* */
        SingletonTimer.getInstance().start();
        hideLoading();
        Log.d("TAG", "onResponse: ");
        if (responseSearchInsurance == null) {
               // || responseSearchInsurance.getShowInsuranceResult() == null) {
            showText();
            if (!Utility.isNetworkAvailable(SearchInsuranceActivity.this)) {

                txt_error.setText(getString(R.string.InternetError));

            } else {

                txt_error.setText(getString(R.string.ErrorServer));

            }
            error_layout.setVisibility(View.VISIBLE);
            return;
        }

        if (responseSearchInsurance.getInsuranceSearchResult().getErrors().size() >0){// != null) {
            showText();
            txt_error.setText(responseSearchInsurance.getInsuranceSearchResult().getErrors().get(0).getMessage());
            error_layout.setVisibility(View.VISIBLE);
            return;
        }

        List<Insurance> travelInsurance = responseSearchInsurance.getInsuranceSearchResult().getInsurances();




        if (travelInsurance == null ) {// && insurancePlan == null) {
            showText();
            txt_error.setText(getString(R.string.PackgeNoFound));
            error_layout.setVisibility(View.VISIBLE);
            return;
        }

       /* if (travelInsurance == null && (insurancePlan != null && insurancePlan.getInsurancePlans() == null)) {
            showText();
            txt_error.setText(getString(R.string.PackgeNoFound));
            error_layout.setVisibility(View.VISIBLE);
            return;
        }*/

       /* if ((travelInsurance != null && travelInsurance.getTravelInsurances() == null) && insurancePlan == null) {
            showText();
            txt_error.setText(getString(R.string.PackgeNoFound));
            error_layout.setVisibility(View.VISIBLE);
            return;
        }*/

     /*   if ((travelInsurance != null && travelInsurance.getTravelInsurances() == null) && (insurancePlan != null && insurancePlan.getInsurancePlans() == null)) {
            showText();
            txt_error.setText(getString(R.string.PackgeNoFound));
            error_layout.setVisibility(View.VISIBLE);
            return;
        }*/
        insurancePlan = responseSearchInsurance.getInsuranceSearchResult();//getShowInsuranceResult().getInsurancePlan();
        /*if (travelInsurance != null && responseSearchInsurance.getInsuranceSearchResult() != null) {
            Insurances = travelInsurance;//getTravelInsurances();
            showTravelInsurances();
        }*/

        if (insurancePlan != null && insurancePlan.getInsurances() != null) {
            Insurances = insurancePlan.getInsurances();
            showInsurancePlans();
        }


    }

    @Override
    public void onError(String message) {
        Log.e("showInsurance: ", message);
        hideLoading();
        showText();
        txt_error.setText(getString(R.string.ErrorServer));
        error_layout.setVisibility(View.VISIBLE);
    }

    //send request to server for get list of insurance
    private void showInsurance() {
        InitUi.Toolbar(this, false, R.color.toolbar_color, getString(R.string.Travel_insurance_to_travel_to_the_country) + " " + countryName);

        showLoading();


//send GetHListRequest Insurance search

        RequestInsuranceSearchResultViewModel request = new RequestInsuranceSearchResultViewModel();
        com.eligasht.service.model.newModel.insurance.request.searchInsurance.QueryModel queryModel = new com.eligasht.service.model.newModel.insurance.request.searchInsurance.QueryModel();



        queryModel.setTravelDuration(String.valueOf(accomodationDays));
        Log.d("TAG", "showInsurance: " + accomodationDays);

        queryModel.setCategory("Insurance");
        queryModel.setCurrentCulture("tr-TR");
        queryModel.setDestinationText("Turkey");
        queryModel.setDestination("TR");
        queryModel.setExclusiveTrain(0);
        queryModel.setIsSearchRequest(1);
        queryModel.setPreferredClass("All");
        queryModel.setTrip("TR");
       Log.d("TAG", "showInsurance: " + returnDate);

      //  request.setPassCount(passCount);
      //  Log.d("TAG", "showInsurance: " + passCount);

        queryModel.setBirthDay(birthDateString.substring(1, birthDateString.length()));
        Log.d("TAG", "showInsurance: " + birthDateString);
        request.setQueryModel(queryModel);

       // request.setCulture(getString(R.string.culture));
      //  requestSearchInsurance.setRequest(request);
        Log.e("InsuranceRequestModel: ", new Gson().toJson(request));
        SingletonService.getInstance().getInsurance().newInsuranceSearchAvail(this, request);


    }

    //first list of insurance
    private void showInsurancePlans() {
        rclInsurancePlans.setVisibility(View.VISIBLE);
        InsurancPlanAdapter insurancPlanAdapter = new InsurancPlanAdapter(this, Insurances, passCount).setListener(this);
        rclInsurancePlans.setAdapter(insurancPlanAdapter);
    }
//در سمپل جدید این نوع از لیست را نداریم
    //second list of insurance
   private void showTravelInsurances() {
        /*rclTravelInsurance.setVisibility(View.VISIBLE);
        TravelInsurancAdapter travelInsurancAdapter = new TravelInsurancAdapter(this, travelInsuranceCoverages, passCount).setListener(this);
        rclTravelInsurance.setAdapter(travelInsurancAdapter);*/
       rclInsurancePlans.setVisibility(View.VISIBLE);
       InsurancPlanAdapter insurancPlanAdapter = new InsurancPlanAdapter(this, Insurances, passCount).setListener(this);
       rclInsurancePlans.setAdapter(insurancPlanAdapter);
    }

    private void showText() {
        rlLoading2.setVisibility(View.GONE);
        rclTravelInsurance.setVisibility(View.GONE);
        rclInsurancePlans.setVisibility(View.GONE);
        txt.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        rclTravelInsurance = findViewById(R.id.rclTravelInsurance);
        rclInsurancePlans = findViewById(R.id.rclInsurancePlans);
        btnOk = findViewById(R.id.btnOk);
        rlLoading2 = findViewById(R.id.rlLoading2);
        rlRoot = findViewById(R.id.rlRoot);
        txt = findViewById(R.id.txt);
        rclTravelInsurance.setLayoutManager(new LinearLayoutManager(this));
        rclInsurancePlans.setLayoutManager(new LinearLayoutManager(this));
        TextView tvTitleDate = findViewById(R.id.tvTitleDate);
        tvTitleDate.setVisibility(View.GONE);
        error_layout = findViewById(R.id.elNotFound);
        txt_error = findViewById(R.id.tvAlert);
        error_layout.setVisibility(View.GONE);
        btnOk.setOnClickListener(this);
        Utility.setAnimLoading(this);
    }

    private void showLoading() {
        Utility.disableEnableControls(false, rlRoot);
        rlLoading2.setVisibility(View.VISIBLE);
        rclTravelInsurance.setVisibility(View.GONE);
        rclInsurancePlans.setVisibility(View.GONE);
        txt.setVisibility(View.GONE);
    }

    private void hideLoading() {
        Utility.disableEnableControls(true, rlRoot);
        rlLoading2.setVisibility(View.GONE);
        txt.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                onBackPressed();
                break;
        }
    }

    //onclick the first's list from  insurance
   /* @Override
    public void onClickTravelInsurancItem(com.eligasht.service.model.insurance.response.SearchInsurance.TravelInsurance_ travelInsurance) {
        Intent intent = new Intent(this, PassengerInsuranceActivity.class);

        Prefs.putString("CountryCode", countryCode);
        Prefs.putString("DepartureDate", departureDate);
        Prefs.putString("DtStart", departureDate);
        Prefs.putString("ReturnDate", returnDate);
        Prefs.putInt("Price", (travelInsurance.getTravelInsurancePricePP().getAmount()) * passCount);
        Prefs.putString("Id", travelInsurance.getTravelInsuranceID().toString());
        Prefs.putInt("PassCount", passCount);
        startActivity(intent);
    }*/

    //onclick the second's list from  insurance
    @Override
    public void onClickInsurancPlanItem(Insurance _insurancePlan) {
        Intent intent = new Intent(this, PassengerInsuranceActivity.class);
        Prefs.putString("CountryCode", countryCode);
        Prefs.putString("DepartureDate", departureDate);
        Prefs.putString("DtStart", departureDate);
        Prefs.putString("ReturnDate", returnDate);
        Prefs.putInt("Price", (_insurancePlan.getInsurancePrice()));// * passCount);
        Prefs.putString("Id", _insurancePlan.getInsuranceID());//getCode().toString());
        Prefs.putString("SearchKey", insurancePlan.getSearchKey());
        Prefs.putInt("PassCount", passCount);
        startActivity(intent);
    }

   /* @Override
    public void onClickInsurancPlanItem(Insurance insurancePlan) {

    }*/


  /*  @Override
    public void onClickInsurancPlanItem(com.eligasht.service.model.insurance.response.SearchInsurance.InsurancePlan_ insurancePlan) {

    }*/

   /* @Override
    public void onClickTravelInsurancItem(com.eligasht.service.model.insurance.response.SearchInsurance.TravelInsurance_ travelInsurance) {

    }*/
}
//04-11 18:39:47.865 5428-5428/com.eligasht E/PreFactorDetails:: {"GetPreFactorDetailsResult":{"Errors":[{"Code":-1100.0,"DetailedMessage":"Object reference not set to an instance of an object.","Message":"Object reference not set to an instance of an object."}]}}
