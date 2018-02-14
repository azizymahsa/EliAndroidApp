package com.reserv.myapplicationeli.views.activities.transfer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.hotel.AirportTransportServicePrice;
import com.reserv.myapplicationeli.api.hotel.comment.AddComment;
import com.reserv.myapplicationeli.api.hotel.hotelName.HotelNameApi;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.RequestAdd;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.RequsetAddComment;
import com.reserv.myapplicationeli.models.hotel.api.addcomment.call.ReviewComment;
import com.reserv.myapplicationeli.models.hotel.api.airportTransportServicePrice.request.AirportPriceRequest;
import com.reserv.myapplicationeli.models.hotel.api.airportTransportServicePrice.request.AirportTransportServicePriceRequest;
import com.reserv.myapplicationeli.models.hotel.api.airportTransportServicePrice.request.Param;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.hotelName.request.HotelNameRequest;
import com.reserv.myapplicationeli.models.hotel.api.hotelName.request.HotelNameRequestModel;
import com.reserv.myapplicationeli.models.hotel.api.hotelName.response.Hotels;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.datetools.DateUtil;
import com.reserv.myapplicationeli.tools.persian.Calendar.persian.util.PersianCalendarUtils;
import com.reserv.myapplicationeli.views.ui.GetAirportMabdaActivity;
import com.reserv.myapplicationeli.views.ui.GetAirportMaghsadActivity;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.reserv.myapplicationeli.views.ui.PassengerActivity;
import com.reserv.myapplicationeli.views.ui.SplashFragment;
import com.reserv.myapplicationeli.views.ui.dialog.app.SplashDialog;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.AlertDialogPassengerFlight;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TransferActivity extends BaseActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener {
    TextView tvDepurtureAirport, tvHotel, tvDepurtureDate, tvReturnDate, tvDepurtureTime, tvReturnTime;
    String DepurtureAirport, Hotel, DepurtureDate, ReturnAirportDate, DepurtureTime, ReturnTime, DepurtureFlt, ReturnFlt, Hotelcode, TmpRq, AirPortCode, ReturnDate, ServiceID, PassengerList, CityId, HotelId;
    DatePickerDialog datePickerDialog;
    DatePickerDialog datePickerDialog2;
    TimePickerDialog timePickerDialog;
    TimePickerDialog timePickerDialog2;
    int month;
    int year_;
    int day;
    int monthMin;
    int year_Min;
    int dayMin;
    String raft, bargasht;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian1;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian2;
    boolean geo;
    EditText tvDepurtureFlt, tvReturnFlt;
    AirportTransportServicePrice airportTransportServicePrice;
    Button btnCal;
    RelativeLayout rlLoading2;
    SplashDialog splashDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        initViews();
        initValues();
        initCalenndar();
        InitUi.Toolbar(this, false, R.color.toolbar_color, "محاسبه قیمت");

        splashDialog = new SplashDialog(TransferActivity.this, null);

    }

    public void initViews() {
        tvDepurtureAirport = findViewById(R.id.tvDepurtureAirport);
        tvHotel = findViewById(R.id.tvHotel);
        tvDepurtureDate = findViewById(R.id.tvDepurtureDate);
        tvReturnDate = findViewById(R.id.tvReturnDate);
        tvDepurtureTime = findViewById(R.id.tvDepurtureTime);
        tvReturnTime = findViewById(R.id.tvReturnTime);
        tvDepurtureFlt = findViewById(R.id.tvDepurtureFlt);
        tvReturnFlt = findViewById(R.id.tvReturnFlt);
        btnCal = findViewById(R.id.btnCal);
        rlLoading2 = findViewById(R.id.rlLoading2);

        tvDepurtureAirport.setOnClickListener(this);
        tvHotel.setOnClickListener(this);
        tvDepurtureDate.setOnClickListener(this);
        tvReturnDate.setOnClickListener(this);
        tvDepurtureTime.setOnClickListener(this);
        tvReturnTime.setOnClickListener(this);
        tvDepurtureFlt.setOnClickListener(this);
        tvReturnFlt.setOnClickListener(this);
        btnCal.setOnClickListener(this);
        rlLoading2.setOnClickListener(this);
        Utility.setAnimLoading(this);
        tvDepurtureFlt.clearFocus();
        tvReturnFlt.clearFocus();

    }

    public void initCalenndar() {

        PersianCalendar persianCalendarDatePicker = new PersianCalendar();
        //  Date currentTime = Calendar.getInstance().getTime();
        //=================================================================================================
        PersianCalendar persianCalendar = new PersianCalendar();

        persianCalendar.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth(), persianCalendarDatePicker.getPersianDay() + 1);


        month = persianCalendarDatePicker.getPersianMonth();
        year_ = persianCalendarDatePicker.getPersianYear();
        day = persianCalendarDatePicker.getPersianDay();

        datePickerDialogGregorian1 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog();
        datePickerDialogGregorian2 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog();

        // datePickerDialogGregorian2.setOnDateSetListener(this);
        //  datePickerDialogGregorian2.setOnCalandarChangeListener(this);

        datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker.toGregorianCalendar());
        datePickerDialogGregorian2.setMinDate(persianCalendarDatePicker.toGregorianCalendar());
        timePickerDialog = TimePickerDialog.newInstance(this, persianCalendar.getTime().getHours(), persianCalendar.getTime().getMinutes(), true);
        timePickerDialog2 = TimePickerDialog.newInstance(this, persianCalendar.getTime().getHours(), persianCalendar.getTime().getMinutes(), true);
        timePickerDialog.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(com.wdullaer.materialdatetimepicker.time.RadialPickerLayout view, int hourOfDay, int minute, int second) {
                tvDepurtureTime.setText(hourOfDay + ":" + minute);


            }
        });
        timePickerDialog2.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(com.wdullaer.materialdatetimepicker.time.RadialPickerLayout view, int hourOfDay, int minute, int second) {
                tvReturnTime.setText(hourOfDay + ":" + minute);


            }
        });
        datePickerDialog = DatePickerDialog.newInstance(
                this,
                persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay()
        );

        datePickerDialog.setMinDate(persianCalendarDatePicker);


        datePickerDialog2 = DatePickerDialog.newInstance(
                this,
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        datePickerDialog2.setMinDate(persianCalendar);


        raft = date_server(persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay());


        bargasht = date_server(persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay());

        //=====================================================================================================


        datePickerDialog.setOnCalandarChangeListener(new DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogGregorian1.show(getFragmentManager(), "DatePickerDialogGregorianRaft");
            }
        });


        datePickerDialogGregorian1.setOnCalandarChangeListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialog.show(getSupportFragmentManager(), "DatepickerdialogRaft");


            }
        });


