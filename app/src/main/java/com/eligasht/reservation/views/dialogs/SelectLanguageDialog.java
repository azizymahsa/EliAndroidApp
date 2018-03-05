package com.eligasht.reservation.views.dialogs;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.components.smoothcheckbox.SmoothCheckBox;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Ahmad.nemati on 3/3/2018.
 */

public class SelectLanguageDialog implements View.OnClickListener {
    // FilterHotelDialog.FilterHotelDialogListener filterHotelDialogListener;
    public LinearLayout england, iran, turkey, arabia;
    android.app.AlertDialog dialog;
    TextView tvAlert;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Activity activity;
    FancyButton btnMobileData, btnWifi, accept;
    private LanguageClick listener;
    String lang = "";

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
        england = dialogView.findViewById(R.id.england);
        iran = dialogView.findViewById(R.id.iran);
        turkey = dialogView.findViewById(R.id.turkey);
        arabia = dialogView.findViewById(R.id.arabia);
        btnMobileData.setCustomTextFont("iran_sans_normal.ttf");
        accept.setCustomTextFont("iran_sans_normal.ttf");
        btnWifi.setCustomTextFont("iran_sans_normal.ttf");
        btnMobileData.setOnClickListener(this);
        btnWifi.setOnClickListener(this);
        dialogView.findViewById(R.id.txt_english).setOnClickListener(this);
        dialogView.findViewById(R.id.txt_persian).setOnClickListener(this);


        if (Prefs.getString("lang", "fa").equals("fa")) {
            england.setBackgroundResource(R.color.white);
            iran.setBackgroundResource(R.drawable.stroke_pruple);
            turkey.setBackgroundResource(R.color.white);
            lang = "fa";
        } else if (Prefs.getString("lang", "fa").equals("en")) {
            england.setBackgroundResource(R.drawable.stroke_pruple);
            iran.setBackgroundResource(R.color.white);
            turkey.setBackgroundResource(R.color.white);
            lang = "en";
        } else if (Prefs.getString("lang", "fa").equals("tr")) {
            england.setBackgroundResource(R.color.white);
            iran.setBackgroundResource(R.color.white);
            turkey.setBackgroundResource(R.drawable.stroke_pruple);
            lang = "tr";

        }

        england.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                england.setBackgroundResource(R.drawable.stroke_pruple);
                iran.setBackgroundResource(R.color.white);
                turkey.setBackgroundResource(R.color.white);
                lang = "en";
                YoYo.with(Techniques.Pulse)
                        .duration(200)
                        .playOn(england);
            }
        });

        iran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                england.setBackgroundResource(R.color.white);
                turkey.setBackgroundResource(R.color.white);
                iran.setBackgroundResource(R.drawable.stroke_pruple);
                lang = "fa";
                YoYo.with(Techniques.Pulse)
                        .duration(200)
                        .playOn(iran);
            }
        });

        turkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                england.setBackgroundResource(R.color.white);
                iran.setBackgroundResource(R.color.white);
                turkey.setBackgroundResource(R.drawable.stroke_pruple);
                lang = "tr";
                YoYo.with(Techniques.Pulse)
                        .duration(200)
                        .playOn(turkey);

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
                if (lang.equals("fa")) {
                    listener.onLanguageCLick("fa");
                } else if (lang.equals("en")) {
                    listener.onLanguageCLick("en");
                } else if (lang.equals("tr")) {
                    listener.onLanguageCLick("tr");
                }
                dialog.dismiss();
                break;
            case R.id.txt_persian:
                england.setBackgroundResource(R.color.white);
                turkey.setBackgroundResource(R.color.white);
                iran.setBackgroundResource(R.drawable.stroke_pruple);
                YoYo.with(Techniques.Pulse)
                        .duration(200)
                        .playOn(iran);
                break;
            case R.id.txt_english:
                england.setBackgroundResource(R.drawable.stroke_pruple);
                iran.setBackgroundResource(R.color.white);
                turkey.setBackgroundResource(R.color.white);
                YoYo.with(Techniques.Pulse)
                        .duration(200)
                        .playOn(england);
                break;
            case R.id.txt_turkish:
                turkey.setBackgroundResource(R.drawable.stroke_pruple);
                iran.setBackgroundResource(R.color.white);
                england.setBackgroundResource(R.color.white);
                YoYo.with(Techniques.Pulse)
                        .duration(200)
                        .playOn(turkey);
                break;

        }
    }

    public interface LanguageClick {
        void onLanguageCLick(String lang);
    }


}

