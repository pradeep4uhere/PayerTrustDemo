package com.example.payertrustdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddAccount extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    TextView textView;
    ImageView imageView;
    Button button;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        initUI();
        ImageView imageView = (ImageView) findViewById(R.id.back_to_contact_details_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Contact Details List");
                Intent intent = new Intent(AddAccount.this, ContactDetail.class);
                startActivity(intent);
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


    private void initUI()
    {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV = findViewById(R.id.customerTextView);

        // create list of customer
        ArrayList<String> customerList = getCustomerList();

        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddAccount.this, android.R.layout.simple_spinner_item, customerList);

        //Set adapter
        customerAutoTV.setAdapter(adapter);
    }

    private ArrayList<String> getCustomerList()
    {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("HDFC Bank");
        customers.add("ICIC BANK");
        customers.add("Kotak bank");
        customers.add("Michael");
        customers.add("William");
        customers.add("Daniel");
        customers.add("Thomas");
        customers.add("Sarah");
        customers.add("HDFC Bank");
        customers.add("ICIC BANK");
        customers.add("Kotak bank");
        customers.add("Michael");
        customers.add("William");
        customers.add("Daniel");
        customers.add("Thomas");
        customers.add("Sarah");
        customers.add("HDFC Bank");
        customers.add("ICIC BANK");
        customers.add("Kotak bank");
        customers.add("Michael");
        customers.add("William");
        customers.add("Daniel");
        customers.add("Thomas");
        customers.add("Sarah");
        customers.add("HDFC Bank");
        customers.add("ICIC BANK");
        customers.add("Kotak bank");
        customers.add("Michael");
        customers.add("William");
        customers.add("Daniel");
        customers.add("Thomas");
        customers.add("Sarah");
        customers.add("HDFC Bank");
        customers.add("ICIC BANK");
        customers.add("Kotak bank");
        customers.add("Michael");
        customers.add("William");
        customers.add("Daniel");
        customers.add("Thomas");
        customers.add("Sarah");
        customers.add("Sophia");
        return customers;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;
            case 4:
                // Whatever you want to happen when the first item gets selected
                break;
            case 5:
                // Whatever you want to happen when the second item gets selected
                break;
            case 6:
                // Whatever you want to happen when the thrid item gets selected
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    private void showToast(String message)
    {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}