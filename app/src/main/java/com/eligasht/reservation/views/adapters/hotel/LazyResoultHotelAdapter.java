package com.eligasht.reservation.views.adapters.hotel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.reservation.tools.GlideApp;
import com.eligasht.reservation.views.activities.hotel.activity.DetailHotelActivity;

import com.eligasht.R;
import com.eligasht.reservation.models.hotel.adapter.SelectHotelModel;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.ticker.TickerView;

import java.util.ArrayList;


public class LazyResoultHotelAdapter extends BaseAdapter {

    private ArrayList<SelectHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;
    Context context;
    Activity activity;
    TextView DateTime;

    public LazyResoultHotelAdapter(ArrayList<SelectHotelModel> selectHotelModelArrayList, Context context, Activity activity,TextView DateTime) {
        this.activity = activity;
        this.DateTime = DateTime;
        this.selectHotelModelArrayList = selectHotelModelArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);



    }

    @Override
    public int getCount() {
        return selectHotelModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.select_hotel_item, null);
            holder = new ViewHolder();
            holder.ivHotelPic = convertView.findViewById(R.id.ivHotelPic);
            holder.ivRate = convertView.findViewById(R.id.ivRate);
            holder.ivIsBestseler = convertView.findViewById(R.id.ivIsBestseler);
            holder.name = convertView.findViewById(R.id.name);
            holder.location = convertView.findViewById(R.id.location);
            holder.title = convertView.findViewById(R.id.title);
            holder.board = convertView.findViewById(R.id.board);
            holder.tvPrice = convertView.findViewById(R.id.tvPrice);
            holder.tvOff = convertView.findViewById(R.id.tvOff);
            holder.txt_lable_hotel = convertView.findViewById(R.id.txt_lable_hotel);
            holder.cvHotel = convertView.findViewById(R.id.cvHotel);
            holder.rlListItem = convertView.findViewById(R.id.rlListItem);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        YoYo.with(Techniques.FadeIn)
                .duration(300)
                .playOn(holder.cvHotel);
        String imageUri = "https://cdn.elicdn.com" + selectHotelModelArrayList.get(position).getImageUrl();

        GlideApp
                .with(context)
                .load(imageUri)
                .centerCrop()
                .error(R.drawable.not_found)
                .into(holder.ivHotelPic);

     /*   AQuery aQuery=new AQuery(v);
        aQuery.id(holder.imgPhoto).image(item.getImageUrl().toString());
*/

        holder.name.setText(selectHotelModelArrayList.get(position).getName());
        holder.location.setText(selectHotelModelArrayList.get(position).getLocation() + "،" + selectHotelModelArrayList.get(position).getCity());
        holder.title.setText(selectHotelModelArrayList.get(position).getTitle());
        holder.board.setText(selectHotelModelArrayList.get(position).getBoard());
        holder.tvPrice.setText(Utility.priceFormat(selectHotelModelArrayList.get(position).getPrice()));

        holder.rlListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(activity, DetailHotelActivity.class);
                i.putExtra("HotelId", selectHotelModelArrayList.get(position).geteHotelId());
                i.putExtra("ResultUniqID", selectHotelModelArrayList.get(position).getResultUniqID());
                i.putExtra("CheckIn", activity.getIntent().getExtras().getString("CheckIn"));
                i.putExtra("CheckOut", activity.getIntent().getExtras().getString("CheckOut"));
                i.putExtra("DateTime", DateTime.getText().toString());

                i.putExtra("type", 2);

                activity.startActivity(i);
            }
        });




        if (selectHotelModelArrayList.get(position).isOff()) {
            holder.tvOff.setVisibility(View.VISIBLE);
            holder.tvOff.setText(selectHotelModelArrayList.get(position).getOff());

        } else {
            holder.tvOff.setVisibility(View.GONE);

        }
        if (selectHotelModelArrayList.get(position).isBestSell()) {
            holder.ivIsBestseler.setVisibility(View.VISIBLE);

        } else {
            holder.ivIsBestseler.setVisibility(View.GONE);

        }

        if (selectHotelModelArrayList.get(position).getTypeText().contains("آپارتمان")) {
            holder.txt_lable_hotel.setText(R.string.ApartmenHotel);
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);

        } else if (selectHotelModelArrayList.get(position).getTypeText().contains("بوتیک")) {
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);
            holder.txt_lable_hotel.setText(R.string.BoutiqueHotel);


        } else if (selectHotelModelArrayList.get(position).getTypeText().contains("ریزورت")) {
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);
            holder.txt_lable_hotel.setText(R.string.ResortHotel);


        } else {
            holder.txt_lable_hotel.setVisibility(View.GONE);

        }


        switch (selectHotelModelArrayList.get(position).getStar()) {

            case 1:
                holder.ivRate.setVisibility(View.VISIBLE);
                //todo change this
                holder.ivRate.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable._1star));

                break;
            case 2:
                holder.ivRate.setVisibility(View.VISIBLE);

                holder.ivRate.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable._2star));

                break;
            case 3:
                holder.ivRate.setVisibility(View.VISIBLE);

                holder.ivRate.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable._3star));

                break;
            case 4:
                holder.ivRate.setVisibility(View.VISIBLE);

                holder.ivRate.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable._4star));

                break;

            case 5:
                holder.ivRate.setVisibility(View.VISIBLE);

                holder.ivRate.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable._5star));

                break;
            case -1:
                holder.ivRate.setVisibility(View.GONE);

                break;


        }


        return convertView;
    }

    public class ViewHolder {
        TextView name, location, title, board, tvOff, ivIsBestseler, txt_lable_hotel;
        TextView  tvPrice;
        ImageView ivHotelPic, ivRate;
        CardView cvHotel;
        RelativeLayout rlListItem;



    }

}








