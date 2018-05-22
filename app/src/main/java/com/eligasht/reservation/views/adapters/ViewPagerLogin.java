package com.eligasht.reservation.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eligasht.R;
import com.eligasht.reservation.tools.GlideApp;
import com.eligasht.reservation.views.activities.hotel.activity.ImageViewActivity;
import com.eligasht.reservation.views.adapters.hotel.rooms.ImageModel;
import com.eligasht.reservation.views.ui.ViewPagerAttention;
import com.github.bluzwong.swipeback.SwipeBackActivityHelper;
import com.google.gson.Gson;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class ViewPagerLogin { private Activity activity;
    private AutoScrollViewPager viewPager;
    private int[] images;
    private int[] Title;
    private int[] messagesTitle;
    int layout;
    int indiactor;
    private ArrayList<Integer> imageModels = new ArrayList<>();
    String brandId;
    int like;
    int currentPage = 0;

    public ViewPagerLogin(final Activity activity, final ArrayList<Integer> imageModels, int layout ) {
        this.activity = activity;
        this.layout = layout;

        this.imageModels = imageModels;
        viewPager = activity.findViewById(layout);
        viewPager.setAdapter(new IntroAdapter());
        viewPager.setPageMargin(0);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setInterval(3000);
        viewPager.startAutoScroll();

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
            image.setImageResource(imageModels.get(position));

            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
