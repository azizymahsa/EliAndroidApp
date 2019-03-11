package com.eligasht.reservation.views.activities.train;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;


import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;

import com.eligasht.reservation.base.SingletonTimer;
import com.eligasht.reservation.models.hotel.FilterPriceModel;
import com.eligasht.reservation.models.hotel.adapter.FilterModel;
import com.eligasht.reservation.models.hotel.adapter.FilterStarModel;

import com.eligasht.reservation.models.train.adapter.SelectTrainModel;
import com.eligasht.reservation.tools.GlideApp;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;

import com.eligasht.reservation.views.adapters.train.TrainResultAdapter;
import com.eligasht.reservation.views.adapters.weather.WeatherAdapter;

import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.ui.InitUi;

import com.eligasht.reservation.views.ui.SearchFlightActivity;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelDialog;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelTypeModel;
import com.eligasht.reservation.views.ui.dialog.hotel.SortDialog;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;

import com.eligasht.service.model.hotel.hotelAvail.request.Room;

import com.eligasht.service.model.newModel.hotel.preSearch.response.ResponseHotelPreSearch;
import com.eligasht.service.model.newModel.hotel.purchase.request.Passenger;
import com.eligasht.service.model.newModel.hotel.search.request.RequestHotelSearch;
import com.eligasht.service.model.newModel.hotel.search.request.UserAgentObject;
import com.eligasht.service.model.newModel.hotel.search.response.ResponseHotelSearch;
import com.eligasht.service.model.newModel.train.domesticSearch.Request.QueryModel;
import com.eligasht.service.model.newModel.train.domesticSearch.Request.RequestDomesticTrainAPI;
import com.eligasht.service.model.newModel.train.domesticSearch.response.ResponseDomesticTrainAPI;
import com.eligasht.service.model.newModel.train.domesticSearch.response.Train;
import com.eligasht.service.model.weather.response.WeatherApi;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class SearchTrainActivity extends BaseActivity implements FilterHotelDialog.FilterHotelDialogListenerArray, View.OnClickListener, SortDialog.SortHotelDialogListener,
        OnServiceStatus<ResponseHotelSearch> {
    private RelativeLayout rlLoading, rlRoot;
    public static  RelativeLayout rlList;
    public static  LinearLayout btnReserv;
    private TextView tvAlert;
    private static TextView tvDate;
    private TextView tvCount;
    private TextView tvFilterIcon;
    private TextView tvFilter;
    private TextView tvSortIcon;
    private TextView tvSort;
    private Window window;
    private RelativeLayout elNotFound, rlEr;
    private TextView tvLoading, tvAlertDesc,weatherCity;
    private double  maxPrice, minPrice;
    private static LinearLayout llFilter;
    private FancyButton btnOk, btnBack, btnHome;
    private FancyButton btnNextDays, btnLastDays;
    private String raft, bargasht;
    private String raftFa, bargashtFa, searchIn;
    public static String globalResultUniqID;
    public static Boolean FLAG_SELECT_TRAIN=false;
    private ArrayList<SelectTrainModel> selectTrainModelArrayListTrue = new ArrayList<>();
    private static ArrayList<SelectTrainModel> selectTrainModelArrayListFalse = new ArrayList<>();
    private static ArrayList<SelectTrainModel> selectTrainModelArrayListFalseNew = new ArrayList<>();
    private ArrayList<SelectTrainModel> selectTrainModelArrayListFilter = new ArrayList<>();
    private ArrayList<FilterModel> filterModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelTypeModel = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelLocationModels = new ArrayList<>();
    private ArrayList<FilterPriceModel> filterHotelPriceModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelBestOffModels = new ArrayList<>();
    private ArrayList<FilterStarModel> filterHotelStarsModels = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    private FancyButton btnFilter, btnSort,btnChangeView;
    private RecyclerView rvWeather;
    SlidingDrawer slidingDrawer;
    static RecyclerView rvTrainResult;
    static TrainResultAdapter trainResultAdapter;
    private ClientService service;
    static boolean isGrid=false;
    private TextView txtCityRaft,txtCityBargashtt;
    int flagOneTwo;
//raft
    public static  ImageView imgTrainlineNameEn;
    public static LinearLayout selectRaftInclud,selectBargashtInclud,lnrHeaderMsg;
    public static  TextView lblTrainNumber;
    public static  TextView txt_select;
    public static  TextView txtTotalFare;
    public static  TextView txtDestinationText;
    public static TextView txtSourceText;
    public static  TextView txtTrainArrivalTime,txtHeaderMsg;
    public static  AVLoadingIndicatorView avi;
    public static  TextView txtTrainTime,lblHasMedia,lblAirConditioning,txtSeatsRemaining,txtCompartmentCapacity,txtSaloonName,txtHotel,txtNameTrain;
    //bargasht
    public static  ImageView imgTrainlineNameEnB;
    public static  TextView lblTrainNumberB;
    public static  TextView txt_selectB;
    public static  TextView txtTotalFareB;
    public static  TextView txtDestinationTextB;
    public static TextView txtSourceTextB;
    public static  TextView txtTrainArrivalTimeB;
    public static  AVLoadingIndicatorView aviB;
    public static  TextView txtTrainTimeB,lblHasMediaB,lblAirConditioningB,txtSeatsRemainingB,txtCompartmentCapacityB,txtSaloonNameB,txtHotelB,txtNameTrainB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_train);

        window = getWindow();
        service = ServiceGenerator.createService(ClientService.class);
        initView();

        raftFa = SingletonDate.getInstance().getStartDate().getDescription();
        bargashtFa = SingletonDate.getInstance().getEndDate().getDescription();
        rooms.add(new Room(getIntent().getExtras().getInt("Adult"), getIntent().getExtras().getInt("Child")));

        raft = SingletonDate.getInstance().getStartDate().getFullGeo();
        bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();

        Utility.setAnimLoading(this);
        Utility.loadingText(tvLoading, Prefs.getString("F", ""));
        notiRecive();
        // Auth_request();
        train_request();
        // weather_request();
        tvDate.setText(raftFa + " - " + bargashtFa);

        rvWeather.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rvTrainResult.setLayoutManager(new LinearLayoutManager(this));
        trainResultAdapter = new TrainResultAdapter(selectTrainModelArrayListTrue,this,tvDate,isGrid);
        rvTrainResult.setAdapter(trainResultAdapter);

    }//endOnCreate

    private void initView() {

        txtCityRaft = findViewById(R.id.txtCityRaft);
        txtCityBargashtt = findViewById(R.id.txtCityBargashtt);
        rvTrainResult = findViewById(R.id.rvTrainResult);
        rlList = findViewById(R.id.rlList);
        btnReserv = findViewById(R.id.btnReserv);
        tvLoading = findViewById(R.id.tvLoading);
        btnFilter = findViewById(R.id.btnFilter);
        btnSort = findViewById(R.id.btnSort);
        tvAlert = findViewById(R.id.tvAlert);
        txtHeaderMsg = findViewById(R.id.txtHeaderMsg);
        lnrHeaderMsg = findViewById(R.id.lnrHeaderMsg);
        tvCount = findViewById(R.id.tvCount);
        btnBack = findViewById(R.id.btnBack);
        tvFilterIcon = findViewById(R.id.tvFilterIcon);
        btnHome = findViewById(R.id.btnHome);
        elNotFound = findViewById(R.id.elNotFound);
        tvSortIcon = findViewById(R.id.tvSortIcon);
        btnChangeView = findViewById(R.id.btnChangeView);
        tvSort = findViewById(R.id.tvSort);
        tvFilter = findViewById(R.id.tvFilter);
        btnOk = findViewById(R.id.btnOk);
        tvDate = findViewById(R.id.tvDate);
        llFilter = findViewById(R.id.llFilter);
        btnNextDays = findViewById(R.id.btnNextDays);
        btnLastDays = findViewById(R.id.btnLastDays);
        rlEr = findViewById(R.id.rlEr);
        tvAlertDesc = findViewById(R.id.tvAlertDesc);
        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        rvWeather = findViewById(R.id.rvWeather);
        slidingDrawer = findViewById(R.id.slidingDrawer);
        weatherCity = findViewById(R.id.weatherCity);
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnChangeView.setCustomTextFont("fonts/icomoon2.ttf");
        btnChangeView.setText(getString(R.string.icon_grid));
        btnOk.setCustomTextFont(getResources().getString(R.string.iran_sans_normal_ttf));

        btnFilter.setOnClickListener(this);
        btnSort.setOnClickListener(this);
        btnNextDays.setOnClickListener(this);
        btnLastDays.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnChangeView.setOnClickListener(this);
        btnReserv.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String raft = extras.getString("Value_Mabda_Train");
            String bargasht = extras.getString("Value_Maghsad_Train");
            txtCityRaft.setText(raft);
            txtCityBargashtt.setText(bargasht);
        }
        //Train
        selectRaftInclud = findViewById(R.id.selectRaftInclud);
        selectBargashtInclud = findViewById(R.id.selectBargashtInclud);
        //raft
        imgTrainlineNameEn = findViewById(R.id.imgTrainlineNameEn);
        lblTrainNumber =  findViewById(R.id.lblTrainNumber);
        txt_select =  findViewById(R.id.txt_select);
        txtTotalFare =  findViewById(R.id.txtTotalFare);
        txtDestinationText =  findViewById(R.id.txtDestinationText);
        txtSourceText =  findViewById(R.id.txtSourceText);
        txtTrainArrivalTime =  findViewById(R.id.txtTrainArrivalTime);
        txtTrainTime =  findViewById(R.id.txtTrainTime);
        lblHasMedia =  findViewById(R.id.lblHasMedia);
        lblAirConditioning =  findViewById(R.id.lblAirConditioning);
        txtSeatsRemaining =  findViewById(R.id.txtSeatsRemaining);
        txtCompartmentCapacity =  findViewById(R.id.txtCompartmentCapacity);
        txtSaloonName =  findViewById(R.id.txtSaloonName);
        txtHotel =  findViewById(R.id.txtHotel);
        txtNameTrain =  findViewById(R.id.txtNameTrain);
        avi =  findViewById(R.id.avi);

        txt_select.setOnClickListener(this);
        //bargasht
        imgTrainlineNameEnB = findViewById(R.id.imgTrainlineNameEnB);
        lblTrainNumberB =  findViewById(R.id.lblTrainNumberB);
        txt_selectB =  findViewById(R.id.txt_selectB);
        txtTotalFareB =  findViewById(R.id.txtTotalFareB);
        txtDestinationTextB =  findViewById(R.id.txtDestinationTextB);
        txtSourceTextB =  findViewById(R.id.txtSourceTextB);
        txtTrainArrivalTimeB =  findViewById(R.id.txtTrainArrivalTimeB);
        txtTrainTimeB =  findViewById(R.id.txtTrainTimeB);
        lblHasMediaB =  findViewById(R.id.lblHasMediaB);
        lblAirConditioningB =  findViewById(R.id.lblAirConditioningB);
        txtSeatsRemainingB =  findViewById(R.id.txtSeatsRemainingB);
        txtCompartmentCapacityB =  findViewById(R.id.txtCompartmentCapacityB);
        txtSaloonNameB =  findViewById(R.id.txtSaloonNameB);
        txtHotelB =  findViewById(R.id.txtHotelB);
        txtNameTrainB =  findViewById(R.id.txtNameTrainB);
        aviB =  findViewById(R.id.aviB);

        txt_selectB.setOnClickListener(this);
    }


    @Override
    public boolean needTerminate() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReserv:
                Prefs.putString("Value_TrainId", selectTrainModelArrayListFalseNew.get(0).getTrainId());
                Intent intent1 = new Intent(getApplicationContext(), PassengerTrainActivity.class);
               /* Prefs.putString("Segmengt_Id_False", selectTrainModel.getID());
                Prefs.putString("Segmengt_Id_True", selectTrainModel.getID());
                Prefs.putString("Train_Searchkey_Search", responseDomesticTrainAPI.getSearchKey());*/

               // intent1.putExtra("Value_TrainId", selectTrainModelArrayListFalseNew.get(0).getTrainId());
                startActivity(intent1);
