package com.eligasht.reservation.views.ui.dialog.app;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
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
        LottieAnimationView lottieAnimationView = dialogView.findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("lottie/no_connection.json");
        lottieAnimationView.playAnimation();
    }

    public void isCancel() {
        try {
            dialog.cancel();
        } catch (Exception e) {
        }
    }

    public void isShow() {
        try {
            if (!dialog.isShowing() && activity != null) {
                dialog.show();
            }
        } catch (Exception e) {
        }
    }

    public android.app.AlertDialog alertDialog() {
        return dialog;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMobileData:
                if (Utility.isNetworkAvailable(activity)) {
                    dialog.cancel();
                } else {
                    try {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                        activity.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btnWifi:
                if (Utility.isNetworkAvailable(activity)) {
                    dialog.cancel();
                } else {
                    try {
                        activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
