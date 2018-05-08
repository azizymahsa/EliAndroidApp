package com.eligasht.reservation.views.adapters.weather;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.HotelPreFactorModel;
import com.eligasht.service.model.weather.response.Forecast;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.List;
/**
 * Created by Reza.nejati on 1/22/2018.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private final List<Forecast> data;
    private Context context;

    public WeatherAdapter(final List<Forecast> data) {
        this.data = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_view_weather_row, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Forecast item = data.get(position);



        holder.setIsRecyclable(false);
        holder.tvDate.setText(item.getDate());

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate;

        public ViewHolder(View v) {
            super(v);
            tvDate = v.findViewById(R.id.tvDate);

        }
    }
}