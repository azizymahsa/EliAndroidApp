package com.reserv.myapplicationeli.views.adapters.pack;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.pack.LstProwHotel;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.datetools.DateUtil;
import com.reserv.myapplicationeli.views.activities.pack.DetailHotelActivityForPack;
import com.reserv.myapplicationeli.views.viewholders.LstProwHotelRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/6/2018.
 */

public class LstProwHotelAdapter extends RecyclerView.Adapter<LstProwHotelRowHolder> {

    private Context context;
    private ArrayList<LstProwHotel> feedItemList;

    public LstProwHotelAdapter(Context context, ArrayList<LstProwHotel> feedItemList) {
        this.context = context;
        this.feedItemList = feedItemList;
    }

    @Override
    public LstProwHotelRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_lst_pro_hotel, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new LstProwHotelRowHolder(view);
    }

    @Override
    public void onBindViewHolder(LstProwHotelRowHolder holder, int position) {
        final LstProwHotel item = feedItemList.get(position);
        holder.txt_hotel_name.setText(ValidationTools.isEmptyOrNull(item.getHotelNameE()) ? item.getHotelNameE() : item.getHotelNameE() );
        holder.txt_location_full_name.setText(ValidationTools.isEmptyOrNull(item.getLocationFullNameFa()) ? item.getLocationFullNameFa() : item.getLocationFullNameFa() + " ");
        holder.txt_city_name.setText(ValidationTools.isEmptyOrNull(item.getCityPersianName()) ? item.getCityPersianName() : item.getCityPersianName()+ " ،");

        long checkin_milis = DateUtil.getMiliSecondFromJSONDate(item.getCheckIn());
        long checkout_milis = DateUtil.getMiliSecondFromJSONDate(item.getCheckOut());
        long diferent_day = DateUtil.getTimeDifference(item.getCheckIn(),item.getCheckOut()).getDay();


        holder.txt_date.setText(" از " +
                DateUtil.getShortStringDateFromMilis(String.valueOf(checkin_milis), "yyyy-MM-dd", true) +
                " تا " +
                DateUtil.getShortStringDateFromMilis(String.valueOf(checkout_milis), "yyyy-MM-dd", true) +
        " - " +
                diferent_day + " شب ");

        try {
            switch (Integer.parseInt(item.getHotelStarRating().split("\\*")[0])) {

                case 1:
                    //todo change this
                    holder.rating.setImageDrawable(ContextCompat.getDrawable(context, R.drawable._1star));

                    break;
                case 2:
                    holder.rating.setImageDrawable(ContextCompat.getDrawable(context, R.drawable._2star));

                    break;
                case 3:
                    holder.rating.setImageDrawable(ContextCompat.getDrawable(context, R.drawable._3star));

                    break;
                case 4:
                    holder.rating.setImageDrawable(ContextCompat.getDrawable(context, R.drawable._4star));

                    break;

                case 5:
                    holder.rating.setImageDrawable(ContextCompat.getDrawable(context, R.drawable._5star));

                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String imageUri = "https://cdn.elicdn.com/" + item.getHotelImgPath();
        Glide.with(context)
                .load(imageUri)
                .into(holder.ivBigImage);

        holder.ivBigImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailHotelActivityForPack.class);
                i.putExtra("HotelId", item.getHotelID());
                // i.putExtra("ResultUniqID",item.getPackRowID());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (feedItemList == null ? 0 : feedItemList.size());
    }
}
