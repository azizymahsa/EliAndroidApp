package com.eligasht.reservation.views.activities.hotel.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.pixplicity.easyprefs.library.Prefs;
import com.eligasht.reservation.R;
import com.eligasht.reservation.api.hotel.hotelFlight.HotelFlightSearch;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.Country;
import com.eligasht.reservation.models.HotelAR;
import com.eligasht.reservation.models.RquestHF;
import com.eligasht.reservation.models.hotel.FilterPriceModel;
import com.eligasht.reservation.models.hotel.adapter.FilterModel;
import com.eligasht.reservation.models.hotel.adapter.SelectFlightHotelModel;
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
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelDialog;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelTypeModel;
import com.eligasht.reservation.views.ui.dialog.hotel.SortDialog;

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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mehdi.sakout.fancybuttons.FancyButton;

public class SelectHotelFlightActivity extends BaseActivity implements FilterHotelDialog.FilterHotelDialogListenerArray, View.OnClickListener, SortDialog.SortHotelDialogListener {
    private ListView list;
    private FlightHotelAdapter adapter;
    private ArrayList<SelectFlightHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private ArrayList<SelectFlightHotelModel> selectHotelModelArrayListFilter = new ArrayList<>();
    private ArrayList<SelectFlightHotelModel> selectHotelModelArrayListFilter1 = new ArrayList<>();
    private ArrayList<FilterModel> filterModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelTypeModel = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels = new ArrayList<>();
    private ArrayList<FilterPriceModel> filterHotelPriceModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelLocationModels = new ArrayList<>();

    private HotelFlightSearch hotelFlightSearch;
    private List<Rooms> rooms = new ArrayList<>();
    RelativeLayout rlLoading, rlRoot;
    TextView tvAlert, tvTitle, tvDate, tvCount, tvFilterIcon, tvFilter, tvSortIcon, tvSort,tvLoading;
    Window window;
    RelativeLayout elNotFound;
    FancyButton btnNextDays, btnLastDays;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    int maxPrice, minPrice;

    LinearLayout llBottom, llSort,llFilter;
    FancyButton btnOk, btnBack, btnHome;
    ImageView ivLoading,ivImage;

    String raft, bargasht;
    String raftFa, bargashtFa;
    boolean isFilter = false;
    String flightId,searchIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hotel_flight);
        Utility.setAnimLoading(this);


        window = getWindow();
        notiRecive();
        list = findViewById(R.id.lvHoteResult);
        llBottom = findViewById(R.id.llBottom);
        llSort = findViewById(R.id.llSort);
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
        // ivImage = findViewById(R.id.ivImage);
        btnNextDays.setOnClickListener(this);
        btnLastDays.setOnClickListener(this);
        // ivImage.setImageResource(R.drawable.flight_h);
        llBottom.setOnClickListener(this);
        llSort.setOnClickListener(this);
        adapter = new FlightHotelAdapter(selectHotelModelArrayList, this, this);
        list.setAdapter(adapter);
        Utility.loadingText(tvLoading,Prefs.getString("FH",""));


        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setOnClickListener(this);
        raftFa = getIntent().getExtras().getString("CheckInFaHF");
        bargashtFa = getIntent().getExtras().getString("CheckOutFaHF");


        tvDate.setText(raftFa + " - " + bargashtFa);
        rooms.add(new Rooms(getIntent().getExtras().getInt("Adult"), getIntent().getExtras().getInt("Child")));


        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        raft = getIntent().getExtras().getString("CheckInHF");
        bargasht = getIntent().getExtras().getString("CheckOutHF");
        new GetHotelAsync().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectHotelModelArrayListFilter.isEmpty()) {

                    Intent i = new Intent(SelectHotelFlightActivity.this, DetailHotelActivity.class);
                    i.putExtra("HotelId", selectHotelModelArrayList.get(position).geteHotelId());
                    i.putExtra("ResultUniqID", selectHotelModelArrayList.get(position).getResultUniqID());
                    i.putExtra("FlightID", flightId);
                    i.putExtra("CheckInHF", getIntent().getExtras().getString("CheckInHF"));
                    i.putExtra("CheckOutHF", getIntent().getExtras().getString("CheckOutHF"));
                    i.putExtra("type", 1);

                    startActivity(i);
                }else{
                    Intent i = new Intent(SelectHotelFlightActivity.this, DetailHotelActivity.class);
                    i.putExtra("HotelId", selectHotelModelArrayListFilter.get(position).geteHotelId());
                    i.putExtra("ResultUniqID", selectHotelModelArrayListFilter.get(position).getResultUniqID());
                    i.putExtra("FlightID", flightId);
                    i.putExtra("CheckInHF", getIntent().getExtras().getString("CheckInHF"));
                    i.putExtra("CheckOutHF", getIntent().getExtras().getString("CheckOutHF"));
                    i.putExtra("type", 1);

                    startActivity(i);


                }


            }
        });
        btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


// TODO: 1/12/2018 change this


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBottom:
                new FilterHotelDialog(SelectHotelFlightActivity.this, filterModels, this, filterHotelTypeModel, filterHotelFacilitiesModels, filterHotelPriceModels,searchIn,filterHotelLocationModels);


                break;
            case R.id.llSort:
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
                btnNextDays.setClickable(true);
                btnNextDays.setEnabled(true);


                    /*         tvDate.setText("از تاریخ: " + raftFa + " تا تاریخ: " + bargashtFa);
                            new GetHotelAsync().execute();*/


