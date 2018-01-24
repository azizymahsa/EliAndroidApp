package com.reserv.myapplicationeli.models.model.pack.filter;

/**
 * Created by elham.bonyani on 1/20/18.
 */

public class PriceFilter {

    private int maxPrice;
    private int minPrice;
    private boolean isSelected;

    public PriceFilter(int minPrice, int maxPrice) {
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    public PriceFilter() {
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
