package com.example.verticalrecyclerviewurlimages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> ImgUrl = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager Manager;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImgUrl.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        ImgUrl.add("https://i.imgur.com/ZcLLrkY.jpg");
        ImgUrl.add("https://i.redd.it/obx4zydshg601.jpg");
        ImgUrl.add("https://i.redd.it/j6myfqglup501.jpg");
        ImgUrl.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        ImgUrl.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        ImgUrl.add("https://i.imgur.com/ZcLLrkY.jpg");
        ImgUrl.add("https://i.redd.it/obx4zydshg601.jpg");
        ImgUrl.add("https://i.redd.it/j6myfqglup501.jpg");
        ImgUrl.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        ImgUrl.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        ImgUrl.add("https://i.imgur.com/ZcLLrkY.jpg");
        ImgUrl.add("https://i.redd.it/obx4zydshg601.jpg");
        ImgUrl.add("https://i.redd.it/j6myfqglup501.jpg");
        ImgUrl.add("https://i.redd.it/tpsnoz5bzo501.jpg");


        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(Manager);
        adapter = new ImageAdapter(ImgUrl, this);
        recyclerView.setAdapter(adapter);

    }
}
