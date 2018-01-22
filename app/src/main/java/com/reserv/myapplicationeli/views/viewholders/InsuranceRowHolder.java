package com.reserv.myapplicationeli.views.viewholders;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;

/**
 * Created by elham.bonyani on 1/15/2018.
 */

public class InsuranceRowHolder extends RecyclerView.ViewHolder {

    public TextView txtPlan;
    public TextView txtTitle;
    public TextView txtPrice;
    public Button btnBuy;
//    public Button btnDetail;
    public LinearLayout root_layout;
    public Button btn_insurance_booking;

    public InsuranceRowHolder(View itemView) {
        super(itemView);

        txtPlan = itemView.findViewById(R.id.txt_plan);
        txtPrice = itemView.findViewById(R.id.txt_price);
        txtTitle = itemView.findViewById(R.id.txt_title);
        btnBuy = itemView.findViewById(R.id.btn_buy);
//        btnDetail = itemView.findViewById(R.id.btn_details);
        root_layout = itemView.findViewById(R.id.layout_root);
        btn_insurance_booking = itemView.findViewById(R.id.btn_buy);

    }
}
