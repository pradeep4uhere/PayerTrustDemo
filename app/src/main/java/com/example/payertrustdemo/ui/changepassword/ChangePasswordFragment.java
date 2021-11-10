package com.example.payertrustdemo.ui.changepassword;

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

import com.example.payertrustdemo.databinding.FragmentChangePasswordBinding;

public class ChangePasswordFragment extends Fragment {
    private ChangePasswordViewModel changepasswordViewModel;
    private FragmentChangePasswordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        changepasswordViewModel =
                new ViewModelProvider(this).get(ChangePasswordViewModel.class);

        binding = FragmentChangePasswordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textChangePassword;
//        changepasswordViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