///cTrainActivity
                break;
            case R.id.txt_select://تغییر بلیط رفت
                txtHeaderMsg.setText("لطفا ابتدا بلیط رفت را انتخاب کنید");
                txtHeaderMsg.setVisibility(View.VISIBLE);
                lnrHeaderMsg.setVisibility(View.VISIBLE);
                btnReserv.setVisibility(View.GONE);
                selectRaftInclud.setVisibility(View.GONE);
                selectBargashtInclud.setVisibility(View.GONE);

                trainResultAdapter = new TrainResultAdapter(selectTrainModelArrayListTrue,this,tvDate,isGrid);
                rvTrainResult.setAdapter(trainResultAdapter);
                rlList.setVisibility(View.VISIBLE);
                llFilter.setVisibility(View.VISIBLE);
                break;
                case R.id.txt_selectB://تغییر بلیط برگشت
                txtHeaderMsg.setText("لطفا بلیط برگشت را انتخاب کنید");
                txtHeaderMsg.setVisibility(View.VISIBLE);
                lnrHeaderMsg.setVisibility(View.VISIBLE);
                btnReserv.setVisibility(View.GONE);
                selectRaftInclud.setVisibility(View.VISIBLE);
                selectBargashtInclud.setVisibility(View.GONE);

                trainResultAdapter = new TrainResultAdapter(selectTrainModelArrayListFalseNew,this,tvDate,isGrid);
                rvTrainResult.setAdapter(trainResultAdapter);
                rlList.setVisibility(View.VISIBLE);
                llFilter.setVisibility(View.VISIBLE);
                break;
                case R.id.btnChangeView:
                if (isGrid){
                    isGrid=false;
                    Log.e("selectTrainModelArrayListTre: ",selectTrainModelArrayListTrue.size()+"" );
                    rvTrainResult.setLayoutManager(new LinearLayoutManager(this));
                    trainResultAdapter = new TrainResultAdapter(selectTrainModelArrayListTrue,this,tvDate,isGrid);
                    rvTrainResult.setAdapter(trainResultAdapter);
                    btnChangeView.setText(getString(R.string.icon_grid));
                    // trainResultAdapter.onAttachedToRecyclerView(rvTrainResult);
                    //  trainResultAdapter.notify();


                }else{
                    isGrid=true;
                    Log.e("selectTrainModelArrayListTrue: ",selectTrainModelArrayListTrue.size()+"" );
                    rvTrainResult.setLayoutManager(new GridLayoutManager(this,3));
                    trainResultAdapter = new TrainResultAdapter(selectTrainModelArrayListTrue,this,tvDate,isGrid);
                    rvTrainResult.setAdapter(trainResultAdapter);
                    btnChangeView.setText(getString(R.string.icon_list));
                    // trainResultAdapter.onAttachedToRecyclerView(rvTrainResult);
//                    trainResultAdapter.notify();

                }
                break;
            case R.id.btnOk:
                finish();
                break;
            case R.id.btnFilter:
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                FilterHotelDialog filterHotelDialog = FilterHotelDialog.newInstance(SearchTrainActivity.this, filterModels, SearchTrainActivity.this, filterHotelTypeModel,
                        filterHotelFacilitiesModels, filterHotelPriceModels, searchIn, filterHotelLocationModels, filterHotelBestOffModels, filterHotelStarsModels);
                filterHotelDialog.show(fm, "test");
                break;
            case R.id.btnSort:
                new SortDialog(SearchTrainActivity.this, this);
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnHome:
                Intent intent = new Intent("sendFinish");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                break;
            case R.id.btnNextDays:
                if (SingletonDate.getInstance().getStartDate().addOneDay()) {
                    tvDate.setText(SingletonDate.getInstance().getStartDate().getDescription() + " - " + SingletonDate.getInstance().getEndDate().getDescription());
                    raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                    bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
                    train_request();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.datePickerError,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnLastDays:
                if (SingletonDate.getInstance().getStartDate().minusOneDay()) {
                    tvDate.setText(SingletonDate.getInstance().getStartDate().getDescription() + " - " + SingletonDate.getInstance().getEndDate().getDescription());
                    raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                    bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
                    train_request();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.DatePickerError2,Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onReturnValue(ArrayList<FilterModel> type, String search, ArrayList<FilterHotelTypeModel> filterHotelTypeModels,
                              ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels,
                              ArrayList<FilterPriceModel> filterHotelPriceModel,
                              ArrayList<FilterHotelTypeModel> filterHotelLocationModels, ArrayList<FilterHotelTypeModel> filterHotelBestOffModels
            , ArrayList<FilterStarModel> filterHotelStarsModels, boolean remove) {
        elNotFound.setVisibility(View.GONE);
        rvTrainResult.setVisibility(View.VISIBLE);
        rlList.setVisibility(View.VISIBLE);
        btnOk.setVisibility(View.VISIBLE);
        rlEr.setVisibility(View.VISIBLE);
        this.filterModels = type;
        this.searchIn = search;
        this.filterHotelTypeModel = filterHotelTypeModels;
        this.filterHotelPriceModels = filterHotelPriceModel;
        this.filterHotelFacilitiesModels = filterHotelFacilitiesModels;
        this.filterHotelLocationModels = filterHotelLocationModels;
        this.filterHotelBestOffModels = filterHotelBestOffModels;
        this.filterHotelStarsModels = filterHotelStarsModels;
       /* selectTrainModelArrayListFilter = new ArrayList<>();
        selectTrainModelArrayListFilter = best_0ff(filterHotelBestOffModels);
        selectTrainModelArrayListFilter = star(filterHotelStarsModels);
        selectTrainModelArrayListFilter = type(filterHotelTypeModels);
        selectTrainModelArrayListFilter = location(filterHotelLocationModels);
        selectTrainModelArrayListFilter = facility(filterHotelFacilitiesModels);
        selectTrainModelArrayListFilter = price(filterHotelPriceModel);
        if (search != null) {
            selectTrainModelArrayListFilter = searchText(search);
        }*/
        if (selectTrainModelArrayListFilter.size() == selectTrainModelArrayListTrue.size() && !remove) {
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
          /*  adapter = new LazyResoultHotelAdapter(selectTrainModelArrayList, SearchTrainActivity.this, SearchTrainActivity.this, tvDate);
            list.setAdapter(adapter);*/
            trainResultAdapter = new TrainResultAdapter(selectTrainModelArrayListTrue,this,tvDate,isGrid);
            rvTrainResult.setAdapter(trainResultAdapter);
            elNotFound.setVisibility(View.VISIBLE);
            rvTrainResult.setVisibility(View.GONE);
            rlList.setVisibility(View.GONE);
            btnOk.setVisibility(View.GONE);
            rlEr.setVisibility(View.GONE);
            tvAlert.setText(R.string.filter_no_found);
            tvAlertDesc.setText(R.string.change_filter);
        } else {
            if (selectTrainModelArrayListFilter.isEmpty()) {
                tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
                tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));

                trainResultAdapter = new TrainResultAdapter(selectTrainModelArrayListTrue,this,tvDate,isGrid);
                rvTrainResult.setAdapter(trainResultAdapter);
                elNotFound.setVisibility(View.VISIBLE);
                rvTrainResult.setVisibility(View.GONE);
                rlList.setVisibility(View.GONE);
                btnOk.setVisibility(View.GONE);
                rlEr.setVisibility(View.GONE);
                tvAlert.setText(R.string.filter_no_found);
                tvAlertDesc.setText(R.string.change_filter);
            } else {
                tvFilter.setTextColor(ContextCompat.getColor(this, R.color.red));
                tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.red));

                trainResultAdapter = new TrainResultAdapter(selectTrainModelArrayListFilter,this,tvDate,isGrid);
                rvTrainResult.setAdapter(trainResultAdapter);
            }
        }
        if (remove) {
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
           /* adapter = new LazyResoultHotelAdapter(selectTrainModelArrayList, SearchTrainActivity.this, SearchTrainActivity.this, tvDate);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();*/
            trainResultAdapter = new TrainResultAdapter(selectTrainModelArrayListTrue,this,tvDate,isGrid);
            rvTrainResult.setAdapter(trainResultAdapter);
            searchIn = "";
        }
    }





    /*public ArrayList<SelectTrainModel> price(ArrayList<FilterPriceModel> filterPriceModels) {
       *//* boolean isFilter = false;
        ArrayList<SelectTrainModel> selectHotelModels = new ArrayList<>();
        if (selectTrainModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectTrainModelArrayList;
        } else {
            selectHotelModels = selectTrainModelArrayListFilter;
        }
        ArrayList<SelectTrainModel> filter = new ArrayList<>();
        for (int j = 0; j < filterPriceModels.size(); j++) {
            if (filterPriceModels.get(j).isCheck()) {
                for (int i = 0; i < selectHotelModels.size(); i++) {
                    isFilter = true;
                    if (filterPriceModels.get(j).getX() == selectHotelModels.get(i).getDiff()) {
                        filter.add(Add_To(i));
                    }
                }
            }
        }
        if (!isFilter) {
            return selectHotelModels;
        } else {
            return filter;
        }*//*
    }*/





