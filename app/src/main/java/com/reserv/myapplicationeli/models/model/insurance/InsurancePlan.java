
package com.reserv.myapplicationeli.models.model.insurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.models.model.Error;

import org.json.JSONObject;

import java.util.ArrayList;

public class InsurancePlan {

    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("InsurancePlans")
    @Expose
    private ArrayList<InsurancePlan_> insurancePlans = null;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public ArrayList<InsurancePlan_> getInsurancePlans() {
        return insurancePlans;
    }

    public void setInsurancePlans(ArrayList<InsurancePlan_> insurancePlans) {
        this.insurancePlans = insurancePlans;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

}
