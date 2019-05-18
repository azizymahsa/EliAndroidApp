package com.eligasht.reservation.views.fragments.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.eligasht.R;
import com.eligasht.reservation.views.adapters.homeFeatureAdapter.HomeFeatureAdapter;
import com.eligasht.reservation.views.adapters.homeFeatureAdapter.HomeFeatureModels;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnClickListener{


    private View rootView;
    private RecyclerView rvFacility;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        rvFacility = rootView.findViewById(R.id.rvFacility);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        ArrayList<HomeFeatureModels> value=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HomeFeatureModels entry=new HomeFeatureModels();
            entry.setPropertyTitle("flight"+" : "+i);
            value.add(entry);
        }




           // rvFacility.addItemDecoration(new DividerItemDecoration(getContext(), 1));
            rvFacility.setLayoutManager(new GridLayoutManager(getContext(), 2));
            rvFacility.setHasFixedSize(true);
            rvFacility.setAdapter(new HomeFeatureAdapter(value, getActivity()));
            rvFacility.setNestedScrollingEnabled(false);


       // }

        return rootView;
    }//end oncreat

    @Override
    public void onResume() {

        Log.e("DEBUG", "onResume of HomeFragment");
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }



    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
           /* case R.id.txtPassNat:


                break;*/
        }
    }

}

