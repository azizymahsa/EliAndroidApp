
package com.eligasht.service.model.hotel.detail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelProprty {

    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("CategoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("PropertyCategory")
    @Expose
    private String propertyCategory;
    @SerializedName("PropertyCategoryID")
    @Expose
    private Integer propertyCategoryID;
    @SerializedName("PropertyDescription")
    @Expose
    private String propertyDescription;
    @SerializedName("PropertyID")
    @Expose
    private Integer propertyID;
    @SerializedName("PropertyIcon")
    @Expose
    private String propertyIcon;
    @SerializedName("PropertyIconFont")
    @Expose
    private String propertyIconFont;
    @SerializedName("PropertyTitle")
    @Expose
    private String propertyTitle;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getPropertyCategory() {
        return propertyCategory;
    }

    public void setPropertyCategory(String propertyCategory) {
        this.propertyCategory = propertyCategory;
    }

    public Integer getPropertyCategoryID() {
        return propertyCategoryID;
    }

    public void setPropertyCategoryID(Integer propertyCategoryID) {
        this.propertyCategoryID = propertyCategoryID;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public Integer getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(Integer propertyID) {
        this.propertyID = propertyID;
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

    public String getPropertyTitle() {
        return propertyTitle;
    }

    public void setPropertyTitle(String propertyTitle) {
        this.propertyTitle = propertyTitle;
    }

}
