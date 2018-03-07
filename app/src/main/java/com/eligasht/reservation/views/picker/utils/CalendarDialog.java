package com.eligasht.reservation.views.picker.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.views.picker.global.adapter.MonthAdapter;
import com.eligasht.reservation.views.picker.global.enums.TypeUsageOfCalendar;
import com.eligasht.reservation.views.picker.global.listeners.ICallbackCalendarDialog;
import com.eligasht.reservation.views.picker.global.model.CustomDate;
import com.eligasht.reservation.views.picker.global.utils.CalendarRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import calendar.CivilDate;
import calendar.DateConverter;
import calendar.PersianDate;


public class CalendarDialog implements OnClickListener {
    private static AlertDialog alertDialog;
    public boolean f13913a = false;
    public boolean f13914b = false;
    private SimpleDateFormat simpleDateFormat;
    private String f13888B;
    private MonthAdapter monthAdapter;
    private boolean isGregorian;
    private String f13891E;
    private String f13892F;
    private List<Boolean> f13893G = new ArrayList(Arrays.asList(new Boolean[365]));
    private int f13894H = 0;
    private Grid gridLayoutManager;
    private int f13896J;
    private int f13897K;
    private SharedPreferences sharedPrefrences;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private ICallbackCalendarDialog callbackCalendarDialog;
    private String currentshortDate;
    private String fullStartDate;
    private String fullEndDate;
    private int persianStartYear;
    private int persianEndYear;
    private int geoStartYear;
    private int geoEndYear;
    private String fullDate;
    private String typeUsageOfCalendar;
    private boolean isReverseTravel;
    private Activity activity;
    private String shortDate;
    private int ab = -1;
    private int indexDaySelected = -1;
    private Context context;
    private View view;
    private CalendarRecyclerView calendarRv;
    private TextView currentMonth;
    private TextView sat;
    private TextView sun;
    private TextView mon;
    private TextView tue;
    private TextView wed;
    private TextView tur;
    private TextView fri;
    private TextView dateDescription;
    private TextView accept;
    private TextView cancel;
    private TextView changeDateKind;
    private ImageView nextPersian;
    private ImageView prePersian;
    private int f13932u;
    private int f13933v;
    private int f13934w;
    private int f13935x;
    private CalendarTool calendarTool;
    private NumberUtil numberUtil;


