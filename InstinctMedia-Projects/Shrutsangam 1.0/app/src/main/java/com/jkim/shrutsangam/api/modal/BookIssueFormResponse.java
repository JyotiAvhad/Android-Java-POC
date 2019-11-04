package com.jkim.shrutsangam.api.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookIssueFormResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Datum> data;

    public class Datum {
        @SerializedName("data")
        public List<listDataResponse> data;

        public List<listDataResponse> getData() {
            return data;
        }

        public void setData(List<listDataResponse> data) {
            this.data = data;
        }
    }

    public class listDataResponse {
        @SerializedName("msg")
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
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
