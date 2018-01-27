package com.reserv.myapplicationeli.views.viewholders.filter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class HotelTypeFilterRowHolder extends RecyclerView.ViewHolder {


     public TextView txt_hotel_type_filter;
     public ViewGroup layout_hotel_type_filter;
     public SmoothCheckBox chk_hotel_type_filter;

    public HotelTypeFilterRowHolder(View itemView) {
        super(itemView);

        this.layout_hotel_type_filter = itemView.findViewById(R.id.layout_hotel_type_filter);
        this.txt_hotel_type_filter = itemView.findViewById(R.id.txt_hotel_type_filter);
        this.chk_hotel_type_filter = itemView.findViewById(R.id.chk_hotel_type_filter);

    }

}
