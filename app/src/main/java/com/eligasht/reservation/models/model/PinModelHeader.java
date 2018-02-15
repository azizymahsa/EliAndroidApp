package com.eligasht.reservation.models.model;

/**
 * Created by Mahsa.azizi on 1/27/2018.
 */

public class PinModelHeader {

    private String txtPin;

    private String txtArrivelFalseLast;
    private String txtDepurtureFalseOne;

    private String txtArrivelTrueLast;
    private String txtDepurtureTrueOne;


    private String num_flight_r;
    private String  num_flight_b;

    private String  lblArrivalCityNameFaR;
    private String lblFlightArrivalTimeR;

    private String lblArrivalCityNameFaB;
    private String lblFlightArrivalTimeB;

    private String  lblAdlCost;


    private String lblProductrow;

    private String txt_economi;

    private String txttedad;
    private int  SegmentFalseCount;
    private int  RemainSeats;

    public int getSegmentFalseCount() {
        return SegmentFalseCount;
    }

    public void setSegmentFalseCount(int segmentFalseCount) {
        SegmentFalseCount = segmentFalseCount;
    }

    public int getRemainSeats() {
        return RemainSeats;
    }

    public void setRemainSeats(int remainSeats) {
        RemainSeats = remainSeats;
    }

    public PinModelHeader(String txtPin, String txtArrivelFalseLast, String txtDepurtureFalseOne, String txtArrivelTrueLast, String txtDepurtureTrueOne, String num_flight_r, String num_flight_b, String lblArrivalCityNameFaR, String lblFlightArrivalTimeR, String lblArrivalCityNameFaB, String lblFlightArrivalTimeB, String lblAdlCost, String lblProductrow, String txt_economi, String txttedad, int SegmentFalseCount, int RemainSeats) {
        this.txtPin = txtPin;
        this.txtArrivelFalseLast = txtArrivelFalseLast;
        this.txtDepurtureFalseOne = txtDepurtureFalseOne;
        this.txtArrivelTrueLast = txtArrivelTrueLast;
        this.txtDepurtureTrueOne = txtDepurtureTrueOne;
        this.num_flight_r = num_flight_r;
        this.num_flight_b = num_flight_b;
        this.lblArrivalCityNameFaR = lblArrivalCityNameFaR;
        this.lblFlightArrivalTimeR = lblFlightArrivalTimeR;
        this.lblArrivalCityNameFaB = lblArrivalCityNameFaB;
        this.lblFlightArrivalTimeB = lblFlightArrivalTimeB;
        this.lblAdlCost = lblAdlCost;
        this.lblProductrow = lblProductrow;
        this.txt_economi = txt_economi;
        this.txttedad = txttedad;
        this.RemainSeats=RemainSeats;
        this.SegmentFalseCount=SegmentFalseCount;
    }



    public String getTxtPin() {
        return txtPin;
    }

    public void setTxtPin(String txtPin) {
        this.txtPin = txtPin;
    }

    public String getTxtArrivelFalseLast() {
        return txtArrivelFalseLast;
    }

    public void setTxtArrivelFalseLast(String txtArrivelFalseLast) {
        this.txtArrivelFalseLast = txtArrivelFalseLast;
    }

    public String getTxtDepurtureFalseOne() {
        return txtDepurtureFalseOne;
    }

    public void setTxtDepurtureFalseOne(String txtDepurtureFalseOne) {
        this.txtDepurtureFalseOne = txtDepurtureFalseOne;
    }

    public String getTxtArrivelTrueLast() {
        return txtArrivelTrueLast;
    }

    public void setTxtArrivelTrueLast(String txtArrivelTrueLast) {
        this.txtArrivelTrueLast = txtArrivelTrueLast;
    }

    public String getTxtDepurtureTrueOne() {
        return txtDepurtureTrueOne;
    }

    public void setTxtDepurtureTrueOne(String txtDepurtureTrueOne) {
        this.txtDepurtureTrueOne = txtDepurtureTrueOne;
    }

    public String getNum_flight_r() {
        return num_flight_r;
    }

    public void setNum_flight_r(String num_flight_r) {
        this.num_flight_r = num_flight_r;
    }

    public String getNum_flight_b() {
        return num_flight_b;
    }

    public void setNum_flight_b(String num_flight_b) {
        this.num_flight_b = num_flight_b;
    }

    public String getLblArrivalCityNameFaR() {
        return lblArrivalCityNameFaR;
    }

    public void setLblArrivalCityNameFaR(String lblArrivalCityNameFaR) {
        this.lblArrivalCityNameFaR = lblArrivalCityNameFaR;
    }

    public String getLblFlightArrivalTimeR() {
        return lblFlightArrivalTimeR;
    }

    public void setLblFlightArrivalTimeR(String lblFlightArrivalTimeR) {
        this.lblFlightArrivalTimeR = lblFlightArrivalTimeR;
    }

    public String getLblArrivalCityNameFaB() {
        return lblArrivalCityNameFaB;
    }

    public void setLblArrivalCityNameFaB(String lblArrivalCityNameFaB) {
        this.lblArrivalCityNameFaB = lblArrivalCityNameFaB;
    }

    public String getLblFlightArrivalTimeB() {
        return lblFlightArrivalTimeB;
    }

    public void setLblFlightArrivalTimeB(String lblFlightArrivalTimeB) {
        this.lblFlightArrivalTimeB = lblFlightArrivalTimeB;
    }

    public String getLblAdlCost() {
        return lblAdlCost;
    }

    public void setLblAdlCost(String lblAdlCost) {
        this.lblAdlCost = lblAdlCost;
    }

    public String getLblProductrow() {
        return lblProductrow;
    }

    public void setLblProductrow(String lblProductrow) {
        this.lblProductrow = lblProductrow;
    }

    public String getTxt_economi() {
        return txt_economi;
    }

    public void setTxt_economi(String txt_economi) {
        this.txt_economi = txt_economi;
    }

    public String getTxttedad() {
        return txttedad;
    }

    public void setTxttedad(String txttedad) {
        this.txttedad = txttedad;
    }
}
