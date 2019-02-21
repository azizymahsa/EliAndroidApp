
package com.eligasht.service.model.newModel.xpackage.PurchasePackage.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPurchasePackage {

    @SerializedName("Passengers")
    @Expose
    private List<Passenger> passengers = null;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;
    @SerializedName("Customers")
    @Expose
    private Customers customers;
    @SerializedName("Rooms")
    @Expose
    private String rooms;

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

}
