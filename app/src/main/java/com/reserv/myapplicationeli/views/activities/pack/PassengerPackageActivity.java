package com.reserv.myapplicationeli.views.activities.pack;

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

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.lost.flight.FlightPreFactorAdapter;
import com.reserv.myapplicationeli.lost.flight.FlightPreFactorModel;
import com.reserv.myapplicationeli.lost.hotel.HotelPreFactorAdapter;
import com.reserv.myapplicationeli.lost.hotel.HotelPreFactorModel;
import com.reserv.myapplicationeli.lost.passenger.PassangerPreFactorAdapter;
import com.reserv.myapplicationeli.lost.passenger.PassengerPreFactorModel;
import com.reserv.myapplicationeli.lost.service.ServicePreFactorAdapter;
import com.reserv.myapplicationeli.lost.service.ServicePreFactorModel;
import com.reserv.myapplicationeli.models.model.ModelRowCountRoom;
import com.reserv.myapplicationeli.models.model.PurchaseFlightResult;
import com.reserv.myapplicationeli.models.model.pack.PackageRoomNoToRequest;
import com.reserv.myapplicationeli.models.model.pack.PartnerList;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.WebUserTools;
import com.reserv.myapplicationeli.tools.db.local.PassengerMosaferItems_Table;
import com.reserv.myapplicationeli.tools.db.local.PassengerPartnerInfo_Table;
import com.reserv.myapplicationeli.tools.db.main.CursorManager;
import com.reserv.myapplicationeli.views.activities.insurance.PassengerInsuranceActivity;
import com.reserv.myapplicationeli.views.activities.main.MainActivity;
import com.reserv.myapplicationeli.views.adapters.GetHotelKhadmatAdapter;
import com.reserv.myapplicationeli.views.adapters.GetKhadmatAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView;
import com.reserv.myapplicationeli.views.components.Header;
import com.reserv.myapplicationeli.views.ui.CountrycodeActivity;
import com.reserv.myapplicationeli.views.ui.NationalitycodeActivity;
import com.reserv.myapplicationeli.views.activities.pack.PassengerPackageActivity;
import com.reserv.myapplicationeli.views.ui.PassengerActivity;
import com.reserv.myapplicationeli.views.ui.PassengerHotelActivity;
import com.reserv.myapplicationeli.views.ui.PassengerHotelFlightActivity;
import com.reserv.myapplicationeli.views.ui.SearchParvazActivity;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.AlertDialogPassenger;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.AlertDialogPassengerFlight;

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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mehdi.sakout.fancybuttons.FancyButton;

public class PassengerPackageActivity extends BaseActivity implements Header.onSearchTextChangedListener, OnClickListener, OnItemSelectedListener,View.OnFocusChangeListener  {

    public static boolean flag;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private Handler handler;
    private ProgressDialog progressBar;
    public FancyButton btnBack;
    public ImageView txt_hom ;
    public TextView txtfamilyP, txtkodemeliP, txtemeliP, txtmobileP, txtMore, tvfactorNumber;
    public ImageView btn_saler, btn_mosaferan, btn_khadamat, btn_pish_factor;
    public Button btnAddsabad, btn_pardakht_factor;
    public EditText txtnamem, txtfamilym,txt_NationalCode_m;
    public static TextView txttavalodm;
    public EditText txtnumber_passport, txtnameP;
    public static TextView txtexp_passport;
    public TextView txtTitle, txtmeliyatm, txtmahale_eghamat, txtTitleCountM;
    public static TextView txtSumKhadamat;
    public LinearLayout linear_number_passport,linear_code_meli,btn_taeed_khadamat, btn_nextm, linear_saler, linear_mosaferan, linear_list_khadamat, linear_pish_factor, linearMahaleeghamat, linearMeliyat, btn_next_partnerInfo;
    private Handler progressBarHandler = new Handler();
    public ListView list_airport;
    public ListView listKhadamat;
    private ArrayList<HashMap<String, String>> mylist = null;
    public static String searchText = "";
    public static long GET_PRICE_KHADAMAT;
    private ScrollView myScrollView;
    private ExpandableRelativeLayout expandableLayout;
    private ArrayList<PackageRoomNoToRequest> packageRoomNoToRequestList;
    public TextView imgCount;
    private String paymentUrl;
    public List<PurchaseFlightResult> data;
    private GetHotelKhadmatAdapter mAdapter;
    private EditText searchtxt;
    public TextView txt_shomare_factor, tvPrice;
    public ImageView textView4;
    private String Gensiyat="";
    public int countB;
    public int countK;
    public int countN;

    Activity activity;
    private LinearLayout llDetailHotel, llDetailPassanger, llDetailService, llDetailFlight;
    private PassengerMosaferItems_Table passengerMosaferItemsTable;
    List<String> alRoom;
    private boolean FlagTab=false;
    private boolean FlagMosaferan=true;
    private com.rey.material.widget.RadioButton btnzan,btnmard,btnzanS,btnmardS;
    RelativeLayout rlLoading,rlRoot;

