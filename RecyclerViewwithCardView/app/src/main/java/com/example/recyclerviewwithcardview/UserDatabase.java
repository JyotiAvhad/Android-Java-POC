package com.example.recyclerviewwithcardview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "users_db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_USERID = "userid";
    public static final String ID = "_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_AVATAR = "avatar";

    public UserDatabase(Context context){

        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_USERID + " INTEGER," + COLUMN_FIRST_NAME + " TEXT,"
                + COLUMN_LAST_NAME + " TEXT," + COLUMN_EMAIL + " TEXT,"
                + COLUMN_AVATAR + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void insertContact (RecyclerViewModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERID, model.getId());
        contentValues.put(COLUMN_FIRST_NAME, model.getFirst_name());
        contentValues.put(COLUMN_LAST_NAME, model.getLast_name());
        contentValues.put(COLUMN_EMAIL, model.getEmail());
        contentValues.put(COLUMN_AVATAR, model.getAvatar());
        db.insert(TABLE_NAME, null, contentValues);
    }

    public RecyclerViewModel getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from contacts where id="+id+"", null );
        try
        {
            int idIndex = cursor.getInt(0);
            String fname = cursor.getString(1);
            String lname = cursor.getString(2);
            String email = cursor.getString(3);
            String avatar = cursor.getString(4);

            return new RecyclerViewModel(idIndex, fname, lname, email, avatar);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public int updateContact(RecyclerViewModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERID, model.getId());
        values.put(COLUMN_FIRST_NAME, model.getFirst_name());
        values.put(COLUMN_LAST_NAME, model.getLast_name());
        values.put(COLUMN_EMAIL, model.getEmail());
        values.put(COLUMN_AVATAR, model.getAvatar());

        // updating row
        return db.update(TABLE_NAME, values, COLUMN_USERID + " = ?",
                new String[] { String.valueOf(model.getId()) });
    }

    public List<RecyclerViewModel> getAllContacts() {
        List<RecyclerViewModel> users = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecyclerViewModel model = new RecyclerViewModel();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setFirst_name(cursor.getString(1));
                model.setLast_name(cursor.getString(2));
                model.setEmail(cursor.getString(3));
                model.setAvatar(cursor.getString(4));
                // Adding contact to list
                users.add(model);
            } while (cursor.moveToNext());
        }

        // return contact list
        return users;
    }
    public void deleteUser(int id) {

        // Deletes a row given its rowId, but I want to be able to pass
        // in the name of the KEY_NAME and have it delete that row.
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_USERID + "=" + id, null);
    }
}
