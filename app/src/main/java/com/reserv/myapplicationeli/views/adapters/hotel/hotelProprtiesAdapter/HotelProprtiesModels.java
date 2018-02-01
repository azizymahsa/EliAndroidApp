package com.reserv.myapplicationeli.views.adapters.hotel.hotelProprtiesAdapter;

/**
 * Created by Reza.nejati on 1/8/2018.
 */

public class HotelProprtiesModels {
    String PropertyTitle;
    String PropertyCat;
    String Image;

    public HotelProprtiesModels(String propertyTitle, String propertyCat, String image) {
        PropertyTitle = propertyTitle;
        PropertyCat = propertyCat;
        Image = image;
    }

    public String getPropertyTitle() {
        return PropertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        PropertyTitle = propertyTitle;
    }

    public String getPropertyCat() {
        return PropertyCat;
    }

    public void setPropertyCat(String propertyCat) {
        PropertyCat = propertyCat;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
