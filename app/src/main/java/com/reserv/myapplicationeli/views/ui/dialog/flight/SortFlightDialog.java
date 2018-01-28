package com.reserv.myapplicationeli.views.ui.dialog.flight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.SortDialog;

import cn.refactor.library.SmoothCheckBox;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Reza.nejati on 1/7/2018.
 */

public class SortFlightDialog implements View.OnClickListener {
/* android.app.AlertDialog dialog;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnCancel;
    SortFlightDialogListener sortFlightDialogListener;*/
    SmoothCheckBox bestCoust, lowCoust;
    /*

     public SortFlightDialog(final Context activity, SortFlightDialogListener sortFlightDialogListener, boolean seler, boolean off, boolean remove) {
         this.activity = activity;
         this.sortFlightDialogListener = sortFlightDialogListener;
         builder = new android.app.AlertDialog.Builder(activity);
         inflater = LayoutInflater.from(activity);
         dialogView = inflater.inflate(R.layout.sort_flight_dialog, null);
         builder.setView(dialogView);
         btnOk = (FancyButton) dialogView.findViewById(R.id.btnOk);
         bestCoust = (SmoothCheckBox) dialogView.findViewById(R.id.bestCoust);
         lowCoust = (SmoothCheckBox) dialogView.findViewById(R.id.lowCoust);

         bestCoust.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                 if(isChecked){
                     lowCoust.setChecked(false);

                 }
             }
         });
         lowCoust.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                 if(isChecked){
                     bestCoust.setChecked(false);

                 }
             }
         });



         if (seler) {
             bestCoust.setChecked(true);


         }
         if (off) {
             lowCoust.setChecked(true);

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


                 if (bestCoust.isChecked()) {
                     sortFlightDialogListener.onReturnValueSort(1);
                 }
                 if (lowCoust.isChecked()) {
                     sortFlightDialogListener.onReturnValueSort(2);
                 }



                 dialog.cancel();


                 break;

         }
     }

     public interface SortFlightDialogListener {
         public void onReturnValueSort(int type);
     }

 }

 */
   android.app.AlertDialog dialog;
    TextView tvMax,tvMin;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
    Context activity;
    FancyButton btnOk, btnCancel;
    SortFlightDialogListener sortFlightDialogListener;

  public SortFlightDialog(final Context activity, SortFlightDialogListener sortFlightDialogListener, boolean seler, boolean off, boolean remove) {
        this.activity = activity;
       // this.sortHotelDialogListener = sortHotelDialogListener;
      this.sortFlightDialogListener = sortFlightDialogListener;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.sort_flight_dialog, null);
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
                sortFlightDialogListener.onReturnValueSort(1);

                dialog.cancel();


                break;
            case R.id.tvMin:
                sortFlightDialogListener.onReturnValueSort(2);

                dialog.cancel();


                break;

        }
    }
    public interface SortFlightDialogListener{
        public void onReturnValueSort(int type);
    }

}
