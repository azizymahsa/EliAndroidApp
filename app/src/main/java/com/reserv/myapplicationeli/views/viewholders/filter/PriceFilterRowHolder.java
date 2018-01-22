package com.reserv.myapplicationeli.views.viewholders.filter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class PriceFilterRowHolder extends RecyclerView.ViewHolder {


     public TextView txt_price_filter;
     public SmoothCheckBox chk_price_filter;
     public ViewGroup layout_price_filter;

    public PriceFilterRowHolder(View itemView) {
        super(itemView);

       this.layout_price_filter = itemView.findViewById(R.id.layout_price_filter);
       this.txt_price_filter = itemView.findViewById(R.id.txt_price_filter);
       this.chk_price_filter = itemView.findViewById(R.id.chk_price_filter);

    }

}
