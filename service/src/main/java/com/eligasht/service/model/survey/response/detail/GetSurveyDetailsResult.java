
package com.eligasht.service.model.survey.response.detail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSurveyDetailsResult {

    @SerializedName("Comments")
    @Expose
    private Comments comments;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("ResultKey")
    @Expose
    private Object resultKey;
    @SerializedName("Warningss")
    @Expose
    private List<Object> warningss = null;
    @SerializedName("Surveies")
    @Expose
    private List<Surveie> surveies = null;
    @SerializedName("SurveyStatuses")
    @Expose
    private List<Object> surveyStatuses = null;

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }



    public Object getResultKey() {
        return resultKey;
    }

    public void setResultKey(Object resultKey) {
        this.resultKey = resultKey;
    }

    public List<Object> getWarningss() {
        return warningss;
    }

    public void setWarningss(List<Object> warningss) {
        this.warningss = warningss;
    }

    public List<Surveie> getSurveies() {
        return surveies;
    }

    public void setSurveies(List<Surveie> surveies) {
        this.surveies = surveies;
    }

    public List<Object> getSurveyStatuses() {
        return surveyStatuses;
    }

    public void setSurveyStatuses(List<Object> surveyStatuses) {
        this.surveyStatuses = surveyStatuses;
    }

}
