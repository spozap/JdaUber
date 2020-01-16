package com.example.uberapp;

import androidx.lifecycle.ViewModel;

import com.example.uberapp.repository.Repository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class sendViewModel extends ViewModel {

    FirebaseAuth mAuth;
    public void sendMessage(String message){
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference msgRef = db.child("Mensajes").push();
        msgRef.setValue(new Message(mAuth.getUid(),"wKP6sGCAEIYSB3nUPjsDjgeBCfB3",message,Repository.actualDate()));
        db.child("Usuarios").child("wKP6sGCAEIYSB3nUPjsDjgeBCfB3").child("Mensaje").setValue(msgRef.getKey());
    }
}
