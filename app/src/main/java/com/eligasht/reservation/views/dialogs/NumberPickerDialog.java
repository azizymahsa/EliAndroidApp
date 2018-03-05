package com.eligasht.reservation.views.dialogs;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import com.eligasht.R;
import com.eligasht.reservation.views.ui.SingletonContext;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by elham.bonyani on 1/14/2018.
 */

public class NumberPickerDialog implements View.OnClickListener {


    android.app.AlertDialog dialog;
    com.shawnlin.numberpicker.NumberPicker numberPicker;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk;
    NumberPickerListener numberPickerListener;
    String[] during_trip_string;
    Integer[] during_trip;


    public NumberPickerDialog(final Context activity, NumberPickerListener numberPickerListener) {
        this.activity = activity;
        this.numberPickerListener = numberPickerListener;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_number_picker, null);
        numberPicker = dialogView.findViewById(R.id.number_picker);
        builder.setView(dialogView);
        numberPicker = dialogView.findViewById(R.id.number_picker);
        numberPicker.setOnClickListener(this);
        during_trip_string = new String[]{activity.getString(R.string.seven_days), activity.getString(R.string.fifteen_days), activity.getString(R.string.twenty_three_days), activity.getString(R.string.thirty_one_days), activity.getString(R.string.fourty_five_days), activity.getString(R.string.two_months), activity.getString(R.string.three_months), activity.getString(R.string.six_months), activity.getString(R.string.one_year)};
        during_trip = new Integer[]{7, 15, 23, 31, 45, 60 , 90, 180, 365};
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(8);
        numberPicker.setValue(0);
        numberPicker.setDisplayedValues(during_trip_string);

        btnOk = dialogView.findViewById(R.id.btnOk);
        Typeface type = Typeface.createFromAsset(activity.getAssets(), SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        numberPicker.setTypeface(type);
        numberPicker.setDividerColorResource(R.color.colorPrimary);
        numberPicker.setSelectedTextColorResource(R.color.colorPrimary);
        numberPicker.setTextSize(R.dimen._16dp_txtSize);
        numberPicker.setSelectedTextSize(R.dimen._16dp_txtSize);

        btnOk.setText("انتخاب");
        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        btnOk.setOnClickListener(this);
        // numberPicker.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnOk:
                numberPickerListener.onReturnValue(during_trip_string[numberPicker.getValue()],during_trip[numberPicker.getValue()]);
                dialog.cancel();


        }
    }

    public interface NumberPickerListener {
        void onReturnValue(String type, int duration);
    }


}
