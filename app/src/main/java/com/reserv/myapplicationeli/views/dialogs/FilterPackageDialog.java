package com.reserv.myapplicationeli.views.dialogs;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.pack.filter.AmenityFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.DegreeFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.PlaceFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.PriceFilter;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.adapters.pack.filter.AmenityFilterAdapter;
import com.reserv.myapplicationeli.views.adapters.pack.filter.DegreeFilterAdapter;
import com.reserv.myapplicationeli.views.adapters.pack.filter.PlaceFilterAdapter;
import com.reserv.myapplicationeli.views.adapters.pack.filter.PriceFilterAdapter;
import com.reserv.myapplicationeli.views.components.SimpleRecycleView;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by hoseinraeisi on 1/20/18.
 */

public class FilterPackageDialog implements View.OnClickListener {


    private Context context;
    private AlertDialog alertDialog;
    private SimpleRecycleView rcl_place;
    private SimpleRecycleView rcl_price;
    private SimpleRecycleView rcl_degree;
    private SimpleRecycleView rcl_amenity;
    private OnFiltePackageListener onFiltePackageListener;
    private ArrayList<DegreeFilter> degreeFiltersSelected;
    private ArrayList<PriceFilter> priceFiltersSelected;
    private ArrayList<PlaceFilter> placeFiltersSelected;
    private ArrayList<AmenityFilter> amenityFiltersSelected;
    private FancyButton btnOk;

    public interface OnFiltePackageListener{

        void onConfirm(ArrayList<DegreeFilter> degreeFiltersSelected,
                       ArrayList<PriceFilter> priceFiltersSelected,
                       ArrayList<PlaceFilter> placeFiltersSelected,
                       ArrayList<AmenityFilter> amenityFiltersSelected);
    }

    public FilterPackageDialog(Context context) {

        if(context == null){
            return;
        }
        this.context = context;
        alertDialog = new AlertDialog.Builder(context).create();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_filter_package_dialog, null);
        initViews(view);
        alertDialog.setView(view);
        alertDialog.setCancelable(false);
    }

    private void initViews(View view) {
        rcl_degree = view.findViewById(R.id.rcl_degree);
        rcl_price = view.findViewById(R.id.rcl_price);
        rcl_place = view.findViewById(R.id.rcl_place);
        rcl_amenity = view.findViewById(R.id.rcl_amenity);
        btnOk = view.findViewById(R.id.btnOk);

        rcl_degree.setLayoutManager( new GridLayoutManager(context, 2));
        rcl_price.setLayoutManager(new LinearLayoutManager(context));
        rcl_place.setLayoutManager(new LinearLayoutManager(context));
        rcl_amenity.setLayoutManager(new LinearLayoutManager(context));

        rcl_place.setNestedScrollingEnabled(false);
        rcl_price.setNestedScrollingEnabled(false);
        rcl_degree.setNestedScrollingEnabled(false);
        rcl_amenity.setNestedScrollingEnabled(false);

        rcl_degree.hideLoading();
        rcl_price.hideLoading();
        rcl_place.hideLoading();
        rcl_amenity.hideLoading();

        rcl_degree.setVisibility(View.GONE);
        rcl_price.setVisibility(View.GONE);
        rcl_place.setVisibility(View.GONE);
        rcl_amenity.setVisibility(View.GONE);

        btnOk.setOnClickListener(this);
    }


    public void show() {
        if(alertDialog != null && !alertDialog.isShowing()){
            alertDialog.show();
        }
    }

    public void dismiss() {
        if(alertDialog != null && alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }

    public void setPrices(ArrayList<PriceFilter> priceFilters){
        if(ValidationTools.isEmptyOrNull(priceFilters)){
            rcl_price.setVisibility(View.GONE);
            return;
        }

        rcl_price.setVisibility(View.VISIBLE);

        PriceFilterAdapter priceFilterAdapter = new PriceFilterAdapter(context,priceFilters).setOnPriceFilterListener(new PriceFilterAdapter.OnPriceFilterListener() {
            @Override
            public void onChangeFilters(ArrayList<PriceFilter> priceFiltersSelected) {
                FilterPackageDialog.this.priceFiltersSelected = priceFiltersSelected;
            }
        });
        rcl_price.showList(priceFilterAdapter);
    }

    public void setPlaces(ArrayList<PlaceFilter> placeFilters){
        if(ValidationTools.isEmptyOrNull(placeFilters)){
            rcl_place.setVisibility(View.GONE);
            return;
        }

        rcl_place.setVisibility(View.VISIBLE);

        PlaceFilterAdapter placeFilterAdapter = new PlaceFilterAdapter(context,placeFilters).setOnPlaceFilterListener(new PlaceFilterAdapter.OnPlaceFilterListener() {
            @Override
            public void onChangeFilters(ArrayList<PlaceFilter> placeFiltersSelected) {
                FilterPackageDialog.this.placeFiltersSelected = placeFiltersSelected;
            }
        });
        rcl_place.showList(placeFilterAdapter);
    }

    public void setDegrees(ArrayList<DegreeFilter> degreeFilters){
        if(ValidationTools.isEmptyOrNull(degreeFilters)){
            rcl_degree.setVisibility(View.GONE);
            return;
        }

        rcl_degree.setVisibility(View.VISIBLE);

        DegreeFilterAdapter degreeFilterAdapter = new DegreeFilterAdapter(context,degreeFilters).setOnDegreeFilterListener(new DegreeFilterAdapter.OnDegreeFilterListener() {
            @Override
            public void onChangeFilters(ArrayList<DegreeFilter> degreeFiltersSelected) {
                FilterPackageDialog.this.degreeFiltersSelected = degreeFiltersSelected;
            }
        });
        rcl_degree.showList(degreeFilterAdapter);
    }

    public void setAmenities(ArrayList<AmenityFilter> amenitieFilters){
        if(ValidationTools.isEmptyOrNull(amenitieFilters)){
            rcl_degree.setVisibility(View.GONE);
            return;
        }

        rcl_amenity.setVisibility(View.VISIBLE);

        AmenityFilterAdapter amenityFilterAdapter = new AmenityFilterAdapter(context,amenitieFilters).setOnAmenityFilterListener(new AmenityFilterAdapter.OnAmenityFilterListener() {
            @Override
            public void onChangeFilters(ArrayList<AmenityFilter> amenityFiltersSelected) {
                FilterPackageDialog.this.amenityFiltersSelected = amenityFiltersSelected;
            }
        });
        rcl_amenity.showList(amenityFilterAdapter);
    }

    public void setOnFiltePackageListener(OnFiltePackageListener onFiltePackageListener){
        this.onFiltePackageListener = onFiltePackageListener;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnOk:
                if(onFiltePackageListener != null){
                    onFiltePackageListener.onConfirm(degreeFiltersSelected,priceFiltersSelected,placeFiltersSelected,amenityFiltersSelected);
                    dismiss();
                }
                break;
        }
    }
}