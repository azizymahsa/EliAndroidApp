package com.reserv.myapplicationeli.tools.datetools;

/**
 * Created by hossein-ra on 1/10/2017.
 */

public enum GregorianMonth {
    Jan,
    Feb,
    Mar,
    Apr,
    May,
    Jun,
    Jul,
    Aug,
    Sep,
    Oct,
    Nov,
    Dec;

    public int getValue()
    {
        return this.ordinal();
    }

    public static GregorianMonth forValue(int value)
    {
        return values()[value];
    }
}
