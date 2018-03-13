package com.eligasht.reservation.views.adapters.pack;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eligasht.R;
import com.eligasht.reservation.models.model.pack.LstProwHotel;
import com.eligasht.reservation.tools.GlideApp;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.views.activities.hotel.activity.DetailHotelActivity;
import com.eligasht.reservation.views.viewholders.LstProwHotelRowHolder;


import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/6/2018.
 * for list of hotel's pic in top of ever package
 */

public class LstProwHotelAdapter extends RecyclerView.Adapter<LstProwHotelRowHolder> {

    private Context context;
    private ArrayList<LstProwHotel> feedItemList;
    TextView Date;

    public LstProwHotelAdapter(Context context, ArrayList<LstProwHotel> feedItemList, TextView Date) {
        this.context = context;
        this.feedItemList = feedItemList;
        this.Date = Date;
    }

    @Override
    public LstProwHotelRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_lst_pro_hotel, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new LstProwHotelRowHolder(view);
    }

    @Override
    public void onBindViewHolder(final LstProwHotelRowHolder holder, int position) {
        final LstProwHotel item = feedItemList.get(position);
        if (item.getHTypeNameE().contains("Apartment")) {
            holder.lableHotelTilte.setVisibility(View.VISIBLE);
            holder.lableHotelTilte.setText(context.getString(R.string.ApartmenHotel));

        } else if (item.getHTypeNameE().contains("Boutique")) {
            holder.lableHotelTilte.setVisibility(View.VISIBLE);
            holder.lableHotelTilte.setText(context.getString(R.string.BoutiqueHotel));

        }
        if (item.getHTypeNameE().contains("Resort")) {
            holder.lableHotelTilte.setVisibility(View.VISIBLE);
            holder.lableHotelTilte.setText(context.getString(R.string.ResortHotel));


        } else {

            holder.lableHotelTilte.setVisibility(View.GONE);
//            holder.lableHotelTilte.setText(item.getHTypeNameF());
        }
        holder.txt_hotel_name.setText(ValidationTools.isEmptyOrNull(item.getHotelNameE()) ? item.getHotelNameE() : item.getHotelNameE());
        holder.txt_hotel_name.setEllipsize(TextUtils.TruncateAt.END);
        holder.txt_location_full_name.setText(ValidationTools.isEmptyOrNull(item.getLocationFullNameFa()) ? item.getLocationFullNameFa() : item.getLocationFullNameFa() + " ");
        holder.txt_city_name.setText(ValidationTools.isEmptyOrNull(item.getCityPersianName()) ? item.getCityPersianName() : item.getCityPersianName() + " ،");

        long checkin_milis = DateUtil.getMiliSecondFromJSONDate(item.getCheckIn());
        long checkout_milis = DateUtil.getMiliSecondFromJSONDate(item.getCheckOut());
        long diferent_day = DateUtil.getTimeDifference(item.getCheckIn(), item.getCheckOut()).getDay();
        if (Prefs.getString("lang","fa").equals("fa")){
            holder.txt_date.setText(context.getString(R.string.from) +" "+
                    DateUtil.getShortStringDateFromMilis(String.valueOf(checkin_milis), "yyyy-MM-dd", true) +
                    context.getString(R.string.to) +" "+
                    DateUtil.getShortStringDateFromMilis(String.valueOf(checkout_milis), "yyyy-MM-dd", true) +
                    " - " +
                    diferent_day +" "+context.getString(R.string.night));
        }else{
            holder.txt_date.setText(context.getString(R.string.from) +" "+
                    DateUtil.getShortStringDateFromMilis(String.valueOf(checkin_milis), "yyyy-MM-dd", false) +
                    context.getString(R.string.to) +" "+
                    DateUtil.getShortStringDateFromMilis(String.valueOf(checkout_milis), "yyyy-MM-dd", false) +
                    " - " +
                    diferent_day +" "+context.getString(R.string.night));
        }


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


        GlideApp
                .with(context)
                .load(imageUri)
                .centerCrop()
                .error(R.drawable.not_found)
                .into(holder.ivBigImage);


        holder.ivBigImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, DetailHotelActivity.class);
                i.putExtra("HotelId", item.getHotelID());
                i.putExtra("ResultUniqID", String.valueOf(item.getPackRowID()));
                i.putExtra("CheckIn", item.getCheckIn());
                i.putExtra("CheckOut", item.getCheckOut());
                i.putExtra("Type", "Pakage");
                i.putExtra("type", 2);
                i.putExtra("DateTime", Date.getText().toString());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (feedItemList == null ? 0 : feedItemList.size());
    }
}
