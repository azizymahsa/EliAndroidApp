package com.eligasht.reservation.views.activities.new_survey.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.views.activities.new_survey.model.GetReplyModel;

import java.util.ArrayList;

public class SurveySpinnerCustomAdapter extends BaseAdapter {
    Context context;
   // int flags[];
    ArrayList<String> listAnswerId;
    ArrayList<String> listTest;
    LayoutInflater inflter;
    boolean isLanguage;


    public SurveySpinnerCustomAdapter(Context applicationContext, ArrayList<String> listTest, ArrayList<String> listAnswerId, boolean isLanguage) {
        this.context = applicationContext;
       // this.flags = flags;
        this.isLanguage = isLanguage;
        this.listAnswerId = listAnswerId;
        this.listTest = listTest;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return listTest.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final TextView names;
       // if (isLanguage){
            view = inflter.inflate(R.layout.survey_custom_spinner_item, null);
            names = view.findViewById(R.id.textView);
        //final CheckBox cb = (CheckBox) row.findViewById(R.id.chbContent);
       /* names.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               value= listTest.get(i);
                id=Integer.parseInt(listAnswerId.get(i));

                System.out.println("Selectes ID == "+listAnswerId.get(i)+"Text"+ listTest.get(i));

            }
        });*/

           // ImageView icon = view.findViewById(R.id.imageView);
            //icon.setImageResource(flags[i]);


        /*}else{
            view = inflter.inflate(R.layout.custom_spinner_item_2, null);
            names = view.findViewById(R.id.textView);



        }*/
        names.setText(listTest.get(i));
        names.setTypeface(Typeface.createFromAsset(context.getAssets(),context.getString(R.string.iran_sans_normal_ttf)));
        return view;
    }
   /* public ArrayList<GetReplyModel> getData(Integer questionID, Boolean questionIsRequired) {
        ArrayList<GetReplyModel> getReplyModels=new ArrayList<>();

                GetReplyModel getReplyModel=new GetReplyModel(questionID,id,value,questionIsRequired);
                System.out.println("Selectes  == "+id+"TEXT="+value);
                getReplyModels.add(0,getReplyModel);

        return getReplyModels;
    }*/
}
