
package com.eligasht.service.model.newModel.flight.purchaseServices.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestGetPurchaseServices {

    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("PreFactorNo")
    @Expose
    private String preFactorNo;
    @SerializedName("SelectedServices")
    @Expose
    private List<SelectedService> selectedServices = null;
    @SerializedName("SelectedInsuranceServices")
    @Expose
    private Object selectedInsuranceServices;
    @SerializedName("SelectedTransferServices")
    @Expose
    private Object selectedTransferServices;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

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

    public Object getSelectedInsuranceServices() {
        return selectedInsuranceServices;
    }

    public void setSelectedInsuranceServices(Object selectedInsuranceServices) {
        this.selectedInsuranceServices = selectedInsuranceServices;
    }

    public Object getSelectedTransferServices() {
        return selectedTransferServices;
    }

    public void setSelectedTransferServices(Object selectedTransferServices) {
        this.selectedTransferServices = selectedTransferServices;
    }

}
