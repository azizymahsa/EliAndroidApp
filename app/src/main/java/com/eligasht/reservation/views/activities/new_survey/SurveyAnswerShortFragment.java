package com.eligasht.reservation.views.activities.new_survey;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.views.activities.new_survey.model.GetReplyModel;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;

import java.util.ArrayList;


public class SurveyAnswerShortFragment extends Fragment {
    public TextView tvTitle,tvDesc;
    public EditText editText;
    private Boolean questionIsRequired;
    private Integer questionID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.short_answer_survey_frag, container, false);

        editText = (EditText) v.findViewById(R.id.editText);

        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvDesc = (TextView) v.findViewById(R.id.tvDesc);
        tvTitle.setText(getArguments().getString("tvTitleS")+"");
        tvDesc.setText(getArguments().getString("tvDescS")+"");

        questionIsRequired = getArguments().getBoolean("QuestionIsRequired");
        questionID = getArguments().getInt("QuestionID");

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

        b.putBoolean("QuestionIsRequired",(surveyQuestionToShows.getQuestionAnswersArr() != null ) ?  surveyQuestionToShows.isQuestionIsRequired() : false );
        b.putInt("QuestionID",(surveyQuestionToShows.getQuestionID() != null ) ?  surveyQuestionToShows.getQuestionID() : 1 );

        f.setArguments(b);

        return f;
    }

    public ArrayList<GetReplyModel> updateList() {
        ArrayList<GetReplyModel> strings=new ArrayList<>();
        GetReplyModel getReplyModel=new GetReplyModel(questionID,0,editText.getText().toString(),questionIsRequired);
        strings.add(getReplyModel);

        return strings;
    }
}