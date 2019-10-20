package com.example.uberapp.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.uberapp.R;

public class HistoryFragment extends Fragment {

    private HistoryViewModel HistoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        HistoryViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
                View root = inflater.inflate(R.layout.fragment_history, container , false);
                final TextView textView = root.findViewById(R.id.text_history);
                HistoryViewModel.getText().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable  String s) {
                        textView.setText(s);
                    }
                });
                return root;
    }
}