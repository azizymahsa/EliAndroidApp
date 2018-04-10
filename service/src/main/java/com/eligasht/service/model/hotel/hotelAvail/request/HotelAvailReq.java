
package com.eligasht.service.model.hotel.hotelAvail.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HotelAvailReq {

    @SerializedName("request")
    @Expose
    private Request request;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}


}
