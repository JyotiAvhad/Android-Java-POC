package com.jkim.shrutsangam.api.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminGetBookNoResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Datum> data;

    public class Datum {
        @SerializedName("book_no")
        private String book_no;

        @SerializedName("name")
        private String book_name;

        @SerializedName("Conditions")
        private String condition;

        @SerializedName("book_id")
        private String BookId;

        public String getBookId() {
            return BookId;
        }

        public void setBookId(String bookId) {
            BookId = bookId;
        }

        public String getBook_no() {
            return book_no;
        }

        public void setBook_no(String book_no) {
            this.book_no = book_no;
        }

        public String getBook_name() {
            return book_name;
        }

        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }

        public String get_condition() {
            return condition;
        }

        public void set_condition(String condition) {
            this.condition = condition;
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
