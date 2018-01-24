
package com.reserv.myapplicationeli.models.model.insurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.models.model.Errors;

import java.util.ArrayList;

public class ShowInsuranceResult {

    @SerializedName("Errors")
    @Expose
    private ArrayList<Errors> error;
    @SerializedName("InsurancePlan")
    @Expose
    private InsurancePlan insurancePlan;

    @SerializedName("TravelInsurance")
    @Expose
    private TravelInsurance travelInsurance;

    public ArrayList<Errors> getError() {
        return error;
    }

    public void setError(ArrayList<Errors> error) {
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
