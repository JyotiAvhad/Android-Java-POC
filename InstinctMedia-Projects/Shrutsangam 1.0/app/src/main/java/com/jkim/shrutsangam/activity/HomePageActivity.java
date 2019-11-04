package com.jkim.shrutsangam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.utils.SessionManager;

public class HomePageActivity extends AppCompatActivity {


    private static final String TAG = "Tag";
    private SessionManager sessionManager;

    CardView libraryLogin, memberLogin, adminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_homepg);

        libraryLogin=findViewById(R.id.library_login);
        memberLogin=findViewById(R.id.member_login);

//        sessionManager = new SessionManager(this);
//        getSessionLoginData();

        libraryLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePageActivity.this,LoginActivity.class));

            }
        });

        memberLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePageActivity.this,MemberLoginActivity.class));

            }
        });

    }

    private void getSessionLoginData() {

        Log.e(TAG, "getSessionLoginData: " + sessionManager.isLoggedIn());


        if (!sessionManager.isLoggedIn()) {
            if (sessionManager.isAdminLoggedIn()) {
                startActivity(new Intent(this, AdminDashboardActivity.class));
                finish();
            } else {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        } else {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }


    }
}