    public JSONArray jsonObj = null;
    public int sum=0;
    int counter = 2;
    int rooms=0;
    int room=0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_pack);

        initViews();
        setupGenderSpinner();

        Bundle bundle = getIntent().getExtras();
        Gson gson = new GsonBuilder().create();
        if (bundle != null) {
            packageRoomNoToRequestList = gson.fromJson(bundle.getString("PackageRoomNoToRequest"), new TypeToken<List<PackageRoomNoToRequest>>() {
            }.getType());
        }


        alRoom = new ArrayList<>();


        if (!ValidationTools.isEmptyOrNull(packageRoomNoToRequestList)) {
            for (PackageRoomNoToRequest packageRoomNoToRequest : packageRoomNoToRequestList) {
                alRoom.add(packageRoomNoToRequest.getRoom_No()+"");

            }
        }
        Set<String> hs = new HashSet<>();
        hs.addAll(alRoom);
        alRoom.clear();
        alRoom.addAll(hs);

        rooms=alRoom.size();
        imgCount = (TextView) findViewById(R.id.imgCount);
        imgCount.setText("اتاق "+getCounter(room));
        imgCount.setOnClickListener(this);
    }

    private void initViews() {
        rlLoading = findViewById(R.id.rlLoading);
        rlRoot = findViewById(R.id.rlRoot);
        btnBack = (FancyButton) findViewById(R.id.btnBack);
        txt_hom = (ImageView) findViewById(R.id.txt_hom);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setVisibility(View.VISIBLE);

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

        textView4 = (ImageView) findViewById(R.id.textView4);
        tvfactorNumber = (TextView) findViewById(R.id.tvfactorNumber);
        expandableLayout = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout);
        txtMore = (TextView) findViewById(R.id.txtMore);
        txtSumKhadamat = (TextView) findViewById(R.id.txtSumKhadamat);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(GET_PRICE_KHADAMAT)));
        txttavalodm = (TextView) findViewById(R.id.txttavalodm);
        txtnamem = (EditText) findViewById(R.id.txtnamem);
       // imgCount = (TextView) findViewById(R.id.imgCount);
        txtfamilym = (EditText) findViewById(R.id.txtfamilym);
        txtnumber_passport = (EditText) findViewById(R.id.txtnumber_passport);
        txtexp_passport = (TextView) findViewById(R.id.txtexp_passport);
        txtTitle = (TextView) findViewById(R.id.tvTitle);
        txtTitleCountM = (TextView) findViewById(R.id.txtTitleCountM);
        btn_next_partnerInfo = (LinearLayout) findViewById(R.id.btn_next_partnerInfo);
        btn_nextm = (LinearLayout) findViewById(R.id.btn_nextm);
        btn_taeed_khadamat = (LinearLayout) findViewById(R.id.btn_taeed_khadamat);
        btn_pardakht_factor = (Button) findViewById(R.id.btn_pardakht_factor);
        btn_saler = (ImageView) findViewById(R.id.btn_saler);
        btn_mosaferan = (ImageView) findViewById(R.id.btn_mosaferan);
        btn_pish_factor = (ImageView) findViewById(R.id.btn_pish_factor);
        linear_saler = (LinearLayout) findViewById(R.id.linear_saler);
        linear_mosaferan = (LinearLayout) findViewById(R.id.linear_mosaferan);
        linear_pish_factor = (LinearLayout) findViewById(R.id.linear_pish_factor);
        linearMahaleeghamat = (LinearLayout) findViewById(R.id.linearMahaleeghamat);
        linearMeliyat = (LinearLayout) findViewById(R.id.linearMeliyat);
        txtnameP = (EditText) findViewById(R.id.txtnameP);
        txtfamilyP = (EditText) findViewById(R.id.txtfamilyP);

        txt_NationalCode_m= (EditText) findViewById(R.id.txt_NationalCode_m);
        txt_NationalCode_m.setOnClickListener(this);
        txt_NationalCode_m.setImeOptions(EditorInfo.IME_ACTION_DONE);
        txt_NationalCode_m.addTextChangedListener(new GenericTextWatcher(txt_NationalCode_m));
        txt_NationalCode_m.setOnFocusChangeListener(this);

        txtmobileP = (EditText) findViewById(R.id.txtmobileP);
        txtkodemeliP = (EditText) findViewById(R.id.txtkodemeliP);
        txtemeliP = (EditText) findViewById(R.id.txtemeliP);
        txtmeliyatm = (TextView) findViewById(R.id.txtmeliyatm);
        txtmahale_eghamat = (TextView) findViewById(R.id.txtmahale_eghamat);
        txt_shomare_factor = (TextView) findViewById(R.id.txt_shomare_factor);
        linear_list_khadamat = (LinearLayout) findViewById(R.id.linear_list_khadamat);
        listKhadamat = (ListView) findViewById(R.id.listKhadamat);
        llDetailHotel = (LinearLayout) findViewById(R.id.llDetailHotel);
        llDetailPassanger = (LinearLayout) findViewById(R.id.llDetailPassanger);
        llDetailService = (LinearLayout) findViewById(R.id.llDetailService);
        llDetailFlight = (LinearLayout) findViewById(R.id.llDetailFlight);


        btn_saler.setOnClickListener(this);
        btn_mosaferan.setOnClickListener(this);
        btn_pish_factor.setOnClickListener(this);

        btn_pardakht_factor.setOnClickListener(this);
        btn_taeed_khadamat.setOnClickListener(this);
        btn_nextm.setOnClickListener(this);
        btn_next_partnerInfo.setOnClickListener(this);
        txtTitleCountM.setOnClickListener(this);
        txtTitle.setOnClickListener(this);
        txtexp_passport.setOnClickListener(this);
        txtnumber_passport.setOnClickListener(this);
        txtnumber_passport.setImeOptions(EditorInfo.IME_ACTION_DONE);

        txt_NationalCode_m= (EditText) findViewById(R.id.txt_NationalCode_m);
        txt_NationalCode_m.setOnClickListener(this);
        txt_NationalCode_m.setImeOptions(EditorInfo.IME_ACTION_DONE);
        txt_NationalCode_m.addTextChangedListener(new GenericTextWatcher(txt_NationalCode_m));
        txt_NationalCode_m.setOnFocusChangeListener(this);

        txtfamilym.setOnClickListener(this);
       // imgCount.setOnClickListener(this);
        txtnamem.setOnClickListener(this);
        txttavalodm.setOnClickListener(this);
        txtSumKhadamat.setOnClickListener(this);
        txtMore.setOnClickListener(this);
        txtmahale_eghamat.setOnClickListener(this);
        txtmeliyatm.setOnClickListener(this);
        txt_shomare_factor.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        txt_hom.setOnClickListener(this);

      //  txtfamilym.setOnFocusChangeListener(this);
        txtnameP.setOnFocusChangeListener(this);
        txtfamilyP.setOnFocusChangeListener(this);
        txtmobileP.setOnFocusChangeListener(this);
        txtkodemeliP.setOnFocusChangeListener(this);
        txtemeliP.setOnFocusChangeListener(this);
        txtfamilym.setOnFocusChangeListener(this);
        txtnumber_passport.setOnFocusChangeListener(this);
        txtnamem.setOnFocusChangeListener(this);

        passengerMosaferItemsTable = new PassengerMosaferItems_Table(PassengerPackageActivity.this);
        passengerMosaferItemsTable.dropTable();
        passengerMosaferItemsTable.openDB();
       // passengerMosaferItemsTable.removeAllRecord();

        btn_pardakht_factor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.putString("TypeGetPre", "P");

                Utility.openUrlCustomTab(PassengerPackageActivity.this, paymentUrl);
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
        }catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        //////////////
    }

    private void setupGenderSpinner() {

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        Spinner spinnerMosafer = (Spinner) findViewById(R.id.spinnerMosafer);

        spinner.setOnItemSelectedListener(this);
        spinnerMosafer.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("لطفا جنسیت را انتخاب کنید");
        categories.add("مرد");
        categories.add("زن");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinnerMosafer.setAdapter(dataAdapter);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.txt_hom:
                Prefs.putBoolean("BACK_HOME",true);
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
                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
                    txtTitle.setText("  اطلاعات مسافران ");
                    ////////////////////bazyabi atelaate akharin mosafer
                    PassengerMosaferItems_Table items_Table=new PassengerMosaferItems_Table(PassengerPackageActivity.this);
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
                            imgCount.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Otagh.value()));
                            txtTitleCountM.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()));
                        }
                    }
                    counter--;
                    //xtTitleCountM.setText(" اطلاعات مسافر " + counter);
                    //  imgCount.setText(counter+"");
                    ///////////////////
                } else if (linear_mosaferan.getVisibility() == View.VISIBLE) {
                    ////////////////agar counter hanuzsefr nashode etelaate mosaferesho neshin bede
                    if(counter>1) {
                        PassengerMosaferItems_Table items_Table=new PassengerMosaferItems_Table(PassengerPackageActivity.this);
                        CursorManager cursorM=items_Table.getMosaferById(counter-1);
                        if(cursorM != null){
                            for (int i = 0; i < cursorM.getCount(); i++){

                                txtnamem.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                                txtfamilym.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));
                                txtnumber_passport.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));

                                txttavalodm.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
                                txtexp_passport.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));

                                txtmahale_eghamat.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));
                                txtmeliyatm.setText( cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));
                                imgCount.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Otagh.value()));
                                txtTitleCountM.setText(cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value()));
                            }

                        }
                        counter--;
                        //txtTitleCountM.setText(" اطلاعات مسافر " + counter);
                        // imgCount.setText(counter+"");
                    }else{
                        //////////////////////
                        linear_mosaferan.setVisibility(View.GONE);
                        linear_saler.setVisibility(View.VISIBLE);


                        txtTitle.setText(" مشخصات خریدار ");
                        ((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
                        ((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
                    }
                } else if (linear_saler.getVisibility() == View.VISIBLE) {
                    finish();
                }
                break;
            case R.id.btn_next_partnerInfo:

                try{
                    //jadvale mosafer khali beshe

                    PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerPackageActivity.this);
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
                    if( RqPartner_Email.trim().length()>6 ){
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
                        AlertDialogPassenger alertDialogPassenger =  new AlertDialogPassenger(PassengerPackageActivity.this);
                        alertDialogPassenger.setText(""+"  "+errorMessage);
                    }else{
                        //insert partner
                        PassengerPartnerInfo_Table partnerInfo_Table = new PassengerPartnerInfo_Table(PassengerPackageActivity.this);

                        partnerInfo_Table.dropTable();
                        partnerInfo_Table.openDB();




                        partnerInfo_Table.insertData(RqPartner_Address, RqPartner_Email, RqPartner_FirstNameFa, RqPartner_Gender, RqPartner_LastNameFa, RqPartner_Mobile, RqPartner_NationalCode, RqPartner_Tel);


                        partnerInfo_Table.closeDB();
                        ////////////////
                        linear_saler.setVisibility(View.GONE);
                        linear_pish_factor.setVisibility(View.GONE);
                        linear_mosaferan.setVisibility(View.VISIBLE);
                        txtTitle.setText(" اطلاعات مسافران ");


                        ((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);
                        ((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                    }
                    Gensiyat="";
                }catch (Exception e) {
                    System.out.println("Exception ::"+e);
                }
                break;
            case R.id.txttavalodm:
                DialogFragment newFragment2 = new DatePickerFragment(txtTitleCountM.getText().toString()+"");
                newFragment2.show(getFragmentManager(), "datePicker");
                flag = true;
                break;
            case  R.id.txtexp_passport:
                DialogFragment newFragment3 = new DatePickerFragment("");
                newFragment3.show(getFragmentManager(), "datePicker");
                flag = false;
                break;
            case R.id.btn_nextm:
                LinearLayout mainLayout;
                mainLayout = (LinearLayout)findViewById(R.id.linear_list_khadamat);

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);
                /////////////////////////
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
                            errorMessagePartner=errorMessagePartner+"\n"+"لطفا کد ملی را درست وارد کنید";
                        }
                    }
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
                        AlertDialogPassenger AlertDialogPassengerFlight =  new AlertDialogPassenger(PassengerPackageActivity.this);
                        AlertDialogPassengerFlight.setText(""+"  "+errorMessagePartner);
                        //Toast.makeText(this,errorMessagePartner,2000).show();
                    }else{
                        PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerPackageActivity.this);

                        //db.dropTable();
                        db.openDB();

                        //faghat yek otagh
                        if( alRoom.size()==1){
                            if(sum == 0) {

                                    if(rooms==1){

                                        if (!ValidationTools.isEmptyOrNull(packageRoomNoToRequestList)) {
                                            // for(int i=0 ; i < alRoom.size(); i++){
                                            for (PackageRoomNoToRequest packageRoomNoToRequest : packageRoomNoToRequestList) {

                                                if (packageRoomNoToRequest.getPassengerType().equals("adl") && packageRoomNoToRequest.getRoom_No()==Integer.parseInt(alRoom.get(room))) {
                                                    countB += 1;
                                                }

                                                if (packageRoomNoToRequest.getPassengerType().equals("chl")&& packageRoomNoToRequest.getRoom_No()==Integer.parseInt(alRoom.get(room))) {
                                                    countK += 1;
                                                }

                                                if (packageRoomNoToRequest.getPassengerType().equals("inf")&& packageRoomNoToRequest.getRoom_No()==Integer.parseInt(alRoom.get(room))) {
                                                    countN += 1;
                                                }
                                            }
                                            //}
                                        }
                                        sum = countB + countK + countN;
                                        rooms=rooms-1;
                                        room=room+1;
                                    }

                                    System.out.println("@ucountK:" + countK + "countB:" + countB + "countN:" + countN);




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

                                    //if(rooms==1){

                                    if (!ValidationTools.isEmptyOrNull(packageRoomNoToRequestList)) {
                                        // for(int i=0 ; i < alRoom.size(); i++){
                                        for (PackageRoomNoToRequest packageRoomNoToRequest : packageRoomNoToRequestList) {

                                            if (packageRoomNoToRequest.getPassengerType().equals("adl") && packageRoomNoToRequest.getRoom_No()==Integer.parseInt(alRoom.get(room))) {
                                                countB += 1;
                                            }

                                            if (packageRoomNoToRequest.getPassengerType().equals("chl")&& packageRoomNoToRequest.getRoom_No()==Integer.parseInt(alRoom.get(room ))) {
                                                countK += 1;
                                            }

                                            if (packageRoomNoToRequest.getPassengerType().equals("inf")&& packageRoomNoToRequest.getRoom_No()==Integer.parseInt(alRoom.get(room ))) {
                                                countN += 1;
                                            }
                                        }
                                        //}
                                    }
                                    sum = countB + countK + countN;
                                    rooms=rooms-1;
                                room=room+1;
                                    //}

                                    System.out.println("@ucountK:" + countK + "countB:" + countB + "countN:" + countN);



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
                                }
                                System.out.println("insert:"+"sum:"+sum);
                            }
                            db.closeDB();
                            //insert mosafer
                        }/*else if(){

                            }*/





                    }


                    //call api saler
                    if(sum==0 && rooms == 0){
                        System.out.println("APICALL:"+"sum:"+sum);
                        System.out.println("insert:");
                        new AsyncFetch().execute();

                    }

                }
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

                ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.khadamat_passenger_off);
                ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
                ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
                ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
                ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
                txtTitle.setText(" مشخصات خریدار ");
                }
                break;
            case R.id.btn_mosaferan:
                if(FlagTab) {
                linear_saler.setVisibility(View.GONE);
                linear_mosaferan.setVisibility(View.VISIBLE);
                linear_list_khadamat.setVisibility(View.GONE);
                linear_pish_factor.setVisibility(View.GONE);

                ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.khadamat_passenger_off);
                ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

                ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#4d4d4d"));
                ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));
                txtTitle.setText("  اطلاعات مسافران ");
                }
                break;
            case R.id.btn_pish_factor:
                if(FlagTab) {
                    linear_saler.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_pish_factor.setVisibility(View.VISIBLE);

                    ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.khadamat_passenger_on);
                    ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);
                    ((Button) findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
                    ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
                    txtTitle.setText(" تایید و پرداخت پیش فاکتور    ");
                }
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
    void ClearMenu(View v) {//android:background="@drawable/blue_line_with_arrow_small"
        //android:background="@drawable/trans_line_with_arrow_small"
        ((ImageView) findViewById(R.id.btn_saler)).setImageResource(R.drawable.trans_line_with_arrow_small);
        ((ImageView) findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.trans_line_with_arrow_small);
//		((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.trans_line_with_arrow_small);
        ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.trans_line_with_arrow_small);

        ((Button) findViewById(R.id.txtSaler)).setTextColor(Color.parseColor("#808080"));
        ((Button) findViewById(R.id.btn_mosaferan)).setTextColor(Color.parseColor("#808080"));
