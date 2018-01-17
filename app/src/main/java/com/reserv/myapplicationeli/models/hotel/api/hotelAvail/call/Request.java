package com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call;


import java.util.List;

/**
 * Created by Reza.nejati on 1/5/2018.
 */

public class Request {
    public final String EchoToken;
    public final Identity identity;
    public final String CheckinString;
    public final String CheckoutString;
    public final String Depart;
    public final String EDepart;
    public final List<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Rooms> Rooms;
    public final String RoomsString;
    public final String Culture;
    public final String Source;

    public Request(String echoToken, Identity identity, String checkinString, String checkoutString,
                   String depart, String EDepart, List<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Rooms> rooms,
                   String roomsString, String culture, String source) {
        EchoToken = echoToken;
        this.identity = identity;
        CheckinString = checkinString;
        CheckoutString = checkoutString;
        Depart = depart;
        this.EDepart = EDepart;
        Rooms = rooms;
        RoomsString = roomsString;
        Culture = culture;
        Source = source;
    }
}
