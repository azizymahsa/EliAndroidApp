package com.eligasht.reservation.views.adapters.hotel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eligasht.R;
import com.eligasht.reservation.models.hotel.adapter.SelectFlightHotelModel;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.activities.hotel.activity.DetailHotelActivity;
import com.eligasht.reservation.views.ui.SearchParvazActivity;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/14/2018.
 */

public class FlightHotelAdapter extends BaseAdapter {
    ImageLoader imageLoader;
    Activity activity;
    TextView DateTime;
    private ArrayList<SelectFlightHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;

    public FlightHotelAdapter(ArrayList<SelectFlightHotelModel> selectHotelModelArrayList, Activity activity,TextView DateTime) {
        this.activity = activity;
        this.selectHotelModelArrayList = selectHotelModelArrayList;
        this.activity = activity;
        this.DateTime = DateTime;
        inflater = LayoutInflater.from(activity);
        imageLoader = ImageLoader.getInstance();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(activity));


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
//            holder.tvBargashtTimeWait = convertView.findViewById(R.id.tvBargashtTimeWait);
//            holder.tvRaftTimeWait = convertView.findViewById(R.id.tvRaftTimeWait);
            holder.tvAirLines = convertView.findViewById(R.id.tvAirLines);
            holder.ivIsBestseler = convertView.findViewById(R.id.ivIsBestseler);
            holder.txt_lable_hotel = convertView.findViewById(R.id.txt_lable_hotel);
            holder.avi2 = convertView.findViewById(R.id.avi2);
            holder.btnChange = convertView.findViewById(R.id.btnChange);
            holder.rlListItem = convertView.findViewById(R.id.rlListItem);


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


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.btnChange.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));

        YoYo.with(Techniques.FadeIn)
                .duration(300)
                .playOn(holder.cvHotel);

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
        holder.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SearchParvazActivity.class);
                intent.putExtra("isChangeFlight",true);
                intent.putExtra("FlightId",selectHotelModelArrayList.get(position).getFlightId());
                intent.putExtra("SearchKey",selectHotelModelArrayList.get(position).getResultUniqID());
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

                activity.startActivity(i);
            }
        });

        holder.name.setText(selectHotelModelArrayList.get(position).getName());
        holder.location.setText(selectHotelModelArrayList.get(position).getLocation() +"،"+ selectHotelModelArrayList.get(position).getCity());
        holder.title.setText(selectHotelModelArrayList.get(position).getTitle());
        holder.board.setText(selectHotelModelArrayList.get(position).getBoard());
        holder.tvPrice.setText(Utility.priceFormat(String.valueOf(Integer.valueOf(selectHotelModelArrayList.get(position).getPrice()) + Integer.valueOf(selectHotelModelArrayList.get(position).getAmount()))));


        holder.tvRaft.setText(selectHotelModelArrayList.get(position).getArrRout());
        holder.tvBargasht.setText(selectHotelModelArrayList.get(position).getDepRout());


        if (selectHotelModelArrayList.get(position).getTypeText().contains("آپارتمان")) {
            holder.txt_lable_hotel.setText(R.string.ApartmenHotel);
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);

        }else if (selectHotelModelArrayList.get(position).getTypeText().contains("بوتیک")) {
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);
            holder.txt_lable_hotel.setText(R.string.BoutiqueHotel);



        }else if (selectHotelModelArrayList.get(position).getTypeText().contains("ریزورت")) {
            holder.txt_lable_hotel.setVisibility(View.VISIBLE);
            holder.txt_lable_hotel.setText(R.string.ResortHotel);



        }else{
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


                }catch (Exception e){
                    holder.nonStop.setText(strings2[0]);
                    holder.nonStop.setVisibility(View.VISIBLE);

                }


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
        String text = "<font color=##2e3192>"  +selectHotelModelArrayList.get(position).getFlights().get(0).FlightTime + "</font> " +
                "<font color=#4d4d4d>" + selectHotelModelArrayList.get(position).getFlights().get(0).FlightNumber + "</font>";
        holder.tvRaftTime.setText(Html.fromHtml(text));

        String texttvBargashtTime = "<font color=##2e3192>"  +selectHotelModelArrayList.get(position).getFlights().get(1).FlightTime + "</font> " +
                "<font color=#4d4d4d>" + selectHotelModelArrayList.get(position).getFlights().get(1).FlightNumber+ "</font>";
        holder.tvBargashtTime.setText(Html.fromHtml(texttvBargashtTime));

        String texttvBargashtTime1 = "<font color=##2e3192>"  +selectHotelModelArrayList.get(position).getFlights().get(0).FlightArrivalTime + "</font> " +
                "<font color=#4d4d4d>" + selectHotelModelArrayList.get(position).getFlights().get(0).FlightNumber+ "</font>";
        holder.tvBargashtTime1.setText(Html.fromHtml(texttvBargashtTime1));
        String texttvBargashtTime2 = "<font color=##2e3192>"  +selectHotelModelArrayList.get(position).getFlights().get(1).FlightArrivalTime + "</font> " +
                "<font color=#4d4d4d>" + selectHotelModelArrayList.get(position).getFlights().get(1).FlightNumber + "</font>";
        holder.tvBargashtTime2.setText(Html.fromHtml(texttvBargashtTime2));

       /* String texttvRaftTimeWait = "<font color=##2e3192>"  +selectHotelModelArrayList.get(position).getFlights().get(1).FlightTime + "</font> " +
                "<font color=#4d4d4d>" + selectHotelModelArrayList.get(position).getFlights().get(1).FlightNumber + "</font>";
        holder.tvRaftTimeWait.setText(Html.fromHtml(texttvRaftTimeWait));
        String texttvBargashtTimeWait = "<font color=##2e3192>"  +selectHotelModelArrayList.get(position).getFlights().get(1).FlightTime + "</font> " +
                "<font color=#4d4d4d>" + selectHotelModelArrayList.get(position).getFlights().get(1).FlightNumber + "</font>";
        holder.tvBargashtTimeWait.setText(Html.fromHtml(texttvBargashtTimeWait));*/

      // holder.tvRaftTime.setText(selectHotelModelArrayList.get(position).getFlights().get(0).FlightTime+" "+selectHotelModelArrayList.get(position).getFlights().get(0).FlightNumber);
       // holder.tvBargashtTime.setText(selectHotelModelArrayList.get(position).getFlights().get(1).FlightTime+" "+selectHotelModelArrayList.get(position).getFlights().get(1).FlightNumber);

       // holder.tvBargashtTime1.setText(selectHotelModelArrayList.get(position).getFlights().get(0).FlightArrivalTime+" "+selectHotelModelArrayList.get(position).getFlights().get(0).FlightNumber);
       // holder.tvBargashtTime2.setText(selectHotelModelArrayList.get(position).getFlights().get(1).FlightArrivalTime+" "+selectHotelModelArrayList.get(position).getFlights().get(1).FlightNumber);

