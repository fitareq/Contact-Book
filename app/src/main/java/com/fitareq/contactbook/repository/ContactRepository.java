package com.fitareq.contactbook.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fitareq.contactbook.database.LocalDatabase;
import com.fitareq.contactbook.model.ContactData;
import com.fitareq.contactbook.model.DataDao;
import com.fitareq.contactbook.model.ResponseObj;

import java.util.List;

public class ContactRepository {
    private DataDao dataDao;
    private LiveData<List<ContactData>> contactData;


    public ContactRepository(Application application)
    {
        dataDao = LocalDatabase.getInstance(application).dataDao();
        contactData = dataDao.getAllContact();

    }

    public void addNewContact(ContactData contactData)
    {
        LocalDatabase.databaseWriteExecutor.execute(()->dataDao.insertContact(contactData));
    }

    public LiveData<List<ContactData>> getAllContact(){
        return this.contactData;
    }


}
