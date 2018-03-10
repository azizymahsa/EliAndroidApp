package com.eligasht.reservation.views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eligasht.R;
import com.eligasht.reservation.models.hotel.api.hotelAvail.call.Identity;
import com.eligasht.reservation.models.model.ModelCheckBox;
import com.eligasht.reservation.models.model.login.call.EmailContractReq;
import com.eligasht.reservation.models.model.login.call.EmailContractRequestModel;
import com.eligasht.reservation.models.model.login.response.EmailContractRes;
import com.eligasht.reservation.models.model.login.response.EmailContractResult;
import com.eligasht.reservation.tools.ValidationTools;
import com.eligasht.reservation.tools.WebUserTools;
import com.eligasht.reservation.views.activities.login.ProfileActivity;
import com.eligasht.reservation.views.adapters.hotel.FilterAdapter;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import cn.refactor.library.SmoothCheckBox;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Reza.nejati on 2/27/2018.
 */

public class ContractAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ProfileActivity context;
    private ArrayList<ContractModel> contractModels = new ArrayList<>();


    public ContractAdapter(ProfileActivity context, ArrayList<ContractModel> contractModels) {
        this.contractModels = contractModels;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contractModels.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_contract, null);
            holder = new ViewHolder();
            holder.num_contract = convertView.findViewById(R.id.contract_number);
            holder.date = convertView.findViewById(R.id.contract_date);
            holder.path = convertView.findViewById(R.id.contract_path);
            holder.depart_date = convertView.findViewById(R.id.contract_depart_date);
            holder.login_date = convertView.findViewById(R.id.contract_login_date);
            holder.sum_price = convertView.findViewById(R.id.contract_total_price);
            holder.remained_price = convertView.findViewById(R.id.contract_remained);
            holder.follower = convertView.findViewById(R.id.contract_follower);
            holder.email = convertView.findViewById(R.id.contract_email);
            holder.btnSaveInfo = convertView.findViewById(R.id.btnSaveInfo);
            holder.visa = convertView.findViewById(R.id.visa);
            holder.ticket = convertView.findViewById(R.id.ticket);
            holder.hotel = convertView.findViewById(R.id.hotel);
            holder.documents = convertView.findViewById(R.id.documents);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            holder.num_contract.setText(  context.getString(R.string.contract_code)+ contractModels.get(position).num_contract);
            holder.date.setText(contractModels.get(position).date);
            holder.path.setText(contractModels.get(position).path);
            holder.depart_date.setText(contractModels.get(position).depart_date);
            holder.login_date.setText(contractModels.get(position).login_date);
            holder.sum_price.setText(contractModels.get(position).sum_price);
            holder.remained_price.setText(contractModels.get(position).remained_price);
            holder.follower.setText(contractModels.get(position).follower);
            holder.email.setText(contractModels.get(position).email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (contractModels.get(position).getTicketIssue().equals("1")) {
            holder.ticket.setText(R.string.working_on_it);
        }

        if (contractModels.get(position).getTicketIssue().equals("1")) {
            holder.ticket.setText(R.string.approved);
        }

        if (contractModels.get(position).getHotelIssue().equals("1")) {
            holder.hotel.setText(R.string.working_on_it);
        }

        if (contractModels.get(position).getHotelConfirm().equals("1")) {
            holder.hotel.setText(R.string.approved);
        }


        if (contractModels.get(position).getVisaIssue().equals("1")) {
            holder.visa.setText(R.string.working_on_it);
        }

        if (contractModels.get(position).getVisaConfirm().equals("1")) {
            holder.visa.setText(R.string.approved);
        }


        if (contractModels.get(position).getDocuments().equals("1")) {
            holder.visa.setText(R.string.delivered);
        }

        if (contractModels.get(position).getDocuments().equals("1")) {
            holder.visa.setText("");
        }
        holder.btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.emailContractProfile(getEmailContractReq(position));

            }
        });
        return convertView;
    }


    public class ViewHolder {
        TextView num_contract;
        TextView date;
        TextView path;
        TextView depart_date;
        TextView login_date;
        TextView sum_price;
        TextView remained_price;
        TextView follower;
        TextView email;
        TextView visa;
        TextView ticket;
        TextView hotel;
        Button btnSaveInfo;
        TextView documents;
    }

    public EmailContractReq getEmailContractReq(int pos) {
        EmailContractReq emailContractReq = new EmailContractReq();
        emailContractReq.setCulture(context.getString(R.string.culture));
        emailContractReq.setidentity(new Identity("EligashtMlb", "123qwe!@#QWE", "Mobile"));
        emailContractReq.setBody("");
        emailContractReq.setEncryptedContractID(contractModels.get(pos).id);
        emailContractReq.setRecieverEmail(WebUserTools.getInstance().getUser().getWebUserProperties().getWebUserMail());
        return emailContractReq;
    }
}
