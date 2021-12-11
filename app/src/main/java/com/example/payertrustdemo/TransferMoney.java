package com.example.payertrustdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.payertrustdemo.model.AccountListresponse;
import com.example.payertrustdemo.model.AddAccountResponse;
import com.example.payertrustdemo.model.ContactResponse;
import com.example.payertrustdemo.retrofit.RetrofitClient;
import com.example.payertrustdemo.util.Constants;
import com.example.payertrustdemo.util.MyPreferences;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferMoney extends AppCompatActivity {

    AccountListresponse.AccountList accountList;
    Button transferMoney;
    TextInputEditText edtName,edtBank,edtAccount,edtIfscCode,edtAmount,edtRemarks;
    MyPreferences myPreferences;
    String[] spinnerItems = { "NEFT", "IMPS"};
    private Spinner spinner;
    String selectedSpinnerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);
        myPreferences = new MyPreferences(this);
        accountList = (AccountListresponse.AccountList) getIntent().getSerializableExtra("accountList");

        spinner = findViewById(R.id.spinner);
        edtName = findViewById(R.id.benifeciery_name);
        edtBank = findViewById(R.id.edtBankName);
        edtAccount = findViewById(R.id.account_number);
        edtIfscCode = findViewById(R.id.edtIfsc);
        edtAmount = findViewById(R.id.transfer_amount);
        edtRemarks = findViewById(R.id.edtRemarks);
        initView();

        //Move to Transfer Money Confirmation Screen
        transferMoney = (Button) findViewById(R.id.transfer_money_submit_btn);
        transferMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = edtAmount.getText().toString().trim();
                String remarks = edtAmount.getText().toString().trim();
                if(TextUtils.isEmpty(amount)){
                    showToast("Enter Amount");
                    return;
                }

                else if(Double.valueOf(amount)<1){
                    showToast("Enter Amount");
                    return;
                }
                else if(TextUtils.isEmpty(remarks)){
                    showToast("Enter Remarks");
                    return;
                }
                else{
                    Intent intent = new Intent(TransferMoney.this, FundTransferConfirmation.class);
                    intent.putExtra("accountList", (Serializable) accountList);
                    intent.putExtra("amount",amount);
                    intent.putExtra("remarks",remarks);
                    intent.putExtra("paymentMode",selectedSpinnerValue);
                    startActivity(intent);
                }


            }
        });

    }


    public void initView(){

        ArrayAdapter ad
                = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSpinnerValue = spinnerItems[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtName.setText(accountList.beneficiary_name);
        edtBank.setText(accountList.bankName);
        edtAccount.setText(accountList.account_number);
        edtIfscCode.setText(accountList.ifsc_code);
    }

    private void showToast(String message)
    {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}