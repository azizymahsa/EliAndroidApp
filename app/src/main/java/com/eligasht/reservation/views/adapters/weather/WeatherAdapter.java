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
        Log.e("date", item.getDate());
        holder.tvDate.setText(Utility.simpleFormatDate("08 May 2018"));
        switch (Integer.valueOf(data.get(position).getCode())) {
            case 0:
                //tornado
                break;
            case 1:
                //tropical storm
                break;
            case 2:
                //hurricane
                break;
            case 3:
                //severe thunderstorms
                break;
            case 4:
                //thunderstorms
                break;
            case 5:
                //mixed rain and snow
                break;
            case 6:
                //mixed rain and sleet
                break;
            case 7:
                //mixed snow and sleet
                break;
            case 8:
                //freezing drizzle
                break;
            case 9:
                //drizzle
                break;
            case 10:
                //freezing rain
                break;
            case 11:
                //showers
                break;
            case 12:
                //showers
                break;
            case 13:
                //snow flurries
                break;
            case 14:
                //light snow showers
                break;
            case 15:
                //blowing snow
                break;
            case 16:
                //snow
                break;
            case 17:
                //hail
                break;
            case 18:
                //sleet
                break;
            case 19:
                //dust
                break;
            case 20:
                //foggy
                break;
            case 21:
                //haze
                break;
            case 22:
                //smoky
                break;
            case 23:
                //blustery
                break;
            case 24:
                //windy
                break;
            case 25:
                //cold
                break;
            case 26:
                //cloudy
                break;
            case 27:
                //mostly cloudy (night)
                break;
            case 28:
                //mostly cloudy (day)
                break;
            case 29:
                //	partly cloudy (night)
                break;
            case 30:
                //partly cloudy (day)
                break;
            case 31:
                //clear (night)
                break;
            case 32:
                //sunny
                break;
            case 35:
                //fair (night)
                break;
            case 36:
                //fair (day)
                break;
            case 37:
                //mixed rain and hail
                break;
            case 38:
                //hot
                break;
            case 39:
                //	isolated thunderstorms
                break;
            case 40:
                //scattered thunderstorms
                break;
            case 41:
                //heavy snow
                break;
            case 42:
                //scattered showers
                break;
            case 43:
                //heavy snow
                break;
            case 44:
                //partly cloudy
                break;
            case 45:
                //thundershowers
                break;
            case 46:
                //snow showers
                break;
            case 47:
                //isolated thundershowers
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
        public TextView tvDate;

        public ViewHolder(View v) {
            super(v);
            tvDate = v.findViewById(R.id.tvDate);
        }
    }
}