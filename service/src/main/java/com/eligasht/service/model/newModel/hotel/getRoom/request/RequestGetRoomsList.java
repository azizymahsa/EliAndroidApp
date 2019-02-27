
package com.eligasht.service.model.newModel.hotel.getRoom.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestGetRoomsList {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("HotelId")
    @Expose
    private String hotelId;
    @SerializedName("PreSearchUniqueId")
    @Expose
    private String preSearchUniqueId;

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
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

}
