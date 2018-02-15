
package com.eligasht.reservation.models.model.insurance.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.eligasht.reservation.models.model.insurance.ShowInsuranceResult;

public class InsuranceRes {

    @SerializedName("ShowInsuranceResult")
    @Expose
    private ShowInsuranceResult ShowInsuranceResult;

    public ShowInsuranceResult getShowInsuranceResult() {
        return ShowInsuranceResult;
    }

    public void setShowInsuranceResult(ShowInsuranceResult showInsuranceResult) {
        this.ShowInsuranceResult = showInsuranceResult;
    }

}
