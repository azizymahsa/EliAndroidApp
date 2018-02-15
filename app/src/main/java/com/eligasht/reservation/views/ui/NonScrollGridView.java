package com.eligasht.reservation.views.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by Reza.nejati on 1/8/2018.
 */

public class NonScrollGridView extends GridView {
    public NonScrollGridView(Context context) {
        super(context);

    }

    public NonScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int bottom = child.getBottom();
            int left = child.getLeft();
            int right = child.getRight();

            Paint paint = new Paint();
            paint.setColor(0xffececec);

            paint.setStrokeWidth(Math.round(4));

            int offset =1; // Some offset

                    canvas.drawLine(left + offset, bottom, right - offset, bottom, paint);
        }


        super.dispatchDraw(canvas);
    }
}