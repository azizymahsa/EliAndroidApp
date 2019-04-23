package com.eligasht.reservation.views.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.ServiceApplication;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.activities.main.MainActivity;
import com.eligasht.reservation.views.adapters.SpinnerCustomAdapter;
import com.eligasht.reservation.views.dialogs.SelectLanguageDialog;
import com.eligasht.reservation.views.ui.InitUi;
import com.eligasht.reservation.views.ui.SplashActivity;
import com.eligasht.service.helper.Const;
import com.eligasht.service.model.newModel.startup.response.Branch;

import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Ahmad.nemati on 3/3/2018.
 */

public class SettingsActivity extends BaseActivity implements View.OnClickListener, SelectLanguageDialog.LanguageClick, AdapterView.OnItemSelectedListener {
    String[] countryNames = {"ایران (IR)", "(UK)England", "(TR) Türkiye"};
    String[] cultureList = null;
    int flags[] = {R.drawable.iran, R.drawable.united_kingdom, R.drawable.turkey};
    String[] curencyNames = {"IRR(iran)"};

   // String[] officeNames = {"Eligasht-IR", "Eligasht-UK", "Eligasht-TK"};
    String[] officeNames = null;
    String[] officeUrl = null;


    Spinner languageSpinner, curencySpinner, officeSpinner;
    Button tvConfirm;
    String lang;
    boolean isChange=false;
    boolean isChangeOff=false;
    FancyButton btnBack;
    TextView txtTitle;
    ImageView txt_hom;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnBack = (FancyButton) findViewById(R.id.btnBack);
        btnBack.setCustomTextFont("fonts/icomoon.ttf");
        btnBack.setText(getString(R.string.search_back_right));
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(this);
        txtTitle= (TextView) findViewById(R.id.tvTitle);
        txtTitle.setOnClickListener(this);
        txtTitle.setText(R.string.settings);

        txt_hom = (ImageView) findViewById(R.id.txt_hom);
        txt_hom.setOnClickListener(this);

      //  InitUi.Toolbar(this, false, R.color.toolbar_color, getResources().getString(R.string.settings));
        tvConfirm = findViewById(R.id.tvConfirm);

        getOfficeNames();

        curencySpinner = findViewById(R.id.curencySpinner);
        languageSpinner = findViewById(R.id.languageSpinner);
        officeSpinner = findViewById(R.id.officeSpinner);
        languageSpinner.setOnItemSelectedListener(this);
        curencySpinner.setOnItemSelectedListener(this);
        officeSpinner.setOnItemSelectedListener(this);
        tvConfirm.setOnClickListener(this);


        languageSpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, countryNames,officeUrl,cultureList, true));
        curencySpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, curencyNames,officeUrl,cultureList, false));
        officeSpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, officeNames,officeUrl,cultureList, false));

        setDefaultLangSpinner();
        setDefaultUrlSpinner();

        tvConfirm.setEnabled(false);
        tvConfirm.setClickable(false);
        tvConfirm.setTextColor(ContextCompat.getColor(this,R.color.focusColor));
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
        finish();
        /* final Fragment currentFragment = mNavHostFragment.getChildFragmentManager().getFragments().get(0);
       final NavController controller = Navigation.findNavController(this, R.id.nav_host_fragment);
        if (currentFragment instanceof OnBackPressedListener)
            ((OnBackPressedListener) currentFragment).onBackPressed();
        else if (!controller.popBackStack())
            finish();*/

    }
    private void setDefaultUrlSpinner() {
          if (Prefs.getString("BranchDef", "").contains("UK")) {


            officeSpinner.setSelection(1);


        } else if (Prefs.getString("BranchDef", "").contains("TR")) {

            officeSpinner.setSelection(2);

        }else{

            officeSpinner.setSelection(0);
        }
    }

    private void setDefaultLangSpinner() {
        if (Prefs.getString("lang", "fa").contains("fa")) {
            curencyNames = new String[]{"IRR(iran)"};
           // officeSpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, officeNames,officeUrl,cultureList, false));
            languageSpinner.setSelection(0);

        } else if (Prefs.getString("lang", "fa").contains("en")) {

            curencyNames = new String[]{"GB"};
          //  officeSpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, officeNames,officeUrl,cultureList, false));

            languageSpinner.setSelection(1);


        } else if (Prefs.getString("lang", "fa").contains("tr")) {
            curencyNames = new String[]{"TRY", "EUR"};
          //  officeSpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, officeNames,officeUrl,cultureList, false));
            languageSpinner.setSelection(2);

        }
    }

    private void getOfficeNames() {
        List<Branch> branchs=SplashActivity.branches;
        officeNames=new String[branchs.size()];
        officeUrl=new String[branchs.size()];
        cultureList=new String[branchs.size()];

        for (int i = 0; i <branchs.size() ; i++) {
            officeNames[i]=branchs.get(i).getName();
            officeUrl[i]=branchs.get(i).getUrl();
            cultureList[i]=branchs.get(i).getDefaultCulture();
        }
    }

    @Override
    public boolean needTerminate() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_hom:
              /*  Prefs.putBoolean("BACK_HOME",true);
                Intent intent2 = new Intent("sendFinish");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);*/
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                finish();
                break;
                case R.id.btnBack:
              /*  Prefs.putBoolean("BACK_HOME",true);
                Intent intent2 = new Intent("sendFinish");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);*/
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.tvConfirm:
                if (isChange) {
                    if (Prefs.getString("lang", "fa").contains(lang)) {
                        tvConfirm.setEnabled(false);
                        tvConfirm.setClickable(false);
                        tvConfirm.setTextColor(ContextCompat.getColor(this, R.color.focusColor));
                      //  return;
                    }
                    Prefs.putString("lang", lang);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent mStartActivity = new Intent(SettingsActivity.this, SplashActivity.class);
                            finish();
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(SettingsActivity.this, mPendingIntentId, mStartActivity,
                                    PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) SettingsActivity.this.getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);
                        }
                    }, 100);
                }
                if (isChangeOff){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent mStartActivity = new Intent(SettingsActivity.this, SplashActivity.class);
                            finish();
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(SettingsActivity.this, mPendingIntentId, mStartActivity,
                                    PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) SettingsActivity.this.getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);
                        }
                    }, 100);
                }
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
                finish();
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
                       // lang = "fa";

                        Prefs.putBoolean("isChangeCulture", true);
                        Prefs.putString("CultureDef", cultureList[position]);
                       // languageSpinner.setSelection(position);
                        if(cultureList[position].contains("-")) {
                          ////  Prefs.putString("lang", cultureList[position].split("-")[0]);
                            lang=cultureList[position].split("-")[0];
                        } else {
                           // Prefs.putString("lang", cultureList[position]);
                            lang=cultureList[position];
                        }

                        curencyNames = new String[]{"IRR(iran)"};
                        curencySpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, curencyNames,officeUrl,cultureList, false));

                        break;
                    case 1:
                      //  lang = "en";

                        Prefs.putBoolean("isChangeCulture", true);
                        Prefs.putString("CultureDef", cultureList[position]);
                       // languageSpinner.setSelection(position);
                        if(cultureList[position].contains("-")) {
                            ////  Prefs.putString("lang", cultureList[position].split("-")[0]);
                            lang=cultureList[position].split("-")[0];
                        } else {
                            // Prefs.putString("lang", cultureList[position]);
                            lang=cultureList[position];
                        }


                        curencyNames = new String[]{"GB"};
                        curencySpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, curencyNames,officeUrl,cultureList, false));

                        break;
                    case 2:
                      //  lang = "tr";

                        Prefs.putBoolean("isChangeCulture", true);
                        Prefs.putString("CultureDef", cultureList[position]);
                       // languageSpinner.setSelection(position);
                        if(cultureList[position].contains("-")) {
                            ////  Prefs.putString("lang", cultureList[position].split("-")[0]);
                            lang=cultureList[position].split("-")[0];
                        } else {
                            // Prefs.putString("lang", cultureList[position]);
                            lang=cultureList[position];
                        }


                        curencyNames = new String[]{"TRY", "EUR"};
                        curencySpinner.setAdapter(new SpinnerCustomAdapter(getApplicationContext(), flags, curencyNames,officeUrl,cultureList, false));

                        break;
                }


                break;
            case R.id.curencySpinner:
                break;
            case R.id.officeSpinner:
                if (isChangeOff){
                    tvConfirm.setEnabled(true);
                    tvConfirm.setClickable(true);
                    tvConfirm.setTextColor(ContextCompat.getColor(this,R.color.white));

                }

                switch (position) {
                    case 0:
                       // officeSpinner.setSelection(position);
                        Prefs.putBoolean("isChangeBranch", true);
                        Prefs.putString("BranchDef", countryNames[position]);
                        Prefs.putBoolean("isChangeUrl", true);
                        Prefs.putString("BASEURL",officeUrl[position]);
                        Const.BASEURL=officeUrl[position];
                        Log.d("onClick:1 ",Const.BASEURL);
                        //update url base
                        ServiceApplication serviceApplication=new ServiceApplication() {
                            @Override
                            public void onCreate() {
                                super.onCreate();

                            }
                        };
                        serviceApplication.onCreate();
                        isChangeOff=true;


                        break;
                    case 1:
                      //  officeSpinner.setSelection(position);
                        Prefs.putBoolean("isChangeBranch", true);
                        Prefs.putString("BranchDef", countryNames[position]);
                        Prefs.putBoolean("isChangeUrl", true);
                        Prefs.putString("BASEURL",officeUrl[position]);
                        Const.BASEURL=officeUrl[position];
                        Log.d("onClick:1 ",Const.BASEURL);
                        //update url base
                        ServiceApplication serviceApplication1=new ServiceApplication() {
                            @Override
                            public void onCreate() {
                                super.onCreate();

                            }
                        };
                        serviceApplication1.onCreate();
                        isChangeOff=true;
                        break;
                    case 2:
                       // officeSpinner.setSelection(position);
                        Prefs.putBoolean("isChangeBranch", true);
                        Prefs.putString("BranchDef", countryNames[position]);
                        Prefs.putBoolean("isChangeUrl", true);
                        Prefs.putString("BASEURL",officeUrl[position]);
                        Const.BASEURL=officeUrl[position];
                        Log.d("onClick:1 ",Const.BASEURL);
                        //update url base
                        ServiceApplication serviceApplication2=new ServiceApplication() {
                            @Override
                            public void onCreate() {
                                super.onCreate();

                            }
                        };
                        serviceApplication2.onCreate();
                        isChangeOff=true;
                        break;
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

}
