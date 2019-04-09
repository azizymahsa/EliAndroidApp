package com.eligasht.reservation.views.viewholders;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eligasht.R;

/**
 * Created by elham.bonyani on 1/15/2018.
 */

public class InsuranceRowHolder extends RecyclerView.ViewHolder {

    public TextView txtPlan;
    public TextView txtTitle;
    public TextView txtPrice;
    public TextView txtCurrencyCode;
    public TextView btn_buy;
    public Button btn_details;
//    public Button btnDetail;
    public LinearLayout root_layout;
    public Button btn_insurance_booking;
    public CardView list_inPlan;

    public InsuranceRowHolder(View itemView) {
        super(itemView);

        txtPlan = itemView.findViewById(R.id.txt_plan);
        txtPrice = itemView.findViewById(R.id.txt_price);
        txtCurrencyCode = itemView.findViewById(R.id.txtCurrencyCode);
        txtTitle = itemView.findViewById(R.id.txt_title);
        btn_buy = itemView.findViewById(R.id.btn_buy);
        btn_details = itemView.findViewById(R.id.btn_details);
//        btnDetail = itemView.findViewById(R.id.btn_details);
        root_layout = itemView.findViewById(R.id.layout_root);
        btn_insurance_booking = itemView.findViewById(R.id.btn_buy);
        list_inPlan = itemView.findViewById(R.id.crd_in_plan);

    }
}
