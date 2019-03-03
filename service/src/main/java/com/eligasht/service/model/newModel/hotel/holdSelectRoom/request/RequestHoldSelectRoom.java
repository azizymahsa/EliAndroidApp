
package com.eligasht.service.model.newModel.hotel.holdSelectRoom.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestHoldSelectRoom {

    @SerializedName("EHotelId")
    @Expose
    private String eHotelId;
    @SerializedName("OfferIds")
    @Expose
    private String offerIds;
    @SerializedName("ResultUniqID")
    @Expose
    private String resultUniqID;
    @SerializedName("FlightGUID")
    @Expose
    private String flightGUID;

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

    public String getFlightGUID() {
        return flightGUID;
    }

    public void setFlightGUID(String flightGUID) {
        this.flightGUID = flightGUID;
    }

}
