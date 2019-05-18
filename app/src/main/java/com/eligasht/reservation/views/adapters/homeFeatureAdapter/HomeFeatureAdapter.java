package com.eligasht.reservation.views.adapters.homeFeatureAdapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eligasht.R;


import java.util.ArrayList;

public class HomeFeatureAdapter   extends RecyclerView.Adapter<HomeFeatureAdapter.ViewHolder>  {
    Activity activity;
    Context context;
    boolean isPolicy;
    private ArrayList<HomeFeatureModels> hotelProprtiesModels = new ArrayList<>();


    public HomeFeatureAdapter(ArrayList<HomeFeatureModels> hotelProprtiesModels, Activity activity) {
        this.hotelProprtiesModels = hotelProprtiesModels;
        this.activity = activity;
    }

 
    @Override
    public HomeFeatureAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();

        return new HomeFeatureAdapter.ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.select_home_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final HomeFeatureAdapter.ViewHolder holder, final int position) {
        if (isPolicy){

            holder.tvTitle.setText(hotelProprtiesModels.get(position).getPropertyTitle());

            holder.tvImage.setImageResource(R.drawable.ic_test_svg);//hotelProprtiesModels.get(position).getPropertyDescription());
        }else{

            try {
                Typeface t = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.Facility));
               // holder.tvImage.setTypeface(t);
                String icon = hotelProprtiesModels.get(position).getImage().substring(1);
                icon = "&#" + icon + ";";
                String valHexStr = icon.replace("&#x", "").replace(";", "");
                long valLong = Long.parseLong(valHexStr, 16);

               // holder.tvImage.setText((char) valLong + "");

            } catch (Exception e) {
            }
            holder.tvTitle.setText(hotelProprtiesModels.get(position).getPropertyTitle());
            holder.tvImage.setImageResource(R.drawable.ic_test_svg);

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
        public TextView tvTitle;
        public ImageView tvImage;
        public ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvImage = v.findViewById(R.id.tvImage);

        }
    }
}

