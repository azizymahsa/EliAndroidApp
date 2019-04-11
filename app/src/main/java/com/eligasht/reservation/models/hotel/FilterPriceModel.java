package com.eligasht.reservation.models.hotel;

/**
 * Created by Reza.nejati on 1/14/2018.
 */

public class FilterPriceModel {
    String diff;
    int x;
    boolean isCheck;
    String currencyCode;

    public FilterPriceModel(String diff, int x, boolean isCheck,String currencyCode) {
        this.diff = diff;
        this.x = x;
        this.isCheck = isCheck;
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheked(boolean cheked) {
        isCheck = cheked;
    }
}
