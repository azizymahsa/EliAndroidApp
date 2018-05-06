package com.eligasht.reservation.views.ui.dialog.hotel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.eligasht.reservation.tools.JustifiedTextView;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.wang.avi.AVLoadingIndicatorView;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/21/2018.
 */
public class AlertDialogPassenger implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView tvAlert, tvTitle;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnOk, btnCancel;
    AVLoadingIndicatorView avi;
    String text;
    String title;
    LottieAnimationView animation_view;
    boolean isFinish;
    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;

    public AlertDialogPassenger(final Activity activity, boolean warning, boolean isFinish) {
        this.activity = activity;
        this.isFinish = isFinish;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_passenger, null);
        builder.setView(dialogView);
        btnOk = dialogView.findViewById(R.id.btnOk);
        avi = dialogView.findViewById(R.id.avi);
        tvAlert = dialogView.findViewById(R.id.tvAlert);
        tvTitle = dialogView.findViewById(R.id.tvTitle);
        animation_view = dialogView.findViewById(R.id.animation_view);
        if (warning)
            animation_view.setAnimation("lottie/warning.json");
        else
            animation_view.setAnimation("lottie/paper.json");

 /*       Typeface typeface = Typeface.createFromAsset(activity.getAssets(), dialogView.getContext().getResources().getString(R.string.iran_sans_bold_ttf));
        tvAlert.setTextSize(2, 12);
        tvAlert.setLineSpacing(25);
        tvAlert.setTypeFace(typeface);*/
        tvAlert.setTextColor(Color.parseColor("#4d4d4d"));
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);
        try {
            dialog.show();
        } catch (Exception e) {
        }
    }

    public void setText(String text, String title) {
        this.text = text;
        this.title = title;
        tvAlert.setText(text);
        tvTitle.setText(title);
        avi.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                if (isFinish)
                    activity.finish();
                else
                dialog.cancel();
                break;
        }
    }
}

