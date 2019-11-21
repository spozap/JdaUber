package com.example.uberapp.ui.details;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetailsViewModel extends ViewModel{

        private MutableLiveData<String> mText;

        public DetailsViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("");
        }

        public void getDetailsFromUser(){

        }
        public LiveData<String> getText() {return mText;}
    }