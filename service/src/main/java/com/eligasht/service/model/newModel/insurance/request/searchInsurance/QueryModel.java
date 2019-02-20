package com.eligasht.service.model.newModel.insurance.request.searchInsurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryModel {
    @SerializedName("BirthDay")
    @Expose
    private String birthDay;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("CurrentCulture")
    @Expose
    private String currentCulture;
    @SerializedName("DestinationText")
    @Expose
    private String destinationText;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("ExclusiveTrain")
    @Expose
    private Integer exclusiveTrain;
    @SerializedName("IsSearchRequest")
    @Expose
    private Integer isSearchRequest;
    @SerializedName("PreferredClass")
    @Expose
    private String preferredClass;
    @SerializedName("TravelDuration")
    @Expose
    private String travelDuration;
    @SerializedName("Trip")
    @Expose
    private String trip;

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrentCulture() {
        return currentCulture;
    }

    public void setCurrentCulture(String currentCulture) {
        this.currentCulture = currentCulture;
    }

    public String getDestinationText() {
        return destinationText;
    }

    public void setDestinationText(String destinationText) {
        this.destinationText = destinationText;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getExclusiveTrain() {
        return exclusiveTrain;
    }

    public void setExclusiveTrain(Integer exclusiveTrain) {
        this.exclusiveTrain = exclusiveTrain;
    }

    public Integer getIsSearchRequest() {
        return isSearchRequest;
    }

    public void setIsSearchRequest(Integer isSearchRequest) {
        this.isSearchRequest = isSearchRequest;
    }

    public String getPreferredClass() {
        return preferredClass;
    }

    public void setPreferredClass(String preferredClass) {
        this.preferredClass = preferredClass;
    }

    public String getTravelDuration() {
        return travelDuration;
    }

    public void setTravelDuration(String travelDuration) {
        this.travelDuration = travelDuration;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

}
