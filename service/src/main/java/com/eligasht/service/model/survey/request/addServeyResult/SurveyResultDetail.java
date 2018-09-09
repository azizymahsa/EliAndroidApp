
package com.eligasht.service.model.survey.request.addServeyResult;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurveyResultDetail {

    @SerializedName("SurveyQuestionID")
    @Expose
    private Integer surveyQuestionID;
    @SerializedName("Answers")
    @Expose
    private List<Answer> answers = null;

    public Integer getSurveyQuestionID() {
        return surveyQuestionID;
    }

    public void setSurveyQuestionID(Integer surveyQuestionID) {
        this.surveyQuestionID = surveyQuestionID;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

}
