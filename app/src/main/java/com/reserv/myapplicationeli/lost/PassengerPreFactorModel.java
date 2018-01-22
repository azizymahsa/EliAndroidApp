package com.reserv.myapplicationeli.lost;

/**
 * Created by Reza.nejati on 1/22/2018.
 */

public class PassengerPreFactorModel {
    String Gender;
    String Nationality;
    String RqPassenger_Birthdate;
    String RqPassenger_PassNo;
    String RqPassenger_FirstNameFa;
    String RqPassenger_LastNameFa;

    public PassengerPreFactorModel(String gender, String nationality, String rqPassenger_Birthdate, String rqPassenger_PassNo, String rqPassenger_FirstNameFa, String rqPassenger_LastNameFa) {
        Gender = gender;
        Nationality = nationality;
        RqPassenger_Birthdate = rqPassenger_Birthdate;
        RqPassenger_PassNo = rqPassenger_PassNo;
        RqPassenger_FirstNameFa = rqPassenger_FirstNameFa;
        RqPassenger_LastNameFa = rqPassenger_LastNameFa;
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

    public String getRqPassenger_FirstNameFa() {
        return RqPassenger_FirstNameFa;
    }

    public void setRqPassenger_FirstNameFa(String rqPassenger_FirstNameFa) {
        RqPassenger_FirstNameFa = rqPassenger_FirstNameFa;
    }

    public String getRqPassenger_LastNameFa() {
        return RqPassenger_LastNameFa;
    }

    public void setRqPassenger_LastNameFa(String rqPassenger_LastNameFa) {
        RqPassenger_LastNameFa = rqPassenger_LastNameFa;
    }
}
