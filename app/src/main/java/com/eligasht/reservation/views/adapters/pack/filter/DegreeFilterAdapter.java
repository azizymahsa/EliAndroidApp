package com.eligasht.reservation.views.adapters.pack.filter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eligasht.reservation.R;

import com.eligasht.reservation.models.model.pack.filter.DegreeFilter;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.viewholders.filter.DegreeFilterRowHolder;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class DegreeFilterAdapter extends RecyclerView.Adapter<DegreeFilterRowHolder> {

    private  Context context;
    private OnDegreeFilterListener onDegreeFilterListener;
    private ArrayList<DegreeFilter> degreeFilters;



    public interface OnDegreeFilterListener{
        void onChangeFilters(ArrayList<DegreeFilter> degreeFiltersSelected);
    }

    public DegreeFilterAdapter(Context context, ArrayList<DegreeFilter> degreeFilters) {
        this.degreeFilters = degreeFilters;
        this.context = context;

    }

    public DegreeFilterAdapter setOnDegreeFilterListener(OnDegreeFilterListener onDegreeFilterListener) {
        this.onDegreeFilterListener = onDegreeFilterListener;
        return this;
    }

    @Override
    public DegreeFilterRowHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_degree_filter, null);
        DegreeFilterRowHolder mh = new DegreeFilterRowHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return mh;
    }

    @Override
    public void onBindViewHolder(final DegreeFilterRowHolder holder, int position) {
        if (ValidationTools.isEmptyOrNull(degreeFilters)) {
            return;
        }

        final DegreeFilter degreeFilter = degreeFilters.get(position);
        holder.chk_degree_filter.setOnCheckedChangeListener(null);
        holder.chk_degree_filter.setChecked(degreeFilter.isSelected());

        holder.txt_place_filter.setText(degreeFilter.getStar() + " ستاره ");

        holder.layout_degree_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.chk_degree_filter.setChecked(!degreeFilter.isSelected,true);

                if(onDegreeFilterListener != null){
                    onDegreeFilterListener.onChangeFilters(getDegreeFiltersSelected());
                    return;
                }
            }
        });

        holder.chk_degree_filter.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                degreeFilter.setSelected(isChecked);
                if(onDegreeFilterListener != null){
                    onDegreeFilterListener.onChangeFilters(getDegreeFiltersSelected());
                    return;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != degreeFilters? degreeFilters.size() : 0);
    }

    private ArrayList<DegreeFilter> getDegreeFiltersSelected() {
        ArrayList<DegreeFilter> degreeFiltersSelected = new ArrayList<>();
        for(DegreeFilter degreeFilter : degreeFilters){
            if(degreeFilter.isSelected){
                degreeFiltersSelected.add(degreeFilter);
            }
        }
        return degreeFiltersSelected;
    }

    public void removeFilter(){
        if(ValidationTools.isEmptyOrNull(degreeFilters)){
            return;
        }

        for(DegreeFilter degreeFilter : degreeFilters){
            degreeFilter.setSelected(false);
        }

        notifyDataSetChanged();
    }
}
