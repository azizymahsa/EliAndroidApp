
package com.eligasht.service.model.hotel.getHotelList.response;

import java.util.List;

import com.eligasht.service.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHotelListResult extends BaseModel {

    @SerializedName("Comments")
    @Expose
    private Object comments;

    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("Cities")
    @Expose
    private List<City> cities = null;

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }


    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

    public Object getWarningss() {
        return warningss;
    }

    public void setWarningss(Object warningss) {
        this.warningss = warningss;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

}
