package com.eligasht.reservation.views.activities.new_survey;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;


public class SurveyAnswerShortFragment extends Fragment {
    public TextView tvTitle,tvDesc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.short_answer_survey_frag, container, false);

         tvTitle = (TextView) v.findViewById(R.id.tvTitle);
         tvDesc = (TextView) v.findViewById(R.id.tvDesc);
        tvTitle.setText(getArguments().getString("tvTitleS")+"");
        tvDesc.setText(getArguments().getString("tvDescS")+"");

        // returns current Fragment item displayed within the pager
        return v;
    }

    public String getText() {
        String title=tvTitle.getText().toString();
        String desc=tvDesc.getText().toString();
        return title+"-"+desc+"-";
    }

    public static SurveyAnswerShortFragment newInstance(String text, SurveyQuestionToShow surveyQuestionToShows) {

        SurveyAnswerShortFragment f = new SurveyAnswerShortFragment();
        Bundle b = new Bundle();
        b.putString("tvTitleS",(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " "  );
        b.putString("tvDescS",(surveyQuestionToShows.getQuestionQuestion() != null ) ? surveyQuestionToShows.getQuestionQuestion() : " " );

        f.setArguments(b);

        return f;
    }
}