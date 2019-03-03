
package com.eligasht.service.model.newModel.hotel.reserve.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseReserveFlightHotel {

    @SerializedName("OfferId")
    @Expose
    private String offerId;
    @SerializedName("PreSearchUniqueId")
    @Expose
    private String preSearchUniqueId;
    @SerializedName("FlightGuid")
    @Expose
    private String flightGuid;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getPreSearchUniqueId() {
        return preSearchUniqueId;
    }

    public void setPreSearchUniqueId(String preSearchUniqueId) {
        this.preSearchUniqueId = preSearchUniqueId;
    }

    public String getFlightGuid() {
        return flightGuid;
    }

    public void setFlightGuid(String flightGuid) {
        this.flightGuid = flightGuid;
    }

}
