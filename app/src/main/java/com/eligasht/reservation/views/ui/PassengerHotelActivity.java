package com.eligasht.reservation.views.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.tools.datetools.SolarCalendar;
import com.eligasht.reservation.tools.persian.Calendar.persian.util.PersianCalendarUtils;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.pixplicity.easyprefs.library.Prefs;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.lost.flight.FlightPreFactorAdapter;
import com.eligasht.reservation.lost.flight.FlightPreFactorModel;
import com.eligasht.reservation.lost.hotel.HotelPreFactorModel;
import com.eligasht.reservation.lost.hotel.HotelPreFactorAdapter;
import com.eligasht.reservation.lost.passenger.PassangerPreFactorAdapter;
import com.eligasht.reservation.lost.passenger.PassengerPreFactorModel;
import com.eligasht.reservation.lost.service.ServicePreFactorAdapter;
import com.eligasht.reservation.lost.service.ServicePreFactorModel;
import com.eligasht.reservation.models.model.PurchaseFlightResult;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.tools.db.local.PassengerMosaferItems_Table;
import com.eligasht.reservation.tools.db.local.PassengerPartnerInfo_Table;
import com.eligasht.reservation.tools.db.main.CursorManager;
import com.eligasht.reservation.views.activities.transfer.ExcursionDta;
import com.eligasht.reservation.views.adapters.GetHotelKhadmatAdapter;
import com.eligasht.reservation.views.components.Header;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassengerFlight;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;

public class PassengerHotelActivity extends BaseActivity implements Header.onSearchTextChangedListener, OnClickListener, OnItemSelectedListener,View.OnFocusChangeListener ,com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener,
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener{

    public static boolean flag;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    Handler handler;
    ProgressDialog progressBar;
    public FancyButton btnBack;
    public ImageView btn_saler, btn_mosaferan, btn_khadamat, btn_pish_factor;
    public TextView txtfamilyP, txtkodemeliP, txtemeliP, txtmobileP, txtMore, tvfactorNumber;
    public Button btnAddsabad,btn_pardakht_factor,txtSaler,txtMasaferan,txtKhadamat,txtPishfactor;
    public EditText txtnamem, txtfamilym;
    public static TextView txttavalodm;
    public EditText txtnumber_passport, txtnameP;
    public static TextView txtexp_passport;
    public TextView txtTitle, txtmeliyatm, txtmahale_eghamat, txtTitleCountM;
    public static TextView txtSumKhadamat;
    public LinearLayout linear_saler, linear_mosaferan, linear_list_khadamat, linear_pish_factor, linearMahaleeghamat, linearMeliyat, btn_next_partnerInfo, btn_nextm, btn_taeed_khadamat;
    private Handler progressBarHandler = new Handler();
    public ListView list_airport, listKhadamat;
    ArrayList<HashMap<String, String>> mylist = null;
    public static String searchText = "";
    //public static long GET_PRICE_KHADAMAT;
    public static long GET_PRICE_KHADAMAT;
    ExpandableRelativeLayout expandableLayout;
    public TextView imgCount;
    String paymentUrl;
    List<PurchaseFlightResult> data=null;
    GetHotelKhadmatAdapter mAdapter;
    //ScrollView myScrollView;
    private EditText searchtxt;
    public TextView txt_shomare_factor, tvPrice;
    public ImageView txt_hom, textView4;

    private String Gensiyat="";
    Activity activity;
    public int countB;
    public int countK;
    public int countN;
    //public int sum=countB+countK+countN;
    public JSONArray jsonObj = null;
    public int sum=0;
    int counter = 2;
    int room=0;
    int rooms=0;
    //int count;
    //change for Prefactor=========================================================================
    LinearLayout llDetailHotel, llDetailPassanger, llDetailService, llDetailFlight;
    private boolean FlagMosaferan=true;
    private boolean FlagTab=false;
    private com.rey.material.widget.RadioButton btnzan,btnmard,btnzanS,btnmardS;
    RelativeLayout rlLoading,rlRoot;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian1;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian2;
    com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog;
    //ExpandableLayoutListView lvFactor;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        Prefs.putString("IST","H");
        PersianCalendar persianCalendarDatePicker = new PersianCalendar();
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth(), persianCalendarDatePicker.getPersianDay());
//=====================================================================================================
        //_____________________________________________________________________
        datePickerDialog = com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.newInstance(
                this,
                persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay()
        );

        //datePickerDialog.setMinDate(persianCalendarDatePicker);


        //______________________________________________________________________


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


                datePickerDialog.initialize(PassengerHotelActivity.this, Integer.parseInt(yearMF), Integer.parseInt(monthMF), Integer.parseInt(dayMF));
                datePickerDialog.show(getSupportFragmentManager(), "DatepickerdialogRaft");


            }
        });
////////////
        datePickerDialogGregorian2 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog(1);
        //	datePickerDialogGregorian2.setMinDate(persianCalendarDatePicker.toGregorianCalendar());
        datePickerDialogGregorian2.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {

                String month=""+monthOfYear+1;
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

                datePickerDialogGregorian1.initialize(PassengerHotelActivity.this, Integer.parseInt(yearMF1), Integer.parseInt(monthMF1), Integer.parseInt(dayMF1));

                datePickerDialogGregorian1.show(getFragmentManager(), "DatePickerDialogGregorianRaft");
            }
        });



        txtTitleCountM = (TextView) findViewById(R.id.txtTitleCountM);
        txtTitleCountM.setOnClickListener(this);

        String RengAge=txtTitleCountM.getText().toString();

