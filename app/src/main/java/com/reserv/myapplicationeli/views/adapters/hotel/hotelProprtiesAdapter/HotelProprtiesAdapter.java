package com.reserv.myapplicationeli.views.adapters.hotel.hotelProprtiesAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
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
    Activity context;

    public HotelProprtiesAdapter(ArrayList<HotelProprtiesModels> hotelProprtiesModels, Activity context) {
        this.hotelProprtiesModels = hotelProprtiesModels;
        this.context = context;
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
        try{
            Typeface t = Typeface.createFromAsset(context.getAssets(), "fonts/Facility.ttf");
            holder.tvImage.setTypeface(t);
            String icon = hotelProprtiesModels.get(position).getImage().substring(1);
            icon="&#"+icon+";";
           // char c = icon.charAt(0);

            String valHexStr = icon.replace("&#x", "").replace(";", "");
            long valLong = Long.parseLong(valHexStr,16);

            holder.tvImage.setText((char)valLong+"");
            Log.e("dfsf", hotelProprtiesModels.get(position).getImage() );
            Log.e("dfsf",  holder.tvImage.getText().toString() );

        }catch (Exception e){}

        holder.tvTitle.setText(hotelProprtiesModels.get(position).getPropertyTitle());



        return convertView;
    }


    public class ViewHolder{
        TextView tvTitle,tvImage;

    }
}

