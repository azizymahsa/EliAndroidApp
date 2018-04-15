
package com.eligasht.service.model.loadflight.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadFlightSubRequest {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("FlightId")
    @Expose
    private String flightId;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
