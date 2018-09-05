
package com.eligasht.service.model.survey.response.detail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurveySection {

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Priority")
    @Expose
    private Integer priority;
    @SerializedName("Questions")
    @Expose
    private List<Question> questions = null;
    @SerializedName("Text")
    @Expose
    private String text;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
