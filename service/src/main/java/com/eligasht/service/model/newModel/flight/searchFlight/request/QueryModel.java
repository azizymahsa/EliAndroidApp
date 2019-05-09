package com.eligasht.service.model.newModel.flight.searchFlight.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryModel {
    @SerializedName("flightClass")
    @Expose
    private String flightClass;
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
    private Object source;
    @SerializedName("SourceText")
    @Expose
    private String sourceText;
    @SerializedName("Destination")
    @Expose
    private Object destination;
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
    private Object fromDate;
    @SerializedName("ToDate")
    @Expose
    private Object toDate;
    @SerializedName("CheckOut")
    @Expose
    private String checkOut;
    @SerializedName("CheckIn")
    @Expose
    private String checkIn;
    @SerializedName("Rooms")
    @Expose
    private Object rooms;
    @SerializedName("NationalityCode")
    @Expose
    private Object nationalityCode;
    @SerializedName("ResidenceCode")
    @Expose
    private Object residenceCode;
    @SerializedName("PreferredClass")
    @Expose
    private String preferredClass;
    @SerializedName("PreferedAir")
    @Expose
    private Object preferedAir;
    @SerializedName("packRowId")
    @Expose
    private Object packRowId;
    @SerializedName("packXferIds")
    @Expose
    private Object packXferIds;
    @SerializedName("flightIds")
    @Expose
    private Object flightIds;
    @SerializedName("Departure")
    @Expose
    private Object departure;
    @SerializedName("DeparturePackage")
    @Expose
    private Object departurePackage;
    @SerializedName("ExtraCheckDate")
    @Expose
    private Object extraCheckDate;
    @SerializedName("TravelDuration")
    @Expose
    private Object travelDuration;
    @SerializedName("BirthDay")
    @Expose
    private Object birthDay;
    @SerializedName("HotelofferId")
    @Expose
    private Object hotelofferId;
    @SerializedName("FlightOfferId")
    @Expose
    private Object flightOfferId;
    @SerializedName("preSearch")
    @Expose
    private Object preSearch;
    @SerializedName("hotelId")
    @Expose
    private Object hotelId;
    @SerializedName("preSearchUniqueId")
    @Expose
    private Object preSearchUniqueId;
    @SerializedName("domesticCategory")
    @Expose
    private Object domesticCategory;
    @SerializedName("searchKey")
    @Expose
    private Object searchKey;
    @SerializedName("flightGuid")
    @Expose
    private Object flightGuid;
    @SerializedName("CurrentCulture")
    @Expose
    private String currentCulture;
    @SerializedName("ChangeDay")
    @Expose
    private Object changeDay;
    @SerializedName("PType")
    @Expose
    private Object pType;
    @SerializedName("ExclusiveTrain")
    @Expose
    private Boolean exclusiveTrain;

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

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

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public Object getDestination() {
        return destination;
    }

    public void setDestination(Object destination) {
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

    public Object getFromDate() {
        return fromDate;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

    public Object getToDate() {
        return toDate;
    }

    public void setToDate(Object toDate) {
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

    public Object getRooms() {
        return rooms;
    }

    public void setRooms(Object rooms) {
        this.rooms = rooms;
    }

    public Object getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(Object nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public Object getResidenceCode() {
        return residenceCode;
    }

    public void setResidenceCode(Object residenceCode) {
        this.residenceCode = residenceCode;
    }

    public String getPreferredClass() {
        return preferredClass;
    }

    public void setPreferredClass(String preferredClass) {
        this.preferredClass = preferredClass;
    }

    public Object getPreferedAir() {
        return preferedAir;
    }

    public void setPreferedAir(Object preferedAir) {
        this.preferedAir = preferedAir;
    }

    public Object getPackRowId() {
        return packRowId;
    }

    public void setPackRowId(Object packRowId) {
        this.packRowId = packRowId;
    }

    public Object getPackXferIds() {
        return packXferIds;
    }

    public void setPackXferIds(Object packXferIds) {
        this.packXferIds = packXferIds;
    }

    public Object getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(Object flightIds) {
        this.flightIds = flightIds;
    }

    public Object getDeparture() {
        return departure;
    }

    public void setDeparture(Object departure) {
        this.departure = departure;
    }

    public Object getDeparturePackage() {
        return departurePackage;
    }

    public void setDeparturePackage(Object departurePackage) {
        this.departurePackage = departurePackage;
    }

    public Object getExtraCheckDate() {
        return extraCheckDate;
    }

    public void setExtraCheckDate(Object extraCheckDate) {
        this.extraCheckDate = extraCheckDate;
    }

    public Object getTravelDuration() {
        return travelDuration;
    }

    public void setTravelDuration(Object travelDuration) {
        this.travelDuration = travelDuration;
    }

    public Object getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Object birthDay) {
        this.birthDay = birthDay;
    }

    public Object getHotelofferId() {
        return hotelofferId;
    }

    public void setHotelofferId(Object hotelofferId) {
        this.hotelofferId = hotelofferId;
    }

    public Object getFlightOfferId() {
        return flightOfferId;
    }

    public void setFlightOfferId(Object flightOfferId) {
        this.flightOfferId = flightOfferId;
    }

    public Object getPreSearch() {
        return preSearch;
    }

    public void setPreSearch(Object preSearch) {
        this.preSearch = preSearch;
    }

    public Object getHotelId() {
        return hotelId;
    }

    public void setHotelId(Object hotelId) {
        this.hotelId = hotelId;
    }

    public Object getPreSearchUniqueId() {
        return preSearchUniqueId;
    }

    public void setPreSearchUniqueId(Object preSearchUniqueId) {
        this.preSearchUniqueId = preSearchUniqueId;
    }

    public Object getDomesticCategory() {
        return domesticCategory;
    }

    public void setDomesticCategory(Object domesticCategory) {
        this.domesticCategory = domesticCategory;
    }

    public Object getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(Object searchKey) {
        this.searchKey = searchKey;
    }

    public Object getFlightGuid() {
        return flightGuid;
    }

    public void setFlightGuid(Object flightGuid) {
        this.flightGuid = flightGuid;
    }

    public String getCurrentCulture() {
        return currentCulture;
    }

    public void setCurrentCulture(String currentCulture) {
        this.currentCulture = currentCulture;
    }

    public Object getChangeDay() {
        return changeDay;
    }

    public void setChangeDay(Object changeDay) {
        this.changeDay = changeDay;
    }

    public Object getPType() {
        return pType;
    }

    public void setPType(Object pType) {
        this.pType = pType;
    }

    public Boolean getExclusiveTrain() {
        return exclusiveTrain;
    }

    public void setExclusiveTrain(Boolean exclusiveTrain) {
        this.exclusiveTrain = exclusiveTrain;
    }

}


