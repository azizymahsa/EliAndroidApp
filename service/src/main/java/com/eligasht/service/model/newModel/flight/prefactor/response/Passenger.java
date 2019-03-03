
package com.eligasht.service.model.newModel.flight.prefactor.response;

import com.eligasht.service.model.newModel.hotel.purchase.request.Warnings;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Passenger {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("Guid")
    @Expose
    private String guid;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("FirstNameEn")
    @Expose
    private String firstNameEn;
    @SerializedName("LastNameEn")
    @Expose
    private String lastNameEn;
    @SerializedName("LastNameFa")
    @Expose
    private String lastNameFa;
    @SerializedName("FirstNameFa")
    @Expose
    private String firstNameFa;
    @SerializedName("Gender")
    @Expose
    private Boolean gender;
    @SerializedName("NationalCode")
    @Expose
    private String nationalCode;
    @SerializedName("PassportNo")
    @Expose
    private String passportNo;
    @SerializedName("Tel")
    @Expose
    private String tel;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Birthday")
    @Expose
    private String birthday;
    @SerializedName("ClubCode")
    @Expose
    private String clubCode;
    @SerializedName("Insurance")
    @Expose
    private String insurance;
    @SerializedName("PassportExpiration")
    @Expose
    private String passportExpiration;
    @SerializedName("Visa")
    @Expose
    private String visa;
    @SerializedName("VisaPrice")
    @Expose
    private Integer visaPrice;
    @SerializedName("VisaCommission")
    @Expose
    private Integer visaCommission;
    @SerializedName("VisaPriceID")
    @Expose
    private Integer visaPriceID;
    @SerializedName("CountryOfResidence")
    @Expose
    private String countryOfResidence;
    @SerializedName("NationalityFa")
    @Expose
    private String nationalityFa;
    @SerializedName("Nationality")
    @Expose
    private String nationality;
    @SerializedName("NationalityCode")
    @Expose
    private String nationalityCode;
    @SerializedName("NationalityID")
    @Expose
    private String nationalityID;
    @SerializedName("Occupency")
    @Expose
    private String occupency;
    @SerializedName("IsRegistered")
    @Expose
    private Boolean isRegistered;
    @SerializedName("AddToClub")
    @Expose
    private Boolean addToClub;
    @SerializedName("RoomNo")
    @Expose
    private String roomNo;
    @SerializedName("RoomID")
    @Expose
    private String roomID;
    @SerializedName("AgeRangeID")
    @Expose
    private Integer ageRangeID;
    @SerializedName("AgeRange")
    @Expose
    private String ageRange;
    @SerializedName("AgeRangeHotelID")
    @Expose
    private Integer ageRangeHotelID;
    @SerializedName("AgeRangeHotel")
    @Expose
    private String ageRangeHotel;
    @SerializedName("PackRowRoomTypeID")
    @Expose
    private String packRowRoomTypeID;
    @SerializedName("PackRoomTypeID")
    @Expose
    private Integer packRoomTypeID;
    @SerializedName("InsurancePlaneCode")
    @Expose
    private String insurancePlaneCode;
    @SerializedName("InsurancePlanePrice")
    @Expose
    private String insurancePlanePrice;
    @SerializedName("SearchedProductType")
    @Expose
    private Integer searchedProductType;
    @SerializedName("Index")
    @Expose
    private Integer index;
    @SerializedName("RequestID")
    @Expose
    private Integer requestID;
    @SerializedName("IDcardNo")
    @Expose
    private String iDcardNo;
    @SerializedName("ClubCardID")
    @Expose
    private String clubCardID;
    @SerializedName("ClubCardNumber")
    @Expose
    private String clubCardNumber;
    @SerializedName("CardScore")
    @Expose
    private Integer cardScore;
    @SerializedName("CounterCommission")
    @Expose
    private Integer counterCommission;
    @SerializedName("Discount")
    @Expose
    private Integer discount;
    @SerializedName("PlusCommission")
    @Expose
    private Integer plusCommission;
    @SerializedName("HotelPrice")
    @Expose
    private Integer hotelPrice;
    @SerializedName("HotelCommission")
    @Expose
    private Integer hotelCommission;
    @SerializedName("ServicePrice")
    @Expose
    private Integer servicePrice;
    @SerializedName("ServiceCommission")
    @Expose
    private Integer serviceCommission;
    @SerializedName("TotalCounterCom")
    @Expose
    private Integer totalCounterCom;
    @SerializedName("TotalCommission")
    @Expose
    private Integer totalCommission;
    @SerializedName("TotalPlusCommission")
    @Expose
    private Integer totalPlusCommission;
    @SerializedName("FlightPrice")
    @Expose
    private Integer flightPrice;
    @SerializedName("FlightCommission")
    @Expose
    private Integer flightCommission;
    @SerializedName("InsurancePrice")
    @Expose
    private Integer insurancePrice;
    @SerializedName("InsCommission")
    @Expose
    private Integer insCommission;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("Commission")
    @Expose
    private Integer commission;
    @SerializedName("TotalPrice")
    @Expose
    private Integer totalPrice;
    @SerializedName("ClubCredit")
    @Expose
    private Integer clubCredit;
    @SerializedName("ClubDiscount")
    @Expose
    private Integer clubDiscount;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("DepService")
    @Expose
    private String depService;
    @SerializedName("ArrService")
    @Expose
    private String arrService;
    @SerializedName("DepartureTrainPrice")
    @Expose
    private Integer departureTrainPrice;
    @SerializedName("ArrivalTrainPrice")
    @Expose
    private Integer arrivalTrainPrice;
    @SerializedName("DepartureTrainServicePrice")
    @Expose
    private Integer departureTrainServicePrice;
    @SerializedName("ArrivalTrainServicePrice")
    @Expose
    private Integer arrivalTrainServicePrice;
    @SerializedName("Errors")
    @Expose
    private List<Error> errors = null;
    @SerializedName("Warningss")
    @Expose
    private List<Warnings> warningss = null;
    @SerializedName("Comments")
    @Expose
    private com.eligasht.service.model.newModel.hotel.purchase.request.Comments comments;
    @SerializedName("ResultKey")
    @Expose
    private String resultKey;
    @SerializedName("TimeStamp")
    @Expose
    private String timeStamp;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLastNameFa() {
        return lastNameFa;
    }

    public void setLastNameFa(String lastNameFa) {
        this.lastNameFa = lastNameFa;
    }

    public String getFirstNameFa() {
        return firstNameFa;
    }

    public void setFirstNameFa(String firstNameFa) {
        this.firstNameFa = firstNameFa;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getClubCode() {
        return clubCode;
    }

    public void setClubCode(String clubCode) {
        this.clubCode = clubCode;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getPassportExpiration() {
        return passportExpiration;
    }

    public void setPassportExpiration(String passportExpiration) {
        this.passportExpiration = passportExpiration;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public Integer getVisaPrice() {
        return visaPrice;
    }

    public void setVisaPrice(Integer visaPrice) {
        this.visaPrice = visaPrice;
    }

    public Integer getVisaCommission() {
        return visaCommission;
    }

    public void setVisaCommission(Integer visaCommission) {
        this.visaCommission = visaCommission;
    }

    public Integer getVisaPriceID() {
        return visaPriceID;
    }

    public void setVisaPriceID(Integer visaPriceID) {
        this.visaPriceID = visaPriceID;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public String getNationalityFa() {
        return nationalityFa;
    }

    public void setNationalityFa(String nationalityFa) {
        this.nationalityFa = nationalityFa;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public String getNationalityID() {
        return nationalityID;
    }

    public void setNationalityID(String nationalityID) {
        this.nationalityID = nationalityID;
    }

    public String getOccupency() {
        return occupency;
    }

    public void setOccupency(String occupency) {
        this.occupency = occupency;
    }

    public Boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public Boolean getAddToClub() {
        return addToClub;
    }

    public void setAddToClub(Boolean addToClub) {
        this.addToClub = addToClub;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Integer getAgeRangeID() {
        return ageRangeID;
    }

    public void setAgeRangeID(Integer ageRangeID) {
        this.ageRangeID = ageRangeID;
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

    public String getAgeRangeHotel() {
        return ageRangeHotel;
    }

    public void setAgeRangeHotel(String ageRangeHotel) {
        this.ageRangeHotel = ageRangeHotel;
    }

    public String getPackRowRoomTypeID() {
        return packRowRoomTypeID;
    }

    public void setPackRowRoomTypeID(String packRowRoomTypeID) {
        this.packRowRoomTypeID = packRowRoomTypeID;
    }

    public Integer getPackRoomTypeID() {
        return packRoomTypeID;
    }

    public void setPackRoomTypeID(Integer packRoomTypeID) {
        this.packRoomTypeID = packRoomTypeID;
    }

    public String getInsurancePlaneCode() {
        return insurancePlaneCode;
    }

    public void setInsurancePlaneCode(String insurancePlaneCode) {
        this.insurancePlaneCode = insurancePlaneCode;
    }

    public String getInsurancePlanePrice() {
        return insurancePlanePrice;
    }

    public void setInsurancePlanePrice(String insurancePlanePrice) {
        this.insurancePlanePrice = insurancePlanePrice;
    }

    public Integer getSearchedProductType() {
        return searchedProductType;
    }

    public void setSearchedProductType(Integer searchedProductType) {
        this.searchedProductType = searchedProductType;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public String getIDcardNo() {
        return iDcardNo;
    }

    public void setIDcardNo(String iDcardNo) {
        this.iDcardNo = iDcardNo;
    }

    public String getClubCardID() {
        return clubCardID;
    }

    public void setClubCardID(String clubCardID) {
        this.clubCardID = clubCardID;
    }

    public String getClubCardNumber() {
        return clubCardNumber;
    }

    public void setClubCardNumber(String clubCardNumber) {
        this.clubCardNumber = clubCardNumber;
    }

    public Integer getCardScore() {
        return cardScore;
    }

    public void setCardScore(Integer cardScore) {
        this.cardScore = cardScore;
    }

    public Integer getCounterCommission() {
        return counterCommission;
    }

    public void setCounterCommission(Integer counterCommission) {
        this.counterCommission = counterCommission;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getPlusCommission() {
        return plusCommission;
    }

    public void setPlusCommission(Integer plusCommission) {
        this.plusCommission = plusCommission;
    }

    public Integer getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(Integer hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public Integer getHotelCommission() {
        return hotelCommission;
    }

    public void setHotelCommission(Integer hotelCommission) {
        this.hotelCommission = hotelCommission;
    }

    public Integer getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Integer servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Integer getServiceCommission() {
        return serviceCommission;
    }

    public void setServiceCommission(Integer serviceCommission) {
        this.serviceCommission = serviceCommission;
    }

    public Integer getTotalCounterCom() {
        return totalCounterCom;
    }

    public void setTotalCounterCom(Integer totalCounterCom) {
        this.totalCounterCom = totalCounterCom;
    }

    public Integer getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(Integer totalCommission) {
        this.totalCommission = totalCommission;
    }

    public Integer getTotalPlusCommission() {
        return totalPlusCommission;
    }

    public void setTotalPlusCommission(Integer totalPlusCommission) {
        this.totalPlusCommission = totalPlusCommission;
    }

    public Integer getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(Integer flightPrice) {
        this.flightPrice = flightPrice;
    }

    public Integer getFlightCommission() {
        return flightCommission;
    }

    public void setFlightCommission(Integer flightCommission) {
        this.flightCommission = flightCommission;
    }

    public Integer getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(Integer insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Integer getInsCommission() {
        return insCommission;
    }

    public void setInsCommission(Integer insCommission) {
        this.insCommission = insCommission;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepService() {
        return depService;
    }

    public void setDepService(String depService) {
        this.depService = depService;
    }

    public String getArrService() {
        return arrService;
    }

    public void setArrService(String arrService) {
        this.arrService = arrService;
    }

    public Integer getDepartureTrainPrice() {
        return departureTrainPrice;
    }

    public void setDepartureTrainPrice(Integer departureTrainPrice) {
        this.departureTrainPrice = departureTrainPrice;
    }

    public Integer getArrivalTrainPrice() {
        return arrivalTrainPrice;
    }

    public void setArrivalTrainPrice(Integer arrivalTrainPrice) {
        this.arrivalTrainPrice = arrivalTrainPrice;
    }

    public Integer getDepartureTrainServicePrice() {
        return departureTrainServicePrice;
    }

    public void setDepartureTrainServicePrice(Integer departureTrainServicePrice) {
        this.departureTrainServicePrice = departureTrainServicePrice;
    }

    public Integer getArrivalTrainServicePrice() {
        return arrivalTrainServicePrice;
    }

    public void setArrivalTrainServicePrice(Integer arrivalTrainServicePrice) {
        this.arrivalTrainServicePrice = arrivalTrainServicePrice;
    }

    public List<java.lang.Error> getErrors() {
        return errors;
    }

    public void setErrors(List<java.lang.Error> errors) {
        this.errors = errors;
    }

    public List<Warnings> getWarningss() {
        return warningss;
    }

    public void setWarningss(List<Warnings> warningss) {
        this.warningss = warningss;
    }

    public com.eligasht.service.model.newModel.hotel.purchase.request.Comments getComments() {
        return comments;
    }

    public void setComments(com.eligasht.service.model.newModel.hotel.purchase.request.Comments comments) {
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
