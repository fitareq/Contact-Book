package com.fitareq.contactbook.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_data")
public class ContactData
{
    @PrimaryKey(autoGenerate = true)
    private Integer id = 0;
    private String phoneNumber;
    private String name;
    private String address;

    public ContactData(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
