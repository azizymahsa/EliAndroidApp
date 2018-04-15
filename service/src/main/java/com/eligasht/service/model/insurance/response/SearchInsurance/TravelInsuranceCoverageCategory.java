
package com.eligasht.service.model.insurance.response.SearchInsurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelInsuranceCoverageCategory {

    @SerializedName("TravelInsuranceCoverageCatID")
    @Expose
    private String travelInsuranceCoverageCatID;
    @SerializedName("TravelInsuranceCoverageCatTile")
    @Expose
    private Object travelInsuranceCoverageCatTile;

    public String getTravelInsuranceCoverageCatID() {
        return travelInsuranceCoverageCatID;
    }

    public void setTravelInsuranceCoverageCatID(String travelInsuranceCoverageCatID) {
        this.travelInsuranceCoverageCatID = travelInsuranceCoverageCatID;
    }

    public Object getTravelInsuranceCoverageCatTile() {
        return travelInsuranceCoverageCatTile;
    }

    public void setTravelInsuranceCoverageCatTile(Object travelInsuranceCoverageCatTile) {
        this.travelInsuranceCoverageCatTile = travelInsuranceCoverageCatTile;
    }

}