//rastie AdateF

//Ddate kochike
                //adate bargasht

                //"2017-12-24"
                try {

                    String str_date = raft;//2018-01-16
                    DateFormat formatter;
                    Date date;
                    formatter = new SimpleDateFormat("yyyy/MM/dd");
                    date = (Date) formatter.parse(str_date);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    cal.add(Calendar.DATE, 1);
                    System.out.println("Add one day to current date : " + formatter.format(cal.getTime()));


                    Date dateRaft = (Date) formatter.parse(raft);
                    Date dateBargasht = (Date) formatter.parse(bargasht);
                    if (dateBargasht.after(dateRaft)) {
                        ///
                        ///
                        SimpleDateFormat dfm = new SimpleDateFormat("dd MMMM yyyy");
                        //  txtDateOnvan.setText(AdateF + "  -  " + dfm.format(cal.getTime()));
                        /////////////////////////////
                        SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM/dd");//2017/03/24 11:49
                        String formatted3 = format3.format(cal.getTime());
                        String[] dateSplite=formatted3.split("/");

                        String dayM=dateSplite[2];
                        String monthM=dateSplite[1];
                        String yearM=dateSplite[0];

                        String  dateShamsi= SolarCalendar.calSolarCalendar(Integer.parseInt(yearM),Integer.parseInt(monthM),Integer.parseInt(dayM));
                        System.out.println("dateShamsi:"+yearM+monthM+dayM+"   "+dateShamsi);

                        String[] dateSplite2=dateShamsi.split("/");

                        String dayMF=dateSplite2[2];
                        String monthMF=dateSplite2[1];
                        String yearMF=dateSplite2[0];
                      /*  String dayMF=dateShamsi.substring(8, 10);//02
                        String monthMF=dateShamsi.substring(5, 7);//01
                        String yearMF=dateShamsi.substring(0, 4);//1396
*/
                        PersianCalendar persianCalendar = new PersianCalendar();
                        persianCalendar.set(Integer.parseInt(yearMF), Integer.parseInt(monthMF)-1, Integer.parseInt(dayMF));
                        /////////////////////
                        //   txtDateOnvan.setText(dfm.format(cal.getTime()) + "  -  " + AdateF);
                        ///
                        raftFa=persianCalendar.getPersianLongDate();
                        raft = formatter.format(cal.getTime());
                        if (getIntent().getExtras().getBoolean("Geo")) {

                            tvDate.setText(DateUtil.getLongStringDate(raft, "yyyy/MM/dd", false)+ " - " + DateUtil.getLongStringDate(bargasht, "yyyy/MM/dd", false));

                        }else{
                            tvDate.setText(raftFa + " - " + bargashtFa);


                        }
                        new GetHotelAsync().execute();
                    } else {
                        Toast.makeText(getApplicationContext(), "تاریخ رفت بزرگتر از تاریخ برگشت می باشد",
                                Toast.LENGTH_SHORT).show();
                    }

                } catch (java.text.ParseException e) {
                    System.out.println("Exception :" + e);
                }






                break;
            case R.id.btnLastDays:

//rastie AdateF

