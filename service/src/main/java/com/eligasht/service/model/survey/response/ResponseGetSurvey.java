
package com.eligasht.service.model.survey.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetSurvey {

    @SerializedName("GetSurveyResult")
    @Expose
    private GetSurveyResult getSurveyResult;

    public GetSurveyResult getGetSurveyResult() {
        return getSurveyResult;
    }

    public void setGetSurveyResult(GetSurveyResult getSurveyResult) {
        this.getSurveyResult = getSurveyResult;
    }

}
