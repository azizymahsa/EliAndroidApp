package com.eligasht.reservation.models.hotel.api;

/**
 * Created by Reza.nejati on 1/23/2018.
 */

public class Errors {
    public final int Code;
    public final String DetailedMessage;
    public final String Message;

    public Errors(int code, String detailedMessage, String message) {
        Code = code;
        DetailedMessage = detailedMessage;
        Message = message;
    }
}
