package com.eligasht.reservation.views.ui.dialog.app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.eligasht.reservation.views.ui.SingletonContext;

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
  Boolean restart;


  public SplashDialog(final Activity activity, TryDialogListener filterHotelDialogListener) {
    this.activity = activity;
    this.filterHotelDialogListener = filterHotelDialogListener;
    builder = new android.app.AlertDialog.Builder(activity);
    inflater = LayoutInflater.from(activity);
    dialogView = inflater.inflate(R.layout.alert_dialog_splash, null);
    builder.setView(dialogView);
    btnOk = dialogView.findViewById(R.id.btnOk);
    tvAlert = dialogView.findViewById(R.id.tvAlert);
    btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources()
        .getString(R.string.iran_sans_normal_ttf));
    btnOk.setOnClickListener(this);
    dialog = builder.create();
    dialog.setCancelable(false);
    restart = false;
  }

  public SplashDialog(final Activity activity, TryDialogListener filterHotelDialogListener,
      Boolean isRestartApp) {
    this.activity = activity;
    this.filterHotelDialogListener = filterHotelDialogListener;
    builder = new android.app.AlertDialog.Builder(activity);
    inflater = LayoutInflater.from(activity);
    dialogView = inflater.inflate(R.layout.alert_dialog_splash, null);
    builder.setView(dialogView);
    btnOk = dialogView.findViewById(R.id.btnOk);
    tvAlert = dialogView.findViewById(R.id.tvAlert);

    btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources()
        .getString(R.string.iran_sans_normal_ttf));
    btnOk.setOnClickListener(this);
    dialog = builder.create();
    dialog.setCancelable(false);
    restart = true;
  }

  public void seeText(String message) {
    tvAlert.setText(message);
  }

  public void showAlert() {
try {
  dialog.show();
}
catch (Exception e)
{
  e.printStackTrace();
}

  }

  public void setBtnText() {
    btnOk.setText("باشه!");

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btnOk:
        dialog.cancel();
        try {
          if (!restart) {
            filterHotelDialogListener.onReturnValue();

          } else {
            filterHotelDialogListener.returnRestartAppValue();
          }

        } catch (Exception e) {
        }

        break;

    }
  }

  public interface TryDialogListener {

    void onReturnValue();

    void returnRestartAppValue();
  }

}

