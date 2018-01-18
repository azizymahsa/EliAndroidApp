package com.reserv.myapplicationeli.models.hotel.api.holdSelectedHotelFlightRoom.call;

import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class RoomHFRequest {

    public final Identity identity;
    public final String Culture;
    public final String EHotelId;
    public final String OfferIds;
    public final String ResultUniqID;
    public final String FltGUID;
    public final String FltId;


    public RoomHFRequest(Identity identity, String culture, String EHotelId, String offerIds, String resultUniqID, String fltGUID, String fltId) {
        this.identity = identity;
        Culture = culture;
        this.EHotelId = EHotelId;
        OfferIds = offerIds;
        ResultUniqID = resultUniqID;
        FltGUID = fltGUID;
        FltId = fltId;
    }
}
