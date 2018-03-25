package com.eligasht.reservation.views.activities.transfer;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class ExcursionDta {
    public final String ArrialAirportCode;
    public final String ArrialAirportName;
    public final String ArrivalFltDate;
    public final String ArrivalFltNo;
    public final String ArrivalFltTime;
    public final String CityID;
    public final String DepartureFltDate;
    public final String DepartureFltNo;
    public final String DepartureFltTime;
    public final String HotelID;
    public final String HotelNameEn;
    public final String PassengerList;

    public ExcursionDta(String arrialAirportCode, String arrialAirportName, String arrivalFltDate, String arrivalFltNo, String arrivalFltTime, String cityID, String departureFltDate, String departureFltNo, String departureFltTime, String hotelID, String hotelNameEn, String passengerList) {
        ArrialAirportCode = arrialAirportCode;
        ArrialAirportName = arrialAirportName;
        ArrivalFltDate = arrivalFltDate;
        ArrivalFltNo = arrivalFltNo;
        ArrivalFltTime = arrivalFltTime;
        CityID = cityID;
        DepartureFltDate = departureFltDate;
        DepartureFltNo = departureFltNo;
        DepartureFltTime = departureFltTime;
        HotelID = hotelID;
        HotelNameEn = hotelNameEn;
        PassengerList = passengerList;
    }
}
