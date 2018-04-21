
package com.eligasht.service.model.hotelflight.search.response;

import com.eligasht.service.model.BaseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class HotelFlightSearchResult extends BaseModel {

    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("HotelSearchResult")
    @Expose
    private HotelSearchResult hotelSearchResult;
    @SerializedName("ResultUniqID")
    @Expose
    private String resultUniqID;

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

    public HotelSearchResult getHotelSearchResult() {
        return hotelSearchResult;
    }

    public void setHotelSearchResult(HotelSearchResult hotelSearchResult) {
        this.hotelSearchResult = hotelSearchResult;
    }

    public String getResultUniqID() {
        return resultUniqID;
    }

    public void setResultUniqID(String resultUniqID) {
        this.resultUniqID = resultUniqID;
    }

}
