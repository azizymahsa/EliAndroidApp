package com.eligasht.reservation.views.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.adapters.SpinnerCustomrAdapter;
import com.eligasht.reservation.views.dialogs.SelectLanguageDialog;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SplashFragment;

/**
 * Created by Ahmad.nemati on 3/3/2018.
 */

public class SettingsActivity extends BaseActivity implements View.OnClickListener, SelectLanguageDialog.LanguageClick, AdapterView.OnItemSelectedListener {
    private View dialogOpener;
    String[] countryNames={"ایران","England","Turkey"};
    int flags[] = {R.drawable.iran, R.drawable.united_kingdom, R.drawable.turkey};
    String[] curencyNames={"IRR(iran)"};
    String[] officeNames={"Eligasht-IR","Eligasht-UK","Eligasht-TK"};
    Spinner languageSpinner,curencySpinner,officeSpinner;

    SpinnerCustomrAdapter customAdapter;
    SpinnerCustomrAdapter curencyNamesAdapter;
    SpinnerCustomrAdapter officeNamesAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        InitUi.Toolbar(this, false, R.color.toolbar_color, getResources().getString(R.string.settings));
        dialogOpener = findViewById(R.id.dialog_opener);
        dialogOpener.setOnClickListener(this);
        curencySpinner = findViewById(R.id.curencySpinner);
        languageSpinner = findViewById(R.id.languageSpinner);
        officeSpinner = findViewById(R.id.officeSpinner);
        languageSpinner.setOnItemSelectedListener(this);
        curencySpinner.setOnItemSelectedListener(this);
        officeSpinner.setOnItemSelectedListener(this);




        languageSpinner.setAdapter(new SpinnerCustomrAdapter(getApplicationContext(),flags,countryNames,true));
        curencySpinner.setAdapter(new SpinnerCustomrAdapter(getApplicationContext(),flags,curencyNames,false));
        officeSpinner.setAdapter(new SpinnerCustomrAdapter(getApplicationContext(),flags,officeNames,false));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.dialog_opener:
                openDialog();
                break;
        }
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
                Intent mStartActivity = new Intent(SettingsActivity.this, SplashFragment.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(SettingsActivity.this, mPendingIntentId, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) SettingsActivity.this.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
            }
        }, 100);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        switch (arg0.getId()){
            case R.id.languageSpinner:

                Toast.makeText(getApplicationContext(), countryNames[position], Toast.LENGTH_LONG).show();
                switch (position){
                    case 0:
                      curencyNames= new String[]{"IRR(iran)"};
                        curencyNamesAdapter.notifyDataSetChanged();

                        break;
                    case 1:
                        curencyNames= new String[]{"GB"};
                        curencyNamesAdapter.notifyDataSetChanged();

                        break;
                    case 2:
                         curencyNames= new String[]{"TRY", "EUR"};
                        curencyNamesAdapter.notifyDataSetChanged();

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
        // TODO Auto-generated method stub
    }

}
