package com.eligasht.reservation.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eligasht.reservation.tools.Prefs;
import com.eligasht.R;
import com.eligasht.reservation.models.Country;
import com.eligasht.reservation.tools.db.local.RecentCity_Table;

import java.util.List;


public class GetAirPortMaghsadAdapter extends BaseAdapter {
	private Context context;

	private LayoutInflater myInflater;
	//public CursorManager cursor;
	public int customerId;
	public String customerName;
	public int catt_ID=0;
	private LayoutInflater inflater;
	private List<Country> data;
	public String value_Mabda_City;
	public String value_Mabda_Airport;
	public String value_Mabda_Airport_Code;
	Activity activity;
	public GetAirPortMaghsadAdapter() {
		//myInflater = LayoutInflater.from(GlobalApplication.getActivity());
	}
	 // create constructor to innitilize context and data sent from MainActivity
    public GetAirPortMaghsadAdapter(Context context, List<Country> data, String value_Mabda_City, String value_Mabda_Airport, String value_Mabda_Airport_Code, Activity activity){
		this.activity=activity;
		this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        myInflater = LayoutInflater.from(context);
        
        this.value_Mabda_City=value_Mabda_City;
        this.value_Mabda_Airport=value_Mabda_Airport;
        this.value_Mabda_Airport_Code=value_Mabda_Airport_Code;
    }
	public GetAirPortMaghsadAdapter(Activity activity){
		this.context=activity;
		myInflater = LayoutInflater.from(activity);
	}
	public GetAirPortMaghsadAdapter(Context context, List<Country> data, Activity activity) {
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
		//	Log.e("POSITION", "" + position);
			convertView = myInflater.inflate(R.layout.row_airport, null);
			holder = new ViewHolder();

			holder.AirportName = convertView.findViewById(R.id.text1);
			holder.CityName = convertView.findViewById(R.id.text2);
			holder.txtIcon = convertView.findViewById(R.id.txtIcon);
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
		
		holder.CityName.setTag(current.getCityName());
		holder.CityName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						RecentCity_Table recentCity_table=new RecentCity_Table(activity);
						recentCity_table.insertData(current.getAirportName(),current.getCityName(),current.getAirportCode(),2);//maghsad

						Prefs.putString("Value-Maghsad-City",current.getCityName());
						Prefs.putString("Value-Maghsad-Airport",current.getAirportName());
						Prefs.putString("Value-Maghsad-Airport-Code",current.getAirportCode());

						Prefs.putString("Value-Mabda-City",value_Mabda_City);
						Prefs.putString("Value-Mabda-Airport",value_Mabda_Airport);
						Prefs.putString("Value-Mabda-Airport-Code",value_Mabda_Airport_Code);


						activity.finish();
					}
				});
		holder.AirportName.setTag(current.getCityName());
		holder.AirportName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {

						RecentCity_Table recentCity_table=new RecentCity_Table(activity);
						recentCity_table.insertData(current.getAirportName(),current.getCityName(),current.getAirportCode(),2);//maghsad

						Prefs.putString("Value-Maghsad-City",current.getCityName());
						Prefs.putString("Value-Maghsad-Airport",current.getAirportName());
						Prefs.putString("Value-Maghsad-Airport-Code",current.getAirportCode());

						Prefs.putString("Value-Mabda-City",value_Mabda_City);
						Prefs.putString("Value-Mabda-Airport",value_Mabda_Airport);
						Prefs.putString("Value-Mabda-Airport-Code",value_Mabda_Airport_Code);

						activity.finish();
					}
				});
		return convertView;
	}
	//AirportCode //AirportName//CityName ":
	static class ViewHolder {
		TextView AirportName;
		TextView CityName;
		TextView txtIcon;
		
	}


}