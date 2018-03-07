package com.eligasht.reservation.models.model.pack;

import com.eligasht.R;
import com.eligasht.reservation.views.ui.SingletonContext;

/**
 * Created by elham.bonyani on 2/17/2017.
 */

public enum ChildAgeRange {

    F0T2(1),
    F2T3(2),
    F3T4(3),
    F4T5(4),
    F5T6(5),
    F6T7(6),
    F7T8(7),
    F8T9(8),
    F9T10(9),
    F10T11(10),
    F11T12(11),
    F12T13(12);

    public static final int SIZE = Integer.SIZE;

    private int intValue;
    private static java.util.HashMap<Integer, ChildAgeRange> mappings;

    private static java.util.HashMap<Integer, ChildAgeRange> getMappings() {
        if (mappings == null) {
            synchronized (ChildAgeRange.class) {
                if (mappings == null) {
                    mappings = new java.util.HashMap<Integer, ChildAgeRange>();
                }
            }
        }
        return mappings;
    }

    ChildAgeRange(int value) {
        intValue = value;
        getMappings().put(value, this);
    }

    public int getValue() {
        return intValue;
    }

    public static ChildAgeRange forValue(Integer value) {
        return getMappings().get((value == null || value == 0) ? 1 : value);
    }

    @Override
    public String toString() {
        switch (this) {

            case F0T2:
                return SingletonContext.getInstance().getContext().getString(R.string._0);
            case F2T3:
                return SingletonContext.getInstance().getContext().getString(R.string._1);
            case F3T4:
                return SingletonContext.getInstance().getContext().getString(R.string._2);
            case F4T5:
                return SingletonContext.getInstance().getContext().getString(R.string._3);
            case F5T6:
                return SingletonContext.getInstance().getContext().getString(R.string._4);
            case F6T7:
                return SingletonContext.getInstance().getContext().getString(R.string._5);
            case F7T8:
                return SingletonContext.getInstance().getContext().getString(R.string._6);
            case F8T9:
                return SingletonContext.getInstance().getContext().getString(R.string._7);
            case F9T10:
                return SingletonContext.getInstance().getContext().getString(R.string._8);
            case F10T11:
                return SingletonContext.getInstance().getContext().getString(R.string._9);
            case F11T12:
                return SingletonContext.getInstance().getContext().getString(R.string._10);
            case F12T13:
                return SingletonContext.getInstance().getContext().getString(R.string._11);
            default:
                return SingletonContext.getInstance().getContext().getString(R.string._12);
        }
    }
}
