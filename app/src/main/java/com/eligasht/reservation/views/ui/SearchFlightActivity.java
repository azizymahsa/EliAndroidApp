package com.eligasht.reservation.views.ui;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
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

import com.eligasht.reservation.models.model.ModelCheckBox;
import com.eligasht.reservation.models.model.PinModelDetail;
import com.eligasht.reservation.models.model.PinModelHeader;
import com.eligasht.reservation.models.model.SearchParvazModelExp;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.adapters.ExpandableListAdapter;
import com.eligasht.reservation.views.adapters.SearchParvazPinAdapter;
import com.eligasht.reservation.views.adapters.weather.WeatherAdapter;
import com.eligasht.reservation.views.components.Header;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.ui.OBGFlight.Flight;
import com.eligasht.reservation.views.ui.OBGFlight.FlightSegment;
import com.eligasht.reservation.views.ui.OBGFlight.FlightSegmentFalse;
import com.eligasht.reservation.views.ui.OBGFlight.FlightSegmentTrue;
import com.eligasht.reservation.views.ui.OBGFlight.PriceField;
import com.eligasht.reservation.views.ui.dialog.flight.FilterFlightDialogNew;
import com.eligasht.reservation.views.ui.dialog.flight.FilterModelّFlight;
import com.eligasht.reservation.views.ui.dialog.flight.SortFlightDialog;

import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.newModel.hotelFlight.changeFlight.request.RequestChangeFlight;

import com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.ResponseChangeFlight;

import com.eligasht.service.model.newModel.flight.searchFlight.response.ResponseSearchFlight;


import com.eligasht.service.model.weather.response.WeatherApi;
import com.google.gson.Gson;
import com.eligasht.reservation.tools.Prefs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import mehdi.sakout.fancybuttons.FancyButton;
public class SearchFlightActivity extends BaseActivity implements SortFlightDialog.SortFlightDialogListener, FilterFlightDialogNew.FilterFlightDialogListenerArray, Header.onSearchTextChangedListener, OnItemClickListener, OnClickListener, OnItemSelectedListener, OnServiceStatus<com.eligasht.service.model.newModel.flight.searchFlight.response.ResponseSearchFlight> {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    public static boolean FlagRemove;
    public static int COUNT_B;
    public static int COUNT_K;
    public static int COUNT_N;
    public static String globalResultUniqID;
    public static RecyclerView recyclerViewFlight;
    public ArrayList<ModelCheckBox> filterAirlines = new ArrayList<>();
    public ArrayList<String> airlineConstraint = new ArrayList<>();
    public TextView txtCityBargasht, txtCityRaft, txtCityBargashtt;
    public FancyButton txtBack, btnHome;
    public List<ParentItemExpandingPlan> dataExpandingList;
    public List<ParentItemExpandingPlan> dataExpandingListFilter = new ArrayList<>();
    public List<ParentItemExpandingPlan> dataExpandingListFilter2 = new ArrayList<>();
    public com.eligasht.reservation.tools.ExpandableListViewE expListViewExpanding;
    public TextView lblMoratabSazi,txtWeatherCity;
    public TextView txtRuzeBad, txtRuzeGhabl, iconFilter, txtFilter, txtNoResult;
    public FancyButton btnLastDays, btnNextDays;
    public String RaftF="";
    public String BargashtF="";
    public String Raft="";
    public String Bargasht="";
    public TextView txtDateOnvan, txtDateOnvanB, tvLoading, txticon, tvChangeFlight, txticonDate;
    public RelativeLayout linear_expand;
    public SearchParvazPinAdapter searchParvazPinAdapter;
    SlidingDrawer slidingDrawer;
    //sort
    boolean besetSeler = false;
    boolean bestOff = false;
    boolean remove = false;
    List<Flight> flightsList = new ArrayList<Flight>();
    List<Flight> flightsListFilter = new ArrayList<Flight>();
    List<FlightSegmentTrue> SegmentListtrueAkhari = new ArrayList<FlightSegmentTrue>();
    List<FlightSegment> SegmentList = new ArrayList<FlightSegment>();
    List<FlightSegmentTrue> SegmentListtrueAvali = new ArrayList<FlightSegmentTrue>();
    List<FlightSegmentFalse> SegmentListFalseAvali = new ArrayList<FlightSegmentFalse>();
    List<FlightSegmentFalse> SegmentListFalseAkhari = new ArrayList<FlightSegmentFalse>();
    RelativeLayout rlLoading, rlRoot;
    FancyButton btn_no_Result;
    List<String> listDataHeaderExpanding;
    HashMap<String, HashMap<String, ItemExpandingPlan>> listDataChildExpanding;
    Window window;
    boolean isChangeFlight;
    String searchKey;
    String searchKeyConfirm;
    String FlightId;
    LinearLayout llNextLastDays, llDateToolbar, llBottom, llSort;
    private ArrayList<FilterModelّFlight> filterModels = new ArrayList<>();
    private ExpandableListAdapter listAdapterExpanding;
    private  RecyclerView recyclerViewHotel;
    String flagWay="";
    private static String GlobalCurrencyCode="";
    private String flightClass="Economy";

    public static void updateAdapterPin(List<PinModelDetail> pinModelDetails, List<PinModelHeader> pinModelHeaders, Context activity) {
        // TODO Auto-generated method stub

        recyclerViewFlight.addItemDecoration(new DividerItemDecoration(activity, 1));
        recyclerViewFlight.setLayoutManager(new LinearLayoutManager(activity));

        if (!pinModelDetails.isEmpty()) {
            recyclerViewFlight.setVisibility(View.VISIBLE);
            recyclerViewFlight.setAdapter(new SearchParvazPinAdapter(pinModelDetails, pinModelHeaders));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_parvaz);
        Utility.setAnimLoading(this);

        slidingDrawer = findViewById(R.id.slidingDrawer);

        recyclerViewHotel = findViewById(R.id.rvWeather);
        txtWeatherCity=findViewById(R.id.weatherCity);
        window = getWindow();
        linear_expand = findViewById(R.id.linear_expand);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getBoolean("BACK_HOME") == true) {
                finish();
            }
        }
        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        iconFilter = findViewById(R.id.iconFilter);
        llBottom = findViewById(R.id.llBottom);
        llBottom.setOnClickListener(this);
        llSort = findViewById(R.id.llSort);
        llSort.setOnClickListener(this);
        txtBack = findViewById(R.id.txtBack);
        txtBack.setOnClickListener(this);
        txtBack.setCustomTextFont("fonts/icomoon.ttf");
        txtBack.setText(getString(R.string.search_back_right));
        btnHome = findViewById(R.id.btnHome);
        txticon = findViewById(R.id.txticon);
        btnHome.setOnClickListener(this);
        txtNoResult = findViewById(R.id.txtNoResult);
        txtNoResult.setOnClickListener(this);
        RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
        txtNoResult.setText(R.string.NoResult);
        linear_no_result.setVisibility(View.GONE);
        txtFilter = findViewById(R.id.txtFilter);
        txtRuzeBad = findViewById(R.id.txtRuzeBad);
        txtRuzeBad.setOnClickListener(this);
        btnLastDays = findViewById(R.id.btnLastDays);
        btnLastDays.setOnClickListener(this);
        btnNextDays = findViewById(R.id.btnNextDays);
        btnNextDays.setOnClickListener(this);
        btn_no_Result = findViewById(R.id.btn_no_Result);
        btn_no_Result.setCustomTextFont(getResources().getString(R.string.iran_sans_normal_ttf));

        llNextLastDays = findViewById(R.id.llNextLastDays);
        btn_no_Result.setOnClickListener(this);
        txtRuzeGhabl = findViewById(R.id.txtRuzeGhabl);
        txtRuzeGhabl.setOnClickListener(this);
        lblMoratabSazi = findViewById(R.id.lblMoratabSazi);
        txtCityRaft = findViewById(R.id.txtCityRaft);
        llDateToolbar = findViewById(R.id.llDateToolbar);
        txtCityRaft.setOnClickListener(this);
        txtCityBargashtt = findViewById(R.id.txtCityBargashtt);
        tvLoading = findViewById(R.id.tvLoading);
        tvChangeFlight = findViewById(R.id.tvChangeFlight);
        Utility.loadingText(tvLoading, Prefs.getString("F", ""));
        txtCityBargashtt.setOnClickListener(this);
        txtCityBargasht = findViewById(R.id.txtCityBargasht);
        txtCityBargasht.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String maghsadf = extras.getString("Value-Maghsad-City");
            String mabdaf = extras.getString("Value-Mabda-City");
            SingletonAnalysis.getInstance().logTransfer(ServiceType.FLIGHT,mabdaf,maghsadf);
            txtCityRaft.setText(mabdaf);//sdfsdf
            txtCityBargashtt.setText(maghsadf);//sdfsdf
            txtCityBargasht.setText(maghsadf + "");

            if (SingletonDate.getInstance().getStartDate() != null) {
                RaftF = SingletonDate.getInstance().getStartDate().getDescription();
                Raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                try {
                    BargashtF = SingletonDate.getInstance().getEndDate().getDescription();
                    Bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
                } catch (Exception e) {
                }
            } else {
                RaftF = extras.getString("Value-DepartureDate-format");
                BargashtF = extras.getString("Value-ArrivalDate-format");
                Raft = extras.getString("Value-DepartureDate");
                Bargasht = extras.getString("Value-ArrivalDate");
            }
            txtDateOnvan = findViewById(R.id.txtDateOnvan);
            txtDateOnvanB = findViewById(R.id.txtDateOnvanB);
            txticonDate = findViewById(R.id.txticonDate);
            try {
                String flagWay = extras.getString("Value-Flag-Two");
                if (flagWay.trim().equals("1")) {
                    if (getIntent().getExtras().getBoolean("Geo")) {
                        txticonDate.setText(RaftF);
                    } else {
                        txticonDate.setText(RaftF);
                    }
                    Bargasht = Raft;
                } else {
                    if (getIntent().getExtras().getBoolean("Geo")) {
                        txtDateOnvan.setText(BargashtF);
                        txtDateOnvanB.setText(null);
                        txtDateOnvanB.setText(RaftF);
                        txtWeatherCity.setText(maghsadf+" "+getString(R.string.weather));

                    } else {
                        txtDateOnvan.setText(BargashtF);
                        txtDateOnvanB.setText(null);
                        txtDateOnvanB.setText(RaftF);
                        txtWeatherCity.setText(getString(R.string.weather)+" "+maghsadf);
                    }
                }
                System.out.println("txtCityBargasht" + maghsadf + "txtCityRaft" + mabdaf);
            } catch (Exception e) {
            }
        }
        flightClass=getIntent().getExtras().getString("Value_flightClass");
        if (extras.getBoolean("isChangeFlight", false)) {
            FlightId = getIntent().getExtras().getString("FlightId");
            searchKey = getIntent().getExtras().getString("SearchKey");
            isChangeFlight = true;
            txticon.setVisibility(View.GONE);
            txtCityRaft.setVisibility(View.GONE);
            txtDateOnvan.setVisibility(View.GONE);
            llNextLastDays.setVisibility(View.GONE);
            txtCityBargashtt.setVisibility(View.GONE);
            llDateToolbar.setVisibility(View.GONE);
            tvChangeFlight.setVisibility(View.VISIBLE);
            tvChangeFlight.setText(R.string.ChangeFlight);
        } else {
            isChangeFlight = false;
            FlightId = "";
            searchKey = "";
        }
        //expandin list
        // get the listview
        expListViewExpanding = findViewById(R.id.lvExp);
        // preparing list data
        expandingListData(true);
        Log.e("dataExpandingList: ",dataExpandingList.size()+"" );
        listAdapterExpanding = new ExpandableListAdapter(SearchFlightActivity.this, dataExpandingList, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding,searchKeyConfirm);
        // setting list adapter
        expListViewExpanding.setAdapter(listAdapterExpanding);
        // Listview Group click listener
        expListViewExpanding.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,int groupPosition, long id) {

                return false;
            }
        });
        // Listview Group expanded listener
        expListViewExpanding.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.d("TAG", "onGroupExpand: ");
            }
        });
        // Listview Group collasped listener
        expListViewExpanding.setOnGroupCollapseListener(new OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.d("TAG", "onGroupCollapse: ");
            }
        });
        // Listview on child click listener
        expListViewExpanding.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Log.d("TAG", "onChildClick: ");

                return false;
            }
        });
