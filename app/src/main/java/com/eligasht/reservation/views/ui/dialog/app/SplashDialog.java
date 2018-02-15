package com.eligasht.reservation.views.ui.dialog.app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eligasht.reservation.R;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 2/5/2018.
 */

public class SplashDialog implements View.OnClickListener {
    android.app.AlertDialog dialog;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnOk;
    TextView tvAlert;
    TryDialogListener filterHotelDialogListener;



    public SplashDialog(final Activity activity,TryDialogListener filterHotelDialogListener) {
        this.activity = activity;
        this.filterHotelDialogListener = filterHotelDialogListener;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_splash, null);
        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        tvAlert = (TextView) dialogView.findViewById(R.id.tvAlert);

        btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(false);
    }
public void seeText(String message){
    tvAlert.setText(message);
}
public void showAlert(){

    dialog.show();
}
    public void setBtnText(){
        btnOk.setText("باشه!");

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                dialog.cancel();
                try {
                    filterHotelDialogListener.onReturnValue();

                }catch (Exception e){}

                break;

        }
    }

    public interface TryDialogListener {
        public void onReturnValue();
    }
}

