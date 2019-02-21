
package com.eligasht.service.model.newModel.xpackage.packageBasket.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackBasket {

    @SerializedName("PackSelectedHotelList")
    @Expose
    private List<PackSelectedHotelList> packSelectedHotelList = null;
    @SerializedName("PackSelectedFlightList")
    @Expose
    private List<PackSelectedFlightList> packSelectedFlightList = null;
    @SerializedName("PackSelectedRoomList")
    @Expose
    private List<PackSelectedRoomList> packSelectedRoomList = null;
    @SerializedName("PackSelectedServices")
    @Expose
    private List<PackSelectedService> packSelectedServices = null;
    @SerializedName("PackSelectedRout")
    @Expose
    private PackSelectedRout packSelectedRout;

    public List<PackSelectedHotelList> getPackSelectedHotelList() {
        return packSelectedHotelList;
    }

    public void setPackSelectedHotelList(List<PackSelectedHotelList> packSelectedHotelList) {
        this.packSelectedHotelList = packSelectedHotelList;
    }

    public List<PackSelectedFlightList> getPackSelectedFlightList() {
        return packSelectedFlightList;
    }

    public void setPackSelectedFlightList(List<PackSelectedFlightList> packSelectedFlightList) {
        this.packSelectedFlightList = packSelectedFlightList;
    }

    public List<PackSelectedRoomList> getPackSelectedRoomList() {
        return packSelectedRoomList;
    }

    public void setPackSelectedRoomList(List<PackSelectedRoomList> packSelectedRoomList) {
        this.packSelectedRoomList = packSelectedRoomList;
    }

    public List<PackSelectedService> getPackSelectedServices() {
        return packSelectedServices;
    }

    public void setPackSelectedServices(List<PackSelectedService> packSelectedServices) {
        this.packSelectedServices = packSelectedServices;
    }

    public PackSelectedRout getPackSelectedRout() {
        return packSelectedRout;
    }

    public void setPackSelectedRout(PackSelectedRout packSelectedRout) {
        this.packSelectedRout = packSelectedRout;
    }

}
