package com.example.lost_and_found_app_android;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Date;
import java.time.LocalDate;

public class LostAndFoundModel {
    private int itemId;
    private String userName;
    private int phoneNumber;
    private String itemDescription;
    private String date;
    private String itemLocation;

    private double itemLocationLat;
    private double itemLocationLng;
    private boolean isDeleted;
    private String lostOrFound;
    public LostAndFoundModel(int itemId, String userName, int phoneNumber, String itemDescription, String date, boolean isDeleted, double itemLocationLat, double itemLocationLng, String lostOrFound) {
        this.itemId = itemId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.itemDescription = itemDescription;
        this.date = date;
        this.isDeleted = isDeleted;
        this.lostOrFound = lostOrFound;
        this.itemLocationLat = itemLocationLat;
        this.itemLocationLng = itemLocationLng;
    }
    public String toString() {
        return itemId + ", " + userName + ", " + phoneNumber + ", " + itemDescription + ", " + date + ", " + itemLocation + ",  "  + itemLocationLat + ", " + itemLocationLng + ", " + lostOrFound;
    }
    // Getters
    public int getItemId() {
        return itemId;
    }

    public String getUserName() {
        return userName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getDate() {
        return date;
    }

    public String getItemLocation() {
        return itemLocation;
    }
    public double getItemLocationLat() {
        return itemLocationLat;
    }
    public double getItemLocationLng() {
        return itemLocationLng;
    }public boolean getIsDeleted() {
        return isDeleted;
    }

    public String getLostOrFound() {
        return lostOrFound;
    }

    // Setters
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setLostOrFound(String lostOrFound) {
        this.lostOrFound = lostOrFound;
    }
}