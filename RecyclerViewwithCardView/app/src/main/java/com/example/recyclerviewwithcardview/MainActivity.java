package com.example.recyclerviewwithcardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        fetchRetrofitData();

    }

    private void fetchRetrofitData() {

        //retrofit api calling

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecyclerViewApiInterface.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RecyclerViewApiInterface apiInterface = retrofit.create(RecyclerViewApiInterface.class);

        Call<String> call = apiInterface.getString();

        //calling retrofit method
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                //on response data
                if (response.isSuccessful()){

                    if (response.body()!=null){
                        
                        Log.d("onResponse: ",response.body());

                        String data=response.body();
                        getListData(data);
                    }
                    else {

                        Log.d("onEmptyResponse", "Returned empty response");

//                        Toast.makeText(context,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {

                Log.d("onEmptyResponse", "Something Went Wrong");
                Toast.makeText(context,"Something Went Wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getListData(String data) {

        try{
            //getting data from response
            JSONObject object =new JSONObject(data);

            if (object.optString("status").equals("true")){

                ArrayList<RecyclerViewModel> modelRecyclerArrayList = new ArrayList<>();
                JSONArray dataArray  = object.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {

                    RecyclerViewModel modelRecycler = new RecyclerViewModel();
                    JSONObject jsonObject = dataArray.getJSONObject(i);

                    modelRecycler.setId(jsonObject.getInt("id"));
                    modelRecycler.setFirst_name(jsonObject.getString("firstname"));
                    modelRecycler.setLast_name(jsonObject.getString("lastname"));
                    modelRecycler.setEmail(jsonObject.getString("email"));
                    modelRecycler.setAvatar(jsonObject.getString("imgURL"));

                    modelRecyclerArrayList.add(modelRecycler);

                }

                recyclerViewAdapter = new RecyclerViewAdapter(this,modelRecyclerArrayList);
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

            }else {
                Toast.makeText(MainActivity.this, object.optString("Sorry :-( Something Went Wrong")+"", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
