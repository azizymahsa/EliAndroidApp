
package com.eligasht.service.model.flight.response.PurchaseFlight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstPassenger {

    @SerializedName("AgeID")
    @Expose
    private String ageID;
    @SerializedName("AgeNameEn")
    @Expose
    private String ageNameEn;
    @SerializedName("AgeNameFa")
    @Expose
    private String ageNameFa;
    @SerializedName("BithDate")
    @Expose
    private String bithDate;
    @SerializedName("NameEn")
    @Expose
    private String nameEn;
    @SerializedName("NameFa")
    @Expose
    private String nameFa;
    @SerializedName("NationalCode")
    @Expose
    private String nationalCode;
    @SerializedName("PassengerID")
    @Expose
    private Integer passengerID;

    public String getAgeID() {
        return ageID;
    }

    public void setAgeID(String ageID) {
        this.ageID = ageID;
    }

    public String getAgeNameEn() {
        return ageNameEn;
    }

    public void setAgeNameEn(String ageNameEn) {
        this.ageNameEn = ageNameEn;
    }

    public String getAgeNameFa() {
        return ageNameFa;
    }

    public void setAgeNameFa(String ageNameFa) {
        this.ageNameFa = ageNameFa;
    }

    public String getBithDate() {
        return bithDate;
    }

    public void setBithDate(String bithDate) {
        this.bithDate = bithDate;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameFa() {
        return nameFa;
    }

    public void setNameFa(String nameFa) {
        this.nameFa = nameFa;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Integer getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(Integer passengerID) {
        this.passengerID = passengerID;
    }

}
