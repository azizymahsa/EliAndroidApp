
package com.eligasht.service.model.newModel.hotel.reserve.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestReserveFlightHotel {

    @SerializedName("HotelOfferId")
    @Expose
    private String hotelOfferId;
    @SerializedName("FlightOfferId")
    @Expose
    private String flightOfferId;
    @SerializedName("HotelId")
    @Expose
    private String hotelId;
    @SerializedName("PreSearchUniqueId")
    @Expose
    private String preSearchUniqueId;
    @SerializedName("FlightGuid")
    @Expose
    private String flightGuid;

    public String getHotelOfferId() {
        return hotelOfferId;
    }

    public void setHotelOfferId(String hotelOfferId) {
        this.hotelOfferId = hotelOfferId;
    }

    public String getFlightOfferId() {
        return flightOfferId;
    }

    public void setFlightOfferId(String flightOfferId) {
        this.flightOfferId = flightOfferId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
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
