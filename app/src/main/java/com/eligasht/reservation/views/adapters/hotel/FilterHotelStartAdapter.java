package com.eligasht.reservation.views.adapters.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.hotel.adapter.FilterStarModel;
import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelTypeModel;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Authors:
 * Reza Nejati <reza.n.j.t.i@gmail.com>
 * Copyright © 2017
 */
public class FilterHotelStartAdapter extends BaseAdapter {
    private ArrayList<FilterStarModel> hotelProprtiesModels = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;

    public FilterHotelStartAdapter(ArrayList<FilterStarModel> hotelProprtiesModels, Context context) {
        this.hotelProprtiesModels = hotelProprtiesModels;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return hotelProprtiesModels.size();
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
            convertView = inflater.inflate(R.layout.filter_hotel_type, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.checkbox = (SmoothCheckBox) convertView.findViewById(R.id.checkbox);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(hotelProprtiesModels.get(position).getTitle());

        holder.checkbox.setEnabled(false);
        holder.checkbox.setClickable(false);


        if (hotelProprtiesModels.get(position).isCheck()){
            holder.checkbox.setChecked(true);





        }else{
            holder.checkbox.setChecked(false);

        }









        return convertView;
    }


    public class ViewHolder{
        TextView tvTitle;
        SmoothCheckBox checkbox;


    }
}

