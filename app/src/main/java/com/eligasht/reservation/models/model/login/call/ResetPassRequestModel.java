
package com.eligasht.reservation.models.model.login.call;

import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPassRequestModel {
    public RequestChangePass request;

    public RequestChangePass getRequest() {
        return request;
    }

    public void setRequest(RequestChangePass request) {
        this.request = request;
    }


}