//=====================================================================================================

        datePickerDialog2.setOnCalandarChangeListener(new DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogGregorian2.show(getFragmentManager(), "DatePickerDialogGregorianBargasht");
            }
        });


        datePickerDialogGregorian2.setOnCalandarChangeListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialog2.show(getSupportFragmentManager(), "DatepickerdialogBargasht");


            }
        });
        datePickerDialog2.setTitle("تاریخ برگشت را انتخاب نمایید");


//=====================================================================================================


        datePickerDialogGregorian1.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {

                geo = true;
                Log.e("GGGGGGGRaft", year + "==" + monthOfYear + 1 + "==" + dayOfMonth);


                String str_date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;//2018-01-16
                DateFormat formatter;
                Date date;
                formatter = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    date = (Date) formatter.parse(str_date);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    datePickerDialogGregorian2.setMinDate(cal);


                    tvDepurtureDate.setText(DateUtil.getLongStringDate(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth, "yyyy/MM/dd", false));

                    raft = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                    Log.e("GGGGGGG", raft);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tvReturnDate.setText(tvDepurtureDate.getText().toString());

                Prefs.putString("bargashtfa", tvDepurtureDate.getText().toString());

                Prefs.putString("raft", raft);
                Prefs.putString("raftfa", tvDepurtureDate.getText().toString());


            }
        });
        datePickerDialogGregorian2.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
                Log.e("GGGGGGGBar", year + "==" + (monthOfYear + 1) + "==" + dayOfMonth);
                geo = true;
                tvReturnDate.setText(DateUtil.getLongStringDate(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth, "yyyy/MM/dd", false));

                bargasht = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;


                Prefs.putString("bargasht", bargasht);
                Prefs.putString("bargashtfa", tvReturnDate.getText().toString());

            }


        });


