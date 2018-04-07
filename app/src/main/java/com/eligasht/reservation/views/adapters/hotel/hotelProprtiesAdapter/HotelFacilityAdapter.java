package com.eligasht.reservation.views.adapters.hotel.hotelProprtiesAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.lost.hotel.HotelPreFactorAdapter;
import com.eligasht.reservation.views.ui.NonScrollGridView;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 4/7/2018.
 */

public class HotelFacilityAdapter extends RecyclerView.Adapter<HotelFacilityAdapter.ViewHolder>  {
    Activity activity;
    Context context;
    boolean isPolicy;
    private ArrayList<HotelProprtiesModels> hotelProprtiesModels = new ArrayList<>();


    public HotelFacilityAdapter(ArrayList<HotelProprtiesModels> hotelProprtiesModels, Activity activity) {
        this.hotelProprtiesModels = hotelProprtiesModels;
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();

        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.select_hotel_item_pro, parent, false));
    }

    @Override
    public void onBindViewHolder(final HotelFacilityAdapter.ViewHolder holder, final int position) {
        if (isPolicy){
            holder.tvTitle.setText(hotelProprtiesModels.get(position).getPropertyTitle());

            holder.tvImage.setText(hotelProprtiesModels.get(position).getPropertyDescription());
        }else{

                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.Facility));
                    holder.tvImage.setTypeface(t);
                    String icon = hotelProprtiesModels.get(position).getImage().substring(1);
                    icon = "&#" + icon + ";";
                    String valHexStr = icon.replace("&#x", "").replace(";", "");
                    long valLong = Long.parseLong(valHexStr, 16);

                    holder.tvImage.setText((char) valLong + "");

                } catch (Exception e) {
                }
                holder.tvTitle.setText(hotelProprtiesModels.get(position).getPropertyTitle());


        }

    }

    @Override
    public int getItemCount() {
        return hotelProprtiesModels.size();
    }



    public long getItemId(int position) {
        return position;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvImage;
        public ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvImage = v.findViewById(R.id.tvImage);

        }
    }
}

