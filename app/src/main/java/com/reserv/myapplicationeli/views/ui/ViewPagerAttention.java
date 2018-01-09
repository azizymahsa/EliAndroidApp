package com.reserv.myapplicationeli.views.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.pixplicity.easyprefs.library.Prefs;
import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.views.adapters.hotel.rooms.ImageModel;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

public class ViewPagerAttention {
    private Activity activity;
    private ViewPager viewPager;
    private int[] images;
    private int[] Title;
    private int[] messagesTitle;
    int layout;
    int indiactor;
    private ArrayList<ImageModel> imageModels = new ArrayList<>();
    ImageLoader imageLoader;
    String brandId;
    int like;




    public ViewPagerAttention(final Activity activity,ArrayList<ImageModel> imageModels,int layout ) {
        this.activity = activity;
        this.layout = layout;


        this.indiactor = indiactor;
        this.imageModels = imageModels;
        viewPager = (ViewPager) activity.findViewById(layout);
        //indicator = (CirclePageIndicator) activity.findViewById(indiactor);
        viewPager.setAdapter(new IntroAdapter());
        viewPager.setPageMargin(0);
        viewPager.setOffscreenPageLimit(1);
       // indicator.setViewPager(viewPager);




       /* indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {




            }
            @Override
            public void onPageSelected(int position) {

                }

            @Override
            public void onPageScrollStateChanged(int state) {



            }
        });

*/


    }
    private class IntroAdapter extends PagerAdapter {
        public IntroAdapter() {
            imageLoader = ImageLoader.getInstance();
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(activity));

        }

        @Override
        public int getCount() {
            return imageModels.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = View.inflate(container.getContext(), R.layout.list_image_item, null);
            ImageView image = (ImageView) view.findViewById(R.id.ivImage);

            container.addView(view, 0);
//            image.setImageResource(images[position]);

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    // this will make circle, pass the width of image
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    .build();



            imageLoader.displayImage(imageModels.get(position).getImage(), image, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                   // notifyDataSetChanged();

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                   // notifyDataSetChanged();

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                   // notifyDataSetChanged();

                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                   // notifyDataSetChanged();

                }
            });










            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }

}
