package com.example.uberapp.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uberapp.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends ViewModel {


    List<History> historyList;


    private MutableLiveData<String> mText;

    public HistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is History fragment");
    }

    public LiveData<String> getText() {return mText;}
    /*
    public ArrayList<History> getHistoryList(){
      //Petición a la bbdd
    } */


}