package com.eligasht.reservation.views.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eligasht.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class LstProwHotelRowHolder extends RecyclerView.ViewHolder {

    public ImageView ivBigImage;
    public TextView txt_hotel_name;
    public TextView txt_date;
    public TextView txt_location_full_name;
    public ImageView rating;
    public AVLoadingIndicatorView avi;
    public TextView txt_city_name;
    public TextView lableHotelTilte;

    public LstProwHotelRowHolder(View view) {
        super(view);
        this.ivBigImage = view.findViewById(R.id.ivBigImage);
        this.avi = view.findViewById(R.id.avi);
        this.txt_hotel_name = view.findViewById(R.id.txt_hotel_name);
        this.txt_date = view.findViewById(R.id.txt_date);
        this.txt_location_full_name = view.findViewById(R.id.txt_location_full_name);
        this.rating = view.findViewById(R.id.rating);
        this.txt_city_name = view.findViewById(R.id.txt_city);
        this.lableHotelTilte = view.findViewById(R.id.txt_lable_hotel);
    }
}
