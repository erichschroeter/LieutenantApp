package com.lieutenant.erichschroeter.lieutenantapp;

/**
 * Created by erich on 5/22/16.
 */
/* package private */ interface DbSchema {

    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LieutenantContract.Entry.TABLE_NAME + " (" +
                    LieutenantContract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LieutenantContract.Entry.CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    LieutenantContract.Entry.UPDATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    LieutenantContract.Entry.TEXT + " TEXT" +
                    ");";
    String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LieutenantContract.Entry.TABLE_NAME + ";";

    String SQL_CREATE_ENTRY_TAGS =
            "CREATE TABLE " + LieutenantContract.Tag.TABLE_NAME + " (" +
                    LieutenantContract.Tag._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    LieutenantContract.Tag.CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    LieutenantContract.Tag.UPDATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    LieutenantContract.Tag.TEXT + " TEXT, " +
                    LieutenantContract.Tag.DESCRIPTION + " TEXT" +
                    ");";
    String SQL_DELETE_ENTRY_TAGS =
            "DROP TABLE IF EXISTS " + LieutenantContract.Tag.TABLE_NAME + ";";

    String SQL_CREATE_TAGGED_ENTRIES =
            "CREATE TABLE " + LieutenantContract.TaggedEntries.TABLE_NAME + " (" +
                    LieutenantContract.TaggedEntries.CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    LieutenantContract.TaggedEntries.UPDATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    " FOREIGN KEY (" + LieutenantContract.TaggedEntries.ENTRY_ID + ") REFERENCES " +
                    LieutenantContract.Entry.TABLE_NAME + " (" + LieutenantContract.Entry._ID + "), " +
                    " FOREIGN KEY (" + LieutenantContract.TaggedEntries.TAG_ID + ") REFERENCES " +
                    LieutenantContract.Tag.TABLE_NAME + " (" + LieutenantContract.Tag._ID + "), " +
                    " PRIMARY KEY (" + LieutenantContract.TaggedEntries.ENTRY_ID + ", " +
                    LieutenantContract.TaggedEntries.TAG_ID + ")" +
                    ");";
    String SQL_DELETE_TAGGED_ENTRIES =
            "DROP TABLE IF EXISTS " + LieutenantContract.TaggedEntries.TABLE_NAME + ";";

}
