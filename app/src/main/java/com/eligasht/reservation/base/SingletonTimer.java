package com.eligasht.reservation.base;

import android.os.CountDownTimer;

import com.eligasht.reservation.models.eventbus.TerminateBus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Ahmad.nemati on 5/30/2018.
 */
public class SingletonTimer {
    private CountDownTimer countDownTimer;
    private static final SingletonTimer ourInstance = new SingletonTimer();

    public static SingletonTimer getInstance() {
        return ourInstance;
    }

    private SingletonTimer() {
        countDownTimer = new CountDownTimer(60000, 40000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                EventBus.getDefault().post(new TerminateBus());
            }
        };
    }

    public void start() {
        countDownTimer.cancel();
        countDownTimer.start();
    }

    public void stop() {
        countDownTimer.cancel();
    }


}