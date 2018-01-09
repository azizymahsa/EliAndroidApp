package com.reserv.myapplicationeli.views.activities.pack;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.model.pack.PRowXfer;
import com.reserv.myapplicationeli.models.model.pack.SearchXPackageResult;
import com.reserv.myapplicationeli.models.model.pack.call.PackageListReq;
import com.reserv.myapplicationeli.models.model.pack.call.PackageRequestModel;
import com.reserv.myapplicationeli.models.model.pack.response.PackageListRes;
import com.reserv.myapplicationeli.tools.datetools.DateUtil;
import com.reserv.myapplicationeli.views.adapters.pack.PRowXferAdapter;
import com.reserv.myapplicationeli.views.components.SimpleRecycleView;
import com.reserv.myapplicationeli.views.ui.InitUi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.reserv.myapplicationeli.base.GlobalApplication.getContext;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class SearchPackActivity extends BaseActivity implements View.OnClickListener {

    public SimpleRecycleView rcl_package;
    public PRowXferAdapter pRowXferAdapter;
    private ClientService service;
    private ArrayList<PRowXfer> pRowXfers;
    private ViewGroup rlLoading;
    private ViewGroup rlRoot;
    private String departureFrom;
    private String departureTo;
    private String country;
    private String culture;
    private String roomList;
    private String cityName;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pack);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.add_room_color_dark));
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
//        String date= DateUtil.getShortStringDate(departureFrom,"yyyy-MM-dd",true)+"-"+DateUtil.getShortStringDate(departureTo,"yyyy-MM-dd",true);
        String city= cityName + " تور";
        InitUi.Toolbar(this, false, R.color.add_room_color, cityName  );

    }

    private void getPackages(String country, String departureFrom, String departureTo, String roomList, String culture) {

        showLoading();
        PackageListReq packageListReq = new PackageListReq();

        packageListReq.setIdentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        packageListReq.setCountry(country);
        packageListReq.setRoomList(roomList);
        packageListReq.setDepartureFrom(departureFrom);
        packageListReq.setDepartureTo(departureTo);
        packageListReq.setCulture(culture);
        Log.e("roomlist", roomList);


        Call<PackageListRes> call = service.getPackageListResult(new PackageRequestModel(packageListReq));
        call.enqueue(new Callback<PackageListRes>() {
            @Override
            public void onResponse(Call<PackageListRes> call, Response<PackageListRes> response) {
                hideLoading();

                if (response == null || response.body() == null) {
                    rcl_package.showText();
                }
                SearchXPackageResult searchXPackageResult = response.body().getSearchXPackageResult();
                if (searchXPackageResult == null) {
                    rcl_package.showText();
                }
               /* if(response.body().getSearchXPackageResult().getError()!= null){
                    Log.i("elham","error : " + response.body().getSearchXPackageResult().getError().toString());
                   // return;
                }*/
                if (searchXPackageResult.getPRowXfers() != null) {
                    pRowXfers = searchXPackageResult.getPRowXfers();
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
        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        rcl_package = (SimpleRecycleView) findViewById(R.id.rcl_package);
        rcl_package.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }


    public void showLoading() {
    //    new InitUi().Loading(this,rlLoading, rlRoot, true,R.drawable.hotel_loading);
        rcl_package.showLoading();
    }


    public void hideLoading() {
    //    new InitUi().Loading(rlLoading, rlRoot, false);
        rcl_package.hideLoading();
    }

    private void showList() {
        pRowXferAdapter = new PRowXferAdapter(this, pRowXfers);
        rcl_package.showList(pRowXferAdapter);
    }
}
