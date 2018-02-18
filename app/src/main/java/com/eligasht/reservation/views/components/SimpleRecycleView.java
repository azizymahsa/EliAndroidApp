package com.eligasht.reservation.views.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eligasht.R;


public class SimpleRecycleView<T extends RecyclerView.ViewHolder> extends LinearLayout {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView textView;
    private TextView txtTitle;
    private Context context;


    public SimpleRecycleView(Context context) {
        super(context);
        build(context);
    }

    public SimpleRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        build(context);
    }

    public SimpleRecycleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        build(context);
    }

    private void build(Context context){
        LayoutInflater.from(context).inflate(R.layout.recyle_view_simple, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(layoutParams);
        initViews();
        this.context = context;
    }



    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        recyclerView.setLayoutManager(layoutManager);
        invalidate();
    }


    public void showLoading(){
        recyclerView.setVisibility(GONE);
        textView.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        invalidate();
    }

    public void hideLoading(){
        recyclerView.setVisibility(VISIBLE);
        textView.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        invalidate();
    }

    public void isSetTitle(boolean isSet){
        if(isSet){
            txtTitle.setVisibility(VISIBLE);
        }else{
            txtTitle.setVisibility(GONE);
        }
        invalidate();
    }

    public void showText(){
        recyclerView.setVisibility(GONE);
        textView.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        invalidate();
    }

    public void setTitle(String input){
        txtTitle.setText(input);
        invalidate();
    }

    public void setTextView(String input){
        textView.setText(input);
        invalidate();
    }


    public void showList(RecyclerView.Adapter<T> adapter){
        if(adapter == null){
            recyclerView.setVisibility(GONE);
            textView.setVisibility(VISIBLE);
            progressBar.setVisibility(GONE);
            return;
        }

        recyclerView.setVisibility(VISIBLE);
        textView.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public RecyclerView.Adapter<T> getAdapter(){
        return recyclerView.getAdapter();
    }

    private void initViews() {
        progressBar =(ProgressBar) findViewById(R.id.prg);
        txtTitle =(TextView) findViewById(R.id.txt_title);
        textView = (TextView) findViewById(R.id.txt);
        recyclerView = (RecyclerView) findViewById(R.id.rcl);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void setNestedScrollingEnabled(boolean value){
        recyclerView.setNestedScrollingEnabled(value);
        invalidate();
    }

    public void smoothScrollToPosition(int position){
        recyclerView.smoothScrollToPosition(position);
        invalidate();
    }

    public RecyclerView getRecyclerView(){
        return recyclerView;
    }
}
