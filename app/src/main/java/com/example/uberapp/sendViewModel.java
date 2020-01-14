package com.example.uberapp;

import androidx.lifecycle.ViewModel;

import com.example.uberapp.repository.Repository;

public class sendViewModel extends ViewModel {
    public void sendMessage(String message){
        Repository.sendMessage(message);
    }
}
