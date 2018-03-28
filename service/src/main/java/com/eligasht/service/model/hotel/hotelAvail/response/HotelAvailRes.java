
package com.eligasht.service.model.hotel.hotelAvail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class HotelAvailRes {

    @SerializedName("HotelAvailResult")
    @Expose
    private HotelAvailResult hotelAvailResult;

    public HotelAvailResult getHotelAvailResult() {
        return hotelAvailResult;
    }

    public void setHotelAvailResult(HotelAvailResult hotelAvailResult) {
        this.hotelAvailResult = hotelAvailResult;
    }

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}
    public String toString() {
        return new ToStringBuilder(this).append("hotelAvailResult", hotelAvailResult).toString();
    }

}
