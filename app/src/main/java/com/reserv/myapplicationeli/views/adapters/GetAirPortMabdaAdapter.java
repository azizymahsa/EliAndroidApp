package com.reserv.myapplicationeli.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.Country;
import com.reserv.myapplicationeli.tools.db.local.RecentCity_Table;
import com.reserv.myapplicationeli.views.ui.GetAirportMabdaActivity;

import java.util.List;


public class GetAirPortMabdaAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater myInflater;
	//public CursorManager cursor;
	public int customerId;
	public String customerName;
	public int catt_ID=0;
	private LayoutInflater inflater;
	private List<Country> data;
	public String value_Maghsad_City;
	public String value_Maghsad_Airport;
	public String value_Maghsad_Airport_Code;
	public static String GET_FRAGMENT = null;
	Activity activity;

	 // create constructor to innitilize context and data sent from MainActivity
    public GetAirPortMabdaAdapter(Context context, List<Country> data, String value_Maghsad_City, String value_Maghsad_Airport, String value_Maghsad_Airport_Code, Activity activity){
    	this.activity=activity;
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        myInflater = LayoutInflater.from(context);
        
        this.value_Maghsad_City=value_Maghsad_City;
        this.value_Maghsad_Airport=value_Maghsad_Airport;
        this.value_Maghsad_Airport_Code=value_Maghsad_Airport_Code;
    }
	public GetAirPortMabdaAdapter(Activity activity){
		this.context=activity;
		myInflater = LayoutInflater.from(context);
	}

	public GetAirPortMabdaAdapter(Context context, List<Country> data, Activity activity) {
		this.activity=activity;
		this.context=context;
		inflater= LayoutInflater.from(context);
		this.data=data;
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
	long s =position+1;
		//return data.getLong(Customers_Table.Columns.CUSTOMER_ID.value());
		return s;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		if (convertView == null) {
			Log.e("POSITION", "" + position);
			convertView = myInflater.inflate(R.layout.row_airport, null);
			holder = new ViewHolder();

			holder.AirportName = (TextView) convertView.findViewById(R.id.text1);
			holder.CityName = (TextView) convertView.findViewById(R.id.text2);
			holder.txtIcon = (TextView) convertView.findViewById(R.id.txtIcon);
			//holder.btnSwip = (Button) convertView.findViewById(R.id.swipe_button);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//cursor.moveToPosition(position);
		final Country current=data.get(position);
		holder.AirportName.setText(current.getAirportName()+ "");
		holder.CityName.setVisibility(View.GONE);
		holder.txtIcon.setVisibility(View.GONE);
		holder.CityName.setText(current.getCityName());
		
		holder.AirportName.setTag(current.getAirportName());
		holder.AirportName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						RecentCity_Table recentCity_table=new RecentCity_Table(activity);
						recentCity_table.insertData(current.getAirportName(),current.getCityName(),current.getAirportCode(),1);//mabda

						Prefs.putString("Value-Mabda-City",current.getCityName());
						Prefs.putString("Value-Mabda-City2",current.getCityName());
						Prefs.putString("Value-Mabda-Airport",current.getAirportName());
						Prefs.putString("Value-Mabda-Airport-Code",current.getAirportCode());

						Prefs.putString("Value-Maghsad-City",value_Maghsad_City);
						Prefs.putString("Value-Maghsad-Airport",value_Maghsad_Airport);
						Prefs.putString("Value-Maghsad-Airport-Code",value_Maghsad_Airport_Code);

						//get
						//Prefs.getString("Value-Mabda-Airport","");



						activity.finish();
					}
				});
		holder.CityName.setTag(current.getCityName());
		holder.CityName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						RecentCity_Table recentCity_table=new RecentCity_Table(activity);
						recentCity_table.insertData(current.getAirportName(),current.getCityName(),current.getAirportCode(),1);//mabda

						Prefs.putString("Value-Mabda-City",current.getCityName());
						Prefs.putString("Value-Mabda-City2",current.getCityName());
						Prefs.putString("Value-Mabda-Airport",current.getAirportName());
						Prefs.putString("Value-Mabda-Airport-Code",current.getAirportCode());

						Prefs.putString("Value-Maghsad-City",value_Maghsad_City);
						Prefs.putString("Value-Maghsad-Airport",value_Maghsad_Airport);
						Prefs.putString("Value-Maghsad-Airport-Code",value_Maghsad_Airport_Code);

						activity.finish();
					//	Toast.makeText(v.getContext(),current.getCityName()+" "+current.getAirportName(),Toast.LENGTH_SHORT).show();
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