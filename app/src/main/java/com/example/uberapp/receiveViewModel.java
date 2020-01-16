package com.example.uberapp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class receiveViewModel extends ViewModel {

    private MutableLiveData<Message> mMsg;
    private MutableLiveData<String> mId;

    public receiveViewModel(){
        mMsg = new MutableLiveData<>();
        mId = new MutableLiveData<>();
    }

    public MutableLiveData<Message> getmMsg() {
        return mMsg;
    }

    public void setmMsg(MutableLiveData<Message> mMsg) {
        this.mMsg = mMsg;
    }

    public MutableLiveData<String> getmId() {
        return mId;
    }

    public void setmId(MutableLiveData<String> mId) {
        this.mId = mId;
    }

    public void readMessageID(String uid){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("Usuarios").child(uid).child("Mensaje").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mId.postValue(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Message","Error while reading message ID");
            }
        });
    }

    public void readMessage(String msgID){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("Mensajes").child(msgID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mMsg.postValue(dataSnapshot.getValue(Message.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Message","Error while reading message");
            }
        });
    }
}
