package com.fitareq.contactbook.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitareq.contactbook.R;
import com.fitareq.contactbook.adapter.ContactListRVAdapter;
import com.fitareq.contactbook.model.ContactData;
import com.fitareq.contactbook.viewmodel.ContactViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class ContactFragment extends Fragment implements ContactListRVAdapter.ItemOnClick {

    private ContactViewModel contactViewModel;
    private RecyclerView contactListRV;
    private TextView addNewContactTV;
    private ContactListRVAdapter contactListRVAdapter;
    private NavController navController;
    private MaterialToolbar toolbar;



    public ContactFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_contact, container, false);
        setHasOptionsMenu(true);

        return viewGroup;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        contactViewModel = new ViewModelProvider(requireActivity()).get(ContactViewModel.class);
        contactListRV = view.findViewById(R.id.contact_list_rv);
        addNewContactTV = view.findViewById(R.id.contact_add_new);
        //toolbar = view.findViewById(R.id.contact_toolbar);

        contactListRV.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));

        contactViewModel.getUserData().observe(requireActivity(), responseObj -> {
            if (responseObj == null)
            {
                navController.navigate(R.id.action_contactFragment_to_loginFragment);
            }
        });

        contactViewModel.getAllContact().observe(requireActivity(), contactData -> {
            contactListRVAdapter = new ContactListRVAdapter(contactData, requireActivity(), this);
            contactListRV.setAdapter(contactListRVAdapter);
        });


        addNewContactTV.setOnClickListener(v -> {
            navController.navigate(R.id.action_contactFragment_to_addNewContactFragment);
        });




    }

    @Override
    public void itemOnClickListener(ContactData data) {
        Bundle bundle = new Bundle();
        bundle.putString("name", data.getName());
        bundle.putString("phone", data.getPhoneNumber());
        bundle.putString("address", data.getAddress());
        bundle.putDouble("lat", data.getLatitude());
        bundle.putDouble("lng", data.getLongitude());
        navController.navigate(R.id.action_contactFragment_to_contactDetailsFragment, bundle);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.v("search", "okayh");
        getActivity().getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        //inflater.inflate(R.menu.app_bar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.contact_menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v("search", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v("search", newText);
                return false;
            }
        });
    }
}