//        holder.tvRaftTimeWait.setText(selectHotelModelArrayList.get(position).getFlights().get(0).FltDurationH + "ساعت " + selectHotelModelArrayList.get(position).getFlights().get(0).FltDurationM + "دقیقه");
//        holder.tvBargashtTimeWait.setText(selectHotelModelArrayList.get(position).getFlights().get(1).FltDurationH + "ساعت " + selectHotelModelArrayList.get(position).getFlights().get(1).FltDurationM + "دقیقه");


        holder.tvAirLines.setText(selectHotelModelArrayList.get(position).getFlights().get(0).AirlineNameFa);

        String imageUri2 = "https://cdn.elicdn.com/Content/AirLine/MblSize/"+selectHotelModelArrayList.get(position).getFlights().get(0).AirlineCode+".png";


        imageLoader.displayImage(imageUri2, holder.ivLogo, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.avi2.setVisibility(View.GONE);

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.avi2.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                holder.avi2.setVisibility(View.GONE);

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
        TextView name, location, title, board, tvPrice, tvOff, tvRaft, tvBargasht, tvBargashtTime, tvRaftTime, tvRaftTimeWait, tvBargashtTimeWait, tvAirLines,nonStop,ivIsBestseler,txt_lable_hotel,tvBargashtTime2,tvBargashtTime1;
        ImageView ivHotelPic, ivRate, ivLogo;
        CardView cvHotel;
        LinearLayout linear_1, linear_2,linear_3;
        TextView tvANRaft2_1, tvANRaft2_2, tvANRaft2_3;
        TextView tvANRaft3_1, tvANRaft3_2, tvANRaft3_3,tvANRaft3_4;
        TextView tvANRaft1_1, tvANRaft1_2;
        AVLoadingIndicatorView avi2;
        FancyButton btnChange;
        RelativeLayout rlListItem;

        LinearLayout linear_1_bargasht, linear_2_bargasht,linear_3_bargasht;
        TextView tvANRaft2_1_bargasht, tvANRaft2_2_bargasht, tvANRaft2_3_bargasht;
        TextView tvANRaft3_1_bargasht, tvANRaft3_2_bargasht, tvANRaft3_3_bargasht,tvANRaft3_4_bargasht;
        TextView tvANRaft1_1_bargasht, tvANRaft1_2_bargasht;

    }

}








