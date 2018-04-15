
package com.eligasht.service.model.insurance.request.SearchInsurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BirthDateList {

    @SerializedName("anim")
    @Expose
    private Boolean anim;
    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("PassNo")
    @Expose
    private Integer passNo;

    public Boolean getAnim() {
        return anim;
    }

    public void setAnim(Boolean anim) {
        this.anim = anim;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getPassNo() {
        return passNo;
    }

    public void setPassNo(Integer passNo) {
        this.passNo = passNo;
    }

}
