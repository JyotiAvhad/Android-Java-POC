package com.example.service_playsong;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    MediaPlayer mediaPlayer;
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //audio file
        mediaPlayer=MediaPlayer.create(this,R.raw.iphone);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaPlayer.start();

        Toast.makeText(MyService.this,"Service Started - Playing Song",Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();

        Toast.makeText(MyService.this,"Service Destroyed - Stopped Song",Toast.LENGTH_SHORT).show();
    }
}
