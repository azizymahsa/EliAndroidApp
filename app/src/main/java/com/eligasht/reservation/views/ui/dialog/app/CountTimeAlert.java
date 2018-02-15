package com.eligasht.reservation.views.ui.dialog.app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eligasht.reservation.R;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/20/2018.
 */

public class CountTimeAlert implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView tvAlert;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnOk, btnCancel;
    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;
    TimerDialogListener timerDialogListener;

    public CountTimeAlert(final Activity activity,TimerDialogListener timerDialogListener) {
        this.activity = activity;
        this.timerDialogListener = timerDialogListener;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_timer, null);
        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        tvAlert = (TextView) dialogView.findViewById(R.id.tvAlert);

        btnOk.setCustomTextFont("iran_sans_normal.ttf");
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                timerDialogListener.onReturnValue(1);

                dialog.cancel();

                break;

        }
    }


    public interface TimerDialogListener{
        public void onReturnValue(int type);
    }

}
