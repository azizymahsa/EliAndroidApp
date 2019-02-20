
package com.eligasht.reservation.models.model.pack.response.responseSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstAvailableDate {

    @SerializedName("DepartDate")
    @Expose
    private String departDate;
    @SerializedName("AirlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("IsSelected")
    @Expose
    private Boolean isSelected;
    @SerializedName("PackTitle")
    @Expose
    private String packTitle;
    @SerializedName("PackID")
    @Expose
    private String packID;

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getPackTitle() {
        return packTitle;
    }

    public void setPackTitle(String packTitle) {
        this.packTitle = packTitle;
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(String packID) {
        this.packID = packID;
    }

}
