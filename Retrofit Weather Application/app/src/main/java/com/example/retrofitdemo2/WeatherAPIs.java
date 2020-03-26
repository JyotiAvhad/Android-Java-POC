package com.example.retrofitdemo2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPIs {

    /*
     * All the methods should be returning Call object
     * which is executed to send the network request and return the response
     * */

    @GET("data/2.5/weather")
    Call<WeatherResponse> getWeatherByCity(@Query("q") String city,
                                           @Query("appid") String apiKey);

    /*@Query - This annotation represents any query key value pair
     * to be sent along with the network request
     * */


}
