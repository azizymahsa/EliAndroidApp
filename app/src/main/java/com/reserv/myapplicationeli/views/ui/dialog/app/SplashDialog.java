package com.reserv.myapplicationeli.views.ui.dialog.app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.hotel.FilterPriceModel;
import com.reserv.myapplicationeli.models.hotel.adapter.FilterModel;
import com.reserv.myapplicationeli.tools.Prefs;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.FilterHotelTypeModel;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 2/5/2018.
 */

public class SplashDialog implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView tvAlert;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnOk;
    TryDialogListener filterHotelDialogListener;


    public SplashDialog(final Activity activity,TryDialogListener filterHotelDialogListener) {
        this.activity = activity;
        this.filterHotelDialogListener = filterHotelDialogListener;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_splash, null);
        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);

        btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                dialog.cancel();
                filterHotelDialogListener.onReturnValue();

                break;

        }
    }

    public interface TryDialogListener {
        public void onReturnValue();
    }
}

