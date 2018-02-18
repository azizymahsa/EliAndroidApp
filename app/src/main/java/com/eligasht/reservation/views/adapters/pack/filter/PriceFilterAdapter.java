package com.eligasht.reservation.views.adapters.pack.filter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eligasht.R;

import com.eligasht.reservation.models.model.pack.filter.PriceFilter;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.viewholders.filter.PriceFilterRowHolder;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class PriceFilterAdapter extends RecyclerView.Adapter<PriceFilterRowHolder> {

    private Context context;
    private ArrayList<PriceFilter> priceFilters;
    private OnPriceFilterListener onPriceFilterListener;


    public PriceFilterAdapter setOnPriceFilterListener(OnPriceFilterListener onPriceFilterListener) {
        this.onPriceFilterListener = onPriceFilterListener;
        return this;
    }

    public interface OnPriceFilterListener {
        void onChangeFilters(ArrayList<PriceFilter> priceFiltersSelected);
    }

    public PriceFilterAdapter(Context context, ArrayList<PriceFilter> priceFilters) {
        this.priceFilters = priceFilters;
        this.context = context;

    }


    @Override
    public PriceFilterRowHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_price_filter, null);
        PriceFilterRowHolder mh = new PriceFilterRowHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return mh;
    }

    @Override
    public void onBindViewHolder(final PriceFilterRowHolder holder, int position) {
        if (ValidationTools.isEmptyOrNull(priceFilters)) {
            return;
        }

        final PriceFilter priceFilter = priceFilters.get(position);

        holder.chk_price_filter.setOnCheckedChangeListener(null);
        holder.chk_price_filter.setChecked(priceFilter.isSelected());
        holder.txt_price_filter.setText(priceFilter.getMaxPrice() + " - " + priceFilter.getMinPrice());

        holder.layout_price_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.chk_price_filter.setChecked(!priceFilter.isSelected(),true);
                if (onPriceFilterListener != null) {
                    onPriceFilterListener.onChangeFilters(getPriceFiltersSelected());
                    return;
                }
            }
        });
        holder.chk_price_filter.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                priceFilter.setSelected(isChecked);
                if (onPriceFilterListener != null) {
                    onPriceFilterListener.onChangeFilters(getPriceFiltersSelected());
                    return;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != priceFilters ? priceFilters.size() : 0);
    }

    private ArrayList<PriceFilter> getPriceFiltersSelected() {
        ArrayList<PriceFilter> priceFiltersSelected = new ArrayList<>();
        for (PriceFilter priceFilter : priceFilters) {
            if (priceFilter.isSelected()) {
                priceFiltersSelected.add(priceFilter);
            }
        }
        return priceFiltersSelected;
    }

    public void removeFilter(){
        if(ValidationTools.isEmptyOrNull(priceFilters)){
            return;
        }

        for(PriceFilter priceFilter : priceFilters){
            priceFilter.setSelected(false);
        }

        notifyDataSetChanged();
    }
}
