
package com.eligasht.service.model.hotel.hotelAvail.request;

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
    private String culture="fa-IR";
    @SerializedName("Depart")
    @Expose
    private String depart="DXB";
    @SerializedName("EDepart")
    @Expose
    private String eDepart;
    @SerializedName("EchoToken")
    @Expose
    private String echoToken="H";
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

    public String getEDepart() {
        return eDepart;
    }

    public void setEDepart(String eDepart) {
        this.eDepart = eDepart;
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

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}


    @Override
    public String toString() {
        return "Request{" +
                "checkinString='" + checkinString + '\'' +
                ", checkoutString='" + checkoutString + '\'' +
                ", culture='" + culture + '\'' +
                ", depart='" + depart + '\'' +
                ", eDepart='" + eDepart + '\'' +
                ", echoToken='" + echoToken + '\'' +
                ", rooms=" + rooms +
                ", roomsString='" + roomsString + '\'' +
                ", source='" + source + '\'' +
                ", identity=" + identity +
                '}';
    }
}
