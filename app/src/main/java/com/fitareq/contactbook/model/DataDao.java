package com.fitareq.contactbook.model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContact(ContactData contactData);

    @Query("SELECT * FROM contact_data")
    LiveData<List<ContactData>> getAllContact();


}
