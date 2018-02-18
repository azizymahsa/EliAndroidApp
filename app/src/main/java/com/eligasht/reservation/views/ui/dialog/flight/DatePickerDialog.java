package com.eligasht.reservation.views.ui.dialog.flight;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.tools.persian.Calendar.persian.PersianDatePicker;
import com.eligasht.reservation.tools.persian.Calendar.persian.util.PersianCalendarUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DatePickerDialog {
    android.app.AlertDialog dialog;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
   PersianDatePicker persianDatePicker;
    TextView tvDateConfirm, tvDateCansel;
    Activity activity;
    TextView tvTitle;
    String date,date1,datePersian;


    public String getDate() {
        return date;//2018-01-16
    }

    public DatePickerDialog(final Activity activity, final TextView textView, String title) {
        this.activity = activity;
        builder = new android.app.AlertDialog.Builder(activity);
        inflater = LayoutInflater.from(activity);
        dialogView = inflater.inflate(R.layout.date_picker, null);
        builder.setView(dialogView);
        dialog = builder.create();
        dialog.setCancelable(true);
        dialog.show();
        persianDatePicker = (PersianDatePicker) dialogView.findViewById(R.id.persianDatePicker);








        tvDateConfirm = (TextView) dialogView.findViewById(R.id.tvDateConfirm);
        tvDateCansel = (TextView) dialogView.findViewById(R.id.tvDateCansel);
        tvTitle = (TextView) dialogView.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        tvDateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(persianDatePicker.getDate()+ "");
                textView.setTextColor(ContextCompat.getColor(activity, R.color.textColorR));








                datePersian = textView.getText()+ "";//1396/10/26
                String[] parts = datePersian.split("/");
                int year = Integer.valueOf(parts[0]);//1396
                int month = Integer.valueOf(parts[1]);//10
                int day =Integer.valueOf(parts[2]) ;//26


                Date d= PersianCalendarUtils.ShamsiToMilady(year,month, day);//
                SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
                String formatted = format1.format(d.getTime());//01/16/2018

                String[] dateGrg=formatted.split("/");
                int monthS = Integer.valueOf(dateGrg[0]);//1
                int dayS = Integer.valueOf(dateGrg[1]);//16
                int yearS =Integer.valueOf(dateGrg[2]) ;//2018

                Log.e("yearS", yearS+"");
                Log.e("monthS", monthS+"");
                Log.e("dayS", dayS+"");




//todo change This

                date=yearS+"-"+"0"+monthS+"-"+dayS;//2018-01-16
                date1=yearS+"/"+"0"+monthS+"/"+dayS;//2018/01/16
                Log.e("testttiiiiii", persianDatePicker.getDisplayDate()+"");//Tue Jan 16 11:34:43 GMT+03:30 2018
                Log.e("tesetes", date);//2018-01-16
                Log.e("tesetes", date1);//2018/01/16

                dialog.cancel();
            }
        });
        tvDateCansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
}
