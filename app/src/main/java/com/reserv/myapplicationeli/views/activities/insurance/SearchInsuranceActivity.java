package com.reserv.myapplicationeli.views.activities.insurance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.model.insurance.BirthDateList;
import com.reserv.myapplicationeli.models.model.insurance.InsurancePlan;
import com.reserv.myapplicationeli.models.model.insurance.InsurancePlan_;
import com.reserv.myapplicationeli.models.model.insurance.TravelInsurance;
import com.reserv.myapplicationeli.models.model.insurance.TravelInsurance_;
import com.reserv.myapplicationeli.models.model.insurance.call.InsuranceListReq;
import com.reserv.myapplicationeli.models.model.insurance.call.InsuranceRequestModel;
import com.reserv.myapplicationeli.models.model.insurance.response.InsuranceRes;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.datetools.DateUtil;
import com.reserv.myapplicationeli.views.adapters.insurance.InsurancPlanAdapter;
import com.reserv.myapplicationeli.views.adapters.insurance.TravelInsurancAdapter;
import com.reserv.myapplicationeli.views.ui.InitUi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by elham.bonyani on 1/15/2018.
 */

public class SearchInsuranceActivity extends BaseActivity implements View.OnClickListener,TravelInsurancAdapter.ListenerTravelInsurancAdapter,InsurancPlanAdapter.ListenerInsurancPlanAdapter{

    private ArrayList<BirthDateList> birthDateLists;
    private int passCount;
    private RecyclerView rclTravelInsurance;
    private RecyclerView rclInsurancePlans;
    private ProgressBar prg;
    private TextView txt;
    private ClientService service;
    private String departureDate;
    private String returnDate;
    private String countryCode;
    private String countryName;
    private String culture;
    private int accomodationDays;
    private Gson gson ;
    private ArrayList<TravelInsurance_> travelInsurances;
    private ArrayList<InsurancePlan_> insurancePlans;
    InsurancePlan insurancePlan;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_insurance);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
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

            long _milis = DateUtil.getMiliSecondGregorianDateTime(departureDate,"yyyy-MM-dd") + (accomodationDays*24*60*60*1000);
            returnDate = DateUtil.getDateTime(String.valueOf(_milis),"yyyy-MM-dd");
        }

        showInsurance();
    }

    private void showInsurance() {
        InitUi.Toolbar(this, false, R.color.flight_status,  "بیمه مسافرتی برای سفر به کشور" + countryName);

        showLoading();
        InsuranceListReq insuranceListReq = new InsuranceListReq();
        insuranceListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        insuranceListReq.setCountryCode(countryCode);
        insuranceListReq.setDepartureDate(departureDate);
        insuranceListReq.setAccomodationDays(accomodationDays);
        insuranceListReq.setReturnDate(returnDate);
        insuranceListReq.setPassCount(passCount);
        insuranceListReq.setBirthDateList(birthDateLists);
        insuranceListReq.setCulture(culture);

        Call<InsuranceRes> call = service.showInsurance(new InsuranceRequestModel(insuranceListReq));
        call.enqueue(new Callback<InsuranceRes>() {
            @Override
            public void onResponse(Call<InsuranceRes> call, Response<InsuranceRes> response) {
                hideLoading();
                if (response == null
                        || response.body() == null
                        || response.body().getShowInsuranceResult() == null) {
                    showText();
                    return;
                }

                TravelInsurance travelInsurance = response.body().getShowInsuranceResult().getTravelInsurance();
                  insurancePlan = response.body().getShowInsuranceResult().getInsurancePlan();

                if(travelInsurance == null && insurancePlan == null){
                    showText();
                    return;
                }

                if(travelInsurance != null && !ValidationTools.isEmptyOrNull(travelInsurance.getTravelInsurances()) ){
                    travelInsurances = travelInsurance.getTravelInsurances();
                    showTravelInsurances();
                }
                if(insurancePlan != null && !ValidationTools.isEmptyOrNull(insurancePlan.getInsurancePlans()) ){
                    insurancePlans = insurancePlan.getInsurancePlans();
                    showInsurancePlans();
                }

            }

            @Override
            public void onFailure(Call<InsuranceRes> call, Throwable t) {
                hideLoading();
                Toast.makeText(SearchInsuranceActivity.this, "خطا در ارتباط", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showInsurancePlans() {
        InsurancPlanAdapter insurancPlanAdapter = new InsurancPlanAdapter(this,insurancePlans,passCount).setListener(this);
        rclInsurancePlans.setAdapter(insurancPlanAdapter);
    }

    private void showTravelInsurances() {
        TravelInsurancAdapter travelInsurancAdapter = new TravelInsurancAdapter(this,travelInsurances,passCount).setListener(this);
        rclTravelInsurance.setAdapter(travelInsurancAdapter);
    }

    private void showText() {
        prg.setVisibility(View.GONE);
        rclTravelInsurance.setVisibility(View.GONE);
        rclInsurancePlans.setVisibility(View.GONE);
        txt.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        rclTravelInsurance = findViewById(R.id.rclTravelInsurance);
        rclInsurancePlans = findViewById(R.id.rclInsurancePlans);
        prg = findViewById(R.id.prg);
        txt = findViewById(R.id.txt);
        rclTravelInsurance.setLayoutManager(new LinearLayoutManager(this));
        rclInsurancePlans.setLayoutManager(new LinearLayoutManager(this));
        TextView tvTitleDate = findViewById(R.id.tvTitleDate);
        tvTitleDate.setVisibility(View.GONE);
    }

    private void showLoading(){
        prg.setVisibility(View.VISIBLE);
        rclTravelInsurance.setVisibility(View.GONE);
        rclInsurancePlans.setVisibility(View.GONE);
        txt.setVisibility(View.GONE);
    }

    private void hideLoading(){
        prg.setVisibility(View.GONE);
        rclTravelInsurance.setVisibility(View.VISIBLE);
        rclInsurancePlans.setVisibility(View.VISIBLE);
        txt.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onClickTravelInsurancItem(TravelInsurance_ travelInsurance) {
        Intent intent = new Intent(this, PassengerInsuranceActivity.class);
        Prefs.putString("CountryCode",countryCode );
        Prefs.putString("DepartureDate",departureDate );
        Prefs.putString("DtStart",departureDate );
        Prefs.putString("ReturnDate",returnDate );
        Prefs.putInt("Price",(travelInsurance.getTravelInsurancePricePP().getAmount())*passCount);
        Prefs.putString("Id",travelInsurance.getInsID().toString());
        startActivity(intent);
    }

    @Override
    public void onClickInsurancPlanItem(InsurancePlan_ _insurancePlan) {
        Intent intent = new Intent(this, PassengerInsuranceActivity.class);
        Prefs.putString("CountryCode",countryCode );
        Prefs.putString("DepartureDate",departureDate );
        Prefs.putString("DtStart",departureDate );
        Prefs.putString("ReturnDate",returnDate );
        Prefs.putInt("Price",(_insurancePlan.getPrice())* passCount);
        Prefs.putString("Id",_insurancePlan.getCode().toString());
        Prefs.putString("SearchKey",insurancePlan.getSearchKey());
        startActivity(intent);
    }
}
