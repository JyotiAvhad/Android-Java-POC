package com.example.boundservice_randomnumbergenerator;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyService extends Service {

    // Binder given to clients
    //to create our binder interface and initialize this using our local binder class
    private final IBinder iBinder = new LocalBinder();

    //random number generator
    private final Random number = new Random();

//    public MyService() {
//    }

    //create the bound service by extending the binder class
    public class LocalBinder extends Binder {

        //to return an instance of MyService class
        MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        //return our IBinder instance from onBind method
        return iBinder;
    }

    //method for clients
    //return random number in range of 0 to 500
    public int getRandomNumber() {
        return number.nextInt(100);
    }

}
