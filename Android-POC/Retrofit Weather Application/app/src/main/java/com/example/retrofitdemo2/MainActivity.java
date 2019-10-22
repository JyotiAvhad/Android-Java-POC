package com.example.retrofitdemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.system.Os;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TextView responseText;
    private TextView responseCoordText;

    Handler handler = new Handler();

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.city_name);
        button = findViewById(R.id.city_click);
        responseText = findViewById(R.id.response_text);
        responseCoordText = findViewById(R.id.response_coord_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchWeatherDetails();
            }
        });
    }

    private void fetchWeatherDetails() {

        Retrofit retrofit = new Retrofit.Builder()          //to convert our interface methods to a callable network request which can be executed
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())     // Converter library used to convert response into POJO
                .build();

        /*Invoke the method corresponding to the HTTP request which will return a Call object.
         *This Call object will used to send the actual network request with the specified parameters
         */
        WeatherAPIs weatherAPIs = retrofit.create(WeatherAPIs.class);

        /*This is the line which actually sends a network request.
        Calling enqueue() executes a call asynchronously.
        It has two callback listeners which will invoked on the main
        */
        Call call = weatherAPIs.getWeatherByCity(editText.getText().toString(), "04a083a810aedb844a074cd7afd4494e");
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {

                //Success Callback
                //  Log.d(TAG, "onResponse: ");

                if (response.body() != null) {
                    final WeatherResponse wResponse = (WeatherResponse) response.body();

                    responseText.setText("Temperature:  " + wResponse.getMain().getTemp() + "\n" +
                            "Minimum Temperature:  " + wResponse.getMain().getTemp_min() + "\n" +
                            "Maximum Temperature:  " + wResponse.getMain().getTemp_max() + "\n" +
                            "Pressure:  " + wResponse.getMain().getPressure() + "\n" +
                            "Humidity:  " + wResponse.getMain().getHumidity() + "\n" +
                            "Sea Level:  " + wResponse.getMain().getSea_level() + "\n" +
                            "Ground Level:  " + wResponse.getMain().getGrnd_level()
                    );
                    if (wResponse.getMain() != null) {

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                responseCoordText.setText("Longitude:  " + wResponse.getCoord().getLon() + "\n" +
                                        "Latitude:  " + wResponse.getCoord().getLat());
                            }
                        }, 1000);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                //Error Callback
                // Log.d(TAG, "onFailure: ");

                responseText.setText(t.getMessage());
            }
        });
    }

}
