package com.eligasht.reservation.views.adapters.pack;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.eligasht.reservation.presenters.RoomPresenter;
import com.eligasht.reservation.views.viewholders.RoomRowHolder;

/**
 * Created by elham.bonyani on 1/4/2018.
 * for list of rooms
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomRowHolder> {

    private RoomPresenter mPresenter;

    public RoomAdapter(RoomPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public RoomRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return mPresenter.createViewHolder(viewGroup,i);
    }

    @Override
    public void onBindViewHolder(RoomRowHolder holder, int position) {
        mPresenter.bindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getRoomsCount();
    }
}
