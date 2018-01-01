package com.reserv.myapplicationeli.slidingmenu;


import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.slidingmenu.adapter.NavDrawerListAdapter;
import com.reserv.myapplicationeli.slidingmenu.customViews.dialog.IDM_Dialog;
import com.reserv.myapplicationeli.slidingmenu.db.local.Config_Table;
import com.reserv.myapplicationeli.slidingmenu.model.NavDrawerItem;
import com.reserv.myapplicationeli.slidingmenu.ui.SelectHotelActivity;


public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	public Activity currentMenu=null;
	// nav drawer title
	private CharSequence mDrawerTitle;
	public Fragment fragment2 = null;
	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	public static String GET_FRAGMENT = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//getSupportActionBar().setLogo(R.mipmap.ic_launcher);
		//<!!!!!!!!!!!!!!!!!!!!!>change color header<!!!!!!!!!!!!!!!!!!!!!>
		ActionBar actionbar = getActionBar();
		//for color
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E06F38")));
		//for imagelogo_eli
	//	actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.transparent));
		//actionbar.setIcon(R.drawable.logo_eli);
		
		/*setTitle("My new title");
		getActionBar().setIcon(R.drawable.logo_eli);
		getActionBar().setHomeButtonEnabled(true);
		 getActionBar().setDisplayHomeAsUpEnabled(true);*/
		//<!!!!!!!!!!!!!!!!!!!!!><!!!!!!!!!!!!!!!!!!!!!>
		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		//header baraye list menu
		/*View header = getLayoutInflater().inflate(R.layout.header, null);
		mDrawerList.addHeaderView(header);*/
		///
		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// خریدهای من
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// لیست هتل
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// شارژ اعتباری
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// مدیریت مالی, Will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
		// استرداد بلیط
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		// قوانین و مقررات 
		//navDrawerItems.add(new NavDrawerItem("    "+"________________", navMenuIcons.getResourceId(12, -1)));
		//  تماس با ما What's hot, We  will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));
		// خروج از حساب کاربری  
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
		/*// مدیریت پیام ها
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
		// راهنما
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
		// امنیت, Will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons.getResourceId(9, -1), true, "22"));
		// پروفایل
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[10], navMenuIcons.getResourceId(10, -1)));
		// غیرفعال سازی, We  will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[11], navMenuIcons.getResourceId(11, -1), true, "50+"));*/
		

		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon ///3khate kenar
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}//end oncreat
//////////tab:
	/*///*مشتری
	public void LoadFavorites(View v) {
		ClearMenu(v);
		Fragment fragment = null;
		System.out.println("loaaaaaaaaaad1");
		fragment = new SelectCustomerFragment();
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
		}
	}
//کالا
	public void LoadContacts(View v) {
		ClearMenu(v);
		Fragment fragment = null;
		System.out.println("loaaaaaaaaaad2");
		fragment = new CommunityFragment();
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
		}
	}
//movaghat
	public void LoadDiscover(View v) {
		ClearMenu(v);
		Fragment fragment = null;
		System.out.println("loaaaaaaaaaad3");
		fragment = new ManageRequestFragment();
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
		}
	}

	public void LoadWall(View v) {
		ClearMenu(v);
		Fragment fragment = null;
		System.out.println("loaaaaaaaaaad4");
		fragment = new FindPeopleFragment();
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
		}
		// startActivity(new Intent(this,FindPeopleFragment.class));
	}

////////////end tab
	 void ClearMenu(View v){
			((TextView)findViewById(R.id.txtDiscover)).setBackgroundDrawable(null);
			((TextView)findViewById(R.id.txtMe)).setBackgroundDrawable(null);
			//((TextView)findViewById(R.id.txtWall)).setBackgroundDrawable(null);
			((TextView)findViewById(R.id.txtContact)).setBackgroundDrawable(null);
			((TextView)findViewById(R.id.txtDiscover)).setTextColor(Color.parseColor("#99e4ee"));
			((TextView)findViewById(R.id.txtMe)).setTextColor(Color.parseColor("#99e4ee"));
			//((TextView)findViewById(R.id.txtWall)).setTextColor(Color.parseColor("#99e4ee"));
			((TextView)findViewById(R.id.txtContact)).setTextColor(Color.parseColor("#99e4ee"));
			
			//((TextView)findViewById(R.id.imageDiscover)).setBackgroundDrawable(null);
			//((TextView)findViewById(R.id.imageDiscover)).setTextColor(Color.WHITE);
			if(v!=null)
			{
				v.setBackgroundResource(R.drawable.tab);
				((TextView)v).setTextColor(Color.WHITE);
			}
			if(currentMenu!=null) currentMenu.finish();
		}*/
	
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/*getMenuInflater().inflate(R.menu.main, menu);
		//
		menu.add(1, 1, 0,"بروز رسانی  جوایز :"+new Config_Table().getValue(Config_Table.LAST_UPDATE_AWARD));
		menu.add(1, 2, 1,"بروز رسانی  موجودی :"+new Config_Table().getValue(Config_Table.LAST_UPDATE_STOCK));
		menu.add(1, 3, 2,"بروز رسانی  داشبورد :"+new Config_Table().getValue(Config_Table.LAST_UPDATE_DASHBORD));
		menu.add(1, 4, 3,"بروز رسانی  کارتابل :"+new Config_Table().getValue(Config_Table.LAST_UPDATE_KARTABL));
		menu.add(1, 5, 4,"بروز رسانی  کامل :"+new Config_Table().getValue(Config_Table.LAST_UPDATE));*/
		return true;
	}
