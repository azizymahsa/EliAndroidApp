
package com.reserv.myapplicationeli.models.model.insurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

public class TravelInsurance {

    @SerializedName("Error")
    @Expose
    private JSONObject error;
    @SerializedName("TravelInsurances")
    @Expose
    private ArrayList<TravelInsurance_> travelInsurances = null;

    public JSONObject getError() {
        return error;
    }

    public void setError(JSONObject error) {
        this.error = error;
    }

    public ArrayList<TravelInsurance_> getTravelInsurances() {
        return travelInsurances;
    }

    public void setTravelInsurances(ArrayList<TravelInsurance_> travelInsurances) {
        this.travelInsurances = travelInsurances;
    }

}
