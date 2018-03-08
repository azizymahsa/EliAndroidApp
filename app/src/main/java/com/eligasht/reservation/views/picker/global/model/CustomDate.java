package com.eligasht.reservation.views.picker.global.model;


import android.content.SharedPreferences;

import com.eligasht.reservation.views.ui.SingletonContext;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.Calendar;
import java.util.Locale;

import calendar.CivilDate;
import calendar.DateConverter;
import calendar.PersianDate;

/**
 * Created by Ahmad.nemati on 3/6/2018.
 */

public class CustomDate {


    private PersianDate persianDate;
    private CivilDate civilDate;


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

    private String getDescriptionGeo(Locale locale) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(civilDate.getYear(), civilDate.getMonth(), civilDate.getDayOfMonth());
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
