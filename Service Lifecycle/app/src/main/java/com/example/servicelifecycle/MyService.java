package com.example.servicelifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = "MyService";

    public MyService() {
    }

    //IBinder is an interface, hence its objects cannot be created
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand: thread id : "+Thread.currentThread().getId());

        Toast.makeText(MyService.this,"Service Started",Toast.LENGTH_SHORT).show();

//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(MyService.this,"Service Destroyed",Toast.LENGTH_SHORT).show();
    }
}
