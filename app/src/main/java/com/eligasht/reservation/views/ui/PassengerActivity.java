package com.eligasht.reservation.views.ui;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.reservation.lost.hotel.HotelPreFactorAdapter;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.tools.datetools.SolarCalendar;
import com.eligasht.reservation.tools.persian.Calendar.persian.util.PersianCalendarUtils;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.flight.request.PreFactorDetails.RequestPreFactorDetails;
import com.eligasht.service.model.flight.request.PurchaseFlight.PartnerInfo;
import com.eligasht.service.model.flight.request.PurchaseFlight.PassList;
import com.eligasht.service.model.flight.request.purchaseServiceFlight.RequestPurchaseFlight;
import com.eligasht.service.model.flight.response.PreFactorDetails.FactorSummary;
import com.eligasht.service.model.flight.response.PreFactorDetails.GetPreFactorDetailsResult;
import com.eligasht.service.model.flight.response.PreFactorDetails.PreFactor;
import com.eligasht.service.model.flight.response.PreFactorDetails.PreFactorFlight;
import com.eligasht.service.model.flight.response.PreFactorDetails.PreFactorHotel;
import com.eligasht.service.model.flight.response.PreFactorDetails.PreFactorService;
import com.eligasht.service.model.flight.response.PreFactorDetails.RequestPassenger;
import com.eligasht.service.model.flight.response.PreFactorDetails.ResponsePreFactorDetails;
import com.eligasht.service.model.flight.response.PurchaseFlight.Service;
import com.eligasht.service.model.flight.response.PurchaseFlight.TmpReserveResult;
import com.eligasht.service.model.flight.response.purchaseServiceFlight.PurchaseServiceResult;
import com.eligasht.service.model.flight.response.purchaseServiceFlight.ResponsePurchaseFlight;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.lost.flight.FlightPreFactorAdapter;
import com.eligasht.reservation.models.FlightPreFactorModel;
import com.eligasht.reservation.models.HotelPreFactorModel;
import com.eligasht.reservation.lost.passenger.PassangerPreFactorAdapter;
import com.eligasht.reservation.models.PassengerPreFactorModel;
import com.eligasht.reservation.lost.service.ServicePreFactorAdapter;
import com.eligasht.reservation.models.ServicePreFactorModel;
import com.eligasht.reservation.models.model.PurchaseFlightResult;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.tools.db.local.PassengerMosaferItems_Table;
import com.eligasht.reservation.tools.db.local.PassengerPartnerInfo_Table;
import com.eligasht.reservation.tools.db.main.CursorManager;
import com.eligasht.reservation.views.activities.transfer.ExcursionDta;
import com.eligasht.reservation.views.adapters.GetKhadmatAdapter;
import com.eligasht.reservation.views.adapters.hotel.rooms.NonScrollListView;
import com.eligasht.reservation.views.components.Header;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;


import mehdi.sakout.fancybuttons.FancyButton;

public class PassengerActivity extends BaseActivity implements Header.onSearchTextChangedListener,OnClickListener,OnItemSelectedListener,View.OnFocusChangeListener,
		com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener, OnServiceStatus<ResponsePurchaseFlight>,
		com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener{

	public static boolean flag;
	public static final int CONNECTION_TIMEOUT = 10000;
	public static final int READ_TIMEOUT = 15000;
	Handler handler;
	ProgressDialog progressBar;
	public FancyButton btnBack;
	public ImageView btn_saler,btn_mosaferan,btn_khadamat,btn_pish_factor;
	public TextView txtfamilyP,txtkodemeliP,txtemeliP,txtmobileP,txtMore;
	public Button btnAddsabad,btn_pardakht_factor,txtSaler,txtMasaferan,txtKhadamat,txtPishfactor;
	public EditText txtnamem,txtfamilym,txt_NationalCode_m;
	public  TextView txttavalodm;
	public EditText txtnumber_passport,txtnameP;
	public static TextView txtexp_passport;
	public TextView txtTitle,txtmeliyatm,txtmahale_eghamat,txtTitleCountM;
	public static TextView txtSumKhadamat;
	public TextView imgCount;
	public LinearLayout linear_expdate,linear_number_passport,linear_code_meli,btn_taeed_khadamat,btn_nextm,linear_saler,linear_mosaferan,linear_list_khadamat,linear_pish_factor,linearMahaleeghamat,linearMeliyat,btn_next_partnerInfo;
	private Handler progressBarHandler = new Handler();
	public ListView list_airport;
	public NonScrollListView listKhadamat;
	ArrayList<HashMap<String,String>> mylist=null;
	public static String searchText = "";

	public static long GET_PRICE_KHADAMAT;

	GetKhadmatAdapter mAdapter;
	ScrollView myScrollView;
	private EditText searchtxt;
	public TextView txt_shomare_factor,tvPrice,tvfactorNumber;

	public ImageView txt_hom;
	LinearLayout llDetailHotel,llDetailPassanger,llDetailService,llDetailFlight;
	private String Gensiyat="";

	public int countB=SearchParvazActivity.COUNT_B;
	public int countK=SearchParvazActivity.COUNT_K;
	public int countN=SearchParvazActivity.COUNT_N;
	public int sum=countB+countK+countN;

	public List<PurchaseFlightResult> data;
	int counter=2;
	private ImageView textView4;
	String paymentUrl;
	private boolean FlagTab=false;
	private boolean FlagMosaferan=true;
	private RadioButton btnzan,btnmard,btnzanS,btnmardS;
	RelativeLayout rlLoading,rlRoot;
	com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian1;
	com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian2;
	com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog;
	com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog2;

	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flight_passenger);

		SwipeBackActivityHelper helper = new SwipeBackActivityHelper();
		helper.setEdgeMode(false)
				.setParallaxMode(true)
				.setParallaxRatio(3)
				.setNeedBackgroundShadow(true)
				.init(this);

		ScrollView scroll_partner=(ScrollView)findViewById(R.id.scroll_partner);
		scroll_partner.fullScroll(ScrollView.FOCUS_UP);
		scroll_partner.scrollTo(0,0);
		scroll_partner.clearFocus();

		txtTitleCountM = (TextView) findViewById(R.id.txtTitleCountM);
		txtTitleCountM.setOnClickListener(this);
		txttavalodm = (TextView) findViewById(R.id.txttavalodm);
		txttavalodm.setOnClickListener(this);
		txtexp_passport = (TextView) findViewById(R.id.txtexp_passport);
		txtexp_passport.setOnClickListener(this);

		Prefs.putString("IST","F");

		PersianCalendar persianCalendarDatePicker = new PersianCalendar();
		PersianCalendar persianCalendar = new PersianCalendar();
		persianCalendar.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth(), persianCalendarDatePicker.getPersianDay());
//=====================================================================================================

		datePickerDialog = com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.newInstance(
				this,
				persianCalendarDatePicker.getPersianYear(),
				persianCalendarDatePicker.getPersianMonth(),
				persianCalendarDatePicker.getPersianDay()
		);

//=====================================================================================================
		datePickerDialogGregorian1 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog(1);
		datePickerDialogGregorian1.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
				String monthl=""+(monthOfYear+1);
				String dayl=""+dayOfMonth;
				if(Integer.toString(monthOfYear+1).length()==1){
					monthl="0"+(monthOfYear+1);
				}
				if(Integer.toString(dayOfMonth).length()==1){
					dayl="0"+dayOfMonth;
				}
				txttavalodm.setText(""+year+"/"+monthl+"/"+dayl);
			}
		});

		datePickerDialogGregorian1.setOnCalandarChangeListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
			@Override
			public void onCalendarChanged(boolean isGregorian) {

				int yearM = datePickerDialogGregorian1.getSelectedDay().getYear();//2018
				int monthM = datePickerDialogGregorian1.getSelectedDay().getMonth();//2
				int dayM = datePickerDialogGregorian1.getSelectedDay().getDay();//18
				//convert to shamsi
				String dateShamsi = SolarCalendar.calSolarCalendar(yearM, monthM, dayM + 1);

				String[] dateSplite2 = dateShamsi.split("/");//shamsi

				String dayMF = dateSplite2[2];
				String monthMF = dateSplite2[1];
				String yearMF = dateSplite2[0];

				datePickerDialog.initialize(PassengerActivity.this, Integer.parseInt(yearMF), Integer.parseInt(monthMF), Integer.parseInt(dayMF));
				datePickerDialog.show(getSupportFragmentManager(), "DatepickerdialogRaft");

			}
		});
////////////
		datePickerDialogGregorian2 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog(1);
		//	datePickerDialogGregorian2.setMinDate(persianCalendarDatePicker.toGregorianCalendar());
		datePickerDialogGregorian2.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {

				String month=""+(monthOfYear+1);
				String day=""+dayOfMonth;
				if(Integer.toString(monthOfYear+1).length()==1){
					month="0"+(monthOfYear+1);
				}
				if(Integer.toString(dayOfMonth).length()==1){
					day="0"+dayOfMonth;
				}
				txtexp_passport.setText(""+year+"/"+month+"/"+day);

			}
		});

		//=====================================================================================================

//change button shamsi to milady (date picker)
		datePickerDialog.setOnCalandarChangeListener(new com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
			@Override
			public void onCalendarChanged(boolean isGregorian) {
				int yearSh = datePickerDialog.getSelectedDay().getYear();//1396
				int monthSh = datePickerDialog.getSelectedDay().getMonth();//10
				int daySh = datePickerDialog.getSelectedDay().getDay();//29
				//convert to miladi
				String[] dateSplite3 = date_server(yearSh, monthSh - 1, daySh - 1).split("-");


				String dayMF1 = dateSplite3[2];
				String monthMF1 = dateSplite3[1];
				String yearMF1 = dateSplite3[0];

				datePickerDialogGregorian1.initialize(PassengerActivity.this, Integer.parseInt(yearMF1), Integer.parseInt(monthMF1), Integer.parseInt(dayMF1));

				datePickerDialogGregorian1.show(getFragmentManager(), "DatePickerDialogGregorianRaft");
			}
		});


		String RengAge=txtTitleCountM.getText().toString();
		Log.e("RengAge:", RengAge);
///////////////setmin
		//min max tavalod solar
		if(RengAge.contains(getString(R.string._childP))){
			Log.e("RengAge:", RengAge);
			String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
			int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
			int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true)-12;
			int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true)-1 ;
			PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
			persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);

			datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());


			String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
			int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
			int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true)-2;
			int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true)-1 ;
			PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
			persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

			datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());
		}else if(RengAge.contains(getString(R.string._InfantP))){
			Log.e("RengAge:", RengAge);
			String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
			int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
			int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true)-2;
			int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true)-1 ;
			PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
			persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);

			datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());


			String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
			int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
			int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true);
			int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true)-1 ;
			PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
			persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

			datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());
		}else{
			Log.e("RengAge:", RengAge);
			String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
			int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
			int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true)-140;
			int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true)-1 ;
			PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
			persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);

			datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());


			String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
			int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
			int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true)-12;
			int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true)-1 ;
			PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
			persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

			datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());

		}
