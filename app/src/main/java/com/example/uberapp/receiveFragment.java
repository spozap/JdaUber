package com.example.uberapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class receiveFragment extends Fragment {

    private receiveViewModel receiveViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.receive_message, container, false);
        receiveViewModel = ViewModelProviders.of(this).get(receiveViewModel.class);
        return root;
    }
}
