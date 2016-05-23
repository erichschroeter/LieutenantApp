package com.lieutenant.erichschroeter.lieutenantapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by erisch on 5/13/2016.
 */
public class LieutenantDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Lieutenant";
    public static final String DATABASE_FILE_EXTENSION = ".db";
    public static final String DATABASE_FILE_NAME = DATABASE_NAME + DATABASE_FILE_EXTENSION;

    public LieutenantDbHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbSchema.SQL_CREATE_ENTRIES);
        db.execSQL(DbSchema.SQL_CREATE_ENTRY_TAGS);
        db.execSQL(DbSchema.SQL_CREATE_TAGGED_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // simply to discard the data and start over.
        db.execSQL(DbSchema.SQL_DELETE_ENTRIES);
        db.execSQL(DbSchema.SQL_DELETE_ENTRY_TAGS);
        db.execSQL(DbSchema.SQL_DELETE_TAGGED_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
