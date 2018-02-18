package com.eligasht.reservation.views.dialogs;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.eligasht.R;
import com.eligasht.reservation.models.model.pack.filter.AmenityFilter;
import com.eligasht.reservation.models.model.pack.filter.DegreeFilter;
import com.eligasht.reservation.models.model.pack.filter.HotelTypeFilter;
import com.eligasht.reservation.models.model.pack.filter.PlaceFilter;
import com.eligasht.reservation.models.model.pack.filter.PriceFilter;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.views.adapters.pack.filter.AmenityFilterAdapter;
import com.eligasht.reservation.views.adapters.pack.filter.DegreeFilterAdapter;
import com.eligasht.reservation.views.adapters.pack.filter.HotelTypeFilterAdapter;
import com.eligasht.reservation.views.adapters.pack.filter.PlaceFilterAdapter;
import com.eligasht.reservation.views.adapters.pack.filter.PriceFilterAdapter;
import com.eligasht.reservation.views.components.SimpleRecycleView;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by elham.bonyani on 1/20/18.
 */

public class FilterPackageDialog implements View.OnClickListener {


    private Context context;
    private AlertDialog alertDialog;
    private SimpleRecycleView rcl_place;
    private SimpleRecycleView rcl_price;
    private SimpleRecycleView rcl_degree;
    private SimpleRecycleView rcl_amenity;
    private SimpleRecycleView rcl_hotel_type;
    private OnFiltePackageListener onFiltePackageListener;
    private ArrayList<DegreeFilter> degreeFiltersSelected;
    private ArrayList<PriceFilter> priceFiltersSelected;
    private ArrayList<PlaceFilter> placeFiltersSelected;
    private ArrayList<AmenityFilter> amenityFiltersSelected;
    private ArrayList<HotelTypeFilter> hotelTypeFiltersSelected;
    private FancyButton btnOk;
    private FancyButton btnDeleteFilter;
    private AmenityFilterAdapter amenityFilterAdapter;
    private PriceFilterAdapter priceFilterAdapter;
    private HotelTypeFilterAdapter hotelTypeFilterAdapter;
    private PlaceFilterAdapter placeFilterAdapter;
    private DegreeFilterAdapter degreeFilterAdapter;

    public interface OnFiltePackageListener{

        void onConfirm(ArrayList<DegreeFilter> degreeFiltersSelected,
                       ArrayList<PriceFilter> priceFiltersSelected,
                       ArrayList<PlaceFilter> placeFiltersSelected,
                       ArrayList<HotelTypeFilter> hotelTypeFiltersSelected,
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
        alertDialog.setCancelable(true);
    }

