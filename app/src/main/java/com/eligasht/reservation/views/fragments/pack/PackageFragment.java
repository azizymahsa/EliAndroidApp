package com.eligasht.reservation.views.fragments.pack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.eligasht.R;
import com.eligasht.reservation.api.retro.ClientService;
import com.eligasht.reservation.api.retro.ServiceGenerator;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.HotelCity;
import com.eligasht.reservation.models.model.ModelRowCountRoom;
import com.eligasht.reservation.models.model.pack.ChildModel;
import com.eligasht.reservation.models.model.pack.call.CityListRq;
import com.eligasht.reservation.models.model.pack.call.CityRequestModel;
import com.eligasht.reservation.models.model.pack.response.CityListRes;
import com.eligasht.reservation.tools.Utility;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.datetools.DateUtil;
import com.eligasht.reservation.views.activities.AddRoomActivity;
import com.eligasht.reservation.views.activities.pack.SearchPackActivity;
import com.eligasht.reservation.views.picker.global.enums.TypeUsageOfCalendar;
import com.eligasht.reservation.views.picker.global.listeners.ICallbackCalendarDialog;
import com.eligasht.reservation.views.picker.global.model.CustomDate;
import com.eligasht.reservation.views.picker.global.model.SingletonDate;
import com.eligasht.reservation.views.picker.utils.CalendarDialog;
import com.eligasht.reservation.views.ui.GetCitiesForPackActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.orhanobut.hawk.Hawk;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


/**
 * Created by elham.bonyani on 1/2/2018.
 */

