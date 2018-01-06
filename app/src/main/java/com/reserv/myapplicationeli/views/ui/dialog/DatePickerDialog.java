package com.reserv.myapplicationeli.views.ui.dialog;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.tools.persian.Calendar.persian.PersianDatePicker;
import com.reserv.myapplicationeli.tools.persian.Calendar.persian.util.PersianCalendarUtils;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.login.LoginException;

import saman.zamani.persiandate.PersianDate;

import static android.content.ContentValues.TAG;


public class DatePickerDialog {
    android.app.AlertDialog dialog;
    View dialogView;
    LayoutInflater inflater;
    android.app.AlertDialog.Builder builder;
   PersianDatePicker persianDatePicker;
    TextView tvDateConfirm, tvDateCansel;
    Activity activity;
    TextView tvTitle;
    String date;


    public String getDate() {
        return date;
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








                String string = textView.getText()+ "";
                String[] parts = string.split("/");
                int year = Integer.valueOf(parts[0]);
                int month = Integer.valueOf(parts[1]);
                int day =Integer.valueOf(parts[2]) ;


                Date d= PersianCalendarUtils.ShamsiToMilady(year,month, day);//Tue Nov 29 11:21:15 GMT+03:30 2016
                SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
                String formatted = format1.format(d.getTime());

                String[] dateGrg=formatted.split("/");
                int yearS = Integer.valueOf(dateGrg[0]);
                int monthS = Integer.valueOf(dateGrg[1]);
                int dayS =Integer.valueOf(dateGrg[2]) ;

                date=yearS+"-"+monthS+"-"+dayS;
                Log.e("testttiiiiii", persianDatePicker.getDisplayDate()+"");

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
