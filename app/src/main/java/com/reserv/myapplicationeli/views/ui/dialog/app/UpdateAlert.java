package com.reserv.myapplicationeli.views.ui.dialog.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.eligasht.reservation.R;
import com.eligasht.reservation.tools.Prefs;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class UpdateAlert implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView tvAlert;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnOk, btnExit;
    String packageName;
    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;
    SmoothCheckBox bestSeler, bestOff, Remove, star2, star3, star4, star5, star1, hotel, boutique, apartment, resort;


    public UpdateAlert(final Activity activity,String  packageName) {
        this.activity = activity;
        this.packageName = packageName;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
    //  dialogView = inflater.inflate(R.layout.alert_dialog_update, null);
        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        btnExit = (FancyButton) dialogView.findViewById(R.id.btnExit);
        tvAlert = (TextView) dialogView.findViewById(R.id.tvAlert);

        btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnExit.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                try {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("bazaar://details?id=" + packageName));
                    intent.setPackage("com.farsitel.bazaar");
                    activity.startActivity(intent);
                } catch (Exception e) {
                }


                break;
            case R.id.btnExit:
                Prefs.remove("WEB_USER");
                dialog.cancel();
                break;

        }
    }
}

