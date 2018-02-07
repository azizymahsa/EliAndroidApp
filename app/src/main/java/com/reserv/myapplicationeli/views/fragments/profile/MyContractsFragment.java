package com.reserv.myapplicationeli.views.fragments.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reserv.myapplicationeli.R;
import com.reserv.myapplicationeli.models.hotel.api.hotelAvail.call.Identity;
import com.reserv.myapplicationeli.models.model.login.call.EmailContractReq;
import com.reserv.myapplicationeli.tools.Prefs;
import com.reserv.myapplicationeli.tools.WebUserTools;

/**
 * Created by elham.bonyani on 1/25/2018.
 */

public class MyContractsFragment extends Fragment implements View.OnClickListener {

    public ViewGroup view;
    private TextView num_contract;
    private TextView date;
    private TextView path;
    private TextView depart_date;
    private TextView login_date;
    private TextView sum_price;
    private TextView remained_price;
    private TextView follower;
    private TextView email;


    public static MyContractsFragment instance() {
        MyContractsFragment fragment = new MyContractsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_my_contracts, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        initViews();
        return view;
    }


    private void initViews() {
        num_contract = view.findViewById(R.id.contract_number);
        date = view.findViewById(R.id.contract_date);
        path = view.findViewById(R.id.contract_path);
        depart_date = view.findViewById(R.id.contract_depart_date);
        login_date = view.findViewById(R.id.contract_login_date);
        sum_price = view.findViewById(R.id.contract_total_price);
        remained_price = view.findViewById(R.id.contract_remained);
        follower = view.findViewById(R.id.contract_follower);
        email = view.findViewById(R.id.contract_email);

        //set data of login for contracts
        num_contract.setText(WebUserTools.getInstance().getUser().getPreviousContracts().get(0).getCntID()+"");
        date.setText(WebUserTools.getInstance().getUser().getPreviousContracts().get(0).getDateFa());
        path.setText(WebUserTools.getInstance().getUser().getPreviousContracts().get(0).getPathNames());
        depart_date.setText(WebUserTools.getInstance().getUser().getPreviousContracts().get(0).getDeparture());
        login_date.setText(WebUserTools.getInstance().getUser().getPreviousContracts().get(0).getCheckDate());
        sum_price.setText(WebUserTools.getInstance().getUser().getPreviousContracts().get(0).getFinalPrice()+"");
        remained_price.setText(WebUserTools.getInstance().getUser().getPreviousContracts().get(0).getRemained()+"");
        follower.setText(WebUserTools.getInstance().getUser().getPreviousContracts().get(0).getFollowerName()+"");
        email.setText(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMail());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    public boolean isValidForm(){
        // check validation and if all thigs are ok return true else return false;
        return true;
    }

    //request for send contract
    public EmailContractReq getEmailContractReq(){
        EmailContractReq emailContractReq = new EmailContractReq();
        emailContractReq.setCulture("fa-IR");
        emailContractReq.setidentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        emailContractReq.setBody("");
        emailContractReq.setEncryptedContractID(WebUserTools.getInstance().getUser().getWebUserProperties().getEncryptWebUserID());
        emailContractReq.setRecieverEmail(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMail());
        return emailContractReq;
    }
}
