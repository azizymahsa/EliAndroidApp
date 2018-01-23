package com.reserv.myapplicationeli.lost.flight;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.reserv.myapplicationeli.R;

import java.util.List;

/**
 * Created by Reza.nejati on 1/23/2018.
 */

public class FlightPreFactorAdapter extends RecyclerView.Adapter<FlightPreFactorAdapter.ViewHolder> {

    private final List<FlightPreFactorModel> data;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public FlightPreFactorAdapter(final List<FlightPreFactorModel> data) {
        this.data = data;
        for (int i = 0; i < data.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_view_list_row_flight, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final FlightPreFactorModel item = data.get(position);
        holder.setIsRecyclable(false);
        holder.tvFlightDetail.setText(item.getAirlineNameEn());
        holder.tvArrAirport.setText(item.getArrAirPortFa());
        holder.tvDepAir.setText(item.getDepAirPortFa());
        holder.tvDate.setText(item.getFltDate());
        holder.tvOutTime.setText(item.getFltTime());
        holder.tvInTime.setText(item.getFltCheckinTime());
        holder.tvNumber.setText(item.getFltNumber());
        holder.tvAirLineName.setText(item.getAirlineNameEn());
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
        holder.tvFlightDetail.setOnClickListener(new View.OnClickListener() {
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFlightDetail,tvArrAirport,tvDepAir,tvDate,tvOutTime,tvInTime,tvNumber,tvAirLineName,tvArrow;
        //  public RelativeLayout tvArrow;
        /**
         * You must use the ExpandableLinearLayout in the recycler view.
         * The ExpandableRelativeLayout doesn't work.
         */
        public ExpandableLinearLayout expandableLayout;

        public ViewHolder(View v) {
            super(v);
            tvFlightDetail = (TextView) v.findViewById(R.id.tvFlightDetail);
            tvArrAirport = (TextView) v.findViewById(R.id.tvArrAirport);
            tvDepAir = (TextView) v.findViewById(R.id.tvDepAir);
            tvDate = (TextView) v.findViewById(R.id.tvDate);
            tvOutTime = (TextView) v.findViewById(R.id.tvOutTime);
            tvInTime = (TextView) v.findViewById(R.id.tvInTime);
            tvNumber = (TextView) v.findViewById(R.id.tvNumber);
            tvAirLineName = (TextView) v.findViewById(R.id.tvAirLineName);
            expandableLayout = (ExpandableLinearLayout) v.findViewById(R.id.expandableLayout);
            tvArrow = (TextView) v.findViewById(R.id.tvArrow);
        }
    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

}