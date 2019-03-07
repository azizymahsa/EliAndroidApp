
package com.eligasht.service.model.newModel.train.domesticSearch.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrainSearched {

    @SerializedName("DomesticTrainSourceText")
    @Expose
    private String domesticTrainSourceText;
    @SerializedName("DomesticTrainSource")
    @Expose
    private String domesticTrainSource;
    @SerializedName("DomesticTrainDestinationText")
    @Expose
    private String domesticTrainDestinationText;
    @SerializedName("DomesticTrainDestination")
    @Expose
    private String domesticTrainDestination;
    @SerializedName("Passengers")
    @Expose
    private Passengers passengers;
    @SerializedName("DomesticTrainTripType")
    @Expose
    private Integer domesticTrainTripType;
    @SerializedName("PreferredDomesticTrainline")
    @Expose
    private Object preferredDomesticTrainline;
    @SerializedName("PreferredClass")
    @Expose
    private Integer preferredClass;
    @SerializedName("DomesticTrainCheckin")
    @Expose
    private String domesticTrainCheckin;
    @SerializedName("DomesticTrainCheckout")
    @Expose
    private String domesticTrainCheckout;
    @SerializedName("PassengerType")
    @Expose
    private String passengerType;
    @SerializedName("Exclusive")
    @Expose
    private Boolean exclusive;

    public String getDomesticTrainSourceText() {
        return domesticTrainSourceText;
    }

    public void setDomesticTrainSourceText(String domesticTrainSourceText) {
        this.domesticTrainSourceText = domesticTrainSourceText;
    }

    public String getDomesticTrainSource() {
        return domesticTrainSource;
    }

    public void setDomesticTrainSource(String domesticTrainSource) {
        this.domesticTrainSource = domesticTrainSource;
    }

    public String getDomesticTrainDestinationText() {
        return domesticTrainDestinationText;
    }

    public void setDomesticTrainDestinationText(String domesticTrainDestinationText) {
        this.domesticTrainDestinationText = domesticTrainDestinationText;
    }

    public String getDomesticTrainDestination() {
        return domesticTrainDestination;
    }

    public void setDomesticTrainDestination(String domesticTrainDestination) {
        this.domesticTrainDestination = domesticTrainDestination;
    }

    public Passengers getPassengers() {
        return passengers;
    }

    public void setPassengers(Passengers passengers) {
        this.passengers = passengers;
    }

    public Integer getDomesticTrainTripType() {
        return domesticTrainTripType;
    }

    public void setDomesticTrainTripType(Integer domesticTrainTripType) {
        this.domesticTrainTripType = domesticTrainTripType;
    }

    public Object getPreferredDomesticTrainline() {
        return preferredDomesticTrainline;
    }

    public void setPreferredDomesticTrainline(Object preferredDomesticTrainline) {
        this.preferredDomesticTrainline = preferredDomesticTrainline;
    }

    public Integer getPreferredClass() {
        return preferredClass;
    }

    public void setPreferredClass(Integer preferredClass) {
        this.preferredClass = preferredClass;
    }

    public String getDomesticTrainCheckin() {
        return domesticTrainCheckin;
    }

    public void setDomesticTrainCheckin(String domesticTrainCheckin) {
        this.domesticTrainCheckin = domesticTrainCheckin;
    }

    public String getDomesticTrainCheckout() {
        return domesticTrainCheckout;
    }

    public void setDomesticTrainCheckout(String domesticTrainCheckout) {
        this.domesticTrainCheckout = domesticTrainCheckout;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public Boolean getExclusive() {
        return exclusive;
    }

    public void setExclusive(Boolean exclusive) {
        this.exclusive = exclusive;
    }

}
