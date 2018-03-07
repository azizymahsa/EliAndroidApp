
package com.eligasht.reservation.models.model.insurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BirthDateList {

    @SerializedName("BirthDate")
    @Expose
    private String birthDate;
    @SerializedName("PassNo")
    @Expose
    private Integer passNo;
    boolean anim=true;

    public boolean isAnim() {
        return anim;
    }

    public void setAnim(boolean anim) {
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
