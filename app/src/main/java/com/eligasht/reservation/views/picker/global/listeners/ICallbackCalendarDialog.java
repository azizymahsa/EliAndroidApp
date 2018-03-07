package com.eligasht.reservation.views.picker.global.listeners;


import com.eligasht.reservation.views.picker.global.model.CustomDate;

public interface ICallbackCalendarDialog {


    void onDateSelected(CustomDate startDate, CustomDate endDate, boolean isGeo);

}
