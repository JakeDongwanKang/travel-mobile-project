//package com.example.travel_project;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//public class MapFragment extends AppCompatActivity implements OnMapReadyCallback {
//
//    SupportMapFragment mapFragment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAPI);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        LatLng bcit = new LatLng(49.25010954461797, -123.00275621174804);
//        googleMap.addMarker(new MarkerOptions().position(bcit).title("BCIT"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bcit, 15));
//    }
//}