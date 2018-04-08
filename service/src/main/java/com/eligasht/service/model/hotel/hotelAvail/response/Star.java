
package com.eligasht.service.model.hotel.hotelAvail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Star {

    @SerializedName("Count")
    @Expose
    private int count;
    @SerializedName("EId")
    @Expose
    private String eId;
    @SerializedName("HotelDetailURL")
    @Expose
    private Object hotelDetailURL;
    @SerializedName("Icon")
    @Expose
    private Object icon;
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Title")
    @Expose
    private String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getEId() {
        return eId;
    }

    public void setEId(String eId) {
        this.eId = eId;
    }

    public Object getHotelDetailURL() {
        return hotelDetailURL;
    }

    public void setHotelDetailURL(Object hotelDetailURL) {
        this.hotelDetailURL = hotelDetailURL;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}
    public String toString() {
        return new ToStringBuilder(this).append("count", count).append("eId", eId).append("hotelDetailURL", hotelDetailURL).append("icon", icon).append("id", id).append("title", title).toString();
    }

}