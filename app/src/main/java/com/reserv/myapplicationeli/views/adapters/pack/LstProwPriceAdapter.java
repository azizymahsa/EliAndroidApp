package com.reserv.myapplicationeli.views.adapters.pack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.pack.LstProwPrice;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.viewholders.PSpecialRoomRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class LstProwPriceAdapter extends RecyclerView.Adapter<PSpecialRoomRowHolder> {

    private Context context;
    private ArrayList<LstProwPrice> feedItemList;

    public LstProwPriceAdapter(Context context, ArrayList<LstProwPrice> feedItemList) {
        this.context = context;
        this.feedItemList = feedItemList;
    }

    @Override
    public PSpecialRoomRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_lst_prow_price, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PSpecialRoomRowHolder(view);
    }

    @Override
    public void onBindViewHolder(PSpecialRoomRowHolder holder, int position) {
        final LstProwPrice item = feedItemList.get(position);
        holder.adaultPrice.setText(String.valueOf(item.getAdl()));
        holder.childPrice.setText(String.valueOf(item.getChNb()));
        holder.infantPrice.setText(String.valueOf(item.getInf()));
        holder.totalPrice.setText(String.valueOf(item.getSumPrice()));
        holder.txt_hr_room_list.setText(ValidationTools.isEmptyOrNull(item.getHRroomListF())?item.getHRroomList():item.getHRroomListF());
    }

    @Override
    public int getItemCount(){
        return(feedItemList == null ? 0 : feedItemList.size());
    }
}
