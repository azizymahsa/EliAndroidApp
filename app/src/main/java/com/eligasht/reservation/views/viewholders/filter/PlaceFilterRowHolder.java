package com.eligasht.reservation.views.viewholders.filter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eligasht.R;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class PlaceFilterRowHolder extends RecyclerView.ViewHolder {


     public TextView txt_place_filter;
     public ViewGroup layout_place_filter;
     public SmoothCheckBox chk_place_filter;

    public PlaceFilterRowHolder(View itemView) {
        super(itemView);

        this.layout_place_filter = itemView.findViewById(R.id.layout_place_filter);
        this.txt_place_filter = itemView.findViewById(R.id.txt_place_filter);
        this.chk_place_filter = itemView.findViewById(R.id.chk_place_filter);

    }

}
