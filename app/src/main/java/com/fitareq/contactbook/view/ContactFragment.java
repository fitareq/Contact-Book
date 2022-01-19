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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitareq.contactbook.R;
import com.fitareq.contactbook.adapter.ContactListRVAdapter;
import com.fitareq.contactbook.viewmodel.ContactViewModel;

public class ContactFragment extends Fragment implements ContactListRVAdapter.ItemOnClick {

    private ContactViewModel contactViewModel;
    private RecyclerView contactListRV;
    private TextView addNewContactTV;
    private ContactListRVAdapter contactListRVAdapter;



    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        contactViewModel = new ViewModelProvider(requireActivity()).get(ContactViewModel.class);
        contactListRV = view.findViewById(R.id.contact_list_rv);
        addNewContactTV = view.findViewById(R.id.contact_add_new);

        contactListRV.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));


        contactViewModel.getAllContact().observe(requireActivity(), contactData -> {
            contactListRVAdapter = new ContactListRVAdapter(contactData, requireActivity(), this);
            contactListRV.setAdapter(contactListRVAdapter);
        });


        addNewContactTV.setOnClickListener(v -> {
            navController.navigate(R.id.action_contactFragment_to_addNewContactFragment);
        });

    }

    @Override
    public void itemOnClickListener(int id) {

    }
}