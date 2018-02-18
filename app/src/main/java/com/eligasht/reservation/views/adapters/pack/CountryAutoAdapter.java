package com.eligasht.reservation.views.adapters.pack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elham.bonydni on 1/15/18.
 */

public class CountryAutoAdapter extends ArrayAdapter<Country> {

    Context context;
    int resource;
    int textViewResourceId;
    List<Country> mList, filteredCountry, mListAll;

    public CountryAutoAdapter(Context context, int resource, int textViewResourceId,
                         List<Country> mList) {
        super(context, resource, textViewResourceId, mList);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.mList = mList;
        mListAll = mList;
        filteredCountry = new ArrayList<Country>();
    }

    @Override
    public Country getItem(int position) {

        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_country_auto, parent, false);
            TextView textView = (TextView) view.findViewById(R.id.txtItem);
            textView.setText(mList.get(position).getCountryNameFa());
        }
        Country country = mList.get(position);
        if (country != null) {
            TextView textView = (TextView) view.findViewById(R.id.txtItem);
            if (textView != null) {
                textView.setText(country.getCountryNameFa());
            }

        }
        return view;
    }

    @Override
    public Filter getFilter() {

        return nameFilter;
    }

    Filter nameFilter = new Filter() {

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            List<Country> filteredList = (List<Country>) results.values;

            if (results != null && results.count > 0) {
                clear();
                for (Country country : filteredList) {
                    add(country);
                }
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null) {
                filteredCountry.clear();
                for (Country country : mListAll) {
                    if (country.getCountryNameFa().contains(constraint)) {
                        filteredCountry.add(country);
                    }
                }
                filterResults.values = filteredCountry;
                filterResults.count = filteredCountry.size();
            }
            return filterResults;
        }
    };
}
