package com.reserv.myapplicationeli.views.adapters.pack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.pack.ChildAgeRange;
import com.reserv.myapplicationeli.models.model.pack.ChildModel;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.viewholders.ChildRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/4/2018.
 */

public class ChildAdapter extends RecyclerView.Adapter<ChildRowHolder> {

    private  Context context;
    private ArrayList<ChildModel> childModels;
    private ArrayList<ChildAgeRange> childAgeRanges;

    public ChildAdapter(Context context,ArrayList<ChildModel> childModels) {
        this.childModels = childModels;
        this.context = context;
        childAgeRanges = new ArrayList<>();
        childAgeRanges.add(ChildAgeRange.F0T2);
        childAgeRanges.add(ChildAgeRange.F2T3);
        childAgeRanges.add(ChildAgeRange.F3T4);
        childAgeRanges.add(ChildAgeRange.F4T5);
        childAgeRanges.add(ChildAgeRange.F5T6);
        childAgeRanges.add(ChildAgeRange.F6T7);
        childAgeRanges.add(ChildAgeRange.F7T8);
        childAgeRanges.add(ChildAgeRange.F8T9);
        childAgeRanges.add(ChildAgeRange.F9T10);
        childAgeRanges.add(ChildAgeRange.F10T11);
        childAgeRanges.add(ChildAgeRange.F11T12);
        childAgeRanges.add(ChildAgeRange.F12T13);
    }


    @Override
    public ChildRowHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_child_item, null);
        ChildRowHolder mh = new ChildRowHolder(view);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return mh;
    }

    @Override
    public void onBindViewHolder(final ChildRowHolder holder, int position) {
        if (ValidationTools.isEmptyOrNull(childModels)) {
            return;
        }
        final ChildModel childModel = childModels.get(position);
        holder.child_name.setText(childModel.getTitle());



        ChildAgeRangeAdapter childAgeRangeAdapter = new ChildAgeRangeAdapter(context,android.R.layout.simple_spinner_item,childAgeRanges);
        holder.spn_range.setAdapter(childAgeRangeAdapter);

        holder.spn_range.setSelection(childAgeRanges.indexOf(childModel.getChildAgeRange()));
        holder.spn_range.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                childModel.setChildAgeRange((ChildAgeRange)  holder.spn_range.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != childModels? childModels.size() : 0);
    }
}