
package com.eligasht.reservation.views.activities.train;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.base.ServiceType;
import com.eligasht.reservation.base.SingletonAnalysis;
import com.eligasht.reservation.lost.flight.FlightPreFactorAdapter;
import com.eligasht.reservation.lost.hotel.HotelPreFactorAdapter;
import com.eligasht.reservation.lost.passenger.PassangerPreFactorAdapter;
import com.eligasht.reservation.lost.service.ServicePreFactorAdapter;
import com.eligasht.reservation.models.FlightPreFactorModel;
import com.eligasht.reservation.models.HotelPreFactorModel;
import com.eligasht.reservation.models.PassengerDBModel;
import com.eligasht.reservation.models.PassengerPreFactorModel;
import com.eligasht.reservation.models.ServicePreFactorModel;
import com.eligasht.reservation.models.train.PassengerServiceModel;
import com.eligasht.reservation.models.train.ServicePreFactorTrainModel;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.tools.datetools.SolarCalendar;
import com.eligasht.reservation.tools.db.local.PassengerMosaferItems_Table;
import com.eligasht.reservation.tools.db.local.PassengerPartnerInfo_Table;
import com.eligasht.reservation.tools.db.main.CursorManager;
import com.eligasht.reservation.tools.persian.Calendar.persian.util.PersianCalendarUtils;
import com.eligasht.reservation.views.activities.GetPassengerActivity;
import com.eligasht.reservation.views.activities.newFlight.PurchaseFlightServices;
import com.eligasht.reservation.views.adapters.GetHotelKhadmatAdapter;
import com.eligasht.reservation.views.components.Header;
import com.eligasht.reservation.views.ui.CountrycodeActivity;
import com.eligasht.reservation.views.ui.NationalitycodeActivity;
import com.eligasht.reservation.views.ui.SearchFlightActivity;
import com.eligasht.reservation.views.ui.SplashActivity;
import com.eligasht.reservation.views.ui.dialog.PromotionCodeDialog;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassengerFlight;
import com.eligasht.reservation.views.ui.dialog.train.DialogPassCount;
import com.eligasht.reservation.views.ui.dialog.train.DialogPassServiceTrain;
import com.eligasht.service.generator.SingletonService;
import com.eligasht.service.listener.OnServiceStatus;
import com.eligasht.service.model.error.Error;

import com.eligasht.service.model.newModel.flight.prefactor.request.RequestGetPreFactor;
import com.eligasht.service.model.newModel.flight.prefactor.response.Flight;
import com.eligasht.service.model.newModel.flight.prefactor.response.Hotel;
import com.eligasht.service.model.newModel.flight.prefactor.response.ResponseGetPreFactor;
import com.eligasht.service.model.newModel.flight.prefactor.response.Summary;

import com.eligasht.service.model.newModel.insurance.response.purchase.ResponseInsurancePurchase;
import com.eligasht.service.model.newModel.promotion.request.RequestPromotionCode;
import com.eligasht.service.model.newModel.promotion.response.ResponsePromotionCode;
import com.eligasht.service.model.newModel.startup.response.Branch;
import com.eligasht.service.model.newModel.train.domesticSearch.response.PassengerService;
import com.eligasht.service.model.newModel.train.domesticTrainGetPrice.request.RequestDomesticTrainGetPrice;
import com.eligasht.service.model.newModel.train.domesticTrainGetPrice.response.ResponseDomesticTrainGetPrice;
import com.eligasht.service.model.newModel.train.domesticTrainGetPrice.response.SegmentList;
import com.eligasht.service.model.newModel.train.domesticTrainGetPrice.response.Train;
import com.eligasht.service.model.newModel.train.purchase.request.Customer;
import com.eligasht.service.model.newModel.train.purchase.request.Passenger;
import com.eligasht.service.model.newModel.train.purchase.request.RequestTrainPurchase;
import com.eligasht.service.model.newModel.train.purchase.response.ResponseTrainPurchase;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import org.json.JSONArray;
import org.json.JSONException;

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

import mehdi.sakout.fancybuttons.FancyButton;

