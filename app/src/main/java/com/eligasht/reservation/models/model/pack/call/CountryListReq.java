package com.eligasht.reservation.models.model.pack.call;


import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by elham.bonyani on 1/7/2018.
 */

public class CountryListReq extends Identity {

    private String Code;

    public CountryListReq(String userName, String password, String termianlId,String cityCode ) {
        super(userName, password, termianlId);
        this.Code = cityCode;
    }

    public String getCityCode() {
        return Code;
    }

    public void setCityCode(String cityCode) {
        Code = cityCode;
    }
}
