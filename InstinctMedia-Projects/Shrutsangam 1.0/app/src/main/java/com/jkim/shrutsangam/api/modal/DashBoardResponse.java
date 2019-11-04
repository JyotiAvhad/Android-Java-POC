package com.jkim.shrutsangam.api.modal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class DashBoardResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Datum> data;

    public class Datum {
        @SerializedName("bhandar_data")
        public JsonObject bhandar_data;
        @SerializedName("book_entry")
        public String book_entry;
        @SerializedName("member")
        public String member;
        @SerializedName("total_book_issue")
        public String total_book_issue;
        @SerializedName("total_book_receive")
        public String total_book_receive;
        @SerializedName("outstanding")
        public String outstanding;
        @SerializedName("total_book_issue_list")
        public JsonArray jsonArrayIssueList;
        @SerializedName("total_book_receive_list")
        public JsonArray jsonArrayReceiveList;

        public JsonObject getBhandar_data() {
            return bhandar_data;
        }

        public void setBhandar_data(JsonObject bhandar_data) {
            this.bhandar_data = bhandar_data;
        }


        public JsonArray getJsonArrayIssueList() {
            return jsonArrayIssueList;
        }

        public void setJsonArrayIssueList(JsonArray jsonArrayIssueList) {
            this.jsonArrayIssueList = jsonArrayIssueList;
        }

        public JsonArray getJsonArrayReceiveList() {
            return jsonArrayReceiveList;
        }

        public void setJsonArrayReceiveList(JsonArray jsonArrayReceiveList) {
            this.jsonArrayReceiveList = jsonArrayReceiveList;
        }

        public String getBook_entry() {
            return book_entry;
        }

        public void setBook_entry(String book_entry) {
            this.book_entry = book_entry;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public String getTotal_book_issue() {
            return total_book_issue;
        }

        public void setTotal_book_issue(String total_book_issue) {
            this.total_book_issue = total_book_issue;
        }

        public String getTotal_book_receive() {
            return total_book_receive;
        }

        public void setTotal_book_receive(String total_book_receive) {
            this.total_book_receive = total_book_receive;
        }

        public String getOutstanding() {
            return outstanding;
        }

        public void setOutstanding(String outstanding) {
            this.outstanding = outstanding;
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
