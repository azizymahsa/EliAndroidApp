package com.eligasht.reservation.views.adapters.hotel.hotelresult;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.models.HotelPreFactorModel;
import com.eligasht.reservation.models.hotel.adapter.SelectHotelModel;
import com.eligasht.reservation.tools.GlideApp;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.activities.hotel.activity.DetailHotelActivity;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Reza Nejati on 19,June,2018
 */
public class HotelResultAdapter extends RecyclerView.Adapter<HotelResultAdapter.ViewHolder> {
    private ArrayList<SelectHotelModel> data = new ArrayList<>();
    private Activity activity;
    Context context;
    TextView DateTime;
    RecyclerView recyclerView;
    boolean isGrid;

    public HotelResultAdapter(final ArrayList<SelectHotelModel> data,Activity activity,TextView DateTime,boolean isGrid) {
        this.data = data;
        this.activity = activity;
        this.DateTime = DateTime;
        this.isGrid = isGrid;


    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        if(isGrid){

            return new ViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.recycler_view_hotel, parent, false));
        }else{

            return new ViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.select_hotel_item, parent, false));

        }

       /* if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            Log.e("test1", recyclerView.getLayoutManager().toString() );

            return new ViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.recycler_view_hotel, parent, false));

        } else  {
            Log.e("test2", recyclerView.getLayoutManager().toString() );

            return new ViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.select_hotel_item, parent, false));
        }*/

    }




    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String imageUri = "https://cdn.elicdn.com" + data.get(position).getImageUrl();

        GlideApp
                .with(context)
                .load(imageUri)
                .centerCrop()
                .error(R.drawable.not_found)
                .into(holder.ivHotelPic);


        holder.name.setText(data.get(position).getName());
        holder.location.setText(data.get(position).getLocation() + "،" + data.get(position).getCity());
        holder.title.setText(data.get(position).getTitle());
        holder.board.setText(data.get(position).getBoard());
        holder.tvPrice.setText(Utility.priceFormat(data.get(position).getPrice()));



        if (data.get(position).isOff()) {
            holder.tvOff.setVisibility(View.VISIBLE);
            holder.tvOff.setText(data.get(position).getOff());
            YoYo.with(Techniques.SlideInRight)
                    .duration(700)
                    .playOn(holder.tvOff);


        } else {
            holder.tvOff.setVisibility(View.GONE);

        }
        if (data.get(position).isBestSell()) {
            holder.ivIsBestseler.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.SlideInLeft)
                    .duration(700)
                    .playOn(holder.ivIsBestseler);
        } else {
            holder.ivIsBestseler.setVisibility(View.GONE);

        }

        if (data.get(position).getTypeText().contains("آپارتمان")||data.get(position).getTypeText().toLowerCase().contains("apart")) {
            holder.txt_lable_hotel.setText(R.string.ApartmenHotel);
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);

        } else if (data.get(position).getTypeText().contains("بوتیک")||data.get(position).getTypeText().toLowerCase().contains("bout")) {
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);
            holder.txt_lable_hotel.setText(R.string.BoutiqueHotel);


        } else if (data.get(position).getTypeText().contains("ریزورت")||data.get(position).getTypeText().toLowerCase().contains("reso")) {
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);
            holder.txt_lable_hotel.setText(R.string.ResortHotel);


        } else {
            holder.txt_lable_hotel.setVisibility(View.GONE);

        }
        holder.rlListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(activity, DetailHotelActivity.class);
                i.putExtra("HotelId", data.get(position).geteHotelId());
                i.putExtra("ResultUniqID", data.get(position).getResultUniqID());
                i.putExtra("CheckIn", activity.getIntent().getExtras().getString("CheckIn"));
                i.putExtra("CheckOut", activity.getIntent().getExtras().getString("CheckOut"));
                i.putExtra("DateTime", DateTime.getText().toString());

                i.putExtra("type", 2);
                SwipeBackActivityHelper.activityBuilder(activity).intent(i).needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
            }
        });


        switch (data.get(position).getStar()) {

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

    }



    @Override
    public int getItemCount() {
        return data.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, location, title, board, tvOff, ivIsBestseler, txt_lable_hotel;
        TextView  tvPrice;
        ImageView ivHotelPic, ivRate;
        CardView cvHotel;
        RelativeLayout rlListItem;
        public ViewHolder(View v) {

            super(v);
           ivHotelPic = v.findViewById(R.id.ivHotelPic);
            ivRate = v.findViewById(R.id.ivRate);
            ivIsBestseler = v.findViewById(R.id.ivIsBestseler);
            name = v.findViewById(R.id.name);
            location = v.findViewById(R.id.location);
            title = v.findViewById(R.id.title);
            board = v.findViewById(R.id.board);
            tvPrice = v.findViewById(R.id.tvPrice);
            tvOff = v.findViewById(R.id.tvOff);
            txt_lable_hotel = v.findViewById(R.id.txt_lable_hotel);
            cvHotel = v.findViewById(R.id.cvHotel);
            rlListItem = v.findViewById(R.id.rlListItem);


        }
    }
}
