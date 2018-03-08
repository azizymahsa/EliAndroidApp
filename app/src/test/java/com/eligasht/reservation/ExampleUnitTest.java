package com.eligasht.reservation;

import org.junit.Test;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public static long daysBetween(Calendar startDate, Calendar endDate) {
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }

    @Test
    public void addition_isCorrect() throws Exception {
        Calendar today = Calendar.getInstance();
        Calendar next = Calendar.getInstance();
//        next.add(Calendar.DATE,1);
        System.out.printf(String.valueOf(daysBetween(today, next)));
    }
}