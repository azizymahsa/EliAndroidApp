package com.eligasht.reservation.models.model.pack.filter;

/**
 * Created by elham.bonyani on 1/20/18.
 */

public class PriceFilter {

    private double  maxPrice;
    private double  minPrice;
    private boolean isSelected;

    public PriceFilter(double  minPrice, double  maxPrice) {
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    public PriceFilter() {
    }

    public double  getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double  maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double  minPrice) {
        this.minPrice = minPrice;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
