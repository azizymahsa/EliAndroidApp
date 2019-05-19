package com.eligasht.reservation.views.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.models.Airport;
import com.eligasht.reservation.models.Country;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.reservation.tools.db.local.RecentCity_Table;
import com.eligasht.reservation.views.ui.SingletonContext;

import java.util.List;


public class GetAirPortMabdaAdapter extends RecyclerView.Adapter<GetAirPortMabdaAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater myInflater;
    //public CursorManager cursor;
    public int customerId;
    public String customerName;
    public int catt_ID = 0;
    private LayoutInflater inflater;
    private List<Airport> data;
    public String value_Maghsad_City;
    public String value_Maghsad_Airport;
    public String value_Maghsad_Airport_Code;
    public String value_Maghsad_Airport_Code2;
    public static String GET_FRAGMENT = null;
    private boolean flag=false;
    Activity activity;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView AirportName, txtLongdes, txtTag;
        public TextView txtIconBaseFantastic, txtIconBaseLocation, txtIcon;
        public LinearLayout lnrAll;
        public RelativeLayout rvSpace;

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
            rvSpace = (RelativeLayout)view.findViewById(R.id.rvSpace);
        }
    }
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
    @Override
    public GetAirPortMabdaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_airport, parent, false);

        return new GetAirPortMabdaAdapter.MyViewHolder(itemView);
    }
    // create constructor to innitilize context and data sent from MainActivity
    public GetAirPortMabdaAdapter(Context context, List<Airport> data, String value_Maghsad_City, String value_Maghsad_Airport, String value_Maghsad_Airport_Code, String value_Maghsad_Airport_Code2, Activity activity) {
        this.activity = activity;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        myInflater = LayoutInflater.from(context);
        flag=false;
        this.value_Maghsad_City = value_Maghsad_City;
        this.value_Maghsad_Airport = value_Maghsad_Airport;
        this.value_Maghsad_Airport_Code = value_Maghsad_Airport_Code;
        this.value_Maghsad_Airport_Code2 = value_Maghsad_Airport_Code2;
    }



    public GetAirPortMabdaAdapter(Context context, List<Airport> data, Activity activity) {
        this.activity = activity;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        myInflater = LayoutInflater.from(context);
        flag=false;
        notifyDataSetChanged();
    }

    public void setData(List<Airport> data) {
        this.data = data;
        flag=false;
        notifyDataSetChanged();
    }

    public void setData(String searchText) {
        this.data = data;
        flag=false;
        notifyDataSetChanged();
    }



    @Override
    public long getItemId(int position) {
        long s = position + 1;
        //return data.getLong(Customers_Table.Columns.CUSTOMER_ID.value());
        return s;
    }
    @Override
    public void onBindViewHolder(GetAirPortMabdaAdapter.MyViewHolder holder, int position) {
        Airport current = data.get(position);
        holder.AirportName.setText(current.getTesxt() + " "+current.getShortDes());
        // holder.txtShortDes.setVisibility(View.GONE);

        if(current.getTag().length()>0){
            holder.txtTag.setVisibility(View.VISIBLE);
            holder.txtTag.setText(current.getTag());
        }
        /*if(current.getShortDes().length()>0){
        holder.txtShortDes.setVisibility(View.VISIBLE);
            holder.txtShortDes.setText(current.getShortDes());
        }*/
        holder.txtLongdes.setTag(current.getLongDes());
        if(current.getLongDes().length()>0 ){
            holder.txtLongdes.setVisibility(View.VISIBLE);
            holder.txtIcon.setVisibility(View.VISIBLE);
            holder.txtLongdes.setText(Html.fromHtml(current.getLongDes().toString()));
        }

        holder.txtIconBaseFantastic.setTag("/");
        holder.txtIconBaseLocation.setTag(SingletonContext.getInstance().getContext().getResources().getString(R.string.icon_location2));
        if(current.getIcon().contains("fligh")){
            if(flag)
                holder.rvSpace.setVisibility(View.GONE);
            else
                holder.rvSpace.setVisibility(View.VISIBLE);


            holder.txtIconBaseFantastic.setVisibility(View.VISIBLE);
            holder.txtIconBaseFantastic.setText("/");//icon_flight
        }else if(current.getIcon().contains("hotel")){
            holder.rvSpace.setVisibility(View.GONE);
            holder.txtIconBaseFantastic.setVisibility(View.VISIBLE);
            holder.txtIconBaseFantastic.setText("=");//icon_hotel
            holder.txtIcon.setVisibility(View.GONE);
           /* LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)holder.lnrAll.getLayoutParams();
            params.setMargins(0, 50, 50, 0);

            holder.lnrAll.setLayoutParams(params);*/
           // tv1.setLayoutParams(params);
            //holder.lnrAll.set
        }else if(current.getIcon().contains("location")){
            flag=true;
            holder.rvSpace.setVisibility(View.GONE);
            holder.txtIconBaseLocation.setVisibility(View.VISIBLE);
            holder.txtIconBaseLocation.setText(SingletonContext.getInstance().getContext().getResources().getString(R.string.icon_location2));//icon_location
            if(current.isSelectable()==false){
                holder.AirportName.setTextColor(Color.parseColor("#b3b3b3"));
                holder.txtIconBaseLocation.setTextColor(Color.parseColor("#b3b3b3"));
                holder.txtIconBaseLocation.setLongClickable(false);
                holder.txtIconBaseLocation.setClickable(false);
                holder.AirportName.setLongClickable(false);
                holder.AirportName.setClickable(false);

            }
        }
        //  "IsSelectable": false,



        holder.AirportName.setTag(current.getTesxt());
        holder.AirportName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(current.isSelectable()) {
                    RecentCity_Table recentCity_table = new RecentCity_Table(activity);
                    // recentCity_table.insertData(current.getTesxt(), current.getShortDes(), current.getLongDes(), 1);//mabda
                    int flagSelect=1;
                    if(current.isSelectable())
                        flagSelect=1;
                    else
                        flagSelect=0;

                    recentCity_table.insertData(current.getTesxt(), current.getTextFa(), current.getShortDes()
                            , current.getLongDes(), current.getTag(), current.getIcon()
                            , current.getIconDown(),flagSelect,current.getValue(),1);//mabda

                    Prefs.putString("Value-Mabda-City", current.getTesxt());
                    Prefs.putString("Value-Mabda-City2", current.getTesxt());
                    Prefs.putString("Value-Mabda-Airport", current.getTesxt());
                    Prefs.putString("Value-Mabda-Airport-Code", current.getShortDes());
                    Prefs.putString("Value-Mabda-Airport-Code2", current.getValue());
                    //H+F
                   /* Prefs.putString("Value-Hotel-City-Fa-HF-Raft", current.getTesxt()+" , "+current.getShortDes());
                    Prefs.putString("Value-Hotel-City-En-HF-Raft", current.getTesxt());*/
                    Prefs.putString("Value-Hotel-City-Code-HF-Source", current.getValue());

                    Prefs.putString("Value-Maghsad-City", value_Maghsad_City);
                    Prefs.putString("Value-Maghsad-Airport", value_Maghsad_Airport);
                    Prefs.putString("Value-Maghsad-Airport-Code", value_Maghsad_Airport_Code);
                    Prefs.putString("Value-Maghsad-Airport-Code2", value_Maghsad_Airport_Code2);
                    //H+F
                   /* Prefs.putString("Value-Hotel-City-Fa-HF-Source", value_Maghsad_City);
                    Prefs.putString("Value-Hotel-City-En-HF-Source", value_Maghsad_Airport_Code);*/
                    Prefs.putString("Value-Hotel-City-Code-HF-Raft", value_Maghsad_Airport_Code2);


                    activity.finish();
                }
            }
        });
        holder.txtLongdes.setTag(Html.fromHtml(current.getLongDes().toString()));
        holder.txtLongdes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RecentCity_Table recentCity_table = new RecentCity_Table(activity);
                // recentCity_table.insertData(current.getTesxt(), current.getTesxt(), current.getShortDes(), 1);//mabda

                int flagSelect=1;
                if(current.isSelectable())
                    flagSelect=1;
                else
                    flagSelect=0;

                recentCity_table.insertData(current.getTesxt(), current.getTextFa(), current.getShortDes()
                        , current.getLongDes(), current.getTag(), current.getIcon()
                        , current.getIconDown(),flagSelect,current.getValue(),1);//mabda

                Prefs.putString("Value-Mabda-City", current.getTesxt());
                Prefs.putString("Value-Mabda-City2", current.getTesxt());
                Prefs.putString("Value-Mabda-Airport", current.getTesxt());
                Prefs.putString("Value-Mabda-Airport-Code", current.getShortDes());
                Prefs.putString("Value-Mabda-Airport-Code2", current.getValue());
               /* //H+F
                Prefs.putString("Value-Hotel-City-Fa-HF-Raft", current.getTesxt()+" , "+current.getShortDes());
                Prefs.putString("Value-Hotel-City-En-HF-Raft", current.getTesxt());*/
                Prefs.putString("Value-Hotel-City-Code-HF-Source", current.getValue());

                Prefs.putString("Value-Maghsad-City", value_Maghsad_City);
                Prefs.putString("Value-Maghsad-Airport", value_Maghsad_Airport);
                Prefs.putString("Value-Maghsad-Airport-Code", value_Maghsad_Airport_Code);
                Prefs.putString("Value-Maghsad-Airport-Code2", value_Maghsad_Airport_Code2);
             /*   //H+F
                Prefs.putString("Value-Hotel-City-Fa-HF-Source", value_Maghsad_City);
                Prefs.putString("Value-Hotel-City-En-HF-Source", value_Maghsad_Airport_Code);*/
                Prefs.putString("Value-Hotel-City-Code-HF-Raft", value_Maghsad_Airport_Code2);

              /*  Prefs.putString("Value-Maghsad-Airport-Code2", value_Maghsad_Airport_Code);
                Prefs.putString("Value-Maghsad-Airport-Code2", current.getShortDes());*/
                View view = activity.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }else {
                    Toast.makeText(v.getContext(),current.getTesxt()+" "+current.getTesxt(),Toast.LENGTH_SHORT).show();

                }

                activity.finish();
            }
        });
    }


    static class ViewHolder {
        TextView AirportName;
        TextView txtLongdes;
        TextView txtShortDes;
        TextView txtTag;
        TextView txtIcon;
        TextView txtIconBaseFantastic;
        TextView txtIconBaseLocation;

    }


}