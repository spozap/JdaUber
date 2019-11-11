package com.example.uberapp.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uberapp.History;
import com.example.uberapp.HistoryAdapter;
import com.example.uberapp.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private HistoryViewModel HistoryViewModel;
    LinearLayout ll;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        HistoryViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
                View root = inflater.inflate(R.layout.fragment_history, container , false);
        RecyclerView rv = (RecyclerView) root.findViewById(R.id.rv1);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        List<History> histories;

        histories = new ArrayList<>();
        histories.add(new History("Calle San sebastián","La Maquinista"));
        histories.add(new History("Glories","Diagonal Mar"));
        histories.add(new History("Calle Gran de Sant Andreu","Heron City"));
        histories.add(new History("asdasdasdasdsa","asdadad"));
        histories.add(new History("fdgjfkdgjdfgdfl","sdlskjgskdj"));
        histories.add(new History("dfkdjfkjsdf","hgfdjhgjdfhghfjd"));

        HistoryAdapter ha = new HistoryAdapter(histories);
        rv.setAdapter(ha);
        return root;

    }



}