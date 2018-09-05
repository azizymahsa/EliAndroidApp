package com.eligasht.reservation.views.activities.new_survey;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;

public class SurveyAnswerLongFragment extends Fragment {
    public TextView tvTitle,tvDesc;
    public View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.long_answer_survey, container, false);

        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvDesc = (TextView) v.findViewById(R.id.tvDesc);

        String tvTitleL = getArguments().getString("tvTitleL");
        String tvDescL = getArguments().getString("tvDescL");

        tvTitle.setText(tvTitleL+" ");
        tvDesc.setText(tvDescL+" ");

        return v;
    }

    public static SurveyAnswerLongFragment newInstance(String text, SurveyQuestionToShow surveyQuestionToShows) {
        SurveyAnswerLongFragment f = new SurveyAnswerLongFragment();
       // try {



        Bundle b = new Bundle();
        b.putString("tvTitleL",(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " "  );
        b.putString("tvDescL",(surveyQuestionToShows.getQuestionQuestion() != null ) ? surveyQuestionToShows.getQuestionQuestion() : " " );

        f.setArguments(b);

     /*   return f;
        }catch (Exception e){
           e.getMessage() ;
        }*/
        return f;

    }
}