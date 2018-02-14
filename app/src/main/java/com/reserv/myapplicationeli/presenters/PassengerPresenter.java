package com.reserv.myapplicationeli.presenters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.contracts.PassengerContract;
import com.reserv.myapplicationeli.models.model.insurance.BirthDateList;
import com.reserv.myapplicationeli.tools.Prefs;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.datetools.DateUtil;
import com.reserv.myapplicationeli.views.viewholders.PassengerRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class PassengerPresenter implements PassengerContract.Presenter{

    private final PassengerContract.View mView;
    private ArrayList<BirthDateList> passengers;
    boolean geo;


    public PassengerPresenter(PassengerContract.View mView) {
        this.mView = mView;
    }

    public void setPassengers(ArrayList<BirthDateList> passengerArrayList){
        if (passengerArrayList == null){
            passengers = new ArrayList<>();
            BirthDateList birthDateList = new BirthDateList();
            birthDateList.setPassNo(1);
            passengers.add(birthDateList);
        }else {
            passengers = passengerArrayList;
        }
        mView.setPassengersCount(getPassengersCount());

    }


    @Override
    public void addPassengers() {
        if (ValidationTools.isEmptyOrNull(getPassengers())) {
            return;
        }
        if(getPassengersCount() >= 9){
            return;
        }
        BirthDateList birthDateList = new BirthDateList();
        birthDateList.setPassNo(getPassengersCount() + 1);
        passengers.add(birthDateList);
        mView.notifyDataSetChange();
        mView.setPassengersCount(getPassengersCount());
    }

    @Override
    public void removePassengers() {
        if (ValidationTools.isEmptyOrNull(getPassengers())) {
            return;
        }

        if(getPassengersCount() == 1){
            return;
        }
        passengers.remove(getPassengersCount() - 1);
        mView.notifyDataSetChange();
        mView.setPassengersCount(getPassengersCount());
    }

    @Override
    public void setBirthday(BirthDateList passenger, String date,boolean geo) {
        passengers.get(passengers.indexOf(passenger)).setBirthDate(date);
        mView.notifyDataSetChange();
        this.geo=geo;

    }

    @Override
    public ArrayList<BirthDateList> getPassengers() {
        return passengers;
    }

    @Override
    public int getPassengersCount() {
        return (null != getPassengers() ? getPassengers().size() : 0);
    }

    @Override
    public PassengerRowHolder createViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_passenger_insurance, null);
        PassengerRowHolder mh = new PassengerRowHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return mh;
    }

    @Override
    public void bindViewHolder(PassengerRowHolder holder, int position) {
        if (ValidationTools.isEmptyOrNull(getPassengers())) {
            return;
        }
        final BirthDateList passenger = getPassengers().get(position);
        holder.txt_passenger_title.setText("مسافر" + " " + getStringPosition(position));
        if(!ValidationTools.isEmptyOrNull(passenger.getBirthDate())){
            if (geo){
                String birthDay = DateUtil.getLongStringDateInsurance(passenger.getBirthDate(),"yyyy-MM-dd",false);
                holder.txt_birthday.setText(birthDay);

            }else{
                String birthDay = DateUtil.getLongStringDateInsurance(passenger.getBirthDate(),"yyyy-MM-dd",true);
                holder.txt_birthday.setText(birthDay);

            }
        }else {
            holder.txt_birthday.setText("تاریخ تولد را انتخاب کنید");
        }
        holder.layout_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mView!=null){
                    mView.onSetBirthDayPassenger(passenger);
                    return;
                }
            }
        });

    }

    private String getStringPosition(int position) {
        switch (position) {
            case 0:
                return "اول";
            case 1:
                return "دوم";
            case 2:
                return "سوم";
            case 3:
                return "چهارم";
            case 4:
                return "پنجم";
            case 5:
                return "ششم";
            case 6:
                return "هفتم";
            case 7:
                return "هشتم";
            case 8:
                return "نهم";
            default:
                return "";
        }
    }
}
