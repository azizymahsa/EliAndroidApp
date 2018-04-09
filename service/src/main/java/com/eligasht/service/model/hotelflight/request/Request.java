
package com.eligasht.service.model.hotelflight.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("CheckinString")
    @Expose
    private String checkinString;
    @SerializedName("CheckoutString")
    @Expose
    private String checkoutString;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("Depart")
    @Expose
    private String depart;
    @SerializedName("EchoToken")
    @Expose
    private String echoToken;
    @SerializedName("Rooms")
    @Expose
    private List<Room> rooms = null;
    @SerializedName("RoomsString")
    @Expose
    private String roomsString;
    @SerializedName("Source")
    @Expose
    private String source;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public String getCheckinString() {
        return checkinString;
    }

    public void setCheckinString(String checkinString) {
        this.checkinString = checkinString;
    }

    public String getCheckoutString() {
        return checkoutString;
    }

    public void setCheckoutString(String checkoutString) {
        this.checkoutString = checkoutString;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getEchoToken() {
        return echoToken;
    }

    public void setEchoToken(String echoToken) {
        this.echoToken = echoToken;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getRoomsString() {
        return roomsString;
    }

    public void setRoomsString(String roomsString) {
        this.roomsString = roomsString;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
