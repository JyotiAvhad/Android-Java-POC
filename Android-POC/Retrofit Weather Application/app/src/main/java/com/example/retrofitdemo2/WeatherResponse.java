package com.example.retrofitdemo2;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("main")     //this annotation maps the given variable to the respective key in JSON response
    private Main main;

    @SerializedName("name")
    private String name;

    @SerializedName("coord")
    private Coordinates coord;


    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoord() {
        return coord;
    }
}
