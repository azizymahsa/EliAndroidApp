
package com.reserv.myapplicationeli.models.model.login.call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;

public class RegisterListReq {

    @SerializedName("identity")
    @Expose
    private Identity identity;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("ActivationCode")
    @Expose
    private String activationCode;
    @SerializedName("BankCardNo")
    @Expose
    private String bankCardNo;
    @SerializedName("Bull")
    @Expose
    private String bull;
    @SerializedName("DemosticCityFa")
    @Expose
    private String demosticCityFa;
    @SerializedName("DemosticCityID")
    @Expose
    private Integer demosticCityID;
    @SerializedName("DemosticPrivinceFa")
    @Expose
    private String demosticPrivinceFa;
    @SerializedName("DemosticPrivinceID")
    @Expose
    private Integer demosticPrivinceID;
    @SerializedName("EncryptWebUserID")
    @Expose
    private String encryptWebUserID;
    @SerializedName("ImgURL")
    @Expose
    private String imgURL;
    @SerializedName("MsgCount")
    @Expose
    private Integer msgCount;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("WebUserAddress")
    @Expose
    private String webUserAddress;
    @SerializedName("WebUserBirthDayMiladi")
    @Expose
    private String webUserBirthDayMiladi;
    @SerializedName("WebUserBirthDayShamsi")
    @Expose
    private String webUserBirthDayShamsi;
    @SerializedName("WebUserFatherName")
    @Expose
    private String webUserFatherName;
    @SerializedName("WebUserFnameE")
    @Expose
    private String webUserFnameE;
    @SerializedName("WebUserFnameF")
    @Expose
    private String webUserFnameF;
    @SerializedName("WebUserGender")
    @Expose
    private Boolean webUserGender;
    @SerializedName("WebUserID")
    @Expose
    private Integer webUserID;
    @SerializedName("WebUserIDcardNo")
    @Expose
    private String webUserIDcardNo;
    @SerializedName("WebUserLnameE")
    @Expose
    private String webUserLnameE;
    @SerializedName("WebUserLnameF")
    @Expose
    private String webUserLnameF;
    @SerializedName("WebUserMail")
    @Expose
    private String webUserMail;
    @SerializedName("WebUserMobile")
    @Expose
    private String webUserMobile;
    @SerializedName("WebUserNameE")
    @Expose
    private String webUserNameE;
    @SerializedName("WebUserNameF")
    @Expose
    private String webUserNameF;
    @SerializedName("WebUserNationalCode")
    @Expose
    private String webUserNationalCode;
    @SerializedName("WebUserOccupation")
    @Expose
    private String webUserOccupation;
    @SerializedName("WebUserTel")
    @Expose
    private String webUserTel;

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBull() {
        return bull;
    }

    public void setBull(String bull) {
        this.bull = bull;
    }

    public String getDemosticCityFa() {
        return demosticCityFa;
    }

    public void setDemosticCityFa(String demosticCityFa) {
        this.demosticCityFa = demosticCityFa;
    }

    public Integer getDemosticCityID() {
        return demosticCityID;
    }

    public void setDemosticCityID(Integer demosticCityID) {
        this.demosticCityID = demosticCityID;
    }

    public String getDemosticPrivinceFa() {
        return demosticPrivinceFa;
    }

    public void setDemosticPrivinceFa(String demosticPrivinceFa) {
        this.demosticPrivinceFa = demosticPrivinceFa;
    }

    public Integer getDemosticPrivinceID() {
        return demosticPrivinceID;
    }

    public void setDemosticPrivinceID(Integer demosticPrivinceID) {
        this.demosticPrivinceID = demosticPrivinceID;
    }

    public String getEncryptWebUserID() {
        return encryptWebUserID;
    }

    public void setEncryptWebUserID(String encryptWebUserID) {
        this.encryptWebUserID = encryptWebUserID;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Integer getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(Integer msgCount) {
        this.msgCount = msgCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebUserAddress() {
        return webUserAddress;
    }

    public void setWebUserAddress(String webUserAddress) {
        this.webUserAddress = webUserAddress;
    }

    public String getWebUserBirthDayMiladi() {
        return webUserBirthDayMiladi;
    }

    public void setWebUserBirthDayMiladi(String webUserBirthDayMiladi) {
        this.webUserBirthDayMiladi = webUserBirthDayMiladi;
    }

    public String getWebUserBirthDayShamsi() {
        return webUserBirthDayShamsi;
    }

    public void setWebUserBirthDayShamsi(String webUserBirthDayShamsi) {
        this.webUserBirthDayShamsi = webUserBirthDayShamsi;
    }

    public String getWebUserFatherName() {
        return webUserFatherName;
    }

    public void setWebUserFatherName(String webUserFatherName) {
        this.webUserFatherName = webUserFatherName;
    }

    public String getWebUserFnameE() {
        return webUserFnameE;
    }

    public void setWebUserFnameE(String webUserFnameE) {
        this.webUserFnameE = webUserFnameE;
    }

    public String getWebUserFnameF() {
        return webUserFnameF;
    }

    public void setWebUserFnameF(String webUserFnameF) {
        this.webUserFnameF = webUserFnameF;
    }

    public Boolean getWebUserGender() {
        return webUserGender;
    }

    public void setWebUserGender(Boolean webUserGender) {
        this.webUserGender = webUserGender;
    }

    public Integer getWebUserID() {
        return webUserID;
    }

    public void setWebUserID(Integer webUserID) {
        this.webUserID = webUserID;
    }

    public String getWebUserIDcardNo() {
        return webUserIDcardNo;
    }

    public void setWebUserIDcardNo(String webUserIDcardNo) {
        this.webUserIDcardNo = webUserIDcardNo;
    }

    public String getWebUserLnameE() {
        return webUserLnameE;
    }

    public void setWebUserLnameE(String webUserLnameE) {
        this.webUserLnameE = webUserLnameE;
    }

    public String getWebUserLnameF() {
        return webUserLnameF;
    }

    public void setWebUserLnameF(String webUserLnameF) {
        this.webUserLnameF = webUserLnameF;
    }

    public String getWebUserMail() {
        return webUserMail;
    }

    public void setWebUserMail(String webUserMail) {
        this.webUserMail = webUserMail;
    }

    public String getWebUserMobile() {
        return webUserMobile;
    }

    public void setWebUserMobile(String webUserMobile) {
        this.webUserMobile = webUserMobile;
    }

    public String getWebUserNameE() {
        return webUserNameE;
    }

    public void setWebUserNameE(String webUserNameE) {
        this.webUserNameE = webUserNameE;
    }

    public String getWebUserNameF() {
        return webUserNameF;
    }

    public void setWebUserNameF(String webUserNameF) {
        this.webUserNameF = webUserNameF;
    }

    public String getWebUserNationalCode() {
        return webUserNationalCode;
    }

    public void setWebUserNationalCode(String webUserNationalCode) {
        this.webUserNationalCode = webUserNationalCode;
    }

    public String getWebUserOccupation() {
        return webUserOccupation;
    }

    public void setWebUserOccupation(String webUserOccupation) {
        this.webUserOccupation = webUserOccupation;
    }

    public String getWebUserTel() {
        return webUserTel;
    }

    public void setWebUserTel(String webUserTel) {
        this.webUserTel = webUserTel;
    }

}
