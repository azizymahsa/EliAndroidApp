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

    public TextView country_arive_go;
    public TextView country_depart_go;
    public TextView country_arive_back;
    public TextView country_depart_back;

    public TextView time_depart_go;
    public TextView time_arive_go;

    public TextView time_depart_back;
    public TextView time_arive_back;

    public TextView txt_count;
    public TextView txt_airline_go;
    public TextView txt_airline_back;

    public Button btn_package_booking;
    // public TextView date_depart;
    // public TextView date_arrive;


    public PRowXferRowHolder(View itemView) {
        super(itemView);

        this.intro_view_pager = itemView.findViewById(R.id.intro_view_pager);
        this.rcl_price = itemView.findViewById(R.id.rcl_price);
        this.rcl_hotels = itemView.findViewById(R.id.rcl_hotels);
        this.country_arive_go = itemView.findViewById(R.id.country_ariv_go);
        this.time_depart_go = itemView.findViewById(R.id.time_deprt_go);
        this.country_depart_go = itemView.findViewById(R.id.country_deprt_go);
        this.time_depart_back = itemView.findViewById(R.id.time_drt_back);
        this.txt_airline_go = itemView.findViewById(R.id.txt_airline_go);
        this.txt_airline_back = itemView.findViewById(R.id.txt_airline_back);
        this.btn_package_booking = itemView.findViewById(R.id.packageBooking);
        this.country_arive_back = itemView.findViewById(R.id.country_arive_back);
        this.country_depart_back = itemView.findViewById(R.id.country_depart_back);
        this.time_arive_go = itemView.findViewById(R.id.time_ariv_go);
        this.time_arive_back = itemView.findViewById(R.id.time_arrive_back);

        //this.date_depart = itemView.findViewById(R.id.date_go);
        //this.date_arrive = itemView.findViewById(R.id.date_back);

        rcl_hotels.hideLoading();
        rcl_price.hideLoading();
        rcl_price.setLayoutManager(new LinearLayoutManager(GlobalApplication.applicationContext));
        rcl_hotels.setLayoutManager(new LinearLayoutManager(GlobalApplication.applicationContext));
    }


}
