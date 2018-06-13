package com.eligasht.reservation.views.ui.dialog.flight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.views.ui.SingletonContext;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class FilterFlightDialog implements View.OnClickListener {
    android.app.AlertDialog dialog;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnCancel;
    FilterFlightDialogListener filterFlightDialogListener;
    SmoothCheckBox noStop, oneStop,twoStopMore, Remove,economiF, businessF, ferstF, star5, star1, hotel, boutique, apartment, resort;
    TextView txtTavaghof;


    public FilterFlightDialog(final Context activity, FilterFlightDialogListener filterFlightDialogListener, boolean bnoStop, boolean boneStop, boolean btwoStopMore, boolean beconomiF, boolean bbusinessF, boolean bferstF, final boolean remove) {
        this.activity = activity;
        this.filterFlightDialogListener = filterFlightDialogListener;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.filter_flight_dialog, null);
        builder.setView(dialogView);
        btnOk = dialogView.findViewById(R.id.btnOk);

        noStop = dialogView.findViewById(R.id.noStop);
        oneStop = dialogView.findViewById(R.id.oneStop);
        twoStopMore = dialogView.findViewById(R.id.twoStopMore);

        Remove = dialogView.findViewById(R.id.Remove);

        economiF = dialogView.findViewById(R.id.economiF);
        businessF = dialogView.findViewById(R.id.businessF);
        ferstF = dialogView.findViewById(R.id.ferstF);
       /* star4 = (SmoothCheckBox) dialogView.findViewById(R.id.ferstF);
        star5 = (SmoothCheckBox) dialogView.findViewById(R.id.star5);*/

        boutique = dialogView.findViewById(R.id.boutique);
        resort = dialogView.findViewById(R.id.resort);
        txtTavaghof = dialogView.findViewById(R.id.txtTavaghof);

        Remove.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                ferstF.setChecked(false);
                businessF.setChecked(false);
                economiF.setChecked(false);

                noStop.setChecked(false);
                oneStop.setChecked(false);
                twoStopMore.setChecked(false);
            }
        });


        if (bnoStop) {
            noStop.setChecked(true);
            oneStop.setChecked(false);
            twoStopMore.setChecked(false);

        }
        if (boneStop) {
            oneStop.setChecked(true);
            noStop.setChecked(false);
            twoStopMore.setChecked(false);
        }
        if (btwoStopMore) {
            twoStopMore.setChecked(true);
            noStop.setChecked(false);
            oneStop.setChecked(false);
        }

        if (beconomiF) {
            economiF.setChecked(true);

        }
        if (bbusinessF) {
            businessF.setChecked(true);

        }
        if (bferstF) {
            ferstF.setChecked(true);

        }

        if (remove) {
            Remove.setChecked(true);

        }

/*        noStop.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if(isChecked){
                ferstF.setChecked(false);
                businessF.setChecked(false);
                economiF.setChecked(false);

                Remove.setChecked(false);
                oneStop.setChecked(false);
                twoStopMore.setChecked(false);
                }
            }
        });

        oneStop.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if(isChecked){
                ferstF.setChecked(false);
                businessF.setChecked(false);
                economiF.setChecked(false);

                noStop.setChecked(false);
                Remove.setChecked(false);
                twoStopMore.setChecked(false);}
            }
        });

        twoStopMore.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if(isChecked){
                ferstF.setChecked(false);
                businessF.setChecked(false);
                economiF.setChecked(false);

                noStop.setChecked(false);
                oneStop.setChecked(false);
                Remove.setChecked(false);}
            }
        });*/


        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();


        //////////////////////////
  /*      // Spinner element
        Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinnerFlight);
        Spinner spinnerMosafer = (Spinner) dialogView.findViewById(R.id.spinnerMosafer);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinnerMosafer.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("زن");
        categories.add("مرد");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down dialog_custom style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinnerMosafer.setAdapter(dataAdapter);*/
        ////////////////////////////////
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                // activity.startActivity(new Intent(activity, ProfileActivity.class));


                if (noStop.isChecked()) {

                    filterFlightDialogListener.onReturnValueFilterFlight(1);
                }
                if (oneStop.isChecked()) {

                    filterFlightDialogListener.onReturnValueFilterFlight(2);
                }
                if (twoStopMore.isChecked()) {

                    filterFlightDialogListener.onReturnValueFilterFlight(3);
                }
                if (Remove.isChecked()) {
                    filterFlightDialogListener.onReturnValueFilterFlight(4);
                }

                if (economiF.isChecked()) {
                    filterFlightDialogListener.onReturnValueFilterFlight(5);
                }
                if (businessF.isChecked()) {
                    filterFlightDialogListener.onReturnValueFilterFlight(6);
                }
                if (ferstF.isChecked()) {
                    filterFlightDialogListener.onReturnValueFilterFlight(7);
                }
                if(noStop.isChecked() && oneStop.isChecked() && twoStopMore.isChecked() ){

                    filterFlightDialogListener.onReturnValueFilterFlight(8);
                }
                if(noStop.isChecked() && oneStop.isChecked()  ){

                    filterFlightDialogListener.onReturnValueFilterFlight(9);
                }
                if(oneStop.isChecked() && twoStopMore.isChecked()  ){

                    filterFlightDialogListener.onReturnValueFilterFlight(10);
                }
               /* if (ferstF.isChecked()) {
                    filterFlightDialogListener.onReturnValueFilterFlight(8);
                }
                if (star5.isChecked()) {
                    filterFlightDialogListener.onReturnValueFilterFlight(9);

                }
                if (resort.isChecked()) {
                    filterFlightDialogListener.onReturnValueFilterFlight(10);

                }
                if (boutique.isChecked()) {
                    filterFlightDialogListener.onReturnValueFilterFlight(11);

                }
                if (apartment.isChecked()) {
                    filterFlightDialogListener.onReturnValueFilterFlight(12);

                }
                if (hotel.isChecked()) {
                    filterFlightDialogListener.onReturnValueFilterFlight(13);

                }*/


                dialog.cancel();


                break;

        }
    }

    public interface FilterFlightDialogListener {
        void onReturnValueFilterFlight(int type);
    }

}

