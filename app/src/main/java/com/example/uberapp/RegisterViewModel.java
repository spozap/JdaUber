package com.example.uberapp;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.example.uberapp.repository.Repository;

public class RegisterViewModel extends ViewModel {

    public void registerUserInFirebase(Activity activity,String email, String password){
        Repository.registerUserInFirebase(activity,email,password);
    }

}
