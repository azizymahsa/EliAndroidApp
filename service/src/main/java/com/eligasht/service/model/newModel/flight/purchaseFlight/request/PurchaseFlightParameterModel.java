
package com.eligasht.service.model.newModel.flight.purchaseFlight.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchaseFlightParameterModel {

    @SerializedName("PartnerList")
    @Expose
    private PartnerList partnerList;
    @SerializedName("PassList")
    @Expose
    private List<PassList> passList = null;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("FlightGuid")
    @Expose
    private String flightGuid;

    public PartnerList getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(PartnerList partnerList) {
        this.partnerList = partnerList;
    }

    public List<PassList> getPassList() {
        return passList;
    }

    public void setPassList(List<PassList> passList) {
        this.passList = passList;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getFlightGuid() {
        return flightGuid;
    }

    public void setFlightGuid(String flightGuid) {
        this.flightGuid = flightGuid;
    }

}
