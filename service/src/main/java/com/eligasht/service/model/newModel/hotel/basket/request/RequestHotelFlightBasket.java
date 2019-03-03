
package com.eligasht.service.model.newModel.hotel.basket.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestHotelFlightBasket {

    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("SelectedEId")
    @Expose
    private String selectedEId;
    @SerializedName("SelectedFlightGuid")
    @Expose
    private String selectedFlightGuid;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSelectedEId() {
        return selectedEId;
    }

    public void setSelectedEId(String selectedEId) {
        this.selectedEId = selectedEId;
    }

    public String getSelectedFlightGuid() {
        return selectedFlightGuid;
    }

    public void setSelectedFlightGuid(String selectedFlightGuid) {
        this.selectedFlightGuid = selectedFlightGuid;
    }

}