///////////////setmin
        //min max tavalod solar
        if(RengAge.contains("کودک")){

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
        }else if(RengAge.contains("نوزاد")){

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
            String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
            int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
            int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true)-120;
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
       /* JSONArray jsonObj = null;
        try {
            jsonObj = new JSONArray(Prefs.getString("Rooms", "dd"));
            countB=0;
            countK=0;
            countN=0;
            for (int i =0; i<jsonObj.length();i++){

                countB =countB+ jsonObj.getJSONObject(i).getInt("CountB");
                countK = countK+jsonObj.getJSONObject(i).getInt("CountK");
                countN =countN+ jsonObj.getJSONObject(i).getInt("CountN");

            }
            sum=countB+countK+countN;

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        try {
            jsonObj = new JSONArray(Prefs.getString("Rooms", "[{\"CountB\":1,\"CountK\":0,\"CountN\":0,\"childModels\":[]}]"));
            Log.e("testroom2", jsonObj.toString());
            rooms=jsonObj.length();


        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Getting JSON Array node


        btnBack = (FancyButton) findViewById(R.id.btnBack);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(this);

        //kharidar
        btnzanS = (com.rey.material.widget.RadioButton) findViewById(R.id.zanS);
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

        btnmardS = (com.rey.material.widget.RadioButton) findViewById(R.id.mardS);
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
        btnzan = (com.rey.material.widget.RadioButton) findViewById(R.id.zan);
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

        btnmard = (com.rey.material.widget.RadioButton) findViewById(R.id.mard);
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
        textView4 = (ImageView) findViewById(R.id.textView4);
        tvfactorNumber = (TextView) findViewById(R.id.tvfactorNumber);
        expandableLayout = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout);
        txt_hom.setOnClickListener(this);

        txtMore = (TextView) findViewById(R.id.txtMore);
        txtMore.setOnClickListener(this);

        txtSumKhadamat = (TextView) findViewById(R.id.txtSumKhadamat);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        txtSumKhadamat.setOnClickListener(this);
        txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(GET_PRICE_KHADAMAT)));

        txttavalodm = (TextView) findViewById(R.id.txttavalodm);
        txttavalodm.setOnClickListener(this);
        txtnamem = (EditText) findViewById(R.id.txtnamem);
        txtnamem.setOnClickListener(this);
        txtnamem.setOnFocusChangeListener(this);
        txtnamem.addTextChangedListener(new GenericTextWatcher(txtnamem));
        imgCount = (TextView) findViewById(R.id.imgCount);

        imgCount.setOnClickListener(this);
        imgCount.setText("اتاق "+getCounter(room));

        txtfamilym = (EditText) findViewById(R.id.txtfamilym);
        //lvFactor = (ExpandableLayoutListView) findViewById(R.id.lvFactor);
        txtfamilym.setOnClickListener(this);
        txtfamilym.setOnFocusChangeListener(this);
        txtfamilym.addTextChangedListener(new GenericTextWatcher(txtfamilym));



        txtnumber_passport = (EditText) findViewById(R.id.txtnumber_passport);
        txtnumber_passport.setOnClickListener(this);
        txtnumber_passport.setOnFocusChangeListener(this);
        txtnumber_passport.setImeOptions(EditorInfo.IME_ACTION_DONE);
        txtnumber_passport.addTextChangedListener(new GenericTextWatcher(txtnumber_passport));
        txtexp_passport = (TextView) findViewById(R.id.txtexp_passport);
        txtexp_passport.setOnClickListener(this);

        txtTitle = (TextView) findViewById(R.id.tvTitle);
        txtTitle.setOnClickListener(this);


        btn_next_partnerInfo = (LinearLayout) findViewById(R.id.btn_next_partnerInfo);
        btn_next_partnerInfo.setOnClickListener(this);

        btn_nextm = (LinearLayout) findViewById(R.id.btn_nextm);
        btn_nextm.setOnClickListener(this);

        btn_taeed_khadamat = (LinearLayout) findViewById(R.id.btn_taeed_khadamat);
        btn_taeed_khadamat.setOnClickListener(this);

        btn_pardakht_factor = (Button) findViewById(R.id.btn_pardakht_factor);
        btn_pardakht_factor.setOnClickListener(this);
            /* btnAddsabad=(Button)findViewById(R.id.btnAddsabad);
             btnAddsabad.setOnClickListener(this);*/

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
        linear_pish_factor = (LinearLayout) findViewById(R.id.linear_pish_factor);
        linearMahaleeghamat = (LinearLayout) findViewById(R.id.linearMahaleeghamat);
        linearMeliyat = (LinearLayout) findViewById(R.id.linearMeliyat);

        txtnameP = (EditText) findViewById(R.id.txtnameP);
        //	txtnameP.setHint("لطفا نام را فارسی وارد کنید");
        txtnameP.addTextChangedListener(new GenericTextWatcher(txtnameP));
        txtnameP.setOnFocusChangeListener(this);
        txtfamilyP = (EditText) findViewById(R.id.txtfamilyP);
        //	txtfamilyP.setHint("لطفا نام خانوادگی را فارسی وارد کنید");
        txtfamilyP.addTextChangedListener(new GenericTextWatcher(txtfamilyP));
        txtfamilyP.setOnFocusChangeListener(this);
        txtmobileP = (EditText) findViewById(R.id.txtmobileP);
        txtmobileP.addTextChangedListener(new GenericTextWatcher(txtmobileP));
        txtmobileP.setOnFocusChangeListener(this);
        txtkodemeliP = (EditText) findViewById(R.id.txtkodemeliP);
        txtkodemeliP.addTextChangedListener(new GenericTextWatcher(txtkodemeliP));
        txtkodemeliP.setOnFocusChangeListener(this);
        txtemeliP = (EditText) findViewById(R.id.txtemeliP);
        txtemeliP.addTextChangedListener(new GenericTextWatcher(txtemeliP));
        txtemeliP.setOnFocusChangeListener(this);
        txtmeliyatm = (TextView) findViewById(R.id.txtmeliyatm);
        txtmeliyatm.setOnClickListener(this);
        txtmahale_eghamat = (TextView) findViewById(R.id.txtmahale_eghamat);
        txtmahale_eghamat.setOnClickListener(this);

        txt_shomare_factor = (TextView) findViewById(R.id.txt_shomare_factor);
        txt_shomare_factor.setOnClickListener(this);

        linear_list_khadamat = (LinearLayout) findViewById(R.id.linear_list_khadamat);

        listKhadamat = (ListView) findViewById(R.id.listKhadamat);
        llDetailHotel = (LinearLayout) findViewById(R.id.llDetailHotel);
        llDetailPassanger = (LinearLayout) findViewById(R.id.llDetailPassanger);
        llDetailService = (LinearLayout) findViewById(R.id.llDetailService);
        llDetailFlight = (LinearLayout) findViewById(R.id.llDetailFlight);
        // myScrollView = (ScrollView) findViewById(R.id.layout_scroll);


        //////////////////////////
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        Spinner spinnerMosafer = (Spinner) findViewById(R.id.spinnerMosafer);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinnerMosafer.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("لطفا جنسیت را انتخاب کنید");
        categories.add("مرد");
        categories.add("زن");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinnerMosafer.setAdapter(dataAdapter);

        //sum = Prefs.getInt("SumPass", 0);
        btn_pardakht_factor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.putString("TypeGetPre", "H");
                Prefs.putString("PaymentUrl", paymentUrl);
                Utility.openUrlCustomTab(PassengerHotelActivity.this, paymentUrl);


            }
        });
        //////////////login user
        try{
            if (WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserID() != -1) {
                txtnameP.setText( WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserFnameF());
                txtfamilyP.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserLnameF());
                txtmobileP.setText( WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMobile());
                txtkodemeliP.setText( WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserNationalCode());
                txtemeliP.setText( WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMail());
            }
        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
        //////////////
    }//end oncreate

    private class AsyncFetchGetPreFactorDetails extends AsyncTask<String, String, String> {
       // ProgressDialog pdLoading = new ProgressDialog(PassengerHotelActivity.this);
        HttpURLConnection conn;
        URL url = null;
        private ListView listAirPort;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            rlLoading.setVisibility(View.VISIBLE);
            Utility.disableEnableControls(false,rlRoot);
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetPreFactorDetails");

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


                String data = OrderToJsonGetPreFactorDetails();


                HttpClient client = new DefaultHttpClient();


                HttpPost post = new HttpPost();
                post = new HttpPost("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetPreFactorDetails");
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


                HashMap<String, String> airport = null;
                mylist = new ArrayList<HashMap<String, String>>();
                HttpResponse res = client.execute(post);
                String retSrc = EntityUtils.toString(res.getEntity(), HTTP.UTF_8);


                return (retSrc);

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }//end doin background


        @Override
        protected void onPostExecute(String resultPishfactor) {

            //this method will be running on UI thread
            //{"PurchaseServiceResult":{"Errors":null,"ResultText":"Temp Contract Saved Successfully!","SuccessResult":782528}}
            //  pdLoading.dismiss();
            //List<PurchaseFlightResult> data=new ArrayList<PurchaseFlightResult>();

            rlLoading.setVisibility(View.GONE);
            Utility.disableEnableControls(true,rlRoot);
            try {
////////////////////////////
                JSONObject jsonObj = new JSONObject(resultPishfactor);

/*         if (!ErrorInApi(jsonObj)){*/
                Log.e("jsonObj", jsonObj.toString());

                // Getting JSON Array node
                JSONObject GetAirportsResult = jsonObj.getJSONObject("GetPreFactorDetailsResult");


                JSONObject jArray = GetAirportsResult.getJSONObject("PreFactor");//FactorSummary


                //FactorSummary
                JSONObject jFact = jArray.getJSONObject("FactorSummary");


                int RqBase_ID = jFact.getInt("RqBase_ID");
                //////////////////////////////
                long totalprice = jFact.getLong("TotalPrice");
                paymentUrl = jFact.getString("OnlinePaymentURL");


                tvPrice.setText(String.valueOf(NumberFormat.getInstance().format(totalprice)) + " ریال ");

//for hotel==========================================================================================
                final RecyclerView recyclerViewHotel = (RecyclerView) findViewById(R.id.recyclerView);
                recyclerViewHotel.addItemDecoration(new DividerItemDecoration(PassengerHotelActivity.this, 1));
                recyclerViewHotel.setLayoutManager(new LinearLayoutManager(PassengerHotelActivity.this));
                ArrayList<HotelPreFactorModel> hotelPreFactorModels = new ArrayList<>();

                JSONArray jArray2 = jArray.getJSONArray("PreFactorHotels");


                for (int i = 0; i < jArray2.length(); i++) {
                    hotelPreFactorModels.add(new HotelPreFactorModel(jArray2.getJSONObject(i).getString("HotelNameE"),
                            Utility.dateShow(jArray2.getJSONObject(i).getString("HotelChekin"))
                            , Utility.dateShow(jArray2.getJSONObject(i).getString("HotelChekout")),
                            jArray2.getJSONObject(i).getString("AdlCount"),
                            jArray2.getJSONObject(i).getString("ChdCount"), jArray2.getJSONObject(i).getString("RoomTitleFa")));

                }
                if (!hotelPreFactorModels.isEmpty()) {
                    recyclerViewHotel.setAdapter(new HotelPreFactorAdapter(hotelPreFactorModels));
                    llDetailHotel.setVisibility(View.VISIBLE);
                }


//for passenger======================================================================================


                final RecyclerView recyclerViewPassenger = (RecyclerView) findViewById(R.id.recyclerViewPassenger);
                recyclerViewPassenger.addItemDecoration(new DividerItemDecoration(PassengerHotelActivity.this, 1));
                recyclerViewPassenger.setLayoutManager(new LinearLayoutManager(PassengerHotelActivity.this));
                ArrayList<PassengerPreFactorModel> passengerPreFactorModels = new ArrayList<>();

                JSONArray jArray3 = jArray.getJSONArray("RequestPassenger");


                for (int i = 0; i < jArray3.length(); i++) {
                    passengerPreFactorModels.add(new PassengerPreFactorModel(jArray3.getJSONObject(i).getString("Gender"),jArray3.getJSONObject(i).getString("Nationality"),
                            jArray3.getJSONObject(i).getString("RqPassenger_Birthdate"),jArray3.getJSONObject(i).getString("RqPassenger_PassNo"),
                            jArray3.getJSONObject(i).getString("RqPassenger_name"),jArray3.getJSONObject(i).getString("RqPassenger_NationalCode")));

                }
                if (!passengerPreFactorModels.isEmpty()) {
                    llDetailPassanger.setVisibility(View.VISIBLE);
                    recyclerViewPassenger.setAdapter(new PassangerPreFactorAdapter(passengerPreFactorModels));

                }


                //for Services=============================================================================
                final RecyclerView recyclerViewService = (RecyclerView) findViewById(R.id.recyclerViewService);
                recyclerViewService.addItemDecoration(new DividerItemDecoration(PassengerHotelActivity.this, 1));
                recyclerViewService.setLayoutManager(new LinearLayoutManager(PassengerHotelActivity.this));
                ArrayList<ServicePreFactorModel> servicePreFactorModels = new ArrayList<>();
                JSONArray jArray4 = jArray.getJSONArray("PreFactorServices");

                for (int i = 0; i < jArray4.length(); i++) {
                    servicePreFactorModels.add(new ServicePreFactorModel(jArray4.getJSONObject(i).getString("ServiceNameEn"),
                            jArray4.getJSONObject(i).getString("ServicePrice"), jArray4.getJSONObject(i).getString("ServiceType"),
                            jArray4.getJSONObject(i).getString("CityFa"), jArray4.getJSONObject(i).getString("ServiceNameFa"),
                            jArray4.getJSONObject(i).getString("CountryFa")));

                }
                if (!servicePreFactorModels.isEmpty()) {
                    llDetailService.setVisibility(View.VISIBLE);
                    recyclerViewService.setAdapter(new ServicePreFactorAdapter(servicePreFactorModels));

                }
                //for flight==================================================================================
                final RecyclerView recyclerViewFlight = (RecyclerView) findViewById(R.id.recyclerViewFlight);
                recyclerViewFlight.addItemDecoration(new DividerItemDecoration(PassengerHotelActivity.this, 1));
                recyclerViewFlight.setLayoutManager(new LinearLayoutManager(PassengerHotelActivity.this));
                ArrayList<FlightPreFactorModel> flightPreFactorModels = new ArrayList<>();
                JSONArray jArray5 = jArray.getJSONArray("PreFactorFlights");

                for (int i = 0; i < jArray5.length(); i++) {
                    /////////////////////////////////////////////


                    ////////////////////////
                    flightPreFactorModels.add(new FlightPreFactorModel(jArray5.getJSONObject(i).getString("AirlineNameFa"),
                            jArray5.getJSONObject(i).getString("DepAirPortFa"),
                            jArray5.getJSONObject(i).getString("ArrAirPortFa"),
                            Utility.dateShow(jArray5.getJSONObject(i).getString("FltDate")),
                            jArray5.getJSONObject(i).getString("FltTime"),
                            //Utility.dateShow(jArray5.getJSONObject(i).getString("FltCheckinTime")),
                            jArray5.getJSONObject(i).getString("FltCheckinTime"),

                            jArray5.getJSONObject(i).getString("FltNumber"),
                            jArray5.getJSONObject(i).getString("AirlineNameFa"),
                            jArray5.getJSONObject(i).getString("DepartureCityFa"),jArray5.getJSONObject(i).getString("AirlineCode")));
                }
                if (!flightPreFactorModels.isEmpty()) {
                    llDetailFlight.setVisibility(View.VISIBLE);
                    recyclerViewFlight.setAdapter(new FlightPreFactorAdapter(flightPreFactorModels));

                }
                setAnimation();

            } catch (JSONException e) {
                AlertDialogPassengerFlight AlertDialogPassengerFlight =  new AlertDialogPassengerFlight(PassengerHotelActivity.this,PassengerHotelActivity.this);
                AlertDialogPassengerFlight.setText("خطا در دریافت اطلاعات از الی گشت ");


            }


        }//end on pos excute


    }


    //end AsyncFetchGetPreFactorDetails
    //AsyncFetchPishFactor
    private class AsyncFetchPishFactor extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(PassengerHotelActivity.this);
        HttpURLConnection conn;
        URL url = null;
        private ListView listAirPort;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            rlLoading.setVisibility(View.VISIBLE);
            Utility.disableEnableControls(false,rlRoot);
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/PurchaseService");

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


                String data = OrderToJsonPishFactor();


                HttpClient client = new DefaultHttpClient();


                HttpPost post = new HttpPost();
                post = new HttpPost("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/PurchaseService");
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


                HashMap<String, String> airport = null;
                mylist = new ArrayList<HashMap<String, String>>();
                HttpResponse res = client.execute(post);
                String retSrc = EntityUtils.toString(res.getEntity(), HTTP.UTF_8);


                return (retSrc);

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }//end doin background

        @Override
        protected void onPostExecute(String resultPishfactor) {


            rlLoading.setVisibility(View.GONE);
            Utility.disableEnableControls(true,rlRoot);
            try {
////////////////////////////
                JSONObject jsonObj = new JSONObject(resultPishfactor);


                // JSONObject jsonObj = new JSONObject(retSrc);

                // Getting JSON Array node
                JSONObject GetAirportsResult = jsonObj.getJSONObject("PurchaseServiceResult");
                int successResult = GetAirportsResult.getInt("SuccessResult");
                if (successResult == 0) {
                    //get Error
                    JSONObject getError = jsonObj.getJSONObject("Errors");
                    String message = getError.getString("Message");
                   // Toast.makeText(PassengerHotelActivity.this, message, Toast.LENGTH_LONG).show();
                    AlertDialogPassengerFlight AlertDialogPassengerFlight =  new AlertDialogPassengerFlight(PassengerHotelActivity.this,PassengerHotelActivity.this);
                    AlertDialogPassengerFlight.setText(message);
                }

                if (successResult > 1) {
                    txt_shomare_factor.setText(GetAirportsResult.getString("SuccessResult"));
                    tvfactorNumber.setText(GetAirportsResult.getString("SuccessResult"));

                    textView4.setImageBitmap(getBitmap(GetAirportsResult.getString("SuccessResult"), 128, getResources().getInteger(R.integer._300), getResources().getInteger(R.integer._150)));

                } else {
                    txt_shomare_factor.setText("خطایی رخ داده است !");
                }
                // sfsfs

                // Setup and Handover data to recyclerview
                ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_on);
                ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
                txtTitle.setText("تایید و پرداخت پیش فاکتور ");
                //myScrollView.setOnTouchListener(null);

                linear_saler.setVisibility(View.GONE);
                linear_mosaferan.setVisibility(View.GONE);
                linear_list_khadamat.setVisibility(View.GONE);
                linear_pish_factor.setVisibility(View.VISIBLE);
                FlagTab=true;
                new AsyncFetchGetPreFactorDetails().execute();
            } catch (JSONException e) {
                AlertDialogPassengerFlight AlertDialogPassengerFlight =  new AlertDialogPassengerFlight(PassengerHotelActivity.this,PassengerHotelActivity.this);
                AlertDialogPassengerFlight.setText("خطا در دریافت اطلاعات از الی گشت ");
            }


        }//end on pos excute

    }//end async get pish factor

    //het khadamat
    private class AsyncFetch extends AsyncTask<String, String, String> {
       // ProgressDialog pdLoading = new ProgressDialog(PassengerHotelActivity.this);
        HttpURLConnection conn;
        URL url = null;
        private ListView listAirPort;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            rlLoading.setVisibility(View.VISIBLE);
            Utility.disableEnableControls(false,rlRoot);

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://mobilews.eligasht.com/LightServices/Rest/Hotel/HotelService.svc/PurchaseFlightHotel");

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


                String data = OrderToJsonPurchase();//Purchase
                Log.e("passssss", data);


                HttpClient client = new DefaultHttpClient();


                HttpPost post = new HttpPost();
                post = new HttpPost("http://mobilews.eligasht.com/LightServices/Rest/Hotel/HotelService.svc/PurchaseFlightHotel");
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


                HashMap<String, String> airport = null;
                mylist = new ArrayList<HashMap<String, String>>();
                HttpResponse res = client.execute(post);
                String retSrc = EntityUtils.toString(res.getEntity(), HTTP.UTF_8);


                return (retSrc);

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }//end doin background

        @Override
        protected void onPostExecute(String result) {
            FlagMosaferan=false;
            //this method will be running on UI thread


            data = new ArrayList<PurchaseFlightResult>();

            rlLoading.setVisibility(View.GONE);
            Utility.disableEnableControls(true,rlRoot);
            try {
////////////////////////////
                JSONObject jsonObj = new JSONObject(result);

                // JSONObject jsonObj = new JSONObject(retSrc);

                // Getting JSON Array node
                JSONObject GetAirportsResult = jsonObj.getJSONObject("PurchaseFlightHotelResult");//Error

				 /* JSONObject GetError = jsonObj.getJSONObject("Error");
				  Toast.makeText(PassengerPackageActivityT.this,  Get, Toast.LENGTH_LONG).show();*/

                JSONArray jArray = GetAirportsResult.getJSONArray("Services");
                JSONObject jsonResult = GetAirportsResult.getJSONObject("TmpReserveResult");

                Prefs.putString("BookingCode_NumFactor", jsonResult.getString("BookingCode"));
                //////////////////////////////
                //  JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    JSONObject excursionDta = json_data.getJSONObject("ExcursionDta");

                    PurchaseFlightResult fishData = new PurchaseFlightResult();
                    fishData.setCityEn(json_data.getString("CityEn"));
                    fishData.setCityFa(json_data.getString("CityFa"));
                    fishData.setCurrency_ID(json_data.getString("Currency_ID"));

                    fishData.setHasFlight(json_data.getString("HasFlight"));
                    fishData.setHasHotel(json_data.getString("HasHotel"));
                    fishData.setLoadDB(json_data.getString("LoadDB"));

                    fishData.setServiceAdlPrice(json_data.getString("ServiceAdlPrice"));
                    fishData.setServiceChdPrice(json_data.getString("ServiceChdPrice"));
                    fishData.setServiceDescEn(json_data.getString("ServiceDescEn"));

                    fishData.setServiceDescFa(json_data.getString("ServiceDescFa"));
                    fishData.setServiceID(json_data.getString("ServiceID"));
                    fishData.setServiceImgURL(json_data.getString("ServiceImgURL"));

                    fishData.setServiceInfPrice(json_data.getString("ServiceInfPrice"));
                    fishData.setServiceNameEn(json_data.getString("ServiceNameEn"));
                    fishData.setServiceNameFa(json_data.getString("ServiceNameFa"));


                    fishData.setServiceTypeEn(json_data.getString("ServiceTypeEn"));
                    fishData.setServiceTypeFa(json_data.getString("ServiceTypeFa"));
                    fishData.setServiceTypeID(json_data.getString("ServiceTypeID"));

                    fishData.setServiceTotalPrice(json_data.getLong("ServiceTotalPrice"));
                    fishData.setSelectID(json_data.getString("SelectID"));
                    fishData.setBookingCode(jsonResult.getString("BookingCode"));





                    fishData.setExcursionDta(new ExcursionDta(excursionDta.getString("ArrialAirportCode"),
                            excursionDta.getString("ArrialAirportName"),
                            excursionDta.getString("ArrivalFltDate")
                            ,excursionDta.getString("ArrivalFltNo"),
                            excursionDta.getString("ArrivalFltTime"),
                            excursionDta.getString("CityID"),excursionDta.getString("DepartureFltDate"),
                            excursionDta.getString("DepartureFltNo"),excursionDta.getString("DepartureFltTime"),
                            excursionDta.getString("HotelID"),excursionDta.getString("HotelNameEn"),excursionDta.getString("PassengerList")));

                    data.add(fishData);
                }

                // Setup and Handover data to recyclerview

                linear_saler.setVisibility(View.GONE);
                linear_mosaferan.setVisibility(View.GONE);
                linear_pish_factor.setVisibility(View.GONE);
                linear_list_khadamat.setVisibility(View.VISIBLE);
                FlagTab=true;
	/*			myScrollView.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						return true;
					}
				});*/

                ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
                ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
                txtTitle.setText("افزودن خدمات به سبد خرید");

                mAdapter = new GetHotelKhadmatAdapter(PassengerHotelActivity.this, data, PassengerHotelActivity.this,0);
                //mAdapter.setAdapter(mAdapter);
                mAdapter.setData(data);
                listKhadamat.setAdapter(mAdapter);
                setAnimation();
            } catch (JSONException e) {
               // Toast.makeText(PassengerHotelActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                AlertDialogPassengerFlight AlertDialogPassengerFlight =  new AlertDialogPassengerFlight(PassengerHotelActivity.this,PassengerHotelActivity.this);
                AlertDialogPassengerFlight.setText("خطا در دریافت اطلاعات از الی گشت ");
            }

        }//end on pos excute

    }//end async

    public String OrderToJsonPurchase() {
        JSONObject jsone = new JSONObject();
        JSONObject manJson = new JSONObject();


        try {
            String GUID = "";
            String ResultUniqId = "";
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                GUID = extras.getString("Flight_GUID");
                ResultUniqId = SearchParvazActivity.globalResultUniqID;
            }

            JSONObject json = new JSONObject();
            JSONObject headerJson = new JSONObject();
            JSONArray detailJsonArray = new JSONArray();
            JSONObject detailsJson = new JSONObject();
            JSONObject detailsPartner = new JSONObject();
            JSONObject identityJson = new JSONObject();

            headerJson.put("EchoToken", ResultUniqId);
            headerJson.put("BookingReferenceID", GUID);///ID.toString()

            //mosaferan
            PassengerMosaferItems_Table items_Table = new PassengerMosaferItems_Table(PassengerHotelActivity.this);
            CursorManager cursorM = items_Table.getAllMosafer();
            if (cursorM != null) {
                for (int i = 0; i < cursorM.getCount(); i++) {

                    cursorM.moveToPosition(i);

                    detailsJson = new JSONObject();
                    detailsJson.put("Gender", cursorM.getBoolean(PassengerMosaferItems_Table.Columns.Gender.value()));
                    detailsJson.put("Nationality", cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));
                    detailsJson.put("Nationality_ID", cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));

                    detailsJson.put("RqPassenger_Address", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Address.value()));
                    detailsJson.put("RqPassenger_Birthdate", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
                    detailsJson.put("RqPassenger_Email", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Email.value()));

                    detailsJson.put("RqPassenger_FirstNameEn", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                    detailsJson.put("RqPassenger_FirstNameFa", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameFa.value()));
                    detailsJson.put("RqPassenger_LastNameEn", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));

                    detailsJson.put("RqPassenger_LastNameFa", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameFa.value()));
                    detailsJson.put("RqPassenger_Mobile", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Mobile.value()));
                    detailsJson.put("RqPassenger_NationalCode", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_NationalCode.value()));

                    detailsJson.put("RqPassenger_PassExpDate", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));
                    detailsJson.put("RqPassenger_PassNo", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));
                    detailsJson.put("RqPassenger_Tel", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Tel.value()));

                    detailJsonArray.put(detailsJson);


                }
                headerJson.put("PassList", detailJsonArray);
            }

            ////kharidar
            PassengerPartnerInfo_Table partnerInfo_Table = new PassengerPartnerInfo_Table(PassengerHotelActivity.this);
            CursorManager cursorManager = partnerInfo_Table.getPartner();
            cursorManager.moveToPosition(0);
            detailsPartner.put("RqPartner_Address", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Address.value()));
            detailsPartner.put("RqPartner_Email", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Email.value()));
            detailsPartner.put("RqPartner_FirstNameFa", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_FirstNameFa.value()));
            detailsPartner.put("RqPartner_Gender", cursorManager.getBoolean(PassengerPartnerInfo_Table.Columns.RqPartner_Gender.value()));
            detailsPartner.put("RqPartner_LastNameFa", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_LastNameFa.value()));
            detailsPartner.put("RqPartner_Mobile", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Mobile.value()));
            detailsPartner.put("RqPartner_NationalCode", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_NationalCode.value()));
            detailsPartner.put("RqPartner_Tel", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Tel.value()));

            headerJson.put("PartnerList", detailsPartner);

            headerJson.put("Culture", "fa-IR");
            headerJson.put("RequestorID ", Prefs.getString("userId","-1"));//Purchase
            headerJson.put("Type", "H");
            headerJson.put("HotelOfferId", getIntent().getExtras().getString("HotelOfferId"));
            headerJson.put("FlightGuID", getIntent().getExtras().get("FlightGuID"));
