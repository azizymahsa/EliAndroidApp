
package com.eligasht.service.model.newModel.train.purchase.request;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestTrainPurchase {

    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("SelectedTrainID")
    @Expose
    private String selectedTrainID;
    @SerializedName("SelectedTrainSegmentIDs")
    @Expose
    private List<String> selectedTrainSegmentIDs = null;
    @SerializedName("Customer")
    @Expose
    private Customer customer;
    @SerializedName("Passengers")
    @Expose
    private List<Passenger> passengers = null;
    @SerializedName("PurchaseIdentity")
    @Expose
    private String purchaseIdentity;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSelectedTrainID() {
        return selectedTrainID;
    }

    public void setSelectedTrainID(String selectedTrainID) {
        this.selectedTrainID = selectedTrainID;
    }

    public List<String> getSelectedTrainSegmentIDs() {
        return selectedTrainSegmentIDs;
    }

    public void setSelectedTrainSegmentIDs(List<String> selectedTrainSegmentIDs) {
        this.selectedTrainSegmentIDs = selectedTrainSegmentIDs;
    }
    public void setSelectedTrainSegmentIDs(String segmengt_id_false, String segmengt_id_true) {
        selectedTrainSegmentIDs=new ArrayList<>();
        selectedTrainSegmentIDs.add(segmengt_id_false);
        selectedTrainSegmentIDs.add(segmengt_id_true);
    }
    public void setSelectedTrainSegmentIDs( String segmengt_id_true) {
        selectedTrainSegmentIDs=new ArrayList<>();

        selectedTrainSegmentIDs.add(segmengt_id_true);
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public String getPurchaseIdentity() {
        return purchaseIdentity;
    }

    public void setPurchaseIdentity(String purchaseIdentity) {
        this.purchaseIdentity = purchaseIdentity;
    }

}
