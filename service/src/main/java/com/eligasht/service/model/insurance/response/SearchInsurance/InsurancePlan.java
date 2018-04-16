
package com.eligasht.service.model.insurance.response.SearchInsurance;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsurancePlan {

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
    @SerializedName("InsurancePlans")
    @Expose
    private List<InsurancePlan_> insurancePlans = null;
    @SerializedName("SearchKey")
    @Expose
    private String searchKey;

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

    public List<InsurancePlan_> getInsurancePlans() {
        return insurancePlans;
    }

    public void setInsurancePlans(List<InsurancePlan_> insurancePlans) {
        this.insurancePlans = insurancePlans;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

}
