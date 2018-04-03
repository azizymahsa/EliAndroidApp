
package com.eligasht.service.model.flight.response.DomesticFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDomesticFlight {

    @SerializedName("GetIsDomesticResult")
    @Expose
    private GetIsDomesticResult getIsDomesticResult;

    public GetIsDomesticResult getGetIsDomesticResult() {
        return getIsDomesticResult;
    }

    public void setGetIsDomesticResult(GetIsDomesticResult getIsDomesticResult) {
        this.getIsDomesticResult = getIsDomesticResult;
    }

}
