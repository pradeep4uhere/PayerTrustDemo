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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payertrustdemo.R;
import com.example.payertrustdemo.Wallet;
import com.example.payertrustdemo.WalletViewAdapter;
import com.example.payertrustdemo.databinding.FragmentMobilePinBinding;
import com.example.payertrustdemo.databinding.FragmentWalletReportBinding;
import com.example.payertrustdemo.ui.mpin.MobilePinViewModel;

import java.util.ArrayList;
import java.util.List;

public class WalletReportFragment extends Fragment {

    private static final String AGR_PARAM1 = "param1";
    private static final String AGR_PARAM2 = "param2";

    private WalletReportViewModel walletReportViewModel;
    private FragmentWalletReportBinding binding;


    public  WalletReportFragment(){

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        walletReportViewModel =
                new ViewModelProvider(this).get(WalletReportViewModel.class);

        binding = FragmentWalletReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textMobilePassword;
//        mobilePinViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
