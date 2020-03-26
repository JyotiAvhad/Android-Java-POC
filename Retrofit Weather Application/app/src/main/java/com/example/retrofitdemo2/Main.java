package com.example.retrofitdemo2;

import com.google.gson.annotations.SerializedName;

public class Main {          //this class is with respect to the “main” key JSON Object in the response

    @SerializedName("temp")
    private Double temp;

    @SerializedName("temp_min")
    private Double temp_min;

    @SerializedName("temp_max")
    private Double temp_max;

    @SerializedName("pressure")
    private Double pressure;

    @SerializedName("humidity")
    private Double humidity;

    @SerializedName("sea_level")
    private Double sea_level;

    @SerializedName("grnd_level")
    private Double grnd_level;

    public Double getTemp() {
        return temp;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getSea_level() {
        return sea_level;
    }

    public Double getGrnd_level() {
        return grnd_level;
    }
}
