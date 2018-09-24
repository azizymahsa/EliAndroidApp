package com.eligasht.reservation.views.activities.new_survey;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.views.activities.new_survey.model.GetReplyModel;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;
import com.eligasht.reservation.views.activities.survey.SurveyActivity;
import com.eligasht.reservation.views.dialogs.FilterPackageDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;

public class SurveyAnswerTimeFragment extends Fragment implements TimePickerDialog.OnTimeSetListener,View.OnClickListener {
    public TextView tvTitle,tvDesc,txtSetTime;
    public View v;
    private TimePickerDialog timePickerDialog2;
    private Context context;
    private DialogFragment dFragment;
    private Boolean questionIsRequired;
    private Integer questionID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.time_answer_survey, container, false);

        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvDesc = (TextView) v.findViewById(R.id.tvDesc);
        txtSetTime = (TextView) v.findViewById(R.id.txtSetTime);
        txtSetTime.setOnClickListener(this);

        String tvTitleL = getArguments().getString("tvTitleL");
        String tvDescL = getArguments().getString("tvDescL");

        tvTitle.setText(tvTitleL+" ");
        tvDesc.setText(tvDescL+" ");

        questionIsRequired = getArguments().getBoolean("QuestionIsRequired");
        questionID = getArguments().getInt("QuestionID");


        PersianCalendar persianCalendarDatePicker = new PersianCalendar();
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.set(persianCalendarDatePicker.getPersianYear(), persianCalendarDatePicker.getPersianMonth(), persianCalendarDatePicker.getPersianDay() + 1);

        timePickerDialog2 = TimePickerDialog.newInstance(this, persianCalendar.getTime().getHours(), persianCalendar.getTime().getMinutes(), true);


       // timePickerDialog2.show(getActivity().getFragmentManager(),"Time Picker");
        return v;
    }

    public static SurveyAnswerTimeFragment newInstance(String text, SurveyQuestionToShow surveyQuestionToShows, Context context) {
        SurveyAnswerTimeFragment f = new SurveyAnswerTimeFragment();
       // try {

        Bundle b = new Bundle();
        b.putString("tvTitleL",(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " "  );
        b.putString("tvDescL",(surveyQuestionToShows.getQuestionQuestion() != null ) ? surveyQuestionToShows.getQuestionQuestion() : " " );
        b.putBoolean("QuestionIsRequired",(surveyQuestionToShows.getQuestionAnswersArr() != null ) ?  surveyQuestionToShows.isQuestionIsRequired() : false );
        b.putInt("QuestionID",(surveyQuestionToShows.getQuestionID() != null ) ?  surveyQuestionToShows.getQuestionID() : 1 );

        f.setArguments(b);

     /*   return f;
        }catch (Exception e){
           e.getMessage() ;
        }*/
        return f;

    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        txtSetTime.setText(hourOfDay + ":" + minute);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtSetTime:
                timePickerDialog2.show(getFragmentManager(), "timeBargasht");
                break;
        }
    }
    public ArrayList<GetReplyModel> updateList() {
        ArrayList<GetReplyModel> strings=new ArrayList<>();
       // if (txtSetTime.getText().toString().length()>2) {
            GetReplyModel getReplyModel = new GetReplyModel(questionID, 0, txtSetTime.getText().toString(), questionIsRequired);
            strings.add(getReplyModel);
       // }
        return strings;
    }
}