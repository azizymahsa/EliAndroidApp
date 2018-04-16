
package com.eligasht.service.model.hotel.hold.request;

import com.eligasht.service.model.identity.Identity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HoldRoomReq {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("EHotelId")
    @Expose
    private String eHotelId;
    @SerializedName("OfferIds")
    @Expose
    private String offerIds;
    @SerializedName("ResultUniqID")
    @Expose
    private String resultUniqID;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getEHotelId() {
        return eHotelId;
    }

    public void setEHotelId(String eHotelId) {
        this.eHotelId = eHotelId;
    }

    public String getOfferIds() {
        return offerIds;
    }

    public void setOfferIds(String offerIds) {
        this.offerIds = offerIds;
    }

    public String getResultUniqID() {
        return resultUniqID;
    }

    public void setResultUniqID(String resultUniqID) {
        this.resultUniqID = resultUniqID;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
