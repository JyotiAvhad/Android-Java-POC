package com.example.sqlitedbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instance for the DBAdapter class
        DBAdapter dbAdapter=new DBAdapter(this);

       /* dbAdapter.open();
        if (dbAdapter.updateContact(1,"Eshvar Mali","eshvar289@hotmail.com")){
            Toast.makeText(this,"Update Successfully.",Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this,"Update failed",Toast.LENGTH_LONG).show();

        dbAdapter.close();*/

        //Deleting the contact from a table
        dbAdapter.open();
        if (dbAdapter.deleteContact(1))
            Toast.makeText(this,"Contact Deleted Successfully",Toast.LENGTH_LONG).show();

        else
            Toast.makeText(this,"Deletion unsuccesfull",Toast.LENGTH_LONG).show();

        dbAdapter.close();

    }
}
