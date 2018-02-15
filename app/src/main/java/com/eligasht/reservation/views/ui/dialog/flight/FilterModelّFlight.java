package com.eligasht.reservation.views.ui.dialog.flight;

/**
 * Created by Reza.nejati on 1/9/2018.
 */

public class FilterModelّFlight {
    boolean noStop;
    boolean oneStop;

    public boolean isNoStop() {
        return noStop;
    }

    public void setNoStop(boolean noStop) {
        this.noStop = noStop;
    }

    public boolean isOneStop() {
        return oneStop;
    }

    public void setOneStop(boolean oneStop) {
        this.oneStop = oneStop;
    }

    public boolean isTwoStopMore() {
        return twoStopMore;
    }

    public void setTwoStopMore(boolean twoStopMore) {
        this.twoStopMore = twoStopMore;
    }

    public boolean isRemove() {
        return Remove;
    }

    public void setRemove(boolean remove) {
        Remove = remove;
    }

    public boolean isEconomiF() {
        return economiF;
    }

    public void setEconomiF(boolean economiF) {
        this.economiF = economiF;
    }

    public boolean isBusinessF() {
        return businessF;
    }

    public void setBusinessF(boolean businessF) {
        this.businessF = businessF;
    }

    public boolean isFerstF() {
        return ferstF;
    }

    public void setFerstF(boolean ferstF) {
        this.ferstF = ferstF;
    }

    boolean twoStopMore;

    boolean Remove;

    boolean economiF;
    boolean businessF;
    boolean ferstF;

    public FilterModelّFlight(boolean noStop, boolean oneStop, boolean twoStopMore, boolean remove, boolean economiF, boolean businessF, boolean ferstF) {
        this.noStop = noStop;
        this.oneStop = oneStop;
        this.twoStopMore = twoStopMore;
        Remove = remove;
        this.economiF = economiF;
        this.businessF = businessF;
        this.ferstF = ferstF;
    }




}
