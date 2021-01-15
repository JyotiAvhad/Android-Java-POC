package com.example.broadcastreceiverdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "numberDb";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "tag";

//    private static final String CREATE = "create table " + DbContract.TABLE_NAME +
//            "(id integer primary key autoincrement," + DbContract.INCOMING_NUMBER + "text);";
//
//    private static final String DROP_TABLE = "drop table if exists "+DbContract.TABLE_NAME;


    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + DbContract.TABLE_NAME +
                "(id integer primary key autoincrement," + DbContract.INCOMING_NUMBER + "text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("drop table if exists " + DbContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void saveNumber(String number, SQLiteDatabase database) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.INCOMING_NUMBER, number);

        Log.d(TAG, "incoming number: " + DbContract.INCOMING_NUMBER);

        database.insert(DbContract.TABLE_NAME, null, contentValues);
    }

    public Cursor readNumber(SQLiteDatabase database) {

        String[] projection = {"id", DbContract.INCOMING_NUMBER};

        Cursor cursor = database.query(DbContract.TABLE_NAME, projection, null, null, null, null, null);
        return cursor;

    }
}
