package com.example.retrofitgetdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")   //endpoints are defined using annotations to encode details about the parameters and request method
    Call<List<Post>> getPosts();    //return value is always a parameterized Call<T> object
}
