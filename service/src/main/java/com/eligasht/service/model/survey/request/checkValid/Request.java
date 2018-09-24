
package com.eligasht.service.model.survey.request.checkValid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("identity")
    @Expose
    private Identity identity;
    @SerializedName("EncryptedSurveyID")
    @Expose
    private String encryptedSurveyID;
    @SerializedName("ApplicationUserID")
    @Expose
    private Integer applicationUserID;

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String getEncryptedSurveyID() {
        return encryptedSurveyID;
    }

    public void setEncryptedSurveyID(String encryptedSurveyID) {
        this.encryptedSurveyID = encryptedSurveyID;
    }

    public Integer getApplicationUserID() {
        return applicationUserID;
    }

    public void setApplicationUserID(Integer applicationUserID) {
        this.applicationUserID = applicationUserID;
    }

}
