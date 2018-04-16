
package com.eligasht.service.model.XPackage.request.searchXPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("DepartureFrom")
    @Expose
    private String departureFrom;
    @SerializedName("DepartureTo")
    @Expose
    private String departureTo;
    @SerializedName("RoomList")
    @Expose
    private String roomList;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public String getDepartureTo() {
        return departureTo;
    }

    public void setDepartureTo(String departureTo) {
        this.departureTo = departureTo;
    }

    public String getRoomList() {
        return roomList;
    }

    public void setRoomList(String roomList) {
        this.roomList = roomList;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
