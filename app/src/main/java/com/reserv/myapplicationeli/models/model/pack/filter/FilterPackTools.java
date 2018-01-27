package com.reserv.myapplicationeli.models.model.pack.filter;

import com.reserv.myapplicationeli.models.model.pack.LstHotelAmenity;
import com.reserv.myapplicationeli.models.model.pack.LstProwHotel;
import com.reserv.myapplicationeli.models.model.pack.LstProwPrice;
import com.reserv.myapplicationeli.models.model.pack.PRowXfer;
import com.reserv.myapplicationeli.tools.ValidationTools;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/20/18.
 */

public class FilterPackTools {


    public static ArrayList<PriceFilter> getPriceFilters(ArrayList<PRowXfer> pRowXfers) {
        ArrayList<PriceFilter> priceFilters = new ArrayList<>();
        if (ValidationTools.isEmptyOrNull(pRowXfers)) {
            return priceFilters;
        }

        int maxSumPrice = getMaxSumPrice(pRowXfers);
        int minSumPrice = getMinSumPrice(pRowXfers);

        int rang = (maxSumPrice - minSumPrice) / 5;
        for (int i = minSumPrice; i < maxSumPrice; i += rang) {
            priceFilters.add(new PriceFilter(i, i + rang));
        }
        return priceFilters;
    }

    public static int getMaxSumPrice(ArrayList<PRowXfer> pRowXfers) {
        if (ValidationTools.isEmptyOrNull(pRowXfers)) {
            return 0;
        }

        int max = 0;

        for (PRowXfer pRowXfer : pRowXfers) {
            if (pRowXfer.getSumPrice() > max) {
                max = pRowXfer.getSumPrice();
            }
        }
        return max;
    }

    public static int getMinSumPrice(ArrayList<PRowXfer> pRowXfers) {
        if (ValidationTools.isEmptyOrNull(pRowXfers)) {
            return 0;
        }

        int min = pRowXfers.get(0).getSumPrice();

        for (PRowXfer pRowXfer : pRowXfers) {
            if (pRowXfer.getSumPrice() < min) {
                min = pRowXfer.getSumPrice();
            }
        }
        return min;
    }

    public static ArrayList<DegreeFilter> getDegreeFilters(ArrayList<PRowXfer> pRowXfers){
        ArrayList<DegreeFilter> degreeFilters = new ArrayList<>();

        degreeFilters.add(new DegreeFilter(1));
        degreeFilters.add(new DegreeFilter(2));
        degreeFilters.add(new DegreeFilter(3));
        degreeFilters.add(new DegreeFilter(4));
        degreeFilters.add(new DegreeFilter(5));

        return degreeFilters;
    }

    public static ArrayList<PlaceFilter> getPlaceFilters(ArrayList<PRowXfer> pRowXfers){
        ArrayList<PlaceFilter> placeFilters = new ArrayList<>();
        if (ValidationTools.isEmptyOrNull(pRowXfers)) {
            return placeFilters;
        }

        for(PRowXfer pRowXfer : pRowXfers){
            for (LstProwHotel lstProwHotel : pRowXfer.getLstProwHotels()){
                if(!isExistPlaceFilter(placeFilters,lstProwHotel.getLocationID())){
                    placeFilters.add(new PlaceFilter(lstProwHotel.getLocationID(),
                            ValidationTools.isEmptyOrNull(lstProwHotel.getLocationFullNameFa())?lstProwHotel.getLocationFullNameEn():lstProwHotel.getLocationFullNameFa()));
                }
            }
        }

        return placeFilters;
    }

    private static boolean isExistPlaceFilter(ArrayList<PlaceFilter> placeFilters ,int id){
        if (ValidationTools.isEmptyOrNull(placeFilters)) {
            return false;
        }

        for (PlaceFilter placeFilter : placeFilters){
            if(placeFilter.getLocationId() == id){
                return true;
            }
        }

        return false;
    }


    public static ArrayList<HotelTypeFilter> getHotelTypeFilters(ArrayList<PRowXfer> pRowXfers){
        ArrayList<HotelTypeFilter> hotelTypeFilters = new ArrayList<>();
        if (ValidationTools.isEmptyOrNull(pRowXfers)) {
            return hotelTypeFilters;
        }

        for(PRowXfer pRowXfer : pRowXfers){
            for (LstProwHotel lstProwHotel : pRowXfer.getLstProwHotels()){
                if(!isExistHotelTypeFilter(hotelTypeFilters,lstProwHotel.getHTypeNameE(),lstProwHotel.getHTypeNameF())){
                    hotelTypeFilters.add(new HotelTypeFilter(lstProwHotel.getHTypeNameE(),lstProwHotel.getHTypeNameF()));
                }
            }
        }

        return hotelTypeFilters;
    }

    private static boolean isExistHotelTypeFilter(ArrayList<HotelTypeFilter> hotelTypeFilters ,String hTypeNameE, String hTypeNameF) {
        if(ValidationTools.isEmptyOrNull(hotelTypeFilters)){
            return false;
        }

        for(HotelTypeFilter hotelTypeFilter : hotelTypeFilters){
            if(hotelTypeFilter.getHotelTypeNameEn().equals(hTypeNameE) && hotelTypeFilter.getHotelTypeNameFa().equals(hTypeNameF)){
                return true;
            }
        }

        return false;
    }


    public static ArrayList<AmenityFilter> getAmenityFilters(ArrayList<PRowXfer> pRowXfers){
        ArrayList<AmenityFilter> amenityFilters = new ArrayList<>();
        if (ValidationTools.isEmptyOrNull(pRowXfers)) {
            return amenityFilters;
        }

        for(PRowXfer pRowXfer : pRowXfers){
            for (LstHotelAmenity lstHotelAmenity : pRowXfer.getLstHotelAmenity()){
                if(!isExistAmenityFilter(amenityFilters,lstHotelAmenity.getAmenityID())){
                    amenityFilters.add(new AmenityFilter(lstHotelAmenity));
                }
            }
        }

        return amenityFilters;
    }

    private static boolean isExistAmenityFilter(ArrayList<AmenityFilter> amenityFilters ,int id){
        if (ValidationTools.isEmptyOrNull(amenityFilters)) {
            return false;
        }

        for (AmenityFilter amenityFilter : amenityFilters){
            if(amenityFilter.getLstHotelAmenity().getAmenityID() == id){
                return true;
            }
        }

        return false;
    }
}
