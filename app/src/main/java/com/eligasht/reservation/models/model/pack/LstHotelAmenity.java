
package com.eligasht.reservation.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstHotelAmenity {

    @SerializedName("AmenityID")
    @Expose
    private Integer amenityID;
    @SerializedName("AmenityIcon")
    @Expose
    private String amenityIcon;
    @SerializedName("AmenityName")
    @Expose
    private String amenityName;
    @SerializedName("AmenityNameFa")
    @Expose
    private String amenityNameFa;

    public Integer getAmenityID() {
        return amenityID;
    }

    public void setAmenityID(Integer amenityID) {
        this.amenityID = amenityID;
    }

    public String getAmenityIcon() {
        return amenityIcon;
    }

    public void setAmenityIcon(String amenityIcon) {
        this.amenityIcon = amenityIcon;
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

}