    public void create(final Activity activity, Context context, ICallbackCalendarDialog iCallbackCalendarDialog, CustomDate startDate, CustomDate endDate, TypeUsageOfCalendar typeUsageofCalendar) {
        this.context = context;
        sharedPrefrences = this.context.getSharedPreferences("eligasht.com", 0);
        this.activity = activity;
        this.persianStartYear = startDate.getPersianYear();
        this.persianEndYear = endDate.getPersianYear();
        fullEndDate = null;
        fullStartDate = null;
        this.geoStartYear = startDate.getGeoYear();//geo start year
        this.geoEndYear = endDate.getGeoYear();//geo end year
        this.isReverseTravel = true;
        this.callbackCalendarDialog = iCallbackCalendarDialog;
        if (!this.sharedPrefrences.getBoolean("isGregorian", false))
            this.fullDate = startDate.getFullPersian() + "-" + endDate.getFullPersian();
        else
            this.fullDate = startDate.getFullGeo() + "-" + endDate.getFullGeo();


        //1395/2/1-1395/2/10 or 1395/2/1
        this.shortDate = "";//9اسفند-10اسفند or 9اسفند
        switch (typeUsageofCalendar) {
            case HOTEL:
                typeUsageOfCalendar = "Hotel";
                break;
            case Train:
                typeUsageOfCalendar = "Train";
                break;
            case AutoAlert:
                typeUsageOfCalendar = "AutoAlert";
                break;
            case NationalFlight:
                typeUsageOfCalendar = "NationalFlight";
                break;
            case InternationalFlight:
                typeUsageOfCalendar = "InternationalFlight";
                break;

        }
        Builder builder = new Builder(activity);
        this.view = LayoutInflater.from(context).inflate(R.layout.calendar_dialog, null);
        activity.getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
        builder.setView(this.view);
        builder.setCancelable(false);
        initViews();
        initListeners();
        initCalendar();
        alertDialog = builder.create();
        alertDialog.show();


        this.calendarRv.addOnScrollListener(new OnScrollListener() {


            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                new Thread(new ScrollerRunnable()).start();
            }

            class ScrollerRunnable implements Runnable {


                @Override
                public void run() {
                    activity.runOnUiThread(new MainThreadRunnable());
                }

                class MainThreadRunnable implements Runnable {

                    @Override
                    public void run() {
                        if (isGregorian) {
                            currentMonth.setText(UiUtils.getGregorianMonthName((((f13932u + f13894H) - 1) % 12) + 1) + " " + String.valueOf(f13934w));
                        } else {
                            currentMonth.setText(UiUtils.getSolarMonthName((((f13932u + f13894H) - 1) % 12) + 1) + " " + numberUtil.toFarsiString(String.valueOf(f13933v)));
                        }
                    }
                }
            }
        });
    }

    public void create(final Activity activity, Context context, ICallbackCalendarDialog iCallbackCalendarDialog, CustomDate startDate, TypeUsageOfCalendar typeUsageofCalendar) {
        this.context = context;
        this.activity = activity;
        sharedPrefrences = this.context.getSharedPreferences("eligasht.com", 0);
        this.persianStartYear = startDate.getPersianYear();
        this.persianEndYear = 0;
        fullEndDate = null;
        fullStartDate = null;
        this.geoStartYear = startDate.getGeoYear();//geo start year
        this.geoEndYear = 0;//geo end year
        this.isReverseTravel = false;
        this.callbackCalendarDialog = iCallbackCalendarDialog;

        if (!this.sharedPrefrences.getBoolean("isGregorian", false))
            this.fullDate = startDate.getFullPersian(); //1395/2/1-1395/2/10 or 1395/2/1
        else
            this.fullDate = startDate.getFullGeo(); //1395/2/1-1395/2/10 or 1395/2/1
        this.shortDate = "";//9اسفند-10اسفند or 9اسفند
        switch (typeUsageofCalendar) {
            case HOTEL:
                typeUsageOfCalendar = "Hotel";
                break;
            case Train:
                typeUsageOfCalendar = "Train";
                break;
            case AutoAlert:
                typeUsageOfCalendar = "AutoAlert";
                break;
            case NationalFlight:
                typeUsageOfCalendar = "NationalFlight";
                break;
            case InternationalFlight:
                typeUsageOfCalendar = "InternationalFlight";
                break;

        }
        Builder builder = new Builder(activity);
        this.view = LayoutInflater.from(context).inflate(R.layout.calendar_dialog, null);
        activity.getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
        builder.setView(this.view);
        builder.setCancelable(false);
        initViews();
        initListeners();
        initCalendar();
        alertDialog = builder.create();
        alertDialog.show();


        this.calendarRv.addOnScrollListener(new OnScrollListener() {


            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                new Thread(new ScrollerRunnable()).start();
            }

            class ScrollerRunnable implements Runnable {


                @Override
                public void run() {
                    activity.runOnUiThread(new MainThreadRunnable());
                }

                class MainThreadRunnable implements Runnable {

                    @Override
                    public void run() {
                        if (isGregorian) {
                            currentMonth.setText(UiUtils.getGregorianMonthName((((f13932u + f13894H) - 1) % 12) + 1) + " " + String.valueOf(f13934w));
                        } else {
                            currentMonth.setText(UiUtils.getSolarMonthName((((f13932u + f13894H) - 1) % 12) + 1) + " " + numberUtil.toFarsiString(String.valueOf(f13933v)));
                        }
                    }
                }
            }
        });
    }

    public void create(final Activity activity, Context context, ICallbackCalendarDialog iCallbackCalendarDialog, boolean reverse, TypeUsageOfCalendar typeUsageofCalendar) {
        this.context = context;
        this.activity = activity;
        sharedPrefrences = this.context.getSharedPreferences("eligasht.com", 0);
        this.persianStartYear = 0;//persian start Year
        this.persianEndYear = 0;//persian end Year
        this.geoStartYear = 0;//geo start year
        this.geoEndYear = 0;//geo end year
        this.isReverseTravel = reverse;
        fullEndDate = null;
        fullStartDate = null;
        this.fullDate = null;
        this.shortDate = null;
        this.callbackCalendarDialog = iCallbackCalendarDialog;
        switch (typeUsageofCalendar) {
            case HOTEL:
                typeUsageOfCalendar = "Hotel";
                break;
            case Train:
                typeUsageOfCalendar = "Train";
                break;
            case AutoAlert:
                typeUsageOfCalendar = "AutoAlert";
                break;
            case NationalFlight:
                typeUsageOfCalendar = "NationalFlight";
                break;
            case InternationalFlight:
                typeUsageOfCalendar = "InternationalFlight";
                break;

        }
        this.fullDate = null; //1395/2/1-1395/2/10 or 1395/2/1
        this.shortDate = null;//9اسفند-10اسفند or 9اسفند
        Builder builder = new Builder(activity);
        this.view = LayoutInflater.from(context).inflate(R.layout.calendar_dialog, null);
        activity.getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
        builder.setView(this.view);
        builder.setCancelable(false);
        initViews();
        initListeners();
        initCalendar();
        alertDialog = builder.create();
        alertDialog.show();


        this.calendarRv.addOnScrollListener(new OnScrollListener() {


            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                new Thread(new ScrollerRunnable()).start();
            }

            class ScrollerRunnable implements Runnable {


                @Override
                public void run() {
                    activity.runOnUiThread(new MainThreadRunnable());
                }

                class MainThreadRunnable implements Runnable {

                    @Override
                    public void run() {
                        if (isGregorian) {
                            currentMonth.setText(UiUtils.getGregorianMonthName((((f13932u + f13894H) - 1) % 12) + 1) + " " + String.valueOf(f13934w));
                        } else {
                            currentMonth.setText(UiUtils.getSolarMonthName((((f13932u + f13894H) - 1) % 12) + 1) + " " + numberUtil.toFarsiString(String.valueOf(f13933v)));
                        }
                    }
                }
            }
        });
    }

    private void initViews() {
        calendarRv = view.findViewById(R.id.calendar_rv);
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        calendarRv.setLayoutAnimation(controller);
        calendarRv.scheduleLayoutAnimation();
        currentMonth = view.findViewById(R.id.current_month);
        dateDescription = view.findViewById(R.id.date_description);
        accept = view.findViewById(R.id.accept);
        cancel = view.findViewById(R.id.cancel);
        changeDateKind = view.findViewById(R.id.change_date_kind);
        sat = view.findViewById(R.id.sat);
        sun = view.findViewById(R.id.sun);
        mon = view.findViewById(R.id.mon);
        tue = view.findViewById(R.id.tue);
        wed = view.findViewById(R.id.wed);
        tur = view.findViewById(R.id.tur);
        fri = view.findViewById(R.id.fri);
        nextPersian = view.findViewById(R.id.next_persian);
        prePersian = view.findViewById(R.id.pre_persian);
        gridLayoutManager = new Grid(this.context, 7);
        calendarRv.setNestedScrollingEnabled(false);
    }

    private void initListeners() {
        accept.setOnClickListener(this);
        cancel.setOnClickListener(this);
        changeDateKind.setOnClickListener(this);
        nextPersian.setOnClickListener(this);
        prePersian.setOnClickListener(this);
    }

    private void initCalendar() {
        numberUtil = new NumberUtil(context);
        if (fullDate != null) {
            fullDate = splitDate(fullDate);

        }
        initTextViews();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.accept:
                if (!((!this.isReverseTravel || this.fullStartDate == null || this.fullEndDate == null) && (this.isReverseTravel || this.fullStartDate == null))) {
                    this.callbackCalendarDialog.onDateSelected(getDateByString(this.fullStartDate), getDateByString(this.fullEndDate), this.sharedPrefrences.getBoolean("isGregorian", false));
                }
                alertDialog.dismiss();
                return;
            case R.id.cancel:
                if (this.fullDate != null) {
                    if (this.sharedPrefrences.getBoolean("isGregorian", false) && (this.fullDate.startsWith("۱") || this.fullDate.startsWith("1"))) {
                        this.sharedPrefrences.edit().putBoolean("isGregorian", false).apply();
                        if (!((!this.isReverseTravel || this.fullStartDate == null || this.fullEndDate == null) && (this.isReverseTravel || this.fullStartDate == null))) {
                            // this.callbackCalendarDialog.onDateSelected(this.currentshortDate, this.fullStartDate, this.fullEndDate, this.sharedPrefrences.getBoolean("isGregorian", false));
                        }
                    } else if (!this.sharedPrefrences.getBoolean("isGregorian", false) && (this.fullDate.startsWith("۲") || this.fullDate.startsWith("2"))) {
                        this.sharedPrefrences.edit().putBoolean("isGregorian", true).apply();
                        if (!((!this.isReverseTravel || this.fullStartDate == null || this.fullEndDate == null) && (this.isReverseTravel || this.fullStartDate == null))) {
                            // this.callbackCalendarDialog.onDateSelected(this.currentshortDate, this.fullStartDate, this.fullEndDate, this.sharedPrefrences.getBoolean("isGregorian", false));
                        }
                    }
                }
                alertDialog.dismiss();
                return;
            case R.id.change_date_kind:
                changeType();
                this.f13894H = 0;
                if (this.sharedPrefrences.getBoolean("isGregorian", false)) {
                    this.sharedPrefrences.edit().putBoolean("isGregorian", false).apply();
                    initFullDate(this.fullDate);
                    initTextViews();
                    if (this.fullDate != null && this.fullDate.contains("-")) {
                        this.dateDescription.setText(this.numberUtil.toFarsiString(this.fullDate.split("-")[0].split("/")[2]) + " " + UiUtils.getSolarMonthName(Integer.parseInt(this.fullDate.split("-")[0].split("/")[1])) + " - " + this.numberUtil.toFarsiString(this.fullDate.split("-")[1].split("/")[2]) + " " + UiUtils.getSolarMonthName(Integer.parseInt(this.fullDate.split("-")[1].split("/")[1])));
                    } else if (this.fullDate != null) {
                        this.dateDescription.setText(this.numberUtil.toFarsiString(this.fullDate.split("/")[2]) + " " + UiUtils.getSolarMonthName(Integer.parseInt(this.fullDate.split("/")[1])));
                    }
                } else {
                    this.sharedPrefrences.edit().putBoolean("isGregorian", true).apply();
                    initFullDate(this.fullDate);
                    initTextViews();
                    if (this.fullDate != null && this.fullDate.contains("-")) {
                        this.dateDescription.setText(this.fullDate.split("-")[0].split("/")[2] + " " + UiUtils.getGregorianMonthName(Integer.parseInt(this.fullDate.split("-")[0].split("/")[1])) + " - " + this.fullDate.split("-")[1].split("/")[2] + " " + UiUtils.getGregorianMonthName(Integer.parseInt(this.fullDate.split("-")[1].split("/")[1])));
                    } else if (this.fullDate != null) {
                        this.dateDescription.setText(this.fullDate.split("/")[2] + " " + UiUtils.getGregorianMonthName(Integer.parseInt(this.fullDate.split("/")[1])));
                    }
                }
                if (this.fullDate == null || !this.fullDate.contains("-")) {
                    this.fullStartDate = this.fullDate;
                } else {
                    this.fullStartDate = this.fullDate.split("-")[0];
                    this.fullEndDate = this.fullDate.split("-")[1];
                }
                this.currentshortDate = this.dateDescription.getText().toString();
                return;
            case R.id.next_persian:
                if (this.sharedPrefrences.getBoolean("isGregorian", false)) {
                    Log.e("f13894H", String.valueOf(f13894H));
                    if (f13894H + 1 == 10)
                        return;
                    this.f13894H++;
                    if ((this.f13894H * 31) + 35 < 366) {

                        this.calendarRv.smoothScrollToPosition((this.f13894H * 31) + 35);
                    } else {
                        this.f13894H--;
                    }
                    if (this.f13894H + this.f13932u != 13) {
                        return;
                    }
                    if (this.isGregorian) {
                        this.f13934w++;
                        return;
                    } else {
                        this.f13933v++;
                        return;
                    }
                }

                if (f13894H - 1 == -1)
                    return;
                this.f13894H--;
                if (this.f13894H > -1) {
                    this.calendarRv.smoothScrollToPosition(((this.f13894H - 1) * 31) + 31);
                } else {
                    this.f13894H = 0;
                }
                if (this.f13894H + this.f13932u != 12) {
                    return;
                }
                if (this.isGregorian) {
                    this.f13934w--;
                    return;
                } else {
                    this.f13933v--;
                    return;
                }
            case R.id.pre_persian:
                if (this.sharedPrefrences.getBoolean("isGregorian", false)) {
                    this.f13894H--;
                    if (this.f13894H > -1) {
                        this.calendarRv.smoothScrollToPosition(((this.f13894H - 1) * 31) + 31);
                    } else {
                        this.f13894H = 0;
                    }
                    if (this.f13894H + this.f13932u != 12) {
                        return;
                    }
                    if (this.isGregorian) {
                        this.f13934w--;
                        return;
                    } else {
                        this.f13933v--;
                        return;
                    }
                }


                this.f13894H++;
                if ((this.f13894H * 31) + 35 < 366) {
                    this.calendarRv.smoothScrollToPosition((this.f13894H * 31) + 35);
                } else {
                    this.f13894H--;
                }
                if (this.f13894H + this.f13932u != 13) {
                    return;
                }
                if (this.isGregorian) {
                    this.f13934w++;
                    return;
                } else {
                    this.f13933v++;
                    return;
                }
            default:
                return;
        }
    }

    private CustomDate getDateByString(String str) {
        if (str == null)
            return null;

        CustomDate customDate = new CustomDate(numberUtil.toEnglishString(str.split("/")[0]), numberUtil.toEnglishString(str.split("/")[1]), numberUtil.toEnglishString(str.split("/")[2]));
        return customDate;

    }


    private void initFullDate(String fullDate) {
        if (this.sharedPrefrences.getBoolean("isGregorian", false)) {
            if (fullDate == null) {
                return;
            }
            if (fullDate.contains("-")) {
                this.fullDate = UiUtils.m18492s(fullDate.split("-")[0]).replace("-", "/") + "-" + UiUtils.m18492s(fullDate.split("-")[1]).replace("-", "/");
            } else {
                this.fullDate = UiUtils.m18492s(fullDate).replace("-", "/");
            }
        } else if (fullDate == null) {
        } else {
            if (fullDate.contains("-")) {
                this.fullDate = UiUtils.m18489p(fullDate.split("-")[0]).replace("-", "/") + "-" + UiUtils.m18489p(fullDate.split("-")[1]).replace("-", "/");
            } else {
                this.fullDate = UiUtils.m18489p(fullDate);
            }
        }
    }

    private String splitDate(String str) {
        if (this.sharedPrefrences.getBoolean("isGregorian", false)) {
            if (!str.startsWith("1")) {
                return str;
            }
            if (str.contains("-")) {
                return UiUtils.m18492s(str.split("-")[0]).replace("-", "/") + "-" + UiUtils.m18492s(str.split("-")[1]).replace("-", "/");
            }
            return UiUtils.m18492s(str);
        } else if (!str.startsWith("2")) {
            return str;
        } else {
            if (str.contains("-")) {
                return UiUtils.m18489p(str.split("-")[0]).replace("-", "/") + "-" + UiUtils.m18489p(str.split("-")[1]).replace("-", "/");
            }
            return UiUtils.m18489p(str).replace("-", "/");
        }
    }

    private void initTextViews() {
        if (this.fullDate != null && (this.fullDate.startsWith("1") || this.fullDate.startsWith("۱"))) {
            this.isGregorian = false;
        } else if (this.fullDate == null || !(this.fullDate.startsWith("2") || this.fullDate.startsWith("۲"))) {
            this.isGregorian = this.sharedPrefrences.getBoolean("isGregorian", false);
        } else {
            this.isGregorian = true;
        }
        this.sharedPrefrences.edit().putBoolean("isGregorian", this.isGregorian).apply();
        if (this.isGregorian) {

            this.sat.setText("S");
            this.sun.setText("F");
            this.mon.setText("T");
            this.tue.setText("W");
            this.wed.setText("T");
            this.tur.setText("M");
            this.fri.setText("S");
            this.changeDateKind.setText("شمسی");
            initAdapter(this.isGregorian);
            if (context.getResources().getBoolean(R.bool.isTablet)) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (((double) (this.displayMetrics.density * 240.0f)) + 0.5d));
                layoutParams.setMargins((int) (((double) (2.0f * this.displayMetrics.density)) - 40d), (int) (((double) (this.displayMetrics.density * 8.0f)) + 0.5d), (int) (((double) (10.0f * this.displayMetrics.density)) + 7d), 0);
                layoutParams.addRule(3, R.id.fri);
                this.calendarRv.setLayoutParams(layoutParams);
            } else {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (((double) (this.displayMetrics.density * 240.0f)) + 0.5d));
                layoutParams.setMargins((int) (((double) (4.0f * this.displayMetrics.density)) + 0.5d), (int) (((double) (this.displayMetrics.density * 8.0f)) + 0.5d), (int) (((double) (2.0f * this.displayMetrics.density)) + 0.5d), 0);
                layoutParams.addRule(3, R.id.fri);
                this.calendarRv.setLayoutParams(layoutParams);
            }
            return;
        }

        this.sat.setText("ش");
        this.sun.setText("ی");
        this.mon.setText("د");
        this.tue.setText("س");
        this.wed.setText("چ");
        this.tur.setText("پ");
        this.fri.setText("ج");
        this.changeDateKind.setText("میلادی");
        initAdapter(this.isGregorian);
        if (context.getResources().getBoolean(R.bool.isTablet)) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (((double) (this.displayMetrics.density * 240.0f)) + 0.5d));
            layoutParams.setMargins(-32, (int) (((double) (this.displayMetrics.density * 8.0f)) + 0.5d), (int) (((double) (15.0f * this.displayMetrics.density)) + .5d), 0);
            layoutParams.addRule(3, R.id.fri);
            this.calendarRv.setLayoutParams(layoutParams);
        } else {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (((double) (this.displayMetrics.density * 240.0f)) + 0.5d));
            layoutParams.setMargins(0, (int) (((double) (this.displayMetrics.density * 8.0f)) + 0.5d), (int) (((double) (6.0f * this.displayMetrics.density)) + 0.5d), 0);
            layoutParams.addRule(3, R.id.fri);
            this.calendarRv.setLayoutParams(layoutParams);
        }

    }

    private void initAdapter(boolean isGeorgian) {
        if (this.fullDate != null) {
            this.fullDate = UiUtils.m18430A(this.fullDate);
        }
        this.simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        this.f13888B = this.numberUtil.toEnglishString(this.simpleDateFormat.format(new Date()));
        this.calendarTool = new CalendarTool(Integer.valueOf(this.f13888B.split("/")[0]).intValue(), Integer.valueOf(this.f13888B.split("/")[1]).intValue(), Integer.valueOf(this.f13888B.split("/")[2]).intValue());
        if (isGeorgian) {
            if (this.fullDate != null && this.fullDate.startsWith("1")) {
                initFullDate(this.fullDate);
            }
            if (this.isReverseTravel) {
                if (this.geoStartYear == 0 || this.geoStartYear != this.calendarTool.m18353d()) {
                    this.f13934w = this.calendarTool.m18353d();
                } else {
                    this.f13934w = this.geoStartYear;
                }
                if (this.geoEndYear == 0 || this.geoEndYear != this.calendarTool.m18353d()) {
                    this.f13897K = this.calendarTool.m18353d();
                } else {
                    this.f13897K = this.geoEndYear;
                }
            } else if (this.geoStartYear == 0 || this.geoStartYear != this.calendarTool.m18353d()) {
                this.f13934w = this.calendarTool.m18353d();
            } else {
                this.f13934w = this.geoStartYear;
            }
            if (this.calendarTool.m18355f() - UiUtils.m18478j(this.calendarTool.m18360k()) > 0) {
                this.f13935x = 7 - Math.abs((this.calendarTool.m18355f() - UiUtils.m18478j(this.calendarTool.m18360k())) % 7);
            } else {
                this.f13935x = UiUtils.m18478j(this.calendarTool.m18360k()) - this.calendarTool.m18355f();
            }
            this.f13932u = this.calendarTool.m18354e();
            this.currentMonth.setText(UiUtils.getGregorianMonthName(this.f13932u) + " " + this.f13934w);
            if (this.shortDate != null) {
                this.dateDescription.setText(this.shortDate);
            } else if (this.typeUsageOfCalendar.equals("Train") || this.typeUsageOfCalendar.equals("NationalFlight") || this.typeUsageOfCalendar.equals("InternationalFlight")) {
                this.dateDescription.setText(R.string.select_dep);
            } else if (this.typeUsageOfCalendar.equals("Hotel")) {
                this.dateDescription.setText(R.string.select_che_in);
            } else if (this.typeUsageOfCalendar.equals("AutoAlert")) {
                this.dateDescription.setText(R.string.select_start_date);
            }
            this.calendarRv.setLayoutManager(this.gridLayoutManager);
            this.calendarRv.setHasFixedSize(true);
            Collections.fill(this.f13893G, Boolean.FALSE);
            if (this.isReverseTravel) {
                if (this.fullDate != null && this.fullDate.contains("-")) {
                    if (this.geoStartYear == this.calendarTool.m18353d()) {
                        this.f13893G.set((UiUtils.m18456b(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[2]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[0]))).intValue()) - UiUtils.m18456b(1, this.calendarTool.m18354e(), this.calendarTool.m18353d())) + this.f13935x, Boolean.valueOf(true));
                    } else {
                        this.f13893G.set(((UiUtils.m18456b(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[2]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[0]))).intValue()) + UiUtils.m18437a(true, this.calendarTool.m18353d())) - UiUtils.m18456b(1, this.calendarTool.m18354e(), this.calendarTool.m18353d())) + this.f13935x, Boolean.valueOf(true));
                    }
                    if (this.geoEndYear == this.calendarTool.m18353d()) {
                        this.f13893G.set((UiUtils.m18456b(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[2]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[0]))).intValue()) - UiUtils.m18456b(1, this.calendarTool.m18354e(), this.calendarTool.m18353d())) + this.f13935x, Boolean.valueOf(true));
                    } else {
                        this.f13893G.set(((UiUtils.m18456b(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[2]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[0]))).intValue()) + UiUtils.m18437a(true, this.calendarTool.m18353d())) - UiUtils.m18456b(1, this.calendarTool.m18354e(), this.calendarTool.m18353d())) + this.f13935x, Boolean.valueOf(true));
                    }
                    this.accept.setEnabled(true);
                    this.accept.setBackgroundResource(R.drawable.radious_accent_btn_calendar);
                }
            } else if (this.fullDate != null) {
                if (this.geoStartYear == this.calendarTool.m18353d()) {
                    this.f13893G.set((UiUtils.m18456b(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[2]))), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[0]))).intValue()) - UiUtils.m18456b(1, this.calendarTool.m18354e(), this.calendarTool.m18353d())) + this.f13935x, Boolean.valueOf(true));
                } else {
                    this.f13893G.set(((UiUtils.m18456b(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[2]))), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[0]))).intValue()) + UiUtils.m18437a(true, this.calendarTool.m18353d())) - UiUtils.m18456b(1, this.calendarTool.m18354e(), this.calendarTool.m18353d())) + this.f13935x, Boolean.valueOf(true));
                }
            }
            this.monthAdapter = new MonthAdapter(this.activity, this.context, this.f13935x, this.calendarTool.m18355f(), this.calendarTool.m18353d(), UiUtils.m18456b(1, this.calendarTool.m18354e(), this.calendarTool.m18353d()), this.f13893G, this.isReverseTravel, isGeorgian, this.fullDate, this.typeUsageOfCalendar);
            this.calendarRv.setAdapter(this.monthAdapter);
            initClickOnCalendar(isGeorgian);
            return;
        }
        if (this.fullDate != null && this.fullDate.startsWith("2")) {
            initFullDate(this.fullDate);
        }
        this.simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        this.f13888B = this.numberUtil.toEnglishString(this.simpleDateFormat.format(new Date()));
        this.calendarTool = new CalendarTool(Integer.valueOf(this.f13888B.split("/")[0]), Integer.valueOf(this.f13888B.split("/")[1]), Integer.valueOf(this.f13888B.split("/")[2]).intValue());
        if (this.isReverseTravel) {
            if (this.persianStartYear == 0 || this.persianStartYear != this.calendarTool.m18348a()) {
                this.f13933v = this.calendarTool.m18348a();
            } else {
                this.f13933v = this.persianStartYear;
            }
            if (this.persianEndYear == 0 || this.persianEndYear != this.calendarTool.m18348a()) {
                this.f13896J = this.calendarTool.m18348a();
            } else {
                this.f13896J = this.persianEndYear;
            }
        } else if (this.persianStartYear == 0 || this.persianStartYear != this.calendarTool.m18348a()) {
            this.f13933v = this.calendarTool.m18348a();
        } else {
            this.f13933v = this.persianStartYear;
        }
        if (this.calendarTool.m18352c() - UiUtils.m18476i(this.calendarTool.m18360k()) > 0) {
            this.f13935x = 7 - Math.abs((this.calendarTool.m18352c() - UiUtils.m18476i(this.calendarTool.m18360k())) % 7);
        } else {
            this.f13935x = UiUtils.m18476i(this.calendarTool.m18360k()) - this.calendarTool.m18352c();
        }
        this.f13932u = this.calendarTool.m18350b();
        this.currentMonth.setText(UiUtils.getSolarMonthName(this.f13932u) + " " + this.numberUtil.toFarsiString(String.valueOf(this.f13933v)));
        if (this.shortDate != null) {
            this.dateDescription.setText(this.shortDate);
        } else if (this.typeUsageOfCalendar.equals("Train") || this.typeUsageOfCalendar.equals("NationalFlight") || this.typeUsageOfCalendar.equals("InternationalFlight")) {
            this.dateDescription.setText("انتخاب تاریخ رفت");
        } else if (this.typeUsageOfCalendar.equals("Hotel")) {
            this.dateDescription.setText("انتخاب تاریخ ورود");
        } else if (this.typeUsageOfCalendar.equals("AutoAlert")) {
            this.dateDescription.setText("انتخاب تاریخ شروع");
        }
        this.calendarRv.setLayoutManager(this.gridLayoutManager);
        this.calendarRv.setHasFixedSize(true);
        Collections.fill(this.f13893G, Boolean.FALSE);
        if (this.isReverseTravel) {
            if (this.fullDate != null && this.fullDate.contains("-")) {
                if (this.persianStartYear == this.calendarTool.m18348a()) {
                    this.f13893G.set((UiUtils.m18433a(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[2]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[0]))).intValue()) - UiUtils.m18433a(1, this.calendarTool.m18350b(), this.calendarTool.m18348a())) + this.f13935x, Boolean.valueOf(true));
                } else {
                    this.f13893G.set(((UiUtils.m18433a(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[2]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[0].split("/")[0]))).intValue()) + UiUtils.m18437a(false, this.calendarTool.m18348a())) - UiUtils.m18433a(1, this.calendarTool.m18350b(), this.calendarTool.m18348a())) + this.f13935x, Boolean.valueOf(true));
                }
                if (this.persianEndYear == this.calendarTool.m18348a()) {
                    this.f13893G.set((UiUtils.m18433a(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[2]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[0]))).intValue()) - UiUtils.m18433a(1, this.calendarTool.m18350b(), this.calendarTool.m18348a())) + this.f13935x, Boolean.valueOf(true));
                } else {
                    this.f13893G.set(((UiUtils.m18433a(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[2]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("-")[1].split("/")[0]))).intValue()) + UiUtils.m18437a(false, this.calendarTool.m18348a())) - UiUtils.m18433a(1, this.calendarTool.m18350b(), this.calendarTool.m18348a())) + this.f13935x, Boolean.valueOf(true));
                }
                this.accept.setEnabled(true);
                this.accept.setBackgroundResource(R.drawable.radious_accent_btn_calendar);
            }
        } else if (this.fullDate != null) {
            if (this.persianStartYear == this.calendarTool.m18348a()) {
                this.f13893G.set((UiUtils.m18433a(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[2]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[0]))).intValue()) - UiUtils.m18433a(1, this.calendarTool.m18350b(), this.calendarTool.m18348a())) + this.f13935x, Boolean.valueOf(true));
            } else {
                this.f13893G.set(((UiUtils.m18433a(Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[2]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[1]))).intValue(), Integer.valueOf(this.numberUtil.toEnglishString(String.valueOf(this.fullDate.split("/")[0]))).intValue()) + UiUtils.m18437a(false, this.calendarTool.m18348a())) - UiUtils.m18433a(1, this.calendarTool.m18350b(), this.calendarTool.m18348a())) + this.f13935x, Boolean.valueOf(true));
            }
        }
        this.monthAdapter = new MonthAdapter(this.activity, this.context, this.f13935x, this.calendarTool.m18352c(), this.f13933v, UiUtils.m18433a(1, this.calendarTool.m18350b(), this.f13933v), this.f13893G, this.isReverseTravel, isGeorgian, this.fullDate, this.typeUsageOfCalendar);
        this.calendarRv.setAdapter(this.monthAdapter);
        initClickOnCalendar(isGeorgian);
    }

    private void initClickOnCalendar(final boolean isGeorgian) {
        this.monthAdapter.setOnDateSelect(new MonthAdapter.DateSelected() {


            public void onDateSelected(View view, int dateSelectedIndex) {
                if (isReverseTravel) {
                    if (!(ab == -1 || indexDaySelected == -1)) {
                        ab = -1;
                        indexDaySelected = -1;
                        accept.setEnabled(false);
                        accept.setBackgroundResource(R.drawable.radious_gray_btn_hotel);
                    }
                    if (ab == -1) {
                        ab = dateSelectedIndex;
                        accept.setEnabled(false);
                        accept.setBackgroundResource(R.drawable.radious_gray_btn_hotel);
                    } else if (ab > dateSelectedIndex) {
                        ab = dateSelectedIndex;
                        f13913a = false;
                    } else if (ab == dateSelectedIndex && (typeUsageOfCalendar.equals("Hotel") || typeUsageOfCalendar.equals("AutoAlert"))) {
                        f13913a = false;
                    } else {
                        indexDaySelected = dateSelectedIndex;
                        accept.setEnabled(true);
                        accept.setBackgroundResource(R.drawable.radious_accent_btn_calendar);
                    }
                } else {
                    accept.setEnabled(true);
                    accept.setBackgroundResource(R.drawable.radious_accent_btn_calendar);
                }
                if (isGeorgian) {
                    if ((dateSelectedIndex - f13935x) + UiUtils.m18456b(1, calendarTool.m18354e(), calendarTool.m18353d()) > UiUtils.m18437a(true, calendarTool.m18353d())) {
                        m18313a(UiUtils.m18434a((dateSelectedIndex - f13935x) + UiUtils.m18456b(1, calendarTool.m18354e(), calendarTool.m18353d() + 1), calendarTool.m18353d() + 1, isGeorgian), calendarTool.m18353d() + 1, (dateSelectedIndex - f13935x) + UiUtils.m18456b(1, calendarTool.m18354e(), calendarTool.m18353d() + 1));
                    } else {
                        m18313a(UiUtils.m18434a((dateSelectedIndex - f13935x) + UiUtils.m18456b(1, calendarTool.m18354e(), calendarTool.m18353d()), calendarTool.m18353d(), isGeorgian), calendarTool.m18353d(), (dateSelectedIndex - f13935x) + UiUtils.m18456b(1, calendarTool.m18354e(), calendarTool.m18353d()));
                    }
                } else if ((dateSelectedIndex - f13935x) + UiUtils.m18433a(1, calendarTool.m18350b(), calendarTool.m18348a()) > UiUtils.m18437a(false, calendarTool.m18348a())) {
                    m18313a(UiUtils.m18434a((dateSelectedIndex - f13935x) + UiUtils.m18433a(1, calendarTool.m18350b(), calendarTool.m18348a() + 1), calendarTool.m18348a() + 1, isGeorgian), calendarTool.m18348a() + 1, (dateSelectedIndex - f13935x) + UiUtils.m18433a(1, calendarTool.m18350b(), calendarTool.m18348a() + 1));
                } else {
                    m18313a(UiUtils.m18434a((dateSelectedIndex - f13935x) + UiUtils.m18433a(1, calendarTool.m18350b(), calendarTool.m18348a()), calendarTool.m18348a(), isGeorgian), calendarTool.m18348a(), (dateSelectedIndex - f13935x) + UiUtils.m18433a(1, calendarTool.m18350b(), calendarTool.m18348a()));
                }
            }
        });
    }

    private void changeType() {


        if (fullStartDate != null && !fullStartDate.isEmpty()) {
            fullDate = fullStartDate;
            if (numberUtil.toEnglishString(fullStartDate).startsWith("1")) {

                persianStartYear = Integer.parseInt(fullStartDate.split("/")[0]);
                geoStartYear = persianDateToGeoYear(fullStartDate);

            } else {
                persianStartYear = geotoPErsianYear(fullStartDate);
                geoStartYear = Integer.parseInt(fullStartDate.split("/")[0]);

            }
            if (fullEndDate != null && !fullEndDate.isEmpty()) {
                fullDate = fullStartDate + "-" + fullEndDate;
                if (numberUtil.toEnglishString(fullEndDate).startsWith("1")) {

                    persianEndYear = Integer.parseInt(fullEndDate.split("/")[0]);
                    geoEndYear = persianDateToGeoYear(fullEndDate);

                } else {
                    persianEndYear = geotoPErsianYear(fullEndDate);
                    geoEndYear = Integer.parseInt(fullEndDate.split("/")[0].toString());

                }

            }

        }
    }

    private int persianDateToGeoYear(String fullPersianDate) {

        int year = Integer.parseInt(fullPersianDate.split("/")[0]);
        int month = Integer.parseInt(fullPersianDate.split("/")[1]);
        int day = Integer.parseInt(fullPersianDate.split("/")[2]);
        PersianDate persianDate = new PersianDate(year, month, day);
        CivilDate civilDate = DateConverter.persianToCivil(persianDate);
        return civilDate.getYear();

    }

    private int geotoPErsianYear(String fullGeoYear) {
        int year = Integer.parseInt(fullGeoYear.split("/")[0]);
        int month = Integer.parseInt(fullGeoYear.split("/")[1]);
        int day = Integer.parseInt(fullGeoYear.split("/")[2]);
        CivilDate civilDate = new CivilDate(year, month, day);
        PersianDate persianDate = DateConverter.civilToPersian(civilDate);
        return persianDate.getYear();
    }


    private void m18313a(int i, int i2, int i3) {
        if (this.f13913a && this.f13914b) {
            this.f13913a = false;
            this.f13914b = false;
        }
        if (this.f13913a) {
            if (this.isGregorian) {
                m18316a(this.f13891E, i2 + "/" + UiUtils.m18494u(String.valueOf(UiUtils.m18432a(i3, i2))) + "/" + UiUtils.m18494u(String.valueOf(i)));
            } else {
                m18316a(this.f13891E, this.numberUtil.toFarsiString(String.valueOf(i2)) + "/" + this.numberUtil.toFarsiString(UiUtils.m18494u(String.valueOf(UiUtils.m18455b(i3, i2)))) + "/" + this.numberUtil.toFarsiString(UiUtils.m18494u(String.valueOf(i))));
            }
        } else if (this.isGregorian) {
            m18316a(i2 + "/" + UiUtils.m18494u(String.valueOf(String.valueOf(UiUtils.m18432a(i3, i2)))) + "/" + UiUtils.m18494u(String.valueOf(i)), this.f13891E);
        } else {
            m18316a(this.numberUtil.toFarsiString(String.valueOf(i2)) + "/" + this.numberUtil.toFarsiString(UiUtils.m18494u(String.valueOf(UiUtils.m18455b(i3, i2)))) + "/" + this.numberUtil.toFarsiString(UiUtils.m18494u(String.valueOf(i))), this.f13891E);
        }
        if (this.isReverseTravel) {
            if (this.f13913a) {
                if (this.isGregorian) {
                    this.dateDescription.setText(this.f13892F + " - " + UiUtils.m18494u(String.valueOf(i)) + " " + UiUtils.getGregorianMonthName(UiUtils.m18432a(i3, i2)));
                } else {
                    this.dateDescription.setText(this.f13892F + " - " + this.numberUtil.toFarsiString(UiUtils.m18494u(String.valueOf(i))) + " " + UiUtils.getSolarMonthName(UiUtils.m18455b(i3, i2)));
                }
                if (this.isGregorian) {
                    this.f13897K = i2;
                } else {
                    this.f13896J = i2;
                }
                this.f13914b = true;
            } else {
                if (this.isGregorian) {
                    if (this.typeUsageOfCalendar.equals("Train") || this.typeUsageOfCalendar.equals("NationalFlight") || this.typeUsageOfCalendar.equals("InternationalFlight")) {
                        this.dateDescription.setText(UiUtils.m18494u(String.valueOf(i)) + " " + UiUtils.getGregorianMonthName(UiUtils.m18432a(i3, i2)) + " - Select return date");
                    } else if (this.typeUsageOfCalendar.equals("Hotel")) {
                        this.dateDescription.setText(UiUtils.m18494u(String.valueOf(i)) + " " + UiUtils.getGregorianMonthName(UiUtils.m18432a(i3, i2)) + " - Select checkout date");
                    } else if (this.typeUsageOfCalendar.equals("AutoAlert")) {
                        this.dateDescription.setText(UiUtils.m18494u(String.valueOf(i)) + " " + UiUtils.getGregorianMonthName(UiUtils.m18432a(i3, i2)) + " - Select start date");
                    }
                    this.f13892F = this.dateDescription.getText().toString().split("-")[0];
                    this.f13891E = String.valueOf(i2) + "/" + UiUtils.m18494u(String.valueOf(UiUtils.m18432a(i3, i2))) + "/" + UiUtils.m18494u(String.valueOf(i));
                } else {
                    if (this.typeUsageOfCalendar.equals("Train") || this.typeUsageOfCalendar.equals("NationalFlight") || this.typeUsageOfCalendar.equals("InternationalFlight")) {
                        this.dateDescription.setText(this.numberUtil.toFarsiString(UiUtils.m18494u(String.valueOf(i))) + " " + UiUtils.getSolarMonthName(UiUtils.m18455b(i3, i2)) + " - انتخاب تاریخ برگشت");
                    } else if (this.typeUsageOfCalendar.equals("Hotel")) {
                        this.dateDescription.setText(this.numberUtil.toFarsiString(UiUtils.m18494u(String.valueOf(i))) + " " + UiUtils.getSolarMonthName(UiUtils.m18455b(i3, i2)) + " - انتخاب تاریخ خروج");
                    } else if (this.typeUsageOfCalendar.equals("AutoAlert")) {
                        this.dateDescription.setText(this.numberUtil.toFarsiString(UiUtils.m18494u(String.valueOf(i))) + " " + UiUtils.getSolarMonthName(UiUtils.m18455b(i3, i2)) + " - انتخاب تاریخ پایان");
                    }
                    this.f13892F = this.dateDescription.getText().toString().split("-")[0];
                    this.f13891E = this.numberUtil.toFarsiString(String.valueOf(i2)) + "/" + this.numberUtil.toFarsiString(UiUtils.m18494u(String.valueOf(UiUtils.m18455b(i3, i2)))) + "/" + this.numberUtil.toFarsiString(UiUtils.m18494u(String.valueOf(i)));
                }
                this.f13913a = true;
            }
        } else if (this.isGregorian) {
            this.dateDescription.setText(UiUtils.m18494u(String.valueOf(i)) + " " + UiUtils.getGregorianMonthName(UiUtils.m18432a(i3, i2)));
        } else {
            this.dateDescription.setText(this.numberUtil.toFarsiString(UiUtils.m18494u(String.valueOf(i))) + " " + UiUtils.getSolarMonthName(UiUtils.m18455b(i3, i2)));
        }
        if (!this.isReverseTravel) {
            setShortDate(this.dateDescription.getText().toString());
        } else if (this.f13914b) {
            setShortDate(this.dateDescription.getText().toString());
        }
    }

    private void m18316a(String fullPerdianDateSelected, String str2) {


        this.fullStartDate = fullPerdianDateSelected;
        this.fullEndDate = str2;
    }

    private void setShortDate(String shortDate) {

        this.currentshortDate = shortDate;
    }

    public void setOnDateSelectListener(ICallbackCalendarDialog ICallbackCalendarDialog) {
        this.callbackCalendarDialog = ICallbackCalendarDialog;
    }


}
