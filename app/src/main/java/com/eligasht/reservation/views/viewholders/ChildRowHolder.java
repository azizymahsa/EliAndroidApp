package com.eligasht.reservation.views.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.eligasht.reservation.R;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class ChildRowHolder extends RecyclerView.ViewHolder {

    public TextView child_name;
    public Spinner spn_range;
    public LinearLayout layout_child;

    public ChildRowHolder(View itemView) {
        super(itemView);
        this.child_name = itemView.findViewById(R.id.txt_child);
        this.spn_range = itemView.findViewById(R.id.spn_range);
        this.layout_child = itemView.findViewById(R.id.layout_children);
    }
}
