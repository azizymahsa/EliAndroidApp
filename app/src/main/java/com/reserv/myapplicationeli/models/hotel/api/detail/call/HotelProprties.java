package com.reserv.myapplicationeli.models.hotel.api.detail.call;

/**
 * Created by Reza.nejati on 1/8/2018.
 */

public class HotelProprties {
   public final String Category;
   public final String PropertyCategory;
   public final String PropertyDescription;
   public final String PropertyTitle;
   public final String PropertyIcon;
   public final String PropertyIconFont;
   public final int CategoryID;

    public HotelProprties(String category, String propertyCategory, String propertyDescription, String propertyTitle, String propertyIcon, String propertyIconFont, int categoryID) {
        Category = category;
        PropertyCategory = propertyCategory;
        PropertyDescription = propertyDescription;
        PropertyTitle = propertyTitle;
        PropertyIcon = propertyIcon;
        PropertyIconFont = propertyIconFont;
        CategoryID = categoryID;
    }
}
