package com.example.payertrustdemo.ui.wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payertrustdemo.R;
import com.example.payertrustdemo.Wallet;
import com.example.payertrustdemo.WalletViewAdapter;
import com.example.payertrustdemo.databinding.FragmentWalletReportBinding;

import java.util.ArrayList;
import java.util.List;

public class WalletReportFragment extends Fragment {

    private static final String AGR_PARAM1 = "param1";
    private static final String AGR_PARAM2 = "param2";

    private WalletReportViewModel walletReportViewModel;
    private FragmentWalletReportBinding binding;

    private String mParam1;
    private String mParam2;

    List<Wallet> lstPerson;
    RecyclerView rcv;

    RecyclerView recyclerView;
    WalletViewAdapter cadapter;

    public  WalletReportFragment(){

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet_report,container,false);
        recyclerView =view.findViewById(R.id.walletrecview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lstPerson = new ArrayList<>();
        Wallet ob1 = new Wallet("23rd Oct 2021 10:30 am","Wallet Recharge","201201VDXV23","Success", 2500,2000, R.drawable.ic_baseline_arrow_upward_24);
        lstPerson.add(ob1);
        Wallet ob2 = new Wallet("23rd Oct 2021 10:30 am","Wallet Recharge","201201VDXV23","Success", 2500,2000, R.drawable.ic_baseline_arrow_upward_24);
        lstPerson.add(ob2);
        Wallet ob3 = new Wallet("23rd Oct 2021 10:30 am","Wallet Recharge","201201VDXV23","Success", 2500,2000, R.drawable.ic_baseline_arrow_upward_24);
        lstPerson.add(ob3);
        Wallet ob4 = new Wallet("23rd Oct 2021 10:30 am","Wallet Recharge","201201VDXV23","Success", 2500,2000, R.drawable.ic_baseline_arrow_upward_24);
        lstPerson.add(ob4);
        Wallet ob5 = new Wallet("23rd Oct 2021 10:30 am","Wallet Recharge","201201VDXV23","Success", 2500,2000, R.drawable.ic_baseline_arrow_upward_24);
        lstPerson.add(ob5);
        Wallet ob6 = new Wallet("23rd Oct 2021 10:30 am","Wallet Recharge","201201VDXV23","Success", 2500,2000, R.drawable.ic_baseline_arrow_upward_24);
        lstPerson.add(ob6);
        Wallet ob7 = new Wallet("23rd Oct 2021 10:30 am","Wallet Recharge","201201VDXV23","Success", 2500,2000, R.drawable.ic_baseline_arrow_upward_24);
        lstPerson.add(ob7);
        Wallet ob8 = new Wallet("23rd Oct 2021 10:30 am","Wallet Recharge","201201VDXV23","Success", 2500,2000, R.drawable.ic_baseline_arrow_upward_24);
        lstPerson.add(ob8);
        cadapter = new WalletViewAdapter(lstPerson,getActivity().getApplicationContext());
        recyclerView.setAdapter(cadapter);
        return  view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
