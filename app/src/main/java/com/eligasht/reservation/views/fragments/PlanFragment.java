package com.eligasht.reservation.views.fragments;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.pixplicity.easyprefs.library.Prefs;
import com.eligasht.reservation.R;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.tools.datetools.SolarCalendar;
import com.eligasht.reservation.tools.persian.Calendar.persian.util.PersianCalendarUtils;
import com.eligasht.reservation.views.ui.GetAirportMabdaActivity;
import com.eligasht.reservation.views.ui.GetAirportMaghsadActivity;
import com.eligasht.reservation.views.ui.SearchParvazActivity;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;

public class PlanFragment extends Fragment implements OnClickListener, TimePickerDialog.OnTimeSetListener, com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener {
    public PlanFragment() {
    }

    public static boolean flag;
    public static TextView tarikh_az_picker;
    public static TextView tarikh_be_picker;
    public TextView txtCountB, tvStart, tvEnd, txtCountK, txtCountN, lbl_forudgah_maghsad, lbl_forudgah_mabda, txtKO, txtBO, txtNO, textView3, tarikh_az, tarikh_be, btntwo, btnOne;
    public Button btnPlusB, btnMinesB, btnPlusK, btnMinesK, btnPlusN, btnMinesN, searchPlan;
    private LinearLayout linear_picker_title, linear_picker;
    public int flagOneTwo = 2;
    private static String picker_be = "2017-12-29";
    private static String picker_az = "2017-12-25";
    private static String picker_az_format = "29 December 2017";
    private static String picker_be_format = "25 December 2017";
    public static int picker_az_year;
    public static int picker_az_month;
    public static int picker_az_day;
    private View rootView;
    boolean Geo = false;
    RelativeLayout txtOption;
    int month;
    int year_;
    int day;
    int monthMin;
    int year_Min;
    int dayMin;
    String raft, bargasht;
    LinearLayout linearLayout_mabda, linearLayout_maghsad;
    ImageView ivImage;
    public LinearLayout linear_tarikh_az_picker;
    public static int countNafar = 1;

    com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog;
    com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePickerDialog2;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian1;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian2;
     /*com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian1;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian2;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_plane, container, false);
        Utility.sendTag("F", true, false);
        Geo = Prefs.getBoolean("geo", false);


        linear_picker = (LinearLayout) rootView.findViewById(R.id.linear_picker);
        linear_tarikh_az_picker = (LinearLayout) rootView.findViewById(R.id.linear_tarikh_az_picker);
        tarikh_az_picker = (TextView) rootView.findViewById(R.id.tarikh_az_picker);
        tarikh_be_picker = (TextView) rootView.findViewById(R.id.tarikh_be_picker);
        linearLayout_mabda = (LinearLayout) rootView.findViewById(R.id.linearLayout_mabda);
        linearLayout_maghsad = (LinearLayout) rootView.findViewById(R.id.linearLayout_maghsad);
    /*tarikh_az_picker.setTypeface(face);
    tarikh_be_picker.setTypeface(face);*/

        tarikh_az = (TextView) rootView.findViewById(R.id.tarikh_az);
        tarikh_be = (TextView) rootView.findViewById(R.id.tarikh_be);
        ivImage = (ImageView) rootView.findViewById(R.id.ivImage);


        btnPlusB = (Button) rootView.findViewById(R.id.btnPlusB);
        btnMinesB = (Button) rootView.findViewById(R.id.btnMinesB);

        btnPlusK = (Button) rootView.findViewById(R.id.btnPlusK);
        btnMinesK = (Button) rootView.findViewById(R.id.btnMinesK);

        btnPlusN = (Button) rootView.findViewById(R.id.btnPlusN);
        btnMinesN = (Button) rootView.findViewById(R.id.btnMinesN);

        btntwo = (TextView) rootView.findViewById(R.id.btntwo);
        btnOne = (TextView) rootView.findViewById(R.id.btnOne);


        searchPlan = (Button) rootView.findViewById(R.id.searchPlan);
        txtBO = (TextView) rootView.findViewById(R.id.txtBO);
        txtKO = (TextView) rootView.findViewById(R.id.txtKO);
        txtNO = (TextView) rootView.findViewById(R.id.txtNO);

