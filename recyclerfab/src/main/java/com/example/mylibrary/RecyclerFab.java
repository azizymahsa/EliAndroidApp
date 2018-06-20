package com.example.mylibrary;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
/**
 * Created by Reza Nejati on 20,May,2018
 */
public class RecyclerFab extends RecyclerView implements View.OnClickListener {
    private int mFabHeight;
    private int mFabWidth;
    private int mFabMargin;
    private int mFabColor;
    private int mFabAlignParent;
    private LinearLayout mLayout;
    private boolean isAnimated = true;
    private Drawable mFabDrawable;

    public RecyclerFab(Context context) {
        super(context);
    }

    public RecyclerFab(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RecyclerFab(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    @Override
    public void onClick(View v) {
        smoothScrollToPosition(0);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        LinearLayoutManager mLayoutManager = (LinearLayoutManager) getLayoutManager();
        if (mLayoutManager.findFirstCompletelyVisibleItemPosition() > 0 && isAnimated) {
            mLayout.setVisibility(VISIBLE);
            mLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_in));
            isAnimated = false;
        } else if (mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0 && !isAnimated) {
            mLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_out));
            mLayout.setVisibility(GONE);
            isAnimated = true;
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public View getRootView() {
        initView();
        return super.getRootView();
    }

    private void initView() {
        RelativeLayout view = (RelativeLayout) getParent();
        mLayout = new LinearLayout(getContext());
        ImageView mImageView = new ImageView(getContext());
        mImageView.setImageDrawable(mFabDrawable);
        mImageView.setColorFilter(ContextCompat.getColor(getContext(), android.R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);

        mLayout.setOnClickListener(this);
        mLayout.setVisibility(GONE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mFabWidth, mFabHeight);
        if (mFabAlignParent == 1){
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mLayout.setTranslationY(-mFabMargin);
            mLayout.setTranslationX(-mFabMargin);
        }
        else {
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            mLayout.setTranslationY(mFabMargin);
            mLayout.setTranslationX(mFabMargin);
        }
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mLayout.setLayoutParams(params);

        view.addView(mLayout);
        mLayout.addView(mImageView);
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(Integer.MAX_VALUE);
        shape.setColor(mFabColor);
        mLayout.setBackground(shape);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RecyclerFab);
        mFabHeight = typedArray.getInt(R.styleable.RecyclerFab_fabHeight, 200);
        mFabWidth = typedArray.getInt(R.styleable.RecyclerFab_fabWidth, 200);
        mFabMargin = typedArray.getInt(R.styleable.RecyclerFab_fabMargin, 0);
        mFabDrawable = typedArray.getDrawable(R.styleable.RecyclerFab_fabDrawable);
        mFabColor = typedArray.getColor(R.styleable.RecyclerFab_fabColor, Color.BLUE);
        if (typedArray.hasValue(R.styleable.RecyclerFab_fabAlignParent)) {
            mFabAlignParent = typedArray.getInt(R.styleable.RecyclerFab_fabAlignParent, 0);
        }
        typedArray.recycle();
    }
}
