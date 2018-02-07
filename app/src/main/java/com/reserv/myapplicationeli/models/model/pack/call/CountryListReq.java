package com.reserv.myapplicationeli.models.model.pack.call;


import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by elham.bonyani on 1/7/2018.
 */

public class CountryListReq extends Identity {

    private String CityCode;

    public CountryListReq(String userName, String password, String termianlId,String cityCode,String requestorID ) {
        super(userName, password, termianlId,requestorID);
        this.CityCode = cityCode;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }
}
