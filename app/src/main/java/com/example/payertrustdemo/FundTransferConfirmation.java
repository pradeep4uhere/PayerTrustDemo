package com.example.payertrustdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payertrustdemo.model.AccountListresponse;
import com.example.payertrustdemo.model.FundTransferResponse;
import com.example.payertrustdemo.retrofit.RetrofitClient;
import com.example.payertrustdemo.util.Constants;
import com.example.payertrustdemo.util.MyPreferences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FundTransferConfirmation extends AppCompatActivity {

    TextView txtBankName,txtAccount,txtIfsc,txtAccountType,txtName;
    TextView txtAmount,txtCharge,txtTotAmount;
    MyPreferences myPreferences;
    AccountListresponse.AccountList accountList;
    String amount,remarks,paymentMode;
    int charge = 15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_transfer_confirmation);
        myPreferences = new MyPreferences(this);
        accountList = (AccountListresponse.AccountList) getIntent().getSerializableExtra("accountList");
        amount = getIntent().getStringExtra("amount");
        remarks = getIntent().getStringExtra("remarks");
        paymentMode = getIntent().getStringExtra("paymentMode");

        txtBankName = findViewById(R.id.txtBankName);
        txtAccount = findViewById(R.id.txtAccountNo);
        txtIfsc = findViewById(R.id.txtIFSC);
        txtAccountType = findViewById(R.id.txtAccountType);
        txtName = findViewById(R.id.txtName);
        txtAmount = findViewById(R.id.txtAmount);
        txtCharge = findViewById(R.id.txtCharge);
        txtTotAmount = findViewById(R.id.txtTotAmount);

        //initializeView
        initView();

        Button button = (Button)findViewById(R.id.money_transfer_submit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fundTransfer();
            }
        });


        ImageView imageView = (ImageView) findViewById(R.id.back_to_fund_transfer_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initView(){
        txtName.setText(accountList.beneficiary_name);
        txtBankName.setText(accountList.bankName);
        txtIfsc.setText(accountList.ifsc_code);
        if(accountList.account_type==1){
            txtAccountType.setText("Saving");
        }
        else{
            txtAccountType.setText("Credit");
        }
        txtAmount.setText("Rs. "+ amount);
        txtCharge.setText("Rs. "+charge );
        txtTotAmount.setText("Rs. "+ (Double.valueOf(amount) + charge));
    }

        private void fundTransfer() {
        showPopupProgressSpinner(true,this);
        String userId = myPreferences.getString(Constants.userId);
        String payoutBankUserContactApiId = String.valueOf(accountList.payout_bank_account_api.id);
        String payoutBankId = "1";
        String fundApiId = String.valueOf(accountList.payout_bank_account_api.fund_api_id);
        String contactId = String.valueOf(accountList.payout_bank_account_api.contact_id);
        String accNo = String.valueOf(accountList.account_number);
        String ifsc = String.valueOf(accountList.ifsc_code);
        Call<FundTransferResponse> call = RetrofitClient.getInstance().getMyApi().transferMoney(userId,payoutBankUserContactApiId,payoutBankId,fundApiId,contactId,accNo,ifsc,String.valueOf(amount),remarks,paymentMode);
        call.enqueue(new Callback<FundTransferResponse>() {
            @Override
            public void onResponse(Call<FundTransferResponse> call, Response<FundTransferResponse> response) {
                FundTransferResponse fundTransferResponse = response.body();
                showPopupProgressSpinner(false,FundTransferConfirmation.this);
                if(fundTransferResponse!= null){
                    if(fundTransferResponse.status){
                        showToast(fundTransferResponse.message);
                        Intent intent = new Intent(FundTransferConfirmation.this, MoneyTransferSuccess.class);
                        intent.putExtra("transferResponse", fundTransferResponse);
                        intent.putExtra("bankName", accountList.bankName);
                        startActivity(intent);
                    }
                    else{
                        showToast(fundTransferResponse.message);
                    }
                }
            }

            @Override
            public void onFailure(Call<FundTransferResponse> call, Throwable t) {
                showToast("An error has occured");
                showPopupProgressSpinner(true,FundTransferConfirmation.this);
            }

        });
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

    private void showToast(String message)
    {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}