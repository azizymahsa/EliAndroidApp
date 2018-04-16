
package com.eligasht.service.model.XPackage.response.GetPreFactorDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPartner {

    @SerializedName("RqPartner_Address")
    @Expose
    private String rqPartnerAddress;
    @SerializedName("RqPartner_Email")
    @Expose
    private String rqPartnerEmail;
    @SerializedName("RqPartner_FatherName")
    @Expose
    private String rqPartnerFatherName;
    @SerializedName("RqPartner_FirstNameEn")
    @Expose
    private String rqPartnerFirstNameEn;
    @SerializedName("RqPartner_FirstNameFa")
    @Expose
    private String rqPartnerFirstNameFa;
    @SerializedName("RqPartner_Gender")
    @Expose
    private Boolean rqPartnerGender;
    @SerializedName("RqPartner_LastNameEn")
    @Expose
    private String rqPartnerLastNameEn;
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
    @SerializedName("RqPartner_nameF")
    @Expose
    private String rqPartnerNameF;
    @SerializedName("WebUser_ID")
    @Expose
    private Integer webUserID;

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

    public String getRqPartnerFatherName() {
        return rqPartnerFatherName;
    }

    public void setRqPartnerFatherName(String rqPartnerFatherName) {
        this.rqPartnerFatherName = rqPartnerFatherName;
    }

    public String getRqPartnerFirstNameEn() {
        return rqPartnerFirstNameEn;
    }

    public void setRqPartnerFirstNameEn(String rqPartnerFirstNameEn) {
        this.rqPartnerFirstNameEn = rqPartnerFirstNameEn;
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

    public String getRqPartnerLastNameEn() {
        return rqPartnerLastNameEn;
    }

    public void setRqPartnerLastNameEn(String rqPartnerLastNameEn) {
        this.rqPartnerLastNameEn = rqPartnerLastNameEn;
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

    public String getRqPartnerNameF() {
        return rqPartnerNameF;
    }

    public void setRqPartnerNameF(String rqPartnerNameF) {
        this.rqPartnerNameF = rqPartnerNameF;
    }

    public Integer getWebUserID() {
        return webUserID;
    }

    public void setWebUserID(Integer webUserID) {
        this.webUserID = webUserID;
    }

}
