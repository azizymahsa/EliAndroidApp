package com.reserv.myapplicationeli.views.ui.OBGParvaz;

import java.util.List;

public class Flight {
   

    private PriceField AdlBaseFare ;

    private PriceField AdlCost ;

    private PriceField AdlTotalFare ;
    
    private PriceField BaseFare ;

    private PriceField ChdBaseFare ;

    private PriceField ChdCost ;

    private PriceField ChdTotalFare ;
    
    private PriceField InfBaseFare ;

    private PriceField InfCost ;

    private PriceField InfTotalFare ;
	private PriceField Taxes ;

    private PriceField TotalFare ;

    private PriceField TotalFareCost ;
    
    
    
    private Error Error ;

    private int Adults ;

    private String AccountID;
    
    private int Childs ;

    private int FareTypeID ;

    private String FlightGUID ;

    private String FlightID ;

    private int FlightTypeID ;

    private int Infants ;

    private boolean IsIntegration ;

    private boolean IsOpenjaw ;

    private boolean OneWay ;

    private String Passengers ;

    private List<FlightSegment> SegmentList ;
    private List<FlightSegmentTrue> SegmentListTrue ;
    private List<FlightSegmentFalse> SegmentListFalse ;

    private boolean IsCharter;
    private int RemainSeats ;
    public String getAccountID() {
		return AccountID;
	}

	public void setAccountID(String accountID) {
		AccountID = accountID;
	}

	public PriceField getAdlBaseFare() {
		return AdlBaseFare;
	}

	public void setAdlBaseFare(PriceField adlBaseFare) {
		AdlBaseFare = adlBaseFare;
	}

	public PriceField getAdlCost() {
		return AdlCost;
	}

	public void setAdlCost(PriceField adlCost) {
		AdlCost = adlCost;
	}

	public PriceField getAdlTotalFare() {
		return AdlTotalFare;
	}

	public void setAdlTotalFare(PriceField adlTotalFare) {
		AdlTotalFare = adlTotalFare;
	}

	public int getAdults() {
		return Adults;
	}

	public void setAdults(int adults) {
		Adults = adults;
	}

	public PriceField getBaseFare() {
		return BaseFare;
	}

	public void setBaseFare(PriceField baseFare) {
		BaseFare = baseFare;
	}

	public PriceField getChdBaseFare() {
		return ChdBaseFare;
	}

	public void setChdBaseFare(PriceField chdBaseFare) {
		ChdBaseFare = chdBaseFare;
	}

	public PriceField getChdCost() {
		return ChdCost;
	}

	public void setChdCost(PriceField chdCost) {
		ChdCost = chdCost;
	}

	public PriceField getChdTotalFare() {
		return ChdTotalFare;
	}

	public void setChdTotalFare(PriceField chdTotalFare) {
		ChdTotalFare = chdTotalFare;
	}

	public int getChilds() {
		return Childs;
	}

	public void setChilds(int childs) {
		Childs = childs;
	}

	public Error getError() {
		return Error;
	}

	public void setError(Error error) {
		Error = error;
	}

	public int getFareTypeID() {
		return FareTypeID;
	}

	public void setFareTypeID(int fareTypeID) {
		FareTypeID = fareTypeID;
	}

	public String getFlightGUID() {
		return FlightGUID;
	}

	public void setFlightGUID(String flightGUID) {
		FlightGUID = flightGUID;
	}

	public String getFlightID() {
		return FlightID;
	}

	public void setFlightID(String flightID) {
		FlightID = flightID;
	}

	public int getFlightTypeID() {
		return FlightTypeID;
	}

	public void setFlightTypeID(int flightTypeID) {
		FlightTypeID = flightTypeID;
	}

	public PriceField getInfBaseFare() {
		return InfBaseFare;
	}

	public void setInfBaseFare(PriceField infBaseFare) {
		InfBaseFare = infBaseFare;
	}

	public PriceField getInfCost() {
		return InfCost;
	}

	public void setInfCost(PriceField infCost) {
		InfCost = infCost;
	}

	public PriceField getInfTotalFare() {
		return InfTotalFare;
	}

	public void setInfTotalFare(PriceField infTotalFare) {
		InfTotalFare = infTotalFare;
	}

	public int getInfants() {
		return Infants;
	}

	public void setInfants(int infants) {
		Infants = infants;
	}

	public boolean isIsIntegration() {
		return IsIntegration;
	}

	public void setIsIntegration(boolean isIntegration) {
		IsIntegration = isIntegration;
	}

	public boolean isIsOpenjaw() {
		return IsOpenjaw;
	}

	public void setIsOpenjaw(boolean isOpenjaw) {
		IsOpenjaw = isOpenjaw;
	}

	public boolean isOneWay() {
		return OneWay;
	}

	public void setOneWay(boolean oneWay) {
		OneWay = oneWay;
	}

	public String getPassengers() {
		return Passengers;
	}

	public void setPassengers(String passengers) {
		Passengers = passengers;
	}

	public List<FlightSegment> getSegmentList() {
		return SegmentList;
	}

	public void setSegmentList(List<FlightSegment> segmentList) {
		SegmentList = segmentList;
	}

	public PriceField getTaxes() {
		return Taxes;
	}

	public void setTaxes(PriceField taxes) {
		Taxes = taxes;
	}

	public PriceField getTotalFare() {
		return TotalFare;
	}

	public void setTotalFare(PriceField totalFare) {
		TotalFare = totalFare;
	}

	public PriceField getTotalFareCost() {
		return TotalFareCost;
	}

	public void setTotalFareCost(PriceField totalFareCost) {
		TotalFareCost = totalFareCost;
	}

	public List<FlightSegmentTrue> getSegmentListTrue() {
		return SegmentListTrue;
	}

	public void setSegmentListTrue(List<FlightSegmentTrue> segmentListTrue) {
		SegmentListTrue = segmentListTrue;
	}

	public List<FlightSegmentFalse> getSegmentListFalse() {
		return SegmentListFalse;
	}

	public void setSegmentListFalse(List<FlightSegmentFalse> segmentListFalse) {
		SegmentListFalse = segmentListFalse;
	}

	public boolean isIsCharter() {
		return IsCharter;
	}

	public void setIsCharter(boolean isCharter) {
		IsCharter = isCharter;
	}

	public int getRemainSeats() {
		return RemainSeats;
	}

	public void setRemainSeats(int remainSeats) {
		RemainSeats = remainSeats;
	}



}
