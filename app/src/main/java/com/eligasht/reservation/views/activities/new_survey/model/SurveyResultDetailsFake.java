package com.eligasht.reservation.views.activities.new_survey.model;

import com.eligasht.service.model.survey.request.addServeyResult.Answer;

import java.util.List;

public class SurveyResultDetailsFake {


    public Integer SurveyQuestionID;
    public List<AnswerModel> answerModels;

    public SurveyResultDetailsFake() {
    }

    public SurveyResultDetailsFake(Integer surveyQuestionID, List<AnswerModel> answerModels) {
       this.SurveyQuestionID = surveyQuestionID;
        this.answerModels = answerModels;
    }


    public Integer getSurveyQuestionID() {
        return SurveyQuestionID;
    }

    public void setSurveyQuestionID(Integer surveyQuestionID) {
        SurveyQuestionID = surveyQuestionID;
    }

    public List<AnswerModel> getAnswerModels() {
        return answerModels;
    }

    public void setAnswerModels(List<AnswerModel> answerModels) {
        this.answerModels = answerModels;
    }

}
