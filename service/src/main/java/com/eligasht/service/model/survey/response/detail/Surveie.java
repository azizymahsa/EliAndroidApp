
package com.eligasht.service.model.survey.response.detail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Surveie {

    @SerializedName("AppCount")
    @Expose
    private Integer appCount;
    @SerializedName("CanApplyStatus")
    @Expose
    private Integer canApplyStatus;
    @SerializedName("CanEditAfterSubmit")
    @Expose
    private Boolean canEditAfterSubmit;
    @SerializedName("CanSeeAnswers")
    @Expose
    private Boolean canSeeAnswers;
    @SerializedName("CanSeeMissedQuestions")
    @Expose
    private Boolean canSeeMissedQuestions;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("CreatorUser")
    @Expose
    private CreatorUser creatorUser;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("DisplayInApplications")
    @Expose
    private List<DisplayInApplication> displayInApplications = null;
    @SerializedName("EncryptedSurveyID")
    @Expose
    private String encryptedSurveyID;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("ImgUrl")
    @Expose
    private Object imgUrl;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("MandatorySignin")
    @Expose
    private Boolean mandatorySignin;
    @SerializedName("Proirity")
    @Expose
    private Integer proirity;
    @SerializedName("ResponseText")
    @Expose
    private String responseText;
    @SerializedName("ShamsiCreateDate")
    @Expose
    private String shamsiCreateDate;
    @SerializedName("ShowAsWizard")
    @Expose
    private Boolean showAsWizard;
    @SerializedName("ShowProgressBar")
    @Expose
    private Boolean showProgressBar;
    @SerializedName("ShowReponse")
    @Expose
    private Boolean showReponse;
    @SerializedName("ShuffleQuestionOrder")
    @Expose
    private Boolean shuffleQuestionOrder;
    @SerializedName("SurveySections")
    @Expose
    private List<SurveySection> surveySections = null;
    @SerializedName("SurveyStatus")
    @Expose
    private SurveyStatus surveyStatus;
    @SerializedName("SurveyTemplate")
    @Expose
    private SurveyTemplate surveyTemplate;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("ValidFrom")
    @Expose
    private String validFrom;
    @SerializedName("ValidTo")
    @Expose
    private String validTo;

    public Integer getAppCount() {
        return appCount;
    }

    public void setAppCount(Integer appCount) {
        this.appCount = appCount;
    }

    public Integer getCanApplyStatus() {
        return canApplyStatus;
    }

    public void setCanApplyStatus(Integer canApplyStatus) {
        this.canApplyStatus = canApplyStatus;
    }

    public Boolean getCanEditAfterSubmit() {
        return canEditAfterSubmit;
    }

    public void setCanEditAfterSubmit(Boolean canEditAfterSubmit) {
        this.canEditAfterSubmit = canEditAfterSubmit;
    }

    public Boolean getCanSeeAnswers() {
        return canSeeAnswers;
    }

    public void setCanSeeAnswers(Boolean canSeeAnswers) {
        this.canSeeAnswers = canSeeAnswers;
    }

    public Boolean getCanSeeMissedQuestions() {
        return canSeeMissedQuestions;
    }

    public void setCanSeeMissedQuestions(Boolean canSeeMissedQuestions) {
        this.canSeeMissedQuestions = canSeeMissedQuestions;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public CreatorUser getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(CreatorUser creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DisplayInApplication> getDisplayInApplications() {
        return displayInApplications;
    }

    public void setDisplayInApplications(List<DisplayInApplication> displayInApplications) {
        this.displayInApplications = displayInApplications;
    }

    public String getEncryptedSurveyID() {
        return encryptedSurveyID;
    }

    public void setEncryptedSurveyID(String encryptedSurveyID) {
        this.encryptedSurveyID = encryptedSurveyID;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Object getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Object imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getMandatorySignin() {
        return mandatorySignin;
    }

    public void setMandatorySignin(Boolean mandatorySignin) {
        this.mandatorySignin = mandatorySignin;
    }

    public Integer getProirity() {
        return proirity;
    }

    public void setProirity(Integer proirity) {
        this.proirity = proirity;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getShamsiCreateDate() {
        return shamsiCreateDate;
    }

    public void setShamsiCreateDate(String shamsiCreateDate) {
        this.shamsiCreateDate = shamsiCreateDate;
    }

    public Boolean getShowAsWizard() {
        return showAsWizard;
    }

    public void setShowAsWizard(Boolean showAsWizard) {
        this.showAsWizard = showAsWizard;
    }

    public Boolean getShowProgressBar() {
        return showProgressBar;
    }

    public void setShowProgressBar(Boolean showProgressBar) {
        this.showProgressBar = showProgressBar;
    }

    public Boolean getShowReponse() {
        return showReponse;
    }

    public void setShowReponse(Boolean showReponse) {
        this.showReponse = showReponse;
    }

    public Boolean getShuffleQuestionOrder() {
        return shuffleQuestionOrder;
    }

    public void setShuffleQuestionOrder(Boolean shuffleQuestionOrder) {
        this.shuffleQuestionOrder = shuffleQuestionOrder;
    }

    public List<SurveySection> getSurveySections() {
        return surveySections;
    }

    public void setSurveySections(List<SurveySection> surveySections) {
        this.surveySections = surveySections;
    }

    public SurveyStatus getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(SurveyStatus surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public SurveyTemplate getSurveyTemplate() {
        return surveyTemplate;
    }

    public void setSurveyTemplate(SurveyTemplate surveyTemplate) {
        this.surveyTemplate = surveyTemplate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

}
