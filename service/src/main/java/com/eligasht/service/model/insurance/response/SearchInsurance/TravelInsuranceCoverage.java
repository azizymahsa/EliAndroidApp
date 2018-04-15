
package com.eligasht.service.model.insurance.response.SearchInsurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelInsuranceCoverage {

    @SerializedName("TravelInsuranceCoverageCategory")
    @Expose
    private TravelInsuranceCoverageCategory travelInsuranceCoverageCategory;
    @SerializedName("TravelInsuranceCoverageID")
    @Expose
    private String travelInsuranceCoverageID;
    @SerializedName("TravelInsuranceCoverageTile")
    @Expose
    private String travelInsuranceCoverageTile;
    @SerializedName("TravelInsuranceCoverageVal")
    @Expose
    private String travelInsuranceCoverageVal;

    public TravelInsuranceCoverageCategory getTravelInsuranceCoverageCategory() {
        return travelInsuranceCoverageCategory;
    }

    public void setTravelInsuranceCoverageCategory(TravelInsuranceCoverageCategory travelInsuranceCoverageCategory) {
        this.travelInsuranceCoverageCategory = travelInsuranceCoverageCategory;
    }

    public String getTravelInsuranceCoverageID() {
        return travelInsuranceCoverageID;
    }

    public void setTravelInsuranceCoverageID(String travelInsuranceCoverageID) {
        this.travelInsuranceCoverageID = travelInsuranceCoverageID;
    }

    public String getTravelInsuranceCoverageTile() {
        return travelInsuranceCoverageTile;
    }

    public void setTravelInsuranceCoverageTile(String travelInsuranceCoverageTile) {
        this.travelInsuranceCoverageTile = travelInsuranceCoverageTile;
    }

    public String getTravelInsuranceCoverageVal() {
        return travelInsuranceCoverageVal;
    }

    public void setTravelInsuranceCoverageVal(String travelInsuranceCoverageVal) {
        this.travelInsuranceCoverageVal = travelInsuranceCoverageVal;
    }

}
