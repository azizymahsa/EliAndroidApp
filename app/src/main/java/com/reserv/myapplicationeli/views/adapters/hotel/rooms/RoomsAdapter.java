package com.reserv.myapplicationeli.views.adapters.hotel.rooms;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
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
import com.reserv.myapplicationeli.views.adapters.hotel.LazyResoultHotelAdapter;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 1/6/2018.
 */

public class RoomsAdapter extends BaseAdapter {
    private ArrayList<RoomsModel> roomsModels = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;

    public RoomsAdapter(ArrayList<RoomsModel> roomsModels, Context context) {
        this.roomsModels = roomsModels;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return roomsModels.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.select_hotel_item_rooms, null);
            holder = new ViewHolder();
            holder.tvBoard = (TextView) convertView.findViewById(R.id.tvBoard);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            holder.tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvBoard.setText(roomsModels.get(position).getBoard());
        holder.tvTitle.setText(roomsModels.get(position).getTitle());
        holder.tvPrice.setText(Utility.priceFormat(roomsModels.get(position).getPrice())+"");
        holder.tvDesc.setText(roomsModels.get(position).getDesc());

        return convertView;
    }


    public class ViewHolder{
        TextView tvBoard,tvTitle,tvPrice,tvDesc;

    }
}