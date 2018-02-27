package com.eligasht.reservation.views.fragments.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.eligasht.R;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.login.call.EmailContractReq;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.activities.login.ProfileActivity;
import com.eligasht.reservation.views.adapters.ContractAdapter;
import com.eligasht.reservation.views.adapters.ContractModel;

import java.util.ArrayList;

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
    ListView lvContract;
    private ArrayList<ContractModel> contractModels = new ArrayList<>();


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
        lvContract = view.findViewById(R.id.lvContract);
        date = view.findViewById(R.id.contract_date);
        path = view.findViewById(R.id.contract_path);
        depart_date = view.findViewById(R.id.contract_depart_date);
        login_date = view.findViewById(R.id.contract_login_date);
        sum_price = view.findViewById(R.id.contract_total_price);
        remained_price = view.findViewById(R.id.contract_remained);
        follower = view.findViewById(R.id.contract_follower);
        email = view.findViewById(R.id.contract_email);

        //set data of login for contracts
        try {
            for (int i = 0; i < WebUserTools.getInstance().getUser().getPreviousContracts().size(); i++) {
                contractModels.add(new ContractModel((WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getRqBaseID() + ""),
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getDateFa(),
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getPathNames()
                        , WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getDeparture(),
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getCheckDate(),
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getFinalPrice() + "",
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getRemained() + "",
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getFollowerName() + "",
                        WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMail() + "",
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getVisaConfirm() + "",
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getVisaIssue() + "",
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getTicketConfirm() + "",
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getTicketIssue() + "",
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getHotelConfirm() + "",
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getHotelIssue() + "",
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getCntDocDeliver() + "",
                        WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getEncryptedCnt_ID() + ""
                ));

            }

            lvContract.setAdapter(new ContractAdapter((ProfileActivity) getActivity(), contractModels));

        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    public boolean isValidForm() {
        // check validation and if all thigs are ok return true else return false;
        return true;
    }

    //request for send contract

}
