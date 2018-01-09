package com.reserv.myapplicationeli.tools.datetools;

/**
 * Created by hossein-ra on 1/10/2017.
 */

public enum PersianMonth {
    Farvardin,
    Ordibehesht,
    Khordad,
    Tir,
    Mordad,
    Shahrivar,
    Mehr,
    Aban,
    Azar,
    Dey,
    Bahman,
    Esfand;

    public int getValue()
    {
        return this.ordinal();
    }

    public static PersianMonth forValue(int value)
    {
        return values()[value];
    }
}
