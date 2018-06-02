package com.eligasht.reservation.models;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;
/**
 * Created by Reza Nejati on 02,June,2018
 */
public class PassengerDBModel  extends SugarRecord {
    @Unique
    private Long id;

    String onvan, Otagh, Gender,  Nationality,  Nationality_ID,
     RqPassenger_Address,  RqPassenger_Birthdate,  RqPassenger_Email,
     RqPassenger_FirstNameEn,  RqPassenger_FirstNameFa,  RqPassenger_LastNameEn,
     RqPassenger_LastNameFa,  RqPassenger_Mobile,  RqPassenger_NationalCode,  RqPassenger_PassExpDate,
     RqPassenger_PassNo, RqPassenger_Tel;

    public PassengerDBModel(){}

    public PassengerDBModel(String onvan, String otagh, String gender, String nationality, String nationality_ID, String rqPassenger_Address, String rqPassenger_Birthdate, String rqPassenger_Email, String rqPassenger_FirstNameEn, String rqPassenger_FirstNameFa, String rqPassenger_LastNameEn, String rqPassenger_LastNameFa, String rqPassenger_Mobile, String rqPassenger_NationalCode, String rqPassenger_PassExpDate, String rqPassenger_PassNo, String rqPassenger_Tel) {
        this.onvan = onvan;
        Otagh = otagh;
        Gender = gender;
        Nationality = nationality;
        Nationality_ID = nationality_ID;
        RqPassenger_Address = rqPassenger_Address;
        RqPassenger_Birthdate = rqPassenger_Birthdate;
        RqPassenger_Email = rqPassenger_Email;
        RqPassenger_FirstNameEn = rqPassenger_FirstNameEn;
        RqPassenger_FirstNameFa = rqPassenger_FirstNameFa;
        RqPassenger_LastNameEn = rqPassenger_LastNameEn;
        RqPassenger_LastNameFa = rqPassenger_LastNameFa;
        RqPassenger_Mobile = rqPassenger_Mobile;
        RqPassenger_NationalCode = rqPassenger_NationalCode;
        RqPassenger_PassExpDate = rqPassenger_PassExpDate;
        RqPassenger_PassNo = rqPassenger_PassNo;
        RqPassenger_Tel = rqPassenger_Tel;
    }
}
