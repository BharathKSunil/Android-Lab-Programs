package com.bharathksunilk.employeedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MyDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME="MyCompany";
    private static final int DB_VERSION=1;

    static final String TABLE_EMPLOYEE="Employee",
            COL_ID="id",
            COL_NAME="name",
            COL_AGE="age",
            COL_ADDRESS="address";

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+TABLE_EMPLOYEE+" ( " +
                COL_ID+" TEXT," +
                COL_NAME+" TEXT," +
                COL_AGE+" TEXT," +
                COL_ADDRESS+" TEXT" +
                ")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * This function finds an employee by ID
     * @param id the id of the employee
     * @return the employee details
     */
    String getEmployeeById(String id){
        //create the select query
        String query="SELECT * FROM "+ TABLE_EMPLOYEE +
                " WHERE "+ COL_ID + " = '" + id + "'; ";

        String result="No Employee found with ID: "+id;

        //get readable database connection
        SQLiteDatabase db=getReadableDatabase();
        //run the query to get a cursor to the result
        Cursor cursor=db.rawQuery(query, null);
        //check if the table returned an entry
        if (cursor.moveToNext()){
            result="ID: "+cursor.getString(0)+"\n" +
                    "Name: "+cursor.getString(1)+"\n"+
                    "Age: "+cursor.getString(2)+"\n"+
                    "Address: "+cursor.getString(3);
        }
        cursor.close();
        db.close();
        return result;
    }

    /**
     * This function adds the employee to the database
     * @param id the id of employee
     * @param name name of employee
     * @param age age of employee
     * @param address address of employee
     */
    void addEmployee(String id, String name, String age, String address){
        //get a writable connection to the database
        SQLiteDatabase db = getWritableDatabase();
        //content values are a safe way to insert to the database
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_NAME, name);
        values.put(COL_AGE, age);
        values.put(COL_ADDRESS, address);
        //insert the data to table
        db.insert(
                TABLE_EMPLOYEE, //the name of the table
                null, //Column Hack
                values //the content values
        );
    }
}
