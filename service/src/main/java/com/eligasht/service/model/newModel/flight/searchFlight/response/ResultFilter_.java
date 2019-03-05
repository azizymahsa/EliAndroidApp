
package com.eligasht.service.model.newModel.flight.searchFlight.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultFilter_ {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("MinPrice")
    @Expose
    private Integer minPrice;
    @SerializedName("MinRangePrice")
    @Expose
    private String minRangePrice;
    @SerializedName("MidRangePrice")
    @Expose
    private Object midRangePrice;
    @SerializedName("MixRangePrice")
    @Expose
    private Object mixRangePrice;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;
    @SerializedName("TextFa")
    @Expose
    private String textFa;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("Selected")
    @Expose
    private Boolean selected;
    @SerializedName("Icon")
    @Expose
    private String icon;
    @SerializedName("MoreDetailText")
    @Expose
    private Object moreDetailText;
    @SerializedName("MoreDetailTextFa")
    @Expose
    private Object moreDetailTextFa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public String getMinRangePrice() {
        return minRangePrice;
    }

    public void setMinRangePrice(String minRangePrice) {
        this.minRangePrice = minRangePrice;
    }

    public Object getMidRangePrice() {
        return midRangePrice;
    }

    public void setMidRangePrice(Object midRangePrice) {
        this.midRangePrice = midRangePrice;
    }

    public Object getMixRangePrice() {
        return mixRangePrice;
    }

    public void setMixRangePrice(Object mixRangePrice) {
        this.mixRangePrice = mixRangePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTextFa() {
        return textFa;
    }

    public void setTextFa(String textFa) {
        this.textFa = textFa;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Object getMoreDetailText() {
        return moreDetailText;
    }

    public void setMoreDetailText(Object moreDetailText) {
        this.moreDetailText = moreDetailText;
    }

    public Object getMoreDetailTextFa() {
        return moreDetailTextFa;
    }

    public void setMoreDetailTextFa(Object moreDetailTextFa) {
        this.moreDetailTextFa = moreDetailTextFa;
    }

}
