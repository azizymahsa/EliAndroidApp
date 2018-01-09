package com.reserv.myapplicationeli.views.ui.dialog.hotel;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.hotel.adapter.FilterModel;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class FilterHotelDialog implements View.OnClickListener {
    android.app.AlertDialog dialog;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnCancel;
    FilterHotelDialogListener filterHotelDialogListener;
    FilterHotelDialogListenerArray filterHotelDialogListenerArray;
    SmoothCheckBox bestSeler, bestOff, Remove, star2, star3, star4, star5, star1, hotel, boutique, apartment, resort;
    ArrayList<FilterModel> filter;
    boolean star1_;
    boolean star2_;
    boolean star3_;
    boolean star4_;
    boolean star5_;
    boolean bestSeler_;
    boolean bestOff_;
    boolean resort_;
    boolean boutique_;
    boolean apartment_;
    boolean hotel_;

    public FilterHotelDialog(final Context activity, FilterHotelDialogListener filterHotelDialogListener, ArrayList<FilterModel> filter, FilterHotelDialogListenerArray filterHotelDialogListenerArray) {
        this.activity = activity;
        this.filter = filter;
        this.filterHotelDialogListener = filterHotelDialogListener;
        this.filterHotelDialogListenerArray = filterHotelDialogListenerArray;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.filter_dialog, null);
      filter.add(new FilterModel(false, false, false, false, false, false, false, false, false, false, false));

        builder.setView(dialogView);
        btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
        bestSeler = (SmoothCheckBox) dialogView.findViewById(R.id.bestSeler);
        bestOff = (SmoothCheckBox) dialogView.findViewById(R.id.bestOff);
        Remove = (SmoothCheckBox) dialogView.findViewById(R.id.Remove);
        star1 = (SmoothCheckBox) dialogView.findViewById(R.id.star1);
        star2 = (SmoothCheckBox) dialogView.findViewById(R.id.star2);
        star3 = (SmoothCheckBox) dialogView.findViewById(R.id.star3);
        star4 = (SmoothCheckBox) dialogView.findViewById(R.id.star4);
        star5 = (SmoothCheckBox) dialogView.findViewById(R.id.star5);

        hotel = (SmoothCheckBox) dialogView.findViewById(R.id.hotel);
        boutique = (SmoothCheckBox) dialogView.findViewById(R.id.boutique);
        apartment = (SmoothCheckBox) dialogView.findViewById(R.id.apartment);
        resort = (SmoothCheckBox) dialogView.findViewById(R.id.resort);

        btnOk.setCustomTextFont("irsans.ttf");
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);

        for (FilterModel filterModel : filter) {
            if (filterModel.isStar1()) {
                star1.isChecked();
                star1_ = true;

            }else {
                star1_ = false;

            }



            if (filterModel.isStar2()) {
                star2.isChecked();
                star2_ = true;

            } else {
                star2_ = false;

            }


            if (filterModel.isStar3()){
                star3.isChecked();
                star3_ = true;


            }else {
                star3_ = false;


            }


            if (filterModel.isStar4()) {
                star4.isChecked();
                star4_ = true;


            }else {
                star4_ = false;

            }
            if (filterModel.isStar5()) {
                star5.isChecked();
                star5_ = true;


            }else{star5_=false;}
            if (filterModel.isBestOff()) {
                bestOff.isChecked();
                bestOff_ = true;


            }else {
                bestOff_ = false;
            }
            if (filterModel.isBestSeler()) {
                bestSeler.isChecked();
                bestSeler_ = true;


            }else
            {bestSeler_=false;}





            if (filterModel.isResort()) {
                resort.isChecked();
                resort_ = true;


            } else {
                resort_ = false;
            }

            if (filterModel.isHotel()){
                hotel.isChecked();
                hotel_ = true;

            }else {

                hotel_ = false;
            }

            if (filterModel.isBoutique()) {
                boutique.isChecked();
                boutique_ = true;


            }
            else {
                boutique_ = false;
            }


            if (filterModel.isApartment()) {
                apartment.isChecked();
                apartment_ = true;


            } else {
                apartment_ = false;
            }

        }
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                // activity.startActivity(new Intent(activity, ProfileActivity.class));


                if (star1.isChecked()) {
                    star1_ = true;


                }
                if (star2.isChecked()) {
                    star2_ = true;

                }
                if (star3.isChecked()) {
                    star3_ = true;


                }

                if (star4.isChecked()) {
                    star4_ = true;
                }

                if (star5.isChecked()) {
                    star5_ = true;


                }
                if (bestSeler.isChecked()) {
                    bestSeler_ = true;
                }

                if (bestOff.isChecked()) {
                    bestOff_ = true;
                }


                if (bestOff.isChecked()) {
                    bestOff_ = true;
                }
                if (apartment.isChecked()) {
                    apartment_ = true;
                }
                if (resort.isChecked()) {
                    resort_ = true;
                }
                if (hotel.isChecked()) {
                    hotel_ = true;
                }
                if (boutique.isChecked()) {
                    boutique_ = true;
                }

                filter.set(0, new FilterModel(star1_, star2_, star3_, star4_, star5_, bestSeler_, bestOff_, resort_, boutique_, apartment_, hotel_));

                filterHotelDialogListenerArray.onReturnValue(filter);


                dialog.cancel();


                break;

        }
    }

    public interface FilterHotelDialogListener {
        public void onReturnValue(int type);
    }

    public interface FilterHotelDialogListenerArray {
        public void onReturnValue(ArrayList<FilterModel> type);
    }

}

