package com.reserv.myapplicationeli.views.adapters.pack;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.pack.LstHotelAmenity;
import com.reserv.myapplicationeli.models.model.pack.LstProwHotel;
import com.reserv.myapplicationeli.models.model.pack.LstProwPrice;
import com.reserv.myapplicationeli.models.model.pack.PRowXfer;
import com.reserv.myapplicationeli.models.model.pack.filter.AmenityFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.DegreeFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.HotelTypeFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.PlaceFilter;
import com.reserv.myapplicationeli.models.model.pack.filter.PriceFilter;
import com.reserv.myapplicationeli.tools.StreamList;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.activities.pack.PackageServicesActivity;
import com.reserv.myapplicationeli.views.components.stickyheaders.Section;
import com.reserv.myapplicationeli.views.viewholders.PRowXferRowHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by elham.bonyani on 1/6/2018.
 * list of all the pack
 */

public class PRowXferAdapter extends RecyclerView.Adapter<PRowXferRowHolder> {

    private Context context;
    private ArrayList<PRowXfer> feedItemList;
    private ArrayList<PRowXfer> filtertemList;
    private ListenerSearchPackAdapter listenerPackAdapter;
    private int type = 2;

    public PRowXferAdapter(Context context, ArrayList<PRowXfer> NameItem) {

        this.context = context;
        this.feedItemList = NameItem;
        filtertemList = new ArrayList<>(feedItemList);
    }

    public interface ListenerSearchPackAdapter {
        void onClickPackageBookingItem(PRowXfer pack);
        void onFilterListChange(ArrayList<PRowXfer> filtertemList);
    }

    public PRowXferAdapter setListener(ListenerSearchPackAdapter listener) {
        this.listenerPackAdapter = listener;
        return this;
    }

    @Override
    public PRowXferRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_list_pack_item, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PRowXferRowHolder(view);
    }

    @Override
    public void onBindViewHolder(PRowXferRowHolder holder, final int position) {
        final PRowXfer item = filtertemList.get(position);

        Animation scaleUp = AnimationUtils.loadAnimation(context, R.anim.anim_list);
        holder.list_pack .startAnimation(scaleUp);

        if(item.getLstProwPriceAdapter() == null){
            List<String> list = StreamList.convertAll(item.getLstProwPrices(), new StreamList.Function<LstProwPrice, String>() {
                @Override
                public String apply(LstProwPrice lstProwPrice) {
                    return String.valueOf(lstProwPrice.getRoomNo());
                }
            });
            ArrayList<Section> sections = new ArrayList<>();
            for (String header : list) {
                Section section = new Section();
                section.setTitle(header);

                for (LstProwPrice lstProwPrice : item.getLstProwPrices()) {
                    if (header.equals(String.valueOf(lstProwPrice.getRoomNo()))) {
                        section.setList(lstProwPrice);
                    }
                }
                if (!ValidationTools.isEmptyOrNull(section.getList())) {
                    sections.add(section);
                }
            }
            item.setLstProwPriceAdapter(new LstProwPriceAdapter(context, sections));
        }

        if(item.getLstProwHotelAdapter() == null){
            item.setLstProwHotelAdapter(new LstProwHotelAdapter(context, item.getLstProwHotels()));
        }


        holder.rcl_price.showList(item.getLstProwPriceAdapter());

        holder.rcl_hotels.showList(item.getLstProwHotelAdapter());

        holder.time_depart_go.setText(item.getXferList().getXFlightsList().get(0).getFltLocalTime());
        holder.time_arive_go.setText(item.getXferList().getXFlightsList().get(0).getFltCheckinTime());
        holder.time_depart_back.setText(item.getXferList().getXFlightsList().get(1).getFltLocalTime());
        holder.time_arive_back.setText(item.getXferList().getXFlightsList().get(1).getFltCheckinTime());

        holder.txt_airline_go.setText(item.getXferList().getXFlightsList().get(0).getAirlineEnglishName());
        holder.txt_airline_back.setText(item.getXferList().getXFlightsList().get(1).getAirlineEnglishName());

//        holder.txt_economi.setText(item.getXferList().getXFlightsList().get(0).getSeatClassNameFa());
        if (item.getXferList().getXFlightsList().get(0).getAvailable().equals("Available") || item.getXferList().getXFlightsList().get(0).getAvailable().equals("")) {

        } else {
          //  holder.txt_count.setText(item.getXferList().getXFlightsList().get(0).getAvailable());
        }
        holder.country_depart_go.setText((ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(0).getDepartureCityName()) ? item.getXferList().getXFlightsList().get(0).getDepartureCityName() : item.getXferList().getXFlightsList().get(0).getDepartureCityName()));
        holder.country_arive_go.setText( (ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(0).getArrivalCityName()) ? item.getXferList().getXFlightsList().get(0).getArrivalCityName() : item.getXferList().getXFlightsList().get(0).getArrivalCityName()));
        holder.country_depart_back.setText((ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(1).getDepartureCityName()) ? item.getXferList().getXFlightsList().get(1).getDepartureCityName() : item.getXferList().getXFlightsList().get(1).getDepartureCityName()));
        holder.country_arive_back.setText( (ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(1).getArrivalCityName()) ? item.getXferList().getXFlightsList().get(1).getArrivalCityName() : item.getXferList().getXFlightsList().get(1).getArrivalCityName()));
        //holder.date_arrive.setText(ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(0).getShamsi())? item.getXferList().getXFlightsList().get(0).getShamsi() : item.getXferList().getXFlightsList().get(0).getShamsi());
        //holder.date_arrive.setText(ValidationTools.isEmptyOrNull(item.getXferList().getXFlightsList().get(1).getShamsi())? item.getXferList().getXFlightsList().get(1).getShamsi() : item.getXferList().getXFlightsList().get(1).getShamsi());

