package com.eligasht.service.model.newModel.xpackage.packageBasket.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPackageBasketParameterModel {
    @SerializedName("SearchKey")
    @Expose
    public String SearchKey;


    @SerializedName("RoomNo")
    @Expose

    public String RoomNo ;

    public String getSearchKey() {
        return SearchKey;
    }

    public void setSearchKey(String searchKey) {
        SearchKey = searchKey;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

}
