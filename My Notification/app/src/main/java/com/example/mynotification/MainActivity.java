package com.example.mynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    //1. Notification Channel
    //2. Notification Builder
    //3. Notification Manager

    private static final String TAG = "MainActivity";

    private static final String Channel_Id = "abcd";
    private static final String Channel_Name = "My Notification";
    private static final String Channel_Desc = "Its a Notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(Channel_Id, Channel_Name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(Channel_Desc);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Button btn_notify = findViewById(R.id.btn_notifyMe);

        btn_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showNotification();
            }
        });
    }

    private void showNotification() {

        //create object of NotificationManager
        NotificationCompat.Builder notifyBuilder;

        notifyBuilder = new NotificationCompat.Builder(this, Channel_Id)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("My Notification....!")
                .setContentText("Hey you are getting some new notification :-)")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManagerCompat =NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, notifyBuilder.build());
    }
}
