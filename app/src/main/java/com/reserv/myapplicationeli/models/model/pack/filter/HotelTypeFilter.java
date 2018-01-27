package com.reserv.myapplicationeli.models.model.pack.filter;

import com.reserv.myapplicationeli.models.model.pack.LstHotelAmenity;

/**
 * Created by elham.bonyani on 1/20/18.
 */

public class HotelTypeFilter {

    public String hotelTypeNameEn;
    public String hotelTypeNameFa;
    public boolean isSelected;

    public HotelTypeFilter(String hotelTypeNameEn,String hotelTypeNameFa) {
        this.hotelTypeNameEn = hotelTypeNameEn;
        this.hotelTypeNameFa = hotelTypeNameFa;
    }

    public HotelTypeFilter() {
    }

    public String getHotelTypeNameEn() {
        return hotelTypeNameEn;
    }

    public void setHotelTypeNameEn(String hotelTypeNameEn) {
        this.hotelTypeNameEn = hotelTypeNameEn;
    }

    public String getHotelTypeNameFa() {
        return hotelTypeNameFa;
    }

    public void setHotelTypeNameFa(String hotelTypeNameFa) {
        this.hotelTypeNameFa = hotelTypeNameFa;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
