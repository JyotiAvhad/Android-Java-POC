package com.example.sqlitedbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    //database column constants
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "_name";
    static final String KEY_EMAIL = "_email";
    static final String TAG="DBAdapter";

    static final String DATABASE_NAME = "MyDatabase";
    static final String DATABASE_TABLE = "contacts";
    static final int DATABASE_VERSION = 1;

    //database creation query
    static final String DATABASE_CREATE = "create table contacts " +
            "("+KEY_ROWID+" integer primary key autoincrement,"+
            KEY_NAME+" text not null,"+KEY_EMAIL+" text not null);";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context context) {
        this.context = context;
        DBHelper=new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME,null , DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG,"Upgrading database from version "+oldVersion+" to "+
                    newVersion+" which will destroy all old data");

            db.execSQL("drop table if exists contacts");

            onCreate(db);
        }
    }

    //open the database
    public DBAdapter open() throws SQLException{
        db=DBHelper.getWritableDatabase();
        return  this;
    }

    //closes the database
    public void close(){
        DBHelper.close();
    }

    //insert a contact into the database
    public long insertContact(String name,String email){
        ContentValues initialValues=new ContentValues();
        initialValues.put(KEY_NAME,name);
        initialValues.put(KEY_EMAIL,email);

        return db.insert(DATABASE_TABLE,null,initialValues);
    }

    //delete a particular contact
    public boolean deleteContact(long rowId){
        return db.delete(DATABASE_TABLE,KEY_ROWID+"="+rowId,null)>0;
    }

    //retrieve all the contacts
    public Cursor getAllContacts(){
        return  db.query(DATABASE_TABLE,new String[] {KEY_ROWID,KEY_NAME,KEY_EMAIL},null,null,null,null,null);
        }

    //retrieve a particular contact
    public Cursor getContact(long rowId) throws SQLException {
        Cursor mCursor=db.query(true,DATABASE_TABLE,new String[]{KEY_ROWID,KEY_NAME,KEY_EMAIL},KEY_ROWID+"="+rowId,null,null,null,null,null);
        if (mCursor != null)
            mCursor.moveToFirst();

        return mCursor;
    }

    //updates a contact
    public boolean updateContact(long rowId,String name,String email){
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_EMAIL,email);

        return db.update(DATABASE_TABLE,contentValues,KEY_ROWID+"="+rowId,null)>0;
    }

}
