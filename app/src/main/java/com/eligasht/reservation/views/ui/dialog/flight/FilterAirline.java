package com.eligasht.reservation.views.ui.dialog.flight;

/**
 * Created by Mahsa.azizi on 1/9/2018.
 */

public  class FilterAirline {
  //  private boolean selected;

   /* public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
*/
    public String getNameAirline() {
        return nameAirline;
    }

    public void setNameAirline(String nameAirline) {
        this.nameAirline = nameAirline;
    }

    private  String nameAirline;

    public FilterAirline( String nameAirline) {
        //this.selected = selected;
        this.nameAirline = nameAirline;
    }
}
