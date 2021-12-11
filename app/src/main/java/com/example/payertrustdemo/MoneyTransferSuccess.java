package com.example.payertrustdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payertrustdemo.model.AccountListresponse;
import com.example.payertrustdemo.model.FundTransferResponse;

public class MoneyTransferSuccess extends AppCompatActivity {

    TextView txtBankName,txtAccount,txtIfsc,txtAccountType,txtName,txtPaymentMode,txtPaymentStatus,
    txtAmount;
    FundTransferResponse fundTransferResponse;
    String bankName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer_success);
        fundTransferResponse = (FundTransferResponse) getIntent().getSerializableExtra("transferResponse");
        bankName = getIntent().getStringExtra("bankName");
        txtBankName = findViewById(R.id.txtBankName);
        txtAccount = findViewById(R.id.txtAccountNo);
        txtIfsc = findViewById(R.id.txtIFSC);
        txtAccountType = findViewById(R.id.txtBankName);
        txtName = findViewById(R.id.txtName);
        txtPaymentMode = findViewById(R.id.txtPaymentMode);
        txtPaymentStatus = findViewById(R.id.txtPaymentStatus);
        txtAmount = findViewById(R.id.txtAmount);
        initView();

    }

    public void initView(){
        txtBankName.setText(bankName);
        txtName.setText(fundTransferResponse.dataSet.bank.name);
        txtBankName.setText(bankName);
        txtPaymentMode.setText(fundTransferResponse.dataSet.mode);
    }

    private void showToast(String message)
    {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}