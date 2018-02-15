package com.eligasht.reservation.models.hotel.getprefactor.response;

import java.util.List;

/**
 * Created by Reza.nejati on 1/31/2018.
 */

public class PreFactorBookingLogs {

    public final List<BookingActionsList> BookingActionsList;
    public boolean SuccessBooking;

    public PreFactorBookingLogs(List<com.eligasht.reservation.models.hotel.getprefactor.response.BookingActionsList> bookingActionsList, boolean successBooking) {
        BookingActionsList = bookingActionsList;
        SuccessBooking = successBooking;
    }
}
