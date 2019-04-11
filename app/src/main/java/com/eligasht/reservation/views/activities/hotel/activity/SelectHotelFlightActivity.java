package com.eligasht.reservation.views.activities.hotel.activity;
import android.app.Activity;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.base.ServiceType;
import com.eligasht.reservation.base.SingletonAnalysis;
import com.eligasht.reservation.base.SingletonTimer;
import com.eligasht.reservation.models.hotel.FilterPriceModel;
import com.eligasht.reservation.models.hotel.adapter.FilterModel;
import com.eligasht.reservation.models.hotel.adapter.FilterStarModel;
import com.eligasht.reservation.models.hotel.adapter.SelectFlightHotelModel;
import com.eligasht.reservation.views.adapters.hotel.hotelresult.HotelFlightResultAdapter;
import com.eligasht.reservation.views.adapters.weather.WeatherAdapter;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.model.flight.request.DomesticFlight.RequestDomesticFlight;
import com.eligasht.service.model.flight.response.DomesticFlight.ResponseDomesticFlight;
import com.eligasht.service.model.hotelflight.search.request.Room;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelDialog;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelTypeModel;
import com.eligasht.reservation.views.ui.dialog.hotel.SortDialog;
import com.eligasht.service.listener.OnServiceStatus;

import com.eligasht.service.model.loadflight.request.LoadFlightRequest;
import com.eligasht.service.model.loadflight.request.LoadFlightSubRequest;
import com.eligasht.service.model.loadflight.response.LoadFlightResponse;
import com.eligasht.service.model.newModel.hotelFlight.preSearch.request.QueryModel;
import com.eligasht.service.model.newModel.hotelFlight.preSearch.request.RequestHotelFlight;
import com.eligasht.service.model.newModel.hotelFlight.preSearch.response.Facility;
import com.eligasht.service.model.newModel.hotelFlight.preSearch.response.HotelType;
import com.eligasht.service.model.newModel.hotelFlight.preSearch.response.Location;
import com.eligasht.service.model.newModel.hotelFlight.preSearch.response.ResponseHotelFlight;
import com.eligasht.service.model.weather.response.WeatherApi;

import com.google.gson.Gson;
import com.eligasht.reservation.tools.Prefs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import mehdi.sakout.fancybuttons.FancyButton;
public class SelectHotelFlightActivity extends BaseActivity implements View.OnClickListener, FilterHotelDialog.FilterHotelDialogListenerArray,
        SortDialog.SortHotelDialogListener, OnServiceStatus<ResponseHotelFlight> {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private LinearLayout llFilter;
    private String searchIn;
    private String flId, searchKey;
    //  private com.eligasht.reservation.tools.ListView list;
    // private FlightHotelAdapter adapter;
    private ArrayList<SelectFlightHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private ArrayList<SelectFlightHotelModel> selectHotelModelArrayListFilter = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelTypeModel = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels = new ArrayList<>();
    private ArrayList<FilterPriceModel> filterHotelPriceModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelLocationModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelBestOffModels = new ArrayList<>();
    private ArrayList<FilterStarModel> filterHotelStarsModels = new ArrayList<>();
    private ArrayList<FilterModel> filterModels = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    private RelativeLayout rlLoading, rlRoot;
    private TextView tvAlert, tvTitle, tvDate, tvCount, tvFilterIcon, tvFilter, tvSortIcon, tvSort, tvLoading, tvAlertDesc, weatherCity;
    private Window window;
    private RelativeLayout elNotFound, rlEr, rlList;
    private FancyButton btnNextDays, btnLastDays;
    private double  maxPrice, minPrice;
    private FancyButton btnFilter, btnSort;
    private FancyButton btnOk, btnBack, btnHome, btnChangeView;
    private String raft, bargasht;
    private String raftFa, bargashtFa;
    private ResponseHotelFlight hotelFlightResponse;
    SlidingDrawer slidingDrawer;
    private RecyclerView rvWeather;
    RecyclerView rvHotelResult;
    HotelFlightResultAdapter hotelFlightResultAdapter;
    boolean isGrid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hotel_flight);
       /* SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
        helper.setEdgeMode(false)
                .setParallaxMode(true)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(true)
                .init(this);*/
        Utility.setAnimLoading(this);
        window = getWindow();
        notiRecive();
        rvHotelResult = findViewById(R.id.rvHotelResult);
        //   list = findViewById(R.id.lvHoteResult);
        rlList = findViewById(R.id.rlList);
        btnFilter = findViewById(R.id.btnFilter);
        btnSort = findViewById(R.id.btnSort);
        tvAlert = findViewById(R.id.tvAlert);
        tvLoading = findViewById(R.id.tvLoading);
        tvTitle = findViewById(R.id.tvTitle);
        weatherCity = findViewById(R.id.weatherCity);
        tvCount = findViewById(R.id.tvCount);
        btnBack = findViewById(R.id.btnBack);
        btnHome = findViewById(R.id.btnHome);
        tvFilterIcon = findViewById(R.id.tvFilterIcon);
        btnHome = findViewById(R.id.btnHome);
        btnChangeView = findViewById(R.id.btnChangeView);
        elNotFound = findViewById(R.id.elNotFound);
        tvSortIcon = findViewById(R.id.tvSortIcon);
        tvSort = findViewById(R.id.tvSort);
        tvFilter = findViewById(R.id.tvFilter);
        btnOk = findViewById(R.id.btnOk);
        tvDate = findViewById(R.id.tvDate);
        llFilter = findViewById(R.id.llFilter);
        btnHome.setOnClickListener(this);
        btnNextDays = findViewById(R.id.btnNextDays);
        btnLastDays = findViewById(R.id.btnLastDays);
        rlEr = findViewById(R.id.rlEr);
        tvAlertDesc = findViewById(R.id.tvAlertDesc);
        slidingDrawer = findViewById(R.id.slidingDrawer);
        rvWeather = findViewById(R.id.rvWeather);
        btnNextDays.setOnClickListener(this);
        btnLastDays.setOnClickListener(this);
        btnFilter.setOnClickListener(this);
        btnSort.setOnClickListener(this);
        btnChangeView.setOnClickListener(this);
