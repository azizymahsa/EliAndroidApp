package com.eligasht.reservation.views.activities.transfer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eligasht.reservation.R;

import com.eligasht.reservation.tools.Prefs;

import java.util.ArrayList;

/**
 * Created by Reza.nejati on 2/13/2018.
 */

public class GetCityHotelAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ArrayList<HotelCityModel> hotelCityModels = new ArrayList<>();
    Activity context;



    public GetCityHotelAdapter(Activity context, ArrayList<HotelCityModel> hotelCityModels ) {
        this.hotelCityModels = hotelCityModels;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return hotelCityModels.size();
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
            convertView = inflater.inflate(R.layout.row_airport, null);
            holder = new ViewHolder();
            holder.text1 = (TextView) convertView.findViewById(R.id.text1);
            holder.text2 = (TextView) convertView.findViewById(R.id.text2);
            holder.llContentLayout = (LinearLayout) convertView.findViewById(R.id.llContentLayout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text1.setText(hotelCityModels.get(position).getHotelNameFa());
        holder.text2.setText(hotelCityModels.get(position).getHotelNameEn());
        holder.llContentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.putString("HotelName",hotelCityModels.get(position).getHotelNameFa());
                Prefs.putString("HotelCode",hotelCityModels.get(position).getHotelID());
                context.finish();


            }
        });

        return convertView;
    }


    public class ViewHolder {
        TextView text1,text2;
        LinearLayout llContentLayout;

    }
}