    private void initViews(View view) {
        rcl_degree = view.findViewById(R.id.rcl_degree);
        rcl_price = view.findViewById(R.id.rcl_price);
        rcl_place = view.findViewById(R.id.rcl_place);
        rcl_amenity = view.findViewById(R.id.rcl_amenity);
        rcl_hotel_type = view.findViewById(R.id.rcl_hotel_type);
        btnOk = view.findViewById(R.id.btnOk);
        btnDeleteFilter = view.findViewById(R.id.btnDeletFilter);

        rcl_degree.setLayoutManager( new GridLayoutManager(context, 2));
        rcl_price.setLayoutManager(new LinearLayoutManager(context));
        rcl_place.setLayoutManager(new LinearLayoutManager(context));
        rcl_amenity.setLayoutManager(new LinearLayoutManager(context));
        rcl_hotel_type.setLayoutManager(new LinearLayoutManager(context));

        rcl_place.setNestedScrollingEnabled(false);
        rcl_price.setNestedScrollingEnabled(false);
        rcl_degree.setNestedScrollingEnabled(false);
        rcl_amenity.setNestedScrollingEnabled(false);
        rcl_hotel_type.setNestedScrollingEnabled(false);

        rcl_degree.hideLoading();
        rcl_hotel_type.hideLoading();
        rcl_price.hideLoading();
        rcl_place.hideLoading();
        rcl_amenity.hideLoading();

        rcl_degree.setVisibility(View.GONE);
        rcl_hotel_type.setVisibility(View.GONE);
        rcl_price.setVisibility(View.GONE);
        rcl_place.setVisibility(View.GONE);
        rcl_amenity.setVisibility(View.GONE);

        btnOk.setOnClickListener(this);
        btnDeleteFilter.setOnClickListener(this);
        btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnDeleteFilter.setCustomTextFont("fonts/iran_sans_normal.ttf");
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

        priceFilterAdapter = new PriceFilterAdapter(context,priceFilters).setOnPriceFilterListener(new PriceFilterAdapter.OnPriceFilterListener() {
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

        placeFilterAdapter = new PlaceFilterAdapter(context,placeFilters).setOnPlaceFilterListener(new PlaceFilterAdapter.OnPlaceFilterListener() {
            @Override
            public void onChangeFilters(ArrayList<PlaceFilter> placeFiltersSelected) {
                FilterPackageDialog.this.placeFiltersSelected = placeFiltersSelected;
            }
        });
        rcl_place.showList(placeFilterAdapter);
    }

    public void setHotelTypess(ArrayList<HotelTypeFilter> hotelTypeFilters){
        if(ValidationTools.isEmptyOrNull(hotelTypeFilters)){
            rcl_hotel_type.setVisibility(View.GONE);
            return;
        }

        rcl_hotel_type.setVisibility(View.VISIBLE);

        hotelTypeFilterAdapter = new HotelTypeFilterAdapter(context,hotelTypeFilters).setOnHotelTypeFilterListener(new HotelTypeFilterAdapter.OnHotelTypeFilterListener() {
            @Override
            public void onChangeFilters(ArrayList<HotelTypeFilter> hotelTypeFiltersSelected) {
                FilterPackageDialog.this.hotelTypeFiltersSelected = hotelTypeFiltersSelected;
            }
        });
        rcl_hotel_type.showList(hotelTypeFilterAdapter);
    }

    public void setDegrees(ArrayList<DegreeFilter> degreeFilters){
        if(ValidationTools.isEmptyOrNull(degreeFilters)){
            rcl_degree.setVisibility(View.GONE);
            return;
        }

        rcl_degree.setVisibility(View.VISIBLE);

        degreeFilterAdapter = new DegreeFilterAdapter(context,degreeFilters).setOnDegreeFilterListener(new DegreeFilterAdapter.OnDegreeFilterListener() {
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

        amenityFilterAdapter = new AmenityFilterAdapter(context,amenitieFilters).setOnAmenityFilterListener(new AmenityFilterAdapter.OnAmenityFilterListener() {
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
                    onFiltePackageListener.onConfirm(degreeFiltersSelected,
                            priceFiltersSelected,
                            placeFiltersSelected,
                            hotelTypeFiltersSelected,
                            amenityFiltersSelected);
                    dismiss();
                }
                break;
            case R.id.btnDeletFilter :

                if(amenityFilterAdapter != null){
                    amenityFilterAdapter.removeFilter();
                }

                if(hotelTypeFilterAdapter != null){
                    hotelTypeFilterAdapter.removeFilter();
                }

                if(placeFilterAdapter != null){
                    placeFilterAdapter.removeFilter();
                }

                if(priceFilterAdapter != null){
                    priceFilterAdapter.removeFilter();
                }

                if(degreeFilterAdapter != null){
                    degreeFilterAdapter.removeFilter();
                }

                if(onFiltePackageListener != null){
                    onFiltePackageListener.onConfirm(degreeFiltersSelected,
                            priceFiltersSelected,
                            placeFiltersSelected,
                            hotelTypeFiltersSelected,
                            amenityFiltersSelected);
                    dismiss();
                }
                break;
        }
    }
}
