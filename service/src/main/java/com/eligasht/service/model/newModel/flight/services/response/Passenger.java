
package com.eligasht.service.model.newModel.flight.services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passenger {

    @SerializedName("AddToClub")
    @Expose
    private Boolean addToClub;
    @SerializedName("AgeRange")
    @Expose
    private String ageRange;
    @SerializedName("AgeRangeHotelID")
    @Expose
    private Integer ageRangeHotelID;
    @SerializedName("AgeRangeID")
    @Expose
    private Integer ageRangeID;
    @SerializedName("ArrivalTrainPrice")
    @Expose
    private Integer arrivalTrainPrice;
    @SerializedName("ArrivalTrainServicePrice")
    @Expose
    private Integer arrivalTrainServicePrice;
    @SerializedName("Birthday")
    @Expose
    private String birthday;
    @SerializedName("CardScore")
    @Expose
    private Integer cardScore;
    @SerializedName("ClubCredit")
    @Expose
    private Integer clubCredit;
    @SerializedName("ClubDiscount")
    @Expose
    private Integer clubDiscount;
    @SerializedName("Commission")
    @Expose
    private Integer commission;
    @SerializedName("CounterCommission")
    @Expose
    private Integer counterCommission;
    @SerializedName("DepartureTrainPrice")
    @Expose
    private Integer departureTrainPrice;
    @SerializedName("DepartureTrainServicePrice")
    @Expose
    private Integer departureTrainServicePrice;
    @SerializedName("Discount")
    @Expose
    private Integer discount;
    @SerializedName("FlightCommission")
    @Expose
    private Integer flightCommission;
    @SerializedName("FlightPrice")
    @Expose
    private Integer flightPrice;
    @SerializedName("Gender")
    @Expose
    private Boolean gender;
    @SerializedName("Guid")
    @Expose
    private String guid;
    @SerializedName("HotelCommission")
    @Expose
    private Integer hotelCommission;
    @SerializedName("HotelPrice")
    @Expose
    private Integer hotelPrice;
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("Index")
    @Expose
    private Integer index;
    @SerializedName("InsCommission")
    @Expose
    private Integer insCommission;
    @SerializedName("InsurancePrice")
    @Expose
    private Integer insurancePrice;
    @SerializedName("IsRegistered")
    @Expose
    private Boolean isRegistered;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("NationalCode")
    @Expose
    private String nationalCode;
    @SerializedName("PackRoomTypeID")
    @Expose
    private Integer packRoomTypeID;
    @SerializedName("PassportExpiration")
    @Expose
    private String passportExpiration;
    @SerializedName("PlusCommission")
    @Expose
    private Integer plusCommission;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("RequestID")
    @Expose
    private Integer requestID;
    @SerializedName("SearchedProductType")
    @Expose
    private Integer searchedProductType;
    @SerializedName("ServiceCommission")
    @Expose
    private Integer serviceCommission;
    @SerializedName("ServicePrice")
    @Expose
    private Integer servicePrice;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("TotalCommission")
    @Expose
    private Integer totalCommission;
    @SerializedName("TotalCounterCom")
    @Expose
    private Integer totalCounterCom;
    @SerializedName("TotalPlusCommission")
    @Expose
    private Integer totalPlusCommission;
    @SerializedName("TotalPrice")
    @Expose
    private Integer totalPrice;
    @SerializedName("VisaCommission")
    @Expose
    private Integer visaCommission;
    @SerializedName("VisaPrice")
    @Expose
    private Integer visaPrice;
    @SerializedName("VisaPriceID")
    @Expose
    private Integer visaPriceID;

    public Boolean getAddToClub() {
        return addToClub;
    }

    public void setAddToClub(Boolean addToClub) {
        this.addToClub = addToClub;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public Integer getAgeRangeHotelID() {
        return ageRangeHotelID;
    }

    public void setAgeRangeHotelID(Integer ageRangeHotelID) {
        this.ageRangeHotelID = ageRangeHotelID;
    }

    public Integer getAgeRangeID() {
        return ageRangeID;
    }

    public void setAgeRangeID(Integer ageRangeID) {
        this.ageRangeID = ageRangeID;
    }

    public Integer getArrivalTrainPrice() {
        return arrivalTrainPrice;
    }

    public void setArrivalTrainPrice(Integer arrivalTrainPrice) {
        this.arrivalTrainPrice = arrivalTrainPrice;
    }

    public Integer getArrivalTrainServicePrice() {
        return arrivalTrainServicePrice;
    }

    public void setArrivalTrainServicePrice(Integer arrivalTrainServicePrice) {
        this.arrivalTrainServicePrice = arrivalTrainServicePrice;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getCardScore() {
        return cardScore;
    }

    public void setCardScore(Integer cardScore) {
        this.cardScore = cardScore;
    }

    public Integer getClubCredit() {
        return clubCredit;
    }

    public void setClubCredit(Integer clubCredit) {
        this.clubCredit = clubCredit;
    }

    public Integer getClubDiscount() {
        return clubDiscount;
    }

    public void setClubDiscount(Integer clubDiscount) {
        this.clubDiscount = clubDiscount;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getCounterCommission() {
        return counterCommission;
    }

    public void setCounterCommission(Integer counterCommission) {
        this.counterCommission = counterCommission;
    }

    public Integer getDepartureTrainPrice() {
        return departureTrainPrice;
    }

    public void setDepartureTrainPrice(Integer departureTrainPrice) {
        this.departureTrainPrice = departureTrainPrice;
    }

    public Integer getDepartureTrainServicePrice() {
        return departureTrainServicePrice;
    }

    public void setDepartureTrainServicePrice(Integer departureTrainServicePrice) {
        this.departureTrainServicePrice = departureTrainServicePrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getFlightCommission() {
        return flightCommission;
    }

    public void setFlightCommission(Integer flightCommission) {
        this.flightCommission = flightCommission;
    }

    public Integer getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(Integer flightPrice) {
        this.flightPrice = flightPrice;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getHotelCommission() {
        return hotelCommission;
    }

    public void setHotelCommission(Integer hotelCommission) {
        this.hotelCommission = hotelCommission;
    }

    public Integer getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(Integer hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getInsCommission() {
        return insCommission;
    }

    public void setInsCommission(Integer insCommission) {
        this.insCommission = insCommission;
    }

    public Integer getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(Integer insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Integer getPackRoomTypeID() {
        return packRoomTypeID;
    }

    public void setPackRoomTypeID(Integer packRoomTypeID) {
        this.packRoomTypeID = packRoomTypeID;
    }

    public String getPassportExpiration() {
        return passportExpiration;
    }

    public void setPassportExpiration(String passportExpiration) {
        this.passportExpiration = passportExpiration;
    }

    public Integer getPlusCommission() {
        return plusCommission;
    }

    public void setPlusCommission(Integer plusCommission) {
        this.plusCommission = plusCommission;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public Integer getSearchedProductType() {
        return searchedProductType;
    }

    public void setSearchedProductType(Integer searchedProductType) {
        this.searchedProductType = searchedProductType;
    }

    public Integer getServiceCommission() {
        return serviceCommission;
    }

    public void setServiceCommission(Integer serviceCommission) {
        this.serviceCommission = serviceCommission;
    }

    public Integer getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Integer servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(Integer totalCommission) {
        this.totalCommission = totalCommission;
    }

    public Integer getTotalCounterCom() {
        return totalCounterCom;
    }

    public void setTotalCounterCom(Integer totalCounterCom) {
        this.totalCounterCom = totalCounterCom;
    }

    public Integer getTotalPlusCommission() {
        return totalPlusCommission;
    }

    public void setTotalPlusCommission(Integer totalPlusCommission) {
        this.totalPlusCommission = totalPlusCommission;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getVisaCommission() {
        return visaCommission;
    }

    public void setVisaCommission(Integer visaCommission) {
        this.visaCommission = visaCommission;
    }

    public Integer getVisaPrice() {
        return visaPrice;
    }

    public void setVisaPrice(Integer visaPrice) {
        this.visaPrice = visaPrice;
    }

    public Integer getVisaPriceID() {
        return visaPriceID;
    }

    public void setVisaPriceID(Integer visaPriceID) {
        this.visaPriceID = visaPriceID;
    }

}
