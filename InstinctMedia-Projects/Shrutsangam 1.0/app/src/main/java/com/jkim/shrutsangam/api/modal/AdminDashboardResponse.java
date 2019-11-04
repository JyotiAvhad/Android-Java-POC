package com.jkim.shrutsangam.api.modal;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminDashboardResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Datum> data;

    public class Datum {
        @SerializedName("web_admin")
        public JsonObject web_admin;
        @SerializedName("bhnadar_master")
        public JsonObject bhnadar_master;

        public JsonObject getWeb_admin() {
            return web_admin;
        }

        public void setWeb_admin(JsonObject web_admin) {
            this.web_admin = web_admin;
        }

        public JsonObject getBhnadar_master() {
            return bhnadar_master;
        }

        public void setBhnadar_master(JsonObject bhnadar_master) {
            this.bhnadar_master = bhnadar_master;
        }
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
