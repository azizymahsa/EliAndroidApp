
package com.eligasht.reservation.models.model.pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class XFlightsList {

    @SerializedName("AirPort_Code")
    @Expose
    private String airPortCode;
    @SerializedName("Airline_Code")
    @Expose
    private String airlineCode;
    @SerializedName("Airline_EnglishName")
    @Expose
    private String airlineEnglishName;
    @SerializedName("Airplane_Model")
    @Expose
    private String airplaneModel;
    @SerializedName("Airplane_Name")
    @Expose
    private String airplaneName;
    @SerializedName("Arr_AirportNameEn")
    @Expose
    private String arrAirportNameEn;
    @SerializedName("Arrival_AirPort_Name")
    @Expose
    private String arrivalAirPortName;
    @SerializedName("Arrival_City_Name")
    @Expose
    private String arrivalCityName;
    @SerializedName("Arrival_City_NameEn")
    @Expose
    private String arrivalCityNameEn;
    @SerializedName("Arrival_Country_Name")
    @Expose
    private String arrivalCountryName;
    @SerializedName("Available")
    @Expose
    private String available;
    @SerializedName("DD")
    @Expose
    private String dD;
    @SerializedName("DayofWeek")
    @Expose
    private String dayofWeek;
    @SerializedName("DepPackXfer_Date")
    @Expose
    private String depPackXferDate;
    @SerializedName("Dep_AirportNameEn")
    @Expose
    private String depAirportNameEn;
    @SerializedName("Departure_AirPort_Name")
    @Expose
    private String departureAirPortName;
    @SerializedName("Departure_City_Name")
    @Expose
    private String departureCityName;
    @SerializedName("Departure_City_NameEn")
    @Expose
    private String departureCityNameEn;
    @SerializedName("Departure_Country_Name")
    @Expose
    private String departureCountryName;
    @SerializedName("FltBase_Code")
    @Expose
    private String fltBaseCode;
    @SerializedName("FltBase_Number")
    @Expose
    private String fltBaseNumber;
    @SerializedName("FltDurationH")
    @Expose
    private String fltDurationH;
    @SerializedName("FltDurationM")
    @Expose
    private String fltDurationM;
    @SerializedName("Flt_ArrDate")
    @Expose
    private String fltArrDate;
    @SerializedName("Flt_CheckinTime")
    @Expose
    private String fltCheckinTime;
    @SerializedName("Flt_ID")
    @Expose
    private Integer fltID;
    @SerializedName("Flt_LocalTime")
    @Expose
    private String fltLocalTime;
    @SerializedName("MaxSeq")
    @Expose
    private Integer maxSeq;
    @SerializedName("PackCityStatus_PName")
    @Expose
    private String packCityStatusPName;
    @SerializedName("PackRout_Seq")
    @Expose
    private Integer packRoutSeq;
    @SerializedName("PackXfer_Date")
    @Expose
    private String packXferDate;
    @SerializedName("PackXfer_ID")
    @Expose
    private Integer packXferID;
    @SerializedName("PackXfer_IDs")
    @Expose
    private String packXferIDs;
    @SerializedName("PackXfer_PID")
    @Expose
    private Integer packXferPID;
    @SerializedName("Pack_Day")
    @Expose
    private Integer packDay;
    @SerializedName("Pack_ID")
    @Expose
    private Integer packID;
    @SerializedName("Pack_RefNum")
    @Expose
    private String packRefNum;
    @SerializedName("Pack_Title")
    @Expose
    private String packTitle;
    @SerializedName("RoutType")
    @Expose
    private String routType;
    @SerializedName("SeatClass_ID")
    @Expose
    private Integer seatClassID;
    @SerializedName("SeatClass_Name")
    @Expose
    private String seatClassName;
    @SerializedName("SeatClass_NameFa")
    @Expose
    private String seatClassNameFa;
    @SerializedName("Shamsi")
    @Expose
    private String shamsi;
    @SerializedName("XferType_PName")
    @Expose
    private String xferTypePName;
    @SerializedName("sp")
    @Expose
    private Integer sp;

    public String getAirPortCode() {
        return airPortCode;
    }

    public void setAirPortCode(String airPortCode) {
        this.airPortCode = airPortCode;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineEnglishName() {
        return airlineEnglishName;
    }

    public void setAirlineEnglishName(String airlineEnglishName) {
        this.airlineEnglishName = airlineEnglishName;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
    }

    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public String getArrAirportNameEn() {
        return arrAirportNameEn;
    }

    public void setArrAirportNameEn(String arrAirportNameEn) {
        this.arrAirportNameEn = arrAirportNameEn;
    }

    public String getArrivalAirPortName() {
        return arrivalAirPortName;
    }

    public void setArrivalAirPortName(String arrivalAirPortName) {
        this.arrivalAirPortName = arrivalAirPortName;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
    }

    public String getArrivalCityNameEn() {
        return arrivalCityNameEn;
    }

    public void setArrivalCityNameEn(String arrivalCityNameEn) {
        this.arrivalCityNameEn = arrivalCityNameEn;
    }

    public String getArrivalCountryName() {
        return arrivalCountryName;
    }

    public void setArrivalCountryName(String arrivalCountryName) {
        this.arrivalCountryName = arrivalCountryName;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getDD() {
        return dD;
    }

    public void setDD(String dD) {
        this.dD = dD;
    }

    public String getDayofWeek() {
        return dayofWeek;
    }

    public void setDayofWeek(String dayofWeek) {
        this.dayofWeek = dayofWeek;
    }

    public String getDepPackXferDate() {
        return depPackXferDate;
    }

    public void setDepPackXferDate(String depPackXferDate) {
        this.depPackXferDate = depPackXferDate;
    }

    public String getDepAirportNameEn() {
        return depAirportNameEn;
    }

    public void setDepAirportNameEn(String depAirportNameEn) {
        this.depAirportNameEn = depAirportNameEn;
    }

    public String getDepartureAirPortName() {
        return departureAirPortName;
    }

    public void setDepartureAirPortName(String departureAirPortName) {
        this.departureAirPortName = departureAirPortName;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public String getDepartureCityNameEn() {
        return departureCityNameEn;
    }

    public void setDepartureCityNameEn(String departureCityNameEn) {
        this.departureCityNameEn = departureCityNameEn;
    }

    public String getDepartureCountryName() {
        return departureCountryName;
    }

    public void setDepartureCountryName(String departureCountryName) {
        this.departureCountryName = departureCountryName;
    }

    public String getFltBaseCode() {
        return fltBaseCode;
    }

    public void setFltBaseCode(String fltBaseCode) {
        this.fltBaseCode = fltBaseCode;
    }

    public String getFltBaseNumber() {
        return fltBaseNumber;
    }

    public void setFltBaseNumber(String fltBaseNumber) {
        this.fltBaseNumber = fltBaseNumber;
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

    public String getFltArrDate() {
        return fltArrDate;
    }

    public void setFltArrDate(String fltArrDate) {
        this.fltArrDate = fltArrDate;
    }

    public String getFltCheckinTime() {
        return fltCheckinTime;
    }

    public void setFltCheckinTime(String fltCheckinTime) {
        this.fltCheckinTime = fltCheckinTime;
    }

    public Integer getFltID() {
        return fltID;
    }

    public void setFltID(Integer fltID) {
        this.fltID = fltID;
    }

    public String getFltLocalTime() {
        return fltLocalTime;
    }

    public void setFltLocalTime(String fltLocalTime) {
        this.fltLocalTime = fltLocalTime;
    }

    public Integer getMaxSeq() {
        return maxSeq;
    }

    public void setMaxSeq(Integer maxSeq) {
        this.maxSeq = maxSeq;
    }

    public String getPackCityStatusPName() {
        return packCityStatusPName;
    }

    public void setPackCityStatusPName(String packCityStatusPName) {
        this.packCityStatusPName = packCityStatusPName;
    }

    public Integer getPackRoutSeq() {
        return packRoutSeq;
    }

    public void setPackRoutSeq(Integer packRoutSeq) {
        this.packRoutSeq = packRoutSeq;
    }

    public String getPackXferDate() {
        return packXferDate;
    }

    public void setPackXferDate(String packXferDate) {
        this.packXferDate = packXferDate;
    }

    public Integer getPackXferID() {
        return packXferID;
    }

    public void setPackXferID(Integer packXferID) {
        this.packXferID = packXferID;
    }

    public String getPackXferIDs() {
        return packXferIDs;
    }

    public void setPackXferIDs(String packXferIDs) {
        this.packXferIDs = packXferIDs;
    }

    public Integer getPackXferPID() {
        return packXferPID;
    }

    public void setPackXferPID(Integer packXferPID) {
        this.packXferPID = packXferPID;
    }

    public Integer getPackDay() {
        return packDay;
    }

    public void setPackDay(Integer packDay) {
        this.packDay = packDay;
    }

    public Integer getPackID() {
        return packID;
    }

    public void setPackID(Integer packID) {
        this.packID = packID;
    }

    public String getPackRefNum() {
        return packRefNum;
    }

    public void setPackRefNum(String packRefNum) {
        this.packRefNum = packRefNum;
    }

    public String getPackTitle() {
        return packTitle;
    }

    public void setPackTitle(String packTitle) {
        this.packTitle = packTitle;
    }

    public String getRoutType() {
        return routType;
    }

    public void setRoutType(String routType) {
        this.routType = routType;
    }

    public Integer getSeatClassID() {
        return seatClassID;
    }

    public void setSeatClassID(Integer seatClassID) {
        this.seatClassID = seatClassID;
    }

    public String getSeatClassName() {
        return seatClassName;
    }

    public void setSeatClassName(String seatClassName) {
        this.seatClassName = seatClassName;
    }

    public String getSeatClassNameFa() {
        return seatClassNameFa;
    }

    public void setSeatClassNameFa(String seatClassNameFa) {
        this.seatClassNameFa = seatClassNameFa;
    }

    public String getShamsi() {
        return shamsi;
    }

    public void setShamsi(String shamsi) {
        this.shamsi = shamsi;
    }

    public String getXferTypePName() {
        return xferTypePName;
    }

    public void setXferTypePName(String xferTypePName) {
        this.xferTypePName = xferTypePName;
    }

    public Integer getSp() {
        return sp;
    }

    public void setSp(Integer sp) {
        this.sp = sp;
    }

}
