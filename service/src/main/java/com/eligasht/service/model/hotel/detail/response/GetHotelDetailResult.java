
package com.eligasht.service.model.hotel.detail.response;

import com.eligasht.service.model.BaseModel;
import com.eligasht.service.model.error.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class GetHotelDetailResult extends BaseModel {

    @SerializedName("Comments")
    @Expose
    private Object comments;

    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("HotelDetail")
    @Expose
    private HotelDetail hotelDetail;

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

    public HotelDetail getHotelDetail() {
        return hotelDetail;
    }

    public void setHotelDetail(HotelDetail hotelDetail) {
        this.hotelDetail = hotelDetail;
    }

}
