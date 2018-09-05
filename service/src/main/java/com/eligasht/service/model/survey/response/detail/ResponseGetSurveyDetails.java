
package com.eligasht.service.model.survey.response.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetSurveyDetails {

    @SerializedName("GetSurveyDetailsResult")
    @Expose
    private GetSurveyDetailsResult getSurveyDetailsResult;

    public GetSurveyDetailsResult getGetSurveyDetailsResult() {
        return getSurveyDetailsResult;
    }

    public void setGetSurveyDetailsResult(GetSurveyDetailsResult getSurveyDetailsResult) {
        this.getSurveyDetailsResult = getSurveyDetailsResult;
    }

}
