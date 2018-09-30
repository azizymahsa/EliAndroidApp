package com.eligasht.reservation.views.activities.new_survey;
import android.graphics.Typeface;
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

public class SurveyAnswerLongFragment extends Fragment {
    public TextView tvTitle,tvDesc;
    public EditText editText;
    public View v;
    private Boolean questionIsRequired;
    private Integer questionID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.long_answer_survey, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/iran_sans_normal.ttf");
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvTitle.setTypeface(type);
        tvDesc = (TextView) v.findViewById(R.id.tvDesc);
        tvDesc.setTypeface(type);
        editText = (EditText) v.findViewById(R.id.editText);
        editText.setTypeface(type);

        String tvTitleL = getArguments().getString("tvTitleL");
        String tvDescL = getArguments().getString("tvDescL");

        tvTitle.setText(tvTitleL+" ");
        tvDesc.setText(tvDescL+" ");

        questionIsRequired = getArguments().getBoolean("QuestionIsRequired");
        questionID = getArguments().getInt("QuestionID");

        return v;
    }

    public static SurveyAnswerLongFragment newInstance(String text, SurveyQuestionToShow surveyQuestionToShows) {
        SurveyAnswerLongFragment f = new SurveyAnswerLongFragment();
       // try {



        Bundle b = new Bundle();
        b.putString("tvTitleL",(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " "  );
        MainSurveyActivity.TV_TITLE=(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " ";
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
   public ArrayList<GetReplyModel> updateList() {
        ArrayList<GetReplyModel> strings=new ArrayList<>();
       //if (editText.getText().toString().length()>2) {
           GetReplyModel getReplyModel = new GetReplyModel(questionID, 0, editText.getText().toString(), questionIsRequired);
           strings.add(getReplyModel);
      // }
        return strings;
    }
}