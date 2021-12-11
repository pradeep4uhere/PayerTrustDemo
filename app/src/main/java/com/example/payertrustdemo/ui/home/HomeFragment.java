package com.example.payertrustdemo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.payertrustdemo.Login;
import com.example.payertrustdemo.PrepaidRecharge;
import com.example.payertrustdemo.R;
import com.example.payertrustdemo.databinding.FragmentHomeBinding;
import com.example.payertrustdemo.ui.payment.PaymentReportFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    Button prepaidRechargeBtn;
    CardView cardView, card1, card2, card3, card4, card5, card6, card7, card8, card9;

//    card1 = findViewById(R.id.card1);
//    card2 = findViewById(R.id.card2);
//    card3 = findViewById(R.id.card3);
//    card4 = findViewById(R.id.card4);
//    card5 = findViewById(R.id.card5);
//    card6 = findViewById(R.id.card6);
//    card7 = findViewById(R.id.card7);
//    card8 = findViewById(R.id.card8);
//    card9 = findViewById(R.id.card9);
//
//    card1.setOnClickListener(this);
//    card2.setOnClickListener(this);
//    card3.setOnClickListener(this);
//    card4.setOnClickListener(this);
//    card5.setOnClickListener(this);
//    card6.setOnClickListener(this);
//    card7.setOnClickListener(this);
//    card8.setOnClickListener(this);
//    card9.setOnClickListener(this);
    public  HomeFragment(){

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        card1 = (CardView) view.findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i;
            i = new Intent(getContext(),PrepaidRecharge.class);
            startActivity(i);
            }
        });

//        card2 = (CardView) view.findViewById(R.id.card8);
//        card2.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//                Fragment fragment = new PaymentReportFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.home_fragment, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });


        return view;
//        homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.balance;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}

// card1 = (CardView) view.findViewById(R.id.card1);
//         card2 = (CardView) view.findViewById(R.id.card8);
//         card1.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        Intent i;
//        switch (v.getId()){
//        case R.id.card8:
//        i = new Intent(getContext(),PrepaidRecharge.class);
//        startActivity(i);
//        break;
//
//        case R.id.card1:
//        i = new Intent(getContext(), PaymentReportFragment.class);
//        startActivity(i);
//        break;
//        }
//        }
//        });