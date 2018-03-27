
package com.eligasht.service.model.hotel.hotelAvail.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
    public String toString() {
        return new ToStringBuilder(this).append("request", request).toString();
    }

}
