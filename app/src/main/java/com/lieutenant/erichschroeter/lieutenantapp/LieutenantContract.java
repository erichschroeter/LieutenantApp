package com.lieutenant.erichschroeter.lieutenantapp;

import android.provider.BaseColumns;

/**
 * Created by erisch on 5/13/2016.
 */
public final class LieutenantContract {

    public LieutenantContract() {}

    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "entries";
        public static final String COLUMN_NAME_TEXT = "text";
    }

    public static abstract class EntryTag implements BaseColumns {
        public static final String TABLE_NAME = "tags";
        public static final String COLUMN_NAME_TEXT = "text";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }

    public static abstract class TaggedEntries implements BaseColumns {
        public static final String TABLE_NAME = "tagged_entries";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TAG_ID = "tagid";
    }
}
