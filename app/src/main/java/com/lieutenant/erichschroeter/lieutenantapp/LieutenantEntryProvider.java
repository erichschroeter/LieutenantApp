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
    static final String PROVIDER_NAME = "com.lieutenant.erichschroeter.lieutenantapp";
    static final String PATH_ENTRY = "entry";
    static final String PATH_TAG = "tag";
    static final String URL_ENTRY = "content://" + PROVIDER_NAME + "/" + PATH_ENTRY;
    static final String URL_TAG = "content://" + PROVIDER_NAME + "/" + PATH_TAG;
    static final Uri CONTENT_URI_ENTRY = Uri.parse(URL_ENTRY);
    static final Uri CONTENT_URI_TAG = Uri.parse(URL_TAG);

    static final int ENTRIES = 1;
    static final int ENTRY_ID = 2;
    static final int TAGS = 3;
    static final int TAG_ID = 4;

    static final UriMatcher _uriMatcher;
    static {
        _uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        _uriMatcher.addURI(PROVIDER_NAME, PATH_ENTRY, ENTRIES);
        _uriMatcher.addURI(PROVIDER_NAME, PATH_ENTRY + "/#", ENTRY_ID);
        _uriMatcher.addURI(PROVIDER_NAME, PATH_TAG, TAGS);
        _uriMatcher.addURI(PROVIDER_NAME, PATH_TAG + "/#", TAG_ID);
    }

    private SQLiteDatabase _db;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        LieutenantDbHelper dbHelper = new LieutenantDbHelper(context);

        _db = dbHelper.getWritableDatabase();

        return (_db == null) ? false : true;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri _uri = null;
        long id = 0;

        switch (_uriMatcher.match(uri)) {
            case ENTRIES:
                id = _db.insert(LieutenantContract.Entry.TABLE_NAME, "", values);

                if (id > 0)
                {
                    _uri = ContentUris.withAppendedId(CONTENT_URI_ENTRY, id);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case ENTRY_ID:
                id = _db.insert(LieutenantContract.Entry.TABLE_NAME, "", values);

                if (id > 0)
                {
                    _uri = ContentUris.withAppendedId(CONTENT_URI_ENTRY, id);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case TAGS:
                break;
            case TAG_ID:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return _uri;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
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

        Cursor c = b.query(_db, projection, selection, selectionArgs, null, null, sortOrder);
        // Register to watch a content URI for changes.
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (_uriMatcher.match(uri)) {
            case ENTRIES:
                break;
            case ENTRY_ID:
                count = _db.update(LieutenantContract.Entry.TABLE_NAME, values,
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
        int count = 0;

        switch (_uriMatcher.match(uri)) {
            case ENTRIES:
                break;
            case ENTRY_ID:
                String id = uri.getPathSegments().get(1);
                count = _db.delete(LieutenantContract.Entry.TABLE_NAME,
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
                return "vnd.android.cursor.dir/vnd.com.lieutenant.erichschroeter.entries";
            case ENTRY_ID:
                return "vnd.android.cursor.item/vnd.com.lieutenant.erichschroeter.entries";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}
