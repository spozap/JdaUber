package com.example.uberapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.AdapterHistory> {

    List<History> histories;

    public HistoryAdapter(List<History> histories){
        this.histories = histories;
    }

    public static class AdapterHistory extends RecyclerView.ViewHolder{

        TextView ubiact;
        TextView ubidest;
        TextView fechact;


        public AdapterHistory(View row_history) {
           super(row_history);
            ubiact = row_history.findViewById(R.id.txtUbiAct);
            ubidest = row_history.findViewById(R.id.txtUbiDest);
            fechact = row_history.findViewById(R.id.txtFechaAct);
        }

    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    @Override
    public AdapterHistory onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_history,viewGroup,false);
        AdapterHistory ah = new AdapterHistory(v);
        return ah;
    }

    @Override
    public void onBindViewHolder(AdapterHistory adapterHistory,int i){
        adapterHistory.ubiact.setText(histories.get(i).getUbicacionSalida());
        adapterHistory.ubidest.setText(histories.get(i).getUbicacionDestino());
        adapterHistory.fechact.setText(histories.get(i).getFechaActual());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView rv){
        super.onAttachedToRecyclerView(rv);
    }
    
}