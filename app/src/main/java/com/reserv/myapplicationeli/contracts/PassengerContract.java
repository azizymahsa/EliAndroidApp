package com.reserv.myapplicationeli.contracts;

import android.content.Context;
import android.view.ViewGroup;

import com.reserv.myapplicationeli.models.model.insurance.BirthDateList;
import com.reserv.myapplicationeli.views.viewholders.PassengerRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public interface PassengerContract {
    interface View {
        Context getAppContext();
        void initViews();
        void showLoading();
        void hideLoading();
        void showPassengers();
        void setPassengersCount(int count);
        void notifyDataSetChange();
        void notifyItemInserted(int layoutPosition);
        void notifyItemRemoved(int layoutPosition);
        void notifyItemRangeChanged(int positionStart, int itemCount);
        void onSetBirthDayPassenger(BirthDateList birthDateList);
    }

    interface Presenter {
        void addPassengers();
        void removePassengers();
        void setBirthday(BirthDateList birthDateList, String date,boolean geo);
        ArrayList<BirthDateList> getPassengers();
        int getPassengersCount();
        PassengerRowHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(PassengerRowHolder holder, int position);
    }
}
