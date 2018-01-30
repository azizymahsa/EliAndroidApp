package com.reserv.myapplicationeli.views.activities.hotel.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
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
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.hotel.hotelAvail.HotelAvailApi;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.FilterPriceModel;
import com.reserv.myapplicationeli.models.hotel.adapter.FilterModel;
import com.reserv.myapplicationeli.models.hotel.adapter.SelectHotelModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.HotelAvailRequestModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Request;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Rooms;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Facilities;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.HotelTypes;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Hotels;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.response.Locations;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.tools.datetools.SolarCalendar;
import com.reserv.myapplicationeli.views.activities.main.MainActivity;
import com.reserv.myapplicationeli.views.adapters.hotel.LazyResoultHotelAdapter;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.FilterHotelDialog;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.FilterHotelTypeModel;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.SortDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;


public class SelectHotelActivity extends BaseActivity implements FilterHotelDialog.FilterHotelDialogListenerArray, View.OnClickListener, SortDialog.SortHotelDialogListener {


    private ListView list;
    private LazyResoultHotelAdapter adapter;
    private ArrayList<SelectHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayListFilter = new ArrayList<>();
    private ArrayList<SelectHotelModel> selectHotelModelArrayListFilter1 = new ArrayList<>();
    private ArrayList<FilterModel> filterModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelTypeModel = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels = new ArrayList<>();
    private ArrayList<FilterHotelTypeModel> filterHotelLocationModels = new ArrayList<>();
    private ArrayList<FilterPriceModel> filterHotelPriceModels = new ArrayList<>();
    private HotelAvailApi availApi;
    private List<Rooms> rooms = new ArrayList<>();
    RelativeLayout rlLoading, rlRoot;
    TextView tvAlert, tvTitle, tvDate, tvCount, tvFilterIcon, tvFilter, tvSortIcon, tvSort;
    Window window;
    RelativeLayout elNotFound;


    int maxPrice, minPrice;

    LinearLayout llBottom, llSort, llFilter;
    FancyButton btnOk, btnBack, btnHome;
    ImageView ivLoading;
    boolean isFilter = false;
    FancyButton btnNextDays, btnLastDays;

    String raft, bargasht;
    String raftFa, bargashtFa, searchIn;
    //TextView tvAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);
        //InitUi.Toolbar(this, false, R.color.flight_status, " چهارشنبه 28 اسفند-دوشنبه 5 فروردین ");
        window = getWindow();
        list = findViewById(R.id.lvHoteResult);
        llBottom = findViewById(R.id.llBottom);
        llSort = findViewById(R.id.llSort);
        tvAlert = findViewById(R.id.tvAlert);
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
        btnNextDays = findViewById(R.id.btnNextDays);
        btnLastDays = findViewById(R.id.btnLastDays);
        btnNextDays.setOnClickListener(this);
        btnLastDays.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        notiRecive();


