package com.example.uberapp.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uberapp.History;
import com.example.uberapp.HistoryAdapter;
import com.example.uberapp.R;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private HistoryViewModel HistoryViewModel;
    private HistoryAdapter ha;
    private ArrayList<String> IDViajes = new ArrayList<>();
    private ArrayList<History> historyList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        HistoryViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container , false);
        RecyclerView rv = (RecyclerView) root.findViewById(R.id.rv1);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        IDViajes = new ArrayList<>();
        HistoryViewModel.getIDViajes().observe(this,
                new Observer<ArrayList<String>>() {
                    @Override
                    public void onChanged(ArrayList<String> Viajes) {
                        IDViajes = Viajes;
                        HistoryViewModel.getTrips(IDViajes);
                    }
                });
        HistoryViewModel.getTripIds();

        HistoryViewModel.getTripList().observe(this,
                new Observer<ArrayList<History>>() {
                    @Override
                    public void onChanged(ArrayList<History> histories) {
                        historyList = histories;
                        ha.setLista(historyList);
                        ha.notifyDataSetChanged();
                    }
                });

        HistoryViewModel.getTripList();

        ha = new HistoryAdapter(historyList, Navigation.findNavController(getActivity(), R.id.nav_host_fragment));
        rv.setAdapter(ha);
        return root;
    }
}