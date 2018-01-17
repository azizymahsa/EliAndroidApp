package com.reserv.myapplicationeli.views.activities.insurance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.contracts.PassengerContract;
import com.reserv.myapplicationeli.models.model.insurance.BirthDateList;
import com.reserv.myapplicationeli.presenters.PassengerPresenter;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.tools.datetools.DateUtil;
import com.reserv.myapplicationeli.views.adapters.insurance.PassengerAdapter;
import com.reserv.myapplicationeli.views.components.SimpleRecycleView;
import com.reserv.myapplicationeli.views.ui.InitUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elham.bonyani on 1/14/2018.
 */

public class AddPassengerActivity extends BaseActivity implements
        View.OnClickListener,
        PassengerContract.View,
        TimePickerDialog.OnTimeSetListener,
        com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener{

    public SimpleRecycleView rcl_add_passenger;
    public ImageView btn_add;
    public ImageView btn_remove;
    private Button btn_confirm;
    private TextView count_passenger;
    public PassengerAdapter passengerAdapter;
    public PassengerPresenter passengerPresenter;
    DatePickerDialog datePickerDialogBirthDay;
    int month;
    int year_;
    int day;
    private BirthDateList currentPassenger;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_passenger);
        InitUi.Toolbar(this, false, R.color.app_base_color, "اطلاعات مسافر");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getColor(R.color.colorPrimary));
        }
        initViews();
        passengerPresenter = new PassengerPresenter(this);
        ArrayList<BirthDateList> passengerArrayList = null;
        Bundle bundle = getIntent().getExtras();
        Gson gson = new GsonBuilder().create();
        if (bundle != null) {
            passengerArrayList = gson.fromJson(bundle.getString("BirthDateList"), new TypeToken<List<BirthDateList>>() {
            }.getType());
        }
        passengerPresenter.setPassengers(passengerArrayList);
        showPassengers();

    }

    @Override
    public Context getAppContext() {
        return this;
    }

    @Override
    public void initViews() {

        count_passenger = (TextView) findViewById(R.id.count_passenger);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_add = (ImageView) findViewById(R.id.btn_add);
        btn_remove = (ImageView) findViewById(R.id.btn_remove);
        rcl_add_passenger = (SimpleRecycleView) findViewById(R.id.rcl_add_passenger);
        rcl_add_passenger.setLayoutManager(new LinearLayoutManager(getAppContext()));
        hideLoading();

        String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");

        int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
        int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true);
        int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true) - 1;


        datePickerDialogBirthDay = DatePickerDialog.newInstance(
                this,
                currentYear - 100,
                0,
                1
        );
        PersianCalendar persianCalendarDatePicker = new PersianCalendar();
        persianCalendarDatePicker.setPersianDate(currentYear,currentMonth ,currentDay);
        datePickerDialogBirthDay.setMaxDate(persianCalendarDatePicker);


        btn_add.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public void showLoading() {
        rcl_add_passenger.showLoading();
    }

    @Override
    public void hideLoading() {
        rcl_add_passenger.hideLoading();
    }

    @Override
    public void showPassengers() {
        passengerAdapter = new PassengerAdapter(passengerPresenter);
        rcl_add_passenger.showList(passengerAdapter);
    }

    @Override
    public void setPassengersCount(int count) {
        count_passenger.setText("( " + count + " )");
    }

    @Override
    public void notifyDataSetChange() {
        if (passengerAdapter != null) {
            passengerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void notifyItemInserted(int layoutPosition) {
        if (passengerAdapter != null) {
            passengerAdapter.notifyItemInserted(layoutPosition);
        }
    }

    @Override
    public void notifyItemRemoved(int layoutPosition) {
        if (passengerAdapter != null) {
            passengerAdapter.notifyItemRemoved(layoutPosition);
        }
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        if (passengerAdapter != null) {
            passengerAdapter.notifyItemRangeChanged(positionStart,itemCount);
        }
    }

    @Override
    public void onSetBirthDayPassenger(BirthDateList passenger) {
        currentPassenger = passenger;
        datePickerDialogBirthDay.show(getSupportFragmentManager(), "BirthDay");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.btn_add:
                passengerPresenter.addPassengers();
                break;
            case R.id.btn_remove:
                passengerPresenter.removePassengers();
                break;
            case R.id.btn_confirm:
                if(!isValidPassengers(passengerPresenter.getPassengers())){
                    Toast.makeText(this, "لطفا تاریخ تولد مسافران را وارد نمایید .", Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new GsonBuilder().create();
                Intent intent = new Intent();
                intent.putExtra("BirthDateList",gson.toJson(passengerPresenter.getPassengers()));
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    private boolean isValidPassengers(ArrayList<BirthDateList> passengers) {
        if(ValidationTools.isEmptyOrNull(passengers)){
            return false;
        }
        for(BirthDateList passenger : passengers){
            if(ValidationTools.isEmptyOrNull(passenger.getBirthDate())){
                return false;
            }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

    }





    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;

        long milis = DateUtil.getMiliSecondPersianDateTime(year, monthOfYear, dayOfMonth);
        String currentDateTime = DateUtil.getDateTime(String.valueOf(milis), "yyyy-MM-dd");


        if (view.getTag().equals("BirthDay")) {

            passengerPresenter.setBirthday(currentPassenger,currentDateTime);
            datePickerDialogBirthDay.initialize(this, year_, month, day);

        }
    }
}
