package com.example.payertrustdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payertrustdemo.model.BankListResponse;
import com.example.payertrustdemo.model.LoginResponse;
import com.example.payertrustdemo.retrofit.RetrofitClient;
import com.example.payertrustdemo.util.Constants;
import com.example.payertrustdemo.util.MyPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAccount extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    TextView textView;
    ImageView imageView;
    Button button;
    Dialog dialog;
    BankListResponse bankListResponse;
    ArrayAdapter<BankListResponse.Datum> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        getAllBank();
        ImageView imageView = (ImageView) findViewById(R.id.back_to_contact_details_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Check If Registered Name is validated
        Button more = (Button) findViewById(R.id.get_benifeciery_name);
        more.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(AddAccount.this).create(); //Read Update
                alertDialog.setTitle("Name Validation");
                alertDialog.setMessage("PRADEEP KUMAR");
                //alertDialog.setView(R.layout.name_validation_dialog_layout);
                alertDialog.show();  //<-- See This!
            }
        });

    }


    private void initBankNameAutoList()
    {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV = findViewById(R.id.customerTextView);
        //Create adapter
        adapter = new ArrayAdapter<BankListResponse.Datum>(AddAccount.this, android.R.layout.simple_spinner_item, bankListResponse.data);

        //Set adapter
        customerAutoTV.setAdapter(adapter);
        customerAutoTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                showToast(String.valueOf(adapter.getItem(pos).id));

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    private void getAllBank() {

        Call<BankListResponse> call = RetrofitClient.getInstance().getMyApi().getAllBank();
        call.enqueue(new Callback<BankListResponse>() {
            @Override
            public void onResponse(Call<BankListResponse> call, Response<BankListResponse> response) {
                bankListResponse = response.body();
                if(bankListResponse!= null){
                    if(bankListResponse.success){
                        initBankNameAutoList();
                    }
                    else{
                        showToast(bankListResponse.message);
                    }
                }
            }

            @Override
            public void onFailure(Call<BankListResponse> call, Throwable t) {
                showToast("An error has occured");
            }

        });
    }

    private void showToast(String message)
    {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}