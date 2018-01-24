package com.reserv.myapplicationeli.models.model.pack.filter;

import com.reserv.myapplicationeli.models.model.pack.LstHotelAmenity;

/**
 * Created by elham.bonyani on 1/20/18.
 */

public class AmenityFilter {

    public LstHotelAmenity lstHotelAmenity;
    public boolean isSelected;

    public AmenityFilter(LstHotelAmenity lstHotelAmenity) {
        this.lstHotelAmenity = lstHotelAmenity;
    }

    public AmenityFilter() {
    }

    public LstHotelAmenity getLstHotelAmenity() {
        return lstHotelAmenity;
    }

    public void setLstHotelAmenity(LstHotelAmenity lstHotelAmenity) {
        this.lstHotelAmenity = lstHotelAmenity;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
