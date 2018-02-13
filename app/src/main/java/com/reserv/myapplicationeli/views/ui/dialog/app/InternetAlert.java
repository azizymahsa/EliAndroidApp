package com.reserv.myapplicationeli.views.ui.dialog.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.views.activities.main.MainActivity;
import com.reserv.myapplicationeli.views.ui.SplashFragment;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/18/2018.
 */

public class InternetAlert implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView tvAlert;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnMobileData, btnWifi;
    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;
    SmoothCheckBox bestSeler, bestOff, Remove, star2, star3, star4, star5, star1, hotel, boutique, apartment, resort;


    public InternetAlert(final Activity activity) {
        this.activity = activity;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_net, null);
        builder.setView(dialogView);
        btnMobileData = (FancyButton) dialogView.findViewById(R.id.btnMobileData);
        btnWifi = (FancyButton) dialogView.findViewById(R.id.btnWifi);
        tvAlert = (TextView) dialogView.findViewById(R.id.tvAlert);

        btnMobileData.setCustomTextFont("iran_sans_normal.ttf");
        btnWifi.setCustomTextFont("iran_sans_normal.ttf");
        btnMobileData.setOnClickListener(this);
        btnWifi.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(false);
    }
    public void isCancel(){
        try{
            dialog.cancel();

        }catch (Exception e){}
    }
    public void isShow(){
        try{

            if (!dialog.isShowing()&&activity!=null){
                dialog.show();

            }
        }catch (Exception e){}

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMobileData:
                if(Utility.isNetworkAvailable(activity)) {
                    dialog.cancel();
                }else{
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                    activity.startActivity(intent);
                }


                break;
            case R.id.btnWifi:
                if(Utility.isNetworkAvailable(activity)) {
                    dialog.cancel();

                }else{
                    activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                }

                break;

        }
    }
}
