package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText first_name, last_name, marks;
    Button add_data, view_all_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //constructor is creating a database & table
        myDb = new DataBaseHelper(this);

        first_name = findViewById(R.id.editText_fn);
        last_name = findViewById(R.id.editText_ln);
        marks = findViewById(R.id.editText_marks);
        add_data = findViewById(R.id.btn_add);
        view_all_data = findViewById(R.id.btn_viewall);

        //called when we click on add btn
        addData();
        //called when we click on view all btn
        viewAllData();
    }

    public void addData() {
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //access DataBaseHelper method insertData using its instance
                //boolean is here to check data is inserted or not in true or false
                boolean isInserted = myDb.insertData(first_name.getText().toString(),
                        last_name.getText().toString(),
                        marks.getText().toString());    //user entered data will be getting over here

                if (isInserted = true) {
                    //data is inserted
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    //data is not inserted
                    Toast.makeText(MainActivity.this, "Data not Inserted ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void viewAllData() {
        view_all_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get all data using method declare in DataBaseHelper class
                Cursor result = myDb.getAllData();

                if (result.getCount() == 0) {

                    //no data available in database
//                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    showMessage("Error","No Data Found");
                    return;
                }

                //data available in database & display it
                StringBuffer stringBuffer = new StringBuffer();
                //get all data one by one
                while (result.moveToNext()) {

                    //get result & store it in buffer
                    stringBuffer.append("ID : " + result.getString(0) + "\n");
                    stringBuffer.append("First Name : " + result.getString(1) + "\n");
                    stringBuffer.append("Last Name : " + result.getString(2) + "\n");
                    stringBuffer.append("Marks : " + result.getString(3) + "\n\n\n");
                }

                //show all data
//                Toast.makeText(MainActivity.this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
                showMessage("Data",stringBuffer.toString());
            }
        });
    }

    public void showMessage(String title, String msg) {
        //create dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);    //when we want to cancel
        builder.setTitle(title);    //title
        builder.setMessage(msg);        //msg
        builder.show();     //show
    }
}
