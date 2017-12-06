package com.bharathksunilk.notes.saver;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This handler handles the database and its related operations
 */
class DBHandler extends SQLiteOpenHelper{

    public static final String DB_NAME="NotesDB",
            TABLE_NOTES="notes",
            COL_DATE="date",
            COL_CONTENT="content";
    public static final int DB_VERSION=1;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery="CREATE TABLE " + TABLE_NOTES + "( " +
                COL_DATE+" TEXT, " +
                COL_CONTENT+" TEXT" +
                ");";
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    void insertNote(String date, String content){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DATE, date);
        values.put(COL_CONTENT, content);

        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    Cursor getNotesForToday(){
        SQLiteDatabase db = getReadableDatabase();

        String today = getTodaysDate();
        String query = "SELECT * FROM "+TABLE_NOTES+" WHERE "+COL_DATE+" = '"+today+"' ;";
        return db.rawQuery(query, null);
    }

    private String getTodaysDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    }
}
