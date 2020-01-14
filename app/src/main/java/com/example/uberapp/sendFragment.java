package com.example.uberapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class sendFragment extends Fragment {

    private sendViewModel sendViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.send_message, container, false);
        sendViewModel = ViewModelProviders.of(this).get(sendViewModel.class);

        EditText messageBox = root.findViewById(R.id.messageBox);
        Button sendMessageBtn = root.findViewById(R.id.sendMsgBtn);
        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendViewModel.sendMessage(messageBox.getText().toString());
                Toast.makeText(getContext(),"Mensaje enviado correctamente",Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}
