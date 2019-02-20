
package com.eligasht.service.model.newModel.insurance.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelInsuranceCoverageCategory {

    @SerializedName("TravelInsuranceCoverageCatID")
    @Expose
    private Object travelInsuranceCoverageCatID;
    @SerializedName("TravelInsuranceCoverageCatTile")
    @Expose
    private Object travelInsuranceCoverageCatTile;

    public Object getTravelInsuranceCoverageCatID() {
        return travelInsuranceCoverageCatID;
    }

    public void setTravelInsuranceCoverageCatID(Object travelInsuranceCoverageCatID) {
        this.travelInsuranceCoverageCatID = travelInsuranceCoverageCatID;
    }

    public Object getTravelInsuranceCoverageCatTile() {
        return travelInsuranceCoverageCatTile;
    }

    public void setTravelInsuranceCoverageCatTile(Object travelInsuranceCoverageCatTile) {
        this.travelInsuranceCoverageCatTile = travelInsuranceCoverageCatTile;
    }

}
