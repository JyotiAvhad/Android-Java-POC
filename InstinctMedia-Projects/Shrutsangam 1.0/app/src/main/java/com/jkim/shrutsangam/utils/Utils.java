package com.jkim.shrutsangam.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.utils.interfaces.OnDialogActionClick;


public class Utils {

    public void alertDialog(String msg, String title, Context context, final OnDialogActionClick onDialogActionClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //Setting message manually and performing action on button click
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onDialogActionClick.onDialogYes();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        onDialogActionClick.onDialogNo();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle(title);
        alert.show();
    }
}
