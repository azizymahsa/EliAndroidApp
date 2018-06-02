package com.eligasht.reservation.views.adapters.addpassenge;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.PassengerPreFactorModel;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.List;
/**
 * Created by Reza Nejati on 02,June,2018
 */
public class AddPPassengerAdapter extends RecyclerView.Adapter<AddPPassengerAdapter.ViewHolder> {

    private final List<PassengerPreFactorModel> data;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public AddPPassengerAdapter(final List<PassengerPreFactorModel> data) {
        this.data = data;
        for (int i = 0; i < data.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_view_list_row_add_passenger, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final PassengerPreFactorModel item = data.get(position);

        holder.setIsRecyclable(false);
        holder.tvBrithDay.setText(item.getRqPassenger_Birthdate());




    }



    @Override
    public int getItemCount() {
        return data.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvBrithDay, tvPassNo;


        public ViewHolder(View v) {
            super(v);
            tvBrithDay = v.findViewById(R.id.tvBrithDay);
            tvPassNo = v.findViewById(R.id.tvPassNo);
        }
    }
}