public class PackageFragment extends Fragment implements View.OnClickListener,
        TimePickerDialog.OnTimeSetListener,
        ICallbackCalendarDialog {


    private final int ADD_ROOM_REQUEST = 100;
    public ViewGroup view;
    public ViewGroup layout_room;
    public TextView txtCity;
    public TextView btnSearchPackage;
    public LinearLayout btn_return_date;
    public LinearLayout btn_depart_date, linear_picker_depart, linear_picker_return;
    DatePickerDialog datePickerDialogDepart, datePickerDialogReturn;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialogDepartgGregorian, datePickerDialogReturnGregorian;
    TextView txt_return_date;
    TextView txt_depart_date;
    boolean geo = false;
    LottieAnimationView lottieAnimationView;
    CustomDate startDate;
    CustomDate endDate;
    CalendarDialog calendarDialog;
    private ClientService service;
    private Gson gson;
    private ArrayList<ModelRowCountRoom> roomsSelected;
    private TextView txt_count_adult;
    private TextView txt_count_child;
    private TextView txt_count_room;
    private HotelCity hotelCity;
    private String departureFrom;
    private String departureTo;

    public static PackageFragment instance() {
        PackageFragment fragment = new PackageFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        hotelCity = Hawk.get("Value-Insurance-City", null);
        if (hotelCity != null && txtCity != null)
            txtCity.setText(hotelCity.getCityNameFa());
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = (ViewGroup) inflater.inflate(R.layout.fragment_package, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        SingletonDate.getInstance().checkConflictDate();

        Utility.sendTag("P", true, false);
        calendarDialog = new CalendarDialog();

        initViews();
        initParam();
        service = ServiceGenerator.createService(ClientService.class);
        getCities();
        try {
            roomsSelected = gson.fromJson(Prefs.getString("Rooms", "dd"), new TypeToken<List<ModelRowCountRoom>>() {
            }.getType());

            txt_count_adult.setText(String.valueOf(getCountAdult(roomsSelected)));
            txt_count_child.setText(String.valueOf(getCountChild(roomsSelected)));
            txt_count_room.setText(String.valueOf(getCountRooms(roomsSelected)));

        } catch (Exception e) {
        }

        return view;
    }

    //send request to server for get cities os spinner
    private void getCities() {
        showLoading();
        Call<CityListRes> call = service.getCityListResult(new CityRequestModel(new CityListRq(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"))));
        call.enqueue(new Callback<CityListRes>() {
            @Override
            public void onResponse(Call<CityListRes> call, Response<CityListRes> response) {
                hideLoading();
                if (response == null || response.body() == null) {
                    needShowAlertDialog(getString(R.string.error_in_connection), true);
                    return;
                }

                if (response.body().getGetHotelListResult() == null || response.body().getGetHotelListResult().getCities() == null) {
                    needShowAlertDialog(getString(R.string.there_is_no_city_to_show), true);
                    return;
                }
                try {
                    Hawk.put("PackCityData", response.body().getGetHotelListResult());
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<CityListRes> call, Throwable t) {
                try {
                    hideLoading();
                    needShowAlertDialog(getString(R.string.error_in_connection), true);
                } catch (Exception e) {
                }
            }
        });


    }

    private void initViews() {

        layout_room = view.findViewById(R.id.layout_room);
        txtCity = view.findViewById(R.id.txtCity);
        btnSearchPackage = view.findViewById(R.id.btnSearchPackage);
        btn_return_date = view.findViewById(R.id.btn_return_date);
        btn_depart_date = view.findViewById(R.id.btn_depart_date);
        txt_count_adult = view.findViewById(R.id.txt_count_adult);
        txt_count_child = view.findViewById(R.id.txt_count_child);
        txt_count_room = view.findViewById(R.id.txt_count_room);
        txt_return_date = view.findViewById(R.id.txt_return_date);
        txt_depart_date = view.findViewById(R.id.txt_depart_date);
        linear_picker_depart = view.findViewById(R.id.linear_picker_depart);
        linear_picker_return = view.findViewById(R.id.linear_picker_return);

        lottieAnimationView = view.findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("circle-l.json");
        txt_return_date.setText(SingletonDate.getInstance().getEndDate().getDescription());
        departureTo = SingletonDate.getInstance().getEndDate().getFullGeo();
        txt_depart_date.setText(SingletonDate.getInstance().getStartDate().getDescription());
        departureFrom = SingletonDate.getInstance().getStartDate().getFullGeo();
        gson = new GsonBuilder().create();

        layout_room.setOnClickListener(this);
        btnSearchPackage.setOnClickListener(this);
        linear_picker_return.setOnClickListener(this);
        linear_picker_depart.setOnClickListener(this);
        txtCity.setOnClickListener(this);


    }

    private void initParam() {

    }

    private void showLoading() {
        txtCity.setVisibility(View.GONE);
        lottieAnimationView.playAnimation();

    }

    private void hideLoading() {
        txtCity.setVisibility(View.VISIBLE);
        lottieAnimationView.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_room:
                Gson gson = new GsonBuilder().create();
                Intent intent = new Intent(getActivity(), AddRoomActivity.class);
                intent.putExtra("roomList", Prefs.getString("Rooms", "dd"));
                startActivityForResult(intent, ADD_ROOM_REQUEST);
                break;

            case R.id.btnSearchPackage:
                if (hotelCity == null) {
                    Toast.makeText(getActivity(), R.string.select_destination_city_first, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txt_depart_date.getText().toString().equals(getString(R.string.please_select_one)) && txt_return_date.getText().toString().equals(getString(R.string.please_select_one))) {
                    Toast.makeText(getActivity(), R.string.select_departure_and_return_date, Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (txt_depart_date.getText().toString().equals(getString(R.string.please_select_one))) {
                        Toast.makeText(getActivity(), getString(R.string.departure_date), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (txt_return_date.getText().toString().equals(getString(R.string.please_select_one))) {
                        Toast.makeText(getActivity(), R.string.return_date, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Intent _intent = new Intent(getActivity(), SearchPackActivity.class);
                _intent.putExtra("DepartureFrom", departureFrom);
                _intent.putExtra("DepartureTo", departureTo);
                _intent.putExtra("RoomList", getRoomList(roomsSelected));
                _intent.putExtra("Culture", "fa-IR");
                _intent.putExtra("Country", String.valueOf(hotelCity.getCityID()));
                _intent.putExtra("CityName", String.valueOf(ValidationTools.isEmptyOrNull(hotelCity.getCityNameFa()) ? hotelCity.getCityNameEn() : hotelCity.getCityNameFa()));
                startActivity(_intent);
                break;

            case R.id.linear_picker_depart:

                if (startDate != null && endDate != null) {
                    calendarDialog.create(getActivity(), getContext(), this, startDate, endDate, TypeUsageOfCalendar.HOTEL);

                } else {
                    calendarDialog.create(getActivity(), getContext(), this, true, TypeUsageOfCalendar.HOTEL);

                }

                break;

            case R.id.linear_picker_return:
                if (endDate != null) {
                    calendarDialog.create(getActivity(), getContext(), new ICallbackCalendarDialog() {
                        @Override
                        public void onDateSelected(CustomDate start, CustomDate end, boolean isGeo) {


                            if (CustomDate.isOlderThan(startDate.getCalendar(), start.getCalendar())) {

                                endDate = start;
                                txt_return_date.setText(endDate.getDescription());

                            } else {
                                Toast.makeText(getContext(), getContext().getString(R.string.end_date_must_be_more_than_start_date), Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, endDate, TypeUsageOfCalendar.HOTEL);

                } else {
                    calendarDialog.create(getActivity(), getContext(), new ICallbackCalendarDialog() {
                        @Override
                        public void onDateSelected(CustomDate start, CustomDate end, boolean isGeo) {

                            if (CustomDate.isOlderThan(startDate.getCalendar(), start.getCalendar())) {

                                endDate = start;
                                txt_return_date.setText(endDate.getDescription());

                            } else {
                                Toast.makeText(getContext(), getContext().getString(R.string.end_date_must_be_more_than_start_date), Toast.LENGTH_SHORT).show();

                            }
                        }
                    }, false, TypeUsageOfCalendar.HOTEL);

                }

                break;
            case R.id.txtCity:
                startActivity(new Intent(getActivity(), GetCitiesForPackActivity.class));
                break;
        }
    }


    //for get list that rooms contents of adult's count and age of children
    private String getRoomList(ArrayList<ModelRowCountRoom> roomsSelected) {
        String roomList = "";
        if (ValidationTools.isEmptyOrNull(roomsSelected)) {
            return "1,0,0,0,0,0";
        }

        for (ModelRowCountRoom room : roomsSelected) {
            roomList = roomList + room.getCountB() + ",";

            if (ValidationTools.isEmptyOrNull(room.getChildModels())) {
                roomList = roomList + "0,0,0,0,0";
                if (roomsSelected.indexOf(room) != (roomsSelected.size() - 1)) {
                    roomList = roomList + "|";
                }
                continue;
            }
            for (ChildModel childModel : room.getChildModels()) {
                roomList = roomList + childModel.getChildAgeRange().getValue();
                if (room.getChildModels().indexOf(childModel) != (room.getChildModels().size() - 1)) {
                    roomList = roomList + ",";
                }
            }
            if (room.getChildModels().size() < 5) {
                for (int i = 0; i < (5 - room.getChildModels().size()); i++) {
                    roomList = roomList + ",0";
                }
            }

            if (roomsSelected.indexOf(room) != (roomsSelected.size() - 1)) {
                roomList = roomList + "|";
            }

        }

        return roomList;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == ADD_ROOM_REQUEST && resultCode == RESULT_OK && intent != null) {
            Bundle bundle = intent.getExtras();
            roomsSelected = gson.fromJson(bundle.getString("Rooms"), new TypeToken<List<ModelRowCountRoom>>() {
            }.getType());

            txt_count_adult.setText(String.valueOf(getCountAdult(roomsSelected)));
            txt_count_child.setText(String.valueOf(getCountChild(roomsSelected)));
            txt_count_room.setText(String.valueOf(getCountRooms(roomsSelected)));

        }
    }

    private int getCountRooms(ArrayList<ModelRowCountRoom> rooms) {
        if (ValidationTools.isEmptyOrNull(rooms)) {
            return 0;
        }

        return rooms.size();
    }

    private int getCountAdult(ArrayList<ModelRowCountRoom> rooms) {
        if (ValidationTools.isEmptyOrNull(rooms)) {
            return 0;
        }

        int count = 0;
        for (ModelRowCountRoom room : rooms) {
            count = count + room.getCountB();
        }
        return count;
    }

    private int getCountChild(ArrayList<ModelRowCountRoom> rooms) {
        if (ValidationTools.isEmptyOrNull(rooms)) {
            return 0;
        }

        int count = 0;
        for (ModelRowCountRoom room : rooms) {
            count = count + room.getCountK();
        }
        return count;
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {

    }

    public void needShowAlertDialog(String message, boolean canelable) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  /*      if (mAlertDialog != null && mAlertDialog.isShowing()) {
            return;
        }
        mAlertDialog = new AlertDialog.Builder(getActivity()).create();
        final LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.alert_dialog_net, null);
        mAlertDialog.setCancelable(canelable);
        FancyButton btnOk = (FancyButton) view.findViewById(R.id.btnOk);
        TextView tvAlert = (TextView) view.findViewById(R.id.tvAlert);

        btnOk.setCustomTextFont(SingletonContext.getInstance().getContext().getResources().getString(R.string.iran_sans_normal_ttf));
        tvAlert.setText(message);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });

        mAlertDialog.setView(view);
        mAlertDialog.setCancelable(true);
        mAlertDialog.show();*/
    }

    @Override
    public void onDateSelected(CustomDate start, CustomDate end, boolean isGeo) {
        SingletonDate.getInstance().setReverseDate(startDate,endDate);

        startDate = start;
        endDate = end;
        departureFrom = startDate.getFullGeo();
        departureTo = endDate.getFullGeo();
        txt_return_date.setText(endDate.getDescription());
        txt_depart_date.setText(startDate.getDescription());



    }
}
