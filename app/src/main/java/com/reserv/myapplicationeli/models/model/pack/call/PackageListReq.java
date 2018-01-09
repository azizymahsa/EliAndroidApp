package com.reserv.myapplicationeli.models.model.pack.call;


import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;

/**
 * Created by elham.bonyani on 1/7/2018.
 */

public class PackageListReq {

    private Identity identity;
    private String RoomList;
    private String DepartureFrom;
    private String DepartureTo;
    private String Country;
    private String Culture;



    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String getRoomList() {
        return RoomList;
    }

    public void setRoomList(String roomList) {
        RoomList = roomList;
    }

    public String getDepartureFrom() {
        return DepartureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        DepartureFrom = departureFrom;
    }

    public String getDepartureTo() {
        return DepartureTo;
    }

    public void setDepartureTo(String departureTo) {
        DepartureTo = departureTo;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCulture() {
        return Culture;
    }

    public void setCulture(String culture) {
        Culture = culture;
    }
}
