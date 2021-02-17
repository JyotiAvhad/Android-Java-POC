package com.example.lottieplayground;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends Activity {

    LottieAnimationView thumb_up;
    LottieAnimationView thumb_down;
    LottieAnimationView toggle;
    int flag = 0;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumb_up = findViewById(R.id.lav_thumbUp);
        thumb_down = findViewById(R.id.lav_thumbDown);
        toggle = findViewById(R.id.lav_toggle);

        Log.d(TAG, "onCreate: u r in main activity");
//        thumb_up.setOnClickListener(this);
//        thumb_down.setOnClickListener(this);

//        thumb_down.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "down!!", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        thumb_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(MainActivity.this, "up!!", Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        thumb_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                thumb_down.setProgress(0);
//                thumb_down.pauseAnimation();
//                thumb_up.playAnimation();
//                Toast.makeText(MainActivity.this, "Cheers!!", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        thumb_down.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                thumb_up.setProgress(0);
//                thumb_up.pauseAnimation();
//                thumb_down.playAnimation();
//                Toast.makeText(MainActivity.this, "Boo!!", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        toggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                changeState();
//                Toast.makeText(MainActivity.this, "CheersBoo!!", Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

//    private void changeState() {
//
//        if (flag == 0) {
//            try {
//
//                toggle.setMinAndMaxProgress(0f, 0.43f);
//                toggle.playAnimation();
//                flag = 1;
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//
//        } else {
//
//            toggle.setMinAndMaxProgress(0.5f, 1f);
//            toggle.playAnimation();
//            flag = 0;
//
//        }
//    }


}