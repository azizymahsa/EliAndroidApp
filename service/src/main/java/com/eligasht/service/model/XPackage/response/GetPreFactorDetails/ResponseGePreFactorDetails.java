
package com.eligasht.service.model.XPackage.response.GetPreFactorDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGePreFactorDetails {

    @SerializedName("GetPreFactorDetailsResult")
    @Expose
    private GetPreFactorDetailsResult getPreFactorDetailsResult;

    public GetPreFactorDetailsResult getGetPreFactorDetailsResult() {
        return getPreFactorDetailsResult;
    }

    public void setGetPreFactorDetailsResult(GetPreFactorDetailsResult getPreFactorDetailsResult) {
        this.getPreFactorDetailsResult = getPreFactorDetailsResult;
    }

}
