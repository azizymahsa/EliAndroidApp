
package com.eligasht.service.model.newModel.hotel.policy.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestHotelPolicy {

    @SerializedName("eHotelId")
    @Expose
    private String eHotelId;
    @SerializedName("offerId")
    @Expose
    private String offerId;
    @SerializedName("searchKey")
    @Expose
    private String searchKey;
    @SerializedName("translteToPersian")
    @Expose
    private Boolean translteToPersian;
    @SerializedName("cultureName")
    @Expose
    private String cultureName;

    public String getEHotelId() {
        return eHotelId;
    }

    public void setEHotelId(String eHotelId) {
        this.eHotelId = eHotelId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Boolean getTranslteToPersian() {
        return translteToPersian;
    }

    public void setTranslteToPersian(Boolean translteToPersian) {
        this.translteToPersian = translteToPersian;
    }

    public String getCultureName() {
        return cultureName;
    }

    public void setCultureName(String cultureName) {
        this.cultureName = cultureName;
    }

}
