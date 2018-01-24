package com.reserv.myapplicationeli.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;

import mehdi.sakout.fancybuttons.FancyButton;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by Reza.nejati on 1/2/2018.
 */

public class BaseActivity extends AppCompatActivity  {
    private BroadcastReceiver sendStartTimer;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notiRecive();

    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }
    public void notiRecive() {
        sendStartTimer = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                finish();
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(sendStartTimer,
                new IntentFilter("sendFinish"));

    }

    protected ProgressDialog mProgressDialog;
    protected AlertDialog mAlertDialog;

    protected void needShowProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("لطفا کمی صبر کنید ...");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    protected void needHideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void needShowAlertDialog(String message, boolean canelable) {
        if(mAlertDialog!= null && mAlertDialog.isShowing()){
            return;
        }
        mAlertDialog = new AlertDialog.Builder(this).create();
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.alert_dialog_net, null);
        mAlertDialog.setCancelable(canelable);
        FancyButton btnOk = (FancyButton) view.findViewById(R.id.btnOk);
        TextView tvAlert = (TextView) view.findViewById(R.id.tvAlert);

        btnOk.setCustomTextFont("irsans.ttf");
        tvAlert.setText(message);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });

        mAlertDialog.setView(view);
        mAlertDialog.setCancelable(true);
        mAlertDialog.show();
    }

}