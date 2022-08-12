package com.example.animalplace.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUsers extends DbHelper {

    Context context;

    public DbUsers(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertUser(String email, String password) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("email", email);
            values.put("password", password);
            id = db.insert(TABLE_USER, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public boolean isUserExists(String email, String password) {
        boolean exists = false;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_USER + " WHERE email = '" + email + "' AND password = '" + password + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public Cursor getAllUsers() {
        Cursor cursor = null;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_USER;
            cursor = db.rawQuery(query, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }
}
