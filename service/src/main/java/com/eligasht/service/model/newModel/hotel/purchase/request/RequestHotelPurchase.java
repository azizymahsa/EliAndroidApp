
package com.eligasht.service.model.newModel.hotel.purchase.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestHotelPurchase {

    @SerializedName("passengers")
    @Expose
    private List<Passenger> passengers = null;
    @SerializedName("customers")
    @Expose
    private Customers customers;
    @SerializedName("hotelOfferId")
    @Expose
    private String hotelOfferId;
    @SerializedName("flightOfferId")
    @Expose
    private String flightOfferId;
    @SerializedName("preSearchUniqueId")
    @Expose
    private String preSearchUniqueId;

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public String getHotelOfferId() {
        return hotelOfferId;
    }

    public void setHotelOfferId(String hotelOfferId) {
        this.hotelOfferId = hotelOfferId;
    }

    public String getFlightOfferId() {
        return flightOfferId;
    }

    public void setFlightOfferId(String flightOfferId) {
        this.flightOfferId = flightOfferId;
    }

    public String getPreSearchUniqueId() {
        return preSearchUniqueId;
    }

    public void setPreSearchUniqueId(String preSearchUniqueId) {
        this.preSearchUniqueId = preSearchUniqueId;
    }

}
