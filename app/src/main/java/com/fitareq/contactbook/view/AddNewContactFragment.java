package com.fitareq.contactbook.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.fitareq.contactbook.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AddNewContactFragment extends Fragment implements OnMapReadyCallback {

    Geocoder geocoder;
    List<Address> addresses;
    GoogleMap map;
    LocationManager locationManager;


    public AddNewContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        geocoder = new Geocoder(requireActivity(), Locale.getDefault());

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Address");
                map.clear();
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
                map.addMarker(markerOptions);
            }
        });
        LatLng dhaka = new LatLng(23.8103, 90.4125);
        try {
            addresses = geocoder.getFromLocation(dhaka.latitude, dhaka.longitude, 1);
            Log.v("address",
                    "address: " + addresses.get(0).getAddressLine(0) +
                            "country : " + addresses.get(0).getCountryName()

            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dhaka, 10));
        googleMap.addMarker(new MarkerOptions().position(dhaka).title("Dhaka"));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(dhaka));
    }

}