        textView3 = (TextView) rootView.findViewById(R.id.textView3);
        txtCountB = (TextView) rootView.findViewById(R.id.txtCountB);
        txtCountK = (TextView) rootView.findViewById(R.id.txtCountK);
        txtCountN = (TextView) rootView.findViewById(R.id.txtCountN);
        tvStart = (TextView) rootView.findViewById(R.id.tvStart);

        txtOption = (RelativeLayout) rootView.findViewById(R.id.txtOption);
        tvEnd = (TextView) rootView.findViewById(R.id.tvEnd);
        lbl_forudgah_mabda = (TextView) rootView.findViewById(R.id.lbl_forudgah_mabda);
        lbl_forudgah_maghsad = (TextView) rootView.findViewById(R.id.lbl_forudgah_maghsad);


        linear_tarikh_az_picker.setOnClickListener(this);
        linear_picker.setOnClickListener(this);
        // tarikh_az_picker.setOnClickListener(this);
        // tarikh_be_picker.setOnClickListener(this);
        btnPlusB.setOnClickListener(this);
        btnMinesB.setOnClickListener(this);

        btnPlusK.setOnClickListener(this);
        btnMinesK.setOnClickListener(this);

        btnPlusN.setOnClickListener(this);
        btnMinesN.setOnClickListener(this);

        linearLayout_mabda.setOnClickListener(this);
        linearLayout_maghsad.setOnClickListener(this);
        txtOption.setOnClickListener(this);

        btntwo.setOnClickListener(this);
        btnOne.setOnClickListener(this);

        searchPlan.setOnClickListener(this);

///Calender nejati\
        //==================================================================================================
        PersianCalendar persianCalendarDatePicker = new PersianCalendar();
        //  Date currentTime = Calendar.getInstance().getTime();
        //=================================================================================================
        ///RRRRRRRRRRRRRRRRRRRRRRRRRRRR
        PersianCalendar persianCalendar = new PersianCalendar();

        persianCalendar.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth(), persianCalendarDatePicker.getPersianDay());
        ///RRRRRRRRRRRRRRRRRRRRRRRRRRRR

        tarikh_az_picker.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());
        picker_az_format = persianCalendarDatePicker.getPersianLongDate();
        tarikh_be_picker.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());
        picker_be_format = persianCalendarDatePicker.getPersianLongDate();
        month = persianCalendarDatePicker.getPersianMonth();//9
        year_ = persianCalendarDatePicker.getPersianYear();//1396
        day = persianCalendarDatePicker.getPersianDay();//24



///RRRRRRRRRRRRRRRRRRRRRRRRRRRR
        datePickerDialogGregorian1 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog();
        datePickerDialogGregorian2 = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog();
//RRRRRRRRRRRRRRRRRRRRR
        datePickerDialogGregorian1.setMinDate(persianCalendarDatePicker.toGregorianCalendar());
        datePickerDialogGregorian2.setMinDate(persianCalendarDatePicker.toGregorianCalendar());
        //RRRRRRRRRRRRRRRRRRRRRRRR

        datePickerDialog = com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.newInstance(
                this,
                persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay()
        );

        datePickerDialog.setMinDate(persianCalendarDatePicker);


        datePickerDialog2 = com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.newInstance(
                this,
                persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay()
        );
        datePickerDialog2.setMinDate(persianCalendarDatePicker);

        raft = date_server(persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay());

        bargasht = date_server(persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay());
//RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
        //=====================================================================================================


        datePickerDialog.setOnCalandarChangeListener(new DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogGregorian1.show(getActivity().getFragmentManager(), "DatePickerDialogGregorianRaft");
            }
        });


        datePickerDialogGregorian1.setOnCalandarChangeListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialog.show(getActivity().getSupportFragmentManager(), "DatepickerdialogRaft");


            }
        });


