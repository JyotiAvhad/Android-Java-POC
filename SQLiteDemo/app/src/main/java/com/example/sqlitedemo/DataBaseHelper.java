package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    //insert data method
    public boolean insertData(String fname, String lname, String marks) {

        //instance of database class (SQLiteDatabase)
        SQLiteDatabase db = this.getWritableDatabase();
        //instance of content value class
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


    //view all data method
    public Cursor getAllData() {

        //instance of database class (SQLiteDatabase)
        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor is an interface which provide random read-write access to the result
        //create an instance of cursor class
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }

    //update data method
    //same as inset operation only with the difference between insert & update is the method which we use
    public boolean updateData(String id,String fname,String lname,String marks){

        //instance of database class (SQLiteDatabase)
        SQLiteDatabase db = this.getWritableDatabase();

        //instance of content value class
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, fname);
        contentValues.put(COL_3, lname);
        contentValues.put(COL_4, marks);

        //update data in table
        db.update(TABLE_NAME,contentValues,"id = ?",new String[]{id});
        return true;
    }
}
