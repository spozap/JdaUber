package com.example.uberapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.uberapp.databases.bbdd;

public class LoginDriver extends AppCompatActivity {

    private Button btnLoginDriver;
    private TextView userDriver;
    private TextView passDriver;
    private TextView tries;
    private int intentos = 3;
    LoginDriverViewModel loginDriverViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_driver);

        loginDriverViewModel = ViewModelProviders.of(this).get(LoginDriverViewModel.class);

        btnLoginDriver = findViewById(R.id.btnLogin);
        userDriver = findViewById(R.id.txtUserDriver);
        passDriver = findViewById(R.id.txtPassDriver);
        tries = findViewById(R.id.driverTries);

        btnLoginDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Users u = new Users(userDriver.getText().toString(),passDriver.getText().toString());
                String username = userDriver.getText().toString();
                String password = passDriver.getText().toString();
                boolean is = loginDriverViewModel.checkIfLoginIsCorrect(username,password);
                if (is == true){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginDriver.this,"Usuario o contraseña incorrecto",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void saveDriver(Users us) {
        ContentValues values = new ContentValues();

        values.put("Username",us.getUsername());
        values.put("Password",us.getPassword());
        values.put("UserType","Driver");

        bbdd bbdd= new bbdd(this);
        SQLiteDatabase db = bbdd.getWritableDatabase();

        db.insert("Users",null,values);
        db.close();

    }
    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
}
