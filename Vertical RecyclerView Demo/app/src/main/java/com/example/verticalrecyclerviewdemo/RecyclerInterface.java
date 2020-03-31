package com.example.verticalrecyclerviewdemo;

import retrofit2.Call;
import retrofit2.http.GET;

interface RecyclerInterface {
    String JSONURL = "https://demonuts.com/Demonuts/JsonTest/Tennis/";

    @GET("json_parsing.php")
    Call<String> getString();
}
