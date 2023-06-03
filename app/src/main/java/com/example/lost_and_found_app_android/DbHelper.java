package com.example.lost_and_found_app_android;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final String TABLE_ITEMS_LOST_AND_FOUND = "TABLE_ITEMS_LOST_AND_FOUND";
    public static final String ITEM_ID = "ITEM_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String ITEM_DESCRIPTION = "ITEM_DESCRIPTION";
    public static final String DATE = "DATE";
    public static final String IS_DELETED = "IS_DELETED";
    public static final String ITEM_LOCATION = "ITEM_LOCATION";
    public static final String LOST_OR_FOUND = "LOST_OR_FOUND";


    public DbHelper(@Nullable Context context) {
        super(context, "table_items_lost_and_found", null, 1);
    }

    @Override // Create table.
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS " + TABLE_ITEMS_LOST_AND_FOUND + " (" + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " VARCHAR(25) NOT NULL, " + PHONE_NUMBER + " INTEGER(15) NOT NULL, " + ITEM_DESCRIPTION + " VARCHAR(255) NOT NULL, " + DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + IS_DELETED + " TINYINT DEFAULT 0, " + ITEM_LOCATION + " VARCHAR(100) NOT NULL DEFAULT NULL, " + LOST_OR_FOUND + " VARCHAR(10) NOT NULL DEFAULT NULL)";
        db.execSQL(queryCreateTable);
    }

    @Override // Called when Database schema changes in the code - will sync this change with the local Db instance.
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int x) {

    }

    public boolean createLostOrFoundItemRecord(LostAndFoundModel itemRecord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues associativeArray = new ContentValues();

        // UPDATE the associativeArray with data for creating a new record.
        associativeArray.put(USER_NAME, itemRecord.getUserName());
        associativeArray.put(PHONE_NUMBER, itemRecord.getPhoneNumber());
        associativeArray.put(ITEM_DESCRIPTION, itemRecord.getItemDescription());
        associativeArray.put(DATE, itemRecord.getDate());
        associativeArray.put(IS_DELETED, itemRecord.getIsDeleted());
        associativeArray.put(ITEM_LOCATION, itemRecord.getItemLocation());
        associativeArray.put(LOST_OR_FOUND, itemRecord.getLostOrFound());

        long insert = db.insert(TABLE_ITEMS_LOST_AND_FOUND, null, associativeArray);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public List<LostAndFoundModel> getAllItemRecords () {
        // Collection of records represented as a dynamic array/vector
        ArrayList<LostAndFoundModel> itemCollection = new ArrayList<>();

        // Make SELECT query
        String selectAll = "SELECT * FROM " + TABLE_ITEMS_LOST_AND_FOUND;
        SQLiteDatabase db = this.getReadableDatabase();

        // Cursor = 'result set' data-type
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) { // If cursor contains a value
            // iterate through the returned result set and add to the itemCollection
            do {
                int itemID = cursor.getInt(0);
                String userName = cursor.getString(1);
                int phoneNumber = cursor.getInt(2);
                String itemDescription = cursor.getString(3);
                String date = cursor.getString(4);
                boolean isDeleted = cursor.getInt(5) == 1 ? true: false;
                String itemLocation = cursor.getString(6);
                String lostOrFoundPost = cursor.getString(7);

                LostAndFoundModel newItemRecord = new LostAndFoundModel(itemID, userName, phoneNumber, itemDescription, date, isDeleted, itemLocation, lostOrFoundPost);
                itemCollection.add(newItemRecord);

            } while (cursor.moveToNext());

        } else {
            // Do not add anything to the list
        }
        // Close connection to the Db.
        cursor.close();
        db.close();
        return itemCollection;
    }

    public boolean deleteLostOrFoundItemRecord(LostAndFoundModel itemRecord) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQueryStr = "DELETE FROM " + TABLE_ITEMS_LOST_AND_FOUND + " WHERE " + ITEM_ID + " = " + itemRecord.getItemId();
        Cursor cursor = db.rawQuery(deleteQueryStr, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
}
