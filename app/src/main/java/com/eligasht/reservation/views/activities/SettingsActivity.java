package com.eligasht.reservation.views.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.dialogs.SelectLanguageDialog;
import com.eligasht.reservation.views.ui.InitUi;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Ahmad.nemati on 3/3/2018.
 */

public class SettingsActivity extends BaseActivity implements View.OnClickListener, SelectLanguageDialog.LanguageClick {
    int tab;
    private View dialogOpener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        InitUi.Toolbar(this, false, R.color.toolbar_color, "تنظیمات");
        dialogOpener = findViewById(R.id.dialog_opener);
        dialogOpener.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.dialog_opener:
                openDialog();
                break;
        }
    }

    private void dialogChooser() {

        if (getTypeOfLanguage().equals("en"))
            tab = 0;
        else
            tab = 1;

        new MaterialDialog.Builder(this)
                .title("انتخاب زبان")
                .items(R.array.lang_c)
                .itemsCallbackSingleChoice(tab, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        if (which == tab)
                            return true;

                        else {
                            if (which == 1)
                                Prefs.putString("lang", "fa");
                            else
                                Prefs.putString("lang", "en");

                        }

                        return true;
                    }
                })
                .positiveText("تایید")
                .show();


    }

    private void openDialog() {
        SelectLanguageDialog selectLanguageDialog = new SelectLanguageDialog(this, this);
        selectLanguageDialog.show();

    }


    private String getTypeOfLanguage() {
        return Prefs.getString("lang", "fa");
    }

    @Override
    public void onLanguageCLick(String lang) {
        if (Prefs.getString("lang", "fa").equals(lang))
            return;
        Prefs.putString("lang", lang);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mStartActivity = new Intent(SettingsActivity.this, MainActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(SettingsActivity.this, mPendingIntentId, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) SettingsActivity.this.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
            }
        }, 100);
    }
}