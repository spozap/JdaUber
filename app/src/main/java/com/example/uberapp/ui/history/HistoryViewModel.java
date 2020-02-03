package com.example.uberapp.ui.history;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uberapp.History;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends ViewModel {

    FirebaseAuth mAuth;
    ArrayList<String> historyIDList;
    ArrayList<History> historyList;


    private MutableLiveData<ArrayList<String>> IDViajes;
    private MutableLiveData<ArrayList<History>> tripList;

    public HistoryViewModel() {
        IDViajes = new MutableLiveData<>();
        tripList = new MutableLiveData<>();
        historyIDList = new ArrayList<>();
        historyList = new ArrayList<>();
    }

    public MutableLiveData<ArrayList<String>> getIDViajes() {
        return IDViajes;
    }

    public MutableLiveData<ArrayList<History>> getTripList() {return tripList;}

    public void setIDViajes(MutableLiveData<ArrayList<String>> IDViajes) {
        this.IDViajes = IDViajes;
    }

    public void getTripIds(){
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(mAuth.getUid())
                .child("Viajes");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    historyIDList.add(d.getValue(String.class));
                    IDViajes.postValue(historyIDList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getTrips(ArrayList<String> tripId){
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Viajes");
            for (String s : tripId){
                ref.child(s).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        History h = dataSnapshot.getValue(History.class);
                        historyList.add(h);
                        tripList.postValue(historyList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        };
    }



}