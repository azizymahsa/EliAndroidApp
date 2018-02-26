package com.eligasht.reservation.views.ui.dialog.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eligasht.R;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class AlertDialogPolicy implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView tvAlert,tvTitle,tvRoomName;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnCancel;
    AVLoadingIndicatorView avi;
    String text;
    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;


    public AlertDialogPolicy(final Context activity) {
        this.activity = activity;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_policy, null);
        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        avi = (AVLoadingIndicatorView) dialogView.findViewById(R.id.avi);
        tvAlert = (TextView) dialogView.findViewById(R.id.tvAlert);
        tvTitle = (TextView) dialogView.findViewById(R.id.tvTitle);
        tvRoomName= (TextView) dialogView.findViewById(R.id.tvRoomName);
       /* Typeface typeface=Typeface.createFromAsset(activity.getAssets(),"fonts/iran_sans_normal.ttf");
        tvAlert.setTextSize(2,12);
        tvAlert.setLineSpacing(30);
        tvAlert.setTypeFace(typeface);
        tvAlert.setTextColor(ContextCompat.getColor(activity,R.color.text_color_4d));*/

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
    public void setTitle(String text){


        tvTitle.setText(text);
    }
    public void setRoomName(String text){


        tvRoomName.setText(text);
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

