package com.reserv.myapplicationeli.models.hotel.api.detail.call;

/**
 * Created by Reza.nejati on 1/8/2018.
 */

public class GetHotelDRequest {
    public final String Culture;
    public final String EHotelId;

    public GetHotelDRequest(String culture, String EHotelId) {
        Culture = culture;
        this.EHotelId = EHotelId;
    }
}
