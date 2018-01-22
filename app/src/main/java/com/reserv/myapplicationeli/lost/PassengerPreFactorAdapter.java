package com.reserv.myapplicationeli.lost;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.ModelCheckBox;
import com.reserv.myapplicationeli.views.adapters.hotel.FilterAdapter;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Reza.nejati on 1/22/2018.
 */

public class PassengerPreFactorAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ArrayList<PassengerPreFactorModel> passengerPreFactorModels = new ArrayList<>();



    public PassengerPreFactorAdapter(Context context, ArrayList<PassengerPreFactorModel> passengerPreFactorModels ) {
        this.passengerPreFactorModels = passengerPreFactorModels;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return passengerPreFactorModels.size();
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
            convertView = inflater.inflate(R.layout.passenger_prefactor_item, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvGender = (TextView) convertView.findViewById(R.id.tvGender);
            holder.tvBrithday = (TextView) convertView.findViewById(R.id.tvBrithday);
            holder.tvPass = (TextView) convertView.findViewById(R.id.tvPass);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvName.setText(passengerPreFactorModels.get(position).getRqPassenger_FirstNameFa()+" "+passengerPreFactorModels.get(position).getRqPassenger_LastNameFa());
        holder.tvGender.setText(passengerPreFactorModels.get(position).getGender());
        holder.tvBrithday.setText(passengerPreFactorModels.get(position).getRqPassenger_Birthdate());
        holder.tvPass.setText(passengerPreFactorModels.get(position).getRqPassenger_PassNo());





        return convertView;
    }


    public class ViewHolder {
        TextView tvName,tvGender,tvBrithday,tvPass;

    }
}
