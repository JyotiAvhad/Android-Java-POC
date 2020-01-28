package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText first_name,last_name,marks;
    Button add_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //constructor is creating a database & table
        myDb=new DataBaseHelper(this);

        first_name=findViewById(R.id.editText_fn);
        last_name=findViewById(R.id.editText_ln);
        marks=findViewById(R.id.editText_marks);
        add_data=findViewById(R.id.btn_add);

        //called when we click on add btn
        addData();
    }

    public void addData(){
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //access DataBaseHelper method insertData using its instance
                //boolean is here to check data is inserted or not in true or false
               boolean isInserted =  myDb.insertData(first_name.getText().toString(),
                        last_name.getText().toString(),
                        marks.getText().toString());    //user entered data will be getting over here

                if (isInserted=true){
                    //data is inserted
                    Toast.makeText(MainActivity.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //data is not inserted
                    Toast.makeText(MainActivity.this,"Data not Inserted ",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
