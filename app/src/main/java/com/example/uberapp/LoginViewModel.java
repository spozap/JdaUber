package com.example.uberapp;

import androidx.lifecycle.ViewModel;

import com.example.uberapp.repository.Repository;

public class LoginViewModel extends ViewModel {

    private final String userTest = "admin";
    private final String passTest = "1234";

    private Repository r;

    public boolean checkIfLoginIsCorrect(String userTxt,String userPasswd){

        if(Repository.checkUserForUsers(userTxt,userPasswd)){
            return true;
        } else {
            return false;
        }
    }

}