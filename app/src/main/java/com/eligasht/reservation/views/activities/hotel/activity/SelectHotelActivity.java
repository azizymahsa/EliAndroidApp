package com.eligasht.reservation.views.activities.hotel.activity;
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
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.base.ServiceType;
import com.eligasht.reservation.base.SingletonAnalysis;
import com.eligasht.reservation.base.SingletonTimer;
import com.eligasht.reservation.models.hotel.FilterPriceModel;
import com.eligasht.reservation.models.hotel.adapter.FilterModel;
import com.eligasht.reservation.models.hotel.adapter.FilterStarModel;
import com.eligasht.reservation.models.hotel.adapter.SelectHotelModel;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.adapters.hotel.hotelresult.HotelResultAdapter;
import com.eligasht.reservation.views.adapters.weather.WeatherAdapter;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelDialog;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelTypeModel;
import com.eligasht.reservation.views.ui.dialog.hotel.SortDialog;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.helper.Const;
import com.eligasht.service.listener.OnServiceStatus;

import com.eligasht.service.model.hotel.hotelAvail.request.Room;

import com.eligasht.service.model.newModel.auth.response.ResponseAuth;
import com.eligasht.service.model.newModel.hotel.preSearch.request.QueryModel;
import com.eligasht.service.model.newModel.hotel.preSearch.request.RequestHotelPreSearch;
import com.eligasht.service.model.newModel.hotel.preSearch.response.ResponseHotelPreSearch;
import com.eligasht.service.model.newModel.hotel.search.request.RequestHotelSearch;
import com.eligasht.service.model.newModel.hotel.search.request.UserAgentObject;
import com.eligasht.service.model.newModel.hotel.search.response.Facility;
import com.eligasht.service.model.newModel.hotel.search.response.ResponseHotelSearch;
import com.eligasht.service.model.weather.response.WeatherApi;
import com.google.gson.Gson;
import com.eligasht.reservation.tools.Prefs;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectHotelActivity extends BaseActivity implements FilterHotelDialog.FilterHotelDialogListenerArray, View.OnClickListener, SortDialog.SortHotelDialogListener,
        OnServiceStatus<ResponseHotelSearch> {
    private RelativeLayout rlLoading, rlRoot, rlList;
    private TextView tvAlert, tvTitle, tvDate, tvCount, tvFilterIcon, tvFilter, tvSortIcon, tvSort;
    private Window window;
    private RelativeLayout elNotFound, rlEr;
    private TextView tvLoading, tvAlertDesc,weatherCity;
    private double  maxPrice, minPrice;
    private LinearLayout llFilter;
    private FancyButton btnOk, btnBack, btnHome;
    private FancyButton btnNextDays, btnLastDays;
    private String raft, bargasht;
    private String raftFa, bargashtFa, searchIn;
    public static String globalResultUniqID;
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
    private FancyButton btnFilter, btnSort,btnChangeView;
    private  RecyclerView rvWeather;
    SlidingDrawer slidingDrawer;
    RecyclerView rvHotelResult;
    HotelResultAdapter hotelResultAdapter;
    private ClientService service;
    boolean isGrid=false;
    private boolean flagDec=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        window = getWindow();
        service = ServiceGenerator.createService(ClientService.class);

        rvHotelResult = findViewById(R.id.rvHotelResult);
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
        raftFa = SingletonDate.getInstance().getStartDate().getDescription();
        bargashtFa = SingletonDate.getInstance().getEndDate().getDescription();
        rooms.add(new Room(getIntent().getExtras().getInt("Adult"), getIntent().getExtras().getInt("Child")));

        raft = SingletonDate.getInstance().getStartDate().getFullGeo();
        bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();

        btnOk.setCustomTextFont(getResources().getString(R.string.iran_sans_normal_ttf));
        // Utility.init_floating(list, this);
        btnFilter.setOnClickListener(this);
        btnSort.setOnClickListener(this);
        btnNextDays.setOnClickListener(this);
        btnLastDays.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnChangeView.setOnClickListener(this);
        Utility.setAnimLoading(this);
        Utility.loadingText(tvLoading, Prefs.getString("H", ""));
        notiRecive();
        // Auth_request();
        hotel_request();
        // weather_request();
        tvDate.setText(raftFa + " - " + bargashtFa);

      /*  adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, this, this, tvDate);
        list.setAdapter(adapter);*/
        rvWeather.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));


        rvHotelResult.setLayoutManager(new LinearLayoutManager(this));
        hotelResultAdapter = new HotelResultAdapter(selectHotelModelArrayList,this,tvDate,isGrid);
        rvHotelResult.setAdapter(hotelResultAdapter);

    }



    @Override
    public boolean needTerminate() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChangeView:
                if (isGrid){
                    isGrid=false;
                    Log.e("selectHotelModelArrayList: ",selectHotelModelArrayList.size()+"" );
                    rvHotelResult.setLayoutManager(new LinearLayoutManager(this));
                    hotelResultAdapter = new HotelResultAdapter(selectHotelModelArrayList,this,tvDate,isGrid);
                    rvHotelResult.setAdapter(hotelResultAdapter);
                    btnChangeView.setText(getString(R.string.icon_grid));

                }else{
                    isGrid=true;
                    Log.e("selectHotelModelArrayList: ",selectHotelModelArrayList.size()+"" );
                    rvHotelResult.setLayoutManager(new GridLayoutManager(this,3));
                    hotelResultAdapter = new HotelResultAdapter(selectHotelModelArrayList,this,tvDate,isGrid);
                    rvHotelResult.setAdapter(hotelResultAdapter);
                    btnChangeView.setText(getString(R.string.icon_list));

                }
                break;
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
               // new SortDialog(SelectHotelActivity.this, this);

                sortPrice();
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

    private void sortPrice() {
       // flagDec=true;

        if (flagDec == true) {
            tvSort.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            tvSortIcon.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            tvSortIcon.setText(getString(R.string.icon_sort_up));
            Collections.sort(selectHotelModelArrayList, new Comparator<SelectHotelModel>() {
                @Override
                public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                    return Integer.valueOf(p2.getPrice().intValue()) - Integer.valueOf(p1.getPrice().intValue()); // Ascending
                }
            });
            Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectHotelModel>() {
                @Override
                public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                    return Integer.valueOf(p2.getPrice().intValue()) - Integer.valueOf(p1.getPrice().intValue()); // Ascending
                }
            });
            hotelResultAdapter.notifyDataSetChanged();
            flagDec=false;
        } else if (flagDec == false) {
            tvSort.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            tvSortIcon.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            tvSortIcon.setText(getString(R.string.icon_sort_down));
            Collections.sort(selectHotelModelArrayList, new Comparator<SelectHotelModel>() {
                @Override
                public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                    return Integer.valueOf(p1.getPrice().intValue()) - Integer.valueOf(p2.getPrice().intValue()); // Ascending
                }
            });
            Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectHotelModel>() {
                @Override
                public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                    return Integer.valueOf(p1.getPrice().intValue()) - Integer.valueOf(p2.getPrice().intValue()); // Ascending
                }
            });
            hotelResultAdapter.notifyDataSetChanged();
            flagDec=true;
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
          /*  adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this, tvDate);
            list.setAdapter(adapter);*/
            hotelResultAdapter = new HotelResultAdapter(selectHotelModelArrayList,this,tvDate,isGrid);
            rvHotelResult.setAdapter(hotelResultAdapter);
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

                hotelResultAdapter = new HotelResultAdapter(selectHotelModelArrayList,this,tvDate,isGrid);
                rvHotelResult.setAdapter(hotelResultAdapter);
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

                hotelResultAdapter = new HotelResultAdapter(selectHotelModelArrayListFilter,this,tvDate,isGrid);
                rvHotelResult.setAdapter(hotelResultAdapter);
            }
        }
        if (remove) {
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
           /* adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this, tvDate);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();*/
            hotelResultAdapter = new HotelResultAdapter(selectHotelModelArrayList,this,tvDate,isGrid);
            rvHotelResult.setAdapter(hotelResultAdapter);
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
                    selectHotelModelArrayList.get(i).getOfferId(), selectHotelModelArrayList.get(i).getLocations(),
                    selectHotelModelArrayList.get(i).getCurrencyCode());
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
                    selectHotelModelArrayListFilter.get(i).getOfferId(), selectHotelModelArrayListFilter.get(i).getLocations(),
                    selectHotelModelArrayListFilter.get(i).getCurrencyCode());
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
                        return Integer.valueOf(p2.getPrice().intValue()) - Integer.valueOf(p1.getPrice().intValue()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectHotelModel>() {
                    @Override
                    public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p2.getPrice().intValue()) - Integer.valueOf(p1.getPrice().intValue()); // Ascending
                    }
                });
                hotelResultAdapter.notifyDataSetChanged();
                break;
            case 2:
                Collections.sort(selectHotelModelArrayList, new Comparator<SelectHotelModel>() {
                    @Override
                    public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p1.getPrice().intValue()) - Integer.valueOf(p2.getPrice().intValue()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectHotelModel>() {
                    @Override
                    public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p1.getPrice().intValue()) - Integer.valueOf(p2.getPrice().intValue()); // Ascending
                    }
                });
                hotelResultAdapter.notifyDataSetChanged();
                break;
        }
    }

    public void hotel_request() {
        hotelPreSearchReq();

    }

    private void hotelPreSearchReq() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SelectHotelActivity.this, R.color.flight_status));
        }
        new InitUi().Loading(SelectHotelActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);
        RequestHotelPreSearch requestHotelPreSearch = new RequestHotelPreSearch();
        QueryModel request = new QueryModel();
        request.setCheckIn(Utility.convertNumbersToEnglish(raft));
        request.setCheckOut(Utility.convertNumbersToEnglish(bargasht));
        request.setDestination(Prefs.getString("Value-Hotel-City-Code", "c25972"));
        request.setCategory("Hotel");
        // request.setRoomsString(getIntent().getExtras().getString("Rooms"));

        request.setRooms(getIntent().getExtras().getString("Rooms"));//rooms);


        requestHotelPreSearch.setQueryModel(request);
        Gson gson = new Gson();
        Log.e("RequestHotelPreSearch", gson.toJson(requestHotelPreSearch));
        SingletonService.getInstance().getHotelService().newHotelPreSearchAvail(new OnServiceStatus<ResponseHotelPreSearch>() {
            @Override
            public void onReady(ResponseHotelPreSearch responseHotelPreSearch) {
                Log.e("responseHotelPreSearch", gson.toJson(responseHotelPreSearch));
                hotelSearchRequest(responseHotelPreSearch);
            }

            @Override
            public void onError(String message) {

            }
        }, requestHotelPreSearch);
    }

    private void hotelSearchRequest(ResponseHotelPreSearch responseHotelPreSearch) {
        Log.e("responseHotelPreSearch: ", new Gson().toJson(responseHotelPreSearch));
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
            window.setStatusBarColor(ContextCompat.getColor(SelectHotelActivity.this, R.color.flight_status));
        }
        new InitUi().Loading(SelectHotelActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);
        RequestHotelSearch hotelAvailReq = new RequestHotelSearch();
        UserAgentObject request = new UserAgentObject();
        /*request.set(Utility.convertNumbersToEnglish(raft));
        request.setCheckoutString(Utility.convertNumbersToEnglish(bargasht));
        request.setDepart(Prefs.getString("Value-Hotel-City-Code", "c25972"));
        request.setRoomsString(getIntent().getExtras().getString("Rooms"));

        request.setRooms(rooms);
        request.setSource("");
        request.setCulture(getString(R.string.culture));*/
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

        new InitUi().Loading(SelectHotelActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SelectHotelActivity.this, R.color.flight_status));
        }
        selectHotelModelArrayList.clear();
        selectHotelModelArrayListFilter.clear();
        try {
            globalResultUniqID= hotelAvailRes.getSearchKey();
            if (hotelAvailRes.getErrors().size() > 0) {
                elNotFound.setVisibility(View.VISIBLE);
                tvAlert.setText(hotelAvailRes.getErrors().get(0).getMessage());
                rvHotelResult.setVisibility(View.GONE);
                rlList.setVisibility(View.GONE);
                llFilter.setVisibility(View.GONE);
            } else if (hotelAvailRes.getHotels().isEmpty()) {
                elNotFound.setVisibility(View.VISIBLE);
                tvAlert.setText(R.string.NoResult);
                tvAlertDesc.setText(getString(R.string.change_date));
                rvHotelResult.setVisibility(View.GONE);
                rlList.setVisibility(View.GONE);
                llFilter.setVisibility(View.GONE);
            } else {
                SingletonTimer.getInstance().start();
                Prefs.putBoolean("IsDemosticHotel",hotelAvailRes.getIsDomestic());
                if (rvWeather.getAdapter()!=null){
                    rvWeather.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
                    slidingDrawer.setVisibility(View.VISIBLE);

                }

                maxPrice = hotelAvailRes.getMaxPrice();
                minPrice = hotelAvailRes.getMinPrice();
                double  dif = maxPrice - minPrice;
                dif = dif / 5;
                double  x0 = minPrice;
                double  x1 = x0 + dif;
                double  x2 = x1 + dif;
                double  x3 = x2 + dif;
                double  x4 = x3 + dif;
                double  x5 = x4 + dif;
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x0)) + "-" + Utility.priceFormat(String.valueOf(x1)), 1, false, hotelAvailRes.getHotels().get(0).getAvailability().getRoomLists().get(0).getCurrencyCode()));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x1)) + "-" + Utility.priceFormat(String.valueOf(x2)), 2, false, hotelAvailRes.getHotels().get(0).getAvailability().getRoomLists().get(0).getCurrencyCode()));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x2)) + "-" + Utility.priceFormat(String.valueOf(x3)), 3, false, hotelAvailRes.getHotels().get(0).getAvailability().getRoomLists().get(0).getCurrencyCode()));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x3)) + "-" + Utility.priceFormat(String.valueOf(x4)), 4, false, hotelAvailRes.getHotels().get(0).getAvailability().getRoomLists().get(0).getCurrencyCode()));
                filterHotelPriceModels.add(new FilterPriceModel(Utility.priceFormat(String.valueOf(x4)) + "-" + Utility.priceFormat(String.valueOf(x5)), 5, false, hotelAvailRes.getHotels().get(0).getAvailability().getRoomLists().get(0).getCurrencyCode()));
                Collections.reverse(filterHotelPriceModels);
                int i = 0;
                for (com.eligasht.service.model.newModel.hotel.search.response.Hotel hotels : hotelAvailRes.getHotels()){
                    //for (int j = 0; j < hotelAvailRes.getHotels().size(); j++) {

                    String off = "";
                    boolean isOff = false;
                    int xiff = 0;
                    int hotelPrice = hotelAvailRes.getHotels().get(i).getAvailability().getRoomLists().get(i).getPrice().intValue();
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
                    }
                    selectHotelModelArrayList.add(new SelectHotelModel(hotels.getName(), hotels.getCity(), hotels.getAvailability().getRoomLists().get(i).getTitle(),
                            hotels.getAvailability().getRoomLists().get(i).getBoard(), hotels.getAvailability().getRoomLists().get(i).getPrice() ,
                            hotels.getMainImage(), hotels.getLocation(),
                            hotels.getAvailability().getRoomLists().get(i).getOldPrice(), hotels.getStarRating(),
                            Integer.valueOf(hotels.getAvailability().getRoomLists().get(i).getEHotelId()),
                            //hotelAvailRes.getHotels().get(i).getHotelID()+"",//.getResultUniqID(),
                            Prefs.getString("PreSearchUniqueId", "")+"",
                            hotels.getBestSell(), isOff,
                            off, hotels.getTypeText(), hotels.getFacilities(),
                            xiff, hotels.getAvailability().getRoomLists().get(i).getOfferId(),
                            hotelAvailRes.getLocations(),
                            hotels.getAvailability().getRoomLists().get(i).getCurrencyCode()));
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
                for (Facility facilities : hotelAvailRes.getFacilities()) {
                    filterHotelFacilitiesModels.add(new FilterHotelTypeModel(facilities.getTitle(), false));
                }
                for (com.eligasht.service.model.newModel.hotel.search.response.HotelType hotelTypes : hotelAvailRes.getHotelTypes()) {
                    filterHotelTypeModel.add(new FilterHotelTypeModel(hotelTypes.getTitle(), false));
                }
                for (com.eligasht.service.model.newModel.hotel.search.response.Location locations : hotelAvailRes.getLocations()) {
                    filterHotelLocationModels.add(new FilterHotelTypeModel(locations.getTitle(), false));
                }
                tvTitle.setText(Prefs.getString("Value-Hotel-City-Fa", "استانبول"));
                SingletonAnalysis.getInstance().logTransfer(ServiceType.HOTEL,"",Prefs.getString("Value-Hotel-City-Fa", "استانبول"));


                weatherCity.setText(getString(R.string.weather)+Prefs.getString("Value-Hotel-City-Fa", "استانبول"));
                tvCount.setText("(" + selectHotelModelArrayList.size() + getString(R.string._not_found) + ")");
                Collections.sort(selectHotelModelArrayList, new Comparator<SelectHotelModel>() {
                    @Override
                    public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p1.getPrice().intValue()) - Integer.valueOf(p2.getPrice().intValue()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectHotelModel>() {
                    @Override
                    public int compare(SelectHotelModel p1, SelectHotelModel p2) {
                        return Integer.valueOf(p1.getPrice().intValue()) - Integer.valueOf(p2.getPrice().intValue()); // Ascending
                    }
                });
            }
            hotelResultAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
            rvHotelResult.setVisibility(View.GONE);
            rlList.setVisibility(View.GONE);
            elNotFound.setVisibility(View.VISIBLE);
            if (!Utility.isNetworkAvailable(SelectHotelActivity.this)) {
                tvAlert.setText(R.string.InternetError);
                tvAlertDesc.setVisibility(View.GONE);

            } else {
                tvAlert.setText(R.string.NoResult);
                tvAlertDesc.setVisibility(View.VISIBLE);
                tvAlertDesc.setText(getString(R.string.change_date));


            }

            rvHotelResult.setVisibility(View.GONE);
            btnOk.setVisibility(View.VISIBLE);
            rlEr.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String message) {

        new InitUi().Loading(SelectHotelActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SelectHotelActivity.this, R.color.flight_status));
        }
        rvHotelResult.setVisibility(View.GONE);
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