        llBottom.setOnClickListener(this);
        llSort.setOnClickListener(this);
        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, this, this);
        list.setAdapter(adapter);

        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setOnClickListener(this);
        raftFa = getIntent().getExtras().getString("CheckInFa");
        bargashtFa = getIntent().getExtras().getString("CheckOutFa");

        tvDate.setText("از تاریخ: " + raftFa + " تا تاریخ: " + bargashtFa);
        rooms.add(new Rooms(getIntent().getExtras().getInt("Adult"), getIntent().getExtras().getInt("Child")));


        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        Gson gson = new Gson();
        raft = getIntent().getExtras().getString("CheckIn");
        bargasht = getIntent().getExtras().getString("CheckOut");
        new GetHotelAsync().execute();

        Log.e("raft", getIntent().getExtras().getString("CheckIn"));
        Log.e("bargasht", getIntent().getExtras().getString("CheckOut"));
        Log.e("cod", Prefs.getString("Value-Hotel-City-Code", ""));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (selectHotelModelArrayListFilter.isEmpty()) {
                    Intent i = new Intent(SelectHotelActivity.this, DetailHotelActivity.class);
                    i.putExtra("HotelId", selectHotelModelArrayList.get(position).geteHotelId());
                    i.putExtra("ResultUniqID", selectHotelModelArrayList.get(position).getResultUniqID());
                    i.putExtra("CheckIn", getIntent().getExtras().getString("CheckIn"));
                    i.putExtra("CheckOut", getIntent().getExtras().getString("CheckOut"));
                    i.putExtra("type", 2);


                    startActivity(i);
                } else {
                    Intent i = new Intent(SelectHotelActivity.this, DetailHotelActivity.class);
                    i.putExtra("HotelId", selectHotelModelArrayListFilter.get(position).geteHotelId());
                    i.putExtra("ResultUniqID", selectHotelModelArrayListFilter.get(position).getResultUniqID());
                    i.putExtra("CheckIn", getIntent().getExtras().getString("CheckIn"));
                    i.putExtra("CheckOut", getIntent().getExtras().getString("CheckOut"));
                    i.putExtra("type", 2);


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
                new FilterHotelDialog(SelectHotelActivity.this, filterModels, this, filterHotelTypeModel, filterHotelFacilitiesModels, filterHotelPriceModels, searchIn, filterHotelLocationModels);


                break;
            case R.id.llSort:
                new SortDialog(SelectHotelActivity.this, this);


                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnHome:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
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
                      /*  String dayMF=dateShamsi.substring(8, 10);//02
                        String monthMF=dateShamsi.substring(5, 7);//01
                        String yearMF=dateShamsi.substring(0, 4);//1396
*/
                        PersianCalendar persianCalendar = new PersianCalendar();
                        persianCalendar.set(Integer.parseInt(yearMF), Integer.parseInt(monthMF) - 1, Integer.parseInt(dayMF));
                        /////////////////////
                        //   txtDateOnvan.setText(dfm.format(cal.getTime()) + "  -  " + AdateF);
                        ///
                        raftFa = persianCalendar.getPersianLongDate();
                        raft = formatter.format(cal.getTime());

                        if (getIntent().getExtras().getBoolean("Geo")) {

                            tvDate.setText("از تاریخ: " +Utility.dateShowView( raft )+ " تا تاریخ: " + Utility.dateShowView( bargasht ));

                        }else{
                            tvDate.setText("از تاریخ: " + raftFa + " تا تاریخ: " + bargashtFa);


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
                        String[] dateSplite = formatted3.split("/");

                        String dayM = dateSplite[2];
                        String monthM = dateSplite[1];
                        String yearM = dateSplite[0];
                        String dateShamsi = com.reserv.myapplicationeli.models.model.SolarCalendar.calSolarCalendar(Integer.parseInt(yearM), Integer.parseInt(monthM), Integer.parseInt(dayM));
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
                        tvDate.setText("از تاریخ: " + raftFa + " تا تاریخ: " + bargashtFa);
                        new GetHotelAsync().execute();
                    } else {
                        Toast.makeText(getApplicationContext(), "قبل از تاریخ امروز", Toast.LENGTH_SHORT).show();
                    }


                } catch (java.text.ParseException e) {
                    System.out.println("Exception :" + e);
                }


                break;
        }
    }

    @Override
    public void onReturnValue(ArrayList<FilterModel> type, String search, ArrayList<FilterHotelTypeModel> filterHotelTypeModels,
                              ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels,
                              ArrayList<FilterPriceModel> filterHotelPriceModel, ArrayList<FilterHotelTypeModel> filterHotelLocationModels) {

        boolean remove = false;

        this.filterModels = type;
        this.searchIn = search;
        list.setVisibility(View.VISIBLE);
        elNotFound.setVisibility(View.GONE);
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
            //  facilities_filter(filterHotelFacilitiesModels);
            location_filter(filterHotelLocationModels);
            price_filter(filterHotelPriceModel);

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
                    ArrayList<SelectHotelModel> selectHotelModelArrayListFilter3 = new ArrayList<>();

                    if (selectHotelModelArrayListFilter.isEmpty()) {


                        for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                            if (selectHotelModel.getName().toLowerCase().contains(search.toLowerCase())) {
                                selectHotelModelArrayListFilter3.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                        selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                        selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                        selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));
                            }

                            //    }
                /*    }
                        for (Iterator<SelectHotelModel> it = selectHotelModelArrayListFilter3.iterator(); it.hasNext(); ) {
                            if (!it.next().getName().toLowerCase().contains(search.toLowerCase())) {
                                it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
                            }*/
                        }

                        selectHotelModelArrayListFilter.clear();
                        selectHotelModelArrayListFilter = selectHotelModelArrayListFilter3;

                    } else {
                        for (Iterator<SelectHotelModel> it = selectHotelModelArrayListFilter.iterator(); it.hasNext(); ) {
                            if (!it.next().getName().toLowerCase().contains(search.toLowerCase())) {
                                it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
                            }


                        }
                    }


                }
            } else {
                if (search != null) {
                    for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                        if (selectHotelModel.getName().toLowerCase().contains(search.toLowerCase())) {
                            selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                    selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                    selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                    selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));
                        }

                    }
                }

            }


            if (filterModel.isRemove()) {
                remove = true;
                tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
                tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));

                adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this);
                tvCount.setText("(" + selectHotelModelArrayList.size() + "مورد یافت شد" + ")");
                adapter.notifyDataSetChanged();
                selectHotelModelArrayListFilter.clear();

            }


        }

        if (selectHotelModelArrayListFilter.isEmpty()) {
            if (!remove) {
                //   Toast.makeText(this, "موردی یافت نشد", Toast.LENGTH_SHORT).show();
                elNotFound.setVisibility(View.VISIBLE);
                tvAlert.setText("هیچ موردی یافت نشد");
                list.setVisibility(View.GONE);
                btnOk.setVisibility(View.GONE);
                tvCount.setText("(" + 0 + "مورد یافت شد" + ")");

            } else {

            }

            isFilter = false;
            ///Toast.makeText(this, "موردی یافت نشد", Toast.LENGTH_SHORT).show();
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));

            adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList, SelectHotelActivity.this, SelectHotelActivity.this);
            tvCount.setText("(" + selectHotelModelArrayList.size() + "مورد یافت شد" + ")");


        } else {
            isFilter = true;
            adapter = new LazyResoultHotelAdapter(selectHotelModelArrayListFilter, SelectHotelActivity.this, SelectHotelActivity.this);
            tvCount.setText("(" + selectHotelModelArrayListFilter.size() + "مورد یافت شد" + ")");
            tvFilter.setTextColor(ContextCompat.getColor(this, R.color.red));
            tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.red));
            if (selectHotelModelArrayListFilter.size() == selectHotelModelArrayList.size()) {

                tvFilter.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
                tvFilterIcon.setTextColor(ContextCompat.getColor(this, R.color.text_color_4d));
            }

        }
        list.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }

    public void top_filter(FilterModel filterModel, ArrayList<FilterHotelTypeModel> filterHotelTypeModels) {
        if (filterModel.isBestSeler()) {
            for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.isBestSell()) {
                    selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(),
                            selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                            selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                            selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));
                }


            }
            type_location_filter(filterHotelTypeModels, 0, false, true);

        }

        if (filterModel.isBestOff()) {
            for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.isOff()) {
                    selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(),
                            selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                            selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                            selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));


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

                                selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModelArrayList.get(j).getName(),
                                        selectHotelModelArrayList.get(j).getCity(), selectHotelModelArrayList.get(j).getTitle(),
                                        selectHotelModelArrayList.get(j).getBoard(), selectHotelModelArrayList.get(j).getPrice(),
                                        selectHotelModelArrayList.get(j).getImageUrl(), selectHotelModelArrayList.get(j).getLocation(),
                                        selectHotelModelArrayList.get(j).getOldPrice(), selectHotelModelArrayList.get(j).getStar(),
                                        selectHotelModelArrayList.get(j).geteHotelId(), selectHotelModelArrayList.get(j).getResultUniqID(),
                                        selectHotelModelArrayList.get(j).isBestSell(), selectHotelModelArrayList.get(j).isOff(),
                                        selectHotelModelArrayList.get(j).getOff(), selectHotelModelArrayList.get(j).getTypeText(),
                                        selectHotelModelArrayList.get(j).getFacilities(), selectHotelModelArrayList.get(j).getDiff(), selectHotelModelArrayList.get(j).getOfferId(), selectHotelModelArrayList.get(j).getLocations()));

                            }

                        }
                    }


                }
            }
        } else if (stars) {


            for (FilterModel filterModel : filterModels) {
                if ((filterModel.isStar1() || filterModel.isStar2() || filterModel.isStar3() || filterModel.isStar4()
                        || filterModel.isStar5())) {


                    ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
                    for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                        if (filterHotelTypeModels.get(i).isCheck()) {
                            for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                                if (filterHotelTypeModels.get(i).getTitle().equals(selectHotelModelArrayListFilter.get(j).getTypeText()) && selectHotelModelArrayListFilter.get(j).getStar() == star) {
                                    isFilter = true;
                                    selectHotelModels.add(new SelectHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                            selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                            selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                            selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                            selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                            selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                            selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                            selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText()
                                            , selectHotelModelArrayListFilter.get(j).getFacilities(), selectHotelModelArrayListFilter.get(j).getDiff(), selectHotelModelArrayListFilter.get(j).getOfferId(), selectHotelModelArrayList.get(j).getLocations()));

                                }

                            }


                        }


                    }
                    selectHotelModelArrayListFilter.clear();
                    selectHotelModelArrayListFilter = selectHotelModels;
                }
            }


        } else if (isBestseler) {
            ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
            for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                if (filterHotelTypeModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                        if (filterHotelTypeModels.get(i).getTitle().equals(selectHotelModelArrayListFilter.get(j).getTypeText()) && selectHotelModelArrayListFilter.get(j).isBestSell()) {
                            selectHotelModels.add(new SelectHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                    selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                    selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                    selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                    selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                    selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                    selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                    selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText(),
                                    selectHotelModelArrayListFilter.get(j).getFacilities(), selectHotelModelArrayListFilter.get(j).getDiff(), selectHotelModelArrayListFilter.get(j).getOfferId(), selectHotelModelArrayList.get(j).getLocations()));

                        }

                    }


                }


            }
            selectHotelModelArrayListFilter.clear();
            selectHotelModelArrayListFilter = selectHotelModels;


        } else if (isOff) {

            ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
            for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                if (filterHotelTypeModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                        if (filterHotelTypeModels.get(i).getTitle().equals(selectHotelModelArrayListFilter.get(j).getTypeText()) && selectHotelModelArrayListFilter.get(j).isOff()) {
                            selectHotelModels.add(new SelectHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                    selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                    selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                    selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                    selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                    selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                    selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                    selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText(),
                                    selectHotelModelArrayListFilter.get(j).getFacilities(), selectHotelModelArrayListFilter.get(j).getDiff(), selectHotelModelArrayListFilter.get(j).getOfferId(), selectHotelModelArrayList.get(j).getLocations()));

                        }

                    }


                }


            }
            selectHotelModelArrayListFilter.clear();
            selectHotelModelArrayListFilter = selectHotelModels;


        } else {


            boolean isFilter = false;
            ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
            for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                if (filterHotelTypeModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                        if (filterHotelTypeModels.get(i).getTitle().equals(selectHotelModelArrayListFilter.get(j).getTypeText())) {
                            isFilter = true;
                            selectHotelModels.add(new SelectHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                    selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                    selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                    selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                    selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                    selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                    selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                    selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText(),
                                    selectHotelModelArrayListFilter.get(j).getFacilities(), selectHotelModelArrayListFilter.get(j).getDiff(), selectHotelModelArrayListFilter.get(j).getOfferId(), selectHotelModelArrayList.get(j).getLocations()));

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
            for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.getStar() == 1) {
                    selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                }

            }
            type_location_filter(filterHotelTypeModels, 1, false, false);

        }
        if (filterModel.isStar2()) {
            for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.getStar() == 2) {
                    selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                }

            }
            type_location_filter(filterHotelTypeModels, 2, false, false);


        }
        if (filterModel.isStar3()) {
            for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.getStar() == 3) {
                    selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                }

            }
            type_location_filter(filterHotelTypeModels, 3, false, false);


        }
        if (filterModel.isStar4()) {
            for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.getStar() == 4) {
                    selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                }

            }

            type_location_filter(filterHotelTypeModels, 4, false, false);

        }
        if (filterModel.isStar5()) {
            for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                if (selectHotelModel.getStar() == 5) {
                    selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                            selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                            selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                            selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                }

            }

            type_location_filter(filterHotelTypeModels, 5, false, false);

        }

    }

    public void isBestOff_and_isStar(FilterModel filterModel) {
        if (filterModel.isBestOff()) {


            if (filterModel.isStar1()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 1 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(),
                                selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(),
                                selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff()
                                , selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                    }


                }
            }
            //===============================================================================
            if (filterModel.isStar2()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 2 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(),
                                selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(),
                                selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                    }


                }
            }
            //===============================================================================
            if (filterModel.isStar3()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 3 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                    }


                }


            }


            //================================================================================
            if (filterModel.isStar4()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 4 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(),
                                selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(),
                                selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                    }


                }

            }
            //===============================================================================

            if (filterModel.isStar5()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 5 && selectHotelModel.isOff()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                    }


                }

            }


            //===============================================================================


        }


    }

    public void isBestSell_and_isStar(FilterModel filterModel) {
        if (filterModel.isBestSeler()) {


            if (filterModel.isStar1()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 1 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(),
                                selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(),
                                selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                    }


                }


            }
            //===============================================================================
            if (filterModel.isStar2()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 2 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(),
                                selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(),
                                selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                    }


                }


            }
            //===============================================================================
            if (filterModel.isStar3()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 3 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(), selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                    }


                }


            }


            //================================================================================
            if (filterModel.isStar4()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 4 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(),
                                selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(),
                                selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(),
                                selectHotelModel.getTypeText(), selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

                    }


                }

            }
            //===============================================================================

            if (filterModel.isStar5()) {
                for (SelectHotelModel selectHotelModel : selectHotelModelArrayList) {

                    if (selectHotelModel.getStar() == 5 && selectHotelModel.isBestSell()) {


                        selectHotelModelArrayListFilter1.add(new SelectHotelModel(selectHotelModel.getName(), selectHotelModel.getCity(), selectHotelModel.getTitle(),
                                selectHotelModel.getBoard(), selectHotelModel.getPrice(), selectHotelModel.getImageUrl(), selectHotelModel.getLocation(),
                                selectHotelModel.getOldPrice(), selectHotelModel.getStar(),
                                selectHotelModel.geteHotelId(), selectHotelModel.getResultUniqID(),
                                selectHotelModel.isBestSell(), selectHotelModel.isOff(), selectHotelModel.getOff(), selectHotelModel.getTypeText(),
                                selectHotelModel.getFacilities(), selectHotelModel.getDiff(), selectHotelModel.getOfferId(), selectHotelModel.getLocations()));

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

                                selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModelArrayList.get(j).getName(),
                                        selectHotelModelArrayList.get(j).getCity(), selectHotelModelArrayList.get(j).getTitle(),
                                        selectHotelModelArrayList.get(j).getBoard(), selectHotelModelArrayList.get(j).getPrice(),
                                        selectHotelModelArrayList.get(j).getImageUrl(), selectHotelModelArrayList.get(j).getLocation(),
                                        selectHotelModelArrayList.get(j).getOldPrice(), selectHotelModelArrayList.get(j).getStar(),
                                        selectHotelModelArrayList.get(j).geteHotelId(), selectHotelModelArrayList.get(j).getResultUniqID(),
                                        selectHotelModelArrayList.get(j).isBestSell(), selectHotelModelArrayList.get(j).isOff(),
                                        selectHotelModelArrayList.get(j).getOff(), selectHotelModelArrayList.get(j).getTypeText(),
                                        selectHotelModelArrayList.get(j).getFacilities(), selectHotelModelArrayList.get(j).getDiff(), selectHotelModelArrayList.get(j).getOfferId(), selectHotelModelArrayList.get(j).getLocations()));

                            }
                        }

                    }
                }


            }


        } else {
            boolean isFilter = false;

            ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();


            for (int i = 0; i < filterHotelFacilitiesModels.size(); i++) {
                if (filterHotelFacilitiesModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayList.size(); j++) {

                        for (int k = 0; k < selectHotelModelArrayList.get(j).getFacilities().size(); k++) {

                            if (filterHotelFacilitiesModels.get(i).getTitle().contains(selectHotelModelArrayList.get(j).getFacilities().get(k).Title)) {
                                isFilter = true;

                                selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModelArrayList.get(j).getName(),
                                        selectHotelModelArrayList.get(j).getCity(), selectHotelModelArrayList.get(j).getTitle(),
                                        selectHotelModelArrayList.get(j).getBoard(), selectHotelModelArrayList.get(j).getPrice(),
                                        selectHotelModelArrayList.get(j).getImageUrl(), selectHotelModelArrayList.get(j).getLocation(),
                                        selectHotelModelArrayList.get(j).getOldPrice(), selectHotelModelArrayList.get(j).getStar(),
                                        selectHotelModelArrayList.get(j).geteHotelId(), selectHotelModelArrayList.get(j).getResultUniqID(),
                                        selectHotelModelArrayList.get(j).isBestSell(), selectHotelModelArrayList.get(j).isOff(),
                                        selectHotelModelArrayList.get(j).getOff(), selectHotelModelArrayList.get(j).getTypeText(),
                                        selectHotelModelArrayList.get(j).getFacilities(), selectHotelModelArrayList.get(j).getDiff(), selectHotelModelArrayList.get(j).getOfferId(), selectHotelModelArrayList.get(j).getLocations()));

                            }
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

    public void location_filter(ArrayList<FilterHotelTypeModel> filterHotelLocationModels) {

        if (selectHotelModelArrayListFilter.isEmpty()) {

            for (int i = 0; i < filterHotelLocationModels.size(); i++) {
                if (filterHotelLocationModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayList.size(); j++) {
                        if (filterHotelLocationModels.get(i).getTitle().equals(selectHotelModelArrayList.get(j).getLocation())) {

                            selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModelArrayList.get(j).getName(),
                                    selectHotelModelArrayList.get(j).getCity(), selectHotelModelArrayList.get(j).getTitle(),
                                    selectHotelModelArrayList.get(j).getBoard(), selectHotelModelArrayList.get(j).getPrice(),
                                    selectHotelModelArrayList.get(j).getImageUrl(), selectHotelModelArrayList.get(j).getLocation(),
                                    selectHotelModelArrayList.get(j).getOldPrice(), selectHotelModelArrayList.get(j).getStar(),
                                    selectHotelModelArrayList.get(j).geteHotelId(), selectHotelModelArrayList.get(j).getResultUniqID(),
                                    selectHotelModelArrayList.get(j).isBestSell(), selectHotelModelArrayList.get(j).isOff(),
                                    selectHotelModelArrayList.get(j).getOff(), selectHotelModelArrayList.get(j).getTypeText(),
                                    selectHotelModelArrayList.get(j).getFacilities(), selectHotelModelArrayList.get(j).getDiff(), selectHotelModelArrayList.get(j).getOfferId(), selectHotelModelArrayList.get(j).getLocations()));

                        }

                    }
                }


            }

        } else {
            boolean isFilter = false;
            ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
            for (int i = 0; i < filterHotelLocationModels.size(); i++) {
                if (filterHotelLocationModels.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                        if (filterHotelLocationModels.get(i).getTitle().equals(selectHotelModelArrayListFilter.get(j).getLocation())) {
                            isFilter = true;
                            selectHotelModels.add(new SelectHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                    selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                    selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                    selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                    selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                    selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                    selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                    selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText(),
                                    selectHotelModelArrayListFilter.get(j).getFacilities(), selectHotelModelArrayListFilter.get(j).getDiff(), selectHotelModelArrayListFilter.get(j).getOfferId(), selectHotelModelArrayList.get(j).getLocations()));

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

    public void price_filter(ArrayList<FilterPriceModel> filterHotelPriceModel) {


        if (selectHotelModelArrayListFilter.isEmpty()) {

            for (int i = 0; i < filterHotelPriceModel.size(); i++) {
                if (filterHotelPriceModel.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayList.size(); j++) {
                        if (filterHotelPriceModel.get(i).getX() == selectHotelModelArrayList.get(j).getDiff()) {

                            selectHotelModelArrayListFilter.add(new SelectHotelModel(selectHotelModelArrayList.get(j).getName(),
                                    selectHotelModelArrayList.get(j).getCity(), selectHotelModelArrayList.get(j).getTitle(),
                                    selectHotelModelArrayList.get(j).getBoard(), selectHotelModelArrayList.get(j).getPrice(),
                                    selectHotelModelArrayList.get(j).getImageUrl(), selectHotelModelArrayList.get(j).getLocation(),
                                    selectHotelModelArrayList.get(j).getOldPrice(), selectHotelModelArrayList.get(j).getStar(),
                                    selectHotelModelArrayList.get(j).geteHotelId(), selectHotelModelArrayList.get(j).getResultUniqID(),
                                    selectHotelModelArrayList.get(j).isBestSell(), selectHotelModelArrayList.get(j).isOff(),
                                    selectHotelModelArrayList.get(j).getOff(), selectHotelModelArrayList.get(j).getTypeText(),
                                    selectHotelModelArrayList.get(j).getFacilities(), selectHotelModelArrayList.get(j).getDiff(), selectHotelModelArrayList.get(j).getOfferId(), selectHotelModelArrayList.get(j).getLocations()));

                        }

                    }
                }


            }
        } else {


            boolean isFilter = false;
            ArrayList<SelectHotelModel> selectHotelModels = new ArrayList<>();
            for (int i = 0; i < filterHotelPriceModel.size(); i++) {
                if (filterHotelPriceModel.get(i).isCheck()) {
                    for (int j = 0; j < selectHotelModelArrayListFilter.size(); j++) {
                        if (filterHotelPriceModel.get(i).getX() == selectHotelModelArrayList.get(j).getDiff()) {
                            isFilter = true;
                            selectHotelModels.add(new SelectHotelModel(selectHotelModelArrayListFilter.get(j).getName(),
                                    selectHotelModelArrayListFilter.get(j).getCity(), selectHotelModelArrayListFilter.get(j).getTitle(),
                                    selectHotelModelArrayListFilter.get(j).getBoard(), selectHotelModelArrayListFilter.get(j).getPrice(),
                                    selectHotelModelArrayListFilter.get(j).getImageUrl(), selectHotelModelArrayListFilter.get(j).getLocation(),
                                    selectHotelModelArrayListFilter.get(j).getOldPrice(), selectHotelModelArrayListFilter.get(j).getStar(),
                                    selectHotelModelArrayListFilter.get(j).geteHotelId(), selectHotelModelArrayListFilter.get(j).getResultUniqID(),
                                    selectHotelModelArrayListFilter.get(j).isBestSell(), selectHotelModelArrayListFilter.get(j).isOff(),
                                    selectHotelModelArrayListFilter.get(j).getOff(), selectHotelModelArrayListFilter.get(j).getTypeText(),
                                    selectHotelModelArrayListFilter.get(j).getFacilities(), selectHotelModelArrayListFilter.get(j).getDiff()
                                    , selectHotelModelArrayListFilter.get(j).getOfferId(), selectHotelModelArrayListFilter.get(j).getLocations()));

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


    private class GetHotelAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            window.setStatusBarColor(getColor(R.color.status_loading));

            new InitUi().Loading(SelectHotelActivity.this, rlLoading, rlRoot, true, R.drawable.hotel_loading);

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                availApi = new HotelAvailApi(new HotelAvailRequestModel(new Request("H", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                        raft, bargasht, Prefs.getString("Value-Hotel-City-Code", "c25972"), "DXB", rooms, getIntent().getExtras().getString("Rooms"), "fa-IR", "")));


                Gson gson = new Gson();

                Log.e("hoteltest", gson.toJson(new HotelAvailRequestModel(new Request("H", new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"),
                        raft, bargasht, Prefs.getString("Value-Hotel-City-Code", "c25972"), "DXB", rooms, getIntent().getExtras().getString("Rooms"), "fa-IR", ""))));
            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            new InitUi().Loading(SelectHotelActivity.this, rlLoading, rlRoot, false, R.drawable.hotel_loading);
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));

            selectHotelModelArrayList.clear();
            selectHotelModelArrayListFilter.clear();
            try {
                if (availApi.hotelAvailModelResponse.HotelAvailResult.Errors != null) {
                    elNotFound.setVisibility(View.VISIBLE);
                    tvAlert.setText(availApi.hotelAvailModelResponse.HotelAvailResult.Errors.get(0).Message);
                    list.setVisibility(View.GONE);
                    llFilter.setVisibility(View.GONE);
                    list.setVisibility(View.GONE);
                    btnOk.setVisibility(View.VISIBLE);


                } else if (availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.Hotels.isEmpty()) {
                    elNotFound.setVisibility(View.VISIBLE);
                    tvAlert.setText("نتیجه ای برای جستجو شما حاصل نشد !");
                    list.setVisibility(View.GONE);
                    llFilter.setVisibility(View.GONE);
                    list.setVisibility(View.GONE);
                    btnOk.setVisibility(View.VISIBLE);


                } else {


                    maxPrice = availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.MaxPrice;
                    minPrice = availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.MinPrice;
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

                    int i = 0;
                    for (Hotels hotels : availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.Hotels) {
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
                                if (p3 > 0) {
                                    // negative
                                    isOff = true;

                                    off = p3 + "%\nتخفیف";

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


                        selectHotelModelArrayList.add(new SelectHotelModel(hotels.Name, hotels.City, hotels.Availability.RoomLists.get(i).Title,
                                hotels.Availability.RoomLists.get(i).Board, hotels.Availability.RoomLists.get(i).Price, hotels.MainImage, hotels.Location,
                                hotels.Availability.RoomLists.get(i).OldPrice, hotels.StarRating,
                                hotels.Availability.RoomLists.get(i).EHotelId,
                                availApi.hotelAvailModelResponse.HotelAvailResult.ResultUniqID, hotels.BestSell, isOff,
                                off, hotels.TypeText, availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.Facilities, xiff, hotels.Availability.RoomLists.get(i).OfferId,
                                availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.Locations));


                        //  i++;


                    }


                    for (Facilities facilities : availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.Facilities) {

                        filterHotelFacilitiesModels.add(new FilterHotelTypeModel(facilities.Title, false));
                    }

                    for (HotelTypes hotelTypes : availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.HotelTypes) {


                        filterHotelTypeModel.add(new FilterHotelTypeModel(hotelTypes.Title, false));


                    }
                    for (Locations locations : availApi.hotelAvailModelResponse.HotelAvailResult.HotelSearchResult.Locations) {


                        filterHotelLocationModels.add(new FilterHotelTypeModel(locations.Title, false));


                    }
                    tvTitle.setText(Prefs.getString("Value-Hotel-City-Fa", "استانبول"));
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
                    adapter.notifyDataSetChanged();

                }


            } catch (Exception e) {
                list.setVisibility(View.GONE);
                elNotFound.setVisibility(View.VISIBLE);
                tvAlert.setText("در حال حاضر پاسخگویی به درخواست  شما امکان پذیر نمی باشد ");
                llFilter.setVisibility(View.GONE);
                btnOk.setVisibility(View.VISIBLE);


                list.setVisibility(View.GONE);
            }


        }

    }

}
