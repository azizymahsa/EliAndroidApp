
package com.eligasht.service.model.newModel.flight.purchaseFlight.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartnerList {

    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Gender")
    @Expose
    private Boolean gender;
    @SerializedName("LastNameFa")
    @Expose
    private String lastNameFa;
    @SerializedName("WebUserID")
    @Expose
    private Integer webUserID;
    @SerializedName("NationalCode")
    @Expose
    private String nationalCode;
    @SerializedName("AgcUserID")
    @Expose
    private Integer agcUserID;
    @SerializedName("FirstNameFa")
    @Expose
    private String firstNameFa;
    @SerializedName("Email")
    @Expose
    private String email;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getLastNameFa() {
        return lastNameFa;
    }

    public void setLastNameFa(String lastNameFa) {
        this.lastNameFa = lastNameFa;
    }

    public Integer getWebUserID() {
        return webUserID;
    }

    public void setWebUserID(Integer webUserID) {
        this.webUserID = webUserID;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Integer getAgcUserID() {
        return agcUserID;
    }

    public void setAgcUserID(Integer agcUserID) {
        this.agcUserID = agcUserID;
    }

    public String getFirstNameFa() {
        return firstNameFa;
    }

    public void setFirstNameFa(String firstNameFa) {
        this.firstNameFa = firstNameFa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
