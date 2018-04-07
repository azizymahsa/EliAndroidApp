
package com.eligasht.service.model.flight.request.PurchaseFlight;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("EchoToken")
    @Expose
    private String echoToken;
    @SerializedName("BookingReferenceID")
    @Expose
    private String bookingReferenceID;
    @SerializedName("passList")
    @Expose
    private List<PassList> passList = null;
    @SerializedName("partnerInfo")
    @Expose
    private PartnerInfo partnerInfo;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public String getEchoToken() {
        return echoToken;
    }

    public void setEchoToken(String echoToken) {
        this.echoToken = echoToken;
    }

    public String getBookingReferenceID() {
        return bookingReferenceID;
    }

    public void setBookingReferenceID(String bookingReferenceID) {
        this.bookingReferenceID = bookingReferenceID;
    }

    public List<PassList> getPassList() {
        return passList;
    }

    public void setPassList(List<PassList> passList) {
        this.passList = passList;
    }

    public PartnerInfo getPartnerInfo() {
        return partnerInfo;
    }

    public void setPartnerInfo(PartnerInfo partnerInfo) {
        this.partnerInfo = partnerInfo;
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

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
