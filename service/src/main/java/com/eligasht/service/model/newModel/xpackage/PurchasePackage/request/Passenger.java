
package com.eligasht.service.model.newModel.xpackage.PurchasePackage.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passenger {

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
    @SerializedName("RoomNo")
    @Expose
    private String roomNo;
    @SerializedName("PassportExpiration")
    @Expose
    private String passportExpiration;
    @SerializedName("PackRowRoomTypeID")
    @Expose
    private Integer packRowRoomTypeID;
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


    @SerializedName("FirstNameFa")
    @Expose
    private String FirstNameFa;

    public String getFirstNameFa() {
        return FirstNameFa;
    }

    public void setFirstNameFa(String firstNameFa) {
        FirstNameFa = firstNameFa;
    }

    public String getLastNameFa() {
        return LastNameFa;
    }

    public void setLastNameFa(String lastNameFa) {
        LastNameFa = lastNameFa;
    }

    @SerializedName("LastNameFa")
    @Expose
    private String LastNameFa;

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

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getPassportExpiration() {
        return passportExpiration;
    }

    public void setPassportExpiration(String passportExpiration) {
        this.passportExpiration = passportExpiration;
    }

    public Integer getPackRowRoomTypeID() {
        return packRowRoomTypeID;
    }

    public void setPackRowRoomTypeID(Integer packRowRoomTypeID) {
        this.packRowRoomTypeID = packRowRoomTypeID;
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
