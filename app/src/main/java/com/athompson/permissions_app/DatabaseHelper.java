package com.athompson.permissions_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "permissions.db";
    private static final int DATABASE_VERSION = 1;
    private static class Permission {

        private long id;
        private String name;
        private String description;

        public Permission(long id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table to store permissions
        db.execSQL("CREATE TABLE permissions (id INTEGER PRIMARY KEY, name TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If you need to upgrade the database schema, do it here
    }
    public void addPermission(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        db.insert("permissions", null, values);
        db.close();
    }

}

