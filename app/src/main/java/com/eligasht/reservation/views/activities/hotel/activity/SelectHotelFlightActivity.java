package com.eligasht.reservation.views.activities.hotel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.api.hotel.changeflight.LoadHotelFlightApi;
import com.eligasht.reservation.api.hotel.hotelFlight.HotelFlightSearch;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.Country;
import com.eligasht.reservation.models.HotelAR;
import com.eligasht.reservation.models.RquestHF;
import com.eligasht.reservation.models.hotel.FilterPriceModel;
import com.eligasht.reservation.models.hotel.adapter.FilterModel;
import com.eligasht.reservation.models.hotel.adapter.FilterStarModel;
import com.eligasht.reservation.models.hotel.adapter.SelectFlightHotelModel;
import com.eligasht.reservation.models.hotel.adapter.SelectHotelModel;
import com.eligasht.reservation.models.hotel.api.changeflight.request.ChangeFlightApiRequest;
import com.eligasht.reservation.models.hotel.api.changeflight.request.Request;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Rooms;
import com.eligasht.reservation.models.hotel.api.hotelAvail.response.Facilities;
import com.eligasht.reservation.models.hotel.api.hotelAvail.response.HotelTypes;
import com.eligasht.reservation.models.hotel.api.hotelAvail.response.Hotels;
import com.eligasht.reservation.models.hotel.api.hotelAvail.response.Locations;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.tools.datetools.SolarCalendar;
import com.eligasht.reservation.views.adapters.hotel.FlightHotelAdapter;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelDialog;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelTypeModel;
import com.eligasht.reservation.views.ui.dialog.hotel.SortDialog;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.gson.Gson;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.pixplicity.easyprefs.library.Prefs;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mehdi.sakout.fancybuttons.FancyButton;

