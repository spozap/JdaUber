package com.example.uberapp.ui.details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.uberapp.R;

public class DetailsFragment extends Fragment {

    private DetailsViewModel DetailsViewModel;

    private TextView showFrom;
    private TextView showWhere;

    private static final String ArgmParam1 = "From";
    private static final String ArgmParam2 = "To";
    private static final String ArgmParam3 = "Date";

    private String mParam1;
    private String mParam2;
    private String mParam3;

   @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mParam1 = getArguments().getString(ArgmParam1);
            mParam2 = getArguments().getString(ArgmParam2);
            mParam3 = getArguments().getString(ArgmParam3);
        } else {
            Log.d("NO","No entra");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        DetailsViewModel =
                ViewModelProviders.of(this).get(DetailsViewModel.class);
                View root = inflater.inflate(R.layout.rv_details,container,false);

                showFrom = root.findViewById(R.id.showFrom);
                showWhere = root.findViewById(R.id.showDest);
                showFrom.setText(mParam1);
                showWhere.setText(mParam2);
        return root;
    }
}