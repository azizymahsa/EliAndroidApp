package com.reserv.myapplicationeli.views.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class PSpecialRoomRowHolder extends  RecyclerView.ViewHolder{


    public TextView adaultPrice;
    public TextView childPrice;
    public TextView infantPrice;
    public TextView totalPrice;
    public TextView total_price;
    public TextView txt_hr_room_list;

    public PSpecialRoomRowHolder(View itemView) {
        super(itemView);

        adaultPrice = itemView.findViewById(R.id.price_adault);
        childPrice = itemView.findViewById(R.id.price_child);
        infantPrice = itemView.findViewById(R.id.price_infant);
        totalPrice = itemView.findViewById(R.id.total_price);
        total_price = itemView.findViewById(R.id.total_price);
        txt_hr_room_list = itemView.findViewById(R.id.txt_hr_room_list);
    }
}
