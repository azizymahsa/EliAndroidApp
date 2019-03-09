
package com.eligasht.service.model.newModel.train.purchase.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("CustomerID")
    @Expose
    private Integer customerID;
    @SerializedName("FatherName")
    @Expose
    private String fatherName;
    @SerializedName("IDcardNo")
    @Expose
    private String iDcardNo;
    @SerializedName("NameFa")
    @Expose
    private String nameFa;
    @SerializedName("Gender")
    @Expose
    private Boolean gender;
    @SerializedName("FirstNameFa")
    @Expose
    private String firstNameFa;
    @SerializedName("LastNameFa")
    @Expose
    private String lastNameFa;
    @SerializedName("Birthday")
    @Expose
    private String birthday;
    @SerializedName("NationalCode")
    @Expose
    private String nationalCode;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Tel")
    @Expose
    private String tel;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("CityCode")
    @Expose
    private String cityCode;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("StateCode")
    @Expose
    private String stateCode;
    @SerializedName("StateProv")
    @Expose
    private String stateProv;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Postal")
    @Expose
    private String postal;
    @SerializedName("AdMail")
    @Expose
    private String adMail;
    @SerializedName("AdSMS")
    @Expose
    private String adSMS;
    @SerializedName("DocID")
    @Expose
    private String docID;
    @SerializedName("FirstNameEn")
    @Expose
    private String firstNameEn;
    @SerializedName("LastNameEn")
    @Expose
    private String lastNameEn;
    @SerializedName("CountryName")
    @Expose
    private String countryName;
    @SerializedName("AgcUser_ID")
    @Expose
    private Integer agcUserID;
    @SerializedName("WebUser_ID")
    @Expose
    private Integer webUserID;
    @SerializedName("IsCompany")
    @Expose
    private Boolean isCompany;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("Warningss")
    @Expose
    private List<Warnings> warningss = null;
    @SerializedName("Comments")
    @Expose
    private Comments comments;
    @SerializedName("ResultKey")
    @Expose
    private String resultKey;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getIDcardNo() {
        return iDcardNo;
    }

    public void setIDcardNo(String iDcardNo) {
        this.iDcardNo = iDcardNo;
    }

    public String getNameFa() {
        return nameFa;
    }

    public void setNameFa(String nameFa) {
        this.nameFa = nameFa;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getFirstNameFa() {
        return firstNameFa;
    }

    public void setFirstNameFa(String firstNameFa) {
        this.firstNameFa = firstNameFa;
    }

    public String getLastNameFa() {
        return lastNameFa;
    }

    public void setLastNameFa(String lastNameFa) {
        this.lastNameFa = lastNameFa;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateProv() {
        return stateProv;
    }

    public void setStateProv(String stateProv) {
        this.stateProv = stateProv;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getAdMail() {
        return adMail;
    }

    public void setAdMail(String adMail) {
        this.adMail = adMail;
    }

    public String getAdSMS() {
        return adSMS;
    }

    public void setAdSMS(String adSMS) {
        this.adSMS = adSMS;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getAgcUserID() {
        return agcUserID;
    }

    public void setAgcUserID(Integer agcUserID) {
        this.agcUserID = agcUserID;
    }

    public Integer getWebUserID() {
        return webUserID;
    }

    public void setWebUserID(Integer webUserID) {
        this.webUserID = webUserID;
    }

    public Boolean getIsCompany() {
        return isCompany;
    }

    public void setIsCompany(Boolean isCompany) {
        this.isCompany = isCompany;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public List<Warnings> getWarningss() {
        return warningss;
    }

    public void setWarningss(List<Warnings> warningss) {
        this.warningss = warningss;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public String getResultKey() {
        return resultKey;
    }

    public void setResultKey(String resultKey) {
        this.resultKey = resultKey;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
