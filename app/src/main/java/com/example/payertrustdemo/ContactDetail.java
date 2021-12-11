package com.example.payertrustdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payertrustdemo.adapter.AccountListAdapter;
import com.example.payertrustdemo.model.AccountListresponse;
import com.example.payertrustdemo.model.AddFundAccountResponse;
import com.example.payertrustdemo.model.AddFundContactResponse;
import com.example.payertrustdemo.model.ContactResponse;
import com.example.payertrustdemo.retrofit.RetrofitClient;
import com.example.payertrustdemo.ui.contact.ContactFragment;
import com.example.payertrustdemo.util.Constants;
import com.example.payertrustdemo.util.MyPreferences;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactDetail extends AppCompatActivity {
    public FloatingActionButton floatingActionButton;
    Dialog dialog;
    Button button;
    TextView txtContactName,getTxtContactNo,getTxtContactEmail;
    ImageView imageView;
    ExtendedFloatingActionButton addAcount;
    RecyclerView recyclerView;
    AccountListAdapter adapter;
    List<AccountListresponse.AccountList> accountLists;
    AccountListresponse accountListresponse;
    MyPreferences myPreferences;
    ContactResponse.Datum contactDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        contactDetails = (ContactResponse.Datum) getIntent().getSerializableExtra("contactDetails");
        txtContactName = findViewById(R.id.contact_name_id);
        getTxtContactNo = findViewById(R.id.contact_number_id);
        getTxtContactEmail = findViewById(R.id.contact_email_id);
        imageView = findViewById(R.id.contact_image_id);
        txtContactName.setText(contactDetails.name);
        getTxtContactNo.setText(contactDetails.mobileNumber);
        getTxtContactEmail.setText(contactDetails.emailAddress);
        //Get Back To Contact List Screen
        ImageView imageView = (ImageView) findViewById(R.id.btn_from_conatct_details);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        myPreferences = new MyPreferences(this);

        addAcount = (ExtendedFloatingActionButton) findViewById(R.id.add_contact_detail_fab_btn);
        addAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactDetail.this, AddAccount.class);
                intent.putExtra("contactDetails", contactDetails);
                startActivity(intent);
            }
        });

        accountLists = new ArrayList<>();
        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AccountListAdapter(accountLists,this,this);
        recyclerView.setAdapter(adapter);

        // getting search view of our item.
        SearchView searchView = findViewById(R.id.searchView);

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        //call api
        getAccountList();

    }

    public void getAccountList() {
        showPopupProgressSpinner(true,this);
        String userId = myPreferences.getString(Constants.userId);
        String contactId = String.valueOf(contactDetails.id);
        Call<AccountListresponse> call = RetrofitClient.getInstance().getMyApi().getAccountList(contactId,userId);
        call.enqueue(new Callback<AccountListresponse>() {
            @Override
            public void onResponse(Call<AccountListresponse> call, Response<AccountListresponse> response) {
                accountListresponse = response.body();
                showPopupProgressSpinner(false,ContactDetail.this);
                if(accountListresponse!= null){
                    if(accountListresponse.status){
                        accountLists.clear();
                        accountLists.addAll(accountListresponse.data.accountList);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountListresponse> call, Throwable t) {
                showToast(t.getMessage());
            }

        });
    }

    private void filter(String text) {
        ArrayList<AccountListresponse.AccountList> filteredlist = new ArrayList<>();
        for (AccountListresponse.AccountList item : accountLists) {
            if (item.account_number.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(filteredlist);
        }
    }

    private void showToast(String message)
    {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Dialog progressDialog = null;
    private ProgressBar progressBar;
    public void showPopupProgressSpinner(Boolean isShowing, Context context) {
        if (isShowing == true) {
            progressDialog = new Dialog(context);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setContentView(R.layout.popup_progressbar);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(Color.TRANSPARENT));

            progressBar = (ProgressBar) progressDialog
                    .findViewById(R.id.progressBar);
            progressDialog.show();
        } else if (isShowing == false) {
            progressDialog.dismiss();
        }
    }

    public void addFundContact(String accountId) {
        showPopupProgressSpinner(true,this);
        String contactId = String.valueOf(contactDetails.id);
        String userId = myPreferences.getString(Constants.userId);
        Call<AddFundContactResponse> call = RetrofitClient.getInstance().getMyApi().addFundContact(userId,contactId,"1");
        call.enqueue(new Callback<AddFundContactResponse>() {
            @Override
            public void onResponse(Call<AddFundContactResponse> call, Response<AddFundContactResponse> response) {
                AddFundContactResponse addFundContactResponse = response.body();
                showPopupProgressSpinner(false,ContactDetail.this);
                if(addFundContactResponse!= null){
                    if(addFundContactResponse.status){
                        addFundAccount(""+addFundContactResponse.data.payoutBankUserContactApi.payout_bank_contact_id,
                                ""+accountId,""+addFundContactResponse.data.payoutBankUserContactApi.payout_user_contact_id);
                    }
                    else{
                        showToast(addFundContactResponse.message);
                    }
                }
            }

            @Override
            public void onFailure(Call<AddFundContactResponse> call, Throwable t) {
                showToast("An error has occured");
                showPopupProgressSpinner(false,ContactDetail.this);
            }

        });
    }

    private void addFundAccount(String bankContactId,String accountId,String userContactId) {
        showPopupProgressSpinner(true,this);
        String userId = myPreferences.getString(Constants.userId);
        Call<AddFundAccountResponse> call = RetrofitClient.getInstance().getMyApi().addFundAccount(userId,bankContactId,accountId,userContactId);
        call.enqueue(new Callback<AddFundAccountResponse>() {
            @Override
            public void onResponse(Call<AddFundAccountResponse> call, Response<AddFundAccountResponse> response) {
                AddFundAccountResponse addAccountResponse = response.body();
                showPopupProgressSpinner(false,ContactDetail.this);
                if(addAccountResponse!= null){
                    if(addAccountResponse.status){
                        showToast(addAccountResponse.message);
                        getAccountList();
                    }
                    else{
                        showToast(addAccountResponse.message);
                    }
                }
            }

            @Override
            public void onFailure(Call<AddFundAccountResponse> call, Throwable t) {
                showToast("An error has occured");
                showPopupProgressSpinner(false,ContactDetail.this);
            }

        });
    }

}