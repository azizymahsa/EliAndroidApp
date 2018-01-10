package com.reserv.myapplicationeli.views.fragments.pack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.api.retro.ClientService;
import com.reserv.myapplicationeli.api.retro.ServiceGenerator;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.model.HotelCity;
import com.reserv.myapplicationeli.models.model.ModelRowCountRoom;
import com.reserv.myapplicationeli.models.model.pack.ChildModel;
import com.reserv.myapplicationeli.models.model.pack.call.CityListRq;
import com.reserv.myapplicationeli.models.model.pack.call.CityRequestModel;
import com.reserv.myapplicationeli.models.model.pack.response.CityListRes;
import com.reserv.myapplicationeli.tools.ValidationTools;
import com.reserv.myapplicationeli.views.activities.pack.AddRoomActivity;
import com.reserv.myapplicationeli.views.activities.pack.SearchPackActivity;
import com.reserv.myapplicationeli.views.adapters.pack.CitySpinnerAdapter;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.DatePickerDialogPrivate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


/**
 * Created by elham.bonyani on 1/2/2018.
 */

public class PackageFragment extends Fragment implements View.OnClickListener {


    public ViewGroup view;
    public ViewGroup layout_room;
    public Spinner spn_cities;
    public ProgressBar prg_cities;
    public Button btnSearchPackage;
    public Button btn_return_date;
    public Button btn_depart_date;
    private final int ADD_ROOM_REQUEST = 100;
    private ClientService service;
    DatePickerDialogPrivate datePickerDialogPrivateDepart, datePickerDialogPrivateReturn;
    private Gson gson;
    private ArrayList<ModelRowCountRoom> roomsSelected;
    private TextView txt_count_adult;
    private TextView txt_count_child;
    private TextView txt_count_room;
    private HotelCity hotelCity;

    public static PackageFragment instance() {
        PackageFragment fragment = new PackageFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
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
        initViews();
        initParam();
        service = ServiceGenerator.createService(ClientService.class);
        getCities();
        return view;
    }

