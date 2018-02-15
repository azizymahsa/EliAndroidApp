
package com.eligasht.reservation.models.model.insurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.eligasht.reservation.models.model.Errors;

import java.util.ArrayList;

public class TravelInsurance {

    @SerializedName("Errors")
    @Expose
    private ArrayList<Errors> error;
    @SerializedName("TravelInsurances")
    @Expose
    private ArrayList<TravelInsurance_> travelInsurances = null;

    public ArrayList<Errors> getError() {
        return error;
    }

    public void setError(ArrayList<Errors> error) {
        this.error = error;
    }

    public ArrayList<TravelInsurance_> getTravelInsurances() {
        return travelInsurances;
    }

    public void setTravelInsurances(ArrayList<TravelInsurance_> travelInsurances) {
        this.travelInsurances = travelInsurances;
    }

}
