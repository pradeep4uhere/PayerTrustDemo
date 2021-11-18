package com.example.payertrustdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payertrustdemo.ContactDetail;
import com.example.payertrustdemo.FundTransferConfirmation;
import com.example.payertrustdemo.R;
import com.example.payertrustdemo.TransferMoney;
import com.example.payertrustdemo.model.AccountListresponse;
import com.example.payertrustdemo.model.ContactResponse;

import java.util.List;

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.MyViewHolder> {

    private List<AccountListresponse.AccountList> accountList;
    Context context;
    public AccountListAdapter(List<AccountListresponse.AccountList> accountList, Context context) {
        List<AccountListresponse.AccountList> accountList1;
        this.accountList = accountList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.account_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtBankName.setText(accountList.get(position).bankName);
        holder.txtAccountNo.setText( accountList.get(position).account_number);
        holder.txtIfsc.setText(accountList.get(position).ifsc_code);
        if(accountList.get(position).is_validated==1) {
            holder.txtValidated.setText("Validated");
        }
        else{
            holder.txtValidated.setText("Not Validated");
        }
        holder.txtName.setText(accountList.get(position).beneficiary_name);
        //holder.imageView.setImageResource(accountList.get(position).bankIcon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TransferMoney.class);
//                intent.putExtra("Contact Details",temp.getTitle());
//                intent.putExtra("Contact Email",temp.getEmail());
//                intent.putExtra("Contact Email",temp.getMobile());
//                intent.putExtra("Contact Email",temp.getThumbnail());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    // method for filtering our recyclerview items.
    public void filterList(List<AccountListresponse.AccountList> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        accountList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtBankName;
        ImageView imageView;
        TextView txtName;
        TextView txtAccountNo;
        TextView txtIfsc;
        TextView txtValidated;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtBankName = itemView.findViewById(R.id.txtBankName);
            txtAccountNo= itemView.findViewById(R.id.txtAccountNo);
            txtIfsc = itemView.findViewById(R.id.txtIfsc);
            txtValidated = itemView.findViewById(R.id.txtValidated);
            txtName = itemView.findViewById(R.id.txtName);

        }
    }
}
