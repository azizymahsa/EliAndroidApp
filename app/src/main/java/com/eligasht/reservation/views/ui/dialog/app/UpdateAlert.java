package com.eligasht.reservation.views.ui.dialog.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.eligasht.R;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.activities.main.MainActivity;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class UpdateAlert  {
    android.app.AlertDialog dialog;
    TextView tvAlert;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnOk, btnExit;
    String packageName;
    boolean force;

    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;
    SmoothCheckBox bestSeler, bestOff, Remove, star2, star3, star4, star5, star1, hotel, boutique, apartment, resort;


    public UpdateAlert(final Activity activity, final String packageName) {
        this.activity = activity;
        this.packageName = packageName;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_update, null);
        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOkLogout);
        btnExit = (FancyButton) dialogView.findViewById(R.id.btnExitLogout);
        tvAlert = (TextView) dialogView.findViewById(R.id.tvAlert);


        dialog = builder.create();
        dialog.setCancelable(false);


        btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnExit.setCustomTextFont("fonts/iran_sans_normal.ttf");
/*        btnOk.setOnClickListener(this);
        btnExit.setOnClickListener(this);*/
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                if (!force){
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    activity.finish();
                }
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("bazaar://details?id=" + packageName));
                    intent.setPackage("com.farsitel.bazaar");
                    activity.startActivity(intent);
                } catch (Exception e) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
                        activity.startActivity(intent);
                    }catch (Exception e2){}


                }
            }
        });
    }
    public void show(){
        try {
            dialog.show();

        }catch (Exception e){}
    }

    public void cancel(){
        try {
            dialog.cancel();

        }catch (Exception e){}
    }
    public void isForce(boolean force){

        if (force){

            dialog.setCancelable(false);
            btnExit.setVisibility(View.GONE);
            tvAlert.setText("نسخه نرم افزار شما پشتیبانی نمی شود، لطفا نرم افزار خود را بروز رسانی کنید.");
            btnOk.setText("بروز رسانی");


        }
    }


}

