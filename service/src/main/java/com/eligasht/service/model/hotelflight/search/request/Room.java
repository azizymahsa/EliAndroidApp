
package com.eligasht.service.model.hotelflight.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {


    @SerializedName("AdultCount")
    @Expose
    private int adultCount;
    @SerializedName("ChildCount")
    @Expose
    private int childCount;

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public void showLog(){ android.util.Log.e(this.getClass().getSimpleName(),toString());}

    public Room(int adultCount, int childCount) {
        this.adultCount = adultCount;
        this.childCount = childCount;
    }

}
