package com.eligasht.reservation.models.model.pack;

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
                return "0 تا 2 سال";
            case F2T3:
                return "2 تا 3 سال";
            case F3T4:
                return "3 تا 4 سال";
            case F4T5:
                return "4 تا 5 سال";
            case F5T6:
                return "5 تا 6 سال";
            case F6T7:
                return "6 تا 7 سال";
            case F7T8:
                return "7 تا 8 سال";
            case F8T9:
                return "8 تا 9 سال";
            case F9T10:
                return "9 تا 10 سال";
            case F10T11:
                return "10 تا 11 سال";
            case F11T12:
                return "11 تا 12 سال";
            case F12T13:
                return "12 سال";
            default:
                return "2 سال";
        }
    }
}
