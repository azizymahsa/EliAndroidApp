package com.reserv.myapplicationeli.views.adapters.pack;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.pack.ChildAgeRange;

import java.util.ArrayList;


public class ChildAgeRangeAdapter extends ArrayAdapter<ChildAgeRange> {

    private ArrayList<ChildAgeRange> mValues;
    private Context mContext;


    public ChildAgeRangeAdapter(Context context, int resourceId, ArrayList<ChildAgeRange> values ) {
        super(context, resourceId, values);
        mValues = values;
        mContext = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row= View.inflate(mContext, R.layout.spinner_item_list, null);
        final ChildAgeRange childAgeRange = mValues.get(position);
        TextView txtItemSpinner=(TextView)row.findViewById(R.id.txtItemSpinner);
        txtItemSpinner.setText(childAgeRange.toString());
        return row;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = View.inflate(mContext,R.layout.spinner_item_top, null);
        TextView txtTopSpinner=(TextView)row.findViewById(R.id.txtTopSpinner);
        txtTopSpinner.setText(mValues.get(position).toString());
        return row;
    }
}
