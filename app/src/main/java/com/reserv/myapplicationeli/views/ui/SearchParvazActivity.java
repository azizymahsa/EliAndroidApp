package com.reserv.myapplicationeli.views.ui;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.icu.util.GregorianCalendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
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

import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.lost.flight.FlightPreFactorAdapter;
import com.reserv.myapplicationeli.lost.flight.FlightPreFactorModel;
import com.reserv.myapplicationeli.models.Country;
import com.reserv.myapplicationeli.models.hotel.adapter.FilterModel;
import com.reserv.myapplicationeli.models.model.ModelCheckBox;
import com.reserv.myapplicationeli.models.model.PinModelDetail;
import com.reserv.myapplicationeli.models.model.PinModelHeader;
import com.reserv.myapplicationeli.models.model.SearchParvazModelExp;
import com.reserv.myapplicationeli.models.model.SolarCalendar;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.tools.datetools.JalaliCalendar;
import com.reserv.myapplicationeli.views.activities.ContactUsActivity;
import com.reserv.myapplicationeli.views.activities.hotel.activity.SelectHotelActivity;
import com.reserv.myapplicationeli.views.adapters.ExpandableListAdapter;
import com.reserv.myapplicationeli.views.adapters.SearchParvazPinAdapter;
import com.reserv.myapplicationeli.views.components.Header;
import com.reserv.myapplicationeli.views.ui.OBGParvaz.Flight;
import com.reserv.myapplicationeli.views.ui.OBGParvaz.FlightSegment;
import com.reserv.myapplicationeli.views.ui.OBGParvaz.FlightSegmentFalse;
import com.reserv.myapplicationeli.views.ui.OBGParvaz.FlightSegmentTrue;
import com.reserv.myapplicationeli.views.ui.OBGParvaz.PriceField;

import com.reserv.myapplicationeli.views.ui.dialog.flight.FilterAirline;
import com.reserv.myapplicationeli.views.ui.dialog.flight.FilterFlightDialog;
import com.reserv.myapplicationeli.views.ui.dialog.flight.FilterFlightDialogNew;
import com.reserv.myapplicationeli.views.ui.dialog.flight.FilterModelّFlight;
import com.reserv.myapplicationeli.views.ui.dialog.flight.SortFlightDialog;
import com.reserv.myapplicationeli.views.ui.dialog.flight.StringAirLines;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.FilterHotelDialog;


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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import mehdi.sakout.fancybuttons.FancyButton;


public class SearchParvazActivity extends BaseActivity implements SortFlightDialog.SortFlightDialogListener, FilterFlightDialogNew.FilterFlightDialogListenerArray, Header.onSearchTextChangedListener, OnItemClickListener, OnClickListener, OnItemSelectedListener {
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

	boolean remove = false;
	private ArrayList<ParentItemExpandingPlan> selectHotelModelArrayListBestSeler = new ArrayList<>();

	List<Flight> flightsList = new ArrayList<Flight>();
	List<Flight> flightsListFilter = new ArrayList<Flight>();
	List<FlightSegmentTrue> SegmentListtrueAkhari = new ArrayList<FlightSegmentTrue>();
	List<FlightSegment> SegmentList = new ArrayList<FlightSegment>();
	List<FlightSegmentTrue> SegmentListtrueAvali = new ArrayList<FlightSegmentTrue>();
	List<FlightSegmentFalse> SegmentListFalseAvali = new ArrayList<FlightSegmentFalse>();
	List<FlightSegmentFalse> SegmentListFalseAkhari = new ArrayList<FlightSegmentFalse>();

	public ArrayList<ModelCheckBox> filterAirlines = new ArrayList<>();
	public ArrayList<String> airlineConstraint = new ArrayList<>();
	private ArrayList<FilterModelّFlight> filterModels = new ArrayList<>();

	public TextView  txtCityBargasht,txtCityRaft,txtCityBargashtt;
	public FancyButton txtBack,btnHome,txticon;

	public List<ParentItemExpandingPlan> dataExpandingList;
	public List<ParentItemExpandingPlan> dataExpandingListFilter = new ArrayList<>();
	public List<ParentItemExpandingPlan> dataExpandingListFilter2 = new ArrayList<>();

