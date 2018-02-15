package com.eligasht.reservation.models.hotel.api.hotelPolicy.request;

import com.eligasht.reservation.models.hotel.api.rooms.call.IdentityRooms;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class RequestPolicy {
    public final IdentityRooms identity;

    public final String EHotelId;
    public final String OfferId;
    public final String SearchKey;
    public final String Culture;
    public final boolean TranslteToPersian;

    public RequestPolicy(IdentityRooms identity, String EHotelId, String offerId, String searchKey, String culture, boolean translteToPersian) {
        this.identity = identity;
        this.EHotelId = EHotelId;
        OfferId = offerId;
        SearchKey = searchKey;
        Culture = culture;
        TranslteToPersian = translteToPersian;
    }
}
