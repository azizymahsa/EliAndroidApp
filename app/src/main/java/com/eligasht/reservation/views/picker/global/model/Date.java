package com.eligasht.reservation.views.picker.global.model;

/**
 * Created by Ahmad.nemati on 3/6/2018.
 */

public class Date {
    private String year;
    private String month;
    private String day;


    public Date(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date() {

    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


}