/*        adapter = new FlightHotelAdapter(selectHotelModelArrayList, this, tvDate);
        list.setAdapter(adapter);*/
        Utility.loadingText(tvLoading, Prefs.getString("FH", ""));
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnChangeView.setCustomTextFont("fonts/icomoon2.ttf");
        btnChangeView.setText(getString(R.string.icon_grid));
        btnBack.setOnClickListener(this);
        raftFa = SingletonDate.getInstance().getStartDate().getDescription();
        bargashtFa = SingletonDate.getInstance().getEndDate().getDescription();
        tvDate.setText(raftFa + " - " + bargashtFa);
        rooms.add(new Room(getIntent().getExtras().getInt("Adult"), getIntent().getExtras().getInt("Child")));
        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        raft = SingletonDate.getInstance().getStartDate().getFullGeo();
        bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        hotelFlightRequest();
        weather_request();
        //  Utility.init_floating(list, this);
        rvWeather.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rvHotelResult.setLayoutManager(new LinearLayoutManager(this));
        hotelFlightResultAdapter = new HotelFlightResultAdapter(selectHotelModelArrayList, this, tvDate, isGrid);
        rvHotelResult.setAdapter(hotelFlightResultAdapter);
    }

    public void weather_request() {
        SingletonService.getInstance().getWeatherPart().getWeatherByCity(new OnServiceStatus<WeatherApi>() {
            @Override
            public void onReady(WeatherApi weatherApi) {
                try {
                    rvWeather.setAdapter(new WeatherAdapter(weatherApi.getQuery().getResults().getChannel().getItem().getForecast()));
                } catch (Exception e) {
                    slidingDrawer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String message) {
                slidingDrawer.setVisibility(View.GONE);
            }
        }, Prefs.getString("Value-Hotel-City-Code-HF-Raft", "IST"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean needTerminate() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChangeView:
                if (isGrid) {
                    isGrid = false;
                    LinearLayoutManager myLayoutManager = (GridLayoutManager) rvHotelResult.getLayoutManager();
                    int scrollPosition = myLayoutManager.findFirstVisibleItemPosition();
                    rvHotelResult.setLayoutManager(new LinearLayoutManager(this));
                    hotelFlightResultAdapter = new HotelFlightResultAdapter(selectHotelModelArrayList, this, tvDate, isGrid);
                    rvHotelResult.setAdapter(hotelFlightResultAdapter);
                    btnChangeView.setText(getString(R.string.icon_grid));
                    rvHotelResult.scrollToPosition(scrollPosition);
                    // hotelResultAdapter.onAttachedToRecyclerView(rvHotelResult);
                    //  hotelResultAdapter.notify();
                } else {
                    isGrid = true;
                    LinearLayoutManager myLayoutManager = (LinearLayoutManager) rvHotelResult.getLayoutManager();
                    int scrollPosition = myLayoutManager.findFirstVisibleItemPosition();
                    rvHotelResult.setLayoutManager(new GridLayoutManager(this, 3));
                    hotelFlightResultAdapter = new HotelFlightResultAdapter(selectHotelModelArrayList, this, tvDate, isGrid);
                    rvHotelResult.setAdapter(hotelFlightResultAdapter);
                    btnChangeView.setText(getString(R.string.icon_list));
                    rvHotelResult.scrollToPosition(scrollPosition);
                    // hotelResultAdapter.onAttachedToRecyclerView(rvHotelResult);
//                     hotelResultAdapter.notify();
                }
                break;
            case R.id.btnFilter:
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                FilterHotelDialog filterHotelDialog = FilterHotelDialog.newInstance(SelectHotelFlightActivity.this, filterModels, SelectHotelFlightActivity.this, filterHotelTypeModel,
                        filterHotelFacilitiesModels, filterHotelPriceModels, searchIn, filterHotelLocationModels, filterHotelBestOffModels, filterHotelStarsModels);
                filterHotelDialog.show(fm, "test");
                break;
            case R.id.btnSort:
                new SortDialog(SelectHotelFlightActivity.this, this);
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
                    hotelFlightRequest();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.datePickerError,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnLastDays:
                if (SingletonDate.getInstance().getStartDate().minusOneDay()) {
                    tvDate.setText(SingletonDate.getInstance().getStartDate().getDescription() + " - " + SingletonDate.getInstance().getEndDate().getDescription());
                    raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                    bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
                    hotelFlightRequest();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.DatePickerError2,
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onReturnValue(int type) {
        tvSort.setTextColor(ContextCompat.getColor(this, R.color.red));
        tvSortIcon.setTextColor(ContextCompat.getColor(this, R.color.red));
        switch (type) {
            case 1:
                Collections.sort(selectHotelModelArrayList, new Comparator<SelectFlightHotelModel>() {
                    @Override
                    public int compare(SelectFlightHotelModel p1, SelectFlightHotelModel p2) {
                        return Integer.valueOf(p2.getPrice().intValue()) - Integer.valueOf(p1.getPrice().intValue()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectFlightHotelModel>() {
                    @Override
                    public int compare(SelectFlightHotelModel p1, SelectFlightHotelModel p2) {
                        return Integer.valueOf(p2.getPrice().intValue()) - Integer.valueOf(p1.getPrice().intValue()); // Ascending
                    }
                });
                hotelFlightResultAdapter.notifyDataSetChanged();
                break;
            case 2:
                Collections.sort(selectHotelModelArrayList, new Comparator<SelectFlightHotelModel>() {
                    @Override
                    public int compare(SelectFlightHotelModel p1, SelectFlightHotelModel p2) {
                        return Integer.valueOf(p1.getPrice().intValue()) - Integer.valueOf(p2.getPrice().intValue()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectFlightHotelModel>() {
                    @Override
                    public int compare(SelectFlightHotelModel p1, SelectFlightHotelModel p2) {
                        return Integer.valueOf(p1.getPrice().intValue()) - Integer.valueOf(p2.getPrice().intValue()); // Ascending
                    }
                });
                hotelFlightResultAdapter.notifyDataSetChanged();
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
        rvHotelResult.setVisibility(View.VISIBLE);
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
        selectHotelModelArrayListFilter = new ArrayList<>();
        selectHotelModelArrayListFilter = best_0ff(filterHotelBestOffModels);
        selectHotelModelArrayListFilter = star(filterHotelStarsModels);
        selectHotelModelArrayListFilter = type(filterHotelTypeModels);
        selectHotelModelArrayListFilter = location(filterHotelLocationModels);
        selectHotelModelArrayListFilter = facility(filterHotelFacilitiesModels);
        selectHotelModelArrayListFilter = price(filterHotelPriceModel);
        if (search != null) {
            selectHotelModelArrayListFilter = searchText(search);
        }
        if (selectHotelModelArrayListFilter.size() == selectHotelModelArrayList.size() && !remove) {
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            hotelFlightResultAdapter = new HotelFlightResultAdapter(selectHotelModelArrayList, this, tvDate, isGrid);
            rvHotelResult.setAdapter(hotelFlightResultAdapter);
            hotelFlightResultAdapter.notifyDataSetChanged();
            elNotFound.setVisibility(View.VISIBLE);
            rvHotelResult.setVisibility(View.GONE);
            rlList.setVisibility(View.GONE);
            btnOk.setVisibility(View.GONE);
            rlEr.setVisibility(View.GONE);
            tvAlert.setText(R.string.filter_no_found);
            tvAlertDesc.setText(R.string.change_filter);
        } else {
            if (selectHotelModelArrayListFilter.isEmpty()) {
                tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
                tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
             /* adapter = new FlightHotelAdapter(selectHotelModelArrayList, SelectHotelFlightActivity.this, tvDate);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();*/
                hotelFlightResultAdapter = new HotelFlightResultAdapter(selectHotelModelArrayList, this, tvDate, isGrid);
                rvHotelResult.setAdapter(hotelFlightResultAdapter);
                hotelFlightResultAdapter.notifyDataSetChanged();
                elNotFound.setVisibility(View.VISIBLE);
                rvHotelResult.setVisibility(View.GONE);
                rlList.setVisibility(View.GONE);
                btnOk.setVisibility(View.GONE);
                rlEr.setVisibility(View.GONE);
                tvAlert.setText(R.string.filter_no_found);
                tvAlertDesc.setText(R.string.change_filter);
            } else {
                tvFilter.setTextColor(ContextCompat.getColor(this, R.color.red));
                tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.red));
 /*             adapter = new FlightHotelAdapter(selectHotelModelArrayListFilter, SelectHotelFlightActivity.this, tvDate);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();*/
                hotelFlightResultAdapter = new HotelFlightResultAdapter(selectHotelModelArrayListFilter, this, tvDate, isGrid);
                rvHotelResult.setAdapter(hotelFlightResultAdapter);
                hotelFlightResultAdapter.notifyDataSetChanged();
            }
        }
        if (remove) {
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
       /*     adapter = new FlightHotelAdapter(selectHotelModelArrayList, SelectHotelFlightActivity.this, tvDate);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();*/
            hotelFlightResultAdapter = new HotelFlightResultAdapter(selectHotelModelArrayList, this, tvDate, isGrid);
            rvHotelResult.setAdapter(hotelFlightResultAdapter);
            hotelFlightResultAdapter.notifyDataSetChanged();
            searchIn = "";
        }
    }

    public ArrayList<SelectFlightHotelModel> best_0ff(ArrayList<FilterHotelTypeModel> filterHotelBestOffModels) {
        boolean isFilter = false;
        ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectFlightHotelModel> filter = new ArrayList<>();
        for (int i = 0; i < selectHotelModels.size(); i++) {
            if (filterHotelBestOffModels.get(0).isCheck() && filterHotelBestOffModels.get(1).isCheck()) {
                isFilter = true;
                if (selectHotelModels.get(i).isBestSell() || selectHotelModels.get(i).isOff()) {
                    filter.add(Add_To(i));
                }
            } else if (filterHotelBestOffModels.get(1).isCheck()) {
                isFilter = true;
                if (selectHotelModels.get(i).isOff()) {
                    filter.add(Add_To(i));
                }
            } else if (filterHotelBestOffModels.get(0).isCheck()) {
                isFilter = true;
                if (selectHotelModels.get(i).isBestSell()) {
                    filter.add(Add_To(i));
                }
            }
        }
        if (!isFilter) {
            return selectHotelModels;
        } else {
            return filter;
        }
    }

    public ArrayList<SelectFlightHotelModel> star(ArrayList<FilterStarModel> selectStarModels) {
        boolean isFilter = false;
        ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectFlightHotelModel> filter = new ArrayList<>();
        for (int j = 0; j < selectStarModels.size(); j++) {
            if (selectStarModels.get(j).isCheck()) {
                for (int i = 0; i < selectHotelModels.size(); i++) {
                    isFilter = true;
                    if (selectStarModels.get(j).getStar() == selectHotelModels.get(i).getStar()) {
                        filter.add(Add_To(i));
                    }
                }
            }
        }
        if (!isFilter) {
            return selectHotelModels;
        } else {
            return filter;
        }
    }

    public ArrayList<SelectFlightHotelModel> type(ArrayList<FilterHotelTypeModel> filterHotelTypeModel) {
        boolean isFilter = false;
        ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectFlightHotelModel> filter = new ArrayList<>();
        for (int j = 0; j < filterHotelTypeModel.size(); j++) {
            if (filterHotelTypeModel.get(j).isCheck()) {
                for (int i = 0; i < selectHotelModels.size(); i++) {
                    isFilter = true;
                    if (filterHotelTypeModel.get(j).getTitle().equals(selectHotelModels.get(i).getTypeText())) {
                        filter.add(Add_To(i));
                    }
                }
            }
        }
        if (!isFilter) {
            return selectHotelModels;
        } else {
            return filter;
        }
    }

    public ArrayList<SelectFlightHotelModel> price(ArrayList<FilterPriceModel> filterPriceModels) {
        boolean isFilter = false;
        ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectFlightHotelModel> filter = new ArrayList<>();
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
        }
    }

    public ArrayList<SelectFlightHotelModel> location(ArrayList<FilterHotelTypeModel> filterHotelLocationModels) {
        boolean isFilter = false;
        ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectFlightHotelModel> filter = new ArrayList<>();
        for (int j = 0; j < filterHotelLocationModels.size(); j++) {
            if (filterHotelLocationModels.get(j).isCheck()) {
                for (int i = 0; i < selectHotelModels.size(); i++) {
                    isFilter = true;
                    if (filterHotelLocationModels.get(j).getTitle().equals(selectHotelModels.get(i).getLocation())) {
                        filter.add(Add_To(i));
                    }
                }
            }
        }
        if (!isFilter) {
            return selectHotelModels;
        } else {
            return filter;
        }
    }

    public ArrayList<SelectFlightHotelModel> facility(ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels) {
        boolean isFilter = false;
        ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectFlightHotelModel> filter = new ArrayList<>();
        for (int i = 0; i < filterHotelFacilitiesModels.size(); i++) {
            if (filterHotelFacilitiesModels.get(i).isCheck()) {
                for (int j = 0; j < selectHotelModels.size(); j++) {
                    for (int k = 0; k < selectHotelModels.get(j).getFacilities().size(); k++) {
                        isFilter = true;
                        if (filterHotelFacilitiesModels.get(i).getTitle().contains(selectHotelModels.get(j).getFacilities().get(k).getTitle())) {
                            filter.add(Add_To(i));
                        }
                    }
                }
            }
        }
        if (!isFilter) {
            return selectHotelModels;
        } else {
            ArrayList<SelectFlightHotelModel> result = new ArrayList<SelectFlightHotelModel>();
            Set<String> titles = new HashSet<>();
            for (SelectFlightHotelModel item : filter) {
                if (titles.add(item.getName())) {
                    result.add(item);
                }
            }
            return result;
        }
    }

    public ArrayList<SelectFlightHotelModel> searchText(String text) {
        boolean isFilter = false;
        ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectFlightHotelModel> filter = new ArrayList<>();
        for (int i = 0; i < selectHotelModels.size(); i++) {
            isFilter = true;
            if (selectHotelModels.get(i).getName().toLowerCase().contains(text.toLowerCase())) {
                filter.add(Add_To(i));
            }
        }
        if (!isFilter) {
            return selectHotelModels;
        } else {
            return filter;
        }
    }

    public SelectFlightHotelModel Add_To(int j) {
        SelectFlightHotelModel selectHotelModel = null;
        try {
            if (selectHotelModelArrayListFilter.isEmpty()) {
                selectHotelModel = new SelectFlightHotelModel(selectHotelModelArrayList.get(j).getName(),
                        selectHotelModelArrayList.get(j).getCity(), selectHotelModelArrayList.get(j).getTitle(),
                        selectHotelModelArrayList.get(j).getBoard(), selectHotelModelArrayList.get(j).getPrice(),
                        selectHotelModelArrayList.get(j).getImageUrl(), selectHotelModelArrayList.get(j).getLocation(),
                        selectHotelModelArrayList.get(j).getOldPrice(), selectHotelModelArrayList.get(j).getStar(),
                        selectHotelModelArrayList.get(j).geteHotelId(), selectHotelModelArrayList.get(j).getResultUniqID(),
                        selectHotelModelArrayList.get(j).isBestSell(), selectHotelModelArrayList.get(j).isOff(),
                        selectHotelModelArrayList.get(j).getOff(), selectHotelModelArrayList.get(j).getTypeText(),
                        selectHotelModelArrayList.get(j).getFacilities(),
                        selectHotelModelArrayList.get(j).getDiff(), selectHotelModelArrayList.get(j).getFlights(),
                        selectHotelModelArrayList.get(j).getArrRout(),
                        selectHotelModelArrayList.get(j).getDepRout(), selectHotelModelArrayList.get(j).getAmount(),
                        selectHotelModelArrayList.get(j).getLocations(), selectHotelModelArrayList.get(j).getFlightId(),
                        selectHotelModelArrayList.get(j).getFlightList().getOfferId(),
                        selectHotelModelArrayList.get(j).getFlightList(),
                        selectHotelModelArrayList.get(j).getFlightList().getFlightGUID(),
                        selectHotelModelArrayList.get(j).getCurrencyCode());
            } else {
                selectHotelModel = new SelectFlightHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                        selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                        selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                        selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                        selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                        selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                        selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                        selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText(),
                        selectHotelModelArrayListFilter.get(j).getFacilities(),
                        selectHotelModelArrayListFilter.get(j).getDiff(), selectHotelModelArrayListFilter.get(j).getFlights(),
                        selectHotelModelArrayListFilter.get(j).getArrRout(), selectHotelModelArrayListFilter.get(j).getDepRout(),
                        selectHotelModelArrayListFilter.get(j).getAmount(), selectHotelModelArrayListFilter.get(j).getLocations(), selectHotelModelArrayList.get(j).getFlightId(),
                        selectHotelModelArrayList.get(j).getFlightList().getOfferId(),
                        selectHotelModelArrayList.get(j).getFlightList(),
                        selectHotelModelArrayList.get(j).getFlightList().getFlightGUID(),
                        selectHotelModelArrayList.get(j).getCurrencyCode()
                        );
            }
        } catch (Exception e) {
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
          /*  adapter = new FlightHotelAdapter(selectHotelModelArrayList, SelectHotelFlightActivity.this, tvDate);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();*/
            hotelFlightResultAdapter = new HotelFlightResultAdapter(selectHotelModelArrayList, this, tvDate, isGrid);
            rvHotelResult.setAdapter(hotelFlightResultAdapter);
            hotelFlightResultAdapter.notifyDataSetChanged();
            elNotFound.setVisibility(View.VISIBLE);
            rvHotelResult.setVisibility(View.GONE);
            rlList.setVisibility(View.GONE);
            btnOk.setVisibility(View.GONE);
            rlEr.setVisibility(View.GONE);
            tvAlert.setText(R.string.filter_no_found);
            tvAlertDesc.setText(R.string.change_filter);
        }
        return selectHotelModel;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 155) {
            if (resultCode == Activity.RESULT_OK) {
                flId = data.getStringExtra("FlightId");
                searchKey = data.getStringExtra("searchKey");
                loadFlightRequest();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public void onReady(ResponseHotelFlight hotelFlightResponse) {
        if(hotelFlightResponse != null)
        Log.e("ResponseHotelFlight:", new Gson().toJson(hotelFlightResponse ));

       this.hotelFlightResponse = hotelFlightResponse;


        new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this, R.color.colorPrimaryDark));
        }
        selectHotelModelArrayList.clear();
        selectHotelModelArrayListFilter.clear();
        try {
            if (hotelFlightResponse.getErrors().size() >0) {
                elNotFound.setVisibility(View.VISIBLE);
                tvAlert.setText(hotelFlightResponse.getErrors().get(0).getDetailedMessage());
                rvHotelResult.setVisibility(View.GONE);
                rlList.setVisibility(View.GONE);
                llFilter.setVisibility(View.GONE);
            } else if (hotelFlightResponse.getHotels().isEmpty()) {
                elNotFound.setVisibility(View.VISIBLE);
                tvAlert.setText(R.string.NoResult);
                tvAlertDesc.setText(getString(R.string.change_date));
                rvHotelResult.setVisibility(View.GONE);
                rlList.setVisibility(View.GONE);
                llFilter.setVisibility(View.GONE);
            } else {
                SingletonTimer.getInstance().start();
                if (rvWeather.getAdapter() != null) {
                    rvWeather.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    slidingDrawer.setVisibility(View.VISIBLE);
                }
                maxPrice = hotelFlightResponse.getMaxPrice();
                minPrice = hotelFlightResponse.getMinPrice();
                double  dif = maxPrice - minPrice;
                dif = dif / 5;
                double  x0 = minPrice;
                double  x1 = x0 + dif;
                double  x2 = x1 + dif;
                double  x3 = x2 + dif;
                double  x4 = x3 + dif;
                double  x5 = x4 + dif;
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x0)) + "-" + Utility.priceFormat(String.valueOf(x1)), 1, false,hotelFlightResponse.getHotels().get(0).getAvailability().getRoomLists().get(0).getCurrencyCode()));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x1)) + "-" + Utility.priceFormat(String.valueOf(x2)), 2, false,hotelFlightResponse.getHotels().get(0).getAvailability().getRoomLists().get(0).getCurrencyCode()));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x2)) + "-" + Utility.priceFormat(String.valueOf(x3)), 3, false,hotelFlightResponse.getHotels().get(0).getAvailability().getRoomLists().get(0).getCurrencyCode()));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x3)) + "-" + Utility.priceFormat(String.valueOf(x4)), 4, false,hotelFlightResponse.getHotels().get(0).getAvailability().getRoomLists().get(0).getCurrencyCode()));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x4)) + "-" + Utility.priceFormat(String.valueOf(x5)), 5, false,hotelFlightResponse.getHotels().get(0).getAvailability().getRoomLists().get(0).getCurrencyCode()));
                Collections.reverse(filterHotelPriceModels);
                int i = 0;
                int j = 0;
                for (com.eligasht.service.model.newModel.hotelFlight.preSearch.response.Hotel hotels : hotelFlightResponse.getHotels()) {
                    String off = "";
                    boolean isOff = false;
                    int xiff = 0;
                    double hotelPrice = hotels.getAvailability().getRoomLists().get(i).getPrice();
                    if ((hotels.getAvailability().getRoomLists().get(i).getOldPrice() > 0) &&
                            (hotels.getAvailability().getRoomLists().get(i).getOldPrice() > hotels.getAvailability().getRoomLists().get(i).getPrice())) {
                        double p1 = hotels.getAvailability().getRoomLists().get(i).getOldPrice() - hotels.getAvailability().getRoomLists().get(i).getPrice();
                        double p2 = p1 * 100;
                        double p3 = p2 / hotels.getAvailability().getRoomLists().get(i).getOldPrice();
                        if (p3 > 0) {
                            // negative
                            isOff = true;
                            off = p3 + getString(R.string.off);
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
                    }
                    selectHotelModelArrayList.add(new SelectFlightHotelModel(hotels.getName(), hotels.getCity(), hotels.getAvailability().getRoomLists().get(i).getTitle(),
                            hotels.getAvailability().getRoomLists().get(i).getBoard(), hotels.getAvailability().getRoomLists().get(i).getPrice() , hotels.getMainImage(), hotels.getLocation(),
                            hotels.getAvailability().getRoomLists().get(i).getOldPrice(), hotels.getStarRating(),
                            Integer.valueOf(hotels.getAvailability().getRoomLists().get(i).getEHotelId()),
                            hotelFlightResponse.getSearchKey()+""//getResultUniqID()
                            ,hotels.getBestSell(), isOff,
                            off, hotels.getTypeText(), hotels.getFacilities(),
                            xiff,
                            hotelFlightResponse.getFlights().getFltList(),
                            hotelFlightResponse.getFlights().getArrRout(),
                            hotelFlightResponse.getFlights().getDepRout(),
                            hotelFlightResponse.getFlights().getAmount() + "",
                            hotelFlightResponse.getLocations(),
                            hotelFlightResponse.getFlights().getFlightID()
                    ,hotelFlightResponse.getFlights().getOfferId(),//FlightOfferId
                            hotelFlightResponse.getFlights(),
                            hotelFlightResponse.getFlights().getFlightGUID(),
                            hotelFlightResponse.getHotels().get(i).getAvailability().getRoomLists().get(0).getCurrencyCode()));
                    hotelFlightResponse.getFlights().getFlightID();
                }
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string._1star), false, 1));
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string._2star), false, 2));
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string._3star), false, 3));
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string._4star), false, 4));
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string._5star), false, 5));
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string.WithoutStar), false, -1));
                filterHotelBestOffModels.add(new FilterHotelTypeModel(getString(R.string.BestSell), false));
                filterHotelBestOffModels.add(new FilterHotelTypeModel(getString(R.string.BestOff), false));
                for (Facility facilities : hotelFlightResponse.getFacilities()) {
                    filterHotelFacilitiesModels.add(new FilterHotelTypeModel(facilities.getTitle(), false));
                }
                for (HotelType hotelTypes : hotelFlightResponse.getHotelTypes()) {
                    filterHotelTypeModel.add(new FilterHotelTypeModel(hotelTypes.getTitle(), false));
                }
                for (Location locations : hotelFlightResponse.getLocations()) {
                    filterHotelLocationModels.add(new FilterHotelTypeModel(locations.getTitle(), false));
                }
                tvTitle.setText(Prefs.getString("Value-Hotel-City-Fa-HF-Raft", "استانبول"));
                SingletonAnalysis.getInstance().logTransfer(ServiceType.HOTELFLIGHT, Prefs.getString("Value-Hotel-City-Fa-HF-Raft", "استانبول"), "");
                weatherCity.setText("پیش بینی وضعیت آب و هوای " + Prefs.getString("Value-Hotel-City-Fa-HF-Raft", "استانبول"));
                tvCount.setText("(" + selectHotelModelArrayList.size() + "مورد یافت شد" + ")");
                Collections.sort(selectHotelModelArrayList, new Comparator<SelectFlightHotelModel>() {
                    @Override
                    public int compare(SelectFlightHotelModel p1, SelectFlightHotelModel p2) {
                        return Integer.valueOf(p1.getPrice().intValue()) - Integer.valueOf(p2.getPrice().intValue()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectFlightHotelModel>() {
                    @Override
                    public int compare(SelectFlightHotelModel p1, SelectFlightHotelModel p2) {
                        return Integer.valueOf(p1.getPrice().intValue()) - Integer.valueOf(p2.getPrice().intValue()); // Ascending
                    }
                });
                hotelFlightResultAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            elNotFound.setVisibility(View.VISIBLE);
            tvAlert.setText(R.string.NoResult);
            tvAlertDesc.setText(getString(R.string.change_date));
            rvHotelResult.setVisibility(View.GONE);
            rlList.setVisibility(View.GONE);
            llFilter.setVisibility(View.GONE);
        }
        requestCheckFlt();
    }

    @Override
    public void onError(String message) {
        onErrors();
    }

    public void hotelFlightRequest() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this, R.color.hf));
        }
        new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);
        selectHotelModelArrayList.clear();
        selectHotelModelArrayListFilter.clear();
        filterModels.clear();
        filterHotelTypeModel.clear();
        filterHotelFacilitiesModels.clear();
        filterHotelLocationModels.clear();
        filterHotelPriceModels.clear();
        filterHotelBestOffModels.clear();
        filterHotelStarsModels.clear();
        RequestHotelFlight hotelFlightRequest = new RequestHotelFlight();
        QueryModel request = new QueryModel();

       // request.setEchoToken("HF");
        request.setCategory("Tour");
        request.setIsSearchRequest(true);
        request.setCheckIn(Utility.convertNumbersToEnglish(raft));
        request.setCheckOut(Utility.convertNumbersToEnglish(bargasht));
        request.setFromDate(Utility.convertNumbersToEnglish(raft));
        request.setToDate(Utility.convertNumbersToEnglish(bargasht));
        request.setDestination(Prefs.getString("Value-Hotel-City-Code-HF-Raft", "IST"));
        //request.setRooms(rooms);
        request.setRooms(getIntent().getExtras().getString("Rooms"));
        request.setCurrentCulture(getString(R.string.culture));
        request.setSource(Prefs.getString("Value-Hotel-City-Code-HF-Source", "THR"));
        hotelFlightRequest.setQueryModel(request);
        Log.e( "RequestHotelFlight: ",new Gson().toJson(hotelFlightRequest ));
        SingletonService.getInstance().getHotelService().newHotelFlightSearchAvail(this, hotelFlightRequest);
    }

    public void loadFlightRequest() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this, R.color.hf));
        }
        new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);
        LoadFlightRequest loadFlightRequest = new LoadFlightRequest();
        LoadFlightSubRequest loadFlightSubRequest = new LoadFlightSubRequest();
        loadFlightSubRequest.setFlightId(flId);
        loadFlightSubRequest.setCulture(getString(R.string.culture));
        com.eligasht.service.model.loadflight.request.Identity identity = new com.eligasht.service.model.loadflight.request.Identity();
        identity.setPassword("123qwe!@#QWE");
        identity.setUserName("EligashtMlb");
        identity.setTermianlId("Mobile");
        loadFlightSubRequest.setIdentity(identity);
        loadFlightSubRequest.setSearchKey(searchKey);
        loadFlightRequest.setRequest(loadFlightSubRequest);
        Log.e("loadFlightRequest", new Gson().toJson(loadFlightRequest));
        SingletonService.getInstance().getHotelService().loadFlight(new OnServiceStatus<LoadFlightResponse>() {
            @Override
            public void onReady(LoadFlightResponse loadFlightResponse) {
                /*new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this, R.color.colorPrimaryDark));
                }
                try {

                    selectHotelModelArrayList.clear();
                    if (loadFlightResponse.getLoadFlightResult().getErrors() != null) {
                        elNotFound.setVisibility(View.VISIBLE);
                        tvAlert.setText(loadFlightResponse.getLoadFlightResult().getErrors().get(0).getDetailedMessage());
                        rvHotelResult.setVisibility(View.GONE);
                        llFilter.setVisibility(View.GONE);
                    } else if (loadFlightResponse.getLoadFlightResult().getHFlight().getFltList().isEmpty()) {
                        elNotFound.setVisibility(View.VISIBLE);
                        tvAlert.setText(R.string.NoResult);
                        rvHotelResult.setVisibility(View.GONE);
                        llFilter.setVisibility(View.GONE);
                    } else {
                        maxPrice = hotelFlightResponse.getMaxPrice();
                        minPrice = hotelFlightResponse.getMinPrice();
                        int dif = maxPrice - minPrice;
                        dif = dif / 5;
                        int x0 = minPrice;
                        int x1 = x0 + dif;
                        int x2 = x1 + dif;
                        int x3 = x2 + dif;
                        int x4 = x3 + dif;
                        int x5 = x4 + dif;
                        int i = 0;
                        int j = 0;
                        for (com.eligasht.service.model.newModel.hotelFlight.preSearch.response.Hotel hotels : hotelFlightResponse.getHotels()) {
                            String off = "";
                            boolean isOff = false;
                            int xiff = 0;
                            int hotelPrice = Integer.valueOf(hotels.getAvailability().getRoomLists().get(i).getPrice());
                            if ((hotels.getAvailability().getRoomLists().get(i).getOldPrice() > 0) &&
                                    (hotels.getAvailability().getRoomLists().get(i).getOldPrice() > Integer.valueOf(hotels.getAvailability().getRoomLists().get(i).getPrice()))) {
                                int p1 = hotels.getAvailability().getRoomLists().get(i).getOldPrice() - Integer.valueOf(hotels.getAvailability().getRoomLists().get(i).getPrice());
                                int p2 = p1 * 100;
                                int p3 = p2 / hotels.getAvailability().getRoomLists().get(i).getOldPrice();
                                if (p3 > 0) {
                                    // negative
                                    isOff = true;
                                    off = p3 + getString(R.string.off);
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
                            }
                            selectHotelModelArrayList.add(new SelectFlightHotelModel(hotels.getName(),
                                    hotels.getCity(),
                                    hotels.getAvailability().getRoomLists().get(i).getTitle(),
                                    hotels.getAvailability().getRoomLists().get(i).getBoard(),
                                    hotels.getAvailability().getRoomLists().get(i).getPrice() + "",
                                    hotels.getMainImage(), hotels.getLocation(),
                                    hotels.getAvailability().getRoomLists().get(i).getOldPrice(),
                                    hotels.getStarRating(),
                                    Integer.valueOf(hotels.getAvailability().getRoomLists().get(i).getEHotelId()),
                                    hotelFlightResponse.getSearchKey()//getResultUniqID()
                                    , hotels.getBestSell(), isOff,
                                    off, hotels.getTypeText(), hotels.getFacilities(),
                                    xiff, loadFlightResponse.getLoadFlightResult().getHFlight().getFltList(),
                                    loadFlightResponse.getLoadFlightResult().getHFlight().getArrRout(),
                                    loadFlightResponse.getLoadFlightResult().getHFlight().getDepRout(),
                                    loadFlightResponse.getLoadFlightResult().getHFlight().getAmount() + "",
                                    hotelFlightResponse.getLocations(),
                                    loadFlightResponse.getLoadFlightResult().getHFlight().getFlightID()));
                            //  i++;
                        }


                        hotelFlightResultAdapter = new HotelFlightResultAdapter(selectHotelModelArrayList, SelectHotelFlightActivity.this, tvDate, isGrid);
                        rvHotelResult.setAdapter(hotelFlightResultAdapter);
                        hotelFlightResultAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    onErrors();
                }*/
            }

            @Override
            public void onError(String message) {
                onErrors();
            }
        }, loadFlightRequest);
    }

    public void onErrors() {
        new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this, R.color.colorPrimaryDark));
        }
        llFilter.setVisibility(View.GONE);
        rvHotelResult.setVisibility(View.GONE);
        rlList.setVisibility(View.GONE);
        elNotFound.setVisibility(View.VISIBLE);
        if (!Utility.isNetworkAvailable(SelectHotelFlightActivity.this)) {
            tvAlert.setText(R.string.InternetError);
        } else {
            tvAlert.setText(R.string.ErrorServer);
        }
        btnOk.setVisibility(View.VISIBLE);
        rlEr.setVisibility(View.VISIBLE);
        tvAlertDesc.setVisibility(View.GONE);
    }

    public void requestCheckFlt() {
        RequestDomesticFlight requestDomesticFlight = new RequestDomesticFlight();
        com.eligasht.service.model.flight.request.DomesticFlight.Request request = new com.eligasht.service.model.flight.request.DomesticFlight.Request();
        request.setUserName("EligashtMlb");
        request.setPassword("123qwe!@#QWE");
        request.setTermianlId("Mobile");
        request.setCode(Prefs.getString("Value-Hotel-City-Code-HF-Raft", "IST"));////inja esme forudgah mikhore
        request.setToCode(Prefs.getString("Value-Hotel-City-Code-HF-Source", "THR"));
        requestDomesticFlight.setRequest(request);
        SingletonService.getInstance().getFlight().domesticFlightAvail(new OnServiceStatus<ResponseDomesticFlight>() {
            @Override
            public void onReady(ResponseDomesticFlight responseDomesticFlight) {
                try {
                    if (responseDomesticFlight.getGetIsDomesticResult().getErrors() != null) {
                        Toast.makeText(SelectHotelFlightActivity.this, responseDomesticFlight.getGetIsDomesticResult().getErrors().get(0).getDetailedMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Prefs.putBoolean("IsDemostic", responseDomesticFlight.getGetIsDomesticResult().getIsDomestic());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(SelectHotelFlightActivity.this, getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
            }
        }, requestDomesticFlight);
    }
}
