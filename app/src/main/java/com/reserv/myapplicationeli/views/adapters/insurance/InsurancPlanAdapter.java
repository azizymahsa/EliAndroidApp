package com.reserv.myapplicationeli.views.adapters.insurance;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.insurance.InsurancePlan_;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.views.viewholders.InsuranceRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/15/2018.
 */

public class InsurancPlanAdapter extends RecyclerView.Adapter<InsuranceRowHolder> {

    private Context context;
    private ArrayList<InsurancePlan_> feedItemList;
    private int count;
    private ListenerInsurancPlanAdapter listener;

    public InsurancPlanAdapter(Context context, ArrayList<InsurancePlan_> feedItemList, int count) {
        this.context = context;
        this.feedItemList = feedItemList;
        this.count = count;
    }

    public interface ListenerInsurancPlanAdapter {
        void onClickInsurancPlanItem(InsurancePlan_ insurancePlan);
    }

    public InsurancPlanAdapter setListener(ListenerInsurancPlanAdapter listener) {
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
        final InsurancePlan_ item = feedItemList.get(position);
        holder.txtPrice.setText(Utility.priceFormat(String.valueOf(item.getPrice() * count)));
        holder.txtPlan.setText(item.getTitle());
        holder.txtTitle.setText(item.getTitleEnglish());

        holder.txtPlan.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.yellow_border));
        holder.txtPlan.setTextColor(ContextCompat.getColor(context, R.color.color_hotel));

        holder.btn_insurance_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickInsurancPlanItem(feedItemList.get(position));
                    return;
                }
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
