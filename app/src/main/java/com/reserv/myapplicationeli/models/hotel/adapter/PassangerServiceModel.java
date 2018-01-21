package com.reserv.myapplicationeli.models.hotel.adapter;

/**
 * Created by Reza.nejati on 1/21/2018.
 */

public class PassangerServiceModel {
    String CityFa;
    String RoomPrice;
    String HotelBoardCode;


    public PassangerServiceModel(String cityFa, String roomPrice, String hotelBoardCode) {
        CityFa = cityFa;
        RoomPrice = roomPrice;
        HotelBoardCode = hotelBoardCode;
    }

    public String getCityFa() {
        return CityFa;
    }

    public void setCityFa(String cityFa) {
        CityFa = cityFa;
    }

    public String getRoomPrice() {
        return RoomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        RoomPrice = roomPrice;
    }

    public String getHotelBoardCode() {
        return HotelBoardCode;
    }

    public void setHotelBoardCode(String hotelBoardCode) {
        HotelBoardCode = hotelBoardCode;
    }
}
