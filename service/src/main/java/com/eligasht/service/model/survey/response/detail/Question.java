
package com.eligasht.service.model.survey.response.detail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("AnswerType")
    @Expose
    private AnswerType answerType;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("IsRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("Proirity")
    @Expose
    private Integer proirity;
    @SerializedName("QuestionAnswers")
    @Expose
    private List<QuestionAnswer> questionAnswers = null;
    @SerializedName("QuestionType")
    @Expose
    private QuestionType questionType;
    @SerializedName("RandomAnswer")
    @Expose
    private Boolean randomAnswer;
    @SerializedName("ShuffleAnswers")
    @Expose
    private Boolean shuffleAnswers;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("UsersCountAnswered")
    @Expose
    private Integer usersCountAnswered;
    @SerializedName("Weight")
    @Expose
    private Integer weight;

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

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

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Integer getProirity() {
        return proirity;
    }

    public void setProirity(Integer proirity) {
        this.proirity = proirity;
    }

    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Boolean getRandomAnswer() {
        return randomAnswer;
    }

    public void setRandomAnswer(Boolean randomAnswer) {
        this.randomAnswer = randomAnswer;
    }

    public Boolean getShuffleAnswers() {
        return shuffleAnswers;
    }

    public void setShuffleAnswers(Boolean shuffleAnswers) {
        this.shuffleAnswers = shuffleAnswers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUsersCountAnswered() {
        return usersCountAnswered;
    }

    public void setUsersCountAnswered(Integer usersCountAnswered) {
        this.usersCountAnswered = usersCountAnswered;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
