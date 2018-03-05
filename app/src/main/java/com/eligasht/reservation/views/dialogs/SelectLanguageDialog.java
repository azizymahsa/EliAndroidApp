package com.eligasht.reservation.views.dialogs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.components.smoothcheckbox.SmoothCheckBox;
import com.eligasht.reservation.views.ui.SingletonContext;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Ahmad.nemati on 3/3/2018.
 */

public class SelectLanguageDialog implements View.OnClickListener {
    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;
    public SmoothCheckBox chk_persian;
    public SmoothCheckBox chk_english;
    android.app.AlertDialog dialog;
    TextView tvAlert;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnMobileData, btnWifi, accept;
    private LanguageClick listener;

    public SelectLanguageDialog(final Activity activity, LanguageClick listener) {
        this.activity = activity;
        this.listener = listener;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_select_language, null);
        builder.setView(dialogView);
        btnMobileData = dialogView.findViewById(R.id.btnMobileData);
        btnWifi = dialogView.findViewById(R.id.btnWifi);
        tvAlert = dialogView.findViewById(R.id.tvAlert);
        accept = dialogView.findViewById(R.id.accept);
        accept.setOnClickListener(this);
        chk_persian = dialogView.findViewById(R.id.chB_persian);
        chk_english = dialogView.findViewById(R.id.chB_english);
        btnMobileData.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        accept.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnWifi.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnMobileData.setOnClickListener(this);
        btnWifi.setOnClickListener(this);
        dialogView.findViewById(R.id.txt_english).setOnClickListener(this);
        dialogView.findViewById(R.id.txt_persian).setOnClickListener(this);

        if (Prefs.getString("lang", "fa").equals("fa"))
            chk_persian.setChecked(true, true);
        else
            chk_english.setChecked(true, true);

        chk_persian.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if (isChecked)
                    chk_english.setChecked(false, true);
            }
        });

        chk_english.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if (isChecked)
                    chk_persian.setChecked(false, true);
            }
        });
        dialog = builder.create();
        dialog.setCancelable(true);
    }

    public void isCancel() {
        try {
            dialog.cancel();

        } catch (Exception e) {
        }
    }

    public void show() {
        try {

            if (!dialog.isShowing() && activity != null) {
                dialog.show();

            }
        } catch (Exception e) {
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accept:
                if (chk_persian.isChecked())
                    listener.onLanguageCLick("fa");
                else
                    listener.onLanguageCLick("en");
                dialog.dismiss();
                break;
            case R.id.txt_persian:
                chk_persian.setChecked(true, true);
                break;
            case R.id.txt_english:
                chk_english.setChecked(true, true);
                break;

        }
    }

    public interface LanguageClick {
        void onLanguageCLick(String lang);
    }


}

