package com.example.boundservice_randomnumbergenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
* Bound Service Demo App - By extending Binder Class
*/
public class MainActivity extends AppCompatActivity {

    MyService myService;
    //to check our service is bound or not
    boolean isBound = false;

    TextView tv_number;
    Button btn_getNumber;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_number = findViewById(R.id.tv_number);
        btn_getNumber = findViewById(R.id.btn_getNum);

    }

    @Override
    protected void onStart() {
        super.onStart();

        //Bind MyService
        //create intent for creating our service
        Intent intent = new Intent(this, MyService.class);

        //create a bound service
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();

        // Unbind from the service
        if (isBound) {

            unbindService(serviceConnection);
            isBound = false;
        }
    }

    public void onButtonClick(View view) {

        if (isBound) {

            int num = myService.getRandomNumber();
            Toast.makeText(MainActivity.this, "Random Number is : " + num, Toast.LENGTH_SHORT).show();

            //  tv_number.setText(num);

            Log.d(TAG, "onClick: " + num);
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //create our Local binder object and cast it local binder with the service instance
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService = binder.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;
        }
    };
}
