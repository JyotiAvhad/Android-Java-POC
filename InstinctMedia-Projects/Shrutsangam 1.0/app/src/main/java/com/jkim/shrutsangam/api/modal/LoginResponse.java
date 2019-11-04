package com.jkim.shrutsangam.api.modal;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Datum> data;

    public class Datum {
        @SerializedName("id")
        public String id;
        @SerializedName("bhandar_id")
        public String bhandar_id;
        @SerializedName("username")
        public String username;
        @SerializedName("full_name")
        public String full_name;
        @SerializedName("email")
        public String email;
        @SerializedName("mobile")
        public String mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
