package com.eligasht.reservation.views.fragments.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.login.call.ChangePasswordReq;
import com.eligasht.reservation.tools.WebUserTools;

/**
 * Created by elham.bonyani on 1/25/2018.
 */

public class ChangePasswordFragment extends Fragment implements View.OnClickListener {

    public ViewGroup view;
    private EditText changePass_old;
    private EditText changePass_new;
    private EditText changePass_new_confirm;

    public static ChangePasswordFragment instance() {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_change_password, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        initViews();
        return view;
    }


    private void initViews() {

        changePass_old = view.findViewById(R.id.changePass_username_current);
        changePass_new = view.findViewById(R.id.changePass_username_new);
        changePass_new_confirm = view.findViewById(R.id.changePass_username_new_confirm);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    public boolean isValidForm() {
        // check validation and if all thigs are ok return true else return false;
        if (changePass_old.getText().length() == 0) {
            Toast.makeText(getActivity(), R.string.enter_your_password, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (changePass_new_confirm.getText().length() == 0) {
            Toast.makeText(getActivity(), R.string.re_enter_your_password, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (changePass_new.getText().length() == 0) {
            Toast.makeText(getActivity(), R.string.enter_your_new_password, Toast.LENGTH_SHORT).show();
            return false;
        }
//        if(changePass_new.getText().equals(changePass_new_confirm.getText())){
//            return true;
//        }else{
//            Toast.makeText(getActivity(),"تکرار رمز عبور اشتباه میباشد", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;


    }

    //request for change password
    public ChangePasswordReq getChangePasswordReq() {

        ChangePasswordReq changePasswordReq = new ChangePasswordReq();
        changePasswordReq.setCulture(WebUserTools.getInstance().getUser().getWebUserProperties().getCulture());
        changePasswordReq.setidentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        changePasswordReq.setOldPassword(changePass_old.getText().toString());
        changePasswordReq.setNewPassword(changePass_new.getText().toString());
        changePasswordReq.setWebUser(WebUserTools.getInstance().getUser().getWebUserProperties());

        return changePasswordReq;
    }
}
