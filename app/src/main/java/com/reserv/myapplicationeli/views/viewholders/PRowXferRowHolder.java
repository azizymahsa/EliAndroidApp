package com.reserv.myapplicationeli.views.viewholders;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.GlobalApplication;
import com.reserv.myapplicationeli.views.components.SimpleRecycleView;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class PRowXferRowHolder extends RecyclerView.ViewHolder {

   public ViewPager intro_view_pager;
   public SimpleRecycleView rcl_price;
   public SimpleRecycleView rcl_hotels;
    public TextView txt_return_air;
    public TextView txt_return_time;
    public TextView txt_depart_air;
    public TextView txt_depart_time;
    public TextView txt_count;
    public TextView txt_economi;
    public TextView txt_airline;
    public ImageView img_airLine;
    public Button btn_package_booking;
   // public TextView date_depart;
   // public TextView date_arrive;


    public PRowXferRowHolder(View itemView) {
        super(itemView);

        this.intro_view_pager = itemView.findViewById(R.id.intro_view_pager);
        this.rcl_price = itemView.findViewById(R.id.rcl_price);
        this.rcl_hotels = itemView.findViewById(R.id.rcl_hotels);
        this.txt_return_air = itemView.findViewById(R.id.txt_return_air);
        this.txt_return_time = itemView.findViewById(R.id.txt_return_time);
        this.txt_depart_air = itemView.findViewById(R.id.txt_depart_air);
        this.txt_depart_time = itemView.findViewById(R.id.txt_depart_time);
        this.txt_economi = itemView.findViewById(R.id.txt_economi);
        this.txt_airline = itemView.findViewById(R.id.txt_airline);
        this.img_airLine = itemView.findViewById(R.id.img_airLine);
        this.btn_package_booking = itemView.findViewById(R.id.packageBooking);
        //this.date_depart = itemView.findViewById(R.id.date_go);
        //this.date_arrive = itemView.findViewById(R.id.date_back);

        rcl_hotels.hideLoading();
        rcl_price.hideLoading();
        rcl_price.setLayoutManager(new LinearLayoutManager(GlobalApplication.applicationContext));
        rcl_hotels.setLayoutManager(new LinearLayoutManager(GlobalApplication.applicationContext));
    }


}
