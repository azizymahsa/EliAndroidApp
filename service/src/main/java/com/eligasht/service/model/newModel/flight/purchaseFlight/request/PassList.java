
package com.eligasht.service.model.newModel.flight.purchaseFlight.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassList {

    @SerializedName("Birthday")
    @Expose
    private String birthday;
    @SerializedName("PassportNo")
    @Expose
    private String passportNo;
    @SerializedName("Gender")
    @Expose
    private Boolean gender;
    @SerializedName("NationalityID")
    @Expose
    private String nationalityID;
    @SerializedName("PassportExpiration")
    @Expose
    private String passportExpiration;
    @SerializedName("NationalCode")
    @Expose
    private String nationalCode;
    @SerializedName("LastNameEn")
    @Expose
    private String lastNameEn;
    @SerializedName("FirstNameEn")
    @Expose
    private String firstNameEn;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("NationalityCode")
    @Expose
    private String NationalityCode;

    public String getNationalityCode() {
        return NationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        NationalityCode = nationalityCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getNationalityID() {
        return nationalityID;
    }

    public void setNationalityID(String nationalityID) {
        this.nationalityID = nationalityID;
    }

    public String getPassportExpiration() {
        return passportExpiration;
    }

    public void setPassportExpiration(String passportExpiration) {
        this.passportExpiration = passportExpiration;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
