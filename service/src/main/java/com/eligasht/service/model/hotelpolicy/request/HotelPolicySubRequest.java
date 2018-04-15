
package com.eligasht.service.model.hotelpolicy.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelPolicySubRequest {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("EHotelId")
    @Expose
    private String eHotelId;
    @SerializedName("OfferId")
    @Expose
    private String offerId;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("TranslteToPersian")
    @Expose
    private Boolean translteToPersian;
    @SerializedName("identity")
    @Expose
    private Identity identity;

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

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
