package com.eligasht.reservation.views.adapters.insurance;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.eligasht.reservation.presenters.PassengerPresenter;
import com.eligasht.reservation.views.viewholders.PassengerRowHolder;

/**
 * Created by elham.bonyani on 1/4/2018.
 * for add passenger in  activity passenger
 */

public class PassengerAdapter extends RecyclerView.Adapter<PassengerRowHolder> {

    private PassengerPresenter mPresenter;

    public PassengerAdapter(PassengerPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public PassengerRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return mPresenter.createViewHolder(viewGroup,i);
    }

    @Override
    public void onBindViewHolder(PassengerRowHolder holder, int position) {
        mPresenter.bindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getPassengersCount();
    }
}
