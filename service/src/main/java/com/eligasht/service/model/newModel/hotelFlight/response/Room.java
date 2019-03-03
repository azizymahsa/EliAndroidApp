
package com.eligasht.service.model.newModel.hotelFlight.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("AdultCount")
    @Expose
    private Integer adultCount;
    @SerializedName("ChildAges")
    @Expose
    private List<Object> childAges = null;
    @SerializedName("ChildCount")
    @Expose
    private Integer childCount;
    @SerializedName("InfantCount")
    @Expose
    private Integer infantCount;

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public List<Object> getChildAges() {
        return childAges;
    }

    public void setChildAges(List<Object> childAges) {
        this.childAges = childAges;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public Integer getInfantCount() {
        return infantCount;
    }

    public void setInfantCount(Integer infantCount) {
        this.infantCount = infantCount;
    }

}
