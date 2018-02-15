package com.eligasht.reservation.views.adapters.pack.filter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eligasht.reservation.R;
import com.eligasht.reservation.models.model.pack.filter.AmenityFilter;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.viewholders.filter.AmenityilterRowHolder;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class AmenityFilterAdapter extends RecyclerView.Adapter<AmenityilterRowHolder> {

    private Context context;
    private ArrayList<AmenityFilter> amenityFilters;
    private OnAmenityFilterListener onAmenityFilterListener;


    public AmenityFilterAdapter setOnAmenityFilterListener(OnAmenityFilterListener onAmenityFilterListener) {
        this.onAmenityFilterListener = onAmenityFilterListener;
        return this;
    }

    public interface OnAmenityFilterListener {
        void onChangeFilters(ArrayList<AmenityFilter> amenityFiltersSelected);
    }

    public AmenityFilterAdapter(Context context, ArrayList<AmenityFilter> amenityFilters) {
        this.amenityFilters = amenityFilters;
        this.context = context;

    }


    @Override
    public AmenityilterRowHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_amenity_filter, null);
        AmenityilterRowHolder mh = new AmenityilterRowHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return mh;
    }

    @Override
    public void onBindViewHolder(final AmenityilterRowHolder holder, int position) {
        if (ValidationTools.isEmptyOrNull(amenityFilters)) {
            return;
        }

        final AmenityFilter amenityFilter = amenityFilters.get(position);

        holder.chk_amenity_filter.setOnCheckedChangeListener(null);
        holder.chk_amenity_filter.setChecked(amenityFilter.isSelected());
        String amenityName = ValidationTools.isEmptyOrNull(amenityFilter.getLstHotelAmenity().getAmenityNameFa())?amenityFilter.getLstHotelAmenity().getAmenityName():amenityFilter.getLstHotelAmenity().getAmenityNameFa();
        holder.txt_amenity_filter.setText(amenityName);

        holder.layout_amenity_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.chk_amenity_filter.setChecked(!amenityFilter.isSelected(),true);
                if (onAmenityFilterListener != null) {
                    onAmenityFilterListener.onChangeFilters(getAmenityFilterSelected());
                    return;
                }
            }
        });
        holder.chk_amenity_filter.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                amenityFilter.setSelected(isChecked);
                if (onAmenityFilterListener != null) {
                    onAmenityFilterListener.onChangeFilters(getAmenityFilterSelected());
                    return;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != amenityFilters ? amenityFilters.size() : 0);
    }

    private ArrayList<AmenityFilter> getAmenityFilterSelected() {
        ArrayList<AmenityFilter> amenityFiltersSelected = new ArrayList<>();
        for (AmenityFilter amenityFilter : amenityFilters) {
            if (amenityFilter.isSelected()) {
                amenityFiltersSelected.add(amenityFilter);
            }
        }
        return amenityFiltersSelected;
    }



    public void removeFilter(){
        if(ValidationTools.isEmptyOrNull(amenityFilters)){
            return;
        }

        for(AmenityFilter amenityFilter : amenityFilters){
            amenityFilter.setSelected(false);
        }

        notifyDataSetChanged();
    }
}
