
package com.eligasht.service.model.newModel.xpackage.searchPack.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class XFlightsList {

    @SerializedName("XferType_PName")
    @Expose
    private String xferTypePName;
    @SerializedName("PackXfer_ID")
    @Expose
    private Integer packXferID;
    @SerializedName("PackXfer_PID")
    @Expose
    private Integer packXferPID;
    @SerializedName("Pack_ID")
    @Expose
    private Integer packID;
    @SerializedName("PackXfer_Date")
    @Expose
    private String packXferDate;
    @SerializedName("Flt_ID")
    @Expose
    private Integer fltID;
    @SerializedName("DayofWeek")
    @Expose
    private String dayofWeek;
    @SerializedName("Shamsi")
    @Expose
    private String shamsi;
    @SerializedName("Flt_LocalTime")
    @Expose
    private String fltLocalTime;
    @SerializedName("FltBase_Code")
    @Expose
    private String fltBaseCode;
    @SerializedName("Departure_City_Name")
    @Expose
    private String departureCityName;
    @SerializedName("Arrival_City_Name")
    @Expose
    private String arrivalCityName;
    @SerializedName("Arrival_Country_Name")
    @Expose
    private String arrivalCountryName;
    @SerializedName("Departure_Country_Name")
    @Expose
    private String departureCountryName;
    @SerializedName("Departure_City_NameEn")
    @Expose
    private String departureCityNameEn;
    @SerializedName("Arrival_City_NameEn")
    @Expose
    private String arrivalCityNameEn;
    @SerializedName("FltBase_Number")
    @Expose
    private String fltBaseNumber;
    @SerializedName("Departure_AirPort_Name")
    @Expose
    private String departureAirPortName;
    @SerializedName("Arrival_AirPort_Name")
    @Expose
    private String arrivalAirPortName;
    @SerializedName("Airline_Code")
    @Expose
    private String airlineCode;
    @SerializedName("Airline_EnglishName")
    @Expose
    private String airlineEnglishName;
    @SerializedName("Airplane_Name")
    @Expose
    private String airplaneName;
    @SerializedName("Airplane_Model")
    @Expose
    private String airplaneModel;
    @SerializedName("SeatClass_ID")
    @Expose
    private Integer seatClassID;
    @SerializedName("SeatClass_Name")
    @Expose
    private String seatClassName;
    @SerializedName("SeatClass_NameFa")
    @Expose
    private String seatClassNameFa;
    @SerializedName("Available")
    @Expose
    private String available;
    @SerializedName("PackRout_Seq")
    @Expose
    private Integer packRoutSeq;
    @SerializedName("AirPort_Code")
    @Expose
    private String airPortCode;
    @SerializedName("PackCityStatus_PName")
    @Expose
    private Object packCityStatusPName;
    @SerializedName("RoutType")
    @Expose
    private String routType;
    @SerializedName("MaxSeq")
    @Expose
    private Integer maxSeq;
    @SerializedName("Pack_Title")
    @Expose
    private String packTitle;
    @SerializedName("Pack_RefNum")
    @Expose
    private String packRefNum;
    @SerializedName("PackXfer_IDs")
    @Expose
    private String packXferIDs;
    @SerializedName("DepPackXfer_Date")
    @Expose
    private Object depPackXferDate;
    @SerializedName("DD")
    @Expose
    private Object dD;
    @SerializedName("sp")
    @Expose
    private Integer sp;
    @SerializedName("Pack_Day")
    @Expose
    private Integer packDay;
    @SerializedName("Flt_CheckinTime")
    @Expose
    private String fltCheckinTime;
    @SerializedName("FltDurationH")
    @Expose
    private String fltDurationH;
    @SerializedName("FltDurationM")
    @Expose
    private String fltDurationM;
    @SerializedName("Dep_AirportNameEn")
    @Expose
    private String depAirportNameEn;
    @SerializedName("Arr_AirportNameEn")
    @Expose
    private String arrAirportNameEn;
    @SerializedName("Flt_ArrDate")
    @Expose
    private String fltArrDate;

    public String getXferTypePName() {
        return xferTypePName;
    }

    public void setXferTypePName(String xferTypePName) {
        this.xferTypePName = xferTypePName;
    }

    public Integer getPackXferID() {
        return packXferID;
    }

    public void setPackXferID(Integer packXferID) {
        this.packXferID = packXferID;
    }

    public Integer getPackXferPID() {
        return packXferPID;
    }

    public void setPackXferPID(Integer packXferPID) {
        this.packXferPID = packXferPID;
    }

    public Integer getPackID() {
        return packID;
    }

    public void setPackID(Integer packID) {
        this.packID = packID;
    }

    public String getPackXferDate() {
        return packXferDate;
    }

    public void setPackXferDate(String packXferDate) {
        this.packXferDate = packXferDate;
    }

    public Integer getFltID() {
        return fltID;
    }

    public void setFltID(Integer fltID) {
        this.fltID = fltID;
    }

    public String getDayofWeek() {
        return dayofWeek;
    }

    public void setDayofWeek(String dayofWeek) {
        this.dayofWeek = dayofWeek;
    }

    public String getShamsi() {
        return shamsi;
    }

    public void setShamsi(String shamsi) {
        this.shamsi = shamsi;
    }

    public String getFltLocalTime() {
        return fltLocalTime;
    }

    public void setFltLocalTime(String fltLocalTime) {
        this.fltLocalTime = fltLocalTime;
    }

    public String getFltBaseCode() {
        return fltBaseCode;
    }

    public void setFltBaseCode(String fltBaseCode) {
        this.fltBaseCode = fltBaseCode;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
    }

    public String getArrivalCountryName() {
        return arrivalCountryName;
    }

    public void setArrivalCountryName(String arrivalCountryName) {
        this.arrivalCountryName = arrivalCountryName;
    }

    public String getDepartureCountryName() {
        return departureCountryName;
    }

    public void setDepartureCountryName(String departureCountryName) {
        this.departureCountryName = departureCountryName;
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

    public String getFltBaseNumber() {
        return fltBaseNumber;
    }

    public void setFltBaseNumber(String fltBaseNumber) {
        this.fltBaseNumber = fltBaseNumber;
    }

    public String getDepartureAirPortName() {
        return departureAirPortName;
    }

    public void setDepartureAirPortName(String departureAirPortName) {
        this.departureAirPortName = departureAirPortName;
    }

    public String getArrivalAirPortName() {
        return arrivalAirPortName;
    }

    public void setArrivalAirPortName(String arrivalAirPortName) {
        this.arrivalAirPortName = arrivalAirPortName;
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

    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Integer getPackRoutSeq() {
        return packRoutSeq;
    }

    public void setPackRoutSeq(Integer packRoutSeq) {
        this.packRoutSeq = packRoutSeq;
    }

    public String getAirPortCode() {
        return airPortCode;
    }

    public void setAirPortCode(String airPortCode) {
        this.airPortCode = airPortCode;
    }

    public Object getPackCityStatusPName() {
        return packCityStatusPName;
    }

    public void setPackCityStatusPName(Object packCityStatusPName) {
        this.packCityStatusPName = packCityStatusPName;
    }

    public String getRoutType() {
        return routType;
    }

    public void setRoutType(String routType) {
        this.routType = routType;
    }

    public Integer getMaxSeq() {
        return maxSeq;
    }

    public void setMaxSeq(Integer maxSeq) {
        this.maxSeq = maxSeq;
    }

    public String getPackTitle() {
        return packTitle;
    }

    public void setPackTitle(String packTitle) {
        this.packTitle = packTitle;
    }

    public String getPackRefNum() {
        return packRefNum;
    }

    public void setPackRefNum(String packRefNum) {
        this.packRefNum = packRefNum;
    }

    public String getPackXferIDs() {
        return packXferIDs;
    }

    public void setPackXferIDs(String packXferIDs) {
        this.packXferIDs = packXferIDs;
    }

    public Object getDepPackXferDate() {
        return depPackXferDate;
    }

    public void setDepPackXferDate(Object depPackXferDate) {
        this.depPackXferDate = depPackXferDate;
    }

    public Object getDD() {
        return dD;
    }

    public void setDD(Object dD) {
        this.dD = dD;
    }

    public Integer getSp() {
        return sp;
    }

    public void setSp(Integer sp) {
        this.sp = sp;
    }

    public Integer getPackDay() {
        return packDay;
    }

    public void setPackDay(Integer packDay) {
        this.packDay = packDay;
    }

    public String getFltCheckinTime() {
        return fltCheckinTime;
    }

    public void setFltCheckinTime(String fltCheckinTime) {
        this.fltCheckinTime = fltCheckinTime;
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

    public String getDepAirportNameEn() {
        return depAirportNameEn;
    }

    public void setDepAirportNameEn(String depAirportNameEn) {
        this.depAirportNameEn = depAirportNameEn;
    }

    public String getArrAirportNameEn() {
        return arrAirportNameEn;
    }

    public void setArrAirportNameEn(String arrAirportNameEn) {
        this.arrAirportNameEn = arrAirportNameEn;
    }

    public String getFltArrDate() {
        return fltArrDate;
    }

    public void setFltArrDate(String fltArrDate) {
        this.fltArrDate = fltArrDate;
    }

}
