
package com.eligasht.service.model.newModel.hotelFlight.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FltList {

    @SerializedName("AirlineID")
    @Expose
    private Integer airlineID;
    @SerializedName("AirlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("AirlineNameEn")
    @Expose
    private String airlineNameEn;
    @SerializedName("AirlineNameFa")
    @Expose
    private String airlineNameFa;
    @SerializedName("FlightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("CabinClassCode")
    @Expose
    private Object cabinClassCode;
    @SerializedName("CabinClassName")
    @Expose
    private Object cabinClassName;
    @SerializedName("CabinClassNameFa")
    @Expose
    private Object cabinClassNameFa;
    @SerializedName("DepartureDate")
    @Expose
    private String departureDate;
    @SerializedName("ArrivalDate")
    @Expose
    private String arrivalDate;
    @SerializedName("DepartureDateShamsi")
    @Expose
    private String departureDateShamsi;
    @SerializedName("ArrivalDateShamsi")
    @Expose
    private Object arrivalDateShamsi;
    @SerializedName("FlightTime")
    @Expose
    private String flightTime;
    @SerializedName("FlightArrivalTime")
    @Expose
    private String flightArrivalTime;
    @SerializedName("DepartureAirportCode")
    @Expose
    private String departureAirportCode;
    @SerializedName("ArrivalAirportCode")
    @Expose
    private String arrivalAirportCode;
    @SerializedName("FltDurationH")
    @Expose
    private String fltDurationH;
    @SerializedName("FltDurationM")
    @Expose
    private String fltDurationM;
    @SerializedName("DepartureAirportNameEn")
    @Expose
    private String departureAirportNameEn;
    @SerializedName("ArrivalAirportNameEn")
    @Expose
    private String arrivalAirportNameEn;
    @SerializedName("DepartureAirportNameFa")
    @Expose
    private String departureAirportNameFa;
    @SerializedName("ArrivalAirportNameFa")
    @Expose
    private String arrivalAirportNameFa;
    @SerializedName("DepartureCityNameEn")
    @Expose
    private String departureCityNameEn;
    @SerializedName("ArrivalCityNameEn")
    @Expose
    private String arrivalCityNameEn;
    @SerializedName("DepartureCityNameFa")
    @Expose
    private String departureCityNameFa;
    @SerializedName("ArrivalCityNameFa")
    @Expose
    private String arrivalCityNameFa;
    @SerializedName("DepartureCityCode")
    @Expose
    private String departureCityCode;
    @SerializedName("ArrivalCityCode")
    @Expose
    private String arrivalCityCode;
    @SerializedName("DepartureCountryNameEn")
    @Expose
    private String departureCountryNameEn;
    @SerializedName("ArrivalCountryNameEn")
    @Expose
    private String arrivalCountryNameEn;
    @SerializedName("DepartureCountryNameFa")
    @Expose
    private String departureCountryNameFa;
    @SerializedName("ArrivalCountryNameFa")
    @Expose
    private String arrivalCountryNameFa;
    @SerializedName("FltDateDayOfWeek")
    @Expose
    private Object fltDateDayOfWeek;
    @SerializedName("AirplaneName")
    @Expose
    private Object airplaneName;
    @SerializedName("IsDepartureSegment")
    @Expose
    private Boolean isDepartureSegment;
    @SerializedName("OperatingAirlineID")
    @Expose
    private Integer operatingAirlineID;
    @SerializedName("OperatingAirlineCode")
    @Expose
    private Object operatingAirlineCode;
    @SerializedName("OperatingAirlineNameEn")
    @Expose
    private Object operatingAirlineNameEn;
    @SerializedName("OperatingAirlineNameFa")
    @Expose
    private Object operatingAirlineNameFa;
    @SerializedName("FreeBaggage")
    @Expose
    private Object freeBaggage;
    @SerializedName("CorporateId")
    @Expose
    private Object corporateId;

    public Integer getAirlineID() {
        return airlineID;
    }

    public void setAirlineID(Integer airlineID) {
        this.airlineID = airlineID;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineNameEn() {
        return airlineNameEn;
    }

    public void setAirlineNameEn(String airlineNameEn) {
        this.airlineNameEn = airlineNameEn;
    }

    public String getAirlineNameFa() {
        return airlineNameFa;
    }

    public void setAirlineNameFa(String airlineNameFa) {
        this.airlineNameFa = airlineNameFa;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Object getCabinClassCode() {
        return cabinClassCode;
    }

    public void setCabinClassCode(Object cabinClassCode) {
        this.cabinClassCode = cabinClassCode;
    }

    public Object getCabinClassName() {
        return cabinClassName;
    }

    public void setCabinClassName(Object cabinClassName) {
        this.cabinClassName = cabinClassName;
    }

    public Object getCabinClassNameFa() {
        return cabinClassNameFa;
    }

    public void setCabinClassNameFa(Object cabinClassNameFa) {
        this.cabinClassNameFa = cabinClassNameFa;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureDateShamsi() {
        return departureDateShamsi;
    }

    public void setDepartureDateShamsi(String departureDateShamsi) {
        this.departureDateShamsi = departureDateShamsi;
    }

    public Object getArrivalDateShamsi() {
        return arrivalDateShamsi;
    }

    public void setArrivalDateShamsi(Object arrivalDateShamsi) {
        this.arrivalDateShamsi = arrivalDateShamsi;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public String getFlightArrivalTime() {
        return flightArrivalTime;
    }

    public void setFlightArrivalTime(String flightArrivalTime) {
        this.flightArrivalTime = flightArrivalTime;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getFltDurationH() {
        return fltDurationH;
    }

    public void setFltDurationH(String fltDurationH) {
        this.fltDurationH = fltDurationH;
    }

    public String getFltDurationM() {
        return fltDurationM;
    }

    public void setFltDurationM(String fltDurationM) {
        this.fltDurationM = fltDurationM;
    }

    public String getDepartureAirportNameEn() {
        return departureAirportNameEn;
    }

    public void setDepartureAirportNameEn(String departureAirportNameEn) {
        this.departureAirportNameEn = departureAirportNameEn;
    }

    public String getArrivalAirportNameEn() {
        return arrivalAirportNameEn;
    }

    public void setArrivalAirportNameEn(String arrivalAirportNameEn) {
        this.arrivalAirportNameEn = arrivalAirportNameEn;
    }

    public String getDepartureAirportNameFa() {
        return departureAirportNameFa;
    }

    public void setDepartureAirportNameFa(String departureAirportNameFa) {
        this.departureAirportNameFa = departureAirportNameFa;
    }

    public String getArrivalAirportNameFa() {
        return arrivalAirportNameFa;
    }

    public void setArrivalAirportNameFa(String arrivalAirportNameFa) {
        this.arrivalAirportNameFa = arrivalAirportNameFa;
    }

    public String getDepartureCityNameEn() {
        return departureCityNameEn;
    }

    public void setDepartureCityNameEn(String departureCityNameEn) {
        this.departureCityNameEn = departureCityNameEn;
    }

    public String getArrivalCityNameEn() {
        return arrivalCityNameEn;
    }

    public void setArrivalCityNameEn(String arrivalCityNameEn) {
        this.arrivalCityNameEn = arrivalCityNameEn;
    }

    public String getDepartureCityNameFa() {
        return departureCityNameFa;
    }

    public void setDepartureCityNameFa(String departureCityNameFa) {
        this.departureCityNameFa = departureCityNameFa;
    }

    public String getArrivalCityNameFa() {
        return arrivalCityNameFa;
    }

    public void setArrivalCityNameFa(String arrivalCityNameFa) {
        this.arrivalCityNameFa = arrivalCityNameFa;
    }

    public String getDepartureCityCode() {
        return departureCityCode;
    }

    public void setDepartureCityCode(String departureCityCode) {
        this.departureCityCode = departureCityCode;
    }

    public String getArrivalCityCode() {
        return arrivalCityCode;
    }

    public void setArrivalCityCode(String arrivalCityCode) {
        this.arrivalCityCode = arrivalCityCode;
    }

    public String getDepartureCountryNameEn() {
        return departureCountryNameEn;
    }

    public void setDepartureCountryNameEn(String departureCountryNameEn) {
        this.departureCountryNameEn = departureCountryNameEn;
    }

    public String getArrivalCountryNameEn() {
        return arrivalCountryNameEn;
    }

    public void setArrivalCountryNameEn(String arrivalCountryNameEn) {
        this.arrivalCountryNameEn = arrivalCountryNameEn;
    }

    public String getDepartureCountryNameFa() {
        return departureCountryNameFa;
    }

    public void setDepartureCountryNameFa(String departureCountryNameFa) {
        this.departureCountryNameFa = departureCountryNameFa;
    }

    public String getArrivalCountryNameFa() {
        return arrivalCountryNameFa;
    }

    public void setArrivalCountryNameFa(String arrivalCountryNameFa) {
        this.arrivalCountryNameFa = arrivalCountryNameFa;
    }

    public Object getFltDateDayOfWeek() {
        return fltDateDayOfWeek;
    }

    public void setFltDateDayOfWeek(Object fltDateDayOfWeek) {
        this.fltDateDayOfWeek = fltDateDayOfWeek;
    }

    public Object getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(Object airplaneName) {
        this.airplaneName = airplaneName;
    }

    public Boolean getIsDepartureSegment() {
        return isDepartureSegment;
    }

    public void setIsDepartureSegment(Boolean isDepartureSegment) {
        this.isDepartureSegment = isDepartureSegment;
    }

    public Integer getOperatingAirlineID() {
        return operatingAirlineID;
    }

    public void setOperatingAirlineID(Integer operatingAirlineID) {
        this.operatingAirlineID = operatingAirlineID;
    }

    public Object getOperatingAirlineCode() {
        return operatingAirlineCode;
    }

    public void setOperatingAirlineCode(Object operatingAirlineCode) {
        this.operatingAirlineCode = operatingAirlineCode;
    }

    public Object getOperatingAirlineNameEn() {
        return operatingAirlineNameEn;
    }

    public void setOperatingAirlineNameEn(Object operatingAirlineNameEn) {
        this.operatingAirlineNameEn = operatingAirlineNameEn;
    }

    public Object getOperatingAirlineNameFa() {
        return operatingAirlineNameFa;
    }

    public void setOperatingAirlineNameFa(Object operatingAirlineNameFa) {
        this.operatingAirlineNameFa = operatingAirlineNameFa;
    }

    public Object getFreeBaggage() {
        return freeBaggage;
    }

    public void setFreeBaggage(Object freeBaggage) {
        this.freeBaggage = freeBaggage;
    }

    public Object getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(Object corporateId) {
        this.corporateId = corporateId;
    }

}
