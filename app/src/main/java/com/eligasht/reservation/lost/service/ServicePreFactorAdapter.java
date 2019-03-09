package com.eligasht.reservation.lost.service;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.ServicePreFactorModel;
import com.eligasht.reservation.models.train.ServicePreFactorTrainModel;
import com.eligasht.reservation.tools.Utility;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.List;

/**
 * Created by Reza.nejati on 1/23/2018.
 */

public class ServicePreFactorAdapter extends RecyclerView.Adapter<ServicePreFactorAdapter.ViewHolder> {

    private  List<ServicePreFactorModel> data = null;
    private List<ServicePreFactorTrainModel> train_data= null;
    private Context context;
    private boolean flagTrain;
    private SparseBooleanArray expandState = new SparseBooleanArray();


    public ServicePreFactorAdapter(final List<ServicePreFactorModel> data) {
        this.train_data =null;
        this.data = data;
        for (int i = 0; i < data.size(); i++) {
            expandState.append(i, false);
        }
    }
    public ServicePreFactorAdapter(final List<ServicePreFactorTrainModel> train_data,boolean flagTrain) {
        this.data =null;
        this.train_data = train_data;
        this.flagTrain = flagTrain;
        for (int i = 0; i < train_data.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public ServicePreFactorAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        ServicePreFactorAdapter.ViewHolder view;
        if (flagTrain) {
            view= new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_list_row_service_train, parent, false));
            return  view;
        }else{
            view= new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_list_row_service, parent, false));
            return  view;
        }



    }


    @Override
    public void onBindViewHolder(final ServicePreFactorAdapter.ViewHolder holder, final int position) {
        if (flagTrain) {
            setDataServiceTrain(holder,position);

        }else{
            setDataService(holder,position);
        }

    }

    private void setDataServiceTrain(ViewHolder holder, int position) {
        final ServicePreFactorTrainModel item = train_data.get(position);
        holder.setIsRecyclable(false);
        holder.tvServicName.setText(item.getServiceNameEn());
        holder.tvServiceFa.setText(item.getServiceNameFa());
        holder.tvCityName.setText(item.getCityFa());
        holder.tvTypeService.setText(item.getServiceType());

        holder.tvCountPass.setText(item.getCountPass());
        holder.tvDescription.setText(item.getDescription());

        if(item.getServicePrice().equals("0"))
            holder.tvPrice.setText(R.string.calculated);
        else
            holder.tvPrice.setText(Utility.priceFormat(item.getServicePrice())+"");

        if (item.getServiceNameFa().contains("بیمه")) {
            holder.tvServiceCityUi.setText(context.getString(R.string.country));
            holder.tvCityName.setVisibility(View.VISIBLE);

        } else {

            holder.tvServiceCityUi.setVisibility(View.VISIBLE);
            holder.tvServiceCityUi.setText(context.getString(R.string.city));

            holder.tvCityName.setVisibility(View.VISIBLE);


        }

        holder.expandableLayout.setInRecyclerView(true);

        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                createRotateAnimator(holder.tvArrow, 0f, 180f).start();
                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
                createRotateAnimator(holder.tvArrow, 180f, 0f).start();
                expandState.put(position, false);
            }
        });

        holder.tvArrow.setRotation(expandState.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandableLayout);
            }
        });
    }

    private void setDataService(ViewHolder holder, int position) {
        final ServicePreFactorModel item = data.get(position);
        holder.setIsRecyclable(false);
        holder.tvServicName.setText(item.getServiceNameEn());
        holder.tvServiceFa.setText(item.getServiceNameFa());
        holder.tvCityName.setText(item.getCityFa());
        holder.tvTypeService.setText(item.getServiceType());

        if(item.getServicePrice().equals("0"))
            holder.tvPrice.setText(R.string.calculated);
        else
            holder.tvPrice.setText(Utility.priceFormat(item.getServicePrice())+"");

        if (item.getServiceNameFa().contains("بیمه")) {
            holder.tvServiceCityUi.setText(context.getString(R.string.country));
            holder.tvCityName.setVisibility(View.VISIBLE);

        } else {

            holder.tvServiceCityUi.setVisibility(View.VISIBLE);
            holder.tvServiceCityUi.setText(context.getString(R.string.city));

            holder.tvCityName.setVisibility(View.VISIBLE);


        }


        // holder.itemView.setBackgroundColor(ContextCompat.getColor(context, item.colorId1));
        holder.expandableLayout.setInRecyclerView(true);
        // holder.expandableLayout.setBackgroundColor(ContextCompat.getColor(context, item.colorId2));
        // holder.expandableLayout.setInterpolator(item.interpolator);
        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                createRotateAnimator(holder.tvArrow, 0f, 180f).start();
                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
                createRotateAnimator(holder.tvArrow, 180f, 0f).start();
                expandState.put(position, false);
            }
        });

        holder.tvArrow.setRotation(expandState.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandableLayout);
            }
        });
    }


    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }

    @Override
    public int getItemCount() {
        if (flagTrain) {
            return train_data.size();
        }else{
            return data.size();
        }
    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvServicName, tvServiceFa, tvCityName, tvTypeService, tvArrow, tvServiceCityUi,tvPrice,tvDescription,tvCountPass;
        /**
         * You must use the ExpandableLinearLayout in the recycler view.
         * The ExpandableRelativeLayout doesn't work.
         */
        public ExpandableLinearLayout expandableLayout;
        //  public RelativeLayout tvArrow;
        RelativeLayout buttonLayout;

        public ViewHolder(View v) {
            super(v);
            tvDescription = v.findViewById(R.id.tvDescription);
            tvCountPass = v.findViewById(R.id.tvCountPass);

            tvServicName = v.findViewById(R.id.tvServicName);
            tvServiceFa = v.findViewById(R.id.tvServiceFa);
            tvCityName = v.findViewById(R.id.tvCityName);
            tvTypeService = v.findViewById(R.id.tvTypeService);
            tvPrice = v.findViewById(R.id.tvPrice);
            expandableLayout = v.findViewById(R.id.expandableLayout);
            tvArrow = v.findViewById(R.id.tvArrow);
            buttonLayout = v.findViewById(R.id.buttonLayout);
            tvServiceCityUi = v.findViewById(R.id.tvServiceCityUi);
        }
    }

}
