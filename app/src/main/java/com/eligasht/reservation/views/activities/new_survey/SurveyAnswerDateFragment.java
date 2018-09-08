package com.eligasht.reservation.views.activities.new_survey;

import android.animation.Animator;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.eligasht.reservation.tools.datetools.SolarCalendar;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;
import com.eligasht.reservation.views.picker.global.enums.TypeUsageOfCalendar;
import com.eligasht.reservation.views.picker.global.listeners.ICallbackCalendarDialog;
import com.eligasht.reservation.views.picker.global.model.CustomDate;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.picker.utils.CalendarDialog;
import com.eligasht.reservation.views.ui.PassengerActivity;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

public class SurveyAnswerDateFragment extends Fragment implements View.OnClickListener, ICallbackCalendarDialog {
    public TextView tvTitle,tvDesc,txtSetDate;
    public View v;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogGregorian1;
    private LottieAnimationView lottieCheckin;
    private String departureDate;
    private LinearLayout layout_depart_date;
    CalendarDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.date_answer_survey, container, false);

        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvDesc = (TextView) v.findViewById(R.id.tvDesc);

       lottieCheckin = v.findViewById(R.id.lottie_checkin);
        lottieCheckin.setSpeed(2f);


        txtSetDate = (TextView) v.findViewById(R.id.txtSetDate);
        txtSetDate.setOnClickListener(this);

        layout_depart_date = (LinearLayout) v.findViewById(R.id.layout_depart_date);
        layout_depart_date.setOnClickListener(this);

        String tvTitleL = getArguments().getString("tvTitleL");
        String tvDescL = getArguments().getString("tvDescL");

        tvTitle.setText(tvTitleL+" ");
        tvDesc.setText(tvDescL+" ");

        dialog = new CalendarDialog();
        departureDate = SingletonDate.getInstance().getStartDate().getFullGeo();
        txtSetDate.setText(SingletonDate.getInstance().getStartDate().getDescription());

     /*   PersianCalendar persianCalendarDatePicker = new PersianCalendar();
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth(), persianCalendarDatePicker.getPersianDay() + 1);

       // timePickerDialog2 = TimePickerDialog.newInstance(this, persianCalendar.getTime().getHours(), persianCalendar.getTime().getMinutes(), true);*/
/*//=====================================================================================================
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
                txtSetDate.setText(""+year+"/"+monthl+"/"+dayl);
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

              //  datePickerDialog.initialize(PassengerActivity.this, Integer.parseInt(yearMF), Integer.parseInt(monthMF), Integer.parseInt(dayMF));
             //   datePickerDialog.show(getSupportFragmentManager(), "DatepickerdialogRaft");

            }
        });*/
////////////


        return v;
    }

    public static SurveyAnswerDateFragment newInstance(String text, SurveyQuestionToShow surveyQuestionToShows, Context context) {
        SurveyAnswerDateFragment f = new SurveyAnswerDateFragment();


        Bundle b = new Bundle();
        b.putString("tvTitleL",(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " "  );
        b.putString("tvDescL",(surveyQuestionToShows.getQuestionQuestion() != null ) ? surveyQuestionToShows.getQuestionQuestion() : " " );

        f.setArguments(b);

        return f;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtSetDate:

                break;
                case R.id.layout_depart_date:

                this.dialog.create(getActivity(), getContext(), this, SingletonDate.getInstance().getStartDate(), TypeUsageOfCalendar.InternationalFlight);
                break;
        }
    }

    @Override
    public void onDateSelected(CustomDate startDate, CustomDate endDate, boolean isGeo) {
        initCheckInCheckOutAnim();
        departureDate = startDate.getFullGeo();
        SingletonDate.getInstance().setStartDate(startDate);
        txtSetDate.setText(startDate.getDescription());

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

        lottieCheckin.playAnimation();
    }
}