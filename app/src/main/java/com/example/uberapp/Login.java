package com.example.uberapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.uberapp.databases.bbdd;

public class Login extends AppCompatActivity {

    private Button login;
    private EditText user;
    private EditText pass;
    private TextView tries;
    private int intentos = 3;
    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        login = findViewById(R.id.btnLogin);
        user = findViewById(R.id.textUser);
        pass = findViewById(R.id.textPassword);
        tries = findViewById(R.id.textTries);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = user.getText().toString();
                String pa = pass.getText().toString();
                //boolean id = loginViewModel.checkIfLoginIsCorrect(us,pa);
                loginViewModel.loginDriverInFirebase(Login.this,us,pa,getSupportFragmentManager());
            }
        });
    }

    public void validateLogin(String userTxt,String userPasswd){
        if(userTxt.equals("admin") && userPasswd.equals("1234")){
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
            login.setEnabled(false); // Desactivar el boton de login
            tries.setText("Te has quedado sin intentos , intentalo más tarde");
        }
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public void saveUser(String username,String password) {
        ContentValues values = new ContentValues();

        values.put("Username",username);
        values.put("Password",password);
        values.put("UserType","User");

        bbdd bbdd= new bbdd(this);
        SQLiteDatabase db = bbdd.getWritableDatabase();

        db.insert("Users",null,values);
        db.close();
    }

    public boolean checkUser(String username,String password){


        bbdd bbdd = new bbdd(this);
        SQLiteDatabase db = bbdd.getWritableDatabase();

        // Columna que se espera que retorne el select
        String[] columnas = {"IDUsername"};

        //La columna en la que va a ir a buscar el where , el ? se reemplaza por el valor que se pasa luego
        String select = "Username = ? and Password=?";

        // Argumento que se va a pasar al WHERE
        String[] selectArgs = {username,password};

        Cursor c = db.query("Users",
                columnas,
                select,
                selectArgs,
                null,
                null,
                null
                );
        int count = c.getCount();
        c.close();
        db.close();

        if (count > 0){
            return  true;
        } else {
            return false;
        }

    }
}