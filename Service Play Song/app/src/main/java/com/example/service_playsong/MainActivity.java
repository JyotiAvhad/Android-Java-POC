package com.example.service_playsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button start_song, stop_song;
    Intent intent;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_song = findViewById(R.id.btn_startService);
        stop_song = findViewById(R.id.btn_stopService);

        start_song.setOnClickListener(this);
        stop_song.setOnClickListener(this);

        intent = new Intent(getApplicationContext(), MyService.class);

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
