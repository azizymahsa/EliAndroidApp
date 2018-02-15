package com.eligasht.reservation.models.hotel.getprefactor.call;


import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by Reza.nejati on 1/28/2018.
 */

public class RequestPre {
    public final Identity identity;
    public final String Type;
    public final String Culture;
    public final String invoiceNo;

    public RequestPre(String culture, String type, String invoiceNo, Identity identity) {
        Culture = culture;
        Type = type;
        this.invoiceNo = invoiceNo;
        this.identity = identity;
    }
}
