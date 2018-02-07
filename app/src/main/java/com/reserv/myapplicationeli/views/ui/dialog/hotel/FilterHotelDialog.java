package com.reserv.myapplicationeli.views.ui.dialog.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ScrollView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.hotel.FilterPriceModel;
import com.reserv.myapplicationeli.models.hotel.adapter.FilterModel;
import com.reserv.myapplicationeli.views.adapters.hotel.FilterHotelTypeAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.PriceFilterAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class FilterHotelDialog implements View.OnClickListener, SmoothCheckBox.OnCheckedChangeListener {
    android.app.AlertDialog dialog;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnDeletFilter;
    FilterHotelDialogListenerArray filterHotelDialogListenerArray;
    SmoothCheckBox bestSeler, bestOff, Remove, star2, star3, star4, star5, star1;
    ArrayList<FilterModel> filter;
    EditText searchtxt;
    boolean star1_;
    boolean star2_;
    boolean star3_;
    boolean star4_;
    boolean star5_;
    boolean bestSeler_;
    boolean bestOff_;
    boolean remove_;
    com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView lvHotelTypes, lvFacilitiesTypes,lvPrice,lvLocationTypes;
    FilterHotelTypeAdapter filterHotelTypeAdapter, filterHotelFacilitiesAdapter,filterHotelLocationAdapter;
    ArrayList<FilterHotelTypeModel> filterHotelTypeModels, filterHotelFacilitiesModels,filterHotelLocationModels;
    ArrayList<FilterPriceModel> filterHotelPriceModel;
    PriceFilterAdapter priceFilterAdapter;
    String search;
    ScrollView scrollViewObject;



    public FilterHotelDialog(final Context activity, ArrayList<FilterModel> filter,
                             FilterHotelDialogListenerArray filterHotelDialogListenerArray, final ArrayList<FilterHotelTypeModel> filterHotelTypeModels,
                             final ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels, final ArrayList<FilterPriceModel> filterHotelPriceModel,
                             String search,final ArrayList<FilterHotelTypeModel> filterHotelLocationModels) {
        this.activity = activity;
        this.filter = filter;
        this.search = search;
        this.filterHotelDialogListenerArray = filterHotelDialogListenerArray;

        this.filterHotelPriceModel = filterHotelPriceModel;
        this.filterHotelLocationModels = filterHotelLocationModels;

        this.filterHotelTypeModels = filterHotelTypeModels;
        this.filterHotelFacilitiesModels = filterHotelFacilitiesModels;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.filter_dialog_r, null);
        // filter.add(new FilterModel(false, false, false, false, false, false, false, false, false, false, false));

        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        scrollViewObject = (ScrollView) dialogView.findViewById(R.id.scrollViewObject);
        btnDeletFilter = (FancyButton) dialogView.findViewById(R.id.btnDeletFilter);
        searchtxt = (EditText) dialogView.findViewById(R.id.searchtxt);
        bestSeler = (SmoothCheckBox) dialogView.findViewById(R.id.bestSeler);
        bestOff = (SmoothCheckBox) dialogView.findViewById(R.id.bestOff);
        Remove = (SmoothCheckBox) dialogView.findViewById(R.id.Remove);
        star1 = (SmoothCheckBox) dialogView.findViewById(R.id.star1);
        star2 = (SmoothCheckBox) dialogView.findViewById(R.id.star2);
        star3 = (SmoothCheckBox) dialogView.findViewById(R.id.star3);
        star4 = (SmoothCheckBox) dialogView.findViewById(R.id.star4);
        star5 = (SmoothCheckBox) dialogView.findViewById(R.id.star5);
        searchtxt.setText(search);
        lvHotelTypes = (com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView) dialogView.findViewById(R.id.lvHotelTypes);
        lvFacilitiesTypes = (com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView) dialogView.findViewById(R.id.lvFacilitiesTypes);
        lvLocationTypes = (com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView) dialogView.findViewById(R.id.lvLocationTypes);
        lvPrice = (com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView) dialogView.findViewById(R.id.lvPrice);
        filterHotelTypeAdapter = new FilterHotelTypeAdapter(filterHotelTypeModels, activity);
        filterHotelFacilitiesAdapter = new FilterHotelTypeAdapter(filterHotelFacilitiesModels, activity);
        filterHotelLocationAdapter = new FilterHotelTypeAdapter(filterHotelLocationModels, activity);
        priceFilterAdapter = new PriceFilterAdapter(filterHotelPriceModel, activity);
        lvPrice.setAdapter(priceFilterAdapter);

        lvHotelTypes.setAdapter(filterHotelTypeAdapter);
        lvLocationTypes.setAdapter(filterHotelLocationAdapter);
        lvFacilitiesTypes.setAdapter(filterHotelFacilitiesAdapter);
        lvHotelTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (filterHotelTypeModels.get(position).isCheck()) {
                    filterHotelTypeModels.set(position, new FilterHotelTypeModel(filterHotelTypeModels.get(position).getTitle(), false));

                } else {
                    filterHotelTypeModels.set(position, new FilterHotelTypeModel(filterHotelTypeModels.get(position).getTitle(), true));
                    Remove.setChecked(false);


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
                    Remove.setChecked(false);


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
                    Remove.setChecked(false);


                }
                filterHotelFacilitiesAdapter.notifyDataSetChanged();
            }
        });
        lvPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (filterHotelPriceModel.get(position).isCheck()) {
                    filterHotelPriceModel.set(position, new FilterPriceModel(filterHotelPriceModel.get(position).getDiff(),filterHotelPriceModel.get(position).getX(), false));

                } else {
                    filterHotelPriceModel.set(position, new FilterPriceModel(filterHotelPriceModel.get(position).getDiff(),filterHotelPriceModel.get(position).getX(), true));
                    Remove.setChecked(false);


                }
                priceFilterAdapter.notifyDataSetChanged();
            }
        });


        Remove.setOnCheckedChangeListener(this);
        star1.setOnCheckedChangeListener(this);
        star2.setOnCheckedChangeListener(this);
        star3.setOnCheckedChangeListener(this);
        star4.setOnCheckedChangeListener(this);
        star5.setOnCheckedChangeListener(this);

        bestOff.setOnCheckedChangeListener(this);
        bestSeler.setOnCheckedChangeListener(this);


        btnOk.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnDeletFilter.setCustomTextFont("fonts/iran_sans_normal.ttf");
        btnOk.setOnClickListener(this);
        btnDeletFilter.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);

        for (FilterModel filterModel : filter) {
            if (filterModel.isStar1()) {
                star1.setChecked(true);
                star1_ = true;

            } else {
                star1_ = false;
                star1.setChecked(false);

            }
            //========


            if (filterModel.isStar2()) {
                star2.setChecked(true);
                star2_ = true;

            } else {
                star2_ = false;
                star2.setChecked(false);

            }

            //========

            if (filterModel.isStar3()) {
                star3.setChecked(true);
                star3_ = true;


            } else {
                star3_ = false;

                star3.setChecked(false);

            }
            //========


            if (filterModel.isStar4()) {
                star4.setChecked(true);
                star4_ = true;


            } else {
                star4_ = false;
                star4.setChecked(false);

            }
            //========

            if (filterModel.isStar5()) {
                star5.setChecked(true);
                star5_ = true;


            } else {
                star5_ = false;
                star5.setChecked(false);

            }
            //========

            if (filterModel.isBestOff()) {
                bestOff.setChecked(true);
                bestOff_ = true;


            } else {
                bestOff_ = false;
                bestOff.setChecked(false);

            }
            //========

            if (filterModel.isBestSeler()) {
                bestSeler.setChecked(true);
                bestSeler_ = true;


            } else {
                bestSeler_ = false;
                bestSeler.setChecked(false);

            }


            //========
            if (filterModel.isRemove()) {
                Remove.setChecked(true);
                remove_ = true;


            } else {
                Remove.setChecked(false);
                remove_ = false;

            }
        }

       /* scrollViewObject.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollViewObject.fullScroll(ScrollView.FOCUS_UP);
            }
        }, 600);*/
      /*  scrollViewObject.setFocusable(false);
        lvHotelTypes.setFocusable(false);
        lvFacilitiesTypes.setFocusable(false);
        lvPrice.setFocusable(false);
        lvLocationTypes.setFocusable(false);*/
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                // activity.startActivity(new Intent(activity, ProfileActivity.class));


                if (star1.isChecked()) {
                    star1_ = true;
                } else {
                    star1_ = false;
                }

                //========
                if (star2.isChecked()) {
                    star2_ = true;

                } else {
                    star2_ = false;
                }


                //========

                if (star3.isChecked()) {
                    star3_ = true;


                } else {
                    star3_ = false;
                }


                //========


                if (star4.isChecked()) {
                    star4_ = true;
                } else {
                    star4_ = false;
                }

                //========

                if (star5.isChecked()) {
                    star5_ = true;
                } else {
                    star5_ = false;
                }


                //========

                if (bestSeler.isChecked()) {
                    bestSeler_ = true;
                } else {
                    bestSeler_ = false;
                }
                //========

                if (bestOff.isChecked()) {
                    bestOff_ = true;
                } else {
                    bestOff_ = false;
                }


                //========


                //========
                if (Remove.isChecked()) {
                    if (filter.isEmpty()) {
                        filter.add(new FilterModel(false, false, false, false, false, false, false, false));


                    } else {
                        filter.set(0, new FilterModel(false, false, false, false, false, false, false, false));

                    }
                } else {
                    if (filter.isEmpty()) {
                        filter.add(new FilterModel(star1_, star2_, star3_, star4_, star5_, bestSeler_, bestOff_, false));

                    } else {
                        filter.set(0, new FilterModel(star1_, star2_, star3_, star4_, star5_, bestSeler_, bestOff_, false));

                    }
                }


                filterHotelDialogListenerArray.onReturnValue(filter, searchtxt.getText().toString(), filterHotelTypeModels, filterHotelFacilitiesModels,filterHotelPriceModel,filterHotelLocationModels);


                dialog.cancel();


                break;
            case R.id.btnDeletFilter:
                for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                    filterHotelTypeModels.set(i, new FilterHotelTypeModel(filterHotelTypeModels.get(i).getTitle(), false));


                }

                for (int i = 0; i < filterHotelFacilitiesModels.size(); i++) {
                    filterHotelFacilitiesModels.set(i, new FilterHotelTypeModel(filterHotelFacilitiesModels.get(i).getTitle(), false));


                }

                for (int i = 0; i < filterHotelPriceModel.size(); i++) {
                    filterHotelPriceModel.set(i, new FilterPriceModel(filterHotelPriceModel.get(i).getDiff(),filterHotelPriceModel.get(i).getX(), false));


                }
                for (int i = 0; i < filterHotelLocationModels.size(); i++) {
                    filterHotelLocationModels.set(i, new FilterHotelTypeModel(filterHotelLocationModels.get(i).getTitle(), false));


                }
                filterHotelTypeAdapter.notifyDataSetChanged();
                filterHotelFacilitiesAdapter.notifyDataSetChanged();
                priceFilterAdapter.notifyDataSetChanged();
                filterHotelLocationAdapter.notifyDataSetChanged();
                if (filter.isEmpty()) {
                    filter.add(new FilterModel(false, false, false, false, false, false, false, true));


                } else {
                    filter.set(0, new FilterModel(false, false, false, false, false, false, false, true));

                }
                filterHotelDialogListenerArray.onReturnValue(filter, searchtxt.getText().toString(), filterHotelTypeModels, filterHotelFacilitiesModels,filterHotelPriceModel,filterHotelLocationModels);

                dialog.cancel();

                break;

        }
    }

    @Override
    public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {

        switch (checkBox.getId()) {
            case R.id.Remove:
                if (isChecked) {

                    bestSeler.setChecked(false);
                    bestOff.setChecked(false);
                    star5.setChecked(false);
                    star4.setChecked(false);
                    star3.setChecked(false);
                    star2.setChecked(false);
                    star1.setChecked(false);


                    for (int i = 0; i < filterHotelTypeModels.size(); i++) {
                        filterHotelTypeModels.set(i, new FilterHotelTypeModel(filterHotelTypeModels.get(i).getTitle(), false));


                    }

                    for (int i = 0; i < filterHotelFacilitiesModels.size(); i++) {
                        filterHotelFacilitiesModels.set(i, new FilterHotelTypeModel(filterHotelFacilitiesModels.get(i).getTitle(), false));


                    }

                    for (int i = 0; i < filterHotelPriceModel.size(); i++) {
                        filterHotelPriceModel.set(i, new FilterPriceModel(filterHotelPriceModel.get(i).getDiff(),filterHotelPriceModel.get(i).getX(), false));


                    }
                    filterHotelTypeAdapter.notifyDataSetChanged();
                    filterHotelFacilitiesAdapter.notifyDataSetChanged();
                    priceFilterAdapter.notifyDataSetChanged();


                }
                break;


            case R.id.bestSeler:
                if (isChecked) {
                    Remove.setChecked(false);
                }
                break;
            case R.id.bestOff:
                if (isChecked) {
                    Remove.setChecked(false);

                }
                break;
            case R.id.star2:
                if (isChecked) {
                    Remove.setChecked(false);

                }
                break;
            case R.id.star3:
                if (isChecked) {
                    Remove.setChecked(false);

                }
                break;
            case R.id.star4:
                if (isChecked) {
                    Remove.setChecked(false);

                }
                break;
            case R.id.star5:
                if (isChecked) {
                    Remove.setChecked(false);

                }
                break;

        }

    }


    public interface FilterHotelDialogListenerArray {
        public void onReturnValue(ArrayList<FilterModel> type, String search,
                                  ArrayList<FilterHotelTypeModel> filterHotelTypeModels,
                                  ArrayList<FilterHotelTypeModel> filterHotelFacilitiesModels,
                                  ArrayList<FilterPriceModel> filterHotelPriceModel,ArrayList<FilterHotelTypeModel> filterHotelLocationModels);
    }

}

