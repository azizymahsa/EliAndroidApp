package com.eligasht.reservation.views.adapters;

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
import com.eligasht.R;
import com.eligasht.reservation.models.model.PurchaseFlightResult;
import com.eligasht.reservation.views.activities.transfer.TransferActivity;
import com.eligasht.reservation.views.ui.PassengerActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class GetKhadmatAdapter extends BaseAdapter {
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
	public long Tprice=0;

	//public List<StrictMath> SegmentListtrueAvali = new ArrayList<FlightSegmentTrue>();
public Activity activity;

	// create constructor to innitilize context and data sent from MainActivity
	public GetKhadmatAdapter(Context context, List<PurchaseFlightResult> data, Activity activity,long Tprice){
		this.context=context;
		this.activity=activity;
		inflater= LayoutInflater.from(context);
		this.data=data;
		this.Tprice=Tprice;
		myInflater = LayoutInflater.from(activity);
        

	}
	public GetKhadmatAdapter(Activity activity){
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
		final ViewHolder holder;

		if (convertView == null) {
			Log.e("POSITION", "" + position);
			convertView = myInflater.inflate(R.layout.row_khadamat, null);
			holder = new ViewHolder();

			holder.txtDescription = convertView.findViewById(R.id.txtDescription);
			holder.txtServiceNameFa = convertView.findViewById(R.id.txtServiceNameFa);

			holder.imageView1= convertView.findViewById(R.id.imageView1);

			holder.btnAddsabad= convertView.findViewById(R.id.btnAddsabad);
			holder.txtAdd= convertView.findViewById(R.id.txtAdd);
			holder.img_khadmat_row= convertView.findViewById(R.id.img_khadmat_row);

			holder.txtServiceTotalPrice= convertView.findViewById(R.id.txtServiceTotalPrice);

			//holder.btnSwip = (Button) convertView.findViewById(R.id.swipe_button);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//cursor.moveToPosition(position);
		final PurchaseFlightResult current=data.get(position);
		holder.txtDescription.setText(current.getServiceDescFa()+ "");

		holder.txtServiceNameFa.setText(current.getServiceNameFa());
		holder.txtServiceTotalPrice.setText(current.getServiceTotalPrice() > 0 ? String.valueOf(NumberFormat.getInstance().format(current.getServiceTotalPrice())) : "IT");//String.valueOf(NumberFormat.getInstance().format(current.getServiceTotalPrice()))+"");
		if(current.getServiceTypeID().equals("4"))
			holder.imageView1.setBackgroundResource(R.drawable.cip_service_khadamat);

		if(current.getServiceTypeID().equals("-1515"))
			holder.imageView1.setBackgroundResource(R.drawable.ic_bime_khadamat);

		if(current.getServiceTypeID().equals("1"))
			holder.imageView1.setBackgroundResource(R.drawable.ic_transfer_forudgahi);

		if(current.getServiceTypeID().equals("9"))
			holder.imageView1.setBackgroundResource(R.drawable.ic_transfer_forudgahi);

		holder.btnAddsabad.setTag(current.getServiceID());
		if(current.getServiceNameEn().contains("Airport Transfer")&& current.getServiceTotalPrice()==0&& Prefs.getString("Flag_First_Computing","F").equals("F")){
			holder.txtAdd.setText(R.string.calculate_price);
			if(Tprice==0) {
				holder.txtServiceTotalPrice.setText("");
			}else if(Tprice >0){
				if(current.getServiceTotalPrice()>0){
					System.out.println(Tprice+"");
				}else {
					current.setServiceTotalPrice(Tprice);
					holder.txtServiceTotalPrice.setText(NumberFormat.getInstance().format(current.getServiceTotalPrice()));
					current.setFlag(false);
					holder.btnAddsabad.setBackgroundResource(R.drawable.blue_button);
					holder.img_khadmat_row.setVisibility(View.GONE);
					holder.txtAdd.setText(R.string.add_to_shoping_cart);

					notifyDataSetChanged();

				}
			}
		}else{
			if (current.isFlag()){

				holder.btnAddsabad.setBackgroundResource(R.drawable.green_button);
				holder.img_khadmat_row.setVisibility(View.VISIBLE);
				holder.txtAdd.setText(R.string.added);


			}else{

				holder.btnAddsabad.setBackgroundResource(R.drawable.blue_button);
				holder.img_khadmat_row.setVisibility(View.GONE);
				holder.txtAdd.setText(R.string.add_to_shoping_cart);



			}
			notifyDataSetChanged();

		}
		holder.btnAddsabad.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String sumSelectId= Prefs.getString("Select_ID_khadamat", "");
				long sumGheymat=0;



				if(current.getServiceNameEn().contains("Airport Transfer")&& current.getLoadDB().equals("false") && Tprice==0 && Prefs.getString("Flag_First_Computing","F").equals("F")){
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

					intent.putExtra("ServiceID",current.getSelectID());
					intent.putExtra("PassengerList",current.getExcursionDta().PassengerList);
					intent.putExtra("BookingCode",current.getBookingCode());
					context.startActivity(intent);



				}else{

					if (current.isFlag()){

						current.setFlag(false);
						holder.btnAddsabad.setBackgroundResource(R.drawable.blue_button);
						holder.img_khadmat_row.setVisibility(View.GONE);
						holder.txtAdd.setText(R.string.add_to_shoping_cart);


					}else{

						current.setFlag(true);

						holder.btnAddsabad.setBackgroundResource(R.drawable.green_button);
						holder.img_khadmat_row.setVisibility(View.VISIBLE);
						holder.txtAdd.setText(R.string.added);

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
					PassengerActivity.updateTotalInfos(sumGheymat);
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