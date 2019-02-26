
package com.eligasht.service.model.newModel.hotel.preSearch.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryModel {

    @SerializedName("IsSearchRequest")
    @Expose
    private Boolean isSearchRequest;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("Trip")
    @Expose
    private String trip;
    @SerializedName("Source")
    @Expose
    private String source;
    @SerializedName("SourceText")
    @Expose
    private String sourceText;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("DestinationText")
    @Expose
    private String destinationText;
    @SerializedName("Child")
    @Expose
    private String child;
    @SerializedName("Adult")
    @Expose
    private String adult;
    @SerializedName("Infant")
    @Expose
    private String infant;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("CheckOut")
    @Expose
    private String checkOut;
    @SerializedName("CheckIn")
    @Expose
    private String checkIn;
    @SerializedName("Rooms")
    @Expose
    private String rooms;
    @SerializedName("NationalityCode")
    @Expose
    private String nationalityCode;
    @SerializedName("ResidenceCode")
    @Expose
    private String residenceCode;
    @SerializedName("PreferredClass")
    @Expose
    private String preferredClass;
    @SerializedName("PreferedAir")
    @Expose
    private String preferedAir;
    @SerializedName("packRowId")
    @Expose
    private String packRowId;
    @SerializedName("packXferIds")
    @Expose
    private String packXferIds;
    @SerializedName("flightIds")
    @Expose
    private String flightIds;
    @SerializedName("Departure")
    @Expose
    private String departure;
    @SerializedName("DeparturePackage")
    @Expose
    private String departurePackage;
    @SerializedName("ExtraCheckDate")
    @Expose
    private String extraCheckDate;
    @SerializedName("TravelDuration")
    @Expose
    private String travelDuration;
    @SerializedName("BirthDay")
    @Expose
    private String birthDay;
    @SerializedName("HotelofferId")
    @Expose
    private String hotelofferId;
    @SerializedName("FlightOfferId")
    @Expose
    private String flightOfferId;
    @SerializedName("preSearch")
    @Expose
    private String preSearch;
    @SerializedName("hotelId")
    @Expose
    private String hotelId;
    @SerializedName("preSearchUniqueId")
    @Expose
    private String preSearchUniqueId;
    @SerializedName("domesticCategory")
    @Expose
    private String domesticCategory;
    @SerializedName("searchKey")
    @Expose
    private String searchKey;
    @SerializedName("flightGuid")
    @Expose
    private String flightGuid;
    @SerializedName("CurrentCulture")
    @Expose
    private String currentCulture;
    @SerializedName("ChangeDay")
    @Expose
    private String changeDay;
    @SerializedName("PType")
    @Expose
    private String pType;
    @SerializedName("ExclusiveTrain")
    @Expose
    private Boolean exclusiveTrain;
    @SerializedName("Key")
    @Expose
    private String key;

    public Boolean getIsSearchRequest() {
        return isSearchRequest;
    }

    public void setIsSearchRequest(Boolean isSearchRequest) {
        this.isSearchRequest = isSearchRequest;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationText() {
        return destinationText;
    }

    public void setDestinationText(String destinationText) {
        this.destinationText = destinationText;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getInfant() {
        return infant;
    }

    public void setInfant(String infant) {
        this.infant = infant;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public String getResidenceCode() {
        return residenceCode;
    }

    public void setResidenceCode(String residenceCode) {
        this.residenceCode = residenceCode;
    }

    public String getPreferredClass() {
        return preferredClass;
    }

    public void setPreferredClass(String preferredClass) {
        this.preferredClass = preferredClass;
    }

    public String getPreferedAir() {
        return preferedAir;
    }

    public void setPreferedAir(String preferedAir) {
        this.preferedAir = preferedAir;
    }

    public String getPackRowId() {
        return packRowId;
    }

    public void setPackRowId(String packRowId) {
        this.packRowId = packRowId;
    }

    public String getPackXferIds() {
        return packXferIds;
    }

    public void setPackXferIds(String packXferIds) {
        this.packXferIds = packXferIds;
    }

    public String getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(String flightIds) {
        this.flightIds = flightIds;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDeparturePackage() {
        return departurePackage;
    }

    public void setDeparturePackage(String departurePackage) {
        this.departurePackage = departurePackage;
    }

    public String getExtraCheckDate() {
        return extraCheckDate;
    }

    public void setExtraCheckDate(String extraCheckDate) {
        this.extraCheckDate = extraCheckDate;
    }

    public String getTravelDuration() {
        return travelDuration;
    }

    public void setTravelDuration(String travelDuration) {
        this.travelDuration = travelDuration;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getHotelofferId() {
        return hotelofferId;
    }

    public void setHotelofferId(String hotelofferId) {
        this.hotelofferId = hotelofferId;
    }

    public String getFlightOfferId() {
        return flightOfferId;
    }

    public void setFlightOfferId(String flightOfferId) {
        this.flightOfferId = flightOfferId;
    }

    public String getPreSearch() {
        return preSearch;
    }

    public void setPreSearch(String preSearch) {
        this.preSearch = preSearch;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getPreSearchUniqueId() {
        return preSearchUniqueId;
    }

    public void setPreSearchUniqueId(String preSearchUniqueId) {
        this.preSearchUniqueId = preSearchUniqueId;
    }

    public String getDomesticCategory() {
        return domesticCategory;
    }

    public void setDomesticCategory(String domesticCategory) {
        this.domesticCategory = domesticCategory;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getFlightGuid() {
        return flightGuid;
    }

    public void setFlightGuid(String flightGuid) {
        this.flightGuid = flightGuid;
    }

    public String getCurrentCulture() {
        return currentCulture;
    }

    public void setCurrentCulture(String currentCulture) {
        this.currentCulture = currentCulture;
    }

    public String getChangeDay() {
        return changeDay;
    }

    public void setChangeDay(String changeDay) {
        this.changeDay = changeDay;
    }

    public String getPType() {
        return pType;
    }

    public void setPType(String pType) {
        this.pType = pType;
    }

    public Boolean getExclusiveTrain() {
        return exclusiveTrain;
    }

    public void setExclusiveTrain(Boolean exclusiveTrain) {
        this.exclusiveTrain = exclusiveTrain;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
