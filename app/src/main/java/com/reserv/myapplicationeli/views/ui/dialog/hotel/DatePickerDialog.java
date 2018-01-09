package com.reserv.myapplicationeli.views.ui.dialog.hotel;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;
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
    String date,date1;


    public String getDate() {
        return date1;
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
                textView.setTextColor(ContextCompat.getColor(activity,R.color.textColorR));








                String string = textView.getText()+ "";
                String[] parts = string.split("/");
                int year = Integer.valueOf(parts[0]);
                int month = Integer.valueOf(parts[1]);
                int day =Integer.valueOf(parts[2]) ;


                Date d= PersianCalendarUtils.ShamsiToMilady(year,month, day);//
                SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
                String formatted = format1.format(d.getTime());

                String[] dateGrg=formatted.split("/");
                int monthS = Integer.valueOf(dateGrg[0]);
                int dayS = Integer.valueOf(dateGrg[1]);
                int yearS =Integer.valueOf(dateGrg[2]) ;

                Log.e("yearS", yearS+"");
                Log.e("monthS", monthS+"");
                Log.e("dayS", dayS+"");




//todo change This

                date=yearS+"-"+"0"+monthS+"-"+dayS;
                date1=yearS+"/"+"0"+monthS+"/"+dayS;
                Log.e("testttiiiiii", persianDatePicker.getDisplayDate()+"");
                Log.e("tesetes", date);
                Log.e("tesetes", date1);

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
