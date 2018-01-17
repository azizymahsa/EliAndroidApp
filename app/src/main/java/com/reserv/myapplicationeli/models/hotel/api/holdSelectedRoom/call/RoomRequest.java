package com.reserv.myapplicationeli.models.hotel.api.holdSelectedRoom.call;

import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class RoomRequest {

    public final Identity identity;
    public final String Culture;
    public final String EHotelId;
    public final String OfferIds;
    public final String ResultUniqID;

    public RoomRequest(Identity identity, String culture, String EHotelId, String offerIds, String resultUniqID) {
        this.identity = identity;
        Culture = culture;
        this.EHotelId = EHotelId;
        OfferIds = offerIds;
        ResultUniqID = resultUniqID;
    }
}
