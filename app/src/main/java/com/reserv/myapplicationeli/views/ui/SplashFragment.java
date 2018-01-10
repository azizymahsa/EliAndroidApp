package com.reserv.myapplicationeli.views.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.views.activities.main.MainActivity;


public class SplashFragment extends BaseActivity {

    private Runnable runnable;

    private ImageView ivSplash;
    private Handler handler;

    private enum DOWNLOAD_TYPE {
        NONE, MAP, SOFTWARE
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_splash);
        super.onCreate(savedInstanceState);
        ivSplash = findViewById(R.id.ivSplash);
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
                R.drawable.comp1_00031};

        handler = new Handler();
        runnable = new Runnable() {
            int i = 0;

            public void run() {
                ivSplash.setImageResource(imageArray[i]);
                i++;
                if (i > imageArray.length - 1) {
                    i = 0;

                }
                if (i == 33) {
                    finish();
                    startActivity(new Intent(SplashFragment.this, MainActivity.class));
                    finish();
                }
                handler.postDelayed(this, 30);  //for interval...
            }
        };
        handler.postDelayed(runnable, 500); //for initial delay..


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);

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
