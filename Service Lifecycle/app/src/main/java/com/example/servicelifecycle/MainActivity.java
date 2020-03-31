package com.example.servicelifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button startSer, stopSer;
    Intent intent;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: thread id :"+Thread.currentThread().getId());

        startSer = findViewById(R.id.btn_startService);
        stopSer = findViewById(R.id.btn_stopService);

        startSer.setOnClickListener(this);
        stopSer.setOnClickListener(this);

        intent = new Intent(getBaseContext(), MyService.class);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_startService:
                startService(intent);
                break;

            case R.id.btn_stopService:
                stopService(intent);
                break;
        }
    }
}
