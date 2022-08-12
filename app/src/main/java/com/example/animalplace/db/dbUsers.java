package com.example.animalplace.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class dbUsers extends DbHelper {

    Context context;

    public dbUsers(@Nullable Context context) {
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
}
