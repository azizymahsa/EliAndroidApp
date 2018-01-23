package com.reserv.myapplicationeli.lost.hotel;

/**
 * Created by Reza.nejati on 1/22/2018.
 */

public class HotelPreFactorModel {
    String HotelName;
    String CheckIn;
    String CheckOut;
    String Adult;
    String Child;
    String Room;

    public HotelPreFactorModel(String hotelName, String checkIn, String checkOut, String adult, String child, String room) {
        HotelName = hotelName;
        CheckIn = checkIn;
        CheckOut = checkOut;
        Adult = adult;
        Child = child;
        Room = room;
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

    public String getAdult() {
        return Adult;
    }

    public void setAdult(String adult) {
        Adult = adult;
    }

    public String getChild() {
        return Child;
    }

    public void setChild(String child) {
        Child = child;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }
}
