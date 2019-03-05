package com.eligasht.reservation.views.adapters.train;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.models.Country;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.db.local.RecentCity_Table;

import java.util.List;


public class GetTrainMabdaAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater myInflater;
    private LinearLayout lnrSearch;
    //public CursorManager cursor;
    public int customerId;
    public String customerName;
    public int catt_ID = 0;
    private LayoutInflater inflater;
    private List<Country> data;

    public static String GET_FRAGMENT = null;
    Activity activity;



    public GetTrainMabdaAdapter(Activity activity) {
        this.context = activity;
        myInflater = LayoutInflater.from(context);
    }

    public GetTrainMabdaAdapter(Context context, List<Country> data, Activity activity) {
        this.activity = activity;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        myInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    public void setData(List<Country> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setData(String searchText) {
        this.data = data;
        notifyDataSetChanged();
    }

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
        long s = position + 1;
        //return data.getLong(Customers_Table.Columns.CUSTOMER_ID.value());
        return s;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = myInflater.inflate(R.layout.row_airport, null);
            holder = new ViewHolder();

            holder.AirportName = convertView.findViewById(R.id.text1);
            holder.CityName = convertView.findViewById(R.id.text2);
            holder.txtIcon = convertView.findViewById(R.id.txtIcon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //cursor.moveToPosition(position);
        final Country current = data.get(position);
        holder.AirportName.setText(current.getText() + "");
        holder.CityName.setVisibility(View.GONE);
        holder.txtIcon.setVisibility(View.GONE);
        holder.CityName.setText("");

        holder.AirportName.setTag(current.getText());
        holder.AirportName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Prefs.putString("Value_Mabda_Train", current.getText());
                Prefs.putString("Value_Mabda_Key_Train", current.getValue());
                //RecentCity_Table recentCity_table = new RecentCity_Table(activity);
                //recentCity_table.insertData(current.getAirportName(), current.getCityName(), current.getAirportCode(), 1);//mabda

                /*  Prefs.putString("Value-Mabda-City", current.getCityName());
                Prefs.putString("Value-Mabda-City2", current.getCityName());
                Prefs.putString("Value-Mabda-Airport", current.getAirportName());
                Prefs.putString("Value-Mabda-Airport-Code", current.getAirportCode());

              Prefs.putString("Value-Maghsad-City", value_Maghsad_City);
                Prefs.putString("Value-Maghsad-Airport", value_Maghsad_Airport);
                Prefs.putString("Value-Maghsad-Airport-Code", value_Maghsad_Airport_Code);
                Prefs.putString("Value-Maghsad-Airport-Code2", value_Maghsad_Airport_Code);

                Prefs.putString("Value-Maghsad-Airport-Code2", value_Maghsad_Airport_Code);
                Prefs.putString("Value-Maghsad-Airport-Code2", current.getAirportCode());*/

                activity.finish();
            }
        });
        holder.CityName.setTag("");
        holder.CityName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Prefs.putString("Value_Mabda_Train", current.getText());
                Prefs.putString("Value_Mabda_Key_Train", current.getValue());
               // RecentCity_Table recentCity_table = new RecentCity_Table(activity);
               // recentCity_table.insertData(current.getAirportName(), current.getCityName(), current.getAirportCode(), 1);//mabda

                /* Prefs.putString("Value-Mabda-City", current.getCityName());
                Prefs.putString("Value-Mabda-City2", current.getCityName());
                Prefs.putString("Value-Mabda-Airport", current.getAirportName());
                Prefs.putString("Value-Mabda-Airport-Code", current.getAirportCode());
                Prefs.putString("Value-Mabda-Airport-Code2", current.getAirportCode());

               Prefs.putString("Value-Maghsad-City", value_Maghsad_City);
                Prefs.putString("Value-Maghsad-Airport", value_Maghsad_Airport);
                Prefs.putString("Value-Maghsad-Airport-Code", value_Maghsad_Airport_Code);
                Prefs.putString("Value-Maghsad-Airport-Code2", value_Maghsad_Airport_Code);

                Prefs.putString("Value-Maghsad-Airport-Code2", value_Maghsad_Airport_Code);
                Prefs.putString("Value-Maghsad-Airport-Code2", current.getAirportCode());*/
                View view = activity.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }else {
                    	Toast.makeText(v.getContext(),current.getCityName()+" "+current.getAirportName(),Toast.LENGTH_SHORT).show();

                }

                activity.finish();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView AirportName;
        TextView CityName;
        TextView txtIcon;


    }


}