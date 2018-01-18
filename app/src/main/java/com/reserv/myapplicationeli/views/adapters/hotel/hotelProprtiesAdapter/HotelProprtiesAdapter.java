package com.reserv.myapplicationeli.views.adapters.hotel.hotelProprtiesAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseAPI;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 1/8/2018.
 */

public class HotelProprtiesAdapter extends BaseAdapter{
    private ArrayList<HotelProprtiesModels> hotelProprtiesModels = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;

    public HotelProprtiesAdapter(ArrayList<HotelProprtiesModels> hotelProprtiesModels, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.select_hotel_item_pro, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvImage = (TextView) convertView.findViewById(R.id.tvImage);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(hotelProprtiesModels.get(position).getPropertyTitle());
        holder.tvImage.setText(hotelProprtiesModels.get(position).getImage());

        return convertView;
    }


    public class ViewHolder{
        TextView tvTitle,tvImage;

    }
}

