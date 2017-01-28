
package com.task.contactmanagement.mvp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Storage extends SQLiteOpenHelper {
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PROFILE_PIC = "profile_pic";
    private static final String URL = "url";
    private static final String FAVORITE = "favorite";
    private static final String TABLE_NAME = "contacts";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            ID + " integer primary key autoincrement not null," +
            FIRST_NAME + " text not null," +
            LAST_NAME + " text not null," +
            PROFILE_PIC + " text not null," +
            FAVORITE + " text not null," +
            URL + " text not null)";
    private static final String TAG = Storage.class.getSimpleName();

    @Inject
    public Storage(Context context) {
        super(context, "contact_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch(SQLException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID, contact.getId());
        values.put(FIRST_NAME, contact.getFirst_name());
        values.put(LAST_NAME, contact.getLast_name());
        values.put(PROFILE_PIC, contact.getProfile_pic());
        values.put(FAVORITE, String.valueOf(contact.getFavourite()));
        values.put(URL, contact.getUrl());

        try {
            db.insert(TABLE_NAME, null, values);
        } catch(SQLException e) {
            Log.d(TAG, e.getMessage());
        }

        db.close();
    }

    public List<Contact> getSavedContacts() {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            Cursor cursor = db.rawQuery(SELECT_QUERY, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    if (cursor.moveToFirst()) {
                        do {
                            Contact contact = new Contact();
                            contact.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                            contact.setFirst_name(cursor.getString(cursor.getColumnIndex(FIRST_NAME)));
                            contact.setLast_name(cursor.getString(cursor.getColumnIndex(LAST_NAME)));
                            contact.setProfile_pic(cursor.getString(cursor.getColumnIndex(PROFILE_PIC)));
                            contact.setFavorite(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(FAVORITE))));
                            contact.setUrl(cursor.getString(cursor.getColumnIndex(URL)));
                            contactList.add(contact);

                        } while (cursor.moveToNext());
                    }
                }
            }
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
        return contactList;
    }



}
