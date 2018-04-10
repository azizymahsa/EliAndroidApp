
package com.eligasht.service.model.hotel.hotelAvail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class HotelAvailResult {

    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
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

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
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

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}

    @Override
    public String toString() {
        return "HotelAvailResult{" +
                "comments=" + comments +
                ", errors=" + errors +
                ", resultKey=" + resultKey +
                ", warningss=" + warningss +
                ", hotelSearchResult=" + hotelSearchResult +
                ", resultUniqID='" + resultUniqID + '\'' +
                '}';
    }
}
