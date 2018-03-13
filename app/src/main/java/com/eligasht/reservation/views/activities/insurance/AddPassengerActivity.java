package com.eligasht.reservation.views.activities.insurance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.reservation.tools.Utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.contracts.PassengerContract;
import com.eligasht.reservation.models.model.insurance.BirthDateList;
import com.eligasht.reservation.presenters.PassengerPresenter;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.views.adapters.insurance.PassengerAdapter;
import com.eligasht.reservation.views.components.SimpleRecycleView;
import com.eligasht.reservation.views.ui.InitUi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by elham.bonyani on 1/14/2018.
 * use mvp model for add passenger->go to passenger presenter
 */

public class AddPassengerActivity extends BaseActivity implements
        View.OnClickListener,
        PassengerContract.View,
        TimePickerDialog.OnTimeSetListener,
        com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener, com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

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
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogDepartgGregorian;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_passenger);
        InitUi.Toolbar(this, false, R.color.toolbar_color, getString(R.string.PassengerSpec));
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar_color));
        }


        initViews();
        passengerPresenter = new PassengerPresenter(this);
        ArrayList<BirthDateList> passengerArrayList = null;
        Bundle bundle = getIntent().getExtras();
        //get birthdayDate of passenger
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

        //shamsi
        datePickerDialogBirthDay = DatePickerDialog.newInstance(
                this,
                currentYear - 100,
                0,
                1
        );
        datePickerDialogBirthDay.setYearRange(1330, currentYear);
        datePickerDialogBirthDay.setTitle(getString(R.string.Brithday));
        datePickerDialogDepartgGregorian = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog(2);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        datePickerDialogDepartgGregorian.initialize(this,year - 50,0,1);
        datePickerDialogDepartgGregorian.setYearRange(1940, year);


        datePickerDialogBirthDay.setOnCalandarChangeListener(new DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogDepartgGregorian.show(getFragmentManager(), "DepartureFromGregorian");
            }
        });

        datePickerDialogDepartgGregorian.setOnCalandarChangeListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogBirthDay.show(getSupportFragmentManager(), "DepartureFrom");
            }
        });
        datePickerDialogDepartgGregorian.setOnDateSetListener(this);



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
            passengerAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Prefs.putBoolean("pasGe", false);
    }

    @Override
    public void onSetBirthDayPassenger(BirthDateList passenger) {
        if (Prefs.getBoolean("pasGe", false)) {
            if (!datePickerDialogDepartgGregorian.isAdded()){
                datePickerDialogDepartgGregorian.show(getFragmentManager(), "DepartureFromGregorian");
                currentPassenger = passenger;
            }


        } else {
            if (!datePickerDialogBirthDay.isAdded()){
            datePickerDialogBirthDay.show(getSupportFragmentManager(), "BirthDay");}

            currentPassenger = passenger;
        }

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
                if (!isValidPassengers(passengerPresenter.getPassengers())) {
                    Toast.makeText(this, R.string.brithdayError, Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new GsonBuilder().create();
                Intent intent = new Intent();
                intent.putExtra("BirthDateList", gson.toJson(passengerPresenter.getPassengers()));
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    private boolean isValidPassengers(ArrayList<BirthDateList> passengers) {
        if (ValidationTools.isEmptyOrNull(passengers)) {
            return false;
        }
        for (BirthDateList passenger : passengers) {
            if (ValidationTools.isEmptyOrNull(passenger.getBirthDate())) {
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


    //shamsi
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
        Prefs.putBoolean("pasGe", false);

        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;

        long milis = DateUtil.getMiliSecondPersianDateTime(year, monthOfYear, dayOfMonth);
        String currentDateTime = DateUtil.getDateTime(String.valueOf(milis), "yyyy-MM-dd");


        if (view.getTag().equals("BirthDay")) {
            passengerPresenter.setBirthday(currentPassenger, Utility.convertNumbersToEnglish(currentDateTime) ,false);
            datePickerDialogBirthDay.initialize(this, year_, month, day);

        }
    }

    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
        Prefs.putBoolean("pasGe", true);
        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;






        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;
        long milis = DateUtil.getMiliSecondGregorianDateTime(year + "-" + (monthOfYear) + "-" + dayOfMonth, "yyyy-MM-dd");

        String currentDateTime = DateUtil.getDateTime(String.valueOf(milis), "yyyy-MM-dd");


        passengerPresenter.setBirthday(currentPassenger, Utility.convertNumbersToEnglish(currentDateTime),true);


    }
}
