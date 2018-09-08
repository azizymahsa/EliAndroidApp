package com.eligasht.reservation.views.activities.new_survey;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;

public class SurveyMultiRadioFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.multi_check_answer_survey, container, false);

        TextView tvTitle = (TextView) v.findViewById(R.id.tvTitleM);
        TextView tvDesc = (TextView) v.findViewById(R.id.tvDescM);
        tvTitle.setText(getArguments().getString("tvTitleM")+"");
        tvDesc.setText(getArguments().getString("tvDescM")+"");

        RadioButton first = (RadioButton) v.findViewById(R.id.first);
        RadioButton second = (RadioButton)v. findViewById(R.id.second);
        RadioButton  third = (RadioButton) v.findViewById(R.id.third);
        RadioButton  four = (RadioButton) v.findViewById(R.id.four);
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
                        Toast.makeText(getContext(), "Your Male", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.second:
                        Toast.makeText(getContext(), "Your Female", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.third:
                        Toast.makeText(getContext(), "Your Other", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.four:
                    Toast.makeText(getContext(), "Your Other", Toast.LENGTH_SHORT).show();
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
        b.putString("tvDescM",(surveyQuestionToShows.getQuestionQuestion() != null ) ? surveyQuestionToShows.getQuestionQuestion() : " " );


        for (int i = 0; i < surveyQuestionToShows.getQuestionAnswersArr().size(); i++) {
            b.putString("rd"+i,(surveyQuestionToShows.getQuestionAnswersArr().get(i).getText() != null ) ? surveyQuestionToShows.getQuestionAnswersArr().get(i).getText() : "null" );



        }
        f.setArguments(b);

        return f;
    }
}