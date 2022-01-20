package com.fitareq.contactbook.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.fitareq.contactbook.R;
import com.fitareq.contactbook.model.ContactData;
import com.fitareq.contactbook.viewmodel.AddNewContactViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AddNewContactFragment extends Fragment implements OnMapReadyCallback {

    private Geocoder geocoder;
    private List<Address> addresses;
    private GoogleMap map;
    private String contactAddress;
    private Double lat, lng;


    private TextInputLayout nameTIL, phoneTIL;
    private TextInputEditText nameET, phoneET;
    private TextView addressTV;
    private Button saveBTN;
    private MaterialToolbar toolbar;

    private AddNewContactViewModel addNewContactViewModel;
    private NavController navController;


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

        navController = Navigation.findNavController(view);

        nameTIL = view.findViewById(R.id.textInputLayout_enter_name);
        phoneTIL = view.findViewById(R.id.textInputLayout_enter_phone);
        nameET = view.findViewById(R.id.add_new_contact_name);
        phoneET = view.findViewById(R.id.add_new_contact_phone);
        addressTV = view.findViewById(R.id.add_new_contact_address);
        saveBTN = view.findViewById(R.id.add_new_contact_save_btn);
        toolbar = view.findViewById(R.id.add_new_contact_toolbar);

        addNewContactViewModel = new ViewModelProvider(requireActivity()).get(AddNewContactViewModel.class);

        geocoder = new Geocoder(requireActivity(), Locale.getDefault());

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        saveBTN.setOnClickListener(v -> {
            saveContact();
        });
        toolbar.setNavigationOnClickListener(v -> {
            navController.popBackStack();
            //navController.navigate(R.id.action_addNewContactFragment_to_contactFragment);
        });
    }

    private void saveContact()
    {
        String name = nameET.getText().toString();
        String phone = phoneET.getText().toString();
        if (TextUtils.isEmpty(name))
        {
            nameTIL.setError("Enter a name");
        }else if (TextUtils.isEmpty(phone))
        {
            phoneTIL.setError("Enter a phone");
        }else {
            ContactData contactData = new ContactData(phone, name, contactAddress, lat, lng);
            addNewContactViewModel.addNewContact(contactData);
            navController.popBackStack();
            //navController.navigate(R.id.action_addNewContactFragment_to_contactFragment);
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng dhaka = new LatLng(23.8103, 90.4125);
        getUserFullAddress(dhaka);



        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dhaka, 10));
        googleMap.addMarker(new MarkerOptions().position(dhaka));
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                getUserFullAddress(latLng);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                map.clear();
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                map.addMarker(markerOptions);
            }
        });
    }

    private void getUserFullAddress(LatLng latLng)
    {
        lat = latLng.latitude;
        lng = latLng.longitude;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            contactAddress = addresses.get(0).getAddressLine(0);
            Log.v("address", contactAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (contactAddress != null)
        {
            addressTV.setText("Location: "+contactAddress);
        }else addressTV.setText("No address Selected");
    }

}