package com.reserv.myapplicationeli.views.adapters.pack.filter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.pack.filter.DegreeFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.HotelTypeFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.PlaceFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.PriceFilter;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.viewholders.filter.PlaceFilterRowHolder;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class PlaceFilterAdapter extends RecyclerView.Adapter<PlaceFilterRowHolder> {

    private  Context context;
    private ArrayList<PlaceFilter> placeFilters;
    private OnPlaceFilterListener onPlaceFilterListener;

    public PlaceFilterAdapter setOnPlaceFilterListener(OnPlaceFilterListener onPlaceFilterListener) {
        this.onPlaceFilterListener = onPlaceFilterListener;
        return this;
    }

    public PlaceFilterAdapter(Context context, ArrayList<PlaceFilter> placeFilters) {
        this.placeFilters = placeFilters;
        this.context = context;

    }

    public interface OnPlaceFilterListener{
        void onChangeFilters(ArrayList<PlaceFilter> placeFiltersSelected);
    }

    @Override
    public PlaceFilterRowHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_place_filter, null);
        PlaceFilterRowHolder mh = new PlaceFilterRowHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return mh;
    }

    @Override
    public void onBindViewHolder(final PlaceFilterRowHolder holder, int position) {
        if (ValidationTools.isEmptyOrNull(placeFilters)) {
            return;
        }

        final PlaceFilter placeFilter = placeFilters.get(position);
        holder.chk_place_filter.setOnCheckedChangeListener(null);
        holder.chk_place_filter.setChecked(placeFilter.isSelected());
        holder.txt_place_filter.setText(placeFilter.getLocationName());

        holder.layout_place_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.chk_place_filter.setChecked(!placeFilter.isSelected(),true);
                if(onPlaceFilterListener != null){
                    onPlaceFilterListener.onChangeFilters(getPlaceFiltersSelected());
                    return;
                }
            }
        });
        holder.chk_place_filter.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean isChecked) {
                placeFilter.setSelected(isChecked);
                if(onPlaceFilterListener != null){
                    onPlaceFilterListener.onChangeFilters(getPlaceFiltersSelected());
                    return;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return (null != placeFilters? placeFilters.size() : 0);
    }

    private ArrayList<PlaceFilter> getPlaceFiltersSelected() {
        ArrayList<PlaceFilter> placeFiltersSelected = new ArrayList<>();
        for(PlaceFilter placeFilter: placeFilters){
            if(placeFilter.isSelected()){
                placeFiltersSelected.add(placeFilter);
            }
        }
        return placeFiltersSelected;
    }

    public void removeFilter(){
        if(ValidationTools.isEmptyOrNull(placeFilters)){
            return;
        }

        for(PlaceFilter placeFilter : placeFilters){
            placeFilter.setSelected(false);
        }

        notifyDataSetChanged();
    }
}
