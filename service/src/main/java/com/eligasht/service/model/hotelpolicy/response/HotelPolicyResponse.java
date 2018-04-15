
package com.eligasht.service.model.hotelpolicy.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelPolicyResponse {

    @SerializedName("GetHotelPolicyResult")
    @Expose
    private GetHotelPolicyResult getHotelPolicyResult;

    public GetHotelPolicyResult getGetHotelPolicyResult() {
        return getHotelPolicyResult;
    }

    public void setGetHotelPolicyResult(GetHotelPolicyResult getHotelPolicyResult) {
        this.getHotelPolicyResult = getHotelPolicyResult;
    }

}
