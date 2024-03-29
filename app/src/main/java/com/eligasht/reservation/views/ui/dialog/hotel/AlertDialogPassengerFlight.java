package com.eligasht.reservation.views.ui.dialog.hotel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.tools.JustifiedTextView;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class AlertDialogPassengerFlight implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView tvAlert,tvTitle;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnOk, btnCancel;
    AVLoadingIndicatorView avi;
    String text;
    String title;
    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;


    public AlertDialogPassengerFlight(final Activity activity) {
        this.activity = activity;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_passenger, null);
        builder.setView(dialogView);
        btnOk = dialogView.findViewById(R.id.btnOk);
        avi = dialogView.findViewById(R.id.avi);
        tvAlert = dialogView.findViewById(R.id.tvAlert);
        tvTitle = dialogView.findViewById(R.id.tvTitle);
      /*  Typeface typeface = Typeface.createFromAsset(activity.getAssets(), dialogView.getContext().getResources().getString(R.string.iran_sans_bold_ttf));
        tvAlert.setTextSize(2,12);
        tvAlert.setLineSpacing(25);
        tvAlert.setTypeFace(typeface);*/
        tvAlert.setTextColor(Color.parseColor("#2e3192"));

      //  btnOk.setCustomTextFont("fonts/iran_sans_bold.ttf");
       // btnOk.setText(activity.getString(R.string.icon_close));
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));

        try{
            dialog.show();
        }catch (Exception e){

            Toast.makeText(activity, activity.getString(R.string.ErrorServer), Toast.LENGTH_SHORT).show();
        }

    }

    public void setText(String text,String title) {
        this.text = text;
        tvAlert.setText(text);
        tvTitle.setText(title);
        avi.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                activity.finish();
                dialog.cancel();


                break;

        }
    }


}

