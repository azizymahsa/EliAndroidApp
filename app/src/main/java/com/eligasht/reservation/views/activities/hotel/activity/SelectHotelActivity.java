package com.eligasht.reservation.views.activities.hotel.activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DividerItemDecoration;
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
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.lost.hotel.HotelPreFactorAdapter;
import com.eligasht.reservation.models.HotelPreFactorModel;
import com.eligasht.reservation.models.hotel.FilterPriceModel;
import com.eligasht.reservation.models.hotel.adapter.FilterModel;
import com.eligasht.reservation.models.hotel.adapter.FilterStarModel;
import com.eligasht.reservation.models.hotel.adapter.SelectHotelModel;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.adapters.hotel.LazyResoultHotelAdapter;
import com.eligasht.reservation.views.adapters.weather.WeatherAdapter;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.PassengerActivity;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelDialog;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelTypeModel;
import com.eligasht.reservation.views.ui.dialog.hotel.SortDialog;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.hotel.hotelAvail.request.HotelAvailReq;
import com.eligasht.service.model.identity.Identity;
import com.eligasht.service.model.hotel.hotelAvail.request.Request;
import com.eligasht.service.model.hotel.hotelAvail.request.Room;
import com.eligasht.service.model.hotel.hotelAvail.response.Facility;
import com.eligasht.service.model.hotel.hotelAvail.response.Hotel;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelAvailRes;
import com.eligasht.service.model.hotel.hotelAvail.response.HotelType;
import com.eligasht.service.model.hotel.hotelAvail.response.Location;
import com.eligasht.service.model.weather.response.WeatherApi;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.gson.Gson;
import com.eligasht.reservation.tools.Prefs;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mehdi.sakout.fancybuttons.FancyButton;
public class SelectHotelActivity extends BaseActivity implements FilterHotelDialog.FilterHotelDialogListenerArray, View.OnClickListener, SortDialog.SortHotelDialogListener,
        OnServiceStatus<HotelAvailRes> {
    private RelativeLayout rlLoading, rlRoot, rlList;
    private TextView tvAlert, tvTitle, tvDate, tvCount, tvFilterIcon, tvFilter, tvSortIcon, tvSort;
    private Window window;
    private RelativeLayout elNotFound, rlEr;
    private TextView tvLoading, tvAlertDesc,weatherCity;
    private int maxPrice, minPrice;
    private LinearLayout llFilter;
    private FancyButton btnOk, btnBack, btnHome;
    private FancyButton btnNextDays, btnLastDays;
    private String raft, bargasht;
    private String raftFa, bargashtFa, searchIn;
    private com.eligasht.reservation.tools.ListView list;
    private LazyResoultHotelAdapter adapter;
    private ArrayList<SelectHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayListFilter = new ArrayList<>();
    private ArrayList<FilterModel> filterModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelTypeModel = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelLocationModels = new ArrayList<>();
    private ArrayList<FilterPriceModel> filterHotelPriceModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelBestOffModels = new ArrayList<>();
    private ArrayList<FilterStarModel> filterHotelStarsModels = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    private FancyButton btnFilter, btnSort;
    private  RecyclerView rvWeather;
    SlidingDrawer slidingDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);
 /*       SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
        helper.setEdgeMode(true)
                .setParallaxMode(true)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(true)
                .init(this);*/
        window = getWindow();
        list = findViewById(R.id.lvHoteResult);
        rlList = findViewById(R.id.rlList);
        tvLoading = findViewById(R.id.tvLoading);
        btnFilter = findViewById(R.id.btnFilter);
        btnSort = findViewById(R.id.btnSort);
        tvAlert = findViewById(R.id.tvAlert);
        tvTitle = findViewById(R.id.tvTitle);
        tvCount = findViewById(R.id.tvCount);
        btnBack = findViewById(R.id.btnBack);
        tvFilterIcon = findViewById(R.id.tvFilterIcon);
        btnHome = findViewById(R.id.btnHome);
        elNotFound = findViewById(R.id.elNotFound);
        tvSortIcon = findViewById(R.id.tvSortIcon);
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
        raftFa = SingletonDate.getInstance().getStartDate().getDescription();
        bargashtFa = SingletonDate.getInstance().getEndDate().getDescription();
        rooms.add(new Room(getIntent().getExtras().getInt("Adult"), getIntent().getExtras().getInt("Child")));

        raft = SingletonDate.getInstance().getStartDate().getFullGeo();
        bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();

        btnOk.setCustomTextFont(getResources().getString(R.string.iran_sans_normal_ttf));
        Utility.init_floating(list, this);
        btnFilter.setOnClickListener(this);
        btnSort.setOnClickListener(this);
        btnNextDays.setOnClickListener(this);
        btnLastDays.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        Utility.setAnimLoading(this);
        Utility.loadingText(tvLoading, Prefs.getString("H", ""));
        notiRecive();
        hotel_request();
        weather_request();
        tvDate.setText(raftFa + " - " + bargashtFa);

        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, this, this, tvDate);
        list.setAdapter(adapter);
        rvWeather.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                finish();
                break;
            case R.id.btnFilter:
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                FilterHotelDialog filterHotelDialog = FilterHotelDialog.newInstance(SelectHotelActivity.this, filterModels, SelectHotelActivity.this, filterHotelTypeModel,
                        filterHotelFacilitiesModels, filterHotelPriceModels, searchIn, filterHotelLocationModels, filterHotelBestOffModels, filterHotelStarsModels);
                filterHotelDialog.show(fm, "test");
                break;
            case R.id.btnSort:
                new SortDialog(SelectHotelActivity.this, this);
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
                    hotel_request();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.datePickerError,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnLastDays:
                if (SingletonDate.getInstance().getStartDate().minusOneDay()) {
                    tvDate.setText(SingletonDate.getInstance().getStartDate().getDescription() + " - " + SingletonDate.getInstance().getEndDate().getDescription());
                    raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                    bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
                    hotel_request();
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
        list.setVisibility(View.VISIBLE);
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
            adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this, tvDate);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            elNotFound.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
            rlList.setVisibility(View.GONE);
            btnOk.setVisibility(View.GONE);
            rlEr.setVisibility(View.GONE);
            tvAlert.setText(R.string.filter_no_found);
            tvAlertDesc.setText(R.string.change_filter);
        } else {
            if (selectHotelModelArrayListFilter.isEmpty()) {
                tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
                tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
                adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this, tvDate);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                elNotFound.setVisibility(View.VISIBLE);
                list.setVisibility(View.GONE);
                rlList.setVisibility(View.GONE);
                btnOk.setVisibility(View.GONE);
                rlEr.setVisibility(View.GONE);
                tvAlert.setText(R.string.filter_no_found);
                tvAlertDesc.setText(R.string.change_filter);
            } else {
                tvFilter.setTextColor(ContextCompat.getColor(this, R.color.red));
                tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.red));
                adapter = new LazyResoultHotelAdapter(selectHotelModelArrayListFilter, SelectHotelActivity.this, SelectHotelActivity.this, tvDate);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
        if (remove) {
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this, tvDate);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            searchIn = "";
        }
    }

    public ArrayList<SelectHotelModel> best_0ff(ArrayList<FilterHotelTypeModel> filterHotelBestOffModels) {
        boolean isFilter = false;
        ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectHotelModel> filter = new ArrayList<>();
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

    public ArrayList<SelectHotelModel> star(ArrayList<FilterStarModel> selectStarModels) {
        boolean isFilter = false;
        ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectHotelModel> filter = new ArrayList<>();
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

    public ArrayList<SelectHotelModel> type(ArrayList<FilterHotelTypeModel> filterHotelTypeModel) {
        boolean isFilter = false;
        ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectHotelModel> filter = new ArrayList<>();
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

    public ArrayList<SelectHotelModel> price(ArrayList<FilterPriceModel> filterPriceModels) {
        boolean isFilter = false;
        ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectHotelModel> filter = new ArrayList<>();
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

    public ArrayList<SelectHotelModel> location(ArrayList<FilterHotelTypeModel> filterHotelLocationModels) {
        boolean isFilter = false;
        ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectHotelModel> filter = new ArrayList<>();
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

    public ArrayList<SelectHotelModel> facility(ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels) {
        boolean isFilter = false;
        ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectHotelModel> filter = new ArrayList<>();
        for (int i = 0; i < filterHotelFacilitiesModels.size(); i++) {
            if (filterHotelFacilitiesModels.get(i).isCheck()) {
                for (int j = 0; j < selectHotelModels.size(); j++) {
                    for (int k = 0; k < selectHotelModels.get(j).getFacilities().size(); k++) {
                        isFilter = true;
                        if (filterHotelFacilitiesModels.get(i).getTitle().contains(selectHotelModels.get(j).getFacilities().get(k).getTitle())) {
                            filter.add(Add_To(k));
                        }
                    }
                }
            }
        }
        if (!isFilter) {
            return selectHotelModels;
        } else {
            ArrayList<SelectHotelModel> result = new ArrayList<SelectHotelModel>();
            Set<String> titles = new HashSet<>();
            for (SelectHotelModel item : filter) {
                if (titles.add(item.getName())) {
                    result.add(item);
                }
            }
            return result;
        }
    }

    public ArrayList<SelectHotelModel> searchText(String text) {
        boolean isFilter = false;
        ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModels = selectHotelModelArrayList;
        } else {
            selectHotelModels = selectHotelModelArrayListFilter;
        }
        ArrayList<SelectHotelModel> filter = new ArrayList<>();
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

    public SelectHotelModel Add_To(int i) {
        SelectHotelModel selectHotelModel;
        if (selectHotelModelArrayListFilter.isEmpty()) {
            selectHotelModel = new SelectHotelModel(selectHotelModelArrayList.get(i).getName(),
                    selectHotelModelArrayList.get(i).getCity(), selectHotelModelArrayList.get(i).getTitle(),
                    selectHotelModelArrayList.get(i).getBoard(), selectHotelModelArrayList.get(i).getPrice(),
                    selectHotelModelArrayList.get(i).getImageUrl(), selectHotelModelArrayList.get(i).getLocation(),
                    selectHotelModelArrayList.get(i).getOldPrice(), selectHotelModelArrayList.get(i).getStar(),
                    selectHotelModelArrayList.get(i).geteHotelId(), selectHotelModelArrayList.get(i).getResultUniqID(),
                    selectHotelModelArrayList.get(i).isBestSell(), selectHotelModelArrayList.get(i).isOff(),
                    selectHotelModelArrayList.get(i).getOff(), selectHotelModelArrayList.get(i).getTypeText(),
                    selectHotelModelArrayList.get(i).getFacilities(), selectHotelModelArrayList.get(i).getDiff(),
                    selectHotelModelArrayList.get(i).getOfferId(), selectHotelModelArrayList.get(i).getLocations());
        } else {
            selectHotelModel = new SelectHotelModel(selectHotelModelArrayListFilter.get(i).getName(),
                    selectHotelModelArrayListFilter.get(i).getCity(), selectHotelModelArrayListFilter.get(i).getTitle(),
                    selectHotelModelArrayListFilter.get(i).getBoard(), selectHotelModelArrayListFilter.get(i).getPrice(),
                    selectHotelModelArrayListFilter.get(i).getImageUrl(), selectHotelModelArrayListFilter.get(i).getLocation(),
                    selectHotelModelArrayListFilter.get(i).getOldPrice(), selectHotelModelArrayListFilter.get(i).getStar(),
                    selectHotelModelArrayListFilter.get(i).geteHotelId(), selectHotelModelArrayListFilter.get(i).getResultUniqID(),
                    selectHotelModelArrayListFilter.get(i).isBestSell(), selectHotelModelArrayListFilter.get(i).isOff(),
                    selectHotelModelArrayListFilter.get(i).getOff(), selectHotelModelArrayListFilter.get(i).getTypeText(),
                    selectHotelModelArrayListFilter.get(i).getFacilities(), selectHotelModelArrayListFilter.get(i).getDiff(),
                    selectHotelModelArrayListFilter.get(i).getOfferId(), selectHotelModelArrayListFilter.get(i).getLocations());
        }
        return selectHotelModel;
    }

    @Override
    public void onReturnValue(int type) {
        tvSort.setTextColor(ContextCompat.getColor(this, R.color.red));
        tvSortIcon.setTextColor(ContextCompat.getColor(this, R.color.red));
        switch (type) {
            case 1:
                Collections.sort(selectHotelModelArrayList, new Comparator<SelectHotelModel>() {
                    @Override
                    public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p2.getPrice()) - Integer.valueOf(p1.getPrice()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectHotelModel>() {
                    @Override
                    public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p2.getPrice()) - Integer.valueOf(p1.getPrice()); // Ascending
                    }
                });
                adapter.notifyDataSetChanged();
                break;
            case 2:
                Collections.sort(selectHotelModelArrayList, new Comparator<SelectHotelModel>() {
                    @Override
                    public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p1.getPrice()) - Integer.valueOf(p2.getPrice()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectHotelModel>() {
                    @Override
                    public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p1.getPrice()) - Integer.valueOf(p2.getPrice()); // Ascending
                    }
                });
                adapter.notifyDataSetChanged();
                break;
        }
    }

    public void hotel_request() {
        selectHotelModelArrayList.clear();
        selectHotelModelArrayListFilter.clear();
        filterModels.clear();
        filterHotelTypeModel.clear();
        filterHotelFacilitiesModels.clear();
        filterHotelLocationModels.clear();
        filterHotelPriceModels.clear();
        filterHotelBestOffModels.clear();
        filterHotelStarsModels.clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SelectHotelActivity.this, R.color.status_loading));
        }
        new InitUi().Loading(SelectHotelActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);
        HotelAvailReq hotelAvailReq = new HotelAvailReq();
        Request request = new Request();
        request.setCheckinString(Utility.convertNumbersToEnglish(raft));
        request.setCheckoutString(Utility.convertNumbersToEnglish(bargasht));
        request.setDepart(Prefs.getString("Value-Hotel-City-Code", "c25972"));
        request.setRoomsString(getIntent().getExtras().getString("Rooms"));
        Identity identity = new Identity();
        identity.setPassword("123qwe!@#QWE");
        identity.setTermianlId("Mobile");
        identity.setUserName("EligashtMlb");
        request.setIdentity(identity);
        request.setRooms(rooms);
        request.setSource("");
        request.setCulture(getString(R.string.culture));
        hotelAvailReq.setRequest(request);
        Gson gson = new Gson();
        Log.e("testt", gson.toJson(request));
        SingletonService.getInstance().getHotelService().hotelAvail(this, hotelAvailReq);
    }
    public void weather_request(){
        SingletonService.getInstance().getWeatherPart().getWeatherByCity(new OnServiceStatus<WeatherApi>() {
            @Override
            public void onReady(WeatherApi weatherApi) {
                rvWeather.setAdapter(new WeatherAdapter(weatherApi.getQuery().getResults().getChannel().getItem().getForecast()));

            }

            @Override
            public void onError(String message) {
            }
        }, Prefs.getString("Value-Hotel-City-En", "IST"));
    }

    @Override
    public void onReady(HotelAvailRes hotelAvailRes) {
        new InitUi().Loading(SelectHotelActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SelectHotelActivity.this, R.color.colorPrimaryDark));
        }
        selectHotelModelArrayList.clear();
        selectHotelModelArrayListFilter.clear();
        try {
            if (hotelAvailRes.getHotelAvailResult().getErrors() != null) {
                elNotFound.setVisibility(View.VISIBLE);
                tvAlert.setText(hotelAvailRes.getHotelAvailResult().getErrors().get(0).getDetailedMessage());
                list.setVisibility(View.GONE);
                rlList.setVisibility(View.GONE);
                llFilter.setVisibility(View.GONE);
            } else if (hotelAvailRes.getHotelAvailResult().getHotelSearchResult().getHotels().isEmpty()) {
                elNotFound.setVisibility(View.VISIBLE);
                tvAlert.setText(R.string.NoResult);
                tvAlertDesc.setText(getString(R.string.change_date));
                list.setVisibility(View.GONE);
                rlList.setVisibility(View.GONE);
                llFilter.setVisibility(View.GONE);
            } else {
                slidingDrawer.setVisibility(View.VISIBLE);
                maxPrice = hotelAvailRes.getHotelAvailResult().getHotelSearchResult().getMaxPrice();
                minPrice = hotelAvailRes.getHotelAvailResult().getHotelSearchResult().getMinPrice();
                int dif = maxPrice - minPrice;
                dif = dif / 5;
                int x0 = minPrice;
                int x1 = x0 + dif;
                int x2 = x1 + dif;
                int x3 = x2 + dif;
                int x4 = x3 + dif;
                int x5 = x4 + dif;
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x0)) + "-" + Utility.priceFormat(String.valueOf(x1)), 1, false));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x1)) + "-" + Utility.priceFormat(String.valueOf(x2)), 2, false));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x2)) + "-" + Utility.priceFormat(String.valueOf(x3)), 3, false));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x3)) + "-" + Utility.priceFormat(String.valueOf(x4)), 4, false));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x4)) + "-" + Utility.priceFormat(String.valueOf(x5)), 5, false));
                Collections.reverse(filterHotelPriceModels);
                int i = 0;
                for (Hotel hotels : hotelAvailRes.getHotelAvailResult().getHotelSearchResult().getHotels()) {
                    String off = "";
                    boolean isOff = false;
                    int xiff = 0;
                    int hotelPrice = hotels.getAvailability().getRoomLists().get(i).getPrice();
                    if ((hotels.getAvailability().getRoomLists().get(i).getOldPrice() > 0) &&
                            (hotels.getAvailability().getRoomLists().get(i).getOldPrice() > hotels.getAvailability().getRoomLists().get(i).getPrice())) {
                        int p1 = hotels.getAvailability().getRoomLists().get(i).getOldPrice() - hotels.getAvailability().getRoomLists().get(i).getPrice();
                        int p2 = p1 * 100;
                        int p3 = p2 / hotels.getAvailability().getRoomLists().get(i).getOldPrice();
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
                    }
                    selectHotelModelArrayList.add(new SelectHotelModel(hotels.getName(), hotels.getCity(), hotels.getAvailability().getRoomLists().get(i).getTitle(),
                            hotels.getAvailability().getRoomLists().get(i).getBoard(), hotels.getAvailability().getRoomLists().get(i).getPrice() + "",
                            hotels.getMainImage(), hotels.getLocation(),
                            hotels.getAvailability().getRoomLists().get(i).getOldPrice(), hotels.getStarRating(),
                            Integer.valueOf(hotels.getAvailability().getRoomLists().get(i).getEHotelId()),
                            hotelAvailRes.getHotelAvailResult().getResultUniqID(), hotels.isBestSell(), isOff,
                            off, hotels.getTypeText(), hotels.getFacilities(),
                            xiff, hotels.getAvailability().getRoomLists().get(i).getOfferId(),
                            hotelAvailRes.getHotelAvailResult().getHotelSearchResult().getLocations()));
                   // Log.e("keeey", hotelAvailRes.getHotelAvailResult().getResultUniqID() );
                }
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string._1star), false, 1));
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string._2star), false, 2));
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string._3star), false, 3));
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string._4star), false, 4));
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string._5star), false, 5));
                filterHotelStarsModels.add(new FilterStarModel(getString(R.string.WithoutStar), false, -1));
                filterHotelBestOffModels.add(new FilterHotelTypeModel(getString(R.string.BestSell), false));
                filterHotelBestOffModels.add(new FilterHotelTypeModel(getString(R.string.BestOff), false));
                for (Facility facilities : hotelAvailRes.getHotelAvailResult().getHotelSearchResult().getFacilities()) {
                    filterHotelFacilitiesModels.add(new FilterHotelTypeModel(facilities.getTitle(), false));
                }
                for (HotelType hotelTypes : hotelAvailRes.getHotelAvailResult().getHotelSearchResult().getHotelTypes()) {
                    filterHotelTypeModel.add(new FilterHotelTypeModel(hotelTypes.getTitle(), false));
                }
                for (Location locations : hotelAvailRes.getHotelAvailResult().getHotelSearchResult().getLocations()) {
                    filterHotelLocationModels.add(new FilterHotelTypeModel(locations.getTitle(), false));
                }
                tvTitle.setText(Prefs.getString("Value-Hotel-City-Fa", "استانبول"));
                weatherCity.setText("پیش بینی وضعیت آب و هوای "+Prefs.getString("Value-Hotel-City-Fa", "استانبول"));
                tvCount.setText("(" + selectHotelModelArrayList.size() + "مورد یافت شد" + ")");
                Collections.sort(selectHotelModelArrayList, new Comparator<SelectHotelModel>() {
                    @Override
                    public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p1.getPrice()) - Integer.valueOf(p2.getPrice()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectHotelModel>() {
                    @Override
                    public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p1.getPrice()) - Integer.valueOf(p2.getPrice()); // Ascending
                    }
                });
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
            list.setVisibility(View.GONE);
            rlList.setVisibility(View.GONE);
            elNotFound.setVisibility(View.VISIBLE);
            if (!Utility.isNetworkAvailable(SelectHotelActivity.this)) {
                tvAlert.setText(R.string.InternetError);
            } else {
                tvAlert.setText(R.string.ErrorServer);
            }
            tvAlertDesc.setVisibility(View.GONE);
            list.setVisibility(View.GONE);
            btnOk.setVisibility(View.VISIBLE);
            rlEr.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String message) {

        new InitUi().Loading(SelectHotelActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SelectHotelActivity.this, R.color.colorPrimaryDark));
        }
        list.setVisibility(View.GONE);
        rlList.setVisibility(View.GONE);
        elNotFound.setVisibility(View.VISIBLE);
        tvAlertDesc.setVisibility(View.GONE);
        btnOk.setVisibility(View.VISIBLE);
        rlEr.setVisibility(View.VISIBLE);
        if (!Utility.isNetworkAvailable(SelectHotelActivity.this)) {
            tvAlert.setText(R.string.InternetError);
        } else {
            tvAlert.setText(R.string.ErrorServer);
        }

    }
}




