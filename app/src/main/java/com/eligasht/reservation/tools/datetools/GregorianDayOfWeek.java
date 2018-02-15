package com.eligasht.reservation.tools.datetools;

/**
 * Created by hossein-ra on 1/10/2017.
 */

public enum GregorianDayOfWeek {
    Sun,
    Mon,
    Tue,
    Wed,
    Thu,
    Fri,
    Sat;

    public int getValue()
    {
        return this.ordinal();
    }

    public static GregorianDayOfWeek forValue(int value)
    {
        return values()[value];
    }
}
