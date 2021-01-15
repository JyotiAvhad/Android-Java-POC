package com.example.staticbroadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private static final String ACTION_SHOW_TOAST = "Hieeeeeee";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


//        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
//
//            Toast.makeText(context, "Boot completed", Toast.LENGTH_SHORT).show();
//        }
//
//        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
//
//            Toast.makeText(context, "Connectivity changed", Toast.LENGTH_SHORT).show();
//        }



        if (intent.getAction().equals(ACTION_SHOW_TOAST)) {
            CharSequence text = "Broadcast Received!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


    }
}
