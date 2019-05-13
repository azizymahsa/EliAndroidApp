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
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eligasht.reservation.models.Airport;
import com.eligasht.reservation.tools.Prefs;
import com.eligasht.R;
import com.eligasht.reservation.models.Airport;
import com.eligasht.reservation.tools.db.local.RecentCity_Table;
import com.eligasht.reservation.views.ui.SingletonContext;
import com.eligasht.service.model.weather.response.Atmosphere;

import java.util.List;


public class GetAirPortMaghsadAdapter extends RecyclerView.Adapter<GetAirPortMaghsadAdapter.MyViewHolder> {

	private Context context;

	private LayoutInflater myInflater;
	//public CursorManager cursor;
	public int customerId;
	public String customerName;
	public int catt_ID=0;
	private LayoutInflater inflater;
	private List<Airport> data;
	public String value_Mabda_City;
	public String value_Mabda_Airport;
	public String value_Mabda_Airport_Code;
	public String value_Mabda_Airport_Code2;
	Activity activity;

	public class MyViewHolder extends RecyclerView.ViewHolder {
		public TextView AirportName, txtLongdes, txtTag;
		public TextView txtIconBaseFantastic, txtIconBaseLocation, txtIcon;
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
			rvSpace = (RelativeLayout)view.findViewById(R.id.rvSpace);
		}
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.row_airport, parent, false);

		return new MyViewHolder(itemView);
	}
	@Override
	public int getItemCount() {
		return data == null ? 0 : data.size();
	}
	// create constructor to innitilize context and data sent from MainActivity
    public GetAirPortMaghsadAdapter(Context context, List<Airport> data, String value_Mabda_City, String value_Mabda_Airport, String value_Mabda_Airport_Code, String value_Mabda_Airport_Code2, Activity activity){
		this.activity=activity;
		this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        myInflater = LayoutInflater.from(context);
        
        this.value_Mabda_City=value_Mabda_City;
        this.value_Mabda_Airport=value_Mabda_Airport;
        this.value_Mabda_Airport_Code=value_Mabda_Airport_Code;
        this.value_Mabda_Airport_Code2=value_Mabda_Airport_Code2;
    }

	public GetAirPortMaghsadAdapter(Context context, List<Airport> data, Activity activity) {
		this.activity=activity;
		this.context=context;
		inflater= LayoutInflater.from(context);
		this.data=data;
		myInflater = LayoutInflater.from(context);
		notifyDataSetChanged();
	}
	public void setData(List<Airport> data) {
		this.data = data;
		notifyDataSetChanged();
	}
	public void setData(String searchText) {
		this.data = data;
		notifyDataSetChanged();
	}

	/*@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}*/


	/*@Override
	public Object getItem(int position) {
		return position;
	}*/

	@Override
	public long getItemId(int position) {
	 //data.moveToPosition(position);
	 //Airport current=data.get(position);
	long s =position+1;
		//return data.getLong(Customers_Table.Columns.CUSTOMER_ID.value());
		return s;
	}
	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		Airport current = data.get(position);
		//holder.title.setText(movie.getTitle());
		holder.AirportName.setText(current.getTesxt() + " "+current.getShortDes());


		if(current.getTag().length()>0){
			holder.txtTag.setVisibility(View.VISIBLE);
			holder.txtTag.setText(current.getTag());
		}
		/*if(current.getShortDes().length()>0){
			holder.txtShortDes.setVisibility(View.VISIBLE);
			holder.txtShortDes.setText(current.getShortDes());
		}*/
		if(current.getLongDes().length()>0){
			holder.txtLongdes.setVisibility(View.VISIBLE);
			holder.txtIcon.setVisibility(View.VISIBLE);
			holder.txtLongdes.setText(Html.fromHtml(current.getLongDes().toString()));
		}
		if(current.getIcon().contains("fligh")){
			holder.rvSpace.setVisibility(View.VISIBLE);
			holder.txtIconBaseFantastic.setVisibility(View.VISIBLE);
			holder.txtIconBaseFantastic.setText("/");//icon_flight
		}else if(current.getIcon().contains("hotel")){
			holder.rvSpace.setVisibility(View.GONE);
			holder.txtIconBaseFantastic.setVisibility(View.VISIBLE);
			holder.txtIconBaseFantastic.setText("=");//icon_hotel
			holder.txtIcon.setVisibility(View.GONE);
		}else if(current.getIcon().contains("location")){
			holder.rvSpace.setVisibility(View.GONE);
			holder.txtIconBaseLocation.setVisibility(View.VISIBLE);
			holder.txtIconBaseLocation.setText(SingletonContext.getInstance().getContext().getResources().getString(R.string.icon_location2));//icon_location
			if(current.isSelectable()==false){
				holder.AirportName.setTextColor(Color.parseColor("#a9a9a9"));
				holder.txtIconBaseLocation.setTextColor(Color.parseColor("#a9a9a9"));
				holder.txtIconBaseLocation.setLongClickable(false);
				holder.txtIconBaseLocation.setClickable(false);
				holder.AirportName.setLongClickable(false);
				holder.AirportName.setClickable(false);

			}
		}

		holder.txtLongdes.setTag(current.getTesxt());
		holder.txtLongdes.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				RecentCity_Table recentCity_table=new RecentCity_Table(activity);
				//recentCity_table.insertData(current.getAirportName(),current.getCityName(),current.getAirportCode(),2);//maghsad

				int flagSelect=1;
				if(current.isSelectable())
					flagSelect=1;
				else
					flagSelect=0;

				recentCity_table.insertData(current.getTesxt(), current.getTextFa(), current.getShortDes()
						, current.getLongDes(), current.getTag(), current.getIcon()
						, current.getIconDown(),flagSelect,current.getValue(),2);//maghsad

				Prefs.putString("Value-Maghsad-City",current.getTesxt());
				Prefs.putString("Value-Maghsad-Airport",current.getTesxt());
				Prefs.putString("Value-Maghsad-Airport-Code",current.getShortDes());
				Prefs.putString("Value-Maghsad-Airport-Code2",current.getValue());
				//H+F
				/*Prefs.putString("Value-Hotel-City-Fa-HF-Source", current.getTesxt());
				Prefs.putString("Value-Hotel-City-En-HF-Source", current.getShortDes());*/
				Prefs.putString("Value-Hotel-City-Code-HF-Raft", current.getValue());

				Prefs.putString("Value-Mabda-City",value_Mabda_City);
				Prefs.putString("Value-Mabda-Airport",value_Mabda_Airport);
				Prefs.putString("Value-Mabda-Airport-Code",value_Mabda_Airport_Code);
				Prefs.putString("Value-Mabda-Airport-Code2",value_Mabda_Airport_Code2);
				//H+F
				/*Prefs.putString("Value-Hotel-City-Fa-HF-Raft", value_Mabda_City);
				Prefs.putString("Value-Hotel-City-En-HF-Raft", value_Mabda_Airport_Code);//shortDes*/
				Prefs.putString("Value-Hotel-City-Code-HF-Source",value_Mabda_Airport_Code2);//value

				activity.finish();
			}
		});
		holder.AirportName.setTag(current.getTesxt());
		holder.AirportName.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				RecentCity_Table recentCity_table=new RecentCity_Table(activity);
				//recentCity_table.insertData(current.getAirportName(),current.getCityName(),current.getAirportCode(),2);//maghsad

				int flagSelect=1;
				if(current.isSelectable())
					flagSelect=1;
				else
					flagSelect=0;

				recentCity_table.insertData(current.getTesxt(), current.getTextFa(), current.getShortDes()
						, current.getLongDes(), current.getTag(), current.getIcon()
						, current.getIconDown(),flagSelect, current.getValue(),2);//maghsad

				Prefs.putString("Value-Maghsad-City",current.getTesxt());
				Prefs.putString("Value-Maghsad-Airport",current.getTesxt());
				Prefs.putString("Value-Maghsad-Airport-Code",current.getShortDes());
				Prefs.putString("Value-Maghsad-Airport-Code2",current.getValue());
				//H+F
				/*Prefs.putString("Value-Hotel-City-Fa-HF-Source", current.getTesxt());
				Prefs.putString("Value-Hotel-City-En-HF-Source", current.getShortDes());*/
				Prefs.putString("Value-Hotel-City-Code-HF-Raft", current.getValue());

				Prefs.putString("Value-Mabda-City",value_Mabda_City);
				Prefs.putString("Value-Mabda-Airport",value_Mabda_Airport);
				Prefs.putString("Value-Mabda-Airport-Code",value_Mabda_Airport_Code);
				Prefs.putString("Value-Mabda-Airport-Code2",value_Mabda_Airport_Code2);
				//H+F
				/*Prefs.putString("Value-Hotel-City-Fa-HF-Raft", value_Mabda_City);
				Prefs.putString("Value-Hotel-City-En-HF-Raft", value_Mabda_Airport_Code);//shortDes*/
				Prefs.putString("Value-Hotel-City-Code-HF-Source",value_Mabda_Airport_Code2);//value

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