	public static boolean FlagRemove;
	private ExpandableListAdapter listAdapterExpanding;
	public ExpandableListView expListViewExpanding;
	public TextView lblMoratabSazi;
	public TextView txtRuzeBad, txtRuzeGhabl,iconFilter,txtFilter,txtNoResult;
	public static int COUNT_B;
	public static int COUNT_K;
	public static int COUNT_N;
	RelativeLayout rlLoading, rlRoot;
	Button btn_no_Result;
	// HashMap<String,HashMap<String,HeaderExpandingPlan>> listDataHeaderExpanding;
	List<String> listDataHeaderExpanding;
	HashMap<String, HashMap<String, ItemExpandingPlan>> listDataChildExpanding;
	//HashMap<String, HashMap<String, ItemExpanding>> listDataChildExpanding;
	public static final int CONNECTION_TIMEOUT = 10000;
	public static final int READ_TIMEOUT = 15000;
	public static String globalResultUniqID;
	public String DdateF;
	public String AdateF;
	public String Ddate;
	public String Adate;
	public TextView txtDateOnvan;
	public LinearLayout linear_expand ;
	public  SearchParvazPinAdapter searchParvazPinAdapter;
	public static RecyclerView recyclerViewFlight;
	Window window;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_parvaz);
		window = getWindow();
		linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			if (bundle.getBoolean("BACK_HOME") == true) {
				finish();
			}
		}

		rlLoading = findViewById(R.id.rlLoading);
		rlRoot = findViewById(R.id.rlRoot);
		iconFilter = findViewById(R.id.iconFilter);

		new AsyncFetch().execute();
		txtBack = (FancyButton) findViewById(R.id.txtBack);
		txtBack.setOnClickListener(this);
		txtBack.setCustomTextFont("fonts/icomoon.ttf");
		txtBack.setText(getString(R.string.search_back_right));

		btnHome = (FancyButton) findViewById(R.id.btnHome);
		btnHome.setOnClickListener(this);
		/*btnHome.setCustomTextFont("fonts/icomoon.ttf");
		btnHome.setText(getString(R.string.search_back_right));
		*/
		txtNoResult = (TextView) findViewById(R.id.txtNoResult);
		txtNoResult.setOnClickListener(this);
		txtFilter = (TextView) findViewById(R.id.txtFilter);
		txtFilter.setOnClickListener(this);

		txtRuzeBad = (TextView) findViewById(R.id.txtRuzeBad);
		txtRuzeBad.setOnClickListener(this);



		btn_no_Result = (Button) findViewById(R.id.btn_no_Result);
		btn_no_Result.setOnClickListener(this);

		txtRuzeGhabl = (TextView) findViewById(R.id.txtRuzeGhabl);
		txtRuzeGhabl.setOnClickListener(this);

		lblMoratabSazi = (TextView) findViewById(R.id.lblMoratabSazi);
		lblMoratabSazi.setOnClickListener(this);

		txtCityRaft = (TextView) findViewById(R.id.txtCityRaft);
		txtCityRaft.setOnClickListener(this);

		txticon = (FancyButton) findViewById(R.id.txticon);
		txticon.setOnClickListener(this);

		txtCityBargashtt = (TextView) findViewById(R.id.txtCityBargashtt);
		txtCityBargashtt.setOnClickListener(this);

		txtCityBargasht = (TextView) findViewById(R.id.txtCityBargasht);
		txtCityBargasht.setOnClickListener(this);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String maghsadf = extras.getString("Value-Maghsad-City");
			String mabdaf = extras.getString("Value-Mabda-City");

			//txtCityRaft.setCustomTextFont("fonts/iran_sans_normal.ttf");
			txtCityRaft.setText(mabdaf);//sdfsdf

			txticon.setCustomTextFont("fonts/fontello_plane.ttf");
			txticon.setText(getString(R.string.icon_plane));//sdfsdf

			//txtCityBargashtt.setCustomTextFont("fonts/iran_sans_normal.ttf");
			txtCityBargashtt.setText(maghsadf);//sdfsdf

			txtCityBargasht.setText(maghsadf + "");

			DdateF = extras.getString("Value-DepartureDate-format");
			AdateF = extras.getString("Value-ArrivalDate-format");

			Ddate = extras.getString("Value-DepartureDate");
			Adate = extras.getString("Value-ArrivalDate");

			txtDateOnvan = (TextView) findViewById(R.id.txtDateOnvan);
			txtDateOnvan.setText(DdateF + "  -  " + AdateF);

			System.out.println("txtCityBargasht" + maghsadf + "txtCityRaft" + mabdaf);
		}


		//expandin list
		// get the listview
		expListViewExpanding = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		expandingListData();


		listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList,searchParvazPinAdapter);

		// setting list adapter
		expListViewExpanding.setAdapter(listAdapterExpanding);

		// Listview Group click listener
		expListViewExpanding.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
										int groupPosition, long id) {




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
			}
		});

		// Listview Group collasped listener
		expListViewExpanding.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {

				//Toast.makeText(SearchParvazActivity.this,listDataHeaderExpanding.get(groupPosition) + " Collapsed",Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListViewExpanding.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
										int groupPosition, int childPosition, long id) {

						/*Toast.makeText(SearchParvazActivity.this,
                                 listDataHeaderExpanding.get(groupPosition)
	     								+ " : "
	     								+ listDataChildExpanding.get(listDataHeaderExpanding.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();*/


				return false;
			}
		});

