package com.example.uberapp.ui.home;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.uberapp.History;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HomeViewModel extends ViewModel {

    FirebaseAuth mAuth;
    private static String G_KEY = "AIzaSyA22u5QBvoTQ4bSYuotFcm_4EQIySHmWVA";

    String JSONExample = "{\n"+
            "   \"destination_addresses\" : [ \"New York, NY, USA\" ],\n"+
            "   \"origin_addresses\" : [ \"Washington, DC, USA\" ],\n"+
            "   \"rows\" : [\n"+
            "      {\n"+
            "         \"elements\" : [\n"+
            "            {\n"+
            "               \"distance\" : {\n"+
            "                  \"text\" : \"362 km\",\n"+
            "                  \"value\" : 361959\n"+
            "               },\n"+
            "               \"duration\" : {\n"+
            "                  \"text\" : \"3 hours 53 mins\",\n"+
            "                  \"value\" : 13970\n"+
            "               },\n"+
            "               \"status\" : \"OK\"\n"+
            "            }\n"+
            "         ]\n"+
            "      }\n"+
            "   ],\n"+
            "   \"status\" : \"OK\"\n"+
            "}";

    public void insertNewTrip(History trip){
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference().child("Viajes").push();
        tripRef.setValue(trip);

        FirebaseDatabase.getInstance().getReference().child("Usuarios").child(mAuth.getUid()).child("Viajes").push().setValue(tripRef.getKey());
    }

    public History getRoute(String JSON){
        History h = null;
        String destination = "";
        String origin = "";
        String km = "";
        try {
            JSONObject jso = new JSONObject(JSON);
            JSONArray destinations = jso.getJSONArray("destination_addresses");
            km = jso.getJSONArray("rows").getJSONObject(0).getJSONArray("elements")
                    .getJSONObject(0).getJSONObject("distance").getString("text");
            for(int i=0;i<destinations.length();i++){
                destination += destinations.getString(i)+" ";
            }
            JSONArray origins = jso.getJSONArray("origin_addresses");
            for (int i=0;i<origins.length();i++){
                origin += origins.getString(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("JSON","Error al procesar JSON");
        }
        h = new History("","",origin,destination,km);
        return h;
    }

}