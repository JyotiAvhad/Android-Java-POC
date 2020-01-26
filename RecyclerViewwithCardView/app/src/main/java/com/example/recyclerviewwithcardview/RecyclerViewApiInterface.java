package com.example.recyclerviewwithcardview;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecyclerViewApiInterface {

    public String BASE_URL = "https://reqres.in/api/";

    @GET("users?page=1")
    Call<String> getString();
}
