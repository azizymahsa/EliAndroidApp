
package com.eligasht.service.model.hotelflight.purchase.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestHFPur {

    @SerializedName("PassList")
    @Expose
    private List<PassList> passList = null;
    @SerializedName("PartnerList")
    @Expose
    private PartnerList partnerList;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("HotelOfferId")
    @Expose
    private String hotelOfferId;
    @SerializedName("FlightGuID")
    @Expose
    private String flightGuID;
    @SerializedName("FlightOfferId")
    @Expose
    private String flightOfferId;
    @SerializedName("Checkin")
    @Expose
    private String checkin;
    @SerializedName("Checkout")
    @Expose
    private String checkout;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public List<PassList> getPassList() {
        return passList;
    }

    public void setPassList(List<PassList> passList) {
        this.passList = passList;
    }

    public PartnerList getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(PartnerList partnerList) {
        this.partnerList = partnerList;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHotelOfferId() {
        return hotelOfferId;
    }

    public void setHotelOfferId(String hotelOfferId) {
        this.hotelOfferId = hotelOfferId;
    }

    public String getFlightGuID() {
        return flightGuID;
    }

    public void setFlightGuID(String flightGuID) {
        this.flightGuID = flightGuID;
    }

    public String getFlightOfferId() {
        return flightOfferId;
    }

    public void setFlightOfferId(String flightOfferId) {
        this.flightOfferId = flightOfferId;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