//Ddate kochike
                //adate bargasht

                try {

                    String str_date = raft;//"11-June-07";
                    DateFormat formatter;
                    Date date;
                    formatter = new SimpleDateFormat("yyyy/MM/dd");
                    date = (Date) formatter.parse(str_date);
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
                        String[] dateSplite=formatted3.split("/");

                        String dayM=dateSplite[2];
                        String monthM=dateSplite[1];
                        String yearM=dateSplite[0];
                        String  dateShamsi= com.eligasht.reservation.models.model.SolarCalendar.calSolarCalendar(Integer.parseInt(yearM),Integer.parseInt(monthM),Integer.parseInt(dayM));
                        String[] dateSplite2=dateShamsi.split("/");

                        String dayMF=dateSplite2[2];
                        String monthMF=dateSplite2[1];
                        String yearMF=dateSplite2[0];
                        PersianCalendar persianCalendar = new PersianCalendar();
                        persianCalendar.set(Integer.parseInt(yearMF), Integer.parseInt(monthMF)-1, Integer.parseInt(dayMF));
                        /////////////////////
                        // txtDateOnvan.setText(AdateF + "  -  " + dfm.format(cal.getTime()));
                        raftFa=persianCalendar.getPersianLongDate();
                        raft = formatter.format(cal.getTime());
                        if (getIntent().getExtras().getBoolean("Geo")) {

                            tvDate.setText(DateUtil.getLongStringDate(raft, "yyyy/MM/dd", false)+ " - " + DateUtil.getLongStringDate(bargasht, "yyyy/MM/dd", false));

                        }else{
                            tvDate.setText(raftFa + " - " + bargashtFa);


                        }
                        new GetHotelAsync().execute();
                    } else {
                        Toast.makeText(getApplicationContext(), "قبل از تاریخ امروز را نمی توان انتخاب کرد", Toast.LENGTH_SHORT).show();
                    }


                } catch (java.text.ParseException e) {
                    System.out.println("Exception :" + e);
                }


                break;
        }
    }

    @Override
    public void onReturnValue(ArrayList<FilterModel> type, String search, ArrayList<FilterHotelTypeModel> filterHotelTypeModels,
                              ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels, ArrayList<FilterPriceModel> filterHotelPriceModel,
                              ArrayList<FilterHotelTypeModel> filterHotelLocationModels) {
        elNotFound.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        btnOk.setVisibility(View.VISIBLE);
        boolean remove = false;

        this.filterModels = type;
        this.searchIn = search;
        this.filterHotelTypeModel = filterHotelTypeModels;
        this.filterHotelPriceModels = filterHotelPriceModel;
        this.filterHotelFacilitiesModels = filterHotelFacilitiesModels;
        this.filterHotelLocationModels = filterHotelLocationModels;

        selectHotelModelArrayListFilter = new ArrayList<>();
        selectHotelModelArrayListFilter1 = new ArrayList<>();


        for (FilterModel filterModel : filterModels) {

            Log.e("test", filterModel.isStar3() + "===" + filterModel.isRemove());


            top_filter(filterModel, filterHotelTypeModels);
            star_filter(filterModel, filterHotelTypeModels);
            facilities_filter(filterHotelFacilitiesModels);
            price_filter(filterHotelPriceModel);
            location_filter(filterHotelLocationModels);


            if ((filterModel.isStar1() || filterModel.isStar2() || filterModel.isStar3() || filterModel.isStar4()
                    || filterModel.isStar5()) && (filterModel.isBestSeler() || filterModel.isBestOff())) {

                isBestOff_and_isStar(filterModel);
                isBestSell_and_isStar(filterModel);
                type_location_filter(filterHotelTypeModels, 0, false, false);

                //todo change this

                selectHotelModelArrayListFilter = new ArrayList<>();
                selectHotelModelArrayListFilter = selectHotelModelArrayListFilter1;


            } else {
                if (!(filterModel.isStar1() || filterModel.isStar2() || filterModel.isStar3() || filterModel.isStar4()
                        || filterModel.isStar5())) {

                    type_location_filter(filterHotelTypeModels, 0, false, false);

                }


                if (!(filterModel.isBestSeler() || filterModel.isBestOff())) {

                    type_location_filter(filterHotelTypeModels, 0, false, false);

                }

            }


            if ((filterModel.isStar1() || filterModel.isStar2() || filterModel.isStar3() || filterModel.isStar4() || filterModel.isStar5() || filterModel.isBestSeler() || filterModel.isBestOff())
                    || filterHotelTypeModels.size() > 0) {

                if (search != null) {
                    ArrayList<SelectFlightHotelModel> selectHotelModelArrayListFilter3 = new ArrayList<>();

                    if (selectHotelModelArrayListFilter.isEmpty()){


                        for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                            if (selectHotelModel.getName().toLowerCase().contains(search.toLowerCase())) {
                                selectHotelModelArrayListFilter3.add(new SelectFlightHotelModel(selectHotelModel.getName(),
                                        selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                        selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                        selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));
                            }

                            //    }
                /*    }
                        for (Iterator<SelectHotelModel> it = selectHotelModelArrayListFilter3.iterator(); it.hasNext(); ) {
                            if (!it.next().getName().toLowerCase().contains(search.toLowerCase())) {
                                it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
                            }*/
                        }

                        selectHotelModelArrayListFilter.clear();
                        selectHotelModelArrayListFilter=selectHotelModelArrayListFilter3;

                    }else{
                        for (Iterator<SelectFlightHotelModel> it = selectHotelModelArrayListFilter.iterator(); it.hasNext(); ) {
                            if (!it.next().getName().toLowerCase().contains(search.toLowerCase())) {
                                it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
                            }


                        }
                    }


                }
            } else {
                if (search != null) {
                    for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                        if (selectHotelModel.getName().toLowerCase().contains(search.toLowerCase())) {
                            selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModel.getName(),
                                    selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                    selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                    selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                    selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                    selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                    selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(),
                                    selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));
                        }

                    }
                }

            }


            if (filterModel.isRemove()) {
                remove = true;

                // search="";
                tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
                tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));

                adapter = new FlightHotelAdapter(selectHotelModelArrayList, SelectHotelFlightActivity.this, SelectHotelFlightActivity.this);
                tvCount.setText("(" + selectHotelModelArrayList.size() + "مورد یافت شد" + ")");
                adapter.notifyDataSetChanged();
                selectHotelModelArrayListFilter.clear();
                searchIn = "";
            }


        }

        if (selectHotelModelArrayListFilter.isEmpty()) {


            isFilter = false;
            ///Toast.makeText(this, "موردی یافت نشد", Toast.LENGTH_SHORT).show();
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));

            adapter = new FlightHotelAdapter(selectHotelModelArrayList, SelectHotelFlightActivity.this, SelectHotelFlightActivity.this);
            tvCount.setText("(" + selectHotelModelArrayList.size() + "مورد یافت شد" + ")");




        } else {
            isFilter = true;
            adapter = new FlightHotelAdapter(selectHotelModelArrayListFilter, SelectHotelFlightActivity.this, SelectHotelFlightActivity.this);
            tvCount.setText("(" + selectHotelModelArrayListFilter.size() + "مورد یافت شد" + ")");
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.red));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.red));

            if (selectHotelModelArrayListFilter.size()==selectHotelModelArrayList.size()){
                if (!remove){
                    elNotFound.setVisibility(View.VISIBLE);
                    tvAlert.setText("نتیجه ای برای فیلتر شما حاصل نشد!");
                    list.setVisibility(View.GONE);
                    btnOk.setVisibility(View.GONE);

                }
                tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
                tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            }
        }
        list.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }

    public void top_filter(FilterModel filterModel, ArrayList<FilterHotelTypeModel> filterHotelTypeModels) {


        if (filterModel.isBestSeler() && filterModel.isBestOff()) {
            for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.isBestSell()&&selectHotelModel.isOff()) {
                    selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModel.getName(),
                            selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                            selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                            selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(), selectHotelModel.getLocations()));
                }


            }
            type_location_filter(filterHotelTypeModels, 0, true, true);

        } else if (filterModel.isBestSeler()) {
            for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.isBestSell()) {
                    selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModel.getName(),
                            selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                            selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                            selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(), selectHotelModel.getLocations()));
                }


            }
            type_location_filter(filterHotelTypeModels, 0, false, true);



        }else if (filterModel.isBestOff()) {
            for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.isOff()) {
                    selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModel.getName(),
                            selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                            selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                            selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));


                }

            }
            type_location_filter(filterHotelTypeModels, 0, true, false);

        }


    }

    public void type_location_filter(ArrayList<FilterHotelTypeModel> filterHotelTypeModels, int star, boolean isOff, boolean isBestseler) {
        boolean stars = true;

        for (FilterModel filterModel : filterModels) {
            if (!(filterModel.isStar1() || filterModel.isStar2() || filterModel.isStar3() || filterModel.isStar4()
                    || filterModel.isStar5())) {
                stars = false;

            }
        }

        if (!stars || !isOff || !isBestseler) {

            if (selectHotelModelArrayListFilter.isEmpty()) {

                for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                    if (filterHotelTypeModels.get(i).isCheck()) {
                        for (int j = 0; j < selectHotelModelArrayList.size(); j++) {
                            if (filterHotelTypeModels.get(i).getTitle().equals(selectHotelModelArrayList.get(j).getTypeText())) {

                                selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModelArrayList.get(j).getName(),
                                        selectHotelModelArrayList.get(j).getCity(), selectHotelModelArrayList.get(j).getTitle(),
                                        selectHotelModelArrayList.get(j).getBoard(), selectHotelModelArrayList.get(j).getPrice(),
                                        selectHotelModelArrayList.get(j).getImageUrl(), selectHotelModelArrayList.get(j).getLocation(),
                                        selectHotelModelArrayList.get(j).getOldPrice(), selectHotelModelArrayList.get(j).getStar(),
                                        selectHotelModelArrayList.get(j).geteHotelId(), selectHotelModelArrayList.get(j).getResultUniqID(),
                                        selectHotelModelArrayList.get(j).isBestSell(), selectHotelModelArrayList.get(j).isOff(),
                                        selectHotelModelArrayList.get(j).getOff(), selectHotelModelArrayList.get(j).getTypeText(),
                                        selectHotelModelArrayList.get(j).getFacilities(), selectHotelModelArrayList.get(j).getDiff(), selectHotelModelArrayList.get(j).getFlights(), selectHotelModelArrayList.get(j).getArrRout(), selectHotelModelArrayList.get(j).getDepRout(), selectHotelModelArrayList.get(j).getAmount(),selectHotelModelArrayList.get(j).getLocations()));

                            }

                        }
                    }


                }
            }
        } else if (stars) {


            for (FilterModel filterModel : filterModels) {
                if ((filterModel.isStar1() || filterModel.isStar2() || filterModel.isStar3() || filterModel.isStar4()
                        || filterModel.isStar5())) {


                    ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
                    for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                        if (filterHotelTypeModels.get(i).isCheck()) {
                            for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                                if (filterHotelTypeModels.get(i).getTitle().equals(selectHotelModelArrayListFilter.get(j).getTypeText()) && selectHotelModelArrayListFilter.get(j).getStar() == star) {
                                    isFilter = true;
                                    selectHotelModels.add(new SelectFlightHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                            selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                            selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                            selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                            selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                            selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                            selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                            selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText()
                                            , selectHotelModelArrayListFilter.get(j).getFacilities(), selectHotelModelArrayListFilter.get(j).getDiff(), selectHotelModelArrayListFilter.get(j).getFlights(), selectHotelModelArrayListFilter.get(j).getArrRout(), selectHotelModelArrayListFilter.get(j).getDepRout(), selectHotelModelArrayListFilter.get(j).getAmount(),selectHotelModelArrayListFilter.get(j).getLocations()));

                                }

                            }


                        }


                    }
                    selectHotelModelArrayListFilter.clear();
                    selectHotelModelArrayListFilter = selectHotelModels;
                }
            }


        } else if (isBestseler) {
            ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
            for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                if (filterHotelTypeModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                        if (filterHotelTypeModels.get(i).getTitle().equals(selectHotelModelArrayListFilter.get(j).getTypeText()) && selectHotelModelArrayListFilter.get(j).isBestSell()) {
                            selectHotelModels.add(new SelectFlightHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                    selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                    selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                    selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                    selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                    selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                    selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                    selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText(),
                                    selectHotelModelArrayListFilter.get(j).getFacilities(), selectHotelModelArrayListFilter.get(j).getDiff(), selectHotelModelArrayListFilter.get(j).getFlights(), selectHotelModelArrayListFilter.get(j).getArrRout(), selectHotelModelArrayListFilter.get(j).getDepRout(), selectHotelModelArrayListFilter.get(j).getAmount(),selectHotelModelArrayListFilter.get(j).getLocations()));

                        }

                    }


                }


            }
            selectHotelModelArrayListFilter.clear();
            selectHotelModelArrayListFilter = selectHotelModels;


        } else if (isOff) {

            ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
            for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                if (filterHotelTypeModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                        if (filterHotelTypeModels.get(i).getTitle().equals(selectHotelModelArrayListFilter.get(j).getTypeText()) && selectHotelModelArrayListFilter.get(j).isOff()) {
                            selectHotelModels.add(new SelectFlightHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                    selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                    selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                    selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                    selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                    selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                    selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                    selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText(),
                                    selectHotelModelArrayListFilter.get(j).getFacilities(), selectHotelModelArrayListFilter.get(j).getDiff(), selectHotelModelArrayListFilter.get(j).getFlights(), selectHotelModelArrayListFilter.get(j).getArrRout(), selectHotelModelArrayListFilter.get(j).getDepRout(), selectHotelModelArrayListFilter.get(j).getAmount(),selectHotelModelArrayListFilter.get(j).getLocations()));

                        }

                    }


                }


            }
            selectHotelModelArrayListFilter.clear();
            selectHotelModelArrayListFilter = selectHotelModels;


        } else {


            boolean isFilter = false;
            ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
            for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                if (filterHotelTypeModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                        if (filterHotelTypeModels.get(i).getTitle().equals(selectHotelModelArrayListFilter.get(j).getTypeText())) {
                            isFilter = true;
                            selectHotelModels.add(new SelectFlightHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                    selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                    selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                    selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                    selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                    selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                    selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                    selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText(),
                                    selectHotelModelArrayListFilter.get(j).getFacilities(), selectHotelModelArrayListFilter.get(j).getDiff(),
                                    selectHotelModelArrayListFilter.get(j).getFlights(), selectHotelModelArrayListFilter.get(j).getArrRout(), selectHotelModelArrayListFilter.get(j).getDepRout(), selectHotelModelArrayListFilter.get(j).getAmount(),selectHotelModelArrayListFilter.get(j).getLocations()));

                        }

                    }


                }

                if (isFilter) {

                    selectHotelModelArrayListFilter.clear();
                    selectHotelModelArrayListFilter = selectHotelModels;
                }


            }


        }


    }

    public void star_filter(FilterModel filterModel, ArrayList<FilterHotelTypeModel> filterHotelTypeModels) {


        if (filterModel.isStar1()) {
            for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.getStar() == 1) {
                    selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                }

            }
            type_location_filter(filterHotelTypeModels, 1, false, false);

        }
        if (filterModel.isStar2()) {
            for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.getStar() == 2) {
                    selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                }

            }
            type_location_filter(filterHotelTypeModels, 2, false, false);


        }
        if (filterModel.isStar3()) {
            for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.getStar() == 3) {
                    selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                }

            }
            type_location_filter(filterHotelTypeModels, 3, false, false);


        }
        if (filterModel.isStar4()) {
            for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.getStar() == 4) {
                    selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                }

            }

            type_location_filter(filterHotelTypeModels, 4, false, false);

        }
        if (filterModel.isStar5()) {
            for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.getStar() == 5) {
                    selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                }

            }

            type_location_filter(filterHotelTypeModels, 5, false, false);

        }

    }

    public void isBestOff_and_isStar(FilterModel filterModel) {
        if (filterModel.isBestOff()) {


            if (filterModel.isStar1()) {
                for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 1 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectFlightHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(),
                                selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(),
                                selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff()
                                , selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                    }


                }
            }
            //===============================================================================
            if (filterModel.isStar2()) {
                for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 2 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectFlightHotelModel(selectHotelModel.getName(),
                                selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(),
                                selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                    }


                }
            }
            //===============================================================================
            if (filterModel.isStar3()) {
                for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 3 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectFlightHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                    }


                }


            }


            //================================================================================
            if (filterModel.isStar4()) {
                for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 4 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectFlightHotelModel(selectHotelModel.getName(),
                                selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(),
                                selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                    }


                }

            }
            //===============================================================================

            if (filterModel.isStar5()) {
                for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 5 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectFlightHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                    }


                }

            }


            //===============================================================================


        }


    }

    public void isBestSell_and_isStar(FilterModel filterModel) {
        if (filterModel.isBestSeler()) {


            if (filterModel.isStar1()) {
                for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 1 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectFlightHotelModel(selectHotelModel.getName(),
                                selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(),
                                selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                    }


                }


            }
            //===============================================================================
            if (filterModel.isStar2()) {
                for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 2 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectFlightHotelModel(selectHotelModel.getName(),
                                selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(),
                                selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                    }


                }


            }
            //===============================================================================
            if (filterModel.isStar3()) {
                for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 3 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectFlightHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                    }


                }


            }


            //================================================================================
            if (filterModel.isStar4()) {
                for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 4 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectFlightHotelModel(selectHotelModel.getName(),
                                selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(),
                                selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                    }


                }

            }
            //===============================================================================

            if (filterModel.isStar5()) {
                for (SelectFlightHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 5 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectFlightHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(),
                                selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getFlights(), selectHotelModel.getArrRout(), selectHotelModel.getDepRout(), selectHotelModel.getAmount(),selectHotelModel.getLocations()));

                    }


                }

            }


            //===============================================================================


        }


    }


    public void facilities_filter(ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels) {

        if (selectHotelModelArrayListFilter.isEmpty()) {

            for (int i = 0; i < filterHotelFacilitiesModels.size(); i++) {
                if (filterHotelFacilitiesModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayList.size(); j++) {

                        for (int k = 0; k < selectHotelModelArrayList.get(j).getFacilities().size(); k++) {

                            if (filterHotelFacilitiesModels.get(i).getTitle().contains(selectHotelModelArrayList.get(j).getFacilities().get(k).Title)) {

                                selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModelArrayList.get(j).getName(),
                                        selectHotelModelArrayList.get(j).getCity(), selectHotelModelArrayList.get(j).getTitle(),
                                        selectHotelModelArrayList.get(j).getBoard(), selectHotelModelArrayList.get(j).getPrice(),
                                        selectHotelModelArrayList.get(j).getImageUrl(), selectHotelModelArrayList.get(j).getLocation(),
                                        selectHotelModelArrayList.get(j).getOldPrice(), selectHotelModelArrayList.get(j).getStar(),
                                        selectHotelModelArrayList.get(j).geteHotelId(), selectHotelModelArrayList.get(j).getResultUniqID(),
                                        selectHotelModelArrayList.get(j).isBestSell(), selectHotelModelArrayList.get(j).isOff(),
                                        selectHotelModelArrayList.get(j).getOff(), selectHotelModelArrayList.get(j).getTypeText(),
                                        selectHotelModelArrayList.get(j).getFacilities(), selectHotelModelArrayList.get(j).getDiff(), selectHotelModelArrayList.get(j).getFlights(),
                                        selectHotelModelArrayList.get(j).getArrRout(), selectHotelModelArrayList.get(j).getDepRout(), selectHotelModelArrayList.get(j).getAmount(),selectHotelModelArrayList.get(j).getLocations()));

                            }
                        }

                    }
                }


            }
            Set<SelectFlightHotelModel> hs = new HashSet<>();
            hs.addAll(selectHotelModelArrayListFilter);
            selectHotelModelArrayListFilter.clear();
            selectHotelModelArrayListFilter.addAll(hs);
            hs.size();


        } else {
            boolean isFilter = false;

            ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();


            for (int i = 0; i < filterHotelFacilitiesModels.size(); i++) {
                if (filterHotelFacilitiesModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayList.size(); j++) {

                        for (int k = 0; k < selectHotelModelArrayList.get(j).getFacilities().size(); k++) {

                            if (filterHotelFacilitiesModels.get(i).getTitle().contains(selectHotelModelArrayList.get(j).getFacilities().get(k).Title)) {
                                isFilter = true;

                                selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModelArrayList.get(j).getName(),
                                        selectHotelModelArrayList.get(j).getCity(), selectHotelModelArrayList.get(j).getTitle(),
                                        selectHotelModelArrayList.get(j).getBoard(), selectHotelModelArrayList.get(j).getPrice(),
                                        selectHotelModelArrayList.get(j).getImageUrl(), selectHotelModelArrayList.get(j).getLocation(),
                                        selectHotelModelArrayList.get(j).getOldPrice(), selectHotelModelArrayList.get(j).getStar(),
                                        selectHotelModelArrayList.get(j).geteHotelId(), selectHotelModelArrayList.get(j).getResultUniqID(),
                                        selectHotelModelArrayList.get(j).isBestSell(), selectHotelModelArrayList.get(j).isOff(),
                                        selectHotelModelArrayList.get(j).getOff(), selectHotelModelArrayList.get(j).getTypeText(),
                                        selectHotelModelArrayList.get(j).getFacilities(), selectHotelModelArrayList.get(j).getDiff(), selectHotelModelArrayList.get(j).getFlights(),
                                        selectHotelModelArrayList.get(j).getArrRout(), selectHotelModelArrayList.get(j).getDepRout(), selectHotelModelArrayList.get(j).getAmount(),selectHotelModelArrayList.get(j).getLocations()));

                            }
                        }


                    }
                }


            }
            Set<SelectFlightHotelModel> hs = new HashSet<>();
            hs.addAll(selectHotelModelArrayListFilter);
            selectHotelModelArrayListFilter.clear();
            selectHotelModelArrayListFilter.addAll(hs);
            hs.size();
            if (isFilter) {

                selectHotelModelArrayListFilter.clear();
                selectHotelModelArrayListFilter = selectHotelModels;
            }


        }


    }
    public void location_filter(ArrayList<FilterHotelTypeModel> filterHotelLocationModels) {


        if (selectHotelModelArrayListFilter.isEmpty()) {

            for (int i = 0; i < filterHotelLocationModels.size(); i++) {
                if (filterHotelLocationModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayList.size(); j++) {
                        if (filterHotelLocationModels.get(i).getTitle().equals(selectHotelModelArrayList.get(j).getLocation())) {

                            selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModelArrayList.get(j).getName(),
                                    selectHotelModelArrayList.get(j).getCity(), selectHotelModelArrayList.get(j).getTitle(),
                                    selectHotelModelArrayList.get(j).getBoard(), selectHotelModelArrayList.get(j).getPrice(),
                                    selectHotelModelArrayList.get(j).getImageUrl(), selectHotelModelArrayList.get(j).getLocation(),
                                    selectHotelModelArrayList.get(j).getOldPrice(), selectHotelModelArrayList.get(j).getStar(),
                                    selectHotelModelArrayList.get(j).geteHotelId(), selectHotelModelArrayList.get(j).getResultUniqID(),
                                    selectHotelModelArrayList.get(j).isBestSell(), selectHotelModelArrayList.get(j).isOff(),
                                    selectHotelModelArrayList.get(j).getOff(), selectHotelModelArrayList.get(j).getTypeText(),
                                    selectHotelModelArrayList.get(j).getFacilities(), selectHotelModelArrayList.get(j).getDiff(), selectHotelModelArrayList.get(j).getFlights(), selectHotelModelArrayList.get(j).getArrRout(), selectHotelModelArrayList.get(j).getDepRout(), selectHotelModelArrayList.get(j).getAmount(),selectHotelModelArrayList.get(j).getLocations()));

                        }

                    }
                }


            }


        } else {
            boolean isFilter = false;


            ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
            for (int i = 0; i < filterHotelLocationModels.size(); i++) {
                if (filterHotelLocationModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                        if (filterHotelLocationModels.get(i).getTitle().equals(selectHotelModelArrayListFilter.get(j).getLocation())) {
                            isFilter = true;
                            selectHotelModels.add(new SelectFlightHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                    selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                    selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                    selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                    selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                    selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                    selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                    selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText(),
                                    selectHotelModelArrayListFilter.get(j).getFacilities(), selectHotelModelArrayListFilter.get(j).getDiff(),
                                    selectHotelModelArrayListFilter.get(j).getFlights(), selectHotelModelArrayListFilter.get(j).getArrRout(), selectHotelModelArrayListFilter.get(j).getDepRout(), selectHotelModelArrayListFilter.get(j).getAmount(), selectHotelModelArrayListFilter.get(j).getLocations()));

                        }

                    }


                }




            }


            if (isFilter) {

                selectHotelModelArrayListFilter.clear();
                selectHotelModelArrayListFilter = selectHotelModels;
            }
        }



    }


    public void price_filter(ArrayList<FilterPriceModel> filterHotelPriceModel) {


        if (selectHotelModelArrayListFilter.isEmpty()) {

            for (int i = 0; i < filterHotelPriceModel.size(); i++) {
                if (filterHotelPriceModel.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayList.size(); j++) {
                        if (filterHotelPriceModel.get(i).getX() == selectHotelModelArrayList.get(j).getDiff()) {

                            selectHotelModelArrayListFilter.add(new SelectFlightHotelModel(selectHotelModelArrayList.get(j).getName(),
                                    selectHotelModelArrayList.get(j).getCity(), selectHotelModelArrayList.get(j).getTitle(),
                                    selectHotelModelArrayList.get(j).getBoard(), selectHotelModelArrayList.get(j).getPrice(),
                                    selectHotelModelArrayList.get(j).getImageUrl(), selectHotelModelArrayList.get(j).getLocation(),
                                    selectHotelModelArrayList.get(j).getOldPrice(), selectHotelModelArrayList.get(j).getStar(),
                                    selectHotelModelArrayList.get(j).geteHotelId(), selectHotelModelArrayList.get(j).getResultUniqID(),
                                    selectHotelModelArrayList.get(j).isBestSell(), selectHotelModelArrayList.get(j).isOff(),
                                    selectHotelModelArrayList.get(j).getOff(), selectHotelModelArrayList.get(j).getTypeText(),
                                    selectHotelModelArrayList.get(j).getFacilities(), selectHotelModelArrayList.get(j).getDiff(), selectHotelModelArrayList.get(j).getFlights(),
                                    selectHotelModelArrayList.get(j).getArrRout(), selectHotelModelArrayList.get(j).getDepRout(), selectHotelModelArrayList.get(j).getAmount(),selectHotelModelArrayList.get(j).getLocations()));

                        }

                    }
                }


            }
        } else {


            boolean isFilter = false;
            ArrayList<SelectFlightHotelModel> selectHotelModels = new ArrayList<>();
            for (int i = 0; i < filterHotelPriceModel.size(); i++) {
                if (filterHotelPriceModel.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                        if (filterHotelPriceModel.get(i).getX() == selectHotelModelArrayList.get(j).getDiff()) {
                            isFilter = true;
                            selectHotelModels.add(new SelectFlightHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                    selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                    selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                    selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                    selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                    selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                    selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                    selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText(),
                                    selectHotelModelArrayListFilter.get(j).getFacilities(),
                                    selectHotelModelArrayListFilter.get(j).getDiff(), selectHotelModelArrayListFilter.get(j).getFlights(),
                                    selectHotelModelArrayListFilter.get(j).getArrRout(), selectHotelModelArrayListFilter.get(j).getDepRout(), selectHotelModelArrayListFilter.get(j).getAmount(),selectHotelModelArrayListFilter.get(j).getLocations()));

                        }

                    }
                }


            }
            if (isFilter) {

                selectHotelModelArrayListFilter.clear();
                selectHotelModelArrayListFilter = selectHotelModels;


            }


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


    private class GetHotelAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this,R.color.hf));
            }
            new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                hotelFlightSearch = new HotelFlightSearch(new HotelAR(new RquestHF("HF", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                        raft, bargasht, Prefs.getString("Value-Hotel-City-Code-HF-Raft", "IST"), rooms, getIntent().getExtras().getString("Rooms"), "fa-IR",
                        Prefs.getString("Value-Hotel-City-Code-HF-Source", "THR"))));


                Gson gson = new Gson();

                Log.e("test", gson.toJson(new HotelAR(new RquestHF("HF", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                        raft, bargasht, Prefs.getString("Value-Hotel-City-Code-HF-Raft", "IST"), rooms, getIntent().getExtras().getString("Rooms"), "fa-IR", Prefs.getString("Value-Hotel-City-Code-HF-Source", "THR")))));
            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this,R.color.colorPrimaryDark));
            }



            selectHotelModelArrayList.clear();
            selectHotelModelArrayListFilter.clear();

            try {
                if (hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.Errors!=null) {
                    elNotFound.setVisibility(View.VISIBLE);
                    tvAlert.setText(hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.Errors.get(0).DetailedMessage);
                    list.setVisibility(View.GONE);
                    llFilter.setVisibility(View.GONE);

                    list.setVisibility(View.GONE);

                }else if (hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Hotels.isEmpty()) {
                    elNotFound.setVisibility(View.VISIBLE);
                    tvAlert.setText("نتیجه ای برای جستجو شما حاصل نشد !");
                    list.setVisibility(View.GONE);
                    llFilter.setVisibility(View.GONE);

                    list.setVisibility(View.GONE);

                }else{

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
                            if (p3 != 0) {
                                isOff = true;

                                off = p3 + "%\nتخفیف";

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
                                hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Locations));

                        flightId = hotelFlightSearch.hotelFlightModelResponse.HotelFlightSearchResult.HotelSearchResult.Flights.FlightID;

                        //  i++;


                    }


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

                }



            } catch (Exception e) {
                llFilter.setVisibility(View.GONE);
                list.setVisibility(View.GONE);
                elNotFound.setVisibility(View.VISIBLE);
                tvAlert.setText("در حال حاضر پاسخگویی به درخواست  شما امکان پذیر نمی باشد ");

                list.setVisibility(View.GONE);
                btnOk.setVisibility(View.VISIBLE);

            }
            //dakheli khareji
            new AsyncCheckFlight().execute();

        }

    }
    private class AsyncCheckFlight extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;
        private ListView listAirPort;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this,R.color.hf));
            }
            new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);


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
            System.out.println("request:"+result);
            new InitUi().Loading(SelectHotelFlightActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(SelectHotelFlightActivity.this,R.color.colorPrimaryDark));
            }
            //this method will be running on UI thread
            System.out.println("result:"+result);
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
                    AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(SelectHotelFlightActivity.this);
                    AlertDialogPassenger.setText(GetError);

                } else {
////////////////////////////////
					/*JSONArray jArray = GetAirportsResult.getJSONArray("Airports");//AirportCode //AirportName//CityName ":

					for (int i = 0; i < jArray.length(); i++) {
						JSONObject json_data = jArray.getJSONObject(i);
*/
                    boolean IsDemostic =GetAirportsResult.getBoolean("IsDomestic");//false khareji true dakheli
                    if(IsDemostic)
                        Prefs.putBoolean("IsDemostic",true);
                    else
                        Prefs.putBoolean("IsDemostic",false);

                    //}




                }

            } catch (JSONException e) {
                AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(SelectHotelFlightActivity.this);
                AlertDialogPassenger.setText("خطا در دریافت اطلاعات از الی گشت ");
            }


        }
    }
    private String OrderToJsonCheckFlight() {
        JSONObject jsone = new JSONObject();
        JSONObject manJson = new JSONObject();


        try {

            manJson.put("UserName", "EligashtMlb");
            manJson.put("Password", "123qwe!@#QWE");
            manJson.put("TermianlId", "Mobile");
            manJson.put("Code", Prefs.getString("Value-Hotel-City-Code-HF-Raft", "IST"));//inja esme forudgah mikhore
            manJson.put("ToCode",Prefs.getString("Value-Hotel-City-Code-HF-Source", "THR"));

            jsone.put("request", manJson);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("request:"+jsone.toString());
        return jsone.toString();
    }

}