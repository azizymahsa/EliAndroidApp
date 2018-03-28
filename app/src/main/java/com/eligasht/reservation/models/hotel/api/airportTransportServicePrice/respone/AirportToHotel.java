
package com.eligasht.reservation.models.hotel.api.airportTransportServicePrice.respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AirportToHotel {

    @SerializedName("AdultTotalFare")
    @Expose
    private AdultTotalFare adultTotalFare;
    @SerializedName("AdultTotalFarePrice")
    @Expose
    private Integer adultTotalFarePrice;
    @SerializedName("ChildTotalFare")
    @Expose
    private ChildTotalFare childTotalFare;
    @SerializedName("ChildTotalFarePrice")
    @Expose
    private Integer childTotalFarePrice;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("DropOff")
    @Expose
    private DropOff dropOff;
    @SerializedName("Duration")
    @Expose
    private Object duration;
    @SerializedName("FromTo")
    @Expose
    private String fromTo;
    @SerializedName("InfantTotalFare")
    @Expose
    private InfantTotalFare infantTotalFare;
    @SerializedName("InfantTotalFarePrice")
    @Expose
    private Integer infantTotalFarePrice;
    @SerializedName("Links")
    @Expose
    private Object links;
    @SerializedName("OfferCode")
    @Expose
    private OfferCode offerCode;
    @SerializedName("PassengerLanguage")
    @Expose
    private Object passengerLanguage;
    @SerializedName("PickUp")
    @Expose
    private PickUp_ pickUp;
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
    private TotalFare totalFare;
    @SerializedName("TotalFarePrice")
    @Expose
    private Integer totalFarePrice;

    public AdultTotalFare getAdultTotalFare() {
        return adultTotalFare;
    }

    public void setAdultTotalFare(AdultTotalFare adultTotalFare) {
        this.adultTotalFare = adultTotalFare;
    }

    public Integer getAdultTotalFarePrice() {
        return adultTotalFarePrice;
    }

    public void setAdultTotalFarePrice(Integer adultTotalFarePrice) {
        this.adultTotalFarePrice = adultTotalFarePrice;
    }

    public ChildTotalFare getChildTotalFare() {
        return childTotalFare;
    }

    public void setChildTotalFare(ChildTotalFare childTotalFare) {
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

    public DropOff getDropOff() {
        return dropOff;
    }

    public void setDropOff(DropOff dropOff) {
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

    public InfantTotalFare getInfantTotalFare() {
        return infantTotalFare;
    }

    public void setInfantTotalFare(InfantTotalFare infantTotalFare) {
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

    public OfferCode getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(OfferCode offerCode) {
        this.offerCode = offerCode;
    }

    public Object getPassengerLanguage() {
        return passengerLanguage;
    }

    public void setPassengerLanguage(Object passengerLanguage) {
        this.passengerLanguage = passengerLanguage;
    }

    public PickUp_ getPickUp() {
        return pickUp;
    }

    public void setPickUp(PickUp_ pickUp) {
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

    public TotalFare getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(TotalFare totalFare) {
        this.totalFare = totalFare;
    }

    public Integer getTotalFarePrice() {
        return totalFarePrice;
    }

    public void setTotalFarePrice(Integer totalFarePrice) {
        this.totalFarePrice = totalFarePrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adultTotalFare", adultTotalFare).append("adultTotalFarePrice", adultTotalFarePrice).append("childTotalFare", childTotalFare).append("childTotalFarePrice", childTotalFarePrice).append("description", description).append("dropOff", dropOff).append("duration", duration).append("fromTo", fromTo).append("infantTotalFare", infantTotalFare).append("infantTotalFarePrice", infantTotalFarePrice).append("links", links).append("offerCode", offerCode).append("passengerLanguage", passengerLanguage).append("pickUp", pickUp).append("providerCompany", providerCompany).append("serviceLanguages", serviceLanguages).append("serviceType", serviceType).append("sightseeingName", sightseeingName).append("supplier", supplier).append("totalFare", totalFare).append("totalFarePrice", totalFarePrice).toString();
    }

}
