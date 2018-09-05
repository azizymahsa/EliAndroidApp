package com.eligasht.reservation.views.activities.new_survey.model;

import java.util.ArrayList;

public class EventModel {
   public ArrayList<SurveyQuestionToShow> surveyQuestionToShows;

    public EventModel(ArrayList<SurveyQuestionToShow> surveyQuestionToShows) {
        this.surveyQuestionToShows=surveyQuestionToShows;
    }

    public ArrayList<SurveyQuestionToShow> getSurveyQuestionToShows() {
        return surveyQuestionToShows;
    }

    public void setSurveyQuestionToShows(ArrayList<SurveyQuestionToShow> surveyQuestionToShows) {
        this.surveyQuestionToShows = surveyQuestionToShows;
    }
}
