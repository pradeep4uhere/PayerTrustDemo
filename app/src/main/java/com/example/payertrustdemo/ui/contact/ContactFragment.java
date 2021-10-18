package com.example.payertrustdemo.ui.contact;
import android.app.Activity;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payertrustdemo.ContactViewAdapter;
import com.example.payertrustdemo.Dashbaord;
import com.example.payertrustdemo.Person;
import com.example.payertrustdemo.R;

import com.example.payertrustdemo.databinding.FragmentContactBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment  {


    private static final String AGR_PARAM1 = "param1";
    private static final String AGR_PARAM2 = "param2";

    public  FloatingActionButton floatingActionButton;


    private String mParam1;
    private String mParam2;

    private ContactViewModel contactViewModel;
    private FragmentContactBinding binding;
    List<Person> lstPerson;
    RecyclerView rcv;

    RecyclerView recyclerView;
    ContactViewAdapter cadapter;
    NavigationView navigationView;
    Button button;
    Dialog dialog;

    public  ContactFragment(){

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_contact,container,false);
//        button = (Button) view.findViewById(R.id.add_new_btn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("Onclick","Onclick");
//                showToast("Button Clicked");
//            }
//        });


        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.add_contact_fab_btn);
        floatingActionButton.bringToFront();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Log.e("Onclick","Onclick");
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("You want to add New Contact ?.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog1, int id) {
                                //showToast("Button Clicked From Facb Icon");
                                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    alert.setView(R.layout.add_new_contact_popup);
                                }
                                final AlertDialog dialog = alert.create();
                                //this line removed app bar from dialog and make it transperent and you see the image is like floating outside dialog box.
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                //finally show the dialog box in android all
                                dialog.show();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog1, int id) {
                                dialog1.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });


        recyclerView =view.findViewById(R.id.recview);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lstPerson = new ArrayList<>();
        Person ob1 = new Person(R.drawable.profile,"Pradeep Kumar","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob1);

        Person ob2 = new Person(R.drawable.profile,"Brijesh Sharma","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob2);
        Person ob3 = new Person(R.drawable.profile,"Amit Pareek","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob3);
        Person ob4 = new Person(R.drawable.profile,"Gaurav Jain","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob4);
        Person ob5 = new Person(R.drawable.profile,"PraRahul Shardeep","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob5);
        Person ob6 = new Person(R.drawable.profile,"Saadeep Kumar","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob6);
        Person ob7 = new Person(R.drawable.profile,"Pradeep","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob7);
        Person ob8 = new Person(R.drawable.profile,"Pradeep","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob8);
        Person ob11 = new Person(R.drawable.profile,"Pradeep Kumar","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob11);
        Person ob21 = new Person(R.drawable.profile,"Brijesh Sharma","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob21);
        Person ob31 = new Person(R.drawable.profile,"Amit Pareek","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob31);
        Person ob41 = new Person(R.drawable.profile,"Gaurav Jain","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob41);
        Person ob51 = new Person(R.drawable.profile,"PraRahul Shardeep","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob51);
        Person ob61 = new Person(R.drawable.profile,"Saadeep Kumar","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob61);
        Person ob71 = new Person(R.drawable.profile,"Pradeep","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob71);
        Person ob81 = new Person(R.drawable.profile,"Pradeep","9015446567","pradeep3300@gmail.com");
        lstPerson.add(ob81);

        cadapter = new ContactViewAdapter(lstPerson,getActivity().getApplicationContext());
        recyclerView.setAdapter(cadapter);
        return  view;

    }
    private void showToast(String message)
    {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}
