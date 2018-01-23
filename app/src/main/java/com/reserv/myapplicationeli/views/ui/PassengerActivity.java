package com.reserv.myapplicationeli.views.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.model.PurchaseFlightResult;
import com.reserv.myapplicationeli.tools.db.local.PassengerMosaferItems_Table;
import com.reserv.myapplicationeli.tools.db.local.PassengerPartnerInfo_Table;
import com.reserv.myapplicationeli.tools.db.main.CursorManager;
import com.reserv.myapplicationeli.views.activities.ContactUsActivity;
import com.reserv.myapplicationeli.views.adapters.GetKhadmatAdapter;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.NonScrollListView;
import com.reserv.myapplicationeli.views.components.Header;
import com.reserv.myapplicationeli.views.fragments.PlanFragment;

import mehdi.sakout.fancybuttons.FancyButton;

public class PassengerActivity extends BaseActivity implements Header.onSearchTextChangedListener,OnClickListener,OnItemSelectedListener{

	public static boolean flag;
	public static final int CONNECTION_TIMEOUT = 10000;
	public static final int READ_TIMEOUT = 15000;
	Handler handler;
	ProgressDialog progressBar;
	public FancyButton btnBack;
	public ImageView btn_saler,btn_mosaferan,btn_khadamat,btn_pish_factor;
	public TextView txtfamilyP,txtkodemeliP,txtemeliP,txtmobileP,txtMore;
	public Button btnAddsabad,btn_pardakht_factor;
	public EditText txtnamem,txtfamilym;
	public static TextView txttavalodm;
	public EditText txtnumber_passport,txtnameP;
	public static TextView txtexp_passport;
	public TextView txtTitle,txtmeliyatm,txtmahale_eghamat,txtTitleCountM;
	public static TextView txtSumKhadamat;
	public LinearLayout btn_taeed_khadamat,btn_nextm,linear_saler,linear_mosaferan,linear_list_khadamat,linear_pish_factor,linearMahaleeghamat,linearMeliyat,btn_next_partnerInfo;
	private Handler progressBarHandler = new Handler();
	public ListView list_airport;
	public NonScrollListView listKhadamat;
	ArrayList<HashMap<String,String>> mylist=null;
	public static String searchText = "";
	//public static long GET_PRICE_KHADAMAT;
	public static long GET_PRICE_KHADAMAT;

	GetKhadmatAdapter mAdapter;
	ScrollView myScrollView;
	private EditText searchtxt;
	public TextView txt_shomare_factor,tvPrice,tvfactorNumber;

	public ImageView txt_hom;

	private String Gensiyat;
	Activity activity;
	public int countB=SearchParvazActivity.COUNT_B;
	public int countK=SearchParvazActivity.COUNT_K;
	public int countN=SearchParvazActivity.COUNT_N;
	public int sum=countB+countK+countN;

