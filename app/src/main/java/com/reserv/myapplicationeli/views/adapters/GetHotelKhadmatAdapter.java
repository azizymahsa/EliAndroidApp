package com.reserv.myapplicationeli.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.model.PurchaseFlightResult;
import com.reserv.myapplicationeli.views.activities.transfer.TransferActivity;
import com.reserv.myapplicationeli.views.ui.PassengerActivity;
import com.reserv.myapplicationeli.views.ui.PassengerHotelActivity;
import com.reserv.myapplicationeli.views.ui.PassengerHotelFlightActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class GetHotelKhadmatAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater myInflater;
	//public CursorManager cursor;
	public int customerId;
	public String customerName;
	public int catt_ID=0;
	private LayoutInflater inflater;
	private List<PurchaseFlightResult> data;
	public String value_Maghsad_City;
	public String value_Maghsad_Airport;
	public String value_Maghsad_Airport_Code;
	List<String> selectId = new ArrayList<String>();
	List<Long> gheymat = new ArrayList<Long>();

	//public List<StrictMath> SegmentListtrueAvali = new ArrayList<FlightSegmentTrue>();
	public Activity activity;

	// create constructor to innitilize context and data sent from MainActivity
	public GetHotelKhadmatAdapter(Context context, List<PurchaseFlightResult> data, Activity activity){
		this.context=context;
		this.activity=activity;
		inflater= LayoutInflater.from(context);
		this.data=data;
		myInflater = LayoutInflater.from(activity);


	}
	public GetHotelKhadmatAdapter(Activity activity){
		//this.context=activity;
		this.activity=activity;
		myInflater = LayoutInflater.from(activity);
	}

	public void setData(List<PurchaseFlightResult> data) {
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

	public View getView(final int position, View convertView, ViewGroup parent) {
		final GetKhadmatAdapter.ViewHolder holder;

		if (convertView == null) {
			Log.e("POSITION", "" + position);
			convertView = myInflater.inflate(R.layout.row_khadamat, null);
			holder = new GetKhadmatAdapter.ViewHolder();

			holder.txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);
			holder.txtServiceNameFa = (TextView) convertView.findViewById(R.id.txtServiceNameFa);

			holder.imageView1= (ImageView) convertView.findViewById(R.id.imageView1);

			holder.btnAddsabad= (RelativeLayout) convertView.findViewById(R.id.btnAddsabad);
			holder.txtAdd= (TextView) convertView.findViewById(R.id.txtAdd);
			holder.img_khadmat_row= (ImageView) convertView.findViewById(R.id.img_khadmat_row);

			holder.txtServiceTotalPrice= (TextView) convertView.findViewById(R.id.txtServiceTotalPrice);

			//holder.btnSwip = (Button) convertView.findViewById(R.id.swipe_button);
			convertView.setTag(holder);
		} else {
			holder = (GetKhadmatAdapter.ViewHolder) convertView.getTag();
		}
		//cursor.moveToPosition(position);
		final PurchaseFlightResult current=data.get(position);
		holder.txtDescription.setText(current.getServiceDescFa()+ "");

		holder.txtServiceNameFa.setText(current.getServiceNameFa());
		holder.txtServiceTotalPrice.setText(current.getServiceTotalPrice() > 0 ? String.valueOf(NumberFormat.getInstance().format(current.getServiceTotalPrice())) : "It");//String.valueOf(NumberFormat.getInstance().format(current.getServiceTotalPrice()))+"");
		if(current.getServiceTypeID().equals("4"))
			holder.imageView1.setBackgroundResource(R.drawable.cip_service_khadamat);

		if(current.getServiceTypeID().equals("-1515"))
			holder.imageView1.setBackgroundResource(R.drawable.ic_bime_khadamat);

		if(current.getServiceTypeID().equals("1"))
			holder.imageView1.setBackgroundResource(R.drawable.ic_transfer_forudgahi);

		if(current.getServiceTypeID().equals("9"))
			holder.imageView1.setBackgroundResource(R.drawable.ic_transfer_forudgahi);

		holder.btnAddsabad.setTag(current.getServiceID());
		holder.btnAddsabad.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//tick_round_button



				//
				String sumSelectId= Prefs.getString("Select_ID_khadamat", "");
				long sumGheymat=0;



				if(current.getServiceNameEn().contains("Airport Transfer")&& current.getLoadDB().equals("false")){
					Intent intent=	new Intent(context, TransferActivity.class);

					intent.putExtra("ArrialAirportCode",current.getExcursionDta().ArrialAirportCode);
					intent.putExtra("ArrivalFltDate",current.getExcursionDta().ArrivalFltDate);
					intent.putExtra("ArrivalFltNo",current.getExcursionDta().ArrivalFltNo);
					intent.putExtra("ArrivalFltTime",current.getExcursionDta().ArrivalFltTime);
					intent.putExtra("CityID",current.getExcursionDta().CityID);
					intent.putExtra("DepartureFltDate",current.getExcursionDta().DepartureFltDate);
					intent.putExtra("DepartureFltNo",current.getExcursionDta().DepartureFltNo);
					intent.putExtra("DepartureFltTime",current.getExcursionDta().DepartureFltTime);
					intent.putExtra("HotelID",current.getExcursionDta().HotelID);
					intent.putExtra("HotelNameEn",current.getExcursionDta().HotelNameEn);
					intent.putExtra("ArrialAirportName",current.getExcursionDta().ArrialAirportName);

					context.startActivity(intent);

				}else{

					if (current.isFlag()){

						current.setFlag(false);
						holder.btnAddsabad.setBackgroundResource(R.drawable.blue_button);
						holder.img_khadmat_row.setVisibility(View.GONE);
						holder.txtAdd.setText("افزودن به سبد خرید");


					}else{

						current.setFlag(true);

						holder.btnAddsabad.setBackgroundResource(R.drawable.green_button);
						holder.img_khadmat_row.setVisibility(View.VISIBLE);
						holder.txtAdd.setText("اضافه شد");

					}
					notifyDataSetChanged();


					sumSelectId="";
					for (int i =0 ;i<data.size();i++){
						if(data.get(i).isFlag()) {
							sumGheymat = sumGheymat + data.get(i).getServiceTotalPrice();
							if(sumSelectId.length()>2) {

								sumSelectId = sumSelectId + "|" + data.get(i).getSelectID();
							}else {
								sumSelectId = data.get(i).getSelectID();//avalin bar
							}
						}
					}
					//	Toast.makeText(v.getContext(),sumSelectId,Toast.LENGTH_SHORT).show();
					Prefs.putString("Select_ID_khadamat",sumSelectId);
					PassengerHotelFlightActivity.updateTotalInfos(sumGheymat);
				}





/*//////////////////////////////////END CHANGE////////////////////////////////////////////////*/



			}
		});

		return convertView;

	}

	static class ViewHolder {
		TextView txtServiceTotalPrice;
		TextView txtDescription;
		TextView txtServiceNameFa;
		ImageView imageView1;
		RelativeLayout btnAddsabad;

		ImageView img_khadmat_row;
		TextView txtAdd;
	}


}