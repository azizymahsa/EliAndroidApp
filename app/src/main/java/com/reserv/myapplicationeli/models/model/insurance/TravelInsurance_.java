
package com.reserv.myapplicationeli.models.model.insurance;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TravelInsurance_ {

    @SerializedName("InsID")
    @Expose
    private Integer insID;
    @SerializedName("TravelInsuranceCoverages")
    @Expose
    private ArrayList<TravelInsuranceCoverage> travelInsuranceCoverages = null;
    @SerializedName("TravelInsuranceID")
    @Expose
    private Integer travelInsuranceID;
    @SerializedName("TravelInsurancePricePP")
    @Expose
    private TravelInsurancePricePP travelInsurancePricePP;
    @SerializedName("TravelInsuranceTile")
    @Expose
    private String travelInsuranceTile;

    public Integer getInsID() {
        return insID;
    }

    public void setInsID(Integer insID) {
        this.insID = insID;
    }

    public ArrayList<TravelInsuranceCoverage> getTravelInsuranceCoverages() {
        return travelInsuranceCoverages;
    }

    public void setTravelInsuranceCoverages(ArrayList<TravelInsuranceCoverage> travelInsuranceCoverages) {
        this.travelInsuranceCoverages = travelInsuranceCoverages;
    }

    public Integer getTravelInsuranceID() {
        return travelInsuranceID;
    }

    public void setTravelInsuranceID(Integer travelInsuranceID) {
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
