package com.reserv.myapplicationeli.views.adapters.hotel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.hotel.adapter.SelectHotelModel;
import com.reserv.myapplicationeli.tools.Utility;

import java.util.ArrayList;


public class LazyResoultHotelAdapter extends BaseAdapter {

    private ArrayList<SelectHotelModel> selectHotelModelArrayList = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;
    ImageLoader imageLoader;
    Context context;
    Activity activity;

    public LazyResoultHotelAdapter(ArrayList<SelectHotelModel> selectHotelModelArrayList, Context context, Activity activity) {
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
            convertView = inflater.inflate(R.layout.select_hotel_item, null);
            holder = new ViewHolder();
            holder.ivHotelPic = (ImageView) convertView.findViewById(R.id.ivHotelPic);
            holder.ivRate = (ImageView) convertView.findViewById(R.id.ivRate);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.location = (TextView) convertView.findViewById(R.id.location);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.board = (TextView) convertView.findViewById(R.id.board);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            holder.tvOff = (TextView) convertView.findViewById(R.id.tvOff);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String imageUri = "https://cdn.elicdn.com" + selectHotelModelArrayList.get(position).getImageUrl();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // this will make circle, pass the width of image
                .displayer(new RoundedBitmapDisplayer(3))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .build();


        imageLoader.displayImage(imageUri, holder.ivHotelPic, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                //    holder.ivHotelPic.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                //   holder.ivHotelPic.setVisibility(View.VISIBLE);

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                //  holder.ivHotelPic.setVisibility(View.VISIBLE);

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                holder.ivHotelPic.setVisibility(View.VISIBLE);

            }
        });

     /*   AQuery aQuery=new AQuery(v);
        aQuery.id(holder.imgPhoto).image(item.getImageUrl().toString());
*/

        holder.name.setText(selectHotelModelArrayList.get(position).getName());
        holder.location.setText(selectHotelModelArrayList.get(position).getLocation() + " " + selectHotelModelArrayList.get(position).getName());
        holder.title.setText(selectHotelModelArrayList.get(position).getTitle());
        holder.board.setText(selectHotelModelArrayList.get(position).getBoard());
        holder.tvPrice.setText(Utility.priceFormat(selectHotelModelArrayList.get(position).getPrice()));


        if (selectHotelModelArrayList.get(position).isOff()){
            holder.tvOff.setVisibility(View.VISIBLE);
            holder.tvOff.setText(selectHotelModelArrayList.get(position).getOff());

            } else {
                holder.tvOff.setVisibility(View.GONE);

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
        TextView name, location, title, board, tvPrice, tvOff;
        ImageView ivHotelPic, ivRate;


    }

}







