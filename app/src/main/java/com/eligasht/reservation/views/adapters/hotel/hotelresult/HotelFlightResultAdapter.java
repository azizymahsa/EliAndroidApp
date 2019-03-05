package com.eligasht.reservation.views.adapters.hotel.hotelresult;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.models.hotel.adapter.SelectFlightHotelModel;
import com.eligasht.reservation.models.hotel.adapter.SelectHotelModel;
import com.eligasht.reservation.tools.GlideApp;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.activities.hotel.activity.DetailHotelActivity;
import com.eligasht.reservation.views.ui.SearchFlightActivity;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;
/**
 * Created by Reza Nejati on 20,June,2018
 */
public class HotelFlightResultAdapter extends RecyclerView.Adapter<HotelFlightResultAdapter.ViewHolder> {
    private ArrayList<SelectFlightHotelModel> data = new ArrayList<>();
    private Activity activity;
    Context context;
    TextView DateTime;
    RecyclerView recyclerView;
    boolean isGrid;

    public HotelFlightResultAdapter(final ArrayList<SelectFlightHotelModel> data,Activity activity,TextView DateTime,boolean isGrid) {
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
                    .inflate(R.layout.select_flight_hotel_item_grid, parent, false));
        }else{

            return new ViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.select_flight_hotel_item, parent, false));

        }

    }




    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.btnChange.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));

        String imageUri = "https://cdn.elicdn.com" + data.get(position).getImageUrl();
        String imageUri2 = "https://cdn.elicdn.com/Content/AirLine/MblSize/" + data.get(position).getFlights().get(0).getAirlineCode() + ".png";

        GlideApp
                .with(activity)
                .load(imageUri)
                .centerCrop()
                .error(R.drawable.not_found)
                .into(holder.ivHotelPic);




        holder.rlListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, DetailHotelActivity.class);
                i.putExtra("HotelId", data.get(position).geteHotelId());
                i.putExtra("ResultUniqID", data.get(position).getResultUniqID());
                i.putExtra("FlightID", data.get(position).getFlightId());
                i.putExtra("CheckInHF", activity.getIntent().getExtras().getString("CheckInHF"));
                i.putExtra("CheckOutHF", activity.getIntent().getExtras().getString("CheckOutHF"));
                i.putExtra("DateTime", DateTime.getText().toString());
                i.putExtra("type", 1);
                //for Hotel+Flight
                i.putExtra("FlightOfferID", data.get(position).getFlightList().getOfferId());
                i.putExtra("FlightGuID", data.get(position).getFlightList().getFlightGUID());
                SwipeBackActivityHelper.activityBuilder(activity)
                        .intent(i)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
            }
        });




        holder.name.setText(data.get(position).getName());
        holder.location.setText(data.get(position).getLocation() + "،" + data.get(position).getCity());
        holder.title.setText(data.get(position).getTitle());
        holder.board.setText(data.get(position).getBoard());
        holder.tvPrice.setText(Utility.priceFormat(String.valueOf(Integer.valueOf(data.get(position).getPrice().intValue()) + Integer.valueOf(data.get(position).getAmount()))));
        if (data.get(position).getTypeText().contains("آپارتمان") || data.get(position).getTypeText().toLowerCase().contains("apart")) {
            holder.txt_lable_hotel.setText(R.string.ApartmenHotel);
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);
        } else if (data.get(position).getTypeText().contains("بوتیک") || data.get(position).getTypeText().toLowerCase().contains("bout")) {
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);
            holder.txt_lable_hotel.setText(R.string.BoutiqueHotel);
        } else if (data.get(position).getTypeText().contains("ریزورت") || data.get(position).getTypeText().toLowerCase().contains("reso")) {
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);
            holder.txt_lable_hotel.setText(R.string.ResortHotel);
        } else {
            holder.txt_lable_hotel.setVisibility(View.GONE);
        }
        GlideApp
                .with(activity)
                .load(imageUri2)
                .centerCrop()
                .error(R.drawable.not_found)
                .into(holder.ivLogo);
        if (data.get(position).isOff()) {
            holder.tvOff.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.SlideInRight)
                    .duration(700)
                    .playOn(holder.tvOff);
            holder.tvOff.setText(data.get(position).getOff());
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







        if (!isGrid){
           /* YoYo.with(Techniques.FadeIn)
                    .duration(300)
                    .playOn(holder.cvHotel);*/
            holder.btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, SearchFlightActivity.class);
                    intent.putExtra("isChangeFlight", true);
                    intent.putExtra("FlightId", data.get(position).getFlightId());
                    intent.putExtra("SearchKey", data.get(position).getResultUniqID());
                    activity.startActivityForResult(intent, 155);
                }
            });
            String waitRaft;
            String waitBargasht;
            String[] strings = data.get(position).getDepRout().split("→");
            switch (strings.length) {
                case 0:
                    waitRaft = activity.getString(R.string.NonStop);
                    holder.linear_1.setVisibility(View.VISIBLE);
                    holder.tvANRaft1_1.setText(strings[0]);
                    holder.tvANRaft1_2.setText(strings[1]);
                    break;
                case 1:
                    holder.linear_1.setVisibility(View.VISIBLE);
                    holder.tvANRaft1_1.setText(strings[0]);
                    holder.tvANRaft1_2.setText(strings[1]);
                    waitRaft = activity.getString(R.string.NonStop);
                    break;
                case 2:
                    waitRaft = activity.getString(R.string.NonStop);
                    holder.linear_1.setVisibility(View.VISIBLE);
                    holder.tvANRaft1_1.setText(strings[0]);
                    holder.tvANRaft1_2.setText(strings[1]);
                    anim(holder.lineOnstep, holder.tvPlane, position);
                    break;
                case 3:
                    waitRaft = activity.getString(R.string.OneStop);
                    holder.linear_2.setVisibility(View.VISIBLE);
                    holder.tvANRaft2_1.setText(strings[0]);
                    holder.tvANRaft2_2.setText(strings[1]);
                    holder.tvANRaft2_3.setText(strings[2]);
                    break;
                case 4:
                    waitRaft = activity.getString(R.string.TwoStop);
                    holder.linear_3.setVisibility(View.VISIBLE);
                    holder.tvANRaft3_1.setText(strings[0]);
                    holder.tvANRaft3_2.setText(strings[1]);
                    holder.tvANRaft3_3.setText(strings[2]);
                    holder.tvANRaft3_4.setText(strings[3]);
                    break;
                case 5:
                    waitRaft = "سه توقف";
                    break;
                case 6:
                    waitRaft = "چهار توقف";
                    break;
                default:
                    waitRaft = "";
                    break;
            }
            String[] strings2 = data.get(position).getArrRout().split("→");
            switch (strings2.length) {
                case 0:
                    waitBargasht = "بدون توقف";
                    holder.linear_1_bargasht.setVisibility(View.VISIBLE);
                    holder.tvANRaft1_1_bargasht.setText(strings2[0]);
                    holder.tvANRaft1_2_bargasht.setText(strings2[1]);
                    break;
                case 1:
                    waitBargasht = "بدون توقف";
                    try {
                        holder.tvANRaft1_2_bargasht.setText(strings2[1]);
                        holder.tvANRaft1_1_bargasht.setText(strings2[0]);
                        holder.linear_1_bargasht.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        holder.nonStop.setText(strings2[0]);
                        holder.nonStop.setVisibility(View.VISIBLE);
                    }
                    break;
                case 2:
                    waitBargasht = "بدون توقف";
                    holder.linear_1_bargasht.setVisibility(View.VISIBLE);
                    holder.tvANRaft1_1_bargasht.setText(strings2[0]);
                    holder.tvANRaft1_2_bargasht.setText(strings2[1]);
                    anim(holder.lineOnstep_barhasht, holder.tvPlane_bargasht, position);
                    break;
                case 3:
                    waitBargasht = "یک توقف";
                    holder.linear_2_bargasht.setVisibility(View.VISIBLE);
                    holder.tvANRaft2_1_bargasht.setText(strings2[0]);
                    holder.tvANRaft2_2_bargasht.setText(strings2[1]);
                    holder.tvANRaft2_3_bargasht.setText(strings2[2]);
                    break;
                case 4:
                    waitBargasht = "دو توقف";
                    holder.linear_3_bargasht.setVisibility(View.VISIBLE);
                    holder.tvANRaft3_1_bargasht.setText(strings2[0]);
                    holder.tvANRaft3_2_bargasht.setText(strings2[1]);
                    holder.tvANRaft3_3_bargasht.setText(strings2[2]);
                    holder.tvANRaft3_4_bargasht.setText(strings2[3]);
                    break;
                case 5:
                    waitBargasht = "سه توقف";
                    break;
                case 6:
                    waitBargasht = "چهار توقف";
                    break;
                default:
                    waitBargasht = "";
                    break;
            }
            holder.tvRaftTime.setText(waitRaft);
            holder.tvBargashtTime.setText(waitBargasht);
            String text = "<font color=##2e3192>" + data.get(position).getFlights().get(0).getFlightTime() + "</font> " +
                    "<font color=#4d4d4d>" + data.get(position).getFlights().get(0).getFlightNumber() + "</font>";
            holder.tvRaftTime.setText(Html.fromHtml(text));
            String texttvBargashtTime = "<font color=##2e3192>" + data.get(position).getFlights().get(1).getFlightTime() + "</font> " +
                    "<font color=#4d4d4d>" + data.get(position).getFlights().get(1).getFlightNumber() + "</font>";
            holder.tvBargashtTime.setText(Html.fromHtml(texttvBargashtTime));
            String texttvBargashtTime1 = "<font color=##2e3192>" + data.get(position).getFlights().get(0).getFlightArrivalTime() + "</font> " +
                    "<font color=#4d4d4d>" + data.get(position).getFlights().get(0).getFlightNumber() + "</font>";
            holder.tvBargashtTime1.setText(Html.fromHtml(texttvBargashtTime1));
            String texttvBargashtTime2 = "<font color=##2e3192>" + data.get(position).getFlights().get(1).getFlightArrivalTime() + "</font> " +
                    "<font color=#4d4d4d>" + data.get(position).getFlights().get(1).getFlightNumber() + "</font>";
            holder.tvBargashtTime2.setText(Html.fromHtml(texttvBargashtTime2));
            holder.tvAirLines.setText(data.get(position).getFlights().get(0).getAirlineNameFa());

        }



    }



        @Override
    public int getItemCount() {
        return data.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, title, board, tvOff, tvRaft, tvBargasht, tvBargashtTime, tvRaftTime, tvRaftTimeWait, tvBargashtTimeWait, tvAirLines, nonStop, ivIsBestseler, txt_lable_hotel, tvBargashtTime2, tvBargashtTime1;
        ImageView ivHotelPic, ivRate, ivLogo;
        TextView  tvPrice;
        CardView cvHotel;
        LinearLayout linear_1, linear_2, linear_3;
        TextView tvANRaft2_1, tvANRaft2_2, tvANRaft2_3;
        TextView tvANRaft3_1, tvANRaft3_2, tvANRaft3_3, tvANRaft3_4;
        TextView tvANRaft1_1, tvANRaft1_2;
        TextView tvPlane, tvPlane_bargasht;
        AVLoadingIndicatorView avi2;
        FancyButton btnChange;
        RelativeLayout rlListItem;

        LinearLayout linear_1_bargasht, linear_2_bargasht, linear_3_bargasht;
        TextView tvANRaft2_1_bargasht, tvANRaft2_2_bargasht, tvANRaft2_3_bargasht;
        TextView tvANRaft3_1_bargasht, tvANRaft3_2_bargasht, tvANRaft3_3_bargasht, tvANRaft3_4_bargasht;
        TextView tvANRaft1_1_bargasht, tvANRaft1_2_bargasht;
        View lineOnstep, lineOnstep_barhasht;
        public ViewHolder(View v) {
            super(v);

            ivHotelPic = v.findViewById(R.id.ivHotelPic);
            ivRate = v.findViewById(R.id.ivRate);
            ivLogo = v.findViewById(R.id.ivLogo);
            name = v.findViewById(R.id.name);
            location = v.findViewById(R.id.location);
            title = v.findViewById(R.id.title);
            board = v.findViewById(R.id.board);
            tvPrice = v.findViewById(R.id.tvPrice);
            tvOff = v.findViewById(R.id.tvOff);
            cvHotel = v.findViewById(R.id.cvHotel);
            tvRaft = v.findViewById(R.id.tvRaft);
            tvBargasht = v.findViewById(R.id.tvBargasht);
            tvBargashtTime = v.findViewById(R.id.tvBargashtTime);
            tvRaftTime = v.findViewById(R.id.tvRaftTime);
            tvAirLines = v.findViewById(R.id.tvAirLines);
            ivIsBestseler = v.findViewById(R.id.ivIsBestseler);
            txt_lable_hotel = v.findViewById(R.id.txt_lable_hotel);
            avi2 = v.findViewById(R.id.avi2);
            btnChange = v.findViewById(R.id.btnChange);
            rlListItem = v.findViewById(R.id.rlListItem);
            tvPlane = v.findViewById(R.id.tvPlane);
            tvPlane_bargasht = v.findViewById(R.id.tvPlane_bargasht);


            linear_1 = v.findViewById(R.id.linear_1);
            linear_2 = v.findViewById(R.id.linear_2);
            tvANRaft2_1 = v.findViewById(R.id.tvANRaft2_1);
            tvANRaft2_2 = v.findViewById(R.id.tvANRaft2_2);
            tvANRaft2_3 = v.findViewById(R.id.tvANRaft2_3);
            tvANRaft1_1 = v.findViewById(R.id.tvANRaft1_1);
            tvANRaft1_2 = v.findViewById(R.id.tvANRaft1_2);


            linear_1_bargasht = v.findViewById(R.id.linear_1_bargasht);
            linear_2_bargasht = v.findViewById(R.id.linear_2_bargasht);
            tvANRaft2_1_bargasht = v.findViewById(R.id.tvANRaft2_1_bargasht);
            tvANRaft2_2_bargasht = v.findViewById(R.id.tvANRaft2_2_bargasht);
            tvANRaft2_3_bargasht = v.findViewById(R.id.tvANRaft2_3_bargasht);
            tvANRaft1_1_bargasht = v.findViewById(R.id.tvANRaft1_1_bargasht);
            tvANRaft1_2_bargasht = v.findViewById(R.id.tvANRaft1_2_bargasht);
            nonStop = v.findViewById(R.id.nonStop);


            linear_3_bargasht = v.findViewById(R.id.linear_3_bargasht);
            linear_3 = v.findViewById(R.id.linear_3);

            tvANRaft3_1 = v.findViewById(R.id.tvANRaft3_1);
            tvANRaft3_2 = v.findViewById(R.id.tvANRaft3_2);
            tvANRaft3_3 = v.findViewById(R.id.tvANRaft3_3);
            tvANRaft3_4 = v.findViewById(R.id.tvANRaft3_4);

            tvANRaft3_1_bargasht = v.findViewById(R.id.tvANRaft3_1_bargasht);
            tvANRaft3_2_bargasht = v.findViewById(R.id.tvANRaft3_2_bargasht);
            tvANRaft3_3_bargasht = v.findViewById(R.id.tvANRaft3_3_bargasht);
            tvANRaft3_4_bargasht = v.findViewById(R.id.tvANRaft3_4_bargasht);
            tvBargashtTime2 = v.findViewById(R.id.tvBargashtTime2);
            tvBargashtTime1 = v.findViewById(R.id.tvBargashtTime1);
            lineOnstep = v.findViewById(R.id.lineOnstep);
            lineOnstep_barhasht = v.findViewById(R.id.lineOnstep_barhasht);



        }
    }


    public void anim(View lineOnstep, View tvPlane, int position) {
      /*  if (lineOnstep.getTag() !=null && lineOnstep.getTag().equals("gg"))
            return;
        lineOnstep.setTag("gg");*/
        boolean run = false;
        for (int i = 0; i < data.get(position).getBooleans().size(); i++) {
            if (!data.get(position).getBooleans().get(i)) {
                run = true;
                data.get(position).getBooleans().set(i,true);
                break;
            }
        }
        if (!run)
            return;
        Log.e("viewtest", lineOnstep.getId()+"" );

        if (position == 0 || position == 1) {

            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {
                    float right = lineOnstep.getRight();
                    float left = lineOnstep.getLeft();
                    tvPlane.setTranslationX(-right);

                    ObjectAnimator anim2 = ObjectAnimator.ofFloat(tvPlane, "translationX", -right, left - 25);
                    anim2.setDuration(2500);
                    anim2.setStartDelay(20);// Duration in milliseconds
                    anim2.setInterpolator(new AccelerateDecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
                    anim2.start();

                    ObjectAnimator anim3 = ObjectAnimator.ofFloat(lineOnstep, "translationX", -right, left - 25);
                    anim3.setDuration(2500);
                    anim3.setStartDelay(20);// Duration in milliseconds
                    anim3.setInterpolator(new AccelerateDecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
                    anim3.start();
                }
            };
            handler.postDelayed(r, 500);
        }else {
            float right = lineOnstep.getRight();
            float left = lineOnstep.getLeft();
            tvPlane.setTranslationX(-right);

            ObjectAnimator anim2 = ObjectAnimator.ofFloat(tvPlane, "translationX", -right, left - 25);
            anim2.setDuration(3000);
            anim2.setStartDelay(50);// Duration in milliseconds
            anim2.setInterpolator(new AccelerateDecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
            anim2.start();

            ObjectAnimator anim3 = ObjectAnimator.ofFloat(lineOnstep, "translationX", -right, left - 25);
            anim3.setDuration(3000);
            anim3.setStartDelay(50);// Duration in milliseconds
            anim3.setInterpolator(new AccelerateDecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
            anim3.start();

        }


    }

}
