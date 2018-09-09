package com.eligasht.reservation.views.activities.new_survey;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.views.activities.new_survey.adapter.SurveySpinnerCustomAdapter;
import com.eligasht.reservation.views.activities.new_survey.model.GetReplyModel;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;


import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;


public class SurveyAnswerSelectionFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public TextView tvTitle,tvDesc;
    Spinner languageSpinner, curencySpinner, officeSpinner;

    String[] countryNames = {"ایران (IR)", "(UK)England", "(TR) Türkiye"};
    int flags[] = {R.drawable.iran, R.drawable.united_kingdom, R.drawable.turkey};
    String[] curencyNames = {"IRR(iran)"};
    String[] officeNames = {"Eligasht-IR", "Eligasht-UK", "Eligasht-TK"};
    private FancyButton txtIcon;
    private Boolean questionIsRequired;
    private Integer questionID;
    SurveySpinnerCustomAdapter surveySpinnerCustomAdapter;
    ArrayList<String> listTest=new ArrayList<>();
    ArrayList<String> listAnswerId=new ArrayList<>();
    int idd=0;
    String value="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.selection_answer_survey_frag, container, false);

        txtIcon = v.findViewById(R.id.txtIcon);
     /*   txtIcon.setCustomTextFont("fonts/icomoon.ttf");
        txtIcon.setText(getString(R.string.search_back_right));*/
        /*txtIcon.setCustomTextFont("fonts/IconFonts.ttf");
        txtIcon.setText(getString(R.string.icon_arrow_down));*/

        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        // tvDesc = (TextView) v.findViewById(R.id.tvDesc);
        tvTitle.setText(getArguments().getString("tvTitle")+"");


        questionIsRequired = getArguments().getBoolean("QuestionIsRequired");
        questionID = getArguments().getInt("QuestionID");

        languageSpinner = v.findViewById(R.id.languageSpinner);

        languageSpinner.setOnItemSelectedListener(this);

        listTest=getArguments().getStringArrayList("listTest");
        listAnswerId=getArguments().getStringArrayList("listAnswerId");
        surveySpinnerCustomAdapter= new SurveySpinnerCustomAdapter(getContext(), listTest,listAnswerId, true);
        languageSpinner.setAdapter(surveySpinnerCustomAdapter);

        // returns current Fragment item displayed within the pager
        return v;
    }

    public static SurveyAnswerSelectionFragment newInstance(String text, SurveyQuestionToShow surveyQuestionToShows) {

        SurveyAnswerSelectionFragment f = new SurveyAnswerSelectionFragment();
        Bundle b = new Bundle();
        b.putString("tvTitle",(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " "  );

        b.putBoolean("QuestionIsRequired",(surveyQuestionToShows.getQuestionAnswersArr() != null ) ?  surveyQuestionToShows.isQuestionIsRequired() : false );
        b.putInt("QuestionID",(surveyQuestionToShows.getQuestionID() != null ) ?  surveyQuestionToShows.getQuestionID() : 1 );

       // b.putString("tvDesc",(surveyQuestionToShows.getQuestionQuestion() != null ) ? surveyQuestionToShows.getQuestionQuestion() : " " );
        ArrayList<String> listTest=new ArrayList<>();
        ArrayList<String> listAnswerId=new ArrayList<>();
        for (int i = 0; i < surveyQuestionToShows.getQuestionAnswersArr().size(); i++) {
           // b.putString("rd"+i,(surveyQuestionToShows.getQuestionAnswersArr().get(i).getText() != null ) ? surveyQuestionToShows.getQuestionAnswersArr().get(i).getText() : "null" );

            listTest.add(i,surveyQuestionToShows.getQuestionAnswersArr().get(i).getText());
            b.putStringArrayList("listTest",listTest);

            listAnswerId.add(i,surveyQuestionToShows.getQuestionAnswersArr().get(i).getAnswerId());
            b.putStringArrayList("listAnswerId",listAnswerId);
        }
        f.setArguments(b);

        return f;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        value= listTest.get(position);
        idd=Integer.parseInt(listAnswerId.get(position));

        System.out.println("Selectes ID == "+listAnswerId.get(position)+"Text"+ listTest.get(position));
        /*switch (position) {
            case 0:
                //lang = "fa";
                curencyNames = new String[]{"IRR(iran)"};
                curencySpinner.setAdapter(new SurveySpinnerCustomAdapter(getContext(), curencyNames, false));

                break;
            case 1:
                //lang = "en";
                curencyNames = new String[]{"GB"};
                curencySpinner.setAdapter(new SurveySpinnerCustomAdapter(getContext(), curencyNames, false));

                break;
            case 2:
               // lang = "tr";
                curencyNames = new String[]{"TRY", "EUR"};
                curencySpinner.setAdapter(new SurveySpinnerCustomAdapter(getContext(), curencyNames, false));

                break;
        }*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public ArrayList<GetReplyModel> updateList() {
       // surveySpinnerCustomAdapter.getData(questionID,questionIsRequired);

     ArrayList<GetReplyModel> getReplyModels=new ArrayList<>();

        GetReplyModel getReplyModel=new GetReplyModel(questionID,idd,value,questionIsRequired);
        System.out.println("Selectes  == "+idd+"TEXT="+value);
        getReplyModels.add(0,getReplyModel);

        return getReplyModels;//surveySpinnerCustomAdapter.getData(questionID,questionIsRequired);

    }
}