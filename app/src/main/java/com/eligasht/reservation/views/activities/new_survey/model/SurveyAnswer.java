package com.eligasht.reservation.views.activities.new_survey.model;

public class SurveyAnswer {
    public String text;

    public String answerId;

    public SurveyAnswer() {
    }

    public SurveyAnswer(String text, String answerId) {
        this.text = text;
        this.answerId = answerId;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
}
