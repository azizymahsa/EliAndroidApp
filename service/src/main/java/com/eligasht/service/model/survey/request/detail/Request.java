
package com.eligasht.service.model.survey.request.detail;

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
    @SerializedName("IsEncrypted")
    @Expose
    private Boolean isEncrypted;

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

    public Boolean getIsEncrypted() {
        return isEncrypted;
    }

    public void setIsEncrypted(Boolean isEncrypted) {
        this.isEncrypted = isEncrypted;
    }

}
