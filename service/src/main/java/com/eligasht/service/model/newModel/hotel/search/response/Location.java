
package com.eligasht.service.model.newModel.hotel.search.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Icon")
    @Expose
    private String icon;
    @SerializedName("Count")
    @Expose
    private Integer count;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("MinRangePrice")
    @Expose
    private Integer minRangePrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinRangePrice() {
        return minRangePrice;
    }

    public void setMinRangePrice(Integer minRangePrice) {
        this.minRangePrice = minRangePrice;
    }

}
