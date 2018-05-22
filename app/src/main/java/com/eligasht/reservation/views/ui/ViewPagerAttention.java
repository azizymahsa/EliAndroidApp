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
import com.eligasht.reservation.tools.GlideApp;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.gson.Gson;

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
    String brandId;
    int like;
    int currentPage = 0;
    Gson gson;
    String list,name;

    public ViewPagerAttention(final Activity activity, final ArrayList<ImageModel> imageModels, int layout,String name ) {
        this.activity = activity;
        this.layout = layout;
        this.name = name;

        gson= new Gson();
        this.indiactor = indiactor;
        this.imageModels = imageModels;
        viewPager = activity.findViewById(layout);
        //indicator = (CirclePageIndicator) activity.findViewById(indiactor);
        viewPager.setAdapter(new IntroAdapter());
        viewPager.setPageMargin(0);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setInterval(4000);
        viewPager.startAutoScroll();
        list=  gson.toJson(imageModels);

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
            ImageView image = view.findViewById(R.id.ivImage);

            container.addView(view, 0);

            GlideApp.with(activity)
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
                    intent.putExtra("hotelName",name);
                    SwipeBackActivityHelper.activityBuilder(activity)
                            .intent(intent)
                            .needParallax(true)
                            .needBackgroundShadow(true)
                            .startActivity();
                }
            });

            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
