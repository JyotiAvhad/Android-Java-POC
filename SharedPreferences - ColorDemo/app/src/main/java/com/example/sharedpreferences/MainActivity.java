package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RelativeLayout relativeLayout;
    int bgColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup=findViewById(R.id.radioGroup);
        relativeLayout=findViewById(R.id.relativeLayout);

        SharedPreferences preferences=getApplicationContext().getSharedPreferences("MyPref",0);

        final SharedPreferences.Editor editor=preferences.edit();

        bgColor=preferences.getInt("backgroundcolor",0);

        relativeLayout.setBackgroundColor(bgColor);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.white:
                        bgColor=getResources().getColor(R.color.colorwhite);
                        relativeLayout.setBackgroundColor(bgColor);
                        break;

                    case R.id.blue:
                        bgColor=getResources().getColor(R.color.colorBlue);
                        relativeLayout.setBackgroundColor(bgColor);
                        break;

                    case R.id.yellow:
                        bgColor=getResources().getColor(R.color.colorYellow);
                        relativeLayout.setBackgroundColor(bgColor);
                        break;
                }

                editor.putInt("backgroundcolor",bgColor);
                editor.commit();
            }
        });
    }
}
