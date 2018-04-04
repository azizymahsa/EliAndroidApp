
package com.eligasht.service.model.flight.response.PreFactorDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPassenger {

    @SerializedName("AddToClub")
    @Expose
    private Boolean addToClub;
    @SerializedName("AgeRangeHotel")
    @Expose
    private Object ageRangeHotel;
    @SerializedName("AgeRangeHotel_ID")
    @Expose
    private Integer ageRangeHotelID;
    @SerializedName("Card_Number")
    @Expose
    private String cardNumber;
    @SerializedName("Com")
    @Expose
    private Integer com;
    @SerializedName("Gender")
    @Expose
    private Boolean gender;
    @SerializedName("IsRegistered")
    @Expose
    private Boolean isRegistered;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("Nationality_ID")
    @Expose
    private Object nationalityID;
    @SerializedName("PackRoomType_ID")
    @Expose
    private Integer packRoomTypeID;
    @SerializedName("PassIndex")
    @Expose
    private Integer passIndex;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("Room_No")
    @Expose
    private Integer roomNo;
    @SerializedName("RqBase_ID")
    @Expose
    private Integer rqBaseID;
    @SerializedName("RqPassenger_Address")
    @Expose
    private Object rqPassengerAddress;
    @SerializedName("RqPassenger_Birthdate")
    @Expose
    private String rqPassengerBirthdate;
    @SerializedName("RqPassenger_Com")
    @Expose
    private Integer rqPassengerCom;
    @SerializedName("RqPassenger_ComPlus")
    @Expose
    private Integer rqPassengerComPlus;
    @SerializedName("RqPassenger_CounterCom")
    @Expose
    private Integer rqPassengerCounterCom;
    @SerializedName("RqPassenger_Discount")
    @Expose
    private Integer rqPassengerDiscount;
    @SerializedName("RqPassenger_Email")
    @Expose
    private Object rqPassengerEmail;
    @SerializedName("RqPassenger_FirstNameEn")
    @Expose
    private Object rqPassengerFirstNameEn;
    @SerializedName("RqPassenger_FirstNameFa")
    @Expose
    private Object rqPassengerFirstNameFa;
    @SerializedName("RqPassenger_FltCom")
    @Expose
    private Integer rqPassengerFltCom;
    @SerializedName("RqPassenger_FltPrice")
    @Expose
    private Integer rqPassengerFltPrice;
    @SerializedName("RqPassenger_HotelCom")
    @Expose
    private Integer rqPassengerHotelCom;
    @SerializedName("RqPassenger_HotelPrice")
    @Expose
    private Integer rqPassengerHotelPrice;
    @SerializedName("RqPassenger_ID")
    @Expose
    private Integer rqPassengerID;
    @SerializedName("RqPassenger_IDcardNo")
    @Expose
    private Object rqPassengerIDcardNo;
    @SerializedName("RqPassenger_InsCom")
    @Expose
    private Integer rqPassengerInsCom;
    @SerializedName("RqPassenger_InsPrice")
    @Expose
    private Integer rqPassengerInsPrice;
    @SerializedName("RqPassenger_LastNameEn")
    @Expose
    private Object rqPassengerLastNameEn;
    @SerializedName("RqPassenger_LastNameFa")
    @Expose
    private Object rqPassengerLastNameFa;
    @SerializedName("RqPassenger_Mobile")
    @Expose
    private Object rqPassengerMobile;
    @SerializedName("RqPassenger_NationalCode")
    @Expose
    private Object rqPassengerNationalCode;
    @SerializedName("RqPassenger_PassExpDate")
    @Expose
    private String rqPassengerPassExpDate;
    @SerializedName("RqPassenger_PassNo")
    @Expose
    private String rqPassengerPassNo;
    @SerializedName("RqPassenger_ServiceCom")
    @Expose
    private Integer rqPassengerServiceCom;
    @SerializedName("RqPassenger_ServicePrice")
    @Expose
    private Integer rqPassengerServicePrice;
    @SerializedName("RqPassenger_Tel")
    @Expose
    private Object rqPassengerTel;
    @SerializedName("RqPassenger_TotalCom")
    @Expose
    private Integer rqPassengerTotalCom;
    @SerializedName("RqPassenger_TotalCounterCom")
    @Expose
    private Integer rqPassengerTotalCounterCom;
    @SerializedName("RqPassenger_TotalPlusCom")
    @Expose
    private Integer rqPassengerTotalPlusCom;
    @SerializedName("RqPassenger_TotalPrice")
    @Expose
    private Integer rqPassengerTotalPrice;
    @SerializedName("RqPassenger_VisaCom")
    @Expose
    private Integer rqPassengerVisaCom;
    @SerializedName("RqPassenger_VisaPrice")
    @Expose
    private Integer rqPassengerVisaPrice;
    @SerializedName("RqPassenger_name")
    @Expose
    private String rqPassengerName;
    @SerializedName("RqPassenger_plusCom")
    @Expose
    private Integer rqPassengerPlusCom;
    @SerializedName("VisaPrice_ID")
    @Expose
    private Integer visaPriceID;
    @SerializedName("customer_TRCredit")
    @Expose
    private Integer customerTRCredit;
    @SerializedName("customer_TRDiscount")
    @Expose
    private Integer customerTRDiscount;

    public Boolean getAddToClub() {
        return addToClub;
    }

    public void setAddToClub(Boolean addToClub) {
        this.addToClub = addToClub;
    }

    public Object getAgeRangeHotel() {
        return ageRangeHotel;
    }

    public void setAgeRangeHotel(Object ageRangeHotel) {
        this.ageRangeHotel = ageRangeHotel;
    }

    public Integer getAgeRangeHotelID() {
        return ageRangeHotelID;
    }

    public void setAgeRangeHotelID(Integer ageRangeHotelID) {
        this.ageRangeHotelID = ageRangeHotelID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCom() {
        return com;
    }

    public void setCom(Integer com) {
        this.com = com;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Object getNationalityID() {
        return nationalityID;
    }

    public void setNationalityID(Object nationalityID) {
        this.nationalityID = nationalityID;
    }

    public Integer getPackRoomTypeID() {
        return packRoomTypeID;
    }

    public void setPackRoomTypeID(Integer packRoomTypeID) {
        this.packRoomTypeID = packRoomTypeID;
    }

    public Integer getPassIndex() {
        return passIndex;
    }

    public void setPassIndex(Integer passIndex) {
        this.passIndex = passIndex;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getRqBaseID() {
        return rqBaseID;
    }

    public void setRqBaseID(Integer rqBaseID) {
        this.rqBaseID = rqBaseID;
    }

    public Object getRqPassengerAddress() {
        return rqPassengerAddress;
    }

    public void setRqPassengerAddress(Object rqPassengerAddress) {
        this.rqPassengerAddress = rqPassengerAddress;
    }

    public String getRqPassengerBirthdate() {
        return rqPassengerBirthdate;
    }

    public void setRqPassengerBirthdate(String rqPassengerBirthdate) {
        this.rqPassengerBirthdate = rqPassengerBirthdate;
    }

    public Integer getRqPassengerCom() {
        return rqPassengerCom;
    }

    public void setRqPassengerCom(Integer rqPassengerCom) {
        this.rqPassengerCom = rqPassengerCom;
    }

    public Integer getRqPassengerComPlus() {
        return rqPassengerComPlus;
    }

    public void setRqPassengerComPlus(Integer rqPassengerComPlus) {
        this.rqPassengerComPlus = rqPassengerComPlus;
    }

    public Integer getRqPassengerCounterCom() {
        return rqPassengerCounterCom;
    }

    public void setRqPassengerCounterCom(Integer rqPassengerCounterCom) {
        this.rqPassengerCounterCom = rqPassengerCounterCom;
    }

    public Integer getRqPassengerDiscount() {
        return rqPassengerDiscount;
    }

    public void setRqPassengerDiscount(Integer rqPassengerDiscount) {
        this.rqPassengerDiscount = rqPassengerDiscount;
    }

    public Object getRqPassengerEmail() {
        return rqPassengerEmail;
    }

    public void setRqPassengerEmail(Object rqPassengerEmail) {
        this.rqPassengerEmail = rqPassengerEmail;
    }

    public Object getRqPassengerFirstNameEn() {
        return rqPassengerFirstNameEn;
    }

    public void setRqPassengerFirstNameEn(Object rqPassengerFirstNameEn) {
        this.rqPassengerFirstNameEn = rqPassengerFirstNameEn;
    }

    public Object getRqPassengerFirstNameFa() {
        return rqPassengerFirstNameFa;
    }

    public void setRqPassengerFirstNameFa(Object rqPassengerFirstNameFa) {
        this.rqPassengerFirstNameFa = rqPassengerFirstNameFa;
    }

    public Integer getRqPassengerFltCom() {
        return rqPassengerFltCom;
    }

    public void setRqPassengerFltCom(Integer rqPassengerFltCom) {
        this.rqPassengerFltCom = rqPassengerFltCom;
    }

    public Integer getRqPassengerFltPrice() {
        return rqPassengerFltPrice;
    }

    public void setRqPassengerFltPrice(Integer rqPassengerFltPrice) {
        this.rqPassengerFltPrice = rqPassengerFltPrice;
    }

    public Integer getRqPassengerHotelCom() {
        return rqPassengerHotelCom;
    }

    public void setRqPassengerHotelCom(Integer rqPassengerHotelCom) {
        this.rqPassengerHotelCom = rqPassengerHotelCom;
    }

    public Integer getRqPassengerHotelPrice() {
        return rqPassengerHotelPrice;
    }

    public void setRqPassengerHotelPrice(Integer rqPassengerHotelPrice) {
        this.rqPassengerHotelPrice = rqPassengerHotelPrice;
    }

    public Integer getRqPassengerID() {
        return rqPassengerID;
    }

    public void setRqPassengerID(Integer rqPassengerID) {
        this.rqPassengerID = rqPassengerID;
    }

    public Object getRqPassengerIDcardNo() {
        return rqPassengerIDcardNo;
    }

    public void setRqPassengerIDcardNo(Object rqPassengerIDcardNo) {
        this.rqPassengerIDcardNo = rqPassengerIDcardNo;
    }

    public Integer getRqPassengerInsCom() {
        return rqPassengerInsCom;
    }

    public void setRqPassengerInsCom(Integer rqPassengerInsCom) {
        this.rqPassengerInsCom = rqPassengerInsCom;
    }

    public Integer getRqPassengerInsPrice() {
        return rqPassengerInsPrice;
    }

    public void setRqPassengerInsPrice(Integer rqPassengerInsPrice) {
        this.rqPassengerInsPrice = rqPassengerInsPrice;
    }

    public Object getRqPassengerLastNameEn() {
        return rqPassengerLastNameEn;
    }

    public void setRqPassengerLastNameEn(Object rqPassengerLastNameEn) {
        this.rqPassengerLastNameEn = rqPassengerLastNameEn;
    }

    public Object getRqPassengerLastNameFa() {
        return rqPassengerLastNameFa;
    }

    public void setRqPassengerLastNameFa(Object rqPassengerLastNameFa) {
        this.rqPassengerLastNameFa = rqPassengerLastNameFa;
    }

    public Object getRqPassengerMobile() {
        return rqPassengerMobile;
    }

    public void setRqPassengerMobile(Object rqPassengerMobile) {
        this.rqPassengerMobile = rqPassengerMobile;
    }

    public Object getRqPassengerNationalCode() {
        return rqPassengerNationalCode;
    }

    public void setRqPassengerNationalCode(Object rqPassengerNationalCode) {
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

    public Integer getRqPassengerServiceCom() {
        return rqPassengerServiceCom;
    }

    public void setRqPassengerServiceCom(Integer rqPassengerServiceCom) {
        this.rqPassengerServiceCom = rqPassengerServiceCom;
    }

    public Integer getRqPassengerServicePrice() {
        return rqPassengerServicePrice;
    }

    public void setRqPassengerServicePrice(Integer rqPassengerServicePrice) {
        this.rqPassengerServicePrice = rqPassengerServicePrice;
    }

    public Object getRqPassengerTel() {
        return rqPassengerTel;
    }

    public void setRqPassengerTel(Object rqPassengerTel) {
        this.rqPassengerTel = rqPassengerTel;
    }

    public Integer getRqPassengerTotalCom() {
        return rqPassengerTotalCom;
    }

    public void setRqPassengerTotalCom(Integer rqPassengerTotalCom) {
        this.rqPassengerTotalCom = rqPassengerTotalCom;
    }

    public Integer getRqPassengerTotalCounterCom() {
        return rqPassengerTotalCounterCom;
    }

    public void setRqPassengerTotalCounterCom(Integer rqPassengerTotalCounterCom) {
        this.rqPassengerTotalCounterCom = rqPassengerTotalCounterCom;
    }

    public Integer getRqPassengerTotalPlusCom() {
        return rqPassengerTotalPlusCom;
    }

    public void setRqPassengerTotalPlusCom(Integer rqPassengerTotalPlusCom) {
        this.rqPassengerTotalPlusCom = rqPassengerTotalPlusCom;
    }

    public Integer getRqPassengerTotalPrice() {
        return rqPassengerTotalPrice;
    }

    public void setRqPassengerTotalPrice(Integer rqPassengerTotalPrice) {
        this.rqPassengerTotalPrice = rqPassengerTotalPrice;
    }

    public Integer getRqPassengerVisaCom() {
        return rqPassengerVisaCom;
    }

    public void setRqPassengerVisaCom(Integer rqPassengerVisaCom) {
        this.rqPassengerVisaCom = rqPassengerVisaCom;
    }

    public Integer getRqPassengerVisaPrice() {
        return rqPassengerVisaPrice;
    }

    public void setRqPassengerVisaPrice(Integer rqPassengerVisaPrice) {
        this.rqPassengerVisaPrice = rqPassengerVisaPrice;
    }

    public String getRqPassengerName() {
        return rqPassengerName;
    }

    public void setRqPassengerName(String rqPassengerName) {
        this.rqPassengerName = rqPassengerName;
    }

    public Integer getRqPassengerPlusCom() {
        return rqPassengerPlusCom;
    }

    public void setRqPassengerPlusCom(Integer rqPassengerPlusCom) {
        this.rqPassengerPlusCom = rqPassengerPlusCom;
    }

    public Integer getVisaPriceID() {
        return visaPriceID;
    }

    public void setVisaPriceID(Integer visaPriceID) {
        this.visaPriceID = visaPriceID;
    }

    public Integer getCustomerTRCredit() {
        return customerTRCredit;
    }

    public void setCustomerTRCredit(Integer customerTRCredit) {
        this.customerTRCredit = customerTRCredit;
    }

    public Integer getCustomerTRDiscount() {
        return customerTRDiscount;
    }

    public void setCustomerTRDiscount(Integer customerTRDiscount) {
        this.customerTRDiscount = customerTRDiscount;
    }

}
