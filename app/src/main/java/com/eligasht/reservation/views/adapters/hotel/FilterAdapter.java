package com.eligasht.reservation.views.adapters.hotel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.model.ModelCheckBox;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Mahsa.azizi on 1/10/2018.
 */

public class FilterAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ArrayList<ModelCheckBox> modelCheckBoxes = new ArrayList<>();



    public FilterAdapter(Context context, ArrayList<ModelCheckBox> modelCheckBoxes ) {
         this.modelCheckBoxes = modelCheckBoxes;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return modelCheckBoxes.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_filter_airline, null);
            holder = new ViewHolder();
            holder.cbCheck = convertView.findViewById(R.id.cbCheck);
            holder.textView1 = convertView.findViewById(R.id.textView1);
            holder.llLayout = convertView.findViewById(R.id.llLayout);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView1.setText(modelCheckBoxes.get(position).getName());
     holder.cbCheck.setEnabled(false);
        holder.cbCheck.setClickable(false);

        holder.llLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modelCheckBoxes.get(position).isCheck()) {


                    modelCheckBoxes.set(position, new ModelCheckBox(modelCheckBoxes.get(position).getName(), false));

                } else {
                    modelCheckBoxes.set(position, new ModelCheckBox(modelCheckBoxes.get(position).getName(), true));

                }
                notifyDataSetChanged();
            }
        });
        if (modelCheckBoxes.get(position).isCheck()){
            holder.cbCheck.setChecked(true);

            Log.e("trueadapter",modelCheckBoxes.get(position).isCheck()+"");
        }else{
            holder.cbCheck.setChecked(false);
            Log.e("falseadapter",modelCheckBoxes.get(position).isCheck()+"");

        }
     /*   if (modelCheckBoxes.get(position).isCheck()){
            holder.cbCheck.setChecked(true);
            Log.e("trueadapter",modelCheckBoxes.get(position).isCheck()+"");
        }else{
            holder.cbCheck.setChecked(false);
            Log.e("falseadapter",modelCheckBoxes.get(position).isCheck()+"");

        }
*/

        return convertView;
    }


    public class ViewHolder {
        TextView textView1;
        SmoothCheckBox cbCheck;
        LinearLayout llLayout;
    }
}
