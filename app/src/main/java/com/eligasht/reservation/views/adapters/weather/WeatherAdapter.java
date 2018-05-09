package com.eligasht.reservation.views.adapters.weather;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.HotelPreFactorModel;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.datetools.DateUtil;
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
     //   holder.tvDate.setText(Utility.simpleFormatDate(item.getDate());
        holder.tvDate.setText(DateUtil.getLongStringDate(item.getDate(), "dd MMMM yyyy", true));
        switch (Integer.valueOf(data.get(position).getCode())) {
            case 0:
                //tornado
                holder.tvW.setText(context.getResources().getString(R.string.wind));
                break;
            case 1:
                //tropical storm
                holder.tvW.setText(context.getResources().getString(R.string.wind));
                break;
            case 2:
                //hurricane
                holder.tvW.setText(context.getResources().getString(R.string.wind));
                break;
            case 3:
                //severe thunderstorms
                holder.tvW.setText(context.getResources().getString(R.string.lightning));
                break;
            case 4:
                //thunderstorms
                holder.tvW.setText(context.getResources().getString(R.string.lightning));
                break;
            case 5:
                //mixed rain and snow
                holder.tvW.setText(context.getResources().getString(R.string.rainy2));

                break;
            case 6:
                //mixed rain and sleet
                holder.tvW.setText(context.getResources().getString(R.string.rainy));

                break;
            case 7:
                //mixed snow and sleet
                holder.tvW.setText(context.getResources().getString(R.string.snow));

                break;
            case 8:
                //freezing drizzle
                holder.tvW.setText(context.getResources().getString(R.string.snow));

                break;
            case 9:
                //drizzle
                holder.tvW.setText(context.getResources().getString(R.string.snow));

                break;
            case 10:
                //freezing rain
                holder.tvW.setText(context.getResources().getString(R.string.rainy));

                break;
            case 11:
                //showers
                holder.tvW.setText(context.getResources().getString(R.string.rainy2));

                break;
            case 12:
                //showers
                holder.tvW.setText(context.getResources().getString(R.string.rainy2));

                break;
            case 13:
                //snow flurries
                holder.tvW.setText(context.getResources().getString(R.string.snow));

                break;
            case 14:
                //light snow showers
                holder.tvW.setText(context.getResources().getString(R.string.snow));

                break;
            case 15:
                //blowing snow
                holder.tvW.setText(context.getResources().getString(R.string.snow3));

                break;
            case 16:
                //snow
                holder.tvW.setText(context.getResources().getString(R.string.snow));

                break;
            case 17:
                //hail
                holder.tvW.setText(context.getResources().getString(R.string.rainy2));

                break;
            case 18:
                //sleet
                holder.tvW.setText(context.getResources().getString(R.string.snow));

                break;
            case 19:
                //dust
                holder.tvW.setText(context.getResources().getString(R.string.wind));

                break;
            case 20:
                //foggy
                holder.tvW.setText(context.getResources().getString(R.string.wind));

                break;
            case 21:
                //haze
                holder.tvW.setText(context.getResources().getString(R.string.sun));

                break;
            case 22:
                //smoky
                holder.tvW.setText(context.getResources().getString(R.string.sun));

                break;
            case 23:
                //blustery
                holder.tvW.setText(context.getResources().getString(R.string.sun));

                break;
            case 24:
                //windy
                holder.tvW.setText(context.getResources().getString(R.string.wind));

                break;
            case 25:
                //cold
                holder.tvW.setText(context.getResources().getString(R.string.sun));

                break;
            case 26:
                //cloudy
                holder.tvW.setText(context.getResources().getString(R.string.cloud));

                break;
            case 27:
                //mostly cloudy (night)
                holder.tvW.setText(context.getResources().getString(R.string.cloud));

                break;
            case 28:
                //mostly cloudy (day)
                holder.tvW.setText(context.getResources().getString(R.string.cloud));

                break;
            case 29:
                //	partly cloudy (night)
                holder.tvW.setText(context.getResources().getString(R.string.cloud));

                break;
            case 30:
                //partly cloudy (day)
                holder.tvW.setText(context.getResources().getString(R.string.cloud));

                break;
            case 31:
                //clear (night)
                holder.tvW.setText(context.getResources().getString(R.string.sun));

                break;
            case 32:
                //sunny
                holder.tvW.setText(context.getResources().getString(R.string.sun));

                break;
            case 33:
                //fair (night)
                holder.tvW.setText(context.getResources().getString(R.string.sun));

                break;
            case 34:
                //fair (day)
                holder.tvW.setText(context.getResources().getString(R.string.sun));

                break;
            case 35:
                //fair (night)
                holder.tvW.setText(context.getResources().getString(R.string.sun));

                break;
            case 36:
                //fair (day)
                holder.tvW.setText(context.getResources().getString(R.string.sun));

                break;
            case 37:
                //mixed rain and hail
                holder.tvW.setText(context.getResources().getString(R.string.rainy));

                break;
            case 38:
                //hot
                holder.tvW.setText(context.getResources().getString(R.string.sun));

                break;
            case 39:
                //	isolated thunderstorms
                holder.tvW.setText(context.getResources().getString(R.string.lightning));

                break;
            case 40:
                //scattered thunderstorms
                holder.tvW.setText(context.getResources().getString(R.string.lightning));

                break;
            case 41:
                //heavy snow
                holder.tvW.setText(context.getResources().getString(R.string.snow3));

                break;
            case 42:
                //scattered showers
                holder.tvW.setText(context.getResources().getString(R.string.rainy2));

                break;
            case 43:
                //heavy snow
                holder.tvW.setText(context.getResources().getString(R.string.snow3));

                break;
            case 44:
                //partly cloudy
                holder.tvW.setText(context.getResources().getString(R.string.cloud));

                break;
            case 45:
                //thundershowers
                holder.tvW.setText(context.getResources().getString(R.string.lightning));

                break;
            case 46:
                //snow showers
                holder.tvW.setText(context.getResources().getString(R.string.rainy2));

                break;
            case 47:
                //isolated thundershowers
                holder.tvW.setText(context.getResources().getString(R.string.lightning));

                break;
                case 3200:
                //not available
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate,tvW;

        public ViewHolder(View v) {
            super(v);
            tvDate = v.findViewById(R.id.tvDate);
            tvW = v.findViewById(R.id.tvW);
        }
    }
}