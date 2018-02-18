package com.eligasht.reservation.views.ui.dialog.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eligasht.R;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/8/2018.
 */

public class SocialFollowDialog implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView tvAlert;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnCancel;
   // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;
    SmoothCheckBox bestSeler, bestOff, Remove, star2, star3, star4, star5, star1, hotel, boutique, apartment, resort;


    public SocialFollowDialog(final Context activity, String text) {
        this.activity = activity;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_social_follow, null);
        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        tvAlert = (TextView) dialogView.findViewById(R.id.tvAlert);
        tvAlert.setText(text);

        btnOk.setCustomTextFont("iran_sans_normal.ttf");
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:

                dialog.cancel();


                break;

        }
    }


}

