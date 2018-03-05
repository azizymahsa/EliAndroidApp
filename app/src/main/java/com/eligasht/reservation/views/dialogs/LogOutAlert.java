package com.eligasht.reservation.views.dialogs;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.ui.SingletonContext;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/27/2018.
 */

public class LogOutAlert implements View.OnClickListener {
    AlertDialog dialog;
    TextView tvAlert;
    View dialogView;
    LayoutInflater inflater;
    AlertDialog.Builder builder;
    MainActivity activity;
    FancyButton btnOk, btnExit;
    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;
    SmoothCheckBox bestSeler, bestOff, Remove, star2, star3, star4, star5, star1, hotel, boutique, apartment, resort;


    public LogOutAlert(final MainActivity activity) {
        this.activity = activity;
        builder = new AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_logout, null);
        builder.setView(dialogView);
        btnOk = dialogView.findViewById(R.id.btnOkLogout);
        btnExit = dialogView.findViewById(R.id.btnExitLogout);
        tvAlert = dialogView.findViewById(R.id.tvAlert);

        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnExit.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOkLogout:
                Prefs.remove("WEB_USER");
               // activity.finish();
                activity.initUser();
                dialog.dismiss();
                break;
            case R.id.btnExitLogout:
                dialog.dismiss();
                break;

        }
    }
}

