
package com.eligasht.service.model.hotelflight.purchase.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartnerList {

    @SerializedName("RqPartner_Address")
    @Expose
    private String rqPartnerAddress;
    @SerializedName("RqPartner_Email")
    @Expose
    private String rqPartnerEmail;
    @SerializedName("RqPartner_FirstNameFa")
    @Expose
    private String rqPartnerFirstNameFa;
    @SerializedName("RqPartner_Gender")
    @Expose
    private Boolean rqPartnerGender;
    @SerializedName("RqPartner_LastNameFa")
    @Expose
    private String rqPartnerLastNameFa;
    @SerializedName("RqPartner_Mobile")
    @Expose
    private String rqPartnerMobile;
    @SerializedName("RqPartner_NationalCode")
    @Expose
    private String rqPartnerNationalCode;
    @SerializedName("RqPartner_Tel")
    @Expose
    private String rqPartnerTel;
    @SerializedName("WebUser_ID ")
    @Expose
    private String webUserID;

    public String getRqPartnerAddress() {
        return rqPartnerAddress;
    }

    public void setRqPartnerAddress(String rqPartnerAddress) {
        this.rqPartnerAddress = rqPartnerAddress;
    }

    public String getRqPartnerEmail() {
        return rqPartnerEmail;
    }

    public void setRqPartnerEmail(String rqPartnerEmail) {
        this.rqPartnerEmail = rqPartnerEmail;
    }

    public String getRqPartnerFirstNameFa() {
        return rqPartnerFirstNameFa;
    }

    public void setRqPartnerFirstNameFa(String rqPartnerFirstNameFa) {
        this.rqPartnerFirstNameFa = rqPartnerFirstNameFa;
    }

    public Boolean getRqPartnerGender() {
        return rqPartnerGender;
    }

    public void setRqPartnerGender(Boolean rqPartnerGender) {
        this.rqPartnerGender = rqPartnerGender;
    }

    public String getRqPartnerLastNameFa() {
        return rqPartnerLastNameFa;
    }

    public void setRqPartnerLastNameFa(String rqPartnerLastNameFa) {
        this.rqPartnerLastNameFa = rqPartnerLastNameFa;
    }

    public String getRqPartnerMobile() {
        return rqPartnerMobile;
    }

    public void setRqPartnerMobile(String rqPartnerMobile) {
        this.rqPartnerMobile = rqPartnerMobile;
    }

    public String getRqPartnerNationalCode() {
        return rqPartnerNationalCode;
    }

    public void setRqPartnerNationalCode(String rqPartnerNationalCode) {
        this.rqPartnerNationalCode = rqPartnerNationalCode;
    }

    public String getRqPartnerTel() {
        return rqPartnerTel;
    }

    public void setRqPartnerTel(String rqPartnerTel) {
        this.rqPartnerTel = rqPartnerTel;
    }

    public String getWebUserID() {
        return webUserID;
    }

    public void setWebUserID(String webUserID) {
        this.webUserID = webUserID;
    }

}
