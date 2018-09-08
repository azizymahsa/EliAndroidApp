package com.eligasht.reservation.views.activities.new_survey;

import android.app.Fragment;
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
import com.eligasht.reservation.views.activities.new_survey.model.InfoRowdata;
import com.eligasht.reservation.views.activities.new_survey.model.SurveyQuestionToShow;

import java.util.ArrayList;

public class SurveyMultiCheckBoxFragment extends Fragment {
    private ListView llChb;

   /* private String[] data = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
            "x", "y", "z" };*/
    private ArrayList<String> arrData=null;

    private ArrayList<InfoRowdata> infodata;
    private ArrayList<String> listTest,listAnswerId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.multi_check_box_answer, container, false);//row_multi_survey_answer

        TextView tvTitle = (TextView) v.findViewById(R.id.tvTitleM);
        TextView tvDesc = (TextView) v.findViewById(R.id.tvDescM);
        tvTitle.setText(getArguments().getString("tvTitleM")+"");
        tvDesc.setText(getArguments().getString("tvDescM")+"");



      /*  arrData=new ArrayList<String>();
        arrData.add("a");
        arrData.add("b");
        arrData.add("c");
        arrData.add("d");
        arrData.add("e");
        arrData.add("f");
        arrData.add("g");
        arrData.add("h");
        arrData.add("i");
        arrData.add("j");
        arrData.add("k");
        arrData.add("l");
        arrData.add("m");
        arrData.add("n");
        arrData.add("o");
        arrData.add("p");*/
        llChb = (ListView) v.findViewById(R.id.llChb);

        listTest=getArguments().getStringArrayList("listTest");
        listAnswerId=getArguments().getStringArrayList("listAnswerId");


        infodata = new ArrayList<InfoRowdata>();
        for (int i = 0; i < listTest.size(); i++) {
            infodata.add(new InfoRowdata(false, i,Integer.parseInt(listAnswerId.get(i)),listTest.get(i)));
            // System.out.println(i);
            //System.out.println("Data is == "+data[i]);
        }


        llChb.invalidate();
        llChb.setAdapter(new SurveyChBoxAdapter(infodata,listTest,getContext(),getActivity()));

        return v;
    }

    public static SurveyMultiCheckBoxFragment newInstance(String text, SurveyQuestionToShow surveyQuestionToShows) {

        SurveyMultiCheckBoxFragment f = new SurveyMultiCheckBoxFragment();
        Bundle b = new Bundle();
        b.putString("tvTitleM",(surveyQuestionToShows.getSectionText() != null ) ? surveyQuestionToShows.getSectionText() : " "  );
        b.putString("tvDescM",(surveyQuestionToShows.getQuestionQuestion() != null ) ? surveyQuestionToShows.getQuestionQuestion() : " " );
        ArrayList<String> listTest=new ArrayList<>();
        ArrayList<String> listAnswerId=new ArrayList<>();

        for (int i = 0; i < surveyQuestionToShows.getQuestionAnswersArr().size(); i++) {

            listTest.add(i,surveyQuestionToShows.getQuestionAnswersArr().get(i).getText());
            b.putStringArrayList("listTest",listTest);

            listAnswerId.add(i,surveyQuestionToShows.getQuestionAnswersArr().get(i).getAnswerId());
            b.putStringArrayList("listAnswerId",listAnswerId);
           // b.putStringArrayList(i,(surveyQuestionToShows.getQuestionAnswersArr().get(i).getText() != null ) ? surveyQuestionToShows.getQuestionAnswersArr().get(i).getText() : "null" );



        }
        f.setArguments(b);

        return f;
    }
}