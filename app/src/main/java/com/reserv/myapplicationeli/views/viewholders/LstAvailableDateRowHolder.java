package com.reserv.myapplicationeli.views.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class LstAvailableDateRowHolder extends RecyclerView.ViewHolder {


    public TextView txt_depart_date;
    public ImageView img_airLine;
    public View view_selected;
    public ViewGroup root;

    public LstAvailableDateRowHolder(View itemView) {
        super(itemView);

        this.txt_depart_date = itemView.findViewById(R.id.txt_depart_date);
        this.img_airLine = itemView.findViewById(R.id.img_airLine);
        this.view_selected = itemView.findViewById(R.id.view_selected);
        this.root = itemView.findViewById(R.id.root);
    }

}
