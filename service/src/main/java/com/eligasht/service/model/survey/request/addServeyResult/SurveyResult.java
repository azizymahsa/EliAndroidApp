
package com.eligasht.service.model.survey.request.addServeyResult;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurveyResult {

    @SerializedName("SurveyID")
    @Expose
    private Integer surveyID;
    @SerializedName("SurveyResultDetails")
    @Expose
    private List<SurveyResultDetail> surveyResultDetails = null;

    public Integer getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Integer surveyID) {
        this.surveyID = surveyID;
    }

    public List<SurveyResultDetail> getSurveyResultDetails() {
        return surveyResultDetails;
    }

    public void setSurveyResultDetails(List<SurveyResultDetail> surveyResultDetails) {
        this.surveyResultDetails = surveyResultDetails;
    }

}
