package com.eligasht.reservation.views.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
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
import com.eligasht.BuildConfig;
import com.eligasht.reservation.base.ServiceType;
import com.eligasht.reservation.base.SingletonAnalysis;
import com.eligasht.reservation.models.PassengerDBModel;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.tools.datetools.SolarCalendar;
import com.eligasht.reservation.tools.persian.Calendar.persian.util.PersianCalendarUtils;
import com.eligasht.reservation.views.activities.GetPassengerActivity;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.helper.Const;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.error.Error;
import com.eligasht.service.model.flight.request.PreFactorDetails.RequestPreFactorDetails;
import com.eligasht.service.model.flight.response.PreFactorDetails.FactorSummary;
import com.eligasht.service.model.flight.response.PreFactorDetails.GetPreFactorDetailsResult;
import com.eligasht.service.model.flight.response.PreFactorDetails.PreFactor;
import com.eligasht.service.model.flight.response.PreFactorDetails.PreFactorFlight;
import com.eligasht.service.model.flight.response.PreFactorDetails.PreFactorHotel;
import com.eligasht.service.model.flight.response.PreFactorDetails.PreFactorService;
import com.eligasht.service.model.flight.response.PreFactorDetails.RequestPassenger;
import com.eligasht.service.model.flight.response.PreFactorDetails.ResponsePreFactorDetails;
import com.eligasht.service.model.hotelflight.purchase.request.PishFactor.RequestPurchaseService;
import com.eligasht.service.model.hotelflight.purchase.request.PurchaseFlightHotel.PartnerList;
import com.eligasht.service.model.hotelflight.purchase.request.PurchaseFlightHotel.PassList;
import com.eligasht.service.model.hotelflight.purchase.request.PurchaseFlightHotel.RequestPurchaseFlightHotel;
import com.eligasht.service.model.hotelflight.purchase.response.PishFactor.PurchaseServiceResult;
import com.eligasht.service.model.hotelflight.purchase.response.PishFactor.ResponsePurchaseService;
import com.eligasht.service.model.hotelflight.purchase.response.PurchaseFlightHotel.PurchaseFlightHotelResult;
import com.eligasht.service.model.hotelflight.purchase.response.PurchaseFlightHotel.ResponsePurchaseFlightHotel;
import com.eligasht.service.model.hotelflight.purchase.response.PurchaseFlightHotel.Service;
import com.eligasht.service.model.hotelflight.purchase.response.PurchaseFlightHotel.TmpReserveResult;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
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
import com.eligasht.reservation.lost.hotel.HotelPreFactorAdapter;
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
import com.eligasht.reservation.views.adapters.GetKhadmatHotelFlightAdapter;
import com.eligasht.reservation.views.components.Header;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassengerFlight;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;

public class PassengerHotelFlightActivity extends BaseActivity implements Header.onSearchTextChangedListener,
        OnClickListener, OnItemSelectedListener, View.OnFocusChangeListener, com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener,
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener, OnServiceStatus<ResponsePreFactorDetails> {

    public static boolean flag;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    Handler handler;
    ProgressDialog progressBar;
    public FancyButton btnBack;
    public ImageView btn_saler, btn_mosaferan, btn_khadamat, btn_pish_factor;
    public TextView txtfamilyP, txtkodemeliP, txtemeliP, txtmobileP, txtMore, tvfactorNumber;
    public Button btnAddsabad, btn_pardakht_factor, txtSaler, txtMasaferan, txtKhadamat, txtPishfactor;
    public EditText txtnamem, txtfamilym;
    public static TextView txttavalodm;
    public EditText txtnumber_passport, txtnameP,txt_NationalCode_m;
    public static TextView txtexp_passport;
    public TextView txtTitle, txtmeliyatm, txtmahale_eghamat, txtTitleCountM;
    public static TextView txtSumKhadamat;
    public LinearLayout linear_saler, linear_mosaferan, linear_list_khadamat, linear_pish_factor, linearMahaleeghamat, linearMeliyat, btn_next_partnerInfo, btn_nextm, btn_taeed_khadamat,llAddPassenger;
    private Handler progressBarHandler = new Handler();
    public ListView list_airport, listKhadamat;
    ArrayList<HashMap<String, String>> mylist = null;
    public static String searchText = "";
    public static long GET_PRICE_KHADAMAT;
    ExpandableRelativeLayout expandableLayout;
    String paymentUrl;
    private boolean FlagTab = false;
    RelativeLayout rlLoading, rlRoot;
    GetKhadmatHotelFlightAdapter mAdapter;
    private EditText searchtxt;
    public TextView txt_shomare_factor, imgCount;
    public TextView tvPrice;
    public ImageView txt_hom, textView4;
    private boolean FlagMosaferan = true;
    private String Gensiyat = "";
    Activity activity;
    public int countB;
    public int countK;
    public int countN;

    public JSONArray jsonObj = null;
    public int sum = 0;
    int counter = 2;
    int room = 0;
    int rooms = 0;
    ScrollView scrolMosafer;
    //int count;
    //change for Prefactor=========================================================================
    LinearLayout llDetailHotel, llDetailPassanger, llDetailService, llDetailFlight;
    List<PurchaseFlightResult> data = null;
    private RadioButton btnzan, btnmard, btnzanS, btnmardS;
    private int defaultRooms = 1;

    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian1;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian2;
    com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        ScrollView scroll_partner = findViewById(R.id.scroll_partner);
        scroll_partner.fullScroll(ScrollView.FOCUS_UP);
        scroll_partner.scrollTo(0, 0);
        scroll_partner.clearFocus();
        Prefs.getString("Rooms", "dd");
        Prefs.putString("IST", "HF");
        PersianCalendar persianCalendarDatePicker = new PersianCalendar();
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth(), persianCalendarDatePicker.getPersianDay());

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
                String monthl = "" + (monthOfYear + 1);
                String dayl = "" + dayOfMonth;
                if (Integer.toString(monthOfYear + 1).length() == 1) {
                    monthl = "0" + (monthOfYear + 1);
                }
                if (Integer.toString(dayOfMonth).length() == 1) {
                    dayl = "0" + dayOfMonth;
                }
                txttavalodm.setText("" + year + "/" + monthl + "/" + dayl);
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

                datePickerDialog.initialize(PassengerHotelFlightActivity.this, Integer.parseInt(yearMF), Integer.parseInt(monthMF), Integer.parseInt(dayMF));
                datePickerDialog.show(getSupportFragmentManager(), "DatepickerdialogRaft");

            }
        });

        datePickerDialogGregorian2 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog(1);
        //	datePickerDialogGregorian2.setMinDate(persianCalendarDatePicker.toGregorianCalendar());
        datePickerDialogGregorian2.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {

                String month = "" + (monthOfYear + 1);
                String day = "" + dayOfMonth;
                if (Integer.toString(monthOfYear + 1).length() == 1) {
                    month = "0" + (monthOfYear + 1);
                }
                if (Integer.toString(dayOfMonth).length() == 1) {
                    day = "0" + dayOfMonth;
                }
                txtexp_passport.setText("" + year + "/" + month + "/" + day);

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

                datePickerDialogGregorian1.initialize(PassengerHotelFlightActivity.this, Integer.parseInt(yearMF1), Integer.parseInt(monthMF1), Integer.parseInt(dayMF1));

                datePickerDialogGregorian1.show(getFragmentManager(), "DatePickerDialogGregorianRaft");
            }
        });

        txtTitleCountM = findViewById(R.id.txtTitleCountM);
        txtTitleCountM.setOnClickListener(PassengerHotelFlightActivity.this);

        String RengAge = txtTitleCountM.getText().toString();

///////////////setmin
        //min max tavalod solar
        if (RengAge.contains(getString(R.string.Child))) {

            String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
            int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
            int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true) - 12;
            int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true) - 1;
            PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
            persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);

            datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());

            String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
            int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
            int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true) - 2;
            int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true) - 1;
            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

            datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());
        } else if (RengAge.contains(getString(R.string.baby))) {

            String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
            int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
            int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true) - 2;
            int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true) - 1;
            PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
            persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);
            datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());

            String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
            int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
            int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true);
            int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true) - 1;
            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

            datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());
        } else {
            String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
            int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
            int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true) - 120;
            int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true) - 1;
            PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
            persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);

            datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());
            String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
            int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
            int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true) - 12;
            int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true) - 1;
            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);
            datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());

        }
