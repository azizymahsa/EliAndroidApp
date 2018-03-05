package com.eligasht.reservation.views.fragments.insurance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.models.model.Country;
import com.eligasht.reservation.models.model.insurance.BirthDateList;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.views.activities.insurance.AddPassengerActivity;
import com.eligasht.reservation.views.activities.insurance.SearchInsuranceActivity;
import com.eligasht.reservation.views.dialogs.NumberPickerDialog;
import com.eligasht.reservation.views.ui.GetCountriesForInsuranceActivity;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

import static android.app.Activity.RESULT_OK;


/**
 * Created by elham.bonyani on 1/14/2018.
 */

public class InsuranceFragment extends Fragment implements View.OnClickListener, NumberPickerDialog.NumberPickerListener,
        TimePickerDialog.OnTimeSetListener,
        com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener, com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

    private final int ADD_PASSENGER_REQUEST = 101;
    public ViewGroup view;
    public ViewGroup layout_passenger;
    public ViewGroup layout_depart_date;
    public ProgressBar prg_country;
    public TextView txtCity;
    NumberPicker numberPicker;
    LinearLayout layout_duringTrip;
    TextView txt_during_trip;
    TextView txt_count_passenger;
    DatePickerDialog datePickerDialogDepart;
    int month;
    int year_;
    int day;
    int monthMin;
    int year_Min;
    int dayMin;
    Country country;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogDepartgGregorian;
    AlertDialog mAlertDialog;
    private ArrayList<BirthDateList> passengers;
    private Gson gson;
    private String departureDate;
    private TextView txt_depart_date;
    private ClientService service;
    private TextView btnSearchInsurance;
    private int accomodationDays;

    public static InsuranceFragment instance() {
        InsuranceFragment fragment = new InsuranceFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        country = Hawk.get("Value-Insurance-Country",null);
        if (country!=null && txtCity!=null)
            txtCity.setText(country.getCountryNameFa());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    //send request to server for get cities

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view != null) {
            return view;
        }
        view = (ViewGroup) inflater.inflate(R.layout.fragment_insurance, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        Utility.sendTag("I", true, false);

        initViews();
        initParam();
        service = ServiceGenerator.createService(ClientService.class);
        return view;
    }

    private void initParam() {
    }

    private void initViews() {
        layout_duringTrip = view.findViewById(R.id.layout_during_travel);
        txt_count_passenger = view.findViewById(R.id.txt_count_passenger);
        txt_during_trip = view.findViewById(R.id.txt_during_trip);
        btnSearchInsurance = view.findViewById(R.id.btnSearchInsurance);
        txtCity = view.findViewById(R.id.txtCity);

        layout_passenger = view.findViewById(R.id.layout_passenger);
        txt_depart_date = view.findViewById(R.id.txt_start_date);
        layout_depart_date = view.findViewById(R.id.layout_depart_date);
        gson = new GsonBuilder().create();

        String currentDateTime = DateUtil.getDateTime(String.valueOf(System.currentTimeMillis()), "yyyy-MM-dd");
        departureDate = currentDateTime;

        int currentDay = DateUtil.getDayOfMonth(currentDateTime, "yyyy-MM-dd", true);
        int currentYear = DateUtil.getYear(currentDateTime, "yyyy-MM-dd", true);
        int currentMonth = DateUtil.getMonth(currentDateTime, "yyyy-MM-dd", true) - 1;

        txt_depart_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy-MM-dd", true));



        datePickerDialogDepart = DatePickerDialog.newInstance(
                this,
                currentYear,
                currentMonth,
                currentDay
        );

        //shamsi
        PersianCalendar persianCalendarDatePicker = new PersianCalendar();
        persianCalendarDatePicker.setPersianDate(currentYear, currentMonth, currentDay);

        datePickerDialogDepart.setMinDate(persianCalendarDatePicker);
        datePickerDialogDepartgGregorian = new com.wdullaer.materialdatetimepicker.date.DatePickerDialog(2);
        datePickerDialogDepartgGregorian.setMinDate(persianCalendarDatePicker.toGregorianCalendar());
        datePickerDialogDepart.setOnCalandarChangeListener(new DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogDepartgGregorian.show(getActivity().getFragmentManager(), "DepartureFromGregorian");
            }
        });
        datePickerDialogDepartgGregorian.setOnCalandarChangeListener(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(boolean isGregorian) {
                datePickerDialogDepart.show(getActivity().getSupportFragmentManager(), "DepartureFrom");
            }
        });
        datePickerDialogDepartgGregorian.setOnDateSetListener(this);

        layout_depart_date.setOnClickListener(this);
        layout_duringTrip.setOnClickListener(this);
        layout_passenger.setOnClickListener(this);
        btnSearchInsurance.setOnClickListener(this);
        txtCity.setOnClickListener(this);
        datePickerDialogDepart.setTitle(getString(R.string.please_select_start_trip_date));
    }

    private void showLoading() {
        prg_country.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        prg_country.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtCity:
                startActivity(new Intent(getActivity(), GetCountriesForInsuranceActivity.class));
                break;
            case R.id.layout_during_travel:
                NumberPickerDialog dialog = new NumberPickerDialog(getActivity(), this);
                break;

            case R.id.layout_passenger:

                Intent intent = new Intent(getActivity(), AddPassengerActivity.class);
                intent.putExtra("BirthDateList", gson.toJson(passengers));
                Prefs.putString("BirthDateListInsuranc", gson.toJson(passengers));//mahsa
                startActivityForResult(intent, ADD_PASSENGER_REQUEST);
                break;

            case R.id.layout_depart_date:
                if (!datePickerDialogDepart.isAdded()) {
                    datePickerDialogDepart.show(getActivity().getSupportFragmentManager(), "DepartureFrom");

                }
                break;
            case R.id.btnSearchInsurance:
                if (country == null) {
                    Toast.makeText(getActivity(), R.string.select_destination_country, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txt_depart_date.getText().toString().equals(getString(R.string.please_select_one)) && txt_during_trip.getText().toString().equals(getString(R.string.please_select_one))) {
                    Toast.makeText(getActivity(), R.string.select_departure_date_and_travel_duration, Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (txt_depart_date.getText().toString().equals(getString(R.string.please_select_one))) {
                        Toast.makeText(getActivity(), R.string.departure_date, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (txt_during_trip.getText().toString().equals(getString(R.string.please_select_one))) {
                        Toast.makeText(getActivity(), R.string.select_travel_duration, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (ValidationTools.isEmptyOrNull(passengers)) {
                    Toast.makeText(getActivity(), R.string.please_specify_traverels_birth_date, Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent _intent = new Intent(getActivity(), SearchInsuranceActivity.class);
                _intent.putExtra("BirthDateList", gson.toJson(passengers));
                Prefs.putString("BirthDateListInsuranc", gson.toJson(passengers));//mahsa
                _intent.putExtra("DepartureDate", departureDate);
                _intent.putExtra("Culture", "fa-IR");
                _intent.putExtra("CountryCode", country.getCountryCode());
                _intent.putExtra("CountryName", country.getCountryNameFa());
                _intent.putExtra("AccomodationDays", accomodationDays);
                startActivity(_intent);

        }

    }

    @Override
    public void onReturnValue(String type, int duration) {
        txt_during_trip.setText(type);
        accomodationDays = duration;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == ADD_PASSENGER_REQUEST && resultCode == RESULT_OK && intent != null) {
            Bundle bundle = intent.getExtras();

            passengers = gson.fromJson(bundle.getString("BirthDateList"), new TypeToken<List<BirthDateList>>() {
            }.getType());

            txt_count_passenger.setText(ValidationTools.isEmptyOrNull(passengers) ? getString(R.string.number_of_passengers) : passengers.size() + getString(R.string.passenger));

        }
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

    }

    //shamsi
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;

        long milis = DateUtil.getMiliSecondPersianDateTime(year, monthOfYear, dayOfMonth);
        String currentDateTime = DateUtil.getDateTime(String.valueOf(milis), "yyyy-MM-dd");


        if (view.getTag().equals("DepartureFrom")) {
            year_Min = year;
            monthMin = monthOfYear;
            dayMin = dayOfMonth;
            txt_depart_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy-MM-dd", true));
            departureDate = currentDateTime;
            departureDate = Utility.convertNumbersToEnglish(departureDate);

            PersianCalendar persianCalendarDatePicker = new PersianCalendar();
            persianCalendarDatePicker.setPersianDate(year_Min, monthMin, dayMin);
            datePickerDialogDepart.initialize(this, year_, month, day);

        }
        Log.e("packagetest2", departureDate);

    }

    //for show dialog
    public void needShowAlertDialog(String message, boolean canelable) {
        if (getActivity() == null) {
            return;
        }
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            return;
        }
        mAlertDialog = new AlertDialog.Builder(getActivity()).create();
        final LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.alert_dialog_net, null);
        mAlertDialog.setCancelable(canelable);
        FancyButton btnOk = view.findViewById(R.id.btnOk);
        TextView tvAlert = view.findViewById(R.id.tvAlert);

        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.irsans_ttf));
        tvAlert.setText(message);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });

        mAlertDialog.setView(view);
        mAlertDialog.setCancelable(true);
        mAlertDialog.show();
    }

    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int endYear, int endMonth, int endDay) {
        year_ = year;
        month = monthOfYear;
        day = dayOfMonth;
        String currentDateTime = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;//2018-01-16;
        txt_depart_date.setText(DateUtil.getLongStringDate(currentDateTime, "yyyy-MM-dd", false));
        departureDate = currentDateTime;
        departureDate = Utility.convertNumbersToEnglish(departureDate);
        Log.e("packagetest1", departureDate);
    }
}
