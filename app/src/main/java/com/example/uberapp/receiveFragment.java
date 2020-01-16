package com.example.uberapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;

public class receiveFragment extends Fragment {

    private receiveViewModel receiveViewModel;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.receive_message, container, false);
        TextView from = root.findViewById(R.id.fromwho);
        TextView messageBox = root.findViewById(R.id.message);
        mAuth = FirebaseAuth.getInstance();
        receiveViewModel = ViewModelProviders.of(this).get(receiveViewModel.class);

        receiveViewModel.readMessageID(mAuth.getUid());
        receiveViewModel.getmId().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                receiveViewModel.readMessage(s);
                Log.i("Message",s);
            }
        });

        receiveViewModel.getmMsg().observe(this, new Observer<Message>() {
            @Override
            public void onChanged(Message message) {
                from.setText(message.getTransmitter());
                messageBox.setText(message.getMessage());
            }
        });
        return root;
    }
}
