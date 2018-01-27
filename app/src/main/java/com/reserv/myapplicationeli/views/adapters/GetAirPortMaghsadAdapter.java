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
		/*if (convertView == null) {
			convertView = myInflater.inflate(R.layout.row_airport, null);
			///
			
				
			
			holder = new ViewHolder();
			holder.row = (TextView) convertView.findViewById(R.id.text1);
			holder.customerID = (TextView) convertView.findViewById(R.id.text2);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}*/
		if (convertView == null) {
			Log.e("POSITION", "" + position);
			convertView = myInflater.inflate(R.layout.row_airport, null);
			holder = new ViewHolder();

			holder.AirportName = (TextView) convertView.findViewById(R.id.text1);
			holder.CityName = (TextView) convertView.findViewById(R.id.text2);
			
			//holder.btnSwip = (Button) convertView.findViewById(R.id.swipe_button);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//cursor.moveToPosition(position);
		final Country current=data.get(position);
		holder.AirportName.setText(current.getAirportName()+ "");
		
		holder.CityName.setText(current.getCityName());
		
		holder.CityName.setTag(current.getCityName());
		holder.CityName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					/*
						Intent i4 = new Intent(context,PlanFragment.class);
						i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						i4.putExtra("Value-Maghsad-City",current.getCityName());//current.getCityName()
						i4.putExtra("Value-Maghsad-Airport",current.getAirportName());
						i4.putExtra("Value-Maghsad-Airport-Code",current.getAirportCode());
						
						i4.putExtra("Value-Mabda-City",value_Mabda_City);//current.getCityName()
						i4.putExtra("Value-Mabda-Airport",value_Mabda_Airport);
						i4.putExtra("Value-Mabda-Airport-Code",value_Mabda_Airport_Code);
						
						context.startActivity(i4);
						//finish();
						Toast.makeText(v.getContext(),current.getCityName()+" "+current.getAirportName(),Toast.LENGTH_SHORT).show();*/
						Prefs.putString("Value-Maghsad-City",current.getCityName());
						Prefs.putString("Value-Maghsad-Airport",current.getAirportName());
						Prefs.putString("Value-Maghsad-Airport-Code",current.getAirportCode());

						Prefs.putString("Value-Mabda-City",value_Mabda_City);
						Prefs.putString("Value-Mabda-Airport",value_Mabda_Airport);
						Prefs.putString("Value-Mabda-Airport-Code",value_Mabda_Airport_Code);

						//get
						//Prefs.getString("Value-Mabda-Airport","");



						activity.finish();
					}
				});
		holder.AirportName.setTag(current.getCityName());
		holder.AirportName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {

						/*Intent i4 = new Intent(context,PlanFragment.class);
						i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						i4.putExtra("Value-Maghsad-City",current.getCityName());//current.getCityName()
						i4.putExtra("Value-Maghsad-Airport",current.getAirportName());
						i4.putExtra("Value-Maghsad-Airport-Code",current.getAirportCode());
						context.startActivity(i4);
						//finish();
						Toast.makeText(v.getContext(),current.getCityName()+" "+current.getAirportName(),Toast.LENGTH_SHORT).show();*/
						Prefs.putString("Value-Maghsad-City",current.getCityName());
						Prefs.putString("Value-Maghsad-Airport",current.getAirportName());
						Prefs.putString("Value-Maghsad-Airport-Code",current.getAirportCode());

						Prefs.putString("Value-Mabda-City",value_Mabda_City);
						Prefs.putString("Value-Mabda-Airport",value_Mabda_Airport);
						Prefs.putString("Value-Mabda-Airport-Code",value_Mabda_Airport_Code);

						//get
						//Prefs.getString("Value-Mabda-Airport","");



						activity.finish();
					}
				});
		return convertView;
	}
	//AirportCode //AirportName//CityName ":
	static class ViewHolder {
		TextView AirportName;
		TextView CityName;
		
		
	}


}