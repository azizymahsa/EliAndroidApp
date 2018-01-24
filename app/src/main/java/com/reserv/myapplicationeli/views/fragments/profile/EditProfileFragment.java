package com.reserv.myapplicationeli.views.fragments.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.reserv.myapplicationeli.R;


public class EditProfileFragment extends Fragment implements View.OnClickListener {


    private TextView txt_arrow1;
    private TextView txt_arrow2;
    private ViewGroup btn_order;
    private ViewGroup btn_user_info;
    private ExpandableRelativeLayout expand_other;
    private ExpandableRelativeLayout expand_user_info;

    public static EditProfileFragment instance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        initViews(view);
        return view;
    }


    private void initViews(View view) {
        txt_arrow1 = view.findViewById(R.id.txt_arrow1);
        txt_arrow2 = view.findViewById(R.id.txt_arrow2);
        btn_order = view.findViewById(R.id.btn_order);
        btn_user_info = view.findViewById(R.id.btn_user_info);
        expand_other = view.findViewById(R.id.expand_other);
        expand_user_info = view.findViewById(R.id.expand_user_info);


        txt_arrow1.setText(getString(R.string.icon_arrow_up));
        txt_arrow1.setText(getString(R.string.icon_arrow_up));

        btn_user_info.setOnClickListener(this);
        btn_order.setOnClickListener(this);
        expand_other.collapse();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_user_info:
                if (expand_user_info.isExpanded()) {
                    expand_user_info.collapse();
                    txt_arrow1.setText(getString(R.string.icon_arrow_up));
                } else {
                    expand_user_info.expand();
                    txt_arrow1.setText(getString(R.string.icon_arrow_down));
                }
                break;

            case R.id.btn_order:

                if (expand_other.isExpanded()) {

                    expand_other.collapse();
                    txt_arrow2.setText(getString(R.string.icon_arrow_up));
                } else {
                    expand_other.expand();
                    txt_arrow2.setText(getString(R.string.icon_arrow_down));
                }

                break;
        }
    }
}
