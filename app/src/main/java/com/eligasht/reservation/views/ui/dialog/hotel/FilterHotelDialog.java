package com.eligasht.reservation.views.ui.dialog.hotel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ScrollView;

import com.eligasht.R;
import com.eligasht.reservation.models.hotel.FilterPriceModel;
import com.eligasht.reservation.models.hotel.adapter.FilterModel;
import com.eligasht.reservation.models.hotel.adapter.FilterStarModel;
import com.eligasht.reservation.views.adapters.hotel.FilterHotelTypeAdapter;
import com.eligasht.reservation.views.adapters.hotel.PriceFilterAdapter;
import com.eligasht.reservation.views.adapters.hotel.StarFilterAdapter;
import com.eligasht.reservation.views.adapters.hotel.rooms.NonScrollListView;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

@SuppressLint("ValidFragment")
public class FilterHotelDialog extends DialogFragment implements View.OnClickListener {
    android.app.AlertDialog dialog;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnDeletFilter;
    FilterHotelDialogListenerArray filterHotelDialogListenerArray;
    ArrayList<FilterModel> filter;

    NonScrollListView lvHotelTypes, lvFacilitiesTypes, lvPrice, lvLocationTypes, lvBestOff, lvStar;
    FilterHotelTypeAdapter filterHotelTypeAdapter, filterHotelFacilitiesAdapter, filterHotelLocationAdapter, lvBestOffAdapter;
    ArrayList<FilterHotelTypeModel> filterHotelTypeModels, filterHotelFacilitiesModels, filterHotelLocationModels;
    ArrayList<FilterPriceModel> filterHotelPriceModel;
    PriceFilterAdapter priceFilterAdapter;
    StarFilterAdapter starFilterAdapter;
    String search;
    ScrollView scrollViewObject;
    EditText searchtxt;
    boolean remove = false;
    private ArrayList<FilterHotelTypeModel> filterHotelBestOffModels = new ArrayList<>();
    private ArrayList<FilterStarModel> filterHotelStarsModels = new ArrayList<>();

