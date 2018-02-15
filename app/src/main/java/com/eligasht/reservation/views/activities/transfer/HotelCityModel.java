package com.eligasht.reservation.views.activities.transfer;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class HotelCityModel {
    public String HotelID;
    public String HotelNameEn;
    public String HotelNameFa;

    public HotelCityModel(String hotelID, String hotelNameEn, String hotelNameFa) {
        HotelID = hotelID;
        HotelNameEn = hotelNameEn;
        HotelNameFa = hotelNameFa;
    }

    public String getHotelID() {
        return HotelID;
    }

    public void setHotelID(String hotelID) {
        HotelID = hotelID;
    }

    public String getHotelNameEn() {
        return HotelNameEn;
    }

    public void setHotelNameEn(String hotelNameEn) {
        HotelNameEn = hotelNameEn;
    }

    public String getHotelNameFa() {
        return HotelNameFa;
    }

    public void setHotelNameFa(String hotelNameFa) {
        HotelNameFa = hotelNameFa;
    }
}
