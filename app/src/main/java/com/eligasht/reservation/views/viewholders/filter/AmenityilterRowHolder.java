package com.eligasht.reservation.views.viewholders.filter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eligasht.reservation.R;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class AmenityilterRowHolder extends RecyclerView.ViewHolder {


     public TextView txt_amenity_filter;
     public SmoothCheckBox chk_amenity_filter;
     public ViewGroup layout_amenity_filter;

    public AmenityilterRowHolder(View itemView) {
        super(itemView);

       this.layout_amenity_filter = itemView.findViewById(R.id.layout_amenity_filter);
       this.txt_amenity_filter = itemView.findViewById(R.id.txt_amenity_filter);
       this.chk_amenity_filter = itemView.findViewById(R.id.chk_amenity_filter);

    }

}
