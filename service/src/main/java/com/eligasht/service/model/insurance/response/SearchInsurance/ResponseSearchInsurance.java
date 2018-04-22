
package com.eligasht.service.model.insurance.response.SearchInsurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSearchInsurance {

    @SerializedName("ShowInsuranceResult")
    @Expose
    public ShowInsuranceResult showInsuranceResult;

    public ShowInsuranceResult getShowInsuranceResult() {
        return showInsuranceResult;
    }

    public void setShowInsuranceResult(ShowInsuranceResult showInsuranceResult) {
        this.showInsuranceResult = showInsuranceResult;
    }

}