//		((Button)findViewById(R.id.btn_khadamat)).setTextColor(Color.parseColor("#808080"));
        ((Button) findViewById(R.id.btn_pish_factor)).setTextColor(Color.parseColor("#808080"));

        //((TextView)findViewById(R.id.imageDiscover)).setBackgroundDrawable(null);
        //((TextView)findViewById(R.id.imageDiscover)).setTextColor(Color.WHITE);
        if (v != null) {
            v.setBackgroundResource(R.drawable.blue_line_with_arrow_small);
            ((TextView) v).setTextColor(Color.parseColor("#33ccff"));
        }
        //if(currentMenu!=null) currentMenu.finish();
    }

    @Override
    public void onBackPressed() {


        if (linear_pish_factor.getVisibility() == View.VISIBLE) {
            linear_pish_factor.setVisibility(View.GONE);
            linear_list_khadamat.setVisibility(View.GONE);

            ((ImageView) findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
            ((Button) findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#4d4d4d"));

            linear_mosaferan.setVisibility(View.VISIBLE);
            txtTitle.setText("  اطلاعات مسافران ");
            ////////////////////bazyabi atelaate akharin mosafer
            PassengerMosaferItems_Table items_Table=new PassengerMosaferItems_Table(PassengerPackageActivity.this);
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
                    //txtTitleCountM.setText(" اطلاعات مسافر " + counter);
                    System.out.println("InsertMosaferGet:"+cursorM.getString(PassengerMosaferItems_Table.Columns.ID.value())+" "+cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value())+" "+cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
                }
            }
            counter--;

            // imgCount.setText(counter+"");
            ///////////////////
        }else if (linear_mosaferan.getVisibility() == View.VISIBLE) {
            ////////////////agar counter hanuzsefr nashode etelaate mosaferesho neshin bede
            if(counter>1) {
                PassengerMosaferItems_Table items_Table=new PassengerMosaferItems_Table(PassengerPackageActivity.this);
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
                        System.out.println("InsertMosaferGet:"+cursorM.getString(PassengerMosaferItems_Table.Columns.ID.value())+" "+cursorM.getString(PassengerMosaferItems_Table.Columns.Onvan.value())+" "+cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));

                    }

                }
                counter--;
                //txtTitleCountM.setText(" اطلاعات مسافر " + counter);
                //  imgCount.setText(counter+"");
            }else{
                //////////////////////
                linear_mosaferan.setVisibility(View.GONE);
                linear_saler.setVisibility(View.VISIBLE);


                txtTitle.setText(" مشخصات خریدار ");
                ((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
                ((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#4d4d4d"));
            }
        } else if (linear_saler.getVisibility() == View.VISIBLE) {

            finish();
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
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    public static void updateTotalInfos(long serviceTotalPrice) {
        // TODO Auto-generated method stub
        //GET_PRICE_KHADAMAT=GET_PRICE_KHADAMAT+serviceTotalPrice;
        //txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(GET_PRICE_KHADAMAT))+"");
        txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(serviceTotalPrice)) + "");
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
                        ((TextView) findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#4d4d4d"));
                        //flagMosafer=flagMosafer+"T";
                    } else {
                        ((TextView) findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#ff3300"));
                        txtmahale_eghamat.setError("لطفا محل اقامت را وارد کنید ");
                    }
                    break;
                case R.id.txtmeliyatm:
                    if (text != null && text.length() > 1) {
                        ((TextView) findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#4d4d4d"));
                        //flagMosafer=flagMosafer+"T";
                    } else {
                        ((TextView) findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#ff3300"));
                        txtmeliyatm.setError("لطفا ملیت را وارد کنید ");
                    }
                    break;
                case R.id.txttavalodm:
                    if (text != null && text.length() > 4) {
                        ((TextView) findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#4d4d4d"));
                        //flagMosafer=flagMosafer+"T";
                    } else {
                        ((TextView) findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#ff3300"));
                        txttavalodm.setError("لطفا تاریخ تولد را وارد کنید ");
                    }
                    break;

                case R.id.txtnamem:
                    if (text != null)
                        if (text.length() > 1 && text.toLowerCase().trim().matches("^[a-zA-Z]+$")) {
                            ((EditText) findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#4d4d4d"));
                            //flagMosafer=flagMosafer+"T";
                        } else {
                            ((EditText) findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#ff3300"));
                            txtnamem.setError("لطفا نام را انگلیسی وارد کنید ");
                        }
                    break;
                case R.id.txtfamilym:
                    if (text != null)
                        if (text.length() > 1 && text.toLowerCase().trim().matches("^[a-zA-Z]+$")) {
                            ((EditText) findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#4d4d4d"));
                            //flagMosafer=flagMosafer+"T";
                        } else {
                            ((EditText) findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#ff3300"));
                            txtfamilym.setError("لطفا نام خانوادگی را انگلیسی وارد کنید ");
                        }
                    break;
                case R.id.txtexp_passport:
                    if (text != null && text.length() > 4) {
                        ((TextView) findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {
                        ((TextView) findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#ff3300"));
                        txtexp_passport.setError("لطفا انقضاء پاسپورت را وارد کنید ");
                    }
                    break;
                case R.id.txtnumber_passport:

                    if (text.trim().length() > 6 && text.trim().length() < 10 && (text.trim().substring(0, 1).matches("^[a-zA-Z]+$")) && text.trim().substring(1, text.length() - 1).matches("[0-9]+")) {
                        ((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#4d4d4d"));

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

                //خریدار
                case R.id.txtemeliP:
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (text.matches(emailPattern) && text.length() > 0) {
                        //if( Patterns.EMAIL_ADDRESS.matcher(text).matches() ){
                        ((EditText) findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {
                        ((EditText) findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#ff3300"));
                        txtemeliP.setError("لطفا ایمیل را وارد کنید ");
                    }

                    break;
                case R.id.txtnameP:

                    if (text != null && text.length() > 1) {
                        ((EditText) findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {
                        ((EditText) findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#ff3300"));
                        txtnameP.setError("لطفا نام را فارسی وارد کنید ");
                    }
                    break;
                case R.id.txtfamilyP:

                    if (text != null && text.length() > 1) {
                        ((EditText) findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {
                        ((EditText) findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
                        txtfamilyP.setError("لطفا نام خانوادگی را فارسی وارد کنید ");
                    }
                    break;

                case R.id.txtmobileP:

                    if (text != null && text.length() > 9 && text.trim().matches("[0-9]+")) {
                        ((EditText) findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {
                        ((EditText) findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#ff3300"));
                        txtmobileP.setError("لطفا شماره موبایل را وارد کنید ");
                    }
                    break;
                case R.id.txtkodemeliP:
                    if (text != null)
                        if (text.length() > 9 && text.length() < 12 && text.trim().matches("[0-9]+")) {
                            ((EditText) findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#4d4d4d"));

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


    //AsyncFetchGetPreFactorDetails
    private class AsyncFetchGetPreFactorDetails extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;
        private ListView listAirPort;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rlLoading.setVisibility(View.VISIBLE);
            Utility.disableEnableControls(false,rlRoot);
            //this method will be running on UI thread
          //  needShowProgressDialog();

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
            //List<PurchaseFlightResult> data=new ArrayList<PurchaseFlightResult>();
            rlLoading.setVisibility(View.GONE);
            Utility.disableEnableControls(true,rlRoot);
         //   needHideProgressDialog();
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
                recyclerViewHotel.addItemDecoration(new DividerItemDecoration(PassengerPackageActivity.this, 1));
                recyclerViewHotel.setLayoutManager(new LinearLayoutManager(PassengerPackageActivity.this));
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
                recyclerViewPassenger.addItemDecoration(new DividerItemDecoration(PassengerPackageActivity.this, 1));
                recyclerViewPassenger.setLayoutManager(new LinearLayoutManager(PassengerPackageActivity.this));
                ArrayList<PassengerPreFactorModel> passengerPreFactorModels = new ArrayList<>();

                JSONArray jArray3 = jArray.getJSONArray("RequestPassenger");


                for (int i = 0; i < jArray3.length(); i++) {
                    passengerPreFactorModels.add(new PassengerPreFactorModel(jArray3.getJSONObject(i).getString("Gender"), jArray3.getJSONObject(i).getString("Nationality"),
                            jArray3.getJSONObject(i).getString("RqPassenger_Birthdate"), jArray3.getJSONObject(i).getString("RqPassenger_PassNo"),
                            jArray3.getJSONObject(i).getString("RqPassenger_name")));

                }
                if (!passengerPreFactorModels.isEmpty()) {
                    llDetailPassanger.setVisibility(View.VISIBLE);
                    recyclerViewPassenger.setAdapter(new PassangerPreFactorAdapter(passengerPreFactorModels));

                }


                //for Services=============================================================================
                final RecyclerView recyclerViewService = (RecyclerView) findViewById(R.id.recyclerViewService);
                recyclerViewService.addItemDecoration(new DividerItemDecoration(PassengerPackageActivity.this, 1));
                recyclerViewService.setLayoutManager(new LinearLayoutManager(PassengerPackageActivity.this));
                ArrayList<ServicePreFactorModel> servicePreFactorModels = new ArrayList<>();
                JSONArray jArray4 = jArray.getJSONArray("PreFactorServices");

                for (int i = 0; i < jArray4.length(); i++) {
                    servicePreFactorModels.add(new ServicePreFactorModel(jArray4.getJSONObject(i).getString("ServiceNameEn"),
                            jArray4.getJSONObject(i).getString("ServicePrice"), jArray4.getJSONObject(i).getString("ServiceType"),
                            jArray4.getJSONObject(i).getString("CityFa"), jArray4.getJSONObject(i).getString("ServiceNameFa"),jArray4.getJSONObject(i).getString("CountryFa")));

                }
                if (!servicePreFactorModels.isEmpty()) {
                    llDetailService.setVisibility(View.VISIBLE);
                    recyclerViewService.setAdapter(new ServicePreFactorAdapter(servicePreFactorModels));

                }
                //for flight==================================================================================
                final RecyclerView recyclerViewFlight = (RecyclerView) findViewById(R.id.recyclerViewFlight);
                recyclerViewFlight.addItemDecoration(new DividerItemDecoration(PassengerPackageActivity.this, 1));
                recyclerViewFlight.setLayoutManager(new LinearLayoutManager(PassengerPackageActivity.this));
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


            } catch (JSONException e) {
                Toast.makeText(PassengerPackageActivity.this, e.toString(), Toast.LENGTH_LONG).show();


            }


        }//end on pos excute


    }//end on pos excute

    //end AsyncFetchGetPreFactorDetails
    //AsyncFetchPishFactor
    private class AsyncFetchPishFactor extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;
        private ListView listAirPort;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
          //  needShowProgressDialog();
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
           // needHideProgressDialog();
            try {
////////////////////////////
                JSONObject jsonObj = new JSONObject(resultPishfactor);

                // JSONObject jsonObj = new JSONObject(retSrc);

                // Getting JSON Array node
                JSONObject GetAirportsResult = jsonObj.getJSONObject("PurchaseServiceResult");
                int successResult = GetAirportsResult.getInt("SuccessResult");
                if (successResult == 0) {
                    //get Errors
                    JSONObject getError = jsonObj.getJSONObject("Errors");

                    String message = getError.getString("Message");
                   // Toast.makeText(PassengerPackageActivity.this, message, Toast.LENGTH_LONG).show();
                    AlertDialogPassengerFlight AlertDialogPassengerFlight =  new AlertDialogPassengerFlight(PassengerPackageActivity.this,PassengerPackageActivity.this);
                    AlertDialogPassengerFlight.setText(message);
                }

                if (successResult > 1) {
                    txt_shomare_factor.setText(GetAirportsResult.getString("SuccessResult"));

                    tvfactorNumber.setText(GetAirportsResult.getString("SuccessResult"));

                    textView4.setImageBitmap(getBitmap(GetAirportsResult.getString("SuccessResult"), 128, 300, 150));
                } else {
                    txt_shomare_factor.setText("خطایی رخ داده است !");
                }
                // sfsfs

                // Setup and Handover data to recyclerview
                ((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.khadamat_passenger_on);
                ((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
                txtTitle.setText(" تایید و پرداخت پیش فاکتور    ");
                //	myScrollView.setOnTouchListener(null);

                linear_saler.setVisibility(View.GONE);
                linear_mosaferan.setVisibility(View.GONE);
                linear_list_khadamat.setVisibility(View.GONE);
                linear_pish_factor.setVisibility(View.VISIBLE);
                FlagTab=true;
                new AsyncFetchGetPreFactorDetails().execute();


            } catch (JSONException e) {
                AlertDialogPassengerFlight AlertDialogPassengerFlight =  new AlertDialogPassengerFlight(PassengerPackageActivity.this,PassengerPackageActivity.this);
                AlertDialogPassengerFlight.setText("خطا در دریافت اطلاعات از الی گشت ");
            }


        }//end on pos excute

    }//end async get pish factor

    //het khadamat
    private class AsyncFetch extends AsyncTask<String, String, String> {
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
                url = new URL("http://mobilews.eligasht.com/LightServices/Rest/Package/PackageService.svc/PurchasePackage");

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


                String data = OrderToJsonPurchase();


                HttpClient client = new DefaultHttpClient();


                HttpPost post = new HttpPost();
                post = new HttpPost("http://mobilews.eligasht.com/LightServices/Rest/Package/PackageService.svc/PurchasePackage");
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


                return retSrc;

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            FlagMosaferan=false;
            rlLoading.setVisibility(View.GONE);
            Utility.disableEnableControls(true,rlRoot);
            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject GetError = null;

                JSONObject GetAirportsResult = jsonObj.getJSONObject("PurchasePackageResult");//Errors
                if (!GetAirportsResult.getString("Errors").equals("null")) {
                    GetError = GetAirportsResult.getJSONObject("Errors");
                }
                if (GetError != null) {


                } else {
                    try{
                    JSONObject jsonResult = GetAirportsResult.getJSONObject("TmpReserveResult");

                    Prefs.putString("BookingCode_NumFactor", jsonResult.getString("BookingCode"));
                    txt_shomare_factor.setText(jsonResult.getString("BookingCode"));

                    textView4.setImageBitmap(getBitmap(jsonResult.getString("BookingCode"), 128, 300, 150));

                    Prefs.putString("BookingCode_NumFactor", jsonResult.getString("BookingCode"));
                    tvfactorNumber.setText(jsonResult.getString("BookingCode"));
                    //////////////////////////////
                    linear_saler.setVisibility(View.GONE);
                    linear_mosaferan.setVisibility(View.GONE);
                    linear_list_khadamat.setVisibility(View.GONE);
                    linear_pish_factor.setVisibility(View.VISIBLE);

                    ((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_on);
                    ((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
                    txtTitle.setText(" تایید و پرداخت پیش فاکتور    ");

                    new AsyncFetchGetPreFactorDetails().execute();
                    FlagTab=true;
                    mAdapter = new GetHotelKhadmatAdapter(PassengerPackageActivity.this, data, PassengerPackageActivity.this);
                    //mAdapter.setAdapter(mAdapter);
                    mAdapter.setData(data);
                    listKhadamat.setAdapter(mAdapter);
                    }catch ( Exception e){
                        e.getMessage();
                    }
                }
            } catch (JSONException e) {
                AlertDialogPassengerFlight AlertDialogPassengerFlight =  new AlertDialogPassengerFlight(PassengerPackageActivity.this,PassengerPackageActivity.this);
                AlertDialogPassengerFlight.setText("خطا در دریافت اطلاعات از الی گشت ");            }
        }
    }


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

            PassengerMosaferItems_Table items_Table = new PassengerMosaferItems_Table(PassengerPackageActivity.this);
            CursorManager cursorM = items_Table.getAllMosafer();
            if (cursorM != null) {
                for (int i = 0; i < cursorM.getCount(); i++) {


                    cursorM.moveToPosition(i);

                    detailsJson = new JSONObject();
                    detailsJson.put("Gender", cursorM.getBoolean(PassengerMosaferItems_Table.Columns.Gender.value()));
                    detailsJson.put("Nationality", cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));
                    detailsJson.put("Nationality_ID", cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));
                    detailsJson.put("Nationality_ID", cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));


                    detailsJson.put("PackRoomType_ID", packageRoomNoToRequestList.get(i).getPackRoomType_ID());
                    detailsJson.put("Room_No", packageRoomNoToRequestList.get(i).getRoom_No());


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
            PassengerPartnerInfo_Table partnerInfo_Table = new PassengerPartnerInfo_Table(PassengerPackageActivity.this);
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
            //	headerJson.put("Type", "P");

            headerJson.put("PackRow_ID", Prefs.getString("PackRow_ID", "12"));
            headerJson.put("PackXfer_IDs", Prefs.getString("PackXfer_IDs", "12"));
            headerJson.put("Flt_IDs", Prefs.getString("Flt_IDs", "12"));
            headerJson.put("Rooms", Prefs.getString("Rooms", "12"));



		/*	headerJson.put("FlightGuID", getIntent().getExtras().get("FlightGuID"));
*//*			headerJson.put("Checkin", getIntent().getExtras().get("Checkin"));
            headerJson.put("Checkout", getIntent().getExtras().get("Checkin"));*//*
            headerJson.put("Checkin", getIntent().getExtras().getString("CheckIn"));
			headerJson.put("Checkout", getIntent().getExtras().getString("CheckOut"));

*/


            identityJson.put("Password", "123qwe!@#QWE");
            identityJson.put("TermianlId", "Mobile");
            identityJson.put("UserName", "EligashtMlb");
            identityJson.put("RequestorID ", Prefs.getString("userId","-1"));//purches
            headerJson.put("identity", identityJson);

            jsone.put("request", headerJson);

            Log.i("Elham","req : " + jsone.toString());

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
            manJson.put("Type", "P");


            identityJson.put("Password", "123qwe!@#QWE");
            identityJson.put("TermianlId", "Mobile");
          //  identityJson.put("RequestorID ", Prefs.getString("userId","-1"));
            identityJson.put("UserName", "EligashtMlb");
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
                            txtnamem.setError("لطفا نام را انگلیسی وارد کنید ");
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
                            txtfamilym.setError("لطفا نام خانوادگی را انگلیسی وارد کنید ");
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
            case R.id.txt_NationalCode_m:
                if(hasFocus){
                    System.out.println("t");
                }else {
                    System.out.println("f");
                    if (txt_NationalCode_m.getText().toString().trim().length() > 6 && txt_NationalCode_m.getText().toString().trim().length() == 10 &&  txt_NationalCode_m.getText().toString().trim().matches("[0-9]+")) {
                        ((EditText) findViewById(R.id.txt_NationalCode_m)).setTextColor(Color.parseColor("#4d4d4d"));

                    } else {
                        //((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
                        txtnumber_passport.setError("لطفا کدملی را صحیح وارد کنید ");
                    }
                    if (txtnumber_passport.getText().toString() != null && txtnumber_passport.getText().toString().length() == 10) {
                    } else {
                        //((EditText) findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
                        txtnumber_passport.setError("لطفا کدملی را صحیح وارد کنید ");
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
                            txtnameP.setError("لطفا نام را فارسی وارد کنید ");
                        }}
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
                            txtfamilyP.setError("لطفا نام خانوادگی را فارسی وارد کنید ");
                        }}
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

}