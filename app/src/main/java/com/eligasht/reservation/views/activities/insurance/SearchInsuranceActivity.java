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

import com.eligasht.reservation.base.ServiceType;
import com.eligasht.reservation.base.SingletonAnalysis;
import com.eligasht.reservation.base.SingletonTimer;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.airPort.Request;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.model.insurance.request.SearchInsurance.RequestSearchInsurance;
import com.eligasht.service.model.insurance.response.SearchInsurance.ResponseSearchInsurance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.insurance.BirthDateList;
import com.eligasht.reservation.models.model.insurance.InsurancePlan;
import com.eligasht.reservation.models.model.insurance.InsurancePlan_;
import com.eligasht.reservation.models.model.insurance.TravelInsurance;
import com.eligasht.reservation.models.model.insurance.TravelInsurance_;
import com.eligasht.reservation.models.model.insurance.call.InsuranceListReq;
import com.eligasht.reservation.models.model.insurance.call.InsuranceRequestModel;
import com.eligasht.reservation.models.model.insurance.response.InsuranceRes;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.views.adapters.insurance.InsurancPlanAdapter;
import com.eligasht.reservation.views.adapters.insurance.TravelInsurancAdapter;
import com.eligasht.reservation.views.ui.InitUi;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by elham.bonyani on 1/15/2018.
 */

public class SearchInsuranceActivity extends BaseActivity implements View.OnClickListener, TravelInsurancAdapter.ListenerTravelInsurancAdapter, InsurancPlanAdapter.ListenerInsurancPlanAdapter, OnServiceStatus<ResponseSearchInsurance> {

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
    private List<com.eligasht.service.model.insurance.response.SearchInsurance.TravelInsurance_> travelInsurances;
    private List<com.eligasht.service.model.insurance.response.SearchInsurance.InsurancePlan_> insurancePlans;
    private com.eligasht.service.model.insurance.response.SearchInsurance.InsurancePlan insurancePlan;
    private RelativeLayout error_layout;
    private TextView txt_error;
    private FancyButton btnOk;
    private RelativeLayout rlLoading2, rlRoot;


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

