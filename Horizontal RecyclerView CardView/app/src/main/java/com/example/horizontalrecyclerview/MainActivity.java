package com.example.horizontalrecyclerview;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<NumberModel> imageModelArrayList;
    private NumberAdapter adapter;

    private int[] myImageList = new int[]{R.drawable.one, R.drawable.two,R.drawable.three, R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine};
    private String[] myImageNameList = new String[]{"One","Two" ,"Three","Four","Five","Six","Seven","Eight","Nine"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        imageModelArrayList = showNumbers();
        adapter = new NumberAdapter(this, imageModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    private ArrayList<NumberModel> showNumbers(){

        ArrayList<NumberModel> list = new ArrayList<>();

        for(int i = 0; i < 9; i++){
            NumberModel numberModel = new NumberModel();
            numberModel.setName(myImageNameList[i]);
            numberModel.setImage_drawable(myImageList[i]);
            list.add(numberModel);
        }

        return list;
    }

}

