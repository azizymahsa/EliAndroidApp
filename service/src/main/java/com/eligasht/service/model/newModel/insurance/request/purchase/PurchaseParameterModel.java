package com.eligasht.service.model.newModel.insurance.request.purchase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PurchaseParameterModel {

    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("PassengersList")
    @Expose
    private List<Passenger> passengersList = null;
    @SerializedName("SelectedInsuranseId")
    @Expose
    private String selectedInsuranseId;
    @SerializedName("CustomerInfo")
    @Expose
    private Customer customerInfo;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public List<Passenger> getPassengersList() {
        return passengersList;
    }

    public void setPassengersList(List<Passenger> passengersList) {
        this.passengersList = passengersList;
    }

    public String getSelectedInsuranseId() {
        return selectedInsuranseId;
    }

    public void setSelectedInsuranseId(String selectedInsuranseId) {
        this.selectedInsuranseId = selectedInsuranseId;
    }

    public Customer getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(Customer customerInfo) {
        this.customerInfo = customerInfo;
    }

}