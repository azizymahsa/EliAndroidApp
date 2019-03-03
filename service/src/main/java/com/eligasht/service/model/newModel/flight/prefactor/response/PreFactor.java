
package com.eligasht.service.model.newModel.flight.prefactor.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreFactor {

    @SerializedName("RqBaseCategory")
    @Expose
    private Integer rqBaseCategory;
    @SerializedName("Summary")
    @Expose
    private Summary summary;
    @SerializedName("Customer")
    @Expose
    private List<Customer> customer = null;
    @SerializedName("Passengers")
    @Expose
    private List<Passenger> passengers = null;
    @SerializedName("Flights")
    @Expose
    private List<Flight> flights = null;
    @SerializedName("Hotels")
    @Expose
    private List<Hotel> hotels = null;
    @SerializedName("Services")
    @Expose
    private List<Service> services = null;
    @SerializedName("PaymentDetail")
    @Expose
    private List<Object> paymentDetail = null;
    @SerializedName("FinCntDetails")
    @Expose
    private Object finCntDetails;
    @SerializedName("PreFactorLogs")
    @Expose
    private List<Object> preFactorLogs = null;

    public Integer getRqBaseCategory() {
        return rqBaseCategory;
    }

    public void setRqBaseCategory(Integer rqBaseCategory) {
        this.rqBaseCategory = rqBaseCategory;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Object> getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(List<Object> paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    public Object getFinCntDetails() {
        return finCntDetails;
    }

    public void setFinCntDetails(Object finCntDetails) {
        this.finCntDetails = finCntDetails;
    }

    public List<Object> getPreFactorLogs() {
        return preFactorLogs;
    }

    public void setPreFactorLogs(List<Object> preFactorLogs) {
        this.preFactorLogs = preFactorLogs;
    }

}
