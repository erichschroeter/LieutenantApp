package com.lieutenant.erichschroeter.lieutenantapp;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by erisch on 5/13/2016.
 */
public final class LieutenantContract {

    public static final String AUTHORITY = "com.lieutenant.erichschroeter.lieutenantapp";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public LieutenantContract() {}

    public static abstract class Entry implements CommonColumns {
        public static final String PATH = "entry";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(LieutenantContract.CONTENT_URI, PATH);
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.com.lieutenant.erichschroeter." + PATH + "_items";
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/vnd.com.lieutenant.erichschroeter." + PATH;

        public static final String TABLE_NAME = "entries";
        public static final String TEXT = "text";

        public static final String[] PROJECTION_ALL = { _ID, CREATED_AT, UPDATED_AT, TEXT};
    }

    public static abstract class Tag implements CommonColumns {
        public static final String PATH = "tag";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(LieutenantContract.CONTENT_URI, PATH);
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.com.lieutenant.erichschroeter." + PATH + "_items";
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/vnd.com.lieutenant.erichschroeter." + PATH;

        public static final String TABLE_NAME = "tags";
        public static final String TEXT = "text";
        public static final String DESCRIPTION = "description";

        public static final String[] PROJECTION_ALL = { _ID, CREATED_AT, UPDATED_AT, TEXT, DESCRIPTION};
    }

    public static abstract class TaggedEntries implements CommonColumns {
        public static final String PATH = "tagged_entry";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(LieutenantContract.CONTENT_URI, PATH);
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.com.lieutenant.erichschroeter." + PATH + "_items";
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/vnd.com.lieutenant.erichschroeter." + PATH;

        public static final String TABLE_NAME = "tagged_entries";
        public static final String ENTRY_ID = "entry_id";
        public static final String TAG_ID = "tag_id";

        public static final String[] PROJECTION_ALL = { _ID, CREATED_AT, UPDATED_AT, ENTRY_ID, TAG_ID};
    }

    public interface CommonColumns extends BaseColumns {
        String CREATED_AT = "created_at";
        String UPDATED_AT = "updated_at";
    }
}
