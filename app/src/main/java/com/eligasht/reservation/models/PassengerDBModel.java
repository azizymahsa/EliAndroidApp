package com.eligasht.reservation.models;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;
/**
 * Created by Reza Nejati on 02,June,2018
 */
public class PassengerDBModel  extends SugarRecord {
    @Unique
    private Long id;

   private String onvan, Otagh, Gender,  Nationality,  Nationality_ID,
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
    public void update(String onvan, String otagh, String gender, String nationality, String nationality_ID, String rqPassenger_Address, String rqPassenger_Birthdate, String rqPassenger_Email, String rqPassenger_FirstNameEn, String rqPassenger_FirstNameFa, String rqPassenger_LastNameEn, String rqPassenger_LastNameFa, String rqPassenger_Mobile, String rqPassenger_NationalCode, String rqPassenger_PassExpDate, String rqPassenger_PassNo, String rqPassenger_Tel){
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

    public String getOnvan() {
        return onvan;
    }

    public void setOnvan(String onvan) {
        this.onvan = onvan;
    }

    public String getOtagh() {
        return Otagh;
    }

    public void setOtagh(String otagh) {
        Otagh = otagh;
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

    public String getNationality_ID() {
        return Nationality_ID;
    }

    public void setNationality_ID(String nationality_ID) {
        Nationality_ID = nationality_ID;
    }

    public String getRqPassenger_Address() {
        return RqPassenger_Address;
    }

    public void setRqPassenger_Address(String rqPassenger_Address) {
        RqPassenger_Address = rqPassenger_Address;
    }

    public String getRqPassenger_Birthdate() {
        return RqPassenger_Birthdate;
    }

    public void setRqPassenger_Birthdate(String rqPassenger_Birthdate) {
        RqPassenger_Birthdate = rqPassenger_Birthdate;
    }

    public String getRqPassenger_Email() {
        return RqPassenger_Email;
    }

    public void setRqPassenger_Email(String rqPassenger_Email) {
        RqPassenger_Email = rqPassenger_Email;
    }

    public String getRqPassenger_FirstNameEn() {
        return RqPassenger_FirstNameEn;
    }

    public void setRqPassenger_FirstNameEn(String rqPassenger_FirstNameEn) {
        RqPassenger_FirstNameEn = rqPassenger_FirstNameEn;
    }

    public String getRqPassenger_FirstNameFa() {
        return RqPassenger_FirstNameFa;
    }

    public void setRqPassenger_FirstNameFa(String rqPassenger_FirstNameFa) {
        RqPassenger_FirstNameFa = rqPassenger_FirstNameFa;
    }

    public String getRqPassenger_LastNameEn() {
        return RqPassenger_LastNameEn;
    }

    public void setRqPassenger_LastNameEn(String rqPassenger_LastNameEn) {
        RqPassenger_LastNameEn = rqPassenger_LastNameEn;
    }

    public String getRqPassenger_LastNameFa() {
        return RqPassenger_LastNameFa;
    }

    public void setRqPassenger_LastNameFa(String rqPassenger_LastNameFa) {
        RqPassenger_LastNameFa = rqPassenger_LastNameFa;
    }

    public String getRqPassenger_Mobile() {
        return RqPassenger_Mobile;
    }

    public void setRqPassenger_Mobile(String rqPassenger_Mobile) {
        RqPassenger_Mobile = rqPassenger_Mobile;
    }

    public String getRqPassenger_NationalCode() {
        return RqPassenger_NationalCode;
    }

    public void setRqPassenger_NationalCode(String rqPassenger_NationalCode) {
        RqPassenger_NationalCode = rqPassenger_NationalCode;
    }

    public String getRqPassenger_PassExpDate() {
        return RqPassenger_PassExpDate;
    }

    public void setRqPassenger_PassExpDate(String rqPassenger_PassExpDate) {
        RqPassenger_PassExpDate = rqPassenger_PassExpDate;
    }

    public String getRqPassenger_PassNo() {
        return RqPassenger_PassNo;
    }

    public void setRqPassenger_PassNo(String rqPassenger_PassNo) {
        RqPassenger_PassNo = rqPassenger_PassNo;
    }

    public String getRqPassenger_Tel() {
        return RqPassenger_Tel;
    }

    public void setRqPassenger_Tel(String rqPassenger_Tel) {
        RqPassenger_Tel = rqPassenger_Tel;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
