
package com.eligasht.service.model.hotel.hotelAvail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Facility_ {

    @SerializedName("Count")
    @Expose
    private int count;
    @SerializedName("EId")
    @Expose
    private String eId;
    @SerializedName("HotelDetailURL")
    @Expose
    private String hotelDetailURL;
    @SerializedName("Icon")
    @Expose
    private String icon;
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

    public String getHotelDetailURL() {
        return hotelDetailURL;
    }

    public void setHotelDetailURL(String hotelDetailURL) {
        this.hotelDetailURL = hotelDetailURL;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
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


}
