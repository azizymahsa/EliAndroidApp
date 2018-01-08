package com.reserv.myapplicationeli.views.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.reserv.myapplicationeli.R;

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
    SmoothCheckBox bestSeler, bestOff, Remove, star2, star3, star4, star5, star1, hotel, boutique, apartment, resort;


    public FilterHotelDialog(final Context activity, FilterHotelDialogListener filterHotelDialogListener, boolean seler, boolean off, boolean remove) {
        this.activity = activity;
        this.filterHotelDialogListener = filterHotelDialogListener;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.filter_dialog, null);
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


        if (seler) {
            bestSeler.setChecked(true);


        }
        if (off) {
            bestOff.setChecked(true);

        }
        if (remove) {
            Remove.setChecked(true);

        }


        btnOk.setCustomTextFont("irsans.ttf");
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                // activity.startActivity(new Intent(activity, ProfileActivity.class));


                if (bestSeler.isChecked()) {
                    filterHotelDialogListener.onReturnValue(1);
                }
                if (bestOff.isChecked()) {
                    filterHotelDialogListener.onReturnValue(2);
                }
                if (Remove.isChecked()) {
                    filterHotelDialogListener.onReturnValue(3);
                }

                if (star1.isChecked()) {
                    filterHotelDialogListener.onReturnValue(4);
                }
                if (star2.isChecked()) {
                    filterHotelDialogListener.onReturnValue(5);
                }
                if (star3.isChecked()) {
                    filterHotelDialogListener.onReturnValue(6);
                }
                if (star4.isChecked()) {
                    filterHotelDialogListener.onReturnValue(7);
                }
                if (star5.isChecked()) {
                    filterHotelDialogListener.onReturnValue(8);

                }
                if (resort.isChecked()) {
                    filterHotelDialogListener.onReturnValue(9);

                }
                if (boutique.isChecked()) {
                    filterHotelDialogListener.onReturnValue(10);

                }
                if (apartment.isChecked()) {
                    filterHotelDialogListener.onReturnValue(11);

                }
                if (hotel.isChecked()) {
                    filterHotelDialogListener.onReturnValue(12);

                }


                dialog.cancel();


                break;

        }
    }

    public interface FilterHotelDialogListener {
        public void onReturnValue(int type);
    }

}

