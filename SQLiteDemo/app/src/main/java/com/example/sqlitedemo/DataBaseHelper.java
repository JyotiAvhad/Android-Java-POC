package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    //database name variable
    public static final String DATABASE_NAME = "stud.db";

    //table name variable
    public static final String TABLE_NAME = "stud_table";

    //database column variable
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FirstName";
    public static final String COL_3 = "LastName";
    public static final String COL_4 = "Marks";

    //default constructor
    public DataBaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 1);
        //create instance of SQLiteDatabase
//        SQLiteDatabase db = this.getWritableDatabase(); //create table & database just for checking
    }

    //SQLiteOpenHelper Class default method-1
    @Override
    public void onCreate(SQLiteDatabase db) {

        //execute the query which you pass in this method as an argument it takes string query
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT,LASTNAME TEXT,MARKS INT)");
    }

    //SQLiteOpenHelper Class default method-2
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //again create the table & Database
        onCreate(db);
    }

    //insert method
    public boolean insertData(String fname, String lname, String marks) {

        SQLiteDatabase db = this.getWritableDatabase();
        //instance of contentvalue class
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, fname);
        contentValues.put(COL_3, lname);
        contentValues.put(COL_4, marks);

        //insert data in table
        long result = db.insert(TABLE_NAME, null, contentValues);//insert method return -1 or 1 as an result

        if (result == -1)
            return false;
        else
            return true;
    }

}
