
package com.eligasht.service.model.insurance.response.SearchInsurance;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelInsurance {

    @SerializedName("Comments")
    @Expose
    private Object comments;
    @SerializedName("Errors")
    @Expose
    private Object errors;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("TravelInsurances")
    @Expose
    private List<TravelInsurance_> travelInsurances = null;

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

    public Object getWarningss() {
        return warningss;
    }

    public void setWarningss(Object warningss) {
        this.warningss = warningss;
    }

    public List<TravelInsurance_> getTravelInsurances() {
        return travelInsurances;
    }

    public void setTravelInsurances(List<TravelInsurance_> travelInsurances) {
        this.travelInsurances = travelInsurances;
    }

}
