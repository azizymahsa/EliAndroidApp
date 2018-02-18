package com.eligasht.reservation.views.adapters.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.hotel.FilterPriceModel;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Reza.nejati on 1/14/2018.
 */

public class PriceFilterAdapter extends BaseAdapter {
    private ArrayList<FilterPriceModel> filterPriceModels = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;

    public PriceFilterAdapter(ArrayList<FilterPriceModel> filterPriceModels, Context context) {
        this.filterPriceModels = filterPriceModels;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return filterPriceModels.size();
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
            convertView = inflater.inflate(R.layout.filter_hotel_type_price, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.checkbox = (SmoothCheckBox) convertView.findViewById(R.id.checkbox);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(filterPriceModels.get(position).getDiff());

        holder.checkbox.setEnabled(false);
        holder.checkbox.setClickable(false);


        if (filterPriceModels.get(position).isCheck()){
            holder.checkbox.setChecked(true);





        }else{
            holder.checkbox.setChecked(false);

        }









        return convertView;
    }


    public class ViewHolder{
        TextView tvTitle;
        SmoothCheckBox checkbox;


    }
}

