package com.example.uberapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class LoginDriver extends AppCompatActivity {

    private Button btnLoginDriver;
    private TextView userDriver;
    private TextView passDriver;
    LoginDriverViewModel loginDriverViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_driver);

        loginDriverViewModel = ViewModelProviders.of(this).get(LoginDriverViewModel.class);

        btnLoginDriver = findViewById(R.id.btnLogin);
        userDriver = findViewById(R.id.txtUserDriver);
        passDriver = findViewById(R.id.txtPassDriver);

        btnLoginDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userDriver.getText().toString();
                String password = passDriver.getText().toString();
                loginDriverViewModel.loginDriverInFirebase(LoginDriver.this,
                        username,password);
            }
        });

    }

}
