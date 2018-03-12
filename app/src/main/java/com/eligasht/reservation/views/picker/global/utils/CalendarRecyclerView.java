package com.eligasht.reservation.views.picker.global.utils;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;

public class CalendarRecyclerView extends RecyclerView {
    public CalendarRecyclerView(Context context) {
        super(context);
    }

    public CalendarRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CalendarRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }



//    @Override
//    protected void attachLayoutAnimationParameters(View child, ViewGroup.LayoutParams params,
//                                                   int index, int count) {
//        final LayoutManager layoutManager = getLayoutManager();
//        if (getAdapter() != null && layoutManager instanceof GridLayoutManager) {
//
//            GridLayoutAnimationController.AnimationParameters animationParams =
//                    (GridLayoutAnimationController.AnimationParameters) params.layoutAnimationParameters;
//
//            if (animationParams == null) {
//                animationParams = new GridLayoutAnimationController.AnimationParameters();
//                params.layoutAnimationParameters = animationParams;
//            }
//
//            final int columns = ((GridLayoutManager) layoutManager).getSpanCount();
//
//            animationParams.count = count;
//            animationParams.index = index;
//            animationParams.columnsCount = columns;
//            animationParams.rowsCount = count / columns;
//
//            final int invertedIndex = count - 1 - index;
//            animationParams.column = columns - 1 - (invertedIndex % columns);
//            animationParams.row = animationParams.rowsCount - 1 - invertedIndex / columns;
//
//        } else {
//            super.attachLayoutAnimationParameters(child, params, index, count);
//        }
//    }
}