///////////////////Pin

		//for flight==================================================================================
		  recyclerViewFlight = (RecyclerView) findViewById(R.id.recyclerViewPassenger);
		recyclerViewFlight.addItemDecoration(new DividerItemDecoration(SearchParvazActivity.this, 1));
		recyclerViewFlight.setLayoutManager(new LinearLayoutManager(SearchParvazActivity.this));
		ArrayList<PinModelDetail> pinModelDetails = new ArrayList<>();
		ArrayList<PinModelHeader> pinModelHeaders = new ArrayList<>();
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
			recyclerViewFlight.setAdapter(new SearchParvazPinAdapter(pinModelDetails,pinModelHeaders));

		}
		////////EndPin
	}//end oncreat======================================================================================
	public static void updateAdapterPin( List<PinModelDetail> pinModelDetails,List<PinModelHeader> pinModelHeaders,Context activity) {
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
			recyclerViewFlight.setAdapter(new SearchParvazPinAdapter(pinModelDetails,pinModelHeaders));

		}

	}
	@Override
	public void onReturnValueFlightNew(ArrayList<FilterModelّFlight> type, ArrayList<ModelCheckBox> arrayTrue) {


		boolean found = false;
		boolean foundFirst = true;
		boolean foundBis = true;
		boolean foundEc = true;
	this. filterAirlines = arrayTrue;


		this.filterModels = type;
		dataExpandingListFilter= new ArrayList<>();
		dataExpandingListFilter2=new ArrayList<>();


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
					//if(dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("اکونومی")){
					for (int i = 0; i < dataExpandingList.size(); i++) {

						if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains("اکونومی")) {

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
						if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains("بیزینس")) {
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

						if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains("فرست")) {

							ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
							HeaderExpandingPlan header = new HeaderExpandingPlan();
							header = dataExpandingList.get(i).getHeader();
							parentItem.setHeader(header);
							parentItem.setItems(dataExpandingList.get(i).getItems());
							dataExpandingListFilter.add(parentItem);


						}
					}


				}


				if ((filterModel.isOneStop() ||filterModel.isNoStop()|| filterModel.isTwoStopMore())&&(filterModel.isEconomiF()||filterModel.isBusinessF())||filterModel.isEconomiF()){




					if (filterModel.isNoStop() && filterModel.isEconomiF()) {

						for (int i = 0; i < dataExpandingListFilter.size(); i++) {

							if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("اکونومی") && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 1) {


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

							if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("اکونومی") && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 2) {


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

							if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("اکونومی") && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount >= 3) {


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

							if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("فرست") && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 1) {


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

							if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("فرست") && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 2) {


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

							if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("فرست") && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount >= 3) {


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

							if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("بیزینس") && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 1) {


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

							if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("بیزینس") && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 2) {


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

							if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("بیزینس") && dataExpandingListFilter.get(i).getHeader().SegmentTrueCount >= 3) {


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




					dataExpandingListFilter.clear();
					dataExpandingListFilter=dataExpandingListFilter2;
				}else{



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
							//if(dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("اکونومی")){
							for (int i = 0; i < dataExpandingList.size(); i++) {
								if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains("اکونومی")) {
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
								if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains("بیزینس")) {

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
								if (dataExpandingList.get(i).getHeader().CabinClassNameFa.contains("فرست")) {
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


         /*   for (int b = 0; b < dataExpandingListFilter.size(); b++) {
                for (int ii = 0; ii < strings.size(); ii++) {
                    if (!dataExpandingListFilter.get(b).getHeader().AirlineNameFa.equals(strings.get(ii))) {
                        Log.e("ok", "onReturnValueFlightNew: ");
                        found = true;
                    }

                }
            }

*/

			if(filterModel.isRemove()){

				listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList,searchParvazPinAdapter);
				expListViewExpanding.setAdapter(listAdapterExpanding);
				iconFilter.setTextColor(Color.parseColor("#4d4d4d"));
				txtFilter.setTextColor(Color.parseColor("#4d4d4d"));


			}


		}



		linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
		linear_expand.setVisibility(View.VISIBLE);
		LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
		txtNoResult.setText("هیچ موردی یافت نشد");
		linear_no_result.setVisibility(View.GONE);

		if (dataExpandingListFilter.isEmpty() ||!foundFirst || !foundEc || !foundBis) {
			// foundFirst=false;

			//Toast.makeText(SearchParvazActivity.this, "هیچ موردی یافت نشد", Toast.LENGTH_LONG).show();
			///////////////
			iconFilter.setTextColor(Color.RED);
			txtFilter.setTextColor(Color.RED);

			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.GONE);
			 linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			txtNoResult.setText("هیچ موردی یافت نشد");
			linear_no_result.setVisibility(View.VISIBLE);

			if(FlagRemove){
				iconFilter.setTextColor(Color.parseColor("#4d4d4d"));
				txtFilter.setTextColor(Color.parseColor("#4d4d4d"));

				FlagRemove=false;

				linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
				linear_expand.setVisibility(View.VISIBLE);
				linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
				txtNoResult.setText("هیچ موردی یافت نشد");
				linear_no_result.setVisibility(View.GONE);
			}
			/////////////////
			//listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList,searchParvazPinAdapter);
			//expListViewExpanding.setAdapter(listAdapterExpanding);
		} else {

			listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingListFilter,searchParvazPinAdapter);
			expListViewExpanding.setAdapter(listAdapterExpanding);
			iconFilter.setTextColor(Color.RED);
			txtFilter.setTextColor(Color.RED);

			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.VISIBLE);
			 linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			txtNoResult.setText("هیچ موردی یافت نشد");
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
				listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList,searchParvazPinAdapter);


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
				listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList,searchParvazPinAdapter);


				// setting list adapter
				expListViewExpanding.setAdapter(listAdapterExpanding);
				listAdapterExpanding.notifyDataSetChanged();

				break;
		}
	}


	private class AsyncFetch extends AsyncTask<String, String, String> {
		//ProgressDialog pdLoading = new ProgressDialog(SearchParvazActivity.this,R.style.StyledDialog);
		//ProgressDialog pdLoading = new ProgressDialog(SearchParvazActivity.this);


		HttpURLConnection conn;
		URL url = null;
		private ListView listAirPort;

		@Override
		protected void onPreExecute() {
			window.setStatusBarColor(getColor(R.color.banafsh_flight));

			new InitUi().Loading(SearchParvazActivity.this, rlLoading, rlRoot, true, R.drawable.flight_loading);


		}

		@Override
		protected String doInBackground(String... params) {
			try {

				url = new URL("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetAirportWithParents");

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


				String data = OrderToJson();
				Log.e("date", data );


				HttpClient client = new DefaultHttpClient();


				HttpPost post = new HttpPost();
				post = new HttpPost("http://mobilews.eligasht.com/LightServices/Rest/Flight/FlightService.svc/SearchFlights");
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
				HashMap<String, String> airport = null;
				ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
				HttpResponse res = client.execute(post);
				String retSrc = EntityUtils.toString(res.getEntity(), HTTP.UTF_8);

			/*	JSONObject jsonObj = new JSONObject("Error");
                JSONObject GetAirportsResult = jsonObj.getJSONObject("SearchFlightsResult");*/

				return retSrc;


			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("errorrr"+e);
				return e.toString();
			} finally {


				conn.disconnect();
			}


		}

		@Override
		protected void onPostExecute(String result){
			new InitUi().Loading(SearchParvazActivity.this, rlLoading, rlRoot, false, R.drawable.flight_loading);//dismiss

			window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
			List<Country> data = new ArrayList<Country>();

			try {
////////////////////////////
				JSONObject jsonObj = new JSONObject(result);
				String GetError="";
				JSONArray jError=null;

				JSONObject GetAirportsResult = jsonObj.getJSONObject("SearchFlightsResult");

				if(!GetAirportsResult.getString("Errors").equals("null")){
					jError=GetAirportsResult.getJSONArray("Errors");//
					JSONObject jPricedItinerary = jError.getJSONObject(0);
					GetError = jPricedItinerary.getString("DetailedMessage");

				}
				if (GetError.length()>1) {
					if(GetError.contains("|")){
						String[] s=GetError.split(Pattern.quote("|"));

						linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
						linear_expand.setVisibility(View.GONE);
						LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
						txtNoResult.setText(s[1]+"");
						linear_no_result.setVisibility(View.VISIBLE);
					}else {
						linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
						linear_expand.setVisibility(View.GONE);
						LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
						txtNoResult.setText(GetError);
						linear_no_result.setVisibility(View.VISIBLE);
					}
					//Toast.makeText(SearchParvazActivity.this, GetError, Toast.LENGTH_LONG).show();
				}else{
				String ResultUniqID = GetAirportsResult.getString("ResultUniqID");//
				globalResultUniqID = ResultUniqID;

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
						flightSegment.setArrivalCityCode(jPricedIfdgtinerary.getString("ArrivalCityCode"));
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
						flightSegment.setDepartureCityCode(jPricedIfdgtinerary.getString("DepartureCityCode"));
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
							flightSegmentTrue.setDepartureCityCode(jPricedIfdgtinerary.getString("DepartureCityCode"));
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

             /*   listAirPort = (ListView)findViewById(R.id.listAirPort);
                mAdapter = new GetAirPortMabdaAdapter(SearchParvazActivity.this, data);
                //mAdapter.setAdapter(mAdapter);
                mAdapter.setData(data);
                listAirPort.setAdapter(mAdapter);*/

				getAirLine();}
			} catch (JSONException e) {//d/sfdsf
				//Toast.makeText(SearchParvazActivity.this, "ارتباط با سرور برقرار نشد !!", Toast.LENGTH_LONG).show();
				//if (flightsListFilter.size() == 0 || flightsListFilter== null) {
					linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
					linear_expand.setVisibility(View.GONE);
					LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
					txtNoResult.setText("در حال حاضر پاسخگویی به درخواست  شما امکان پذیر نمی باشد ");
					linear_no_result.setVisibility(View.VISIBLE);
				//}

			}

		}

		private void showDataExpanding() {
			// preparing list data
			expandingListData();


			listAdapterExpanding = new ExpandableListAdapter(SearchParvazActivity.this, dataExpandingList,searchParvazPinAdapter);

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
							// Toast.makeText(getApplicationContext(),
							// "Group Clicked " + listDataHeader.get(groupPosition),
							// Toast.LENGTH_SHORT).show();
							return false;
						}
					});

					// Listview Group expanded listener
					expListViewExpanding.setOnGroupExpandListener(new OnGroupExpandListener() {

						@Override
						public void onGroupExpand(int groupPosition) {
							//	Toast.makeText(SearchParvazActivity.this,listDataHeaderExpanding.get(groupPosition) + " Expanded",Toast.LENGTH_SHORT).show();
							//Toast.makeText(SearchParvazActivity.this, " Expanded",Toast.LENGTH_SHORT).show();
						}
					});

					// Listview Group collasped listener
					expListViewExpanding.setOnGroupCollapseListener(new OnGroupCollapseListener() {

						@Override
						public void onGroupCollapse(int groupPosition) {
                    /*Toast.makeText(SearchParvazActivity.this,
                             listDataHeaderExpanding.get(groupPosition) + " Collapsed",
     						Toast.LENGTH_SHORT).show();*/

						}
					});

					// Listview on child click listener
					expListViewExpanding.setOnChildClickListener(new OnChildClickListener() {

						@Override
						public boolean onChildClick(ExpandableListView parent, View v,
													int groupPosition, int childPosition, long id) {
                    /*Toast.makeText(SearchParvazActivity.this,
     						listDataHeaderExpanding.get(groupPosition)
     								+ " : "
     								+ listDataChildExpanding.get(listDataHeaderExpanding.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();*/
							return false;
						}
					});
				}
			});
		}

	}//end asynTask

	public String OrderToJson() {
		JSONObject jsone = new JSONObject();
		JSONObject manJson = new JSONObject();

		JSONObject identityJson = new JSONObject();
		JSONObject jsoneIde = new JSONObject();

		try {

			Bundle extras = getIntent().getExtras();
			if (extras != null) {
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

				System.out.println("YYYYYYYYYYYYYYYYYYYYYYY");
				System.out.println("maghsadf" + maghsadf + "mabda" + mabdaf + "flagWay" + flagWay + "aadlcount:" + adlCount + "Ddate" + Ddate + "Adate" + Adate);

				//identity":{"Password":"123qwe!@#QWE","TermianlId":"Mobile","UserName":"EligashtMlb"}
				identityJson.put("Password", "123qwe!@#QWE");
				identityJson.put("TermianlId", "Mobile");
				identityJson.put("UserName", "EligashtMlb");

				// jsoneIde.put("identity",identityJson);//{"identity":{"Password":"123qwe!@#QWE","TermianlId":"Mobile","UserName":"EligashtMlb"}}

				manJson.put("DepartureAirportcode", mabdaf);
				manJson.put("ArrivalAirportcode", maghsadf);
				manJson.put("DepartureDate", Ddate);
				manJson.put("ArrivalDate", Adate);
				manJson.put("OneWay", flagWay); // اگر فقط رفت باشد عدد یک و در صورت رفت و برگشت عدد 2 را ارسال بفرمایید
				manJson.put("CabinClassCode", "all");

				manJson.put("AdlCount", Integer.parseInt(adlCount));
				manJson.put("ChdCount", Integer.parseInt(chdCount));
				manJson.put("InfCount", Integer.parseInt(infCount));//{"DepartureAirportcode":"THR","ArrivalAirportcode":"IST","DepartureDate":"2017-12-28","ArrivalDate":"2017-12-31","OneWay":"2","CabinClassCode":"Y","AdlCount":1,"ChdCount":0,"InfCount":0}
				manJson.put("Culture", "fa-IR");

				manJson.put("identity", identityJson);
				jsone.put("request", manJson);
				//jsone.put("request",jsoneIde);
			} else {
				identityJson.put("Password", "123qwe!@#QWE");
				identityJson.put("TermianlId", "Mobile");
				identityJson.put("UserName", "EligashtMlb");

				jsoneIde.put("identity", identityJson);

				manJson.put("DepartureAirportcode", "IST");
				manJson.put("ArrivalAirportcode", "IKA");
				manJson.put("DepartureDate", "2017-12-24");
				manJson.put("ArrivalDate", "2018-01-29");
				manJson.put("OneWay", "2"); // اگر فقط رفت باشد عدد یک و در صورت رفت و برگشت عدد 2 را ارسال بفرمایید
				manJson.put("CabinClassCode", "Y");

				manJson.put("AdlCount", 1);
				manJson.put("ChdCount", 0);
				manJson.put("InfCount", 0);
				manJson.put("Culture", "fa-IR");

				//Global
				COUNT_B = 1;
				COUNT_K = 0;
				COUNT_N = 0;


				manJson.put("identity", identityJson);

				jsone.put("request", manJson);
				//jsone.put("request",jsoneIde);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsone.toString();
	}

	private void ferstClassListData() {
		//	if(SegmentList.get(0).getCabinClassNameFa().contains("فرست")){
		List<ParentItemExpandingPlan> dataExpandingListFilterTrue = new ArrayList<ParentItemExpandingPlan>();

		if (dataExpandingListFilter != null) {

			linear_expand.setVisibility(View.VISIBLE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			linear_no_result.setVisibility(View.GONE);
			try {
				System.out.println("flightsListFilter.size():" + flightsListFilter.size());

				for (int i = 0; i < dataExpandingListFilter.size(); i++) {

					System.out.println("HEADER I=" + i);

					if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("فرست")) {
						System.out.println("فرست" + i);
						//header

						ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");

						HeaderExpandingPlan header = new HeaderExpandingPlan();
						header = dataExpandingListFilter.get(i).getHeader();

						parentItem.setHeader(header);
						parentItem.setItems(dataExpandingListFilter.get(i).getItems());

						dataExpandingListFilterTrue.add(parentItem);
					}//endif
				}//endfor
				//if(dataExpandingListFilterTrue.size()>0){
				dataExpandingListFilter = new ArrayList<ParentItemExpandingPlan>();
				dataExpandingListFilter = dataExpandingListFilterTrue;
				//}

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

		}
		if (flightsListFilter.size() == 0 || flightsListFilter == null) {
			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.GONE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			txtNoResult.setText("نتیجه ای برای جستجوی شما یافت نشد");
			linear_no_result.setVisibility(View.VISIBLE);
		}

	}//end fersclas

	private void bisnesListData() {
		//	if(SegmentList.get(0).getCabinClassNameFa().contains("بیزنس")){
		List<ParentItemExpandingPlan> dataExpandingListFilterTrue = new ArrayList<ParentItemExpandingPlan>();

		if (dataExpandingListFilter != null) {
			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.VISIBLE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			linear_no_result.setVisibility(View.GONE);
			try {
				System.out.println("flightsListFilter.size():" + flightsListFilter.size());

				for (int i = 0; i < dataExpandingListFilter.size(); i++) {

					System.out.println("HEADER I=" + i);

					if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("بیزنس")) {
						System.out.println("بیزنس" + i);
						//header

						ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");

						HeaderExpandingPlan header = new HeaderExpandingPlan();
						header = dataExpandingListFilter.get(i).getHeader();

						parentItem.setHeader(header);
						parentItem.setItems(dataExpandingListFilter.get(i).getItems());

						dataExpandingListFilterTrue.add(parentItem);
					}//endif
				}//endfor
				//if(dataExpandingListFilterTrue.size()>0){
				dataExpandingListFilter = new ArrayList<ParentItemExpandingPlan>();
				dataExpandingListFilter = dataExpandingListFilterTrue;
				//}

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

		}
		if (flightsListFilter.size() == 0 || flightsListFilter == null) {
			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.GONE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			txtNoResult.setText("نتیجه ای برای جستجوی شما یافت نشد");
			linear_no_result.setVisibility(View.VISIBLE);
		}

	}//end bisnes

	private void economyListData() {

		List<ParentItemExpandingPlan> dataExpandingListFilterTrue = new ArrayList<ParentItemExpandingPlan>();

		if (dataExpandingListFilter != null) {
			LinearLayout linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.VISIBLE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			linear_no_result.setVisibility(View.GONE);
			try {
				System.out.println("flightsListFilter.size():" + flightsListFilter.size());

				for (int i = 0; i < dataExpandingListFilter.size(); i++) {

					System.out.println("HEADER I=" + i);

					if (dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("اکونومی")) {
						System.out.println("اکونومی" + i);
						//header

						ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");

						HeaderExpandingPlan header = new HeaderExpandingPlan();
						header = dataExpandingListFilter.get(i).getHeader();

						parentItem.setHeader(header);
						parentItem.setItems(dataExpandingListFilter.get(i).getItems());

						dataExpandingListFilterTrue.add(parentItem);
					}//endif
				}//endfor
				//	if(dataExpandingListFilterTrue.size()>0){
				dataExpandingListFilter = new ArrayList<ParentItemExpandingPlan>();
				dataExpandingListFilter = dataExpandingListFilterTrue;
				//}

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

		}
		if (flightsListFilter.size() == 0 || flightsListFilter == null) {
			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.GONE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			txtNoResult.setText("نتیجه ای برای جستجوی شما یافت نشد");
			linear_no_result.setVisibility(View.VISIBLE);
		}

	}//end economy

	private void expandingListData() {
		linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
		linear_expand.setVisibility(View.VISIBLE);
		LinearLayout linear_no_resultt = (LinearLayout) findViewById(R.id.linear_no_result);
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
					//	String test = searchParvazModelExps1.get(i).AirlineCode;


					System.out.println("HEADER I=" + i);

					//List<FlightSegment> SegmentList =new ArrayList<FlightSegment>();
					SegmentList = flightsList.get(i).getSegmentList();

					//List<FlightSegmentTrue> SegmentListtrueAvali =new ArrayList<FlightSegmentTrue>();
					SegmentListtrueAvali = flightsList.get(i).getSegmentListTrue();

					SegmentListtrueAkhari = flightsList.get(i).getSegmentListTrue();


					//List<FlightSegmentFalse> SegmentListFalseAvali =new ArrayList<FlightSegmentFalse>();
					SegmentListFalseAvali = flightsList.get(i).getSegmentListFalse();
					//List<FlightSegmentFalse> SegmentListFalseAkhari =new ArrayList<FlightSegmentFalse>();
					SegmentListFalseAkhari = flightsList.get(i).getSegmentListFalse();

					if(SegmentListFalseAvali.size() == 0 || SegmentListFalseAvali==null){//yek tarafe
						ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
						HeaderExpandingPlan header = new HeaderExpandingPlan(SegmentListtrueAvali.get(0).getDepartureCityNameFa(), SegmentListtrueAvali.get(0).getFlightTime(),
								SegmentListtrueAkhari.get(SegmentListtrueAkhari.size() - 1).getArrivalCityNameFa(), SegmentListtrueAkhari.get(SegmentListtrueAkhari.size() - 1).getFlightTime(),

								" ", " ",
								" ", " ",

								flightsList.get(i).getAdlCost().getAmount(),
								flightsList.get(i).getFlightGUID()
								, SegmentList.get(0).getAirlineNameFa()
								, SegmentList.get(0).getAirlineCode()

								, SegmentList.get(0).getCabinClassNameFa()
								, flightsList.get(i).getRemainSeats()
								, flightsList.get(i).isIsCharter()
								, SegmentList.get(0).getAirlineNameEn()
								, SegmentListtrueAkhari.size()
								,SegmentListtrueAvali.get(0).getFltDateDayOfWeek()
								,(String) (SegmentListFalseAvali.size() >0 ? SegmentListFalseAvali.get(0).getFltDateDayOfWeek() : "0")//SegmentListFalseAvali.get(0).getFltDateDayOfWeek()
						,SegmentListFalseAkhari.size()
						,SegmentListtrueAvali.get(0).getFlightNumber()
						,(String) (SegmentListFalseAvali.size() >0 ? SegmentListFalseAvali.get(0).getFlightNumber() : "0"),false);//ArrivalCityNameEnR baraye sort bayad en bashe


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


							item.AdlBaseFare = flightsList.get(i).getAdlBaseFare().getAmount();
							item.Taxes = flightsList.get(i).getTaxes().getAmount();
							item.TotalFare = flightsList.get(i).getTotalFare().getAmount();

							item.DepartureCityNameFa = SegmentList.get(j).getDepartureCityNameFa();
							item.ArrivalCityNameFa = SegmentList.get(j).getArrivalCityNameFa();
							parentItem.Items.add(item);

						}

						dataExpandingList.add(parentItem);
					}else{//2tarafe
						//header

						ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");
						HeaderExpandingPlan header = new HeaderExpandingPlan(SegmentListtrueAvali.get(0).getDepartureCityNameFa(), SegmentListtrueAvali.get(0).getFlightTime(),
								SegmentListtrueAkhari.get(SegmentListtrueAkhari.size() - 1).getArrivalCityNameFa(), SegmentListtrueAkhari.get(SegmentListtrueAkhari.size() - 1).getFlightTime(),

								SegmentListFalseAvali.get(0).getDepartureCityNameFa(), SegmentListFalseAvali.get(0).getFlightArrivalTime(),
								SegmentListFalseAkhari.get(SegmentListFalseAkhari.size() - 1).getArrivalCityNameFa(), SegmentListFalseAkhari.get(0).getFlightTime(),

								flightsList.get(i).getAdlCost().getAmount(),
								flightsList.get(i).getFlightGUID()
								, SegmentList.get(0).getAirlineNameFa()
								, SegmentList.get(0).getAirlineCode()

								, SegmentList.get(0).getCabinClassNameFa()
								, flightsList.get(i).getRemainSeats()
								, flightsList.get(i).isIsCharter()
								, SegmentList.get(0).getAirlineNameEn()
								, SegmentListtrueAkhari.size()
						,SegmentListtrueAvali.get(0).getFltDateDayOfWeek()
						,(String) (SegmentListFalseAvali.size() >0 ? SegmentListFalseAvali.get(0).getFltDateDayOfWeek() : "0")
								,SegmentListFalseAkhari.size()
								,SegmentListtrueAvali.get(0).getFlightNumber()
								, (String) (SegmentListFalseAvali.size() >0 ? SegmentListFalseAvali.get(0).getFlightNumber() : "0"),false);//ArrivalCityNameEnR baraye sort bayad en bashe


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


							item.AdlBaseFare = flightsList.get(i).getAdlBaseFare().getAmount();
							item.Taxes = flightsList.get(i).getTaxes().getAmount();
							item.TotalFare = flightsList.get(i).getTotalFare().getAmount();

							item.DepartureCityNameFa = SegmentList.get(j).getDepartureCityNameFa();
							item.ArrivalCityNameFa = SegmentList.get(j).getArrivalCityNameFa();
							parentItem.Items.add(item);

						}

						dataExpandingList.add(parentItem);
					}
				}
			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

		}
		if(flightsList.size()==0 || flightsList==null) {

			linear_expand.setVisibility(View.GONE);
			LinearLayout linear_no_result= (LinearLayout) findViewById(R.id.linear_no_result);
			txtNoResult.setText("نتیجه ای برای جستجوی شما یافت نشد");
			linear_no_result.setVisibility(View.VISIBLE);
		}


	}//end expanding listdata

	private void fitertwoStopMore() {
		//if(SegmentListtrueAkhari.size()>3){//more two tavaghof
		List<ParentItemExpandingPlan> dataExpandingListFilterTrue = new ArrayList<ParentItemExpandingPlan>();

		if (dataExpandingListFilter != null) {
			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.VISIBLE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			linear_no_result.setVisibility(View.GONE);
			try {
				System.out.println("flightsListFilter.size():" + flightsListFilter.size());

				for (int i = 0; i < dataExpandingListFilter.size(); i++) {

					System.out.println("HEADER I=" + i);
					//SegmentListtrueAkhari.get(i).
					if (dataExpandingListFilter.get(i).getHeader().SegmentTrueCount > 2) {//more two tavaghof
						//if(dataExpandingListFilter.get(i).getHeader().CabinClassNameFa.contains("اکونومی")){
						System.out.println("more two tavaghof 3 " + i);
						//header

						ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");

						HeaderExpandingPlan header = new HeaderExpandingPlan();
						header = dataExpandingListFilter.get(i).getHeader();

						parentItem.setHeader(header);
						parentItem.setItems(dataExpandingListFilter.get(i).getItems());

						dataExpandingListFilterTrue.add(parentItem);
					}//endif
				}//endfor
				if (dataExpandingListFilterTrue.size() > 0) {
					dataExpandingListFilter = new ArrayList<ParentItemExpandingPlan>();
					dataExpandingListFilter = dataExpandingListFilterTrue;
				}

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

		}
		if (dataExpandingListFilter.size() == 0 || dataExpandingListFilter == null) {
			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.GONE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			txtNoResult.setText("نتیجه ای برای جستجوی شما یافت نشد");
			linear_no_result.setVisibility(View.VISIBLE);
		}

	}//endfitertwoStopMore

	private void filterOneStopData() {

		List<ParentItemExpandingPlan> dataExpandingListFilterTrue = new ArrayList<ParentItemExpandingPlan>();

		if (dataExpandingListFilter != null) {
			LinearLayout linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.VISIBLE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			linear_no_result.setVisibility(View.GONE);
			try {
				System.out.println("flightsListFilter.size():" + flightsListFilter.size());

				for (int i = 0; i < dataExpandingListFilter.size(); i++) {

					System.out.println("HEADER I=" + i);
					//SegmentListtrueAkhari.get(i).
					if (dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 2) {//yek tavaghof

						System.out.println("yek tavaghof 2 " + i);
						//header

						ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");

						HeaderExpandingPlan header = new HeaderExpandingPlan();
						header = dataExpandingListFilter.get(i).getHeader();

						parentItem.setHeader(header);
						parentItem.setItems(dataExpandingListFilter.get(i).getItems());

						dataExpandingListFilterTrue.add(parentItem);
					}//endif
				}//endfor
				if (dataExpandingListFilterTrue.size() > 0) {
					dataExpandingListFilter = new ArrayList<ParentItemExpandingPlan>();
					dataExpandingListFilter = dataExpandingListFilterTrue;
				}

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

		}
		if (dataExpandingListFilter.size() == 0 || dataExpandingListFilter ==null) {
			LinearLayout linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.GONE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			txtNoResult.setText("نتیجه ای برای جستجوی شما یافت نشد");
			linear_no_result.setVisibility(View.VISIBLE);
		}

	}//filterOneStop

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

	public void filterAirLineTrue(ArrayList<ModelCheckBox> listTrue) {
///if (SegmentList.get(0).getAirlineNameFa().contains(listTrue.get(t).getName())&&listTrue.get(t).isCheck()) {//
		List<ParentItemExpandingPlan> dataExpandingListFilterTrue = new ArrayList<ParentItemExpandingPlan>();

		if (dataExpandingListFilter != null) {
			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.VISIBLE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			linear_no_result.setVisibility(View.GONE);
			try {
				System.out.println("flightsListFilter.size():" + flightsListFilter.size());

				for (int i = 0; i < dataExpandingListFilter.size(); i++) {

					System.out.println("HEADER I=" + i);
					String s = dataExpandingListFilter.get(i).getHeader().AirlineNameFa;

					for (int j = 0; j < listTrue.size(); j++) {
						if (s.contains(listTrue.get(j).getName()) && listTrue.get(j).isCheck()) {//
							System.out.println("bedune tavaghof 1 " + i);
							//header

							ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");

							HeaderExpandingPlan header = new HeaderExpandingPlan();
							header = dataExpandingListFilter.get(i).getHeader();

							parentItem.setHeader(header);
							parentItem.setItems(dataExpandingListFilter.get(i).getItems());

							dataExpandingListFilterTrue.add(parentItem);
						}//endif
					}
				}//endfor
				if (dataExpandingListFilterTrue.size() > 0) {
					dataExpandingListFilter = new ArrayList<ParentItemExpandingPlan>();
					dataExpandingListFilter = dataExpandingListFilterTrue;
				}

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

		}
		if (flightsListFilter.size() == 0 || flightsListFilter== null) {
			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.GONE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			txtNoResult.setText("نتیجه ای برای جستجوی شما یافت نشد");
			linear_no_result.setVisibility(View.VISIBLE);
		}


	}//end filterAirLine

	public void filternoStopData() {

		List<ParentItemExpandingPlan> dataExpandingListFilterTrue = new ArrayList<ParentItemExpandingPlan>();

		if (dataExpandingListFilter != null) {
			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.VISIBLE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			linear_no_result.setVisibility(View.GONE);
			try {
				System.out.println("flightsListFilter.size():" + flightsListFilter.size());

				for (int i = 0; i < dataExpandingListFilter.size(); i++) {

					System.out.println("HEADER I=" + i);

					if (dataExpandingListFilter.get(i).getHeader().SegmentTrueCount == 1) {//bedune tavaghof

						System.out.println("bedune tavaghof 1 " + i);
						//header

						ParentItemExpandingPlan parentItem = new ParentItemExpandingPlan("");

						HeaderExpandingPlan header = new HeaderExpandingPlan();
						header = dataExpandingListFilter.get(i).getHeader();

						parentItem.setHeader(header);
						parentItem.setItems(dataExpandingListFilter.get(i).getItems());

						dataExpandingListFilterTrue.add(parentItem);
					}//endif
				}//endfor
				if (dataExpandingListFilterTrue.size() > 0) {
					dataExpandingListFilter = new ArrayList<ParentItemExpandingPlan>();
					dataExpandingListFilter = dataExpandingListFilterTrue;
				}

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

		}
		if (flightsListFilter.size() == 0 || flightsListFilter == null) {
			linear_expand = (LinearLayout) findViewById(R.id.linear_expand);
			linear_expand.setVisibility(View.GONE);
			LinearLayout linear_no_result = (LinearLayout) findViewById(R.id.linear_no_result);
			txtNoResult.setText("نتیجه ای برای جستجوی شما یافت نشد");
			linear_no_result.setVisibility(View.VISIBLE);
		}

	}//end Filternostop

	public class ParentItemExpandingPlan {

		public ParentItemExpandingPlan(String header) {
			//Header = header;
			Header = new HeaderExpandingPlan();
			Items = new ArrayList<ItemExpandingPlan>();
		}
		//public String Header;

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

		public HeaderExpandingPlan Header;
		public List<ItemExpandingPlan> Items;


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

		public long AdlCost;
		public String flGUID;//apiPassenger

		public String AirlineNameFa;
		public String AirlineCode;

		public String CabinClassNameFa;

		public int RemainSeats;
		public boolean IsCharter;
		public int SegmentTrueCount;
		public  String FltDateDayOfWeek;
		public  String FltDateDayOfWeekFalse;
		public  int SegmentFalseCount;
		public String FlightNumberB;
		public String FlightNumberR;

		public boolean isPin() {
			return IsPin;
		}

		public void setPin(boolean pin) {
			IsPin = pin;
		}

		public boolean IsPin;
		public HeaderExpandingPlan(String ArrivalCityNameFaR, String FlightArrivalTimeR,
								   String DepartureCityNameFaR, String FlightTimeR,

								   String ArrivalCityNameFaB, String FlightArrivalTimeB,
								   String DepartureCityNameFaB, String FlightTimeB,
								   long AdlCost
				, String flGUID, String AirlineNameFa
				, String AirlineCode
				, String CabinClassNameFa, int RemainSeats, boolean IsCharter, String AirlineNameEa, int SegmentTrueCount,String FltDateDayOfWeekTrue,String FltDateDayOfWeekFalse,int SegmentFalseCount,String FlightNumberR
				,String FlightNumberB
		,boolean IsPin) {
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
			this.FltDateDayOfWeek=FltDateDayOfWeekTrue;
			this.FltDateDayOfWeekFalse=FltDateDayOfWeekFalse;

			this.SegmentFalseCount=SegmentFalseCount;
			this.FlightNumberR=FlightNumberR;
			this.FlightNumberB=FlightNumberB;
			this. IsPin= IsPin;
		}

		public HeaderExpandingPlan() {

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

		public long AdlBaseFare;
		public long Taxes;
		public long TotalFare;

		public String AirlineCode;

		public String DepartureCityNameFa;
		public String ArrivalCityNameFa;


		public ItemExpandingPlan(String DepartureAirportNameFaR, String FlightTimeR,
								 String ArrivalAirportNameFaR, String FlightArrivalTimeR,
								 String AirlineNameFaR, String FlightNumberR,
								 String DepartureAirportNameFaB, String FlightTimeB,
								 String ArrivalAirportNameFaB, String FlightArrivalTimeB,
								 String AirlineNameFaB, String FlightNumberB
				, String flGUID
								 //nerkh
				, long AdlCost, long Taxes, long TotalFare) {

			this.DepartureAirportNameFaR = DepartureAirportNameFaR;
			this.FlightTimeR = FlightTimeR;
			this.ArrivalAirportNameFaR = ArrivalAirportNameFaR;
			this.FlightArrivalTimeR = FlightArrivalTimeR;
			this.AirlineNameFaR = AirlineNameFaR;
			this.FlightNumberR = FlightNumberR;
			this.flGUID = flGUID;

			this.AdlBaseFare = AdlBaseFare;
			this.Taxes = Taxes;
			this.TotalFare = TotalFare;


		}

		public ItemExpandingPlan() {
			// TODO Auto-generated constructor stub
		}

	}

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
				/*Intent intent = new Intent(this,PlanFragment.class);
				//i2.putExtra("CUSTOMER_ID", (int) customerID);
				startActivity(intent);*/
				finish();
				break;
			case R.id.btnHome:
				finish();
				break;
			case R.id.txtFilter:

				new FilterFlightDialogNew(SearchParvazActivity.this, filterModels, this, filterAirlines);

				break;
			case R.id.iconFilter:

				new FilterFlightDialogNew(SearchParvazActivity.this, filterModels, this, filterAirlines);

				break;
			case R.id.lblMoratabSazi://sort
				// custom dialog
				new SortFlightDialog(SearchParvazActivity.this, this, besetSeler, bestOff, remove);

				break;
			case R.id.txtRuzeBad:

				//"2017-12-24"
				try {

					String str_date = Ddate;//2018-01-16
					DateFormat formatter;
					Date date;
					formatter = new SimpleDateFormat("yyyy-MM-dd");
					date = (Date) formatter.parse(str_date);
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					cal.add(Calendar.DATE, 1);
					System.out.println("Add one day to current date : " + formatter.format(cal.getTime()));


					Date dateRaft = (Date) formatter.parse(Ddate);
					Date dateBargasht = (Date) formatter.parse(Adate);
					if (dateBargasht.after(dateRaft)) {
						///
						///
						SimpleDateFormat dfm = new SimpleDateFormat("dd MMMM yyyy");
						//  txtDateOnvan.setText(AdateF + "  -  " + dfm.format(cal.getTime()));
						/////////////////////////////
						SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM/dd HH:mm");//2017/03/24 11:49
						String formatted3 = format3.format(cal.getTime());

						String dayM=formatted3.substring(8, 10);//02
						String monthM=formatted3.substring(5, 7);//01
						String yearM=formatted3.substring(0, 4);//1396


						String  dateShamsi= SolarCalendar.calSolarCalendar(Integer.parseInt(yearM),Integer.parseInt(monthM),Integer.parseInt(dayM));
						System.out.println("dateShamsi:"+yearM+monthM+dayM+"   "+dateShamsi);
						String dayMF=dateShamsi.substring(8, 10);//02
						String monthMF=dateShamsi.substring(5, 7);//01
						String yearMF=dateShamsi.substring(0, 4);//1396

						PersianCalendar persianCalendar = new PersianCalendar();
						persianCalendar.set(Integer.parseInt(yearMF), Integer.parseInt(monthMF)-1, Integer.parseInt(dayMF));
						/////////////////////
						//   txtDateOnvan.setText(dfm.format(cal.getTime()) + "  -  " + AdateF);
						txtDateOnvan.setText(persianCalendar.getPersianLongDate() + "  -  " + AdateF);
						///
						Ddate = formatter.format(cal.getTime());
						callApiDateNext();
					} else {
						Toast.makeText(getApplicationContext(), "تاریخ رفت بزرگتر از تاریخ برگشت می باشد",
								Toast.LENGTH_SHORT).show();
					}

				} catch (java.text.ParseException e) {
					System.out.println("Exception :" + e);
				}

				break;
			case R.id.btn_no_Result:
				finish();
				break;
			case R.id.txtRuzeGhabl:

				//"2017-12-24"
				try {

					String str_date = Ddate;//"11-June-07";
					DateFormat formatter;
					Date date;
					formatter = new SimpleDateFormat("yyyy-MM-dd");
					date = (Date) formatter.parse(str_date);
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					cal.add(Calendar.DATE, -1);
					System.out.println("Mines one day to current date : " + formatter.format(cal.getTime()));
					//shart kamtar az emruz
					if (System.currentTimeMillis() <= date.getTime()) {
						Ddate = formatter.format(cal.getTime());

						///onvan
						SimpleDateFormat dfm = new SimpleDateFormat("dd MMMM yyyy");
						/////////////////////////////
						SimpleDateFormat format3 = new SimpleDateFormat("yyyy/MM/dd HH:mm");//2017/03/24 11:49
						String formatted3 = format3.format(cal.getTime());

						String dayM=formatted3.substring(8, 10);//02
						String monthM=formatted3.substring(5, 7);//01
						String yearM=formatted3.substring(0, 4);//1396

						String  dateShamsi= SolarCalendar.calSolarCalendar(Integer.parseInt(yearM),Integer.parseInt(monthM),Integer.parseInt(dayM));
						String dayMF=dateShamsi.substring(8, 10);//02
						String monthMF=dateShamsi.substring(5, 7);//01
						String yearMF=dateShamsi.substring(0, 4);//1396

						PersianCalendar persianCalendar = new PersianCalendar();
						persianCalendar.set(Integer.parseInt(yearMF), Integer.parseInt(monthMF)-1, Integer.parseInt(dayMF));
						/////////////////////
						// txtDateOnvan.setText(AdateF + "  -  " + dfm.format(cal.getTime()));
						txtDateOnvan.setText(persianCalendar.getPersianLongDate() + "  -  " + AdateF);
						///
						callApiDateNext();
					} else {
						Toast.makeText(getApplicationContext(), "قبل از تاریخ امروز", Toast.LENGTH_SHORT).show();
					}


				} catch (java.text.ParseException e) {
					System.out.println("Exception :" + e);
				}



				break;

		}
	}

	private void callApiDateNext() {

		new AsyncFetch().execute();

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
		if (Prefs.getBoolean("BACK_HOME", true)) {
			this.finish();
		}
		Prefs.putBoolean("BACK_HOME", false);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}