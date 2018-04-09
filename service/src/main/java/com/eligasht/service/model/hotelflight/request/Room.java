
package com.eligasht.service.model.hotelflight.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("AdultCount")
    @Expose
    private Integer adultCount;
    @SerializedName("ChildCount")
    @Expose
    private Integer childCount;

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

}
