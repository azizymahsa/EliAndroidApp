package com.eligasht.reservation.models.model;

/**
 * Created by Mahsa.azizi on 1/7/2018.
 */

public class SearchParvazModelExp {
//header
    public String ArrivalCityNameFaR;
    public String  FlightArrivalTimeR;

    public String DepartureCityNameFaR;
    public String FlightTimeR;

    public String ArrivalCityNameFaB;
    public String  FlightArrivalTimeB;

    public String DepartureCityNameFaB;
    public String FlightTimeB;

    public long AdlCost;
    public String flGUID;//apiPassenger

    public String AirlineNameFa;
    public String AirlineCode;

    public String CabinClassNameFa;

    public int RemainSeats;
    public boolean IsCharter;

    public String AirlineNameEa;

  //detail
    public String Detail_DepartureAirportNameFaR;
    public String Detail_FlightTimeR;

    public String Detail_ArrivalAirportNameFaR;
    public String Detail_FlightArrivalTimeR;

    public String Detail_AirlineNameFaR;
    public String Detail_FlightNumberR;

    public String Detail_flGUID;//apiPassenger

    public long Detail_AdlBaseFare;
    public long Detail_Taxes;
    public long Detail_TotalFare;

    public String Detail_AirlineCode;

    public String Detail_DepartureCityNameFa;
    public String Detail_ArrivalCityNameFa;


    public SearchParvazModelExp(String arrivalCityNameFaR, String flightArrivalTimeR,
                                String departureCityNameFaR, String flightTimeR, String arrivalCityNameFaB,
                                String flightArrivalTimeB, String departureCityNameFaB, String flightTimeB
            , long adlCost, String flGUID, String airlineNameFa, String airlineCode, String cabinClassNameFa,
                                int remainSeats, boolean isCharter, String airlineNameEa, String detail_DepartureAirportNameFaR,
                                String detail_FlightTimeR, String detail_ArrivalAirportNameFaR, String detail_FlightArrivalTimeR,
                                String detail_AirlineNameFaR, String detail_FlightNumberR, String detail_flGUID,
                                long detail_AdlBaseFare, long detail_Taxes, long detail_TotalFare, String detail_AirlineCode, String detail_DepartureCityNameFa, String detail_ArrivalCityNameFa) {
        ArrivalCityNameFaR = arrivalCityNameFaR;
        FlightArrivalTimeR = flightArrivalTimeR;
        DepartureCityNameFaR = departureCityNameFaR;
        FlightTimeR = flightTimeR;
        ArrivalCityNameFaB = arrivalCityNameFaB;
        FlightArrivalTimeB = flightArrivalTimeB;
        DepartureCityNameFaB = departureCityNameFaB;
        FlightTimeB = flightTimeB;
        AdlCost = adlCost;
        this.flGUID = flGUID;
        AirlineNameFa = airlineNameFa;
        AirlineCode = airlineCode;
        CabinClassNameFa = cabinClassNameFa;
        RemainSeats = remainSeats;
        IsCharter = isCharter;
        AirlineNameEa = airlineNameEa;
        Detail_DepartureAirportNameFaR = detail_DepartureAirportNameFaR;
        Detail_FlightTimeR = detail_FlightTimeR;
        Detail_ArrivalAirportNameFaR = detail_ArrivalAirportNameFaR;
        Detail_FlightArrivalTimeR = detail_FlightArrivalTimeR;
        Detail_AirlineNameFaR = detail_AirlineNameFaR;
        Detail_FlightNumberR = detail_FlightNumberR;
        Detail_flGUID = detail_flGUID;
        Detail_AdlBaseFare = detail_AdlBaseFare;
        Detail_Taxes = detail_Taxes;
        Detail_TotalFare = detail_TotalFare;
        Detail_AirlineCode = detail_AirlineCode;
        Detail_DepartureCityNameFa = detail_DepartureCityNameFa;
        Detail_ArrivalCityNameFa = detail_ArrivalCityNameFa;
    }

