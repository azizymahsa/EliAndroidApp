package com.reserv.myapplicationeli.views.adapters.pack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.reserv.myapplicationeli.models.Country;
import com.reserv.myapplicationeli.views.viewholders.CityRowHolder;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/6/2018.
 */
//cities
public class CityAdapter extends RecyclerView.Adapter<CityRowHolder>{

    private Context context;
    private ArrayList<Country> countries;

    public CityAdapter(Context context, ArrayList<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    @Override
    public CityRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CityRowHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
