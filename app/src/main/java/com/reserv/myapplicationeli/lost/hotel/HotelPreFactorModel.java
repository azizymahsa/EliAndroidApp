package com.reserv.myapplicationeli.lost.hotel;

/**
 * Created by Reza.nejati on 1/22/2018.
 */

public class HotelPreFactorModel {
    String HotelName;
    String CheckIn;
    String CheckOut;
    String Sum;

    public HotelPreFactorModel(String hotelName, String checkIn, String checkOut, String sum) {
        HotelName = hotelName;
        CheckIn = checkIn;
        CheckOut = checkOut;
        Sum = sum;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(String checkIn) {
        CheckIn = checkIn;
    }

    public String getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(String checkOut) {
        CheckOut = checkOut;
    }

    public String getSum() {
        return Sum;
    }

    public void setSum(String sum) {
        Sum = sum;
    }
}
