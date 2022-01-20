package com.fitareq.contactbook.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_data")
public class ContactData
{
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String phoneNumber;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;

    public ContactData(String phoneNumber, String name, String address, Double latitude, Double longitude) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
