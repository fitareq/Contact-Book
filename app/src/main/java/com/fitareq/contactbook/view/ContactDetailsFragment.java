package com.fitareq.contactbook.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitareq.contactbook.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class ContactDetailsFragment extends Fragment implements OnMapReadyCallback {


    private ConstraintLayout bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private String name, phone, address;
    private Double lat, lng;

    private TextView imageTV, nameTV, phoneTV, addressTV;
    private MaterialToolbar toolbar;
    private NavController navController;


    public ContactDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        bottomSheet = view.findViewById(R.id.bottom_sheet);
        imageTV = view.findViewById(R.id.bottom_sheet_image_tv);
        nameTV = view.findViewById(R.id.bottom_sheet_name);
        phoneTV = view.findViewById(R.id.bottom_sheet_phone);
        addressTV = view.findViewById(R.id.bottom_sheet_address);
        toolbar = view.findViewById(R.id.contact_details_toolbar);


        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        name = getArguments().getString("name");
        phone = getArguments().getString("phone");
        address = getArguments().getString("address");
        lat = getArguments().getDouble("lat");
        lng = getArguments().getDouble("lng");

        String img = " "+name.charAt(0)+" ";



        nameTV.setText(name);
        phoneTV.setText(phone);
        addressTV.setText(address);
        imageTV.setText(img.toUpperCase());


        toolbar.setNavigationOnClickListener(v -> {
            navController.navigate(R.id.action_contactDetailsFragment_to_contactFragment);
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.contact_details_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng dhaka = new LatLng(lat, lng);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dhaka, 10));
        googleMap.addMarker(new MarkerOptions().position(dhaka));

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }
}