package com.fitareq.contactbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.fitareq.contactbook.database.LocalDatabase;
import com.fitareq.contactbook.model.ContactData;
import com.fitareq.contactbook.repository.AddNewContactRepository;

public class AddNewContactViewModel extends AndroidViewModel {

    AddNewContactRepository repository;
    public AddNewContactViewModel(@NonNull Application application) {
        super(application);
        repository = new AddNewContactRepository(application);
    }
    public void addNewContact(ContactData contactData)
    {
        repository.addNewContact(contactData);
    }
}
