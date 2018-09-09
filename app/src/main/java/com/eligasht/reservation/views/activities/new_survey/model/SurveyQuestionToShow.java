package com.eligasht.reservation.views.activities.new_survey.model;

import java.util.List;

public class SurveyQuestionToShow {


        public String SectionText;
        public String SectionDesc;
        public Integer SectionID;


        public Integer QuestionID;
        public Integer QuestionIDType;
        public String QuestionQuestion;
        public List<SurveyAnswer> QuestionAnswersArr = null;

        public boolean QuestionIsRequired;

    public SurveyQuestionToShow(String sectionText, String sectionDesc, Integer sectionID, Integer questionID, Integer questionIDType, String questionQuestion, List<SurveyAnswer> questionAnswersArr, boolean questionIsRequired, Integer mainID, String color) {
        SectionText = sectionText;
        SectionDesc = sectionDesc;
        SectionID = sectionID;
        QuestionID = questionID;
        QuestionIDType = questionIDType;
        QuestionQuestion = questionQuestion;
        QuestionAnswersArr = questionAnswersArr;
        QuestionIsRequired = questionIsRequired;
        this.mainID = mainID;
        this.color = color;
    }

    public Integer mainID;



    public String color;

    public SurveyQuestionToShow() {
    }


    public Integer getMainID() {
        return mainID;
    }

    public void setMainID(Integer mainID) {
        this.mainID = mainID;
    }
    public boolean isQuestionIsRequired() {
        return QuestionIsRequired;
    }

    public void setQuestionIsRequired(boolean questionIsRequired) {
        QuestionIsRequired = questionIsRequired;
    }
    public String getSectionText() {
        return SectionText;
    }

    public void setSectionText(String sectionText) {
        SectionText = sectionText;
    }

    public String getSectionDesc() {
        return SectionDesc;
    }

    public void setSectionDesc(String sectionDesc) {
        SectionDesc = sectionDesc;
    }

    public Integer getSectionID() {
        return SectionID;
    }

    public void setSectionID(Integer sectionID) {
        SectionID = sectionID;
    }

    public Integer getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(Integer questionID) {
        QuestionID = questionID;
    }

    public Integer getQuestionIDType() {
        return QuestionIDType;
    }

    public void setQuestionIDType(Integer questionIDType) {
        QuestionIDType = questionIDType;
    }

    public String getQuestionQuestion() {
        return QuestionQuestion;
    }

    public void setQuestionQuestion(String questionQuestion) {
        QuestionQuestion = questionQuestion;
    }

    public List<SurveyAnswer> getQuestionAnswersArr() {
        return QuestionAnswersArr;
    }

    public void setQuestionAnswersArr(List<SurveyAnswer> questionAnswersArr) {
        QuestionAnswersArr = questionAnswersArr;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
