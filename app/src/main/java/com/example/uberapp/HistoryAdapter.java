package com.example.uberapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.AdapterHistory> {
    
    static List<History> histories;
    static NavController nv;

    public HistoryAdapter(List<History> histories, NavController nv){
        this.histories = histories;
        this.nv = nv;
    }


    public static class AdapterHistory extends RecyclerView.ViewHolder {

        TextView ubiact;
        TextView ubidest;
        TextView fechact;

        public AdapterHistory(final View row_history) {
           super(row_history);
            ubiact = row_history.findViewById(R.id.txtUbiAct);
            ubidest = row_history.findViewById(R.id.txtUbiDest);
            fechact = row_history.findViewById(R.id.txtFechaAct);

            row_history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle args = new Bundle();
                    args.putString("From", histories.get(getAdapterPosition()).UbicacionDestino);
                    args.putString("To",histories.get(getAdapterPosition()).UbicacionSalida);
                    args.putString("Date",histories.get(getAdapterPosition()).FechaActual);
                    Log.d("Clicked","Clicado en el item");
                    nv.navigate(R.id.rvDetails,args);
                }
            });
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