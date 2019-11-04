package com.jkim.shrutsangam.api.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingListResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<Datum> data;

    public class Datum {
        @SerializedName("total_book")
        public List<listDataResponse> total_book;

        public List<listDataResponse> getTotal_book() {
            return total_book;
        }

        public void setTotal_book(List<listDataResponse> total_book) {
            this.total_book = total_book;
        }
    }

    public class listDataResponse {
        @SerializedName("OverDueDay")
        private String OverDueDay;
        @SerializedName("book_no")
        private String book_no;
        @SerializedName("name")
        private String name;
        @SerializedName("MemberName")
        private String MemberName;

        public String getOverDueDay() {
            return OverDueDay;
        }

        public void setOverDueDay(String overDueDay) {
            OverDueDay = overDueDay;
        }

        public String getBook_no() {
            return book_no;
        }

        public void setBook_no(String book_no) {
            this.book_no = book_no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMemberName() {
            return MemberName;
        }

        public void setMemberName(String memberName) {
            MemberName = memberName;
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
