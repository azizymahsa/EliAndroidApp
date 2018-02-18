package com.eligasht.reservation.views.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eligasht.R;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class PassengerRowHolder extends RecyclerView.ViewHolder {


    public TextView txt_passenger_title;
    public TextView txt_birthday;
    public ViewGroup layout_birthday;

    public PassengerRowHolder(View view) {
        super(view);
        this.txt_passenger_title = view.findViewById(R.id.txt_passenger_title);
        this.txt_birthday = view.findViewById(R.id.txt_birthday);
        this.layout_birthday = view.findViewById(R.id.layout_birthday);
    }
}
