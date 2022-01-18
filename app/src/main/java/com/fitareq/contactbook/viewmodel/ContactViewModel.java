package com.fitareq.contactbook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fitareq.contactbook.model.ContactData;
import com.fitareq.contactbook.model.ResponseObj;
import com.fitareq.contactbook.repository.ContactRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository repository;
    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
    }

    public void addNewContact(ContactData contactData){
        repository.addNewContact(contactData);
    }

    public LiveData<List<ContactData>> getAllContact(){
        return repository.getAllContact();
    }


}
