package com.reserv.myapplicationeli.views.ui.dialog.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.hotel.adapter.FilterModel;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/12/2018.
 */

public class SortDialog implements View.OnClickListener {
    android.app.AlertDialog dialog;
    TextView tvMax,tvMin;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnCancel;
   SortHotelDialogListener sortHotelDialogListener;


    public SortDialog(final Context activity,SortHotelDialogListener sortHotelDialogListener) {
        this.activity = activity;
        this.sortHotelDialogListener = sortHotelDialogListener;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.alert_dialog_sort, null);
        builder.setView(dialogView);
        tvMax = (TextView) dialogView.findViewById(R.id.tvMax);
        tvMin = (TextView) dialogView.findViewById(R.id.tvMin);

        tvMax.setOnClickListener(this);
        tvMin.setOnClickListener(this);
        dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvMax:
                sortHotelDialogListener.onReturnValue(1);

                dialog.cancel();


                break;
                case R.id.tvMin:
                    sortHotelDialogListener.onReturnValue(2);

                dialog.cancel();


                break;

        }
    }
    public interface SortHotelDialogListener{
        public void onReturnValue(int type);
    }

}
