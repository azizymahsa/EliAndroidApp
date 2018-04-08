package com.eligasht.reservation.views.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.api.hotel.changeflight.ChangeFlightApi;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.Country;
import com.eligasht.reservation.models.hotel.api.changeflight.request.ChangeFlightApiRequest;
import com.eligasht.reservation.models.hotel.api.changeflight.request.Request;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.ModelCheckBox;
import com.eligasht.reservation.models.model.PinModelDetail;
import com.eligasht.reservation.models.model.PinModelHeader;
import com.eligasht.reservation.models.model.SearchParvazModelExp;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.adapters.ExpandableListAdapter;
import com.eligasht.reservation.views.adapters.SearchParvazPinAdapter;
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
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.DomesticFlight.RequestDomesticFlight;
import com.eligasht.service.model.flight.request.airPort.RequestAirports;
import com.eligasht.service.model.flight.request.searchFlight.RequestSearchFlight;
import com.eligasht.service.model.flight.response.DomesticFlight.GetIsDomesticResult;
import com.eligasht.service.model.flight.response.DomesticFlight.ResponseDomesticFlight;
import com.eligasht.service.model.flight.response.airPort.ResponsAirports;
import com.eligasht.service.model.flight.response.searchFlight.AdlBaseFare;
import com.eligasht.service.model.flight.response.searchFlight.AdlCost;
import com.eligasht.service.model.flight.response.searchFlight.AdlTotalFare;
import com.eligasht.service.model.flight.response.searchFlight.BaseFare;
import com.eligasht.service.model.flight.response.searchFlight.ChdBaseFare;
import com.eligasht.service.model.flight.response.searchFlight.ChdCost;
import com.eligasht.service.model.flight.response.searchFlight.ChdTotalFare;
import com.eligasht.service.model.flight.response.searchFlight.InfBaseFare;
import com.eligasht.service.model.flight.response.searchFlight.InfCost;
import com.eligasht.service.model.flight.response.searchFlight.InfTotalFare;
import com.eligasht.service.model.flight.response.searchFlight.ResponsSearchFlight;
import com.eligasht.service.model.flight.response.searchFlight.SegmentList;
import com.eligasht.service.model.flight.response.searchFlight.Taxes;
import com.eligasht.service.model.flight.response.searchFlight.TotalFare;
import com.eligasht.service.model.flight.response.searchFlight.TotalFareCost;
import com.google.gson.Gson;
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


public class SearchParvazActivity extends BaseActivity implements SortFlightDialog.SortFlightDialogListener, FilterFlightDialogNew.FilterFlightDialogListenerArray, Header.onSearchTextChangedListener, OnItemClickListener, OnClickListener, OnItemSelectedListener, OnServiceStatus<ResponsSearchFlight>{
    //HashMap<String, HashMap<String, ItemExpanding>> listDataChildExpanding;
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
    public TextView lblMoratabSazi;
    public TextView txtRuzeBad, txtRuzeGhabl, iconFilter, txtFilter, txtNoResult;
    public String RaftF;
    public String BargashtF;
    public String Raft;
    public String Bargasht;
    public TextView txtDateOnvan, txtDateOnvanB, tvLoading, txticon, tvChangeFlight, txticonDate;
    public RelativeLayout linear_expand;
    public SearchParvazPinAdapter searchParvazPinAdapter;
    public ArrayList<ModelCheckBox> arrayTrue = new ArrayList<>();
    //onSearchTextChangedListener, OnClickListener, OnItemClickListener,FiltersChangedListener,OnItemSelectedListener
    //sort
    boolean besetSeler = false;
    boolean bestOff = false;
    //filter
    boolean bnoStop = false;
    boolean boneStop = false;
    boolean btwoStopMore = false;
    boolean beconomiF = false;
    boolean bbusinessF = false;
    boolean bferstF = false;
    ChangeFlightApi changeFlightApi;
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
    // HashMap<String,HashMap<String,HeaderExpandingPlan>> listDataHeaderExpanding;
    List<String> listDataHeaderExpanding;
    HashMap<String, HashMap<String, ItemExpandingPlan>> listDataChildExpanding;
    Window window;
    boolean isChangeFlight;
    String searchKey;
    String FlightId;
    LinearLayout llNextLastDays,llDateToolbar,llBottom,llSort;
    private ArrayList<ParentItemExpandingPlan> selectHotelModelArrayListBestSeler = new ArrayList<>();
    private ArrayList<FilterModelّFlight> filterModels = new ArrayList<>();
    private ExpandableListAdapter listAdapterExpanding;

    public static void updateAdapterPin(List<PinModelDetail> pinModelDetails, List<PinModelHeader> pinModelHeaders, Context activity) {
        // TODO Auto-generated method stub
        // recyclerViewFlight = (RecyclerView) findViewById(R.id.recyclerViewPassenger);
        recyclerViewFlight.addItemDecoration(new DividerItemDecoration(activity, 1));
        recyclerViewFlight.setLayoutManager(new LinearLayoutManager(activity));
        //ArrayList<PinModelDetail> pinModelDetails = new ArrayList<>();
        //ArrayList<PinModelHeader> pinModelHeaders = new ArrayList<>();
        //JSONArray jArray5 = jArray.getJSONArray("PreFactorFlights");

		/*for (int i = 0; i < jArray5.length(); i++) {

			pinModelDetails.add(new PinModelDetail(jArray5.getJSONObject(i).getString("AirlineNameFa"),
					jArray5.getJSONObject(i).getString("DepAirPortFa"),
					jArray5.getJSONObject(i).getString("ArrAirPortFa"),
					Utility.dateShow(jArray5.getJSONObject(i).getString("FltDate")),
					jArray5.getJSONObject(i).getString("FltTime"),
					//Utility.dateShow(jArray5.getJSONObject(i).getString("FltCheckinTime")),
					jArray5.getJSONObject(i).getString("FltCheckinTime"),

					jArray5.getJSONObject(i).getString("FltNumber"),
					jArray5.getJSONObject(i).getString("AirlineNameFa"),
					jArray5.getJSONObject(i).getString("DepartureCityFa")));

		}*/
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
        llBottom= findViewById(R.id.llBottom);
        llBottom.setOnClickListener(this);
        llSort= findViewById(R.id.llSort);
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
        txtFilter = findViewById(R.id.txtFilter);

        txtRuzeBad = findViewById(R.id.txtRuzeBad);
        txtRuzeBad.setOnClickListener(this);


        btn_no_Result = findViewById(R.id.btn_no_Result);
        btn_no_Result.setCustomTextFont(getResources().getString(R.string.iran_sans_normal_ttf));
        ;
        llNextLastDays = findViewById(R.id.llNextLastDays);
        btn_no_Result.setOnClickListener(this);

        txtRuzeGhabl = findViewById(R.id.txtRuzeGhabl);
        txtRuzeGhabl.setOnClickListener(this);

        lblMoratabSazi = findViewById(R.id.lblMoratabSazi);
        //lblMoratabSazi.setOnClickListener(this);

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

            //txtCityRaft.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
            txtCityRaft.setText(mabdaf);//sdfsdf

            //txtCityBargashtt.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
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
                        txtDateOnvanB.setText(RaftF);
                    } else {
                        txtDateOnvan.setText(BargashtF);
                        txtDateOnvanB.setText(RaftF);
                        // txtDateOnvan.setText(RaftF + "  -  " + BargashtF);
                    }
                }

                System.out.println("txtCityBargasht" + maghsadf + "txtCityRaft" + mabdaf);
            } catch (Exception e) {
            }

        }
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
        expandingListData();


        listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding);

        // setting list adapter
        expListViewExpanding.setAdapter(listAdapterExpanding);

        // Listview Group click listener
        expListViewExpanding.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

