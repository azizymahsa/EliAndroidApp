package com.eligasht.reservation.views.picker.global.model;

/**
 * Created by Ahmad.nemati on 3/8/2018.
 */

public class SingletonDate {
    private static final SingletonDate ourInstance = new SingletonDate();
    private CustomDate startDate;
    private CustomDate endDate;

    private SingletonDate() {
    }

    public static SingletonDate getInstance() {
        return ourInstance;
    }

    public void setReverseDate(CustomDate startDate, CustomDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setSingleDate(CustomDate startDate) {
        this.startDate = startDate;
        this.endDate = null;
    }

    public CustomDate getStartDate() {
        return this.startDate;
    }

    public CustomDate getEndDate() {
        return this.endDate;
    }
}
