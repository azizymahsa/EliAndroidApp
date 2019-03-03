
package com.eligasht.service.model.newModel.flight.purchaseServices.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdditionalServicesObject {

    @SerializedName("PreFactorNo")
    @Expose
    private String preFactorNo;
    @SerializedName("SelectedServices")
    @Expose
    private List<SelectedService> selectedServices = null;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;

    public String getPreFactorNo() {
        return preFactorNo;
    }

    public void setPreFactorNo(String preFactorNo) {
        this.preFactorNo = preFactorNo;
    }

    public List<SelectedService> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(List<SelectedService> selectedServices) {
        this.selectedServices = selectedServices;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

}
