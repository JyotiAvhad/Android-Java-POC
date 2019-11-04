package com.jkim.shrutsangam.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.jkim.shrutsangam.activity.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "session";

    //region loginSession
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_ID = "id";
    public static final String KEY_BHANDER_ID = "bhandar_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_FULLNAME = "fullname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MOBILE = "mobile";
    //endregion

    //region admin login session
    private static final String IS_ADMIN_LOGIN = "is_adminlogin";
    public static final String KEY_ADMIN_ID = "admin_ID";
    public static final String KEY_ADMIN_USERNAME = "admin_username";
    //endregion

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String id, String bhanderId, String username, String fullname, String email, String mobile) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_BHANDER_ID, bhanderId);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_FULLNAME, fullname);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_MOBILE, mobile);
        editor.commit();
    }

    public void createAdminLoginSession(String id, String adminName) {
        editor.putBoolean(IS_ADMIN_LOGIN, true);
        editor.putString(KEY_ADMIN_ID, id);
        editor.putString(KEY_ADMIN_USERNAME, adminName);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_ID, pref.getString(KEY_ID, null));
        user.put(KEY_BHANDER_ID, pref.getString(KEY_BHANDER_ID, null));
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        user.put(KEY_FULLNAME, pref.getString(KEY_FULLNAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_MOBILE, pref.getString(KEY_MOBILE, null));
        return user;
    }

    public HashMap<String, String> getAdminDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_ADMIN_ID, pref.getString(KEY_ADMIN_ID, null));
        user.put(KEY_ADMIN_USERNAME, pref.getString(KEY_ADMIN_USERNAME, null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public boolean isAdminLoggedIn() {
        return pref.getBoolean(IS_ADMIN_LOGIN, false);
    }
}