/*			headerJson.put("Checkin", getIntent().getExtras().get("Checkin"));
			headerJson.put("Checkout", getIntent().getExtras().get("Checkin"));*/
            headerJson.put("Checkin", getIntent().getExtras().getString("CheckIn"));
            headerJson.put("Checkout", getIntent().getExtras().getString("CheckOut"));


            identityJson.put("Password", "123qwe!@#QWE");
            identityJson.put("TermianlId", "Mobile");
            identityJson.put("UserName", "EligashtMlb");

            headerJson.put("identity", identityJson);

            jsone.put("request", headerJson);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsone.toString();
    }

    public String OrderToJsonGetPreFactorDetails() {
        JSONObject jsone = new JSONObject();
        JSONObject manJson = new JSONObject();
        JSONObject identityJson = new JSONObject();


        try {
            manJson.put("Culture", "fa-IR");

            manJson.put("invoiceNo", tvfactorNumber.getText().toString());//perches service
            Log.e("54", tvfactorNumber.getText().toString());
            manJson.put("Type", "H");//perches service


            identityJson.put("Password", "123qwe!@#QWE");
            identityJson.put("TermianlId", "Mobile");
            identityJson.put("UserName", "EligashtMlb");
            //identityJson.put("RequestorID ", Prefs.getString("userId","-1"));
            manJson.put("identity", identityJson);
            //manJson.put("CityCode",URLEncoder.encode(GetAirportActivity.searchText,"UTF-8"));
            jsone.put("request", manJson);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsone.toString();
    }

    public String OrderToJsonPishFactor() {
			/* public class PurchaseServiceReq
			    {
			        public string RqBaseID { get; set; } // "782528"  => شماره پیش فاکتور
			        public string ServiceStr { get; set; } // I1515:1877300|S1303:1877300|S1304:1877300     => کد سرویس2: کد مسافر1 ،کد مسافر2 کد سرویس: کد مسافر1 ،کد مسافر2 |
			        public string Exc { get; set; }
			        public string InsCoverageXML { get; set; }
			        public string InsPrcieXML { get; set; }
			        public int InsPlanCode { get; set; }
			    }*/
        JSONObject jsone = new JSONObject();
        JSONObject manJson = new JSONObject();
        JSONObject identityJson = new JSONObject();


        try {
            manJson.put("Culture", "fa-IR");

            manJson.put("RqBaseID", Prefs.getString("BookingCode_NumFactor", ""));
            manJson.put("ServiceStr", Prefs.getString("Select_ID_khadamat", ""));
            Prefs.putString("Select_ID_khadamat", "");//khali kardan field
            manJson.put("Exc", "");
            manJson.put("InsCoverageXML", "");

            manJson.put("InsPrcieXML", "");
            manJson.put("InsPlanCode", -1);

            identityJson.put("Password", "123qwe!@#QWE");
            identityJson.put("TermianlId", "Mobile");
            identityJson.put("UserName", "EligashtMlb");
           // identityJson.put("RequestorID ", Prefs.getString("userId","-1"));
            manJson.put("identity", identityJson);
            //manJson.put("CityCode",URLEncoder.encode(GetAirportActivity.searchText,"UTF-8"));
            jsone.put("request", manJson);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsone.toString();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Prefs.getBoolean("IsDemostic",true);

    }

    @Override
    public void onClick(View v) {
        Fragment fragment2;

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
                    txtTitle.setText(" افزودن خدمات به سبد خرید");
                }else if (linear_list_khadamat.getVisibility() == View.VISIBLE) {
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.VISIBLE);


                    txtTitle.setText("  اطلاعات مسافران ");
                    ((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
                    ((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
                    ////////////////////bazyabi atelaate akharin mosafer
                    PassengerMosaferItems_Table items_Table=new PassengerMosaferItems_Table(PassengerHotelActivity.this);
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
                            imgCount.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Otagh.value()));
                        }
                    }
                    counter--;

                    ///////////////////
                }else if (linear_mosaferan.getVisibility() == View.VISIBLE) {
                    ////////////////agar counter hanuzsefr nashode etelaate mosaferesho neshin bede
                   /* if(counter>1) {
                        PassengerMosaferItems_Table items_Table=new PassengerMosaferItems_Table(PassengerHotelActivity.this);
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
                                imgCount.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Otagh.value()));
                            }

                        }
                        counter--;

                    }else{*/
                        //////////////////////
                        linear_mosaferan.setVisibility(View.GONE);
                        linear_saler.setVisibility(View.VISIBLE);


                        txtTitle.setText(" مشخصات خریدار ");
                        ((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
                        ((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
                  //  }
                }else if(linear_saler.getVisibility() == View.VISIBLE) {

                    Prefs.putBoolean("BACK_HOME", true);
                    //	myScrollView.setOnTouchListener(null);
                    Intent intent = new Intent("sendFinish");

                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                }



                break;
            case R.id.btn_next_partnerInfo:

                try{
                    //jadvale mosafer khali beshe

                    PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerHotelActivity.this);
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

					/*String RqPartner_Address= "No.7,23rd St.,Khaled Eslamboli St.,Tehran,Iran";
					String RqPartner_Email= "mohebbi@eligasht.com";
					String RqPartner_FirstNameFa= "مریم";
					String RqPartner_Gender= "Female";
					String RqPartner_LastNameFa= "محبی";
					String RqPartner_Mobile= "0235884";
					String RqPartner_NationalCode= "0062532148";
					String RqPartner_Tel= "21587632";*/
                    String errorMessage="";
                    String flagMosafer="T";
                    ///Validate
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if ( RqPartner_Email.trim().matches(emailPattern) &&  RqPartner_Email.trim().length() > 0) {
                   // if( RqPartner_Email.trim().length()>6 ){
                        ((EditText)findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer=flagMosafer+"T";
                    }else{
                        //((EditText)findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#ff3300"));
                        flagMosafer=flagMosafer+"F";
                        errorMessage=errorMessage+"\n"+"لطفا ایمیل را درست وارد کنید";
                    }
                    //	if(RqPartner_FirstNameFa != null && RqPartner_FirstNameFa.length()>1){
                    //if( RqPartner_FirstNameFa.trim().length()>3 && RqPartner_FirstNameFa.trim().length()<20 && !(RqPartner_FirstNameFa.trim().matches("^[a-zA-Z]+$"))){
                    if(RqPartner_FirstNameFa != null)
                        if( RqPartner_FirstNameFa.length()>2 && !(RqPartner_FirstNameFa.toLowerCase().trim().matches("^[a-zA-Z]+$"))){
                            ((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer=flagMosafer+"T";
                        }else{
                            //((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#ff3300"));
                            flagMosafer=flagMosafer+"F";
                            errorMessage=errorMessage+"\n"+"لطفا نام را درست وارد کنید";
                        }
                    //if(RqPartner_LastNameFa != null && RqPartner_LastNameFa.length()>1){
                    if(RqPartner_LastNameFa != null)
                        if( RqPartner_LastNameFa.length()>2 && !(RqPartner_LastNameFa.toLowerCase().trim().matches("^[a-zA-Z]+$"))){
                            ((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer=flagMosafer+"T";
                        }else{
                            //((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
                            flagMosafer=flagMosafer+"F";
                            errorMessage=errorMessage+"\n"+"لطفا نام خانوادگی را درست وارد کنید";
                        }
                    if(RqPartner_Mobile != null && RqPartner_Mobile.length()==11 && RqPartner_Mobile.trim().matches("[0-9]+")){
                        ((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer=flagMosafer+"T";
                    }else{
                        //((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#ff3300"));
                        flagMosafer=flagMosafer+"F";
                        errorMessage=errorMessage+"\n"+"لطفا موبایل را درست وارد کنید";
                    }
                    if(RqPartner_NationalCode != null)
                        if( RqPartner_NationalCode.length()==10 && RqPartner_NationalCode.trim().matches("[0-9]+")){
                            ((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer=flagMosafer+"T";
                        }else{
                            //((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#ff3300"));
                            flagMosafer=flagMosafer+"F";
                            errorMessage=errorMessage+"\n"+"لطفا کد ملی را درست وارد کنید";
                        }
                    if (Gensiyat.contains("true") || Gensiyat.contains("false")){
                        flagMosafer=flagMosafer+"T";
                    }else{
                        flagMosafer=flagMosafer+"F";
                        errorMessage=errorMessage+"\n"+"لطفا جنسیت را انتخاب کنید";
                    }
                    //////////////////////////End Validate
                    if(flagMosafer.contains("F")){
                        //Toast.makeText(this,"اطلاعات ورودی نامعتبر است",2000).show();
                        //Toast.makeText(this,errorMessage,2000).show();
                        AlertDialogPassenger alertDialogPassenger =  new AlertDialogPassenger(PassengerHotelActivity.this);
                        alertDialogPassenger.setText(""+"  "+errorMessage);
                    }else{
                        //insert partner
                        PassengerPartnerInfo_Table partnerInfo_Table = new PassengerPartnerInfo_Table(PassengerHotelActivity.this);

                        partnerInfo_Table.dropTable();
                        partnerInfo_Table.openDB();




                        partnerInfo_Table.insertData(RqPartner_Address, RqPartner_Email, RqPartner_FirstNameFa, RqPartner_Gender, RqPartner_LastNameFa, RqPartner_Mobile, RqPartner_NationalCode, RqPartner_Tel);


                        partnerInfo_Table.closeDB();
                        ////////////////
                        linear_saler.setVisibility(View.GONE);
                        linear_pish_factor.setVisibility(View.GONE);
                        linear_mosaferan.setVisibility(View.VISIBLE);
                        txtTitle.setText(" اطلاعات مسافران ");
                        //((Button)findViewById(R.id.btn_saler)).setBackgroundResource(R.drawable.blue_line_with_arrow_small);
                        //((Button)findViewById(R.id.btn_saler)).setTextColor(Color.parseColor("#33ccff"));//

                        ((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);
                        ((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                        Gensiyat="";
                    }
                    setAnimation();
                }catch (Exception e) {
                    System.out.println("Exception ::"+e);
                }
                break;
            case R.id.txttavalodm:

                String RengAge=txtTitleCountM.getText().toString();

///////////////setmin
                if(RengAge.contains("کودک")){

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

                }else if(RengAge.contains("نوزاد")){

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
                    int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true)-120;
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


                datePickerDialogGregorian1.show(getFragmentManager() , "DatePickerDialogGregorianRaft");
				/*DialogFragment newFragment2 = new DatePickerFragment(txtTitleCountM.getText().toString());
				newFragment2.show(getFragmentManager(), "datePicker");*/
                flag = true;
                break;
            case  R.id.txtexp_passport:
                datePickerDialogGregorian2.show(getFragmentManager() , "DatePickerDialogGregorianRaft");
				/*DialogFragment newFragment3 = new DatePickerFragment("");
				newFragment3.show(getFragmentManager(), "datePicker");*/
                flag = false;
                break;
       /*     case R.id.txttavalodm:
                DialogFragment newFragment2 = new PassengerHotelActivity.DatePickerFragment(txtTitleCountM.getText().toString());
                newFragment2.show(getFragmentManager(), "datePicker");
                flag = true;
                break;
            case  R.id.txtexp_passport:
                DialogFragment newFragment3 = new PassengerHotelActivity.DatePickerFragment("");
                newFragment3.show(getFragmentManager(), "datePicker");
                flag = false;
                break;*/
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
                    String RqPassenger_NationalCode="";//codemeli
                    String RqPassenger_PassExpDate= txtexp_passport.getText().toString();
                    String RqPassenger_PassNo=txtnumber_passport.getText().toString();
                    String RqPassenger_Tel= "25548632";



                    String flagMosafer="T";

                    String errorMessagePartner="";
                    ///Validate

                    ///Validate
                    if( RqPassenger_PassNo.trim().length()>6 && RqPassenger_PassNo.trim().length()<10 && (RqPassenger_PassNo.trim().substring(0,1).matches("^[a-zA-Z]+$")) && RqPassenger_PassNo.trim().substring(1, RqPassenger_PassNo.length()-1).matches("[0-9]+")){
                        ((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer=flagMosafer+"T";
                    }else{
                        //((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
                        flagMosafer=flagMosafer+"F";
                        errorMessagePartner=errorMessagePartner+"\n"+"لطفا شماره پاسپورت را درست وارد کنید";
                    }
                    if(Nationality != null && Nationality.length()>1){
                        ((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer=flagMosafer+"T";
                    }else{
                        //((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#ff3300"));
                        flagMosafer=flagMosafer+"F";
                        errorMessagePartner=errorMessagePartner+"\n"+"لطفا محل اقامت را انتخاب  کنید";
                    }
                    if(Nationality_ID != null && Nationality_ID.length()>1){
                        ((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer=flagMosafer+"T";
                    }else{
                        //((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#ff3300"));
                        flagMosafer=flagMosafer+"F";
                        errorMessagePartner=errorMessagePartner+"\n"+"لطفا ملیت را انتخاب کنید";
                    }
                    if(RqPassenger_Birthdate != null && RqPassenger_Birthdate.length()>4){
                        ((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer=flagMosafer+"T";
                    }else{
                        //((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#ff3300"));
                        flagMosafer=flagMosafer+"F";
                        errorMessagePartner=errorMessagePartner+"\n"+"لطفا تاریخ تولد را انتخاب کنید";
                    }
                    ////////////////////////////////////
                    if(txtTitleCountM.getText().toString().contains("کودک")){

                    }else if(txtTitleCountM.getText().toString().contains("نوزاد")){

                    }
                    /////////////////////////////////
                    if(RqPassenger_FirstNameEn != null)
                        if( RqPassenger_FirstNameEn.length()>1 && RqPassenger_FirstNameEn.toLowerCase().trim().matches("^[a-zA-Z]+$")){
                            ((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer=flagMosafer+"T";
                        }else{
                            //((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#ff3300"));
                            flagMosafer=flagMosafer+"F";
                            errorMessagePartner=errorMessagePartner+"\n"+"لطفا نام را درست وارد کنید";
                        }
                    if(RqPassenger_LastNameEn != null)
                        if( RqPassenger_LastNameEn.length()>1 && RqPassenger_LastNameEn.toLowerCase().trim().matches("^[a-zA-Z]+$") ){
                            ((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#4d4d4d"));
                            flagMosafer=flagMosafer+"T";
                        }else{
                            //((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#ff3300"));
                            flagMosafer=flagMosafer+"F";
                            errorMessagePartner=errorMessagePartner+"\n"+"لطفا نام خانوادگی را درست وارد کنید";
                        }
                    if(RqPassenger_PassExpDate != null && RqPassenger_PassExpDate.length()>4){
                        ((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#4d4d4d"));
                        flagMosafer=flagMosafer+"T";
                    }else{
                        //((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#ff3300"));
                        flagMosafer=flagMosafer+"F";
                        errorMessagePartner=errorMessagePartner+"\n"+"لطفا انقضا پاسپورت را انتخاب کنید";
                    }
                    if (Gensiyat.contains("true") || Gensiyat.contains("false")){
                        flagMosafer=flagMosafer+"T";
                    }else{
                        flagMosafer=flagMosafer+"F";
                        errorMessagePartner=errorMessagePartner+"\n"+"لطفا جنسیت را انتخاب کنید";
                    }
                    ///endValidate


                    if(flagMosafer.contains("F")){
                        //Toast.makeText(this,"اطلاعات ورودی نامعتبر است!",2000).show();
                        AlertDialogPassenger AlertDialogPassengerFlight =  new AlertDialogPassenger(PassengerHotelActivity.this);
                        AlertDialogPassengerFlight.setText(""+"  "+errorMessagePartner);
                        //Toast.makeText(this,errorMessagePartner,2000).show();
                    }else{
                        PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerHotelActivity.this);

                        //db.dropTable();
                        db.openDB();

                        //faghat yek otagh
                        if(jsonObj != null)
                        if( jsonObj.length()==1){
                            if(sum == 0) {
                                try {
                                    if(rooms==1){

                                        countK = jsonObj.getJSONObject(room).getInt("CountK");
                                        countB = jsonObj.getJSONObject(room).getInt("CountB");
                                        countN = jsonObj.getJSONObject(room).getInt("CountN");
                                        sum = countB + countK + countN;
                                        rooms=rooms-1;
                                        room=room+1;
                                    }

                                    System.out.println("@ucountK:" + countK + "countB:" + countB + "countN:" + countN);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            if(sum>0){
                                System.out.println("gender:"+Gender);
                                //	db.insertData(counter-1,Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);
                                if(counter-1 ==1){
                                    db.insertData(counter-1,"اطلاعات مسافر اول ( بزرگسال )" ,"اتاق "+getCounter(room),Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);

                                }else{
                                    db.insertData(counter-1,txtTitleCountM.getText().toString() ,imgCount.getText().toString(),Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);
                                }
                                System.out.println("InsertMosafer:"+(counter-1)+" "+txtTitleCountM.getText().toString()+" "+RqPassenger_FirstNameEn);
                                if(countB>=1) {
                                    System.out.println("countB:"+countB);
                                    //  txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter-1)+" (بزرگسال) ");
                                    //  imgCount.setText("اتاق "+getCounter(rooms+1));
                                    countB--;
                                }else if(countK>=1) {
                                    System.out.println("countK:"+countK);
                                    // txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter-1)+" (کودک) ");
                                    // imgCount.setText("اتاق "+getCounter(rooms+1));
                                    countK--;
                                }else if(countN>=1) {
                                    System.out.println("countN:"+countN);
                                    // txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter-1)+" (نوزاد) ");
                                    //  imgCount.setText("اتاق "+getCounter(rooms+1));
                                    countN--;
                                }
                                if(countB!=0){

                                    txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter)+" (بزرگسال) ");
                                    imgCount.setText("اتاق "+getCounter(room));
                                }
                                else if(countK!=0){

                                    txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter)+" (کودک) ");
                                    imgCount.setText("اتاق "+getCounter(room));
                                }
                                else if(countN!=0){

                                    txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter)+" (نوزاد) ");
                                    imgCount.setText("اتاق "+getCounter(room));
                                }


                                System.out.println("counterMosafer:"+getCounter(counter)+counter);

                                counter++;
                                sum--;
                                ///pak kardan data haye mosafere ghabli:
                                if(sum>0){
                                    //counter--;

                                    txttavalodm.setText("");
                                    txtnamem.setText("");
                                    txtfamilym.setText("");
                                    txtexp_passport.setText("");
                                    txtnumber_passport.setText("");
                                }
                                System.out.println("insert:"+"sum:"+sum);
                            }
                        }else if (rooms>=0){

                            if(sum == 0) {
                                try {
                                    //if(rooms==1){

                                    countK = jsonObj.getJSONObject(room).getInt("CountK");
                                    countB = jsonObj.getJSONObject(room).getInt("CountB");
                                    countN = jsonObj.getJSONObject(room).getInt("CountN");
                                    sum = countB + countK + countN;
                                    rooms=rooms-1;
                                    room=room+1;
                                    //}

                                    System.out.println("@ucountK:" + countK + "countB:" + countB + "countN:" + countN);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            if(sum>0){
                                System.out.println("gender:"+Gender);
                                //	db.insertData(counter-1,Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);
                                if(counter-1 ==1){
                                    db.insertData(counter-1,"اطلاعات مسافر اول ( بزرگسال )" ,"اتاق "+getCounter(room),Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);

                                }else{
                                    db.insertData(counter-1,txtTitleCountM.getText().toString() ,imgCount.getText().toString(),Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);
                                    System.out.println("InsertMosafercou:"+(counter-1)+" "+txtTitleCountM.getText().toString()+" "+RqPassenger_FirstNameEn+" "+imgCount.getText().toString());
                                }
                                System.out.println("InsertMosafer:"+(counter-1)+" "+txtTitleCountM.getText().toString()+" "+RqPassenger_FirstNameEn);
                                if(countB>=1) {
                                    System.out.println("countB:"+countB);
                                    txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter-1)+" (بزرگسال) ");
                                    imgCount.setText("اتاق "+getCounter(room));
                                    countB--;
                                }else if(countK>=1) {
                                    System.out.println("countK:"+countK);
                                    txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter-1)+" (کودک) ");
                                    imgCount.setText("اتاق "+getCounter(room));
                                    countK--;
                                }else if(countN>=1) {
                                    System.out.println("countN:"+countN);
                                    txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter-1)+" (نوزاد) ");
                                    imgCount.setText("اتاق "+getCounter(room));
                                    countN--;
                                }
                                if(countB!=0){

                                    txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter)+" (بزرگسال) ");
                                    imgCount.setText("اتاق "+getCounter(room));
                                }
                                else if(countK!=0){

                                    txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter)+" (کودک) ");
                                    imgCount.setText("اتاق "+getCounter(room));
                                }
                                else if(countN!=0){

                                    txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter)+" (نوزاد) ");
                                    imgCount.setText("اتاق "+getCounter(room));
                                }else if(countB + countK + countN==0){

                                    if(rooms-1>=0){
                                        txtTitleCountM.setText(" اطلاعات مسافر " + getCounter(counter)+" (بزرگسال) ");
                                        imgCount.setText("اتاق "+getCounter(room+1));
                                        txttavalodm.setText("");
                                        txtnamem.setText("");
                                        txtfamilym.setText("");
                                        txtexp_passport.setText("");
                                        txtnumber_passport.setText("");
                                    }
                                }



                                System.out.println("counterMosafer:"+getCounter(counter)+counter);

                                counter++;
                                sum--;
                                ///pak kardan data haye mosafere ghabli:
                                if(sum>0){
                                    //counter--;

                                    txttavalodm.setText("");
                                    txtnamem.setText("");
                                    txtfamilym.setText("");
                                    txtexp_passport.setText("");
                                    txtnumber_passport.setText("");
                                    btnzan.setChecked(false);
                                    btnmard.setChecked(false);
                                    Gensiyat="";
                                    txtnamem.setFocusable(true);
                                }
                                System.out.println("insert:"+"sum:"+sum);
                            }
                            db.closeDB();



                            linear_mosaferan.clearFocus();
                        }


                        //call api saler
                        if(sum==0 && rooms == 0){
                            System.out.println("APICALL:"+"sum:"+sum);
                            System.out.println("insert:");
                            new PassengerHotelActivity.AsyncFetch().execute();

                        }


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
                    txtTitle.setText(" افزودن خدمات به سبد خرید");
                    setAnimation();
                }
                break;

            case R.id.btn_taeed_khadamat:


                //call api pishFactor
                new AsyncFetchPishFactor().execute();

                //call api GetPreFactorDetails

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
                ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#aaaaaa"));
                ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#aaaaaa"));
                ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#aaaaaa"));
                txtTitle.setText(" مشخصات خریدار ");
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
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#aaaaaa"));
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#aaaaaa"));
                    txtTitle.setText("اطلاعات مسافران");
                    setAnimation();
                }
                //.setOnTouchListener(null);
                break;
            case R.id.btn_khadamat:
                if(FlagTab) {
                linear_saler.setVisibility(View.GONE);
                linear_mosaferan.setVisibility(View.GONE);
                linear_list_khadamat.setVisibility(View.VISIBLE);
                linear_pish_factor.setVisibility(View.GONE);

			/*	myScrollView.setSmoothScrollingEnabled(false); // disable scrolling
				myScrollView.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						return true;
					}
				});*/
                //myScrollView.setVisibility(View.GONE);

                ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
                ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
                ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

                ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
                ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#aaaaaa"));
                txtTitle.setText(" افزودن خدمات به سبد خرید");
                    setAnimation();
                }
                break;
            case R.id.btn_pish_factor:
                if(FlagTab) {
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
                    txtTitle.setText(" تایید و پرداخت پیش فاکتور ");
                    setAnimation();
                }
                //myScrollView.setOnTouchListener(null);
                break;
            case R.id.txt_hom:
                Prefs.putBoolean("BACK_HOME", true);
                //	myScrollView.setOnTouchListener(null);
                Intent intent = new Intent("sendFinish");

                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                //this.startActivity(i4);
                break;
        }

    }
    public String getCounter(int i) {
        String s="";
        switch (i) {
            case 0:
                s= "اول";
                break;
            case 1:
                s= "اول";
                break;
            case 2:
                s= "دوم";
                break;
            case 3:
                s= "سوم";
                break;
            case 4:
                s= "چهارم";
                break;
            case 5:
                s= "پنجم";
                break;
            case 6:
                s= "ششم";
                break;
            case 7:
                s= "هفتم";
                break;
            case 8:
                s= "هشتم";
                break;
            case 9:
                s="نهم";
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
            //Toast.makeText(this, "You selected countrycode: " + countryCode, Toast.LENGTH_LONG).show();
            if (countryCode != null)
                txtmahale_eghamat.setText(countryCode + "");//txtmahale_eghamat.setText(countryCode+" "+countryName);
            if (nationalityCode != null)
                txtmeliyatm.setText(nationalityCode + "");//txtmeliyatm.setText(nationalityCode+" "+nationalityName);
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        public String  RengAge ;
        public DatePickerFragment(String RengAge) {
            this.RengAge=RengAge;
        }
        public DatePickerFragment() {
            //this.RengAge=RengAge;
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DatePickerDialog dialog = null;
            if(flag){//tavalodm
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                if(RengAge.contains("کودک")){
                    dialog = new DatePickerDialog(getActivity(), this, year-12, month, day);
                }else if(RengAge.contains("نوزاد")){
                    dialog = new DatePickerDialog(getActivity(), this, year-2, month, day);
                }else if(RengAge.contains("بزرگسال")){
                    dialog = new DatePickerDialog(getActivity(), this, year-30, month, day);
                }
                // dialog.getDatePicker().setMinDate(c.getTimeInMillis());
///////////////setmin
                if(RengAge.contains("کودک")){
                    System.out.println("koodak");
                    //c = Calendar.getInstance();
                    c.add(Calendar.YEAR, -12); // subtract 2 years from now
                    dialog.getDatePicker().setMinDate(c.getTimeInMillis());
                    c.add(Calendar.YEAR, 10); // add 4 years to min date to have 2 years after now
                    dialog.getDatePicker().setMaxDate(c.getTimeInMillis());

                }else if(RengAge.contains("نوزاد")){
                    System.out.println("Nozad");
                    //c = Calendar.getInstance();
                    c.add(Calendar.YEAR, -2); // subtract 2 years from now
                    dialog.getDatePicker().setMinDate(c.getTimeInMillis());
                    c.add(Calendar.YEAR, 2); // add 4 years to min date to have 2 years after now
                    dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                }else{
                    c.add(Calendar.YEAR, -120);
                    dialog.getDatePicker().setMinDate(c.getTimeInMillis());
                    c.add(Calendar.YEAR, 108);
                    dialog.getDatePicker().setMaxDate(c.getTimeInMillis());}
                ///////end setMin
            }else{//expPasport
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                dialog = new DatePickerDialog(getActivity(), this, year, month+6, day);//1997/12/23

                c.add(Calendar.MONTH, 6);
                dialog.getDatePicker().setMinDate(c.getTimeInMillis());
                c.add(Calendar.YEAR, 6);
                dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                //dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
		 	   /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		 	    Date mDate;*/
                // dialog.getDatePicker().setMinDate(c.getTimeInMillis());
            }

            return  dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String sMonth=String.valueOf(month+1);
            String sDay=String.valueOf(day);
            if(sMonth.length()==1)
                sMonth="0"+sMonth;

            if(sDay.length()==1)
                sDay = "0"+sDay;
            if(flag){
                txttavalodm.setText(year+"/" +sMonth +"/"+ sDay);
            }else{

                txtexp_passport.setText(year+"/" + sMonth +"/" + sDay);
            }



        }
    }//endDatepicker
    @Override
    public void onBackPressed() {

        if (linear_pish_factor.getVisibility() == View.VISIBLE) {
            linear_pish_factor.setVisibility(View.GONE);
            linear_list_khadamat.setVisibility(View.VISIBLE);

            ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
            ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
            txtTitle.setText(" افزودن خدمات به سبد خرید");
        } else if (linear_list_khadamat.getVisibility() == View.VISIBLE) {
            linear_list_khadamat.setVisibility(View.GONE);
            linear_mosaferan.setVisibility(View.VISIBLE);


            txtTitle.setText("  اطلاعات مسافران ");
            ((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
            ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
            ////////////////////bazyabi atelaate akharin mosafer
            PassengerMosaferItems_Table items_Table = new PassengerMosaferItems_Table(PassengerHotelActivity.this);
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
                    //txtTitleCountM.setText(" اطلاعات مسافر " + counter);
                    System.out.println("InsertMosaferGet:" + cursorM.getString(PassengerMosaferItems_Table.Columns.ID.value()) + " " + cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()) + " " + cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                }
            }
            counter--;

            // imgCount.setText(counter + "");
            ///////////////////
        } else if (linear_mosaferan.getVisibility() == View.VISIBLE) {
            ////////////////agar counter hanuzsefr nashode etelaate mosaferesho neshin bede
          /*  if (counter > 1) {
                PassengerMosaferItems_Table items_Table = new PassengerMosaferItems_Table(PassengerHotelActivity.this);
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
                        System.out.println("InsertMosaferGet:" + cursorM.getString(PassengerMosaferItems_Table.Columns.ID.value()) + " " + cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()) + " " + cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));

                    }

                }
                counter--;

            } else {*/
                //////////////////////
                linear_mosaferan.setVisibility(View.GONE);
                linear_saler.setVisibility(View.VISIBLE);


                txtTitle.setText(" مشخصات خریدار ");
                ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
                ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
           // }
        } else if (linear_saler.getVisibility() == View.VISIBLE) {

            Prefs.putBoolean("BACK_HOME", true);
            //	myScrollView.setOnTouchListener(null);
            Intent intent = new Intent("sendFinish");

            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }
    @Override
    public void searchTextChanged(String searchText) {
			/*this.searchText = searchText;
			if(searchText.length()>2)
			new AsyncFetch().execute();*/
        //mAdapter.setData(searchText);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if(item.contains("زن")) {
            Gensiyat = "false";
        }else if(item.contains("مرد")){
            Gensiyat="true";
        }
        // Showing selected spinner item
        //	Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onResume(){
        super.onResume();
        long gheymatKh=Prefs.getLong("Tprice",0);
        System.out.println("wwwTPRICE:"+gheymatKh);
        mAdapter = new GetHotelKhadmatAdapter(PassengerHotelActivity.this, data, PassengerHotelActivity.this,gheymatKh);
        mAdapter.setData(data);
        listKhadamat.setAdapter(mAdapter);

    }
    public static void updateTotalInfos(long serviceTotalPrice) {
        // TODO Auto-generated method stub
        //GET_PRICE_KHADAMAT=GET_PRICE_KHADAMAT+serviceTotalPrice;
        //txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(GET_PRICE_KHADAMAT))+"");
        txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(serviceTotalPrice))+"");
		/*for (int i =0 ;i<data.size();i++){
			if(data.get(i).isFlag()){
				GET_PRICE_KHADAMAT=GET_PRICE_KHADAMAT+data.get(i).getServiceTotalPrice();
			}

		}
		txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(GET_PRICE_KHADAMAT))+"");*/
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
/*
            switch (view.getId()) {
                //مسافر
                case R.id.txtmahale_eghamat:
                    if (text != null && text.length() > 1) {
                        ((TextView) findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#aaaaaa"));
                        //flagMosafer=flagMosafer+"T";
                    } else {
                        ((TextView) findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#ff3300"));
                        txtmahale_eghamat.setError("لطفا محل اقامت را وارد کنید ");
                    }
                    break;
                case R.id.txtmeliyatm:
                    if (text != null && text.length() > 1) {
                        ((TextView) findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#aaaaaa"));
                        //flagMosafer=flagMosafer+"T";
                    } else {
                        ((TextView) findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#ff3300"));
                        txtmeliyatm.setError("لطفا ملیت را وارد کنید ");
                    }
                    break;
                case R.id.txttavalodm:
                    if (text != null && text.length() > 4) {
                        ((TextView) findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#aaaaaa"));
                        //flagMosafer=flagMosafer+"T";
                    } else {
                        ((TextView) findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#ff3300"));
                        txttavalodm.setError("لطفا تاریخ تولد را وارد کنید ");
                    }
                    break;

                case R.id.txtnamem:
                    if (text != null)
                        if (text.length() > 1 && text.toLowerCase().trim().matches("^[a-zA-Z]+$")) {
                            ((EditText) findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#aaaaaa"));
                            //flagMosafer=flagMosafer+"T";
                        } else {
                            ((EditText) findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#ff3300"));
                            txtnamem.setError("لطفا نام را انگلیسی وارد کنید ");
                        }
                    break;
                case R.id.txtfamilym:
                    if (text != null)
                        if (text.length() > 1 && text.toLowerCase().trim().matches("^[a-zA-Z]+$")) {
                            ((EditText) findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#aaaaaa"));
                            //flagMosafer=flagMosafer+"T";
                        } else {
                            ((EditText) findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#ff3300"));
                            txtfamilym.setError("لطفا نام خانوادگی را انگلیسی وارد کنید ");
                        }
                    break;
                case R.id.txtexp_passport:
                    if (text != null && text.length() > 4) {
                        ((TextView) findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#aaaaaa"));

                    } else {
                        ((TextView) findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#ff3300"));
                        txtexp_passport.setError("لطفا انقضاء پاسپورت را وارد کنید ");
                    }
                    break;
                case R.id.txtnumber_passport:

                    if (text.trim().length() > 6 && text.trim().length() < 10 && (text.trim().substring(0, 1).matches("^[a-zA-Z]+$")) && text.trim().substring(1, text.length() - 1).matches("[0-9]+")) {
                        ((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#aaaaaa"));

                    } else {
                        ((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
                        txtnumber_passport.setError("لطفا شماره پاسپورت را صحیح وارد کنید ");
                    }
                    if (text != null && text.length() > 4) {
                    } else {
                        ((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
                        txtnumber_passport.setError("لطفا شماره پاسپورت را وارد کنید ");
                    }

                    break;

                //مشخصات خریدار
                case R.id.txtemeliP:
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (text.matches(emailPattern) && text.length() > 0) {
                        //if( Patterns.EMAIL_ADDRESS.matcher(text).matches() ){
                        ((EditText) findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#aaaaaa"));

                    } else {
                        ((EditText) findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#ff3300"));
                        txtemeliP.setError("لطفا ایمیل را وارد کنید ");
                    }

                    break;
                case R.id.txtnameP:

                    if (text != null && text.length() > 1) {
                        ((EditText) findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#aaaaaa"));

                    } else {
                        ((EditText) findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#ff3300"));
                        txtnameP.setError("لطفا نام را فارسی وارد کنید ");
                    }
                    break;
                case R.id.txtfamilyP:

                    if (text != null && text.length() > 1) {
                        ((EditText) findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#aaaaaa"));

                    } else {
                        ((EditText) findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
                        txtfamilyP.setError("لطفا نام خانوادگی را فارسی وارد کنید ");
                    }
                    break;

                case R.id.txtmobileP:

                    if (text != null && text.length() > 9 && text.trim().matches("[0-9]+")) {
                        ((EditText) findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#aaaaaa"));

                    } else {
                        ((EditText) findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#ff3300"));
                        txtmobileP.setError("لطفا شماره موبایل را وارد کنید ");
                    }
                    break;
                case R.id.txtkodemeliP:
                    if (text != null)
                        if (text.length() > 9 && text.length() < 12 && text.trim().matches("[0-9]+")) {
                            ((EditText) findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#aaaaaa"));

                        } else {
                            ((EditText) findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#ff3300"));
                            txtkodemeliP.setError("لطفا کد ملی را وارد کنید ");
                        }
                    break;

            }*/
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

    public boolean ErrorInApi(JSONObject jsonObj) throws JSONException {

        JSONObject GetAirportsResult = jsonObj.getJSONObject("GetPreFactorDetailsResult");


        if (GetAirportsResult.getJSONArray("Errors") != null) {
            JSONArray errors = GetAirportsResult.getJSONArray("Errors");
            Toast.makeText(PassengerHotelActivity.this, errors.getJSONObject(0).getString("DetailedMessage"), Toast.LENGTH_SHORT).show();

            return true;


        }


        return false;
    }

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
                        txtmahale_eghamat.setError("لطفا محل اقامت را وارد کنید ");
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
                        txtmeliyatm.setError("لطفا ملیت را وارد کنید ");
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
                        txttavalodm.setError("لطفا تاریخ تولد را وارد کنید ");
                    }
                }
                break;

            case R.id.txtnamem:
                if(hasFocus){
                    System.out.println("t");
                }else{
                    System.out.println("f");
                    if(txtnamem.getText().toString() != null)
                        if( txtnamem.getText().toString().length()>1 && txtnamem.getText().toString().toLowerCase().trim().matches("^[a-zA-Z]+$")){
                            ((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#4d4d4d"));
                            //flagMosafer=flagMosafer+"T";
                        }else{
                            //((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#ff3300"));
                            txtnamem.setError("لطفا نام را درست وارد کنید ");
                        }
                }
                break;
            case R.id.txtfamilym:
                if(hasFocus){
                    System.out.println("t");
                }else{
                    System.out.println("f");
                    if(txtfamilym.getText().toString() != null)
                        if( txtfamilym.getText().toString().length()>1 && txtfamilym.getText().toString().toLowerCase().trim().matches("^[a-zA-Z]+$") ){
                            ((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#4d4d4d"));
                            //flagMosafer=flagMosafer+"T";
                        }else{
                            //((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#ff3300"));
                            txtfamilym.setError("لطفا نام خانوادگی را درست وارد کنید ");
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
                        txtexp_passport.setError("لطفا انقضاء پاسپورت را وارد کنید ");
                    }}
                break;
            case R.id.txtnumber_passport:
                if(hasFocus){
                    System.out.println("t");
                }else {
                    System.out.println("f");
                    if (txtnumber_passport.getText().toString().trim().length() > 6 && txtnumber_passport.getText().toString().trim().length() < 10 && (txtnumber_passport.getText().toString().trim().substring(0, 1).matches("^[a-zA-Z]+$")) && txtnumber_passport.getText().toString().trim().substring(1, txtnumber_passport.getText().toString().length() - 1).matches("[0-9]+")) {
                        ((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {
                        //((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
                        txtnumber_passport.setError("لطفا شماره پاسپورت را صحیح وارد کنید ");
                    }
                    if (txtmeliyatm.getText().toString() != null && txtmeliyatm.getText().toString().length() > 4) {
                    } else {
                        //((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
                        txtnumber_passport.setError("لطفا شماره پاسپورت را وارد کنید ");
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
                        txtemeliP.setError("لطفا ایمیل را وارد کنید ");
                    }
                }
                break;
            case R.id.txtnameP:
                if(hasFocus){
                    System.out.println("t");
                }else{
                    System.out.println("f");
                    if(txtnameP.getText().toString() != null)
                        if( txtnameP.getText().toString().length()>2 && !(txtnameP.getText().toString().toLowerCase().trim().matches("^[a-zA-Z]+$"))){
                            ((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));

                        }else{
                            //((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#ff3300"));
                            txtnameP.setError("لطفا نام را به فارسی وارد کنید ");
                        }
                }
                break;
            case R.id.txtfamilyP:
                if(hasFocus){
                    System.out.println("t");
                }else{
                    System.out.println("f");
                    if(txtfamilyP.getText().toString() != null)
                        if( txtfamilyP.getText().toString().length()>2 && !(txtfamilyP.getText().toString().toLowerCase().trim().matches("^[a-zA-Z]+$"))){
                            ((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));

                        }else{
                            //((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
                            txtfamilyP.setError("لطفا نام خانوادگی را به فارسی وارد کنید ");
                        }
                }
                break;

            case R.id.txtmobileP:
                if(hasFocus){
                    System.out.println("t");
                }else{
                    System.out.println("f");
                    if(txtmobileP.getText().toString() != null && txtmobileP.getText().toString().length()==11 && txtmobileP.getText().toString().trim().matches("[0-9]+")){
                        ((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#4d4d4d"));

                    }else{
                        //((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#ff3300"));
                        txtmobileP.setError("لطفا شماره موبایل را وارد کنید ");
                    }}
                break;
            case R.id.txtkodemeliP:
                if(hasFocus){
                    System.out.println("t");
                }else{
                    System.out.println("f");
                    if(txtkodemeliP.getText().toString() != null)
                        if( txtkodemeliP.getText().toString().length()==10 &&  txtkodemeliP.getText().toString().trim().matches("[0-9]+")){
                            ((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#4d4d4d"));

                        }else{
                            //((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#ff3300"));
                            txtkodemeliP.setError("لطفا کد ملی را وارد کنید ");
                        }
                }
                break;
        }

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
            //raft = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
            //	Log.e("GGGGGGG", raft);

        } catch (ParseException e) {
            e.printStackTrace();
        }




    }//end dateset change
    @Override
    public void onDateSet(com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {

	/*	String str_date = year + "/" + (monthOfYear + 1) + "/" + (dayOfMonth-1);//2018-01-16
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
			//raft = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
			//Log.e("GGGGGGG", raft);

		} catch (ParseException e) {
			e.printStackTrace();
		}*/
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