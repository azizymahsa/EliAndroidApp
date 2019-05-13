package com.eligasht.reservation.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.models.Airport;
import com.eligasht.reservation.models.Country;
import com.eligasht.reservation.tools.db.local.RecentCity_Table;
import com.eligasht.reservation.views.ui.GetCountriesForInsuranceActivity;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.service.model.newModel.insurance.response.InsuranceCountry.ResponseInsuranceCountry;
import com.orhanobut.hawk.Hawk;
import com.eligasht.reservation.tools.Prefs;

import java.util.ArrayList;
import java.util.List;


public class GetCountriesForInsuranceAdapter extends RecyclerView.Adapter<GetCountriesForInsuranceAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater myInflater;
    //public CursorManager cursor;
    public int customerId;
    public String customerName;
    public int catt_ID = 0;
    private LayoutInflater inflater;
    private List<ResponseInsuranceCountry> data;
    public String value_Maghsad_City;
    public String value_Maghsad_Airport;
    public String value_Maghsad_Airport_Code;
    public static String GET_FRAGMENT = null;
    Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView AirportName, txtLongdes, txtTag;
        public TextView txtIconBaseFantastic, txtIconBaseLocation, txtIcon;
        public LinearLayout lnrAll;

        public MyViewHolder(View view) {
            super(view);
            //title = (TextView) view.findViewById(R.id.title);
            AirportName = (TextView)view.findViewById(R.id.text1);
            txtLongdes = (TextView)view.findViewById(R.id.txtLongdes);
            //	holder.txtShortDes = convertView.findViewById(R.id.txtShortDes);
            txtTag = (TextView)view.findViewById(R.id.txtTag);
            txtIconBaseFantastic = (TextView)view.findViewById(R.id.txtIconBaseFantastic);
            txtIconBaseLocation = (TextView)view.findViewById(R.id.txtIconBaseLocation);
            txtIcon = (TextView)view.findViewById(R.id.txtIcon);
            lnrAll = (LinearLayout)view.findViewById(R.id.lnrAll);
        }
    }
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
    @Override
    public GetCountriesForInsuranceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_airport, parent, false);

        return new GetCountriesForInsuranceAdapter.MyViewHolder(itemView);
    }

    public GetCountriesForInsuranceAdapter(Context context, List<ResponseInsuranceCountry> countryList, GetCountriesForInsuranceActivity activity) {
        this.activity = activity;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = countryList;
        myInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    public void setData(List<ResponseInsuranceCountry> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setData(String searchText) {
        this.data = data;
        notifyDataSetChanged();
    }




    @Override
    public long getItemId(int position) {

        long s = position + 1;

        return s;
    }
    @Override
    public void onBindViewHolder(GetCountriesForInsuranceAdapter.MyViewHolder holder, int position) {
        ResponseInsuranceCountry current = data.get(position);
        holder.AirportName.setText(current.getText() );
        // holder.txtShortDes.setVisibility(View.GONE);

        if(current.getTag().length()>0){
            holder.txtTag.setVisibility(View.VISIBLE);
            holder.txtTag.setText(current.getTag());
        }
        /*if(current.getShortDes().length()>0){
        holder.txtShortDes.setVisibility(View.VISIBLE);
            holder.txtShortDes.setText(current.getShortDes());
        }*/
        holder.txtLongdes.setTag(current.getLongDescription());
        if(current.getLongDescription().length()>0 ){
            holder.txtLongdes.setVisibility(View.VISIBLE);
            holder.txtIcon.setVisibility(View.VISIBLE);
            holder.txtLongdes.setText(Html.fromHtml(current.getLongDescription().toString()));
        }

        holder.txtIconBaseFantastic.setTag("/");
        holder.txtIconBaseLocation.setTag(SingletonContext.getInstance().getContext().getResources().getString(R.string.icon_location2));
        if(current.getIcon().contains("fligh")){
            holder.txtIconBaseFantastic.setVisibility(View.VISIBLE);
            holder.txtIconBaseFantastic.setText("/");//icon_flight
        }else if(current.getIcon().contains("hotel")){
            holder.txtIconBaseFantastic.setVisibility(View.VISIBLE);
            holder.txtIconBaseFantastic.setText("=");//icon_hotel
            holder.txtIcon.setVisibility(View.GONE);
           /* LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)holder.lnrAll.getLayoutParams();
            params.setMargins(0, 50, 50, 0);

            holder.lnrAll.setLayoutParams(params);*/
            // tv1.setLayoutParams(params);
            //holder.lnrAll.set
        }else if(current.getIcon().contains("location")){
            holder.txtIconBaseLocation.setVisibility(View.VISIBLE);
            holder.txtIconBaseLocation.setText(SingletonContext.getInstance().getContext().getResources().getString(R.string.icon_location2));//icon_location
            if(current.getIsSelectable()==false){
                holder.AirportName.setTextColor(Color.parseColor("#a9a9a9"));
                holder.txtIconBaseLocation.setTextColor(Color.parseColor("#a9a9a9"));
                holder.txtIconBaseLocation.setLongClickable(false);
                holder.txtIconBaseLocation.setClickable(false);
                holder.AirportName.setLongClickable(false);
                holder.AirportName.setClickable(false);

            }
        }
        //  "IsSelectable": false,



        holder.AirportName.setTag(current.getText());
        holder.AirportName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(current.getIsSelectable()) {
                    Hawk.put("Value-Insurance-Country", current);
                    Prefs.putString("Value-Insurance-Country-Code", current.getCityCode());//getCountryCode());
                    Prefs.putInt("Value-Insurance-Country-Id", Integer.parseInt(current.getID()));//getCountryID());

                    activity.finish();
                }
            }
        });
        holder.txtLongdes.setTag(Html.fromHtml(current.getLongDescription().toString()));
        holder.txtLongdes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Hawk.put("Value-Insurance-Country", current);
                Prefs.putString("Value-Insurance-Country-Code", current.getCityCode());//getCountryCode());
                Prefs.putInt("Value-Insurance-Country-Id", Integer.parseInt(current.getID()));//getCountryID());

                activity.finish();

                View view = activity.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }else {
                    Toast.makeText(v.getContext(),current.getText()+" "+current.getValue(),Toast.LENGTH_SHORT).show();

                }

                activity.finish();
            }
        });
    }


    static class ViewHolder {
        TextView countryName;
    }


}