package com.eligasht.reservation.views.picker.global.model;

/**
 * Created by Ahmad.nemati on 3/10/2018.
 */

public class EventBusCalendar {
    private boolean isUpdated;

    public EventBusCalendar(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }
}