//////////Menu Right setting
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return false;

	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//menu.findItem(R.id.option_kala).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {

		// update the main content by replacing fragments
		Fragment fragment=null;
		switch (position) {
		//   خریدهای من 
		case 0:
			
			/*fragment = new PlanFragment();
			MainActivity.GET_FRAGMENT="com.info.androidhive.slidingmenu.PlanFragment";
			Bundle bundle1 = new Bundle();
			bundle1.putInt("TYPE",SelectCustomerFragment.TYPE_ADD_REQUEST);
			fragment.setArguments(bundle1);*/
		
			break;
		// لیست هتل
		case 1:
			View v1 = null;
			Intent i2 = new Intent(this,SelectHotelActivity.class);
			//i2.putExtra("CUSTOMER_ID", (int) customerID);
			startActivity(i2);
			/*fragment = new SelectCustomerFragment("4");
			MainActivity.GET_FRAGMENT="com.info.androidhive.slidingmenu.db.server.Functions.SelectHotelActivity";
			Bundle bundle = new Bundle();
			//bundle.putInt("TYPE",SelectHotelActivity.TYPE_ADD_REQUEST);
			fragment.setArguments(bundle);*/
			break;
		// شارژ اعتبار
		case 2:
			View v2 = null;
		//	ClearMenu(v2);
			fragment = new PhotosFragment();
			MainActivity.GET_FRAGMENT="com.info.androidhive.slidingmenu.PhotosFragment";
			break;
	
		// مدیریت مالی
		case 3:
			View v4 = null;
			//ClearMenu(v4);
			fragment = new PhotosFragment();
			MainActivity.GET_FRAGMENT="com.info.androidhive.slidingmenu.PhotosFragment";
			/*// Show Alert DialogFragment
			alertdFragment.show(this.getFragmentManager(), "Alert Dialog Fragment");
			mDrawerLayout.closeDrawer(mDrawerList);*/
			/*ReportsDialog dialog = new ReportsDialog(this);
			dialog.show();*/
			//fragment = new PagesFragment();
			break;
		// استرداد بلیط
		case 4:
			View v5 = null;
			//ClearMenu(v5);
			fragment = new PhotosFragment();
			MainActivity.GET_FRAGMENT="com.info.androidhive.slidingmenu.PhotosFragment";
			break;
		// قوانین و مقررات  
		case 5:
			View v6 = null;
			//ClearMenu(v6);
			fragment = new PhotosFragment();
			MainActivity.GET_FRAGMENT="com.info.androidhive.slidingmenu.PhotosFragment";
			/*Intent myIntent = new Intent(CurrentActivity.this, HomeFragment.class);
			myIntent.putExtra("key", value); //Optional parameters
			CurrentActivity.this.startActivity(myIntent);
			startActivity(new Intent(this, HomeFragment.class));*/
			break;
			// تماس با ما   
		case 6:
			View v7 = null;
			//ClearMenu(v6);
			fragment = new PhotosFragment();
			MainActivity.GET_FRAGMENT="com.info.androidhive.slidingmenu.PhotosFragment";
			/*Intent myIntent = new Intent(CurrentActivity.this, HomeFragment.class);
			myIntent.putExtra("key", value); //Optional parameters
			CurrentActivity.this.startActivity(myIntent);
			startActivity(new Intent(this, HomeFragment.class));*/
			break;
	//تخروج از حساب کاربری
		case 7:
		//	fragment = new MessageInboxFragment();
			break;
			/*	
		// راهنما
		case 8:
			fragment = new PhotosFragment();
			break;
		// امنیت
		case 9:
			fragment = new CommunityFragment();
			break;
		// پروفایل
		case 10:
			fragment = new PagesFragment();
			break;
		// غیرفعال سازی
		case 11:
			IDM_Dialog daDialog = new IDM_Dialog(this);
			daDialog.setMessage("آیا مایل به غیر فعال سازی نرم افزار هستید؟");
			daDialog.setCancelButton();
			daDialog.setAcceptButton(new OnAcceptInterface() {
				@Override
				public void accept() {
					Intent intent2 = new Intent(getBaseContext(),SplashFragment.class);
					intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_CLEAR_TASK);
					Config_Table configs = new Config_Table();
					configs.updateData(Config_Table.IS_ACTIVE, "false");
					getBaseContext().startActivity(intent2);
				}
			});
			daDialog.show();
			//fragment = new WhatsHotFragment();
			break;*/
		default:
			break;
		}

		if (fragment != null) {
			fragment2=fragment;
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
/////////////////////////
			// Works with either the framework FragmentManager or the
			// support package FragmentManager (getSupportFragmentManager).
			/*getFragmentManager().beginTransaction()
			                           .replace(R.id.frame_container, fragment, "detail")
			                           // Add this transaction to the back stack
			                           .disallowAddToBackStack()
			                           .commit();*/
			
/////////////////////////			
			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}
	@Override
	public void onBackPressed() {
	     // is there any fragment in backstack, if yes popout.
		//if(fragment.isVisible()){
		    // replace 1st fragment
		
			String backStateName = fragment2.getClass().getName();
			 if(GET_FRAGMENT.equals("com.info.androidhive.slidingmenu.db.server.Functions.AddRequestFragment")){
				 IDM_Dialog daDialog = new IDM_Dialog(this);
					daDialog.setMessage("آیا مایل به خروج از صفحه درخواست گیری هستید؟");
					daDialog.setCancelButton();
					daDialog.setAcceptButton(new IDM_Dialog.OnAcceptInterface() {
						@Override
						public void accept() {
							/* fragment2 = new SelectCustomerFragment();
							if (fragment2 != null) {
								FragmentManager fragmentManager = getFragmentManager();
								fragmentManager.beginTransaction().replace(R.id.frame_container, fragment2).commit();
								
							} */
							/*Intent intent2 = new Intent(getBaseContext(),SelectCustomerFragment.class);
							intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
									| Intent.FLAG_ACTIVITY_CLEAR_TASK);
							Config_Table configs = new Config_Table();
							configs.updateData(Config_Table.IS_ACTIVE, "false");
							getBaseContext().startActivity(intent2);*/
						}
					});
					daDialog.show();
				
			 }else if(GET_FRAGMENT.equals("com.info.androidhive.slidingmenu.db.server.Functions.ReportCustomerFactsFragment")){
				
			 }else if(GET_FRAGMENT.equals("com.info.androidhive.slidingmenu.db.server.Functions.ReportAmountAwardsFragment")){
				 
			 }else if(GET_FRAGMENT.equals("com.info.androidhive.slidingmenu.db.server.Functions.ReportCalculateCreditFragment")){
			
			 }else if(GET_FRAGMENT.equals("com.info.androidhive.slidingmenu.db.server.Functions.ManageRequestFragment")){
				
			 }else if(GET_FRAGMENT.equals("com.info.androidhive.slidingmenu.db.server.Functions.AddRequestFragment")){
				 // Alert dialog for Exit App
		    	  IDM_Dialog daDialog = new IDM_Dialog(this);
					daDialog.setMessage("آیا مایل به خروج از صفحه درخواست گیری هستید؟");
					daDialog.setCancelButton();
					daDialog.setAcceptButton(new IDM_Dialog.OnAcceptInterface() {
						@Override
						public void accept() {
							/*Intent intent2 = new Intent(getBaseContext(),SelectCustomerFragment.class);
							intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
									| Intent.FLAG_ACTIVITY_CLEAR_TASK);
							Config_Table configs = new Config_Table();
							configs.updateData(Config_Table.IS_ACTIVE, "false");
							getBaseContext().startActivity(intent2);*/
						}
					});
					daDialog.show();
			 }else if(GET_FRAGMENT.equals("com.info.androidhive.slidingmenu.ui.SelectCustomerFragment")){
				
			 }//
			 else if(GET_FRAGMENT.equals("com.info.androidhive.slidingmenu.ui.ReportCustomerFactsFragment")){
				/* fragment2 = new SelectCustomerFragment();
					if (fragment2 != null) {
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction().replace(R.id.frame_container, fragment2).commit();
						
					}*/
			 }else {
				 // Alert dialog for Exit App
		    	  IDM_Dialog daDialog = new IDM_Dialog(this);
		    	  daDialog.setMessage("آیا مایل به خروج از نرم افزار هستید؟");
					daDialog.setCancelButton();
					daDialog.setAcceptButton(new IDM_Dialog.OnAcceptInterface() {
						@Override
						public void accept() {
							finish();
				            System.exit(0);
						}
					});
					daDialog.show();
			 }
		//}
		//}else{
			
		//}
	   /* if (getFragmentManager().getBackStackEntryCount() > 0) {
	        getFragmentManager().popBackStack();
	        return;
	    }*/
	   // super.onBackPressed();
	}
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
