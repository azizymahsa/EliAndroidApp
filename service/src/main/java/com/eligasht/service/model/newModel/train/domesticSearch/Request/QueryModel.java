
package com.eligasht.service.model.newModel.train.domesticSearch.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryModel {

    @SerializedName("Trip")
    @Expose
    private String trip;
    @SerializedName("Infant")
    @Expose
    private String infant;
    @SerializedName("PType")
    @Expose
    private String pType;
    @SerializedName("ExclusiveTrain")
    @Expose
    private Boolean exclusiveTrain;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("Adult")
    @Expose
    private String adult;
    @SerializedName("CurrentCulture")
    @Expose
    private String currentCulture;
    @SerializedName("Child")
    @Expose
    private String child;

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getInfant() {
        return infant;
    }

    public void setInfant(String infant) {
        this.infant = infant;
    }

    public String getPType() {
        return pType;
    }

    public void setPType(String pType) {
        this.pType = pType;
    }

    public Boolean getExclusiveTrain() {
        return exclusiveTrain;
    }

    public void setExclusiveTrain(Boolean exclusiveTrain) {
        this.exclusiveTrain = exclusiveTrain;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getCurrentCulture() {
        return currentCulture;
    }

    public void setCurrentCulture(String currentCulture) {
        this.currentCulture = currentCulture;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

}
