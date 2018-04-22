
package com.eligasht.service.model.flight.response.PreFactorDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePreFactorDetails {

    @SerializedName("GetPreFactorDetailsResult")
    @Expose
    public GetPreFactorDetailsResult getPreFactorDetailsResult;

    public GetPreFactorDetailsResult getGetPreFactorDetailsResult() {
        return getPreFactorDetailsResult;
    }

    public void setGetPreFactorDetailsResult(GetPreFactorDetailsResult getPreFactorDetailsResult) {
        this.getPreFactorDetailsResult = getPreFactorDetailsResult;
    }

}
