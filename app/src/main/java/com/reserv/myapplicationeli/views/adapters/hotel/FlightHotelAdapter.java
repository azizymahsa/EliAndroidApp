package com.reserv.myapplicationeli.views.adapters.hotel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.hotel.FilterPriceModel;
import com.reserv.myapplicationeli.models.hotel.adapter.SelectFlightHotelModel;
import com.reserv.myapplicationeli.models.hotel.adapter.SelectHotelModel;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.views.activities.hotel.activity.DetailHotelActivity;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Reza.nejati on 1/14/2018.
 */

public class FlightHotelAdapter extends BaseAdapter {
    private ArrayList<SelectFlightHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;
    ImageLoader imageLoader;
    Context context;
    Activity activity;

    public FlightHotelAdapter(ArrayList<SelectFlightHotelModel> selectHotelModelArrayList, Context context, Activity activity) {
        this.activity = activity;
        this.selectHotelModelArrayList = selectHotelModelArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        imageLoader = ImageLoader.getInstance();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));


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
            holder.ivHotelPic = (ImageView) convertView.findViewById(R.id.ivHotelPic);
            holder.ivRate = (ImageView) convertView.findViewById(R.id.ivRate);
            holder.ivLogo = (ImageView) convertView.findViewById(R.id.ivLogo);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.location = (TextView) convertView.findViewById(R.id.location);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.board = (TextView) convertView.findViewById(R.id.board);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            holder.tvOff = (TextView) convertView.findViewById(R.id.tvOff);
            holder.cvHotel = (CardView) convertView.findViewById(R.id.cvHotel);
            holder.tvRaft = (TextView) convertView.findViewById(R.id.tvRaft);
            holder.tvBargasht = (TextView) convertView.findViewById(R.id.tvBargasht);
            holder.tvBargashtTime = (TextView) convertView.findViewById(R.id.tvBargashtTime);
            holder.tvRaftTime = (TextView) convertView.findViewById(R.id.tvRaftTime);
            holder.tvBargashtTimeWait = (TextView) convertView.findViewById(R.id.tvBargashtTimeWait);
            holder.tvRaftTimeWait = (TextView) convertView.findViewById(R.id.tvRaftTimeWait);
            holder.tvAirLines = (TextView) convertView.findViewById(R.id.tvAirLines);
            holder.ivIsBestseler = (TextView) convertView.findViewById(R.id.ivIsBestseler);



            holder.linear_1 = (LinearLayout) convertView.findViewById(R.id.linear_1);
            holder.linear_2 = (LinearLayout) convertView.findViewById(R.id.linear_2);
            holder.tvANRaft2_1 = (TextView) convertView.findViewById(R.id.tvANRaft2_1);
            holder.tvANRaft2_2 = (TextView) convertView.findViewById(R.id.tvANRaft2_2);
            holder.tvANRaft2_3 = (TextView) convertView.findViewById(R.id.tvANRaft2_3);
            holder.tvANRaft1_1 = (TextView) convertView.findViewById(R.id.tvANRaft1_1);
            holder.tvANRaft1_2 = (TextView) convertView.findViewById(R.id.tvANRaft1_2);


            holder.linear_1_bargasht = (LinearLayout) convertView.findViewById(R.id.linear_1_bargasht);
            holder.linear_2_bargasht = (LinearLayout) convertView.findViewById(R.id.linear_2_bargasht);
            holder.tvANRaft2_1_bargasht = (TextView) convertView.findViewById(R.id.tvANRaft2_1_bargasht);
            holder.tvANRaft2_2_bargasht = (TextView) convertView.findViewById(R.id.tvANRaft2_2_bargasht);
            holder.tvANRaft2_3_bargasht = (TextView) convertView.findViewById(R.id.tvANRaft2_3_bargasht);
            holder.tvANRaft1_1_bargasht = (TextView) convertView.findViewById(R.id.tvANRaft1_1_bargasht);
            holder.tvANRaft1_2_bargasht = (TextView) convertView.findViewById(R.id.tvANRaft1_2_bargasht);
            holder.nonStop = (TextView) convertView.findViewById(R.id.nonStop);




            holder.linear_3_bargasht = (LinearLayout) convertView.findViewById(R.id.linear_3_bargasht);
            holder.linear_3 = (LinearLayout) convertView.findViewById(R.id.linear_3);

            holder.tvANRaft3_1 = (TextView) convertView.findViewById(R.id.tvANRaft3_1);
            holder.tvANRaft3_2 = (TextView) convertView.findViewById(R.id.tvANRaft3_2);
            holder.tvANRaft3_3 = (TextView) convertView.findViewById(R.id.tvANRaft3_3);
            holder.tvANRaft3_4 = (TextView) convertView.findViewById(R.id.tvANRaft3_4);

            holder.tvANRaft3_1_bargasht = (TextView) convertView.findViewById(R.id.tvANRaft3_1_bargasht);
            holder.tvANRaft3_2_bargasht = (TextView) convertView.findViewById(R.id.tvANRaft3_2_bargasht);
            holder.tvANRaft3_3_bargasht = (TextView) convertView.findViewById(R.id.tvANRaft3_3_bargasht);
            holder.tvANRaft3_4_bargasht = (TextView) convertView.findViewById(R.id.tvANRaft3_4_bargasht);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Animation scaleUp = AnimationUtils.loadAnimation(activity, R.anim.anim_list);
        holder.cvHotel.startAnimation(scaleUp);

        String imageUri = "https://cdn.elicdn.com" + selectHotelModelArrayList.get(position).getImageUrl();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // this will make circle, pass the width of image
                .displayer(new RoundedBitmapDisplayer(3))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .build();


        imageLoader.displayImage(imageUri, holder.ivHotelPic, options, null);

     /*   AQuery aQuery=new AQuery(v);
        aQuery.id(holder.imgPhoto).image(item.getImageUrl().toString());
*/

        holder.name.setText(selectHotelModelArrayList.get(position).getName());
        holder.location.setText(selectHotelModelArrayList.get(position).getLocation() + " " + selectHotelModelArrayList.get(position).getName());
        holder.title.setText(selectHotelModelArrayList.get(position).getTitle());
        holder.board.setText(selectHotelModelArrayList.get(position).getBoard());
        holder.tvPrice.setText(Utility.priceFormat(String.valueOf(Integer.valueOf(selectHotelModelArrayList.get(position).getPrice()) + Integer.valueOf(selectHotelModelArrayList.get(position).getAmount()))));


        holder.tvRaft.setText(selectHotelModelArrayList.get(position).getArrRout());
        holder.tvBargasht.setText(selectHotelModelArrayList.get(position).getDepRout());

        String waitRaft;
        String waitBargasht;


        String[] strings = selectHotelModelArrayList.get(position).getDepRout().split("→");
        switch (strings.length) {
            case 0:
                waitRaft = "بدون توقف";
                holder.linear_1.setVisibility(View.VISIBLE);
                holder.tvANRaft1_1.setText(strings[0]);
                holder.tvANRaft1_2.setText(strings[1]);
                break;
            case 1:
                holder.linear_1.setVisibility(View.VISIBLE);
                holder.tvANRaft1_1.setText(strings[0]);
                holder.tvANRaft1_2.setText(strings[1]);
                waitRaft = "بدون توقف";

                break;
            case 2:
                waitRaft = "بدون توقف";
                holder.linear_1.setVisibility(View.VISIBLE);
                holder.tvANRaft1_1.setText(strings[0]);
                holder.tvANRaft1_2.setText(strings[1]);

                break;
            case 3:
                waitRaft = "یک توقف";
                holder.linear_2.setVisibility(View.VISIBLE);
                holder.tvANRaft2_1.setText(strings[0]);
                holder.tvANRaft2_2.setText(strings[1]);
                holder.tvANRaft2_3.setText(strings[2]);

                break;
            case 4:
                waitRaft = "دو توقف";
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
                holder.nonStop.setText(strings2[0]);
                holder.nonStop.setVisibility(View.VISIBLE);

                break;
            case 1:
                waitBargasht = "بدون توقف";
                holder.nonStop.setText(strings2[0]);
                holder.nonStop.setVisibility(View.VISIBLE);


                break;
            case 2:
                waitBargasht = "بدون توقف";
                holder.linear_1_bargasht.setVisibility(View.VISIBLE);
                holder.tvANRaft1_1_bargasht.setText(strings2[0]);
                holder.tvANRaft1_2_bargasht.setText(strings2[1]);

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


//selectHotelModelArrayList.get(position).getFlights().get(1).FlightArrivalTime+"-"+
        holder.tvRaftTime.setText(selectHotelModelArrayList.get(position).getFlights().get(0).FlightTime+" ,"+selectHotelModelArrayList.get(position).getFlights().get(0).FlightNumber);
        holder.tvBargashtTime.setText(selectHotelModelArrayList.get(position).getFlights().get(1).FlightTime+" ,"+selectHotelModelArrayList.get(position).getFlights().get(0).FlightNumber);


        holder.tvRaftTimeWait.setText(selectHotelModelArrayList.get(position).getFlights().get(0).FltDurationH + "ساعت " + selectHotelModelArrayList.get(position).getFlights().get(0).FltDurationM + "دقیقه");
        holder.tvBargashtTimeWait.setText(selectHotelModelArrayList.get(position).getFlights().get(1).FltDurationH + "ساعت " + selectHotelModelArrayList.get(position).getFlights().get(1).FltDurationM + "دقیقه");


        holder.tvAirLines.setText(selectHotelModelArrayList.get(position).getFlights().get(0).AirlineNameFa);


        String s = selectHotelModelArrayList.get(position).getFlights().get(0).AirlineCode.toLowerCase();
        if (s.toLowerCase().contains("ir")) {
            holder.ivLogo.setImageResource(R.drawable.ir);
        }

        if (s.toLowerCase().contains("a3")) {
            holder.ivLogo.setImageResource(R.drawable.a);
        }

        if (s.toLowerCase().contains("af")) {
            holder.ivLogo.setImageResource(R.drawable.af);
        }

        if (s.toLowerCase().contains("az")) {
            holder.ivLogo.setImageResource(R.drawable.az);
        }

        if (s.toLowerCase().contains("ek")) {
            holder.ivLogo.setImageResource(R.drawable.ek);
        }

        if (s.toLowerCase().contains("ey")) {
            holder.ivLogo.setImageResource(R.drawable.ey);
        }

        if (s.toLowerCase().contains("fz")) {
            holder.ivLogo.setImageResource(R.drawable.fz);
        }

        if (s.toLowerCase().contains("g9")) {
            holder.ivLogo.setImageResource(R.drawable.g);
        }

        if (s.toLowerCase().contains("j2")) {
            holder.ivLogo.setImageResource(R.drawable.j);
        }

        if (s.toLowerCase().contains("ji")) {
            holder.ivLogo.setImageResource(R.drawable.ji);
        }

        if (s.toLowerCase().contains("kk")) {
            holder.ivLogo.setImageResource(R.drawable.kk);
        }

        if (s.toLowerCase().contains("kl")) {
            holder.ivLogo.setImageResource(R.drawable.kl);
        }

        if (s.toLowerCase().contains("lh")) {
            holder.ivLogo.setImageResource(R.drawable.lh);
        }

        if (s.toLowerCase().contains("pc")) {
            holder.ivLogo.setImageResource(R.drawable.pc);
        }

        if (s.toLowerCase().contains("qr")) {
            holder.ivLogo.setImageResource(R.drawable.qr);
        }

        if (s.toLowerCase().contains("su")) {
            holder.ivLogo.setImageResource(R.drawable.su);
        }

        if (s.toLowerCase().contains("tg")) {
            holder.ivLogo.setImageResource(R.drawable.tg);
        }

        if (s.toLowerCase().contains("tk")) {
            holder.ivLogo.setImageResource(R.drawable.tk);
        }

        if (s.toLowerCase().contains("w5")) {
            holder.ivLogo.setImageResource(R.drawable.w);
        }

        if (s.toLowerCase().contains("wy")) {
            holder.ivLogo.setImageResource(R.drawable.wy);
        }


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


        switch (selectHotelModelArrayList.get(position).getStar()) {

            case 1:
                //todo change this
                holder.ivRate.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable._1star));

                break;
            case 2:
                holder.ivRate.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable._2star));

                break;
            case 3:
                holder.ivRate.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable._3star));

                break;
            case 4:
                holder.ivRate.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable._4star));

                break;

            case 5:
                holder.ivRate.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable._5star));

                break;

        }


        return convertView;
    }

    public class ViewHolder {
        TextView name, location, title, board, tvPrice, tvOff, tvRaft, tvBargasht, tvBargashtTime, tvRaftTime, tvRaftTimeWait, tvBargashtTimeWait, tvAirLines,nonStop,ivIsBestseler;
        ImageView ivHotelPic, ivRate, ivLogo;
        CardView cvHotel;
        LinearLayout linear_1, linear_2,linear_3;
        TextView tvANRaft2_1, tvANRaft2_2, tvANRaft2_3;
        TextView tvANRaft3_1, tvANRaft3_2, tvANRaft3_3,tvANRaft3_4;
        TextView tvANRaft1_1, tvANRaft1_2;

        LinearLayout linear_1_bargasht, linear_2_bargasht,linear_3_bargasht;
        TextView tvANRaft2_1_bargasht, tvANRaft2_2_bargasht, tvANRaft2_3_bargasht;
        TextView tvANRaft3_1_bargasht, tvANRaft3_2_bargasht, tvANRaft3_3_bargasht,tvANRaft3_4_bargasht;
        TextView tvANRaft1_1_bargasht, tvANRaft1_2_bargasht;

    }

}








