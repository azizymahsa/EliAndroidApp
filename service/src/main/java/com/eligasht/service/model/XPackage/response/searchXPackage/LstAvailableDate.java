
package com.eligasht.service.model.XPackage.response.searchXPackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstAvailableDate {

    @SerializedName("AirlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("DepartDate")
    @Expose
    private String departDate;
    @SerializedName("DepartDateStr")
    @Expose
    private String departDateStr;
    @SerializedName("IsSelected")
    @Expose
    private Boolean isSelected;
    @SerializedName("PackID")
    @Expose
    private String packID;
    @SerializedName("PackTitle")
    @Expose
    private String packTitle;

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getDepartDateStr() {
        return departDateStr;
    }

    public void setDepartDateStr(String departDateStr) {
        this.departDateStr = departDateStr;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(String packID) {
        this.packID = packID;
    }

    public String getPackTitle() {
        return packTitle;
    }

    public void setPackTitle(String packTitle) {
        this.packTitle = packTitle;
    }

}