//=====================================================================================================


//=====================================================================================================
        if (Prefs.getString("bargashtfa", "null").equals("null")) {
            //    tvReturnDate.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());

        } else {

            //    tvReturnDate.setText(Prefs.getString("bargashtfa", "null"));
            bargasht = Prefs.getString("bargasht", "null").replaceAll("-", "/");


            Log.e("testdate", bargasht);

            String[] dateSplite2 = bargasht.split("/");

            String dayMF = dateSplite2[2];
            String monthMF = dateSplite2[1];
            String yearMF = dateSplite2[0];
            String[] dateSplite3 = com.reserv.myapplicationeli.tools.datetools.SolarCalendar.calSolarCalendar(Integer.valueOf(yearMF), Integer.valueOf(monthMF) - 1, Integer.valueOf(dayMF) + 1).split("/");

            String dayMF1 = dateSplite3[2];
            String monthMF1 = dateSplite3[1];
            String yearMF1 = dateSplite3[0];


            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(Integer.valueOf(yearMF1), Integer.valueOf(monthMF1), Integer.valueOf(dayMF1));
            Log.e("testesttt", persianCalendarDatePicker2.getPersianLongDateAndTime());
            datePickerDialog2.initialize(this, persianCalendarDatePicker2.getPersianYear(), persianCalendarDatePicker2.getPersianMonth(), persianCalendarDatePicker2.getPersianDay());


        }


        if (Prefs.getString("raftfa", "null").equals("null")) {
            // tvDepurtureDate.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());

        } else {
            // tvDepurtureDate.setText(Prefs.getString("raftfa", "null"));
            raft = Prefs.getString("raft", "null").replaceAll("-", "/");
            Log.e("testdate", raft);

            String[] dateSplite2 = raft.split("/");

            String dayMF = dateSplite2[2];
            String monthMF = dateSplite2[1];
            String yearMF = dateSplite2[0];
            String[] dateSplite3 = com.reserv.myapplicationeli.tools.datetools.SolarCalendar.calSolarCalendar(Integer.valueOf(yearMF), Integer.valueOf(monthMF) - 1, Integer.valueOf(dayMF) + 1).split("/");

            String dayMF1 = dateSplite3[2];
            String monthMF1 = dateSplite3[1];
            String yearMF1 = dateSplite3[0];


            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(Integer.valueOf(yearMF1), Integer.valueOf(monthMF1), Integer.valueOf(dayMF1));
            Log.e("testesttt", persianCalendarDatePicker2.getPersianLongDateAndTime());
            datePickerDialog.initialize(this, persianCalendarDatePicker2.getPersianYear(), persianCalendarDatePicker2.getPersianMonth(), persianCalendarDatePicker2.getPersianDay());


        }

    }

    public void initValues() {

        DepurtureAirport = getIntent().getExtras().getString("ArrialAirportName");
        ReturnAirportDate = getIntent().getExtras().getString("ArrivalFltDate");

        ReturnFlt = getIntent().getExtras().getString("ArrivalFltNo");

        DepurtureFlt = getIntent().getExtras().getString("DepartureFltNo");

        DepurtureTime = getIntent().getExtras().getString("DepartureFltTime");
        ReturnTime = getIntent().getExtras().getString("ArrivalFltTime");
        DepurtureDate = getIntent().getExtras().getString("DepartureFltDate");
        Hotel = getIntent().getExtras().getString("HotelNameEn");
        Log.e("hotelTest", Hotel);
        Log.e("hotelTest1", DepurtureFlt);
        Log.e("hotelTest2", ReturnFlt);
        Log.e("hotelTest3", ReturnAirportDate);
        Log.e("hotelTest4", DepurtureTime);

        CityId = getIntent().getExtras().getString("CityID");

        Hotelcode = getIntent().getExtras().getString("HotelID");
        getIntent().getExtras().getString("HotelNameEn");
        AirPortCode = getIntent().getExtras().getString("ArrialAirportCode");

        ServiceID = getIntent().getExtras().getString("ServiceID");
        PassengerList = getIntent().getExtras().getString("PassengerList");
        TmpRq = getIntent().getExtras().getString("BookingCode");

        if (!ValidationTools.isEmptyOrNull(DepurtureAirport)) {
            tvDepurtureAirport.setText(DepurtureAirport);
            tvDepurtureAirport.setClickable(false);
            tvDepurtureAirport.setEnabled(false);

        } else {
            tvDepurtureAirport.setClickable(true);
            tvDepurtureAirport.setEnabled(true);
        }

        if (!ValidationTools.isEmptyOrNull(ReturnAirportDate)) {
            tvReturnDate.setText(Utility.dateShow(ReturnAirportDate));
            tvReturnDate.setClickable(false);
            tvReturnDate.setEnabled(false);

        } else {
            tvReturnDate.setClickable(true);
            tvReturnDate.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(ReturnFlt)) {
            tvReturnFlt.setText(ReturnFlt);
            tvReturnFlt.setClickable(false);
            tvReturnFlt.setEnabled(false);

        } else {
            tvReturnFlt.setClickable(true);
            tvReturnFlt.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(DepurtureFlt)) {
            tvDepurtureFlt.setText(DepurtureFlt);
            tvDepurtureFlt.setClickable(false);
            tvDepurtureFlt.setEnabled(false);

        } else {
            tvDepurtureFlt.setClickable(true);
            tvDepurtureFlt.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(DepurtureTime)) {
            tvDepurtureTime.setText(DepurtureTime);
            tvDepurtureTime.setClickable(false);
            tvDepurtureTime.setEnabled(false);

        } else {
            tvDepurtureTime.setClickable(true);
            tvDepurtureTime.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(ReturnTime)) {
            tvReturnTime.setText(ReturnTime);
            tvReturnTime.setClickable(false);
            tvReturnTime.setEnabled(false);

        } else {
            tvReturnTime.setClickable(true);
            tvReturnTime.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(DepurtureDate)) {
            tvDepurtureDate.setText(Utility.dateShow(DepurtureDate));
            tvDepurtureDate.setClickable(false);
            tvDepurtureDate.setEnabled(false);

        } else {
            tvDepurtureDate.setClickable(true);
            tvDepurtureDate.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(Hotel)) {
            tvHotel.setText(Hotel);

            tvHotel.setClickable(false);
            tvHotel.setEnabled(false);

        } else {
            tvHotel.setClickable(true);
            tvHotel.setEnabled(true);
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ValidationTools.isEmptyOrNull(DepurtureAirport)) {
            tvDepurtureAirport.setText(Prefs.getString("Value-Mabda-City2", "انتخاب کنید"));

        }
        if (ValidationTools.isEmptyOrNull(Hotel)) {
            tvHotel.setText(Prefs.getString("HotelName", "انتخاب کنید"));
        }
        if (ValidationTools.isEmptyOrNull(AirPortCode)) {
            AirPortCode = Prefs.getString("Value-Mabda-Airport-Code2", "");

        }
        if (ValidationTools.isEmptyOrNull(Hotelcode)) {
            Hotelcode = Prefs.getString("HotelCode", "");

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Prefs.putString("HotelName", "انتخاب کنید");
        Prefs.putString("Value-Mabda-Airport-Code2", "");
        Prefs.putString("Value-Mabda-City2", "انتخاب کنید");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tvDepurtureAirport:
                Intent intent = new Intent(this, GetAirportMabdaActivity.class);
                startActivity(intent);

                break;
            case R.id.tvHotel:
                startActivity(new Intent(TransferActivity.this, GetHotelActivity.class));
                break;
            case R.id.tvDepurtureDate:
                if (geo) {
                    datePickerDialogGregorian1.show(getFragmentManager(), "DatePickerDialogGregorianRaft");

                } else {
                    datePickerDialog.show(getSupportFragmentManager(), "DatepickerdialogRaft");

                }
                break;
            case R.id.tvReturnDate:
                if (geo) {
                    datePickerDialogGregorian2.show(getFragmentManager(), "DatePickerDialogGregorianRaft");

                } else {
                    datePickerDialog2.show(getSupportFragmentManager(), "DatepickerdialogBargasht");

                }

                break;
            case R.id.tvDepurtureTime:
                timePickerDialog.show(getFragmentManager(), "timeRaft");

                break;
            case R.id.tvReturnTime:
                timePickerDialog2.show(getFragmentManager(), "timeBargasht");

                break;
            case R.id.tvDepurtureFlt:


                break;
            case R.id.tvReturnFlt:

                break;
            case R.id.btnCal:
                boolean cal = true;

                if (tvDepurtureAirport.getText().toString().contains("انتخاب")) {
                    cal = false;
                    GradientDrawable drawable = (GradientDrawable) tvDepurtureAirport.getBackground();
                    drawable.setStroke(4, Color.RED); // set stroke wid
                    // tvDepurtureAirport.setError("فرودگاه مقصد را انتخاب کنید");
                } else {
                    GradientDrawable drawable = (GradientDrawable) tvDepurtureAirport.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.text_color));
                }

                if (tvDepurtureTime.getText().toString().contains("انتخاب")) {
                    cal = false;
                    GradientDrawable drawable = (GradientDrawable) tvDepurtureTime.getBackground();
                    drawable.setStroke(4, Color.RED); // se
                    // tvDepurtureTime.setError("ساعت رفت را انتخاب کنید");

                } else {
                    GradientDrawable drawable = (GradientDrawable) tvDepurtureTime.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.text_color));
                }

                if (tvReturnTime.getText().toString().contains("انتخاب")) {
                    //  tvReturnTime.setError("ساعت برگشت را انتخاب کنید");
                    GradientDrawable drawable = (GradientDrawable) tvReturnTime.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.text_color));
                    cal = false;

                } else {
                    GradientDrawable drawable = (GradientDrawable) tvReturnTime.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.text_color));
                }


                if (ValidationTools.isEmptyOrNull(tvDepurtureFlt.getText().toString())) {
                    cal = false;
                    GradientDrawable drawable = (GradientDrawable) tvDepurtureFlt.getBackground();
                    drawable.setStroke(4, Color.RED);
                    //    tvDepurtureFlt.setError("شماره پرواز رفت را انتخاب کنید");
                    Log.e("test1", tvDepurtureFlt.getHint().toString());


                } else if (tvDepurtureFlt.getText().toString().length() >= 6) {
                    GradientDrawable drawable = (GradientDrawable) tvDepurtureFlt.getBackground();
                    drawable.setStroke(4, Color.RED);
                    Log.e("test2", tvDepurtureFlt.getText().toString().length() + "");

                    cal = false;
                    //  Toast.makeText(this, "شماره پرواز رفت را به درستی وارد نمایید", Toast.LENGTH_SHORT).show();

                    splashDialog.seeText("شماره پرواز رفت را به درستی وارد نمایید");
                    splashDialog.setBtnText();
                    splashDialog.showAlert();
                } else {
                    GradientDrawable drawable = (GradientDrawable) tvDepurtureFlt.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.text_color));
                    Log.e("test4", tvDepurtureFlt.getText().toString() + "");

                }


                if (ValidationTools.isEmptyOrNull(tvReturnFlt.getText().toString())) {
                    GradientDrawable drawable = (GradientDrawable) tvReturnFlt.getBackground();
                    drawable.setStroke(4, Color.RED);
                    cal = false;
                    //  tvReturnFlt.setError("شماره پرواز برگشت را وارد نمایید");


                } else if (tvReturnFlt.getText().toString().length() >= 6) {
                    cal = false;
                    GradientDrawable drawable = (GradientDrawable) tvReturnFlt.getBackground();
                    drawable.setStroke(4, Color.RED);
                    // tvReturnFlt.setError("شماره پرواز برگشت را به درستی وارد نمایید");
                    //    Toast.makeText(this, "شماره پرواز برگشت را به درستی وارد نمایید", Toast.LENGTH_SHORT).show();
                    //   AlertDialogPassengerFlight.setText("خطا در دریافت اطلاعات از الی گشت ");
                    splashDialog.seeText("شماره پرواز برگشت را به درستی وارد نمایید");
                    splashDialog.showAlert();
                    splashDialog.setBtnText();


                } else {
                    GradientDrawable drawable = (GradientDrawable) tvReturnFlt.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.text_color));
                }


                if (tvReturnDate.getText().toString().contains("انتخاب")) {
                    cal = false;
                    //  tvReturnDate.setError("شماره پرواز برگشت را وارد نمایید");
                    GradientDrawable drawable = (GradientDrawable) tvReturnDate.getBackground();
                    drawable.setStroke(4, Color.RED);
                } else {
                    GradientDrawable drawable = (GradientDrawable) tvReturnDate.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.text_color));
                }


                if (tvDepurtureDate.getText().toString().contains("انتخاب")) {
                    GradientDrawable drawable = (GradientDrawable) tvDepurtureDate.getBackground();
                    drawable.setStroke(4, Color.RED);
                    cal = false;
                    // tvDepurtureDate.setError("تاریخ برگشت را انتخاب کنید");

                } else {
                    GradientDrawable drawable = (GradientDrawable) tvDepurtureDate.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.text_color));
                }
                if (tvReturnTime.getText().toString().contains("انتخاب")) {
                    // tvReturnTime.setError("تاریخ رفت را انتخاب کنید");
                    GradientDrawable drawable = (GradientDrawable) tvReturnTime.getBackground();
                    drawable.setStroke(4, Color.RED);
                    cal = false;
                } else {
                    GradientDrawable drawable = (GradientDrawable) tvReturnTime.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.text_color));
                }
                if (tvHotel.getText().toString().contains("انتخاب")) {
                    //  tvHotel.setError("نام هتل را انتخاب کنید");
                    GradientDrawable drawable = (GradientDrawable) tvHotel.getBackground();
                    drawable.setStroke(4, Color.RED);
                    cal = false;
                } else {
                    GradientDrawable drawable = (GradientDrawable) tvHotel.getBackground();
                    drawable.setStroke(4, ContextCompat.getColor(this, R.color.text_color));
                }
                if (cal) {
                    DepurtureAirport = tvDepurtureAirport.getText().toString();
                    DepurtureDate = raft;
                    ReturnAirportDate = bargasht;
                    DepurtureTime = tvDepurtureTime.getText().toString();
                    ReturnTime = tvReturnTime.getText().toString();
                    DepurtureFlt = tvDepurtureFlt.getText().toString();
                    ReturnFlt = tvReturnFlt.getText().toString();
                    ReturnDate = tvReturnDate.getText().toString();
                    new GetPriceAsync().execute();
                    break;
                }
        }

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
        geo = false;

        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.set(year, month, day);


        if (view.getTag().equals("DatepickerdialogBargasht")) {
            tvReturnDate.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
            bargasht = date_server(year, monthOfYear, dayOfMonth);
            Prefs.putString("bargashtfa", tvReturnDate.getText().toString());
            Prefs.putString("bargasht", bargasht);


        }


        if (view.getTag().equals("DatepickerdialogRaft")) {
            year_Min = year;
            monthMin = monthOfYear;
            dayMin = dayOfMonth;
            tvDepurtureDate.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
            //  tvReturnDate.setText(persianCalendar.getPersianLongDate());
            raft = date_server(year, monthOfYear, dayOfMonth);
            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(year_Min, monthMin, dayMin);


            if (Utility.campareDate(raft, bargasht)) {
                tvReturnDate.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
                datePickerDialog2.initialize(this, year_, month, day);
                datePickerDialog2.setMinDate(persianCalendarDatePicker2);
            }


            Prefs.putString("bargashtfa", tvReturnDate.getText().toString());

            Prefs.putString("raft", raft);
            Prefs.putString("raftfa", tvDepurtureDate.getText().toString());


        }

    }

    public static String date_server(int y, int m, int d) {
        Date date = PersianCalendarUtils.ShamsiToMilady(y, m + 1, d);

        SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
        String formatted = format1.format(date.getTime());
        String[] dateGrg = formatted.split("/");
        int monthS = Integer.valueOf(dateGrg[0]);
        long dayS = Long.valueOf(dateGrg[1]);
        int yearS = Integer.valueOf(dateGrg[2]);


        return yearS + "/" + monthS + "/" + dayS;
    }

    @Override
    public void onTimeSet(com.wdullaer.materialdatetimepicker.time.RadialPickerLayout view, int hourOfDay, int minute, int second) {
        Log.e("test", view.getTag() + "");
      /*  if (view.getTag().equals("timeRaft")) {
            tvDepurtureTime.setText(hourOfDay + ":" + minute);

        }
        if (view.getTag().equals("timeBargasht")) {
            tvReturnTime.setText(hourOfDay + ":" + minute);


        }*/
    }


    private class GetPriceAsync extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            rlLoading2.setVisibility(View.VISIBLE);
            Log.e("logeeeeee", new Gson().toJson(new AirportTransportServicePriceRequest
                    (new AirportPriceRequest(TmpRq, new Param(DepurtureAirport, AirPortCode, DepurtureDate, DepurtureTime, DepurtureFlt, ReturnDate, ServiceID, ReturnTime, ReturnFlt, CityId, Hotelcode, PassengerList, "true"), "Culture", new Identity("EligashtMlb",
                            "123qwe!@#QWE", "Mobile")))));

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                airportTransportServicePrice = new AirportTransportServicePrice(new AirportTransportServicePriceRequest
                        (new AirportPriceRequest(TmpRq, new Param(DepurtureAirport, AirPortCode, DepurtureDate, DepurtureTime, DepurtureFlt, ReturnDate, ServiceID, ReturnTime, ReturnFlt, CityId, Hotelcode, PassengerList, "true"), "Culture", new Identity("EligashtMlb",
                                "123qwe!@#QWE", "Mobile"))));

            } catch (Exception e) {


            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                rlLoading2.setVisibility(View.GONE);
                finish();
                Prefs.putLong("Tprice",12000000);


                if (airportTransportServicePrice.airportTransportRespone.getAirportTransportServicePriceResult().Errors != null) {
                    Toast.makeText(TransferActivity.this, airportTransportServicePrice.airportTransportRespone.getAirportTransportServicePriceResult().Errors.get(0).DetailedMessage, Toast.LENGTH_SHORT).show();

                } else {
                    Log.e("wer", new Gson().toJson(airportTransportServicePrice.airportTransportRespone.toString()));
                    Toast.makeText(TransferActivity.this, airportTransportServicePrice.airportTransportRespone.getAirportTransportServicePriceResult().Errors.get(0).DetailedMessage, Toast.LENGTH_SHORT).show();
                   // Toast.makeText(TransferActivity.this, airportTransportServicePrice.airportTransportRespone.AirportTransportServicePriceResult.TransferAvailabilityRoundtripResults.get(0).TotalPrice.Amount, Toast.LENGTH_SHORT).show();
                   // Toast.makeText(TransferActivity.this, airportTransportServicePrice.airportTransportRespone.AirportTransportServicePriceResult.TransferAvailabilityRoundtripResults.get(0).TotalPrice.Amount, Toast.LENGTH_SHORT).show();

                }


            } catch (Exception e) {

                Toast.makeText(TransferActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

            }


        }
    }


}
