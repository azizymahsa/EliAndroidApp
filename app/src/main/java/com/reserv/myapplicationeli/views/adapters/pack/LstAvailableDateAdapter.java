package com.reserv.myapplicationeli.views.adapters.pack;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.pack.LstAvailableDate;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.datetools.DateUtil;
import com.reserv.myapplicationeli.views.viewholders.LstAvailableDateRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/6/2018.
 * list of date in top of the activity search pack
 */

public class  LstAvailableDateAdapter extends RecyclerView.Adapter<LstAvailableDateRowHolder> {

    private Context context;
    private ArrayList<LstAvailableDate> feedItemList;
    private ListenerLstAvailableDateAdapter listener;


    public LstAvailableDateAdapter(Context context, ArrayList<LstAvailableDate> NameItem) {

        this.context = context;
        this.feedItemList = NameItem;
        if(getIndexSelectedItem() == feedItemList.size() - 1){
            feedItemList.add(new LstAvailableDate());
        }
        if(!ValidationTools.isEmptyOrNull(feedItemList)){
            if(getIndexSelectedItem() == feedItemList.size() - 1){
                feedItemList.add(new LstAvailableDate());
            }
        }

    }

    public interface ListenerLstAvailableDateAdapter {
        void onClickLstAvailableDateItem(LstAvailableDate lstAvailableDate);
    }

    public LstAvailableDateAdapter setListener(ListenerLstAvailableDateAdapter listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public LstAvailableDateRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_lst_available_date, null);
        return new LstAvailableDateRowHolder(view);
    }

    @Override
    public void onBindViewHolder(LstAvailableDateRowHolder holder, final int position) {
        final LstAvailableDate item = feedItemList.get(position);

        if(item.getPackID() == null){
            holder.txt_coming_soon.setVisibility(View.VISIBLE);
            holder.layout_date.setVisibility(View.GONE);
            return;
        }

        holder.txt_coming_soon.setVisibility(View.GONE);
        holder.layout_date.setVisibility(View.VISIBLE);
        if(item.getIsSelected()){
            holder.view_selected.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_banafash_with_stroke));
        }else{
            holder.view_selected.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_white_with_stroke));
        }
        if(item.getIsSelected()){
            holder.txt_depart_date.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_banafash_with_stroke));
        }else{
            holder.txt_depart_date.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_white_with_stroke));
        }

        long milis = DateUtil.getMiliSecondFromJSONDate(item.getDepartDate());
        holder.txt_depart_date.setText(DateUtil.getShortStringDateFromMilis(String.valueOf(milis),"yyyy-MM-dd",true));
        Glide.with(context)
                .load("http://www.eligasht.com/Content/AirLine/" + item.getAirlineCode() + ".png")
                .into(holder.img_airLine);


        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onClickLstAvailableDateItem(item);
                    return;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (feedItemList == null ? 0 : feedItemList.size());
    }

    public int getIndexSelectedItem() {
        if (ValidationTools.isEmptyOrNull(feedItemList)) {
            return 0;
        }

        for (int i = 0; i < feedItemList.size(); i++) {
            if (feedItemList.get(i).getIsSelected()) {
                return i;
            }
        }

        return 0;
    }
}



