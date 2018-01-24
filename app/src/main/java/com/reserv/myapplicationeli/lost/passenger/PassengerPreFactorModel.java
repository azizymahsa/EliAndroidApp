package com.reserv.myapplicationeli.lost.passenger;

/**
 * Created by Reza.nejati on 1/23/2018.
 */

public class PassengerPreFactorModel {

    String Gender;
    String Nationality;
    String RqPassenger_Birthdate;
    String RqPassenger_PassNo;
    String RqPassenger_name;

    public PassengerPreFactorModel(String gender, String nationality, String rqPassenger_Birthdate, String rqPassenger_PassNo, String rqPassenger_name) {
        Gender = gender;
        Nationality = nationality;
        RqPassenger_Birthdate = rqPassenger_Birthdate;
        RqPassenger_PassNo = rqPassenger_PassNo;
        RqPassenger_name = rqPassenger_name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getRqPassenger_Birthdate() {
        return RqPassenger_Birthdate;
    }

    public void setRqPassenger_Birthdate(String rqPassenger_Birthdate) {
        RqPassenger_Birthdate = rqPassenger_Birthdate;
    }

    public String getRqPassenger_PassNo() {
        return RqPassenger_PassNo;
    }

    public void setRqPassenger_PassNo(String rqPassenger_PassNo) {
        RqPassenger_PassNo = rqPassenger_PassNo;
    }

    public String getRqPassenger_name() {
        return RqPassenger_name;
    }

    public void setRqPassenger_name(String rqPassenger_name) {
        RqPassenger_name = rqPassenger_name;
    }
}


