
package com.eligasht.service.model.newModel.hotel.search.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestHotelSearch {

    @SerializedName("PreSearchUniqueId")
    @Expose
    private String preSearchUniqueId;
    @SerializedName("CultureName")
    @Expose
    private String cultureName;
    @SerializedName("UserAgentObject")
    @Expose
    private UserAgentObject userAgentObject;
    @SerializedName("FirstPageResults")
    @Expose
    private Integer firstPageResults;

    public String getPreSearchUniqueId() {
        return preSearchUniqueId;
    }

    public void setPreSearchUniqueId(String preSearchUniqueId) {
        this.preSearchUniqueId = preSearchUniqueId;
    }

    public String getCultureName() {
        return cultureName;
    }

    public void setCultureName(String cultureName) {
        this.cultureName = cultureName;
    }

    public UserAgentObject getUserAgentObject() {
        return userAgentObject;
    }

    public void setUserAgentObject(UserAgentObject userAgentObject) {
        this.userAgentObject = userAgentObject;
    }

    public Integer getFirstPageResults() {
        return firstPageResults;
    }

    public void setFirstPageResults(Integer firstPageResults) {
        this.firstPageResults = firstPageResults;
    }

}
