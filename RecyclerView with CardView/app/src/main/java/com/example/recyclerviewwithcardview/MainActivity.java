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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    Context context;
    List<RecyclerViewModel> modelRecyclerArrayList = new ArrayList<>();
    UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        if (null == db){
            db = new UserDatabase(this);
            db.getWritableDatabase();
        }

        if (db.getAllContacts().size() > 0){
            fetchDataFromDb();
        } else {
            fetchRetrofitData();
        }
    }

    private void fetchDataFromDb() {

        if (null == db){
            db = new UserDatabase(this);
            db.getWritableDatabase();
        }

        modelRecyclerArrayList = db.getAllContacts();
        recyclerViewAdapter = new RecyclerViewAdapter(this,modelRecyclerArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
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
                        setListData(data);
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

    private void setListData(String data) {

        try{
            //getting data from response
            JSONObject object = new JSONObject(data);

            //if (object.optString("status").equals("true")){


                JSONArray dataArray  = object.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject jsonObject = dataArray.getJSONObject(i);

                    RecyclerViewModel modelRecycler = new RecyclerViewModel();
                    if (jsonObject.has("id"))  modelRecycler.setId(jsonObject.getInt("id"));
                    if (jsonObject.has("first_name"))  modelRecycler.setFirst_name(jsonObject.getString("first_name"));
                    if (jsonObject.has("last_name"))  modelRecycler.setLast_name(jsonObject.getString("last_name"));
                    if (jsonObject.has("email"))  modelRecycler.setEmail(jsonObject.getString("email"));
                    if (jsonObject.has("avatar"))  modelRecycler.setAvatar(jsonObject.getString("avatar"));
                    modelRecyclerArrayList.add(modelRecycler);

                    insertUserDataToDB(modelRecycler);
                }

                recyclerViewAdapter = new RecyclerViewAdapter(this,modelRecyclerArrayList);
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

            /*}else {
                Toast.makeText(MainActivity.this, object.optString("Sorry :-( Something Went Wrong")+"", Toast.LENGTH_SHORT).show();
            }*/

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void insertUserDataToDB(RecyclerViewModel model) {
        UserDatabase userDatabase = new UserDatabase(this);
        userDatabase.insertContact(model);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        recyclerView.notify();
    }
}
