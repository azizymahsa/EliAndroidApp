
package com.eligasht.service.model.flight.response.searchFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SegmentList {

    @SerializedName("AirlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("AirlineID")
    @Expose
    private Integer airlineID;
    @SerializedName("AirlineNameEn")
    @Expose
    private String airlineNameEn;
    @SerializedName("AirlineNameFa")
    @Expose
    private String airlineNameFa;
    @SerializedName("AirplaneName")
    @Expose
    private String airplaneName;
    @SerializedName("ArrivalAirportCode")
    @Expose
    private String arrivalAirportCode;
    @SerializedName("ArrivalAirportNameEn")
    @Expose
    private String arrivalAirportNameEn;
    @SerializedName("ArrivalAirportNameFa")
    @Expose
    private String arrivalAirportNameFa;
    @SerializedName("ArrivalCityCode")
    @Expose
    private String arrivalCityCode;
    @SerializedName("ArrivalCityNameEn")
    @Expose
    private String arrivalCityNameEn;
    @SerializedName("ArrivalCityNameFa")
    @Expose
    private String arrivalCityNameFa;
    @SerializedName("ArrivalCountryNameEn")
    @Expose
    private String arrivalCountryNameEn;
    @SerializedName("ArrivalCountryNameFa")
    @Expose
    private String arrivalCountryNameFa;
    @SerializedName("ArrivalDate")
    @Expose
    private String arrivalDate;
    @SerializedName("ArrivalDateShamsi")
    @Expose
    private String arrivalDateShamsi;
    @SerializedName("CabinClassCode")
    @Expose
    private String cabinClassCode;
    @SerializedName("CabinClassName")
    @Expose
    private String cabinClassName;
    @SerializedName("CabinClassNameFa")
    @Expose
    private String cabinClassNameFa;
    @SerializedName("DepartureAirportCode")
    @Expose
    private String departureAirportCode;
    @SerializedName("DepartureAirportNameEn")
    @Expose
    private String departureAirportNameEn;
    @SerializedName("DepartureAirportNameFa")
    @Expose
    private String departureAirportNameFa;
    @SerializedName("DepartureCityCode")
    @Expose
    private String departureCityCode;
    @SerializedName("DepartureCityNameEn")
    @Expose
    private String departureCityNameEn;
    @SerializedName("DepartureCityNameFa")
    @Expose
    private String departureCityNameFa;
    @SerializedName("DepartureCountryNameEn")
    @Expose
    private String departureCountryNameEn;
    @SerializedName("DepartureCountryNameFa")
    @Expose
    private String departureCountryNameFa;
    @SerializedName("DepartureDate")
    @Expose
    private String departureDate;
    @SerializedName("DepartureDateShamsi")
    @Expose
    private String departureDateShamsi;
    @SerializedName("FlightArrivalTime")
    @Expose
    private String flightArrivalTime;
    @SerializedName("FlightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("FlightTime")
    @Expose
    private String flightTime;
    @SerializedName("FltDateDayOfWeek")
    @Expose
    private String fltDateDayOfWeek;
    @SerializedName("FltDurationH")
    @Expose
    private String fltDurationH;
    @SerializedName("FltDurationM")
    @Expose
    private String fltDurationM;
    @SerializedName("IsDepartureSegment")
    @Expose
    private Boolean isDepartureSegment;
    @SerializedName("OperatingAirlineCode")
    @Expose
    private Object operatingAirlineCode;
    @SerializedName("OperatingAirlineID")
    @Expose
    private Integer operatingAirlineID;
    @SerializedName("OperatingAirlineNameEn")
    @Expose
    private String operatingAirlineNameEn;
    @SerializedName("OperatingAirlineNameFa")
    @Expose
    private Object operatingAirlineNameFa;

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Integer getAirlineID() {
        return airlineID;
    }

    public void setAirlineID(Integer airlineID) {
        this.airlineID = airlineID;
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

    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getArrivalAirportNameEn() {
        return arrivalAirportNameEn;
    }

    public void setArrivalAirportNameEn(String arrivalAirportNameEn) {
        this.arrivalAirportNameEn = arrivalAirportNameEn;
    }

    public String getArrivalAirportNameFa() {
        return arrivalAirportNameFa;
    }

    public void setArrivalAirportNameFa(String arrivalAirportNameFa) {
        this.arrivalAirportNameFa = arrivalAirportNameFa;
    }

    public String getArrivalCityCode() {
        return arrivalCityCode;
    }

    public void setArrivalCityCode(String arrivalCityCode) {
        this.arrivalCityCode = arrivalCityCode;
    }

    public String getArrivalCityNameEn() {
        return arrivalCityNameEn;
    }

    public void setArrivalCityNameEn(String arrivalCityNameEn) {
        this.arrivalCityNameEn = arrivalCityNameEn;
    }

    public String getArrivalCityNameFa() {
        return arrivalCityNameFa;
    }

    public void setArrivalCityNameFa(String arrivalCityNameFa) {
        this.arrivalCityNameFa = arrivalCityNameFa;
    }

    public String getArrivalCountryNameEn() {
        return arrivalCountryNameEn;
    }

    public void setArrivalCountryNameEn(String arrivalCountryNameEn) {
        this.arrivalCountryNameEn = arrivalCountryNameEn;
    }

    public String getArrivalCountryNameFa() {
        return arrivalCountryNameFa;
    }

    public void setArrivalCountryNameFa(String arrivalCountryNameFa) {
        this.arrivalCountryNameFa = arrivalCountryNameFa;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalDateShamsi() {
        return arrivalDateShamsi;
    }

    public void setArrivalDateShamsi(String arrivalDateShamsi) {
        this.arrivalDateShamsi = arrivalDateShamsi;
    }

    public String getCabinClassCode() {
        return cabinClassCode;
    }

    public void setCabinClassCode(String cabinClassCode) {
        this.cabinClassCode = cabinClassCode;
    }

    public String getCabinClassName() {
        return cabinClassName;
    }

    public void setCabinClassName(String cabinClassName) {
        this.cabinClassName = cabinClassName;
    }

    public String getCabinClassNameFa() {
        return cabinClassNameFa;
    }

    public void setCabinClassNameFa(String cabinClassNameFa) {
        this.cabinClassNameFa = cabinClassNameFa;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDepartureAirportNameEn() {
        return departureAirportNameEn;
    }

    public void setDepartureAirportNameEn(String departureAirportNameEn) {
        this.departureAirportNameEn = departureAirportNameEn;
    }

    public String getDepartureAirportNameFa() {
        return departureAirportNameFa;
    }

    public void setDepartureAirportNameFa(String departureAirportNameFa) {
        this.departureAirportNameFa = departureAirportNameFa;
    }

    public String getDepartureCityCode() {
        return departureCityCode;
    }

    public void setDepartureCityCode(String departureCityCode) {
        this.departureCityCode = departureCityCode;
    }

    public String getDepartureCityNameEn() {
        return departureCityNameEn;
    }

    public void setDepartureCityNameEn(String departureCityNameEn) {
        this.departureCityNameEn = departureCityNameEn;
    }

    public String getDepartureCityNameFa() {
        return departureCityNameFa;
    }

    public void setDepartureCityNameFa(String departureCityNameFa) {
        this.departureCityNameFa = departureCityNameFa;
    }

    public String getDepartureCountryNameEn() {
        return departureCountryNameEn;
    }

    public void setDepartureCountryNameEn(String departureCountryNameEn) {
        this.departureCountryNameEn = departureCountryNameEn;
    }

    public String getDepartureCountryNameFa() {
        return departureCountryNameFa;
    }

    public void setDepartureCountryNameFa(String departureCountryNameFa) {
        this.departureCountryNameFa = departureCountryNameFa;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureDateShamsi() {
        return departureDateShamsi;
    }

    public void setDepartureDateShamsi(String departureDateShamsi) {
        this.departureDateShamsi = departureDateShamsi;
    }

    public String getFlightArrivalTime() {
        return flightArrivalTime;
    }

    public void setFlightArrivalTime(String flightArrivalTime) {
        this.flightArrivalTime = flightArrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public String getFltDateDayOfWeek() {
        return fltDateDayOfWeek;
    }

    public void setFltDateDayOfWeek(String fltDateDayOfWeek) {
        this.fltDateDayOfWeek = fltDateDayOfWeek;
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

    public Boolean getIsDepartureSegment() {
        return isDepartureSegment;
    }

    public void setIsDepartureSegment(Boolean isDepartureSegment) {
        this.isDepartureSegment = isDepartureSegment;
    }

    public Object getOperatingAirlineCode() {
        return operatingAirlineCode;
    }

    public void setOperatingAirlineCode(Object operatingAirlineCode) {
        this.operatingAirlineCode = operatingAirlineCode;
    }

    public Integer getOperatingAirlineID() {
        return operatingAirlineID;
    }

    public void setOperatingAirlineID(Integer operatingAirlineID) {
        this.operatingAirlineID = operatingAirlineID;
    }

    public String getOperatingAirlineNameEn() {
        return operatingAirlineNameEn;
    }

    public void setOperatingAirlineNameEn(String operatingAirlineNameEn) {
        this.operatingAirlineNameEn = operatingAirlineNameEn;
    }

    public Object getOperatingAirlineNameFa() {
        return operatingAirlineNameFa;
    }

    public void setOperatingAirlineNameFa(Object operatingAirlineNameFa) {
        this.operatingAirlineNameFa = operatingAirlineNameFa;
    }

}