            long _milis = DateUtil.getMiliSecondGregorianDateTime(departureDate, "yyyy-MM-dd") + (accomodationDays * 86400000L);
            returnDate = DateUtil.getDateTime(String.valueOf(_milis), "yyyy-MM-dd");
            returnDate = Utility.convertNumbersToEnglish(returnDate);
            SingletonAnalysis.getInstance().logTransfer(ServiceType.INSURANCE,"",countryName);

        }

        showInsurance();
    }

    @Override
    public boolean needTerminate() {
        return true;
    }

    @Override
    public void onReady(ResponseSearchInsurance responseSearchInsurance) {
        {
            Gson gson2 = new Gson();
            gson2.toJson(responseSearchInsurance);
            Log.e("onResponse", gson2.toJson(responseSearchInsurance) );
            SingletonTimer.getInstance().start();
            hideLoading();
            Log.d("TAG", "onResponse: ");
            if (responseSearchInsurance == null
                    || responseSearchInsurance.getShowInsuranceResult() == null ) {
                showText();
                if (!Utility.isNetworkAvailable(SearchInsuranceActivity.this)) {

                    txt_error.setText(getString(R.string.InternetError));

                } else {

                    txt_error.setText(getString(R.string.ErrorServer));

                }
                error_layout.setVisibility(View.VISIBLE);
                return;
            }

            if (responseSearchInsurance.getShowInsuranceResult().getErrors()!=null) {
                showText();
                txt_error.setText(responseSearchInsurance.getShowInsuranceResult().getErrors().get(0).getMessage());
                error_layout.setVisibility(View.VISIBLE);
                return;
            }

            com.eligasht.service.model.insurance.response.SearchInsurance.TravelInsurance travelInsurance = responseSearchInsurance.getShowInsuranceResult().getTravelInsurance();


            insurancePlan = responseSearchInsurance.getShowInsuranceResult().getInsurancePlan();

            if (travelInsurance == null && insurancePlan == null) {
                showText();
                txt_error.setText(getString(R.string.PackgeNoFound));
                error_layout.setVisibility(View.VISIBLE);
                return;
            }

            if (travelInsurance == null && (insurancePlan != null && insurancePlan.getInsurancePlans()==null)) {
                showText();
                txt_error.setText(getString(R.string.PackgeNoFound));
                error_layout.setVisibility(View.VISIBLE);
                return;
            }

            if ((travelInsurance != null && travelInsurance.getTravelInsurances()==null) && insurancePlan == null) {
                showText();
                txt_error.setText(getString(R.string.PackgeNoFound));
                error_layout.setVisibility(View.VISIBLE);
                return;
            }

            if ((travelInsurance != null && travelInsurance.getTravelInsurances()==null) && (insurancePlan != null && insurancePlan.getInsurancePlans()==null)) {
                showText();
                txt_error.setText(getString(R.string.PackgeNoFound));
                error_layout.setVisibility(View.VISIBLE);
               return;
            }

            if (travelInsurance != null && travelInsurance.getTravelInsurances()!=null) {
                travelInsurances = travelInsurance.getTravelInsurances();
                showTravelInsurances();
            }

            if (insurancePlan != null && insurancePlan.getInsurancePlans()!=null) {
                insurancePlans = insurancePlan.getInsurancePlans();
                showInsurancePlans();
            }

        }
    }

    @Override
    public void onError(String message) {
        Log.e( "showInsurance: ",message);
        hideLoading();
        showText();
        txt_error.setText(getString(R.string.ErrorServer));
        error_layout.setVisibility(View.VISIBLE);
    }

    //send request to server for get list of insurance
    private void showInsurance() {
        InitUi.Toolbar(this, false, R.color.toolbar_color, getString(R.string.Travel_insurance_to_travel_to_the_country) + " " + countryName);

        showLoading();
        /* InsuranceListReq insuranceListReq = new InsuranceListReq();
        insuranceListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        insuranceListReq.setCountryCode(countryCode);
        Log.d("TAG", "showInsurance: "+countryCode);
        insuranceListReq.setDepartureDate(departureDate);
        Log.d("TAG", "showInsurance: "+departureDate);

        insuranceListReq.setAccomodationDays(accomodationDays);
        Log.d("TAG", "showInsurance: "+accomodationDays);

        insuranceListReq.setReturnDate(returnDate);
        Log.d("TAG", "showInsurance: "+returnDate);

        insuranceListReq.setPassCount(passCount);
        Log.d("TAG", "showInsurance: "+passCount);

        insuranceListReq.setBirthDateList(birthDateLists);
        Log.d("TAG", "showInsurance: "+birthDateLists);

        insuranceListReq.setCulture(culture);
        Log.d("TAG", "showInsurance: "+culture);*/

//send GetHListRequest Insurance search
        //Call<InsuranceRes> call = service.showInsurance(new InsuranceRequestModel(insuranceListReq));
        RequestSearchInsurance requestSearchInsurance = new RequestSearchInsurance();
        com.eligasht.service.model.insurance.request.SearchInsurance.Request request = new com.eligasht.service.model.insurance.request.SearchInsurance.Request();

        com.eligasht.service.model.insurance.request.SearchInsurance.Identity identity = new com.eligasht.service.model.insurance.request.SearchInsurance.Identity();

        request.setIdentity(identity);

        request.setCountryCode(countryCode);
        Log.d("TAG", "showInsurance: " + countryCode);
        request.setDepartureDate(departureDate);
        Log.d("TAG", "showInsurance: " + departureDate);

        request.setAccomodationDays(accomodationDays);
        Log.d("TAG", "showInsurance: " + accomodationDays);

        request.setReturnDate(returnDate);
        Log.d("TAG", "showInsurance: " + returnDate);

        request.setPassCount(passCount);
        Log.d("TAG", "showInsurance: " + passCount);

        request.setBirthDateList(birthDateLists);
        Log.d("TAG", "showInsurance: " + birthDateLists);


        request.setCulture(getString(R.string.culture));
        requestSearchInsurance.setRequest(request);
        Log.e("InsuranceRequestModel: ", new Gson().toJson(requestSearchInsurance));
        SingletonService.getInstance().getInsurance().getSearchInsuranceAvail(this, requestSearchInsurance);

/*
        call.enqueue(new Callback<InsuranceRes>() {
            @Override
            public void onResponse(Call<InsuranceRes> call, Response<InsuranceRes> response) {
                hideLoading();
                Log.d("TAG", "onResponse: ");
                if (response == null
                        || response.body() == null
                        || response.body().getShowInsuranceResult() == null) {
                    showText();
                    if (!Utility.isNetworkAvailable(SearchInsuranceActivity.this)) {

                        txt_error.setText(getString(R.string.InternetError));

                    } else {

                        txt_error.setText(getString(R.string.ErrorServer));

                    }
                    error_layout.setVisibility(View.VISIBLE);
                    return;
                }

                if (!ValidationTools.isEmptyOrNull(response.body().getShowInsuranceResult().getError())) {
                    showText();
                    txt_error.setText(response.body().getShowInsuranceResult().getError().get(0).getDetailedMessage());
                    error_layout.setVisibility(View.VISIBLE);
                    return;
                }

                TravelInsurance travelInsurance = response.body().getShowInsuranceResult().getTravelInsurance();
                insurancePlan = response.body().getShowInsuranceResult().getInsurancePlan();

                if (travelInsurance == null && insurancePlan == null) {
                    showText();
                    txt_error.setText(getString(R.string.PackgeNoFound));
                    error_layout.setVisibility(View.VISIBLE);
                    return;
                }

                if (travelInsurance == null && (insurancePlan != null && ValidationTools.isEmptyOrNull(insurancePlan.getInsurancePlans()))) {
                    showText();
                    txt_error.setText(getString(R.string.PackgeNoFound));
                    error_layout.setVisibility(View.VISIBLE);
                    return;
                }

                if ((travelInsurance != null && ValidationTools.isEmptyOrNull(travelInsurance.getTravelInsurances())) && insurancePlan == null) {
                    showText();
                    txt_error.setText(getString(R.string.PackgeNoFound));
                    error_layout.setVisibility(View.VISIBLE);
                    return;
                }

                if ((travelInsurance != null && ValidationTools.isEmptyOrNull(travelInsurance.getTravelInsurances())) && (insurancePlan != null && ValidationTools.isEmptyOrNull(insurancePlan.getInsurancePlans()))) {
                    showText();
                    txt_error.setText(getString(R.string.PackgeNoFound));
                    error_layout.setVisibility(View.VISIBLE);
                    return;
                }

                if (travelInsurance != null && !ValidationTools.isEmptyOrNull(travelInsurance.getTravelInsurances())) {
                    travelInsurances = travelInsurance.getTravelInsurances();
                    showTravelInsurances();
                }

                if (insurancePlan != null && !ValidationTools.isEmptyOrNull(insurancePlan.getInsurancePlans())) {
                    insurancePlans = insurancePlan.getInsurancePlans();
                    showInsurancePlans();
                }

            }

            @Override
            public void onFailure(Call<InsuranceRes> call, Throwable t) {
                hideLoading();
                showText();
                txt_error.setText(getString(R.string.ErrorServer));
                error_layout.setVisibility(View.VISIBLE);

            }
        });
*/
    }
    //first list of insurance
    private void showInsurancePlans() {
        rclInsurancePlans.setVisibility(View.VISIBLE);
        InsurancPlanAdapter insurancPlanAdapter = new InsurancPlanAdapter(this, insurancePlans, passCount).setListener(this);
        rclInsurancePlans.setAdapter(insurancPlanAdapter);
    }

    //second list of insurance
    private void showTravelInsurances() {
        rclTravelInsurance.setVisibility(View.VISIBLE);
        TravelInsurancAdapter travelInsurancAdapter = new TravelInsurancAdapter(this, travelInsurances, passCount).setListener(this);
        rclTravelInsurance.setAdapter(travelInsurancAdapter);
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
    @Override
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
    }

    //onclick the second's list from  insurance
    @Override
    public void onClickInsurancPlanItem(com.eligasht.service.model.insurance.response.SearchInsurance.InsurancePlan_ _insurancePlan) {
        Intent intent = new Intent(this, PassengerInsuranceActivity.class);
        Prefs.putString("CountryCode", countryCode);
        Prefs.putString("DepartureDate", departureDate);
        Prefs.putString("DtStart", departureDate);
        Prefs.putString("ReturnDate", returnDate);
        Prefs.putInt("Price", (_insurancePlan.getPrice()) * passCount);
        Prefs.putString("Id", _insurancePlan.getCode().toString());
        Prefs.putString("SearchKey", insurancePlan.getSearchKey());
        Prefs.putInt("PassCount", passCount);
        startActivity(intent);
    }


  /*  @Override
    public void onClickInsurancPlanItem(com.eligasht.service.model.insurance.response.SearchInsurance.InsurancePlan_ insurancePlan) {

    }*/

   /* @Override
    public void onClickTravelInsurancItem(com.eligasht.service.model.insurance.response.SearchInsurance.TravelInsurance_ travelInsurance) {

    }*/
}
//04-11 18:39:47.865 5428-5428/com.eligasht E/PreFactorDetails:: {"GetPreFactorDetailsResult":{"Errors":[{"Code":-1100.0,"DetailedMessage":"Object reference not set to an instance of an object.","Message":"Object reference not set to an instance of an object."}]}}
