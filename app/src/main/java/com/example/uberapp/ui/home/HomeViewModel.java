package com.example.uberapp.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.uberapp.History;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    FirebaseAuth mAuth;
    public void insertNewTrip(History trip){
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference().child("Viajes").push();
        tripRef.setValue(trip);

        FirebaseDatabase.getInstance().getReference().child("Usuarios").child(mAuth.getUid()).child("Viajes").push().setValue(tripRef.getKey());
    }

    /*public ArrayList<History> readTrips(){

    }*/
}