    public static FilterHotelDialog newInstance(final Context activity, ArrayList<FilterModel> filter,
                                                FilterHotelDialogListenerArray filterHotelDialogListenerArray, final ArrayList<FilterHotelTypeModel> filterHotelTypeModels,
                                                final ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels, final ArrayList<FilterPriceModel> filterHotelPriceModel,
                                                String search, final ArrayList<FilterHotelTypeModel> filterHotelLocationModels, final ArrayList<FilterHotelTypeModel> filterHotelBestOffModels
            , final ArrayList<FilterStarModel> filterHotelStarsModels) {
        FilterHotelDialog ret = new FilterHotelDialog();
        ret.initialize(activity,filter,filterHotelDialogListenerArray, filterHotelTypeModels, filterHotelFacilitiesModels, filterHotelPriceModel,
                search, filterHotelLocationModels, filterHotelBestOffModels, filterHotelStarsModels);
        return ret;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.filter_dialog_r, container,
                false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        btnOk = rootView.findViewById(R.id.btnOk);
        scrollViewObject = rootView.findViewById(R.id.scrollViewObject);
        btnDeletFilter = rootView.findViewById(R.id.btnDeletFilter);
        searchtxt = rootView.findViewById(R.id.searchtxt);

        searchtxt.setText(search);
        lvHotelTypes = rootView.findViewById(R.id.lvHotelTypes);
        lvFacilitiesTypes = rootView.findViewById(R.id.lvFacilitiesTypes);
        lvLocationTypes = rootView.findViewById(R.id.lvLocationTypes);
        lvPrice = rootView.findViewById(R.id.lvPrice);
        lvBestOff = rootView.findViewById(R.id.lvBestOff);
        lvStar = rootView.findViewById(R.id.lvStar);

        filterHotelTypeAdapter = new FilterHotelTypeAdapter(filterHotelTypeModels, activity);
        filterHotelFacilitiesAdapter = new FilterHotelTypeAdapter(filterHotelFacilitiesModels, activity);
        filterHotelLocationAdapter = new FilterHotelTypeAdapter(filterHotelLocationModels, activity);
        priceFilterAdapter = new PriceFilterAdapter(filterHotelPriceModel, activity);
        lvBestOffAdapter = new FilterHotelTypeAdapter(filterHotelBestOffModels, activity);
        starFilterAdapter = new StarFilterAdapter(filterHotelStarsModels, activity);


        lvPrice.setAdapter(priceFilterAdapter);
        lvHotelTypes.setAdapter(filterHotelTypeAdapter);
        lvLocationTypes.setAdapter(filterHotelLocationAdapter);
        lvFacilitiesTypes.setAdapter(filterHotelFacilitiesAdapter);
        lvBestOff.setAdapter(lvBestOffAdapter);
        lvStar.setAdapter(starFilterAdapter);

        lvStar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (filterHotelStarsModels.get(position).isCheck()) {
                    filterHotelStarsModels.set(position, new FilterStarModel(filterHotelStarsModels.get(position).getTitle(), false, filterHotelStarsModels.get(position).getStar()));

                } else {
                    filterHotelStarsModels.set(position, new FilterStarModel(filterHotelStarsModels.get(position).getTitle(), true, filterHotelStarsModels.get(position).getStar()));


                }
                starFilterAdapter.notifyDataSetChanged();

            }
        });

        lvBestOff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (filterHotelBestOffModels.get(position).isCheck()) {
                    filterHotelBestOffModels.set(position, new FilterHotelTypeModel(filterHotelBestOffModels.get(position).getTitle(), false));
                } else {
                    filterHotelBestOffModels.set(position, new FilterHotelTypeModel(filterHotelBestOffModels.get(position).getTitle(), true));
                }
                lvBestOffAdapter.notifyDataSetChanged();

            }
        });


        lvHotelTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (filterHotelTypeModels.get(position).isCheck()) {
                    filterHotelTypeModels.set(position, new FilterHotelTypeModel(filterHotelTypeModels.get(position).getTitle(), false));

                } else {
                    filterHotelTypeModels.set(position, new FilterHotelTypeModel(filterHotelTypeModels.get(position).getTitle(), true));
                }
                filterHotelTypeAdapter.notifyDataSetChanged();
            }
        });

        lvLocationTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (filterHotelLocationModels.get(position).isCheck()) {
                    filterHotelLocationModels.set(position, new FilterHotelTypeModel(filterHotelLocationModels.get(position).getTitle(), false));
                } else {
                    filterHotelLocationModels.set(position, new FilterHotelTypeModel(filterHotelLocationModels.get(position).getTitle(), true));
                }
                filterHotelLocationAdapter.notifyDataSetChanged();
            }
        });

        lvFacilitiesTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (filterHotelFacilitiesModels.get(position).isCheck()) {
                    filterHotelFacilitiesModels.set(position, new FilterHotelTypeModel(filterHotelFacilitiesModels.get(position).getTitle(), false));
                } else {
                    filterHotelFacilitiesModels.set(position, new FilterHotelTypeModel(filterHotelFacilitiesModels.get(position).getTitle(), true));
                }
                filterHotelFacilitiesAdapter.notifyDataSetChanged();
            }
        });
        lvPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (filterHotelPriceModel.get(position).isCheck()) {
                    filterHotelPriceModel.set(position, new FilterPriceModel(filterHotelPriceModel.get(position).getDiff(), filterHotelPriceModel.get(position).getX(), false));
                } else {
                    filterHotelPriceModel.set(position, new FilterPriceModel(filterHotelPriceModel.get(position).getDiff(), filterHotelPriceModel.get(position).getX(), true));
                }
                priceFilterAdapter.notifyDataSetChanged();
            }
        });

        btnOk.setCustomTextFont(getResources().getString(R.string.iran_sans_normal_ttf));
        btnDeletFilter.setCustomTextFont(getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);
        btnDeletFilter.setOnClickListener(this);
        // Do something else
        return rootView;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                // activity.startActivity(new Intent(activity, ProfileActivity.class));
                filterHotelDialogListenerArray.onReturnValue(filter, searchtxt.getText().toString(), filterHotelTypeModels, filterHotelFacilitiesModels,
                        filterHotelPriceModel, filterHotelLocationModels, filterHotelBestOffModels, filterHotelStarsModels, false);
                dismiss();
                break;
            case R.id.btnDeletFilter:
                remove = true;
                search = "";
                for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                    filterHotelTypeModels.set(i, new FilterHotelTypeModel(filterHotelTypeModels.get(i).getTitle(), false));
                }
                for (int i = 0; i < filterHotelBestOffModels.size(); i++) {
                    filterHotelBestOffModels.set(i, new FilterHotelTypeModel(filterHotelBestOffModels.get(i).getTitle(), false));
                }
                for (int i = 0; i < filterHotelStarsModels.size(); i++) {
                    filterHotelStarsModels.set(i, new FilterStarModel(filterHotelStarsModels.get(i).getTitle(), false, filterHotelStarsModels.get(i).getStar()));
                }
                for (int i = 0; i < filterHotelFacilitiesModels.size(); i++) {
                    filterHotelFacilitiesModels.set(i, new FilterHotelTypeModel(filterHotelFacilitiesModels.get(i).getTitle(), false));
                }

                for (int i = 0; i < filterHotelPriceModel.size(); i++) {
                    filterHotelPriceModel.set(i, new FilterPriceModel(filterHotelPriceModel.get(i).getDiff(), filterHotelPriceModel.get(i).getX(), false));


                }
                for (int i = 0; i < filterHotelLocationModels.size(); i++) {
                    filterHotelLocationModels.set(i, new FilterHotelTypeModel(filterHotelLocationModels.get(i).getTitle(), false));

                }
                filterHotelTypeAdapter.notifyDataSetChanged();
                filterHotelFacilitiesAdapter.notifyDataSetChanged();
                priceFilterAdapter.notifyDataSetChanged();
                filterHotelLocationAdapter.notifyDataSetChanged();
                lvBestOffAdapter.notifyDataSetChanged();
                starFilterAdapter.notifyDataSetChanged();
                if (filter.isEmpty()) {
                    filter.add(new FilterModel(false, false, false, false, false, false, false, true));


                } else {
                    filter.set(0, new FilterModel(false, false, false, false, false, false, false, true));

                }
                filterHotelDialogListenerArray.onReturnValue(filter, searchtxt.getText().toString(), filterHotelTypeModels, filterHotelFacilitiesModels,
                        filterHotelPriceModel, filterHotelLocationModels, filterHotelBestOffModels, filterHotelStarsModels, remove);

            dismiss();

                break;

        }
    }

