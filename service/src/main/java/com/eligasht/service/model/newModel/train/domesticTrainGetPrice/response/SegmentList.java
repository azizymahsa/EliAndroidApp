
package com.eligasht.service.model.newModel.train.domesticTrainGetPrice.response;

import com.eligasht.service.model.newModel.train.domesticSearch.response.AdlBaseFare;
import com.eligasht.service.model.newModel.train.domesticSearch.response.BaseFare;
import com.eligasht.service.model.newModel.train.domesticSearch.response.ChdBaseFare;
import com.eligasht.service.model.newModel.train.domesticSearch.response.InfBaseFare;
import com.eligasht.service.model.newModel.train.domesticSearch.response.PassengerService;
import com.eligasht.service.model.newModel.train.domesticSearch.response.Saloon;
import com.eligasht.service.model.newModel.train.domesticSearch.response.Taxes;
import com.eligasht.service.model.newModel.train.domesticSearch.response.TotalFare;
import com.eligasht.service.model.newModel.train.domesticSearch.response.TotalFareCost;
import com.eligasht.service.model.newModel.train.domesticSearch.response.TrainFacility;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SegmentList {

    @SerializedName("PrivateID")
    @Expose
    private String privateID;
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("TrainlineID")
    @Expose
    private Integer trainlineID;
    @SerializedName("TrainlineCode")
    @Expose
    private Integer trainlineCode;
    @SerializedName("TrainlineNameEn")
    @Expose
    private String trainlineNameEn;
    @SerializedName("TrainlineNameFa")
    @Expose
    private String trainlineNameFa;
    @SerializedName("TrainNumber")
    @Expose
    private String trainNumber;
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
    private String arrivalDateShamsi;
    @SerializedName("TrainTime")
    @Expose
    private String trainTime;
    @SerializedName("TrainArrivalTime")
    @Expose
    private String trainArrivalTime;
    @SerializedName("DepartureTrainStationCode")
    @Expose
    private String departureTrainStationCode;
    @SerializedName("ArrivalTrainStationCode")
    @Expose
    private String arrivalTrainStationCode;
    @SerializedName("TrainDurationH")
    @Expose
    private Object trainDurationH;
    @SerializedName("TrainDurationM")
    @Expose
    private Object trainDurationM;
    @SerializedName("DepartureTrainStationNameEn")
    @Expose
    private String departureTrainStationNameEn;
    @SerializedName("ArrivalTrainStationNameEn")
    @Expose
    private String arrivalTrainStationNameEn;
    @SerializedName("DepartureTrainStationNameFa")
    @Expose
    private String departureTrainStationNameFa;
    @SerializedName("ArrivalTrainStationNameFa")
    @Expose
    private String arrivalTrainStationNameFa;
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
    @SerializedName("TrainDateDayOfWeek")
    @Expose
    private String trainDateDayOfWeek;
    @SerializedName("IsDepartureSegment")
    @Expose
    private Boolean isDepartureSegment;
    @SerializedName("HotelIsIncluded")
    @Expose
    private Boolean hotelIsIncluded;
    @SerializedName("SeatsRemaining")
    @Expose
    private Integer seatsRemaining;
    @SerializedName("IsCompartment")
    @Expose
    private Boolean isCompartment;
    @SerializedName("JourneyDuration")
    @Expose
    private String journeyDuration;
    @SerializedName("CompartmentCapacity")
    @Expose
    private Integer compartmentCapacity;
    @SerializedName("Degree")
    @Expose
    private Integer degree;
    @SerializedName("Note")
    @Expose
    private Object note;
    @SerializedName("PassengerServices")
    @Expose
    private List<PassengerService> passengerServices = null;
    @SerializedName("PassengerType")
    @Expose
    private String passengerType;
    @SerializedName("Saloon")
    @Expose
    private Saloon saloon;
    @SerializedName("SeatAvailable")
    @Expose
    private Integer seatAvailable;
    @SerializedName("SeatOccupancy")
    @Expose
    private Integer seatOccupancy;
    @SerializedName("TotalFareCost")
    @Expose
    private TotalFareCost totalFareCost;
    @SerializedName("AdlBaseFare")
    @Expose
    private AdlBaseFare adlBaseFare;
    @SerializedName("ChdBaseFare")
    @Expose
    private ChdBaseFare chdBaseFare;
    @SerializedName("InfBaseFare")
    @Expose
    private InfBaseFare infBaseFare;
    @SerializedName("BaseFare")
    @Expose
    private BaseFare baseFare;
    @SerializedName("Taxes")
    @Expose
    private Taxes taxes;
    @SerializedName("TotalFare")
    @Expose
    private TotalFare totalFare;
    @SerializedName("DiscountPrice")
    @Expose
    private Object discountPrice;
    @SerializedName("TrainFacility")
    @Expose
    private TrainFacility trainFacility;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getPrivateID() {
        return privateID;
    }

    public void setPrivateID(String privateID) {
        this.privateID = privateID;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public Integer getTrainlineID() {
        return trainlineID;
    }

    public void setTrainlineID(Integer trainlineID) {
        this.trainlineID = trainlineID;
    }

    public Integer getTrainlineCode() {
        return trainlineCode;
    }

    public void setTrainlineCode(Integer trainlineCode) {
        this.trainlineCode = trainlineCode;
    }

    public String getTrainlineNameEn() {
        return trainlineNameEn;
    }

    public void setTrainlineNameEn(String trainlineNameEn) {
        this.trainlineNameEn = trainlineNameEn;
    }

    public String getTrainlineNameFa() {
        return trainlineNameFa;
    }

    public void setTrainlineNameFa(String trainlineNameFa) {
        this.trainlineNameFa = trainlineNameFa;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
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

    public String getArrivalDateShamsi() {
        return arrivalDateShamsi;
    }

    public void setArrivalDateShamsi(String arrivalDateShamsi) {
        this.arrivalDateShamsi = arrivalDateShamsi;
    }

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    public String getTrainArrivalTime() {
        return trainArrivalTime;
    }

    public void setTrainArrivalTime(String trainArrivalTime) {
        this.trainArrivalTime = trainArrivalTime;
    }

    public String getDepartureTrainStationCode() {
        return departureTrainStationCode;
    }

    public void setDepartureTrainStationCode(String departureTrainStationCode) {
        this.departureTrainStationCode = departureTrainStationCode;
    }

    public String getArrivalTrainStationCode() {
        return arrivalTrainStationCode;
    }

    public void setArrivalTrainStationCode(String arrivalTrainStationCode) {
        this.arrivalTrainStationCode = arrivalTrainStationCode;
    }

    public Object getTrainDurationH() {
        return trainDurationH;
    }

    public void setTrainDurationH(Object trainDurationH) {
        this.trainDurationH = trainDurationH;
    }

    public Object getTrainDurationM() {
        return trainDurationM;
    }

    public void setTrainDurationM(Object trainDurationM) {
        this.trainDurationM = trainDurationM;
    }

    public String getDepartureTrainStationNameEn() {
        return departureTrainStationNameEn;
    }

    public void setDepartureTrainStationNameEn(String departureTrainStationNameEn) {
        this.departureTrainStationNameEn = departureTrainStationNameEn;
    }

    public String getArrivalTrainStationNameEn() {
        return arrivalTrainStationNameEn;
    }

    public void setArrivalTrainStationNameEn(String arrivalTrainStationNameEn) {
        this.arrivalTrainStationNameEn = arrivalTrainStationNameEn;
    }

    public String getDepartureTrainStationNameFa() {
        return departureTrainStationNameFa;
    }

    public void setDepartureTrainStationNameFa(String departureTrainStationNameFa) {
        this.departureTrainStationNameFa = departureTrainStationNameFa;
    }

    public String getArrivalTrainStationNameFa() {
        return arrivalTrainStationNameFa;
    }

    public void setArrivalTrainStationNameFa(String arrivalTrainStationNameFa) {
        this.arrivalTrainStationNameFa = arrivalTrainStationNameFa;
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

    public String getTrainDateDayOfWeek() {
        return trainDateDayOfWeek;
    }

    public void setTrainDateDayOfWeek(String trainDateDayOfWeek) {
        this.trainDateDayOfWeek = trainDateDayOfWeek;
    }

    public Boolean getIsDepartureSegment() {
        return isDepartureSegment;
    }

    public void setIsDepartureSegment(Boolean isDepartureSegment) {
        this.isDepartureSegment = isDepartureSegment;
    }

    public Boolean getHotelIsIncluded() {
        return hotelIsIncluded;
    }

    public void setHotelIsIncluded(Boolean hotelIsIncluded) {
        this.hotelIsIncluded = hotelIsIncluded;
    }

    public Integer getSeatsRemaining() {
        return seatsRemaining;
    }

    public void setSeatsRemaining(Integer seatsRemaining) {
        this.seatsRemaining = seatsRemaining;
    }

    public Boolean getIsCompartment() {
        return isCompartment;
    }

    public void setIsCompartment(Boolean isCompartment) {
        this.isCompartment = isCompartment;
    }

    public String getJourneyDuration() {
        return journeyDuration;
    }

    public void setJourneyDuration(String journeyDuration) {
        this.journeyDuration = journeyDuration;
    }

    public Integer getCompartmentCapacity() {
        return compartmentCapacity;
    }

    public void setCompartmentCapacity(Integer compartmentCapacity) {
        this.compartmentCapacity = compartmentCapacity;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    public List<PassengerService> getPassengerServices() {
        return passengerServices;
    }

    public void setPassengerServices(List<PassengerService> passengerServices) {
        this.passengerServices = passengerServices;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public Saloon getSaloon() {
        return saloon;
    }

    public void setSaloon(Saloon saloon) {
        this.saloon = saloon;
    }

    public Integer getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(Integer seatAvailable) {
        this.seatAvailable = seatAvailable;
    }

    public Integer getSeatOccupancy() {
        return seatOccupancy;
    }

    public void setSeatOccupancy(Integer seatOccupancy) {
        this.seatOccupancy = seatOccupancy;
    }

    public TotalFareCost getTotalFareCost() {
        return totalFareCost;
    }

    public void setTotalFareCost(TotalFareCost totalFareCost) {
        this.totalFareCost = totalFareCost;
    }

    public AdlBaseFare getAdlBaseFare() {
        return adlBaseFare;
    }

    public void setAdlBaseFare(AdlBaseFare adlBaseFare) {
        this.adlBaseFare = adlBaseFare;
    }

    public ChdBaseFare getChdBaseFare() {
        return chdBaseFare;
    }

    public void setChdBaseFare(ChdBaseFare chdBaseFare) {
        this.chdBaseFare = chdBaseFare;
    }

    public InfBaseFare getInfBaseFare() {
        return infBaseFare;
    }

    public void setInfBaseFare(InfBaseFare infBaseFare) {
        this.infBaseFare = infBaseFare;
    }

    public BaseFare getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(BaseFare baseFare) {
        this.baseFare = baseFare;
    }

    public Taxes getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    public TotalFare getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(TotalFare totalFare) {
        this.totalFare = totalFare;
    }

    public Object getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Object discountPrice) {
        this.discountPrice = discountPrice;
    }

    public TrainFacility getTrainFacility() {
        return trainFacility;
    }

    public void setTrainFacility(TrainFacility trainFacility) {
        this.trainFacility = trainFacility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
