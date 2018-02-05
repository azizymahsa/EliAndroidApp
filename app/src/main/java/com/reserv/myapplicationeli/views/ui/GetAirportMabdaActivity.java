package com.reserv.myapplicationeli.views.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.models.Country;
import com.reserv.myapplicationeli.tools.db.local.RecentCity_Table;
import com.reserv.myapplicationeli.tools.db.main.CursorManager;
import com.reserv.myapplicationeli.views.adapters.GetAirPortMabdaAdapter;
import com.reserv.myapplicationeli.views.adapters.GetAirPortMaghsadAdapter;
import com.reserv.myapplicationeli.views.components.Header;
import com.reserv.myapplicationeli.views.ui.dialog.hotel.AlertDialogPassenger;
import com.wang.avi.AVLoadingIndicatorView;


public class GetAirportMabdaActivity extends BaseActivity implements Header.onSearchTextChangedListener,OnClickListener{
	   public static final int CONNECTION_TIMEOUT = 10000;
	    public static final int READ_TIMEOUT = 15000;
		Handler handler;
		ProgressDialog progressBar;
		private Handler progressBarHandler = new Handler();
		public ListView list_airport;
		ArrayList<HashMap<String,String>> mylist=null;
		public static String searchText = "";
		
		GetAirPortMabdaAdapter mAdapter;
		private EditText searchtxt;
	AVLoadingIndicatorView avi;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_get_airport);
			avi=findViewById(R.id.avi);

			//////////////////show recent
			ListView listAirPort = (ListView) findViewById(R.id.listAirPort);
			List<Country> data=new ArrayList<>();
			RecentCity_Table recentCity_table=new RecentCity_Table(this);
			CursorManager cursorManager=recentCity_table.getAll(1);//mabda
			if(cursorManager != null) {
				for (int i = 0; i < cursorManager.getCount(); i++) {
					cursorManager.moveToPosition(i);
					Country fishData = new Country();
					fishData.setCityName(cursorManager.getString(RecentCity_Table.Columns.CityName.value()));
					fishData.setAirportName(cursorManager.getString(RecentCity_Table.Columns.AirPortName.value()));
					fishData.setAirportCode(cursorManager.getString(RecentCity_Table.Columns.AirPortCode.value()));
					fishData.setAirportID(cursorManager.getString(RecentCity_Table.Columns.AirPortCode.value()));
					fishData.setParentId(cursorManager.getString(RecentCity_Table.Columns.CityName.value()));

					data.add(fishData);
				}
			}
			mAdapter = new GetAirPortMabdaAdapter(GetAirportMabdaActivity.this, data,  GetAirportMabdaActivity.this);

			mAdapter.setData(data);
			listAirPort.setAdapter(mAdapter);

			//////////////////////////
			//////////////////////////Remove recent
			CursorManager cursorManager1=recentCity_table.getCountRow();
			System.out.println("count:"+cursorManager1.getInt("COUNT(Id)"));
			if(cursorManager1 != null) {
				if (cursorManager1.getInt("COUNT(Id)") >= 10) {

					CursorManager cursorType1=recentCity_table.getAll(1);//mabda
					CursorManager cursorType2=recentCity_table.getAll(2);//maghsad

					RecentCity_Table db=new RecentCity_Table(this);
					db.dropTable();
					db.openDB();


					if (cursorType1 != null)
						for (int e = cursorType1.getCount()-1; e >= 0; e--) {
							cursorType1.moveToPosition(e);
							db.insertData(cursorType1.getString(RecentCity_Table.Columns.AirPortName.value()),cursorType1.getString(RecentCity_Table.Columns.CityName.value()),cursorType1.getString(RecentCity_Table.Columns.AirPortCode.value()),1);//mabda
						}
					if (cursorType2 != null)
						for (int t = cursorType2.getCount()-1; t >= 0; t--) {
							cursorType2.moveToPosition(t);
							db.insertData(cursorType2.getString(RecentCity_Table.Columns.AirPortName.value()),cursorType2.getString(RecentCity_Table.Columns.CityName.value()),cursorType2.getString(RecentCity_Table.Columns.AirPortCode.value()),2);//maghsad
						}
					db.closeDB();

				}
			}

			/////////////////////////////
			//searchtxt = (EditText) findViewById(R.id.searchtxt);
		    //Make call to AsyncTask

	    	searchtxt = (EditText) findViewById(R.id.searchtxt);
			searchtxt.addTextChangedListener(
	                new TextWatcher() {
	                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
	                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

	                    private Timer timer=new Timer();
	                    private final long DELAY = 10; // milliseconds

	                    @Override
	                    public void afterTextChanged(final Editable s) {
	                        timer.cancel();
	                        timer = new Timer();
	                        timer.schedule(
	                                new TimerTask() {
	                                    @Override
	                                    public void run() {
	                                       runOnUiThread(new Runnable() {
	                                            @Override
	                                            public void run() {
	                                            	String d = s.toString().trim(); 
	                                            	if(d.length()>1){
	                                            		
	                                            	 GetAirportMabdaActivity.searchText = d.toLowerCase();
	                                            	 new AsyncFetch().execute();
	                                            	 
	                                            	 }else {
														if (d.length() < 0 || d.length() == 0) {
															////
															ListView listAirPort = (ListView) findViewById(R.id.listAirPort);
															List<Country> data = null;
															mAdapter = new GetAirPortMabdaAdapter(GetAirportMabdaActivity.this, data, GetAirportMabdaActivity.this);

															mAdapter.setData(data);
															listAirPort.setAdapter(mAdapter);

														}
													}
	                                            }
	                                        });
	                                    }
	                                },
	                                DELAY
	                        );
	                    }
	                }
	        );
	    }//end oncreate

	    private class AsyncFetch extends AsyncTask<String, String, String> {
			HttpURLConnection conn;
			URL url = null;
			private ListView listAirPort;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				avi.setVisibility(View.VISIBLE);

				//this method will be running on UI thread


			}

	        @Override
	        protected String doInBackground(String... params) {
	            try {

	                // Enter URL address where your json file resides
	                // Even you can make call to php file which returns json data
	                url = new URL("http://mobilews.eligasht.com/Services/CommonRest/StaticDataService.svc/GetAirportWithParents");

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
						post = new HttpPost("http://mobilews.eligasht.com/Services/CommonRest/StaticDataService.svc/GetAirportWithParents");
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
						//{"GetAirportWithParentsResult":{"Errors":[],"List":[{"Key":"IST|Istanbul, Turkey (IST-All Airports)","Value":"استانبول ( همه فرودگاه ها ),نزدیک استانبول, ترکیه"},{"Key":"IST|Istan
						//try {
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


	        }

	        @Override
	        protected void onPostExecute(String result) {

	            //this method will be running on UI thread

				avi.setVisibility(View.INVISIBLE);
	            List<Country> data=new ArrayList<Country>();


	            try {

	            	if (!TextUtils.isEmpty(searchtxt.getText())) {


////////////////////////////
						JSONObject jsonObj = new JSONObject(result);

						//JSONObject GetAirportsResult = jsonObj.getJSONObject("GetAirportWithParentsResult");
						/////////////////////////////////////
						String GetError = "";
						JSONArray jError = null;
						// Getting JSON Array node
						JSONObject GetAirportsResult = jsonObj.getJSONObject("GetAirportWithParentsResult");//Error
						if (!GetAirportsResult.getString("Errors").equals("null")) {
							jError = GetAirportsResult.getJSONArray("Errors");//
							JSONObject jPricedItinerary = jError.getJSONObject(0);
							GetError = jPricedItinerary.getString("Message");
						}
						if (GetError.length() > 1) {
							AlertDialogPassenger AlertDialogPassenger = new AlertDialogPassenger(GetAirportMabdaActivity.this);
							AlertDialogPassenger.setText(GetError);

						} else {
////////////////////////////////
							JSONArray jArray = GetAirportsResult.getJSONArray("Airports");//AirportCode //AirportName//CityName ":

							for (int i = 0; i < jArray.length(); i++) {
								JSONObject json_data = jArray.getJSONObject(i);
								Country fishData = new Country();
								fishData.setCityName(json_data.getString("CityName"));
								fishData.setAirportName(json_data.getString("AirportName"));
								fishData.setAirportCode(json_data.getString("AirportCode"));
								fishData.setAirportID(json_data.getString("AirportID"));
								fishData.setParentId(json_data.getString("ParentId"));

								data.add(fishData);
							}


							String Value_Maghsad_City = "";
							String Value_Maghsad_Airport = "";
							String Value_Maghsad_Airport_Code = "";
							////
							if (Prefs.getString("Value-Maghsad-City", "") != null) {
								Value_Maghsad_City = Prefs.getString("Value-Maghsad-City", "");
								Value_Maghsad_Airport = Prefs.getString("Value-Maghsad-Airport", "");
								Value_Maghsad_Airport_Code = Prefs.getString("Value-Maghsad-Airport-Code", "");
							}

							////
							listAirPort = (ListView) findViewById(R.id.listAirPort);
							mAdapter = new GetAirPortMabdaAdapter(GetAirportMabdaActivity.this, data, Value_Maghsad_City, Value_Maghsad_Airport, Value_Maghsad_Airport_Code, GetAirportMabdaActivity.this);

							mAdapter.setData(data);
							listAirPort.setAdapter(mAdapter);
						}
					}
	            } catch (JSONException e) {
					AlertDialogPassenger AlertDialogPassenger =  new AlertDialogPassenger(GetAirportMabdaActivity.this);
					AlertDialogPassenger.setText("در حال حاضر پاسخگویی به درخواست شما امکان پذیر نمی باشد ");
	            }

	        }

	    }//end asynTask
	    public String OrderToJson() {
			JSONObject jsone = new JSONObject();
			JSONObject manJson = new JSONObject();
			
			
			try {
				//{"CreatorUserId":0,"Id":"9a633b86-8735-4060-83a3-4797548f0203","Orderno":6,"CustomerCode":"","Visitor":34,"OrderDate":"1395\/08\/19","OrderTime":"11:25:20","IsEmergancy":0,"TimeCheck":30,"ByTel":0,"SaleType":0,"IsConvert":0,"OrderStatus":7,"SMSCounter":0,"FinishTime":"11:33:03","IsReceived":0,"IsSent":0,"WarehouseId":145,"IsTemp":0,"Serial":"31007a81d4b22300"}
				

				manJson.put("UserName", "EligashtMlb");
				manJson.put("Password", "123qwe!@#QWE");
				manJson.put("TermianlId", "Mobile");
				manJson.put("CityCode",GetAirportMabdaActivity.searchText);
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
			// TODO Auto-generated method stub
			
		}
		@Override
		public void searchTextChanged(String searchText) {
			/*this.searchText = searchText;
			if(searchText.length()>2)
			new AsyncFetch().execute();*/
			//mAdapter.setData(searchText);
			
		}
	}