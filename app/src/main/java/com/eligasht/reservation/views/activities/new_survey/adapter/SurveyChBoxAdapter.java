package com.eligasht.reservation.views.activities.new_survey.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.views.activities.new_survey.model.InfoRowdata;

import java.util.ArrayList;

public class SurveyChBoxAdapter extends BaseAdapter {

    private final ArrayList<String> data;
    private final ArrayList<InfoRowdata> infodata;
    private final Activity activity;
    private final Context context;

    public SurveyChBoxAdapter(ArrayList<InfoRowdata> infodata, ArrayList<String> data, Context context, Activity activity) {
    this.infodata=infodata;
    this.data=data;
    this.context=context;
    this.activity=activity;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View row = null;
        row = View.inflate(context, R.layout.row_multi_survey_answer, null);
        TextView tvContent=(TextView) row.findViewById(R.id.tvContent);
        //tvContent.setText(data[position]);
        tvContent.setText(data.get(position));
        //System.out.println("The Text is here like.. == "+tvContent.getText().toString());

        final CheckBox cb = (CheckBox) row.findViewById(R.id.chbContent);
        cb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (infodata.get(position).isclicked) {
                    infodata.get(position).isclicked = false;
                } else {
                    infodata.get(position).isclicked = true;
                }

                for(int i=0;i<infodata.size();i++)
                {
                    if (infodata.get(i).isclicked)
                    {
                        System.out.println("Selectes Are == "+ data.get(i));
                    }
                }
            }
        });

        if (infodata.get(position).isclicked) {

            cb.setChecked(true);
        }
        else {
            cb.setChecked(false);
        }
        return row;
    }

}