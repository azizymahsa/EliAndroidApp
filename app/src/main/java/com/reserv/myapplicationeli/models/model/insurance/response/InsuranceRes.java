
package com.reserv.myapplicationeli.models.model.insurance.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.models.model.insurance.ShowInsuranceResult;

public class InsuranceRes {

    @SerializedName("ShowInsuranceResult")
    @Expose
    private ShowInsuranceResult showInsuranceResult;

    public ShowInsuranceResult getShowInsuranceResult() {
        return showInsuranceResult;
    }

    public void setShowInsuranceResult(ShowInsuranceResult showInsuranceResult) {
        this.showInsuranceResult = showInsuranceResult;
    }

}
