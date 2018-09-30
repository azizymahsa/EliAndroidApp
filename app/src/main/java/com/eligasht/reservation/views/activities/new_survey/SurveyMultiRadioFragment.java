package com.eligasht.reservation.views.activities.new_survey;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.views.activities.new_survey.model.GetReplyModel;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;

import java.util.ArrayList;

public class SurveyMultiRadioFragment extends Fragment {

    private TextView tvTitle;
    private TextView answer;
    private Boolean questionIsRequired;
    private Integer questionID;
    RadioButton first,second,third,four;
    private int id=0;
    private String value="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.multi_check_answer_survey, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/iran_sans_normal.ttf");
         tvTitle = (TextView) v.findViewById(R.id.tvTitleM);
         tvTitle.setTypeface(type);
        TextView tvDesc = (TextView) v.findViewById(R.id.tvDescM);
        tvDesc.setTypeface(type);
        tvTitle.setText(getArguments().getString("tvTitleM")+"");
        tvDesc.setText(getArguments().getString("tvDescM")+"");

        questionIsRequired = getArguments().getBoolean("QuestionIsRequired");
        questionID = getArguments().getInt("QuestionID");

         first = (RadioButton) v.findViewById(R.id.first);
         first.setTypeface(type);
         second = (RadioButton)v. findViewById(R.id.second);
        second.setTypeface(type);
         third = (RadioButton) v.findViewById(R.id.third);
        third.setTypeface(type);
         four = (RadioButton) v.findViewById(R.id.four);
        four.setTypeface(type);
        try {
            if (getArguments().getString("rd0") != null) {
                first.setVisibility(View.VISIBLE);
                first.setText(getArguments().getString("rd0") + "");
            }else{
                first.setVisibility(View.GONE);
            }
            if (getArguments().getString("rd1") != null) {
                second.setVisibility(View.VISIBLE);
                second.setText(getArguments().getString("rd1") + "");
            }else{
                second.setVisibility(View.GONE);
            }
            if (getArguments().getString("rd2") != null) {
                third.setVisibility(View.VISIBLE);
                third.setText(getArguments().getString("rd2") + "");
            }else{
                third.setVisibility(View.GONE);
            }
            if (getArguments().getString("rd3") != null) {
                four.setVisibility(View.VISIBLE);
                four.setText(getArguments().getString("rd3")+"");
            }else{
                four.setVisibility(View.GONE);
            }

        }catch (Exception e){

        }
        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radios);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                int buttonId = radioGroup.getCheckedRadioButtonId();
                switch(buttonId) {
                    case R.id.first:
                       id=getArguments().getInt("rdI0");
                       value= first.getText().toString();
                      //  Toast.makeText(getContext(),  first.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.second:
                        id=getArguments().getInt("rdI1");
                        value= second.getText().toString();
                      //  Toast.makeText(getContext(),  second.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.third:
                        id=getArguments().getInt("rdI2");
                        value= third.getText().toString();
                      //  Toast.makeText(getContext(),  third.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.four:
                        id=getArguments().getInt("rdI3");
                        value= four.getText().toString();
                    //Toast.makeText(getContext(),  four.getText().toString(), Toast.LENGTH_SHORT).show();
                    break;
                }
            }

        });
        return v;
    }

    public static SurveyMultiRadioFragment newInstance(String text, SurveyQuestionToShow surveyQuestionToShows) {

        SurveyMultiRadioFragment f = new SurveyMultiRadioFragment();
        Bundle b = new Bundle();
        b.putString("tvTitleM",(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " "  );
        MainSurveyActivity.TV_TITLE=(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " ";
        b.putString("tvDescM",(surveyQuestionToShows.getQuestionQuestion() != null ) ? surveyQuestionToShows.getQuestionQuestion() : " " );

        b.putBoolean("QuestionIsRequired",(surveyQuestionToShows.getQuestionAnswersArr() != null ) ?  surveyQuestionToShows.isQuestionIsRequired() : false );
        b.putInt("QuestionID",(surveyQuestionToShows.getQuestionID() != null ) ?  surveyQuestionToShows.getQuestionID() : 1 );

        for (int i = 0; i < surveyQuestionToShows.getQuestionAnswersArr().size(); i++) {
            b.putString("rd"+i,(surveyQuestionToShows.getQuestionAnswersArr().get(i).getText() != null ) ? surveyQuestionToShows.getQuestionAnswersArr().get(i).getText() : "null" );
            b.putInt("rdI"+i,(surveyQuestionToShows.getQuestionAnswersArr().get(i).getAnswerId() != null ) ? Integer.parseInt(surveyQuestionToShows.getQuestionAnswersArr().get(i).getAnswerId()) : 1 );

        }
        f.setArguments(b);

        return f;
    }

    public ArrayList<GetReplyModel> updateList() {
        ArrayList<GetReplyModel> strings=new ArrayList<>();

            GetReplyModel getReplyModel = new GetReplyModel(questionID, id, value, questionIsRequired);
            strings.add(getReplyModel);

        return strings;
    }
}