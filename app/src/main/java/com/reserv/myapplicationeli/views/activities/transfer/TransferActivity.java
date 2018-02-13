package com.reserv.myapplicationeli.views.activities.transfer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.datetools.DateUtil;
import com.reserv.myapplicationeli.tools.persian.Calendar.persian.util.PersianCalendarUtils;
import com.reserv.myapplicationeli.views.ui.GetAirportMabdaActivity;
import com.reserv.myapplicationeli.views.ui.GetAirportMaghsadActivity;
import com.reserv.myapplicationeli.views.ui.InitUi;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TransferActivity extends BaseActivity implements View.OnClickListener,TimePickerDialog.OnTimeSetListener, com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener {
    TextView tvDepurtureAirport, tvHotel, tvDepurtureDate, tvReturnDate, tvDepurtureTime, tvReturnTime, tvDepurtureFlt, tvReturnFlt;
    String DepurtureAirport, Hotel, DepurtureDate, ReturnAirportDate, DepurtureTime, ReturnTime, DepurtureFlt, ReturnFlt;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        initViews();
        initValues();
        initCalenndar();
        InitUi.Toolbar(this, false, R.color.toolbar_color, "محاسبه قیمت");

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

        tvDepurtureAirport.setOnClickListener(this);
        tvHotel.setOnClickListener(this);
        tvDepurtureDate.setOnClickListener(this);
        tvReturnDate.setOnClickListener(this);
        tvDepurtureTime.setOnClickListener(this);
        tvReturnTime.setOnClickListener(this);
        tvDepurtureFlt.setOnClickListener(this);
        tvReturnFlt.setOnClickListener(this);


    }
    public void initCalenndar(){

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
        timePickerDialog=TimePickerDialog.newInstance(this,persianCalendar.getTime().getHours(),persianCalendar.getTime().getMinutes(),true);
        timePickerDialog2=TimePickerDialog.newInstance(this,persianCalendar.getTime().getHours(),persianCalendar.getTime().getMinutes(),true);
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
            bargasht = Prefs.getString("bargasht", "null").replaceAll("-","/");





            Log.e("testdate", bargasht );

            String[] dateSplite2=bargasht.split("/");

            String dayMF=dateSplite2[2];
            String monthMF=dateSplite2[1];
            String yearMF=dateSplite2[0];
            String[] dateSplite3= com.reserv.myapplicationeli.tools.datetools.SolarCalendar.calSolarCalendar(Integer.valueOf(yearMF),Integer.valueOf(monthMF)-1,Integer.valueOf(dayMF)+1).split("/");

            String dayMF1=dateSplite3[2];
            String monthMF1=dateSplite3[1];
            String yearMF1=dateSplite3[0];


            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(Integer.valueOf(yearMF1), Integer.valueOf(monthMF1), Integer.valueOf(dayMF1));
            Log.e("testesttt", persianCalendarDatePicker2.getPersianLongDateAndTime());
            datePickerDialog2.initialize(this, persianCalendarDatePicker2.getPersianYear(),  persianCalendarDatePicker2.getPersianMonth(),  persianCalendarDatePicker2.getPersianDay());




        }


        if (Prefs.getString("raftfa", "null").equals("null")) {
           // tvDepurtureDate.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());

        } else {
           // tvDepurtureDate.setText(Prefs.getString("raftfa", "null"));
            raft = Prefs.getString("raft", "null").replaceAll("-","/");
            Log.e("testdate", raft );

            String[] dateSplite2=raft.split("/");

            String dayMF=dateSplite2[2];
            String monthMF=dateSplite2[1];
            String yearMF=dateSplite2[0];
            String[] dateSplite3= com.reserv.myapplicationeli.tools.datetools.SolarCalendar.calSolarCalendar(Integer.valueOf(yearMF),Integer.valueOf(monthMF)-1,Integer.valueOf(dayMF)+1).split("/");

            String dayMF1=dateSplite3[2];
            String monthMF1=dateSplite3[1];
            String yearMF1=dateSplite3[0];


            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(Integer.valueOf(yearMF1), Integer.valueOf(monthMF1), Integer.valueOf(dayMF1));
            Log.e("testesttt", persianCalendarDatePicker2.getPersianLongDateAndTime());
            datePickerDialog.initialize(this, persianCalendarDatePicker2.getPersianYear(),  persianCalendarDatePicker2.getPersianMonth(),  persianCalendarDatePicker2.getPersianDay());






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
        Log.e("hotelTest", Hotel );
        Log.e("hotelTest1", DepurtureFlt );
        Log.e("hotelTest2", ReturnFlt );
        Log.e("hotelTest3", ReturnAirportDate );
        Log.e("hotelTest4", DepurtureTime );

        getIntent().getExtras().getString("CityID");

        getIntent().getExtras().getString("HotelID");
        getIntent().getExtras().getString("HotelNameEn");
        getIntent().getExtras().getString("ArrialAirportCode");


        if (!ValidationTools.isEmptyOrNull(DepurtureAirport)){
            tvDepurtureAirport.setText(DepurtureAirport);
            tvDepurtureAirport.setClickable(false);
            tvDepurtureAirport.setEnabled(false);

        }else {
            tvDepurtureAirport.setClickable(true);
            tvDepurtureAirport.setEnabled(true);
        }

        if (!ValidationTools.isEmptyOrNull(ReturnAirportDate)){
            tvReturnDate.setText(ReturnAirportDate);
            tvReturnDate.setClickable(false);
            tvReturnDate.setEnabled(false);

        }else {
            tvReturnDate.setClickable(true);
            tvReturnDate.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(ReturnFlt)){
            tvReturnFlt.setText(ReturnFlt);
            tvReturnFlt.setClickable(false);
            tvReturnFlt.setEnabled(false);

        }else {
            tvReturnFlt.setClickable(true);
            tvReturnFlt.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(DepurtureFlt)){
            tvDepurtureFlt.setText(DepurtureFlt);
            tvDepurtureFlt.setClickable(false);
            tvDepurtureFlt.setEnabled(false);

        }else {
            tvDepurtureFlt.setClickable(true);
            tvDepurtureFlt.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(DepurtureTime)){
            tvDepurtureTime.setText(DepurtureTime);
            tvDepurtureTime.setClickable(false);
            tvDepurtureTime.setEnabled(false);

        }else {
            tvDepurtureTime.setClickable(true);
            tvDepurtureTime.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(ReturnTime)){
            tvReturnTime.setText(ReturnTime);
            tvReturnTime.setClickable(false);
            tvReturnTime.setEnabled(false);

        }else {
            tvReturnTime.setClickable(true);
            tvReturnTime.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(DepurtureDate)){
            tvDepurtureDate.setText(DepurtureDate);
            tvDepurtureDate.setClickable(false);
            tvDepurtureDate.setEnabled(false);

        }else {
            tvDepurtureDate.setClickable(true);
            tvDepurtureDate.setEnabled(true);
        }
        if (!ValidationTools.isEmptyOrNull(Hotel)){
            tvHotel.setText(Hotel);

            tvHotel.setClickable(false);
            tvHotel.setEnabled(false);

        }else {
            tvHotel.setClickable(true);
            tvHotel.setEnabled(true);
        }



    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        if (Prefs.getString("Value-Mabda-City", "") != null && Prefs.getString("Value-Mabda-City", "").length() > 1) {
            tvDepurtureAirport.setText(Prefs.getString("Value-Mabda-City2", "انتخاب کنید"));
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Prefs.putString("Value-Mabda-City2", "انتخاب کنید");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tvDepurtureAirport:
                Intent intent = new Intent(this, GetAirportMabdaActivity.class);
                startActivity(intent);

                break;
            case R.id.tvHotel:
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
        }

    }
