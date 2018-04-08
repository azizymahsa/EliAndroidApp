package com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.lost.hotel.HotelPreFactorAdapter;
import com.eligasht.reservation.lost.hotel.HotelPreFactorModel;
import com.eligasht.reservation.views.ui.NonScrollGridView;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import java.util.ArrayList;
/**
 * Created by Reza.nejati on 1/8/2018.
 */

public class HotelProprtiesAdapter extends BaseAdapter {
    Activity context;
    boolean isPolicy;
    private ArrayList<HotelProprtiesModels> hotelProprtiesModels = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;


    public HotelProprtiesAdapter(ArrayList<HotelProprtiesModels> hotelProprtiesModels, Activity context, boolean isPolicy) {
        this.hotelProprtiesModels = hotelProprtiesModels;
        this.context = context;
        this.isPolicy = isPolicy;
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
            if (hotelProprtiesModels.get(position).getCategoryID() == 1) {
                convertView = inflater.inflate(R.layout.select_hotel_item_pro, null);

            } else {

                convertView = inflater.inflate(R.layout.select_hotel_item_pro2, null);

            }
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            holder.tvImage = convertView.findViewById(R.id.tvImage);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (isPolicy){
            holder.tvTitle.setText(hotelProprtiesModels.get(position).getPropertyTitle());

            holder.tvImage.setText(hotelProprtiesModels.get(position).getPropertyDescription());
        }else{
            if (hotelProprtiesModels.get(position).getCategoryID() == 1) {
                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.Facility));
                    holder.tvImage.setTypeface(t);
                    String icon = hotelProprtiesModels.get(position).getImage().substring(1);
                    icon = "&#" + icon + ";";
                    // char c = icon.charAt(0);

                    String valHexStr = icon.replace("&#x", "").replace(";", "");
                    long valLong = Long.parseLong(valHexStr, 16);

                    holder.tvImage.setText((char) valLong + "");


                } catch (Exception e) {
                }
                holder.tvTitle.setText(hotelProprtiesModels.get(position).getPropertyTitle());
               // nonScrollGridView.setNumColumns(3);

            } else {
             //   nonScrollGridView.setNumColumns(1);


                holder.tvTitle.setText(hotelProprtiesModels.get(position).getPropertyTitle());
                holder.tvImage.setText(hotelProprtiesModels.get(position).getPropertyDescription());


            }
        }



        return convertView;
    }


    public class ViewHolder {
        TextView tvTitle, tvImage;

    }
}