    public  String  getArrivalCityNameFaR() {
      return ArrivalCityNameFaR;
  }public void    setArrivalCityNameFaR(String arrivalCityNameFaR) {
      ArrivalCityNameFaR = arrivalCityNameFaR;
  }public String  getFlightArrivalTimeR() {
      return FlightArrivalTimeR;
  }public void    setFlightArrivalTimeR(String flightArrivalTimeR) {
      FlightArrivalTimeR = flightArrivalTimeR;
  }public String  getDepartureCityNameFaR() {
      return DepartureCityNameFaR;
  }public void    setDepartureCityNameFaR(String departureCityNameFaR) {
      DepartureCityNameFaR = departureCityNameFaR;
  }public String  getFlightTimeR() {
      return FlightTimeR;
  }public void    setFlightTimeR(String flightTimeR) {
      FlightTimeR = flightTimeR;
  }public String  getArrivalCityNameFaB() {
      return ArrivalCityNameFaB;
  }public void    setArrivalCityNameFaB(String arrivalCityNameFaB) {
      ArrivalCityNameFaB = arrivalCityNameFaB;
  }public String  getFlightArrivalTimeB() {
      return FlightArrivalTimeB;
  }public void    setFlightArrivalTimeB(String flightArrivalTimeB) {
      FlightArrivalTimeB = flightArrivalTimeB;
  }public String  getDepartureCityNameFaB() {
      return DepartureCityNameFaB;
  }public void    setDepartureCityNameFaB(String departureCityNameFaB) {
      DepartureCityNameFaB = departureCityNameFaB;
  }public String  getFlightTimeB() {
      return FlightTimeB;
  }public void    setFlightTimeB(String flightTimeB) {
      FlightTimeB = flightTimeB;
  }public long    getAdlCost() {
      return AdlCost;
  }public void    setAdlCost(long adlCost) {
      AdlCost = adlCost;
  }public String  getFlGUID() {
      return flGUID;
  }public void    setFlGUID(String flGUID) {
      this.flGUID = flGUID;
  }public String  getAirlineNameFa() {
      return AirlineNameFa;
  }public void    setAirlineNameFa(String airlineNameFa) {
      AirlineNameFa = airlineNameFa;
  }public String  getAirlineCode() {
      return AirlineCode;
  }public void    setAirlineCode(String airlineCode) {
      AirlineCode = airlineCode;
  }public String  getCabinClassNameFa() {
      return CabinClassNameFa;
  }public void    setCabinClassNameFa(String cabinClassNameFa) {
      CabinClassNameFa = cabinClassNameFa;
  }public int     getRemainSeats() {
      return RemainSeats;
  }public void    setRemainSeats(int remainSeats) {
      RemainSeats = remainSeats;
  }public boolean isCharter() {
      return IsCharter;
  }public void    setCharter(boolean charter) {
      IsCharter = charter;
  }public String  getAirlineNameEa() {
      return AirlineNameEa;
  }public void    setAirlineNameEa(String airlineNameEa) {
      AirlineNameEa = airlineNameEa;
  }public String  getDetail_DepartureAirportNameFaR() {
      return Detail_DepartureAirportNameFaR;
  }public void    setDetail_DepartureAirportNameFaR(String detail_DepartureAirportNameFaR) {
      Detail_DepartureAirportNameFaR = detail_DepartureAirportNameFaR;
  }public String  getDetail_FlightTimeR() {
      return Detail_FlightTimeR;
  }public void    setDetail_FlightTimeR(String detail_FlightTimeR) {
      Detail_FlightTimeR = detail_FlightTimeR;
  }public String  getDetail_ArrivalAirportNameFaR() {
      return Detail_ArrivalAirportNameFaR;
  }public void    setDetail_ArrivalAirportNameFaR(String detail_ArrivalAirportNameFaR) {
      Detail_ArrivalAirportNameFaR = detail_ArrivalAirportNameFaR;
  }public String  getDetail_FlightArrivalTimeR() {
      return Detail_FlightArrivalTimeR;
  }public void    setDetail_FlightArrivalTimeR(String detail_FlightArrivalTimeR) {
      Detail_FlightArrivalTimeR = detail_FlightArrivalTimeR;
  }public String  getDetail_AirlineNameFaR() {
      return Detail_AirlineNameFaR;
  }public void    setDetail_AirlineNameFaR(String detail_AirlineNameFaR) {
      Detail_AirlineNameFaR = detail_AirlineNameFaR;
  }public String  getDetail_FlightNumberR() {
      return Detail_FlightNumberR;
  }public void    setDetail_FlightNumberR(String detail_FlightNumberR) {
      Detail_FlightNumberR = detail_FlightNumberR;
  }public String  getDetail_flGUID() {
      return Detail_flGUID;
  }public void    setDetail_flGUID(String detail_flGUID) {
      Detail_flGUID = detail_flGUID;
  }public long    getDetail_AdlBaseFare() {
      return Detail_AdlBaseFare;
  }public void    setDetail_AdlBaseFare(long detail_AdlBaseFare) {
      Detail_AdlBaseFare = detail_AdlBaseFare;
  }public long    getDetail_Taxes() {
      return Detail_Taxes;
  }public void    setDetail_Taxes(long detail_Taxes) {
      Detail_Taxes = detail_Taxes;
  }public long    getDetail_TotalFare() {
      return Detail_TotalFare;
  }public void    setDetail_TotalFare(long detail_TotalFare) {
      Detail_TotalFare = detail_TotalFare;
  }public String  getDetail_AirlineCode() {
      return Detail_AirlineCode;
  }public void    setDetail_AirlineCode(String detail_AirlineCode) {
      Detail_AirlineCode = detail_AirlineCode;
  }public String  getDetail_DepartureCityNameFa() {
      return Detail_DepartureCityNameFa;
  }public void    setDetail_DepartureCityNameFa(String detail_DepartureCityNameFa) {
      Detail_DepartureCityNameFa = detail_DepartureCityNameFa;
  }public String  getDetail_ArrivalCityNameFa() {
      return Detail_ArrivalCityNameFa;
  }public void    setDetail_ArrivalCityNameFa(String detail_ArrivalCityNameFa) {
      Detail_ArrivalCityNameFa = detail_ArrivalCityNameFa;
  }
}