//Sort
    @Override
    public void onReturnValue(int type) {
       tvSort.setTextColor(ContextCompat.getColor(this, R.color.red));
        tvSortIcon.setTextColor(ContextCompat.getColor(this, R.color.red));
        switch (type) {
            case 1:
                Collections.sort(selectTrainModelArrayListTrue, new Comparator<SelectTrainModel>() {
                    @Override
                    public int compare(SelectTrainModel p1, SelectTrainModel p2) {
                        return p2.getTotalFare() - p1.getTotalFare(); // Ascending
                    }
                });
                Collections.sort(selectTrainModelArrayListFalseNew, new Comparator<SelectTrainModel>() {
                    @Override
                    public int compare(SelectTrainModel p1, SelectTrainModel p2) {
                        return p2.getTotalFare() - p1.getTotalFare(); // Ascending
                    }
                });
                trainResultAdapter.notifyDataSetChanged();
                break;
            case 2:
                Collections.sort(selectTrainModelArrayListTrue, new Comparator<SelectTrainModel>() {
                    @Override
                    public int compare(SelectTrainModel p1, SelectTrainModel p2) {
                        return p1.getTotalFare() - p2.getTotalFare(); // Ascending
                    }
                });
                Collections.sort(selectTrainModelArrayListFalseNew, new Comparator<SelectTrainModel>() {
                    @Override
                    public int compare(SelectTrainModel p1, SelectTrainModel p2) {
                        return p1.getTotalFare() - p2.getTotalFare(); // Ascending
                    }
                });
                trainResultAdapter.notifyDataSetChanged();
                break;
        }
    }

    public void train_request() {
        selectTrainModelArrayListTrue.clear();
        selectTrainModelArrayListFalse.clear();
        selectTrainModelArrayListFalseNew.clear();
        selectTrainModelArrayListFilter.clear();
        filterModels.clear();
        filterHotelTypeModel.clear();
        filterHotelFacilitiesModels.clear();
        filterHotelLocationModels.clear();
        filterHotelPriceModels.clear();
        filterHotelBestOffModels.clear();
        filterHotelStarsModels.clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SearchTrainActivity.this, R.color.status_loading));
        }
        new InitUi().Loading(SearchTrainActivity.this, rlLoading, rlRoot, true, R.drawable.train_loading);
        Gson gson = new Gson();
        RequestDomesticTrainAPI requestHotelPreSearch = new RequestDomesticTrainAPI();
        QueryModel request = new QueryModel();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            flagOneTwo = extras.getInt("Value_FlagOneTwo");

            String valueTrip=createTrip(raft.replaceAll("/", "-"),bargasht.replaceAll("/", "-"),flagOneTwo);

        request.setTrip(valueTrip);
        request.setInfant(extras.getString("Value_Pass_CountN"));
        request.setExclusiveTrain(extras.getBoolean("Value_ExclusiveTrain"));
        request.setCategory("DomesticTrain");
        request.setAdult(extras.getString("Value_Pass_CountB"));
        request.setCurrentCulture(getString(R.string.culture));
        request.setChild(extras.getString("Value_Pass_CountK"));
        request.setPType(extras.getString("Value_PType"));



        requestHotelPreSearch.setQueryModel(request);

        Log.e("RequestDomesticTrainAPI:", gson.toJson(requestHotelPreSearch));
        }
        SingletonService.getInstance().getTrain().newGetTrainSearchAvail(new OnServiceStatus<ResponseDomesticTrainAPI>() {
            @Override
            public void onReady(ResponseDomesticTrainAPI responseDomesticTrainAPI) {
                Log.e("ResponseDomesticTrainAPI:", gson.toJson(responseDomesticTrainAPI));
                new InitUi().Loading(SearchTrainActivity.this, rlLoading, rlRoot, false, R.drawable.train_loading);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(SearchTrainActivity.this, R.color.status_loading));
                }
                selectTrainModelArrayListTrue.clear();
                selectTrainModelArrayListFalse.clear();
                selectTrainModelArrayListFalseNew.clear();
                selectTrainModelArrayListFilter.clear();
                try {
                    globalResultUniqID= responseDomesticTrainAPI.getSearchKey();
                    if (responseDomesticTrainAPI.getErrors().size() > 0) {
                        elNotFound.setVisibility(View.VISIBLE);
                        tvAlert.setText(responseDomesticTrainAPI.getErrors().get(0).getMessage());
                        rvTrainResult.setVisibility(View.GONE);
                        rlList.setVisibility(View.GONE);
                        llFilter.setVisibility(View.GONE);
                        lnrHeaderMsg.setVisibility(View.GONE);
                    } else if (responseDomesticTrainAPI.getTrains().isEmpty()) {
                        elNotFound.setVisibility(View.VISIBLE);
                        tvAlert.setText(R.string.NoResult);
                        tvAlertDesc.setText(getString(R.string.change_date));
                        rvTrainResult.setVisibility(View.GONE);
                        rlList.setVisibility(View.GONE);
                        llFilter.setVisibility(View.GONE);
                        lnrHeaderMsg.setVisibility(View.GONE);
                    } else {
                        SingletonTimer.getInstance().start();

                        /*maxPrice = responseDomesticTrainAPI.getMaxPrice();
                        minPrice = responseDomesticTrainAPI.getMinPrice();
                        double  dif = maxPrice - minPrice;
                        dif = dif / 5;
                        double  x0 = minPrice;
                        double  x1 = x0 + dif;
                        double  x2 = x1 + dif;
                        double  x3 = x2 + dif;
                        double  x4 = x3 + dif;
                        double  x5 = x4 + dif;
                        filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x0)) + "-" + Utility.priceFormat(String.valueOf(x1)), 1, false));
                        filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x1)) + "-" + Utility.priceFormat(String.valueOf(x2)), 2, false));
                        filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x2)) + "-" + Utility.priceFormat(String.valueOf(x3)), 3, false));
                        filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x3)) + "-" + Utility.priceFormat(String.valueOf(x4)), 4, false));
                        filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x4)) + "-" + Utility.priceFormat(String.valueOf(x5)), 5, false));
                        Collections.reverse(filterHotelPriceModels);*/
                        int i = 0;
                     //  handleTrainList(responseDomesticTrainAPI);

                        Prefs.putString("Train_Searchkey_Search", responseDomesticTrainAPI.getSearchKey());
                        for (Train trains : responseDomesticTrainAPI.getTrains()){
                            for (int j = 0; j < trains.getSegmentList().size(); j++) {
                                if (trains.getSegmentList().get(j).getIsDepartureSegment()) {
                                    selectTrainModelArrayListTrue.add(new SelectTrainModel(trains.getSegmentList().get(j).getPrivateID(),
                                            trains.getSegmentList().get(j).getID(),
                                            trains.getSegmentList().get(j).getTrainlineID(),
                                            trains.getSegmentList().get(j).getTrainlineCode(),
                                            trains.getSegmentList().get(j).getTrainlineNameEn(),
                                            trains.getSegmentList().get(j).getTrainlineNameFa(),
                                            responseDomesticTrainAPI.getSearchedProduct().getSourceText(),
                                            responseDomesticTrainAPI.getSearchedProduct().getDestinationText(),
                                            trains.getSegmentList().get(j).getTrainTime(),
                                            trains.getSegmentList().get(j).getTrainArrivalTime(),
                                            trains.getSegmentList().get(j).getTrainNumber(),
                                            trains.getSegmentList().get(j).getTotalFare().getAmount(),
                                            trains.getSegmentList().get(j).getTrainFacility(),
                                            trains.getSegmentList().get(j).getTrainFacility().getHasAirConditioning(),
                                            trains.getSegmentList().get(j).getTrainFacility().getHasMedia(),
                                            trains.getSegmentList().get(j).getSeatsRemaining(),
                                            trains.getSegmentList().get(j).getSeatAvailable(),
                                            trains.getSegmentList().get(j).getCompartmentCapacity(),
                                            trains.getSegmentList().get(j).getSaloon().getName(),
                                            trains.getSegmentList().get(j).getHotelIsIncluded(),
                                            trains.getSegmentList().get(j).getIsDepartureSegment(),
                                            "انتخاب بلیط رفت",
                                            trains.getTrainID()
                                    ));
                                }else{

                                    selectTrainModelArrayListFalse.add(new SelectTrainModel(trains.getSegmentList().get(j).getPrivateID(),
                                            trains.getSegmentList().get(j).getID(),
                                            trains.getSegmentList().get(j).getTrainlineID(),
                                            trains.getSegmentList().get(j).getTrainlineCode(),///ino ckeck kon
                                            trains.getSegmentList().get(j).getTrainlineNameEn(),
                                            trains.getSegmentList().get(j).getTrainlineNameFa(),
                                            responseDomesticTrainAPI.getSearchedProduct().getSourceText(),
                                            responseDomesticTrainAPI.getSearchedProduct().getDestinationText(),
                                            trains.getSegmentList().get(j).getTrainTime(),
                                            trains.getSegmentList().get(j).getTrainArrivalTime(),
                                            trains.getSegmentList().get(j).getTrainNumber(),
                                            trains.getSegmentList().get(j).getTotalFare().getAmount(),
                                            trains.getSegmentList().get(j).getTrainFacility(),
                                            trains.getSegmentList().get(j).getTrainFacility().getHasAirConditioning(),
                                            trains.getSegmentList().get(j).getTrainFacility().getHasMedia(),
                                            trains.getSegmentList().get(j).getSeatsRemaining(),
                                            trains.getSegmentList().get(j).getSeatAvailable(),
                                            trains.getSegmentList().get(j).getCompartmentCapacity(),
                                            trains.getSegmentList().get(j).getSaloon().getName(),
                                            trains.getSegmentList().get(j).getHotelIsIncluded(),
                                            trains.getSegmentList().get(j).getIsDepartureSegment(),
                                            "انتخاب بلیط برگشت",
                                            trains.getTrainID()
                                    ));
                                }
                            }



                      /*      String off = "";
                            boolean isOff = false;
                            int xiff = 0;
                            int hotelPrice = responseDomesticTrainAPI.getTrains().get(i).getTotalFare().getAmount();
                            if ((hotels.getAvailability().getRoomLists().get(i).getOldPrice() > 0) &&
                                    (hotels.getAvailability().getRoomLists().get(i).getOldPrice() > hotels.getAvailability().getRoomLists().get(i).getPrice())) {
                                int p1 = Integer.valueOf(hotels.getAvailability().getRoomLists().get(i).getOldPrice().intValue()) - Integer.valueOf(hotels.getAvailability().getRoomLists().get(i).getPrice().intValue());
                                int p2 = p1 * 100;
                                int p3 = p2 / Integer.parseInt(String.valueOf(hotels.getAvailability().getRoomLists().get(i).getOldPrice().intValue()));
                                if (p3 != 0) {
                                    if (p3 > 0) {
                                        isOff = true;
                                        off = p3 + getString(R.string.off);
                                    }
                                }
                            }
                            if ((hotelPrice >= x0) && (hotelPrice < x1)) {
                                xiff = 1;
                            }
                            if ((hotelPrice >= x1) && (hotelPrice < x2)) {
                                xiff = 2;
                            }
                            if ((hotelPrice >= x2) && (hotelPrice < x3)) {
                                xiff = 3;
                            }
                            if ((hotelPrice >= x3) && (hotelPrice < x4)) {
                                xiff = 4;
                            }
                            if ((hotelPrice >= x4) && (hotelPrice <= x5)) {
                                xiff = 5;
                            }*/

                            // Log.e("keeey", responseDomesticTrainAPI.getHotelAvailResult().getResultUniqID() );
                        }
                       /* filterHotelStarsModels.add(new FilterStarModel(getString(R.string._1star), false, 1));
                        filterHotelStarsModels.add(new FilterStarModel(getString(R.string._2star), false, 2));
                        filterHotelStarsModels.add(new FilterStarModel(getString(R.string._3star), false, 3));
                        filterHotelStarsModels.add(new FilterStarModel(getString(R.string._4star), false, 4));
                        filterHotelStarsModels.add(new FilterStarModel(getString(R.string._5star), false, 5));
                        filterHotelStarsModels.add(new FilterStarModel(getString(R.string.WithoutStar), false, -1));
                        filterHotelBestOffModels.add(new FilterHotelTypeModel(getString(R.string.BestSell), false));
                        filterHotelBestOffModels.add(new FilterHotelTypeModel(getString(R.string.BestOff), false));*/
                       /* for (Facility facilities : responseDomesticTrainAPI.getFacilities()) {
                            filterHotelFacilitiesModels.add(new FilterHotelTypeModel(facilities.getTitle(), false));
                        }
                        for (com.eligasht.service.model.newModel.hotel.search.response.HotelType hotelTypes : responseDomesticTrainAPI.getHotelTypes()) {
                            filterHotelTypeModel.add(new FilterHotelTypeModel(hotelTypes.getTitle(), false));
                        }
                        for (com.eligasht.service.model.newModel.hotel.search.response.Location locations : responseDomesticTrainAPI.getLocations()) {
                            filterHotelLocationModels.add(new FilterHotelTypeModel(locations.getTitle(), false));
                        }*/
                      //  tvTitle.setText(Prefs.getString("Value_Train_City", "استانبول")+"");
                        //Google ANALYTIC
                      //  SingletonAnalysis.getInstance().logTransfer(ServiceType.Train,"",Prefs.getString("Value_Train_City", "استانبول"));



                       // tvCount.setText("(" + selectTrainModelArrayList.size() + getString(R.string._not_found) + ")");
                       Collections.sort(selectTrainModelArrayListTrue, new Comparator<SelectTrainModel>() {
                            @Override
                            public int compare(SelectTrainModel p1, SelectTrainModel p2) {
                                return p1.getTotalFare() - p2.getTotalFare(); // Ascending
                            }
                        });
                        Collections.sort(selectTrainModelArrayListFalse, new Comparator<SelectTrainModel>() {
                            @Override
                            public int compare(SelectTrainModel p1, SelectTrainModel p2) {
                                return p1.getTotalFare() - p2.getTotalFare(); // Ascending
                            }
                        });
                    }
                    trainResultAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                    lnrHeaderMsg.setVisibility(View.GONE);
                    rvTrainResult.setVisibility(View.GONE);
                    rlList.setVisibility(View.GONE);
                    elNotFound.setVisibility(View.VISIBLE);
                    if (!Utility.isNetworkAvailable(SearchTrainActivity.this)) {
                        tvAlert.setText(R.string.InternetError);
                        tvAlertDesc.setVisibility(View.GONE);

                    } else {
                        tvAlert.setText(R.string.NoResult);
                        tvAlertDesc.setVisibility(View.VISIBLE);
                        tvAlertDesc.setText(getString(R.string.change_date));


                    }

                    rvTrainResult.setVisibility(View.GONE);
                    btnOk.setVisibility(View.VISIBLE);
                    rlEr.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(String message) {
                new InitUi().Loading(SearchTrainActivity.this, rlLoading, rlRoot, false, R.drawable.train_loading);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(SearchTrainActivity.this, R.color.status_loading));
                }
                lnrHeaderMsg.setVisibility(View.GONE);
                rvTrainResult.setVisibility(View.GONE);
                rlList.setVisibility(View.GONE);
                elNotFound.setVisibility(View.VISIBLE);
                tvAlertDesc.setVisibility(View.GONE);
                btnOk.setVisibility(View.VISIBLE);
                rlEr.setVisibility(View.VISIBLE);
                if (!Utility.isNetworkAvailable(SearchTrainActivity.this)) {
                    tvAlert.setText(R.string.InternetError);
                } else {
                    tvAlert.setText(R.string.ErrorServer);
                }
            }
        }, requestHotelPreSearch);
    }


    public static void updateSegmentTop(SelectTrainModel selectTrainModel, Context context, Activity activity) {
        // TODO Auto-generated method stub


        if (FLAG_SELECT_TRAIN ) {
            showSegmentRaft(selectTrainModel,context,activity);
            txtHeaderMsg.setText("لطفا بلیط برگشت را انتخاب کنید");
            txtHeaderMsg.setVisibility(View.VISIBLE);
            lnrHeaderMsg.setVisibility(View.VISIBLE);
            btnReserv.setVisibility(View.GONE);
            selectRaftInclud.setVisibility(View.VISIBLE);
            llFilter.setVisibility(View.VISIBLE);
        }else{
            showSegmentBargasht(selectTrainModel,context,activity);
            txtHeaderMsg.setVisibility(View.GONE);
            lnrHeaderMsg.setVisibility(View.GONE);
            rlList.setVisibility(View.INVISIBLE);
            btnReserv.setVisibility(View.VISIBLE);
            selectBargashtInclud.setVisibility(View.VISIBLE);
            llFilter.setVisibility(View.GONE);
        }


    }

    private static void showSegmentRaft(SelectTrainModel selectTrainModel, Context context, Activity activity) {
       // String imageUri = "https://cdn.elicdn.com" +"/Content/Images/Train/TrainLine/"+ selectTrainModel.getTrainlineNameEn()+".png";//imgTrainlineNameEn
        String imageUri=createImgURL(selectTrainModel);
        GlideApp
                .with(context)
                .load(imageUri)
                .centerCrop()
                .error(R.drawable.not_found)
                .into( imgTrainlineNameEn);
         avi.setVisibility(View.GONE);

         lblTrainNumber.setText( selectTrainModel.getTrainNumber()+" شماره قطار ");
         txtTotalFare.setText(NumberFormat.getInstance().format( selectTrainModel.getTotalFare())+"");
         txtDestinationText.setText( selectTrainModel.getDestinationText());
         txtSourceText.setText( selectTrainModel.getSourceText());
         txtTrainArrivalTime.setText( selectTrainModel.getTrainArrivalTime());
         txtTrainTime.setText( selectTrainModel.getTrainTime());
        seatRemainingRaft(selectTrainModel.getSeatAvailable());
        // txtSeatsRemaining.setText(" ظرفیت "+ selectTrainModel.getSeatAvailable()+" نفر ");
         txtCompartmentCapacity.setText("کوپه ی"+ selectTrainModel.getCompartmentCapacity()+"نفره");
         txtSaloonName.setText( selectTrainModel.getSaloonName()+"");
         txtNameTrain.setText(" قطار "+ selectTrainModel.getTrainlineNameFa()+"");
        // txtHotel.setText( selectTrainModel.getSaloonName()+"");
        if( selectTrainModel.getHotelIsIncluded())
             txtHotel.setVisibility(View.VISIBLE);
        else
             txtHotel.setVisibility(View.INVISIBLE);

        if( selectTrainModel.getHasMedia())
             lblHasMedia.setVisibility(View.VISIBLE);
        else
             lblHasMedia.setVisibility(View.GONE);

        if( selectTrainModel.getHasAirConditioning())
             lblAirConditioning.setVisibility(View.VISIBLE);
        else
             lblAirConditioning.setVisibility(View.GONE);

        txt_select.setBackgroundResource(R.drawable.background_strock_blue);
        txt_select.setText("تغییر بلیط رفت");
        createListDepartureFalse(selectTrainModel.getTrainlineCode());
        trainResultAdapter = new TrainResultAdapter(selectTrainModelArrayListFalseNew,activity,tvDate,isGrid);
        rvTrainResult.setAdapter(trainResultAdapter);
        trainResultAdapter.notifyDataSetChanged();
        Prefs.putString("Segmengt_Id_True", selectTrainModel.getID());

    }



    private static void showSegmentBargasht(SelectTrainModel selectTrainModel, Context context, Activity activity) {
        String imageUri=createImgURL(selectTrainModel);
        // String imageUri = "https://cdn.elicdn.com" +"/Content/Images/Train/TrainLine/"+ selectTrainModel.getTrainlineNameEn()+".png";//imgTrainlineNameEn

        GlideApp
                .with(context)
                .load(imageUri)
                .centerCrop()
                .error(R.drawable.not_found)
                .into( imgTrainlineNameEnB);
        aviB.setVisibility(View.GONE);

        lblTrainNumberB.setText( selectTrainModel.getTrainNumber()+" شماره قطار ");
        txtTotalFareB.setText(NumberFormat.getInstance().format( selectTrainModel.getTotalFare())+"");
        txtDestinationTextB.setText( selectTrainModel.getDestinationText());
        txtSourceTextB.setText( selectTrainModel.getSourceText());
        txtTrainArrivalTimeB.setText( selectTrainModel.getTrainArrivalTime());
        txtTrainTimeB.setText( selectTrainModel.getTrainTime());
       // txtSeatsRemainingB.setText(" ظرفیت "+ selectTrainModel.getSeatAvailable()+" نفر ");
        seatRemainingBargasht(selectTrainModel.getSeatAvailable());
        txtCompartmentCapacityB.setText("کوپه ی"+ selectTrainModel.getCompartmentCapacity()+"نفره");
        txtSaloonNameB.setText( selectTrainModel.getSaloonName()+"");
        txtNameTrainB.setText(" قطار "+ selectTrainModel.getTrainlineNameFa()+"");
        // txtHotel.setText( selectTrainModel.getSaloonName()+"");
        if( selectTrainModel.getHotelIsIncluded())
            txtHotelB.setVisibility(View.VISIBLE);
        else
            txtHotelB.setVisibility(View.INVISIBLE);

        if( selectTrainModel.getHasMedia())
            lblHasMediaB.setVisibility(View.VISIBLE);
        else
            lblHasMediaB.setVisibility(View.GONE);

        if( selectTrainModel.getHasAirConditioning())
            lblAirConditioningB.setVisibility(View.VISIBLE);
        else
            lblAirConditioningB.setVisibility(View.GONE);
        Prefs.putString("Segmengt_Id_False", selectTrainModel.getID());
    }

    private static String seatRemainingRaft(Integer count) {
        String attributeStr ="";
        if (count > 20 ){
            attributeStr = "+20";
            txtSeatsRemaining.setText(" ظرفیت "+attributeStr+" بلیط ");
            txtSeatsRemaining.setTextColor(Color.parseColor("#0EBB79"));//green

            return attributeStr;

        } else if (count > 5) {
            attributeStr = count.toString();
           txtSeatsRemaining.setText(" ظرفیت "+attributeStr+" بلیط ");
           txtSeatsRemaining.setTextColor(Color.parseColor("#1D5394"));//blue

            return attributeStr;

        } else if (count > 0 ){
            attributeStr = count.toString();
           txtSeatsRemaining.setText(" ظرفیت "+attributeStr+" بلیط ");
           txtSeatsRemaining.setTextColor(Color.parseColor("#F7941D"));//yellow

            return attributeStr;

        } else {
            attributeStr = " تکمیل";
           txtSeatsRemaining.setText(" ظرفیت "+attributeStr+" ");
           txtSeatsRemaining.setTextColor(Color.parseColor("#FF6D7B"));//red

            return attributeStr;
        }

    }
    private static String seatRemainingBargasht(Integer count) {
        String attributeStr ="";
        if (count > 20 ){
            attributeStr = "+20";
            txtSeatsRemainingB.setText(" ظرفیت "+attributeStr+" بلیط ");
            txtSeatsRemainingB.setTextColor(Color.parseColor("#0EBB79"));//green

            return attributeStr;

        } else if (count > 5) {
            attributeStr = count.toString();
            txtSeatsRemainingB.setText(" ظرفیت "+attributeStr+" بلیط ");
            txtSeatsRemainingB.setTextColor(Color.parseColor("#1D5394"));//blue

            return attributeStr;

        } else if (count > 0 ){
            attributeStr = count.toString();
            txtSeatsRemainingB.setText(" ظرفیت "+attributeStr+" بلیط ");
            txtSeatsRemainingB.setTextColor(Color.parseColor("#F7941D"));//yellow

            return attributeStr;

        } else {
            attributeStr = " تکمیل";
            txtSeatsRemainingB.setText(" ظرفیت "+attributeStr+" ");
            txtSeatsRemainingB.setTextColor(Color.parseColor("#FF6D7B"));//red

            return attributeStr;
        }

    }
    private static void createListDepartureFalse(Integer trainlineCodeListTrue) {
        selectTrainModelArrayListFalseNew.clear();
        for (int j = 0; j < selectTrainModelArrayListFalse.size(); j++) {
            if (selectTrainModelArrayListFalse.get(j).getTrainlineCode()== trainlineCodeListTrue){
                selectTrainModelArrayListFalseNew.add(new SelectTrainModel(selectTrainModelArrayListFalse.get(j).getPrivateID(),
                        selectTrainModelArrayListFalse.get(j).getID(),
                        selectTrainModelArrayListFalse.get(j).getTrainlineID(),
                        selectTrainModelArrayListFalse.get(j).getTrainlineCode(),///ino ckeck kon
                        selectTrainModelArrayListFalse.get(j).getTrainlineNameEn(),
                        selectTrainModelArrayListFalse.get(j).getTrainlineNameFa(),
                        selectTrainModelArrayListFalse.get(j).getSourceText(),
                        selectTrainModelArrayListFalse.get(j).getDestinationText(),
                        selectTrainModelArrayListFalse.get(j).getTrainTime(),
                        selectTrainModelArrayListFalse.get(j).getTrainArrivalTime(),
                        selectTrainModelArrayListFalse.get(j).getTrainNumber(),
                        selectTrainModelArrayListFalse.get(j).getTotalFare(),
                        selectTrainModelArrayListFalse.get(j).getFacilities(),
                        selectTrainModelArrayListFalse.get(j).getHasAirConditioning(),
                        selectTrainModelArrayListFalse.get(j).getHasMedia(),
                        selectTrainModelArrayListFalse.get(j).getSeatsRemaining(),
                        selectTrainModelArrayListFalse.get(j).getSeatAvailable(),
                        selectTrainModelArrayListFalse.get(j).getCompartmentCapacity(),
                        selectTrainModelArrayListFalse.get(j).getSaloonName(),
                        selectTrainModelArrayListFalse.get(j).getHotelIsIncluded(),
                        selectTrainModelArrayListFalse.get(j).getDepartureSegment(),
                        "انتخاب بلیط برگشت",
                        selectTrainModelArrayListFalse.get(j).getTrainId()
                ));
            }
        }

    }

    private static String createImgURL(SelectTrainModel selectTrainModel) {
        String imageUri="";
        String LogoName= selectTrainModel.getTrainlineNameEn();
        if ( selectTrainModel.getTrainlineNameEn().equals("Raja"))
        {
            // LogoName= selectTrainModel.getTrainlineNameEn();
        } else if ( selectTrainModel.getTrainlineNameEn().equals("Fadak")&&  selectTrainModel.getSaloonName().contains("هتل"))
        {
            LogoName = LogoName + "-purple";
        }else if ( selectTrainModel.getTrainlineNameEn().equals("Fadak") &&  selectTrainModel.getSaloonName().contains("بيزينس"))
        {
            LogoName = LogoName + "-blue";
        }else if ( selectTrainModel.getTrainlineNameEn().equals("Fadak") &&  selectTrainModel.getSaloonName().contains("اکونومی"))
        {
            LogoName = LogoName + "-silver";
        }else if ( selectTrainModel.getTrainlineNameEn().equals("Fadak")){
            LogoName = LogoName + "-gold";
        }

        return   "https://cdn.elicdn.com" +"/Content/Images/Train/TrainLine/"+LogoName +".png";//imgTrainlineNameEn
        //Log.d("imageUri:", "imageUri: "+imageUri);
    }




    private String createTrip(String raft, String bargasht, int flagOneTwo) {
        String tripRaft="";
        String tripBargasht="";
        String trip="";
        tripRaft=Prefs.getString("Value_Mabda_Key_Train", "")+"-"+Prefs.getString("Value_Maghsad_Key_Train", "");
        tripBargasht=Prefs.getString("Value_Maghsad_Key_Train", "")+"-"+Prefs.getString("Value_Mabda_Key_Train", "");
        tripRaft=tripRaft+"-"+raft;
        tripBargasht=tripBargasht+"-"+bargasht;
        if(flagOneTwo==1){
            trip=tripRaft;
        }if(flagOneTwo==2){
            trip=tripRaft+"|"+tripBargasht;
        }
        return trip;
    }

    private void hotelSearchRequest(ResponseHotelPreSearch responseHotelPreSearch) {
        Log.e("responseHotelPreSearch: ", new Gson().toJson(responseHotelPreSearch));
        selectTrainModelArrayListTrue.clear();
        selectTrainModelArrayListFalse.clear();
        selectTrainModelArrayListFilter.clear();
        filterModels.clear();
        filterHotelTypeModel.clear();
        filterHotelFacilitiesModels.clear();
        filterHotelLocationModels.clear();
        filterHotelPriceModels.clear();
        filterHotelBestOffModels.clear();
        filterHotelStarsModels.clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SearchTrainActivity.this, R.color.status_loading));
        }
        new InitUi().Loading(SearchTrainActivity.this, rlLoading, rlRoot, true, R.drawable.train_loading);
        RequestHotelSearch hotelAvailReq = new RequestHotelSearch();
        UserAgentObject request = new UserAgentObject();

        Prefs.putString("PreSearchUniqueId", responseHotelPreSearch.getResultText());
        hotelAvailReq.setPreSearchUniqueId(responseHotelPreSearch.getResultText());//583a1157-3624-44c7-9728-e7254dcd88c8
        hotelAvailReq.setCultureName(getString(R.string.culture));//583a1157-3624-44c7-9728-e7254dcd88c8

        Gson gson = new Gson();
        Log.e("RequestHotelSearch:", gson.toJson(hotelAvailReq));
        SingletonService.getInstance().getHotelService().newHotelSearchAvail(this, hotelAvailReq);
    }

    public void weather_request(){
        SingletonService.getInstance().getWeatherPart().getWeatherByCity(new OnServiceStatus<WeatherApi>() {
            @Override
            public void onReady(WeatherApi weatherApi) {


                try{
                    rvWeather.setAdapter(new WeatherAdapter(weatherApi.getQuery().getResults().getChannel().getItem().getForecast()));

                }catch (Exception e){
                    slidingDrawer.setVisibility(View.GONE);

                }

            }

            @Override
            public void onError(String message) {
                slidingDrawer.setVisibility(View.GONE);

            }

        }, Prefs.getString("Value-Hotel-City-En", "IST"));
    }

    @Override
    public void onReady(ResponseHotelSearch hotelAvailRes) {
        Gson gson = new Gson();
        Log.e("ResponseHotelSearch:", gson.toJson(hotelAvailRes));


    }

    @Override
    public void onError(String message) {



    }
}

