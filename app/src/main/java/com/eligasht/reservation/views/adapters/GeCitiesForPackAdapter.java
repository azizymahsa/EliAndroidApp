package com.eligasht.reservation.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eligasht.R;
import com.orhanobut.hawk.Hawk;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;


public class GeCitiesForPackAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater myInflater;
    //public CursorManager cursor;
    public int customerId;
    public String customerName;
    public int catt_ID = 0;
    private LayoutInflater inflater;
    private List<com.eligasht.reservation.models.model.Country> data;
    public String value_Maghsad_City;
    public String value_Maghsad_Airport;
    public String value_Maghsad_Airport_Code;
    public static String GET_FRAGMENT = null;
    Activity activity;

    public GeCitiesForPackAdapter(Context context, ArrayList<com.eligasht.reservation.models.model.Country> data, Activity activity) {
        this.activity = activity;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        myInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    public void setData(List<com.eligasht.reservation.models.model.Country> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setData(String searchText) {
        this.data = data;
        notifyDataSetChanged();
    }

    /*
    public void setData(String searchText) {
        this.cursor = new Customers_Table().getCustomersFilter(searchText);
        //initiated = true;
        notifyDataSetChanged();
    }*/
    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }


    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        //data.moveToPosition(position);
        //Country current=data.get(position);
        long s = position + 1;
        //return data.getLong(Customers_Table.Columns.CUSTOMER_ID.value());
        return s;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            Log.e("POSITION", "" + position);
            convertView = myInflater.inflate(R.layout.row_country, null);
            holder = new ViewHolder();

            holder.countryName = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //cursor.moveToPosition(position);
        final com.eligasht.reservation.models.model.Country current = data.get(position);
        holder.countryName.setText(current.getCountryNameFa() + "");

        holder.countryName.setTag(current.getCountryNameFa());
        holder.countryName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Hawk.put("Value-Insurance-Country", current);
                Prefs.putString("Value-Insurance-Country-Code", current.getCountryCode());
                Prefs.putInt("Value-Insurance-Country-Id", current.getCountryID());

                activity.finish();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView countryName;
    }


}