package com.eligasht.reservation.views.picker.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Ahmad.nemati on 3/7/2018.
 */

public class Grid extends GridLayoutManager {
    public Grid(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public Grid(Context context, int spanCount) {
        super(context, spanCount);
    }

    public Grid(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    protected boolean isLayoutRTL() {
        return true;
    }

}
