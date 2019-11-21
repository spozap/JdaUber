package com.example.uberapp;

import androidx.lifecycle.ViewModel;

import com.example.uberapp.repository.Repository;

public class LoginDriverViewModel extends ViewModel {

    private static final String username = "sergi";
    private static final String password = "1234";

    private int tries = 3;


    public boolean checkIfLoginIsCorrect(String user,String pass){

        boolean is = Repository.checkLoginForDrivers(user,pass);

        if(is == true){
            return true;
        } else {
            return false;
        }
    }
}