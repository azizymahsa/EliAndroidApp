
package com.eligasht.service.model.XPackage.response.GetPreFactorDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreFactor {

    @SerializedName("FactorSummary")
    @Expose
    private FactorSummary factorSummary;
    @SerializedName("FinCntDetails")
    @Expose
    private Object finCntDetails;
    @SerializedName("PreFactorBookingLogs")
    @Expose
    private List<Object> preFactorBookingLogs = null;
    @SerializedName("PreFactorFlights")
    @Expose
    private List<PreFactorFlight> preFactorFlights = null;
    @SerializedName("PreFactorHotels")
    @Expose
    private List<PreFactorHotel> preFactorHotels = null;
    @SerializedName("PreFactorServices")
    @Expose
    private List<PreFactorService> preFactorServices = null;
    @SerializedName("RequestPartner")
    @Expose
    private List<RequestPartner> requestPartner = null;
    @SerializedName("RequestPassenger")
    @Expose
    private List<RequestPassenger> requestPassenger = null;
    @SerializedName("RequestPayment")
    @Expose
    private List<Object> requestPayment = null;
    @SerializedName("RqBaseCategory")
    @Expose
    private Integer rqBaseCategory;

    public FactorSummary getFactorSummary() {
        return factorSummary;
    }

    public void setFactorSummary(FactorSummary factorSummary) {
        this.factorSummary = factorSummary;
    }

    public Object getFinCntDetails() {
        return finCntDetails;
    }

    public void setFinCntDetails(Object finCntDetails) {
        this.finCntDetails = finCntDetails;
    }

    public List<Object> getPreFactorBookingLogs() {
        return preFactorBookingLogs;
    }

    public void setPreFactorBookingLogs(List<Object> preFactorBookingLogs) {
        this.preFactorBookingLogs = preFactorBookingLogs;
    }

    public List<PreFactorFlight> getPreFactorFlights() {
        return preFactorFlights;
    }

    public void setPreFactorFlights(List<PreFactorFlight> preFactorFlights) {
        this.preFactorFlights = preFactorFlights;
    }

    public List<PreFactorHotel> getPreFactorHotels() {
        return preFactorHotels;
    }

    public void setPreFactorHotels(List<PreFactorHotel> preFactorHotels) {
        this.preFactorHotels = preFactorHotels;
    }

    public List<PreFactorService> getPreFactorServices() {
        return preFactorServices;
    }

    public void setPreFactorServices(List<PreFactorService> preFactorServices) {
        this.preFactorServices = preFactorServices;
    }

    public List<RequestPartner> getRequestPartner() {
        return requestPartner;
    }

    public void setRequestPartner(List<RequestPartner> requestPartner) {
        this.requestPartner = requestPartner;
    }

    public List<RequestPassenger> getRequestPassenger() {
        return requestPassenger;
    }

    public void setRequestPassenger(List<RequestPassenger> requestPassenger) {
        this.requestPassenger = requestPassenger;
    }

    public List<Object> getRequestPayment() {
        return requestPayment;
    }

    public void setRequestPayment(List<Object> requestPayment) {
        this.requestPayment = requestPayment;
    }

    public Integer getRqBaseCategory() {
        return rqBaseCategory;
    }

    public void setRqBaseCategory(Integer rqBaseCategory) {
        this.rqBaseCategory = rqBaseCategory;
    }

}
