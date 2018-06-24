package com.eligasht.reservation.views.activities;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.eligasht.R;
import com.eligasht.reservation.base.BaseActivity;
import com.eligasht.reservation.models.PassengerDBModel;
import com.eligasht.reservation.views.adapters.addpassenge.AddPassengerAdapter;
import com.eligasht.reservation.views.ui.InitUi;

import java.util.Collections;
import java.util.List;
/**
 * Created by Reza Nejati on 12,June,2018
 */
public class GetPassengerActivity extends BaseActivity {
    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_passenger);
        InitUi.Toolbar(this, false, R.color.toolbar_color, getString(R.string.select_passenger));
        initViews();
    }

    public void initViews(){
        recyclerView=findViewById(R.id.recyclerView);
    }

    @Override
    public boolean needTerminate() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        onData();
    }
    public void onData(){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<PassengerDBModel> passengerDBModels=PassengerDBModel.listAll(PassengerDBModel.class);
        Collections.reverse(passengerDBModels);
        recyclerView.setAdapter(new AddPassengerAdapter(passengerDBModels,true,this));


    }
}
