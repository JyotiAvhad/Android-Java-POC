package com.example.retrofitdemo2;

import com.google.gson.annotations.SerializedName;

public class Coordinates {

    @SerializedName("lon")
    private Double lon;

    @SerializedName("lat")
    private Double lat;


    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }
}
