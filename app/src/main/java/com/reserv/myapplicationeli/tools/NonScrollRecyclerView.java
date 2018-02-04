package com.reserv.myapplicationeli.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Reza.nejati on 2/4/2018.
 */

public class NonScrollRecyclerView extends android.support.v7.widget.RecyclerView {
    public NonScrollRecyclerView(Context context) {
        super(context);
    }
    public NonScrollRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public NonScrollRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}
