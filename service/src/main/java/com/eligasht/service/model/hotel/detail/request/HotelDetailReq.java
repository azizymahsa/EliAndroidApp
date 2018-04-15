
package com.eligasht.service.model.hotel.detail.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelDetailReq {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("EHotelId")
    @Expose
    private String eHotelId;

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

}
