
package com.eligasht.service.model.survey.request.addServeyResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("identity")
    @Expose
    private Identity identity;
    @SerializedName("SurveyResult")
    @Expose
    private SurveyResult surveyResult;

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public SurveyResult getSurveyResult() {
        return surveyResult;
    }

    public void setSurveyResult(SurveyResult surveyResult) {
        this.surveyResult = surveyResult;
    }

}
