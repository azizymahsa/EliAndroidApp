
package com.eligasht.service.model.hotel.hotelAvail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HotelAvailRes {

    @SerializedName("HotelAvailResult")
    @Expose
    public HotelAvailResult hotelAvailResult;

    public HotelAvailResult getHotelAvailResult() {
        return hotelAvailResult;
    }

    public void setHotelAvailResult(HotelAvailResult hotelAvailResult) {
        this.hotelAvailResult = hotelAvailResult;
    }

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}


}