//=====================================================================================================

        datePickerDialog2.setOnCalandarChangeListener(new DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogGregorian2.show(getActivity().getFragmentManager(), "DatePickerDialogGregorianBargasht");
            }
        });


        datePickerDialogGregorian2.setOnCalandarChangeListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialog2.show(getActivity().getSupportFragmentManager(), "DatepickerdialogBargasht");


            }
        });


//=====================================================================================================


        datePickerDialogGregorian1.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
                Geo = true;


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


                    tarikh_az_picker.setText(DateUtil.getLongStringDate(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth, "yyyy/MM/dd", false));

                    raft = year + "/" + (monthOfYear + 1 )+ "/" + dayOfMonth;
                    Log.e("GGGGGGG", raft);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tarikh_be_picker.setText(tarikh_az_picker.getText().toString());
                Prefs.putString("bargashtfa", tarikh_az_picker.getText().toString());

                Prefs.putString("raft", raft);
                Prefs.putString("raftfa", tarikh_az_picker.getText().toString());


            }
        });
        datePickerDialogGregorian2.setOnDateSetListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
                Log.e("GGGGGGGBar", year + "==" + (monthOfYear + 1) + "==" + dayOfMonth);
                Geo = true;

                tarikh_be_picker.setText(DateUtil.getLongStringDate(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth, "yyyy/MM/dd", false));
                bargasht = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                Prefs.putString("bargasht", bargasht);
                Prefs.putString("bargashtfa", DateUtil.getLongStringDate(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth, "yyyy/MM/dd", false));


            }


        });


        //RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRrr

        //set value bundle
        //get
        if (Prefs.getString("Value-Mabda-City", "") != null && Prefs.getString("Value-Mabda-City", "").length() > 1) {
            tvStart.setText(Prefs.getString("Value-Mabda-City", ""));
            lbl_forudgah_mabda.setText(Prefs.getString("Value-Mabda-Airport", ""));
        }

        if (Prefs.getString("Value-Maghsad-Airport", "") != null && Prefs.getString("Value-Maghsad-Airport", "").length() > 1) {
            lbl_forudgah_maghsad.setText(Prefs.getString("Value-Maghsad-Airport", ""));
            tvEnd.setText(Prefs.getString("Value-Maghsad-City", ""));
        }//return rootView;

        if (Prefs.getString("bargashtfa", "null").equals("null")) {

            tarikh_be_picker.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());
            picker_be_format = persianCalendarDatePicker.getPersianLongDate();
        } else {

            tarikh_be_picker.setText(Prefs.getString("bargashtfa", "null").replaceAll("/", "-"));
            picker_be_format = Prefs.getString("bargashtfa", "null").replaceAll("/", "-");
            bargasht = Prefs.getString("bargasht", "null").replaceAll("/", "-");

            Log.e("testdate", bargasht );

            String[] dateSplite2=bargasht.split("-");

            String dayMF=dateSplite2[2];
            String monthMF=dateSplite2[1];
            String yearMF=dateSplite2[0];
            String[] dateSplite3= SolarCalendar.calSolarCalendar(Integer.valueOf(yearMF),Integer.valueOf(monthMF)-1,Integer.valueOf(dayMF)+1).split("/");

            String dayMF1=dateSplite3[2];
            String monthMF1=dateSplite3[1];
            String yearMF1=dateSplite3[0];


            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(Integer.valueOf(yearMF1), Integer.valueOf(monthMF1), Integer.valueOf(dayMF1));
            Log.e("testesttt", persianCalendarDatePicker2.getPersianLongDateAndTime());
            datePickerDialog2.initialize(this, persianCalendarDatePicker2.getPersianYear(),  persianCalendarDatePicker2.getPersianMonth(),  persianCalendarDatePicker2.getPersianDay());





        }


        if (Prefs.getString("raftfa", "null").equals("null")) {
            tarikh_az_picker.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());
            picker_az_format = persianCalendarDatePicker.getPersianLongDate();

        } else {
            tarikh_az_picker.setText(Prefs.getString("raftfa", "null").replaceAll("/", "-"));
            picker_az_format = Prefs.getString("raftfa", "null").replaceAll("/", "-");
            raft = Prefs.getString("raft", "null").replaceAll("/", "-");





            String[] dateSplite2=raft.split("-");

            String dayMF=dateSplite2[2];
            String monthMF=dateSplite2[1];
            String yearMF=dateSplite2[0];
            String[] dateSplite3= SolarCalendar.calSolarCalendar(Integer.valueOf(yearMF),Integer.valueOf(monthMF)-1,Integer.valueOf(dayMF)+1).split("/");

            String dayMF1=dateSplite3[2];
            String monthMF1=dateSplite3[1];
            String yearMF1=dateSplite3[0];


            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(Integer.valueOf(yearMF1), Integer.valueOf(monthMF1), Integer.valueOf(dayMF1));
            Log.e("testesttt", persianCalendarDatePicker2.getPersianLongDateAndTime());
            datePickerDialog.initialize(this, persianCalendarDatePicker2.getPersianYear(),  persianCalendarDatePicker2.getPersianMonth(),  persianCalendarDatePicker2.getPersianDay());
















        }
        return rootView;
    }//end oncreat

    @Override
    public void onResume() {
        Prefs.putBoolean("geo", Geo);

        Log.e("DEBUG", "onResume of PlanFragment");
        super.onResume();
        if (Prefs.getString("Value-Mabda-City", "") != null && Prefs.getString("Value-Mabda-City", "").length() > 1) {
            tvStart.setText(Prefs.getString("Value-Mabda-City", ""));
            lbl_forudgah_mabda.setText(Prefs.getString("Value-Mabda-Airport", ""));
        }

        if (Prefs.getString("Value-Maghsad-Airport", "") != null && Prefs.getString("Value-Maghsad-Airport", "").length() > 1) {
            lbl_forudgah_maghsad.setText(Prefs.getString("Value-Maghsad-Airport", ""));
            tvEnd.setText(Prefs.getString("Value-Maghsad-City", ""));
        }//return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Prefs.putBoolean("geo", Geo);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Prefs.putString("Value-Flight-City-Fa", "");
        Prefs.putString("Value-Flight-City-En", "");
        Prefs.putString("Value-Flight-City-Code", "");
    }

    public boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {


            case R.id.btnPlusB:
                countNafar = Integer.parseInt(txtCountB.getText().toString()) + Integer.parseInt(txtCountK.getText().toString()) + Integer.parseInt(txtCountN.getText().toString());
                if (countNafar < 9) {
                    try {
                        String btnPlusBStr = txtCountB.getText().toString();
                        int btnPlusBIntVal = Integer.parseInt(btnPlusBStr);
                        if (isInRange(1, 8, btnPlusBIntVal))
                            btnPlusBIntVal = btnPlusBIntVal + 1;
                        txtCountB.setText(String.valueOf(btnPlusBIntVal));//}
                    } catch (Exception e) {
                        e.printStackTrace();
                        //  Toast.makeText(getActivity(), "Some error :(", 2000).show();
                    }
                }
                break;
            case R.id.btnMinesB:
                try {
                    String btnMinesBValStr = txtCountB.getText().toString();
                    int btnMinesBIntVal = Integer.parseInt(btnMinesBValStr);
                    if (isInRange(2, 9, btnMinesBIntVal))
                        btnMinesBIntVal = btnMinesBIntVal - 1;
                    txtCountB.setText(String.valueOf(btnMinesBIntVal));//}
                } catch (Exception e) {
                    e.printStackTrace();
                    //   Toast.makeText(getActivity(), "Some errors :(", 2000).show();
                }
                break;

            case R.id.btnPlusK:
                countNafar = Integer.parseInt(txtCountB.getText().toString()) + Integer.parseInt(txtCountK.getText().toString()) + Integer.parseInt(txtCountN.getText().toString());
                if (countNafar < 9) {
                    try {
                        String btnPlusKValStr = txtCountK.getText().toString();
                        int btnPlisKIntVal = Integer.parseInt(btnPlusKValStr);
                        if (isInRange(0, 8, btnPlisKIntVal))
                            btnPlisKIntVal = btnPlisKIntVal + 1;
                        txtCountK.setText(String.valueOf(btnPlisKIntVal));//}
                    } catch (Exception e) {
                        e.printStackTrace();
                        //  Toast.makeText(getActivity(), "Some errors :(", 2000).show();
                    }
                }
                break;
            case R.id.btnMinesK:
                try {
                    String btnMinesKValStr = txtCountK.getText().toString();
                    int btnMinesKIntVal = Integer.parseInt(btnMinesKValStr);
                    if (isInRange(1, 9, btnMinesKIntVal))
                        btnMinesKIntVal = btnMinesKIntVal - 1;
                    txtCountK.setText(String.valueOf(btnMinesKIntVal));//}
                } catch (Exception e) {
                    e.printStackTrace();
                    // Toast.makeText(getActivity(), "Some errors :(", 2000).show();
                }
                break;

            case R.id.btnPlusN:
                countNafar = Integer.parseInt(txtCountB.getText().toString()) + Integer.parseInt(txtCountK.getText().toString()) + Integer.parseInt(txtCountN.getText().toString());
                if (countNafar < 9) {
                    try {
                        String presentValStr3 = txtCountN.getText().toString();
                        int presentIntVal3 = Integer.parseInt(presentValStr3);
                        if (isInRange(0, 8, presentIntVal3))
                            presentIntVal3 = presentIntVal3 + 1;
                        txtCountN.setText(String.valueOf(presentIntVal3));//}
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Toast.makeText(getActivity(), "Some errors :(", 2000).show();
                    }
                }
                break;
            case R.id.btnMinesN:
                try {
                    String presentValStr4 = txtCountN.getText().toString();
                    int presentIntVal4 = Integer.parseInt(presentValStr4);
                    if (isInRange(1, 9, presentIntVal4))
                        presentIntVal4 = presentIntVal4 - 1;
                    txtCountN.setText(String.valueOf(presentIntVal4));//}
                } catch (Exception e) {
                    e.printStackTrace();
                    // Toast.makeText(getActivity(), "Some errors :(", 2000).show();
                }
                break;
            case R.id.linearLayout_maghsad:

                Intent i3 = new Intent(getActivity(), GetAirportMaghsadActivity.class);
                // Bundle bundle = getActivity().getIntent().getExtras();
                if (Prefs.getString("Value-Mabda-City", "") != null || Prefs.getString("Value-Mabda-City", "") != "") {
                    i3.putExtra("Value-Mabda-City", Prefs.getString("Value-Mabda-City", ""));
                    i3.putExtra("Value-Mabda-Airport", Prefs.getString("Value-Mabda-Airport", ""));
                    i3.putExtra("Value-Mabda-Airport-Code", Prefs.getString("Value-Mabda-Airport-Code", ""));//*THR
                }

                startActivityForResult(i3, 2);
                break;
            case R.id.linearLayout_mabda:


                Intent intent = new Intent(getActivity(), GetAirportMabdaActivity.class);
                // Bundle bundle2 = getActivity().getIntent().getExtras();
                if (Prefs.getString("Value-Maghsad-City", "") != null && Prefs.getString("Value-Maghsad-City", "").length() > 1) {
                    intent.putExtra("Value-Maghsad-City", Prefs.getString("Value-Maghsad-City", ""));
                    intent.putExtra("Value-Maghsad-Airport", Prefs.getString("Value-Maghsad-Airport", ""));
                    intent.putExtra("Value-Maghsad-Airport-Code", Prefs.getString("Value-Maghsad-Airport-Code", ""));//*
                }

                startActivityForResult(intent, 2);
                break;
            case R.id.txtOption:
                anim();

                break;
            case R.id.btntwo:
                flagOneTwo = 2;
                ((LinearLayout) rootView.findViewById(R.id.llButton)).setBackgroundResource(R.drawable.raftobargasht_button);
                // ((Button) rootView.findViewById(R.id.btnOne)).setBackgroundResource(R.drawable.raft_big);
                ((TextView) rootView.findViewById(R.id.btntwo)).setTextColor(Color.parseColor("#ffffff"));
                ((TextView) rootView.findViewById(R.id.btnOne)).setTextColor(Color.parseColor("#d9d9d9"));

                //  linear_picker_title = (LinearLayout) rootView.findViewById(R.id.linear_picker_title);
                linear_picker = (LinearLayout) rootView.findViewById(R.id.linear_picker);

                tarikh_be.setVisibility(View.VISIBLE);
                linear_picker.setVisibility(View.VISIBLE);
                //v.setBackgroundResource(R.drawable.btnwhite);
         /*((Button)rootView.findViewById(R.id.btnAward)).setBackgroundDrawable(R.drawable.background_back);
         ((Button)rootView.findViewById(R.id.btntwo)).setTextColor(Color.parseColor("#E06F3"));
         ((Button)rootView.findViewById(R.id.btnOne)).setTextColor(Color.parseColor("#ffffff"));*/
                break;
            case R.id.btnOne:
                flagOneTwo = 1;
                ((LinearLayout) rootView.findViewById(R.id.llButton)).setBackgroundResource(R.drawable.raft_button);
                //((Button) rootView.findViewById(R.id.btntwo)).setBackgroundResource(R.drawable.raft_big);
                ((TextView) rootView.findViewById(R.id.btnOne)).setTextColor(Color.parseColor("#ffffff"));
                ((TextView) rootView.findViewById(R.id.btntwo)).setTextColor(Color.parseColor("#d9d9d9"));

                //linear_picker_title = (LinearLayout) rootView.findViewById(R.id.linear_picker_title);
                linear_picker = (LinearLayout) rootView.findViewById(R.id.linear_picker);

                tarikh_be.setVisibility(View.INVISIBLE);
                linear_picker.setVisibility(View.INVISIBLE);
                break;
            // case R.id.tarikh_be_picker:
            case R.id.linear_picker:
                if (Geo) {
                    datePickerDialogGregorian2.show(getActivity().getFragmentManager(), "DatePickerDialogGregorianBargasht");

                } else {
                    datePickerDialog2.show(getActivity().getSupportFragmentManager(), "DatepickerdialogBargasht");

                }
                break;
            //case R.id.tarikh_az_picker:
            case R.id.linear_tarikh_az_picker:
                if (Geo) {
                    datePickerDialogGregorian1.show(getActivity().getFragmentManager(), "DatePickerDialogGregorianBargasht");

                } else {
                    datePickerDialog.show(getActivity().getSupportFragmentManager(), "DatepickerdialogRaft");

                }


                break;
            case R.id.searchPlan:
                boolean ok = true;

                try {


                    Intent intent1 = new Intent(getActivity(), SearchParvazActivity.class);

                    if (Prefs.getString("Value-Mabda-City", "") != null && Prefs.getString("Value-Mabda-City", "").length() > 0 && Prefs.getString("Value-Maghsad-Airport-Code", "") != null && Prefs.getString("Value-Maghsad-Airport-Code", "").length() > 0) {
                        System.out.println("not default" + Prefs.getString("Value-Mabda-City", ""));
                        if (Prefs.getString("Value-Mabda-Airport-Code", "") != null && Prefs.getString("Value-Mabda-Airport-Code", "").length() > 0) {
                            intent1.putExtra("Value-Mabda-City", Prefs.getString("Value-Mabda-City", ""));
                            intent1.putExtra("Value-Mabda-Airport", Prefs.getString("Value-Mabda-Airport", ""));
                            intent1.putExtra("Value-Mabda-Airport-Code", Prefs.getString("Value-Mabda-Airport-Code", ""));//*THR
                        } else {
                            intent1.putExtra("Value-Mabda-City", tvStart.getText().toString());
                            intent1.putExtra("Value-Mabda-Airport", lbl_forudgah_mabda.getText().toString());
                            intent1.putExtra("Value-Mabda-Airport-Code", "THR");//*THR
                        }
                        if (Prefs.getString("Value-Maghsad-Airport-Code", "") != null && Prefs.getString("Value-Maghsad-Airport-Code", "").length() > 0) {
                            intent1.putExtra("Value-Maghsad-City", Prefs.getString("Value-Maghsad-City", ""));
                            intent1.putExtra("Value-Maghsad-Airport", Prefs.getString("Value-Maghsad-Airport", ""));
                            intent1.putExtra("Value-Maghsad-Airport-Code", Prefs.getString("Value-Maghsad-Airport-Code", ""));//*

                        } else {
                            intent1.putExtra("Value-Maghsad-City", tvEnd.getText().toString());
                            intent1.putExtra("Value-Maghsad-Airport", lbl_forudgah_maghsad.getText().toString());
                            intent1.putExtra("Value-Maghsad-Airport-Code", "IST");//*
                        }
                        intent1.putExtra("Value-Mabda-City", tvStart.getText().toString());
                        intent1.putExtra("Value-Maghsad-City", tvEnd.getText().toString());

                        intent1.putExtra("Value-Flag-Two", Integer.toString(flagOneTwo));
                        intent1.putExtra("Value-AdlCount", txtCountB.getText().toString());
                        intent1.putExtra("Value-ChdCount", txtCountK.getText().toString());
                        intent1.putExtra("Value-InfCount", txtCountN.getText().toString());

                        //////////////recent date
                        if (Prefs.getString("bargashtfa", "null").equals("null")) {
                            intent1.putExtra("Value-ArrivalDate", bargasht);//2017-11-29
                        } else {


                            picker_be_format = Prefs.getString("bargashtfa", "null");
                            bargasht = Prefs.getString("bargasht", "null");
                            intent1.putExtra("Value-ArrivalDate", bargasht.replace("/", "-"));//2017-11-29
                        }

                        if (Prefs.getString("raftfa", "null").equals("null")) {

                            intent1.putExtra("Value-DepartureDate", raft);//2017-11-24
                        } else {

                            picker_az_format = Prefs.getString("raftfa", "null");
                            raft = Prefs.getString("raft", "null");
                            intent1.putExtra("Value-DepartureDate", raft.replace("/", "-"));//2017-11-24
                        }
                        //////////////////////end recent date


                        intent1.putExtra("Value-DepartureDate-format", picker_az_format);//2017-December-24
                        intent1.putExtra("Value-ArrivalDate-format", picker_be_format);//2017-December-29
                        intent1.putExtra("Geo", Geo);//2017-11-24


                        startActivity(intent1);
                    } else {//default
                        AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(getActivity());
                        AlertDialogPassenger.setText("لطفا مبدا و مقصد را انتخاب کنید ");
                       /* System.out.println("default");
                        intent1.putExtra("Value-Mabda-City", tvStart.getText().toString());
                        intent1.putExtra("Value-Mabda-Airport", lbl_forudgah_mabda.getText().toString());
                        intent1.putExtra("Value-Mabda-Airport-Code", "THR");/*//*THR

                        intent1.putExtra("Value-Maghsad-City", tvEnd.getText().toString());
                        intent1.putExtra("Value-Maghsad-Airport", lbl_forudgah_maghsad.getText().toString());
                        intent1.putExtra("Value-Maghsad-Airport-Code", "IST");/*//*

                        intent1.putExtra("Value-Flag-Two", Integer.toString(flagOneTwo));
                        intent1.putExtra("Value-AdlCount", txtCountB.getText().toString());
                        intent1.putExtra("Value-ChdCount", txtCountK.getText().toString());
                        intent1.putExtra("Value-InfCount", txtCountN.getText().toString());

                        intent1.putExtra("Value-DepartureDate",  raft);//2017-11-24
                        intent1.putExtra("Value-ArrivalDate", bargasht);//2017-11-29

                        intent1.putExtra("Value-DepartureDate-format", picker_az_format);//2017-December-24
                        intent1.putExtra("Value-ArrivalDate-format", picker_be_format);//2017-December-29*/
                    }

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "خطایی رخ داده است", Toast.LENGTH_SHORT).show();
                }
                break;

        }
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


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
        Geo = false;

        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.set(year, month, day);


        Log.e("salam", date_server(year_, month, day));
        if (view.getTag().equals("DatepickerdialogBargasht")) {
            tarikh_be_picker.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());

            bargasht = date_server(year, monthOfYear, dayOfMonth);//2018-02-9


            picker_be_format = persianCalendar.getPersianLongDate();//جمعه 20 بهمن 1396
            Prefs.putString("bargashtfa", tarikh_be_picker.getText().toString());//پنج‌شنبه 19 بهمن 1396
            Prefs.putString("bargasht", bargasht);//2018-02-11

        }


        if (view.getTag().equals("DatepickerdialogRaft")) {

            year_Min = year;
            monthMin = monthOfYear;
            dayMin = dayOfMonth;
            tarikh_az_picker.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());
            //  tvBargasht.setText(persianCalendar.getPersianLongDate());
            raft = date_server(year, monthOfYear, dayOfMonth);
            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(year_Min, monthMin, dayMin);


            if (Utility.campareDate(raft.replaceAll("-","/"),bargasht.replaceAll("-","/"))){
                tarikh_be_picker.setText(persianCalendar.getPersianWeekDayName()+" "+persianCalendar.getPersianDay()+" "+persianCalendar.getPersianMonthName());
                datePickerDialog2.initialize(this, year_, month, day);
                datePickerDialog2.setMinDate(persianCalendarDatePicker2);
            }






            Prefs.putString("bargashtfa", tarikh_be_picker.getText().toString());

            Prefs.putString("raft", raft);
            Prefs.putString("raftfa",tarikh_az_picker.getText().toString());

        }
    }


    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

    }


    public void anim() {


        YoYo.with(Techniques.SlideOutDown).duration(500).interpolate(new AccelerateDecelerateInterpolator()).withListener(new android.animation.Animator.AnimatorListener() {


            @Override
            public void onAnimationStart(android.animation.Animator animation) {


                YoYo.with(Techniques.SlideOutDown)
                        .duration(500)
                        .playOn(lbl_forudgah_mabda);
                YoYo.with(Techniques.SlideOutUp)
                        .duration(500)
                        .playOn(lbl_forudgah_maghsad);


                YoYo.with(Techniques.SlideOutUp)
                        .duration(500)
                        .playOn(tvEnd);

            }

            @Override
            public void onAnimationEnd(android.animation.Animator animation) {

                String start = "";
                String end = "";
                String startF = "";
                String endF = "";

                start = tvStart.getText().toString();
                end = tvEnd.getText().toString();

                startF = lbl_forudgah_mabda.getText().toString();
                endF = lbl_forudgah_maghsad.getText().toString();

                tvStart.setText(end);
                tvEnd.setText(start);

                lbl_forudgah_mabda.setText(endF);
                lbl_forudgah_maghsad.setText(startF);
/////////////////////////
                String airportMaghsad = Prefs.getString("Value-Maghsad-Airport-Code", "");
                String airPortMabda = Prefs.getString("Value-Mabda-Airport-Code", "");

                Prefs.putString("Value-Mabda-Airport-Code", airportMaghsad);
                Prefs.putString("Value-Maghsad-Airport-Code", airPortMabda);

                String mabdaCity = Prefs.getString("Value-Mabda-City", "");
                String mabdaAirPort = Prefs.getString("Value-Mabda-Airport", "");
                String maghsadCity = Prefs.getString("Value-Maghsad-City", "");
                String maghsadAirPort = Prefs.getString("Value-Maghsad-Airport", "");

                Prefs.putString("Value-Mabda-City", maghsadCity);
                Prefs.putString("Value-Mabda-Airport", maghsadAirPort);
                Prefs.putString("Value-Maghsad-City", mabdaCity);
                Prefs.putString("Value-Maghsad-Airport", mabdaAirPort);
////////////////////////
                YoYo.with(Techniques.SlideInUp)
                        .duration(500)
                        .playOn(lbl_forudgah_mabda);
                YoYo.with(Techniques.SlideInDown)
                        .duration(500)
                        .playOn(lbl_forudgah_maghsad);

                YoYo.with(Techniques.SlideInUp)
                        .duration(500)
                        .playOn(tvStart);
                YoYo.with(Techniques.SlideInDown)
                        .duration(500)
                        .playOn(tvEnd);


            }

            @Override
            public void onAnimationCancel(android.animation.Animator animation) {

            }

            @Override
            public void onAnimationRepeat(android.animation.Animator animation) {

            }

        })
                .playOn(tvStart);


        final Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_around_center_point);
        ivImage.startAnimation(animation);
    }
}

