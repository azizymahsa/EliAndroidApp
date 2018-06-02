package com.eligasht.reservation.views.activities.login;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eligasht.R;
import com.eligasht.reservation.views.fragments.profile.EditProfileFragment;
/**
 * Created by Reza Nejati on 02,June,2018
 */
public class AddPassengerFragmnet extends Fragment implements View.OnClickListener {
    LinearLayout llAddPassenger;
    View view;
    public static AddPassengerFragmnet instance() {
        AddPassengerFragmnet fragment = new AddPassengerFragmnet();
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
        llAddPassenger.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llAddPassenger:
                break;
        }
    }
}
