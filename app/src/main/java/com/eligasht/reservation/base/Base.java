package com.eligasht.reservation.base;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.adjust.sdk.Adjust;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.views.ui.dialog.app.InternetAlert;
import com.google.gson.Gson;
import com.zplesac.connectionbuddy.ConnectionBuddy;
import com.zplesac.connectionbuddy.interfaces.ConnectivityChangeListener;
import com.zplesac.connectionbuddy.models.ConnectivityEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;


/**
 * Created by Reza.nejati on 2/12/2018.
 */

public abstract class Base extends AppCompatActivity implements ConnectivityChangeListener {
    InternetAlert internetAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            String languageToLoad = Prefs.getString("lang", "fa"); // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                config.setLocale(locale);
            }else{
                config.locale = locale;

            }
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } catch (Exception e) {
            Log.e("testerror", e.getMessage());
        }
        super.onCreate(savedInstanceState);
        ConnectionBuddy.getInstance().clearNetworkCache(this, savedInstanceState);
        internetAlert = new InternetAlert(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            String languageToLoad = Prefs.getString("lang", "fa"); // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                config.setLocale(locale);
            }else{
                config.locale = locale;

            }
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } catch (Exception e) {
            Log.e("testerror", e.getMessage());
        }

        // Register for connectivity changes
        ConnectionBuddy.getInstance().registerForConnectivityEvents(this, this);
    }

    @Override
    protected void onStop() {
        // Unregister from connectivity events
        ConnectionBuddy.getInstance().unregisterFromConnectivityEvents(this);

        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            String languageToLoad = Prefs.getString("lang", "fa"); // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                config.setLocale(locale);
            }else{
                config.locale = locale;

            }
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        } catch (Exception e) {
        }
    }

    /**
     * Override this method if you want to manually handle connectivity change events.
     *
     * @param event ConnectivityEvent which holds all data about network connection state.
     */
    @Override
    public void onConnectionChange(ConnectivityEvent event) {
        try {
            JSONObject jsonObj = new JSONObject(new Gson().toJson(event));
            JSONObject getAirportsResult = jsonObj.getJSONObject("state");


            if (getAirportsResult.getString("value").equals("1")) {

                internetAlert.isCancel();
            } else {

                internetAlert.isShow();

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