//min max passport solar
        PersianCalendar persianCalendar2 = new PersianCalendar();

        persianCalendar2.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth() + 6, persianCalendarDatePicker.getPersianDay());
        datePickerDialogGregorian2.setMinDate(persianCalendar2.toGregorianCalendar());
        persianCalendar2.set(persianCalendarDatePicker.getPersianYear() + 6, persianCalendarDatePicker.getPersianMonth(), persianCalendarDatePicker.getPersianDay());
        datePickerDialogGregorian2.setMaxDate(persianCalendar2.toGregorianCalendar());
        ///////end setMin
        try {
            jsonObj = new JSONArray(Prefs.getString("Rooms", "dd"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonObj == null) {
            defaultRooms = 1;
            rooms = 1;
        } else if (jsonObj.length() == 1) {
            defaultRooms = 1;
            rooms = 1;
        } else {
            defaultRooms = 0;
            rooms = jsonObj.length();
        }
        btnBack = findViewById(R.id.btnBack);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(PassengerHotelFlightActivity.this);

        //kharidar
        btnzanS = findViewById(R.id.zanS);
        btnzanS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (btnzanS.isChecked()) {
                    btnmardS.setChecked(false);
                    System.out.println("zan");
                    Gensiyat = "false";
                }
            }
        });

        btnmardS = findViewById(R.id.mardS);
        btnmardS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (btnmardS.isChecked()) {
                    btnzanS.setChecked(false);
                    System.out.println("mard");
                    Gensiyat = "true";
                }
            }
        });
        ////////mosafer
        btnzan = findViewById(R.id.zan);
        btnzan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (btnzan.isChecked()) {
                    btnmard.setChecked(false);
                    System.out.println("zan");
                    Gensiyat = "false";
                }
            }
        });

        btnmard = findViewById(R.id.mard);
        btnmard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (btnmard.isChecked()) {
                    btnzan.setChecked(false);
                    System.out.println("mard");
                    Gensiyat = "true";
                }
            }
        });

        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);

        txt_hom = findViewById(R.id.txt_hom);
        textView4 = findViewById(R.id.textView4);
        tvfactorNumber = findViewById(R.id.tvfactorNumber);
        imgCount = findViewById(R.id.imgCount);
        if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
            imgCount.setText(getCounter(room) + " " + getString(R.string.room));
        } else {
            imgCount.setText(getString(R.string.room) + " " + getCounter(room));
        }
        expandableLayout = findViewById(R.id.expandableLayout);
        txt_hom.setOnClickListener(PassengerHotelFlightActivity.this);

        txtMore = findViewById(R.id.txtMore);
        txtMore.setOnClickListener(PassengerHotelFlightActivity.this);

        llAddPassenger = (LinearLayout) findViewById(R.id.llAddPassenger);
        llAddPassenger.setOnClickListener(this);
        txtSumKhadamat = findViewById(R.id.txtSumKhadamat);
        tvPrice = findViewById(R.id.tvPrice);
        txtSumKhadamat.setOnClickListener(PassengerHotelFlightActivity.this);
        txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(GET_PRICE_KHADAMAT)));

        txttavalodm = findViewById(R.id.txttavalodm);
        txttavalodm.setOnClickListener(PassengerHotelFlightActivity.this);
        txtnamem = findViewById(R.id.txtnamem);
        txtnamem.setOnFocusChangeListener(this);
        txtnamem.setOnClickListener(PassengerHotelFlightActivity.this);
        txtnamem.addTextChangedListener(new GenericTextWatcher(txtnamem));

        txtfamilym = findViewById(R.id.txtfamilym);
        //lvFactor = (ExpandableLayoutListView) findViewById(R.id.lvFactor);
        txtfamilym.setOnClickListener(PassengerHotelFlightActivity.this);
        txtfamilym.setOnFocusChangeListener(this);
        txtfamilym.addTextChangedListener(new GenericTextWatcher(txtfamilym));

        txtnumber_passport = findViewById(R.id.txtnumber_passport);
        txtnumber_passport.setOnClickListener(PassengerHotelFlightActivity.this);
        txtnumber_passport.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        txtnumber_passport.setOnFocusChangeListener(this);
        txtnumber_passport.addTextChangedListener(new GenericTextWatcher(txtnumber_passport));
        txtexp_passport = findViewById(R.id.txtexp_passport);
        txtexp_passport.setOnClickListener(PassengerHotelFlightActivity.this);

        txt_NationalCode_m= (EditText) findViewById(R.id.txt_NationalCode_m);

        txt_NationalCode_m.setOnClickListener(this);
        txt_NationalCode_m.setImeOptions(EditorInfo.IME_ACTION_DONE);
        txt_NationalCode_m.addTextChangedListener(new GenericTextWatcher(txt_NationalCode_m));
        txt_NationalCode_m.setOnFocusChangeListener(this);

        txtTitle = findViewById(R.id.tvTitle);
        txtTitle.setOnClickListener(PassengerHotelFlightActivity.this);

        btn_next_partnerInfo = findViewById(R.id.btn_next_partnerInfo);
        btn_next_partnerInfo.setOnClickListener(PassengerHotelFlightActivity.this);

        btn_nextm = findViewById(R.id.btn_nextm);
        btn_nextm.setOnClickListener(PassengerHotelFlightActivity.this);

        btn_taeed_khadamat = findViewById(R.id.btn_taeed_khadamat);
        btn_taeed_khadamat.setOnClickListener(PassengerHotelFlightActivity.this);

        btn_pardakht_factor = findViewById(R.id.btn_pardakht_factor);
        btn_pardakht_factor.setOnClickListener(PassengerHotelFlightActivity.this);

        btn_saler = findViewById(R.id.btn_saler);
        btn_mosaferan = findViewById(R.id.btn_mosaferan);
        btn_khadamat = findViewById(R.id.btn_khadamat);
        btn_pish_factor = findViewById(R.id.btn_pish_factor);

        txtSaler = findViewById(R.id.txtSaler);
        txtMasaferan = findViewById(R.id.txtMasaferan);
        txtKhadamat = findViewById(R.id.txtKhadamat);
        txtPishfactor = findViewById(R.id.txtPishfactor);

        btn_saler.setOnClickListener(this);
        btn_mosaferan.setOnClickListener(this);
        btn_khadamat.setOnClickListener(this);
        btn_pish_factor.setOnClickListener(this);
        setAnimation();

        linear_saler = findViewById(R.id.linear_saler);
        linear_mosaferan = findViewById(R.id.linear_mosaferan);
        linear_pish_factor = findViewById(R.id.linear_pish_factor);
        linearMahaleeghamat = findViewById(R.id.linearMahaleeghamat);
        linearMeliyat = findViewById(R.id.linearMeliyat);

        txtnameP = findViewById(R.id.txtnameP);
        //	txtnameP.setHint("لطفا نام را فارسی وارد کنید");
        txtnameP.addTextChangedListener(new GenericTextWatcher(txtnameP));
        txtnameP.setOnFocusChangeListener(this);
        txtfamilyP = (EditText) findViewById(R.id.txtfamilyP);

        //	txtfamilyP.setHint("لطفا نام خانوادگی را فارسی وارد کنید");
        txtfamilyP.setOnFocusChangeListener(this);
        txtfamilyP.addTextChangedListener(new GenericTextWatcher(txtfamilyP));
        txtmobileP = (EditText) findViewById(R.id.txtmobileP);
        txtmobileP.addTextChangedListener(new GenericTextWatcher(txtmobileP));
        txtmobileP.setOnFocusChangeListener(this);
        txtkodemeliP = (EditText) findViewById(R.id.txtkodemeliP);
        txtkodemeliP.setOnFocusChangeListener(this);
        txtkodemeliP.addTextChangedListener(new GenericTextWatcher(txtkodemeliP));
        txtemeliP = (EditText) findViewById(R.id.txtemeliP);
        txtemeliP.setOnFocusChangeListener(this);
        txtemeliP.addTextChangedListener(new GenericTextWatcher(txtemeliP));

        txtmeliyatm = findViewById(R.id.txtmeliyatm);
        txtmeliyatm.setOnClickListener(PassengerHotelFlightActivity.this);
        txtmahale_eghamat = findViewById(R.id.txtmahale_eghamat);
        txtmahale_eghamat.setOnClickListener(PassengerHotelFlightActivity.this);

        txt_shomare_factor = findViewById(R.id.txt_shomare_factor);
        txt_shomare_factor.setOnClickListener(PassengerHotelFlightActivity.this);

        linear_list_khadamat = findViewById(R.id.linear_list_khadamat);

        listKhadamat = findViewById(R.id.listKhadamat);
        llDetailHotel = findViewById(R.id.llDetailHotel);
        llDetailPassanger = findViewById(R.id.llDetailPassanger);
        llDetailService = findViewById(R.id.llDetailService);
        llDetailFlight = findViewById(R.id.llDetailFlight);

        // Spinner element
        Spinner spinner = findViewById(R.id.spinner1);
        Spinner spinnerMosafer = findViewById(R.id.spinnerMosafer);

        // Spinner click listener
        spinner.setOnItemSelectedListener(PassengerHotelFlightActivity.this);
        spinnerMosafer.setOnItemSelectedListener(PassengerHotelFlightActivity.this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add(getString(R.string.Please_choose_a_gender));
        categories.add(getString(R.string.man));
        categories.add(getString(R.string.Female));

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(PassengerHotelFlightActivity.this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinnerMosafer.setAdapter(dataAdapter);

        btn_pardakht_factor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.putString("TypeGetPre", "HF");
                Utility.openUrlCustomTab(PassengerHotelFlightActivity.this, paymentUrl);

            }
        });
        //////////////login user
        try {
            if (WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID() != -1) {
                txtnameP.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF());
                txtfamilyP.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
                txtmobileP.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMobile());
                txtkodemeliP.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserNationalCode());
                txtemeliP.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMail());
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        scrolMosafer = findViewById(R.id.scrolMosafer);
        //////////////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrolMosafer.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    Log.e("testy", scrollY + "");
                    Log.e("testx", scrollX + "");

                }
            });
        }
    }//end oncreate


    @Override
    public void onReady(ResponsePreFactorDetails responsePreFactorDetails) {


        rlLoading.setVisibility(View.GONE);
        Utility.disableEnableControls(true, rlRoot);
        try {
            SingletonAnalysis.getInstance().logPreBooking(ServiceType.HOTELFLIGHT);

            GetPreFactorDetailsResult purchaseServiceReault = responsePreFactorDetails.getGetPreFactorDetailsResult();//.getJSONObject("GetPreFactorDetailsResult");

            PreFactor jArray = purchaseServiceReault.getPreFactor();//("PreFactor");//FactorSummary

            //FactorSummary
            FactorSummary jFact = jArray.getFactorSummary();

            int RqBase_ID = jFact.getRqBaseID();
            //////////////////////////////
            long totalprice = jFact.getTotalPrice();
            paymentUrl = jFact.getOnlinePaymentURL();
            tvPrice.setText(String.valueOf(NumberFormat.getInstance().format(totalprice)) + " " + getString(R.string.Rial));
            if (paymentUrl==null||paymentUrl.equals(""))
                btn_pardakht_factor.setVisibility(View.INVISIBLE);

//for hotel==========================================================================================
            final RecyclerView recyclerViewHotel = findViewById(R.id.recyclerView);
            recyclerViewHotel.addItemDecoration(new DividerItemDecoration(PassengerHotelFlightActivity.this, 1));
            recyclerViewHotel.setLayoutManager(new LinearLayoutManager(PassengerHotelFlightActivity.this));
            ArrayList<HotelPreFactorModel> hotelPreFactorModels = new ArrayList<>();

            List<PreFactorHotel> jArray2 = jArray.getPreFactorHotels();

            for (int i = 0; i < jArray2.size(); i++) {
                if (Locale.getDefault().getLanguage().equals("fa")) {
                    hotelPreFactorModels.add(new HotelPreFactorModel(jArray2.get(i).getHotelNameE(),
                            Utility.dateShow(jArray2.get(i).getHotelChekin())
                            , Utility.dateShow(jArray2.get(i).getHotelChekout()),
                            jArray2.get(i).getAdlCount() + "",
                            jArray2.get(i).getChdCount() + "", jArray2.get(i).getRoomTitleFa(), jArray2.get(i).getCityEn()));
                }else{
                    hotelPreFactorModels.add(new HotelPreFactorModel(jArray2.get(i).getHotelNameE(),
                            Utility.dateShow(jArray2.get(i).getHotelChekin())
                            , Utility.dateShow(jArray2.get(i).getHotelChekout()),
                            jArray2.get(i).getAdlCount() + "",
                            jArray2.get(i).getChdCount() + "", jArray2.get(i).getRoomTitleEn(), jArray2.get(i).getCityEn()));
                }
            }
            if (!hotelPreFactorModels.isEmpty()) {
                recyclerViewHotel.setAdapter(new HotelPreFactorAdapter(hotelPreFactorModels));
                llDetailHotel.setVisibility(View.VISIBLE);
            }
            //for passenger======================================================================================

            final RecyclerView recyclerViewPassenger = findViewById(R.id.recyclerViewPassenger);
            recyclerViewPassenger.addItemDecoration(new DividerItemDecoration(PassengerHotelFlightActivity.this, 1));
            recyclerViewPassenger.setLayoutManager(new LinearLayoutManager(PassengerHotelFlightActivity.this));
            ArrayList<PassengerPreFactorModel> passengerPreFactorModels = new ArrayList<>();

            List<RequestPassenger> jArray3 = jArray.getRequestPassenger();

            for (int i = 0; i < jArray3.size(); i++) {
                passengerPreFactorModels.add(new PassengerPreFactorModel(jArray3.get(i).getGender() + "", jArray3.get(i).getNationality(),
                        jArray3.get(i).getRqPassengerBirthdate(), jArray3.get(i).getRqPassengerPassNo(),
                        jArray3.get(i).getRqPassengerName(), jArray3.get(i).getRqPassengerNationalCode() + ""));

            }
            if (!passengerPreFactorModels.isEmpty()) {
                llDetailPassanger.setVisibility(View.VISIBLE);
                recyclerViewPassenger.setAdapter(new PassangerPreFactorAdapter(passengerPreFactorModels));
            }
            //for Services=============================================================================
            final RecyclerView recyclerViewService = findViewById(R.id.recyclerViewService);
            recyclerViewService.addItemDecoration(new DividerItemDecoration(PassengerHotelFlightActivity.this, 1));
            recyclerViewService.setLayoutManager(new LinearLayoutManager(PassengerHotelFlightActivity.this));
            ArrayList<ServicePreFactorModel> servicePreFactorModels = new ArrayList<>();
            List<PreFactorService> jArray4 = jArray.getPreFactorServices();

            for (int i = 0; i < jArray4.size(); i++) {
                if (Locale.getDefault().getLanguage().equals("fa")) {
                    servicePreFactorModels.add(new ServicePreFactorModel(jArray4.get(i).getServiceNameEn(),
                            jArray4.get(i).getServicePrice() + "", jArray4.get(i).getServiceType(),
                            jArray4.get(i).getCityFa(), jArray4.get(i).getServiceNameFa(), jArray4.get(i).getCountryFa()));
                }else{
                    servicePreFactorModels.add(new ServicePreFactorModel(jArray4.get(i).getServiceNameEn(),
                            jArray4.get(i).getServicePrice() + "", jArray4.get(i).getServiceType(),
                            jArray4.get(i).getCityEn(), jArray4.get(i).getServiceNameEn(), jArray4.get(i).getCountryEn()));
                }
            }
            if (!servicePreFactorModels.isEmpty()) {
                llDetailService.setVisibility(View.VISIBLE);
                recyclerViewService.setAdapter(new ServicePreFactorAdapter(servicePreFactorModels));

            }
            //for flight==================================================================================
            final RecyclerView recyclerViewFlight = findViewById(R.id.recyclerViewFlight);
            recyclerViewFlight.addItemDecoration(new DividerItemDecoration(PassengerHotelFlightActivity.this, 1));
            recyclerViewFlight.setLayoutManager(new LinearLayoutManager(PassengerHotelFlightActivity.this));
            ArrayList<FlightPreFactorModel> flightPreFactorModels = new ArrayList<>();
            List<PreFactorFlight> jArray5 = jArray.getPreFactorFlights();

            for (int i = 0; i < jArray5.size(); i++) {
                if (Locale.getDefault().getLanguage().equals("fa")) {
                    flightPreFactorModels.add(new FlightPreFactorModel(jArray5.get(i).getAirlineNameFa(),
                            jArray5.get(i).getDepAirPortFa(),
                            jArray5.get(i).getArrAirPortFa(),
                            Utility.dateShow(jArray5.get(i).getFltDate()),
                            jArray5.get(i).getFltTime(),
                            jArray5.get(i).getFltCheckinTime(),

                            jArray5.get(i).getFltNumber(),
                            jArray5.get(i).getAirlineNameFa(),
                            jArray5.get(i).getDepartureCityFa(), jArray5.get(i).getAirlineCode(), jArray5.get(i).getArrivalCityFa()));
                }else{
                    flightPreFactorModels.add(new FlightPreFactorModel(jArray5.get(i).getAirlineNameEn(),
                            jArray5.get(i).getDepAirPortEn(),
                            jArray5.get(i).getArrAirPortEn(),
                            Utility.dateShow(jArray5.get(i).getFltDate()),
                            jArray5.get(i).getFltTime(),
                            jArray5.get(i).getFltCheckinTime(),

                            jArray5.get(i).getFltNumber(),
                            jArray5.get(i).getAirlineNameEn(),
                            jArray5.get(i).getDepartureCityEn(), jArray5.get(i).getAirlineCode(), jArray5.get(i).getArrivalCityEn()));
                }
            }
            if (!flightPreFactorModels.isEmpty()) {
                llDetailFlight.setVisibility(View.VISIBLE);
                recyclerViewFlight.setAdapter(new FlightPreFactorAdapter(flightPreFactorModels));
            }
            setAnimation();
            Log.e("responsecrDetails:", new Gson().toJson(responsePreFactorDetails).toString());

        } catch (Exception e) {
            //Toast.makeText(PassengerHotelFlightActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(PassengerHotelFlightActivity.this,true,true);
            AlertDialogPassenger.setText(getString(R.string.Error_getting_information_from_eli), getString(R.string.massege));
        }
    }

    @Override
    public void onError(String message) {
        Log.e("responsectorDetails:", message);
        rlLoading.setVisibility(View.GONE);
        Utility.disableEnableControls(true, rlRoot);
        AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(PassengerHotelFlightActivity.this,true,true);
        AlertDialogPassenger.setText(message, getString(R.string.massege));
    }

    private void sendRequestPreFactorDetailsAvil() {

        try {
            rlLoading.setVisibility(View.VISIBLE);
            Utility.disableEnableControls(false, rlRoot);

            RequestPreFactorDetails requestPreFactorDetails = new RequestPreFactorDetails();
            com.eligasht.service.model.flight.request.PreFactorDetails.Request request = new com.eligasht.service.model.flight.request.PreFactorDetails.Request();

            com.eligasht.service.model.flight.request.PreFactorDetails.Identity identity = new com.eligasht.service.model.flight.request.PreFactorDetails.Identity();

            request.setIdentity(identity);

            request.setCulture(getString(R.string.culture));

            request.setInvoiceNo(tvfactorNumber.getText().toString());//perches service
            request.setType("HF");//perches service


            requestPreFactorDetails.setRequest(request);

            SingletonService.getInstance().getFlight().flightPreFactorDetailAvail(PassengerHotelFlightActivity.this, requestPreFactorDetails);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    private void sendRequestPurchaseFlightHotel() {
        rlLoading.setVisibility(View.VISIBLE);
        Utility.disableEnableControls(false, rlRoot);

        RequestPurchaseFlightHotel hotelFlightPurchaseRequest = new RequestPurchaseFlightHotel();
        com.eligasht.service.model.hotelflight.purchase.request.PurchaseFlightHotel.Request request = new com.eligasht.service.model.hotelflight.purchase.request.PurchaseFlightHotel.Request();

        com.eligasht.service.model.hotelflight.purchase.request.PurchaseFlightHotel.Identity identity = new com.eligasht.service.model.hotelflight.purchase.request.PurchaseFlightHotel.Identity();

        try {
            String GUID = "";
            String ResultUniqId = "";
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                GUID = extras.getString("Flight_GUID");
                ResultUniqId = SearchFlightActivity.globalResultUniqID;
            }

            //mosaferan
            PassengerMosaferItems_Table items_Table = new PassengerMosaferItems_Table(PassengerHotelFlightActivity.this);
            CursorManager cursorM = items_Table.getAllMosafer();
            if (cursorM != null) {
                List<PassList> passLists = new ArrayList<>();
                for (int i = 0; i < cursorM.getCount(); i++) {
                    PassList passList1 = new PassList();
                    cursorM.moveToPosition(i);

                    passList1.setGender(cursorM.getBoolean(PassengerMosaferItems_Table.Columns.Gender.value()));
                    passList1.setNationality(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));
                    passList1.setNationalityID((cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value())).toUpperCase());

                    passList1.setRqPassengerAddress(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Address.value()));
                    passList1.setRqPassengerBirthdate(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
                    passList1.setRqPassengerEmail(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Email.value()));

                    passList1.setRqPassengerFirstNameEn(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                    passList1.setRqPassengerFirstNameFa(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameFa.value()));
                    passList1.setRqPassengerLastNameEn(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));

                    passList1.setRqPassengerLastNameFa(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameFa.value()));
                    passList1.setRqPassengerMobile(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Mobile.value()));
                    passList1.setRqPassengerNationalCode(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_NationalCode.value()));

                    passList1.setRqPassengerPassExpDate(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));
                    passList1.setRqPassengerPassNo(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));
                    passList1.setRqPassengerTel(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Tel.value()));

                    passLists.add(i, passList1);

                }
                request.setPassList(passLists);

            }

            ////kharidar
            PassengerPartnerInfo_Table partnerInfo_Table = new PassengerPartnerInfo_Table(PassengerHotelFlightActivity.this);
            CursorManager cursorManager = partnerInfo_Table.getPartner();
            cursorManager.moveToPosition(0);
            PartnerList partnerList = new PartnerList();
            partnerList.setRqPartnerAddress(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Address.value()));
            partnerList.setRqPartnerEmail(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Email.value()));
            partnerList.setRqPartnerFirstNameFa(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_FirstNameFa.value()));
            partnerList.setRqPartnerGender(cursorManager.getBoolean(PassengerPartnerInfo_Table.Columns.RqPartner_Gender.value()));
            partnerList.setRqPartnerLastNameFa(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_LastNameFa.value()));
            partnerList.setRqPartnerMobile(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Mobile.value()));
            partnerList.setRqPartnerNationalCode(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_NationalCode.value()));
            partnerList.setRqPartnerTel(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Tel.value()));
            partnerList.setAgcUser_ID( cursorManager.getString(PassengerPartnerInfo_Table.Columns.AgcUser_ID.value()));

            partnerList.setWebUserID(Prefs.getString("userId", "-1"));//Purchase

            request.setPartnerList(partnerList);

            request.setCulture(getString(R.string.culture));
            request.setType("HF");

            request.setHotelOfferId(getIntent().getExtras().getString("HotelOfferId"));
            request.setFlightGuID(getIntent().getExtras().get("FlightGuID") + "");
            request.setFlightOfferId(getIntent().getExtras().get("flightId") + "");

            request.setCheckin(getIntent().getExtras().getString("CheckIn"));
            request.setCheckout(getIntent().getExtras().getString("CheckOut"));

            identity.setPassword("123qwe!@#QWE");
            identity.setTermianlId("Mobile");
            identity.setUserName("EligashtMlb");

            request.setIdentity(identity);

        } catch (Exception e) {
            e.printStackTrace();
        }

        hotelFlightPurchaseRequest.setRequest(request);
        Log.e("sendReq ", new Gson().toJson(hotelFlightPurchaseRequest).toString());

        SingletonService.getInstance().getHotelService().getPurchase(new OnServiceStatus<ResponsePurchaseFlightHotel>() {
            @Override
            public void onReady(ResponsePurchaseFlightHotel hotelFlightPurchaseResponse) {
                FlagMosaferan = false;
                rlLoading.setVisibility(View.GONE);
                Utility.disableEnableControls(true, rlRoot);
                data = new ArrayList<PurchaseFlightResult>();

                try {
                    Log.e("Response", new Gson().toJson(hotelFlightPurchaseResponse).toString());
                    PurchaseFlightHotelResult purchaseServiceReault = hotelFlightPurchaseResponse.getPurchaseFlightHotelResult();//Error
                    List<Service> jArray = purchaseServiceReault.getServices();
                    TmpReserveResult jsonResult = purchaseServiceReault.getTmpReserveResult();
                    Prefs.putString("BookingCode_NumFactor", jsonResult.getBookingCode() + "");

                    for (int i = 0; i < jArray.size(); i++) {
                        Service json_data = jArray.get(i);
                        com.eligasht.service.model.hotelflight.purchase.response.PurchaseFlightHotel.ExcursionDta excursionDta = json_data.getExcursionDta();
                        PurchaseFlightResult fishData = new PurchaseFlightResult();
                        fishData.setCityEn(json_data.getCityEn());
                        fishData.setCityFa(json_data.getCityFa());
                        fishData.setCurrency_ID(json_data.getCurrencyID() + "");

                        fishData.setHasFlight(json_data.getHasFlight() + "");
                        fishData.setHasHotel(json_data.getHasHotel() + "");
                        fishData.setLoadDB(json_data.getLoadDB() + "");

                        fishData.setServiceAdlPrice(json_data.getServiceAdlPrice() + "");
                        fishData.setServiceChdPrice(json_data.getServiceChdPrice() + "");
                        fishData.setServiceDescEn(json_data.getServiceDescEn() + "");

                        fishData.setServiceDescFa(json_data.getServiceDescFa() + "");
                        fishData.setServiceID(json_data.getServiceID() + "");
                        fishData.setServiceImgURL(json_data.getServiceImgURL() + "");

                        fishData.setServiceInfPrice(json_data.getServiceInfPrice() + "");
                        fishData.setServiceNameEn(json_data.getServiceNameEn() + "");
                        fishData.setServiceNameFa(json_data.getServiceNameFa() + "");

                        fishData.setServiceTypeEn(json_data.getServiceTypeEn() + "");
                        fishData.setServiceTypeFa(json_data.getServiceTypeFa());
                        fishData.setServiceTypeID(json_data.getServiceTypeID() + "");

                        fishData.setServiceTotalPrice(Long.parseLong(json_data.getServiceTotalPrice()));
                        fishData.setSelectID(json_data.getSelectID());

                        fishData.setBookingCode(jsonResult.getBookingCode() + "");

                        fishData.setExcursionData(new ExcursionDta(excursionDta.getArrialAirportCode(),
                                excursionDta.getArrialAirportName(),
                                excursionDta.getArrivalFltDate()
                                , excursionDta.getArrivalFltNo(),
                                excursionDta.getArrivalFltTime(),
                                excursionDta.getCityID() + "", excursionDta.getDepartureFltDate(),
                                excursionDta.getDepartureFltNo(), excursionDta.getDepartureFltTime(),
                                excursionDta.getHotelID() + "", excursionDta.getHotelNameEn(), excursionDta.getPassengerList()));
                        data.add(fishData);
                    }

                    linear_saler.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.GONE);
                    linear_pish_factor.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.VISIBLE);
                    FlagTab = true;

                    ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
                    txtTitle.setText(getString(R.string.Add_to_cart_services));

                    mAdapter = new GetKhadmatHotelFlightAdapter(PassengerHotelFlightActivity.this, data, PassengerHotelFlightActivity.this, 0);

                    if (BuildConfig.DEBUG && Const.TEST)
                        Collections.reverse(data);
                    mAdapter.setData(data);
                    listKhadamat.setAdapter(mAdapter);
                    setAnimation();
                } catch (Exception e) {
                    AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(PassengerHotelFlightActivity.this,true,true);
                    AlertDialogPassenger.setText(getString(R.string.Error_getting_information_from_eli), getString(R.string.massege));
                }

            }

            @Override
            public void onError(String message) {
                rlLoading.setVisibility(View.GONE);
                Utility.disableEnableControls(true, rlRoot);
            }
        }, hotelFlightPurchaseRequest);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Prefs.putBoolean("IsDemostic", true);
        Prefs.putLong("Tprice",0);
        Prefs.putString("Flag_First_Computing", "F");

    }

    @Override
    public boolean needTerminate() {
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        Fragment fragment2;

        switch (v.getId()) {
            case R.id.llAddPassenger:

                Intent intent = new Intent(this, GetPassengerActivity.class);
                startActivityForResult(intent, 555);


                break;
            case R.id.txtMore:
                linearMahaleeghamat.setVisibility(View.VISIBLE);
                linearMeliyat.setVisibility(View.VISIBLE);
                break;
            case R.id.btnBack:
                if (linear_pish_factor.getVisibility() == View.VISIBLE) {
                    linear_pish_factor.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.VISIBLE);

                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
                    txtTitle.setText(getString(R.string.Add_to_cart_services));
                } else if (linear_list_khadamat.getVisibility() == View.VISIBLE) {
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.VISIBLE);

                    txtTitle.setText(getString(R.string.passneger_info));
                    ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
                    ////////////////////bazyabi atelaate akharin mosafer
                    PassengerMosaferItems_Table items_Table = new PassengerMosaferItems_Table(PassengerHotelFlightActivity.this);
                    CursorManager cursorM = items_Table.getMosaferById(counter - 1);
                    if (cursorM != null) {
                        for (int i = 0; i < cursorM.getCount(); i++) {

                            txtnamem.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                            txtfamilym.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));
                            txtnumber_passport.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));

                            txttavalodm.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
                            txtexp_passport.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));
                            txtmahale_eghamat.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));

                            txtmeliyatm.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));
                            txtTitleCountM.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()));
                            imgCount.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Otagh.value()));
                        }
                    }
                    counter--;

                } else if (linear_mosaferan.getVisibility() == View.VISIBLE) {

                    linear_mosaferan.setVisibility(View.GONE);
                    linear_saler.setVisibility(View.VISIBLE);

                    txtTitle.setText(getString(R.string.Buyer_Specifications));
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
                } else if (linear_saler.getVisibility() == View.VISIBLE) {
                    Prefs.putBoolean("BACK_HOME", true);
                    Intent intentSendFinish = new Intent("sendFinish");
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intentSendFinish);
                }
                break;
            case R.id.btn_next_partnerInfo:
                if (FlagTab) {
                    linear_saler.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.VISIBLE);
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_pish_factor.setVisibility(View.GONE);

                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
                    ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
                    txtTitle.setText(getString(R.string.Traveler_info));
                    setAnimation();

                } else {
                    try {
                        //jadvale mosafer khali beshe

                        PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerHotelFlightActivity.this);

                        db.dropTable();
                        ////////////////////////Validate
                        String RqPartner_Address = "No.7,23rd St.,Khaled Eslamboli St.,Tehran,Iran";
                        String RqPartner_Email = txtemeliP.getText().toString();
                        String RqPartner_FirstNameFa = txtnameP.getText().toString();
                        String RqPartner_Gender = Gensiyat;
                        String RqPartner_LastNameFa = txtfamilyP.getText().toString();
                        String RqPartner_Mobile = txtmobileP.getText().toString();
                        String RqPartner_NationalCode = txtkodemeliP.getText().toString();
                        String RqPartner_Tel = "21587632";
                        String AgcUser_ID = "-1";

					/*String RqPartner_Address= "No.7,23rd St.,Khaled Eslamboli St.,Tehran,Iran";
					String RqPartner_Email= "mohebbi@eligasht.com";
					String RqPartner_FirstNameFa= "مریم";
					String RqPartner_Gender= "Female";
					String RqPartner_LastNameFa= "محبی";
					String RqPartner_Mobile= "0235884";
					String RqPartner_NationalCode= "0062532148";
					String RqPartner_Tel= "21587632";*/
                        String errorMessage = "";
                        String flagMosafer = "T";
                        ///Validate
                        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        if (RqPartner_Email.matches(emailPattern) && RqPartner_Email.trim().length() > 0) {
                            ((EditText) findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer = flagMosafer + "T";
                        } else {
                            flagMosafer = flagMosafer + "F";
                            errorMessage = errorMessage + "\n" + "* " + getString(R.string.Email_format_is_correct);
                        }

                        if (RqPartner_FirstNameFa != null) {
                            if (Locale.getDefault().getLanguage().equals("en")) {
                                if (RqPartner_FirstNameFa.length() > 2 && ((RqPartner_FirstNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) || !(RqPartner_FirstNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")))) {
                                    ((EditText) findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));
                                    flagMosafer = flagMosafer + "T";
                                } else {
                                    flagMosafer = flagMosafer + "F";
                                    errorMessage = errorMessage + "\n" + "* " + getString(R.string.Name_of_at_least_2_characters_and_maximum_100_characters);
                                }
                            } else {
                                if (RqPartner_FirstNameFa.length() > 2 && !(RqPartner_FirstNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))) {
                                    ((EditText) findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));
                                    flagMosafer = flagMosafer + "T";
                                } else {
                                    flagMosafer = flagMosafer + "F";
                                    errorMessage = errorMessage + "\n" + "* " + getString(R.string.Name_of_at_least_2_characters_and_maximum_100_characters);
                                }
                            }
                        }
                        if (RqPartner_LastNameFa != null) {
                            if (Locale.getDefault().getLanguage().equals("en")) {
                                if (RqPartner_LastNameFa.length() > 2 && ((RqPartner_LastNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) || !(RqPartner_LastNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")))) {
                                    ((EditText) findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));
                                    flagMosafer = flagMosafer + "T";
                                } else {
                                    flagMosafer = flagMosafer + "F";
                                    errorMessage = errorMessage + "\n" + "* " + getString(R.string.The_last_name_is_at_least_2_characters_and_a_maximum_of_100_characters);
                                }
                            } else {
                                if (RqPartner_LastNameFa.length() > 2 && !(RqPartner_LastNameFa.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))) {
                                    ((EditText) findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));
                                    flagMosafer = flagMosafer + "T";
                                } else {
                                    flagMosafer = flagMosafer + "F";
                                    errorMessage = errorMessage + "\n" + "* " + getString(R.string.The_last_name_is_at_least_2_characters_and_a_maximum_of_100_characters);
                                }
                            }
                        }
                        if (RqPartner_Mobile != null && RqPartner_Mobile.length() == 11 && RqPartner_Mobile.matches("[0-9]+")) {
                            ((EditText) findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer = flagMosafer + "T";
                        } else {
                            flagMosafer = flagMosafer + "F";
                            errorMessage = errorMessage + "\n" + "* " + getString(R.string.Enter_the_correct_mobile_format);
                        }

                        if (RqPartner_NationalCode != null)
                            if (RqPartner_NationalCode.length() == 10 && RqPartner_NationalCode.matches("[0-9]+")) {
                                ((EditText) findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#4d4d4d"));
                                flagMosafer = flagMosafer + "T";
                            } else {
                                flagMosafer = flagMosafer + "F";
                                errorMessage = errorMessage + "\n" + "* " + getString(R.string.The_national_code_is_not_correct);
                            }
                        if (Gensiyat.contains("true") || Gensiyat.contains("false")) {
                            flagMosafer = flagMosafer + "T";
                        } else {
                            flagMosafer = flagMosafer + "F";
                            errorMessage = errorMessage + "\n" + "* " + getString(R.string.Please_choose_a_gender);
                        }
                        //////////////////////////End Validate
                        if (flagMosafer.contains("F")) {

                            try {
                                AlertDialogPassenger alertDialogPassenger = new AlertDialogPassenger(PassengerHotelFlightActivity.this,false,false);
                                alertDialogPassenger.setText("" + "  " + errorMessage, getString(R.string.EditInput));
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        } else {
                            //insert partner
                            PassengerPartnerInfo_Table partnerInfo_Table = new PassengerPartnerInfo_Table(PassengerHotelFlightActivity.this);

                            partnerInfo_Table.dropTable();
                            partnerInfo_Table.openDB();

                            partnerInfo_Table.insertData(RqPartner_Address, RqPartner_Email, RqPartner_FirstNameFa, RqPartner_Gender, RqPartner_LastNameFa, RqPartner_Mobile, RqPartner_NationalCode, RqPartner_Tel,AgcUser_ID);

                            partnerInfo_Table.closeDB();
                            ////////////////
                            linear_saler.setVisibility(View.GONE);
                            linear_pish_factor.setVisibility(View.GONE);
                            linear_mosaferan.setVisibility(View.VISIBLE);
                            txtTitle.setText(getString(R.string.Traveler_info));

                            Gensiyat = "";
                            ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);
                            ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                            setAnimation();
                        }

                    } catch (Exception e) {
                        System.out.println("Exception ::" + e);
                    }
                }
                break;

            case R.id.txttavalodm:

                String RengAge = txtTitleCountM.getText().toString();