	public List<PurchaseFlightResult> data;
	int counter=2;
	private ImageView textView4;

	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_passenger);

		 data=new ArrayList<PurchaseFlightResult>();
		btnBack = (FancyButton) findViewById(R.id.btnBack);
		btnBack.setCustomTextFont("fonts/icomoon.ttf");
		btnBack.setText(getString(R.string.search_back_right));
		btnBack.setVisibility(View.VISIBLE);
		btnBack.setOnClickListener(this);

		txt_hom = (ImageView) findViewById(R.id.txt_hom);
		txt_hom.setOnClickListener(this);

		txtMore = (TextView) findViewById(R.id.txtMore);
		txtMore.setOnClickListener(this);

		tvPrice= (TextView) findViewById(R.id.tvPrice);
		tvPrice.setOnClickListener(this);

		txtSumKhadamat = (TextView) findViewById(R.id.txtSumKhadamat);
		txtSumKhadamat.setOnClickListener(this);
		txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(GET_PRICE_KHADAMAT)));

		txttavalodm = (TextView) findViewById(R.id.txttavalodm);
		txttavalodm.setOnClickListener(this);
		txtnamem = (EditText) findViewById(R.id.txtnamem);
		txtnamem.setOnClickListener(this);
		txtnamem.addTextChangedListener(new GenericTextWatcher(txtnamem));

		txtfamilym = (EditText) findViewById(R.id.txtfamilym);
		txtfamilym.setOnClickListener(this);
		txtfamilym.addTextChangedListener(new GenericTextWatcher(txtfamilym));
		txtnumber_passport = (EditText) findViewById(R.id.txtnumber_passport);
		txtnumber_passport.setOnClickListener(this);
		txtnumber_passport.setImeOptions(EditorInfo.IME_ACTION_DONE);
		txtnumber_passport.addTextChangedListener(new GenericTextWatcher(txtnumber_passport));
		txtexp_passport = (TextView) findViewById(R.id.txtexp_passport);
		txtexp_passport.setOnClickListener(this);

		txtTitle= (TextView) findViewById(R.id.tvTitle);
		txtTitle.setOnClickListener(this);
		txtTitleCountM = (TextView) findViewById(R.id.txtTitleCountM);
		txtTitleCountM.setOnClickListener(this);

		btn_next_partnerInfo=(LinearLayout) findViewById(R.id.btn_next_partnerInfo);
		btn_next_partnerInfo.setOnClickListener(this);

		btn_nextm=(LinearLayout)findViewById(R.id.btn_nextm);
		btn_nextm.setOnClickListener(this);

		btn_taeed_khadamat=(LinearLayout)findViewById(R.id.btn_taeed_khadamat);
		btn_taeed_khadamat.setOnClickListener(this);

		btn_pardakht_factor=(Button)findViewById(R.id.btn_pardakht_factor);
		btn_pardakht_factor.setOnClickListener(this);

		textView4 = (ImageView) findViewById(R.id.textView4);

		textView4 = (ImageView) findViewById(R.id.textView4);
		tvfactorNumber = (TextView) findViewById(R.id.tvfactorNumber);
			/* btnAddsabad=(Button)findViewById(R.id.btnAddsabad);
			 btnAddsabad.setOnClickListener(this);*/

		btn_saler= (ImageView) findViewById(R.id.btn_saler);
		btn_mosaferan=(ImageView)findViewById(R.id.btn_mosaferan);
		btn_khadamat=(ImageView)findViewById(R.id.btn_khadamat);
		btn_pish_factor=(ImageView)findViewById(R.id.btn_pish_factor);

		btn_saler.setOnClickListener(this);
		btn_mosaferan.setOnClickListener(this);
		btn_khadamat.setOnClickListener(this);
		btn_pish_factor.setOnClickListener(this);

		linear_saler = (LinearLayout) findViewById(R.id.linear_saler);
		linear_mosaferan = (LinearLayout) findViewById(R.id.linear_mosaferan);
		linear_pish_factor= (LinearLayout) findViewById(R.id.linear_pish_factor);
		linearMahaleeghamat= (LinearLayout) findViewById(R.id.linearMahaleeghamat);
		linearMeliyat= (LinearLayout) findViewById(R.id.linearMeliyat);

		txtnameP= (EditText)findViewById(R.id.txtnameP);
		//	txtnameP.setHint("لطفا نام را فارسی وارد کنید");
		txtnameP.addTextChangedListener(new GenericTextWatcher(txtnameP));

		txtfamilyP= (EditText)findViewById(R.id.txtfamilyP);
		//	txtfamilyP.setHint("لطفا نام خانوادگی را فارسی وارد کنید");
		txtfamilyP.addTextChangedListener(new GenericTextWatcher(txtfamilyP));
		txtmobileP= (EditText)findViewById(R.id.txtmobileP);
		txtmobileP.addTextChangedListener(new GenericTextWatcher(txtmobileP));
		txtkodemeliP= (EditText)findViewById(R.id.txtkodemeliP);
		txtkodemeliP.addTextChangedListener(new GenericTextWatcher(txtkodemeliP));
		txtemeliP= (EditText)findViewById(R.id.txtemeliP);
		txtemeliP.addTextChangedListener(new GenericTextWatcher(txtemeliP));

		txtmeliyatm= (TextView)findViewById(R.id.txtmeliyatm);
		txtmeliyatm.setOnClickListener(this);
		txtmahale_eghamat= (TextView)findViewById(R.id.txtmahale_eghamat);
		txtmahale_eghamat.setOnClickListener(this);

		txt_shomare_factor= (TextView)findViewById(R.id.txt_shomare_factor);
		txt_shomare_factor.setOnClickListener(this);

		linear_list_khadamat = (LinearLayout) findViewById(R.id.linear_list_khadamat);

		listKhadamat = (NonScrollListView)findViewById(R.id.listKhadamat);
		myScrollView = (ScrollView) findViewById(R.id.layout_scroll);



		//////////////////////////
		// Spinner element
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		Spinner spinnerMosafer = (Spinner) findViewById(R.id.spinnerMosafer);

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);
		spinnerMosafer.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		List<String> categories = new ArrayList<String>();
		categories.add("مرد");
		categories.add("زن");



		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

		// Drop down dialog_custom style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
		spinnerMosafer.setAdapter(dataAdapter);
		////////////////////////////////



		// new AsyncFetch().execute();

	}//end oncreate
	//AsyncFetchGetPreFactorDetails
	private class AsyncFetchGetPreFactorDetails extends AsyncTask<String, String, String> {
		ProgressDialog pdLoading = new ProgressDialog(PassengerActivity.this);
		HttpURLConnection conn;
		URL url = null;
		private ListView listAirPort;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			//this method will be running on UI thread
			pdLoading.setMessage("\tLoading...");
			pdLoading.setCancelable(false);
			pdLoading.show();

		}

		@Override
		protected String doInBackground(String... params) {
			try {

				// Enter URL address where your json file resides
				// Even you can make call to php file which returns json data
				url = new URL("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetPreFactorDetails");

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.toString();
			}
			try {

				// Setup HttpURLConnection class to send and receive data from php and mysql
				conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(READ_TIMEOUT);
				conn.setConnectTimeout(CONNECTION_TIMEOUT);
				// conn.setRequestMethod("GET");
				conn.setRequestMethod("POST");
				// setDoOutput to true as we recieve data from json file
				conn.setDoOutput(true);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return e1.toString();
			}

			try {

				int response_code = conn.getResponseCode();

				String serial = null;

				JSONObject errorObj = new JSONObject();

				try {
					errorObj.put("Success", false);

					Class<?> c = Class.forName("android.os.SystemProperties");
					Method get = c.getMethod("get", String.class);
					serial = (String) get.invoke(c, "ro.serialno");//31007a81d4b22300
				} catch (Exception ignored) {
				}


				String data =OrderToJsonGetPreFactorDetails();


				HttpClient client = new DefaultHttpClient();


				HttpPost post = new HttpPost();
				post = new HttpPost("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/GetPreFactorDetails");
				post.setHeader("Content-Type", "application/json; charset=UTF-8");
				post.setHeader("Accept", "application/json; charset=UTF-8");


				StringEntity se = null;
				try {
					se = new StringEntity(data, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				post.setEntity(se);
				ByteArrayOutputStream os = new ByteArrayOutputStream();


				HashMap<String, String> airport = null;
				mylist = new ArrayList<HashMap<String, String>>();
				HttpResponse res = client.execute(post);
				String retSrc = EntityUtils.toString(res.getEntity(), HTTP.UTF_8);


				return (retSrc);

			} catch (IOException e) {
				e.printStackTrace();
				return e.toString();
			} finally {
				conn.disconnect();
			}


		}//end doin background

		@Override
		protected void onPostExecute(String resultPishfactor) {

			//this method will be running on UI thread
			//{"PurchaseServiceResult":{"Errors":null,"ResultText":"Temp Contract Saved Successfully!","SuccessResult":782528}}
			//  pdLoading.dismiss();
			//List<PurchaseFlightResult> data=new ArrayList<PurchaseFlightResult>();

			pdLoading.dismiss();
			try {
////////////////////////////
				JSONObject jsonObj = new JSONObject(resultPishfactor);

				// Getting JSON Array node
				JSONObject GetAirportsResult = jsonObj.getJSONObject("GetPreFactorDetailsResult");
				JSONObject jArray = GetAirportsResult.getJSONObject("PreFactor");//FactorSummary
				JSONObject jFact = jArray.getJSONObject("FactorSummary");

				int RqBase_ID=jFact.getInt("RqBase_ID");
				//////////////////////////////
				long totalprice=jFact.getLong("TotalPrice");
				tvPrice.setText(" "+String.valueOf(NumberFormat.getInstance().format(totalprice))+" ریال ");



			} catch (JSONException e) {
				Toast.makeText(PassengerActivity.this, "ارتباط با سرور برقرار نشد !!", Toast.LENGTH_LONG).show();
			}


		}//end on pos excute

	}
	//end AsyncFetchGetPreFactorDetails
	//AsyncFetchPishFactor
	private class AsyncFetchPishFactor extends AsyncTask<String, String, String> {
		ProgressDialog pdLoading = new ProgressDialog(PassengerActivity.this);
		HttpURLConnection conn;
		URL url = null;
		private ListView listAirPort;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			//this method will be running on UI thread
			pdLoading.setMessage("\tLoading...");
			pdLoading.setCancelable(false);
			pdLoading.show();

		}

		@Override
		protected String doInBackground(String... params) {
			try {

				// Enter URL address where your json file resides
				// Even you can make call to php file which returns json data
				url = new URL("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/PurchaseService");

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.toString();
			}
			try {

				// Setup HttpURLConnection class to send and receive data from php and mysql
				conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(READ_TIMEOUT);
				conn.setConnectTimeout(CONNECTION_TIMEOUT);
				// conn.setRequestMethod("GET");
				conn.setRequestMethod("POST");
				// setDoOutput to true as we recieve data from json file
				conn.setDoOutput(true);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return e1.toString();
			}

			try {

				int response_code = conn.getResponseCode();

				String serial = null;

				JSONObject errorObj = new JSONObject();

				try {
					errorObj.put("Success", false);

					Class<?> c = Class.forName("android.os.SystemProperties");
					Method get = c.getMethod("get", String.class);
					serial = (String) get.invoke(c, "ro.serialno");//31007a81d4b22300
				} catch (Exception ignored) {
				}


				String data =OrderToJsonPishFactor();


				HttpClient client = new DefaultHttpClient();


				HttpPost post = new HttpPost();
				post = new HttpPost("http://mobilews.eligasht.com/LightServices/Rest/Common/StaticDataService.svc/PurchaseService");
				post.setHeader("Content-Type", "application/json; charset=UTF-8");
				post.setHeader("Accept", "application/json; charset=UTF-8");


				StringEntity se = null;
				try {
					se = new StringEntity(data, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				post.setEntity(se);
				ByteArrayOutputStream os = new ByteArrayOutputStream();


				HashMap<String, String> airport = null;
				mylist = new ArrayList<HashMap<String, String>>();
				HttpResponse res = client.execute(post);
				String retSrc = EntityUtils.toString(res.getEntity(), HTTP.UTF_8);


				return (retSrc);

			} catch (IOException e) {
				e.printStackTrace();
				return e.toString();
			} finally {
				conn.disconnect();
			}


		}//end doin background

		@Override
		protected void onPostExecute(String resultPishfactor) {



			pdLoading.dismiss();
			try {
////////////////////////////
				JSONObject jsonObj = new JSONObject(resultPishfactor);

				// JSONObject jsonObj = new JSONObject(retSrc);

				// Getting JSON Array node
				JSONObject GetAirportsResult = jsonObj.getJSONObject("PurchaseServiceResult");
				int successResult=GetAirportsResult.getInt("SuccessResult");
				if(successResult==0){
					//get Error
					JSONObject getError = jsonObj.getJSONObject("Errors");

					String message= getError.getString("Message");
					Toast.makeText(PassengerActivity.this, message, Toast.LENGTH_LONG).show();
				}

				if(successResult >1) {
					txt_shomare_factor.setText(GetAirportsResult.getString("SuccessResult"));

					tvfactorNumber.setText(GetAirportsResult.getString("SuccessResult"));

					textView4.setImageBitmap(getBitmap(GetAirportsResult.getString("SuccessResult"), 128, 500, 200));
				}else{
					//txt_shomare_factor.setText("خطایی رخ داده است !");
					Toast.makeText(PassengerActivity.this, "خطایی رخ داده است !", Toast.LENGTH_LONG).show();
					finish();

				}
				// sfsfs

				// Setup and Handover data to recyclerview
				((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_on);
				((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
				txtTitle.setText(" تایید و پرداخت پیش فاکتور    ");
				//	myScrollView.setOnTouchListener(null);

				linear_saler.setVisibility(View.GONE);
				linear_mosaferan.setVisibility(View.GONE);
				linear_list_khadamat.setVisibility(View.GONE);
				linear_pish_factor.setVisibility(View.VISIBLE);


			} catch (JSONException e) {
				Toast.makeText(PassengerActivity.this, "ارتباط با سرور برقرار نشد !!", Toast.LENGTH_LONG).show();
			}


		}//end on pos excute

	}//end async get pish factor
	//het khadamat
	private class AsyncFetch extends AsyncTask<String, String, String> {
		ProgressDialog pdLoading = new ProgressDialog(PassengerActivity.this);
		HttpURLConnection conn;
		URL url = null;
		private ListView listAirPort;

		@Override
		protected void onPreExecute(){
			super.onPreExecute();

			//this method will be running on UI thread
			pdLoading.setMessage("\tLoading...");
			pdLoading.setCancelable(false);
			pdLoading.show();

		}

		@Override
		protected String doInBackground(String... params) {
			try {

				// Enter URL address where your json file resides
				// Even you can make call to php file which returns json data
				url = new URL("http://mobilews.eligasht.com/LightServices/Rest/Flight/FlightService.svc/PurchaseFlight");

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.toString();
			}
			try {

				// Setup HttpURLConnection class to send and receive data from php and mysql
				conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(READ_TIMEOUT);
				conn.setConnectTimeout(CONNECTION_TIMEOUT);
				// conn.setRequestMethod("GET");
				conn.setRequestMethod("POST");
				// setDoOutput to true as we recieve data from json file
				conn.setDoOutput(true);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return e1.toString();
			}

			try {

				int response_code = conn.getResponseCode();

				String serial = null;

				JSONObject errorObj = new JSONObject();

				try {
					errorObj.put("Success", false);

					Class<?> c = Class.forName("android.os.SystemProperties");
					Method get = c.getMethod("get", String.class);
					serial = (String) get.invoke(c, "ro.serialno");//31007a81d4b22300
				} catch (Exception ignored) {
				}


				String data =OrderToJson();


				HttpClient client = new DefaultHttpClient();


				HttpPost post = new HttpPost();
				post = new HttpPost("http://mobilews.eligasht.com/LightServices/Rest/Flight/FlightService.svc/PurchaseFlight");
				post.setHeader("Content-Type", "application/json; charset=UTF-8");
				post.setHeader("Accept", "application/json; charset=UTF-8");


				StringEntity se = null;
				try {
					se = new StringEntity(data, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				post.setEntity(se);
				ByteArrayOutputStream os = new ByteArrayOutputStream();


				HashMap<String, String> airport = null;
				mylist = new ArrayList<HashMap<String, String>>();
				HttpResponse res = client.execute(post);
				String retSrc = EntityUtils.toString(res.getEntity(), HTTP.UTF_8);


				return retSrc;

			} catch (IOException e) {
				e.printStackTrace();
				return e.toString();
			} finally {
				conn.disconnect();
			}


		}//end doin background

		@Override
		protected void onPostExecute(String result) {

			//this method will be running on UI thread

			pdLoading.dismiss();


			pdLoading.dismiss();
			try {
////////////////////////////
				JSONObject jsonObj = new JSONObject(result);
				JSONObject GetError=null;
				// JSONObject jsonObj = new JSONObject(retSrc);

				// Getting JSON Array node
				JSONObject GetAirportsResult = jsonObj.getJSONObject("PurchaseFlightResult");//Error
				if(!GetAirportsResult.getString("Error").equals("null")){
				 GetError = GetAirportsResult.getJSONObject("Error");
				}
				if (GetError != null) {


						Toast.makeText(PassengerActivity.this, "لطفا یک پرواز دیگر را چک کنید ! خطا در پرواز", Toast.LENGTH_LONG).show();
				}else{


				JSONArray jArray = GetAirportsResult.getJSONArray("Services");
				JSONObject jsonResult = GetAirportsResult.getJSONObject("TmpReserveResult");

				Prefs.putString("BookingCode_NumFactor", jsonResult.getString("BookingCode"));
				//////////////////////////////
				//  JSONArray jArray = new JSONArray(result);

				// Extract data from json and store into ArrayList as class objects
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject json_data = jArray.getJSONObject(i);

					PurchaseFlightResult fishData = new PurchaseFlightResult();
					fishData.setCityEn(json_data.getString("CityEn"));
					fishData.setCityFa(json_data.getString("CityFa"));
					fishData.setCurrency_ID(json_data.getString("Currency_ID"));

					fishData.setHasFlight(json_data.getString("HasFlight"));
					fishData.setHasHotel(json_data.getString("HasHotel"));
					fishData.setLoadDB(json_data.getString("LoadDB"));

					fishData.setServiceAdlPrice(json_data.getString("ServiceAdlPrice"));
					fishData.setServiceChdPrice(json_data.getString("ServiceChdPrice"));
					fishData.setServiceDescEn(json_data.getString("ServiceDescEn"));

					fishData.setServiceDescFa(json_data.getString("ServiceDescFa"));
					fishData.setServiceID(json_data.getString("ServiceID"));
					fishData.setServiceImgURL(json_data.getString("ServiceImgURL"));

					fishData.setServiceInfPrice(json_data.getString("ServiceInfPrice"));
					fishData.setServiceNameEn(json_data.getString("ServiceNameEn"));
					fishData.setServiceNameFa(json_data.getString("ServiceNameFa"));


					fishData.setServiceTypeEn(json_data.getString("ServiceTypeEn"));
					fishData.setServiceTypeFa(json_data.getString("ServiceTypeFa"));
					fishData.setServiceTypeID(json_data.getString("ServiceTypeID"));

					fishData.setServiceTotalPrice(json_data.getLong("ServiceTotalPrice"));
					fishData.setSelectID(json_data.getString("SelectID"));
					fishData.setFlag(false);
					data.add(fishData);
				}

				// Setup and Handover data to recyclerview

				linear_saler.setVisibility(View.GONE);
				linear_mosaferan.setVisibility(View.GONE);
				linear_pish_factor.setVisibility(View.GONE);
				linear_list_khadamat.setVisibility(View.VISIBLE);
					/*myScrollView.setSmoothScrollingEnabled(false);
				myScrollView.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						return true;
					}
				});*/

				((ImageView) findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
				((Button) findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
				txtTitle.setText(" افزودن خدمات به سبد خرید");

				mAdapter = new GetKhadmatAdapter(PassengerActivity.this, data, PassengerActivity.this);
				//mAdapter.setAdapter(mAdapter);
				mAdapter.setData(data);
				listKhadamat.setAdapter(mAdapter);
			}
			} catch (JSONException e) {
				Toast.makeText(PassengerActivity.this, "ارتباط با سرور برقرار نشد !!", Toast.LENGTH_LONG).show();
			}

		}//end on pos excute

	}//end async
	public String OrderToJson() {
		JSONObject jsone = new JSONObject();
		JSONObject manJson = new JSONObject();


		try {
			String GUID ="";
			String ResultUniqId="";
			Bundle extras = getIntent().getExtras();
			if(extras != null){
				GUID = extras.getString("Flight_GUID");
				ResultUniqId = SearchParvazActivity.globalResultUniqID;
			}

			JSONObject json = new JSONObject();
			JSONObject headerJson = new JSONObject();
			JSONArray detailJsonArray = new JSONArray();
			JSONObject detailsJson = new JSONObject();
			JSONObject detailsPartner = new JSONObject();
			JSONObject identityJson = new JSONObject();

			headerJson.put("EchoToken",ResultUniqId);
			headerJson.put("BookingReferenceID", GUID);///ID.toString()

			//mosaferan
			PassengerMosaferItems_Table items_Table=new PassengerMosaferItems_Table(PassengerActivity.this);
			CursorManager cursorM=items_Table.getAllMosafer();
			if(cursorM != null){
				for (int i = 0; i < cursorM.getCount(); i++) {

					cursorM.moveToPosition(i);

					detailsJson = new JSONObject();
					detailsJson.put("Gender",cursorM.getString(PassengerMosaferItems_Table.Columns.Gender.value()));
					detailsJson.put("Nationality", cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality.value()));
					detailsJson.put("Nationality_ID",cursorM.getString(PassengerMosaferItems_Table.Columns.Nationality_ID.value()));

					detailsJson.put("RqPassenger_Address", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Address.value()));
					detailsJson.put("RqPassenger_Birthdate", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Birthdate.value()));
					detailsJson.put("RqPassenger_Email",cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Email.value()));

					detailsJson.put("RqPassenger_FirstNameEn", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameEn.value()));
					detailsJson.put("RqPassenger_FirstNameFa", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_FirstNameFa.value()));
					detailsJson.put("RqPassenger_LastNameEn",cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameEn.value()));

					detailsJson.put("RqPassenger_LastNameFa", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_LastNameFa.value()));
					detailsJson.put("RqPassenger_Mobile", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Mobile.value()));
					detailsJson.put("RqPassenger_NationalCode",cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_NationalCode.value()));

					detailsJson.put("RqPassenger_PassExpDate", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassExpDate.value()));
					detailsJson.put("RqPassenger_PassNo", cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_PassNo.value()));
					detailsJson.put("RqPassenger_Tel",cursorM.getString(PassengerMosaferItems_Table.Columns.RqPassenger_Tel.value()));

					detailJsonArray.put(detailsJson);


				}
				headerJson.put("passList", detailJsonArray);
			}

			////kharidar
			PassengerPartnerInfo_Table partnerInfo_Table=new PassengerPartnerInfo_Table(PassengerActivity.this);
			CursorManager cursorManager=partnerInfo_Table.getPartner();
			cursorManager.moveToPosition(0);
			detailsPartner.put("RqPartner_Address", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Address.value()));
			detailsPartner.put("RqPartner_Email", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Email.value()));
			detailsPartner.put("RqPartner_FirstNameFa", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_FirstNameFa.value()));
			detailsPartner.put("RqPartner_Gender", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Gender.value()));
			detailsPartner.put("RqPartner_LastNameFa", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_LastNameFa.value()));
			detailsPartner.put("RqPartner_Mobile", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Mobile.value()));
			detailsPartner.put("RqPartner_NationalCode", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_NationalCode.value()));
			detailsPartner.put("RqPartner_Tel", cursorManager.getString(PassengerPartnerInfo_Table.Columns.RqPartner_Tel.value()));

			headerJson.put("partnerInfo", detailsPartner);

			headerJson.put("Culture", "fa-IR");

			identityJson.put("Password", "123qwe!@#QWE");
			identityJson.put("TermianlId", "Mobile");
			identityJson.put("UserName", "EligashtMlb");
			headerJson.put("identity",identityJson);

			jsone.put("request",headerJson);


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsone.toString();
	}
	public String OrderToJsonGetPreFactorDetails() {
		JSONObject jsone = new JSONObject();
		JSONObject manJson = new JSONObject();
		JSONObject identityJson = new JSONObject();


		try {
			manJson.put("Culture", "fa-IR");

			manJson.put("invoiceNo", tvfactorNumber.getText().toString());//perches service


			identityJson.put("Password", "123qwe!@#QWE");
			identityJson.put("TermianlId", "Mobile");
			identityJson.put("UserName", "EligashtMlb");
			manJson.put("identity",identityJson);
			//manJson.put("CityCode",URLEncoder.encode(GetAirportActivity.searchText,"UTF-8"));
			jsone.put("request",manJson);


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsone.toString();
	}
	public String OrderToJsonPishFactor() {
			/* public class PurchaseServiceReq
			    {
			        public string RqBaseID { get; set; } // "782528"  => شماره پیش فاکتور
			        public string ServiceStr { get; set; } // I1515:1877300|S1303:1877300|S1304:1877300     => کد سرویس2: کد مسافر1 ،کد مسافر2 کد سرویس: کد مسافر1 ،کد مسافر2 |
			        public string Exc { get; set; }
			        public string InsCoverageXML { get; set; }
			        public string InsPrcieXML { get; set; }
			        public int InsPlanCode { get; set; }
			    }*/
		JSONObject jsone = new JSONObject();
		JSONObject manJson = new JSONObject();
		JSONObject identityJson = new JSONObject();


		try {
			manJson.put("Culture", "fa-IR");

			manJson.put("RqBaseID", Prefs.getString("BookingCode_NumFactor", ""));
			manJson.put("ServiceStr", Prefs.getString("Select_ID_khadamat", ""));
			Prefs.putString("Select_ID_khadamat","");//khali kardan field
			manJson.put("Exc", "");
			manJson.put("InsCoverageXML","");

			manJson.put("InsPrcieXML","");
			manJson.put("InsPlanCode",-1);

			identityJson.put("Password", "123qwe!@#QWE");
			identityJson.put("TermianlId", "Mobile");
			identityJson.put("UserName", "EligashtMlb");
			manJson.put("identity",identityJson);
			//manJson.put("CityCode",URLEncoder.encode(GetAirportActivity.searchText,"UTF-8"));
			jsone.put("request",manJson);


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsone.toString();


	}
	@Override
	public void onClick(View v) {
		Fragment fragment2;

		switch (v.getId()) {


			case R.id.txtMore:

				linearMahaleeghamat.setVisibility(View.VISIBLE);
				linearMeliyat.setVisibility(View.VISIBLE);
				break;

			case R.id.btnBack:


////
				if (linear_pish_factor.getVisibility() == View.VISIBLE) {
					linear_pish_factor.setVisibility(View.GONE);
					linear_list_khadamat.setVisibility(View.VISIBLE);
					/*myScrollView.setSmoothScrollingEnabled(false);
					myScrollView.setOnTouchListener(new View.OnTouchListener() {
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							return true;
						}
					});*/

					((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
					((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#aaaaaa"));
					txtTitle.setText(" افزودن خدمات به سبد خرید");
				}else if (linear_list_khadamat.getVisibility() == View.VISIBLE) {
					linear_list_khadamat.setVisibility(View.GONE);
					linear_mosaferan.setVisibility(View.VISIBLE);
						//myScrollView.setOnTouchListener(null);

					txtTitle.setText("  اطلاعات مسافران ");
					((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
					((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#aaaaaa"));
				}else if (linear_mosaferan.getVisibility() == View.VISIBLE) {
					linear_mosaferan.setVisibility(View.GONE);
					linear_saler.setVisibility(View.VISIBLE);
					//	myScrollView.setOnTouchListener(null);


					txtTitle.setText("  مشخصات خریدار ");
					((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
					((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#aaaaaa"));
				}else if(linear_saler.getVisibility() == View.VISIBLE) {
					finish();
				}
				break;
			case R.id.btn_next_partnerInfo:

				try{
					//jadvale mosafer khali beshe

					PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerActivity.this);
					//   db.openDB();
					db.dropTable();
					////////////////////////Validate
					String RqPartner_Address= "No.7,23rd St.,Khaled Eslamboli St.,Tehran,Iran";
					String RqPartner_Email= txtemeliP.getText().toString();
					String RqPartner_FirstNameFa= txtnameP.getText().toString();
					String RqPartner_Gender= Gensiyat;
					String RqPartner_LastNameFa= txtfamilyP.getText().toString();
					String RqPartner_Mobile= txtmobileP.getText().toString();
					String RqPartner_NationalCode= txtkodemeliP.getText().toString();
					String RqPartner_Tel= "21587632";

					/*String RqPartner_Address= "No.7,23rd St.,Khaled Eslamboli St.,Tehran,Iran";
					String RqPartner_Email= "mohebbi@eligasht.com";
					String RqPartner_FirstNameFa= "مریم";
					String RqPartner_Gender= "Female";
					String RqPartner_LastNameFa= "محبی";
					String RqPartner_Mobile= "0235884";
					String RqPartner_NationalCode= "0062532148";
					String RqPartner_Tel= "21587632";*/

					String flagMosafer="T";
					///Validate
					if( RqPartner_Email.trim().length()>6 ){
						((EditText)findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#aaaaaa"));
						flagMosafer=flagMosafer+"T";
					}else{
						((EditText)findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#ff3300"));
						flagMosafer=flagMosafer+"F";
					}
					if(RqPartner_FirstNameFa != null && RqPartner_FirstNameFa.length()>1){
						((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#aaaaaa"));
						flagMosafer=flagMosafer+"T";
					}else{
						((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#ff3300"));
						flagMosafer=flagMosafer+"F";
					}
					if(RqPartner_LastNameFa != null && RqPartner_LastNameFa.length()>1){
						((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#aaaaaa"));
						flagMosafer=flagMosafer+"T";
					}else{
						((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
						flagMosafer=flagMosafer+"F";
					}
					if(RqPartner_Mobile != null && RqPartner_Mobile.length()>4 && RqPartner_Mobile.trim().matches("[0-9]+")){
						((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#aaaaaa"));
						flagMosafer=flagMosafer+"T";
					}else{
						((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#ff3300"));
						flagMosafer=flagMosafer+"F";
					}
					if(RqPartner_NationalCode != null)
						if( RqPartner_NationalCode.length()>1 && RqPartner_NationalCode.trim().matches("[0-9]+")){
							((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#aaaaaa"));
							flagMosafer=flagMosafer+"T";
						}else{
							((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#ff3300"));
							flagMosafer=flagMosafer+"F";
						}
					//////////////////////////End Validate
					if(flagMosafer.contains("F")){
						Toast.makeText(this,"اطلاعات ورودی نامعتبر است!",2000).show();
					}else{
						//insert partner
						PassengerPartnerInfo_Table partnerInfo_Table = new PassengerPartnerInfo_Table(PassengerActivity.this);

						partnerInfo_Table.dropTable();
						partnerInfo_Table.openDB();




						partnerInfo_Table.insertData(RqPartner_Address, RqPartner_Email, RqPartner_FirstNameFa, RqPartner_Gender, RqPartner_LastNameFa, RqPartner_Mobile, RqPartner_NationalCode, RqPartner_Tel);


						partnerInfo_Table.closeDB();
						////////////////
						linear_saler.setVisibility(View.GONE);
						linear_pish_factor.setVisibility(View.GONE);
						linear_mosaferan.setVisibility(View.VISIBLE);
						txtTitle.setText(" اطلاعات مسافران ");
						//((Button)findViewById(R.id.btn_saler)).setBackgroundResource(R.drawable.blue_line_with_arrow_small);
						//((Button)findViewById(R.id.btn_saler)).setTextColor(Color.parseColor("#33ccff"));//

						((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);
						((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
					}
				}catch (Exception e) {
					System.out.println("Exception ::"+e);
				}
				break;
			case R.id.txttavalodm:
				DialogFragment newFragment2 = new DatePickerFragment();
				newFragment2.show(getFragmentManager(), "datePicker");
				flag = true;
				break;
			case  R.id.txtexp_passport:
				DialogFragment newFragment3 = new DatePickerFragment();
				newFragment3.show(getFragmentManager(), "datePicker");
				flag = false;
				break;
			case R.id.btn_nextm:

				String Gender= Gensiyat;
				String Nationality=txtmahale_eghamat.getText().toString();// "ir";
				String Nationality_ID= txtmeliyatm.getText().toString().toLowerCase();
				String RqPassenger_Address= "No.7,23rd St.,Khaled Eslamboli St.,Tehran,Iran";
				String RqPassenger_Birthdate= txttavalodm.getText().toString();
				String RqPassenger_Email= "mohebbi@eligasht.com";
				String RqPassenger_FirstNameEn= txtnamem.getText().toString();
				String RqPassenger_FirstNameFa= "مریم";
				String RqPassenger_LastNameEn=txtfamilym.getText().toString();
				String RqPassenger_LastNameFa= "محبی";
				String RqPassenger_Mobile= "0235588456";
				String RqPassenger_NationalCode= "0062532148";//codemeli
				String RqPassenger_PassExpDate= txtexp_passport.getText().toString();
				String RqPassenger_PassNo=txtnumber_passport.getText().toString();
				String RqPassenger_Tel= "25548632";

				/*String Gender= "Female";
				String Nationality= "ir";
				String Nationality_ID= "iran";
				String RqPassenger_Address= "No.7,23rd St.,Khaled Eslamboli St.,Tehran,Iran";
				String RqPassenger_Birthdate= "1997/12/23";
				String RqPassenger_Email= "mohebbi@eligasht.com";
				String RqPassenger_FirstNameEn= "Maryam";
				String RqPassenger_FirstNameFa= "مریم";
				String RqPassenger_LastNameEn="Mohebi";
				String RqPassenger_LastNameFa= "محبی";
				String RqPassenger_Mobile= "0235588456";
				String RqPassenger_NationalCode= "0062532148";
				String RqPassenger_PassExpDate= "2018/08/23";
				String RqPassenger_PassNo= "d1234567";
				String RqPassenger_Tel= "25548632";*/

				String flagMosafer="T";
				///Validate
				if( RqPassenger_PassNo.trim().length()>6 && RqPassenger_PassNo.trim().length()<10 && (RqPassenger_PassNo.trim().substring(0,1).matches("^[a-zA-Z]+$")) && RqPassenger_PassNo.trim().substring(1, RqPassenger_PassNo.length()-1).matches("[0-9]+")){
					((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#aaaaaa"));
					flagMosafer=flagMosafer+"T";
				}else{
					((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
					flagMosafer=flagMosafer+"F";
				}
				if(Nationality != null && Nationality.length()>1){
					((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#aaaaaa"));
					flagMosafer=flagMosafer+"T";
				}else{
					((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#ff3300"));
					flagMosafer=flagMosafer+"F";
				}
				if(Nationality_ID != null && Nationality_ID.length()>1){
					((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#aaaaaa"));
					flagMosafer=flagMosafer+"T";
				}else{
					((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#ff3300"));
					flagMosafer=flagMosafer+"F";
				}
				if(RqPassenger_Birthdate != null && RqPassenger_Birthdate.length()>4){
					((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#aaaaaa"));
					flagMosafer=flagMosafer+"T";
				}else{
					((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#ff3300"));
					flagMosafer=flagMosafer+"F";
				}
				if(RqPassenger_FirstNameEn != null)
					if( RqPassenger_FirstNameEn.length()>1 && RqPassenger_FirstNameEn.toLowerCase().trim().matches("^[a-zA-Z]+$")){
						((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#aaaaaa"));
						flagMosafer=flagMosafer+"T";
					}else{
						((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#ff3300"));
						flagMosafer=flagMosafer+"F";
					}
				if(RqPassenger_LastNameEn != null)
					if( RqPassenger_LastNameEn.length()>1 && RqPassenger_LastNameEn.toLowerCase().trim().matches("^[a-zA-Z]+$") ){
						((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#aaaaaa"));
						flagMosafer=flagMosafer+"T";
					}else{
						((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#ff3300"));
						flagMosafer=flagMosafer+"F";
					}
				if(RqPassenger_PassExpDate != null && RqPassenger_PassExpDate.length()>4){
					((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#aaaaaa"));
					flagMosafer=flagMosafer+"T";
				}else{
					((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#ff3300"));
					flagMosafer=flagMosafer+"F";
				}
				///endValidate


				if(flagMosafer.contains("F")){
					Toast.makeText(this,"اطلاعات ورودی نامعتبر است!",2000).show();
				}else{
					PassengerMosaferItems_Table db = new PassengerMosaferItems_Table(PassengerActivity.this);

					//db.dropTable();
					db.openDB();


					if(sum>0){

						db.insertData(Gender, Nationality, Nationality_ID, RqPassenger_Address, RqPassenger_Birthdate, RqPassenger_Email, RqPassenger_FirstNameEn, RqPassenger_FirstNameFa, RqPassenger_LastNameEn, RqPassenger_LastNameFa, RqPassenger_Mobile, RqPassenger_NationalCode, RqPassenger_PassExpDate, RqPassenger_PassNo, RqPassenger_Tel);
						if(countB>0) {
							txtTitleCountM.setText(" اطلاعات مسافربزرگسال " + counter);
							countB--;
						}else if(countK>0) {
							txtTitleCountM.setText(" اطلاعات مسافرکودک " + counter);
							countK--;
						}else if(countN>0) {
							txtTitleCountM.setText(" اطلاعات مسافرنوزاد " + counter);
							countN--;
						}
						System.out.println("counterMosafer:"+counter);
						counter++;
						sum--;
						///pak kardan data haye mosafere ghabli:
						txttavalodm.setText("");
						txtnamem.setText("");
						txtfamilym.setText("");
						txtexp_passport.setText("");
						txtnumber_passport.setText("");

						System.out.println("insert:"+"sum:"+sum);
					}
					db.closeDB();
					//insert mosafer

				}


				//call api saler
				if(sum==0){
					System.out.println("APICALL:"+"sum:"+sum);
					System.out.println("insert:");
					new AsyncFetch().execute();

				}
				break;

			case R.id.btn_taeed_khadamat:


				//call api pishFactor
				new AsyncFetchPishFactor().execute();

				//call api GetPreFactorDetails
				new AsyncFetchGetPreFactorDetails().execute();
				break;


			case R.id.txtmeliyatm:
				final Intent intent4 = new Intent(this, NationalitycodeActivity.class);
				startActivityForResult(intent4, 1);

				break;
			case R.id.txtmahale_eghamat:
				final Intent intent3 = new Intent(this, CountrycodeActivity.class);
				startActivityForResult(intent3, 1);
				break;

			case R.id.btn_saler:
				linear_saler.setVisibility(View.VISIBLE);
				linear_mosaferan.setVisibility(View.GONE);
				linear_list_khadamat.setVisibility(View.GONE);
				linear_pish_factor.setVisibility(View.GONE);

				((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
				((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
				((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
				((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#aaaaaa"));
				((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#aaaaaa"));
				((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#aaaaaa"));
				txtTitle.setText(" مشخصات خریدار ");
				//myScrollView.setOnTouchListener(null);
				/*if (linear_pish_factor.getVisibility() == View.VISIBLE){
					linear_pish_factor.setVisibility(View.GONE);
					linear_list_khadamat.setVisibility(View.VISIBLE);

					((Button)findViewById(R.id.btn_pish_factor)).setBackgroundResource(R.drawable.factor_passenger_off);
					txtTitle.setText("مرحله 3/4: افزودن خدمات به سبد خرید");
				}else if (linear_list_khadamat.getVisibility() == View.VISIBLE){
					linear_list_khadamat.setVisibility(View.GONE);
					linear_mosaferan.setVisibility(View.VISIBLE);

					txtTitle.setText("مرحله 2/4:  اطلاعات مسافران را وارد کنید");
					((Button)findViewById(R.id.btn_khadamat)).setBackgroundResource(R.drawable.khadamat_passenger_off);
				}else if (linear_mosaferan.getVisibility() == View.VISIBLE){
					linear_mosaferan.setVisibility(View.GONE);
					linear_saler.setVisibility(View.VISIBLE);

					txtTitle.setText("مرحله 1/4:  مشخصات خریدار را وارد کنید");
					((Button)findViewById(R.id.btn_mosaferan)).setBackgroundResource(R.drawable.mosaferan_passenger_off);
				}*/
				break;
			case R.id.btn_mosaferan:
				linear_saler.setVisibility(View.GONE);
				linear_mosaferan.setVisibility(View.VISIBLE);
				linear_list_khadamat.setVisibility(View.GONE);
				linear_pish_factor.setVisibility(View.GONE);

				((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
				((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
				((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

				((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
				((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#aaaaaa"));
				((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#aaaaaa"));
				txtTitle.setText("  اطلاعات مسافران ");

				//.setOnTouchListener(null);
				break;
			case R.id.btn_khadamat:
				linear_saler.setVisibility(View.GONE);
				linear_mosaferan.setVisibility(View.GONE);
				linear_list_khadamat.setVisibility(View.VISIBLE);
				linear_pish_factor.setVisibility(View.GONE);

			/*	myScrollView.setSmoothScrollingEnabled(false); // disable scrolling
				myScrollView.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						return true;
					}
				});*/
				//myScrollView.setVisibility(View.GONE);

				((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
				((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
				((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);

				((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
				((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
				((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#aaaaaa"));
				txtTitle.setText(" افزودن خدمات به سبد خرید");
				break;
			case R.id.btn_pish_factor:
				linear_saler.setVisibility(View.GONE);
				linear_mosaferan.setVisibility(View.GONE);
				linear_list_khadamat.setVisibility(View.GONE);
				linear_pish_factor.setVisibility(View.VISIBLE);

				((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_on);
				((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_on);
				((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_on);
				((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#000000"));
				((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#000000"));
				((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#000000"));
				txtTitle.setText(" تایید و پرداخت پیش فاکتور    ");
			//myScrollView.setOnTouchListener(null);
				break;
			case R.id.txt_hom:
				Prefs.putBoolean("BACK_HOME",true);
			//	myScrollView.setOnTouchListener(null);
				finish();
				//this.startActivity(i4);
				break;
		}

	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1 && resultCode == Activity.RESULT_OK){
			String countryCode = data.getStringExtra(CountrycodeActivity.RESULT_CONTRYCODE);//RESULT_CONTRYNAME
			String countryName = data.getStringExtra(CountrycodeActivity.RESULT_CONTRYNAME);

			String nationalityCode = data.getStringExtra(NationalitycodeActivity.RESULT_NATIONALITYCODE);
			String nationalityName = data.getStringExtra(NationalitycodeActivity.RESULT_NATIONALITYNAME);
			//Toast.makeText(this, "You selected countrycode: " + countryCode, Toast.LENGTH_LONG).show();
			if(countryCode != null)
				txtmahale_eghamat.setText(countryCode+"");//txtmahale_eghamat.setText(countryCode+" "+countryName);
			if(nationalityCode != null)
				txtmeliyatm.setText(nationalityCode+"");//txtmeliyatm.setText(nationalityCode+" "+nationalityName);
		}
	}

	public static class DatePickerFragment extends DialogFragment
			implements DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			DatePickerDialog dialog = null;
			if(flag){//tavalodm
				final Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH);
				int day = c.get(Calendar.DAY_OF_MONTH);
				dialog = new DatePickerDialog(getActivity(), this, year-30, month, day);
				// dialog.getDatePicker().setMinDate(c.getTimeInMillis());
			}else{//expPasport
				final Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH);
				int day = c.get(Calendar.DAY_OF_MONTH);
				dialog = new DatePickerDialog(getActivity(), this, year+1, month, day);//1997/12/23

		 	   /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		 	    Date mDate;*/
				// dialog.getDatePicker().setMinDate(c.getTimeInMillis());
			}
			//dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
			return  dialog;
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			String sMonth=String.valueOf(month+1);
			String sDay=String.valueOf(day);
			if(sMonth.length()==1)
				sMonth="0"+sMonth;

			if(sDay.length()==1)
				sDay = "0"+sDay;
			if(flag){
				txttavalodm.setText(year+"/" +sMonth +"/"+ sDay);
			}else{
				txtexp_passport.setText(year+"/" + sMonth +"/" + sDay);
			}



		}
	}//endDatepicker
	void ClearMenu(View v){//android:background="@drawable/blue_line_with_arrow_small"
		//android:background="@drawable/trans_line_with_arrow_small"
		((ImageView)findViewById(R.id.btn_saler)).setImageResource(R.drawable.trans_line_with_arrow_small);
		((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.trans_line_with_arrow_small);
		((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.trans_line_with_arrow_small);
		((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.trans_line_with_arrow_small);

		((Button)findViewById(R.id.txtSaler)).setTextColor(Color.parseColor("#808080"));
		((Button)findViewById(R.id.btn_mosaferan)).setTextColor(Color.parseColor("#808080"));
		((Button)findViewById(R.id.btn_khadamat)).setTextColor(Color.parseColor("#808080"));
		((Button)findViewById(R.id.btn_pish_factor)).setTextColor(Color.parseColor("#808080"));

		//((TextView)findViewById(R.id.imageDiscover)).setBackgroundDrawable(null);
		//((TextView)findViewById(R.id.imageDiscover)).setTextColor(Color.WHITE);
		if(v!=null)
		{
			v.setBackgroundResource(R.drawable.blue_line_with_arrow_small);
			((TextView)v).setTextColor(Color.parseColor("#33ccff"));
		}
		//if(currentMenu!=null) currentMenu.finish();
	}
	@Override
	public void onBackPressed() {

			/* Intent intent = new Intent(this,PlanFragment.class);
				//i2.putExtra("CUSTOMER_ID", (int) customerID);
				startActivity(intent);*/
		if (linear_pish_factor.getVisibility() == View.VISIBLE) {
			linear_pish_factor.setVisibility(View.GONE);
			linear_list_khadamat.setVisibility(View.VISIBLE);
			/*myScrollView.setSmoothScrollingEnabled(false);
			myScrollView.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return true;
				}
			});*/
			((ImageView)findViewById(R.id.btn_pish_factor)).setImageResource(R.drawable.factor_passenger_off);
			((Button)findViewById(R.id.txtPishfactor)).setTextColor(Color.parseColor("#aaaaaa"));
			txtTitle.setText(" افزودن خدمات به سبد خرید");
		}else if (linear_list_khadamat.getVisibility() == View.VISIBLE) {
			linear_list_khadamat.setVisibility(View.GONE);
			linear_mosaferan.setVisibility(View.VISIBLE);
				//myScrollView.setOnTouchListener(null);

			txtTitle.setText("  اطلاعات مسافران ");
			((ImageView)findViewById(R.id.btn_khadamat)).setImageResource(R.drawable.khadamat_passenger_off);
			((Button)findViewById(R.id.txtKhadamat)).setTextColor(Color.parseColor("#aaaaaa"));
		}else if (linear_mosaferan.getVisibility() == View.VISIBLE) {
			linear_mosaferan.setVisibility(View.GONE);
			linear_saler.setVisibility(View.VISIBLE);
				//myScrollView.setOnTouchListener(null);

			txtTitle.setText(" مشخصات خریدار ");
			((ImageView)findViewById(R.id.btn_mosaferan)).setImageResource(R.drawable.mosaferan_passenger_off);
			((Button)findViewById(R.id.txtMasaferan)).setTextColor(Color.parseColor("#aaaaaa"));
		}else if(linear_saler.getVisibility() == View.VISIBLE) {
			/*Intent intent = new Intent(this,PlanFragment.class);
			//i2.putExtra("CUSTOMER_ID", (int) customerID);
			startActivity(intent);*/
			//PassengerActivity.this.finish();
			finish();
		}
	}
	@Override
	public void searchTextChanged(String searchText) {
			/*this.searchText = searchText;
			if(searchText.length()>2)
			new AsyncFetch().execute();*/
		//mAdapter.setData(searchText);

	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
							   long id) {
		// On selecting a spinner item
		String item = parent.getItemAtPosition(position).toString();
		if(item.equals("زن"))
			Gensiyat="Female";
		else
			Gensiyat="Man";
		// Showing selected spinner item
		//Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}


	public static void updateTotalInfos(long serviceTotalPrice) {
		// TODO Auto-generated method stub
		//GET_PRICE_KHADAMAT=GET_PRICE_KHADAMAT+serviceTotalPrice;
		//txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(GET_PRICE_KHADAMAT))+"");
		txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(serviceTotalPrice))+"");
		/*for (int i =0 ;i<data.size();i++){
			if(data.get(i).isFlag()){
				GET_PRICE_KHADAMAT=GET_PRICE_KHADAMAT+data.get(i).getServiceTotalPrice();
			}

		}
		txtSumKhadamat.setText(String.valueOf(NumberFormat.getInstance().format(GET_PRICE_KHADAMAT))+"");*/
	}

	private class GenericTextWatcher implements TextWatcher{

		private View view;
		private GenericTextWatcher(View view) {
			this.view = view;
		}

		public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
		public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

		public void afterTextChanged(Editable editable) {
			String text = editable.toString();

			switch(view.getId()){
				//مسافر
				case R.id.txtmahale_eghamat:
					if(text != null && text.length()>1){
						((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#aaaaaa"));
						//flagMosafer=flagMosafer+"T";
					}else{
						((TextView)findViewById(R.id.txtmahale_eghamat)).setTextColor(Color.parseColor("#ff3300"));
						txtmahale_eghamat.setError("لطفا محل اقامت را وارد کنید ");
					}
					break;
				case R.id.txtmeliyatm:
					if(text != null && text.length()>1){
						((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#aaaaaa"));
						//flagMosafer=flagMosafer+"T";
					}else{
						((TextView)findViewById(R.id.txtmeliyatm)).setTextColor(Color.parseColor("#ff3300"));
						txtmeliyatm.setError("لطفا ملیت را وارد کنید ");
					}
					break;
				case R.id.txttavalodm:
					if(text != null && text.length()>4){
						((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#aaaaaa"));
						//flagMosafer=flagMosafer+"T";
					}else{
						((TextView)findViewById(R.id.txttavalodm)).setTextColor(Color.parseColor("#ff3300"));
						txttavalodm.setError("لطفا تاریخ تولد را وارد کنید ");
					}
					break;

				case R.id.txtnamem:
					if(text != null)
						if( text.length()>1 && text.toLowerCase().trim().matches("^[a-zA-Z]+$")){
							((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#aaaaaa"));
							//flagMosafer=flagMosafer+"T";
						}else{
							((EditText)findViewById(R.id.txtnamem)).setTextColor(Color.parseColor("#ff3300"));
							txtnamem.setError("لطفا نام را انگلیسی وارد کنید ");
						}
					break;
				case R.id.txtfamilym:
					if(text != null)
						if( text.length()>1 && text.toLowerCase().trim().matches("^[a-zA-Z]+$") ){
							((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#aaaaaa"));
							//flagMosafer=flagMosafer+"T";
						}else{
							((EditText)findViewById(R.id.txtfamilym)).setTextColor(Color.parseColor("#ff3300"));
							txtfamilym.setError("لطفا نام خانوادگی را انگلیسی وارد کنید ");
						}
					break;
				case R.id.txtexp_passport:
					if(text != null && text.length()>4){
						((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#aaaaaa"));

					}else{
						((TextView)findViewById(R.id.txtexp_passport)).setTextColor(Color.parseColor("#ff3300"));
						txtexp_passport.setError("لطفا انقضاء پاسپورت را وارد کنید ");
					}
					break;
				case R.id.txtnumber_passport:

					if( text.trim().length()>6 && text.trim().length()<10 && (text.trim().substring(0,1).matches("^[a-zA-Z]+$")) && text.trim().substring(1, text.length()-1).matches("[0-9]+")){
						((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#aaaaaa"));

					}else{
						((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
						txtnumber_passport.setError("لطفا شماره پاسپورت را صحیح وارد کنید ");
					}
					if(text != null && text.length()>4){
					}else{
						((EditText)findViewById(R.id.txtnumber_passport)).setTextColor(Color.parseColor("#ff3300"));
						txtnumber_passport.setError("لطفا شماره پاسپورت را وارد کنید ");
					}

					break;

				//خریدار
				case R.id.txtemeliP:
					String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
					if (text.matches(emailPattern) && text.length() > 0){
						//if( Patterns.EMAIL_ADDRESS.matcher(text).matches() ){
						((EditText)findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#aaaaaa"));

					}else{
						((EditText)findViewById(R.id.txtemeliP)).setTextColor(Color.parseColor("#ff3300"));
						txtemeliP.setError("لطفا ایمیل را وارد کنید ");
					}

					break;
				case R.id.txtnameP:

					if(text != null && text.length()>1){
						((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#aaaaaa"));

					}else{
						((EditText)findViewById(R.id.txtnameP)).setTextColor(Color.parseColor("#ff3300"));
						txtnameP.setError("لطفا نام را فارسی وارد کنید ");
					}
					break;
				case R.id.txtfamilyP:

					if(text != null && text.length()>1){
						((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#aaaaaa"));

					}else{
						((EditText)findViewById(R.id.txtfamilyP)).setTextColor(Color.parseColor("#ff3300"));
						txtfamilyP.setError("لطفا نام خانوادگی را فارسی وارد کنید ");
					}
					break;

				case R.id.txtmobileP:

					if(text != null && text.length()>9 && text.trim().matches("[0-9]+")){
						((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#aaaaaa"));

					}else{
						((EditText)findViewById(R.id.txtmobileP)).setTextColor(Color.parseColor("#ff3300"));
						txtmobileP.setError("لطفا شماره موبایل را وارد کنید ");
					}
					break;
				case R.id.txtkodemeliP:
					if(text != null)
						if( text.length()>9 &&  text.length()<12 && text.trim().matches("[0-9]+")){
							((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#aaaaaa"));

						}else{
							((EditText)findViewById(R.id.txtkodemeliP)).setTextColor(Color.parseColor("#ff3300"));
							txtkodemeliP.setError("لطفا کد ملی را وارد کنید ");
						}
					break;

			}
		}
	}





	public static Bitmap getBitmap(String barcode, int barcodeType, int width, int height)
	{
		Bitmap barcodeBitmap = null;
		BarcodeFormat barcodeFormat = convertToZXingFormat(barcodeType);
		try
		{
			barcodeBitmap = encodeAsBitmap(barcode, barcodeFormat, width, height);
		}
		catch (WriterException e)
		{
			e.printStackTrace();
		}
		return barcodeBitmap;
	}

	private static BarcodeFormat convertToZXingFormat(int format)
	{
		switch (format)
		{
			case 8:
				return BarcodeFormat.CODABAR;
			case 1:
				return BarcodeFormat.CODE_128;
			case 2:
				return BarcodeFormat.CODE_39;
			case 4:
				return BarcodeFormat.CODE_93;
			case 32:
				return BarcodeFormat.EAN_13;
			case 64:
				return BarcodeFormat.EAN_8;
			case 128:
				return BarcodeFormat.ITF;
			case 512:
				return BarcodeFormat.UPC_A;
			case 1024:
				return BarcodeFormat.UPC_E;
			//default 128?
			default:
				return BarcodeFormat.CODE_128;
		}
	}


	/**************************************************************
	 * getting from com.google.zxing.client.android.encode.QRCodeEncoder
	 *
	 * See the sites below
	 * http://code.google.com/p/zxing/
	 * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/EncodeActivity.java
	 * http://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/encode/QRCodeEncoder.java
	 */

	private static final int WHITE = 0xFFFFFFFF;
	private static final int BLACK = 0xFF000000;

	private static Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException
	{
		if (contents == null) {
			return null;
		}
		Map<EncodeHintType, Object> hints = null;
		String encoding = guessAppropriateEncoding(contents);
		if (encoding != null) {
			hints = new EnumMap<>(EncodeHintType.class);
			hints.put(EncodeHintType.CHARACTER_SET, encoding);
		}
		MultiFormatWriter writer = new MultiFormatWriter();
		BitMatrix result;
		try {
			result = writer.encode(contents, format, img_width, img_height, hints);
		} catch (IllegalArgumentException iae) {
			// Unsupported format
			return null;
		}
		int width = result.getWidth();
		int height = result.getHeight();
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			int offset = y * width;
			for (int x = 0; x < width; x++) {
				pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
			}
		}

		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	private static String guessAppropriateEncoding(CharSequence contents) {
		// Very crude at the moment
		for (int i = 0; i < contents.length(); i++) {
			if (contents.charAt(i) > 0xFF) {
				return "UTF-8";
			}
		}
		return null;
	}
}