public class SelectHotelFlightActivity extends BaseActivity implements View.OnClickListener, FilterHotelDialog.FilterHotelDialogListenerArray, SortDialog.SortHotelDialogListener {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    LinearLayout llFilter;
    String flightId, searchIn;
    String flId, searchKey;
    FilterHotelDialog filterHotelDialog;
    private com.eligasht.reservation.tools.ListView list;
    private FlightHotelAdapter adapter;
    private ArrayList<SelectFlightHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private ArrayList<SelectFlightHotelModel> selectHotelModelArrayListFilter = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelTypeModel = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels = new ArrayList<>();
    private ArrayList<FilterPriceModel> filterHotelPriceModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelLocationModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelBestOffModels = new ArrayList<>();
    private ArrayList<FilterStarModel> filterHotelStarsModels = new ArrayList<>();
    private ArrayList<FilterModel> filterModels = new ArrayList<>();
    private HotelFlightSearch hotelFlightSearch;
    private List<Rooms> rooms = new ArrayList<>();
    private RelativeLayout rlLoading, rlRoot;
    private TextView tvAlert, tvTitle, tvDate, tvCount, tvFilterIcon, tvFilter, tvSortIcon, tvSort, tvLoading,tvAlertDesc;
    private Window window;
    private RelativeLayout elNotFound, rlEr, rlList;
    private FancyButton btnNextDays, btnLastDays;
    private int maxPrice, minPrice;
    private FancyButton btnFilter, btnSort;
    private FancyButton btnOk, btnBack, btnHome;
    private ImageView ivLoading, ivImage;
    private String raft, bargasht;
    private String raftFa, bargashtFa;
    private LoadHotelFlightApi loadHotelFlightApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hotel_flight);
        SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
        helper.setEdgeMode(false)
                .setParallaxMode(true)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(true)
                .init(this);
        Utility.setAnimLoading(this);

        window = getWindow();
        notiRecive();
        list = findViewById(R.id.lvHoteResult);
        rlList = findViewById(R.id.rlList);
        btnFilter = findViewById(R.id.btnFilter);
        btnSort = findViewById(R.id.btnSort);
        tvAlert = findViewById(R.id.tvAlert);
        tvLoading = findViewById(R.id.tvLoading);
        tvTitle = findViewById(R.id.tvTitle);
        tvCount = findViewById(R.id.tvCount);
        btnBack = findViewById(R.id.btnBack);
        btnHome = findViewById(R.id.btnHome);
        ivLoading = findViewById(R.id.ivLoading);
        tvFilterIcon = findViewById(R.id.tvFilterIcon);
        btnHome = findViewById(R.id.btnHome);
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

        // ivImage = findViewById(R.id.ivImage);
        btnNextDays.setOnClickListener(this);
        btnLastDays.setOnClickListener(this);
        // ivImage.setImageResource(R.drawable.flight_h);
        btnFilter.setOnClickListener(this);
        btnSort.setOnClickListener(this);
        adapter = new FlightHotelAdapter(selectHotelModelArrayList, this, tvDate);
        list.setAdapter(adapter);
        Utility.loadingText(tvLoading, Prefs.getString("FH", ""));


        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setOnClickListener(this);
        /*  raftFa = getIntent().getExtras().getString("CheckInFa");
        bargashtFa = getIntent().getExtras().getString("CheckOutFa");*/
        raftFa = SingletonDate.getInstance().getStartDate().getDescription();
        bargashtFa = SingletonDate.getInstance().getEndDate().getDescription();
        tvDate.setText(raftFa + " - " + bargashtFa);
        rooms.add(new Rooms(getIntent().getExtras().getInt("Adult"), getIntent().getExtras().getInt("Child")));


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

        new GetHotelAsync().execute();
        Utility.init_floating(list, this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFilter:
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                FilterHotelDialog filterHotelDialog = FilterHotelDialog.newInstance(SelectHotelFlightActivity.this, filterModels, SelectHotelFlightActivity.this, filterHotelTypeModel,
                        filterHotelFacilitiesModels, filterHotelPriceModels, searchIn, filterHotelLocationModels, filterHotelBestOffModels, filterHotelStarsModels);

                filterHotelDialog.show(fm,"test");



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
                    new GetHotelAsync().execute();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.datePickerError,
                            Toast.LENGTH_SHORT).show();

                }

              /*  btnNextDays.setClickable(true);
                btnNextDays.setEnabled(true);


                    *//*         tvDate.setText("از تاریخ: " + raftFa + " تا تاریخ: " + bargashtFa);
                            new GetHotelAsync().execute();*//*


//rastie AdateF

//Ddate kochike
                //adate bargasht

                //"2017-12-24"
                try {

                    String str_date = raft;//2018-01-16
                    DateFormat formatter;
                    Date date;
                    formatter = new SimpleDateFormat("yyyy/MM/dd");
                    date = formatter.parse(str_date);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    cal.add(Calendar.DATE, 1);
                    System.out.println("Add one day to current date : " + formatter.format(cal.getTime()));


                    Date dateRaft = formatter.parse(raft);
                    Date dateBargasht = formatter.parse(bargasht);
                    if (dateBargasht.after(dateRaft)) {
                        ///
                        ///
                        SimpleDateFormat dfm = new SimpleDateFormat("dd MMMM yyyy");
                        //  txtDateOnvan.setText(AdateF + "  -  " + dfm.format(cal.getTime()));
                        /////////////////////////////
                        SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM/dd");//2017/03/24 11:49
                        String formatted3 = format3.format(cal.getTime());
                        String[] dateSplite = formatted3.split("/");

                        String dayM = dateSplite[2];
                        String monthM = dateSplite[1];
                        String yearM = dateSplite[0];

                        String dateShamsi = SolarCalendar.calSolarCalendar(Integer.parseInt(yearM), Integer.parseInt(monthM), Integer.parseInt(dayM));
                        System.out.println("dateShamsi:" + yearM + monthM + dayM + "   " + dateShamsi);

                        String[] dateSplite2 = dateShamsi.split("/");

                        String dayMF = dateSplite2[2];
                        String monthMF = dateSplite2[1];
                        String yearMF = dateSplite2[0];
                      *//*  String dayMF=dateShamsi.substring(8, 10);//02
                        String monthMF=dateShamsi.substring(5, 7);//01
                        String yearMF=dateShamsi.substring(0, 4);//1396
*//*
                        PersianCalendar persianCalendar = new PersianCalendar();
                        persianCalendar.set(Integer.parseInt(yearMF), Integer.parseInt(monthMF) - 1, Integer.parseInt(dayMF));
                        /////////////////////
                        //   txtDateOnvan.setText(dfm.format(cal.getTime()) + "  -  " + AdateF);
                        ///
                        raftFa = persianCalendar.getPersianLongDate();
                        raft = formatter.format(cal.getTime());
                        if (getIntent().getExtras().getBoolean("Geo")) {

                            tvDate.setText(DateUtil.getLongStringDate(raft, "yyyy/MM/dd", false) + " - " + DateUtil.getLongStringDate(bargasht, "yyyy/MM/dd", false));

                        } else {
                            tvDate.setText(raftFa + " - " + bargashtFa);


                        }
                        new GetHotelAsync().execute();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.datePickerError,
                                Toast.LENGTH_SHORT).show();
                    }

                } catch (java.text.ParseException e) {
                    System.out.println("Exception :" + e);
                }
*/

                break;
            case R.id.btnLastDays:

