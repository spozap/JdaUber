package com.example.uberapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginDriver extends AppCompatActivity {

    private Button btnLoginDriver;
    private TextView userDriver;
    private TextView passDriver;
    private TextView tries;
    private int intentos = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_driver);


        btnLoginDriver = findViewById(R.id.btnLogin);
        userDriver = findViewById(R.id.txtUserDriver);
        passDriver = findViewById(R.id.txtPassDriver);
        tries = findViewById(R.id.driverTries);

        btnLoginDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users u = new Users(userDriver.getText().toString(),passDriver.getText().toString());
                saveDriver(u);
                //validateLoginDriver(userDriver.getText().toString(),passDriver.getText().toString());
            }
        });

    }

    public void validateLoginDriver(String DriverTxt, String DriverPasswd){
        if(DriverTxt.equals("admin") && DriverPasswd.equals("1234")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            CheckIntentos(intentos);
        }
    }

    public void CheckIntentos(int ints){
        ints = getIntentos();
        if (ints > 1) {
            ints--;
            tries.setText("Tienes "+ints+" intentos disponibles");
            setIntentos(ints);
        } else {
            btnLoginDriver.setEnabled(false); // Desactivar el boton de login
            tries.setText("Te has quedado sin intentos , intentalo más tarde");
        }
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
