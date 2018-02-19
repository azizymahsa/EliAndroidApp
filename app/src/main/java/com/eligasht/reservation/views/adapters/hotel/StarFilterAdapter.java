package com.eligasht.reservation.views.adapters.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.hotel.FilterPriceModel;
import com.eligasht.reservation.models.hotel.adapter.FilterStarModel;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by Reza.nejati on 2/18/2018.
 */

public class StarFilterAdapter extends BaseAdapter {
    private ArrayList<FilterStarModel> filterStarModels = new ArrayList<>();
    private LayoutInflater inflater;
    private ViewHolder holder;

    public StarFilterAdapter(ArrayList<FilterStarModel> filterStarModels, Context context) {
        this.filterStarModels = filterStarModels;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return filterStarModels.size();
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
            holder.tvRial = (TextView) convertView.findViewById(R.id.tvRial);
            holder.checkbox = (SmoothCheckBox) convertView.findViewById(R.id.checkbox);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(filterStarModels.get(position).getTitle());

        holder.checkbox.setEnabled(false);
        holder.checkbox.setClickable(false);
        holder.tvRial.setVisibility(View.GONE);


        if (filterStarModels.get(position).isCheck()){
            holder.checkbox.setChecked(true);





        }else{
            holder.checkbox.setChecked(false);

        }









        return convertView;
    }


    public class ViewHolder{
        TextView tvTitle,tvRial;
        SmoothCheckBox checkbox;


    }
}

