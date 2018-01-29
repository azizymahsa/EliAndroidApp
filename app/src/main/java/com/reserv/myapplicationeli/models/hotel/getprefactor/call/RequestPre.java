package com.reserv.myapplicationeli.models.hotel.getprefactor.call;

import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by Reza.nejati on 1/28/2018.
 */

public class RequestPre {
    public final String Culture;
    public final String invoiceNo;
    public final String Type;
    public final Identity identity;

    public RequestPre(String culture, String invoiceNo, String type, Identity identity) {
        Culture = culture;
        this.invoiceNo = invoiceNo;
        Type = type;
        this.identity = identity;
    }
}
