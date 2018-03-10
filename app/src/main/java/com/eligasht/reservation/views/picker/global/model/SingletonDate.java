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
        this.startDate.setAnotherCustomDate(endDate);
    }


    public CustomDate getStartDate() {
        return this.startDate;
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
}
