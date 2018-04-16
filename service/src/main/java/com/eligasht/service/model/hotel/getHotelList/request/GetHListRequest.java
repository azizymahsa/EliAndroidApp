
package com.eligasht.service.model.hotel.getHotelList.request;

import com.eligasht.service.model.identity.Identity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHListRequest {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("identity")
    @Expose
    private Identity identity;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
