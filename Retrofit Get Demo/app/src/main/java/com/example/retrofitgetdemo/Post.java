package com.example.retrofitgetdemo;

import com.google.gson.annotations.SerializedName;

public class Post { //model class

    private int userId;
    private int id;
    private String title;

    @SerializedName("body")    //annotation is needed for Gson to map the JSON keys with our fields
    private String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
