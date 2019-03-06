package com.eligasht.reservation.views.fragments.train;

import android.animation.Animator;
import android.annotation.SuppressLint;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.datetools.SolarCalendar;
import com.eligasht.reservation.tools.persian.Calendar.persian.util.PersianCalendarUtils;
import com.eligasht.reservation.views.activities.train.GetCityTrainMabdaActivity;
import com.eligasht.reservation.views.activities.train.GetCityTrainMaghsadActivity;
import com.eligasht.reservation.views.picker.global.enums.TypeUsageOfCalendar;
import com.eligasht.reservation.views.picker.global.listeners.ICallbackCalendarDialog;
import com.eligasht.reservation.views.picker.global.model.CustomDate;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.picker.utils.CalendarDialog;
import com.eligasht.reservation.views.ticker.TickerView;

import com.eligasht.reservation.views.ui.PassengerActivity;
import com.eligasht.reservation.views.ui.SearchFlightActivity;
import com.eligasht.reservation.views.ui.dialog.hotel.AlertDialogPassenger;
import com.eligasht.reservation.views.ui.dialog.train.DialogPassCount;
import com.eligasht.service.helper.Const;
import com.eligasht.service.model.newModel.auth.response.ResponseAuth;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.refactor.library.SmoothCheckBox;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainFragment extends Fragment implements OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, ICallbackCalendarDialog {
    private static Boolean Darbast = false;
    public static boolean flag;
    public static TextView tarikh_az_picker;
    public static TextView tarikh_be_picker;
    private TickerView txtCountB, txtCountK, txtCountN;
    public TextView tvStart, tvMaghsad, txtKO, txtBO, txtNO, textView3, tarikh_az, tarikh_be, btntwo, btnOne, searchTrain;
    public Button btnPlusB, btnMinesB, btnPlusK, btnMinesK, btnPlusN, btnMinesN;
    public int flagOneTwo = 2;
    public String flagTypePass = "F";
    private static String picker_az_format = "29 December 2017";
    private static String picker_be_format = "25 December 2017";
    private View rootView;
    public boolean Geo = false;
    public RelativeLayout txtOption;
    public int month;
    public int year_;
    public int day;
    public int monthMin;
    public int year_Min;
    public int dayMin;
    public String raft, bargasht;
    public LinearLayout linearLayout_mabda, linearLayout_maghsad;
    public ImageView ivImage;
    public LinearLayout linear_tarikh_az_picker, linear_picker;
    public static int countNafar = 1;
    public LinearLayout llButton,lnr_pass_count;
    public DatePickerDialog datePickerDialog;
    public DatePickerDialog datePickerDialog2;
    public com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian1;
    public com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian2;
    public CalendarDialog calendarDialog;
    private LottieAnimationView lottieCheckin, lottieCheckout;
    private ClientService service;
    public TextView txtPassNat,txtPassWom,txtPassMen;
    private CheckBox rd_darbast;
    private int flagRd=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_train, container, false);
        initView();
        initCalendar();

        tarikh_be_picker.setText(SingletonDate.getInstance().getEndDate().getDescription());
        bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
        tarikh_az_picker.setText(SingletonDate.getInstance().getStartDate().getDescription());
        raft = SingletonDate.getInstance().getStartDate().getFullGeo();
        service = ServiceGenerator.createService(ClientService.class);
        Auth_request();
        return rootView;
    }//end oncreate

    private void initCalendar() {
        //==================================================================================================
        PersianCalendar persianCalendarDatePicker = new PersianCalendar();
        //  Date currentTime = Calendar.getInstance().getTime();
        //=================================================================================================
        ///RRRRRRRRRRRRRRRRRRRRRRRRRRRR
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth(), persianCalendarDatePicker.getPersianDay());
        ///RRRRRRRRRRRRRRRRRRRRRRRRRRRR
        tarikh_az_picker.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
        picker_az_format = persianCalendarDatePicker.getPersianLongDate();
        tarikh_be_picker.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
        picker_be_format = persianCalendarDatePicker.getPersianLongDate();
        month = persianCalendarDatePicker.getPersianMonth();//9
        year_ = persianCalendarDatePicker.getPersianYear();//1396
        day = persianCalendarDatePicker.getPersianDay();//24
        ///RRRRRRRRRRRRRRRRRRRRRRRRRRRR

        datePickerDialog = DatePickerDialog.newInstance(
                this,
                persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay()
        );
        datePickerDialog.setMinDate(persianCalendarDatePicker);
        datePickerDialog2 = DatePickerDialog.newInstance(
                this,
                persianCalendarDatePicker.getPersianYear(),
                persianCalendarDatePicker.getPersianMonth(),
                persianCalendarDatePicker.getPersianDay()
        );
        datePickerDialog2.setMinDate(persianCalendarDatePicker);
        //RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRrr
        calendarDialog = new CalendarDialog();
        SingletonDate.getInstance().checkConflictDate();
        Utility.sendTag("F", true, false);
        Geo = Prefs.getBoolean("geo", false);

        /*Prefs.putString("Value_Mabda_Train", current.getText());
        Prefs.putString("Value_Mabda_Key_Train", current.getValue());
        Prefs.putString("Value_Maghsad_Train", current.getText());
                Prefs.putString("Value_Maghsad_Key_Train", current.getValue());*/
        //get
        if (Prefs.getString("Value_Mabda_Train", "") != null && Prefs.getString("Value_Mabda_Train", "").length() > 1) {
            tvStart.setText(Prefs.getString("Value_Mabda_Train", ""));
           // lbl_forudgah_mabda.setText(Prefs.getString("Value-Mabda-Airport", ""));
        }
        if (Prefs.getString("Value_Maghsad_Train", "") != null && Prefs.getString("Value_Maghsad_Train", "").length() > 1) {
          //  lbl_forudgah_maghsad.setText(Prefs.getString("Value-Maghsad-Airport", ""));
            tvMaghsad.setText(Prefs.getString("Value_Maghsad_Train", ""));
        }//return rootView;
        if (Prefs.getString("bargashtfa", "null").equals("null")) {
            tarikh_be_picker.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
            picker_be_format = persianCalendarDatePicker.getPersianLongDate();
        } else {
            try {
                tarikh_be_picker.setText(Prefs.getString("bargashtfa", "null").replaceAll("/", "-"));
                picker_be_format = Prefs.getString("bargashtfa", "null").replaceAll("/", "-");
                bargasht = Prefs.getString("bargasht", "null").replaceAll("/", "-");
                Log.e("testdate", bargasht);
                String[] dateSplite2 = bargasht.split("-");
                String dayMF = dateSplite2[2];
                String monthMF = dateSplite2[1];
                String yearMF = dateSplite2[0];
                String[] dateSplite3 = SolarCalendar.calSolarCalendar(Integer.valueOf(yearMF), Integer.valueOf(monthMF) - 1, Integer.valueOf(dayMF) + 1).split("/");
                String dayMF1 = dateSplite3[2];
                String monthMF1 = dateSplite3[1];
                String yearMF1 = dateSplite3[0];
                PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
                persianCalendarDatePicker2.set(Integer.valueOf(yearMF1), Integer.valueOf(monthMF1), Integer.valueOf(dayMF1));
                Log.e("testesttt", persianCalendarDatePicker2.getPersianLongDateAndTime());
                datePickerDialog2.initialize(this, persianCalendarDatePicker2.getPersianYear(), persianCalendarDatePicker2.getPersianMonth(), persianCalendarDatePicker2.getPersianDay());
            } catch (Exception e) {
            }
        }
        if (Prefs.getString("raftfa", "null").equals("null")) {
            tarikh_az_picker.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
            picker_az_format = persianCalendarDatePicker.getPersianLongDate();
        } else {
            try {
                tarikh_az_picker.setText(Prefs.getString("raftfa", "null").replaceAll("/", "-"));
                picker_az_format = Prefs.getString("raftfa", "null").replaceAll("/", "-");
                raft = Prefs.getString("raft", "null").replaceAll("/", "-");
                String[] dateSplite2 = raft.split("-");
                String dayMF = dateSplite2[2];
                String monthMF = dateSplite2[1];
                String yearMF = dateSplite2[0];
                String[] dateSplite3 = SolarCalendar.calSolarCalendar(Integer.valueOf(yearMF), Integer.valueOf(monthMF) - 1, Integer.valueOf(dayMF) + 1).split("/");
                String dayMF1 = dateSplite3[2];
                String monthMF1 = dateSplite3[1];
                String yearMF1 = dateSplite3[0];
                PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
                persianCalendarDatePicker2.set(Integer.valueOf(yearMF1), Integer.valueOf(monthMF1), Integer.valueOf(dayMF1));
                Log.e("testesttt", persianCalendarDatePicker2.getPersianLongDateAndTime());
                datePickerDialog.initialize(this, persianCalendarDatePicker2.getPersianYear(), persianCalendarDatePicker2.getPersianMonth(), persianCalendarDatePicker2.getPersianDay());
            } catch (Exception e) {
            }
        }
    }

    private void initView() {
        llButton = rootView.findViewById(R.id.llButton);
        linear_tarikh_az_picker = rootView.findViewById(R.id.linear_tarikh_az_picker);
        tarikh_az_picker = rootView.findViewById(R.id.tarikh_az_picker);
        tarikh_be_picker = rootView.findViewById(R.id.tarikh_be_picker);
        linearLayout_mabda = rootView.findViewById(R.id.linearLayout_mabda);
        linearLayout_maghsad = rootView.findViewById(R.id.linearLayout_maghsad);

        lottieCheckin = rootView.findViewById(R.id.lottie_checkin);
        lottieCheckout = rootView.findViewById(R.id.lottie_checkout);
        lottieCheckin.setSpeed(2f);
        lottieCheckout.setSpeed(2f);
        tarikh_az = rootView.findViewById(R.id.tarikh_az);
        tarikh_be = rootView.findViewById(R.id.tarikh_be);
        ivImage = rootView.findViewById(R.id.ivImage);

        lnr_pass_count = rootView.findViewById(R.id.lnr_pass_count);

        txtPassNat = rootView.findViewById(R.id.txtPassNat);
        txtPassWom = rootView.findViewById(R.id.txtPassWom);
        txtPassMen = rootView.findViewById(R.id.txtPassMen);

        btntwo = rootView.findViewById(R.id.btntwo);
        btnOne = rootView.findViewById(R.id.btnOne);
        searchTrain = rootView.findViewById(R.id.searchTrain);
        txtBO = rootView.findViewById(R.id.txtBO);
        txtKO = rootView.findViewById(R.id.txtKO);
        txtNO = rootView.findViewById(R.id.txtNO);
        textView3 = rootView.findViewById(R.id.textView3);
        txtCountB = rootView.findViewById(R.id.txtCountB);
        txtCountK = rootView.findViewById(R.id.txtCountK);
        txtCountN = rootView.findViewById(R.id.txtCountN);
        tvStart = rootView.findViewById(R.id.tvStart);
        txtOption = rootView.findViewById(R.id.txtOption);
        tvMaghsad = rootView.findViewById(R.id.tvMaghsad);

        linear_tarikh_az_picker.setOnClickListener(this);


        linearLayout_mabda.setOnClickListener(this);
        linearLayout_maghsad.setOnClickListener(this);
        txtOption.setOnClickListener(this);
        btntwo.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        searchTrain.setOnClickListener(this);

        lnr_pass_count.setOnClickListener(this);

        txtPassNat.setOnClickListener(this);
        txtPassWom.setOnClickListener(this);
        txtPassMen.setOnClickListener(this);
        rd_darbast = (CheckBox) rootView.findViewById(R.id.rd_darbast);
        rd_darbast.setOnClickListener(this);





    }

    private void Auth_request() {


        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("grant_type", "password");
            paramObject.put("username", "eli_gasht_1397");
            paramObject.put("password", "Eli@accesstoken");

            Call<ResponseAuth> call = service.getAuthResult("password","eli_gasht_1397","Eli@accesstoken");
            call.enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    Log.d("ResponseToken: ","res:"+response.body().getTokenType()+" "+response.body().getAccessToken());
                    Const.TOKEN=response.body().getTokenType()+" "+response.body().getAccessToken();
                    //hotel_request();
                }
                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t)  {
                    Log.d("requestSearchPackage: ","error");

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onResume() {
        Prefs.putBoolean("geo", Geo);
        Log.e("DEBUG", "onResume of trainFragment");
        super.onResume();

        if (Prefs.getString("Value_Mabda_Train", "") != null && Prefs.getString("Value_Mabda_Train", "").length() > 1) {
            tvStart.setText(Prefs.getString("Value_Mabda_Train", ""));
        }
        if (Prefs.getString("Value_Maghsad_Train", "") != null && Prefs.getString("Value_Maghsad_Train", "").length() > 1) {
            tvMaghsad.setText(Prefs.getString("Value_Maghsad_Train", ""));
        }
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

    private void initCheckInCheckOutAnim() {
        lottieCheckin.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieCheckin.setFrame(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        lottieCheckout.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieCheckout.setFrame(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        lottieCheckin.playAnimation();
        lottieCheckout.playAnimation();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {


                case R.id.lnr_pass_count:

                    try {
                        DialogPassCount dialogPassCount = new DialogPassCount(getActivity(),false,false);
                        dialogPassCount.setText( "لطفا تعداد را وارد کنید");
                    }catch (Exception e){
                        e.getMessage();
                    }
                break;
                case R.id.rd_darbast:
                    if (((CheckBox) v).isChecked()) {
                        Darbast=true;
                        System.out.println("Darbast1"+Darbast);
                    }else{
                        Darbast=false;
                        System.out.println("Darbast2"+Darbast);
                    }

                break;
               case R.id.txtPassNat:
                    flagTypePass="F";
                    clickPassNat();

                break;
                case R.id.txtPassMen:
                    flagTypePass="B";
                    clickPassMen();

                break;
                case R.id.txtPassWom:
                    flagTypePass="S";
                    clickPassWo();

                break;

            case R.id.linearLayout_maghsad:
                Intent i3 = new Intent(getActivity(), GetCityTrainMaghsadActivity.class);

                SwipeBackActivityHelper.activityBuilder(getActivity())
                        .intent(i3)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
                break;
            case R.id.linearLayout_mabda:
                Intent intent = new Intent(getActivity(), GetCityTrainMabdaActivity.class);

                SwipeBackActivityHelper.activityBuilder(getActivity())
                        .intent(intent)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
                break;
            case R.id.txtOption:
                anim();
                break;
            case R.id.btntwo:
               btnTwo();

                break;
            case R.id.btnOne:
                btnOne();

                break;

            case R.id.linear_tarikh_az_picker:
                SingletonDate.getInstance().checkConflictDate();
                if (flagOneTwo == 1) {
                    calendarDialog.create(getActivity(), getContext(), this, SingletonDate.getInstance().getStartDate(), TypeUsageOfCalendar.NationalFlight);
                } else {

                    calendarDialog.create(getActivity(), getContext(), this, SingletonDate.getInstance().getStartDate(), SingletonDate.getInstance().getEndDate(), TypeUsageOfCalendar.NationalFlight);
                }
                break;
            case R.id.searchTrain:
                boolean ok = true;
                Prefs.getString("Value_Pass_CountB","");
                Prefs.getString("Value_Pass_CountK", "");
                Prefs.getString("Value_Pass_CountN", "");
               /* try {
                    Intent intent1 = new Intent(getActivity(), SearchFlightActivity.class);
                    if (Prefs.getString("Value-Mabda-City-Train", "") != null && Prefs.getString("Value-Mabda-City-Train", "").length() > 0 && Prefs.getString("Value-Maghsad-Airport-Code-Train", "") != null && Prefs.getString("Value-Maghsad-Airport-Code-Train", "").length() > 0) {
                        System.out.println("not default" + Prefs.getString("Value-Mabda-City-Train", ""));

                        intent1.putExtra("Value_Mabda_City_Train", tvStart.getText().toString());
                        intent1.putExtra("Value_Maghsad_City_Train", tvMaghsad.getText().toString());
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
                        AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(getActivity(), false,false);
                        AlertDialogPassenger.setText(getString(R.string.please_select_destination_and_origin), getString(R.string.massege));

                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), getString(R.string.something_went_wron), Toast.LENGTH_SHORT).show();
                }*/
                break;
        }
    }

    private void btnTwo() {
        SingletonDate.getInstance().checkConflictDate();
        tarikh_be_picker.setText(SingletonDate.getInstance().getEndDate().getDescription());
        bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
        tarikh_az_picker.setText(SingletonDate.getInstance().getStartDate().getDescription());
        raft = SingletonDate.getInstance().getStartDate().getFullGeo();
        flagOneTwo = 2;
        llButton.setBackgroundResource(R.drawable.raftobargasht_button);
        YoYo.with(Techniques.Pulse)
                .duration(200)
                .playOn(llButton);
        ((TextView) rootView.findViewById(R.id.btntwo)).setTextColor(Color.parseColor("#ffffff"));
        ((TextView) rootView.findViewById(R.id.btnOne)).setTextColor(Color.parseColor("#d9d9d9"));
        //  linear_picker_title = (LinearLayout) rootView.findViewById(R.id.linear_picker_title);
        linear_picker = rootView.findViewById(R.id.linear_picker);
        tarikh_be.setVisibility(View.VISIBLE);
        linear_picker.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.Pulse)
                .duration(200)
                .playOn(linear_picker);
        YoYo.with(Techniques.Pulse)
                .duration(200)
                .playOn(tarikh_be);
        YoYo.with(Techniques.Pulse)
                .duration(200)
                .playOn(linear_picker);
    }

    private void btnOne() {
        SingletonDate.getInstance().checkConflictDate();
        tarikh_be_picker.setText(SingletonDate.getInstance().getEndDate().getDescription());
        bargasht = SingletonDate.getInstance().getEndDate().getFullGeo();
        tarikh_az_picker.setText(SingletonDate.getInstance().getStartDate().getDescription());
        raft = SingletonDate.getInstance().getStartDate().getFullGeo();
        flagOneTwo = 1;
        llButton.setBackgroundResource(R.drawable.raft_button);
        YoYo.with(Techniques.Pulse)
                .duration(200)
                .playOn(llButton);
        ((TextView) rootView.findViewById(R.id.btnOne)).setTextColor(Color.parseColor("#ffffff"));
        ((TextView) rootView.findViewById(R.id.btntwo)).setTextColor(Color.parseColor("#d9d9d9"));
        linear_picker = rootView.findViewById(R.id.linear_picker);
        tarikh_be.setVisibility(View.INVISIBLE);
        linear_picker.setVisibility(View.INVISIBLE);
        YoYo.with(Techniques.Pulse)
                .duration(200)
                .playOn(linear_picker);
        YoYo.with(Techniques.Pulse)
                .duration(400)
                .playOn(tarikh_be);
        YoYo.with(Techniques.Pulse)
                .duration(200)
                .playOn(linear_picker);
    }

    private void clickPassNat() {
        try {

            txtPassNat.setBackgroundResource(R.drawable.background_strock_purple_mat);
            txtPassMen.setBackgroundResource(R.drawable.background_strock_gray_curv_narrow);
            txtPassWom.setBackgroundResource(R.drawable.background_strock_gray_curv_narrow);

            txtPassNat.setTextColor(Color.parseColor(getString(R.color.white)));
            txtPassMen.setTextColor(Color.parseColor(getString(R.color.grayBB)));
            txtPassWom.setTextColor(Color.parseColor(getString(R.color.grayBB)));
            YoYo.with(Techniques.Pulse)
                    .duration(200)
                    .playOn(txtPassNat);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private void clickPassWo() {
        try {

            txtPassNat.setBackgroundResource(R.drawable.background_strock_gray_curv_narrow);
            txtPassMen.setBackgroundResource(R.drawable.background_strock_gray_curv_narrow);
            txtPassWom.setBackgroundResource(R.drawable.background_strock_purple_mat);

            txtPassNat.setTextColor(Color.parseColor(getString(R.color.grayBB)));
            txtPassMen.setTextColor(Color.parseColor(getString(R.color.grayBB)));
            txtPassWom.setTextColor(Color.parseColor(getString(R.color.white)));
            YoYo.with(Techniques.Pulse)
                    .duration(200)
                    .playOn(txtPassWom);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private void clickPassMen() {
        try {

            txtPassNat.setBackgroundResource(R.drawable.background_strock_gray_curv_narrow);
            txtPassMen.setBackgroundResource(R.drawable.background_strock_purple_mat);
            txtPassWom.setBackgroundResource(R.drawable.background_strock_gray_curv_narrow);

            txtPassNat.setTextColor(Color.parseColor(getString(R.color.grayBB)));
            txtPassMen.setTextColor(Color.parseColor(getString(R.color.white)));
            txtPassWom.setTextColor(Color.parseColor(getString(R.color.grayBB)));
            YoYo.with(Techniques.Pulse)
                    .duration(200)
                    .playOn(txtPassMen);
        } catch (Exception e) {
            e.printStackTrace();

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
            tarikh_be_picker.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
            bargasht = date_server(year, monthOfYear, dayOfMonth);//2018-02-9
            picker_be_format = persianCalendar.getPersianLongDate();//جمعه 20 بهمن 1396
            Prefs.putString("bargashtfa", tarikh_be_picker.getText().toString());//پنج‌شنبه 19 بهمن 1396
            Prefs.putString("bargasht", bargasht);//2018-02-11
        }
        if (view.getTag().equals("DatepickerdialogRaft")) {
            year_Min = year;
            monthMin = monthOfYear;
            dayMin = dayOfMonth;
            tarikh_az_picker.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
            //  tvBargasht.setText(persianCalendar.getPersianLongDate());
            raft = date_server(year, monthOfYear, dayOfMonth);
            PersianCalendar persianCalendarDatePicker2 = new PersianCalendar();
            persianCalendarDatePicker2.set(year_Min, monthMin, dayMin);
            if (Utility.campareDate(raft.replaceAll("-", "/"), bargasht.replaceAll("-", "/"))) {
                tarikh_be_picker.setText(persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName());
                datePickerDialog2.initialize(this, year_, month, day);
                datePickerDialog2.setMinDate(persianCalendarDatePicker2);
            } else {
                datePickerDialog2.setMinDate(persianCalendarDatePicker2);
            }
            Prefs.putString("bargashtfa", tarikh_be_picker.getText().toString());
            Prefs.putString("raft", raft);
            Prefs.putString("raftfa", tarikh_az_picker.getText().toString());
        }
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
    }

    public void anim() {
        YoYo.with(Techniques.SlideOutDown).duration(500).interpolate(new AccelerateDecelerateInterpolator()).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                YoYo.with(Techniques.SlideOutUp)
                        .duration(500)
                        .playOn(tvMaghsad);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                String start = "";
                String end = "";
                String startF = "";
                String endF = "";
                start = tvStart.getText().toString();
                end = tvMaghsad.getText().toString();

                tvStart.setText(end);
                tvMaghsad.setText(start);

                if (start.contains(getString(R.string.origin)) && end.contains(getString(R.string.destination))) {
                    tvStart.setText(getString(R.string.select_origin_city));
                    tvMaghsad.setText(getString(R.string.select_destination_city));
                } else if (start.contains(getString(R.string.origin))) {
                    tvMaghsad.setText(getString(R.string.select_destination_city));

                } else if (end.contains(getString(R.string.destination))) {
                    tvStart.setText(getString(R.string.select_origin_city));

                }
/////////////////////////

                String airportMaghsad = Prefs.getString("Value-Maghsad-Airport-Code-Train", "");
                String airPortMabda = Prefs.getString("Value-Mabda-Airport-Code-Train", "");
                Prefs.putString("Value_Mabda_Key_Train", airportMaghsad);
                Prefs.putString("Value_Maghsad_Key_Train", airPortMabda);
                String mabdaCity = Prefs.getString("Value_Mabda_Train", "");
                String mabdaAirPort = Prefs.getString("Value_Mabda_Key_Train", "");
                String maghsadCity = Prefs.getString("Value_Maghsad_Train", "");
                String maghsadAirPort = Prefs.getString("Value_Maghsad_Key_Train", "");
                Prefs.putString("Value_Mabda_Train", maghsadCity);
                Prefs.putString("Value_Mabda_Key_Train", maghsadAirPort);
                Prefs.putString("Value_Maghsad_Train", mabdaCity);
                Prefs.putString("Value_Maghsad_Key_Train", mabdaAirPort);
////////////////////////

                YoYo.with(Techniques.SlideInUp)
                        .duration(500)
                        .playOn(tvStart);
                YoYo.with(Techniques.SlideInDown)
                        .duration(500)
                        .playOn(tvMaghsad);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        })
                .playOn(tvStart);
        final Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_around_center_point);
        ivImage.startAnimation(animation);
    }

    @Override
    public void onDateSelected(CustomDate startDate, CustomDate endDate, boolean isGeo) {
        initCheckInCheckOutAnim();
        if (flagOneTwo == 1) {
            tarikh_az_picker.setText(startDate.getDescription());
            SingletonDate.getInstance().setStartDate(startDate);
        } else {
            SingletonDate.getInstance().setReverseDate(startDate, endDate);
            tarikh_az_picker.setText(startDate.getDescription());
            tarikh_be_picker.setText(endDate.getDescription());
            Prefs.putString("bargasht", endDate.getFullGeo());
            Prefs.putString("bargashtfa", endDate.getDescription());
            Prefs.putString("raft", startDate.getFullGeo());
            Prefs.putString("raftfa", startDate.getDescription());
            raft = startDate.getFullGeo();
            bargasht = endDate.getFullGeo();
            Prefs.putBoolean("GeoFlight", isGeo);

        }
    }
}

