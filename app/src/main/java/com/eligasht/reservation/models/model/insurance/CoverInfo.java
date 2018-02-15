
package com.eligasht.reservation.models.model.insurance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoverInfo {

    @SerializedName("CoverLimit")
    @Expose
    private String coverLimit;
    @SerializedName("TitleEn")
    @Expose
    private String titleEn;
    @SerializedName("TitleFa")
    @Expose
    private String titleFa;

    public String getCoverLimit() {
        return coverLimit;
    }

    public void setCoverLimit(String coverLimit) {
        this.coverLimit = coverLimit;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleFa() {
        return titleFa;
    }

    public void setTitleFa(String titleFa) {
        this.titleFa = titleFa;
    }

}
