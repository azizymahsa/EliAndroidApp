package com.reserv.myapplicationeli.views.adapters.pack.filter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.pack.filter.HotelTypeFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.PlaceFilter;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.viewholders.filter.HotelTypeFilterRowHolder;
import com.reserv.myapplicationeli.views.viewholders.filter.PlaceFilterRowHolder;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class HotelTypeFilterAdapter extends RecyclerView.Adapter<HotelTypeFilterRowHolder> {

    private  Context context;
    private ArrayList<HotelTypeFilter> hotelTypeFilters;
    private OnHotelTypeFilterListener onHotelTypeFilterListener;

    public HotelTypeFilterAdapter setOnHotelTypeFilterListener(OnHotelTypeFilterListener onHotelTypeFilterListener) {
        this.onHotelTypeFilterListener = onHotelTypeFilterListener;
        return this;
    }

    public HotelTypeFilterAdapter(Context context, ArrayList<HotelTypeFilter> hotelTypeFilters) {
        this.hotelTypeFilters = hotelTypeFilters;
        this.context = context;

    }

    public interface OnHotelTypeFilterListener{
        void onChangeFilters(ArrayList<HotelTypeFilter>  hotelTypeFiltersSelected);
    }

    @Override
    public HotelTypeFilterRowHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hotel_type_filter, null);
        HotelTypeFilterRowHolder mh = new HotelTypeFilterRowHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return mh;
    }

    @Override
    public void onBindViewHolder(final HotelTypeFilterRowHolder holder, int position) {
        if (ValidationTools.isEmptyOrNull(hotelTypeFilters)) {
            return;
        }

        final HotelTypeFilter hotelTypeFilter = hotelTypeFilters.get(position);
        holder.chk_hotel_type_filter.setOnCheckedChangeListener(null);
        holder.chk_hotel_type_filter.setChecked(hotelTypeFilter.isSelected());
        holder.txt_hotel_type_filter.setText(ValidationTools.isEmptyOrNull(hotelTypeFilter.getHotelTypeNameFa())? hotelTypeFilter.getHotelTypeNameEn() :hotelTypeFilter.getHotelTypeNameFa() );

        holder.layout_hotel_type_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.chk_hotel_type_filter.setChecked(!hotelTypeFilter.isSelected(),true);
                if(onHotelTypeFilterListener != null){
                    onHotelTypeFilterListener.onChangeFilters(getHotelTypeFiltersSelected());
                    return;
                }
            }
        });
        holder.chk_hotel_type_filter.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean isChecked) {
                hotelTypeFilter.setSelected(isChecked);
                if(onHotelTypeFilterListener != null){
                    onHotelTypeFilterListener.onChangeFilters(getHotelTypeFiltersSelected());
                    return;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return (null != hotelTypeFilters? hotelTypeFilters.size() : 0);
    }

    private ArrayList<HotelTypeFilter> getHotelTypeFiltersSelected() {
        ArrayList<HotelTypeFilter> hotelTypeFiltersSelected = new ArrayList<>();
        for(HotelTypeFilter hotelTypeFilter: hotelTypeFilters){
            if(hotelTypeFilter.isSelected()){
                hotelTypeFiltersSelected.add(hotelTypeFilter);
            }
        }
        return hotelTypeFiltersSelected;
    }
}
