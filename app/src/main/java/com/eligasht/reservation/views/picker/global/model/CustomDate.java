package com.eligasht.reservation.views.picker.global.model;


import android.content.SharedPreferences;

import com.eligasht.reservation.views.ui.SingletonContext;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import calendar.CivilDate;
import calendar.DateConverter;
import calendar.PersianDate;

/**
 * Created by Ahmad.nemati on 3/6/2018.
 */

public class CustomDate {


    private PersianDate persianDate;
    private CivilDate civilDate;
    private CustomDate anotherCustomDate = null;


    public CustomDate(String year, String month, String day) {


        if (year.startsWith("1")) {
            persianDate = new PersianDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            civilDate = DateConverter.persianToCivil(persianDate);
        } else {
            civilDate = new CivilDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            persianDate = DateConverter.civilToPersian(civilDate);
        }
    }

    public CustomDate(int year, int month, int day) {
        String y = String.valueOf(year);
        if (y.startsWith("1")) {
            persianDate = new PersianDate(year, month, day);
            civilDate = DateConverter.persianToCivil(persianDate);
        } else {
            civilDate = new CivilDate(year, month, day);
            persianDate = DateConverter.civilToPersian(civilDate);
        }
    }

    public static boolean isOlderThan(Calendar startDate, Calendar endDate) {
        long start = startDate.getTimeInMillis();
        long end = endDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays((end - start)) >= 0;
        // return TimeUnit.MILLISECONDS.toDays((end - start));
    }

    public static CustomDate today() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        return new CustomDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

    }

    public int getPersianYear() {
        return persianDate.getYear();
    }

    public int getPersianMonth() {
        return persianDate.getMonth();
    }

    public int getPersianDay() {
        return persianDate.getDayOfMonth();
    }

    public int getGeoYear() {
        return civilDate.getYear();
    }

    public int getGeoMonth() {
        return civilDate.getMonth();
    }

    public int getGeoDay() {
        return civilDate.getDayOfMonth();
    }

    public String getFullPersian() {
        return persianDate.getYear() + "/" + persianDate.getMonth() + "/" + persianDate.getDayOfMonth();
    }

    public String getFullGeo() {
        String month;
        if (civilDate.getMonth() < 10)
            month = "0" + civilDate.getMonth();
        else
            month = String.valueOf(civilDate.getMonth());

        String day;
        if (civilDate.getDayOfMonth() < 10)
            day = "0" + civilDate.getDayOfMonth();
        else
            day = String.valueOf(civilDate.getDayOfMonth());

        return civilDate.getYear() + "/" + month + "/" + day;
    }

    private String getDescriptionPersian() {
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.setPersianDate(persianDate.getYear(), persianDate.getMonth() - 1, persianDate.getDayOfMonth());

        return persianCalendar.getPersianWeekDayName()
                + " " +
                persianCalendar.getPersianDay() + " " + persianCalendar.getPersianMonthName();
    }

    public boolean addOneDay() {
        if (anotherCustomDate == null) {
            addDay(1);
            return true;
        }
        if (daysBetween(getCalendar(), anotherCustomDate.getCalendar()) > 0) {
            addDay(1);
            return true;
        }

        return false;
    }


    private void addDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(civilDate.getYear(), civilDate.getMonth() - 1, civilDate.getDayOfMonth());
        calendar.add(Calendar.DATE, day);
        calendar.setTimeZone(TimeZone.getDefault());
        updateDate(calendar);
    }

    public boolean minusOneDay() {

        Calendar today = Calendar.getInstance();
        today.setTimeZone(TimeZone.getDefault());
        if (daysBetween(today, getCalendar()) > 0) {
            addDay(-1);
            return true;
        }
        return false;
    }

    public void setAnotherCustomDate(CustomDate anotherCustomDate) {
        this.anotherCustomDate = anotherCustomDate;
    }

    private void updateDate(Calendar calendar) {
        civilDate = new CivilDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        persianDate = DateConverter.civilToPersian(civilDate);

    }

    private long daysBetween(Calendar startDate, Calendar endDate) {
        long end = startDate.getTimeInMillis();
        long start = endDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }

    public Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(civilDate.getYear(), civilDate.getMonth() - 1, civilDate.getDayOfMonth());
        calendar.setTimeZone(TimeZone.getDefault());
        return calendar;
    }


    private String getDescriptionGeo(Locale locale) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(civilDate.getYear(), civilDate.getMonth() - 1, civilDate.getDayOfMonth());
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale)
                + " " +
                civilDate.getDayOfMonth() + " " + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
    }

    public String getDescription() {
        SharedPreferences sharedPrefrences = SingletonContext.getInstance().getContext().getSharedPreferences("eligasht.com", 0);
        if (sharedPrefrences.getBoolean("isGregorian", false))
            return getDescriptionGeo(Locale.ENGLISH);
        if (Locale.getDefault().getLanguage().equals("fa"))
            return getDescriptionPersian();
        return getDescriptionGeo(Locale.getDefault());
    }


    @Override
    public String toString() {
        return "CustomDate{" +
                " Persian Year='" + getPersianYear() + '\n' +
                ", Persian month='" + getPersianMonth() + '\n' +
                ", Persian day='" + getPersianDay() + '\n' +
                " Geo year='" + getGeoYear() + '\n' +
                ", Geo month='" + getGeoMonth() + '\n' +
                ", Geo day='" + getGeoDay() + '\n' +
                " Full Persian='" + getFullPersian() + '\n' +
                ", Full Geo='" + getFullGeo() + '\n' +
                ", Description Persian='" + getDescriptionPersian() + '\n' +
                ", Description Geo='" + getDescriptionGeo(Locale.getDefault()) + '\n' +
                '}';
    }


}
