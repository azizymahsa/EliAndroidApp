package com.reserv.myapplicationeli.views.activities.hotel.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.hotel.SelectHotelModel;
import com.reserv.myapplicationeli.views.adapters.LazyResoultHotelAdapter;
import com.reserv.myapplicationeli.views.components.Header;
import com.reserv.myapplicationeli.views.ui.InitUi;

import java.util.ArrayList;


public class SelectHotelActivity extends BaseActivity implements OnClickListener {


    private ListView list;
    private LazyResoultHotelAdapter adapter;
    private ArrayList<SelectHotelModel> selectHotelModelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        InitUi.Toolbar(this, false, R.color.color_hotel, " چهارشنبه 28 اسفند-دوشنبه 5 فروردین ");
        Window window = getWindow();
        window.setStatusBarColor(getColor(R.color.color_hotel_dark));
        list = (ListView) findViewById(R.id.lvHoteResult);

        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        selectHotelModelArrayList.add(new SelectHotelModel("1"));
        adapter = new LazyResoultHotelAdapter(selectHotelModelArrayList,this,this );
        list.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtBack:

                break;

        }
    }





}
