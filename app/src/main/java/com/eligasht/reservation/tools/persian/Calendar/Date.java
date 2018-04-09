package com.eligasht.reservation.tools.persian.Calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * __  ____   _____ _____
 * |  \/  \ \ / /_ _|_   _|
 * | |\/| |\ V / | |  | |
 * |_|  |_| \_/ |___| |_|
 * Maheen Veera Information Technology
 * http://mvit.ir
 * Intelligent Transportation Service Management (ITSM) Project
 * Authors:
 * Reza Nejati <nejati@mvit.ir>
 * Mohammad Saleh Shirani <shirani@mvit.ir>
 * Ali MasudianPour <masudianpour@mvit.ir>
 * Copyright © 2016
 */
public class Date {
    String date_;
    String date;

    public String getGregorianDate() {
        return date_;
    }

    public String getPersianDate() {
        return date;
    }


    public Date() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss");
        date_ = dateFormat.format(cal.getTime());
        date = dateFormat.format(cal.getTime());
        String[] date_parts = date.split("\\s+");
        String[] _date = date_parts[0].split("/");
        String year = _date[0];
        String month = _date[1];
        String day = _date[2];
        CalendarTool ct = new CalendarTool(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
        date= ct.getIranianDate().toString() + "\r" + date_parts[1];


    }

}
