package com.eligasht.reservation.views.ui.dialog.hotel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import com.eligasht.R;
import com.eligasht.reservation.tools.JustifiedTextView;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class AlertDialogPassenger implements View.OnClickListener {
    android.app.AlertDialog dialog;
    JustifiedTextView tvAlert;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnCancel;
    AVLoadingIndicatorView avi;
    String text;
    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;


    public AlertDialogPassenger(final Context activity) {
        this.activity = activity;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_passenger, null);
        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        avi = (AVLoadingIndicatorView) dialogView.findViewById(R.id.avi);
        tvAlert = (JustifiedTextView) dialogView.findViewById(R.id.tvAlert);

        Typeface typeface=Typeface.createFromAsset(activity.getAssets(),"fonts/iran_sans_bold.ttf");
        tvAlert.setTextSize(2,12);
        tvAlert.setLineSpacing(25);
        tvAlert.setTypeFace(typeface);
        tvAlert.setTextColor(Color.parseColor("#2e3192"));

       btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }

    public void setText(String text) {
        this.text = text;
        tvAlert.setText(text);
        avi.setVisibility(View.GONE);

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

