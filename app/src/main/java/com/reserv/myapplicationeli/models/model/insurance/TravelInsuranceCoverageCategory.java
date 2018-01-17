
package com.reserv.myapplicationeli.models.model.insurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelInsuranceCoverageCategory {

    @SerializedName("TravelInsuranceCoverageCatID")
    @Expose
    private String travelInsuranceCoverageCatID;
    @SerializedName("TravelInsuranceCoverageCatTile")
    @Expose
    private String travelInsuranceCoverageCatTile;

    public String getTravelInsuranceCoverageCatID() {
        return travelInsuranceCoverageCatID;
    }

    public void setTravelInsuranceCoverageCatID(String travelInsuranceCoverageCatID) {
        this.travelInsuranceCoverageCatID = travelInsuranceCoverageCatID;
    }

    public String getTravelInsuranceCoverageCatTile() {
        return travelInsuranceCoverageCatTile;
    }

    public void setTravelInsuranceCoverageCatTile(String travelInsuranceCoverageCatTile) {
        this.travelInsuranceCoverageCatTile = travelInsuranceCoverageCatTile;
    }

}
