package com.reserv.myapplicationeli.models.hotel.getprefactor.response;

/**
 * Created by Reza.nejati on 1/31/2018.
 */

public class BookingActionsList {
    public final int ActionStep;
    public final String MsgTextFa;

    public BookingActionsList(int actionStep, String msgTextFa) {
        ActionStep = actionStep;
        MsgTextFa = msgTextFa;
    }
}
