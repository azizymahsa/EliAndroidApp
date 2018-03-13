package com.eligasht.reservation.views.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.eligasht.R;
import com.eligasht.reservation.views.activities.hotel.activity.ImageViewActivity;
import com.eligasht.reservation.views.adapters.hotel.rooms.ImageModel;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class ViewPagerAttention {
    private Activity activity;
    private AutoScrollViewPager viewPager;
    private int[] images;
    private int[] Title;
    private int[] messagesTitle;
    int layout;
    int indiactor;
    private ArrayList<ImageModel> imageModels = new ArrayList<>();
    ImageLoader imageLoader;
    String brandId;
    int like;
    int currentPage = 0;
    Gson gson;
    String list;



    public ViewPagerAttention(final Activity activity, final ArrayList<ImageModel> imageModels, int layout ) {
        this.activity = activity;
        this.layout = layout;

        gson= new Gson();
        this.indiactor = indiactor;
        this.imageModels = imageModels;
        viewPager = (AutoScrollViewPager) activity.findViewById(layout);
        //indicator = (CirclePageIndicator) activity.findViewById(indiactor);
        viewPager.setAdapter(new IntroAdapter());
        viewPager.setPageMargin(0);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setInterval(4000);
        viewPager.startAutoScroll();
        list=  gson.toJson(imageModels);

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



            Glide.with(activity)
                    .load(imageModels.get(position).getImage())
                    .centerCrop()
                    .error(R.drawable.not_found)
                    .into(image);





            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(activity, ImageViewActivity.class);
                    intent.putExtra("pic",position);
                    intent.putExtra("list",list);
                    activity.startActivity(intent);
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
