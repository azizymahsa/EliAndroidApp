package com.eligasht.reservation.views.adapters.hotel;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.models.hotel.adapter.SelectFlightHotelModel;
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
 * Created by Reza.nejati on 1/14/2018.
 */

public class FlightHotelAdapter extends BaseAdapter {
    Activity activity;
    TextView DateTime;
    private ArrayList<SelectFlightHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;


    public FlightHotelAdapter(ArrayList<SelectFlightHotelModel> selectHotelModelArrayList, Activity activity, TextView DateTime) {
        this.activity = activity;
        this.selectHotelModelArrayList = selectHotelModelArrayList;
        this.activity = activity;
        this.DateTime = DateTime;
        inflater = LayoutInflater.from(activity);


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
            convertView = inflater.inflate(R.layout.select_flight_hotel_item, null);

            LinearLayout linear_1, linear_2;
            TextView tvANRaft2_1, tvANRaft2_2, tvANRaft2_3;
            TextView tvANRaft1_1, tvANRaft1_2;
            holder = new ViewHolder();
            holder.ivHotelPic = convertView.findViewById(R.id.ivHotelPic);
            holder.ivRate = convertView.findViewById(R.id.ivRate);
            holder.ivLogo = convertView.findViewById(R.id.ivLogo);
            holder.name = convertView.findViewById(R.id.name);
            holder.location = convertView.findViewById(R.id.location);
            holder.title = convertView.findViewById(R.id.title);
            holder.board = convertView.findViewById(R.id.board);
            holder.tvPrice = convertView.findViewById(R.id.tvPrice);
            holder.tvOff = convertView.findViewById(R.id.tvOff);
            holder.cvHotel = convertView.findViewById(R.id.cvHotel);
            holder.tvRaft = convertView.findViewById(R.id.tvRaft);
            holder.tvBargasht = convertView.findViewById(R.id.tvBargasht);
            holder.tvBargashtTime = convertView.findViewById(R.id.tvBargashtTime);
            holder.tvRaftTime = convertView.findViewById(R.id.tvRaftTime);
            holder.tvAirLines = convertView.findViewById(R.id.tvAirLines);
            holder.ivIsBestseler = convertView.findViewById(R.id.ivIsBestseler);
            holder.txt_lable_hotel = convertView.findViewById(R.id.txt_lable_hotel);
            holder.avi2 = convertView.findViewById(R.id.avi2);
            holder.btnChange = convertView.findViewById(R.id.btnChange);
            holder.rlListItem = convertView.findViewById(R.id.rlListItem);
            holder.tvPlane = convertView.findViewById(R.id.tvPlane);
            holder.tvPlane_bargasht = convertView.findViewById(R.id.tvPlane_bargasht);


            holder.linear_1 = convertView.findViewById(R.id.linear_1);
            holder.linear_2 = convertView.findViewById(R.id.linear_2);
            holder.tvANRaft2_1 = convertView.findViewById(R.id.tvANRaft2_1);
            holder.tvANRaft2_2 = convertView.findViewById(R.id.tvANRaft2_2);
            holder.tvANRaft2_3 = convertView.findViewById(R.id.tvANRaft2_3);
            holder.tvANRaft1_1 = convertView.findViewById(R.id.tvANRaft1_1);
            holder.tvANRaft1_2 = convertView.findViewById(R.id.tvANRaft1_2);


            holder.linear_1_bargasht = convertView.findViewById(R.id.linear_1_bargasht);
            holder.linear_2_bargasht = convertView.findViewById(R.id.linear_2_bargasht);
            holder.tvANRaft2_1_bargasht = convertView.findViewById(R.id.tvANRaft2_1_bargasht);
            holder.tvANRaft2_2_bargasht = convertView.findViewById(R.id.tvANRaft2_2_bargasht);
            holder.tvANRaft2_3_bargasht = convertView.findViewById(R.id.tvANRaft2_3_bargasht);
            holder.tvANRaft1_1_bargasht = convertView.findViewById(R.id.tvANRaft1_1_bargasht);
            holder.tvANRaft1_2_bargasht = convertView.findViewById(R.id.tvANRaft1_2_bargasht);
            holder.nonStop = convertView.findViewById(R.id.nonStop);


            holder.linear_3_bargasht = convertView.findViewById(R.id.linear_3_bargasht);
            holder.linear_3 = convertView.findViewById(R.id.linear_3);

            holder.tvANRaft3_1 = convertView.findViewById(R.id.tvANRaft3_1);
            holder.tvANRaft3_2 = convertView.findViewById(R.id.tvANRaft3_2);
            holder.tvANRaft3_3 = convertView.findViewById(R.id.tvANRaft3_3);
            holder.tvANRaft3_4 = convertView.findViewById(R.id.tvANRaft3_4);

            holder.tvANRaft3_1_bargasht = convertView.findViewById(R.id.tvANRaft3_1_bargasht);
            holder.tvANRaft3_2_bargasht = convertView.findViewById(R.id.tvANRaft3_2_bargasht);
            holder.tvANRaft3_3_bargasht = convertView.findViewById(R.id.tvANRaft3_3_bargasht);
            holder.tvANRaft3_4_bargasht = convertView.findViewById(R.id.tvANRaft3_4_bargasht);
            holder.tvBargashtTime2 = convertView.findViewById(R.id.tvBargashtTime2);
            holder.tvBargashtTime1 = convertView.findViewById(R.id.tvBargashtTime1);
            holder.lineOnstep = convertView.findViewById(R.id.lineOnstep);
            holder.lineOnstep_barhasht = convertView.findViewById(R.id.lineOnstep_barhasht);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.btnChange.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));

        YoYo.with(Techniques.FadeIn)
                .duration(300)
                .playOn(holder.cvHotel);

        String imageUri = "https://cdn.elicdn.com" + selectHotelModelArrayList.get(position).getImageUrl();


        GlideApp
                .with(activity)
                .load(imageUri)
                .centerCrop()
                .error(R.drawable.not_found)
                .into(holder.ivHotelPic);
        holder.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SearchFlightActivity.class);
                intent.putExtra("isChangeFlight", true);
                intent.putExtra("FlightId", selectHotelModelArrayList.get(position).getFlightId());
                intent.putExtra("SearchKey", selectHotelModelArrayList.get(position).getResultUniqID());
                activity.startActivityForResult(intent, 155);



            }
        });
        holder.rlListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, DetailHotelActivity.class);
                i.putExtra("HotelId", selectHotelModelArrayList.get(position).geteHotelId());
                i.putExtra("ResultUniqID", selectHotelModelArrayList.get(position).getResultUniqID());
                i.putExtra("FlightID", selectHotelModelArrayList.get(position).getFlightId());
                i.putExtra("CheckInHF", activity.getIntent().getExtras().getString("CheckInHF"));
                i.putExtra("CheckOutHF", activity.getIntent().getExtras().getString("CheckOutHF"));
                i.putExtra("DateTime", DateTime.getText().toString());

                i.putExtra("type", 1);
                SwipeBackActivityHelper.activityBuilder(activity)
                        .intent(i)
                        .needParallax(true)
                        .needBackgroundShadow(true)
                        .startActivity();
            }
        });

        holder.name.setText(selectHotelModelArrayList.get(position).getName());
        holder.location.setText(selectHotelModelArrayList.get(position).getLocation() + "،" + selectHotelModelArrayList.get(position).getCity());
        holder.title.setText(selectHotelModelArrayList.get(position).getTitle());
        holder.board.setText(selectHotelModelArrayList.get(position).getBoard());

        holder.tvPrice.setText(Utility.priceFormat(String.valueOf(Integer.valueOf(selectHotelModelArrayList.get(position).getPrice()) + Integer.valueOf(selectHotelModelArrayList.get(position).getAmount()))));


        if (selectHotelModelArrayList.get(position).getTypeText().contains("آپارتمان")||selectHotelModelArrayList.get(position).getTypeText().toLowerCase().contains("apart")) {
            holder.txt_lable_hotel.setText(R.string.ApartmenHotel);
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);

        } else if (selectHotelModelArrayList.get(position).getTypeText().contains("بوتیک")||selectHotelModelArrayList.get(position).getTypeText().toLowerCase().contains("bout")) {
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);
            holder.txt_lable_hotel.setText(R.string.BoutiqueHotel);


        } else if (selectHotelModelArrayList.get(position).getTypeText().contains("ریزورت")||selectHotelModelArrayList.get(position).getTypeText().toLowerCase().contains("reso")) {
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);
            holder.txt_lable_hotel.setText(R.string.ResortHotel);


        } else {
            holder.txt_lable_hotel.setVisibility(View.GONE);

        }

        String waitRaft;
        String waitBargasht;


        String[] strings = selectHotelModelArrayList.get(position).getDepRout().split("→");
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


        String[] strings2 = selectHotelModelArrayList.get(position).getArrRout().split("→");
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


        String text = "<font color=##2e3192>" + selectHotelModelArrayList.get(position).getFlights().get(0).getFlightTime() + "</font> " +
                "<font color=#4d4d4d>" + selectHotelModelArrayList.get(position).getFlights().get(0).getFlightNumber() + "</font>";
        holder.tvRaftTime.setText(Html.fromHtml(text));

        String texttvBargashtTime = "<font color=##2e3192>" + selectHotelModelArrayList.get(position).getFlights().get(1).getFlightTime() + "</font> " +
                "<font color=#4d4d4d>" + selectHotelModelArrayList.get(position).getFlights().get(1).getFlightNumber() + "</font>";
        holder.tvBargashtTime.setText(Html.fromHtml(texttvBargashtTime));

        String texttvBargashtTime1 = "<font color=##2e3192>" + selectHotelModelArrayList.get(position).getFlights().get(0).getFlightArrivalTime() + "</font> " +
                "<font color=#4d4d4d>" + selectHotelModelArrayList.get(position).getFlights().get(0).getFlightNumber() + "</font>";
        holder.tvBargashtTime1.setText(Html.fromHtml(texttvBargashtTime1));
        String texttvBargashtTime2 = "<font color=##2e3192>" + selectHotelModelArrayList.get(position).getFlights().get(1).getFlightArrivalTime() + "</font> " +
                "<font color=#4d4d4d>" + selectHotelModelArrayList.get(position).getFlights().get(1).getFlightNumber() + "</font>";
        holder.tvBargashtTime2.setText(Html.fromHtml(texttvBargashtTime2));


        holder.tvAirLines.setText(selectHotelModelArrayList.get(position).getFlights().get(0).getAirlineNameFa());

        String imageUri2 = "https://cdn.elicdn.com/Content/AirLine/MblSize/" + selectHotelModelArrayList.get(position).getFlights().get(0).getAirlineCode() + ".png";


        GlideApp
                .with(activity)
                .load(imageUri2)
                .centerCrop()
                .error(R.drawable.not_found)
                .into(holder.ivLogo);


        if (selectHotelModelArrayList.get(position).isOff()) {
            holder.tvOff.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.SlideInRight)
                    .duration(700)
                    .playOn(holder.tvOff);

            holder.tvOff.setText(selectHotelModelArrayList.get(position).getOff());

        } else {
            holder.tvOff.setVisibility(View.GONE);

        }
        if (selectHotelModelArrayList.get(position).isBestSell()) {
            holder.ivIsBestseler.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.SlideInLeft)
                    .duration(700)
                    .playOn(holder.ivIsBestseler);
        } else {
            holder.ivIsBestseler.setVisibility(View.GONE);

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

    }

    public void anim(View lineOnstep, View tvPlane, int position) {
      /*  if (lineOnstep.getTag() !=null && lineOnstep.getTag().equals("gg"))
            return;
        lineOnstep.setTag("gg");*/
        boolean run = false;
        for (int i = 0; i < selectHotelModelArrayList.get(position).getBooleans().size(); i++) {
            if (!selectHotelModelArrayList.get(position).getBooleans().get(i)) {
                run = true;
                selectHotelModelArrayList.get(position).getBooleans().set(i,true);
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








