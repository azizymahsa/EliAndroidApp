package com.reserv.myapplicationeli.views.activities.hotel.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.views.adapters.LazyResoultHotelAdapter;
import com.reserv.myapplicationeli.views.components.Header;


public class SelectHotelActivity extends Activity  implements Header.onSearchTextChangedListener, OnItemClickListener ,OnClickListener{
	

	 ListView list;
	 LazyResoultHotelAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_hotel);
	
		
		  list=(ListView)findViewById(R.id.lvHoteResult);
	        adapter=new LazyResoultHotelAdapter(this, mStrings);
	        list.setAdapter(adapter);
	        
	       /* Button b=(Button)findViewById(R.id.button1);
	        b.setOnClickListener(listener);*/


	
	 }//end oncreat

	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void searchTextChanged(String searchText) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		Fragment fragment2;
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.txtBack:
	
			break;
		
		}
	}
	  @Override
	    public void onDestroy()
	    {
	        list.setAdapter(null);
	        super.onDestroy();
	    }
	    
	    public OnClickListener listener=new OnClickListener(){
	        @Override
	        public void onClick(View arg0) {
	            adapter.imageLoader.clearCache();
	            adapter.notifyDataSetChanged();
	        }
	    };
	    
	    private String[] mStrings={
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/9.jpg",
	            "https://cdnx.elicdn.com/Content/Hotel/3570.jpg",
	            "https://pbs.twimg.com/profile_images/1417650153/android-hug_normal.png",
	            "https://pbs.twimg.com/profile_images/1517737798/aam-twitter-right-final_normal.png",
	            "https://pbs.twimg.com/profile_images/3319660679/70e7025a05b674852b9f3cea0998259c_normal.jpeg",
	            "https://pbs.twimg.com/profile_images/2100693240/58534_150210305010136_148613708503129_315282_6481640_n_normal.jpg",
	            "https://pbs.twimg.com/profile_images/1306095935/androidcoo_normal.png",
	            "https://pbs.twimg.com/profile_images/2938108229/399ba333772228bfbb40134018fbe777_normal.jpeg",
	            "https://pbs.twimg.com/profile_images/487047133392949248/sVTI9rGI_normal.png","https://pbs.twimg.com/profile_images/3092003750/9b72a46e957a52740c667f4c64fa5d10_normal.jpeg",
	            "https://pbs.twimg.com/profile_images/2508170683/m8jf0po4imu8t5eemjdd_normal.png",
	            "https://pbs.twimg.com/profile_images/1701796334/TA-New-Logo_normal.jpg",
	            "https://pbs.twimg.com/profile_images/913338263/AndroidPolice_logo_normal.png",
	            "https://pbs.twimg.com/profile_images/1417650153/android-hug_normal.png",
	            "https://pbs.twimg.com/profile_images/1517737798/aam-twitter-right-final_normal.png",
	            "https://pbs.twimg.com/profile_images/3319660679/70e7025a05b674852b9f3cea0998259c_normal.jpeg",
	            "https://pbs.twimg.com/profile_images/2100693240/58534_150210305010136_148613708503129_315282_6481640_n_normal.jpg",
	            "https://pbs.twimg.com/profile_images/1306095935/androidcoo_normal.png",
	            "https://pbs.twimg.com/profile_images/2938108229/399ba333772228bfbb40134018fbe777_normal.jpeg",
	            "https://pbs.twimg.com/profile_images/487047133392949248/sVTI9rGI_normal.png","https://pbs.twimg.com/profile_images/3092003750/9b72a46e957a52740c667f4c64fa5d10_normal.jpeg",
	            "https://pbs.twimg.com/profile_images/2508170683/m8jf0po4imu8t5eemjdd_normal.png",
	            "https://pbs.twimg.com/profile_images/1701796334/TA-New-Logo_normal.jpg",
	            "https://pbs.twimg.com/profile_images/913338263/AndroidPolice_logo_normal.png",
	            "https://pbs.twimg.com/profile_images/1417650153/android-hug_normal.png",
	            "https://pbs.twimg.com/profile_images/1517737798/aam-twitter-right-final_normal.png",
	            "https://pbs.twimg.com/profile_images/3319660679/70e7025a05b674852b9f3cea0998259c_normal.jpeg",
	            "https://pbs.twimg.com/profile_images/2100693240/58534_150210305010136_148613708503129_315282_6481640_n_normal.jpg",
	            "https://pbs.twimg.com/profile_images/1306095935/androidcoo_normal.png",
	            "https://pbs.twimg.com/profile_images/2938108229/399ba333772228bfbb40134018fbe777_normal.jpeg",
	            "https://pbs.twimg.com/profile_images/487047133392949248/sVTI9rGI_normal.png"
	    };
	
}
