package com.example.recyclerviewwithcardview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Context c;
    public Dialog d;
    RecyclerViewModel model;
    EditText fname, lname, email;
    Button save, cancel, delete;

    public CustomDialogClass(Context c, RecyclerViewModel model) {
        super(c);
        this.c = c;
        this.model = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);
        cancel = findViewById(R.id.cancel);

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);

        fname.setText(model.getFirst_name());
        lname.setText(model.getLast_name());
        email.setText(model.getEmail());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.save:
                model.setFirst_name(fname.getText().toString());
                model.setLast_name(lname.getText().toString());
                model.setEmail(email.getText().toString());
                new UserDatabase(c).updateContact(model);
                dismiss();
                break;

            case R.id.cancel:
                dismiss();
                break;

            case R.id.delete:
                new UserDatabase(c).deleteUser(model.getId());
                dismiss();
                break;
        }
    }
}