    private void getCities() {
        showLoading();
        Call<CityListRes> call = service.getCityListResult(new CityRequestModel(new CityListRq(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"))));
        call.enqueue(new Callback<CityListRes>() {
            @Override
            public void onResponse(Call<CityListRes> call, Response<CityListRes> response) {
                hideLoading();
                if (response == null || response.body() == null || response.body().getGetHotelListResult() == null || response.body().getGetHotelListResult().getCities() == null) {
                    return;
                }


                CitySpinnerAdapter citySpinnerAdapter = new CitySpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, response.body().getGetHotelListResult().getCities());
                spn_cities.setAdapter(citySpinnerAdapter);
            }

            @Override
            public void onFailure(Call<CityListRes> call, Throwable t) {
                hideLoading();
                Toast.makeText(getActivity(), "خطا در ارتباط", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initViews() {

        layout_room = (ViewGroup) view.findViewById(R.id.layout_room);
        spn_cities = (Spinner) view.findViewById(R.id.spn_cities);
        prg_cities = (ProgressBar) view.findViewById(R.id.prg_cities);
        btnSearchPackage = (Button) view.findViewById(R.id.btnSearchPackage);
        btn_return_date = (Button) view.findViewById(R.id.btn_return_date);
        btn_depart_date = (Button) view.findViewById(R.id.btn_depart_date);
        txt_count_adult = (TextView) view.findViewById(R.id.txt_count_adult);
        txt_count_child = (TextView) view.findViewById(R.id.txt_count_child);
        txt_count_room = (TextView) view.findViewById(R.id.txt_count_room);

        gson = new GsonBuilder().create();

        layout_room.setOnClickListener(this);
        btnSearchPackage.setOnClickListener(this);
        btn_return_date.setOnClickListener(this);
        btn_depart_date.setOnClickListener(this);

        spn_cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                hotelCity = (HotelCity) spn_cities.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void initParam() {

    }

    private void showLoading() {
        spn_cities.setVisibility(View.GONE);
        prg_cities.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        prg_cities.setVisibility(View.GONE);
        spn_cities.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_room:
                Intent intent = new Intent(getActivity(), AddRoomActivity.class);
                startActivityForResult(intent, ADD_ROOM_REQUEST);
                break;

            case R.id.btnSearchPackage:

                if (btn_depart_date.getText().toString().equals("انتخاب کنید") && btn_return_date.getText().toString().equals("انتخاب کنید")) {
                    Toast.makeText(getActivity(), "تاریخ رفت و برگشت را انتخاب کنید", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (btn_depart_date.getText().toString().equals("انتخاب کنید")) {
                        Toast.makeText(getActivity(), "تاریخ رفت ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (btn_return_date.getText().toString().equals("انتخاب کنید")) {
                        Toast.makeText(getActivity(), "تاریخ برگشت ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Intent _intent = new Intent(getActivity(), SearchPackActivity.class);
                _intent.putExtra("DepartureFrom", datePickerDialogPrivateDepart.getDate());
                _intent.putExtra("DepartureTo", datePickerDialogPrivateDepart.getDate());
                _intent.putExtra("RoomList",getRoomList(roomsSelected));
                Log.e("roooms",  getRoomList(roomsSelected)+"");
                _intent.putExtra("Culture","fa-IR");
                _intent.putExtra("Country",String.valueOf(hotelCity.getCityID()));
                _intent.putExtra("CityName",String.valueOf(ValidationTools.isEmptyOrNull(hotelCity.getCityNameFa())?hotelCity.getCityNameEn():hotelCity.getCityNameFa()));
                startActivity(_intent);
                break;

            case R.id.btn_depart_date:
                datePickerDialogPrivateDepart = new DatePickerDialogPrivate(getActivity(), btn_depart_date, "تاریخ رفت");
                break;

            case R.id.btn_return_date:
                datePickerDialogPrivateReturn = new DatePickerDialogPrivate(getActivity(), btn_return_date, "تاریخ برگشت");
                break;
        }
    }

    private String getRoomList(ArrayList<ModelRowCountRoom> roomsSelected) {
        String roomList = "";
        if(ValidationTools.isEmptyOrNull(roomsSelected)){
            return "1,0,0,0,0,0";
        }

        for (ModelRowCountRoom room : roomsSelected){
            roomList = roomList + room.getCountB() + "," ;

            if(ValidationTools.isEmptyOrNull(room.getChildModels())){
                roomList = roomList + "0,0,0,0,0";
                if(roomsSelected.indexOf(room) != (roomsSelected.size() -1)){
                    roomList = roomList + "|";
                }
                continue;
            }
            for(ChildModel childModel : room.getChildModels()){
                roomList = roomList + childModel.getChildAgeRange().getValue();
                if(room.getChildModels().indexOf(childModel) != (room.getChildModels().size() -1)){
                    roomList = roomList + ",";
                }
            }
            if(room.getChildModels().size()<5){
                for(int i =0;i<(5 - room.getChildModels().size());i++){
                    roomList = roomList + ",0";
                }
            }

            if(roomsSelected.indexOf(room) != (roomsSelected.size() -1)){
                roomList = roomList + "|";
            }

        }

        return roomList;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == ADD_ROOM_REQUEST && resultCode == RESULT_OK && intent!=null){
            Bundle bundle = intent.getExtras();
            roomsSelected = gson.fromJson(bundle.getString("Rooms"), new TypeToken<List<ModelRowCountRoom>>() {
            }.getType());

            txt_count_adult.setText(String.valueOf(getCountAdult(roomsSelected)));
            txt_count_child.setText(String.valueOf(getCountChild(roomsSelected)));
            txt_count_room.setText(String.valueOf(getCountRooms(roomsSelected)));

        }
    }

    private int getCountRooms(ArrayList<ModelRowCountRoom> rooms){
        if(ValidationTools.isEmptyOrNull(rooms)){
            return 0;
        }

        return rooms.size();
    }

    private int getCountAdult(ArrayList<ModelRowCountRoom> rooms){
        if(ValidationTools.isEmptyOrNull(rooms)){
            return 0;
        }

        int count = 0;
        for (ModelRowCountRoom room:rooms){
            count = count + room.getCountB();
        }
        return count;
    }

    private int getCountChild(ArrayList<ModelRowCountRoom> rooms){
        if(ValidationTools.isEmptyOrNull(rooms)){
            return 0;
        }

        int count = 0;
        for (ModelRowCountRoom room:rooms){
            count = count + room.getCountK();
        }
        return count;
    }
}
