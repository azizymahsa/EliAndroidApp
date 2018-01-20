package com.reserv.myapplicationeli.views.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.tools.Utility;
import com.reserv.myapplicationeli.views.activities.main.MainActivity;
import com.reserv.myapplicationeli.views.ui.dialog.app.InternetAlert;
import com.wang.avi.AVLoadingIndicatorView;


public class SplashFragment extends BaseActivity {

    private Runnable runnable,runnable2;
    private Handler handler,handler2;
    private ImageView ivSplash,ivLoading;
    AVLoadingIndicatorView avi;


    private enum DOWNLOAD_TYPE {
        NONE, MAP, SOFTWARE
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_splash);
        super.onCreate(savedInstanceState);
        ivSplash = findViewById(R.id.ivSplash);
        ivLoading = findViewById(R.id.ivLoading);
        avi = findViewById(R.id.avi);
        final int[] imageArray = new int[]{R.drawable.comp1_00000,
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
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,
                R.drawable.comp1_00031,};


        final int[] i = {0};

        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {

            public void run() {
                ivSplash.setImageResource(imageArray[i[0]]);
                i[0]++;
                if (i[0] > imageArray.length - 1) {
                    i[0] = 0;

                }
                if (i[0] == 31) {
                    avi.setVisibility(View.VISIBLE);
                }
                if (i[0] == 49) {
                   // handler.removeCallbacksAndMessages(null);
                   if(Utility.isNetworkAvailable(SplashFragment.this)){

                       startActivity(new Intent(SplashFragment.this, MainActivity.class));
                       finish();
                    }else{
                       handler.removeCallbacks(runnable);

                       new InternetAlert(SplashFragment.this);
                   }




                }else{
            handler.postDelayed(this, 30); } //for interval...
            }
        };
        if (i[0] < 49) {
            handler.postDelayed(runnable, 30); //for initial delay..

        }
/*

        final int[] imageArray2 = new int[]{
                R.drawable.small_01,
                R.drawable.small_02,
                R.drawable.small_03,
                R.drawable.small_04,
                R.drawable.small_05,
                R.drawable.small_06,
                R.drawable.small_07,
                R.drawable.small_08,
                R.drawable.small_09,
                R.drawable.small_10,
                R.drawable.small_11,
                R.drawable.small_12,
                R.drawable.small_13,
                R.drawable.small_14,
                R.drawable.small_15,
                R.drawable.small_16,
                R.drawable.small_17,
                R.drawable.small_18,
                R.drawable.small_19,
                R.drawable.small_20,
                R.drawable.small_21,
                R.drawable.small_22,
                R.drawable.small_23,
                R.drawable.small_24};



        handler2 = new Handler(Looper.getMainLooper());
        runnable2 = new Runnable() {
            int i = 0;

            public void run() {
                ivLoading.setImageResource(imageArray2[i]);
                i++;
                if (i > imageArray2.length - 1) {
                    i = 0;

                }

                handler2.postDelayed(this, 50);  //for interval...
            }
        };
        handler2.postDelayed(runnable2, 100); //for initial delay..


*/


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
//        handler2.removeCallbacks(runnable2);

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