///////////////setmin
                if (RengAge.contains(getString(R.string._childP))) {
                    Log.e("RengAge:", RengAge);
                    String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
                    int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
                    int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true) - 12;
                    int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true) - 1;
                    PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
                    persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);

                    datePickerDialog.setMinDate(persianCalendarDatePicker1);
                    datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());

                    String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
                    int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
                    int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true) - 2;
                    int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true) - 1;
                    PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
                    persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

                    datePickerDialog.setYearRange(currentYear, currentYear2);
                    datePickerDialog.setMaxDate(persianCalendarDatePicker2);
                    datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());

                } else if (RengAge.contains(getString(R.string._InfantP))) {
                    Log.e("RengAge:", RengAge);
                    String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
                    int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
                    int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true) - 2;
                    int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true) - 1;
                    PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
                    persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);

                    datePickerDialog.setMinDate(persianCalendarDatePicker1);
                    datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());

                    String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
                    int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
                    int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true);
                    int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true) - 1;
                    PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
                    persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

                    datePickerDialog.setYearRange(currentYear, currentYear2);
                    datePickerDialog.setMaxDate(persianCalendarDatePicker2);
                    datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());
                } else {
                    Log.e("RengAge:", RengAge);
                    String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
                    int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
                    int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true) - 120;
                    int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true) - 1;
                    PersianCalendar persianCalendarDatePicker1 = new PersianCalendar();
                    persianCalendarDatePicker1.set(currentYear, currentMonth, currentDay);

                    datePickerDialog.setMinDate(persianCalendarDatePicker1);
                    datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker1.toGregorianCalendar());

                    String currentDateTime2 = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
                    int currentDay2 = DateUtil.getDayOfMonth(currentDateTime2, "yyyy-MM-dd", true);
                    int currentYear2 = DateUtil.getYear(currentDateTime2, "yyyy-MM-dd", true) - 12;
                    int currentMonth2 = DateUtil.getMonth(currentDateTime2, "yyyy-MM-dd", true) - 1;
                    PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
                    persianCalendarDatePicker2.set(currentYear2, currentMonth2, currentDay2);

                    datePickerDialog.setYearRange(currentYear, currentYear2);
                    datePickerDialog.setMaxDate(persianCalendarDatePicker2);
                    datePickerDialogGregorian1.setMaxDate(persianCalendarDatePicker2.toGregorianCalendar());

                }
                ///////end setMin

                if (!datePickerDialogGregorian1.isAdded())
                    datePickerDialogGregorian1.show(getFragmentManager(), "DatePickerDialogGregorianRaft");

                flag = true;
                break;
            case R.id.txtexp_passport:
                if (!datePickerDialogGregorian2.isAdded())
                    datePickerDialogGregorian2.show(getFragmentManager(), "DatePickerDialogGregorianRaft");

                flag = false;
                break;
            case R.id.btn_nextm:
                LinearLayout mainLayout;
                mainLayout = findViewById(R.id.linear_list_khadamat);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);
                ///////////////
                txtexp_passport.setScroller(new Scroller(this));

                scrolMosafer.fullScroll(ScrollView.FOCUS_UP);
                if (FlagMosaferan) {
                    String Gender= Gensiyat;
                    String Nationality=txtmahale_eghamat.getText().toString();// "ir";
                    String Nationality_ID= txtmeliyatm.getText().toString().toLowerCase();
                    String RqPassenger_Address= null;
                    String RqPassenger_Birthdate= txttavalodm.getText().toString();
                    String RqPassenger_Email= null;
                    String RqPassenger_FirstNameEn= txtnamem.getText().toString();
                    String RqPassenger_FirstNameFa=null;
                    String RqPassenger_LastNameEn=txtfamilym.getText().toString();
                    String RqPassenger_LastNameFa= null;
                    String RqPassenger_Mobile= null;
                    String RqPassenger_NationalCode= txt_NationalCode_m.getText().toString();//codemeli
                    String RqPassenger_PassExpDate= txtexp_passport.getText().toString();
                    String RqPassenger_PassNo=txtnumber_passport.getText().toString();
                    String RqPassenger_Tel= null;

                    String flagMosafer = "T";
                    String errorMessagePartner = "";
                    ///Validate

                    if (txt_NationalCode_m.getText().toString() != null && txt_NationalCode_m.getText().toString().length() == 10) {
                        ((EditText) findViewById(R.id.txt_NationalCode_m)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer = flagMosafer + "T";
                    } else {
                        flagMosafer = flagMosafer + "F";
                        errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.The_national_code_is_not_correct);
                    }
                    if (RqPassenger_PassNo.trim().length() > 6 && RqPassenger_PassNo.trim().length() < 10 && (RqPassenger_PassNo.trim().substring(0, 1).matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) && RqPassenger_PassNo.trim().substring(1, RqPassenger_PassNo.length() - 1).matches("[0-9]+")) {
                        ((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer = flagMosafer + "T";
                    } else {
                        flagMosafer = flagMosafer + "F";
                        errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.Enter_the_passport_number_correctly);
                    }
                    if (Nationality != null && Nationality.length() > 1) {
                        ((TextView) findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer = flagMosafer + "T";
                    } else {
                        flagMosafer = flagMosafer + "F";
                        errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.Enter_the_place_of_residence);
                    }
                    if (Nationality_ID != null && Nationality_ID.length() > 1) {
                        ((TextView) findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer = flagMosafer + "T";
                    } else {
                        //((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#ff3300"));
                        flagMosafer = flagMosafer + "F";
                        errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.Enter_your_nationality);
                    }
                    if (RqPassenger_Birthdate != null && RqPassenger_Birthdate.length() > 4) {
                        ((TextView) findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer = flagMosafer + "T";
                    } else {
                        flagMosafer = flagMosafer + "F";
                        errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.Enter_the_date_of_birth);
                    }
                    if (txtTitleCountM.getText().toString().contains(getString(R.string.Child))) {

                    } else if (txtTitleCountM.getText().toString().contains(getString(R.string.baby))) {

                    }
                    if (RqPassenger_FirstNameEn != null)
                        if (RqPassenger_FirstNameEn.length() > 1 && RqPassenger_FirstNameEn.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) {
                            ((EditText) findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer = flagMosafer + "T";
                        } else {
                            flagMosafer = flagMosafer + "F";
                            errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.Name_of_at_least_2_characters_and_maximum_100_characters);
                        }
                    if (RqPassenger_LastNameEn != null)
                        if (RqPassenger_LastNameEn.length() > 1 && RqPassenger_LastNameEn.toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) {
                            ((EditText) findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer = flagMosafer + "T";
                        } else {
                            flagMosafer = flagMosafer + "F";
                            errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.The_last_name_is_at_least_2_characters_and_a_maximum_of_100_characters);
                        }
                    if (RqPassenger_PassExpDate != null && RqPassenger_PassExpDate.length() > 4) {
                        ((TextView) findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer = flagMosafer + "T";
                    } else {
                        flagMosafer = flagMosafer + "F";
                        errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.Enter_the_passport_expiration_date);
                    }
                    if (Gensiyat.contains("true") || Gensiyat.contains("false")) {
                        flagMosafer = flagMosafer + "T";
                    } else {
                        flagMosafer = flagMosafer + "F";
                        errorMessagePartner = errorMessagePartner + "\n" + getString(R.string.Enter_your_gender);
                    }
                    ///endValidate

                    if (flagMosafer.contains("F")) {
                        try {
                            AlertDialogPassenger AlertDialogPassengerFlight = new AlertDialogPassenger(PassengerHotelFlightActivity.this,false,false);
                            AlertDialogPassengerFlight.setText("" + "  " + errorMessagePartner, getString(R.string.EditInput));
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    } else {
                        PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerHotelFlightActivity.this);

                        db.openDB();

                        //faghat yek otagh

                        if (defaultRooms == 1) {
                            if (sum == 0) {

                                if (rooms == 1) {
                                    try {
                                        if (jsonObj == null) {
                                            countB = 1;
                                            countK = 0;
                                            countN = 0;
                                        } else {
                                            countK = jsonObj.getJSONObject(room).getInt("CountK");
                                            countB = jsonObj.getJSONObject(room).getInt("CountB");
                                            countN = jsonObj.getJSONObject(room).getInt("CountN");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();

                                    }
                                    sum = countB + countK + countN;
                                    rooms = rooms - 1;
                                    room = room + 1;
                                }

                                System.out.println("@ucountK:" + countK + "countB:" + countB + "countN:" + countN);

                            }

                            if (sum > 0) {
                                System.out.println("gender:" + Gender);
                                if (counter - 1 == 1) {
                                    db.insertData(counter - 1, getString(R.string.First_passenger_information), getString(R.string.room) + getCounter(room), Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);
                                } else {
                                    db.insertData(counter - 1, txtTitleCountM.getText().toString(), imgCount.getText().toString(), Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);
                                }
                                System.out.println("InsertMosafer:" + (counter - 1) + " " + txtTitleCountM.getText().toString() + " " + RqPassenger_FirstNameEn);
                                if (countB >= 1) {
                                    System.out.println("countB:" + countB);
                                    countB--;
                                } else if (countK >= 1) {
                                    System.out.println("countK:" + countK);
                                    countK--;
                                } else if (countN >= 1) {
                                    System.out.println("countN:" + countN);
                                    countN--;
                                }
                                if (countB != 0) {
                                    txtTitleCountM.setText(getString(R.string.Passenger_information) + getCounter(counter) + getString(R.string.adult_));
                                    if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
                                        imgCount.setText(getCounter(room) + " " + getString(R.string.room));
                                    } else {
                                        imgCount.setText(getString(R.string.room) + " " + getCounter(room));
                                    }
                                } else if (countK != 0) {
                                    txtTitleCountM.setText(getString(R.string.Passenger_information) + getCounter(counter) + getString(R.string.child_));
                                    if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
                                        imgCount.setText(getCounter(room) + " " + getString(R.string.room));
                                    } else {
                                        imgCount.setText(getString(R.string.room) + " " + getCounter(room));
                                    }
                                } else if (countN != 0) {
                                    txtTitleCountM.setText(getString(R.string.Passenger_information) + getCounter(counter) + getString(R.string.baby));
                                    if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
                                        imgCount.setText(getCounter(room) + " " + getString(R.string.room));
                                    } else {
                                        imgCount.setText(getString(R.string.room) + " " + getCounter(room));
                                    }
                                }

                                System.out.println("counterMosafer:" + getCounter(counter) + counter);

                                counter++;
                                sum--;
                                ///pak kardan data haye mosafere ghabli:
                                if (sum > 0) {

                                    txttavalodm.setText("");
                                    txtnamem.setText("");
                                    txtfamilym.setText("");
                                    txtexp_passport.setText("");
                                    txtnumber_passport.setText("");
                                    txt_NationalCode_m .setText("");
                                }
                                System.out.println("insert:" + "sum:" + sum);
                            }
                        } else if (rooms >= 0) {

                            if (sum == 0) {
                                try {

                                    countK = jsonObj.getJSONObject(room).getInt("CountK");
                                    countB = jsonObj.getJSONObject(room).getInt("CountB");
                                    countN = jsonObj.getJSONObject(room).getInt("CountN");
                                    sum = countB + countK + countN;
                                    rooms = rooms - 1;
                                    room = room + 1;

                                    System.out.println("@ucountK:" + countK + "countB:" + countB + "countN:" + countN);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            if (sum > 0) {
                                System.out.println("gender:" + Gender);
                                if (counter - 1 == 1) {
                                    db.insertData(counter - 1, getString(R.string.First_passenger_information), getString(R.string.room) + getCounter(room), Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);

                                } else {
                                    db.insertData(counter - 1, txtTitleCountM.getText().toString(), imgCount.getText().toString(), Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);
                                    System.out.println("InsertMosafercou:" + (counter - 1) + " " + txtTitleCountM.getText().toString() + " " + RqPassenger_FirstNameEn + " " + imgCount.getText().toString());
                                }
                                System.out.println("InsertMosafer:" + (counter - 1) + " " + txtTitleCountM.getText().toString() + " " + RqPassenger_FirstNameEn);
                                if (countB >= 1) {
                                    System.out.println("countB:" + countB);
                                    txtTitleCountM.setText(getString(R.string.info_passenger) + getCounter(counter - 1) + getString(R.string.adult_));
                                    if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
                                        imgCount.setText(getCounter(room) + " " + getString(R.string.room));
                                    } else {
                                        imgCount.setText(getString(R.string.room) + " " + getCounter(room));

                                    }
                                    countB--;
                                } else if (countK >= 1) {
                                    System.out.println("countK:" + countK);
                                    txtTitleCountM.setText(getString(R.string.info_passenger) + getCounter(counter - 1) + getString(R.string.child_));
                                    if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
                                        imgCount.setText(getCounter(room) + " " + getString(R.string.room));
                                    } else {
                                        imgCount.setText(getString(R.string.room) + " " + getCounter(room));

                                    }
                                    countK--;
                                } else if (countN >= 1) {
                                    System.out.println("countN:" + countN);
                                    txtTitleCountM.setText(getString(R.string.info_passenger) + getCounter(counter - 1) + getString(R.string.baby));
                                    if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
                                        imgCount.setText(getCounter(room) + " " + getString(R.string.room));
                                    } else {
                                        imgCount.setText(getString(R.string.room) + " " + getCounter(room));

                                    }
                                    countN--;
                                }
                                if (countB != 0) {

                                    txtTitleCountM.setText(getString(R.string.info_passenger) + getCounter(counter) + getString(R.string.adult_));
                                    if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
                                        imgCount.setText(getCounter(room) + " " + getString(R.string.room));
                                    } else {
                                        imgCount.setText(getString(R.string.room) + " " + getCounter(room));

                                    }
                                } else if (countK != 0) {

                                    txtTitleCountM.setText(getString(R.string.info_passenger) + getCounter(counter) + getString(R.string.child_));
                                    if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
                                        imgCount.setText(getCounter(room) + " " + getString(R.string.room));
                                    } else {
                                        imgCount.setText(getString(R.string.room) + " " + getCounter(room));

                                    }
                                } else if (countN != 0) {

                                    txtTitleCountM.setText(getString(R.string.info_passenger) + getCounter(counter) + getString(R.string.baby));
                                    if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("tr")) {
                                        imgCount.setText(getCounter(room) + " " + getString(R.string.room));
                                    } else {
                                        imgCount.setText(getString(R.string.room) + " " + getCounter(room));

                                    }
                                } else if (countB + countK + countN == 0) {

                                    if (rooms - 1 >= 0) {

                                        if (Prefs.getString("lang", "fa").equals("fa")) {
                                            txtTitleCountM.setText(getString(R.string.info_passenger) + getCounter(counter) + getString(R.string.adult_));
                                            imgCount.setText(getString(R.string.room) + " " + getCounter(room + 1));
                                        } else {
                                            txtTitleCountM.setText(getCounter(counter) + getString(R.string.info_passenger) + getString(R.string.adult_));
                                            imgCount.setText(getCounter(room + 1) + " " + getString(R.string.room));
                                        }
                                        txttavalodm.setText("");
                                        txtnamem.setText("");
                                        txtfamilym.setText("");
                                        txtexp_passport.setText("");
                                        txtnumber_passport.setText("");
                                    }
                                }
                                System.out.println("counterMosafer:" + getCounter(counter) + counter);


                                counter++;
                                sum--;
                                ///pak kardan data haye mosafere ghabli:
                                if (sum > 0) {
                                    //counter--;

                                    txttavalodm.setText("");
                                    txtnamem.setText("");
                                    txtfamilym.setText("");
                                    txtexp_passport.setText("");
                                    txtnumber_passport.setText("");
                                    Gensiyat = "";
                                    btnzan.setChecked(false);
                                    btnmard.setChecked(false);
                                    txtnamem.setFocusable(true);
                                }
                                System.out.println("insert:" + "sum:" + sum);
                            }
                            db.closeDB();


                            linear_mosaferan.clearFocus();
                        }

                        //call api saler
                        if (sum == 0 && rooms == 0) {
                            System.out.println("APICALL:" + "sum:" + sum);
                            System.out.println("insert:");
                            //  new AsyncFetch().execute();
                            sendRequestPurchaseFlightHotel();

                        }

                    }


                }
                if (FlagTab) {
                    linear_saler.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.VISIBLE);
                    linear_pish_factor.setVisibility(View.GONE);

                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
                    ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
                    txtTitle.setText(getString(R.string.Add_to_cart_services));
                    setAnimation();
                }
                break;

            case R.id.btn_taeed_khadamat:


                //call api pishFactor
                sendRequestPurchaseService();

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
                if (FlagTab) {
                    linear_saler.setVisibility(View.VISIBLE);
                    linear_mosaferan.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_pish_factor.setVisibility(View.GONE);

                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
                    ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#aaaaaa"));
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#aaaaaa"));
                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#aaaaaa"));
                    txtTitle.setText(getString(R.string.Buyer_Specifications));
                    setAnimation();
                }


                break;
            case R.id.btn_mosaferan:
                if (FlagTab) {
                    linear_saler.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.VISIBLE);
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_pish_factor.setVisibility(View.GONE);

                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
                    ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#aaaaaa"));
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#aaaaaa"));
                    txtTitle.setText(getString(R.string.Traveler_info));
                    setAnimation();
                }
                break;
            case R.id.btn_khadamat:
                if (FlagTab) {
                    linear_saler.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.VISIBLE);
                    linear_pish_factor.setVisibility(View.GONE);


                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
                    ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#aaaaaa"));
                    txtTitle.setText(getString(R.string.Add_to_cart_services));
                    setAnimation();
                }
                break;
            case R.id.btn_pish_factor:
                if (FlagTab) {
                    linear_saler.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_pish_factor.setVisibility(View.VISIBLE);

                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_on);
                    ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);
                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
                    txtTitle.setText(getString(R.string.Approval_and_payment_of_pre_invoice));
                    setAnimation();
                }
                break;
            case R.id.txt_hom:
                Prefs.putBoolean("BACK_HOME", true);
                Intent intentsendFinish = new Intent("sendFinish");

                LocalBroadcastManager.getInstance(this).sendBroadcast(intentsendFinish);
                break;
        }

    }


    private void sendRequestPurchaseService() {
        rlLoading.setVisibility(View.VISIBLE);
        Utility.disableEnableControls(false, rlRoot);

        RequestPurchaseService hotelFlightPurchaseRequest = new RequestPurchaseService();
        com.eligasht.service.model.hotelflight.purchase.request.PishFactor.Request request = new com.eligasht.service.model.hotelflight.purchase.request.PishFactor.Request();

        com.eligasht.service.model.hotelflight.purchase.request.PishFactor.Identity identity = new com.eligasht.service.model.hotelflight.purchase.request.PishFactor.Identity();


        try {
            request.setCulture(getString(R.string.culture));

            request.setRqBaseID(Prefs.getString("BookingCode_NumFactor", ""));
            request.setServiceStr(Prefs.getString("Select_ID_khadamat", ""));
            Prefs.putString("Select_ID_khadamat", "");//khali kardan field
            request.setExc("");
            request.setInsCoverageXML("");

            request.setInsPrcieXML("");
            request.setInsPlanCode(-1);

            identity.setPassword("123qwe!@#QWE");
            identity.setTermianlId("Mobile");
            identity.setUserName("EligashtMlb");
            request.setIdentity(identity);


        } catch (Exception e) {
            e.printStackTrace();
        }

        hotelFlightPurchaseRequest.setRequest(request);

        SingletonService.getInstance().getHotelService().getPishFactor(new OnServiceStatus<ResponsePurchaseService>() {
            @Override
            public void onReady(ResponsePurchaseService responsePurchaseService) {
                Log.e("onReady: ", new Gson().toJson(responsePurchaseService).toString());
                rlLoading.setVisibility(View.GONE);
                Utility.disableEnableControls(true, rlRoot);
                try {

                    PurchaseServiceResult purchaseServiceReault = responsePurchaseService.getPurchaseServiceResult();
                    int successResult = purchaseServiceReault.getSuccessResult();
                    if (successResult == 0) {
                        if (purchaseServiceReault.getErrors() != null) {
                            //get Error
                            List<Error> getError = purchaseServiceReault.getErrors();

                            String message = getError.get(0).getMessage();
                            AlertDialogPassengerFlight AlertDialogPassengerFlight = new AlertDialogPassengerFlight(PassengerHotelFlightActivity.this);
                            AlertDialogPassengerFlight.setText(message, getString(R.string.massege));
                        }

                    }

                    if (successResult > 1) {
                        txt_shomare_factor.setText(purchaseServiceReault.getSuccessResult() + "");
                        tvfactorNumber.setText(purchaseServiceReault.getSuccessResult() + "");

                        textView4.setImageBitmap(getBitmap(purchaseServiceReault.getSuccessResult() + "", 128, getResources().getInteger(R.integer._300), getResources().getInteger(R.integer._150)));

                    } else {
                        txt_shomare_factor.setText(getString(R.string.An_error_has_occurred) + "");
                    }

                    // Setup and Handover data to recyclerview
                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_on);
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
                    txtTitle.setText(getString(R.string.Approval_and_payment_of_pre_invoice));

                    linear_saler.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_pish_factor.setVisibility(View.VISIBLE);
                    FlagTab = true;

                    sendRequestPreFactorDetailsAvil();

                } catch (Exception e) {
                    try {

                        AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(PassengerHotelFlightActivity.this,true,true);
                        AlertDialogPassenger.setText(getString(R.string.Error_getting_information_from_eli), getString(R.string.massege));
                    } catch (Exception ee) {
                        ee.getMessage();
                    }
                }
            }

            @Override
            public void onError(String message) {
                Log.e("onError: ", message);
                rlLoading.setVisibility(View.GONE);
                Utility.disableEnableControls(true, rlRoot);
            }
        }, hotelFlightPurchaseRequest);
    }


    @Override
    public void onResume() {
        super.onResume();
        long gheymatKh = Prefs.getLong("Tprice", 0);
        mAdapter = new GetKhadmatHotelFlightAdapter(PassengerHotelFlightActivity.this, data, PassengerHotelFlightActivity.this, gheymatKh);
        mAdapter.setData(data);
        listKhadamat.setAdapter(mAdapter);
        final ScrollView scroll_partner = findViewById(R.id.scroll_partner);
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
        String s = "";
        switch (i) {
            case 0:
                s = getString(R.string.First);
                break;
            case 1:
                s = getString(R.string.First);
                break;
            case 2:
                s = getString(R.string.Second);
                break;
            case 3:
                s = getString(R.string.Third);
                break;
            case 4:
                s = getString(R.string.Fourth);
                break;
            case 5:
                s = getString(R.string.Fifth);
                break;
            case 6:
                s = getString(R.string.Sixth);
                break;
            case 7:
                s = getString(R.string.Seventh);
                break;
            case 8:
                s = getString(R.string.Eighth);
                break;
            case 9:
                s = getString(R.string.ninth);
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
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            String countryCode = data.getStringExtra(CountrycodeActivity.RESULT_CONTRYCODE);//RESULT_CONTRYNAME
            String countryName = data.getStringExtra(CountrycodeActivity.RESULT_CONTRYNAME);

            String nationalityCode = data.getStringExtra(NationalitycodeActivity.RESULT_NATIONALITYCODE);
            String nationalityName = data.getStringExtra(NationalitycodeActivity.RESULT_NATIONALITYNAME);

            if (countryCode != null)
                txtmahale_eghamat.setText(countryCode + "");//txtmahale_eghamat.setText(countryCode+" "+countryName);
            if (nationalityCode != null)
                txtmeliyatm.setText(nationalityCode + "");//txtmeliyatm.setText(nationalityCode+" "+nationalityName);
        }
        if(requestCode == 555 && resultCode == Activity.RESULT_OK){

            List<PassengerDBModel> passengerDBModels=PassengerDBModel.listAll(PassengerDBModel.class);
            for (PassengerDBModel model:passengerDBModels) {
                if (model.getId()==data.getLongExtra("Id",0)){
                    txtnamem.setText(model.getRqPassenger_FirstNameEn());
                    txtfamilym.setText(model.getRqPassenger_LastNameEn());
                    txtnumber_passport.setText(model.getRqPassenger_PassNo());
                    txt_NationalCode_m.setText(model.getRqPassenger_NationalCode());
                    txttavalodm.setText(model.getRqPassenger_Birthdate());
                    txtexp_passport.setText(model.getRqPassenger_PassExpDate());
                    Log.e("gender", model.getGender());
                    if(Boolean.valueOf(model.getGender())){
                        btnzan.setChecked(true);
                    }else{
                        btnmard.setChecked(true);

                    }



                }

            }

        }
    }


    @Override
    public void onBackPressed() {

        if (linear_pish_factor.getVisibility() == View.VISIBLE) {
            linear_pish_factor.setVisibility(View.GONE);
            linear_list_khadamat.setVisibility(View.VISIBLE);

            ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
            ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
            txtTitle.setText(getString(R.string.Add_to_cart_services));
        } else if (linear_list_khadamat.getVisibility() == View.VISIBLE) {
            linear_list_khadamat.setVisibility(View.GONE);
            linear_mosaferan.setVisibility(View.VISIBLE);


            txtTitle.setText(getString(R.string.passneger_info));
            ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
            ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
            ////////////////////bazyabi atelaate akharin mosafer
            PassengerMosaferItems_Table items_Table = new PassengerMosaferItems_Table(PassengerHotelFlightActivity.this);
            CursorManager cursorM = items_Table.getMosaferById(counter - 1);
            if (cursorM != null) {
                for (int i = 0; i < cursorM.getCount(); i++) {

                    txtnamem.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                    txtfamilym.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));
                    txtnumber_passport.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));

                    txttavalodm.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
                    txtexp_passport.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));

                    txtmahale_eghamat.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));

                    txtmeliyatm.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));
                    txtTitleCountM.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()));
                    imgCount.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Otagh.value()));
                    //txtTitleCountM.setText(getString(R.string.info_passenger) + counter);
                    System.out.println("InsertMosaferGet:" + cursorM.getString(PassengerMosaferItems_Table.Columns.ID.value()) + " " + cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()) + " " + cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                }
            }
            counter--;

            // imgCount.setText(counter + "");
            ///////////////////
        } else if (linear_mosaferan.getVisibility() == View.VISIBLE) {

            linear_mosaferan.setVisibility(View.GONE);
            linear_saler.setVisibility(View.VISIBLE);


            txtTitle.setText(getString(R.string.Buyer_Specifications));
            ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
            ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
            //  }
        } else if (linear_saler.getVisibility() == View.VISIBLE) {

            Prefs.putBoolean("BACK_HOME", true);
            Intent intent = new Intent("sendFinish");

            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }

    @Override
    public void searchTextChanged(String searchText) {


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

        String item = parent.getItemAtPosition(position).toString();
        if (item.contains(getString(R.string.Female))) {
            Gensiyat = "false";
        } else if (item.contains(getString(R.string.man))) {
            Gensiyat = "true";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public static void updateTotalInfos(long serviceTotalPrice) {
        // TODO Auto-generated method stub

        txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(serviceTotalPrice)) + "");

    }

    private class GenericTextWatcher implements TextWatcher {

        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {

        }
    }


    public static Bitmap getBitmap(String barcode, int barcodeType, int width, int height) {
        Bitmap barcodeBitmap = null;
        BarcodeFormat barcodeFormat = convertToZXingFormat(barcodeType);
        try {
            barcodeBitmap = encodeAsBitmap(barcode, barcodeFormat, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return barcodeBitmap;
    }

    private static BarcodeFormat convertToZXingFormat(int format) {
        switch (format) {
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

    private static Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
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


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {


//مسافر
            case R.id.txtmahale_eghamat:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txtmahale_eghamat.getText().toString() != null && txtmahale_eghamat.getText().toString().length() > 1) {
                        ((TextView) findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#4d4d4d"));
                    } else {
                        txtmahale_eghamat.setError(getString(R.string.Please_enter_your_residence));
                    }
                }
                break;
            case R.id.txtmeliyatm:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txtmeliyatm.getText().toString() != null && txtmeliyatm.getText().toString().length() > 1) {
                        ((TextView) findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#4d4d4d"));
                        //flagMosafer=flagMosafer+"T";
                    } else {
                        //((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#ff3300"));
                        txtmeliyatm.setError(getString(R.string.Please_enter_your_nationality));
                    }
                }
                break;
            case R.id.txttavalodm:
                if (hasFocus) {//txtTitleCountM
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txttavalodm.getText().toString() != null && txttavalodm.getText().toString().length() > 4) {
                        ((TextView) findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#4d4d4d"));
                    } else {

                        txttavalodm.setError(getString(R.string.Enter_the_date_of_birth));
                    }
                }
                break;

            case R.id.txtnamem:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txtnamem.getText().toString() != null)
                        if (txtnamem.getText().toString().length() > 1 && txtnamem.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) {
                            ((EditText) findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#4d4d4d"));
                        } else {
                            txtnamem.setError(getString(R.string.Please_enter_a_name_in_English));
                        }
                }
                break;
            case R.id.txtfamilym:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txtfamilym.getText().toString() != null)
                        if (txtfamilym.getText().toString().length() > 1 && txtfamilym.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) {
                            ((EditText) findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#4d4d4d"));

                        } else {

                            txtfamilym.setError(getString(R.string.Please_enter_a_surname_in_English));
                        }
                }
                break;
            case R.id.txtexp_passport:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txtexp_passport.getText().toString() != null && txtexp_passport.getText().toString().length() > 4) {
                        ((TextView) findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {

                        txtexp_passport.setError(getString(R.string.Please_enter_your_passport_expiration_date));
                    }
                }
                break;
            case R.id.txtnumber_passport:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txtnumber_passport.getText().toString().trim().length() > 6 && txtnumber_passport.getText().toString().trim().length() < 10 && (txtnumber_passport.getText().toString().trim().substring(0, 1).matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) && txtnumber_passport.getText().toString().trim().substring(1, txtnumber_passport.getText().toString().length() - 1).matches("[0-9]+")) {
                        ((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {
                      //  txtnumber_passport.setError(getString(R.string.Please_enter_the_passport_number_correctly));
                    }
                    if (txtmeliyatm.getText().toString() != null && txtmeliyatm.getText().toString().length() > 4) {
                    } else {
                       // txtnumber_passport.setError(getString(R.string.Please_enter_the_passport_number));
                    }
                }
                break;

            //خریدار
            case R.id.txtemeliP:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (txtemeliP.getText().toString().matches(emailPattern) && txtemeliP.getText().toString().length() > 0) {
                        ((EditText) findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {
                        txtemeliP.setError(getString(R.string.Please_enter_the_email));
                    }
                }
                break;
            case R.id.txtnameP:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txtnameP.getText().toString() != null) {
                        if (Locale.getDefault().getLanguage().equals("en")) {
                            if (txtnameP.getText().toString().length() > 2 && ((txtnameP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) || !(txtnameP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")))) {
                                ((EditText) findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));

                            } else {

                                txtnameP.setError(getString(R.string.Please_enter_a_name_in_Persian));
                            }
                        } else {
                            if (txtnameP.getText().toString().length() > 2 && !(txtnameP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))) {
                                ((EditText) findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));

                            } else {

                                txtnameP.setError(getString(R.string.Please_enter_a_name_in_Persian));
                            }
                        }

                    }
                }
                break;
            case R.id.txtfamilyP:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txtfamilyP.getText().toString() != null) {
                        if (Locale.getDefault().getLanguage().equals("en")) {
                            if (txtfamilyP.getText().toString().length() > 2 && ((txtfamilyP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) || !(txtfamilyP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")))) {
                                ((EditText) findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));

                            } else {
                                txtfamilyP.setError(getString(R.string.Please_enter_last_name_in_Persian));
                            }
                        } else {
                            if (txtfamilyP.getText().toString().length() > 2 && !(txtfamilyP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))) {
                                ((EditText) findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));

                            } else {
                                txtfamilyP.setError(getString(R.string.Please_enter_last_name_in_Persian));
                            }
                        }
                    }
                }
                break;

            case R.id.txtmobileP:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txtmobileP.getText().toString() != null && txtmobileP.getText().toString().length() == 11 && txtmobileP.getText().toString().matches("[0-9]+")) {
                        ((EditText) findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {

                        txtmobileP.setError(getString(R.string.Please_enter_the_mobile_number));
                    }
                }
                break;
            case R.id.txtkodemeliP:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");

                    if (txtkodemeliP.getText().toString() != null)
                        if (txtkodemeliP.getText().toString().length() == 10 && txtkodemeliP.getText().toString().matches("[0-9]+")) {
                            ((EditText) findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#4d4d4d"));

                        } else {

                            txtkodemeliP.setError(getString(R.string.Please_enter_the_national_code));
                        }
                }
                break;
        }

    }

    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {


        String str_date = year + "/" + (monthOfYear + 1) + "/" + (dayOfMonth - 1);//2018-01-16
        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        try {
            date = formatter.parse(str_date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            datePickerDialogGregorian2.setMinDate(cal);


            txttavalodm.setText(DateUtil.getLongStringDate(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth, "yyyy/MM/dd", false));
            txttavalodm.setText(str_date);


        } catch (ParseException e) {
            e.printStackTrace();
        }


    }//end dateset change

    @Override
    public void onDateSet(com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {


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
}