package com.example.uberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uberapp.ui.share.ShareFragment;

public class Login extends AppCompatActivity {

    private Button login;
    private TextView user;
    private TextView pass;
    private TextView tries;
    private int intentos = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        login = findViewById(R.id.btnLogin);
        user = findViewById(R.id.textUser);
        pass = findViewById(R.id.textPassword);
        tries = findViewById(R.id.textTries);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin(user.getText().toString(),pass.getText().toString());
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

}
