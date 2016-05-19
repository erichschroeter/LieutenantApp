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

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LieutenantContract.Entry.TABLE_NAME + " (" +
                    LieutenantContract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    LieutenantContract.Entry.COLUMN_NAME_TEXT + " TEXT" +
                    ");";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LieutenantContract.Entry.TABLE_NAME + ";";

    private static final String SQL_CREATE_ENTRY_TAGS =
            "CREATE TABLE " + LieutenantContract.EntryTag.TABLE_NAME + " (" +
                    LieutenantContract.EntryTag._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    LieutenantContract.EntryTag.COLUMN_NAME_TEXT + " TEXT, " +
                    LieutenantContract.EntryTag.COLUMN_NAME_DESCRIPTION + " TEXT" +
                    ");";
    private static final String SQL_DELETE_ENTRY_TAGS =
            "DROP TABLE IF EXISTS " + LieutenantContract.EntryTag.TABLE_NAME + ";";

    private static final String SQL_CREATE_TAGGED_ENTRIES =
            "CREATE TABLE " + LieutenantContract.TaggedEntries.TABLE_NAME + " (" +
                    " FOREIGN KEY (" + LieutenantContract.TaggedEntries.COLUMN_NAME_ENTRY_ID + ") REFERENCES " +
                    LieutenantContract.Entry.TABLE_NAME + " (" + LieutenantContract.Entry._ID + "), " +
                    " FOREIGN KEY (" + LieutenantContract.TaggedEntries.COLUMN_NAME_TAG_ID + ") REFERENCES " +
                    LieutenantContract.EntryTag.TABLE_NAME + " (" + LieutenantContract.EntryTag._ID + "), " +
                    " PRIMARY KEY (" + LieutenantContract.TaggedEntries.COLUMN_NAME_ENTRY_ID + ", " +
                    LieutenantContract.TaggedEntries.COLUMN_NAME_TAG_ID + ")" +
                    ");";
    private static final String SQL_DELETE_TAGGED_ENTRIES =
            "DROP TABLE IF EXISTS " + LieutenantContract.TaggedEntries.TABLE_NAME + ";";


    public LieutenantDbHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRY_TAGS);
        db.execSQL(SQL_CREATE_TAGGED_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // simply to discard the data and start over.
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRY_TAGS);
        db.execSQL(SQL_DELETE_TAGGED_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
