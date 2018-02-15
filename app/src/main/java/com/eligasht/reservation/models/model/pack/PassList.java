
package com.eligasht.reservation.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassList {

    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("Nationality_ID")
    @Expose
    private String nationalityID;
    @SerializedName("RqPassenger_Address")
    @Expose
    private String rqPassengerAddress;
    @SerializedName("RqPassenger_Birthdate")
    @Expose
    private String rqPassengerBirthdate;
    @SerializedName("RqPassenger_Email")
    @Expose
    private String rqPassengerEmail;
    @SerializedName("RqPassenger_FirstNameEn")
    @Expose
    private String rqPassengerFirstNameEn;
    @SerializedName("RqPassenger_FirstNameFa")
    @Expose
    private String rqPassengerFirstNameFa;
    @SerializedName("RqPassenger_LastNameEn")
    @Expose
    private String rqPassengerLastNameEn;
    @SerializedName("RqPassenger_LastNameFa")
    @Expose
    private String rqPassengerLastNameFa;
    @SerializedName("RqPassenger_Mobile")
    @Expose
    private String rqPassengerMobile;
    @SerializedName("RqPassenger_NationalCode")
    @Expose
    private String rqPassengerNationalCode;
    @SerializedName("RqPassenger_PassExpDate")
    @Expose
    private String rqPassengerPassExpDate;
    @SerializedName("RqPassenger_PassNo")
    @Expose
    private String rqPassengerPassNo;
    @SerializedName("RqPassenger_Tel")
    @Expose
    private String rqPassengerTel;
    @SerializedName("PackRoomType_ID")
    @Expose
    private Integer packRoomTypeID;
    @SerializedName("Room_No")
    @Expose
    private Integer roomNo;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalityID() {
        return nationalityID;
    }

    public void setNationalityID(String nationalityID) {
        this.nationalityID = nationalityID;
    }

    public String getRqPassengerAddress() {
        return rqPassengerAddress;
    }

    public void setRqPassengerAddress(String rqPassengerAddress) {
        this.rqPassengerAddress = rqPassengerAddress;
    }

    public String getRqPassengerBirthdate() {
        return rqPassengerBirthdate;
    }

    public void setRqPassengerBirthdate(String rqPassengerBirthdate) {
        this.rqPassengerBirthdate = rqPassengerBirthdate;
    }

    public String getRqPassengerEmail() {
        return rqPassengerEmail;
    }

    public void setRqPassengerEmail(String rqPassengerEmail) {
        this.rqPassengerEmail = rqPassengerEmail;
    }

    public String getRqPassengerFirstNameEn() {
        return rqPassengerFirstNameEn;
    }

    public void setRqPassengerFirstNameEn(String rqPassengerFirstNameEn) {
        this.rqPassengerFirstNameEn = rqPassengerFirstNameEn;
    }

    public String getRqPassengerFirstNameFa() {
        return rqPassengerFirstNameFa;
    }

    public void setRqPassengerFirstNameFa(String rqPassengerFirstNameFa) {
        this.rqPassengerFirstNameFa = rqPassengerFirstNameFa;
    }

    public String getRqPassengerLastNameEn() {
        return rqPassengerLastNameEn;
    }

    public void setRqPassengerLastNameEn(String rqPassengerLastNameEn) {
        this.rqPassengerLastNameEn = rqPassengerLastNameEn;
    }

    public String getRqPassengerLastNameFa() {
        return rqPassengerLastNameFa;
    }

    public void setRqPassengerLastNameFa(String rqPassengerLastNameFa) {
        this.rqPassengerLastNameFa = rqPassengerLastNameFa;
    }

    public String getRqPassengerMobile() {
        return rqPassengerMobile;
    }

    public void setRqPassengerMobile(String rqPassengerMobile) {
        this.rqPassengerMobile = rqPassengerMobile;
    }

    public String getRqPassengerNationalCode() {
        return rqPassengerNationalCode;
    }

    public void setRqPassengerNationalCode(String rqPassengerNationalCode) {
        this.rqPassengerNationalCode = rqPassengerNationalCode;
    }

    public String getRqPassengerPassExpDate() {
        return rqPassengerPassExpDate;
    }

    public void setRqPassengerPassExpDate(String rqPassengerPassExpDate) {
        this.rqPassengerPassExpDate = rqPassengerPassExpDate;
    }

    public String getRqPassengerPassNo() {
        return rqPassengerPassNo;
    }

    public void setRqPassengerPassNo(String rqPassengerPassNo) {
        this.rqPassengerPassNo = rqPassengerPassNo;
    }

    public String getRqPassengerTel() {
        return rqPassengerTel;
    }

    public void setRqPassengerTel(String rqPassengerTel) {
        this.rqPassengerTel = rqPassengerTel;
    }

    public Integer getPackRoomTypeID() {
        return packRoomTypeID;
    }

    public void setPackRoomTypeID(Integer packRoomTypeID) {
        this.packRoomTypeID = packRoomTypeID;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

}
