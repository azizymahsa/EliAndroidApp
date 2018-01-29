package com.reserv.myapplicationeli.models;

import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Rooms;

import java.util.List;

/**
 * Created by Reza.nejati on 1/28/2018.
 */

public class RquestHF {
    public final String EchoToken;
    public final Identity identity;
    public final String CheckinString;
    public final String CheckoutString;
    public final String Depart;
    public final List<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Rooms> Rooms;
    public final String RoomsString;
    public final String Culture;
    public final String Source;

    public RquestHF(String echoToken, Identity identity, String checkinString, String checkoutString, String depart, List<com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Rooms> rooms, String roomsString, String culture, String source) {
        EchoToken = echoToken;
        this.identity = identity;
        CheckinString = checkinString;
        CheckoutString = checkoutString;
        Depart = depart;
        Rooms = rooms;
        RoomsString = roomsString;
        Culture = culture;
        Source = source;
    }
}
