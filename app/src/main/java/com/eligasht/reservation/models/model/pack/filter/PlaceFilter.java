package com.eligasht.reservation.models.model.pack.filter;

/**
 * Created by elham.bonyani on 1/20/18.
 */

public class PlaceFilter {

    private String locationName;
    private int locationId;
    private boolean isSelected;

    public PlaceFilter(int locationId,String locationName) {
        this.locationName = locationName;
        this.locationId = locationId;
    }

    public PlaceFilter() {
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