///////////////////Pin
        Utility.init_floating_flight(expListViewExpanding, this);
        //for flight==================================================================================
        recyclerViewFlight = findViewById(R.id.recyclerViewPassenger);
        recyclerViewFlight.addItemDecoration(new DividerItemDecoration(SearchFlightActivity.this, 1));
        recyclerViewFlight.setLayoutManager(new LinearLayoutManager(SearchFlightActivity.this));
        ArrayList<PinModelDetail> pinModelDetails = new ArrayList<>();
        ArrayList<PinModelHeader> pinModelHeaders = new ArrayList<>();
        if (!pinModelDetails.isEmpty()) {
            recyclerViewFlight.setVisibility(View.VISIBLE);
            recyclerViewFlight.setAdapter(new SearchParvazPinAdapter(pinModelDetails, pinModelHeaders));
        }
        ////////EndPin
        if (isChangeFlight) {
            sendRequestChangeFlight();
            Log.e("ch1", "onCreate: ");
        } else {
            sendRequest();
            Log.e("ch2", "onCreate: ");
        }
        weather_request();




    }//end oncreat======================================================================================

    @Override
    public boolean needTerminate() {
        return true;
    }

    public void weather_request(){
        Bundle extras = getIntent().getExtras();
        if (extras.getString("Value-Maghsad-Airport-Code")==null)
            return;
        SingletonService.getInstance().getWeatherPart().getWeatherByCity(new OnServiceStatus<WeatherApi>() {
            @Override
            public void onReady(WeatherApi weatherApi) {
                Log.e("jddoon", new Gson().toJson(weatherApi) );
                try{
                    recyclerViewHotel.setAdapter(new WeatherAdapter(weatherApi.getQuery().getResults().getChannel().getItem().getForecast()));

                }catch (Exception e){
                    slidingDrawer.setVisibility(View.GONE);

                }
            }

            @Override
            public void onError(String message) {
                slidingDrawer.setVisibility(View.GONE);


            }
        },  extras.getString("Value-Maghsad-Airport-Code"));
    }

    private void sendRequest() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SearchFlightActivity.this, R.color.status_loading));
        }
        new InitUi().Loading(SearchFlightActivity.this, rlLoading, rlRoot, true, R.drawable.flight_loading);
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                if (SingletonDate.getInstance().getStartDate() != null) {
                    Raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                    try {
                        Bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();//2018/03/11
                    } catch (Exception e) {
                    }
                }
                String maghsadf = extras.getString("Value-Maghsad-Airport-Code");
                String mabdaf = extras.getString("Value-Mabda-Airport-Code");
                 flagWay = extras.getString("Value-Flag-Two");
                String adlCount = extras.getString("Value-AdlCount");
                String chdCount = extras.getString("Value-ChdCount");
                String infCount = extras.getString("Value-InfCount");
                int flagTwo = Integer.parseInt(extras.getString("Value-Flag-Two"));
                //Global variable count mosafer
                COUNT_B = Integer.parseInt(adlCount);
                COUNT_K = Integer.parseInt(chdCount);
                COUNT_N = Integer.parseInt(infCount);
                com.eligasht.service.model.newModel.flight.searchFlight.request.RequestSearchFlight requestSearchFlight = new com.eligasht.service.model.newModel.flight.searchFlight.request.RequestSearchFlight();
                com.eligasht.service.model.newModel.flight.searchFlight.request.QueryModel request = new com.eligasht.service.model.newModel.flight.searchFlight.request.QueryModel();
                //com.eligasht.service.model.flight.request.searchFlight.Identity identity = new com.eligasht.service.model.flight.request.searchFlight.Identity();
                //identity":{"Password":"123qwe!@#QWE","TermianlId":"Mobile","jUserName":"EligashtMlb"}
               // request.setIdentity(identity);
                request.setFlightClass(flightClass);
                request.setSourceText(mabdaf);
                request.setDestinationText(maghsadf);
                request.setCheckIn(Utility.convertNumbersToEnglish(Raft));
                request.setCheckOut(Utility.convertNumbersToEnglish(Bargasht));
                if(flagTwo == 1){
                    request.setTrip(mabdaf+"-"+maghsadf+"-"+Utility.convertNumbersToEnglish(Raft).replace("/","-"));//+"|"+maghsadf+"-"+mabdaf+"-"+Utility.convertNumbersToEnglish(Bargasht).replace("/","-"));//THR-IST-2019-03-06|IST-THR-2019-03-12//OneWay(flagWay);// اگر فقط رفت باشد عدد یک و در صورت رفت و برگشت عدد 2 را ارسال بفرمایید

                } else{
                    request.setTrip(mabdaf+"-"+maghsadf+"-"+Utility.convertNumbersToEnglish(Raft).replace("/","-")+"|"+maghsadf+"-"+mabdaf+"-"+Utility.convertNumbersToEnglish(Bargasht).replace("/","-"));//THR-IST-2019-03-06|IST-THR-2019-03-12//OneWay(flagWay);// اگر فقط رفت باشد عدد یک و در صورت رفت و برگشت عدد 2 را ارسال بفرمایید

                }
                request.setPreferredClass("all");
                request.setAdult(adlCount);
                request.setChild(chdCount);
                request.setInfant(infCount);
                request.setCurrentCulture(getString(R.string.culture));
                request.setExclusiveTrain(false);
                requestSearchFlight.setQueryModel(request);
                Log.d("sendRequestFlight: ",new Gson().toJson(requestSearchFlight));
                SingletonService.getInstance().getFlight().newFlightSearchAvail(SearchFlightActivity.this, requestSearchFlight);
               // System.out.println("maghsadf" + maghsadf + "mabda" + mabdaf + "flagWay" + flagWay + "aadlcount:" + adlCount + "Raft" + Raft + "Bargasht" + Bargasht);
            } else {
                com.eligasht.service.model.newModel.flight.searchFlight.request.RequestSearchFlight requestSearchFlight = new com.eligasht.service.model.newModel.flight.searchFlight.request.RequestSearchFlight();
                com.eligasht.service.model.newModel.flight.searchFlight.request.QueryModel request = new com.eligasht.service.model.newModel.flight.searchFlight.request.QueryModel();
                //com.eligasht.service.model.flight.request.searchFlight.Identity identity = new com.eligasht.service.model.flight.request.searchFlight.Identity();
                //requestSearchFlight.setIdentity(identity);
                request.setSourceText("IST");
                request.setDestinationText("IKA");
                request.setCheckIn("2017-12-24");
                request.setCheckOut("2018-01-29");
                request.setTrip("IST"+"-"+"IKA"+"-"+Utility.convertNumbersToEnglish(Raft)+"|"+"IKA"+"-"+"IST"+"-"+Utility.convertNumbersToEnglish(Bargasht));//THR-IST-2019-03-06|IST-THR-2019-03-12//OneWay(flagWay);// اگر فقط رفت باشد عدد یک و در صورت رفت و برگشت عدد 2 را ارسال بفرمایید
                request.setPreferredClass("all");
                request.setAdult("1");
                request.setChild("0");
                request.setInfant("0");
                request.setCurrentCulture(getString(R.string.culture));
                request.setExclusiveTrain(false);
              requestSearchFlight.setQueryModel(request);
                SingletonService.getInstance().getFlight().newFlightSearchAvail(SearchFlightActivity.this, requestSearchFlight);
                //Global
                COUNT_B = 1;
                COUNT_K = 0;
                COUNT_N = 0;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onReady(ResponseSearchFlight responsSearchFlight) {
        if (responsSearchFlight != null){
        Log.d("responsSearchFlight: ",new Gson().toJson(responsSearchFlight));

        txtCityRaft.setText(responsSearchFlight.getFlightSearched().getFlightSearchSegments().get(0).getSourceText()+"");//mabdaf
        txtCityBargashtt.setText(responsSearchFlight.getFlightSearched().getFlightSearchSegments().get(0).getDestinationText()+"");//maghsadf
        txtCityBargasht.setText(responsSearchFlight.getFlightSearched().getFlightSearchSegments().get(0).getDestinationText() + "");//maghsadf
          }
        if (flightsList != null) {
            flightsList.clear();
        }
       try {
            SingletonTimer.getInstance().start();
            new InitUi().Loading(SearchFlightActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);//dismiss
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(SearchFlightActivity.this, R.color.colorPrimaryDark));
            }
            if (responsSearchFlight.getErrors().size() >0 ) {
                new InitUi().Loading(SearchFlightActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);//dismiss
                // Log.e("date", result);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(SearchFlightActivity.this, R.color.colorPrimaryDark));
                }
                linear_expand = findViewById(R.id.linear_expand);
                linear_expand.setVisibility(View.GONE);
                RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                txtNoResult.setText(responsSearchFlight.getErrors().get(0).getDetailedMessage());
                linear_no_result.setVisibility(View.VISIBLE);
            } else {
                if (recyclerViewHotel.getAdapter()!=null){
                    recyclerViewHotel.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
                    slidingDrawer.setVisibility(View.VISIBLE);

                }
                 searchKeyConfirm= responsSearchFlight.getFlightSearched().getSearchKey();
                if (responsSearchFlight.getFlights().size() > 0){
                    responsSearchFlight.getFlights().get(0).getBaseFare();
                    GlobalCurrencyCode=responsSearchFlight.getFlights().get(0).getAdlBaseFare().getCurrencyCode();
                    Prefs.putString("GlobalCurrencyCode", GlobalCurrencyCode);
                }
                if (Locale.getDefault().getLanguage().equals("fa")) {
                    getDataFaJson(responsSearchFlight);
                } else if (Locale.getDefault().getLanguage().equals("en")) {
                    getDataEnJson(responsSearchFlight);
                }else{
                    getDataEnJson(responsSearchFlight);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            new InitUi().Loading(SearchFlightActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);//dismiss

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(SearchFlightActivity.this, R.color.colorPrimaryDark));
            }
            linear_expand = findViewById(R.id.linear_expand);
            linear_expand.setVisibility(View.GONE);
            RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
            txtNoResult.setText(getString(R.string.NoResult));
            linear_no_result.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String message) {
        System.out.println("onError: " + message);
        new InitUi().Loading(SearchFlightActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);//dismiss

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SearchFlightActivity.this, R.color.colorPrimaryDark));
        }
        linear_expand = findViewById(R.id.linear_expand);
        linear_expand.setVisibility(View.GONE);
        RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
        txtNoResult.setText(getString(R.string.ErrorServer));
        linear_no_result.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReturnValueFlightNew(ArrayList<FilterModelّFlight> type, ArrayList<ModelCheckBox> arrayTrue) {
        boolean found = false;
        boolean foundFirst = true;
        boolean foundBis = true;
        boolean foundEc = true;
        this.filterAirlines = arrayTrue;
        this.filterModels = type;
        dataExpandingListFilter = new ArrayList<>();
        dataExpandingListFilter2 = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.clear();
        for (int r = 0; r < arrayTrue.size(); r++) {
            if (arrayTrue.get(r).isCheck()) {
                strings.add(arrayTrue.get(r).getName());
            }
        }
        for (FilterModelّFlight filterModel : filterModels) {
            if (strings.isEmpty()) {
                if (filterModel.isNoStop()) {
                    for (int i = 0; i < dataExpandingList.size(); i++) {
                        if (dataExpandingList.get(i).getHeader().SegmentTrueCount == 1) {//bedune tavaghof
                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                            header = dataExpandingList.get(i).getHeader();
                            parentItem.setHeader(header);
                            parentItem.setItems(dataExpandingList.get(i).getItems());
                            dataExpandingListFilter.add(parentItem);
                        }
                    }
                }
                if (filterModel.isOneStop()) {
                    for (int i = 0; i < dataExpandingList.size(); i++) {
                        if (dataExpandingList.get(i).getHeader().SegmentTrueCount == 2) {//yek tavaghof
                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                            header = dataExpandingList.get(i).getHeader();
                            parentItem.setHeader(header);
                            parentItem.setItems(dataExpandingList.get(i).getItems());
                            dataExpandingListFilter.add(parentItem);
                        }
                    }
                }
                if (filterModel.isTwoStopMore()) {
                    for (int i = 0; i < dataExpandingList.size(); i++) {
                        if (dataExpandingList.get(i).getHeader().SegmentTrueCount >= 3) {//2 va bishtar tavaghof
                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                            header = dataExpandingList.get(i).getHeader();
                            parentItem.setHeader(header);
                            parentItem.setItems(dataExpandingList.get(i).getItems());
                            dataExpandingListFilter.add(parentItem);
                        }
                    }
                }
                if (filterModel.isEconomiF()) {

                    for (int i = 0; i < dataExpandingList.size(); i++) {
                        if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains(getString(R.string.Economi))) {
                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                            header = dataExpandingList.get(i).getHeader();
                            parentItem.setHeader(header);
                            parentItem.setItems(dataExpandingList.get(i).getItems());
                            dataExpandingListFilter.add(parentItem);
                        }
                    }
                }
                if (filterModel.isBusinessF()) {
                    for (int i = 0; i < dataExpandingList.size(); i++) {
                        if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains(getString(R.string._biss))) {
                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                            header = dataExpandingList.get(i).getHeader();
                            parentItem.setHeader(header);
                            parentItem.setItems(dataExpandingList.get(i).getItems());
                            dataExpandingListFilter.add(parentItem);
                        }
                    }
                }
                if (filterModel.isFerstF()) {
                    for (int i = 0; i < dataExpandingList.size(); i++) {
                        if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains(getString(R.string._ferst))) {
                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                            header = dataExpandingList.get(i).getHeader();
                            parentItem.setHeader(header);
                            parentItem.setItems(dataExpandingList.get(i).getItems());
                            dataExpandingListFilter.add(parentItem);
                        }
                    }
                }
                if ((filterModel.isOneStop() || filterModel.isNoStop() || filterModel.isTwoStopMore()) && (filterModel.isEconomiF() || filterModel.isBusinessF()) || filterModel.isEconomiF()) {
                    boolean isClear = false;
                    if (filterModel.isNoStop() && filterModel.isEconomiF()) {
                        for (int i = 0; i < dataExpandingListFilter.size(); i++) {
                            if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains(getString(R.string.Economi)) && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 1) {
                                isClear = true;
                                ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                HeaderExpandingPlan header = new HeaderExpandingPlan();
                                header = dataExpandingListFilter.get(i).getHeader();
                                parentItem.setHeader(header);
                                parentItem.setItems(dataExpandingListFilter.get(i).getItems());
                                dataExpandingListFilter2.add(parentItem);
                            }
                        }
                    }
                    if (filterModel.isOneStop() && filterModel.isEconomiF()) {
                        for (int i = 0; i < dataExpandingListFilter.size(); i++) {
                            if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains(getString(R.string.Economi)) && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 2) {
                                isClear = true;
                                ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                HeaderExpandingPlan header = new HeaderExpandingPlan();
                                header = dataExpandingListFilter.get(i).getHeader();
                                parentItem.setHeader(header);
                                parentItem.setItems(dataExpandingListFilter.get(i).getItems());
                                dataExpandingListFilter2.add(parentItem);
                            }
                        }
                    }
                    if (filterModel.isTwoStopMore() && filterModel.isEconomiF()) {
                        for (int i = 0; i < dataExpandingListFilter.size(); i++) {
                            if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains(getString(R.string.Economi)) && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount >= 3) {
                                isClear = true;
                                ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                HeaderExpandingPlan header = new HeaderExpandingPlan();
                                header = dataExpandingListFilter.get(i).getHeader();
                                parentItem.setHeader(header);
                                parentItem.setItems(dataExpandingListFilter.get(i).getItems());
                                dataExpandingListFilter2.add(parentItem);
                            }
                        }
                    }
                    //=====================================================================================================
                    if (filterModel.isNoStop() && filterModel.isFerstF()) {
                        for (int i = 0; i < dataExpandingListFilter.size(); i++) {
                            if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains(getString(R.string._ferst)) && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 1) {
                                isClear = true;
                                ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                HeaderExpandingPlan header = new HeaderExpandingPlan();
                                header = dataExpandingListFilter.get(i).getHeader();
                                parentItem.setHeader(header);
                                parentItem.setItems(dataExpandingListFilter.get(i).getItems());
                                dataExpandingListFilter2.add(parentItem);
                            }
                        }
                    }
                    if (filterModel.isOneStop() && filterModel.isFerstF()) {
                        for (int i = 0; i < dataExpandingListFilter.size(); i++) {
                            if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains(getString(R.string._ferst)) && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 2) {
                                isClear = true;
                                ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                HeaderExpandingPlan header = new HeaderExpandingPlan();
                                header = dataExpandingListFilter.get(i).getHeader();
                                parentItem.setHeader(header);
                                parentItem.setItems(dataExpandingListFilter.get(i).getItems());
                                dataExpandingListFilter2.add(parentItem);
                            }
                        }
                    }
                    if (filterModel.isTwoStopMore() && filterModel.isFerstF()) {
                        for (int i = 0; i < dataExpandingListFilter.size(); i++) {
                            if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains(getString(R.string._ferst)) && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount >= 3) {
                                isClear = true;
                                ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                HeaderExpandingPlan header = new HeaderExpandingPlan();
                                header = dataExpandingListFilter.get(i).getHeader();
                                parentItem.setHeader(header);
                                parentItem.setItems(dataExpandingListFilter.get(i).getItems());
                                dataExpandingListFilter2.add(parentItem);
                            }
                        }
                    }
                    //=================================================================================================
                    if (filterModel.isNoStop() && filterModel.isBusinessF()) {
                        for (int i = 0; i < dataExpandingListFilter.size(); i++) {
                            if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains(getString(R.string._biss)) && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 1) {
                                ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                HeaderExpandingPlan header = new HeaderExpandingPlan();
                                header = dataExpandingListFilter.get(i).getHeader();
                                parentItem.setHeader(header);
                                parentItem.setItems(dataExpandingListFilter.get(i).getItems());
                                dataExpandingListFilter2.add(parentItem);
                            }
                        }
                    }
                    if (filterModel.isOneStop() && filterModel.isBusinessF()) {
                        for (int i = 0; i < dataExpandingListFilter.size(); i++) {
                            if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains(getString(R.string._biss)) && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 2) {
                                isClear = true;
                                ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                HeaderExpandingPlan header = new HeaderExpandingPlan();
                                header = dataExpandingListFilter.get(i).getHeader();
                                parentItem.setHeader(header);
                                parentItem.setItems(dataExpandingListFilter.get(i).getItems());
                                dataExpandingListFilter2.add(parentItem);
                            }
                        }
                    }
                    if (filterModel.isTwoStopMore() && filterModel.isBusinessF()) {
                        for (int i = 0; i < dataExpandingListFilter.size(); i++) {
                            if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains(getString(R.string._biss)) && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount >= 3) {
                                isClear = true;
                                ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                HeaderExpandingPlan header = new HeaderExpandingPlan();
                                header = dataExpandingListFilter.get(i).getHeader();
                                parentItem.setHeader(header);
                                parentItem.setItems(dataExpandingListFilter.get(i).getItems());
                                dataExpandingListFilter2.add(parentItem);
                            }
                        }
                    }
                    //=====================================================================================================
                    if (isClear) {
                        dataExpandingListFilter.clear();
                        dataExpandingListFilter = dataExpandingListFilter2;
                    }
                } else {
                }
                //====+++==+++++++++++++++++++++++++++++++++++++++++++++++===(*&*(&+++++
            } else {
                if (strings.size() > 0) {
                    if (!(filterModel.isNoStop() || filterModel.isOneStop() || filterModel.isTwoStopMore() || filterModel.isBusinessF() || filterModel.isBusinessF() || filterModel.isFerstF())) {
                        for (int i = 0; i < dataExpandingList.size(); i++) {
                            //if (dataExpandingList.get(i).getHeader().SegmentTrueCount == 1) {//bedune tavaghof
                            for (int ii = 0; ii < strings.size(); ii++) {
                                if (dataExpandingList.get(i).getHeader().AirlineNameFa.equals(strings.get(ii))) {
                                    ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                    HeaderExpandingPlan header = new HeaderExpandingPlan();
                                    header = dataExpandingList.get(i).getHeader();
                                    parentItem.setHeader(header);
                                    parentItem.setItems(dataExpandingList.get(i).getItems());
                                    dataExpandingListFilter.add(parentItem);
                                }
                            }
                        }
                    } else {
                        if (filterModel.isNoStop()) {
                            for (int i = 0; i < dataExpandingList.size(); i++) {
                                if (dataExpandingList.get(i).getHeader().SegmentTrueCount == 1) {//bedune tavaghof
                                    for (int ii = 0; ii < strings.size(); ii++) {
                                        if (dataExpandingList.get(i).getHeader().AirlineNameFa.equals(strings.get(ii))) {
                                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                                            header = dataExpandingList.get(i).getHeader();
                                            parentItem.setHeader(header);
                                            parentItem.setItems(dataExpandingList.get(i).getItems());
                                            dataExpandingListFilter.add(parentItem);
                                        }
                                    }
                                }
                            }
                        }
                        if (filterModel.isOneStop()) {
                            for (int i = 0; i < dataExpandingList.size(); i++) {
                                if (dataExpandingList.get(i).getHeader().SegmentTrueCount == 2) {//yek tavaghof
                                    for (int ii = 0; ii < strings.size(); ii++) {
                                        if (dataExpandingList.get(i).getHeader().AirlineNameFa.equals(strings.get(ii))) {
                                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                                            header = dataExpandingList.get(i).getHeader();
                                            parentItem.setHeader(header);
                                            parentItem.setItems(dataExpandingList.get(i).getItems());
                                            dataExpandingListFilter.add(parentItem);
                                        }
                                    }
                                }
                            }
                        }
                        if (filterModel.isTwoStopMore()) {
                            for (int i = 0; i < dataExpandingList.size(); i++) {
                                if (dataExpandingList.get(i).getHeader().SegmentTrueCount >= 3) {//2 va bishtar tavaghof
                                    for (int ii = 0; ii < strings.size(); ii++) {
                                        if (dataExpandingList.get(i).getHeader().AirlineNameFa.equals(strings.get(ii))) {
                                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                                            header = dataExpandingList.get(i).getHeader();
                                            parentItem.setHeader(header);
                                            parentItem.setItems(dataExpandingList.get(i).getItems());
                                            dataExpandingListFilter.add(parentItem);
                                        }
                                    }
                                }
                            }
                        }
                        if (filterModel.isEconomiF()) {
                            //if(dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains(getString(R.string.Economi))){
                            for (int i = 0; i < dataExpandingList.size(); i++) {
                                if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains(getString(R.string.Economi))) {
                                    for (int ii = 0; ii < strings.size(); ii++) {
                                        if (dataExpandingList.get(i).getHeader().AirlineNameFa.equals(strings.get(ii))) {
                                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                                            header = dataExpandingList.get(i).getHeader();
                                            parentItem.setHeader(header);
                                            parentItem.setItems(dataExpandingList.get(i).getItems());
                                            dataExpandingListFilter.add(parentItem);
                                        }
                                    }
                                }
                            }
                        }
                        if (filterModel.isBusinessF()) {
                            for (int i = 0; i < dataExpandingList.size(); i++) {
                                if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains(getString(R.string._biss))) {
                                    for (int ii = 0; ii < strings.size(); ii++) {
                                        if (dataExpandingList.get(i).getHeader().AirlineNameFa.equals(strings.get(ii))) {
                                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                                            header = dataExpandingList.get(i).getHeader();
                                            parentItem.setHeader(header);
                                            parentItem.setItems(dataExpandingList.get(i).getItems());
                                            dataExpandingListFilter.add(parentItem);
                                        }
                                    }
                                }
                            }
                        }
                        if (filterModel.isFerstF()) {
                            for (int i = 0; i < dataExpandingList.size(); i++) {
                                if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains(getString(R.string._ferst))) {
                                    for (int ii = 0; ii < strings.size(); ii++) {
                                        if (dataExpandingList.get(i).getHeader().AirlineNameFa.equals(strings.get(ii))) {
                                            ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                                            HeaderExpandingPlan header = new HeaderExpandingPlan();
                                            header = dataExpandingList.get(i).getHeader();
                                            parentItem.setHeader(header);
                                            parentItem.setItems(dataExpandingList.get(i).getItems());
                                            dataExpandingListFilter.add(parentItem);
                                        }
                                    }
                                }
                            }
                        }
                    }//end oh filter
                }
            }
            if (filterModel.isRemove()) {
                listAdapterExpanding = new ExpandableListAdapter(SearchFlightActivity.this, dataExpandingList, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding,searchKeyConfirm);
                expListViewExpanding.setAdapter(listAdapterExpanding);
                iconFilter.setTextColor(Color.parseColor("#4d4d4d"));
                txtFilter.setTextColor(Color.parseColor("#4d4d4d"));
            }
        }
        linear_expand = findViewById(R.id.linear_expand);
        linear_expand.setVisibility(View.VISIBLE);
        RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
        txtNoResult.setText(R.string.NoResult);
        linear_no_result.setVisibility(View.GONE);
        if (dataExpandingListFilter.isEmpty() || !foundFirst || !foundEc || !foundBis) {

            iconFilter.setTextColor(Color.RED);
            txtFilter.setTextColor(Color.RED);
            linear_expand = findViewById(R.id.linear_expand);
            linear_expand.setVisibility(View.GONE);
            linear_no_result = findViewById(R.id.linear_no_result);
            txtNoResult.setText(R.string.NoResult);
            linear_no_result.setVisibility(View.VISIBLE);
            if (FlagRemove) {
                iconFilter.setTextColor(Color.parseColor("#4d4d4d"));
                txtFilter.setTextColor(Color.parseColor("#4d4d4d"));
                FlagRemove = false;
                linear_expand = findViewById(R.id.linear_expand);
                linear_expand.setVisibility(View.VISIBLE);
                linear_no_result = findViewById(R.id.linear_no_result);
                txtNoResult.setText(R.string.NoResult);
                linear_no_result.setVisibility(View.GONE);
            }

        } else {
            listAdapterExpanding = new ExpandableListAdapter(SearchFlightActivity.this, dataExpandingListFilter, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding,searchKeyConfirm);
            expListViewExpanding.setAdapter(listAdapterExpanding);
            iconFilter.setTextColor(Color.RED);
            txtFilter.setTextColor(Color.RED);
            linear_expand = findViewById(R.id.linear_expand);
            linear_expand.setVisibility(View.VISIBLE);
            linear_no_result = findViewById(R.id.linear_no_result);
            txtNoResult.setText(R.string.NoResult);
            linear_no_result.setVisibility(View.GONE);
        }
        listAdapterExpanding.notifyDataSetChanged();
    }

    @Override
    public void onReturnValueSort(int type) {
        switch (type) {
            case 1:
                besetSeler = true;
                bestOff = false;
                Collections.sort(dataExpandingList, new Comparator<ParentItemExpandingPlan>() {
                    @Override
                    public int compare(SearchFlightActivity.ParentItemExpandingPlan o1, SearchFlightActivity.ParentItemExpandingPlan o2) {

                        return Double.compare(o2.Header.AdlCost, o1.Header.AdlCost);
                    }
                });

                listAdapterExpanding = new ExpandableListAdapter(SearchFlightActivity.this, dataExpandingList, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding,searchKeyConfirm);

                expListViewExpanding.setAdapter(listAdapterExpanding);
                listAdapterExpanding.notifyDataSetChanged();
                break;
            case 2:
                besetSeler = false;//bestCoust
                bestOff = true;//lowCoust
                Collections.sort(dataExpandingList, new Comparator<ParentItemExpandingPlan>() {
                    @Override
                    public int compare(SearchFlightActivity.ParentItemExpandingPlan o1, SearchFlightActivity.ParentItemExpandingPlan o2) {
                        return Double.compare(o1.Header.AdlCost, o2.Header.AdlCost);
                    }
                });
                listAdapterExpanding = new ExpandableListAdapter(SearchFlightActivity.this, dataExpandingList, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding,searchKeyConfirm);
                expListViewExpanding.setAdapter(listAdapterExpanding);
                listAdapterExpanding.notifyDataSetChanged();
                break;
        }
    }

    private void expandingListData(boolean flag) {
        linear_expand = findViewById(R.id.linear_expand);
        linear_expand.setVisibility(View.VISIBLE);
        RelativeLayout linear_no_resultt = findViewById(R.id.linear_no_result);
        linear_no_resultt.setVisibility(View.GONE);
        listDataChildExpanding = new HashMap<String, HashMap<String, ItemExpandingPlan>>();
        dataExpandingList = new ArrayList<ParentItemExpandingPlan>();
        ArrayList<SearchParvazModelExp> searchParvazModelExps = new ArrayList<SearchParvazModelExp>();
        ArrayList<SearchParvazModelExp> searchParvazModelExps1 = new ArrayList<>();
        if (flightsList != null) {
            try {
                System.out.println("flightsList.size():" + flightsList.size());
                dataExpandingList = new ArrayList<ParentItemExpandingPlan>();
                for (int i = 0; i < flightsList.size(); i++) {
                   // System.out.println("HEADER I=" + i);
                    SegmentList = flightsList.get(i).getSegmentList();
                    SegmentListtrueAvali = flightsList.get(i).getSegmentListTrue();
                    SegmentListtrueAkhari = flightsList.get(i).getSegmentListTrue();
                    SegmentListFalseAvali = flightsList.get(i).getSegmentListFalse();
                    SegmentListFalseAkhari = flightsList.get(i).getSegmentListFalse();
                    if (SegmentListFalseAvali.size() == 0 || SegmentListFalseAvali == null) {//yek tarafe
                        ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                        HeaderExpandingPlan header = new HeaderExpandingPlan(SegmentListtrueAvali.get(0).getDepartureCityNameFa(), SegmentListtrueAvali.get(0).getFlightTime(),
                                SegmentListtrueAkhari.get(SegmentListtrueAkhari.size() - 1).getArrivalCityNameFa(), SegmentListtrueAkhari.get(SegmentListtrueAkhari.size() - 1).getFlightTime(),
                                " ", " ",
                                " ", " ",
                                flightsList.get(i).getTotalFare().getAmount(),
                                flightsList.get(i).getFlightGUID()
                                , SegmentList.get(0).getAirlineNameFa()
                                , SegmentList.get(0).getAirlineCode()
                                , SegmentList.get(0).getCabinClassNameFa()
                                , flightsList.get(i).getRemainSeats()
                                , flightsList.get(i).isIsCharter()
                                , SegmentList.get(0).getAirlineNameEn()
                                , SegmentListtrueAkhari.size()
                                , SegmentListtrueAvali.get(0).getFltDateDayOfWeek()
                                , SegmentListFalseAvali.size() > 0 ? SegmentListFalseAvali.get(0).getFltDateDayOfWeek() : "0"//SegmentListFalseAvali.get(0).getFltDateDayOfWeek()
                                , SegmentListFalseAkhari.size()
                                , SegmentListtrueAvali.get(0).getFlightNumber()
                                , SegmentListFalseAvali.size() > 0 ? SegmentListFalseAvali.get(0).getFlightNumber() : "0", false
                                , SegmentListtrueAkhari
                                , SegmentListFalseAkhari);//ArrivalCityNameEnR baraye sort bayad en bashe
                        parentItem.setHeader(header);
                        for (int j = 0; j < SegmentList.size(); j++) {
                           // System.out.println("Detail j=" + j);
                            ItemExpandingPlan item = new ItemExpandingPlan();
                            //Item
                            item.DepartureAirportNameFaR = SegmentList.get(j).getDepartureAirportNameFa();
                            item.FlightTimeR = SegmentList.get(j).getFlightTime();
                            item.ArrivalAirportNameFaR = SegmentList.get(j).getArrivalAirportNameFa();
                            item.FlightArrivalTimeR = SegmentList.get(j).getFlightArrivalTime();
                            item.AirlineNameFaR = SegmentList.get(j).getAirlineNameFa();
                            item.FlightNumberR = SegmentList.get(j).getFlightNumber();
                            item.AirlineCode = SegmentList.get(j).getAirlineCode();
                            item.flGUID = flightsList.get(i).getFlightGUID();
                            item.AdlBaseFare = flightsList.get(i).getBaseFare().getAmount();
                            item.Taxes = flightsList.get(i).getTaxes().getAmount();
                            item.TotalFare = flightsList.get(i).getTotalFare().getAmount();
                            item.DepartureCityNameFa = SegmentList.get(j).getDepartureCityNameFa();
                            item.ArrivalCityNameFa = SegmentList.get(j).getArrivalCityNameFa();
                            item.OperatingAirlineNameEn = SegmentList.get(j).getOperatingAirlineNameEn();
                            item.weight = SegmentList.get(j).getWeight();
                            item.Pieces = SegmentList.get(j).getPieces();
                            parentItem.Items.add(item);
                        }
                        dataExpandingList.add(parentItem);
                    } else {//2tarafe
                        //header
                        ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
                        HeaderExpandingPlan header = new HeaderExpandingPlan(SegmentListtrueAvali.get(0).getDepartureCityNameFa(), SegmentListtrueAvali.get(0).getFlightTime(),
                                SegmentListtrueAkhari.get(SegmentListtrueAkhari.size() - 1).getArrivalCityNameFa(), SegmentListtrueAkhari.get(SegmentListtrueAkhari.size() - 1).getFlightTime(),
                                SegmentListFalseAvali.get(0).getDepartureCityNameFa(), SegmentListFalseAvali.get(0).getFlightArrivalTime(),
                                SegmentListFalseAkhari.get(SegmentListFalseAkhari.size() - 1).getArrivalCityNameFa(), SegmentListFalseAkhari.get(0).getFlightTime(),
                                flightsList.get(i).getTotalFare().getAmount(),
                                flightsList.get(i).getFlightGUID()
                                , SegmentList.get(0).getAirlineNameFa()
                                , SegmentList.get(0).getAirlineCode()
                                , SegmentList.get(0).getCabinClassNameFa()
                                , flightsList.get(i).getRemainSeats()
                                , flightsList.get(i).isIsCharter()
                                , SegmentList.get(0).getAirlineNameEn()
                                , SegmentListtrueAkhari.size()
                                , SegmentListtrueAvali.get(0).getFltDateDayOfWeek()
                                , SegmentListFalseAvali.size() > 0 ? SegmentListFalseAvali.get(0).getFltDateDayOfWeek() : "0"
                                , SegmentListFalseAkhari.size()
                                , SegmentListtrueAvali.get(0).getFlightNumber()
                                , SegmentListFalseAvali.size() > 0 ? SegmentListFalseAvali.get(0).getFlightNumber() : "0", false
                                , SegmentListtrueAkhari
                                , SegmentListFalseAkhari);//ArrivalCityNameEnR baraye sort bayad en bashe

                        parentItem.setHeader(header);
                        //fore Detail item
                        for (int j = 0; j < SegmentList.size(); j++) {
                            System.out.println("Detail j=" + j);
                            ItemExpandingPlan item = new ItemExpandingPlan();
                            //Item
                            item.DepartureAirportNameFaR = SegmentList.get(j).getDepartureAirportNameFa();
                            item.FlightTimeR = SegmentList.get(j).getFlightTime();
                            item.ArrivalAirportNameFaR = SegmentList.get(j).getArrivalAirportNameFa();
                            item.FlightArrivalTimeR = SegmentList.get(j).getFlightArrivalTime();
                            item.AirlineNameFaR = SegmentList.get(j).getAirlineNameFa();
                            item.FlightNumberR = SegmentList.get(j).getFlightNumber();
                            item.AirlineCode = SegmentList.get(j).getAirlineCode();
                            item.flGUID = flightsList.get(i).getFlightGUID();
                            item.AdlBaseFare = flightsList.get(i).getBaseFare().getAmount();
                            item.Taxes = flightsList.get(i).getTaxes().getAmount();
                            item.TotalFare = flightsList.get(i).getTotalFare().getAmount();
                            item.DepartureCityNameFa = SegmentList.get(j).getDepartureCityNameFa();
                            item.ArrivalCityNameFa = SegmentList.get(j).getArrivalCityNameFa();
                            item.OperatingAirlineNameEn = SegmentList.get(j).getOperatingAirlineNameEn();
                            item.FltDurationH = SegmentList.get(j).getFltDurationH();
                            item.FltDurationM = SegmentList.get(j).getFltDurationM();
                            item.weight = SegmentList.get(j).getWeight();
                            item.Pieces = SegmentList.get(j).getPieces();
                            item.nonRefundable = flightsList.get(i).isNonRefundable();
                            parentItem.Items.add(item);
                        }
                        dataExpandingList.add(parentItem);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
        if(!flag){
            if (flightsList.size() == 0 || flightsList == null) {
                new InitUi().Loading(SearchFlightActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);//dismiss
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(SearchFlightActivity.this, R.color.colorPrimaryDark));
                }
                linear_expand.setVisibility(View.GONE);

                txtNoResult.setText(R.string.NoResult);
                if (flag && flightsList == null)
                    linear_no_result.setVisibility(View.VISIBLE);
                else if (flag && flightsList.size() > 0)
                    linear_no_result.setVisibility(View.GONE);
                else
                    linear_no_result.setVisibility(View.VISIBLE);
            }else{
                linear_no_result.setVisibility(View.GONE);
            }
        }
    }//end expanding listdata

    private void getAirLine() {
        listDataChildExpanding = new HashMap<String, HashMap<String, ItemExpandingPlan>>();
        ArrayList<SearchParvazModelExp> searchParvazModelExps = new ArrayList<SearchParvazModelExp>();
        ArrayList<SearchParvazModelExp> searchParvazModelExps1 = new ArrayList<>();
        if (flightsList != null) {
            System.out.println("flightsList.size():" + flightsList.size());

            for (int i = 0; i < flightsList.size(); i++) {
                SegmentList = flightsList.get(i).getSegmentList();

                airlineConstraint.add(SegmentList.get(0).getAirlineNameFa());
                //Log.e("rrrrrrrr", SegmentList.get(0).getAirlineNameFa());
            }
            List<String> al = new ArrayList<>();
            Set<String> hs = new HashSet<>();
            hs.addAll(airlineConstraint);
            airlineConstraint.clear();
            airlineConstraint.addAll(hs);
            for (int i = 0; i < airlineConstraint.size(); i++) {
                filterAirlines.add(new ModelCheckBox(airlineConstraint.get(i), false));
            }
        }
    }//end getAirLine

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
    }

    @Override
    public void searchTextChanged(String searchText) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onClick(View v) {
        Fragment fragment2;
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.txtBack:
                finish();
                break;
            case R.id.btnHome:
                Intent intent = new Intent("sendFinish");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                break;
            case R.id.llBottom:
                new FilterFlightDialogNew(SearchFlightActivity.this, filterModels, this, filterAirlines);
                break;
            case R.id.llSort://sort
                // custom dialog
                new SortFlightDialog(SearchFlightActivity.this, this, besetSeler, bestOff, remove);
                break;
            case R.id.txtRuzeBad:
                if (SingletonDate.getInstance().getStartDate().addOneDay()) {
                    Raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                    Bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
                    RaftF = SingletonDate.getInstance().getStartDate().getDescription();
                    BargashtF = SingletonDate.getInstance().getEndDate().getDescription();

                    if(flagWay.trim().equals("2")){// اگر فقط رفت باشد عدد یک و در صورت رفت و برگشت عدد 2
                        txtDateOnvanB.setText(null);
                        txtDateOnvanB.setText(RaftF);
                        txtDateOnvan.setText(BargashtF);
                    }else{
                        txticonDate.setText(RaftF);
                        txtDateOnvanB.setText(null);
                       // txtDateOnvanB.setText(RaftF);
                    }
                    callApiDateNext();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.datePickerError, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnLastDays:
                if (SingletonDate.getInstance().getStartDate().addOneDay()) {
                    Raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                    Bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
                    RaftF = SingletonDate.getInstance().getStartDate().getDescription();
                    BargashtF = SingletonDate.getInstance().getEndDate().getDescription();
                    if(flagWay.trim().equals("2")){// اگر فقط رفت باشد عدد یک و در صورت رفت و برگشت عدد 2
                        txtDateOnvanB.setText(null);
                        txtDateOnvanB.setText(RaftF);
                        txtDateOnvan.setText(BargashtF);
                    }else{
                        txticonDate.setText(RaftF);
                        txtDateOnvanB.setText(null);
                        // txtDateOnvanB.setText(RaftF);
                    }
                    callApiDateNext();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.datePickerError,
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_no_Result:
                finish();
                break;
            case R.id.txtRuzeGhabl:
                if (SingletonDate.getInstance().getStartDate().minusOneDay()) {
                    Raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                    Bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
                    RaftF = SingletonDate.getInstance().getStartDate().getDescription();
                    BargashtF = SingletonDate.getInstance().getEndDate().getDescription();
                    if(flagWay.trim().equals("2")){// اگر فقط رفت باشد عدد یک و در صورت رفت و برگشت عدد 2
                        txtDateOnvanB.setText(null);
                        txtDateOnvanB.setText(RaftF);
                        txtDateOnvan.setText(BargashtF);
                    }else{
                        txticonDate.setText(RaftF);
                        txtDateOnvanB.setText(null);
                        // txtDateOnvanB.setText(RaftF);
                    }
                    callApiDateNext();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.DatePickerError2,
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnNextDays:
                if (SingletonDate.getInstance().getStartDate().minusOneDay()) {
                    Raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                    Bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
                    RaftF = SingletonDate.getInstance().getStartDate().getDescription();
                    BargashtF = SingletonDate.getInstance().getEndDate().getDescription();
                    if(flagWay.trim().equals("2")){// اگر فقط رفت باشد عدد یک و در صورت رفت و برگشت عدد 2
                        txtDateOnvanB.setText(null);
                        txtDateOnvanB.setText(RaftF);
                        txtDateOnvan.setText(BargashtF);
                    }else{
                        txticonDate.setText(RaftF);
                        txtDateOnvanB.setText(null);
                        // txtDateOnvanB.setText(RaftF);
                    }
                    callApiDateNext();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.DatePickerError2, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void callApiDateNext() {
        sendRequest();
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onResume() {
        Log.e("DEBUG", "onResume of SearchFlightActivity");
        super.onResume();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","onDestroy invoked");
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void showDataExpanding() {
        // preparing list data
        expandingListData(false);
        Log.e("dataExpandingList: ",dataExpandingList.size()+"" );
        listAdapterExpanding = new ExpandableListAdapter(SearchFlightActivity.this, dataExpandingList, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding,searchKeyConfirm);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // setting list adapter
                expListViewExpanding.setAdapter(listAdapterExpanding);
                // Listview Group click listener
                expListViewExpanding.setOnGroupClickListener(new OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        Log.d("TAG", "onGroupClick: 1500");
                        ExpandableListAdapter.shouldShowAnimation = false;
                        return false;
                    }
                });
                // Listview Group expanded listener
                expListViewExpanding.setOnGroupExpandListener(new OnGroupExpandListener() {
                    @Override
                    public void onGroupExpand(int groupPosition) {
                    }
                });
                // Listview Group collasped listener
                expListViewExpanding.setOnGroupCollapseListener(new OnGroupCollapseListener() {
                    @Override
                    public void onGroupCollapse(int groupPosition) {
                    }
                });
                // Listview on child click listener
                expListViewExpanding.setOnChildClickListener(new OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                int groupPosition, int childPosition, long id) {
                        return false;
                    }
                });
            }
        });
        expListViewExpanding.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                ExpandableListAdapter.shouldShowAnimation = true;
            }
        });
    }

    public class ParentItemExpandingPlan {
        public HeaderExpandingPlan Header;
        //public String Header;
        public List<ItemExpandingPlan> Items;

        public ParentItemExpandingPlan(String header) {
            //Header = header;
            Header = new HeaderExpandingPlan();
            Items = new ArrayList<ItemExpandingPlan>();
        }

        public HeaderExpandingPlan getHeader() {
            return Header;
        }

        public void setHeader(HeaderExpandingPlan header) {
            Header = header;
        }

        public List<ItemExpandingPlan> getItems() {
            return Items;
        }

        public void setItems(List<ItemExpandingPlan> items) {
            Items = items;
        }
    }
    public class HeaderExpandingPlan {
        public String AirlineNameEa;
        public String ArrivalCityNameFaR;
        public String FlightArrivalTimeR;
        public String DepartureCityNameFaR;
        public String FlightTimeR;
        public String ArrivalCityNameFaB;
        public String FlightArrivalTimeB;
        public String DepartureCityNameFaB;
        public String FlightTimeB;
        public double AdlCost;
        public String flGUID;//apiPassenger
        public String AirlineNameFa;
        public String AirlineCode;
        public String CabinClassNameFa;
        public int RemainSeats;
        public boolean IsCharter;
        public int SegmentTrueCount;
        public String FltDateDayOfWeek;
        public String FltDateDayOfWeekFalse;
        public int SegmentFalseCount;
        public String FlightNumberB;
        public String FlightNumberR;
        public boolean IsPin;
        public List<FlightSegmentTrue> segmentListtrueAkhari;
        public List<FlightSegmentFalse> segmentListfalseAkhari;

        public HeaderExpandingPlan(String ArrivalCityNameFaR, String FlightArrivalTimeR,
                                   String DepartureCityNameFaR, String FlightTimeR,
                                   String ArrivalCityNameFaB, String FlightArrivalTimeB,
                                   String DepartureCityNameFaB, String FlightTimeB,
                                   double AdlCost
                , String flGUID, String AirlineNameFa
                , String AirlineCode
                , String CabinClassNameFa, int RemainSeats, boolean IsCharter, String AirlineNameEa, int SegmentTrueCount, String FltDateDayOfWeekTrue, String FltDateDayOfWeekFalse, int SegmentFalseCount, String FlightNumberR
                , String FlightNumberB
                , boolean IsPin,
                /*,String FltDurationHR,String FltDurationMR
                ,String FltDurationHB,String FltDurationMB*/List<FlightSegmentTrue> segmentListtrueAkhari, List<FlightSegmentFalse> segmentListFalseAkhari) {
            this.ArrivalCityNameFaR = ArrivalCityNameFaR;
            this.FlightArrivalTimeR = FlightArrivalTimeR;
            this.DepartureCityNameFaR = DepartureCityNameFaR;
            this.FlightTimeR = FlightTimeR;
            this.ArrivalCityNameFaB = ArrivalCityNameFaB;
            this.FlightArrivalTimeB = FlightArrivalTimeB;
            this.DepartureCityNameFaB = DepartureCityNameFaB;
            this.FlightTimeB = FlightTimeB;
            this.AdlCost = AdlCost;
            this.flGUID = flGUID;
            this.AirlineNameFa = AirlineNameFa;
            this.AirlineCode = AirlineCode;
            this.CabinClassNameFa = CabinClassNameFa;
            this.RemainSeats = RemainSeats;
            this.IsCharter = IsCharter;
            this.AirlineNameEa = AirlineNameEa;
            this.SegmentTrueCount = SegmentTrueCount;
            this.FltDateDayOfWeek = FltDateDayOfWeekTrue;
            this.FltDateDayOfWeekFalse = FltDateDayOfWeekFalse;
            this.SegmentFalseCount = SegmentFalseCount;
            this.FlightNumberR = FlightNumberR;
            this.FlightNumberB = FlightNumberB;
            this.IsPin = IsPin;
            this.segmentListtrueAkhari = segmentListtrueAkhari;
            this.segmentListfalseAkhari = segmentListFalseAkhari;

        }

        public HeaderExpandingPlan() {
        }

        public boolean isPin() {
            return IsPin;
        }

        public void setPin(boolean pin) {
            IsPin = pin;
        }

        public String getArrivalCityNameFaR() {
            return ArrivalCityNameFaR;
        }

        public void setArrivalCityNameFaR(String arrivalCityNameFaR) {
            ArrivalCityNameFaR = arrivalCityNameFaR;
        }
    }
    public class ItemExpandingPlan {
        public String DepartureAirportNameFaR;
        public String FlightTimeR;
        public String ArrivalAirportNameFaR;
        public String FlightArrivalTimeR;
        public String AirlineNameFaR;
        public String FlightNumberR;
        public String flGUID;//apiPassenger
        public double AdlBaseFare;
        public double Taxes;
        public double TotalFare;
        public String AirlineCode;
        public String DepartureCityNameFa;
        public String ArrivalCityNameFa;
        public String OperatingAirlineNameEn;
        public String FltDurationH;
        public String FltDurationM;
        public String weight;
        public String Pieces;
        public Boolean nonRefundable;

        public ItemExpandingPlan() {
            // TODO Auto-generated constructor stub
        }
    }

    private void sendRequestChangeFlight() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(SearchFlightActivity.this, R.color.status_loading));
        }
        new InitUi().Loading(SearchFlightActivity.this, rlLoading, rlRoot, true, R.drawable.flight_loading);
        RequestChangeFlight requestChangeFlight = new RequestChangeFlight();
       /* com.eligasht.service.model.flight.request.ChangeFlight.Request request = new com.eligasht.service.model.flight.request.ChangeFlight.Request();
        com.eligasht.service.model.flight.request.ChangeFlight.Identity identity = new com.eligasht.service.model.flight.request.ChangeFlight.Identity();
        request.setIdentity(identity);*/
        //requestChangeFlight.setCulture(getString(R.string.culture));
        requestChangeFlight.setCurrentFlight(FlightId);
        requestChangeFlight.setFligthId(searchKey);
        //requestChangeFlight.setRequest(request);
        Log.e("requestChangeFlight: ",new Gson().toJson(requestChangeFlight)+"" );
        SingletonService.getInstance().getFlight().newGetChangeFlight(new OnServiceStatus<ResponseChangeFlight>() {
            @Override
            public void onReady(ResponseChangeFlight responsSearchFlight) {
                recyclerViewHotel.setLayoutManager(new LinearLayoutManager(SearchFlightActivity.this,LinearLayoutManager.HORIZONTAL,false));
            //    slidingDrawer.setVisibility(View.VISIBLE);
                new InitUi().Loading(SearchFlightActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(SearchFlightActivity.this, R.color.colorPrimaryDark));
                }
                try {
                    String GetError = "";
                    if (responsSearchFlight.getErrors() != null) {
                        // if (!GetAirportsResult.getString("Errors").equals("null")) {
                        GetError = responsSearchFlight.getErrors().get(0).getDetailedMessage();
                    }
                    if (GetError.length() > 1) {
                        if (GetError.contains("|")) {
                            String[] s = GetError.split(Pattern.quote("|"));
                            linear_expand = findViewById(R.id.linear_expand);
                            linear_expand.setVisibility(View.GONE);
                            RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                            txtNoResult.setText(s[1] + "");
                            linear_no_result.setVisibility(View.VISIBLE);
                        } else {
                            linear_expand = findViewById(R.id.linear_expand);
                            linear_expand.setVisibility(View.GONE);
                            RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                            txtNoResult.setText(GetError);
                            linear_no_result.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (Locale.getDefault().getLanguage().equals("fa")) {
                            GetFlightFa(responsSearchFlight);
                        } else  {
                            getFlightEn(responsSearchFlight);
                        }
                        //Add to list expanding :
                        showDataExpanding();
                        //dakheli khareji
                        //new AsyncCheckFlight().execute();
                        getAirLine();
                    }
                } catch (Exception e) {
                    linear_expand = findViewById(R.id.linear_expand);
                    linear_expand.setVisibility(View.GONE);
                    RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                    linear_no_result.setVisibility(View.VISIBLE);
                    if (!Utility.isNetworkAvailable(SearchFlightActivity.this)) {
                        txtNoResult.setText(R.string.InternetError);
                    } else {
                        txtNoResult.setText(R.string.ErrorServer);
                    }
                }
            }

            @Override
            public void onError(String message) {
                new InitUi().Loading(SearchFlightActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);//dismiss
                // Log.e("date", result);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(ContextCompat.getColor(SearchFlightActivity.this, R.color.colorPrimaryDark));
                }
                Log.e("changeFlight:", "onError: " + message);
                linear_expand = findViewById(R.id.linear_expand);
                linear_expand.setVisibility(View.GONE);
                RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                txtNoResult.setText(getString(R.string.ErrorServer));
                linear_no_result.setVisibility(View.VISIBLE);
            }
        }, requestChangeFlight);
    }

    private void getFlightEn(ResponseChangeFlight responsSearchFlight) {
        //Flights
        for (int i = 0; i < responsSearchFlight.getFlights().size(); i++) {
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.Flight jPricedItinerary = responsSearchFlight.getFlights().get(i);//jArray.getJSONObject(i);//
            Flight flight = new Flight();
            flight.setNonRefundable(jPricedItinerary.getNonRefundable());
            flight.setRemainSeats(jPricedItinerary.getRemainSeats());
            //SegmentList
            List<com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.SegmentList> ss = jPricedItinerary.getSegmentList();
            List<FlightSegment> SegmentList = new ArrayList<FlightSegment>();
            List<FlightSegmentTrue> SegmentListTrue = new ArrayList<FlightSegmentTrue>();
            List<FlightSegmentFalse> SegmentListFalse = new ArrayList<FlightSegmentFalse>();
            for (int i1 = 0; i1 < ss.size(); i1++) {
                com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.SegmentList jPricedIfdgtinerary = ss.get(i1);//
                FlightSegment flightSegment = new FlightSegment();



                flightSegment.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                flightSegment.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                flightSegment.setAirlineNameEn(jPricedIfdgtinerary.getAirlineNameEn());
                flightSegment.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameEn());
                flightSegment.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                flightSegment.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                flightSegment.setArrivalAirportNameEn(jPricedIfdgtinerary.getArrivalAirportNameEn());
                flightSegment.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameEn());
                flightSegment.setArrivalCityCode(jPricedIfdgtinerary.getArrivalCityCode());
                flightSegment.setArrivalCityNameEn(jPricedIfdgtinerary.getArrivalCityNameEn());
                flightSegment.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameEn());
                flightSegment.setArrivalCountryNameEn(jPricedIfdgtinerary.getArrivalCountryNameEn());
                flightSegment.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameEn());
                //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                flightSegment.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDateShamsi());
                flightSegment.setCabinClassCode(jPricedIfdgtinerary.getCabinClassCode());
                flightSegment.setCabinClassName(jPricedIfdgtinerary.getCabinClassName());
                flightSegment.setCabinClassNameFa(jPricedIfdgtinerary.getCabinClassName());
                flightSegment.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                flightSegment.setDepartureAirportNameEn(jPricedIfdgtinerary.getDepartureAirportNameEn());
                flightSegment.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameEn());
                flightSegment.setDepartureCityCode(jPricedIfdgtinerary.getDepartureCityCode());
                flightSegment.setDepartureCityNameEn(jPricedIfdgtinerary.getDepartureCityNameEn());
                flightSegment.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameEn());
                flightSegment.setDepartureCountryNameEn(jPricedIfdgtinerary.getDepartureCountryNameEn());
                flightSegment.setDepartureCountryNameFa(jPricedIfdgtinerary.getDepartureCountryNameEn());
                // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                flightSegment.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDateShamsi());
                flightSegment.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                flightSegment.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                flightSegment.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                flightSegment.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                flightSegment.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                flightSegment.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                flightSegment.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                flightSegment.setOperatingAirlineNameEn(jPricedIfdgtinerary.getOperatingAirlineNameEn() + "");
                try {
                    flightSegment.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                    flightSegment.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                } catch (Exception e) {
                }
                //List<flightSegment> SegmentList ;
                SegmentList.add(flightSegment);
                if (jPricedIfdgtinerary.getIsDepartureSegment()) {
                    FlightSegmentTrue flightSegmentTrue = new FlightSegmentTrue();
                    flightSegmentTrue.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                    flightSegmentTrue.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                    // flightSegmentTrue.setAirlineNameEn(jPricedIfdgtinerary.getString("AirlineNameEn"));
                    flightSegmentTrue.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameEn());
                    flightSegmentTrue.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                    flightSegmentTrue.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                    //flightSegmentTrue.setArrivalAirportNameEn(jPricedIfdgtinerary.getString("ArrivalAirportNameEn"));
                    flightSegmentTrue.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameEn());
                    //flightSegmentTrue.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
                    //  flightSegmentTrue.setArrivalCityNameEn(jPricedIfdgtinerary.getString("ArrivalCityNameEn"));
                    flightSegmentTrue.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameEn());
                    // flightSegmentTrue.setArrivalCountryNameEn(jPricedIfdgtinerary.getString("ArrivalCountryNameEn"));
                    flightSegmentTrue.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameEn());
                    //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                    flightSegmentTrue.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDateShamsi());
                    // flightSegmentTrue.setCabinClassCode(jPricedIfdgtinerary.getString("CabinClassCode"));
                    // flightSegmentTrue.setCabinClassName(jPricedIfdgtinerary.getString("CabinClassName"));
                    // flightSegmentTrue.setCabinClassNameFa(jPricedIfdgtinerary.getString("CabinClassName"));
                    flightSegmentTrue.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                    // flightSegmentTrue.setDepartureAirportNameEn(jPricedIfdgtinerary.getString("DepartureAirportNameEn"));
                    flightSegmentTrue.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameEn());
                    // flightSegmentTrue.setDepartureCityCode(jPricedIfdgtinerary.getString("DepartureCityCode"));
                    //flightSegmentTrue.setDepartureCityNameEn(jPricedIfdgtinerary.getString("DepartureCityNameEn"));
                    flightSegmentTrue.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameEn());
                    //flightSegmentTrue.setDepartureCountryNameEn(jPricedIfdgtinerary.getString("DepartureCountryNameEn"));
                    //flightSegmentTrue.setDepartureCountryNameFa(jPricedIfdgtinerary.getString("DepartureCountryNameFa"));
                    // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                    flightSegmentTrue.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDate());
                    flightSegmentTrue.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                    flightSegmentTrue.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                    flightSegmentTrue.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                    flightSegmentTrue.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                    flightSegmentTrue.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                    flightSegmentTrue.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                    flightSegmentTrue.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                    try {
                        flightSegmentTrue.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                        flightSegmentTrue.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                    } catch (Exception e) {
                    }
                    SegmentListTrue.add(flightSegmentTrue);
                } else {
                    FlightSegmentFalse flightSegmentTrue = new FlightSegmentFalse();
                    flightSegmentTrue.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                    flightSegmentTrue.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                    // flightSegmentTrue.setAirlineNameEn(jPricedIfdgtinerary.getString("AirlineNameEn"));
                    flightSegmentTrue.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameEn());
                    flightSegmentTrue.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                    flightSegmentTrue.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                    //flightSegmentTrue.setArrivalAirportNameEn(jPricedIfdgtinerary.getString("ArrivalAirportNameEn"));
                    flightSegmentTrue.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameEn());
                    //flightSegmentTrue.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
                    //  flightSegmentTrue.setArrivalCityNameEn(jPricedIfdgtinerary.getString("ArrivalCityNameEn"));
                    flightSegmentTrue.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameEn());
                    // flightSegmentTrue.setArrivalCountryNameEn(jPricedIfdgtinerary.getString("ArrivalCountryNameEn"));
                    flightSegmentTrue.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameEn());
                    //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                    flightSegmentTrue.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDate());
                    // flightSegmentTrue.setCabinClassCode(jPricedIfdgtinerary.getString("CabinClassCode"));
                    // flightSegmentTrue.setCabinClassName(jPricedIfdgtinerary.getString("CabinClassName"));
                    // flightSegmentTrue.setCabinClassNameFa(jPricedIfdgtinerary.getString("CabinClassNameFa"));
                    flightSegmentTrue.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                    // flightSegmentTrue.setDepartureAirportNameEn(jPricedIfdgtinerary.getString("DepartureAirportNameEn"));
                    flightSegmentTrue.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameEn());
                    flightSegmentTrue.setDepartureCityCode(jPricedIfdgtinerary.getDepartureCityCode());
                    //flightSegmentTrue.setDepartureCityNameEn(jPricedIfdgtinerary.getString("DepartureCityNameEn"));
                    flightSegmentTrue.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameEn());
                    //flightSegmentTrue.setDepartureCountryNameEn(jPricedIfdgtinerary.getString("DepartureCountryNameEn"));
                    //flightSegmentTrue.setDepartureCountryNameFa(jPricedIfdgtinerary.getString("DepartureCountryNameFa"));
                    // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                    flightSegmentTrue.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDateShamsi());
                    flightSegmentTrue.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                    flightSegmentTrue.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                    flightSegmentTrue.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                    flightSegmentTrue.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                    flightSegmentTrue.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                    flightSegmentTrue.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                    flightSegmentTrue.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                    try {
                        flightSegmentTrue.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                        flightSegmentTrue.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                    } catch (Exception e) {
                    }
                    SegmentListFalse.add(flightSegmentTrue);
                }
                flight.setSegmentList(SegmentList);
                flight.setSegmentListTrue(SegmentListTrue);
                flight.setSegmentListFalse(SegmentListFalse);
            }//for segment parvazha
            ///////////////////////////////////////
            //  Flight flight =new Flight();
            flight.setAdults(jPricedItinerary.getAdults()); //int Adults ;
            flight.setRemainSeats(jPricedItinerary.getRemainSeats());
            flight.setNonRefundable(jPricedItinerary.getNonRefundable());
            flight.setIsCharter(jPricedItinerary.getIsCharter());
            flight.setAccountID(jPricedItinerary.getAccountID());// AccountID;
            flight.setChilds(jPricedItinerary.getChilds());//AdlBaseFare
            flight.setFlightGUID(jPricedItinerary.getFlightGUID());
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.AdlBaseFare jAdlBaseFare = jPricedItinerary.getAdlBaseFare();
            PriceField priceField = new PriceField();
            priceField.setAmount(jAdlBaseFare.getAmount());
            priceField.setCurrencyCode(jAdlBaseFare.getCurrencyCode());
            flight.setAdlBaseFare(priceField);//AdlCost
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.AdlCost AdlCost = jPricedItinerary.getAdlCost();
            PriceField priceField2 = new PriceField();
            priceField2.setAmount(AdlCost.getAmount());
            priceField2.setCurrencyCode(AdlCost.getCurrencyCode());
            flight.setAdlCost(priceField2);//AdlTotalFare
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.AdlTotalFare AdlTotalFare = jPricedItinerary.getAdlTotalFare();
            PriceField priceField3 = new PriceField();
            priceField3.setAmount(AdlTotalFare.getAmount());
            priceField3.setCurrencyCode(AdlTotalFare.getCurrencyCode());
            flight.setAdlTotalFare(priceField3);//BaseFare
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.BaseFare BaseFare = jPricedItinerary.getBaseFare();
            PriceField priceField4 = new PriceField();
            priceField4.setAmount(BaseFare.getAmount());
            priceField4.setCurrencyCode(BaseFare.getCurrencyCode());
            flight.setBaseFare(priceField4);//ChdBaseFare
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.ChdBaseFare ChdBaseFare = jPricedItinerary.getChdBaseFare();
            PriceField priceField5 = new PriceField();
            priceField5.setAmount(ChdBaseFare.getAmount());
            priceField5.setCurrencyCode(ChdBaseFare.getCurrencyCode());
            flight.setChdBaseFare(priceField5);//BaseFare
            //  ChdCost  ChdTotalFare InfBaseFare InfCost InfTotalFare
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.ChdCost ChdCost = jPricedItinerary.getChdCost();
            PriceField priceField6 = new PriceField();
            priceField6.setAmount(ChdCost.getAmount());
            priceField6.setCurrencyCode(ChdCost.getCurrencyCode());
            flight.setChdCost(priceField6);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.ChdTotalFare ChdTotalFare = jPricedItinerary.getChdTotalFare();
            PriceField priceField7 = new PriceField();
            priceField7.setAmount(ChdTotalFare.getAmount());
            priceField7.setCurrencyCode(ChdTotalFare.getCurrencyCode());
            flight.setChdTotalFare(priceField7);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.InfBaseFare InfBaseFare = jPricedItinerary.getInfBaseFare();
            PriceField priceField8 = new PriceField();
            priceField8.setAmount(InfBaseFare.getAmount());
            priceField8.setCurrencyCode(InfBaseFare.getCurrencyCode());
            flight.setInfBaseFare(priceField8);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.InfCost InfCost = jPricedItinerary.getInfCost();
            PriceField priceField9 = new PriceField();
            priceField9.setAmount(InfCost.getAmount());
            priceField9.setCurrencyCode(InfCost.getCurrencyCode());
            flight.setInfCost(priceField9);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.InfTotalFare InfTotalFare = jPricedItinerary.getInfTotalFare();
            PriceField priceField10 = new PriceField();
            priceField10.setAmount(InfTotalFare.getAmount());
            priceField10.setCurrencyCode(InfTotalFare.getCurrencyCode());
            flight.setInfTotalFare(priceField10);//
            // Taxes TotalFare TotalFareCost
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.Taxes Taxes = jPricedItinerary.getTaxes();
            PriceField priceField11 = new PriceField();
            priceField11.setAmount(Taxes.getAmount());
            priceField11.setCurrencyCode(Taxes.getCurrencyCode());
            flight.setTaxes(priceField11);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.TotalFare TotalFare = jPricedItinerary.getTotalFare();
            PriceField priceField12 = new PriceField();
            priceField12.setAmount(TotalFare.getAmount());
            priceField12.setCurrencyCode(TotalFare.getCurrencyCode());
            flight.setTotalFare(priceField12);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.TotalFareCost TotalFareCost = jPricedItinerary.getTotalFareCost();
            PriceField priceField13 = new PriceField();
            priceField13.setAmount(TotalFareCost.getAmount());
            priceField13.setCurrencyCode(TotalFareCost.getCurrencyCode());
            flight.setTotalFareCost(priceField13);//

            flightsList.add(flight);
        }
    }

    private void GetFlightFa(ResponseChangeFlight responsSearchFlight) {
        //Flights
        for (int i = 0; i < responsSearchFlight.getFlights().size(); i++) {
            Flight flight = new Flight();
            flight.setNonRefundable(responsSearchFlight.getFlights().get(i).getNonRefundable());
            flight.setRemainSeats(responsSearchFlight.getFlights().get(i).getRemainSeats());
            //SegmentList
            //  JSONArray ss = jPricedItinerary.getJSONArray("SegmentList");
            List<FlightSegment> SegmentList = new ArrayList<FlightSegment>();
            List<FlightSegmentTrue> SegmentListTrue = new ArrayList<FlightSegmentTrue>();
            List<FlightSegmentFalse> SegmentListFalse = new ArrayList<FlightSegmentFalse>();
            for (int i1 = 0; i1 < responsSearchFlight.getFlights().get(i).getSegmentList().size(); i1++) {
                //SONObject jPricedIfdgtinerary = ss.getJSONObject(i1);//
                com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.SegmentList jPricedIfdgtinerary = responsSearchFlight.getFlights().get(i).getSegmentList().get(i1);
                FlightSegment flightSegment = new FlightSegment();
                flightSegment.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                flightSegment.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                flightSegment.setAirlineNameEn(jPricedIfdgtinerary.getAirlineNameEn());
                flightSegment.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameFa());
                flightSegment.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                flightSegment.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                flightSegment.setArrivalAirportNameEn(jPricedIfdgtinerary.getArrivalAirportNameEn());
                flightSegment.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameFa());
                flightSegment.setArrivalCityCode(jPricedIfdgtinerary.getArrivalCityCode());
                flightSegment.setArrivalCityNameEn(jPricedIfdgtinerary.getArrivalCityNameEn());
                flightSegment.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameFa());
                flightSegment.setArrivalCountryNameEn(jPricedIfdgtinerary.getArrivalCountryNameEn());
                flightSegment.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameFa());
                //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                flightSegment.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDateShamsi());
                flightSegment.setCabinClassCode(jPricedIfdgtinerary.getCabinClassCode());
                flightSegment.setCabinClassName(jPricedIfdgtinerary.getCabinClassName());
                flightSegment.setCabinClassNameFa(jPricedIfdgtinerary.getCabinClassNameFa());
                flightSegment.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                flightSegment.setDepartureAirportNameEn(jPricedIfdgtinerary.getDepartureAirportNameEn());
                flightSegment.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameFa());
                flightSegment.setDepartureCityCode(jPricedIfdgtinerary.getDepartureCityCode());
                flightSegment.setDepartureCityNameEn(jPricedIfdgtinerary.getDepartureCityNameEn());
                flightSegment.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameFa());
                flightSegment.setDepartureCountryNameEn(jPricedIfdgtinerary.getDepartureCountryNameEn());
                flightSegment.setDepartureCountryNameFa(jPricedIfdgtinerary.getDepartureCountryNameFa());
                // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                flightSegment.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDateShamsi());
                flightSegment.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                flightSegment.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                flightSegment.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                flightSegment.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                flightSegment.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                flightSegment.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                flightSegment.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                flightSegment.setOperatingAirlineNameEn(jPricedIfdgtinerary.getOperatingAirlineNameEn() + "");
                try {
                    flightSegment.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                    flightSegment.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                } catch (Exception e) {
                }
                //List<flightSegment> SegmentList ;
                SegmentList.add(flightSegment);
                if (jPricedIfdgtinerary.getIsDepartureSegment()) {
                    FlightSegmentTrue flightSegmentTrue = new FlightSegmentTrue();
                    flightSegmentTrue.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                    flightSegmentTrue.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                    // flightSegmentTrue.setAirlineNameEn(jPricedIfdgtinerary.getString("AirlineNameEn"));
                    flightSegmentTrue.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameFa());
                    flightSegmentTrue.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                    flightSegmentTrue.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                    //flightSegmentTrue.setArrivalAirportNameEn(jPricedIfdgtinerary.getString("ArrivalAirportNameEn"));
                    flightSegmentTrue.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameFa());
                    //flightSegmentTrue.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
                    //  flightSegmentTrue.setArrivalCityNameEn(jPricedIfdgtinerary.getString("ArrivalCityNameEn"));
                    flightSegmentTrue.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameFa());
                    // flightSegmentTrue.setArrivalCountryNameEn(jPricedIfdgtinerary.getString("ArrivalCountryNameEn"));
                    flightSegmentTrue.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameFa());
                    //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                    flightSegmentTrue.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDateShamsi());
                    // flightSegmentTrue.setCabinClassCode(jPricedIfdgtinerary.getString("CabinClassCode"));
                    // flightSegmentTrue.setCabinClassName(jPricedIfdgtinerary.getString("CabinClassName"));
                    // flightSegmentTrue.setCabinClassNameFa(jPricedIfdgtinerary.getString("CabinClassNameFa"));
                    flightSegmentTrue.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                    // flightSegmentTrue.setDepartureAirportNameEn(jPricedIfdgtinerary.getString("DepartureAirportNameEn"));
                    flightSegmentTrue.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameFa());
                    // flightSegmentTrue.setDepartureCityCode(jPricedIfdgtinerary.getString("DepartureCityCode"));
                    //flightSegmentTrue.setDepartureCityNameEn(jPricedIfdgtinerary.getString("DepartureCityNameEn"));
                    flightSegmentTrue.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameFa());
                    //flightSegmentTrue.setDepartureCountryNameEn(jPricedIfdgtinerary.getString("DepartureCountryNameEn"));
                    //flightSegmentTrue.setDepartureCountryNameFa(jPricedIfdgtinerary.getString("DepartureCountryNameFa"));
                    // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                    flightSegmentTrue.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDateShamsi());
                    flightSegmentTrue.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                    flightSegmentTrue.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                    flightSegmentTrue.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                    flightSegmentTrue.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                    flightSegmentTrue.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                    flightSegmentTrue.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                    flightSegmentTrue.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                    try {
                        flightSegmentTrue.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                        flightSegmentTrue.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                    } catch (Exception e) {
                    }
                    SegmentListTrue.add(flightSegmentTrue);
                } else {
                    FlightSegmentFalse flightSegmentTrue = new FlightSegmentFalse();
                    flightSegmentTrue.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                    flightSegmentTrue.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                    // flightSegmentTrue.setAirlineNameEn(jPricedIfdgtinerary.getString("AirlineNameEn"));
                    flightSegmentTrue.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameFa());
                    flightSegmentTrue.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                    flightSegmentTrue.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                    //flightSegmentTrue.setArrivalAirportNameEn(jPricedIfdgtinerary.getString("ArrivalAirportNameEn"));
                    flightSegmentTrue.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameFa());
                    //flightSegmentTrue.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
                    //  flightSegmentTrue.setArrivalCityNameEn(jPricedIfdgtinerary.getString("ArrivalCityNameEn"));
                    flightSegmentTrue.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameFa());
                    // flightSegmentTrue.setArrivalCountryNameEn(jPricedIfdgtinerary.getString("ArrivalCountryNameEn"));
                    flightSegmentTrue.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameFa());
                    //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                    flightSegmentTrue.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDateShamsi());
                    // flightSegmentTrue.setCabinClassCode(jPricedIfdgtinerary.getString("CabinClassCode"));
                    // flightSegmentTrue.setCabinClassName(jPricedIfdgtinerary.getString("CabinClassName"));
                    // flightSegmentTrue.setCabinClassNameFa(jPricedIfdgtinerary.getString("CabinClassNameFa"));
                    flightSegmentTrue.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                    // flightSegmentTrue.setDepartureAirportNameEn(jPricedIfdgtinerary.getString("DepartureAirportNameEn"));
                    flightSegmentTrue.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameFa());
                    flightSegmentTrue.setDepartureCityCode(jPricedIfdgtinerary.getDepartureCityCode());
                    //flightSegmentTrue.setDepartureCityNameEn(jPricedIfdgtinerary.getString("DepartureCityNameEn"));
                    flightSegmentTrue.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameFa());
                    //flightSegmentTrue.setDepartureCountryNameEn(jPricedIfdgtinerary.getString("DepartureCountryNameEn"));
                    //flightSegmentTrue.setDepartureCountryNameFa(jPricedIfdgtinerary.getString("DepartureCountryNameFa"));
                    // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                    flightSegmentTrue.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDateShamsi());
                    flightSegmentTrue.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                    flightSegmentTrue.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                    flightSegmentTrue.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                    flightSegmentTrue.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                    flightSegmentTrue.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                    flightSegmentTrue.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                    flightSegmentTrue.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                    try {
                        flightSegmentTrue.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                        flightSegmentTrue.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                    } catch (Exception e) {
                    }
                    SegmentListFalse.add(flightSegmentTrue);
                }
                flight.setSegmentList(SegmentList);
                flight.setSegmentListTrue(SegmentListTrue);
                flight.setSegmentListFalse(SegmentListFalse);
            }//for segment parvazha
            ///////////////////////////////////////
            //  Flight flight =new Flight();
            flight.setAdults(responsSearchFlight.getFlights().get(i).getAdults()); //int Adults ;
            flight.setRemainSeats(responsSearchFlight.getFlights().get(i).getRemainSeats()); //int Adults ;
            flight.setNonRefundable(responsSearchFlight.getFlights().get(i).getNonRefundable()); //int Adults ;
            flight.setIsCharter(responsSearchFlight.getFlights().get(i).getIsCharter()); //int Adults ;
            flight.setAccountID(responsSearchFlight.getFlights().get(i).getAccountID());// AccountID;
            flight.setChilds(responsSearchFlight.getFlights().get(i).getChilds());//AdlBaseFare
            flight.setFlightGUID(responsSearchFlight.getFlights().get(i).getFlightGUID());
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.AdlBaseFare jAdlBaseFare = responsSearchFlight.getFlights().get(i).getAdlBaseFare();
            PriceField priceField = new PriceField();
            priceField.setAmount(jAdlBaseFare.getAmount());
            priceField.setCurrencyCode(jAdlBaseFare.getCurrencyCode());
            flight.setAdlBaseFare(priceField);//AdlCost
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.AdlCost AdlCost = responsSearchFlight.getFlights().get(i).getAdlCost();
            PriceField priceField2 = new PriceField();
            priceField2.setAmount(AdlCost.getAmount());
            priceField2.setCurrencyCode(AdlCost.getCurrencyCode());
            flight.setAdlCost(priceField2);//AdlTotalFare
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.AdlTotalFare AdlTotalFare = responsSearchFlight.getFlights().get(i).getAdlTotalFare();
            PriceField priceField3 = new PriceField();
            priceField3.setAmount(AdlTotalFare.getAmount());
            priceField3.setCurrencyCode(AdlTotalFare.getCurrencyCode());
            flight.setAdlTotalFare(priceField3);//BaseFare
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.BaseFare BaseFare = responsSearchFlight.getFlights().get(i).getBaseFare();
            PriceField priceField4 = new PriceField();
            priceField4.setAmount(BaseFare.getAmount());
            priceField4.setCurrencyCode(BaseFare.getCurrencyCode());
            flight.setBaseFare(priceField4);//ChdBaseFare
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.ChdBaseFare ChdBaseFare = responsSearchFlight.getFlights().get(i).getChdBaseFare();
            PriceField priceField5 = new PriceField();
            priceField5.setAmount(ChdBaseFare.getAmount());
            priceField5.setCurrencyCode(ChdBaseFare.getCurrencyCode());
            flight.setChdBaseFare(priceField5);//BaseFare
            //  ChdCost  ChdTotalFare InfBaseFare InfCost InfTotalFare
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.ChdCost ChdCost = responsSearchFlight.getFlights().get(i).getChdCost();
            PriceField priceField6 = new PriceField();
            priceField6.setAmount(ChdCost.getAmount());
            priceField6.setCurrencyCode(ChdCost.getCurrencyCode());
            flight.setChdCost(priceField6);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.ChdTotalFare ChdTotalFare = responsSearchFlight.getFlights().get(i).getChdTotalFare();
            PriceField priceField7 = new PriceField();
            priceField7.setAmount(ChdTotalFare.getAmount());
            priceField7.setCurrencyCode(ChdTotalFare.getCurrencyCode());
            flight.setChdTotalFare(priceField7);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.InfBaseFare InfBaseFare = responsSearchFlight.getFlights().get(i).getInfBaseFare();
            PriceField priceField8 = new PriceField();
            priceField8.setAmount(InfBaseFare.getAmount());
            priceField8.setCurrencyCode(InfBaseFare.getCurrencyCode());
            flight.setInfBaseFare(priceField8);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.InfCost InfCost = responsSearchFlight.getFlights().get(i).getInfCost();
            PriceField priceField9 = new PriceField();
            priceField9.setAmount(InfCost.getAmount());
            priceField9.setCurrencyCode(InfCost.getCurrencyCode());
            flight.setInfCost(priceField9);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.InfTotalFare InfTotalFare = responsSearchFlight.getFlights().get(i).getInfTotalFare();
            PriceField priceField10 = new PriceField();
            priceField10.setAmount(InfTotalFare.getAmount());
            priceField10.setCurrencyCode(InfTotalFare.getCurrencyCode());
            flight.setInfTotalFare(priceField10);//
            // Taxes TotalFare TotalFareCost
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.Taxes Taxes = responsSearchFlight.getFlights().get(i).getTaxes();
            PriceField priceField11 = new PriceField();
            priceField11.setAmount(Taxes.getAmount());
            priceField11.setCurrencyCode(Taxes.getCurrencyCode());
            flight.setTaxes(priceField11);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.TotalFare TotalFare = responsSearchFlight.getFlights().get(i).getTotalFare();
            PriceField priceField12 = new PriceField();
            priceField12.setAmount(TotalFare.getAmount());
            priceField12.setCurrencyCode(TotalFare.getCurrencyCode());
            flight.setTotalFare(priceField12);//
            com.eligasht.service.model.newModel.hotelFlight.changeFlight.response.TotalFareCost TotalFareCost = responsSearchFlight.getFlights().get(i).getTotalFareCost();
            PriceField priceField13 = new PriceField();
            priceField13.setAmount(TotalFareCost.getAmount());
            priceField13.setCurrencyCode(TotalFareCost.getCurrencyCode());
            flight.setTotalFareCost(priceField13);//
            flightsList.add(flight);
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////
void getDataFaJson(com.eligasht.service.model.newModel.flight.searchFlight.response.ResponseSearchFlight responsSearchFlight) {
    try {
        String GetError = "";
        if (responsSearchFlight.getErrors().size()> 0 ) {
            // if (!GetAirportsResult.getString("Errors").equals("null")) {
            GetError = responsSearchFlight.getErrors().get(0).getDetailedMessage();
        }
        if (GetError.length() > 1) {
            if (GetError.contains("|")) {
                String[] s = GetError.split(Pattern.quote("|"));
                linear_expand = findViewById(R.id.linear_expand);
                linear_expand.setVisibility(View.GONE);
                RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                txtNoResult.setText(s[1] + "");
                linear_no_result.setVisibility(View.VISIBLE);
            } else {
                linear_expand = findViewById(R.id.linear_expand);
                linear_expand.setVisibility(View.GONE);
                RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                txtNoResult.setText(GetError);
                linear_no_result.setVisibility(View.VISIBLE);
            }
        } else {
            String ResultUniqID = null;//
            ResultUniqID = responsSearchFlight.getResultUniqID();
            globalResultUniqID = ResultUniqID;
            GetFlightFa(responsSearchFlight);
            ///////////Parvaz
            //Add to list expanding :
            showDataExpanding();
            //dakheli khareji
            //  new AsyncCheckFlight().execute();
          //  SendReqCheckFlight();
            if(responsSearchFlight.getFlights().size()>0){
               boolean IsDemostic = responsSearchFlight.getFlights().get(0).getIsDomestic();//false khareji true dakheli//false khareji true dakheli
                if (IsDemostic)
                    Prefs.putBoolean("IsDemostic", true);
                else
                    Prefs.putBoolean("IsDemostic", false);

                getAirLine();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}//end fa

    private void GetFlightFa(com.eligasht.service.model.newModel.flight.searchFlight.response.ResponseSearchFlight responsSearchFlight) {
        //Flights
        for (int i = 0; i < responsSearchFlight.getFlights().size(); i++) {
            Flight flight = new Flight();
            //SegmentList
            //  JSONArray ss = jPricedItinerary.getJSONArray("SegmentList");
            List<FlightSegment> SegmentList = new ArrayList<FlightSegment>();
            List<FlightSegmentTrue> SegmentListTrue = new ArrayList<FlightSegmentTrue>();
            List<FlightSegmentFalse> SegmentListFalse = new ArrayList<FlightSegmentFalse>();
            for (int i1 = 0; i1 < responsSearchFlight.getFlights().get(i).getSegmentList().size(); i1++) {
                //SONObject jPricedIfdgtinerary = ss.getJSONObject(i1);//
                com.eligasht.service.model.newModel.flight.searchFlight.response.SegmentList jPricedIfdgtinerary = responsSearchFlight.getFlights().get(i).getSegmentList().get(i1);
                FlightSegment flightSegment = new FlightSegment();
                flightSegment.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                flightSegment.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                flightSegment.setAirlineNameEn(jPricedIfdgtinerary.getAirlineNameEn());
                flightSegment.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameFa());
                flightSegment.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                flightSegment.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                flightSegment.setArrivalAirportNameEn(jPricedIfdgtinerary.getArrivalAirportNameEn());
                flightSegment.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameFa());
                flightSegment.setArrivalCityCode(jPricedIfdgtinerary.getArrivalCityCode());
                flightSegment.setArrivalCityNameEn(jPricedIfdgtinerary.getArrivalCityNameEn());
                flightSegment.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameFa());
                flightSegment.setArrivalCountryNameEn(jPricedIfdgtinerary.getArrivalCountryNameEn());
                flightSegment.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameFa());
                //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                flightSegment.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDateShamsi());
                flightSegment.setCabinClassCode(jPricedIfdgtinerary.getCabinClassCode());
                flightSegment.setCabinClassName(jPricedIfdgtinerary.getCabinClassName());
                flightSegment.setCabinClassNameFa(jPricedIfdgtinerary.getCabinClassNameFa());
                flightSegment.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                flightSegment.setDepartureAirportNameEn(jPricedIfdgtinerary.getDepartureAirportNameEn());
                flightSegment.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameFa());
                flightSegment.setDepartureCityCode(jPricedIfdgtinerary.getDepartureCityCode());
                flightSegment.setDepartureCityNameEn(jPricedIfdgtinerary.getDepartureCityNameEn());
                flightSegment.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameFa());
                flightSegment.setDepartureCountryNameEn(jPricedIfdgtinerary.getDepartureCountryNameEn());
                flightSegment.setDepartureCountryNameFa(jPricedIfdgtinerary.getDepartureCountryNameFa());
                // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                flightSegment.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDateShamsi());
                flightSegment.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                flightSegment.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                flightSegment.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                flightSegment.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                flightSegment.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                flightSegment.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                flightSegment.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                flightSegment.setOperatingAirlineNameEn(jPricedIfdgtinerary.getOperatingAirlineNameEn() + "");
                if (jPricedIfdgtinerary.getFreeBaggage() == null) {
                    flightSegment.setWeight("");
                } else {
                    flightSegment.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                    flightSegment.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                }
                //List<flightSegment> SegmentList ;
                SegmentList.add(flightSegment);
                if (jPricedIfdgtinerary.getIsDepartureSegment()) {
                    FlightSegmentTrue flightSegmentTrue = new FlightSegmentTrue();
                    flightSegmentTrue.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                    flightSegmentTrue.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                    // flightSegmentTrue.setAirlineNameEn(jPricedIfdgtinerary.getString("AirlineNameEn"));
                    flightSegmentTrue.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameFa());
                    flightSegmentTrue.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                    flightSegmentTrue.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                    //flightSegmentTrue.setArrivalAirportNameEn(jPricedIfdgtinerary.getString("ArrivalAirportNameEn"));
                    flightSegmentTrue.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameFa());
                    //flightSegmentTrue.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
                    //  flightSegmentTrue.setArrivalCityNameEn(jPricedIfdgtinerary.getString("ArrivalCityNameEn"));
                    flightSegmentTrue.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameFa());
                    // flightSegmentTrue.setArrivalCountryNameEn(jPricedIfdgtinerary.getString("ArrivalCountryNameEn"));
                    flightSegmentTrue.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameFa());
                    //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                    flightSegmentTrue.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDateShamsi());
                    // flightSegmentTrue.setCabinClassCode(jPricedIfdgtinerary.getString("CabinClassCode"));
                    // flightSegmentTrue.setCabinClassName(jPricedIfdgtinerary.getString("CabinClassName"));
                    // flightSegmentTrue.setCabinClassNameFa(jPricedIfdgtinerary.getString("CabinClassNameFa"));
                    flightSegmentTrue.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                    // flightSegmentTrue.setDepartureAirportNameEn(jPricedIfdgtinerary.getString("DepartureAirportNameEn"));
                    flightSegmentTrue.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameFa());
                    // flightSegmentTrue.setDepartureCityCode(jPricedIfdgtinerary.getString("DepartureCityCode"));
                    //flightSegmentTrue.setDepartureCityNameEn(jPricedIfdgtinerary.getString("DepartureCityNameEn"));
                    flightSegmentTrue.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameFa());
                    //flightSegmentTrue.setDepartureCountryNameEn(jPricedIfdgtinerary.getString("DepartureCountryNameEn"));
                    //flightSegmentTrue.setDepartureCountryNameFa(jPricedIfdgtinerary.getString("DepartureCountryNameFa"));
                    // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                    flightSegmentTrue.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDateShamsi());
                    flightSegmentTrue.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                    flightSegmentTrue.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                    flightSegmentTrue.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                    flightSegmentTrue.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                    flightSegmentTrue.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                    flightSegmentTrue.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                    flightSegmentTrue.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                    if (jPricedIfdgtinerary.getFreeBaggage() == null) {
                        flightSegmentTrue.setWeight("");
                    } else {
                        flightSegmentTrue.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                        flightSegmentTrue.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                    }
                    SegmentListTrue.add(flightSegmentTrue);
                } else {
                    FlightSegmentFalse flightSegmentTrue = new FlightSegmentFalse();
                    flightSegmentTrue.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                    flightSegmentTrue.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                    // flightSegmentTrue.setAirlineNameEn(jPricedIfdgtinerary.getString("AirlineNameEn"));
                    flightSegmentTrue.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameFa());
                    flightSegmentTrue.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                    flightSegmentTrue.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                    //flightSegmentTrue.setArrivalAirportNameEn(jPricedIfdgtinerary.getString("ArrivalAirportNameEn"));
                    flightSegmentTrue.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameFa());
                    //flightSegmentTrue.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
                    //  flightSegmentTrue.setArrivalCityNameEn(jPricedIfdgtinerary.getString("ArrivalCityNameEn"));
                    flightSegmentTrue.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameFa());
                    // flightSegmentTrue.setArrivalCountryNameEn(jPricedIfdgtinerary.getString("ArrivalCountryNameEn"));
                    flightSegmentTrue.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameFa());
                    //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                    flightSegmentTrue.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDateShamsi());
                    // flightSegmentTrue.setCabinClassCode(jPricedIfdgtinerary.getString("CabinClassCode"));
                    // flightSegmentTrue.setCabinClassName(jPricedIfdgtinerary.getString("CabinClassName"));
                    // flightSegmentTrue.setCabinClassNameFa(jPricedIfdgtinerary.getString("CabinClassNameFa"));
                    flightSegmentTrue.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                    // flightSegmentTrue.setDepartureAirportNameEn(jPricedIfdgtinerary.getString("DepartureAirportNameEn"));
                    flightSegmentTrue.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameFa());
                    flightSegmentTrue.setDepartureCityCode(jPricedIfdgtinerary.getDepartureCityCode());
                    //flightSegmentTrue.setDepartureCityNameEn(jPricedIfdgtinerary.getString("DepartureCityNameEn"));
                    flightSegmentTrue.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameFa());
                    //flightSegmentTrue.setDepartureCountryNameEn(jPricedIfdgtinerary.getString("DepartureCountryNameEn"));
                    //flightSegmentTrue.setDepartureCountryNameFa(jPricedIfdgtinerary.getString("DepartureCountryNameFa"));
                    // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                    flightSegmentTrue.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDateShamsi());
                    flightSegmentTrue.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                    flightSegmentTrue.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                    flightSegmentTrue.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                    flightSegmentTrue.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                    flightSegmentTrue.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                    flightSegmentTrue.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                    flightSegmentTrue.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                    if (jPricedIfdgtinerary.getFreeBaggage() == null) {
                        flightSegmentTrue.setWeight("");
                    } else {
                        flightSegmentTrue.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                        flightSegmentTrue.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                    }
                    SegmentListFalse.add(flightSegmentTrue);
                }
                flight.setSegmentList(SegmentList);
                flight.setSegmentListTrue(SegmentListTrue);
                flight.setSegmentListFalse(SegmentListFalse);
            }//for segment parvazha
            ///////////////////////////////////////
            //  Flight flight =new Flight();
            flight.setAdults(responsSearchFlight.getFlights().get(i).getAdults()); //int Adults ;
            flight.setRemainSeats(responsSearchFlight.getFlights().get(i).getRemainSeats()); //int Adults ;
            flight.setNonRefundable(responsSearchFlight.getFlights().get(i).isNonRefundable()); //int Adults ;
            flight.setIsCharter(responsSearchFlight.getFlights().get(i).getIsCharter()); //int Adults ;
            flight.setAccountID(responsSearchFlight.getFlights().get(i).getAccountID());// AccountID;
            flight.setChilds(responsSearchFlight.getFlights().get(i).getChilds());//AdlBaseFare
            flight.setFlightGUID(responsSearchFlight.getFlights().get(i).getFlightGUID());
            com.eligasht.service.model.newModel.flight.searchFlight.response.AdlBaseFare jAdlBaseFare = responsSearchFlight.getFlights().get(i).getAdlBaseFare();
            PriceField priceField = new PriceField();
            priceField.setAmount(jAdlBaseFare.getAmount());
            priceField.setCurrencyCode(jAdlBaseFare.getCurrencyCode());
            flight.setAdlBaseFare(priceField);//AdlCost
            com.eligasht.service.model.newModel.flight.searchFlight.response.AdlCost AdlCost = responsSearchFlight.getFlights().get(i).getAdlCost();
            PriceField priceField2 = new PriceField();
            priceField2.setAmount(AdlCost.getAmount());
            priceField2.setCurrencyCode(AdlCost.getCurrencyCode());
            flight.setAdlCost(priceField2);//AdlTotalFare
            com.eligasht.service.model.newModel.flight.searchFlight.response.AdlTotalFare AdlTotalFare = responsSearchFlight.getFlights().get(i).getAdlTotalFare();
            PriceField priceField3 = new PriceField();
            priceField3.setAmount(AdlTotalFare.getAmount());
            priceField3.setCurrencyCode(AdlTotalFare.getCurrencyCode());
            flight.setAdlTotalFare(priceField3);//BaseFare
            com.eligasht.service.model.newModel.flight.searchFlight.response.BaseFare BaseFare = responsSearchFlight.getFlights().get(i).getBaseFare();
            PriceField priceField4 = new PriceField();
            priceField4.setAmount(BaseFare.getAmount());
            priceField4.setCurrencyCode(BaseFare.getCurrencyCode());
            flight.setBaseFare(priceField4);//ChdBaseFare
            com.eligasht.service.model.newModel.flight.searchFlight.response.ChdBaseFare ChdBaseFare = responsSearchFlight.getFlights().get(i).getChdBaseFare();
            PriceField priceField5 = new PriceField();
            priceField5.setAmount(ChdBaseFare.getAmount());
            priceField5.setCurrencyCode(ChdBaseFare.getCurrencyCode());
            flight.setChdBaseFare(priceField5);//BaseFare
            //  ChdCost  ChdTotalFare InfBaseFare InfCost InfTotalFare
            com.eligasht.service.model.newModel.flight.searchFlight.response.ChdCost ChdCost = responsSearchFlight.getFlights().get(i).getChdCost();
            PriceField priceField6 = new PriceField();
            priceField6.setAmount(ChdCost.getAmount());
            priceField6.setCurrencyCode(ChdCost.getCurrencyCode());
            flight.setChdCost(priceField6);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.ChdTotalFare ChdTotalFare = responsSearchFlight.getFlights().get(i).getChdTotalFare();
            PriceField priceField7 = new PriceField();
            priceField7.setAmount(ChdTotalFare.getAmount());
            priceField7.setCurrencyCode(ChdTotalFare.getCurrencyCode());
            flight.setChdTotalFare(priceField7);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.InfBaseFare InfBaseFare = responsSearchFlight.getFlights().get(i).getInfBaseFare();
            PriceField priceField8 = new PriceField();
            priceField8.setAmount(InfBaseFare.getAmount());
            priceField8.setCurrencyCode(InfBaseFare.getCurrencyCode());
            flight.setInfBaseFare(priceField8);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.InfCost InfCost = responsSearchFlight.getFlights().get(i).getInfCost();
            PriceField priceField9 = new PriceField();
            priceField9.setAmount(InfCost.getAmount());
            priceField9.setCurrencyCode(InfCost.getCurrencyCode());
            flight.setInfCost(priceField9);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.InfTotalFare InfTotalFare = responsSearchFlight.getFlights().get(i).getInfTotalFare();
            PriceField priceField10 = new PriceField();
            priceField10.setAmount(InfTotalFare.getAmount());
            priceField10.setCurrencyCode(InfTotalFare.getCurrencyCode());
            flight.setInfTotalFare(priceField10);//
            // Taxes TotalFare TotalFareCost
            com.eligasht.service.model.newModel.flight.searchFlight.response.Taxes Taxes = responsSearchFlight.getFlights().get(i).getTaxes();
            PriceField priceField11 = new PriceField();
            priceField11.setAmount(Taxes.getAmount());
            priceField11.setCurrencyCode(Taxes.getCurrencyCode());
            flight.setTaxes(priceField11);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.TotalFare TotalFare = responsSearchFlight.getFlights().get(i).getTotalFare();
            PriceField priceField12 = new PriceField();
            priceField12.setAmount(TotalFare.getAmount());
            priceField12.setCurrencyCode(TotalFare.getCurrencyCode());
            flight.setTotalFare(priceField12);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.TotalFareCost TotalFareCost = responsSearchFlight.getFlights().get(i).getTotalFareCost();
            PriceField priceField13 = new PriceField();
            priceField13.setAmount(TotalFareCost.getAmount());
            priceField13.setCurrencyCode(TotalFareCost.getCurrencyCode());
            flight.setTotalFareCost(priceField13);//
            flightsList.add(flight);
        }
    }//end GeflightFa
    private void getDataEnJson(com.eligasht.service.model.newModel.flight.searchFlight.response.ResponseSearchFlight responsSearchFlight) {
        try {
            String GetError = "";
            if (responsSearchFlight.getErrors().size() >0) {
                // if (!GetAirportsResult.getString("Errors").equals("null")) {
                GetError = responsSearchFlight.getErrors().get(0).getDetailedMessage();
            }
            if (GetError.length() > 1) {
                if (GetError.contains("|")) {
                    String[] s = GetError.split(Pattern.quote("|"));
                    linear_expand = findViewById(R.id.linear_expand);
                    linear_expand.setVisibility(View.GONE);
                    RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                    txtNoResult.setText(s[1] + "");
                    linear_no_result.setVisibility(View.VISIBLE);
                } else {
                    linear_expand = findViewById(R.id.linear_expand);
                    linear_expand.setVisibility(View.GONE);
                    RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                    txtNoResult.setText(GetError);
                    linear_no_result.setVisibility(View.VISIBLE);
                }
            } else {
                String ResultUniqID = null;//
                ResultUniqID = responsSearchFlight.getResultUniqID();//GetAirportsResult.getString("ResultUniqID");
                globalResultUniqID = ResultUniqID;
                getFlightEn(responsSearchFlight);
                ///////////Parvaz
                //Add to list expanding :
                showDataExpanding();
                //dakheli khareji
                // new AsyncCheckFlight().execute();
               // SendReqCheckFlight();
                boolean IsDemostic = responsSearchFlight.getFlights().get(0).getIsDomestic();//false khareji true dakheli
                if (IsDemostic)
                    Prefs.putBoolean("IsDemostic", true);
                else
                    Prefs.putBoolean("IsDemostic", false);
                getAirLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end en

    private void getFlightEn(com.eligasht.service.model.newModel.flight.searchFlight.response.ResponseSearchFlight responsSearchFlight) {
        //Flights
        for (int i = 0; i < responsSearchFlight.getFlights().size(); i++) {
            com.eligasht.service.model.newModel.flight.searchFlight.response.Flight jPricedItinerary = responsSearchFlight.getFlights().get(i);//jArray.getJSONObject(i);//
            Flight flight = new Flight();
            //SegmentList
            List<com.eligasht.service.model.newModel.flight.searchFlight.response.SegmentList> ss = jPricedItinerary.getSegmentList();
            List<FlightSegment> SegmentList = new ArrayList<FlightSegment>();
            List<FlightSegmentTrue> SegmentListTrue = new ArrayList<FlightSegmentTrue>();
            List<FlightSegmentFalse> SegmentListFalse = new ArrayList<FlightSegmentFalse>();
            for (int i1 = 0; i1 < ss.size(); i1++) {
                com.eligasht.service.model.newModel.flight.searchFlight.response.SegmentList jPricedIfdgtinerary = ss.get(i1);//
                FlightSegment flightSegment = new FlightSegment();
                flightSegment.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                flightSegment.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                flightSegment.setAirlineNameEn(jPricedIfdgtinerary.getAirlineNameEn());
                flightSegment.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameEn());
                flightSegment.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                flightSegment.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                flightSegment.setArrivalAirportNameEn(jPricedIfdgtinerary.getArrivalAirportNameEn());
                flightSegment.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameEn());
                flightSegment.setArrivalCityCode(jPricedIfdgtinerary.getArrivalCityCode());
                flightSegment.setArrivalCityNameEn(jPricedIfdgtinerary.getArrivalCityNameEn());
                flightSegment.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameEn());
                flightSegment.setArrivalCountryNameEn(jPricedIfdgtinerary.getArrivalCountryNameEn());
                flightSegment.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameEn());
                //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                flightSegment.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDateShamsi());
                flightSegment.setCabinClassCode(jPricedIfdgtinerary.getCabinClassCode());
                flightSegment.setCabinClassName(jPricedIfdgtinerary.getCabinClassName());
                flightSegment.setCabinClassNameFa(jPricedIfdgtinerary.getCabinClassName());
                flightSegment.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                flightSegment.setDepartureAirportNameEn(jPricedIfdgtinerary.getDepartureAirportNameEn());
                flightSegment.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameEn());
                flightSegment.setDepartureCityCode(jPricedIfdgtinerary.getDepartureCityCode());
                flightSegment.setDepartureCityNameEn(jPricedIfdgtinerary.getDepartureCityNameEn());
                flightSegment.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameEn());
                flightSegment.setDepartureCountryNameEn(jPricedIfdgtinerary.getDepartureCountryNameEn());
                flightSegment.setDepartureCountryNameFa(jPricedIfdgtinerary.getDepartureCountryNameEn());
                // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                flightSegment.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDateShamsi());
                flightSegment.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                flightSegment.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                flightSegment.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                flightSegment.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                flightSegment.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                flightSegment.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                flightSegment.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                flightSegment.setOperatingAirlineNameEn(jPricedIfdgtinerary.getOperatingAirlineNameEn() + "");
                if (jPricedIfdgtinerary.getFreeBaggage() == null) {
                    flightSegment.setWeight("");
                } else {
                    flightSegment.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                    flightSegment.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                }
                //List<flightSegment> SegmentList ;
                SegmentList.add(flightSegment);
                if (jPricedIfdgtinerary.getIsDepartureSegment()) {
                    FlightSegmentTrue flightSegmentTrue = new FlightSegmentTrue();
                    flightSegmentTrue.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                    flightSegmentTrue.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                    // flightSegmentTrue.setAirlineNameEn(jPricedIfdgtinerary.getString("AirlineNameEn"));
                    flightSegmentTrue.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameEn());
                    flightSegmentTrue.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                    flightSegmentTrue.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                    //flightSegmentTrue.setArrivalAirportNameEn(jPricedIfdgtinerary.getString("ArrivalAirportNameEn"));
                    flightSegmentTrue.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameEn());
                    //flightSegmentTrue.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
                    //  flightSegmentTrue.setArrivalCityNameEn(jPricedIfdgtinerary.getString("ArrivalCityNameEn"));
                    flightSegmentTrue.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameEn());
                    // flightSegmentTrue.setArrivalCountryNameEn(jPricedIfdgtinerary.getString("ArrivalCountryNameEn"));
                    flightSegmentTrue.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameEn());
                    //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                    flightSegmentTrue.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDateShamsi());
                    // flightSegmentTrue.setCabinClassCode(jPricedIfdgtinerary.getString("CabinClassCode"));
                    // flightSegmentTrue.setCabinClassName(jPricedIfdgtinerary.getString("CabinClassName"));
                    // flightSegmentTrue.setCabinClassNameFa(jPricedIfdgtinerary.getString("CabinClassName"));
                    flightSegmentTrue.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                    // flightSegmentTrue.setDepartureAirportNameEn(jPricedIfdgtinerary.getString("DepartureAirportNameEn"));
                    flightSegmentTrue.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameEn());
                    // flightSegmentTrue.setDepartureCityCode(jPricedIfdgtinerary.getString("DepartureCityCode"));
                    //flightSegmentTrue.setDepartureCityNameEn(jPricedIfdgtinerary.getString("DepartureCityNameEn"));
                    flightSegmentTrue.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameEn());
                    //flightSegmentTrue.setDepartureCountryNameEn(jPricedIfdgtinerary.getString("DepartureCountryNameEn"));
                    //flightSegmentTrue.setDepartureCountryNameFa(jPricedIfdgtinerary.getString("DepartureCountryNameFa"));
                    // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                    flightSegmentTrue.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDate());
                    flightSegmentTrue.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                    flightSegmentTrue.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                    flightSegmentTrue.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                    flightSegmentTrue.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                    flightSegmentTrue.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                    flightSegmentTrue.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                    flightSegmentTrue.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                    if (jPricedIfdgtinerary.getFreeBaggage() == null) {
                        flightSegmentTrue.setWeight("");
                    } else {
                        flightSegmentTrue.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                        flightSegmentTrue.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                    }
                    SegmentListTrue.add(flightSegmentTrue);
                } else {
                    FlightSegmentFalse flightSegmentTrue = new FlightSegmentFalse();
                    flightSegmentTrue.setAirlineCode(jPricedIfdgtinerary.getAirlineCode());
                    flightSegmentTrue.setAirlineID(jPricedIfdgtinerary.getAirlineID());
                    // flightSegmentTrue.setAirlineNameEn(jPricedIfdgtinerary.getString("AirlineNameEn"));
                    flightSegmentTrue.setAirlineNameFa(jPricedIfdgtinerary.getAirlineNameEn());
                    flightSegmentTrue.setAirplaneName(jPricedIfdgtinerary.getAirplaneName());
                    flightSegmentTrue.setArrivalAirportCode(jPricedIfdgtinerary.getArrivalAirportCode());
                    //flightSegmentTrue.setArrivalAirportNameEn(jPricedIfdgtinerary.getString("ArrivalAirportNameEn"));
                    flightSegmentTrue.setArrivalAirportNameFa(jPricedIfdgtinerary.getArrivalAirportNameEn());
                    //flightSegmentTrue.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
                    //  flightSegmentTrue.setArrivalCityNameEn(jPricedIfdgtinerary.getString("ArrivalCityNameEn"));
                    flightSegmentTrue.setArrivalCityNameFa(jPricedIfdgtinerary.getArrivalCityNameEn());
                    // flightSegmentTrue.setArrivalCountryNameEn(jPricedIfdgtinerary.getString("ArrivalCountryNameEn"));
                    flightSegmentTrue.setArrivalCountryNameFa(jPricedIfdgtinerary.getArrivalCountryNameEn());
                    //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                    flightSegmentTrue.setArrivalDateShamsi(jPricedIfdgtinerary.getArrivalDate());
                    // flightSegmentTrue.setCabinClassCode(jPricedIfdgtinerary.getString("CabinClassCode"));
                    // flightSegmentTrue.setCabinClassName(jPricedIfdgtinerary.getString("CabinClassName"));
                    // flightSegmentTrue.setCabinClassNameFa(jPricedIfdgtinerary.getString("CabinClassNameFa"));
                    flightSegmentTrue.setDepartureAirportCode(jPricedIfdgtinerary.getDepartureAirportCode());
                    // flightSegmentTrue.setDepartureAirportNameEn(jPricedIfdgtinerary.getString("DepartureAirportNameEn"));
                    flightSegmentTrue.setDepartureAirportNameFa(jPricedIfdgtinerary.getDepartureAirportNameEn());
                    flightSegmentTrue.setDepartureCityCode(jPricedIfdgtinerary.getDepartureCityCode());
                    //flightSegmentTrue.setDepartureCityNameEn(jPricedIfdgtinerary.getString("DepartureCityNameEn"));
                    flightSegmentTrue.setDepartureCityNameFa(jPricedIfdgtinerary.getDepartureCityNameEn());
                    //flightSegmentTrue.setDepartureCountryNameEn(jPricedIfdgtinerary.getString("DepartureCountryNameEn"));
                    //flightSegmentTrue.setDepartureCountryNameFa(jPricedIfdgtinerary.getString("DepartureCountryNameFa"));
                    // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                    flightSegmentTrue.setDepartureDateShamsi(jPricedIfdgtinerary.getDepartureDateShamsi());
                    flightSegmentTrue.setFlightArrivalTime(jPricedIfdgtinerary.getFlightArrivalTime());
                    flightSegmentTrue.setFlightNumber(jPricedIfdgtinerary.getFlightNumber());
                    flightSegmentTrue.setFlightTime(jPricedIfdgtinerary.getFlightTime());
                    flightSegmentTrue.setFltDateDayOfWeek(jPricedIfdgtinerary.getFltDateDayOfWeek());
                    flightSegmentTrue.setFltDurationH(jPricedIfdgtinerary.getFltDurationH());
                    flightSegmentTrue.setFltDurationM(jPricedIfdgtinerary.getFltDurationM());
                    flightSegmentTrue.setIsDepartureSegment(jPricedIfdgtinerary.getIsDepartureSegment());
                    if (jPricedIfdgtinerary.getFreeBaggage() == null) {
                        flightSegmentTrue.setWeight("");
                        flightSegmentTrue.setPieces("");
                    } else {
                        flightSegmentTrue.setWeight(jPricedIfdgtinerary.getFreeBaggage().getWeight() + "");
                        flightSegmentTrue.setPieces(jPricedIfdgtinerary.getFreeBaggage().getPieces() + "");
                    }
                    SegmentListFalse.add(flightSegmentTrue);
                }
                flight.setSegmentList(SegmentList);
                flight.setSegmentListTrue(SegmentListTrue);
                flight.setSegmentListFalse(SegmentListFalse);
            }//for segment parvazha
            ///////////////////////////////////////
            //  Flight flight =new Flight();
            flight.setAdults(jPricedItinerary.getAdults()); //int Adults ;
            flight.setRemainSeats(jPricedItinerary.getRemainSeats()); //int Adults ;
            flight.setNonRefundable(jPricedItinerary.isNonRefundable()); //int Adults ;
            flight.setIsCharter(jPricedItinerary.getIsCharter()); //int Adults ;
            flight.setAccountID(jPricedItinerary.getAccountID());// AccountID;
            flight.setChilds(jPricedItinerary.getChilds());//AdlBaseFare
            flight.setFlightGUID(jPricedItinerary.getFlightGUID());
            com.eligasht.service.model.newModel.flight.searchFlight.response.AdlBaseFare jAdlBaseFare = jPricedItinerary.getAdlBaseFare();
            PriceField priceField = new PriceField();
            priceField.setAmount(jAdlBaseFare.getAmount());
            priceField.setCurrencyCode(jAdlBaseFare.getCurrencyCode());
            flight.setAdlBaseFare(priceField);//AdlCost
            com.eligasht.service.model.newModel.flight.searchFlight.response.AdlCost AdlCost = jPricedItinerary.getAdlCost();
            PriceField priceField2 = new PriceField();
            priceField2.setAmount(AdlCost.getAmount());
            priceField2.setCurrencyCode(AdlCost.getCurrencyCode());
            flight.setAdlCost(priceField2);//AdlTotalFare
            com.eligasht.service.model.newModel.flight.searchFlight.response.AdlTotalFare AdlTotalFare = jPricedItinerary.getAdlTotalFare();
            PriceField priceField3 = new PriceField();
            priceField3.setAmount(AdlTotalFare.getAmount());
            priceField3.setCurrencyCode(AdlTotalFare.getCurrencyCode());
            flight.setAdlTotalFare(priceField3);//BaseFare
            com.eligasht.service.model.newModel.flight.searchFlight.response.BaseFare BaseFare = jPricedItinerary.getBaseFare();
            PriceField priceField4 = new PriceField();
            priceField4.setAmount(BaseFare.getAmount());
            priceField4.setCurrencyCode(BaseFare.getCurrencyCode());
            flight.setBaseFare(priceField4);//ChdBaseFare
            com.eligasht.service.model.newModel.flight.searchFlight.response.ChdBaseFare ChdBaseFare = jPricedItinerary.getChdBaseFare();
            PriceField priceField5 = new PriceField();
            priceField5.setAmount(ChdBaseFare.getAmount());
            priceField5.setCurrencyCode(ChdBaseFare.getCurrencyCode());
            flight.setChdBaseFare(priceField5);//BaseFare
            //  ChdCost  ChdTotalFare InfBaseFare InfCost InfTotalFare
            com.eligasht.service.model.newModel.flight.searchFlight.response.ChdCost ChdCost = jPricedItinerary.getChdCost();
            PriceField priceField6 = new PriceField();
            priceField6.setAmount(ChdCost.getAmount());
            priceField6.setCurrencyCode(ChdCost.getCurrencyCode());
            flight.setChdCost(priceField6);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.ChdTotalFare ChdTotalFare = jPricedItinerary.getChdTotalFare();
            PriceField priceField7 = new PriceField();
            priceField7.setAmount(ChdTotalFare.getAmount());
            priceField7.setCurrencyCode(ChdTotalFare.getCurrencyCode());
            flight.setChdTotalFare(priceField7);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.InfBaseFare InfBaseFare = jPricedItinerary.getInfBaseFare();
            PriceField priceField8 = new PriceField();
            priceField8.setAmount(InfBaseFare.getAmount());
            priceField8.setCurrencyCode(InfBaseFare.getCurrencyCode());
            flight.setInfBaseFare(priceField8);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.InfCost InfCost = jPricedItinerary.getInfCost();
            PriceField priceField9 = new PriceField();
            priceField9.setAmount(InfCost.getAmount());
            priceField9.setCurrencyCode(InfCost.getCurrencyCode());
            flight.setInfCost(priceField9);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.InfTotalFare InfTotalFare = jPricedItinerary.getInfTotalFare();
            PriceField priceField10 = new PriceField();
            priceField10.setAmount(InfTotalFare.getAmount());
            priceField10.setCurrencyCode(InfTotalFare.getCurrencyCode());
            flight.setInfTotalFare(priceField10);//
            // Taxes TotalFare TotalFareCost
            com.eligasht.service.model.newModel.flight.searchFlight.response.Taxes Taxes = jPricedItinerary.getTaxes();
            PriceField priceField11 = new PriceField();
            priceField11.setAmount(Taxes.getAmount());
            priceField11.setCurrencyCode(Taxes.getCurrencyCode());
            flight.setTaxes(priceField11);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.TotalFare TotalFare = jPricedItinerary.getTotalFare();
            PriceField priceField12 = new PriceField();
            priceField12.setAmount(TotalFare.getAmount());
            priceField12.setCurrencyCode(TotalFare.getCurrencyCode());
            flight.setTotalFare(priceField12);//
            com.eligasht.service.model.newModel.flight.searchFlight.response.TotalFareCost TotalFareCost = jPricedItinerary.getTotalFareCost();
            PriceField priceField13 = new PriceField();
            priceField13.setAmount(TotalFareCost.getAmount());
            priceField13.setCurrencyCode(TotalFareCost.getCurrencyCode());
            flight.setTotalFareCost(priceField13);//
            flightsList.add(flight);
        }
    }//end GetFlightEn
}



