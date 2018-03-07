package com.eligasht.reservation.views.ui.dialog.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.ui.SingletonContext;

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
        btnMobileData = dialogView.findViewById(R.id.btnMobileData);
        btnWifi = dialogView.findViewById(R.id.btnWifi);
        tvAlert = dialogView.findViewById(R.id.tvAlert);

        btnMobileData.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnWifi.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
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