/*

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        if (view.getTag().equals("timeRaft")) {
            tvDepurtureTime.setText(hourOfDay + ":" + minute);

        }
        if (view.getTag().equals("timeBargasht")) {
            tvReturnTime.setText(hourOfDay + ":" + minute);


        }
    }
*/

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
        geo = false;

        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.set(year, month, day);


        if (view.getTag().equals("DatepickerdialogBargasht")) {
            tvReturnDate.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());
            bargasht = date_server(year, monthOfYear, dayOfMonth);
            Prefs.putString("bargashtfa",tvReturnDate.getText().toString());
            Prefs.putString("bargasht", bargasht);




        }


        if (view.getTag().equals("DatepickerdialogRaft")) {
            year_Min = year;
            monthMin = monthOfYear;
            dayMin = dayOfMonth;
            tvDepurtureDate.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());
            //  tvReturnDate.setText(persianCalendar.getPersianLongDate());
            raft = date_server(year, monthOfYear, dayOfMonth);
            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(year_Min, monthMin, dayMin);


            if (Utility.campareDate(raft,bargasht)){
                tvReturnDate.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());
                datePickerDialog2.initialize(this, year_, month, day);
                datePickerDialog2.setMinDate(persianCalendarDatePicker2);
            }






            Prefs.putString("bargashtfa", tvReturnDate.getText().toString());

            Prefs.putString("raft", raft);
            Prefs.putString("raftfa",tvDepurtureDate.getText().toString());


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
        Log.e("test", view.getTag()+"" );
      /*  if (view.getTag().equals("timeRaft")) {
            tvDepurtureTime.setText(hourOfDay + ":" + minute);

        }
        if (view.getTag().equals("timeBargasht")) {
            tvReturnTime.setText(hourOfDay + ":" + minute);


        }*/
    }
}
