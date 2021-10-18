package com.example.payertrustdemo.ui.recharge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.payertrustdemo.databinding.FragmentRechargeBinding;
import com.example.payertrustdemo.ui.recharge.RechargeViewModel;

public class RechargeFragment extends Fragment {

    private RechargeViewModel rechargeViewModel;
    private FragmentRechargeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rechargeViewModel =
                new ViewModelProvider(this).get(RechargeViewModel.class);

        binding = FragmentRechargeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRecharge;
        rechargeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
