
package com.eligasht.service.model.survey.response.addServeyResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSetAnswer {

    @SerializedName("AddSurveyResultResult")
    @Expose
    private AddSurveyResultResult addSurveyResultResult;

    public AddSurveyResultResult getAddSurveyResultResult() {
        return addSurveyResultResult;
    }

    public void setAddSurveyResultResult(AddSurveyResultResult addSurveyResultResult) {
        this.addSurveyResultResult = addSurveyResultResult;
    }

}
