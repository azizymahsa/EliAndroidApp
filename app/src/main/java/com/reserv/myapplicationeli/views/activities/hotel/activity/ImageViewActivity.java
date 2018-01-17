package com.reserv.myapplicationeli.views.activities.hotel.activity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.base.BaseActivity;
import com.reserv.myapplicationeli.views.ui.InitUi;

public class ImageViewActivity extends BaseActivity {
    PhotoView photoView;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        photoView = findViewById(R.id.photo_view);
        imageLoader = ImageLoader.getInstance();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        InitUi.Toolbar(this, false, R.color.flight_status, "نمایش عکس");

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // this will make circle, pass the width of image
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .build();

        imageLoader.displayImage(getIntent().getExtras().getString("pic"), photoView, options, null);


    }
}
