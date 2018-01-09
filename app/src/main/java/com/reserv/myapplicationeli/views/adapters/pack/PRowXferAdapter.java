package com.reserv.myapplicationeli.views.adapters.pack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.pack.PRowXfer;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.viewholders.PRowXferRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class PRowXferAdapter extends RecyclerView.Adapter<PRowXferRowHolder> {

    private Context context;
    private ArrayList<PRowXfer> feedItemList;

    public PRowXferAdapter(Context context, ArrayList<PRowXfer> NameItem) {

        this.context = context;
        this.feedItemList = NameItem;
    }

    @Override
    public PRowXferRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_list_pack_item, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PRowXferRowHolder(view);
    }

    @Override
    public void onBindViewHolder(PRowXferRowHolder holder, final int position) {
        final PRowXfer item = feedItemList.get(position);

        LstProwPriceAdapter lstProwPriceAdapter = new LstProwPriceAdapter(context,item.getLstProwPrices());
        holder.rcl_price.showList(lstProwPriceAdapter);


        LstProwHotelAdapter lstProwHotelAdapter = new LstProwHotelAdapter(context,feedItemList.get(position).getLstProwHotels());
        holder.rcl_hotels.showList(lstProwHotelAdapter);

        holder.txt_depart_time.setText(item.getXferList().getXFlightsList().get(0).getFltLocalTime());
        holder.txt_return_time.setText(item.getXferList().getXFlightsList().get(0).getFltCheckinTime());
        holder.txt_airline.setText(item.getXferList().getXFlightsList().get(0).getAirlineEnglishName());
        holder.txt_economi.setText(item.getXferList().getXFlightsList().get(0).getSeatClassNameFa());
        if(item.getXferList().getXFlightsList().get(0).getAvailable().equals("Available") || item.getXferList().getXFlightsList().get(0).getAvailable().equals("")){
                    holder.txt_count.setText("");
                } else { holder.txt_count.setText(item.getXferList().getXFlightsList().get(0).getAvailable());}
        holder.txt_depart_air.setText(" رفت به " + (ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(0).getArrivalCityName())?item.getXferList().getXFlightsList().get(0).getArrivalCityName():item.getXferList().getXFlightsList().get(0).getArrivalCityName()));
        holder.txt_return_air.setText(" برگشت به " + (ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(0).getDepartureCityName())?item.getXferList().getXFlightsList().get(0).getDepartureCityName():item.getXferList().getXFlightsList().get(0).getDepartureCityName()));

        Glide.with(context)
                .load("http://www.eligasht.com/Content/AirLine/" + item.getXferList().getXFlightsList().get(0).getAirlineCode() + ".png")
                .into(holder.img_airLine);
    }

    @Override
    public int getItemCount() {
        return (feedItemList == null ? 0 : feedItemList.size());
    }
}



