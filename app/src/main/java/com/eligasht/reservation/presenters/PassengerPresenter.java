package com.eligasht.reservation.presenters;


import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.eligasht.R;
import com.eligasht.reservation.contracts.PassengerContract;
import com.eligasht.reservation.models.model.insurance.BirthDateList;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.views.viewholders.PassengerRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class PassengerPresenter implements PassengerContract.Presenter {

    private final PassengerContract.View mView;
    private ArrayList<BirthDateList> passengers;
    boolean geo;
    Context context;
    PassengerRowHolder holder;


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








        if (getPassengersCount()!=1){
            Animation animations = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
            holder.card_passenger.startAnimation(animations);
            Handler handle = new Handler();
            handle.postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub

                    if (getPassengersCount()!=1) {

                        passengers.remove(getPassengersCount() - 1);
                        mView.notifyDataSetChange();
                        mView.setPassengersCount(getPassengersCount());
                    }
                }
            }, 400);

        }




    }

    @Override
    public void setBirthday(BirthDateList passenger, String date,boolean geo) {
        Log.e("date", date);
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
        this.context=parent.getContext();
        return mh;
    }

    @Override
    public void bindViewHolder(PassengerRowHolder holder, int position) {
        this.holder=holder;
        if (ValidationTools.isEmptyOrNull(getPassengers())) {
            return;
        }
        final BirthDateList passenger = getPassengers().get(position);
        holder.txt_passenger_title.setText(context.getString(R.string.Passanger)+ " " + getStringPosition(position));
        if(!ValidationTools.isEmptyOrNull(passenger.getBirthDate())){
            if (geo){
                String birthDay = DateUtil.getLongStringDateInsurance(passenger.getBirthDate(),"yyyy-MM-dd",false);
                holder.txt_birthday.setText(birthDay);

            }else{
                String birthDay = DateUtil.getLongStringDateInsurance(passenger.getBirthDate(),"yyyy-MM-dd",true);
                holder.txt_birthday.setText(birthDay);

            }
        }else {
            holder.txt_birthday.setText(context.getString(R.string.Brithday));
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

        if (passenger.isAnim()){

            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            holder.card_passenger.startAnimation(animation);

            BirthDateList passenger2= getPassengers().get(position);
            passenger2.setAnim(false);



        }


    }

    private String getStringPosition(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.First);
            case 1:
                return context.getString(R.string.Second);
            case 2:
                return context.getString(R.string.Third);
            case 3:
                return context.getString(R.string.Fourth);
            case 4:
                return context.getString(R.string.Fifth);
            case 5:
                return context.getString(R.string.Sixth);
            case 6:
                return context.getString(R.string.Seventh);
            case 7:
                return context.getString(R.string.Eighth);
            case 8:
                return context.getString(R.string.ninth);
            default:
                return "";
        }
    }
}
