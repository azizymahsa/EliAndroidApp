package com.reserv.myapplicationeli.models.hotel.api.rooms.call;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class RoomRequest {
    public final IdentityRooms identity;
    public final String FltGUID;
    public final String EHotelId;
    public final String OfferIds;
    public final String FltId;
    public final String ResultUniqID;
    public final String Culture;

    public RoomRequest(IdentityRooms identity, String fltGUID, String EHotelId, String offerIds, String fltId, String resultUniqID, String culture) {
        this.identity = identity;
        FltGUID = fltGUID;
        this.EHotelId = EHotelId;
        OfferIds = offerIds;
        FltId = fltId;
        ResultUniqID = resultUniqID;
        Culture = culture;
    }
}
