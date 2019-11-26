package com.example.retrofitgetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()  //To send network requests to an API
                .baseUrl("https://jsonplaceholder.typicode.com/")   //specify the base URL for the service
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        //Calls may be executed synchronously with execute(), or asynchronously with enqueue().
        call.enqueue(new Callback<List<Post>>() {

            //onResponse() method is called for a response that can be correctly handled even if the server returns an error message
            //if you get a status code of 404 or 500, this method will still be called
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response)
            {

                if (!response.isSuccessful())   ////isSuccessful() to find out if the status code is in the range 200-300, indicating success
                {
                    tvResult.setText("Code: " + response.code());   //To get the status code for to handle situations
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    tvResult.append(content);
                }

            }

            //onFailure() invoked when a network exception occurred communicating to the server or
            //when an unexpected exception occurred handling the request or processing the response.
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                tvResult.setText(t.getMessage());

            }
        });
    }
}
