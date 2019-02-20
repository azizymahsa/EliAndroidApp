
package com.eligasht.reservation.models.model.pack.response.responseSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstHotelAmenity {

    @SerializedName("AmenityID")
    @Expose
    private Integer amenityID;
    @SerializedName("AmenityName")
    @Expose
    private String amenityName;
    @SerializedName("AmenityNameFa")
    @Expose
    private String amenityNameFa;
    @SerializedName("AmenityIcon")
    @Expose
    private String amenityIcon;

    public Integer getAmenityID() {
        return amenityID;
    }

    public void setAmenityID(Integer amenityID) {
        this.amenityID = amenityID;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

    public String getAmenityNameFa() {
        return amenityNameFa;
    }

    public void setAmenityNameFa(String amenityNameFa) {
        this.amenityNameFa = amenityNameFa;
    }

    public String getAmenityIcon() {
        return amenityIcon;
    }

    public void setAmenityIcon(String amenityIcon) {
        this.amenityIcon = amenityIcon;
    }

}
