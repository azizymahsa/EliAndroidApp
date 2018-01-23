
package com.reserv.myapplicationeli.models.model.insurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.models.model.Error;

import org.json.JSONObject;

public class ShowInsuranceResult {

    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("InsurancePlan")
    @Expose
    private InsurancePlan insurancePlan;

    @SerializedName("TravelInsurance")
    @Expose
    private TravelInsurance travelInsurance;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public InsurancePlan getInsurancePlan() {
        return insurancePlan;
    }

    public void setInsurancePlan(InsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }

    public TravelInsurance getTravelInsurance() {
        return travelInsurance;
    }

    public void setTravelInsurance(TravelInsurance travelInsurance) {
        this.travelInsurance = travelInsurance;
    }

}
