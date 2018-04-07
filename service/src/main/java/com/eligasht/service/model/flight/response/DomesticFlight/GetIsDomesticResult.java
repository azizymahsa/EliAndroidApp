
package com.eligasht.service.model.flight.response.DomesticFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetIsDomesticResult {

    @SerializedName("Comments")
    @Expose
    private Object comments;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public Boolean getDomestic() {
        return isDomestic;
    }

    public void setDomestic(Boolean domestic) {
        isDomestic = domestic;
    }

    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private Object warningss;
    @SerializedName("IsDomestic")
    @Expose
    private Boolean isDomestic;

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
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

    public Boolean getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(Boolean isDomestic) {
        this.isDomestic = isDomestic;
    }

}
