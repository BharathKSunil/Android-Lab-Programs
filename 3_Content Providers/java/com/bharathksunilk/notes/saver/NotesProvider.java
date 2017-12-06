package com.bharathksunilk.notes.saver;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class NotesProvider extends ContentProvider {
    public NotesProvider() {
    }

    DBHandler handler;
    static final String CONTENT_URI="content://com.bharathksunilk.notes";

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // Implement this to handle requests for the MIME type of the data
        // at the given URI.
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        handler.insertNote(values.getAsString(DBHandler.COL_DATE), values.getAsString(DBHandler.COL_CONTENT));
        return uri;
    }


    @Override
    public boolean onCreate() {
        handler = new DBHandler(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return handler.getNotesForToday();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // Implement this to handle requests to update one or more rows.
        return 0;
    }
}