//min max passport solar
		PersianCalendar persianCalendar2 = new PersianCalendar();

		persianCalendar2.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth()+6, persianCalendarDatePicker.getPersianDay() );
		datePickerDialogGregorian2.setMinDate(persianCalendar2.toGregorianCalendar());
		persianCalendar2.set(persianCalendarDatePicker.getPersianYear()+6, persianCalendarDatePicker.getPersianMonth(), persianCalendarDatePicker.getPersianDay() );
		datePickerDialogGregorian2.setMaxDate(persianCalendar2.toGregorianCalendar());
		///////end setMin

///////////////////////////////

		/////////////////////
		data=new ArrayList<PurchaseFlightResult>();
		btnBack = (FancyButton) findViewById(R.id.btnBack);
		btnBack.setCustomTextFont("fonts/icomoon.ttf");
		btnBack.setText(getString(R.string.search_back_right));
		btnBack.setVisibility(View.VISIBLE);
		btnBack.setOnClickListener(this);

		//kharidar
		btnzanS = (RadioButton) findViewById(R.id.zanS);
		btnzanS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(btnzanS.isChecked()){
					btnmardS.setChecked(false);
					System.out.println("zan");
					Gensiyat="false";
				}
			}
		});

		btnmardS = (RadioButton) findViewById(R.id.mardS);
		btnmardS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(btnmardS.isChecked()){
					btnzanS.setChecked(false);
					System.out.println("mard");
					Gensiyat="true";
				}
			}
		});
		////////mosafer
		btnzan = (RadioButton) findViewById(R.id.zan);
		btnzan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(btnzan.isChecked()){
					btnmard.setChecked(false);
					System.out.println("zan");
					Gensiyat="false";
				}
			}
		});

		btnmard = (RadioButton) findViewById(R.id.mard);
		btnmard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(btnmard.isChecked()){
					btnzan.setChecked(false);
					System.out.println("mard");
					Gensiyat="true";
				}
			}
		});

		rlLoading = findViewById(R.id.rlLoading);
		rlRoot = findViewById(R.id.rlRoot);


		txt_hom = (ImageView) findViewById(R.id.txt_hom);
		txt_hom.setOnClickListener(this);

		txtMore = (TextView) findViewById(R.id.txtMore);
		txtMore.setOnClickListener(this);

		tvPrice= (TextView) findViewById(R.id.tvPrice);
		tvPrice.setOnClickListener(this);

		imgCount=(TextView) findViewById(R.id.imgCount);
		imgCount.setOnClickListener(this);

		txtSumKhadamat = (TextView) findViewById(R.id.txtSumKhadamat);
		txtSumKhadamat.setOnClickListener(this);
		txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(GET_PRICE_KHADAMAT)));


		txtnamem = (EditText) findViewById(R.id.txtnamem);
		txtnamem.setOnClickListener(this);
		txtnamem.addTextChangedListener(new GenericTextWatcher(txtnamem));
		txtnamem.setOnFocusChangeListener(this);

		txtfamilym = (EditText) findViewById(R.id.txtfamilym);
		txtfamilym.setOnClickListener(this);
		txtfamilym.addTextChangedListener(new GenericTextWatcher(txtfamilym));
		txtfamilym.setOnFocusChangeListener(this);

		txt_NationalCode_m= (EditText) findViewById(R.id.txt_NationalCode_m);
		txtnumber_passport= (EditText) findViewById(R.id.txtnumber_passport);
		txt_NationalCode_m.setOnClickListener(this);
		txt_NationalCode_m.setImeOptions(EditorInfo.IME_ACTION_DONE);
		txt_NationalCode_m.addTextChangedListener(new GenericTextWatcher(txt_NationalCode_m));
		txt_NationalCode_m.setOnFocusChangeListener(this);

		txtnumber_passport = (EditText) findViewById(R.id.txtnumber_passport);
		txtnumber_passport.setOnClickListener(this);
		txtnumber_passport.setImeOptions(EditorInfo.IME_ACTION_DONE);
		txtnumber_passport.addTextChangedListener(new GenericTextWatcher(txtnumber_passport));
		txtnumber_passport.setOnFocusChangeListener(this);




		txtTitle= (TextView) findViewById(R.id.tvTitle);
		txtTitle.setOnClickListener(this);


		btn_next_partnerInfo=(LinearLayout) findViewById(R.id.btn_next_partnerInfo);
		btn_next_partnerInfo.setOnClickListener(this);

		btn_nextm=(LinearLayout)findViewById(R.id.btn_nextm);
		btn_nextm.setOnClickListener(this);

		btn_taeed_khadamat=(LinearLayout)findViewById(R.id.btn_taeed_khadamat);
		btn_taeed_khadamat.setOnClickListener(this);

		linear_code_meli=(LinearLayout)findViewById(R.id.linear_code_meli);
		linear_code_meli.setOnClickListener(this);

		linear_number_passport=(LinearLayout)findViewById(R.id.linear_number_passport);
		linear_number_passport.setOnClickListener(this);

		linear_expdate=(LinearLayout)findViewById(R.id.linear_expdate);
		linear_expdate.setOnClickListener(this);

		btn_pardakht_factor=(Button)findViewById(R.id.btn_pardakht_factor);
		btn_pardakht_factor.setOnClickListener(this);

		textView4 = (ImageView) findViewById(R.id.textView4);

		textView4 = (ImageView) findViewById(R.id.textView4);
		tvfactorNumber = (TextView) findViewById(R.id.tvfactorNumber);

		btn_saler= (ImageView) findViewById(R.id.btn_saler);
		btn_mosaferan=(ImageView)findViewById(R.id.btn_mosaferan);
		btn_khadamat=(ImageView)findViewById(R.id.btn_khadamat);
		btn_pish_factor=(ImageView)findViewById(R.id.btn_pish_factor);

		txtSaler= (Button) findViewById(R.id.txtSaler);
		txtMasaferan=(Button)findViewById(R.id.txtMasaferan);
		txtKhadamat=(Button)findViewById(R.id.txtKhadamat);
		txtPishfactor=(Button)findViewById(R.id.txtPishfactor);

		btn_saler.setOnClickListener(this);
		btn_mosaferan.setOnClickListener(this);
		btn_khadamat.setOnClickListener(this);
		btn_pish_factor.setOnClickListener(this);
		setAnimation();

		linear_saler = (LinearLayout) findViewById(R.id.linear_saler);
		linear_mosaferan = (LinearLayout) findViewById(R.id.linear_mosaferan);
		linear_pish_factor= (LinearLayout) findViewById(R.id.linear_pish_factor);
		linearMahaleeghamat= (LinearLayout) findViewById(R.id.linearMahaleeghamat);
		linearMeliyat= (LinearLayout) findViewById(R.id.linearMeliyat);

		txtnameP= (EditText)findViewById(R.id.txtnameP);
		//	txtnameP.setHint("لطفا نام را فارسی وارد کنید");
		txtnameP.addTextChangedListener(new GenericTextWatcher(txtnameP));
		txtnameP.setOnFocusChangeListener(this);
		txtfamilyP= (EditText)findViewById(R.id.txtfamilyP);
		//	txtfamilyP.setHint("لطفا نام خانوادگی را فارسی وارد کنید");
		txtfamilyP.addTextChangedListener(new GenericTextWatcher(txtfamilyP));
		txtfamilyP.setOnFocusChangeListener(this);
		txtmobileP= (EditText)findViewById(R.id.txtmobileP);
		txtmobileP.addTextChangedListener(new GenericTextWatcher(txtmobileP));
		txtmobileP.setOnFocusChangeListener(this);
		txtkodemeliP= (EditText)findViewById(R.id.txtkodemeliP);
		txtkodemeliP.addTextChangedListener(new GenericTextWatcher(txtkodemeliP));
		txtkodemeliP.setOnFocusChangeListener(this);
		txtemeliP= (EditText)findViewById(R.id.txtemeliP);
		txtemeliP.addTextChangedListener(new GenericTextWatcher(txtemeliP));
		//txtemeliP.setOnFocusChangeListener(this);
		//txtemeliP.clearFocus();

		txtmeliyatm= (TextView)findViewById(R.id.txtmeliyatm);
		txtmeliyatm.setOnClickListener(this);
		txtmahale_eghamat= (TextView)findViewById(R.id.txtmahale_eghamat);
		txtmahale_eghamat.setOnClickListener(this);

		txt_shomare_factor= (TextView)findViewById(R.id.txt_shomare_factor);
		txt_shomare_factor.setOnClickListener(this);

		linear_list_khadamat = (LinearLayout) findViewById(R.id.linear_list_khadamat);

		listKhadamat = (NonScrollListView)findViewById(R.id.listKhadamat);
		myScrollView = (ScrollView) findViewById(R.id.layout_scroll);
		llDetailHotel = (LinearLayout) findViewById(R.id.llDetailHotel);
		llDetailPassanger = (LinearLayout) findViewById(R.id.llDetailPassanger);
		llDetailService = (LinearLayout) findViewById(R.id.llDetailService);
		llDetailFlight = (LinearLayout) findViewById(R.id.llDetailFlight);
		Utility.setAnimLoading(this);

		boolean checkDomestic=Prefs.getBoolean("IsDemostic",false);
		if(checkDomestic){
			linear_code_meli.setVisibility(View.VISIBLE);
			linear_number_passport.setVisibility(View.GONE);
			linear_expdate.setVisibility(View.GONE);
		}else{
			linear_code_meli.setVisibility(View.GONE);
			linear_number_passport.setVisibility(View.VISIBLE);
			linear_expdate.setVisibility(View.VISIBLE);
		}
		//////////////////////////
		// Spinner element
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		Spinner spinnerMosafer = (Spinner) findViewById(R.id.spinnerMosafer);

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);
		spinnerMosafer.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		List<String> categories = new ArrayList<String>();
		categories.add(getString(R.string.Please_choose_a_gender));
		categories.add(getString(R.string.man));
		categories.add(getString(R.string.Female));

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

		// Drop down dialog_custom style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
		spinnerMosafer.setAdapter(dataAdapter);
		////////////////////////////////

		btn_pardakht_factor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Prefs.putString("TypeGetPre", "F");
				Utility.openUrlCustomTab(PassengerActivity.this, paymentUrl);

			}
		});

		//////////////login user
		try{
			if (WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID() != -1) {

				txtfamilyP.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
				txtmobileP.setText( WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMobile());
				txtkodemeliP.setText( WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserNationalCode());
				txtemeliP.setText( WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMail());
				txtnameP.setText( WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF());
				//txtemeliP.clearFocus();

				//txtemeliP.setCursorVisible(false);
			}
		}catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
		//////////////

	}//end oncreate

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		switch (v.getId()){


//مسافر
			case R.id.txtmahale_eghamat:
				if(hasFocus){
					System.out.println("t");
				}else{
					System.out.println("f");
					if(txtmahale_eghamat.getText().toString() != null && txtmahale_eghamat.getText().toString().length()>1){
						((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#4d4d4d"));
						//flagMosafer=flagMosafer+"T";
					}else{
						//((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#ff3300"));
						txtmahale_eghamat.setError(getString(R.string.Please_enter_your_residence));
					}
				}
				break;
			case R.id.txtmeliyatm:
				if(hasFocus){
					System.out.println("t");
				}else{
					System.out.println("f");
					if(txtmeliyatm.getText().toString() != null && txtmeliyatm.getText().toString().length()>1){
						((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#4d4d4d"));
						//flagMosafer=flagMosafer+"T";
					}else{
						//((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#ff3300"));
						txtmeliyatm.setError(getString(R.string.Please_enter_your_nationality));
					}}
				break;
			case R.id.txttavalodm:
				if(hasFocus){//txtTitleCountM
					System.out.println("t");
				}else{
					System.out.println("f");
					if(txttavalodm.getText().toString() != null && txttavalodm.getText().toString().length()>4){
						((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#4d4d4d"));
						//flagMosafer=flagMosafer+"T";
					}else{
						//((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#ff3300"));
						txttavalodm.setError(getString(R.string.Please_enter_the_date_of_birth));
					}
				}
				break;

			case R.id.txtnamem:
				if(hasFocus){
					System.out.println("t");
				}else{
					System.out.println("f");
					if(txtnamem.getText().toString() != null)
						if( txtnamem.getText().toString().length()>1 && txtnamem.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")){
							((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#4d4d4d"));
							//flagMosafer=flagMosafer+"T";
						}else{
							//((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#ff3300"));
							txtnamem.setError(getString(R.string.Please_enter_a_name_in_English));
						}
				}
				break;
			case R.id.txtfamilym:
				if(hasFocus){
					System.out.println("t");
				}else{
					System.out.println("f");
					if(txtfamilym.getText().toString() != null)
						if( txtfamilym.getText().toString().length()>1 && txtfamilym.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$") ){
							((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#4d4d4d"));
							//flagMosafer=flagMosafer+"T";
						}else{
							//((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#ff3300"));
							txtfamilym.setError(getString(R.string.Please_enter_a_surname_in_English));
						}
				}
				break;
			case R.id.txtexp_passport:
				if(hasFocus){
					System.out.println("t");
				}else{
					System.out.println("f");
					if(txtexp_passport.getText().toString() != null && txtexp_passport.getText().toString().length()>4){
						((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#4d4d4d"));

					}else{
						//((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#ff3300"));
						txtexp_passport.setError(getString(R.string.Please_enter_your_passport_expiration_date));
					}
				}
				break;
			case R.id.txtnumber_passport:
				if(hasFocus){
					System.out.println("t");
				}else {
					System.out.println("f");
					if (txtnumber_passport.getText().toString().trim().length() > 6 && txtnumber_passport.getText().toString().trim().length() < 10 && (txtnumber_passport.getText().toString().trim().substring(0, 1).matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) && txtnumber_passport.getText().toString().trim().substring(1, txtnumber_passport.getText().toString().length() - 1).matches("[0-9]+")) {
						((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#4d4d4d"));

					} else {
						//((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
						txtnumber_passport.setError(getString(R.string.Please_enter_the_passport_number_correctly));
					}
					if (txtmeliyatm.getText().toString() != null && txtmeliyatm.getText().toString().length() > 4) {
					} else {
						//((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
						txtnumber_passport.setError(getString(R.string.Please_enter_the_passport_number));
					}
				}
				break;
			case R.id.txt_NationalCode_m:
				if(hasFocus){
					System.out.println("t");
				}else {
					System.out.println("f");
					if (txt_NationalCode_m.getText().toString().trim().length() > 6 && txt_NationalCode_m.getText().toString().trim().length() == 10 &&  txt_NationalCode_m.getText().toString().matches("[0-9]+")) {
						((EditText) findViewById(R.id.txt_NationalCode_m)).setTextColor(Color.parseColor("#4d4d4d"));

					} else {
						//((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
						txtnumber_passport.setError(getString(R.string.Please_enter_the_correct_code));
					}
					if (txtnumber_passport.getText().toString() != null && txtnumber_passport.getText().toString().length() == 10) {
					} else {
						//((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
						txtnumber_passport.setError(getString(R.string.Please_enter_the_correct_code));
					}
				}
				break;
			//خریدار
			case R.id.txtemeliP:
				if(hasFocus){
					System.out.println("t");
				}else {
					System.out.println("f");
					String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
					if (txtemeliP.getText().toString().matches(emailPattern) && txtemeliP.getText().toString().length() > 0) {
						//if( Patterns.EMAIL_ADDRESS.matcher(text).matches() ){
						((EditText) findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#4d4d4d"));

					} else {
						//((EditText) findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#ff3300"));
						txtemeliP.setError(getString(R.string.Please_enter_the_email));
					}
				}
				break;
			case R.id.txtnameP:
				if(hasFocus){
					System.out.println("t");
				}else{
					System.out.println("f");
					if(txtnameP.getText().toString() != null){
						if(Locale.getDefault().getLanguage().equals("en")){
							if( txtnameP.getText().toString().length()>2 && ((txtnameP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) || !(txtnameP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")))){
								((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));

							}else{

								txtnameP.setError(getString(R.string.Please_enter_a_name_in_Persian));
							}
						}else{
							if( txtnameP.getText().toString().length()>2 && !(txtnameP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))){
								((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));

							}else{

								txtnameP.setError(getString(R.string.Please_enter_a_name_in_Persian));
							}
						}

					}
				}
				break;
			case R.id.txtfamilyP:
				if(hasFocus){
					System.out.println("t");
				}else{
					System.out.println("f");
					if(txtfamilyP.getText().toString() != null){
						if(Locale.getDefault().getLanguage().equals("en")){
							if( txtfamilyP.getText().toString().length()>2 && ((txtfamilyP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) || !(txtfamilyP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")))){
								((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));

							}else{
								//((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
								txtfamilyP.setError(getString(R.string.Please_enter_last_name_in_Persian));
							}
						}else{
							if( txtfamilyP.getText().toString().length()>2 && !(txtfamilyP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))){
								((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));

							}else{
								//((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
								txtfamilyP.setError(getString(R.string.Please_enter_last_name_in_Persian));
							}
						}
					}
				}
				break;

			case R.id.txtmobileP:
				if(hasFocus){
					System.out.println("t");
				}else{
					System.out.println("f");
					if(txtmobileP.getText().toString() != null && txtmobileP.getText().toString().length()==11 && txtmobileP.getText().toString().matches("[0-9]+")){
						((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#4d4d4d"));

					}else{
						//((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#ff3300"));
						txtmobileP.setError(getString(R.string.Please_enter_the_mobile_number));
					}}
				break;
			case R.id.txtkodemeliP:
				if(hasFocus){
					System.out.println("t");
				}else{
					System.out.println("f");

					if(txtkodemeliP.getText().toString() != null)
						if( txtkodemeliP.getText().toString().length()==10  && txtkodemeliP.getText().toString().matches("[0-9]+")){
							((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#4d4d4d"));

						}else{
							//((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#ff3300"));
							txtkodemeliP.setError(getString(R.string.Please_enter_the_national_code));
						}
				}
				break;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Prefs.getBoolean("IsDemostic",true);
		Prefs.getLong("TPrice",0);

		Prefs.putString("Flag_First_Computing","F");
	}



	@Override
	public void onClick(View v) {
https://github.com/multidots/android-fingerprint-authentication.git
		switch (v.getId()) {


			case R.id.txtMore:

				linearMahaleeghamat.setVisibility(View.VISIBLE);
				linearMeliyat.setVisibility(View.VISIBLE);
				break;

			case R.id.btnBack:

				if (linear_pish_factor.getVisibility() == View.VISIBLE) {
					linear_pish_factor.setVisibility(View.GONE);
					linear_list_khadamat.setVisibility(View.VISIBLE);

					((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
					((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
					txtTitle.setText(R.string.Add_to_cart_services);
				}else if (linear_list_khadamat.getVisibility() == View.VISIBLE) {
					linear_list_khadamat.setVisibility(View.GONE);
					linear_mosaferan.setVisibility(View.VISIBLE);


					txtTitle.setText(R.string.passneger_info);
					((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
					((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
					////////////////////bazyabi atelaate akharin mosafer
					PassengerMosaferItems_Table items_Table=new PassengerMosaferItems_Table(PassengerActivity.this);
					CursorManager cursorM=items_Table.getMosaferById(counter-1);
					if(cursorM != null){
						for (int i = 0; i < cursorM.getCount(); i++) {

							txtnamem.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
							txtfamilym.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));
							txtnumber_passport.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));

							txttavalodm.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
							txtexp_passport.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));

							txtmahale_eghamat.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));

							txtmeliyatm.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));
							txtTitleCountM.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()));
						}
					}
					counter--;
					//xtTitleCountM.setText(getString(R.string.info_passenger) + counter);
					imgCount.setText(counter+"");
					///////////////////
				}else if (linear_mosaferan.getVisibility() == View.VISIBLE) {


					linear_mosaferan.setVisibility(View.GONE);
					linear_saler.setVisibility(View.VISIBLE);


					txtTitle.setText(R.string.Buyer_Specifications);
					((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
					((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
					//}
				}else if(linear_saler.getVisibility() == View.VISIBLE) {

					Prefs.putBoolean("BACK_HOME", true);

					finish();

				}


				break;
			case R.id.btn_next_partnerInfo:
				if(FlagTab){
					linear_saler.setVisibility(View.GONE);
					linear_mosaferan.setVisibility(View.VISIBLE);
					linear_list_khadamat.setVisibility(View.GONE);
					linear_pish_factor.setVisibility(View.GONE);

					((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
					((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
					((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

					((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
					((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
					((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
					txtTitle.setText(R.string.Traveler_info);
					setAnimation();

				}else{
					try{
						//jadvale mosafer khali beshe

						PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerActivity.this);
						//   db.openDB();
						db.dropTable();
						////////////////////////Validate
						String RqPartner_Address= "No.7,23rd St.,Khaled Eslamboli St.,Tehran,Iran";
						String RqPartner_Email= txtemeliP.getText().toString();
						String RqPartner_FirstNameFa= txtnameP.getText().toString();
						String RqPartner_Gender= Gensiyat;
						String RqPartner_LastNameFa= txtfamilyP.getText().toString();
						String RqPartner_Mobile= txtmobileP.getText().toString();
						String RqPartner_NationalCode= txtkodemeliP.getText().toString();
						String RqPartner_Tel= "21587632";


						String errorMessage="";
						String flagMosafer="T";
						///Validate
						String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
						if ( RqPartner_Email.matches(emailPattern) &&  RqPartner_Email.trim().length() > 0) {
							((EditText)findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#4d4d4d"));
							flagMosafer=flagMosafer+"T";
						}else{
							//((EditText)findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#ff3300"));
							flagMosafer=flagMosafer+"F";
							errorMessage=errorMessage+"\n"+"* "+getString(R.string.Email_format_is_correct);
						}
						//	if(RqPartner_FirstNameFa != null && RqPartner_FirstNameFa.length()>1){
						//if( RqPartner_FirstNameFa.trim().length()>3 && RqPartner_FirstNameFa.trim().length()<20 && !(RqPartner_FirstNameFa.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))){
						if(RqPartner_FirstNameFa != null){
							if(Locale.getDefault().getLanguage().equals("en")){
								if( RqPartner_FirstNameFa.length()>2 && ((RqPartner_FirstNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) || !(RqPartner_FirstNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) )){
									((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));
									flagMosafer=flagMosafer+"T";
								}else{
									//((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#ff3300"));
									flagMosafer=flagMosafer+"F";
									errorMessage=errorMessage+"\n"+"* "+getString(R.string.Name_of_at_least_2_characters_and_maximum_100_characters);
								}
							}else{
								if( RqPartner_FirstNameFa.length()>2 && !(RqPartner_FirstNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))){
									((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));
									flagMosafer=flagMosafer+"T";
								}else{
									//((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#ff3300"));
									flagMosafer=flagMosafer+"F";
									errorMessage=errorMessage+"\n"+"* "+getString(R.string.Name_of_at_least_2_characters_and_maximum_100_characters);
								}
							}
						}
						//if(RqPartner_LastNameFa != null && RqPartner_LastNameFa.length()>1){
						if(RqPartner_LastNameFa != null){
							if(Locale.getDefault().getLanguage().equals("en")){
								if( RqPartner_LastNameFa.length()>2 && ((RqPartner_LastNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) || !(RqPartner_LastNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) )){
									((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));
									flagMosafer=flagMosafer+"T";
								}else{
									//((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
									flagMosafer=flagMosafer+"F";
									errorMessage=errorMessage+"\n"+"* "+getString(R.string.The_last_name_is_at_least_2_characters_and_a_maximum_of_100_characters);
								}
							}else{
								if( RqPartner_LastNameFa.length()>2 && !(RqPartner_LastNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))){
									((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));
									flagMosafer=flagMosafer+"T";
								}else{
									//((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
									flagMosafer=flagMosafer+"F";
									errorMessage=errorMessage+"\n"+"* "+getString(R.string.The_last_name_is_at_least_2_characters_and_a_maximum_of_100_characters);
								}
							}
						}
						if(RqPartner_Mobile != null && RqPartner_Mobile.length()==11 && RqPartner_Mobile.matches("[0-9]+")){
							((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#4d4d4d"));
							flagMosafer=flagMosafer+"T";
						}else{
							//((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#ff3300"));
							flagMosafer=flagMosafer+"F";
							errorMessage=errorMessage+"\n"+"* "+getString(R.string.Enter_the_correct_mobile_format);
						}
					/*if(RqPartner_NationalCode != null)
						if( RqPartner_NationalCode.length()>1 && RqPartner_NationalCode.matches("[0-9]+")){*/
						if(RqPartner_NationalCode != null)
							if( RqPartner_NationalCode.length()==10 && RqPartner_NationalCode.matches("[0-9]+")){
								((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#4d4d4d"));
								flagMosafer=flagMosafer+"T";
							}else{
								//((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#ff3300"));
								flagMosafer=flagMosafer+"F";
								errorMessage=errorMessage+"\n"+"* "+getString(R.string.The_national_code_is_not_correct);
							}
						if (Gensiyat.contains("true") || Gensiyat.contains("false")){
							flagMosafer=flagMosafer+"T";
						}else{
							flagMosafer=flagMosafer+"F";
							errorMessage=errorMessage+"\n"+"* "+getString(R.string.Enter_your_gender);
						}
						//////////////////////////End Validate
						if(flagMosafer.contains("F")){
							//Toast.makeText(this,"اطلاعات ورودی نامعتبر است",2000).show();
							//Toast.makeText(this,errorMessage,2000).show();
							try {
								AlertDialogPassenger alertDialogPassenger = new AlertDialogPassenger(PassengerActivity.this,false,false);
								alertDialogPassenger.setText("" + "  " + errorMessage, getString(R.string.EditInput));
							}catch (Exception e){
							e.getMessage();
							}
						}else{
							//insert partner
							PassengerPartnerInfo_Table partnerInfo_Table = new PassengerPartnerInfo_Table(PassengerActivity.this);

							partnerInfo_Table.dropTable();
							partnerInfo_Table.openDB();

							partnerInfo_Table.insertData(RqPartner_Address, RqPartner_Email, RqPartner_FirstNameFa, RqPartner_Gender, RqPartner_LastNameFa, RqPartner_Mobile, RqPartner_NationalCode, RqPartner_Tel);

							partnerInfo_Table.closeDB();
							////////////////
							linear_saler.setVisibility(View.GONE);
							linear_pish_factor.setVisibility(View.GONE);
							linear_mosaferan.setVisibility(View.VISIBLE);
							txtTitle.setText(getString(R.string.passneger_info));
							//((Button)findViewById(R.id.btn_saler)).setBackgroundResource(R.drawable.blue_line_with_arrow_small);
							//((Button)findViewById(R.id.btn_saler)).setTextColor(Color.parseColor("#33ccff"));//
							Gensiyat="";
							((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);
							((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
							setAnimation();
						}

					}catch (Exception e) {
						System.out.println("Exception ::"+e);
					}
				}
				break;
			case R.id.txttavalodm:

				String RengAge=txtTitleCountM.getText().toString();

///////////////setmin
				if(RengAge.contains(getString(R.string._childP))){
					Log.e("RengAge:", RengAge);
					String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
					int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
					int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true)-12;
					int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true)-1 ;
					PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
					persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);

					datePickerDialog.setMinDate(persianCalendarDatePicker1);
					datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());


					String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
					int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
					int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true)-2;
					int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true)-1 ;
					PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
					persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

					datePickerDialog.setYearRange(currentYear,currentYear2);
					datePickerDialog.setMaxDate(persianCalendarDatePicker2);
					datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());

				}else if(RengAge.contains(getString(R.string._InfantP))){
					Log.e("RengAge:", RengAge);
					String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
					int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
					int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true)-2;
					int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true)-1 ;
					PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
					persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);

					datePickerDialog.setMinDate(persianCalendarDatePicker1);
					datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());


					String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
					int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
					int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true);
					int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true)-1 ;
					PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
					persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

					datePickerDialog.setYearRange(currentYear,currentYear2);
					datePickerDialog.setMaxDate(persianCalendarDatePicker2);
					datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());
				}else{

					String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
					int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
					int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true)-140;
					int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true)-1 ;
					PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
					persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);

					datePickerDialog.setMinDate(persianCalendarDatePicker1);
					datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());


					String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
					int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
					int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true)-12;
					int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true)-1 ;
					PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
					persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

					datePickerDialog.setYearRange(currentYear,currentYear2);
					datePickerDialog.setMaxDate(persianCalendarDatePicker2);
					datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());

				}
				///////end setMin

				if (!datePickerDialogGregorian1.isAdded())
					datePickerDialogGregorian1.show(getFragmentManager() , "DatePickerDialogGregorianRaft");
				/*DialogFragment newFragment2 = new DatePickerFragment(txtTitleCountM.getText().toString());
				newFragment2.show(getFragmentManager(), "datePicker");*/
				flag = true;
				break;
			case  R.id.txtexp_passport:
				if (!datePickerDialogGregorian2.isAdded())
					datePickerDialogGregorian2.show(getFragmentManager() , "DatePickerDialogGregorianRaft");
				/*DialogFragment newFragment3 = new DatePickerFragment("");
				newFragment3.show(getFragmentManager(), "datePicker");*/
				flag = false;
				break;
			case R.id.btn_nextm:
				LinearLayout mainLayout;
				mainLayout = (LinearLayout)findViewById(R.id.linear_list_khadamat);

				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);
				///////////////
				txtexp_passport.setScroller(new Scroller(this));
				ScrollView scrolMosafer=(ScrollView)findViewById(R.id.scrolMosafer);
				scrolMosafer.fullScroll(ScrollView.FOCUS_UP);

				if(FlagMosaferan){
					String Gender= Gensiyat;
					String Nationality=txtmahale_eghamat.getText().toString();// "ir";
					String Nationality_ID= txtmeliyatm.getText().toString().toLowerCase();
					String RqPassenger_Address= "No.7,23rd St.,Khaled Eslamboli St.,Tehran,Iran";
					String RqPassenger_Birthdate= txttavalodm.getText().toString();
					String RqPassenger_Email= "mahsa.azizi@eligasht.com";
					String RqPassenger_FirstNameEn= txtnamem.getText().toString();
					String RqPassenger_FirstNameFa= "مهسا";
					String RqPassenger_LastNameEn=txtfamilym.getText().toString();
					String RqPassenger_LastNameFa= "عزیزی";
					String RqPassenger_Mobile= "0235588456";
					String RqPassenger_NationalCode= txt_NationalCode_m.getText().toString();//codemeli
					String RqPassenger_PassExpDate= txtexp_passport.getText().toString();
					String RqPassenger_PassNo=txtnumber_passport.getText().toString();
					String RqPassenger_Tel= "25548632";

					String flagMosafer="T";

					String errorMessagePartner="";
					///Validate
					if(linear_code_meli.getVisibility()==View.VISIBLE){
						if(txt_NationalCode_m.getText().toString() != null && txt_NationalCode_m.getText().toString().length()==10){
							((EditText)findViewById(R.id.txt_NationalCode_m)).setTextColor(Color.parseColor("#4d4d4d"));
							flagMosafer=flagMosafer+"T";
						} else{

							flagMosafer=flagMosafer+"F";
							errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.The_national_code_is_not_correct);
						}
					}
					if(linear_number_passport.getVisibility()==View.VISIBLE){
						if( RqPassenger_PassNo.trim().length()>6 && RqPassenger_PassNo.trim().length()<10 && (RqPassenger_PassNo.trim().substring(0,1).matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) && RqPassenger_PassNo.trim().substring(1, RqPassenger_PassNo.length()-1).matches("[0-9]+")){
							((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#4d4d4d"));
							flagMosafer=flagMosafer+"T";
						}else{
							//((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
							flagMosafer=flagMosafer+"F";
							errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Enter_the_passport_number_correctly);
						}
					}
					if(Nationality != null && Nationality.length()>1){
						((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#4d4d4d"));
						flagMosafer=flagMosafer+"T";
					}else{
						//((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#ff3300"));
						flagMosafer=flagMosafer+"F";
						errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Enter_the_place_of_residence);
					}
					if(Nationality_ID != null && Nationality_ID.length()>1){
						((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#4d4d4d"));
						flagMosafer=flagMosafer+"T";
					}else{
						//((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#ff3300"));
						flagMosafer=flagMosafer+"F";
						errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Enter_your_nationality);
					}
					if(RqPassenger_Birthdate != null && RqPassenger_Birthdate.length()>4){
						((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#4d4d4d"));
						flagMosafer=flagMosafer+"T";
					}else{
						//((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#ff3300"));
						flagMosafer=flagMosafer+"F";
						errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Enter_the_date_of_birth);
					}
					////////////////////////////////////
					if(txtTitleCountM.getText().toString().contains(getString(R.string.child))){

					}else if(txtTitleCountM.getText().toString().contains(getString(R.string.baby))){

					}
					/////////////////////////////////
					if(RqPassenger_FirstNameEn != null)
						if( RqPassenger_FirstNameEn.length()>1 && RqPassenger_FirstNameEn.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")){
							((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#4d4d4d"));
							flagMosafer=flagMosafer+"T";
						}else{
							//((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#ff3300"));
							flagMosafer=flagMosafer+"F";
							errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Name_of_at_least_2_characters_and_maximum_100_characters);
						}
					if(RqPassenger_LastNameEn != null)
						if( RqPassenger_LastNameEn.length()>1 && RqPassenger_LastNameEn.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$") ){
							((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#4d4d4d"));
							flagMosafer=flagMosafer+"T";
						}else{
							//((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#ff3300"));
							flagMosafer=flagMosafer+"F";
							errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.The_last_name_is_at_least_2_characters_and_a_maximum_of_100_characters);
						}
					if(linear_expdate.getVisibility()==View.VISIBLE){
						if(RqPassenger_PassExpDate != null && RqPassenger_PassExpDate.length()>4){
							((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#4d4d4d"));
							flagMosafer=flagMosafer+"T";
						}else{
							//((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#ff3300"));
							flagMosafer=flagMosafer+"F";
							errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Enter_the_passport_expiration_date);
						}
					}
					if (Gensiyat.contains("true") || Gensiyat.contains("false")){
						flagMosafer=flagMosafer+"T";
					}else{
						flagMosafer=flagMosafer+"F";
						errorMessagePartner=errorMessagePartner+"\n"+"* "+getString(R.string.Please_choose_a_gender);
					}
					///endValidate


					if(flagMosafer.contains("F")){
						//Toast.makeText(this,"اطلاعات ورودی نامعتبر است!",2000).show();
						try {
							AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(PassengerActivity.this,false,false);
							AlertDialogPassenger.setText("" + "  " + errorMessagePartner, getString(R.string.EditInput));
						}catch (Exception e){
							e.getMessage();
						}//Toast.makeText(this,errorMessagePartner,2000).show();
					}else{
						PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerActivity.this);

						//db.dropTable();
						db.openDB();


						if(sum>0){
							System.out.println("gender:"+Gender);
							//	db.insertData(counter-1,Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);
							if(counter-1 ==1){
								db.insertData(counter-1,getString(R.string.First_passenger_information),"",Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);

							}else{
								db.insertData(counter-1,txtTitleCountM.getText().toString(),"",Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);
							}
							System.out.println("InsertMosafer:"+(counter-1)+" "+txtTitleCountM.getText().toString()+" "+RqPassenger_FirstNameEn);
							if(countB>=1) {
								System.out.println("countB:"+countB);
								//txtTitleCountM.setText(" اطلاعات مسافربزرگسال " + counter);
								//imgCount.setText(counter+"");
								countB--;
							}else if(countK>=1) {
								System.out.println("countK:"+countK);
								//txtTitleCountM.setText(" اطلاعات مسافرکودک " + counter);
								//imgCount.setText(counter+"");
								countK--;
							}else if(countN>=1) {
								System.out.println("countN:"+countN);
								//txtTitleCountM.setText(" اطلاعات مسافرنوزاد " + counter);
								//imgCount.setText(counter+"");
								countN--;
							}
							if(countB!=0){
								if(Locale.getDefault().getLanguage().equals("en")||Locale.getDefault().getLanguage().equals("tr")){
									txtTitleCountM.setText( getCounter(counter)+" " +getString(R.string.Passenger_information)+" " + getString(R.string.adult_));

								}else {
									txtTitleCountM.setText(getString(R.string.Passenger_information) + getCounter(counter) + getString(R.string.adult_));
								}
								imgCount.setText(counter+"");
							}
							else if(countK!=0){
								if(Locale.getDefault().getLanguage().equals("en")||Locale.getDefault().getLanguage().equals("tr")){
									txtTitleCountM.setText( getCounter(counter) +" " +getString(R.string.Passenger_information) +" " + getString(R.string.child_));

								}else {
									txtTitleCountM.setText(getString(R.string.Passenger_information) + getCounter(counter)+getString(R.string.child_));
								}
								imgCount.setText(counter+"");
							}
							else if(countN!=0){
								if(Locale.getDefault().getLanguage().equals("en")||Locale.getDefault().getLanguage().equals("tr")){
									txtTitleCountM.setText( getCounter(counter) +" " +getString(R.string.Passenger_information) +" " + getString(R.string.baby_));

								}else {
									txtTitleCountM.setText(getString(R.string.Passenger_information) + getCounter(counter)+getString(R.string.baby_));
								}
								imgCount.setText(counter+"");
							}


							System.out.println("counterMosafer:"+getCounter(counter)+counter);

							counter++;
							sum--;
							///pak kardan data haye mosafere ghabli:
							if(sum>0){
								//counter--;
								txtnamem.setFocusable(true);
								txttavalodm.setText("");
								txtnamem.setText("");
								txtfamilym.setText("");
								txtexp_passport.setText("");
								txtnumber_passport.getText().clear();
								btnzan.setChecked(false);
								btnmard.setChecked(false);
								txtnamem.setFocusable(true);
								Gensiyat="";
							}
							System.out.println("insert:"+"sum:"+sum);
						}
						db.closeDB();
						//	txtnamem.setFocusable(true);
						//insert mosafer


						linear_mosaferan.clearFocus();

					}


					//call api saler
					if(sum==0){
						System.out.println("APICALL:"+"sum:"+sum);
						System.out.println("insert:");

						sendRequestPerchase();//khadamat
					}
				}
				if(FlagTab){
					linear_saler.setVisibility(View.GONE);
					linear_mosaferan.setVisibility(View.GONE);
					linear_list_khadamat.setVisibility(View.VISIBLE);
					linear_pish_factor.setVisibility(View.GONE);



					((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
					((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
					((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

					((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
					((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
					((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
					txtTitle.setText(getString(R.string.Add_to_cart_services));
					setAnimation();

				}
				break;

			case R.id.btn_taeed_khadamat:

				//call api pishFactor
				RequestPurchase();
				break;

			case R.id.txtmeliyatm:
				final Intent intent4 = new Intent(this, NationalitycodeActivity.class);
				startActivityForResult(intent4, 1);

				break;
			case R.id.txtmahale_eghamat:
				final Intent intent3 = new Intent(this, CountrycodeActivity.class);
				startActivityForResult(intent3, 1);
				break;

			case R.id.btn_saler:
				if(FlagTab) {
					linear_saler.setVisibility(View.VISIBLE);
					linear_mosaferan.setVisibility(View.GONE);
					linear_list_khadamat.setVisibility(View.GONE);
					linear_pish_factor.setVisibility(View.GONE);

					((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
					((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
					((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
					((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
					((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
					((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
					txtTitle.setText(getString(R.string.Buyer_Specifications));
					setAnimation();
				}
				//myScrollView.setOnTouchListener(null);
				/*if (linear_pish_factor.getVisibility() == View.VISIBLE){
					linear_pish_factor.setVisibility(View.GONE);
					linear_list_khadamat.setVisibility(View.VISIBLE);

					((Button)findViewById(R.id.btn_pish_factor)).setBackgroundResource(R.drawable.factor_passenger_off);
					txtTitle.setText("مرحله 3/4: افزودن خدمات به سبد خرید");
				}else if (linear_list_khadamat.getVisibility() == View.VISIBLE){
					linear_list_khadamat.setVisibility(View.GONE);
					linear_mosaferan.setVisibility(View.VISIBLE);

					txtTitle.setText("مرحله 2/4:  اطلاعات مسافران را وارد کنید");
					((Button)findViewById(R.id.btn_khadamat)).setBackgroundResource(R.drawable.khadamat_passenger_off);
				}else if (linear_mosaferan.getVisibility() == View.VISIBLE){
					linear_mosaferan.setVisibility(View.GONE);
					linear_saler.setVisibility(View.VISIBLE);

					txtTitle.setText("مرحله 1/4:  مشخصات خریدار را وارد کنید");
					((Button)findViewById(R.id.btn_mosaferan)).setBackgroundResource(R.drawable.mosaferan_passenger_off);
				}*/
				break;
			case R.id.btn_mosaferan:
				if(FlagTab) {
					linear_saler.setVisibility(View.GONE);
					linear_mosaferan.setVisibility(View.VISIBLE);
					linear_list_khadamat.setVisibility(View.GONE);
					linear_pish_factor.setVisibility(View.GONE);

					((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
					((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
					((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

					((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
					((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
					((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
					txtTitle.setText(getString(R.string.passneger_info));
					setAnimation();
				}
				//.setOnTouchListener(null);
				break;
			case R.id.btn_khadamat:
				if(FlagTab){
					linear_saler.setVisibility(View.GONE);
					linear_mosaferan.setVisibility(View.GONE);
					linear_list_khadamat.setVisibility(View.VISIBLE);
					linear_pish_factor.setVisibility(View.GONE);


					((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
					((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
					((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

					((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
					((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
					((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
					txtTitle.setText(getString(R.string.Add_to_cart_services));
					setAnimation();
				}
				break;
			case R.id.btn_pish_factor:
				if(FlagTab) {
					linear_saler.setVisibility(View.GONE);
					linear_mosaferan.setVisibility(View.GONE);
					linear_list_khadamat.setVisibility(View.GONE);
					linear_pish_factor.setVisibility(View.VISIBLE);

					((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_on);
					((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
					((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);
					((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
					((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
					((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
					txtTitle.setText(getString(R.string.Approval_and_payment_of_pre_invoice));
					setAnimation();
				}
				//myScrollView.setOnTouchListener(null);
				break;
			case R.id.txt_hom:
				Prefs.putBoolean("BACK_HOME",true);
				Intent intent = new Intent("sendFinish");
				LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
				break;
		}

	}

	private void sendRequestPerchase() {

		rlLoading.setVisibility(View.VISIBLE);
		Utility.disableEnableControls(false,rlRoot);

		com.eligasht.service.model.flight.request.PurchaseFlight.RequestPurchaseFlight requestPurchaseFlightt = new com.eligasht.service.model.flight.request.PurchaseFlight.RequestPurchaseFlight();
		com.eligasht.service.model.flight.request.PurchaseFlight.Request request = new com.eligasht.service.model.flight.request.PurchaseFlight.Request();
		com.eligasht.service.model.flight.request.PurchaseFlight.Identity identity = new com.eligasht.service.model.flight.request.PurchaseFlight.Identity();
		request.setIdentity(identity);

		try {
			String GUID ="";
			String ResultUniqId="";
			Bundle extras = getIntent().getExtras();
			if(extras != null){
				GUID = extras.getString("Flight_GUID");
				ResultUniqId = SearchParvazActivity.globalResultUniqID;
			}


			request.setEchoToken(ResultUniqId);
			request.setBookingReferenceID(GUID);

			//mosaferan
			PassengerMosaferItems_Table items_Table=new PassengerMosaferItems_Table(PassengerActivity.this);
			CursorManager cursorM=items_Table.getAllMosafer();

			if(cursorM != null){
				List<PassList> passLists=new ArrayList<>();
				for (int i = 0; i < cursorM.getCount(); i++) {

					cursorM.moveToPosition(i);

					PassList passList=new PassList();

					passList.setGender(cursorM.getBoolean(PassengerMosaferItems_Table.Columns.Gender.value()));
					passList.setNationality( cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));
					passList.setNationalityID(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));

					passList.setRqPassengerAddress( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Address.value()));
					passList.setRqPassengerBirthdate( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
					passList.setRqPassengerEmail(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Email.value()));

					passList.setRqPassengerFirstNameEn( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
					passList.setRqPassengerFirstNameFa( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameFa.value()));
					passList.setRqPassengerLastNameEn(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));

					passList.setRqPassengerLastNameFa( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameFa.value()));
					passList.setRqPassengerMobile( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Mobile.value()));
					passList.setRqPassengerNationalCode(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_NationalCode.value()));

					passList.setRqPassengerPassExpDate( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));
					passList.setRqPassengerPassNo( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));
					passList.setRqPassengerTel(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Tel.value()));
					passLists.add(i,passList);

				}
				request.setPassList(passLists);
			}


			////kharidar
			PassengerPartnerInfo_Table partnerInfo_Table=new PassengerPartnerInfo_Table(PassengerActivity.this);
			CursorManager cursorManager=partnerInfo_Table.getPartner();
			cursorManager.moveToPosition(0);
			PartnerInfo partnerInfo=new PartnerInfo();

			partnerInfo.setRqPartnerAddress( cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Address.value()));
			partnerInfo.setRqPartnerEmail( cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Email.value()));
			partnerInfo.setRqPartnerFirstNameFa( cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_FirstNameFa.value()));
			partnerInfo.setRqPartnerGender( cursorManager.getBoolean(PassengerPartnerInfo_Table.Columns.RqPartner_Gender.value()));
			partnerInfo.setRqPartnerLastNameFa( cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_LastNameFa.value()));
			partnerInfo.setRqPartnerMobile( cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Mobile.value()));
			partnerInfo.setRqPartnerNationalCode( cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_NationalCode.value()));
			partnerInfo.setRqPartnerTel( cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Tel.value()));
			partnerInfo.setWebUserID ( Prefs.getString("userId","-1"));//Purchase

			request.setPartnerInfo(partnerInfo);

			request.setCulture( getString(R.string.culture));
			request.setType( "F");


			requestPurchaseFlightt.setRequest(request);
			Log.e("jjjssson", new Gson().toJson(requestPurchaseFlightt) );
			SingletonService.getInstance().getFlight().purchaseFlightAvail(new OnServiceStatus<com.eligasht.service.model.flight.response.PurchaseFlight.ResponsePurchaseFlight>() {
				@Override
				public void onReady(com.eligasht.service.model.flight.response.PurchaseFlight.ResponsePurchaseFlight responsePurchaseFlight) {

					rlLoading.setVisibility(View.GONE);
					Utility.disableEnableControls(true,rlRoot);

					FlagMosaferan=false;

					try {

						String GetError="";
						List<com.eligasht.service.model.error.Error> jError=null;
						// Getting JSON Array node
						com.eligasht.service.model.flight.response.PurchaseFlight.PurchaseFlightResult GetAirportsResult = responsePurchaseFlight.getPurchaseFlightResult();//jsonObj.getJSONObject("PurchaseFlightResult");//Error
						if(GetAirportsResult.getErrors()!= null){
							jError = GetAirportsResult.getErrors();
							com.eligasht.service.model.error.Error jPricedItinerary = jError.get(0);
							GetError = jPricedItinerary.getMessage();
						}
						if (GetError.length()>1) {
							AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(PassengerActivity.this,false,true);
							AlertDialogPassenger.setText(""+"  "+GetError,getString(R.string.massege));

						}else{

							List<Service> jArray = GetAirportsResult.getServices();
							TmpReserveResult jsonResult = GetAirportsResult.getTmpReserveResult();

							Prefs.putString("BookingCode_NumFactor", jsonResult.getBookingCode()+"");

							for (int i = 0; i < jArray.size(); i++) {
								Service json_data = jArray.get(i);
								com.eligasht.service.model.flight.response.PurchaseFlight.ExcursionDta excursionData = json_data.getExcursionDta();

								PurchaseFlightResult purchaseFlightResult = new PurchaseFlightResult();
								purchaseFlightResult.setCityEn(json_data.getCityEn());
								purchaseFlightResult.setCityFa(json_data.getCityFa());
								purchaseFlightResult.setCurrency_ID(json_data.getCurrencyID()+"");

								purchaseFlightResult.setHasFlight(json_data.getHasFlight()+"");
								purchaseFlightResult.setHasHotel(json_data.getHasHotel()+"");
								purchaseFlightResult.setLoadDB(json_data.getLoadDB()+"");

								purchaseFlightResult.setServiceAdlPrice(json_data.getServiceAdlPrice()+"");
								purchaseFlightResult.setServiceChdPrice(json_data.getServiceChdPrice()+"");
								purchaseFlightResult.setServiceDescEn(json_data.getServiceDescEn());

								purchaseFlightResult.setServiceDescFa(json_data.getServiceDescFa());
								purchaseFlightResult.setServiceID(json_data.getServiceID());
								purchaseFlightResult.setServiceImgURL(json_data.getServiceImgURL());

								purchaseFlightResult.setServiceInfPrice(json_data.getServiceInfPrice()+"");
								purchaseFlightResult.setServiceNameEn(json_data.getServiceNameEn());
								purchaseFlightResult.setServiceNameFa(json_data.getServiceNameFa());


								purchaseFlightResult.setServiceTypeEn(json_data.getServiceTypeEn());
								purchaseFlightResult.setServiceTypeFa(json_data.getServiceTypeFa());
								purchaseFlightResult.setServiceTypeID(json_data.getServiceTypeID()+"");

								purchaseFlightResult.setServiceTotalPrice( Long.parseLong(json_data.getServiceTotalPrice()));
								purchaseFlightResult.setSelectID(json_data.getSelectID());

								purchaseFlightResult.setBookingCode(jsonResult.getBookingCode()+"");

								purchaseFlightResult.setExcursionData(new ExcursionDta(excursionData.getArrialAirportCode(),
										excursionData.getArrialAirportName(),
										excursionData.getArrivalFltDate()
										,excursionData.getArrivalFltNo(),
										excursionData.getArrivalFltTime(),
										excursionData.getCityID()+"",excursionData.getDepartureFltDate(),
										excursionData.getDepartureFltNo(),excursionData.getDepartureFltTime(),
										excursionData.getHotelID()+"",excursionData.getHotelNameEn()+"",excursionData.getPassengerList()));

								purchaseFlightResult.setFlag(false);
								data.add(purchaseFlightResult);
							}

							// Setup and Handover data to recyclerview

							linear_saler.setVisibility(View.GONE);
							linear_mosaferan.setVisibility(View.GONE);
							linear_pish_factor.setVisibility(View.GONE);

							linear_list_khadamat.setVisibility(View.VISIBLE);
							FlagTab=true;

							((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
							((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
							txtTitle.setText(R.string.Add_to_cart_services);

							mAdapter = new GetKhadmatAdapter(PassengerActivity.this, data, PassengerActivity.this,0);

							mAdapter.setData(data);
							listKhadamat.setAdapter(mAdapter);
							if(linear_code_meli.getVisibility()==View.VISIBLE){
								listKhadamat.setVisibility(View.GONE);
							}
							setAnimation();
						}
					} catch (Exception e) {
						AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(PassengerActivity.this,true,true);
						AlertDialogPassenger.setText(R.string.Error_getting_information_from_eli+"",getString(R.string.massege));

					}
				}

				@Override
				public void onError(String message) {
					System.out.println("PurchesFlightError: "+message);
					rlLoading.setVisibility(View.GONE);
					Utility.disableEnableControls(true,rlRoot);
					AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(PassengerActivity.this,true,true);
					AlertDialogPassenger.setText(R.string.Error_getting_information_from_eli+"",getString(R.string.massege));
				}
			}, requestPurchaseFlightt);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onReady(ResponsePurchaseFlight responsePurchaseFlight) {

		Log.e("PurchesRespons:",responsePurchaseFlight.getPurchaseServiceResult().getSuccessResult()+"");

		rlLoading.setVisibility(View.GONE);
		Utility.disableEnableControls(true,rlRoot);
		try {

			PurchaseServiceResult GetAirportsResult = responsePurchaseFlight.getPurchaseServiceResult();//jsonObj.getJSONObject("PurchaseServiceResult");
			int successResult=GetAirportsResult.getSuccessResult();
			if(successResult==0){
				//get Error
				List<com.eligasht.service.model.error.Error> getError = GetAirportsResult.getErrors();
				String message= getError.get(0).getDetailedMessage();
				AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(PassengerActivity.this,false,true);
				AlertDialogPassenger.setText(message,getString(R.string.massege));
			}

			if(successResult >1) {
				txt_shomare_factor.setText(GetAirportsResult.getSuccessResult()+"");

				tvfactorNumber.setText(GetAirportsResult.getSuccessResult()+"");

				textView4.setImageBitmap(getBitmap(GetAirportsResult.getSuccessResult()+"", 128, 300, 150));
			}else{

				AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(PassengerActivity.this,true,true);
				AlertDialogPassenger.setText(getString(R.string.An_error_has_occurred),getString(R.string.massege));

				Prefs.putBoolean("BACK_HOME", true);

				finish();

			}

			// Setup and Handover data to recyclerview
			((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_on);
			((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
			txtTitle.setText(getString(R.string.Approval_and_payment_of_pre_invoice));

			linear_saler.setVisibility(View.GONE);
			linear_mosaferan.setVisibility(View.GONE);
			linear_list_khadamat.setVisibility(View.GONE);
			linear_pish_factor.setVisibility(View.VISIBLE);
			FlagTab=true;
			//call api GetPreFactorDetails

			RequestPreFactorDetails();

		} catch (Exception e) {
			AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(PassengerActivity.this,true,true);
			AlertDialogPassenger.setText(getString(R.string.Error_getting_information_from_eli),getString(R.string.massege));

		}
	}

	private void RequestPreFactorDetails() {
		ProgressDialog pdLoading = new ProgressDialog(PassengerActivity.this);

		//this method will be running on UI thread
		rlLoading.setVisibility(View.VISIBLE);
		Utility.disableEnableControls(false,rlRoot);
		RequestPreFactorDetails requestPreFactorDetails = new RequestPreFactorDetails();
		com.eligasht.service.model.flight.request.PreFactorDetails.Request request = new com.eligasht.service.model.flight.request.PreFactorDetails.Request();
		com.eligasht.service.model.flight.request.PreFactorDetails.Identity identity = new com.eligasht.service.model.flight.request.PreFactorDetails.Identity();
		request.setIdentity(identity);

		request.setCulture(getString(R.string.culture));
		request.setType("F");

		request.setInvoiceNo(tvfactorNumber.getText().toString());//perches service

		requestPreFactorDetails.setRequest(request);
		SingletonService.getInstance().getFlight().flightPreFactorDetailAvail(new OnServiceStatus<ResponsePreFactorDetails>() {
			@Override
			public void onReady(ResponsePreFactorDetails responsePreFactorDetails) {


				Log.e("ResponsePreFactor",responsePreFactorDetails.getGetPreFactorDetailsResult().getPreFactor().toString()+"");


				rlLoading.setVisibility(View.GONE);
				Utility.disableEnableControls(true,rlRoot);
				try {
					// Getting JSON Array node
					GetPreFactorDetailsResult GetAirportsResult = responsePreFactorDetails.getGetPreFactorDetailsResult();//.getJSONObject("GetPreFactorDetailsResult");

					PreFactor jArray = GetAirportsResult.getPreFactor();//("PreFactor");//FactorSummary

					//FactorSummary
					FactorSummary jFact = jArray.getFactorSummary();
					if (jFact.getOnlinePaymentURL()==null||jFact.getOnlinePaymentURL().equals("")|| TextUtils.isEmpty(jFact.getOnlinePaymentURL())){
						btn_pardakht_factor.setVisibility(View.INVISIBLE);
					}else{
						paymentUrl = jFact.getOnlinePaymentURL();
					}

					int RqBase_ID = jFact.getRqBaseID();//Int("RqBase_ID");
					//////////////////////////////
					long totalprice = jFact.getTotalPrice();

					tvPrice.setText(totalprice > 0 ? String.valueOf(NumberFormat.getInstance().format(totalprice))+" "+getString(R.string.Rial) : "It");//String.valueOf(NumberFormat.getInstance().format(totalprice)) + " ریال ");
//for hotel==========================================================================================
					final RecyclerView recyclerViewHotel = (RecyclerView) findViewById(R.id.recyclerView);
					recyclerViewHotel.addItemDecoration(new DividerItemDecoration(PassengerActivity.this, 1));
					recyclerViewHotel.setLayoutManager(new LinearLayoutManager(PassengerActivity.this));
					ArrayList<HotelPreFactorModel> hotelPreFactorModels = new ArrayList<>();

					List<PreFactorHotel> jArray2 = jArray.getPreFactorHotels();//PreFactorHotels();


			for (int i = 0; i < jArray2.size(); i++) {
				hotelPreFactorModels.add(new HotelPreFactorModel(jArray2.get(i).getHotelNameE(),
						Utility.dateShow(jArray2.get(i).getHotelChekin())
						, Utility.dateShow(jArray2.get(i).getHotelChekout()),
						jArray2.get(i).getAdlCount()+"",
						jArray2.get(i).getChdCount()+"",jArray2.get(i).getRoomTitleFa()));

			}
			if (!hotelPreFactorModels.isEmpty()) {
				recyclerViewHotel.setAdapter(new HotelPreFactorAdapter(hotelPreFactorModels));
				llDetailHotel.setVisibility(View.VISIBLE);
			}
//for passenger======================================================================================

					final RecyclerView recyclerViewPassenger = (RecyclerView) findViewById(R.id.recyclerViewPassenger);
					recyclerViewPassenger.addItemDecoration(new DividerItemDecoration(PassengerActivity.this, 1));
					recyclerViewPassenger.setLayoutManager(new LinearLayoutManager(PassengerActivity.this));
					ArrayList<PassengerPreFactorModel> passengerPreFactorModels = new ArrayList<>();

					List<RequestPassenger> jArray3 = jArray.getRequestPassenger();//RequestPassenger");

					System.out.println("json detail mossfaer:"+jArray3);
					for (int i = 0; i < jArray3.size(); i++) {
						passengerPreFactorModels.add(new PassengerPreFactorModel(jArray3.get(i).getGender()+"",jArray3.get(i).getNationality(),
								jArray3.get(i).getRqPassengerBirthdate(),jArray3.get(i).getRqPassengerPassNo(),
								jArray3.get(i).getRqPassengerName(),jArray3.get(i).getRqPassengerNationalCode()+""));

					}
					if (!passengerPreFactorModels.isEmpty()) {
						llDetailPassanger.setVisibility(View.VISIBLE);
						recyclerViewPassenger.setAdapter(new PassangerPreFactorAdapter(passengerPreFactorModels));

					}


					//for Services=============================================================================
					final RecyclerView recyclerViewService = (RecyclerView) findViewById(R.id.recyclerViewService);
					recyclerViewService.addItemDecoration(new DividerItemDecoration(PassengerActivity.this, 1));
					recyclerViewService.setLayoutManager(new LinearLayoutManager(PassengerActivity.this));
					ArrayList<ServicePreFactorModel> servicePreFactorModels = new ArrayList<>();
					List<PreFactorService> jArray4 = jArray.getPreFactorServices();

			for (int i = 0; i < jArray4.size(); i++) {
				servicePreFactorModels.add(new ServicePreFactorModel(jArray4.get(i).getServiceNameEn(),
						jArray4.get(i).getServicePrice()+"",jArray4.get(i).getServiceType(),
						jArray4.get(i).getCityFa(),jArray4.get(i).getServiceNameFa(),jArray4.get(i).getCountryFa()));

			}
			if (!servicePreFactorModels.isEmpty()) {
				llDetailService.setVisibility(View.VISIBLE);
				recyclerViewService.setAdapter(new ServicePreFactorAdapter(servicePreFactorModels));

			}
					//for flight==================================================================================
					final RecyclerView recyclerViewFlight = (RecyclerView) findViewById(R.id.recyclerViewFlight);
					recyclerViewFlight.addItemDecoration(new DividerItemDecoration(PassengerActivity.this, 1));
					recyclerViewFlight.setLayoutManager(new LinearLayoutManager(PassengerActivity.this));
					ArrayList<FlightPreFactorModel> flightPreFactorModels = new ArrayList<>();
					List<PreFactorFlight> jArray5 = jArray.getPreFactorFlights();

					for (int i = 0; i < jArray5.size(); i++) {

						flightPreFactorModels.add(new FlightPreFactorModel(jArray5.get(i).getAirlineNameFa(),
								jArray5.get(i).getDepAirPortFa()+"",//String("DepAirPortFa"),
								jArray5.get(i).getArrAirPortFa()+"",//String("ArrAirPortFa"),
								Utility.dateShow(jArray5.get(i).getFltDate())+"",//String("FltDate")),
								jArray5.get(i).getFltTime()+"",//String("FltTime"),
								//Utility.dateShow(jArray5.getJSONObject(i).getString("FltCheckinTime")),
								jArray5.get(i).getFltCheckinTime()+"",//;//String("FltCheckinTime"),

								jArray5.get(i).getFltNumber()+"",
								jArray5.get(i).getAirlineNameFa()+"",
								jArray5.get(i).getDepartureCityFa()+"",jArray5.get(i).getAirlineCode()+""));

					}
					if (!flightPreFactorModels.isEmpty()) {
						llDetailFlight.setVisibility(View.VISIBLE);
						recyclerViewFlight.setAdapter(new FlightPreFactorAdapter(flightPreFactorModels));


					}
					setAnimation();
				} catch (Exception e) {
					AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(PassengerActivity.this,true,true);
					AlertDialogPassenger.setText(getString(R.string.Error_getting_information_from_eli),getString(R.string.massege)+"fff");

				}
			}

			@Override
			public void onError(String message) {
				Log.e("ResponsErroe","ee");
			}
		}, requestPreFactorDetails);


	}

	@Override
	public void onError(String message) {
		System.out.println("PurchesonError: "+message);
		rlLoading.setVisibility(View.GONE);
		Utility.disableEnableControls(true,rlRoot);

	}
	private void RequestPurchase() {
		ProgressDialog pdLoading = new ProgressDialog(PassengerActivity.this);

		//this method will be running on UI thread
		rlLoading.setVisibility(View.VISIBLE);
		Utility.disableEnableControls(false,rlRoot);

		RequestPurchaseFlight requestPurchaseFlight = new RequestPurchaseFlight();
		com.eligasht.service.model.flight.request.purchaseServiceFlight.Request request = new com.eligasht.service.model.flight.request.purchaseServiceFlight.Request();
		JSONObject jsone = new JSONObject();
		JSONObject manJson = new JSONObject();
		JSONObject identityJson = new JSONObject();

		com.eligasht.service.model.flight.request.purchaseServiceFlight.Identity identity = new com.eligasht.service.model.flight.request.purchaseServiceFlight.Identity();

		request.setIdentity(identity);

		request.setCulture(getString(R.string.culture));
		request.setType("F");
		request.setRqBaseID(Prefs.getString("BookingCode_NumFactor", ""));
		request.setServiceStr(Prefs.getString("Select_ID_khadamat", ""));
		request.setExc("");
		request.setInsCoverageXML("");

		request.setInsPrcieXML("");
		request.setInsPlanCode(-1);

		requestPurchaseFlight.setRequest(request);
		SingletonService.getInstance().getFlight().flightPurchaseAvail(PassengerActivity.this,requestPurchaseFlight);
	}

	private void setAnimation() {
		YoYo.with(Techniques.BounceInRight)
				.duration(600)
				.playOn(btn_saler);
		YoYo.with(Techniques.BounceInRight)
				.duration(600)
				.playOn(btn_mosaferan);
		YoYo.with(Techniques.BounceInRight)
				.duration(600)
				.playOn(btn_khadamat);
		YoYo.with(Techniques.BounceInRight)
				.duration(600)
				.playOn(btn_pish_factor);

		YoYo.with(Techniques.BounceInRight)
				.duration(600)
				.playOn(txtSaler);
		YoYo.with(Techniques.BounceInRight)
				.duration(600)
				.playOn(txtMasaferan);
		YoYo.with(Techniques.BounceInRight)
				.duration(600)
				.playOn(txtKhadamat);
		YoYo.with(Techniques.BounceInRight)
				.duration(600)
				.playOn(txtPishfactor);
	}

	@Override
	public void onResume(){
		super.onResume();
		long gheymatKh=Prefs.getLong("Tprice",0);

		mAdapter = new GetKhadmatAdapter(PassengerActivity.this, data, PassengerActivity.this,gheymatKh);
		mAdapter.setData(data);
		listKhadamat.setAdapter(mAdapter);
		final ScrollView scroll_partner=(ScrollView)findViewById(R.id.scroll_partner);
		//scroll_partner.fullScroll(ScrollView.FOCUS_UP);
		scroll_partner.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				scroll_partner.getViewTreeObserver().removeOnPreDrawListener(this);
				scroll_partner.setScrollY(0);
				return false;
			}
		});
		scroll_partner.clearFocus();

	}
	public String getCounter(int i) {
		String s="";
		switch (i) {
			case 0:
				s= getString(R.string.First);
				break;
			case 1:
				s= getString(R.string.First);
				break;
			case 2:
				s= getString(R.string.Second);
				break;
			case 3:
				s= getString(R.string.Third);
				break;
			case 4:
				s= getString(R.string.Fourth);
				break;
			case 5:
				s= getString(R.string.Fifth);
				break;
			case 6:
				s= getString(R.string.Sixth);
				break;
			case 7:
				s= getString(R.string.Seventh);
				break;
			case 8:
				s= getString(R.string.Eighth);
				break;
			case 9:
				s=getString(R.string.ninth);
				break;
			default:

				System.out.println("Unknown result");

				break;

		}
		return s;

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1 && resultCode == Activity.RESULT_OK){
			String countryCode = data.getStringExtra(CountrycodeActivity.RESULT_CONTRYCODE);//RESULT_CONTRYNAME
			String countryName = data.getStringExtra(CountrycodeActivity.RESULT_CONTRYNAME);

			String nationalityCode = data.getStringExtra(NationalitycodeActivity.RESULT_NATIONALITYCODE);
			String nationalityName = data.getStringExtra(NationalitycodeActivity.RESULT_NATIONALITYNAME);

			if(countryCode != null)
				txtmahale_eghamat.setText(countryCode+"");//txtmahale_eghamat.setText(countryCode+" "+countryName);
			if(nationalityCode != null)
				txtmeliyatm.setText(nationalityCode+"");//txtmeliyatm.setText(nationalityCode+" "+nationalityName);
		}
	}

	@Override
	public void onBackPressed() {

		if (linear_pish_factor.getVisibility() == View.VISIBLE) {
			linear_pish_factor.setVisibility(View.GONE);
			linear_list_khadamat.setVisibility(View.VISIBLE);

			((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
			((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
			txtTitle.setText(getString(R.string.Add_to_cart_services));
		}else if (linear_list_khadamat.getVisibility() == View.VISIBLE) {
			linear_list_khadamat.setVisibility(View.GONE);
			linear_mosaferan.setVisibility(View.VISIBLE);


			txtTitle.setText(getString(R.string.passneger_info));
			((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
			((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
			////////////////////bazyabi atelaate akharin mosafer
			PassengerMosaferItems_Table items_Table=new PassengerMosaferItems_Table(PassengerActivity.this);
			CursorManager cursorM=items_Table.getMosaferById(counter-1);
			if(cursorM != null){
				for (int i = 0; i < cursorM.getCount(); i++) {

					txtnamem.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
					txtfamilym.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));
					txtnumber_passport.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));

					txttavalodm.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
					txtexp_passport.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));

					txtmahale_eghamat.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));

					txtmeliyatm.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));
					txtTitleCountM.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()));

					System.out.println("InsertMosaferGet:"+cursorM.getString(PassengerMosaferItems_Table.Columns.ID.value())+" "+cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value())+" "+cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
				}
			}
			counter--;

			imgCount.setText(counter+"");
			///////////////////
		}else if (linear_mosaferan.getVisibility() == View.VISIBLE) {

			linear_mosaferan.setVisibility(View.GONE);
			linear_saler.setVisibility(View.VISIBLE);


			txtTitle.setText(getString(R.string.Buyer_Specifications));
			((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
			((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
			//	}
		}else if(linear_saler.getVisibility() == View.VISIBLE) {

			Prefs.putBoolean("BACK_HOME", true);

			finish();

		}
	}
	@Override
	public void searchTextChanged(String searchText) {


	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
							   long id) {
		// On selecting a spinner item
		String item = parent.getItemAtPosition(position).toString();
		if(item.contains(getString(R.string.Female))) {
			Gensiyat = "false";
		}else if(item.contains(getString(R.string.man))){
			Gensiyat="true";
		}


	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}


	public static void updateTotalInfos(long serviceTotalPrice) {
		// TODO Auto-generated method stub
		txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(serviceTotalPrice))+"");

	}

	private class GenericTextWatcher implements TextWatcher{

		private View view;
		private GenericTextWatcher(View view) {
			this.view = view;
		}

		public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
		public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

		public void afterTextChanged(Editable editable) {
			String text = editable.toString();

			switch(view.getId()){
				/*//مسافر
				case R.id.txtmahale_eghamat:
					if(text != null && text.length()>1){
						((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#4d4d4d"));
						//flagMosafer=flagMosafer+"T";
					}else{
						((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#ff3300"));
						txtmahale_eghamat.setError("لطفا محل اقامت را وارد کنید ");
					}
					break;
				case R.id.txtmeliyatm:
					if(text != null && text.length()>1){
						((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#4d4d4d"));
						//flagMosafer=flagMosafer+"T";
					}else{
						((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#ff3300"));
						txtmeliyatm.setError("لطفا ملیت را وارد کنید ");
					}
					break;
				case R.id.txttavalodm:
					if(text != null && text.length()>4){
						((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#4d4d4d"));
						//flagMosafer=flagMosafer+"T";
					}else{
						((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#ff3300"));
						txttavalodm.setError("لطفا تاریخ تولد را وارد کنید ");
					}
					break;

				case R.id.txtnamem:
					if(text != null)
						if( text.length()>1 && text.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")){
							((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#4d4d4d"));
							//flagMosafer=flagMosafer+"T";
						}else{
							((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#ff3300"));
							txtnamem.setError("لطفا نام را انگلیسی وارد کنید ");
						}
					break;
				case R.id.txtfamilym:
					if(text != null)
						if( text.length()>1 && text.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$") ){
							((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#4d4d4d"));
							//flagMosafer=flagMosafer+"T";
						}else{
							((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#ff3300"));
							txtfamilym.setError("لطفا نام خانوادگی را انگلیسی وارد کنید ");
						}
					break;
				case R.id.txtexp_passport:
					if(text != null && text.length()>4){
						((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#4d4d4d"));

					}else{
						((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#ff3300"));
						txtexp_passport.setError("لطفا انقضاء پاسپورت را وارد کنید ");
					}
					break;
				case R.id.txtnumber_passport:

					if( text.trim().length()>6 && text.trim().length()<10 && (text.trim().substring(0,1).matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) && text.trim().substring(1, text.length()-1).matches("[0-9]+")){
						((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#4d4d4d"));

					}else{
						((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
						txtnumber_passport.setError("لطفا شماره پاسپورت را صحیح وارد کنید ");
					}
					if(text != null && text.length()>4){
					}else{
						((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
						txtnumber_passport.setError("لطفا شماره پاسپورت را وارد کنید ");
					}

					break;

				//خریدار
				case R.id.txtemeliP:
					String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
					if (text.matches(emailPattern) && text.length() > 0){
						//if( Patterns.EMAIL_ADDRESS.matcher(text).matches() ){
						((EditText)findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#4d4d4d"));

					}else{
						((EditText)findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#ff3300"));
						txtemeliP.setError("لطفا ایمیل را وارد کنید ");
					}

					break;
				case R.id.txtnameP:

					if(text != null)
						if( text.length()>2 && !(text.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))){
							((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));

						}else{
							((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#ff3300"));
							txtnameP.setError("لطفا نام را فارسی وارد کنید ");
						}
					break;
				case R.id.txtfamilyP:

					if(text != null)
						if( text.length()>2 && !(text.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))){
							((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));

						}else{
							((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
							txtfamilyP.setError("لطفا نام خانوادگی را فارسی وارد کنید ");
						}
					break;

				case R.id.txtmobileP:

					if(text != null && text.length()>9 && text.matches("[0-9]+")){
						((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#4d4d4d"));

					}else{
						((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#ff3300"));
						txtmobileP.setError("لطفا شماره موبایل را وارد کنید ");
					}
					break;
				case R.id.txtkodemeliP:
					if(text != null)
						if( text.length()>9 &&  text.length()<12 && text.matches("[0-9]+")){
							((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#4d4d4d"));

						}else{
							((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#ff3300"));
							txtkodemeliP.setError("لطفا کد ملی را وارد کنید ");
						}
					break;*/

			}
		}
	}


	public static Bitmap getBitmap(String barcode, int barcodeType, int width, int height)
	{
		Bitmap barcodeBitmap = null;
		BarcodeFormat barcodeFormat = convertToZXingFormat(barcodeType);
		try
		{
			barcodeBitmap = encodeAsBitmap(barcode, barcodeFormat, width, height);
		}
		catch (WriterException e)
		{
			e.printStackTrace();
		}
		return barcodeBitmap;
	}

	private static BarcodeFormat convertToZXingFormat(int format)
	{
		switch (format)
		{
			case 8:
				return BarcodeFormat.CODABAR;
			case 1:
				return BarcodeFormat.CODE_128;
			case 2:
				return BarcodeFormat.CODE_39;
			case 4:
				return BarcodeFormat.CODE_93;
			case 32:
				return BarcodeFormat.EAN_13;
			case 64:
				return BarcodeFormat.EAN_8;
			case 128:
				return BarcodeFormat.ITF;
			case 512:
				return BarcodeFormat.UPC_A;
			case 1024:
				return BarcodeFormat.UPC_E;
			//default 128?
			default:
				return BarcodeFormat.CODE_128;
		}
	}


	/**************************************************************
	 * getting from com.google.zxing.client.android.encode.QRCodeEncoder
	 *
	 * See the sites below
	 * http://code.google.com/p/zxing/
	 * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/EncodeActivity.java
	 * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/QRCodeEncoder.java
	 */

	private static final int WHITE = 15132390;
	private static final int BLACK = 0xFF000000;

	private static Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException
	{
		if (contents == null) {
			return null;
		}
		Map<EncodeHintType, Object> hints = null;
		String encoding = guessAppropriateEncoding(contents);
		if (encoding != null) {
			hints = new EnumMap<>(EncodeHintType.class);
			hints.put(EncodeHintType.CHARACTER_SET, encoding);
		}
		MultiFormatWriter writer = new MultiFormatWriter();
		BitMatrix result;
		try {
			result = writer.encode(contents, format, img_width, img_height, hints);
		} catch (IllegalArgumentException iae) {
			// Unsupported format
			return null;
		}
		int width = result.getWidth();
		int height = result.getHeight();
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			int offset = y * width;
			for (int x = 0; x < width; x++) {
				pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
			}
		}

		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	private static String guessAppropriateEncoding(CharSequence contents) {
		// Very crude at the moment
		for (int i = 0; i < contents.length(); i++) {
			if (contents.charAt(i) > 0xFF) {
				return "UTF-8";
			}
		}
		return null;
	}
	public String date_server(int y, int m, int d) {//1396  9 25
		Date date = PersianCalendarUtils.ShamsiToMilady(y, m + 1, d);//Mon Jan 15 12:38:00 GMT+03:30 2018

		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");//01/15/2018
		String formatted = format1.format(date.getTime());
		String[] dateGrg = formatted.split("/");
		int monthS = Integer.valueOf(dateGrg[0]);//1
		long dayS = Long.valueOf(dateGrg[1]);//15
		int yearS = Integer.valueOf(dateGrg[2]);//2018


		return yearS + "-" + "0" + monthS + "-" + dayS;
	}
	/*String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
	int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
	int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true)-2;
	int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true)-1 ;
	PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
	persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

	datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());*/
	@Override
	public void onDateSet(com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {


	}


	@Override
	public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {

		String str_date = year + "/" + (monthOfYear + 1) + "/" + (dayOfMonth-1);//2018-01-16
		DateFormat formatter;
		Date date;
		formatter = new SimpleDateFormat("yyyy/MM/dd");
		try {
			date = (Date) formatter.parse(str_date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			datePickerDialogGregorian2.setMinDate(cal);

			txttavalodm.setText(DateUtil.getLongStringDate(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth, "yyyy/MM/dd", false));
			txttavalodm.setText(str_date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}//end dateset change
}