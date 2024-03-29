package com.eligasht.reservation.views.adapters.insurance;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.gson.Gson;
import com.eligasht.R;
import com.eligasht.reservation.models.model.insurance.TravelInsurance_;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.activities.insurance.InsurnaceDetailsActivity;
import com.eligasht.reservation.views.viewholders.InsuranceRowHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elham.bonyani on 1/15/2018.
 * for first list of insurance
 */

public class TravelInsurancAdapter extends RecyclerView.Adapter<InsuranceRowHolder> {

    private Context context;
    private List<com.eligasht.service.model.insurance.response.SearchInsurance.TravelInsurance_> feedItemList;
    private int count;
    private ListenerTravelInsurancAdapter listener;

    public TravelInsurancAdapter(Context context, List<com.eligasht.service.model.insurance.response.SearchInsurance.TravelInsurance_> feedItemList , int count) {
        this.context = context;
        this.feedItemList = feedItemList;
        this.count = count;
    }

    public interface ListenerTravelInsurancAdapter {
        void onClickTravelInsurancItem(com.eligasht.service.model.insurance.response.SearchInsurance.TravelInsurance_ travelInsurance);
    }

    public TravelInsurancAdapter setListener(ListenerTravelInsurancAdapter listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public InsuranceRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_search_insurance_item, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new InsuranceRowHolder(view);
    }

    @Override
    public void onBindViewHolder(InsuranceRowHolder holder, final int position) {
        final com.eligasht.service.model.insurance.response.SearchInsurance.TravelInsurance_ item = feedItemList.get(position);
        holder.txtPrice.setText(Utility.priceFormat(String.valueOf(item.getTravelInsurancePricePP().getAmount() * count)));
        String[] titles = item.getTravelInsuranceTile().split("-");
        holder.txtPlan.setText(titles[1]);
        holder.txtTitle.setText(titles[0]);

        switch (Integer.valueOf(item.getTravelInsuranceID())){
            case -1:
                holder.txtPlan.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.blue_border));
                holder.txtPlan.setTextColor(ContextCompat.getColor(context, R.color.pressed_color));
                break;
            case -2:
                holder.txtPlan.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.green_border));
                holder.txtPlan.setTextColor(ContextCompat.getColor(context, R.color.green));
                break;
            case -3:
                holder.txtPlan.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.red_border));
                holder.txtPlan.setTextColor(ContextCompat.getColor(context, R.color.red_border));
                break;
            case 1:
                holder.txtPlan.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.red_border));
                holder.txtPlan.setTextColor(ContextCompat.getColor(context, R.color.red_border));
                break;
        }

        holder.btn_insurance_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickTravelInsurancItem(feedItemList.get(position));
                    return;
                }
            }
        });
        holder.btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,InsurnaceDetailsActivity.class);
                intent.putExtra("details", new Gson().toJson(item.getTravelInsuranceCoverages()));
                context.startActivity(intent);

            }
        });

        Animation scaleUp = AnimationUtils.loadAnimation(context, R.anim.anim_list);
        holder.list_inPlan.startAnimation(scaleUp);


    }

    @Override
    public int getItemCount() {
        return (feedItemList == null ? 0 : feedItemList.size());
    }
}
