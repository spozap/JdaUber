package com.example.uberapp.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.uberapp.History;
import com.example.uberapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private HomeViewModel homeViewModel;
    private Button btnTrip;
    private MapView mapView;
    private GoogleMap gMap;
    Location currentLocation;


    private static final int REQUEST_CODE = 101;
    private final String G_KEY = "AIzaSyA22u5QBvoTQ4bSYuotFcm_4EQIySHmWVA";

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    FusedLocationProviderClient fusedLocationProviderClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        btnTrip = root.findViewById(R.id.btnTrip);
        mapView = root.findViewById(R.id.map_container);
        mapView.onCreate(mapViewBundle);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        fetchLastLocation();
        btnTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRoute g = new GetRoute();
                g.execute();

                //History h = homeViewModel.getRoute("");
                //homeViewModel.insertNewTrip(h);
            }
        });

        return root;
    }

    private void fetchLastLocation() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            Log.i("Location","permission");
            return;
        } else {

            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null){
                        currentLocation = location;
                        Log.i("Location","Location "+location.toString());
                        mapView.getMapAsync(HomeFragment.this);
                    }
                    else {
                        Log.i("Location","null");
                    }
                }
            });

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;
        ArrayList<Marker> listMarker = new ArrayList<>();
        LatLng actuaLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(actuaLng).title("Estoy aqui");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(actuaLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(actuaLng,5));
        googleMap.addMarker(markerOptions);

        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Posicionando el marcador y añadiendo el marcador
                int count = 0;

                Marker m = gMap.addMarker(new MarkerOptions().position(latLng));
                listMarker.add(m);
                if (listMarker.size() > 1){
                        Marker remove = listMarker.get(0);
                        listMarker.remove(0);
                        remove.remove();
                        count++;
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                break;
        }

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public class GetRoute extends AsyncTask<String,String,String>{

        String origin;
        String destination;
        @Override
        protected String doInBackground(String... strings) {
            origin = "41.414936, 2.202087";
            destination = "41.505089, 2.116144";

            String link = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origin+"&destinations="+destination+"&departure_time=now&key="+G_KEY;
            URL url;
            HttpURLConnection connection;

            try{
                url = new URL(link);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { // If the response is 200 (Connected) then read the result
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String linea = "";
                    StringBuilder response = new StringBuilder();

                    while ((linea = br.readLine()) != null){
                        response.append(linea+"\n");
                    }
                    Log.d("JSON",response.toString());

                    //Parsing the response to History object
                    homeViewModel.getRoute(response.toString());

                }
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}