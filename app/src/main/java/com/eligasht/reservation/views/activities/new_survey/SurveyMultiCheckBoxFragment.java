package com.eligasht.reservation.views.activities.new_survey;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.views.activities.new_survey.adapter.SurveyChBoxAdapter;
import com.eligasht.reservation.views.activities.new_survey.model.GetReplyModel;
import com.eligasht.reservation.views.activities.new_survey.model.InfoRowdata;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;

import java.util.ArrayList;

public class SurveyMultiCheckBoxFragment extends Fragment {
    private ListView llChb;

    private ArrayList<String> arrData=null;

    private SurveyChBoxAdapter surveyChBoxAdapter;
    private ArrayList<InfoRowdata> infodata;
    private ArrayList<String> listTest,listAnswerId;
    private Boolean questionIsRequired;
    private Integer questionID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.multi_check_box_answer, container, false);//row_multi_survey_answer
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/iran_sans_normal.ttf");
        TextView tvTitle = (TextView) v.findViewById(R.id.tvTitleM);
        tvTitle.setTypeface(type);
        TextView tvDesc = (TextView) v.findViewById(R.id.tvDescM);
        tvDesc.setTypeface(type);
        tvTitle.setText(getArguments().getString("tvTitleM")+"");
        tvDesc.setText(getArguments().getString("tvDescM")+"");


        llChb = (ListView) v.findViewById(R.id.llChb);

        listTest=getArguments().getStringArrayList("listTest");
        listAnswerId=getArguments().getStringArrayList("listAnswerId");

        questionIsRequired = getArguments().getBoolean("QuestionIsRequired");
        questionID = getArguments().getInt("QuestionID");

        infodata = new ArrayList<InfoRowdata>();
        for (int i = 0; i < listTest.size(); i++) {
            infodata.add(new InfoRowdata(false, i,Integer.parseInt(listAnswerId.get(i)),listTest.get(i)));

        }


        llChb.invalidate();
        surveyChBoxAdapter=new SurveyChBoxAdapter(infodata,listTest,getContext(),getActivity());
        llChb.setAdapter(surveyChBoxAdapter);

        return v;
    }

    public static SurveyMultiCheckBoxFragment newInstance(String text, SurveyQuestionToShow surveyQuestionToShows) {

        SurveyMultiCheckBoxFragment f = new SurveyMultiCheckBoxFragment();
        Bundle b = new Bundle();
        b.putString("tvTitleM",(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " "  );
        MainSurveyActivity.TV_TITLE=(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " ";
        b.putString("tvDescM",(surveyQuestionToShows.getQuestionQuestion() != null ) ? surveyQuestionToShows.getQuestionQuestion() : " " );

        b.putBoolean("QuestionIsRequired",(surveyQuestionToShows.getQuestionAnswersArr() != null ) ?  surveyQuestionToShows.isQuestionIsRequired() : false );
        b.putInt("QuestionID",(surveyQuestionToShows.getQuestionID() != null ) ?  surveyQuestionToShows.getQuestionID() : 1 );

        ArrayList<String> listTest=new ArrayList<>();
        ArrayList<String> listAnswerId=new ArrayList<>();

        for (int i = 0; i < surveyQuestionToShows.getQuestionAnswersArr().size(); i++) {

            listTest.add(i,surveyQuestionToShows.getQuestionAnswersArr().get(i).getText());
            b.putStringArrayList("listTest",listTest);

            listAnswerId.add(i,surveyQuestionToShows.getQuestionAnswersArr().get(i).getAnswerId());
            b.putStringArrayList("listAnswerId",listAnswerId);

        }
        f.setArguments(b);

        return f;
    }
    public ArrayList<GetReplyModel> updateList() {
        surveyChBoxAdapter.getData(questionID,questionIsRequired);

       /* ArrayList<GetReplyModel> strings=new ArrayList<>();
        GetReplyModel getReplyModel=new GetReplyModel(1,1,txtSetTime.getText().toString());
        strings.add(getReplyModel);
*/

        return surveyChBoxAdapter.getData(questionID,questionIsRequired);
    }
}