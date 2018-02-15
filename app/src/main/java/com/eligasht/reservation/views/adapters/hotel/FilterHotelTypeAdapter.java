package com.eligasht.reservation.views.adapters.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eligasht.reservation.R;

import com.eligasht.reservation.views.ui.dialog.hotel.FilterHotelTypeModel;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Reza.nejati on 1/13/2018.
 */

public class FilterHotelTypeAdapter extends BaseAdapter{
    private ArrayList<FilterHotelTypeModel> hotelProprtiesModels = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;

    public FilterHotelTypeAdapter(ArrayList<FilterHotelTypeModel> hotelProprtiesModels, Context context) {
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

