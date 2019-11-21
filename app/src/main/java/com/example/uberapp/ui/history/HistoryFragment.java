package com.example.uberapp.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uberapp.History;
import com.example.uberapp.HistoryAdapter;
import com.example.uberapp.R;
import com.example.uberapp.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private HistoryViewModel HistoryViewModel;
    LinearLayout ll;
    private HistoryAdapter ha;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        HistoryViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container , false);
        RecyclerView rv = (RecyclerView) root.findViewById(R.id.rv1);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        List<History> histories;
        histories = Repository.getHistories();

        ha = new HistoryAdapter(histories, Navigation.findNavController(getActivity(), R.id.nav_host_fragment));
        rv.setAdapter(ha);
        return root;
    }


}