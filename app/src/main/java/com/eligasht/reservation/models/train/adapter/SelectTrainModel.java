package com.eligasht.reservation.models.train.adapter;


import com.eligasht.service.model.newModel.hotel.search.response.Facility;
import com.eligasht.service.model.newModel.hotel.search.response.Location;
import com.eligasht.service.model.newModel.train.domesticSearch.response.TrainFacility;

import java.util.List;

/**
 * Created by Mahsa.azizi on 6/3/2019.
 */

public class SelectTrainModel {

    private String PrivateID;
    private String ID;
    private Integer TrainlineID;
    private Integer TrainlineCode;
    private String TrainlineNameEn;
    private String TrainlineNameFa;
    private String SourceText;//raft
    private String DestinationText;//bargasht
    private String TrainTime;
    private String TrainArrivalTime;
    private String TrainNumber;
    private Boolean IsDepartureSegment;
    private Integer TotalFare;//gheymat
    TrainFacility Facilities;
    private Boolean HasAirConditioning;
    private Boolean HasMedia;
    private Integer SeatsRemaining;
    private Integer SeatAvailable;
    private Integer CompartmentCapacity;
    private String SaloonName;
    private Boolean HotelIsIncluded;
    private String TextButton;




    public SelectTrainModel(String privateID, String ID, Integer trainlineID, Integer trainlineCode, String trainlineNameEn, String trainlineNameFa, String sourceText, String destinationText, String trainTime, String trainArrivalTime, String trainNumber, Integer totalFare, TrainFacility facilities, Boolean hasAirConditioning, Boolean hasMedia,
                            Integer seatsRemaining, Integer seatAvailable, Integer compartmentCapacity, String saloonName,Boolean hotelIsIncluded,Boolean IsDepartureSegment,String TextButton) {
        this.PrivateID = privateID;
        this.ID = ID;
        this.TrainlineID = trainlineID;
        this.TrainlineCode = trainlineCode;
        this.TrainlineNameEn = trainlineNameEn;
        this.TrainlineNameFa = trainlineNameFa;
        this.SourceText = sourceText;
        this.DestinationText = destinationText;
        this.TrainTime = trainTime;
        this.TrainArrivalTime = trainArrivalTime;
        this.TrainNumber = trainNumber;
        this.TotalFare = totalFare;
        this.Facilities = facilities;
        this.HasAirConditioning = hasAirConditioning;
        this.HasMedia = hasMedia;
        this.SeatsRemaining = seatsRemaining;
        this.SeatAvailable = seatAvailable;
        this.CompartmentCapacity = compartmentCapacity;
        this.SaloonName = saloonName;
        this.HotelIsIncluded = hotelIsIncluded;
        this.IsDepartureSegment =  IsDepartureSegment;
        this.TextButton =  TextButton;
    }

    public String getTextButton() {
        return TextButton;
    }

    public void setTextButton(String textButton) {
        TextButton = textButton;
    }

    public String getPrivateID() {
        return PrivateID;
    }

    public void setPrivateID(String privateID) {
        PrivateID = privateID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getTrainlineID() {
        return TrainlineID;
    }

    public void setTrainlineID(Integer trainlineID) {
        TrainlineID = trainlineID;
    }

    public Integer getTrainlineCode() {
        return TrainlineCode;
    }

    public void setTrainlineCode(Integer trainlineCode) {
        TrainlineCode = trainlineCode;
    }

    public String getTrainlineNameEn() {
        return TrainlineNameEn;
    }

    public void setTrainlineNameEn(String trainlineNameEn) {
        TrainlineNameEn = trainlineNameEn;
    }

    public String getTrainlineNameFa() {
        return TrainlineNameFa;
    }

    public void setTrainlineNameFa(String trainlineNameFa) {
        TrainlineNameFa = trainlineNameFa;
    }

    public String getSourceText() {
        return SourceText;
    }

    public void setSourceText(String sourceText) {
        SourceText = sourceText;
    }

    public String getDestinationText() {
        return DestinationText;
    }

    public void setDestinationText(String destinationText) {
        DestinationText = destinationText;
    }

    public String getTrainTime() {
        return TrainTime;
    }

    public void setTrainTime(String trainTime) {
        TrainTime = trainTime;
    }

    public String getTrainArrivalTime() {
        return TrainArrivalTime;
    }

    public void setTrainArrivalTime(String trainArrivalTime) {
        TrainArrivalTime = trainArrivalTime;
    }

    public String getTrainNumber() {
        return TrainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        TrainNumber = trainNumber;
    }

    public Boolean getDepartureSegment() {
        return IsDepartureSegment;
    }

    public void setDepartureSegment(Boolean departureSegment) {
        IsDepartureSegment = departureSegment;
    }

    public Integer getTotalFare() {
        return TotalFare;
    }

    public void setTotalFare(Integer totalFare) {
        TotalFare = totalFare;
    }

    public TrainFacility getFacilities() {
        return Facilities;
    }

    public void setFacilities(TrainFacility facilities) {
        Facilities = facilities;
    }

    public Boolean getHasAirConditioning() {
        return HasAirConditioning;
    }

    public void setHasAirConditioning(Boolean hasAirConditioning) {
        HasAirConditioning = hasAirConditioning;
    }

    public Boolean getHasMedia() {
        return HasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        HasMedia = hasMedia;
    }

    public Integer getSeatsRemaining() {
        return SeatsRemaining;
    }

    public void setSeatsRemaining(Integer seatsRemaining) {
        SeatsRemaining = seatsRemaining;
    }

    public Integer getSeatAvailable() {
        return SeatAvailable;
    }

    public void setSeatAvailable(Integer seatAvailable) {
        SeatAvailable = seatAvailable;
    }

    public Integer getCompartmentCapacity() {
        return CompartmentCapacity;
    }

    public void setCompartmentCapacity(Integer compartmentCapacity) {
        CompartmentCapacity = compartmentCapacity;
    }

    public String getSaloonName() {
        return SaloonName;
    }

    public void setSaloonName(String saloonName) {
        SaloonName = saloonName;
    }

    public Boolean getHotelIsIncluded() {
        return HotelIsIncluded;
    }

    public void setHotelIsIncluded(Boolean hotelIsIncluded) {
        HotelIsIncluded = hotelIsIncluded;
    }
}