//        Glide.with(context)
//                .load("http://www.eligasht.com/Content/AirLine/" + item.getXferList().getXFlightsList().get(0).getAirlineCode() + ".png")
//                .into(holder.img_airLine);

        holder.btnServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PackageServicesActivity.class);
              intent.putExtra("services",new Gson().toJson(item.getLstProwServices()));
                context.startActivity(intent);
            }
        });

        holder.btn_package_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenerPackAdapter != null) {
                    listenerPackAdapter.onClickPackageBookingItem(item);
                    return;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (filtertemList == null ? 0 : filtertemList.size());
    }


    public void filter(ArrayList<DegreeFilter> degreeFiltersSelected,
                       ArrayList<PriceFilter> priceFiltersSelected,
                       ArrayList<PlaceFilter> placeFiltersSelected,
                       ArrayList<HotelTypeFilter> hotelTypeFiltersSelected,
                       ArrayList<AmenityFilter> amenityFiltersSelected){


        filtertemList = new ArrayList<>(feedItemList);
        ArrayList<PRowXfer> filterDegreeList = new ArrayList<>();
        ArrayList<PRowXfer> filterPriceList = new ArrayList<>();
        ArrayList<PRowXfer> filterPlaceList = new ArrayList<>();
        ArrayList<PRowXfer> filterHotelTypeList = new ArrayList<>();
        ArrayList<PRowXfer> filterAmenityList = new ArrayList<>();

        if(!ValidationTools.isEmptyOrNull(degreeFiltersSelected)){

            for(DegreeFilter degreeFilter : degreeFiltersSelected){
                for(PRowXfer pRowXfer : feedItemList){
                    for(LstProwHotel lstProwHotel : pRowXfer.getLstProwHotels()){
                        if(Integer.parseInt(lstProwHotel.getHotelStarRating().split("\\*")[0]) == degreeFilter.getStar()){
                            filterDegreeList.add(pRowXfer);
                        }
                    }
                }
            }
            filtertemList = filterDegreeList;
        }


        if(!ValidationTools.isEmptyOrNull(priceFiltersSelected)){
            for(PriceFilter priceFilter : priceFiltersSelected){
                for(PRowXfer pRowXfer : filtertemList){
                    if(pRowXfer.getSumPrice() <= priceFilter.getMaxPrice() && pRowXfer.getSumPrice() >= priceFilter.getMinPrice()){
                        if(!isExistPack(filterPriceList,pRowXfer)){
                            filterPriceList.add(pRowXfer);
                        }
                    }
                }
            }
            filtertemList = filterPriceList;
        }


        if(!ValidationTools.isEmptyOrNull(placeFiltersSelected)){
            for(PlaceFilter placeFilter : placeFiltersSelected){
                for(PRowXfer pRowXfer : filtertemList){
                    for(LstProwHotel lstProwHotel :pRowXfer.getLstProwHotels()){
                        if(lstProwHotel.getLocationID() == placeFilter.getLocationId()){
                            filterPlaceList.add(pRowXfer);
                        }
                    }
                }
            }
            filtertemList = filterPlaceList;
        }

        if(!ValidationTools.isEmptyOrNull(hotelTypeFiltersSelected)){
            for(HotelTypeFilter hotelTypeFilter : hotelTypeFiltersSelected){
                for(PRowXfer pRowXfer : filtertemList){
                    for(LstProwHotel lstProwHotel :pRowXfer.getLstProwHotels()){
                        if(lstProwHotel.getHTypeNameE().equals(hotelTypeFilter.getHotelTypeNameEn()) && lstProwHotel.getHTypeNameF().equals(hotelTypeFilter.getHotelTypeNameFa())){
                            filterHotelTypeList.add(pRowXfer);
                        }
                    }
                }
            }
            filtertemList = filterHotelTypeList;
        }

        if(!ValidationTools.isEmptyOrNull(amenityFiltersSelected)){
            for(AmenityFilter amenityFilter : amenityFiltersSelected){
                for(PRowXfer pRowXfer : filtertemList){
                    for(LstHotelAmenity lstHotelAmenity :pRowXfer.getLstHotelAmenity()){
                        if(lstHotelAmenity.getAmenityID() == amenityFilter.getLstHotelAmenity().getAmenityID()){
                            filterAmenityList.add(pRowXfer);
                        }
                    }
                }
            }
            filtertemList = filterAmenityList;
        }

        if(listenerPackAdapter != null){
            listenerPackAdapter.onFilterListChange(filtertemList);
        }
        sort(type);
    }


    public void sort(int type){
        this.type = type;
        switch (type) {
            case 1:
                Collections.sort(filtertemList, new Comparator<PRowXfer>() {
                    @Override
                    public int compare(PRowXfer p1, PRowXfer p2) {
                        return Integer.valueOf(p2.getSumPrice()) - Integer.valueOf(p1.getSumPrice()); // Ascending
                    }
                });
                notifyDataSetChanged();

                break;
            case 2:
                Collections.sort(filtertemList, new Comparator<PRowXfer>() {
                    @Override
                    public int compare(PRowXfer p1, PRowXfer p2) {
                        return Integer.valueOf(p1.getSumPrice()) - Integer.valueOf(p2.getSumPrice()); // Ascending
                    }
                });
                notifyDataSetChanged();

                break;


        }

    }
    private boolean isExistPack(ArrayList<PRowXfer> filterPriceList, PRowXfer pRowXfer) {
        if(ValidationTools.isEmptyOrNull(filterPriceList)){
            return false;
        }
        for (PRowXfer p : filterPriceList){
            if(p.getPackID() == pRowXfer.getPackID()){
                return true;
            }
        }
        return false;
    }
}