//rastie AdateF

//Ddate kochike
                //adate bargasht
                if (SingletonDate.getInstance().getStartDate().minusOneDay()) {
                    tvDate.setText(SingletonDate.getInstance().getStartDate().getDescription() + " - " + SingletonDate.getInstance().getEndDate().getDescription());
                    raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                    bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
                    new GetHotelAsync().execute();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.DatePickerError2,
                            Toast.LENGTH_SHORT).show();

                }


             /*   try {

                    String str_date = raft;//"11-June-07";
                    DateFormat formatter;
                    Date date;
                    formatter = new SimpleDateFormat("yyyy/MM/dd");
                    date = formatter.parse(str_date);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    cal.add(Calendar.DATE, -1);
                    System.out.println("Mines one day to current date : " + formatter.format(cal.getTime()));
                    //shart kamtar az emruz
                    if (System.currentTimeMillis() <= date.getTime()) {
                        raft = formatter.format(cal.getTime());

                        ///onvan
                        SimpleDateFormat dfm = new SimpleDateFormat("dd MMMM yyyy");
                        /////////////////////////////
                        SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM/dd");//2017/03/24 11:49
                        String formatted3 = format3.format(cal.getTime());
                        String[] dateSplite = formatted3.split("/");

                        String dayM = dateSplite[2];
                        String monthM = dateSplite[1];
                        String yearM = dateSplite[0];
                        String dateShamsi = com.eligasht.reservation.models.model.SolarCalendar.calSolarCalendar(Integer.parseInt(yearM), Integer.parseInt(monthM), Integer.parseInt(dayM));
                        String[] dateSplite2 = dateShamsi.split("/");

                        String dayMF = dateSplite2[2];
                        String monthMF = dateSplite2[1];
                        String yearMF = dateSplite2[0];
                        PersianCalendar persianCalendar = new PersianCalendar();
                        persianCalendar.set(Integer.parseInt(yearMF), Integer.parseInt(monthMF) - 1, Integer.parseInt(dayMF));
                        /////////////////////
                        // txtDateOnvan.setText(AdateF + "  -  " + dfm.format(cal.getTime()));
                        raftFa = persianCalendar.getPersianLongDate();
                        raft = formatter.format(cal.getTime());
                        if (getIntent().getExtras().getBoolean("Geo")) {

                            tvDate.setText(DateUtil.getLongStringDate(raft, "yyyy/MM/dd", false) + " - " + DateUtil.getLongStringDate(bargasht, "yyyy/MM/dd", false));

                        } else {
                            tvDate.setText(raftFa + " - " + bargashtFa);


                        }
                        new GetHotelAsync().execute();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.DatePickerError2, Toast.LENGTH_SHORT).show();
                    }


                } catch (java.text.ParseException e) {
                    System.out.println("Exception :" + e);
                }
*/

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
                        return Integer.valueOf(p2.getPrice()) - Integer.valueOf(p1.getPrice()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectFlightHotelModel>() {
                    @Override
                    public int compare(SelectFlightHotelModel p1, SelectFlightHotelModel p2) {
                        return Integer.valueOf(p2.getPrice()) - Integer.valueOf(p1.getPrice()); // Ascending
                    }
                });
                adapter.notifyDataSetChanged();
                break;
            case 2:
                Collections.sort(selectHotelModelArrayList, new Comparator<SelectFlightHotelModel>() {
                    @Override
                    public int compare(SelectFlightHotelModel p1, SelectFlightHotelModel p2) {
                        return Integer.valueOf(p1.getPrice()) - Integer.valueOf(p2.getPrice()); // Ascending
                    }
                });
                Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectFlightHotelModel>() {
                    @Override
                    public int compare(SelectFlightHotelModel p1, SelectFlightHotelModel p2) {
                        return Integer.valueOf(p1.getPrice()) - Integer.valueOf(p2.getPrice()); // Ascending
                    }
                });
                adapter.notifyDataSetChanged();
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
            adapter = new FlightHotelAdapter(selectHotelModelArrayList, SelectHotelFlightActivity.this, tvDate);
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
                adapter = new FlightHotelAdapter(selectHotelModelArrayList, SelectHotelFlightActivity.this, tvDate);
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
                adapter = new FlightHotelAdapter(selectHotelModelArrayListFilter, SelectHotelFlightActivity.this, tvDate);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }


        }

        if (remove) {
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            adapter = new FlightHotelAdapter(selectHotelModelArrayList, SelectHotelFlightActivity.this, tvDate);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
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

                        if (filterHotelFacilitiesModels.get(i).getTitle().contains(selectHotelModels.get(j).getFacilities().get(k).Title)) {
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

        try{



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
                        selectHotelModelArrayList.get(j).getLocations(), selectHotelModelArrayList.get(j).getFlightId());


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
                        selectHotelModelArrayListFilter.get(j).getAmount(), selectHotelModelArrayListFilter.get(j).getLocations(), selectHotelModelArrayList.get(j).getFlightId());
            }

        }
        catch (Exception e){
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            adapter = new FlightHotelAdapter(selectHotelModelArrayList, SelectHotelFlightActivity.this, tvDate);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            elNotFound.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
            rlList.setVisibility(View.GONE);
            btnOk.setVisibility(View.GONE);
            rlEr.setVisibility(View.GONE);
            tvAlert.setText(R.string.filter_no_found);
            tvAlertDesc.setText(R.string.change_filter);
        }




        return selectHotelModel;
    }

    private String OrderToJsonCheckFlight() {
        JSONObject jsone = new JSONObject();
        JSONObject manJson = new JSONObject();


        try {

            manJson.put("UserName", "EligashtMlb");
            manJson.put("Password", "123qwe!@#QWE");
            manJson.put("TermianlId", "Mobile");
            manJson.put("Code", Prefs.getString("Value-Hotel-City-Code-HF-Raft", "IST"));//inja esme forudgah mikhore
            manJson.put("ToCode", Prefs.getString("Value-Hotel-City-Code-HF-Source", "THR"));

            jsone.put("request", manJson);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("request:" + jsone.toString());
        return jsone.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 155) {
            if (resultCode == Activity.RESULT_OK) {
                flId = data.getStringExtra("FlightId");
                searchKey = data.getStringExtra("searchKey");

                new ChangeFlightAsync().execute();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    private class GetHotelAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

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
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                hotelFlightSearch = new HotelFlightSearch(new HotelAR(new RquestHF("HF", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                        Utility.convertNumbersToEnglish(raft),  Utility.convertNumbersToEnglish(bargasht), Prefs.getString("Value-Hotel-City-Code-HF-Raft", "IST"), rooms, getIntent().getExtras().getString("Rooms"), getString(R.string.culture),
                        Prefs.getString("Value-Hotel-City-Code-HF-Source", "THR"))));


                Gson gson = new Gson();

                Log.e("test", gson.toJson(new HotelAR(new RquestHF("HF", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                        raft, bargasht, Prefs.getString("Value-Hotel-City-Code-HF-Raft", "IST"), rooms, getIntent().getExtras().getString("Rooms"), getString(R.string.culture), Prefs.getString("Value-Hotel-City-Code-HF-Source", "THR")))));
            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this, R.color.colorPrimaryDark));
            }


            selectHotelModelArrayList.clear();
            selectHotelModelArrayListFilter.clear();

            try {
                Log.e("test", new Gson().toJson(hotelFlightSearch.hotelFlightModelResponse));
                if (hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.Errors != null) {
                    elNotFound.setVisibility(View.VISIBLE);
                    tvAlert.setText(hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.Errors.get(0).DetailedMessage);
                    list.setVisibility(View.GONE);
                    rlList.setVisibility(View.GONE);
                    llFilter.setVisibility(View.GONE);


                } else if (hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Hotels.isEmpty()) {
                    elNotFound.setVisibility(View.VISIBLE);
                    tvAlert.setText(R.string.NoResult);
                    tvAlertDesc.setText(getString(R.string.change_date));
                    list.setVisibility(View.GONE);
                    rlList.setVisibility(View.GONE);
                    llFilter.setVisibility(View.GONE);


                } else {

                    maxPrice = hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.MaxPrice;
                    minPrice = hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.MinPrice;
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
                    int j = 0;
                    for (Hotels hotels : hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Hotels) {
                        String off = "";
                        boolean isOff = false;
                        int xiff = 0;
                        int hotelPrice = Integer.valueOf(hotels.Availability.RoomLists.get(i).Price);


                        if ((hotels.Availability.RoomLists.get(i).OldPrice > 0) &&
                                (hotels.Availability.RoomLists.get(i).OldPrice > Integer.valueOf(hotels.Availability.RoomLists.get(i).Price))) {

                            int p1 = hotels.Availability.RoomLists.get(i).OldPrice - Integer.valueOf(hotels.Availability.RoomLists.get(i).Price);
                            int p2 = p1 * 100;
                            int p3 = p2 / hotels.Availability.RoomLists.get(i).OldPrice;
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


                        selectHotelModelArrayList.add(new SelectFlightHotelModel(hotels.Name, hotels.City, hotels.Availability.RoomLists.get(i).Title,
                                hotels.Availability.RoomLists.get(i).Board, hotels.Availability.RoomLists.get(i).Price, hotels.MainImage, hotels.Location,
                                hotels.Availability.RoomLists.get(i).OldPrice, hotels.StarRating,
                                hotels.Availability.RoomLists.get(i).EHotelId,
                                hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.ResultUniqID, hotels.BestSell, isOff,
                                off, hotels.TypeText, hotels.Facilities,
                                xiff, hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Flights.FltList,
                                hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Flights.ArrRout,
                                hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Flights.DepRout,
                                hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Flights.Amount,
                                hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Locations, hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Flights.FlightID));

                        flightId = hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Flights.FlightID;

                        //  i++;


                    }

                    filterHotelStarsModels.add(new FilterStarModel(getString(R.string._1star), false, 1));
                    filterHotelStarsModels.add(new FilterStarModel(getString(R.string._2star), false, 2));
                    filterHotelStarsModels.add(new FilterStarModel(getString(R.string._3star), false, 3));
                    filterHotelStarsModels.add(new FilterStarModel(getString(R.string._4star), false, 4));
                    filterHotelStarsModels.add(new FilterStarModel(getString(R.string._5star), false, 5));
                    filterHotelStarsModels.add(new FilterStarModel(getString(R.string.WithoutStar), false, -1));

                    filterHotelBestOffModels.add(new FilterHotelTypeModel(getString(R.string.BestSell), false));
                    filterHotelBestOffModels.add(new FilterHotelTypeModel(getString(R.string.BestOff), false));
                    for (Facilities facilities : hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Facilities) {

                        filterHotelFacilitiesModels.add(new FilterHotelTypeModel(facilities.Title, false));
                    }

                    for (HotelTypes hotelTypes : hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.HotelTypes) {


                        filterHotelTypeModel.add(new FilterHotelTypeModel(hotelTypes.Title, false));


                    }

                    for (Locations locations : hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Locations) {


                        filterHotelLocationModels.add(new FilterHotelTypeModel(locations.Title, false));


                    }
                    tvTitle.setText(Prefs.getString("Value-Hotel-City-Fa-HF-Raft", "استانبول"));
                    tvCount.setText("(" + selectHotelModelArrayList.size() + "مورد یافت شد" + ")");
                    Collections.sort(selectHotelModelArrayList, new Comparator<SelectFlightHotelModel>() {
                        @Override
                        public int compare(SelectFlightHotelModel p1, SelectFlightHotelModel p2) {
                            return Integer.valueOf(p1.getPrice()) - Integer.valueOf(p2.getPrice()); // Ascending
                        }
                    });
                    Collections.sort(selectHotelModelArrayListFilter, new Comparator<SelectFlightHotelModel>() {
                        @Override
                        public int compare(SelectFlightHotelModel p1, SelectFlightHotelModel p2) {
                            return Integer.valueOf(p1.getPrice()) - Integer.valueOf(p2.getPrice()); // Ascending
                        }
                    });
                    adapter.notifyDataSetChanged();
                 /*   filterHotelDialog = new FilterHotelDialog(SelectHotelFlightActivity.this, filterModels, SelectHotelFlightActivity.this, filterHotelTypeModel,
                            filterHotelFacilitiesModels, filterHotelPriceModels, searchIn, filterHotelLocationModels, filterHotelBestOffModels, filterHotelStarsModels);
*/
                }

            } catch (Exception e) {
                llFilter.setVisibility(View.GONE);
                list.setVisibility(View.GONE);
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
            //dakheli khareji
            new AsyncCheckFlight().execute();

        }

    }

    private class AsyncCheckFlight extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {
            try {


                url = new URL("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetIsDomestic");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                // conn.setRequestMethod("GET");
                conn.setRequestMethod("POST");
                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                String serial = null;

                JSONObject errorObj = new JSONObject();

                try {
                    errorObj.put("Success", false);

                    Class<?> c = Class.forName("android.os.SystemProperties");
                    Method get = c.getMethod("get", String.class);
                    serial = (String) get.invoke(c, "ro.serialno");//31007a81d4b22300
                } catch (Exception ignored) {
                }


                String data = OrderToJsonCheckFlight();


                HttpClient client = new DefaultHttpClient();


                HttpPost post = new HttpPost();
                post = new HttpPost("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetIsDomestic");
                post.setHeader("Content-Type", "application/json; charset=UTF-8");
                post.setHeader("Accept", "application/json; charset=UTF-8");


                StringEntity se = null;
                try {
                    se = new StringEntity(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                post.setEntity(se);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                //{"GetAirportWithParentsResult":{"Errors":[],"List":[{"Key":"IST|Istanbul, Turkey (IST-All Airports)","Value":"استانبول ( همه فرودگاه ها ),نزدیک استانبول, ترکیه"},{"Key":"IST|Istan
                //try {
                //HashMap<String, String> airport = null;
                //mylist = new ArrayList<HashMap<String, String>>();
                HttpResponse res = client.execute(post);
                String retSrc = EntityUtils.toString(res.getEntity(), HTTP.UTF_8);


                return (retSrc);


            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {
            //this method will be running on UI thread
            System.out.println("result:" + result);
            //	avi.setVisibility(View.INVISIBLE);
            List<Country> data = new ArrayList<Country>();


            try {


////////////////////////////
                JSONObject jsonObj = new JSONObject(result);

                //JSONObject GetAirportsResult = jsonObj.getJSONObject("GetAirportWithParentsResult");
                /////////////////////////////////////
                String GetError = "";
                JSONArray jError = null;

                // Getting JSON Array node
                JSONObject GetAirportsResult = jsonObj.getJSONObject("GetIsDomesticResult");//Error
                if (!GetAirportsResult.getString("Errors").equals("null")) {
                    jError = GetAirportsResult.getJSONArray("Errors");//
                    JSONObject jPricedItinerary = jError.getJSONObject(0);
                    GetError = jPricedItinerary.getString("Message");
                }
                if (GetError.length() > 1) {
                    try {
                      /*  AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(SelectHotelFlightActivity.this);
                        AlertDialogPassenger.setText(GetError);*/
                    } catch (Exception e) {
                    }

                } else {
////////////////////////////////
                    /*JSONArray jArray = GetAirportsResult.getJSONArray("Airports");//AirportCode //AirportName//CityName ":

					for (int i = 0; i < jArray.length(); i++) {
						JSONObject json_data = jArray.getJSONObject(i);
*/
                    boolean IsDemostic = GetAirportsResult.getBoolean("IsDomestic");//false khareji true dakheli
                    if (IsDemostic)
                        Prefs.putBoolean("IsDemostic", true);
                    else
                        Prefs.putBoolean("IsDemostic", false);

                    //}


                }

            } catch (JSONException e) {

            }


        }

    }

    private class ChangeFlightAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this, R.color.hf));
            }
            Log.e("changeflightTest", new Gson().toJson((new ChangeFlightApiRequest(new Request(flId, searchKey, getString(R.string.culture), new Identity("EligashtMlb",
                    "123qwe!@#QWE", "Mobile"))))));
            new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                loadHotelFlightApi = new LoadHotelFlightApi(new ChangeFlightApiRequest(new Request(flId, searchKey, getString(R.string.culture), new Identity("EligashtMlb",
                        "123qwe!@#QWE", "Mobile"))));


            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this, R.color.colorPrimaryDark));
            }

            try {
                selectHotelModelArrayList.clear();
                if (loadHotelFlightApi.changeFlightApiResponse.LoadFlightResult.Error != null) {
                    elNotFound.setVisibility(View.VISIBLE);
                    tvAlert.setText(hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.Errors.get(0).DetailedMessage);
                    list.setVisibility(View.GONE);
                    llFilter.setVisibility(View.GONE);

                    list.setVisibility(View.GONE);

                } else if (loadHotelFlightApi.changeFlightApiResponse.LoadFlightResult.HFlight.FltList.isEmpty()) {
                    elNotFound.setVisibility(View.VISIBLE);
                    tvAlert.setText(R.string.NoResult);
                    list.setVisibility(View.GONE);
                    llFilter.setVisibility(View.GONE);

                    list.setVisibility(View.GONE);

                } else {
                    maxPrice = hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.MaxPrice;
                    minPrice = hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.MinPrice;
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
                    for (Hotels hotels : hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Hotels) {
                        String off = "";
                        boolean isOff = false;
                        int xiff = 0;
                        int hotelPrice = Integer.valueOf(hotels.Availability.RoomLists.get(i).Price);


                        if ((hotels.Availability.RoomLists.get(i).OldPrice > 0) &&
                                (hotels.Availability.RoomLists.get(i).OldPrice > Integer.valueOf(hotels.Availability.RoomLists.get(i).Price))) {

                            int p1 = hotels.Availability.RoomLists.get(i).OldPrice - Integer.valueOf(hotels.Availability.RoomLists.get(i).Price);
                            int p2 = p1 * 100;
                            int p3 = p2 / hotels.Availability.RoomLists.get(i).OldPrice;
                            if (p3 != 0) {
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


                        selectHotelModelArrayList.add(new SelectFlightHotelModel(hotels.Name, hotels.City, hotels.Availability.RoomLists.get(i).Title,
                                hotels.Availability.RoomLists.get(i).Board, hotels.Availability.RoomLists.get(i).Price, hotels.MainImage, hotels.Location,
                                hotels.Availability.RoomLists.get(i).OldPrice, hotels.StarRating,
                                hotels.Availability.RoomLists.get(i).EHotelId,
                                hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.ResultUniqID, hotels.BestSell, isOff,
                                off, hotels.TypeText, hotels.Facilities,
                                xiff, loadHotelFlightApi.changeFlightApiResponse.LoadFlightResult.HFlight.FltList,
                                loadHotelFlightApi.changeFlightApiResponse.LoadFlightResult.HFlight.ArrRout,
                                loadHotelFlightApi.changeFlightApiResponse.LoadFlightResult.HFlight.DepRout,
                                loadHotelFlightApi.changeFlightApiResponse.LoadFlightResult.HFlight.Amount,
                                hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Locations, loadHotelFlightApi.changeFlightApiResponse.LoadFlightResult.HFlight.FlightID));

                        flightId = loadHotelFlightApi.changeFlightApiResponse.LoadFlightResult.HFlight.FlightID;

                        //  i++;


                    }
                    adapter = new FlightHotelAdapter(selectHotelModelArrayList, SelectHotelFlightActivity.this, tvDate);
                    list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                }


            } catch (Exception e) {
                llFilter.setVisibility(View.GONE);
                list.setVisibility(View.GONE);
                elNotFound.setVisibility(View.VISIBLE);
                if (!Utility.isNetworkAvailable(SelectHotelFlightActivity.this)) {

                    tvAlert.setText(R.string.InternetError);

                } else {

                    tvAlert.setText(R.string.ErrorServer);

                }
                list.setVisibility(View.GONE);
                tvAlertDesc.setVisibility(View.GONE);
                btnOk.setVisibility(View.VISIBLE);
                rlEr.setVisibility(View.VISIBLE);

            }

        }

    }

}
