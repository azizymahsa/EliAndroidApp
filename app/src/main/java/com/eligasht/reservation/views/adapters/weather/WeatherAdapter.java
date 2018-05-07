/*
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
*/
/**
 * Created by Reza.nejati on 1/22/2018.
 *//*


public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private final List<Forecast> data;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public WeatherAdapter(final List<Forecast> data) {
        this.data = data;
        for (int i = 0; i < data.size(); i++) {
            expandState.append(i, false);
        }
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

        String sum = "";
        sum = item.getAdult() +context.getString(R.string.adault);
        if (Integer.valueOf(item.getChild()) != 0) {
            sum = sum + " " + item.getChild() + context.getString(R.string.child);

        }


        holder.setIsRecyclable(false);
        holder.tvHotelName.setText(item.getHotelName());
        holder.tvSum.setText(sum);
        holder.tvCheckOut.setText(item.getCheckIn());
        holder.tvCheckIn.setText(item.getCheckOut());
        holder.tvRoom.setText(item.getRoom());
        // holder.itemView.setBackgroundColor(ContextCompat.getColor(context, item.colorId1));
        holder.expandableLayout.setInRecyclerView(true);
        // holder.expandableLayout.setBackgroundColor(ContextCompat.getColor(context, item.colorId2));
        // holder.expandableLayout.setInterpolator(item.interpolator);

        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                createRotateAnimator(holder.tvArrow, 0f, 180f).start();
                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
                createRotateAnimator(holder.tvArrow, 180f, 0f).start();
                expandState.put(position, false);
            }
        });

        holder.tvArrow.setRotation(expandState.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandableLayout);
            }
        });
    }

    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHotelName, tvSum, tvCheckOut, tvCheckIn, tvArrow, tvNumAdult, tvRoom;
        public ExpandableLinearLayout expandableLayout;
        RelativeLayout buttonLayout;
        public ViewHolder(View v) {
            super(v);
            tvHotelName = v.findViewById(R.id.tvHotelDetail);
            tvSum = v.findViewById(R.id.tvSum);
            tvCheckOut = v.findViewById(R.id.tvCheckOut);
            tvCheckIn = v.findViewById(R.id.tvCheckIn);
            expandableLayout = v.findViewById(R.id.expandableLayout);
            tvArrow = v.findViewById(R.id.tvArrow);
            tvRoom = v.findViewById(R.id.tvRoom);
            buttonLayout = v.findViewById(R.id.buttonLayout);
        }
    }
}*/