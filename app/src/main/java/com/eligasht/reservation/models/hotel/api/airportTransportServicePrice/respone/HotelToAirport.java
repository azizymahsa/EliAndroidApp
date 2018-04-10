
package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HotelToAirport {

    @SerializedName("AdultTotalFare")
    @Expose
    private AdultTotalFare_ adultTotalFare;
    @SerializedName("AdultTotalFarePrice")
    @Expose
    private Integer adultTotalFarePrice;
    @SerializedName("ChildTotalFare")
    @Expose
    private ChildTotalFare_ childTotalFare;
    @SerializedName("ChildTotalFarePrice")
    @Expose
    private Integer childTotalFarePrice;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("DropOff")
    @Expose
    private DropOff__ dropOff;
    @SerializedName("Duration")
    @Expose
    private Object duration;
    @SerializedName("FromTo")
    @Expose
    private String fromTo;
    @SerializedName("InfantTotalFare")
    @Expose
    private InfantTotalFare_ infantTotalFare;
    @SerializedName("InfantTotalFarePrice")
    @Expose
    private Integer infantTotalFarePrice;
    @SerializedName("Links")
    @Expose
    private Object links;
    @SerializedName("OfferCode")
    @Expose
    private OfferCode_ offerCode;
    @SerializedName("PassengerLanguage")
    @Expose
    private Object passengerLanguage;
    @SerializedName("PickUp")
    @Expose
    private PickUp___ pickUp;
    @SerializedName("ProviderCompany")
    @Expose
    private String providerCompany;
    @SerializedName("ServiceLanguages")
    @Expose
    private Object serviceLanguages;
    @SerializedName("ServiceType")
    @Expose
    private String serviceType;
    @SerializedName("SightseeingName")
    @Expose
    private Object sightseeingName;
    @SerializedName("Supplier")
    @Expose
    private String supplier;
    @SerializedName("TotalFare")
    @Expose
    private TotalFare_ totalFare;
    @SerializedName("TotalFarePrice")
    @Expose
    private Integer totalFarePrice;

    public AdultTotalFare_ getAdultTotalFare() {
        return adultTotalFare;
    }

    public void setAdultTotalFare(AdultTotalFare_ adultTotalFare) {
        this.adultTotalFare = adultTotalFare;
    }

    public Integer getAdultTotalFarePrice() {
        return adultTotalFarePrice;
    }

    public void setAdultTotalFarePrice(Integer adultTotalFarePrice) {
        this.adultTotalFarePrice = adultTotalFarePrice;
    }

    public ChildTotalFare_ getChildTotalFare() {
        return childTotalFare;
    }

    public void setChildTotalFare(ChildTotalFare_ childTotalFare) {
        this.childTotalFare = childTotalFare;
    }

    public Integer getChildTotalFarePrice() {
        return childTotalFarePrice;
    }

    public void setChildTotalFarePrice(Integer childTotalFarePrice) {
        this.childTotalFarePrice = childTotalFarePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DropOff__ getDropOff() {
        return dropOff;
    }

    public void setDropOff(DropOff__ dropOff) {
        this.dropOff = dropOff;
    }

    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }

    public String getFromTo() {
        return fromTo;
    }

    public void setFromTo(String fromTo) {
        this.fromTo = fromTo;
    }

    public InfantTotalFare_ getInfantTotalFare() {
        return infantTotalFare;
    }

    public void setInfantTotalFare(InfantTotalFare_ infantTotalFare) {
        this.infantTotalFare = infantTotalFare;
    }

    public Integer getInfantTotalFarePrice() {
        return infantTotalFarePrice;
    }

    public void setInfantTotalFarePrice(Integer infantTotalFarePrice) {
        this.infantTotalFarePrice = infantTotalFarePrice;
    }

    public Object getLinks() {
        return links;
    }

    public void setLinks(Object links) {
        this.links = links;
    }

    public OfferCode_ getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(OfferCode_ offerCode) {
        this.offerCode = offerCode;
    }

    public Object getPassengerLanguage() {
        return passengerLanguage;
    }

    public void setPassengerLanguage(Object passengerLanguage) {
        this.passengerLanguage = passengerLanguage;
    }

    public PickUp___ getPickUp() {
        return pickUp;
    }

    public void setPickUp(PickUp___ pickUp) {
        this.pickUp = pickUp;
    }

    public String getProviderCompany() {
        return providerCompany;
    }

    public void setProviderCompany(String providerCompany) {
        this.providerCompany = providerCompany;
    }

    public Object getServiceLanguages() {
        return serviceLanguages;
    }

    public void setServiceLanguages(Object serviceLanguages) {
        this.serviceLanguages = serviceLanguages;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Object getSightseeingName() {
        return sightseeingName;
    }

    public void setSightseeingName(Object sightseeingName) {
        this.sightseeingName = sightseeingName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public TotalFare_ getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(TotalFare_ totalFare) {
        this.totalFare = totalFare;
    }

    public Integer getTotalFarePrice() {
        return totalFarePrice;
    }

    public void setTotalFarePrice(Integer totalFarePrice) {
        this.totalFarePrice = totalFarePrice;
    }



}
