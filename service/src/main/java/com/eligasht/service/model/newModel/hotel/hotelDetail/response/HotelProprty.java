
package com.eligasht.service.model.newModel.hotel.hotelDetail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelProprty {

    @SerializedName("CategoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("PropertyCategoryID")
    @Expose
    private Integer propertyCategoryID;
    @SerializedName("PropertyCategory")
    @Expose
    private String propertyCategory;
    @SerializedName("PropertyID")
    @Expose
    private Integer propertyID;
    @SerializedName("PropertyTitle")
    @Expose
    private String propertyTitle;
    @SerializedName("PropertyDescription")
    @Expose
    private String propertyDescription;
    @SerializedName("PropertyIcon")
    @Expose
    private String propertyIcon;
    @SerializedName("PropertyIconFont")
    @Expose
    private String propertyIconFont;

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPropertyCategoryID() {
        return propertyCategoryID;
    }

    public void setPropertyCategoryID(Integer propertyCategoryID) {
        this.propertyCategoryID = propertyCategoryID;
    }

    public String getPropertyCategory() {
        return propertyCategory;
    }

    public void setPropertyCategory(String propertyCategory) {
        this.propertyCategory = propertyCategory;
    }

    public Integer getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(Integer propertyID) {
        this.propertyID = propertyID;
    }

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getPropertyIcon() {
        return propertyIcon;
    }

    public void setPropertyIcon(String propertyIcon) {
        this.propertyIcon = propertyIcon;
    }

    public String getPropertyIconFont() {
        return propertyIconFont;
    }

    public void setPropertyIconFont(String propertyIconFont) {
        this.propertyIconFont = propertyIconFont;
    }

}
