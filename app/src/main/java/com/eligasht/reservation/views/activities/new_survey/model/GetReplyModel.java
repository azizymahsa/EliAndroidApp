package com.eligasht.reservation.views.activities.new_survey.model;

public class GetReplyModel {
    public  int   SurveyQuestionID;
    public  int   ID;
    public  String   Value;
    public boolean  IsRequired;

    public GetReplyModel(int surveyQuestionID, int id, String value,boolean IsRequired) {
        this.SurveyQuestionID = surveyQuestionID;
        this.ID = id;
        this.Value = value;
        this.IsRequired=IsRequired;
    }
}
