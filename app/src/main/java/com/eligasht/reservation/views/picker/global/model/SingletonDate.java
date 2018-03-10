package com.eligasht.reservation.views.picker.global.model;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Ahmad.nemati on 3/8/2018.
 */

public class SingletonDate {
    private static final SingletonDate ourInstance = new SingletonDate();
    private CustomDate startDate;
    private CustomDate endDate;

    private SingletonDate() {
        EventBus.getDefault().register(this);
    }

    public static SingletonDate getInstance() {
        return ourInstance;
    }

    public void setReverseDate(CustomDate startDate, CustomDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDate.setAnotherCustomDate(endDate);
    }


    public CustomDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(CustomDate customDate) {
        this.startDate = customDate;
        this.startDate.setAnotherCustomDate(endDate);
    }

    public CustomDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(CustomDate customDate) {
        this.endDate = customDate;
        this.startDate.setAnotherCustomDate(customDate);
    }

    public void initDate() {
        startDate = CustomDate.today();
        endDate = CustomDate.today();
        startDate.setAnotherCustomDate(endDate);
    }

    public void checkConflictDate() {
        if (!CustomDate.isOlderThan(startDate.getCalendar(), endDate.getCalendar())) {
            endDate = startDate;
            startDate.setAnotherCustomDate(endDate);
            endDate.setAnotherCustomDate(null);
        }
    }

    @Subscribe()
    public void onMessageEvent(EventBusCalendar event) {
        if (event.isUpdated()) {
            Log.e("Tag", "onMessageEvent: Evetn Bus Updated");
            startDate.setAnotherCustomDate(endDate);
        }

    }
}