//                Log.d("TAG", "onGroupClick: clicked");


                        /* Toast.makeText(getApplicationContext(),
                         "Group Clicked " + listDataHeader.get(groupPosition),
	    				 Toast.LENGTH_SHORT).show();*/
                return false;
            }
        });

        // Listview Group expanded listener
        expListViewExpanding.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                //Toast.makeText(SearchParvazActivity.this,listDataHeaderExpanding.get(groupPosition) + " Expanded",Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onGroupExpand: ");
            }
        });

        // Listview Group collasped listener
        expListViewExpanding.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.d("TAG", "onGroupCollapse: ");
                //Toast.makeText(SearchParvazActivity.this,listDataHeaderExpanding.get(groupPosition) + " Collapsed",Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListViewExpanding.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Log.d("TAG", "onChildClick: ");
                        /*Toast.makeText(SearchParvazActivity.this,
                                 listDataHeaderExpanding.get(groupPosition)
	     								+ " : "
	     								+ listDataChildExpanding.get(listDataHeaderExpanding.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();*/


                return false;
            }
        });

///////////////////Pin
        Utility.init_floating_flight(expListViewExpanding, this);

        //for flight==================================================================================
        recyclerViewFlight = findViewById(R.id.recyclerViewPassenger);
        recyclerViewFlight.addItemDecoration(new DividerItemDecoration(SearchParvazActivity.this, 1));
        recyclerViewFlight.setLayoutManager(new LinearLayoutManager(SearchParvazActivity.this));
        ArrayList<PinModelDetail> pinModelDetails = new ArrayList<>();
        ArrayList<PinModelHeader> pinModelHeaders = new ArrayList<>();

        if (!pinModelDetails.isEmpty()) {
            recyclerViewFlight.setVisibility(View.VISIBLE);
            recyclerViewFlight.setAdapter(new SearchParvazPinAdapter(pinModelDetails, pinModelHeaders));

        }
        ////////EndPin


        if (isChangeFlight) {
            new ChangeFlightAsync().execute();
            Log.e("ch1", "onCreate: ");
        } else {

           // new AsyncFetch().execute();
            sendRequest();
            Log.e("ch2", "onCreate: ");

        }
        // and get whatever type user account id is

    }//end oncreat======================================================================================

    private void sendRequest() {
        ///////////////////////////////////////////////////////////
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

            window.setStatusBarColor(ContextCompat.getColor(SearchParvazActivity.this, R.color.status_loading));
        }


        new InitUi().Loading(SearchParvazActivity.this, rlLoading, rlRoot, true, R.drawable.flight_loading);

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

                String flagWay = extras.getString("Value-Flag-Two");

                String adlCount = extras.getString("Value-AdlCount");
                String chdCount = extras.getString("Value-ChdCount");
                String infCount = extras.getString("Value-InfCount");
                //Global variable count mosafer
                COUNT_B = Integer.parseInt(adlCount);
                COUNT_K = Integer.parseInt(chdCount);
                COUNT_N = Integer.parseInt(infCount);

                RequestSearchFlight requestSearchFlight = new RequestSearchFlight();
                com.eligasht.service.model.flight.request.searchFlight.Request request = new com.eligasht.service.model.flight.request.searchFlight.Request();

                com.eligasht.service.model.flight.request.searchFlight.Identity identity = new com.eligasht.service.model.flight.request.searchFlight.Identity();
                //identity":{"Password":"123qwe!@#QWE","TermianlId":"Mobile","jUserName":"EligashtMlb"}
                request.setIdentity(identity);

                request.setDepartureAirportcode(mabdaf);
                request.setArrivalAirportcode(maghsadf);
                request.setDepartureDate(Utility.convertNumbersToEnglish(Raft));
                request.setArrivalDate(Utility.convertNumbersToEnglish(Bargasht));
                request.setOneWay(flagWay);// اگر فقط رفت باشد عدد یک و در صورت رفت و برگشت عدد 2 را ارسال بفرمایید
                request.setCabinClassCode("all");
                request.setAdlCount(Integer.parseInt(adlCount));
                request.setChdCount(Integer.parseInt(chdCount));
                request.setInfCount(Integer.parseInt(infCount));

                request.setCulture(getString(R.string.culture));
                requestSearchFlight.setRequest(request);

                SingletonService.getInstance().getFlightSearchService().flightSearchAvail(SearchParvazActivity.this,requestSearchFlight);
                System.out.println("maghsadf" + maghsadf + "mabda" + mabdaf + "flagWay" + flagWay + "aadlcount:" + adlCount + "Raft" + Raft + "Bargasht" + Bargasht);

            } else {
                RequestSearchFlight requestSearchFlight = new RequestSearchFlight();
                com.eligasht.service.model.flight.request.searchFlight.Request request = new com.eligasht.service.model.flight.request.searchFlight.Request();

                com.eligasht.service.model.flight.request.searchFlight.Identity identity = new com.eligasht.service.model.flight.request.searchFlight.Identity();
                //identity":{"Password":"123qwe!@#QWE","TermianlId":"Mobile","UserName":"EligashtMlb"}
                request.setIdentity(identity);

                request.setDepartureAirportcode("IST");
                request.setArrivalAirportcode("IKA");
                request.setDepartureDate("2017-12-24");
                request.setArrivalDate("2018-01-29");
                request.setOneWay("2");// اگر فقط رفت باشد عدد یک و در صورت رفت و برگشت عدد 2 را ارسال بفرمایید
                request.setCabinClassCode("all");
                request.setAdlCount(1);
                request.setChdCount(0);
                request.setInfCount(0);

                request.setCulture(getString(R.string.culture));
                requestSearchFlight.setRequest(request);

                SingletonService.getInstance().getFlightSearchService().flightSearchAvail(SearchParvazActivity.this,requestSearchFlight);

                //Global
                COUNT_B = 1;
                COUNT_K = 0;
                COUNT_N = 0;

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //////////////////////////////////////////////////////////

    }
        @Override
        public void onReady(ResponsSearchFlight responsSearchFlight) {
            if (flightsList != null) {
                flightsList.clear();
            }
            try {
                System.out.println("Response: " + responsSearchFlight.getSearchFlightsResult().getFlights().size());
                new InitUi().Loading(SearchParvazActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);//dismiss
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                    window.setStatusBarColor(ContextCompat.getColor(SearchParvazActivity.this, R.color.colorPrimaryDark));
                }

                if (Locale.getDefault().getLanguage().equals("fa")) {
                    getDataFaJson(responsSearchFlight);
                } else if (Locale.getDefault().getLanguage().equals("en")) {
                    getDataEnJson(responsSearchFlight);
                }
                if (responsSearchFlight.getSearchFlightsResult().getFlights().size()>0)
                 responsSearchFlight.getSearchFlightsResult().getFlights().get(0).getBaseFare();
            }catch (Exception e){
                    System.out.println("Exception: "+e);
             }
        }

        @Override
        public void onError(String message) {
            System.out.println("onError: "+message);
            new InitUi().Loading(SearchParvazActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);//dismiss
            // Log.e("date", result);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                window.setStatusBarColor(ContextCompat.getColor(SearchParvazActivity.this, R.color.colorPrimaryDark));
            }

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
                    //if(dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains(getString(R.string.Economi))){
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

                listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding);
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
            // foundFirst=false;

            //Toast.makeText(SearchParvazActivity.this, "هیچ موردی یافت نشد", Toast.LENGTH_LONG).show();
            ///////////////
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
            /////////////////
            //listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList,searchParvazPinAdapter);
            //expListViewExpanding.setAdapter(listAdapterExpanding);
        } else {

            listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingListFilter, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding);
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
                    public int compare(SearchParvazActivity.ParentItemExpandingPlan o1, SearchParvazActivity.ParentItemExpandingPlan o2) {
                        //return o1.Header.AirlineNameEa.compareToIgnoreCase(o2.Header.AirlineNameEa);
                        return Double.compare(o2.Header.AdlCost, o1.Header.AdlCost);
                    }
                });
                //////////////////////////////////////
                listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding);


                // setting list adapter
                expListViewExpanding.setAdapter(listAdapterExpanding);
                listAdapterExpanding.notifyDataSetChanged();
                break;
            case 2:
                besetSeler = false;//bestCoust
                bestOff = true;//lowCoust
                Collections.sort(dataExpandingList, new Comparator<ParentItemExpandingPlan>() {
                    @Override
                    public int compare(SearchParvazActivity.ParentItemExpandingPlan o1, SearchParvazActivity.ParentItemExpandingPlan o2) {
                        //return o1.Header.AirlineNameEa.compareToIgnoreCase(o2.Header.AirlineNameEa);
                        return Double.compare(o1.Header.AdlCost, o2.Header.AdlCost);
                    }
                });
                //////////////////////////////////////
                listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding);


                // setting list adapter
                expListViewExpanding.setAdapter(listAdapterExpanding);
                listAdapterExpanding.notifyDataSetChanged();

                break;
        }
    }



    private void expandingListData() {
        linear_expand = findViewById(R.id.linear_expand);
        linear_expand.setVisibility(View.VISIBLE);
        RelativeLayout linear_no_resultt = findViewById(R.id.linear_no_result);
        linear_no_resultt.setVisibility(View.GONE);
        //	listDataHeaderExpanding =new HashMap<String, HashMap<String,HeaderExpandingPlan>>();// new ArrayList<String>();
        listDataChildExpanding = new HashMap<String, HashMap<String, ItemExpandingPlan>>();
        dataExpandingList = new ArrayList<ParentItemExpandingPlan>();
        ArrayList<SearchParvazModelExp> searchParvazModelExps = new ArrayList<SearchParvazModelExp>();
        ArrayList<SearchParvazModelExp> searchParvazModelExps1 = new ArrayList<>();

        if (flightsList != null) {

            try {
                System.out.println("flightsList.size():" + flightsList.size());
                dataExpandingList = new ArrayList<ParentItemExpandingPlan>();
                for (int i = 0; i < flightsList.size(); i++) {

                    System.out.println("HEADER I=" + i);

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
                                //flightsList.get(i).getAdlCost().getAmount(),
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
                                ,SegmentListtrueAkhari
                                ,SegmentListFalseAkhari);//ArrivalCityNameEnR baraye sort bayad en bashe

                        //parentItem.Header.add(header);
                        parentItem.setHeader(header);

                        //fore Detail item
                        for (int j = 0; j < SegmentList.size(); j++) {
                            System.out.println("Detail j=" + j);

                            //////////
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
                                //flightsList.get(i).getAdlCost().getAmount(),
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
                                ,SegmentListtrueAkhari
                                ,SegmentListFalseAkhari );//ArrivalCityNameEnR baraye sort bayad en bashe


                        //parentItem.Header.add(header);
                        parentItem.setHeader(header);


                        //fore Detail item
                        for (int j = 0; j < SegmentList.size(); j++) {
                            System.out.println("Detail j=" + j);

                            //////////
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

                            item.FltDurationH=SegmentList.get(j).getFltDurationH();
                            item.FltDurationM=SegmentList.get(j).getFltDurationM();

                            parentItem.Items.add(item);

                        }

                        dataExpandingList.add(parentItem);
                    }
                }
            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

        }
        if (flightsList.size() == 0 || flightsList == null) {

            linear_expand.setVisibility(View.GONE);
            RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
            txtNoResult.setText(R.string.NoResult);
            linear_no_result.setVisibility(View.VISIBLE);
        }


    }//end expanding listdata

    private void getAirLine() {
        //	listDataHeaderExpanding =new HashMap<String, HashMap<String,HeaderExpandingPlan>>();// new ArrayList<String>();
        listDataChildExpanding = new HashMap<String, HashMap<String, ItemExpandingPlan>>();
        //dataExpandingList = new ArrayList<ParentItemExpandingPlan>();
        ArrayList<SearchParvazModelExp> searchParvazModelExps = new ArrayList<SearchParvazModelExp>();
        ArrayList<SearchParvazModelExp> searchParvazModelExps1 = new ArrayList<>();

        if (flightsList != null) {


            System.out.println("flightsList.size():" + flightsList.size());
            //dataExpandingList = new ArrayList<ParentItemExpandingPlan>();
            for (int i = 0; i < flightsList.size(); i++) {
                SegmentList = flightsList.get(i).getSegmentList();
                //FilterAirline filterAirline=new FilterAirline(SegmentList.get(0).getAirlineNameFa());

                //	filterAirlines.add(SegmentList.get(0).getAirlineNameFa(),false);


                airlineConstraint.add(SegmentList.get(0).getAirlineNameFa());


                Log.e("rrrrrrrr", SegmentList.get(0).getAirlineNameFa());
            }
            List<String> al = new ArrayList<>();
// add elements to al, including duplicates
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

                new FilterFlightDialogNew(SearchParvazActivity.this, filterModels, this, filterAirlines);

                break;

            case R.id.llSort://sort
                // custom dialog
                new SortFlightDialog(SearchParvazActivity.this, this, besetSeler, bestOff, remove);

                break;
            case R.id.txtRuzeBad:

                if (SingletonDate.getInstance().getStartDate().addOneDay()) {
                    //tvDate.setText(SingletonDate.getInstance().getStartDate().getDescription() + " - " + SingletonDate.getInstance().getEndDate().getDescription());
                    Raft = SingletonDate.getInstance().getStartDate().getFullGeo();
                    Bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();

                    RaftF = SingletonDate.getInstance().getStartDate().getDescription();
                    BargashtF = SingletonDate.getInstance().getEndDate().getDescription();

                    txtDateOnvanB.setText(RaftF);
                    txtDateOnvan.setText(BargashtF);
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

                    txtDateOnvanB.setText(RaftF);
                    txtDateOnvan.setText(BargashtF);
                    callApiDateNext();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.DatePickerError2,
                            Toast.LENGTH_SHORT).show();

                }



                break;

        }
    }

    private void callApiDateNext() {

      //  new AsyncFetch().execute();
        sendRequest();
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onResume() {
        Log.e("DEBUG", "onResume of SearchParvazActivity");
        super.onResume();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }





    public void showDataExpanding() {
        // preparing list data
        expandingListData();


        listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding);

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
        public  List<FlightSegmentTrue> segmentListtrueAkhari;
        public  List<FlightSegmentFalse> segmentListfalseAkhari;
      /*  public String FltDurationHR;
        public  String FltDurationMR;
        public String FltDurationHB;
        public  String FltDurationMB;*/

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
            /*this.FltDurationHR=FltDurationHR;
            this.FltDurationMR=FltDurationMR;
            this.FltDurationHB=FltDurationHB;
            this.FltDurationMB=FltDurationMB;*/
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
        public  String FltDurationM;




        public ItemExpandingPlan() {
            // TODO Auto-generated constructor stub
        }

    }

    private class ChangeFlightAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {

                window.setStatusBarColor(ContextCompat.getColor(SearchParvazActivity.this, R.color.status_loading));
            }

            new InitUi().Loading(SearchParvazActivity.this, rlLoading, rlRoot, true, R.drawable.flight_loading);
            Log.e("teschange", new Gson().toJson(new ChangeFlightApiRequest(new Request(FlightId,
                    searchKey, getString(R.string.culture), new Identity("EligashtMlb",
                    "123qwe!@#QWE", "Mobile")))));
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                changeFlightApi = new ChangeFlightApi(new ChangeFlightApiRequest(new Request(FlightId,
                        searchKey, getString(R.string.culture), new Identity("EligashtMlb",
                        "123qwe!@#QWE", "Mobile"))));


            } catch (Exception e) {

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            new InitUi().Loading(SearchParvazActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);


            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {

                window.setStatusBarColor(ContextCompat.getColor(SearchParvazActivity.this, R.color.colorPrimaryDark));
            }
            List<Country> data = new ArrayList<Country>();

            try {

                Log.e("Response", new Gson().toJson(changeFlightApi.changeFlightApiResponse));
                JSONObject jsonObj = new JSONObject(new Gson().toJson(changeFlightApi.changeFlightApiResponse));
                String GetError = "";
                JSONArray jError = null;

                JSONObject GetAirportsResult = jsonObj.getJSONObject("HotelPlusFlightChangeFltResult");
                Log.e("Response2", GetAirportsResult.toString());


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


                    JSONArray jArray = GetAirportsResult.getJSONArray("Flights");//


                    //Flights
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jPricedItinerary = jArray.getJSONObject(i);//
                        Flight flight = new Flight();
                        //SegmentList
                        JSONArray ss = jPricedItinerary.getJSONArray("SegmentList");

                        List<FlightSegment> SegmentList = new ArrayList<FlightSegment>();
                        List<FlightSegmentTrue> SegmentListTrue = new ArrayList<FlightSegmentTrue>();
                        List<FlightSegmentFalse> SegmentListFalse = new ArrayList<FlightSegmentFalse>();

                        for (int i1 = 0; i1 < ss.length(); i1++) {
                            JSONObject jPricedIfdgtinerary = ss.getJSONObject(i1);//
                            // for(int i=0;i<jArray.length();i++){

                            FlightSegment flightSegment = new FlightSegment();
                            flightSegment.setAirlineCode(jPricedIfdgtinerary.getString("AirlineCode"));
                            flightSegment.setAirlineID(jPricedIfdgtinerary.getInt("AirlineID"));
                            flightSegment.setAirlineNameEn(jPricedIfdgtinerary.getString("AirlineNameEn"));
                            flightSegment.setAirlineNameFa(jPricedIfdgtinerary.getString("AirlineNameFa"));
                            flightSegment.setAirplaneName(jPricedIfdgtinerary.getString("AirplaneName"));
                            flightSegment.setArrivalAirportCode(jPricedIfdgtinerary.getString("ArrivalAirportCode"));
                            flightSegment.setArrivalAirportNameEn(jPricedIfdgtinerary.getString("ArrivalAirportNameEn"));
                            flightSegment.setArrivalAirportNameFa(jPricedIfdgtinerary.getString("ArrivalAirportNameFa"));
                            // flightSegment.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
                            flightSegment.setArrivalCityNameEn(jPricedIfdgtinerary.getString("ArrivalCityNameEn"));

                            flightSegment.setArrivalCityNameFa(jPricedIfdgtinerary.getString("ArrivalCityNameFa"));
                            flightSegment.setArrivalCountryNameEn(jPricedIfdgtinerary.getString("ArrivalCountryNameEn"));
                            flightSegment.setArrivalCountryNameFa(jPricedIfdgtinerary.getString("ArrivalCountryNameFa"));
                            //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                            flightSegment.setArrivalDateShamsi(jPricedIfdgtinerary.getString("ArrivalDateShamsi"));
                            flightSegment.setCabinClassCode(jPricedIfdgtinerary.getString("CabinClassCode"));
                            flightSegment.setCabinClassName(jPricedIfdgtinerary.getString("CabinClassName"));
                            flightSegment.setCabinClassNameFa(jPricedIfdgtinerary.getString("CabinClassNameFa"));
                            flightSegment.setDepartureAirportCode(jPricedIfdgtinerary.getString("DepartureAirportCode"));
                            flightSegment.setDepartureAirportNameEn(jPricedIfdgtinerary.getString("DepartureAirportNameEn"));

                            flightSegment.setDepartureAirportNameFa(jPricedIfdgtinerary.getString("DepartureAirportNameFa"));
                            //    flightSegment.setDepartureCityCode(jPricedIfdgtinerary.getString("DepartureCityCode"));
                            flightSegment.setDepartureCityNameEn(jPricedIfdgtinerary.getString("DepartureCityNameEn"));
                            flightSegment.setDepartureCityNameFa(jPricedIfdgtinerary.getString("DepartureCityNameFa"));
                            flightSegment.setDepartureCountryNameEn(jPricedIfdgtinerary.getString("DepartureCountryNameEn"));
                            flightSegment.setDepartureCountryNameFa(jPricedIfdgtinerary.getString("DepartureCountryNameFa"));
                            // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                            flightSegment.setDepartureDateShamsi(jPricedIfdgtinerary.getString("DepartureDateShamsi"));
                            flightSegment.setFlightArrivalTime(jPricedIfdgtinerary.getString("FlightArrivalTime"));
                            flightSegment.setFlightNumber(jPricedIfdgtinerary.getString("FlightNumber"));

                            flightSegment.setFlightTime(jPricedIfdgtinerary.getString("FlightTime"));
                            flightSegment.setFltDateDayOfWeek(jPricedIfdgtinerary.getString("FltDateDayOfWeek"));
                            flightSegment.setFltDurationH(jPricedIfdgtinerary.getString("FltDurationH"));
                            flightSegment.setFltDurationM(jPricedIfdgtinerary.getString("FltDurationM"));
                            flightSegment.setIsDepartureSegment(jPricedIfdgtinerary.getBoolean("IsDepartureSegment"));
                            try {
                                flightSegment.setOperatingAirlineNameEn(jPricedIfdgtinerary.getString("OperatingAirlineNameEn"));

                            } catch (Exception e) {

                                flightSegment.setOperatingAirlineNameEn("null");


                            }
                            //List<flightSegment> SegmentList ;
                            SegmentList.add(flightSegment);

                            if (jPricedIfdgtinerary.getBoolean("IsDepartureSegment")) {
                                FlightSegmentTrue flightSegmentTrue = new FlightSegmentTrue();
                                flightSegmentTrue.setAirlineCode(jPricedIfdgtinerary.getString("AirlineCode"));
                                flightSegmentTrue.setAirlineID(jPricedIfdgtinerary.getInt("AirlineID"));
                                // flightSegmentTrue.setAirlineNameEn(jPricedIfdgtinerary.getString("AirlineNameEn"));
                                flightSegmentTrue.setAirlineNameFa(jPricedIfdgtinerary.getString("AirlineNameFa"));
                                flightSegmentTrue.setAirplaneName(jPricedIfdgtinerary.getString("AirplaneName"));
                                flightSegmentTrue.setArrivalAirportCode(jPricedIfdgtinerary.getString("ArrivalAirportCode"));
                                //flightSegmentTrue.setArrivalAirportNameEn(jPricedIfdgtinerary.getString("ArrivalAirportNameEn"));
                                flightSegmentTrue.setArrivalAirportNameFa(jPricedIfdgtinerary.getString("ArrivalAirportNameFa"));
                                //flightSegmentTrue.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
                                //  flightSegmentTrue.setArrivalCityNameEn(jPricedIfdgtinerary.getString("ArrivalCityNameEn"));

                                flightSegmentTrue.setArrivalCityNameFa(jPricedIfdgtinerary.getString("ArrivalCityNameFa"));
                                // flightSegmentTrue.setArrivalCountryNameEn(jPricedIfdgtinerary.getString("ArrivalCountryNameEn"));
                                flightSegmentTrue.setArrivalCountryNameFa(jPricedIfdgtinerary.getString("ArrivalCountryNameFa"));
                                //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                                flightSegmentTrue.setArrivalDateShamsi(jPricedIfdgtinerary.getString("ArrivalDateShamsi"));
                                // flightSegmentTrue.setCabinClassCode(jPricedIfdgtinerary.getString("CabinClassCode"));
                                // flightSegmentTrue.setCabinClassName(jPricedIfdgtinerary.getString("CabinClassName"));
                                // flightSegmentTrue.setCabinClassNameFa(jPricedIfdgtinerary.getString("CabinClassNameFa"));
                                flightSegmentTrue.setDepartureAirportCode(jPricedIfdgtinerary.getString("DepartureAirportCode"));
                                // flightSegmentTrue.setDepartureAirportNameEn(jPricedIfdgtinerary.getString("DepartureAirportNameEn"));

                                flightSegmentTrue.setDepartureAirportNameFa(jPricedIfdgtinerary.getString("DepartureAirportNameFa"));
                                // flightSegmentTrue.setDepartureCityCode(jPricedIfdgtinerary.getString("DepartureCityCode"));
                                //flightSegmentTrue.setDepartureCityNameEn(jPricedIfdgtinerary.getString("DepartureCityNameEn"));
                                flightSegmentTrue.setDepartureCityNameFa(jPricedIfdgtinerary.getString("DepartureCityNameFa"));
                                //flightSegmentTrue.setDepartureCountryNameEn(jPricedIfdgtinerary.getString("DepartureCountryNameEn"));
                                //flightSegmentTrue.setDepartureCountryNameFa(jPricedIfdgtinerary.getString("DepartureCountryNameFa"));
                                // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                                flightSegmentTrue.setDepartureDateShamsi(jPricedIfdgtinerary.getString("DepartureDateShamsi"));
                                flightSegmentTrue.setFlightArrivalTime(jPricedIfdgtinerary.getString("FlightArrivalTime"));
                                flightSegmentTrue.setFlightNumber(jPricedIfdgtinerary.getString("FlightNumber"));

                                flightSegmentTrue.setFlightTime(jPricedIfdgtinerary.getString("FlightTime"));
                                flightSegmentTrue.setFltDateDayOfWeek(jPricedIfdgtinerary.getString("FltDateDayOfWeek"));
                                //flightSegmentTrue.setFltDurationH(jPricedIfdgtinerary.getString("FltDurationH"));
                                // flightSegmentTrue.setFltDurationM(jPricedIfdgtinerary.getString("FltDurationM"));
                                flightSegmentTrue.setIsDepartureSegment(jPricedIfdgtinerary.getBoolean("IsDepartureSegment"));
                                SegmentListTrue.add(flightSegmentTrue);
                            } else {
                                FlightSegmentFalse flightSegmentTrue = new FlightSegmentFalse();
                                flightSegmentTrue.setAirlineCode(jPricedIfdgtinerary.getString("AirlineCode"));
                                flightSegmentTrue.setAirlineID(jPricedIfdgtinerary.getInt("AirlineID"));
                                // flightSegmentTrue.setAirlineNameEn(jPricedIfdgtinerary.getString("AirlineNameEn"));
                                flightSegmentTrue.setAirlineNameFa(jPricedIfdgtinerary.getString("AirlineNameFa"));
                                flightSegmentTrue.setAirplaneName(jPricedIfdgtinerary.getString("AirplaneName"));
                                flightSegmentTrue.setArrivalAirportCode(jPricedIfdgtinerary.getString("ArrivalAirportCode"));
                                //flightSegmentTrue.setArrivalAirportNameEn(jPricedIfdgtinerary.getString("ArrivalAirportNameEn"));
                                flightSegmentTrue.setArrivalAirportNameFa(jPricedIfdgtinerary.getString("ArrivalAirportNameFa"));
                                //flightSegmentTrue.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
                                //  flightSegmentTrue.setArrivalCityNameEn(jPricedIfdgtinerary.getString("ArrivalCityNameEn"));

                                flightSegmentTrue.setArrivalCityNameFa(jPricedIfdgtinerary.getString("ArrivalCityNameFa"));
                                // flightSegmentTrue.setArrivalCountryNameEn(jPricedIfdgtinerary.getString("ArrivalCountryNameEn"));
                                flightSegmentTrue.setArrivalCountryNameFa(jPricedIfdgtinerary.getString("ArrivalCountryNameFa"));
                                //  flightSegment.setArrivalDate(jPricedIfdgtinerary.getString("ArrivalDate"));az noe date convert mikhad
                                flightSegmentTrue.setArrivalDateShamsi(jPricedIfdgtinerary.getString("ArrivalDateShamsi"));
                                // flightSegmentTrue.setCabinClassCode(jPricedIfdgtinerary.getString("CabinClassCode"));
                                // flightSegmentTrue.setCabinClassName(jPricedIfdgtinerary.getString("CabinClassName"));
                                // flightSegmentTrue.setCabinClassNameFa(jPricedIfdgtinerary.getString("CabinClassNameFa"));
                                flightSegmentTrue.setDepartureAirportCode(jPricedIfdgtinerary.getString("DepartureAirportCode"));
                                // flightSegmentTrue.setDepartureAirportNameEn(jPricedIfdgtinerary.getString("DepartureAirportNameEn"));

                                flightSegmentTrue.setDepartureAirportNameFa(jPricedIfdgtinerary.getString("DepartureAirportNameFa"));
                                // flightSegmentTrue.setDepartureCityCode(jPricedIfdgtinerary.getString("DepartureCityCode"));
                                //flightSegmentTrue.setDepartureCityNameEn(jPricedIfdgtinerary.getString("DepartureCityNameEn"));
                                flightSegmentTrue.setDepartureCityNameFa(jPricedIfdgtinerary.getString("DepartureCityNameFa"));
                                //flightSegmentTrue.setDepartureCountryNameEn(jPricedIfdgtinerary.getString("DepartureCountryNameEn"));
                                //flightSegmentTrue.setDepartureCountryNameFa(jPricedIfdgtinerary.getString("DepartureCountryNameFa"));
                                // flightSegment.setDepartureDate(jPricedIfdgtinerary.getString("DepartureDate"));convert date mikhad
                                flightSegmentTrue.setDepartureDateShamsi(jPricedIfdgtinerary.getString("DepartureDateShamsi"));
                                flightSegmentTrue.setFlightArrivalTime(jPricedIfdgtinerary.getString("FlightArrivalTime"));
                                flightSegmentTrue.setFlightNumber(jPricedIfdgtinerary.getString("FlightNumber"));

                                flightSegmentTrue.setFlightTime(jPricedIfdgtinerary.getString("FlightTime"));
                                flightSegmentTrue.setFltDateDayOfWeek(jPricedIfdgtinerary.getString("FltDateDayOfWeek"));
                                //flightSegmentTrue.setFltDurationH(jPricedIfdgtinerary.getString("FltDurationH"));
                                // flightSegmentTrue.setFltDurationM(jPricedIfdgtinerary.getString("FltDurationM"));
                                flightSegmentTrue.setIsDepartureSegment(jPricedIfdgtinerary.getBoolean("IsDepartureSegment"));
                                SegmentListFalse.add(flightSegmentTrue);
                            }

                            flight.setSegmentList(SegmentList);
                            flight.setSegmentListTrue(SegmentListTrue);
                            flight.setSegmentListFalse(SegmentListFalse);
                        }//for segment parvazha

                        ///////////////////////////////////////
                        //  Flight flight =new Flight();
                        flight.setAdults(jPricedItinerary.getInt("Adults")); //int Adults ;

                        flight.setRemainSeats(jPricedItinerary.getInt("RemainSeats")); //int Adults ;
                        flight.setIsCharter(jPricedItinerary.getBoolean("IsCharter")); //int Adults ;

                        flight.setAccountID(jPricedItinerary.getString("AccountID"));// AccountID;

                        flight.setChilds(jPricedItinerary.getInt("Childs"));//AdlBaseFare

                        flight.setFlightGUID(jPricedItinerary.getString("FlightGUID"));

                        JSONObject jAdlBaseFare = jPricedItinerary.getJSONObject("AdlBaseFare");

                        PriceField priceField = new PriceField();
                        priceField.setAmount(jAdlBaseFare.getLong("Amount"));
                        priceField.setCurrencyCode(jAdlBaseFare.getString("CurrencyCode"));
                        flight.setAdlBaseFare(priceField);//AdlCost

                        JSONObject AdlCost = jPricedItinerary.getJSONObject("AdlCost");
                        PriceField priceField2 = new PriceField();
                        priceField2.setAmount(AdlCost.getLong("Amount"));
                        priceField2.setCurrencyCode(AdlCost.getString("CurrencyCode"));
                        flight.setAdlCost(priceField2);//AdlTotalFare

                        JSONObject AdlTotalFare = jPricedItinerary.getJSONObject("AdlTotalFare");
                        PriceField priceField3 = new PriceField();
                        priceField3.setAmount(AdlTotalFare.getLong("Amount"));
                        priceField3.setCurrencyCode(AdlTotalFare.getString("CurrencyCode"));
                        flight.setAdlTotalFare(priceField3);//BaseFare

                        JSONObject BaseFare = jPricedItinerary.getJSONObject("BaseFare");
                        PriceField priceField4 = new PriceField();
                        priceField4.setAmount(BaseFare.getLong("Amount"));
                        priceField4.setCurrencyCode(BaseFare.getString("CurrencyCode"));
                        flight.setBaseFare(priceField4);//ChdBaseFare

                        JSONObject ChdBaseFare = jPricedItinerary.getJSONObject("ChdBaseFare");
                        PriceField priceField5 = new PriceField();
                        priceField5.setAmount(ChdBaseFare.getLong("Amount"));
                        priceField5.setCurrencyCode(ChdBaseFare.getString("CurrencyCode"));
                        flight.setChdBaseFare(priceField5);//BaseFare

                        //  ChdCost  ChdTotalFare InfBaseFare InfCost InfTotalFare
                        JSONObject ChdCost = jPricedItinerary.getJSONObject("ChdCost");
                        PriceField priceField6 = new PriceField();
                        priceField6.setAmount(ChdCost.getLong("Amount"));
                        priceField6.setCurrencyCode(ChdCost.getString("CurrencyCode"));
                        flight.setChdCost(priceField6);//

                        JSONObject ChdTotalFare = jPricedItinerary.getJSONObject("ChdTotalFare");
                        PriceField priceField7 = new PriceField();
                        priceField7.setAmount(ChdTotalFare.getLong("Amount"));
                        priceField7.setCurrencyCode(ChdTotalFare.getString("CurrencyCode"));
                        flight.setChdTotalFare(priceField7);//

                        JSONObject InfBaseFare = jPricedItinerary.getJSONObject("InfBaseFare");
                        PriceField priceField8 = new PriceField();
                        priceField8.setAmount(InfBaseFare.getLong("Amount"));
                        priceField8.setCurrencyCode(InfBaseFare.getString("CurrencyCode"));
                        flight.setInfBaseFare(priceField8);//

                        JSONObject InfCost = jPricedItinerary.getJSONObject("InfCost");
                        PriceField priceField9 = new PriceField();
                        priceField9.setAmount(InfCost.getLong("Amount"));
                        priceField9.setCurrencyCode(InfCost.getString("CurrencyCode"));
                        flight.setInfCost(priceField9);//

                        JSONObject InfTotalFare = jPricedItinerary.getJSONObject("InfTotalFare");
                        PriceField priceField10 = new PriceField();
                        priceField10.setAmount(InfTotalFare.getLong("Amount"));
                        priceField10.setCurrencyCode(InfTotalFare.getString("CurrencyCode"));
                        flight.setInfTotalFare(priceField10);//

                        // Taxes TotalFare TotalFareCost
                        JSONObject Taxes = jPricedItinerary.getJSONObject("Taxes");
                        PriceField priceField11 = new PriceField();
                        priceField11.setAmount(Taxes.getLong("Amount"));
                        priceField11.setCurrencyCode(Taxes.getString("CurrencyCode"));
                        flight.setTaxes(priceField11);//

                        JSONObject TotalFare = jPricedItinerary.getJSONObject("TotalFare");
                        PriceField priceField12 = new PriceField();
                        priceField12.setAmount(TotalFare.getLong("Amount"));
                        priceField12.setCurrencyCode(TotalFare.getString("CurrencyCode"));
                        flight.setTotalFare(priceField12);//

                        JSONObject TotalFareCost = jPricedItinerary.getJSONObject("TotalFareCost");
                        PriceField priceField13 = new PriceField();
                        priceField13.setAmount(TotalFareCost.getLong("Amount"));
                        priceField13.setCurrencyCode(TotalFareCost.getString("CurrencyCode"));
                        flight.setTotalFareCost(priceField13);//


                        flightsList.add(flight);

                        //}//for segment parvazha
                    }      ///////////Parvaz
                    //Add to list expanding :

                    showDataExpanding();

                    //dakheli khareji
                    //new AsyncCheckFlight().execute();

                    getAirLine();
                }
            } catch (JSONException e) {//d/sfdsf
                //Toast.makeText(SearchParvazActivity.this, "ارتباط با سرور برقرار نشد !!", Toast.LENGTH_LONG).show();
                //if (flightsListFilter.size() == 0 || flightsListFilter== null) {
                linear_expand = findViewById(R.id.linear_expand);
                linear_expand.setVisibility(View.GONE);
                RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                linear_no_result.setVisibility(View.VISIBLE);

                if (!Utility.isNetworkAvailable(SearchParvazActivity.this)) {

                    txtNoResult.setText(R.string.InternetError);

                } else {

                    txtNoResult.setText(R.string.ErrorServer);

                }

                //}

            }

        }

        private void showDataExpanding() {
            // preparing list data
            expandingListData();


            listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList, searchParvazPinAdapter, isChangeFlight, searchKey, FlightId, expListViewExpanding);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //recyclerView.setAdapter(tweetAdapter);


                    // setting list adapter
                    expListViewExpanding.setAdapter(listAdapterExpanding);

                    // Listview Group click listener
                    expListViewExpanding.setOnGroupClickListener(new OnGroupClickListener() {

                        @Override
                        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                            Log.d("TAG", "onGroupClick: ");
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

        }

    }

    void getDataFaJson(ResponsSearchFlight responsSearchFlight) {
        try {
            String GetError = "";

           if( responsSearchFlight.getSearchFlightsResult().getErrors() != null){
           // if (!GetAirportsResult.getString("Errors").equals("null")) {
               GetError= responsSearchFlight.getSearchFlightsResult().getErrors().get(0).getDetailedMessage();


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
                ResultUniqID =responsSearchFlight.getSearchFlightsResult().getResultUniqID();

                globalResultUniqID = ResultUniqID;




                //Flights
                for (int i = 0; i <   responsSearchFlight.getSearchFlightsResult().getFlights().size(); i++) {
                   // JSONObject jPricedItinerary = jArray.getJSONObject(i);//
                 //   Flight jPricedItinerary = responsSearchFlight.getSearchFlightsResult().getFlights().get(i);
                    Flight flight = new Flight();
                    //SegmentList
                  //  JSONArray ss = jPricedItinerary.getJSONArray("SegmentList");

                    List<FlightSegment> SegmentList = new ArrayList<FlightSegment>();
                    List<FlightSegmentTrue> SegmentListTrue = new ArrayList<FlightSegmentTrue>();
                    List<FlightSegmentFalse> SegmentListFalse = new ArrayList<FlightSegmentFalse>();

                    for (int i1 = 0; i1 <   responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getSegmentList().size(); i1++) {
                        //SONObject jPricedIfdgtinerary = ss.getJSONObject(i1);//
                      SegmentList jPricedIfdgtinerary=  responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getSegmentList().get(i1);


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
                        flightSegment.setOperatingAirlineNameEn(jPricedIfdgtinerary.getOperatingAirlineNameEn());
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
                            SegmentListFalse.add(flightSegmentTrue);
                        }

                        flight.setSegmentList(SegmentList);
                        flight.setSegmentListTrue(SegmentListTrue);
                        flight.setSegmentListFalse(SegmentListFalse);
                    }//for segment parvazha

                    ///////////////////////////////////////
                    //  Flight flight =new Flight();
                    flight.setAdults(responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getAdults()); //int Adults ;

                    flight.setRemainSeats(responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getRemainSeats()); //int Adults ;
                    flight.setIsCharter(responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getIsCharter()); //int Adults ;

                    flight.setAccountID(responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getAccountID());// AccountID;

                    flight.setChilds(responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getChilds());//AdlBaseFare

                    flight.setFlightGUID(responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getFlightGUID());

                    AdlBaseFare jAdlBaseFare = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getAdlBaseFare();

                    PriceField priceField = new PriceField();
                    priceField.setAmount(jAdlBaseFare.getAmount());
                    priceField.setCurrencyCode(jAdlBaseFare.getCurrencyCode());
                    flight.setAdlBaseFare(priceField);//AdlCost

                    AdlCost AdlCost = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getAdlCost();
                    PriceField priceField2 = new PriceField();
                    priceField2.setAmount(AdlCost.getAmount());
                    priceField2.setCurrencyCode(AdlCost.getCurrencyCode());
                    flight.setAdlCost(priceField2);//AdlTotalFare

                    AdlTotalFare AdlTotalFare = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getAdlTotalFare();
                    PriceField priceField3 = new PriceField();
                    priceField3.setAmount(AdlTotalFare.getAmount());
                    priceField3.setCurrencyCode(AdlTotalFare.getCurrencyCode());
                    flight.setAdlTotalFare(priceField3);//BaseFare

                    BaseFare BaseFare = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getBaseFare();
                    PriceField priceField4 = new PriceField();
                    priceField4.setAmount(BaseFare.getAmount());
                    priceField4.setCurrencyCode(BaseFare.getCurrencyCode());
                    flight.setBaseFare(priceField4);//ChdBaseFare

                    ChdBaseFare ChdBaseFare = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getChdBaseFare();
                    PriceField priceField5 = new PriceField();
                    priceField5.setAmount(ChdBaseFare.getAmount());
                    priceField5.setCurrencyCode(ChdBaseFare.getCurrencyCode());
                    flight.setChdBaseFare(priceField5);//BaseFare

                    //  ChdCost  ChdTotalFare InfBaseFare InfCost InfTotalFare
                    ChdCost ChdCost = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getChdCost();
                    PriceField priceField6 = new PriceField();
                    priceField6.setAmount(ChdCost.getAmount());
                    priceField6.setCurrencyCode(ChdCost.getCurrencyCode());
                    flight.setChdCost(priceField6);//

                    ChdTotalFare ChdTotalFare = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getChdTotalFare();
                    PriceField priceField7 = new PriceField();
                    priceField7.setAmount(ChdTotalFare.getAmount());
                    priceField7.setCurrencyCode(ChdTotalFare.getCurrencyCode());
                    flight.setChdTotalFare(priceField7);//

                    InfBaseFare InfBaseFare = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getInfBaseFare();
                    PriceField priceField8 = new PriceField();
                    priceField8.setAmount(InfBaseFare.getAmount());
                    priceField8.setCurrencyCode(InfBaseFare.getCurrencyCode());
                    flight.setInfBaseFare(priceField8);//

                    InfCost InfCost = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getInfCost();
                    PriceField priceField9 = new PriceField();
                    priceField9.setAmount(InfCost.getAmount());
                    priceField9.setCurrencyCode(InfCost.getCurrencyCode());
                    flight.setInfCost(priceField9);//

                    InfTotalFare InfTotalFare = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getInfTotalFare();
                    PriceField priceField10 = new PriceField();
                    priceField10.setAmount(InfTotalFare.getAmount());
                    priceField10.setCurrencyCode(InfTotalFare.getCurrencyCode());
                    flight.setInfTotalFare(priceField10);//

                    // Taxes TotalFare TotalFareCost
                    Taxes Taxes = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getTaxes();
                    PriceField priceField11 = new PriceField();
                    priceField11.setAmount(Taxes.getAmount());
                    priceField11.setCurrencyCode(Taxes.getCurrencyCode());
                    flight.setTaxes(priceField11);//

                    TotalFare TotalFare = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getTotalFare();
                    PriceField priceField12 = new PriceField();
                    priceField12.setAmount(TotalFare.getAmount());
                    priceField12.setCurrencyCode(TotalFare.getCurrencyCode());
                    flight.setTotalFare(priceField12);//

                    TotalFareCost TotalFareCost = responsSearchFlight.getSearchFlightsResult().getFlights().get(i).getTotalFareCost();
                    PriceField priceField13 = new PriceField();
                    priceField13.setAmount(TotalFareCost.getAmount());
                    priceField13.setCurrencyCode(TotalFareCost.getCurrencyCode());
                    flight.setTotalFareCost(priceField13);//


                    flightsList.add(flight);

                    //}//for segment parvazha
                }      ///////////Parvaz
                //Add to list expanding :

                showDataExpanding();

                //dakheli khareji
             //  new AsyncCheckFlight().execute();
               SendReqCheckFlight();

                getAirLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end fa

    private void SendReqCheckFlight() {
        RequestDomesticFlight requestDomesticFlight= new RequestDomesticFlight();
        com.eligasht.service.model.flight.request.DomesticFlight.Request request = new com.eligasht.service.model.flight.request.DomesticFlight.Request();



        try {

            Bundle extras = getIntent().getExtras();
            String maghsadf = "IST";
            String mabdaf = "THR";
            if (extras != null) {
                maghsadf = extras.getString("Value-Maghsad-Airport-Code");
                mabdaf = extras.getString("Value-Mabda-Airport-Code");
            }

            request.setUserName("EligashtMlb");
            request.setPassword("123qwe!@#QWE");
            request.setTermianlId("Mobile");
            request.setCode(mabdaf);////inja esme forudgah mikhore
            request.setToCode(maghsadf);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        requestDomesticFlight.setRequest(request);

        SingletonService.getInstance().getCheckFlight().domesticFlightAvail(new OnServiceStatus<ResponseDomesticFlight>() {
            @Override
            public void onReady(ResponseDomesticFlight responseDomesticFlight) {
                System.out.println("DomesticResult:"+responseDomesticFlight.getGetIsDomesticResult().getIsDomestic());
                new InitUi().Loading(SearchParvazActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);//dismiss

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                    window.setStatusBarColor(ContextCompat.getColor(SearchParvazActivity.this, R.color.colorPrimaryDark));
                }

                List<Country> data = new ArrayList<Country>();



                    String GetError = "";
                    Object jError = null;

                    GetIsDomesticResult GetAirportsResult = responseDomesticFlight.getGetIsDomesticResult();//Error
                    if (GetAirportsResult.getErrors()!= null) {
                        jError = GetAirportsResult.getErrors();//

                        GetError = GetAirportsResult.getErrors().get(0).getMessage();//.getString("Message");
                    }
                    if (GetError.length() > 1) {
                        AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(SearchParvazActivity.this);
                        AlertDialogPassenger.setText(GetError, getString(R.string.massege));

                    } else {

                        boolean IsDemostic = GetAirportsResult.getIsDomestic();//false khareji true dakheli
                        if (IsDemostic)
                            Prefs.putBoolean("IsDemostic", true);
                        else
                            Prefs.putBoolean("IsDemostic", false);


                    }



            }

            @Override
            public void onError(String message) {
                System.out.println("onError: "+message);
                new InitUi().Loading(SearchParvazActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);//dismiss
                // Log.e("date", result);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

                    window.setStatusBarColor(ContextCompat.getColor(SearchParvazActivity.this, R.color.colorPrimaryDark));
                }
            }
        }, requestDomesticFlight);
        }


    private void getDataEnJson(ResponsSearchFlight responsSearchFlight) {
        try {

            String GetError = "";

            if( responsSearchFlight.getSearchFlightsResult().getErrors() != null){
                // if (!GetAirportsResult.getString("Errors").equals("null")) {
                GetError= responsSearchFlight.getSearchFlightsResult().getErrors().get(0).getDetailedMessage();


            }
            if (GetError.length() > 1) {
                if (GetError.contains("|")) {
                    String[] s = GetError.split(Pattern.quote("|"));

                    linear_expand = findViewById(R.id.linear_expand);
                    linear_expand.setVisibility(View.GONE);
                    RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                    txtNoResult.setText(s[1] + "");
                    linear_no_result.setVisibility(View.VISIBLE);
                } else  {
                    linear_expand = findViewById(R.id.linear_expand);
                    linear_expand.setVisibility(View.GONE);
                    RelativeLayout linear_no_result = findViewById(R.id.linear_no_result);
                    txtNoResult.setText(GetError);
                    linear_no_result.setVisibility(View.VISIBLE);
                }

            } else {
                String ResultUniqID = null;//

                ResultUniqID = responsSearchFlight.getSearchFlightsResult().getResultUniqID();//GetAirportsResult.getString("ResultUniqID");

                globalResultUniqID = ResultUniqID;

               // JSONArray jArray = GetAirportsResult.getJSONArray("Flights");//


                //Flights
                for (int i = 0; i < responsSearchFlight.getSearchFlightsResult().getFlights().size(); i++) {
                    com.eligasht.service.model.flight.response.searchFlight.Flight jPricedItinerary = responsSearchFlight.getSearchFlightsResult().getFlights().get(i);//jArray.getJSONObject(i);//

                    Flight flight = new Flight();
                    //SegmentList
                    List<SegmentList> ss = jPricedItinerary.getSegmentList();

                    List<FlightSegment> SegmentList = new ArrayList<FlightSegment>();
                    List<FlightSegmentTrue> SegmentListTrue = new ArrayList<FlightSegmentTrue>();
                    List<FlightSegmentFalse> SegmentListFalse = new ArrayList<FlightSegmentFalse>();

                    for (int i1 = 0; i1 < ss.size(); i1++) {
                        com.eligasht.service.model.flight.response.searchFlight.SegmentList jPricedIfdgtinerary = ss.get(i1);//

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
                        flightSegment.setOperatingAirlineNameEn(jPricedIfdgtinerary.getOperatingAirlineNameEn());
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
                    flight.setIsCharter(jPricedItinerary.getIsCharter()); //int Adults ;

                    flight.setAccountID(jPricedItinerary.getAccountID());// AccountID;

                    flight.setChilds(jPricedItinerary.getChilds());//AdlBaseFare

                    flight.setFlightGUID(jPricedItinerary.getFlightGUID());

                    AdlBaseFare jAdlBaseFare = jPricedItinerary.getAdlBaseFare();

                    PriceField priceField = new PriceField();
                    priceField.setAmount(jAdlBaseFare.getAmount());
                    priceField.setCurrencyCode(jAdlBaseFare.getCurrencyCode());
                    flight.setAdlBaseFare(priceField);//AdlCost

                    AdlCost AdlCost = jPricedItinerary.getAdlCost();
                    PriceField priceField2 = new PriceField();
                    priceField2.setAmount(AdlCost.getAmount());
                    priceField2.setCurrencyCode(AdlCost.getCurrencyCode());
                    flight.setAdlCost(priceField2);//AdlTotalFare

                    AdlTotalFare AdlTotalFare = jPricedItinerary.getAdlTotalFare();
                    PriceField priceField3 = new PriceField();
                    priceField3.setAmount(AdlTotalFare.getAmount());
                    priceField3.setCurrencyCode(AdlTotalFare.getCurrencyCode());
                    flight.setAdlTotalFare(priceField3);//BaseFare

                    BaseFare BaseFare = jPricedItinerary.getBaseFare();
                    PriceField priceField4 = new PriceField();
                    priceField4.setAmount(BaseFare.getAmount());
                    priceField4.setCurrencyCode(BaseFare.getCurrencyCode());
                    flight.setBaseFare(priceField4);//ChdBaseFare

                    ChdBaseFare ChdBaseFare = jPricedItinerary.getChdBaseFare();
                    PriceField priceField5 = new PriceField();
                    priceField5.setAmount(ChdBaseFare.getAmount());
                    priceField5.setCurrencyCode(ChdBaseFare.getCurrencyCode());
                    flight.setChdBaseFare(priceField5);//BaseFare

                    //  ChdCost  ChdTotalFare InfBaseFare InfCost InfTotalFare
                    ChdCost ChdCost = jPricedItinerary.getChdCost();
                    PriceField priceField6 = new PriceField();
                    priceField6.setAmount(ChdCost.getAmount());
                    priceField6.setCurrencyCode(ChdCost.getCurrencyCode());
                    flight.setChdCost(priceField6);//

                    ChdTotalFare ChdTotalFare = jPricedItinerary.getChdTotalFare();
                    PriceField priceField7 = new PriceField();
                    priceField7.setAmount(ChdTotalFare.getAmount());
                    priceField7.setCurrencyCode(ChdTotalFare.getCurrencyCode());
                    flight.setChdTotalFare(priceField7);//

                    InfBaseFare InfBaseFare = jPricedItinerary.getInfBaseFare();
                    PriceField priceField8 = new PriceField();
                    priceField8.setAmount(InfBaseFare.getAmount());
                    priceField8.setCurrencyCode(InfBaseFare.getCurrencyCode());
                    flight.setInfBaseFare(priceField8);//

                    InfCost InfCost = jPricedItinerary.getInfCost();
                    PriceField priceField9 = new PriceField();
                    priceField9.setAmount(InfCost.getAmount());
                    priceField9.setCurrencyCode(InfCost.getCurrencyCode());
                    flight.setInfCost(priceField9);//

                    InfTotalFare InfTotalFare = jPricedItinerary.getInfTotalFare();
                    PriceField priceField10 = new PriceField();
                    priceField10.setAmount(InfTotalFare.getAmount());
                    priceField10.setCurrencyCode(InfTotalFare.getCurrencyCode());
                    flight.setInfTotalFare(priceField10);//

                    // Taxes TotalFare TotalFareCost
                    Taxes Taxes = jPricedItinerary.getTaxes();
                    PriceField priceField11 = new PriceField();
                    priceField11.setAmount(Taxes.getAmount());
                    priceField11.setCurrencyCode(Taxes.getCurrencyCode());
                    flight.setTaxes(priceField11);//

                    TotalFare TotalFare = jPricedItinerary.getTotalFare();
                    PriceField priceField12 = new PriceField();
                    priceField12.setAmount(TotalFare.getAmount());
                    priceField12.setCurrencyCode(TotalFare.getCurrencyCode());
                    flight.setTotalFare(priceField12);//

                    TotalFareCost TotalFareCost = jPricedItinerary.getTotalFareCost();
                    PriceField priceField13 = new PriceField();
                    priceField13.setAmount(TotalFareCost.getAmount());
                    priceField13.setCurrencyCode(TotalFareCost.getCurrencyCode());
                    flight.setTotalFareCost(priceField13);//


                    flightsList.add(flight);

                    //}//for segment parvazha
                }      ///////////Parvaz
                //Add to list expanding :

                showDataExpanding();

                //dakheli khareji
               // new AsyncCheckFlight().execute();
                SendReqCheckFlight();
                getAirLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//end en

}



