package com.eligasht.reservation.views.activities.login;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eligasht.R;
import com.eligasht.reservation.models.PassengerDBModel;
import com.eligasht.reservation.models.db.NotificationModel;
import com.eligasht.reservation.views.activities.addPassenger.SavePassengerActivity;
import com.eligasht.reservation.views.adapters.addpassenge.AddPassengerAdapter;
import com.eligasht.reservation.views.adapters.notification.NotificationAdapter;

import java.util.Collections;
import java.util.List;
/**
 * Created by Reza Nejati on 02,June,2018
 */
public class AddPassengerFragment extends Fragment implements View.OnClickListener {
    LinearLayout llAddPassenger;
    View view;
    RecyclerView recyclerView;



    public static AddPassengerFragment instance() {
        AddPassengerFragment fragment = new AddPassengerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_add_passenger, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        initViews();
        return view;
    }
    public void initViews(){
        llAddPassenger=view.findViewById(R.id.llAddPassenger);
        recyclerView=view.findViewById(R.id.recyclerView);
        llAddPassenger.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llAddPassenger:
                startActivity(new Intent(getActivity(), SavePassengerActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onData();
    }
    public void onData(){

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<PassengerDBModel> passengerDBModels=PassengerDBModel.listAll(PassengerDBModel.class);
        Collections.reverse(passengerDBModels);
        recyclerView.setAdapter(new AddPassengerAdapter(passengerDBModels));


    }
}
