package com.eligasht.reservation.views.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.adapters.SpinnerCustomAdapter;
import com.eligasht.reservation.views.dialogs.SelectLanguageDialog;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SplashActivity;

/**
 * Created by Ahmad.nemati on 3/3/2018.
 */

public class SettingsActivity extends BaseActivity implements View.OnClickListener, SelectLanguageDialog.LanguageClick, AdapterView.OnItemSelectedListener {
    String[] countryNames = {"ایران (IR)", "(UK)England", "(TR) Türkiye"};
    int flags[] = {R.drawable.iran, R.drawable.united_kingdom, R.drawable.turkey};
    String[] curencyNames = {"IRR(iran)"};
    String[] officeNames = {"Eligasht-IR", "Eligasht-UK", "Eligasht-TK"};
    Spinner languageSpinner, curencySpinner, officeSpinner;
    Button tvConfirm;
    String lang;
    boolean isChange=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        InitUi.Toolbar(this, false, R.color.toolbar_color, getResources().getString(R.string.settings));
        tvConfirm = findViewById(R.id.tvConfirm);

        curencySpinner = findViewById(R.id.curencySpinner);
        languageSpinner = findViewById(R.id.languageSpinner);
        officeSpinner = findViewById(R.id.officeSpinner);
        languageSpinner.setOnItemSelectedListener(this);
        curencySpinner.setOnItemSelectedListener(this);
        officeSpinner.setOnItemSelectedListener(this);
        tvConfirm.setOnClickListener(this);


        languageSpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, countryNames, true));
        curencySpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, curencyNames, false));
        officeSpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, officeNames, false));

        if (Prefs.getString("lang", "fa").contains("fa")) {
            curencyNames = new String[]{"IRR(iran)"};
            curencySpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, curencyNames, false));
            languageSpinner.setSelection(0);

        } else if (Prefs.getString("lang", "fa").contains("en")) {

            curencyNames = new String[]{"GB"};
            curencySpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, curencyNames, false));

            languageSpinner.setSelection(1);


        } else if (Prefs.getString("lang", "fa").contains("tr")) {
            curencyNames = new String[]{"TRY", "EUR"};
            curencySpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, curencyNames, false));
            languageSpinner.setSelection(2);

        }
        tvConfirm.setEnabled(false);
        tvConfirm.setClickable(false);
        tvConfirm.setTextColor(ContextCompat.getColor(this,R.color.focusColor));
    }

    @Override
    public boolean needTerminate() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tvConfirm:
                if (Prefs.getString("lang", "fa").contains(lang)) {
                    tvConfirm.setEnabled(false);
                    tvConfirm.setClickable(false);
                    tvConfirm.setTextColor(ContextCompat.getColor(this,R.color.focusColor));
                    return;
                }
                Prefs.putString("lang", lang);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mStartActivity = new Intent(SettingsActivity.this, SplashActivity.class);
                        int mPendingIntentId = 123456;
                        PendingIntent mPendingIntent = PendingIntent.getActivity(SettingsActivity.this, mPendingIntentId, mStartActivity,
                                PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager mgr = (AlarmManager) SettingsActivity.this.getSystemService(Context.ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                        System.exit(0);
                    }
                }, 100);
                break;
        }
    }



    private String getTypeOfLanguage() {
        return Prefs.getString("lang", "fa");
    }

    @Override
    public void onLanguageCLick(String lang) {
        if (Prefs.getString("lang", "fa").contains(lang))
            return;
        Prefs.putString("lang", lang);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mStartActivity = new Intent(SettingsActivity.this, SplashActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(SettingsActivity.this, mPendingIntentId, mStartActivity,PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) SettingsActivity.this.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
            }
        }, 100);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        switch (arg0.getId()) {
            case R.id.languageSpinner:
                if (isChange){
                    tvConfirm.setEnabled(true);
                    tvConfirm.setClickable(true);
                    tvConfirm.setTextColor(ContextCompat.getColor(this,R.color.white));

                }
                isChange=true;
                switch (position) {
                    case 0:
                        lang = "fa";
                        curencyNames = new String[]{"IRR(iran)"};
                        curencySpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, curencyNames, false));

                        break;
                    case 1:
                        lang = "en";
                        curencyNames = new String[]{"GB"};
                        curencySpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, curencyNames, false));

                        break;
                    case 2:
                        lang = "tr";
                        curencyNames = new String[]{"TRY", "EUR"};
                        curencySpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, curencyNames, false));

                        break;
                }


                break;
            case R.id.curencySpinner:
                break;
            case R.id.officeSpinner:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

}
