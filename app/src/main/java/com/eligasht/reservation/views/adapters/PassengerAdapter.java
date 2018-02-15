package com.eligasht.reservation.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eligasht.reservation.R;
import com.eligasht.reservation.models.hotel.adapter.PassangerServiceModel;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class PassengerAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ArrayList<PassangerServiceModel> passangerServiceModels = new ArrayList<>();



    public PassengerAdapter(Context context, ArrayList<PassangerServiceModel> passangerServiceModels ) {
        this.passangerServiceModels = passangerServiceModels;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return passangerServiceModels.size();
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
            convertView = inflater.inflate(R.layout.passenger_service, null);
            holder = new ViewHolder();
            holder.cbCheck = (SmoothCheckBox) convertView.findViewById(R.id.cbCheck);
            holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
/*

        holder.textView1.setText(modelCheckBoxes.get(position).getName());
        holder.cbCheck.setEnabled(false);

        if (modelCheckBoxes.get(position).isCheck()){
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
    }
}