//this class just me send request of insurance and set sum of passenger but there is problem of ui
public class PassengerTrainActivity extends BaseActivity implements Header.onSearchTextChangedListener, OnClickListener, OnItemSelectedListener, View.OnFocusChangeListener, com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener,
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener,OnServiceStatus<ResponseTrainPurchase> {


    public static boolean flag;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    public FancyButton btnBack;
    public ImageView txt_hom;
    public TextView txtfamilyP;
    public TextView txtkodemeliP;
    public TextView txtemeliP;
    public TextView txtmobileP;
    public TextView txtMore;
    public static TextView tvfactorNumber;
    public TextView txtTelP;
    public TextView txtaddressP;
    public static ImageView btn_saler;
    public static ImageView btn_mosaferan;
    public ImageView btn_khadamat;
    public static ImageView btn_pish_factor;
    public Button btnAddsabad;
    public static Button btn_pardakht_factor;
    public static Button txtSaler;
    public static Button txtMasaferan;
    public static Button txtKhadamat;
    public static Button txtPishfactor;
    public EditText txtnamem, txtfamilym;
    public static TextView txttavalodm;
    public EditText txtnameP,txt_NationalCode_m;

    public static TextView txt_typt_service_bargasht,txt_typt_service_raft;
    public TextView txtTitle, txtmeliyatm, txtmahale_eghamat, txtTitleCountM;
    public static TextView txtSumKhadamat;
    public LinearLayout linear_typt_service_raft,linear_typt_service_bargasht,btn_taeed_khadamat, btn_nextm, linear_saler, linear_mosaferan, linear_list_khadamat, linear_pish_factor, linearMahaleeghamat, linearMeliyat, btn_next_partnerInfo;
    private Handler progressBarHandler = new Handler();
    public ListView list_airport;
    public ListView listKhadamat;
    private ArrayList<HashMap<String, String>> mylist = null;
    public static String searchText = "";
    public static long GET_PRICE_KHADAMAT;
    private ScrollView myScrollView;
    private ExpandableRelativeLayout expandableLayout;
    public TextView imgCount;
    private static String paymentUrl;
    public List<PurchaseFlightServices> data;
    private GetHotelKhadmatAdapter mAdapter;
    public static TextView txt_shomare_factor;
    public static TextView tvPrice;
    public ImageView textView4;
    private String Gensiyat = "";
    public int countB;
    public int countK;
    public int countN;

    private static LinearLayout llDetailHotel;
    private static LinearLayout llDetailPassanger;
    private static LinearLayout llDetailService;
    private static LinearLayout llDetailFlight;
    private LinearLayout llAddPassenger;
    private boolean FlagTab = false;
    private boolean FlagMosaferan = true;
    List<String> alRoom;
    private RadioButton btnzan, btnmard, btnzanS, btnmardS;
    static RelativeLayout rlLoading;
    static RelativeLayout rlRoot;
    private static TextView btnPromotionCode;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian1;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian2;
    com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog;
    public JSONArray jsonObj = null;
    public JSONArray jsonObjBDate = null;
    public int sum = 0;
    int counter = 2;
    int rooms;
    List<PassengerServiceModel> passSeviceListRaft;
    List<PassengerServiceModel> passSeviceListBargasht;
    public static void updateFactorByPromotion(String promotionCode, Activity activity, Context context) {
        rlLoading.setVisibility(View.VISIBLE);
        Utility.disableEnableControls(false,rlRoot);
        try {


            RequestPromotionCode requestPromotionCode = new RequestPromotionCode();

            requestPromotionCode.setPrefactorNum(tvfactorNumber.getText().toString());
            requestPromotionCode.setDiscountCode(promotionCode);
            Log.d("RequestPromotionCode: ",new Gson().toJson(requestPromotionCode));

            SingletonService.getInstance().getFlight().newGetPromotionCodeAvail(new OnServiceStatus<ResponsePromotionCode>() {
                @Override
                public void onReady(ResponsePromotionCode responsePromotionCode) {
                    Log.e("ResponsePromotionCode:", new Gson().toJson(responsePromotionCode) );
                    rlLoading.setVisibility(View.GONE);
                    Utility.disableEnableControls(true,rlRoot);
                    if(responsePromotionCode.getDisounctAmount()>0)
                        btnPromotionCode.setVisibility(View.GONE);
                    //retry call preFactor
                    sendRequestGetPreFactorDetails(context,activity);
                }

                @Override
                public void onError(String message) {
                    System.out.println("PurchesFlightError: "+message);
                    rlLoading.setVisibility(View.GONE);
                    Utility.disableEnableControls(true,rlRoot);
                    AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(activity,true,true);
                    AlertDialogPassenger.setText(R.string.Error_getting_information_from_eli+"",context.getString(R.string.massege));

                }
            },requestPromotionCode);

        }catch (Exception e){
            e.getMessage();
        }

    }
    public static void updateServicePass() {
        txt_typt_service_raft.setText(Prefs.getString("Value_Train_True", ""));
        txt_typt_service_bargasht.setText(Prefs.getString("Value_Train_False", ""));


    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_train);

        initViews();
        initCalender();
        setupGenderSpinner();
        RequestDomesticTrainPrice();

    }//endOnCreate

    private void initCalender() {
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

                datePickerDialog.initialize(PassengerTrainActivity.this, Integer.parseInt(yearMF), Integer.parseInt(monthMF), Integer.parseInt(dayMF));
                datePickerDialog.show(getSupportFragmentManager(), "DatepickerdialogRaft");

            }
        });

        datePickerDialogGregorian2 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog(1);
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
              //  txt_typt_service_bargasht.setText("" + year + "/" + month + "/" + day);

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

                datePickerDialogGregorian1.initialize(PassengerTrainActivity.this, Integer.parseInt(yearMF1), Integer.parseInt(monthMF1), Integer.parseInt(dayMF1));

                datePickerDialogGregorian1.show(getFragmentManager(), "DatePickerDialogGregorianRaft");
            }
        });
       String RengAge = txtTitleCountM.getText().toString();

