package com.jkim.shrutsangam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.AdminLoginResponse;
import com.jkim.shrutsangam.api.modal.LoginResponse;
import com.jkim.shrutsangam.utils.Constant;
import com.jkim.shrutsangam.utils.SessionManager;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


// First Screen of the project

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                getLoginChoice();

            }
        },1000);

    }

    private void getLoginChoice() {

        startActivity(new Intent(this,HomePageActivity.class));
        finish();
    }


}
