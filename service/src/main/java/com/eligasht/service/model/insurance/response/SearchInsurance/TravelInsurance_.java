
package com.eligasht.service.model.insurance.response.SearchInsurance;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelInsurance_ {

    @SerializedName("InsID")
    @Expose
    private String insID;
    @SerializedName("TravelInsuranceCoverages")
    @Expose
    private List<TravelInsuranceCoverage> travelInsuranceCoverages = null;
    @SerializedName("TravelInsuranceID")
    @Expose
    private String travelInsuranceID;
    @SerializedName("TravelInsurancePricePP")
    @Expose
    private TravelInsurancePricePP travelInsurancePricePP;
    @SerializedName("TravelInsuranceTile")
    @Expose
    private String travelInsuranceTile;

    public String getInsID() {
        return insID;
    }

    public void setInsID(String insID) {
        this.insID = insID;
    }

    public List<TravelInsuranceCoverage> getTravelInsuranceCoverages() {
        return travelInsuranceCoverages;
    }

    public void setTravelInsuranceCoverages(List<TravelInsuranceCoverage> travelInsuranceCoverages) {
        this.travelInsuranceCoverages = travelInsuranceCoverages;
    }

    public String getTravelInsuranceID() {
        return travelInsuranceID;
    }

    public void setTravelInsuranceID(String travelInsuranceID) {
        this.travelInsuranceID = travelInsuranceID;
    }

    public TravelInsurancePricePP getTravelInsurancePricePP() {
        return travelInsurancePricePP;
    }

    public void setTravelInsurancePricePP(TravelInsurancePricePP travelInsurancePricePP) {
        this.travelInsurancePricePP = travelInsurancePricePP;
    }

    public String getTravelInsuranceTile() {
        return travelInsuranceTile;
    }

    public void setTravelInsuranceTile(String travelInsuranceTile) {
        this.travelInsuranceTile = travelInsuranceTile;
    }

}