///////////////setmin
        //min max tavalod solar
        if (RengAge.contains(getString(R.string._childP))) {

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
        } else if (RengAge.contains(getString(R.string._InfantP))) {

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
        /////////////////////////Get date list
        try {//[{"BirthDate":"1951-03-22","PassNo":1},{"BirthDate":"1951-04-10","PassNo":2}]
            jsonObjBDate = new JSONArray(Prefs.getString("BirthDateListInsuranc", ""));
            Log.e("testroom2", jsonObjBDate.toString());//[{"BirthDate":"1951-03-22","PassNo":1},{"BirthDate":"1951-04-10","PassNo":2}]
            int countDate = jsonObjBDate.length();
            String birthDate = jsonObjBDate.getJSONObject(0).getString("BirthDate");
            txttavalodm.setText(birthDate + "");
            System.out.println("BIRTHDATE:" + birthDate);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //////////////////////////////
    }//endInitCalender

    private void setupGenderSpinner() {

        Spinner spinner = findViewById(R.id.spinner1);
        Spinner spinnerMosafer = findViewById(R.id.spinnerMosafer);

        spinner.setOnItemSelectedListener(this);
        spinnerMosafer.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add(getString(R.string.Please_choose_a_gender));
        categories.add(getString(R.string.man));
        categories.add(getString(R.string.Female));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinnerMosafer.setAdapter(dataAdapter);

    }
    private void ActiveOperation() {

        List<Branch>  branchesDef=new ArrayList<>();
        List<Integer> activeOperation=new ArrayList<>();
        try {



            branchesDef= SplashActivity.branchesDef;
            if (branchesDef != null){
                if (branchesDef.get(0).getIsDefault()){
                    if(Prefs.getBoolean("isChangeUrl", false)){
                        // branchesDef.clear();
                        branchesDef=new ArrayList<>();

                        branchesDef=SplashActivity.branches;

                        for (int i = 0; i < branchesDef.size(); i++) {
                            if(Prefs.getString("BASEURL", "").equals(branchesDef.get(i).getUrl())){

                                activeOperation=branchesDef.get(i).getActiveOperations();


                            }
                        }

                    }else {//default branch

                        activeOperation=branchesDef.get(0).getActiveOperations();

                    }
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < activeOperation.size() ; i++) {
            if (activeOperation.get(i)==62) {
                btn_pardakht_factor.setVisibility(View.VISIBLE);
                btn_pardakht_factor.setEnabled(true);
            }


        }

    }
    private void initViews() {
        btnPromotionCode = (TextView) findViewById(R.id.btnPromotionCode);
        btnPromotionCode.setOnClickListener(this);
        llAddPassenger = (LinearLayout) findViewById(R.id.llAddPassenger);
        llAddPassenger.setOnClickListener(this);
        ScrollView scroll_partner = findViewById(R.id.scroll_partner);
        scroll_partner.fullScroll(ScrollView.FOCUS_UP);
        scroll_partner.scrollTo(0, 0);
        scroll_partner.clearFocus();
        txtTitleCountM = findViewById(R.id.txtTitleCountM);
        txtTitleCountM.setOnClickListener(this);
        btnBack = findViewById(R.id.btnBack);
        txt_hom = findViewById(R.id.txt_hom);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setVisibility(View.VISIBLE);

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
        textView4 = findViewById(R.id.textView4);
        tvfactorNumber = findViewById(R.id.tvfactorNumber);
        expandableLayout = findViewById(R.id.expandableLayout);
        txtMore = findViewById(R.id.txtMore);
        txtSumKhadamat = findViewById(R.id.txtSumKhadamat);
        tvPrice = findViewById(R.id.tvPrice);
        txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(GET_PRICE_KHADAMAT)));
        txttavalodm = findViewById(R.id.txttavalodm);
        txttavalodm.setOnClickListener(this);
        txtnamem = findViewById(R.id.txtnamem);
        imgCount = findViewById(R.id.imgCount);
        txtfamilym = findViewById(R.id.txtfamilym);
        txt_NationalCode_m= (EditText) findViewById(R.id.txt_NationalCode_m);
     //   txt_typt_service_raft= (TextView) findViewById(R.id.txt_typt_service_raft);
        txt_NationalCode_m.setOnClickListener(this);
        txt_NationalCode_m.setImeOptions(EditorInfo.IME_ACTION_DONE);
        //txt_NationalCode_m.addTextChangedListener(new GenericTextWatcher(txt_NationalCode_m));
        txt_NationalCode_m.setOnFocusChangeListener(this);

       // txt_typt_service_raft = (TextView) findViewById(R.id.txt_typt_service_raft);
      // txt_typt_service_raft.setOnClickListener(this);
      // // txt_typt_service_raft.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        //txt_typt_service_raft.addTextChangedListener(new GenericTextWatcher(txt_typt_service_raft));
      //  txt_typt_service_raft.setOnFocusChangeListener(this);

        txt_typt_service_bargasht = findViewById(R.id.txt_typt_service_bargasht);
        txt_typt_service_raft = findViewById(R.id.txt_typt_service_raft);
        txtTitle = findViewById(R.id.tvTitle);

        btn_next_partnerInfo = findViewById(R.id.btn_next_partnerInfo);

        btn_nextm = findViewById(R.id.btn_nextm);
        btn_taeed_khadamat = findViewById(R.id.btn_taeed_khadamat);
        linear_typt_service_bargasht = findViewById(R.id.linear_typt_service_bargasht);
        linear_typt_service_raft = findViewById(R.id.linear_typt_service_raft);
        btn_pardakht_factor = findViewById(R.id.btn_pardakht_factor);
        btn_pardakht_factor.setEnabled(false);
        btn_pardakht_factor.setVisibility(View.GONE);
        ActiveOperation();
        btn_saler = findViewById(R.id.btn_saler);
        btn_mosaferan = findViewById(R.id.btn_mosaferan);
        btn_khadamat = findViewById(R.id.btn_khadamat);
        btn_pish_factor = findViewById(R.id.btn_pish_factor);

        txtSaler = findViewById(R.id.txtSaler);
        txtMasaferan = findViewById(R.id.txtMasaferan);
        txtKhadamat = findViewById(R.id.txtKhadamat);
        txtPishfactor = findViewById(R.id.txtPishfactor);

        setAnimation();

        linear_saler = findViewById(R.id.linear_saler);
        linear_mosaferan = findViewById(R.id.linear_mosaferan);
        linear_pish_factor = findViewById(R.id.linear_pish_factor);
        linearMahaleeghamat = findViewById(R.id.linearMahaleeghamat);
        linearMeliyat = findViewById(R.id.linearMeliyat);
        txtnameP = findViewById(R.id.txtnameP);
        txtfamilyP = (EditText) findViewById(R.id.txtfamilyP);
        txtmobileP = (EditText) findViewById(R.id.txtmobileP);
        txtkodemeliP = (EditText) findViewById(R.id.txtkodemeliP);
        txtemeliP = (EditText) findViewById(R.id.txtemeliP);
        txtTelP = (EditText) findViewById(R.id.txtTelP);
        txtaddressP = (EditText) findViewById(R.id.txtaddressP);
        txtmeliyatm = findViewById(R.id.txtmeliyatm);
        txtmahale_eghamat = findViewById(R.id.txtmahale_eghamat);
        txt_shomare_factor = findViewById(R.id.txt_shomare_factor);
        linear_list_khadamat = findViewById(R.id.linear_list_khadamat);
        listKhadamat = findViewById(R.id.listKhadamat);
        llDetailHotel = findViewById(R.id.llDetailHotel);
        llDetailPassanger = findViewById(R.id.llDetailPassanger);
        llDetailService = findViewById(R.id.llDetailService);
        llDetailFlight = findViewById(R.id.llDetailFlight);

        txtnameP.setOnFocusChangeListener(this);
        txtfamilyP.setOnFocusChangeListener(this);
        txtmobileP.setOnFocusChangeListener(this);
        txtkodemeliP.setOnFocusChangeListener(this);
        txtemeliP.setOnFocusChangeListener(this);
        txtTelP .setOnFocusChangeListener(this);
        txtfamilym.setOnFocusChangeListener(this);
        txtnamem.setOnFocusChangeListener(this);

        txtemeliP.addTextChangedListener(new GenericTextWatcher(txtemeliP));
        txtTelP.addTextChangedListener(new GenericTextWatcher(txtTelP));
        txtaddressP.addTextChangedListener(new GenericTextWatcher(txtaddressP));
        txtkodemeliP.addTextChangedListener(new GenericTextWatcher(txtkodemeliP));
        txtmobileP.addTextChangedListener(new GenericTextWatcher(txtmobileP));
        txtfamilyP.addTextChangedListener(new GenericTextWatcher(txtfamilyP));
        txtnameP.addTextChangedListener(new GenericTextWatcher(txtnameP));
        txtnamem.addTextChangedListener(new GenericTextWatcher(txtnamem));
        txtfamilym.addTextChangedListener(new GenericTextWatcher(txtfamilym));


        txt_shomare_factor.setOnClickListener(this);
        txtmahale_eghamat.setOnClickListener(this);
        txtmeliyatm.setOnClickListener(this);
        btn_saler.setOnClickListener(this);
        btn_mosaferan.setOnClickListener(this);
        btn_pish_factor.setOnClickListener(this);
        btn_pardakht_factor.setOnClickListener(this);
        btn_taeed_khadamat.setOnClickListener(this);

        btn_nextm.setOnClickListener(this);
        btn_next_partnerInfo.setOnClickListener(this);

        txtTitle.setOnClickListener(this);
        txt_typt_service_bargasht.setOnClickListener(this);
        txt_typt_service_raft.setOnClickListener(this);

        txtfamilym.setOnClickListener(this);
        imgCount.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        txt_hom.setOnClickListener(this);
        txtMore.setOnClickListener(this);
        txtSumKhadamat.setOnClickListener(this);

        txtnamem.setOnClickListener(this);
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
        //////////////
        sum = Prefs.getInt("PassCount", 1);

        countB = sum;
        btn_pardakht_factor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.putString("TypeGetPre", "I");

                Utility.openUrlCustomTab(PassengerTrainActivity.this, paymentUrl);
            }
        });
    }//endInitView

    @Override
    public void onReady(ResponseTrainPurchase responseTrainPurchase) {
        Log.e("ResponseTrainPurchase:", new Gson().toJson(responseTrainPurchase));
        FlagMosaferan = false;
        rlLoading.setVisibility(View.GONE);

        try {
            List<Error> GetError = null;
            if (responseTrainPurchase != null)
                if (responseTrainPurchase.getErrors().size() > 0) {
                    AlertDialogPassengerFlight AlertDialogPassengerFlight = new AlertDialogPassengerFlight(PassengerTrainActivity.this);
                    AlertDialogPassengerFlight.setText(responseTrainPurchase.getErrors().get(0).getMessage() + responseTrainPurchase.getBookingMessageEn(), getString(R.string.massege));
                } else {
                    try {

                        Prefs.putString("BookingCode_NumFactor", responseTrainPurchase.getBookingCode() + "");
                        txt_shomare_factor.setText(responseTrainPurchase.getBookingCode() + "");
                        textView4.setImageBitmap(getBitmap(responseTrainPurchase.getBookingCode() + "", 128, 300, 150));
                        Prefs.putString("BookingCode_NumFactor", responseTrainPurchase.getBookingCode() + "");
                        tvfactorNumber.setText(responseTrainPurchase.getBookingCode() + "");

                        linear_saler.setVisibility(View.GONE);
                        linear_mosaferan.setVisibility(View.GONE);
                        linear_list_khadamat.setVisibility(View.GONE);
                        linear_pish_factor.setVisibility(View.VISIBLE);
                        ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.khadamat_passenger_on);
                        ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
                        txtTitle.setText(getString(R.string.Approval_and_payment_of_pre_invoice));

                        sendRequestGetPreFactorDetails(getApplicationContext(),this);
                        FlagTab = true;

                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
        } catch (Exception e) {
            AlertDialogPassengerFlight AlertDialogPassengerFlight = new AlertDialogPassengerFlight(PassengerTrainActivity.this);
            AlertDialogPassengerFlight.setText(getString(R.string.Error_getting_information_from_eli), getString(R.string.massege));
        }
    }
    @Override
    public void onError(String message) {
        rlLoading.setVisibility(View.GONE);
        AlertDialogPassengerFlight AlertDialogPassengerFlight = new AlertDialogPassengerFlight(PassengerTrainActivity.this);
        AlertDialogPassengerFlight.setText(getString(R.string.Error_getting_information_from_eli),getString(R.string.massege));
    }
    //Third step
    private static void sendRequestGetPreFactorDetails(Context context,Activity activity) {
        rlLoading.setVisibility(View.VISIBLE);
       // Utility.disableEnableControls(false, rlRoot);

        RequestGetPreFactor requestGePreFactorDetails = new RequestGetPreFactor();


        requestGePreFactorDetails.setPreFactorNo(tvfactorNumber.getText().toString());
        Log.e("RequestGetPreFactor: ", new Gson().toJson(requestGePreFactorDetails));
        SingletonService.getInstance().getFlight().newGetPreFactorServiceAvail(new OnServiceStatus<ResponseGetPreFactor>() {
            @Override
            public void onReady(ResponseGetPreFactor responseGePreFactorDetails) {

                Log.e("ResponseGetPreFactor: ", new Gson().toJson(responseGePreFactorDetails));
                rlLoading.setVisibility(View.GONE);
              //  Utility.disableEnableControls(true, rlRoot);
                try {
                    SingletonAnalysis.getInstance().logPreBooking(ServiceType.PACKAGE);

                   com.eligasht.service.model.newModel.flight.prefactor.response.PreFactor jArray = responseGePreFactorDetails.getFactorDetails().getPreFactor();//("PreFactor");//FactorSummary

                    Summary jFact = responseGePreFactorDetails.getFactorDetails().getPreFactor().getSummary();//getFactorSummary();
                    Integer RqBase_ID = jFact.getRqBaseID();
                    Integer totalprice = jFact.getTotalPrice().getAmount();
                    if (jFact.getOnlinePaymentURL() == null || jFact.getOnlinePaymentURL().equals("") || TextUtils.isEmpty(jFact.getOnlinePaymentURL())) {
                        btn_pardakht_factor.setVisibility(View.INVISIBLE);
                    } else {
                        paymentUrl = jFact.getOnlinePaymentURL();
                    }
                    tvPrice.setText(String.valueOf(NumberFormat.getInstance().format(totalprice)) + " " + context.getString(R.string.Rial));
//for hotel==========================================================================================
                    final RecyclerView recyclerViewHotel = (RecyclerView) activity.findViewById(R.id.recyclerView);
                    recyclerViewHotel.addItemDecoration(new DividerItemDecoration(activity, 1));
                    recyclerViewHotel.setLayoutManager(new LinearLayoutManager(activity));
                    ArrayList<HotelPreFactorModel> hotelPreFactorModels = new ArrayList<>();
                    List<Hotel> jArray2 = jArray.getHotels();
                    for (int i = 0; i < jArray2.size(); i++) {
                        if (Locale.getDefault().getLanguage().equals("fa")) {
                            hotelPreFactorModels.add(new HotelPreFactorModel(jArray2.get(i).getHotelNameE(),
                                    Utility.dateShow(jArray2.get(i).getHotelChekin()),
                                    Utility.dateShow(jArray2.get(i).getHotelChekout()),
                                    jArray2.get(i).getAdlCount() + "",
                                    jArray2.get(i).getChdCount() + "", jArray2.get(i).getRoomTitleFa(), jArray2.get(i).getCityEn()));
                        }else{
                            hotelPreFactorModels.add(new HotelPreFactorModel(jArray2.get(i).getHotelNameE(),
                                    Utility.dateShow(jArray2.get(i).getHotelChekin()),
                                    Utility.dateShow(jArray2.get(i).getHotelChekout()),
                                    jArray2.get(i).getAdlCount() + "",
                                    jArray2.get(i).getChdCount() + "", jArray2.get(i).getRoomTitleEn(), jArray2.get(i).getCityEn()));
                        }
                    }
                    if (!hotelPreFactorModels.isEmpty()) {
                        recyclerViewHotel.setAdapter(new HotelPreFactorAdapter(hotelPreFactorModels));
                        llDetailHotel.setVisibility(View.VISIBLE);
                    }
//for passenger======================================================================================
                    final RecyclerView recyclerViewPassenger = (RecyclerView) activity.findViewById(R.id.recyclerViewPassenger);
                    recyclerViewPassenger.addItemDecoration(new DividerItemDecoration(activity, 1));
                    recyclerViewPassenger.setLayoutManager(new LinearLayoutManager(activity));
                    ArrayList<PassengerPreFactorModel> passengerPreFactorModels = new ArrayList<>();
                    List<com.eligasht.service.model.newModel.flight.prefactor.response.Passenger> jArray3 = jArray.getPassengers();
                    for (int i = 0; i < jArray3.size(); i++) {
                        String Birthday[] = jArray3.get(i).getBirthday().split("T");
                        passengerPreFactorModels.add(new PassengerPreFactorModel(jArray3.get(i).getGender() + "", jArray3.get(i).getNationality(),
                                Birthday[0].replaceAll("-","/"), jArray3.get(i).getNationalCode(),
                                jArray3.get(i).getName(), (String) jArray3.get(i).getPassportNo()));
                    }
                    if (!passengerPreFactorModels.isEmpty()) {
                        llDetailPassanger.setVisibility(View.VISIBLE);
                        recyclerViewPassenger.setAdapter(new PassangerPreFactorAdapter(passengerPreFactorModels));
                    }
                    //for Services=============================================================================
                    final RecyclerView recyclerViewService = (RecyclerView) activity.findViewById(R.id.recyclerViewService);
                    recyclerViewService.addItemDecoration(new DividerItemDecoration(activity, 1));
                    recyclerViewService.setLayoutManager(new LinearLayoutManager(activity));
                    ArrayList<ServicePreFactorTrainModel> servicePreFactorModels = new ArrayList<>();

                    List<com.eligasht.service.model.newModel.flight.prefactor.response.Service> jArray4 = jArray.getServices();

                    for (int i = 0; i < jArray4.size(); i++) {
                        if (Locale.getDefault().getLanguage().equals("fa")) {
                            servicePreFactorModels.add(new ServicePreFactorTrainModel(jArray4.get(i).getServiceNameEn(),
                                    jArray4.get(i).getServicePrice().getAmount() + "", jArray4.get(i).getServiceType(),
                                    jArray4.get(i).getCityFa(), jArray4.get(i).getServiceNameFa(), jArray4.get(i).getCountryFa(),
                                    jArray4.get(i).getServiceDescFa(),
                                    jArray4.get(i).getAdultsCount()+" بزرگسال "+
                                            jArray4.get(i).getChildsCount()+" کودک "+
                                            jArray4.get(i).getInfantsCount()+" نوزاد ")
                            );
                        }else{
                            servicePreFactorModels.add(new ServicePreFactorTrainModel(jArray4.get(i).getServiceNameEn(),
                                    jArray4.get(i).getServicePrice().getAmount() + "", jArray4.get(i).getServiceType(),
                                    jArray4.get(i).getCityEn(), jArray4.get(i).getServiceNameEn(), jArray4.get(i).getCountryEn(),
                                    jArray4.get(i).getServiceDescFa(),
                                    jArray4.get(i).getAdultsCount()+" بزرگسال "+
                                    jArray4.get(i).getChildsCount()+" کودک "+
                                    jArray4.get(i).getInfantsCount()+" نوزاد ")
                            );
                        }
                    }
                    if (!servicePreFactorModels.isEmpty()) {
                        llDetailService.setVisibility(View.VISIBLE);
                        recyclerViewService.setAdapter(new ServicePreFactorAdapter(servicePreFactorModels,true));

                    }
                    //for flight==================================================================================
                    final RecyclerView recyclerViewFlight = (RecyclerView) activity.findViewById(R.id.recyclerViewFlight);
                    recyclerViewFlight.addItemDecoration(new DividerItemDecoration(activity, 1));
                    recyclerViewFlight.setLayoutManager(new LinearLayoutManager(activity));
                    ArrayList<FlightPreFactorModel> flightPreFactorModels = new ArrayList<>();
                    //List<PreFactorFlight> jArray5 = jArray.getPreFactorFlights();
                    List<Flight> jArray5 = jArray.getFlights();
                    for (int i = 0; i < jArray5.size(); i++) {
                        if (Locale.getDefault().getLanguage().equals("fa")) {
                            flightPreFactorModels.add(new FlightPreFactorModel(jArray5.get(i).getAirlineNameFa(),
                                    jArray5.get(i).getDepAirPortFa(),
                                    jArray5.get(i).getArrAirPortFa(),
                                    Utility.dateShow(jArray5.get(i).getFltDate()),
                                    jArray5.get(i).getFltTime(),
                                    //Utility.dateShow(jArray5.getJSONObject(i).getString("FltCheckinTime")),
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
                                    //Utility.dateShow(jArray5.getJSONObject(i).getString("FltCheckinTime")),
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
                    // }end Error
                } catch (Exception e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onError(String message) {
                rlLoading.setVisibility(View.GONE);
              //  Utility.disableEnableControls(true, rlRoot);
                AlertDialogPassengerFlight AlertDialogPassengerFlight = new AlertDialogPassengerFlight(activity);
                AlertDialogPassengerFlight.setText(message.toString(), context.getString(R.string.massege));
            }
        }, requestGePreFactorDetails);
    }
//oneStep
    private void RequestDomesticTrainPrice() {

        rlLoading.setVisibility(View.VISIBLE);

        RequestDomesticTrainGetPrice requestDomesticTrainGetPrice = new RequestDomesticTrainGetPrice();

        try {
            requestDomesticTrainGetPrice.setSearchKey(Prefs.getString("Train_Searchkey_Search", ""));
            requestDomesticTrainGetPrice.setTrainID(Prefs.getString("Value_TrainId", ""));
            Bundle extras = getIntent().getExtras();
            if (extras != null)
                if (extras.getBoolean("Train_One_Way"))
                     requestDomesticTrainGetPrice.setTrainSegmentIds(  Prefs.getString("Segmengt_Id_True", ""));//perches service
                else
                    requestDomesticTrainGetPrice.setTrainSegmentIds( Prefs.getString("Segmengt_Id_False", ""), Prefs.getString("Segmengt_Id_True", ""));//perches service


        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("RequestDomesticTrainGetPrice:", new Gson().toJson(requestDomesticTrainGetPrice));

        SingletonService.getInstance().getTrain().newGetDomesticTrainGetPriceAvail(new OnServiceStatus<ResponseDomesticTrainGetPrice>() {
            @Override
            public void onReady(ResponseDomesticTrainGetPrice responseDomesticTrainGetPrice) {
                Log.e("ResponseDomesticTrainGetPrice:", new Gson().toJson(responseDomesticTrainGetPrice));
                rlLoading.setVisibility(View.GONE);
                clearPrefsValue();
                try {
                    passSeviceListRaft=new ArrayList<>();
                   passSeviceListBargasht=new ArrayList<>();
                    if (responseDomesticTrainGetPrice.getErrors().size() > 0) {
                        AlertDialogPassengerFlight AlertDialogPassengerFlight = new AlertDialogPassengerFlight(PassengerTrainActivity.this);
                        AlertDialogPassengerFlight.setText(responseDomesticTrainGetPrice.getErrors().get(0).getMessage() + "/n"+responseDomesticTrainGetPrice.getErrors().get(0).getDetailedMessage(), getString(R.string.massege));
                    } else {
                        for (SegmentList segmentList : responseDomesticTrainGetPrice.getTrains().get(0).getSegmentList()) {
                            for (PassengerService passengerService : segmentList.getPassengerServices()) {
                                PassengerServiceModel passengerServiceModel = new PassengerServiceModel(passengerService.getCode(),
                                        "",
                                        passengerService.getName(),
                                        "");

                                if (segmentList.getIsDepartureSegment()) {
                                    passSeviceListRaft.add(passengerServiceModel);
                                } else {
                                    passSeviceListBargasht.add(passengerServiceModel);
                                }
                            }
                        }

                        if (passSeviceListRaft == null) {
                            linear_typt_service_raft.setVisibility(View.GONE);
                        }
                        Bundle extras = getIntent().getExtras();
                        if (extras != null)
                        if (passSeviceListBargasht == null || extras.getBoolean("Train_One_Way")) {//one way not service for return
                            linear_typt_service_bargasht.setVisibility(View.GONE);
                        }

                    }

                } catch (Exception e) {
                    Toast.makeText(PassengerTrainActivity.this, e.toString(), Toast.LENGTH_LONG).show();


                }


            }

            @Override
            public void onError(String message) {
                Log.e("onError:", message);
                Toast.makeText(PassengerTrainActivity.this,message, Toast.LENGTH_LONG).show();
            }
        }, requestDomesticTrainGetPrice);

    }

    private void clearPrefsValue() {
        Prefs.putString("Pass_service_Train_True", "");
        Prefs.putString("Value_Train_True", "");

        Prefs.putString("Pass_service_Train_False", "");
        Prefs.putString("Value_Train_False", "");
    }

    //secondStep
private void  RequestPurchaseTrain(){
    rlLoading.setVisibility(View.VISIBLE);
    RequestTrainPurchase requestTrainPurchase = new RequestTrainPurchase();


    try {

        List<Passenger> passLists = new ArrayList<>();

        PassengerMosaferItems_Table items_Table = new PassengerMosaferItems_Table(PassengerTrainActivity.this);
        CursorManager cursorM = items_Table.getAllMosafer();
        if (cursorM != null) {
            for (int i = 0; i < cursorM.getCount(); i++) {

                cursorM.moveToPosition(i);

                Passenger passList=new Passenger();
                passList.setGender(cursorM.getBoolean(PassengerMosaferItems_Table.Columns.Gender.value()));//.put("Gender", cursorM.getBoolean(PassengerMosaferItems_Table.Columns.Gender.value()));
                passList.setNationality( cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));
                passList.setNationalityID((cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value())).toUpperCase());
               // passList.setNationalityID((cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value())).toUpperCase());
                passList.setPackRoomTypeID(Prefs.getInt("PackRoomType_ID",12));
                passList.setRoomNo(Integer.toString(Prefs.getInt("Room_No", 12)));

                passList.setAddress(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Address.value()));
                passList.setBirthday(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
                passList.setEmail(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Email.value()));

                passList.setFirstNameEn(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                passList.setFirstNameFa(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameFa.value()));
                passList.setLastNameEn(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));

                passList.setFirstNameFa(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameFa.value()));
                passList.setMobile(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Mobile.value()));
                passList.setNationalCode(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_NationalCode.value()));

                /* txt_typt_service_raft.setText(Prefs.getString("Pass_service_Train_True", ""));
        txt_typt_service_bargasht.setText(Prefs.getString("Pass_service_Train_False", ""));*/
               // passList.setPassportExpiration(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));
                passList.setArrService(Prefs.getString("Pass_service_Train_True", ""));//service Raft

               // passList.setPassportNo(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));
                passList.setDepService(Prefs.getString("Pass_service_Train_False", ""));//service Bargasht

                passList.setTel(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Tel.value()));
               // passList.setInsurancePrice(Prefs.getInt("Price", 0));


                passLists.add(passList);

            }
            requestTrainPurchase.setPassengers(passLists);
        }

        ////kharidar
        PassengerPartnerInfo_Table partnerInfo_Table = new PassengerPartnerInfo_Table(PassengerTrainActivity.this);
        CursorManager cursorManager = partnerInfo_Table.getPartner();
        cursorManager.moveToPosition(0);
        Customer partnerList=new Customer();
        partnerList.setAddress(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Address.value()));
        partnerList.setAdMail(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Email.value()));
        partnerList.setFirstNameFa(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_FirstNameFa.value()));
        partnerList.setGender(cursorManager.getBoolean(PassengerPartnerInfo_Table.Columns.RqPartner_Gender.value()));
        partnerList.setLastNameFa(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_LastNameFa.value()));
        partnerList.setMobile(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Mobile.value()));
        partnerList.setNationalCode(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_NationalCode.value()));
        partnerList.setTel(cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Tel.value()));
        partnerList.setAgcUserID(cursorManager.getInt(PassengerPartnerInfo_Table.Columns.AgcUser_ID.value()));
        partnerList.setWebUserID (Integer.parseInt(Prefs.getString("userId", "-1")));//Purchase

        requestTrainPurchase.setCustomer(partnerList);


        requestTrainPurchase.setSelectedTrainID(Prefs.getString("Value_TrainId", ""));

        requestTrainPurchase.setSearchKey(Prefs.getString("Train_Searchkey_Search", ""));
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            if (extras.getBoolean("Train_One_Way")){
                requestTrainPurchase.setSelectedTrainSegmentIDs(Prefs.getString("Segmengt_Id_True", ""));//for One Way

            }else{
                requestTrainPurchase.setSelectedTrainSegmentIDs(Prefs.getString("Segmengt_Id_False", ""),Prefs.getString("Segmengt_Id_True", ""));

            }

        Log.e("RequestTrainPurchase:", new Gson().toJson(requestTrainPurchase));

        SingletonService.getInstance().getTrain().newGetPurchaseTrainAvail(this, requestTrainPurchase);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Prefs.getBoolean("IsDemostic", true);
    }

    @Override
    public boolean needTerminate() {
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnPromotionCode:
                try {

                    PromotionCodeDialog resultGiftDialog = PromotionCodeDialog.newInstance(this,false,6);
                    resultGiftDialog.show(getSupportFragmentManager(),"dialog") ;

                }catch (Exception e) {
                }
                break;
            case R.id.llAddPassenger:
                Intent intent1 = new Intent(this, GetPassengerActivity.class);
                startActivityForResult(intent1, 555);
                break;
            case R.id.txt_hom:
                Prefs.putBoolean("BACK_HOME", true);
                Intent intent = new Intent("sendFinish");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                break;
            case R.id.txtMore:

                linearMahaleeghamat.setVisibility(View.VISIBLE);
                linearMeliyat.setVisibility(View.VISIBLE);
                break;

            case R.id.btnBack:
                if (linear_pish_factor.getVisibility() == View.VISIBLE) {
                    linear_pish_factor.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.VISIBLE);
                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.khadamat_passenger_off);
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
                    txtTitle.setText(getString(R.string.passneger_info));
                    ////////////////////bazyabi atelaate akharin mosafer
                    PassengerMosaferItems_Table items_Table = new PassengerMosaferItems_Table(PassengerTrainActivity.this);
                    CursorManager cursorM = items_Table.getMosaferById(counter - 1);
                    if (cursorM != null) {
                        for (int i = 0; i < cursorM.getCount(); i++) {

                            txtnamem.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                            txtfamilym.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));
                            txt_typt_service_raft.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));

                            txttavalodm.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
                            txt_typt_service_bargasht.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));

                            txtmahale_eghamat.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));

                            txtmeliyatm.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));
                            txtTitleCountM.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()));
                        }
                    }
                    counter--;
                    imgCount.setText(counter + "");
                } else if (linear_mosaferan.getVisibility() == View.VISIBLE) {

                    linear_mosaferan.setVisibility(View.GONE);
                    linear_saler.setVisibility(View.VISIBLE);

                    txtTitle.setText(getString(R.string.Buyer_Specifications));
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
                    // }
                } else if (linear_saler.getVisibility() == View.VISIBLE) {
                    Prefs.putBoolean("BACK_HOME", true);

                    finish();

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
                        PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerTrainActivity.this);
                        db.dropTable();
                        ////////////////////////Validate
                        String RqPartner_Address = txtaddressP.getText().toString();
                        String RqPartner_Email = txtemeliP.getText().toString();
                        String RqPartner_FirstNameFa = txtnameP.getText().toString();
                        String RqPartner_Gender = Gensiyat;
                        String RqPartner_LastNameFa = txtfamilyP.getText().toString();
                        String RqPartner_Mobile = txtmobileP.getText().toString();
                        String RqPartner_NationalCode = txtkodemeliP.getText().toString();
                        String RqPartner_Tel = txtTelP.getText().toString();

                        String AgcUser_ID = "-1";

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
                            if (Locale.getDefault().getLanguage().contains("en") || Locale.getDefault().getLanguage().contains("tr")) {
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
                            if (Locale.getDefault().getLanguage().contains("en") || Locale.getDefault().getLanguage().contains("tr")) {
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
                        if (RqPartner_Tel != null && RqPartner_Tel.length() == 11 && RqPartner_Tel.matches("[0-9]+")) {
                            ((EditText) findViewById(R.id.txtTelP)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer = flagMosafer + "T";
                        } else {
                            flagMosafer = flagMosafer + "F";
                            errorMessage = errorMessage + "\n" + "* " +getString(R.string._enter_the_correct_tel_format);//getString(R.string.Enter_the_correct_mobile_format);
                        }
                        if (RqPartner_Address != null && RqPartner_Address.length() > 10 ) {
                            ((EditText) findViewById(R.id.txtaddressP)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer = flagMosafer + "T";
                        } else {
                            flagMosafer = flagMosafer + "F";
                            errorMessage = errorMessage + "\n" + "* " + getString(R.string._please_enter_the_full_address);//getString(R.string.Enter_the_correct_mobile_format);
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

                            AlertDialogPassenger alertDialogPassenger = new AlertDialogPassenger(PassengerTrainActivity.this,false,false);
                            alertDialogPassenger.setText("" + "  " + errorMessage,getString(R.string.EditInput));
                        } else {
                            //insert partner
                            PassengerPartnerInfo_Table partnerInfo_Table = new PassengerPartnerInfo_Table(PassengerTrainActivity.this);

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
            case R.id.txt_typt_service_bargasht:
                try {
                    DialogPassServiceTrain dialogPassServiceTrain = new DialogPassServiceTrain(this,false,false,passSeviceListBargasht,false);
                    dialogPassServiceTrain.setText( "لطفا یک مورد را انتخاب کنید");



                }catch (Exception e){
                    e.getMessage();
                }
				flag = false;
                break;
                case R.id.txt_typt_service_raft:
                try {
                    DialogPassServiceTrain dialogPassServiceTrain = new DialogPassServiceTrain(this,false,false,passSeviceListRaft,true);
                    dialogPassServiceTrain.setText( "لطفا یک مورد را انتخاب کنید");



                }catch (Exception e){
                    e.getMessage();
                }

                break;
            case R.id.btn_nextm:
                LinearLayout mainLayout;
                mainLayout = findViewById(R.id.linear_list_khadamat);


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
                    String RqPassenger_PassExpDate= txt_typt_service_bargasht.getText().toString();
                    String RqPassenger_PassNo=txt_typt_service_raft.getText().toString();
                    String RqPassenger_Tel= null;

                    String flagMosafer = "T";
                    String errorMessagePartner = "";

                    if (txt_NationalCode_m.getText().toString() != null && txt_NationalCode_m.getText().toString().length() == 10) {
                        ((EditText) findViewById(R.id.txt_NationalCode_m)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer = flagMosafer + "T";
                    } else {
                        flagMosafer = flagMosafer + "F";
                        errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.The_national_code_is_not_correct);
                    }

                   if (passSeviceListRaft != null && passSeviceListRaft.size() >0) {
                        if (RqPassenger_PassNo.trim().length() > 0 && txt_typt_service_raft.getText().toString() != null) {
                            ((TextView) findViewById(R.id.txt_typt_service_raft)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer = flagMosafer + "T";
                        } else {
                            flagMosafer = flagMosafer + "F";
                            errorMessagePartner = errorMessagePartner + "\n" + "* " +"لطفا خدمات رفت را انتخاب کنید";
                        }
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

                    if (passSeviceListBargasht != null && passSeviceListBargasht.size() >0) {
                            if (RqPassenger_PassExpDate != null && RqPassenger_PassExpDate.length() > 4) {
                                ((TextView) findViewById(R.id.txt_typt_service_bargasht)).setTextColor(Color.parseColor("#4d4d4d"));
                                flagMosafer = flagMosafer + "T";
                            } else {
                                flagMosafer = flagMosafer + "F";
                                errorMessagePartner = errorMessagePartner + "\n" + "* " + "لطفا خدمات برگشت را انتخاب کنید";
                            }
                    }
                    if (Gensiyat.contains("true") || Gensiyat.contains("false")) {
                        flagMosafer = flagMosafer + "T";
                    } else {
                        flagMosafer = flagMosafer + "F";
                        errorMessagePartner = errorMessagePartner + "\n" + "* " + getString(R.string.Enter_your_gender);
                    }
                    ///endValidate

                    if (flagMosafer.contains("F")) {
                        AlertDialogPassenger AlertDialogPassengerFlight = new AlertDialogPassenger(PassengerTrainActivity.this,false,false);
                        AlertDialogPassengerFlight.setText("" + "  " + errorMessagePartner,getString(R.string.EditInput));
                    } else {
                        PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerTrainActivity.this);

                        db.openDB();

                        if (sum > 0) {
                            System.out.println("gender:" + Gender);

                            if (counter - 1 == 1) {
                                db.insertData(counter - 1, getString(R.string.First_passenger_information), imgCount.getText().toString(), Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);
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
                                txtTitleCountM.setText(getString(R.string.Passenger_information) + getCounter(counter) + " (بزرگسال) ");
                                imgCount.setText(counter + "");
                            } else if (countK != 0) {
                                txtTitleCountM.setText(getString(R.string.Passenger_information) + getCounter(counter) + getString(R.string.child_));
                                imgCount.setText(counter + "");
                            } else if (countN != 0) {
                                txtTitleCountM.setText(getString(R.string.Passenger_information) + getCounter(counter) + getString(R.string.baby_));
                                imgCount.setText(counter + "");
                            }

                            System.out.println("counterMosafer:" + getCounter(counter) + counter);

                            sum--;
                            ///pak kardan data haye mosafere ghabli:
                            if (sum > 0) {

                                try {
                                    txttavalodm.setText(jsonObjBDate.getJSONObject(counter - 1).getString("BirthDate") + "");
                                    RqPassenger_Birthdate = jsonObjBDate.getJSONObject(counter - 1).getString("BirthDate");
                                    System.out.println("BIRTHDATECounter:" + jsonObjBDate.getJSONObject(counter).getString("BirthDate") + " ==" + (counter));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                txtnamem.setText("");
                                txtfamilym.setText("");
                                txt_typt_service_bargasht.setText("");
                                txt_typt_service_raft.setText("");
                                txt_NationalCode_m .setText("");
                                btnzan.setChecked(false);
                                btnmard.setChecked(false);
                                Gensiyat = "";
                                txtnamem.setFocusable(true);

                            }
                            counter++;
                            System.out.println("insert:" + "sum:" + sum);
                        }
                        db.closeDB();
                        linear_mosaferan.clearFocus();
                    }

                    //call api saler
                    if (sum == 0) {
                        System.out.println("APICALL:" + "sum:" + sum);
                        System.out.println("insert:");
                        //new AsyncFetch().execute();
                         RequestPurchaseTrain();
                    }
                }
                if (FlagTab) {
                    linear_saler.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_pish_factor.setVisibility(View.VISIBLE);

                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.khadamat_passenger_on);
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);
                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
                    txtTitle.setText(getString(R.string.Approval_and_payment_of_pre_invoice));
                    setAnimation();
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);
                txt_typt_service_bargasht.setScroller(new Scroller(this));
                ScrollView scrolMosafer = findViewById(R.id.scrolMosafer);
                scrolMosafer.fullScroll(ScrollView.FOCUS_UP);
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

                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.khadamat_passenger_off);
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
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

                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.khadamat_passenger_off);
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
                    txtTitle.setText(getString(R.string.passneger_info));
                    setAnimation();
                }
                break;
            case R.id.btn_pish_factor:
                if (FlagTab) {
                    linear_saler.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_pish_factor.setVisibility(View.VISIBLE);

                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.khadamat_passenger_on);
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);
                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
                    txtTitle.setText(getString(R.string.Approval_and_payment_of_pre_invoice));
                    setAnimation();
                }
                break;
        }
    }

    public String getCounter(int i) {
        String s = "";
        switch (i) {
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
                txtmahale_eghamat.setText(countryCode + "");
            if (nationalityCode != null)
                txtmeliyatm.setText(nationalityCode + "");
        }
        if(requestCode == 555 && resultCode == Activity.RESULT_OK){

            List<PassengerDBModel> passengerDBModels=PassengerDBModel.listAll(PassengerDBModel.class);
            for (PassengerDBModel model:passengerDBModels) {
                if (model.getId()==data.getLongExtra("Id",0)){
                    txtnamem.setText(model.getRqPassenger_FirstNameEn());
                    txtfamilym.setText(model.getRqPassenger_LastNameEn());
                    txt_typt_service_raft.setText(model.getRqPassenger_PassNo());
                    txt_NationalCode_m.setText(model.getRqPassenger_NationalCode());
                    txttavalodm.setText(model.getRqPassenger_Birthdate());
                    txt_typt_service_bargasht.setText(model.getRqPassenger_PassExpDate());
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
            linear_list_khadamat.setVisibility(View.GONE);

            ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.khadamat_passenger_off);
            ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));

            linear_mosaferan.setVisibility(View.VISIBLE);
            txtTitle.setText(getString(R.string.passneger_info));
            ////////////////////bazyabi atelaate akharin mosafer
            PassengerMosaferItems_Table items_Table = new PassengerMosaferItems_Table(PassengerTrainActivity.this);
            CursorManager cursorM = items_Table.getMosaferById(counter - 1);
            if (cursorM != null) {
                for (int i = 0; i < cursorM.getCount(); i++) {

                    txtnamem.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                    txtfamilym.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));
                    txt_typt_service_raft.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));

                    txttavalodm.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
                    txt_typt_service_bargasht.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));

                    txtmahale_eghamat.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));

                    txtmeliyatm.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));
                    txtTitleCountM.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()));
                    System.out.println("InsertMosaferGet:" + cursorM.getString(PassengerMosaferItems_Table.Columns.ID.value()) + " " + cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()) + " " + cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                }
            }
            counter--;

            imgCount.setText(counter + "");

        } else if (linear_mosaferan.getVisibility() == View.VISIBLE) {

            linear_mosaferan.setVisibility(View.GONE);
            linear_saler.setVisibility(View.VISIBLE);

            txtTitle.setText(getString(R.string.Buyer_Specifications));
            ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
            ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
        } else if (linear_saler.getVisibility() == View.VISIBLE) {

            finish();
        }
    }

    @Override
    public void searchTextChanged(String searchText) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
            String text = editable.toString();


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

        Bitmap bitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
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
                    } else {
                        txtmeliyatm.setError(getString(R.string.Please_enter_your_nationality));
                    }
                }
                break;
            case R.id.txttavalodm:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txttavalodm.getText().toString() != null && txttavalodm.getText().toString().length() > 4) {
                        ((TextView) findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#4d4d4d"));
                    } else {
                        txttavalodm.setError(getString(R.string.Please_enter_the_date_of_birth));
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
            case R.id.txt_typt_service_bargasht:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txt_typt_service_bargasht.getText().toString() != null && txt_typt_service_bargasht.getText().toString().length() > 4) {
                        ((TextView) findViewById(R.id.txt_typt_service_bargasht)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {

                        txt_typt_service_bargasht.setError("لطفا خدمات برگشت را انتخاب کنید");
                    }
                }
                break;
            case R.id.txt_typt_service_raft:
                if (hasFocus) {
                    System.out.println("t");
                } else {
                    System.out.println("f");
                    if (txt_typt_service_raft.getText().toString().trim().length() > 4 && txt_typt_service_raft.getText().toString() != null) {
                        ((TextView) findViewById(R.id.txt_typt_service_raft)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {

                       // txt_typt_service_raft.setError(getString(R.string.Please_enter_the_passport_number_correctly));
                    }
                    if (txtmeliyatm.getText().toString() != null && txtmeliyatm.getText().toString().length() > 4) {
                    } else {

                       // txt_typt_service_raft.setError(getString(R.string.Please_enter_the_passport_number));
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
                        if (Locale.getDefault().getLanguage().contains("en") || Locale.getDefault().getLanguage().contains("tr")) {
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
                        if (Locale.getDefault().getLanguage().contains("en") || Locale.getDefault().getLanguage().contains("tr")) {
                            if (txtfamilyP.getText().toString().length() > 2 && ((txtfamilyP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")) || !(txtfamilyP.getText().toString().toLowerCase().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")))) {
                                ((EditText) findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));

                            } else {
                                //((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
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

    private static void setAnimation() {
        YoYo.with(Techniques.BounceInRight)
                .duration(600)
                .playOn(btn_saler);
        YoYo.with(Techniques.BounceInRight)
                .duration(600)
                .playOn(btn_mosaferan);

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
    public void onResume() {
        super.onResume();

        final ScrollView scroll_partner = findViewById(R.id.scroll_partner);
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
}


 



