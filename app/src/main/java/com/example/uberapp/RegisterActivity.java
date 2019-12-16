package com.example.uberapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class RegisterActivity extends AppCompatActivity {

    private TextView Username,Password;
    private Button Register;
    private RegisterViewModel registerViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        Username = findViewById(R.id.txtEditUsername);
        Password = findViewById(R.id.txtEditPassword);
        Register = findViewById(R.id.btnRegister);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerViewModel.registerUserInFirebase(RegisterActivity.this,
                        Username.getText().toString(),
                        Password.getText().toString());
            }
        });
    }
}
