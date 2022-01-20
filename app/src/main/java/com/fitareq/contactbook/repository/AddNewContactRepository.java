package com.fitareq.contactbook.repository;

import android.app.Application;

import com.fitareq.contactbook.database.LocalDatabase;
import com.fitareq.contactbook.model.ContactData;
import com.fitareq.contactbook.model.DataDao;

public class AddNewContactRepository {
    private DataDao dataDao;

    public AddNewContactRepository(Application application)
    {
        dataDao = LocalDatabase.getInstance(application).dataDao();
    }

    public void addNewContact(ContactData contactData)
    {
        LocalDatabase.databaseWriteExecutor.execute(()->dataDao.insertContact(contactData));
    }
}
