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
import com.eligasht.reservation.models.model.insurance.InsurancePlan_;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.views.activities.insurance.InsurnaceDetailsActivity;
import com.eligasht.reservation.views.viewholders.InsuranceRowHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elham.bonyani on 1/15/2018.
 * for second list of insurance
 */

public class InsurancPlanAdapter extends RecyclerView.Adapter<InsuranceRowHolder> {

    private Context context;
  //  private List<com.eligasht.service.model.insurance.response.SearchInsurance.InsurancePlan_> feedItemList;
    private List<com.eligasht.service.model.newModel.insurance.response.Insurance> feedItemList;
    private int count;
    private ListenerInsurancPlanAdapter listener;

    public InsurancPlanAdapter(Context context, List<com.eligasht.service.model.newModel.insurance.response.Insurance> feedItemList, int count) {
        this.context = context;
        this.feedItemList = feedItemList;
        this.count = count;
    }

    public interface ListenerInsurancPlanAdapter {
        void onClickInsurancPlanItem(com.eligasht.service.model.newModel.insurance.response.Insurance insurancePlan);
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
        final com.eligasht.service.model.newModel.insurance.response.Insurance item = feedItemList.get(position);

        switch (item.getInsuranceID()) {//InsuranceID
            case "1":
                //cell.photoImage.image =  imageLiteral(resourceName: "yellow-border")//yellow
                //cell.titleLabel.textColor =  colorLiteral(red: 0.9686434865, green: 0.578962326, blue: 0.1115724519, alpha: 1)
                holder.txtPrice.setText(Utility.priceFormat(String.valueOf(item.getInsurancePrice())));//InsurancePrice
                holder.txtPlan.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.yellow_border));//yellow
            case "-3":
                //cell.photoImage.image =  imageLiteral(resourceName: "red-border")//c
                //cell.titleLabel.textColor =  colorLiteral(red: 0.9255638719, green: 0.4216762483, blue: 0.4419255555, alpha: 1)
               // price1 = price1 * categoryArray.count

                holder.txtPrice.setText(Utility.priceFormat(String.valueOf(item.getInsurancePrice() * count)));
                holder.txtPlan.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.red_border));//red
            case "-2":
               /* cell.photoImage.image =  imageLiteral(resourceName: "green-border")//green
                cell.titleLabel.textColor =  colorLiteral(red: 0.1016399041, green: 0.7363144159, blue: 0.6097738743, alpha: 1)
                price1 = price1 * categoryArray.count*/
                holder.txtPrice.setText(Utility.priceFormat(String.valueOf(item.getInsurancePrice() * count)));
                holder.txtPlan.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.green_border));//green
            case "-1":
               /* cell.photoImage.image =  imageLiteral(resourceName: "blue-border")//blue
                cell.titleLabel.textColor =  colorLiteral(red: 0, green: 0.6590303183, blue: 0.9333506227, alpha: 1)
                price1 = price1 * categoryArray.count*/
                holder.txtPrice.setText(Utility.priceFormat(String.valueOf(item.getInsurancePrice() * count)));
                holder.txtPlan.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.blue_border));//blue
            default:
                holder.txtPrice.setText(Utility.priceFormat(String.valueOf(item.getInsurancePrice())));
                holder.txtPlan.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.yellow_border));//yellow

        }

        holder.txtPlan.setText(item.getInsuranceNameFa());//InsuranceNameFa
        holder.txtTitle.setText(item.getInsuranceNameEn());//InsuranceNameEn


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
        holder.btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,InsurnaceDetailsActivity.class);
                intent.putExtra("details", new Gson().toJson(item.getTravelInsuranceCoverages()));//getCoverInfos()));
                intent.putExtra("plan", true);
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
