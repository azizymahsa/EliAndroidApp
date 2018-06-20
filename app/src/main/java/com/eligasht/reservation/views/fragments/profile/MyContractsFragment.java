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
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.activities.login.ProfileActivity;
import com.eligasht.reservation.views.adapters.ContractAdapter;
import com.eligasht.reservation.models.ContractModels;

import java.util.ArrayList;

/**
 * Created by elham.bonyani on 1/25/2018.
 */

public class MyContractsFragment extends Fragment {

    public ViewGroup view;

    ListView lvContract;
    TextView tvNoContracts;
    private ArrayList<ContractModels> contractModels = new ArrayList<>();


    public static MyContractsFragment instance() {
        return new MyContractsFragment();
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
        lvContract = view.findViewById(R.id.lvContract);
        tvNoContracts = view.findViewById(R.id.tvNoContracts);
        try {
            for (int i = 0; i < WebUserTools.getInstance().getUser().getPreviousContracts().size(); i++) {
                contractModels.add(new ContractModels((WebUserTools.getInstance().getUser().getPreviousContracts().get(i).getRqBaseID() + ""),
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

            if (contractModels.isEmpty()||contractModels.size()==0){
                lvContract.setVisibility(View.GONE);
                tvNoContracts.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            lvContract.setVisibility(View.GONE);
            tvNoContracts.setVisibility(View.VISIBLE);
        }
    }



    public boolean isValidForm() {
        return true;
    }

}
