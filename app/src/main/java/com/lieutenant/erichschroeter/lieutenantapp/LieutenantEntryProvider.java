package com.lieutenant.erichschroeter.lieutenantapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by erisch on 5/16/2016.
 */
public class LieutenantEntryProvider extends ContentProvider {

    static final int ENTRIES = 1;
    static final int ENTRY_ID = 2;
    static final int TAGS = 3;
    static final int TAG_ID = 4;
    static final int TAGGED_ENTRIES = 5;
    static final int TAGGED_ENTRY_ID = 6;

    static final UriMatcher _uriMatcher;
    static {
        _uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        _uriMatcher.addURI(LieutenantContract.AUTHORITY, "entry", ENTRIES);
        _uriMatcher.addURI(LieutenantContract.AUTHORITY, "entry/#", ENTRY_ID);
        _uriMatcher.addURI(LieutenantContract.AUTHORITY, "tag", TAGS);
        _uriMatcher.addURI(LieutenantContract.AUTHORITY, "tag/#", TAG_ID);
        _uriMatcher.addURI(LieutenantContract.AUTHORITY, "tagged_entry", TAGGED_ENTRIES);
        _uriMatcher.addURI(LieutenantContract.AUTHORITY, "tagged_entry/#", TAGGED_ENTRY_ID);
    }

    private LieutenantDbHelper _dbHelper;

    @Override
    public boolean onCreate() {
        _dbHelper = new LieutenantDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        Uri _uri = null;
        long id = 0;

        switch (_uriMatcher.match(uri)) {
            case ENTRIES:
                id = db.insert(LieutenantContract.Entry.TABLE_NAME, null, values);

                if (id > 0)
                {
                    _uri = ContentUris.withAppendedId(LieutenantContract.Entry.CONTENT_URI, id);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case ENTRY_ID:
                id = db.insert(LieutenantContract.Entry.TABLE_NAME, null, values);

                if (id > 0)
                {
                    _uri = ContentUris.withAppendedId(LieutenantContract.Entry.CONTENT_URI, id);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return _uri;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        SQLiteQueryBuilder b = new SQLiteQueryBuilder();
        b.setTables(LieutenantContract.Entry.TABLE_NAME);

        switch (_uriMatcher.match(uri)) {
            case ENTRIES:
                break;
            case ENTRY_ID:
                b.appendWhere(LieutenantContract.Entry._ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        Cursor c = b.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        // Register to watch a content URI for changes.
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        int count = 0;

        switch (_uriMatcher.match(uri)) {
            case ENTRIES:
                break;
            case ENTRY_ID:
                count = db.update(LieutenantContract.Entry.TABLE_NAME, values,
                        LieutenantContract.Entry._ID + "=" + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : ""),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        int count = 0;

        switch (_uriMatcher.match(uri)) {
            case ENTRIES:
                break;
            case ENTRY_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(LieutenantContract.Entry.TABLE_NAME,
                        LieutenantContract.Entry._ID + "=" + id +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : ""),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (_uriMatcher.match(uri)) {
            case ENTRIES:
                return LieutenantContract.Entry.CONTENT_TYPE;
            case ENTRY_ID:
                return LieutenantContract.Entry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}
