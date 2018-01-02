package com.reserv.myapplicationeli.views.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.reserv.myapplicationeli.GlobalApplication;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.Country;



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

	public GetAirPortMabdaAdapter() {
		myInflater = LayoutInflater.from(GlobalApplication.getActivity());
	}
	 // create constructor to innitilize context and data sent from MainActivity
    public GetAirPortMabdaAdapter(Context context, List<Country> data, String value_Maghsad_City, String value_Maghsad_Airport, String value_Maghsad_Airport_Code){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        myInflater = LayoutInflater.from(GlobalApplication.getActivity());
        
        this.value_Maghsad_City=value_Maghsad_City;
        this.value_Maghsad_Airport=value_Maghsad_Airport;
        this.value_Maghsad_Airport_Code=value_Maghsad_Airport_Code;
    }
	public GetAirPortMabdaAdapter(Activity activity){
		this.context=activity;
		myInflater = LayoutInflater.from(GlobalApplication.getActivity());
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
		
		holder.AirportName.setTag(current.getAirportName());
		holder.AirportName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					
						Intent i4 = new Intent(context, com.reserv.myapplicationeli.views.ui.PlanFragment.class);
						
						i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						
						i4.putExtra("Value-Mabda-City",current.getCityName());//current.getCityName()
						i4.putExtra("Value-Mabda-Airport",current.getAirportName());
						i4.putExtra("Value-Mabda-Airport-Code",current.getAirportCode());
					
						i4.putExtra("Value-Maghsad-City",value_Maghsad_City);//current.getCityName()
						i4.putExtra("Value-Maghsad-Airport",value_Maghsad_Airport);
						i4.putExtra("Value-Maghsad-Airport-Code",value_Maghsad_Airport_Code);
						context.startActivity(i4);
						
						
						/*Intent i4=new Intent();
						//i4.putExtra("MESSAGE",message);
				        i4.putExtra("Value-Mabda-City"," d");//current.getCityName()
						i4.putExtra("Value-Mabda-Airport",current.getAirportName());
						i4.putExtra("Value-Mabda-Airport-Code",current.getAirportCode());
				      //  setResult(2,i4);
*/				        
				       // finish();//finishing activity
						
						Toast.makeText(v.getContext(),current.getCityName()+" "+current.getAirportName(),Toast.LENGTH_SHORT).show();
					}
				});
		holder.CityName.setTag(current.getCityName());
		holder.CityName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					
						Intent i4 = new Intent(context, com.reserv.myapplicationeli.views.ui.PlanFragment.class);
						
						i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						i4.putExtra("Value-Mabda-City",current.getCityName());//current.getCityName()
						i4.putExtra("Value-Mabda-Airport",current.getAirportName());
						i4.putExtra("Value-Mabda-Airport-Code",current.getAirportCode());
						context.startActivity(i4);
						
						
						/*Intent i4=new Intent();
						//i4.putExtra("MESSAGE",message);
				        i4.putExtra("Value-Mabda-City"," d");//current.getCityName()
						i4.putExtra("Value-Mabda-Airport",current.getAirportName());
						i4.putExtra("Value-Mabda-Airport-Code",current.getAirportCode());
				      //  setResult(2,i4);
*/				        
				       // finish();//finishing activity
						
						Toast.makeText(v.getContext(),current.getCityName()+" "+current.getAirportName(),Toast.LENGTH_SHORT).show();
					}
				});
		return convertView;
		}

	static class ViewHolder {
		TextView AirportName;
		TextView CityName;
		
		
	}


}