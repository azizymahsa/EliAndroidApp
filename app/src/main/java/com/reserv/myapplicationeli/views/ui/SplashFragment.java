package com.reserv.myapplicationeli.views.ui;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.os.Bundle;
import android.os.Handler;

import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ProgressBar;

import com.reserv.myapplicationeli.R;

import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.tools.db.local.Config_Table;
import com.reserv.myapplicationeli.views.activities.IDM_Activity;
import com.reserv.myapplicationeli.views.activities.MainActivity;


public class SplashFragment extends BaseActivity
{
	//sequence png
	private static final int FRAME_DELAY = 110; // in ms

	private ArrayList<Bitmap> mBitmaps;
	private final AtomicInteger mBitmapIndex = new AtomicInteger();
	private View mView;
	private Thread mThread;

	//
	private final int SPLASH_DISPLAY_LENGHT = 3000;
	private Button[] keypad;
	private ProgressBar progress;
	//private ConnectionMessagesHandler messageHandler;

	private enum DOWNLOAD_TYPE {
		NONE, MAP, SOFTWARE
	}

	private DOWNLOAD_TYPE DLType = DOWNLOAD_TYPE.NONE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.fragment_splash);
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		Utility.applyFonts(findViewById(R.id.page));

		// load resources
		mBitmaps = new ArrayList<Bitmap>();
		for(int resId : new int[]{
				// resource ids here
				R.drawable.comp1_00000,
				R.drawable.comp1_00001,
				R.drawable.comp1_00002,
				R.drawable.comp1_00003,
				R.drawable.comp1_00004,
				R.drawable.comp1_00005,
				R.drawable.comp1_00006,
				R.drawable.comp1_00007,
				R.drawable.comp1_00008,
				R.drawable.comp1_00009,
				R.drawable.comp1_00010,
				R.drawable.comp1_00011,
				R.drawable.comp1_00012,
				R.drawable.comp1_00013,
				R.drawable.comp1_00014,
				R.drawable.comp1_00015,
				R.drawable.comp1_00016,
				R.drawable.comp1_00017,
				R.drawable.comp1_00018,
				R.drawable.comp1_00019,
				R.drawable.comp1_00020,
				R.drawable.comp1_00021,
				R.drawable.comp1_00022,
				R.drawable.comp1_00023,
				R.drawable.comp1_00024,
				R.drawable.comp1_00025,
				R.drawable.comp1_00026,
				R.drawable.comp1_00027,
				R.drawable.comp1_00028,
				R.drawable.comp1_00029,
				R.drawable.comp1_00030,
				R.drawable.comp1_00031,

		}){
			mBitmaps.add(BitmapFactory.decodeResource(getResources(), resId));
		}
		// create View and implement 'draw'
		ViewGroup root = (ViewGroup) findViewById(R.id.page);
		root.addView(mView = new View(this){
			@Override
			public void draw(Canvas canvas) {
				canvas.drawBitmap(mBitmaps.get(Math.abs(mBitmapIndex.get() % mBitmaps.size())), 120, 350, null);
				super.draw(canvas);
			}
		});

		initPage();


	}//endoncreat



	private void initPage() {
		progress = (ProgressBar) findViewById(R.id.progress);

		Utility.applyFonts(findViewById(R.id.page));

		loadMainPage();

	}

	// =========================================================================
	// ===================== LOAD MAIN PAGE ====================================
	// =========================================================================
	private void loadMainPage() {
	//	final Config_Table config = new Config_Table();


		findViewById(R.id.mainPassLayout).setVisibility(View.GONE);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent mainIntent = new Intent(SplashFragment.this,MainActivity.class);
				SplashFragment.this.startActivity(mainIntent);
				SplashFragment.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);

	}//end loading

	@Override
	protected void onStart() {
		super.onStart();

		mThread = new Thread(){
			@Override
			public void run() {
				// wait and invalidate view until interrupted
				while(true){
					try {
						Thread.sleep(FRAME_DELAY);
					} catch (InterruptedException e) {
						e.printStackTrace();
						break; // get out if interrupted
					}
					mBitmapIndex.incrementAndGet();
					mView.postInvalidate();
				}
			}
		};

		mThread.start();
	}

	@Override
	protected void onStop() {
		mThread.interrupt();
		super.onStop();
	}

	// =========================================================================
	// ===================== DOWNLOAD SOFTWARE =================================
	// =========================================================================
	private void downloadSoftware() {
/*		DLType = DOWNLOAD_TYPE.SOFTWARE;
		UpdateFromWebService service = new UpdateFromWebService(this, null);
		service.DownloadNewVersion(this);
		((TextView) findViewById(R.id.progressDownloadText)).setText("در حال دریافت نسخه جدید نرم افزار");
		findViewById(R.id.mainPassLayout).setVisibility(View.GONE);
		progress.setVisibility(View.VISIBLE);
		findViewById(R.id.downloadButtonsPanel).setVisibility(View.GONE);*/
	}




}