/*public void show(){
      *//*  if (!dialog.isShowing()){
            dialog.show();
        }*//*
}*/

    private void initialize(final Context activity, ArrayList<FilterModel> filter,
                            FilterHotelDialogListenerArray filterHotelDialogListenerArray, final ArrayList<FilterHotelTypeModel> filterHotelTypeModels,
                            final ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels, final ArrayList<FilterPriceModel> filterHotelPriceModel,
                            String search, final ArrayList<FilterHotelTypeModel> filterHotelLocationModels, final ArrayList<FilterHotelTypeModel> filterHotelBestOffModels
            , final ArrayList<FilterStarModel> filterHotelStarsModels) {
        this.activity = activity;
        this.filter = filter;
        this.search = search;
        this.filterHotelDialogListenerArray = filterHotelDialogListenerArray;

        this.filterHotelPriceModel = filterHotelPriceModel;
        this.filterHotelLocationModels = filterHotelLocationModels;
        this.filterHotelTypeModels = filterHotelTypeModels;
        this.filterHotelFacilitiesModels = filterHotelFacilitiesModels;
        this.filterHotelStarsModels = filterHotelStarsModels;
        this.filterHotelBestOffModels = filterHotelBestOffModels;


    }

    public interface FilterHotelDialogListenerArray {
        void onReturnValue(ArrayList<FilterModel> type, String search,
                           ArrayList<FilterHotelTypeModel> filterHotelTypeModels,
                           ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels,
                           ArrayList<FilterPriceModel> filterHotelPriceModel, ArrayList<FilterHotelTypeModel> filterHotelLocationModels, ArrayList<FilterHotelTypeModel> filterHotelBestOffModels
                , ArrayList<FilterStarModel> filterHotelStarsModels, boolean remove);
    }
}
