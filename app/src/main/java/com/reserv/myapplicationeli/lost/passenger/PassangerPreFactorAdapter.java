package com.reserv.myapplicationeli.lost.passenger;

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
import com.reserv.myapplicationeli.lost.hotel.HotelPreFactorAdapter;
import com.reserv.myapplicationeli.lost.hotel.HotelPreFactorModel;

import java.util.List;

/**
 * Created by Reza.nejati on 1/23/2018.
 */

public class PassangerPreFactorAdapter extends RecyclerView.Adapter<PassangerPreFactorAdapter.ViewHolder> {

    private final List<PassengerPreFactorModel> data;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public PassangerPreFactorAdapter(final List<PassengerPreFactorModel> data) {
        this.data = data;
        for (int i = 0; i < data.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public PassangerPreFactorAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new PassangerPreFactorAdapter.ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_view_list_row_passenger, parent, false));
    }


    @Override
    public void onBindViewHolder(final PassangerPreFactorAdapter.ViewHolder holder, final int position) {
        final PassengerPreFactorModel item = data.get(position);

        holder.setIsRecyclable(false);
        holder.tvBrithDay.setText(item.getRqPassenger_Birthdate());
        holder.tvPassNo.setText(item.getRqPassenger_PassNo());
        holder.tvNationality.setText(item.getNationality());
        holder.tvGender.setText(item.getGender());
        holder.tvPassangerName.setText(item.getRqPassenger_name());
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
        holder.tvPassangerName.setOnClickListener(new View.OnClickListener() {
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
        public TextView tvBrithDay, tvPassNo, tvNationality, tvGender,tvArrow,tvPassangerName;
        //  public RelativeLayout tvArrow;
        /**
         * You must use the ExpandableLinearLayout in the recycler view.
         * The ExpandableRelativeLayout doesn't work.
         */
        public ExpandableLinearLayout expandableLayout;

        public ViewHolder(View v) {
            super(v);
            tvBrithDay = (TextView) v.findViewById(R.id.tvBrithDay);
            tvPassNo = (TextView) v.findViewById(R.id.tvPassNo);
            tvNationality = (TextView) v.findViewById(R.id.tvNationality);
            tvGender = (TextView) v.findViewById(R.id.tvGender);
            tvPassangerName = (TextView) v.findViewById(R.id.tvPassangerName);
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
