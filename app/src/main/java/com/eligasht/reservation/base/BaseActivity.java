package com.eligasht.reservation.base;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.eligasht.reservation.models.eventbus.TerminateBus;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.sdsmdg.tastytoast.TastyToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Reza.nejati on 1/2/2018.
 */

public abstract class BaseActivity extends Base {
    private BroadcastReceiver sendStartTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notiRecive();
        try {
          EventBus.getDefault().register(this);
        } catch (Exception e) {

        }

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



    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            EventBus.getDefault().unregister(this);
        } catch (Exception e) {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void terminate(TerminateBus terminateBus) {
        try {
            if (needTerminate() && SingletonTimer.NEED_SHOW_TOAST) {
                SingletonTimer.NEED_SHOW_TOAST=false;
                TastyToast.makeText(SingletonContext.getInstance().getContext(), "زمان نشست پایان یافته است", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                finish();
            }

        } catch (Exception e) {

        }
    }


    public abstract boolean needTerminate();
}