package com.reserv.myapplicationeli.views.adapters.pack;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.HotelCity;

import java.util.ArrayList;

//cities spinner
public class CitySpinnerAdapter extends ArrayAdapter<HotelCity> {

    private ArrayList<HotelCity> mValues;
    private Context mContext;


    public CitySpinnerAdapter(Context context, int resourceId, ArrayList<HotelCity> values ) {
        super(context, resourceId, values);
        mValues = values;
        mContext = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row= View.inflate(mContext, R.layout.spinner_item_list, null);
        final HotelCity hotelCity = mValues.get(position);
        TextView txtItemSpinner=(TextView)row.findViewById(R.id.txtItemSpinner);
        txtItemSpinner.setText(hotelCity.getCityNameFa());
        return row;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = View.inflate(mContext, R.layout.spinner_city_top, null);
        TextView txtTopSpinner=(TextView)row.findViewById(R.id.txtTopSpinner);
        txtTopSpinner.setText(mValues.get(position).getCityNameFa());
        return row;
    }
}
