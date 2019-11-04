package com.jkim.shrutsangam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.AdminLoginResponse;
import com.jkim.shrutsangam.api.modal.LoginResponse;
import com.jkim.shrutsangam.utils.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MemberLoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MemberLoginActivity.class.getSimpleName();
   // private EditTwext etMobileNum, etPassord;
    private EditText etUsername, etPassword;
    private TextView txtError;
    private RelativeLayout rlLoader;
    private CardView cardLogin, cardAdminLogin;
    private SessionManager sessionManager;
    private String id, bhandarId, username, fullname, email, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        sessionManager = new SessionManager(this);

        init();
    }

    private void init() {

        txtError = findViewById(R.id.txt_error);
        etUsername = findViewById(R.id.et_username);
        //etMobileNum = findViewById(R.id.et_mobile_number);
        etPassword = findViewById(R.id.et_password);
        rlLoader = findViewById(R.id.rl_loader);
        cardLogin = findViewById(R.id.card_login);
        cardAdminLogin = findViewById(R.id.card_admin_login);

        cardLogin.setOnClickListener(this);
        cardAdminLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_login:
                if (etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
                    txtError.setVisibility(View.VISIBLE);
                    txtError.setText("Please Enter Valid Credentials ...");
                }

                else
                {
                    Log.d(TAG, "username:"+etUsername.getText().toString());
                    Log.d(TAG, "Password"+etPassword.getText().toString());

                    login();
                }
                break;
            case R.id.card_admin_login:
                if (etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
                    txtError.setVisibility(View.VISIBLE);
                    txtError.setText("Please Enter Valid Credentials ...");
                }
                else adminLogin();
                break;
        }
    }

    private void login() {
        showLoader(false, true, "visible");
//        Log.d(TAG, "login: *******************************************");
        //api calling
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        //get login credentials
        Call<LoginResponse> call = apiService.login(etUsername.getText().toString(), etPassword.getText().toString());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("TAG", "onResponse: " + response.body().getMessage());
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {

                        LoginResponse loginResponse = response.body();
                        List<LoginResponse.Datum> datumList = loginResponse.getData();
                        for (LoginResponse.Datum datum : datumList) {
                            Log.e(TAG, "onResponse: " + datum.username);
                            id = datum.id;
                            username = datum.username;
                            fullname = datum.full_name;
                            email = datum.email;
                            mobile = datum.mobile;
                            bhandarId = datum.bhandar_id;
                        }
                        sessionManager.createLoginSession(id, bhandarId, username, fullname, email, mobile);
                        startActivity(new Intent(MemberLoginActivity.this, DashboardActivity.class));
                        finish();
                    } else
                        Toast.makeText(MemberLoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MemberLoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                showLoader(true, false, "gone");
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                showLoader(true, false, "gone");
            }
        });
    }

    private void adminLogin() {
        showLoader(false, true, "visible");
        //api calling
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AdminLoginResponse> call = apiService.adminLogin(etUsername.getText().toString(), etPassword.getText().toString());
        call.enqueue(new Callback<AdminLoginResponse>() {
            @Override
            public void onResponse(Call<AdminLoginResponse> call, Response<AdminLoginResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        AdminLoginResponse adminLoginResponse = response.body();
                        List<AdminLoginResponse.Datum> datumList = adminLoginResponse.getData();
                        for (AdminLoginResponse.Datum datum : datumList) {
                            Log.e(TAG, "onResponse: " + datum.uname);
                            sessionManager.createAdminLoginSession(datum.id, datum.uname);
                        }
                        startActivity(new Intent(MemberLoginActivity.this, AdminDashboardActivity.class));
                        finish();
                    } else
                        Toast.makeText(MemberLoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MemberLoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                showLoader(true, false, "gone");
            }

            @Override
            public void onFailure(Call<AdminLoginResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                showLoader(true, false, "gone");
            }
        });
    }

    private void showLoader(boolean maincontent, boolean loader, String status) {
//        svMaincontent.setClickable(maincontent);
        rlLoader.setClickable(loader);
        if (status.equals("visible")) {
            rlLoader.setVisibility(View.VISIBLE);
        } else rlLoader.setVisibility(View.GONE);
    }

}
