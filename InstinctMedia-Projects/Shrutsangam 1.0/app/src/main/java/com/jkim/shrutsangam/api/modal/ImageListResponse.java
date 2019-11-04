package com.jkim.shrutsangam.api.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageListResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<ImageListResponse.Datum> data;

    public class Datum {
        @SerializedName("listrec")
        public List<ImageListResponse.imageListDataResponse> listrec;

        public List<imageListDataResponse> getListrec() {
            return listrec;
        }

        public void setListrec(List<imageListDataResponse> listrec) {
            this.listrec = listrec;
        }
    }

    public class imageListDataResponse {
        @SerializedName("id")
        private String id;
        @SerializedName("title")
        private String title;
        @SerializedName("books_id")
        private String books_id;
        @SerializedName("bookImage")
        private String bookImage;
        @SerializedName("InsertedBy")
        private String InsertedBy;
        @SerializedName("InsertedDate")
        private String InsertedDate;
        @SerializedName("UpdatedBy")
        private String UpdatedBy;
        @SerializedName("UpdatedDate")
        private String UpdatedDate;
        @SerializedName("status")
        private String status;
        @SerializedName("time")
        private String time;
        @SerializedName("librarian_id")
        private String librarian_id;
        @SerializedName("username")
        private String username;
        @SerializedName("BhandarName")
        private String BhandarName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBooks_id() {
            return books_id;
        }

        public void setBooks_id(String books_id) {
            this.books_id = books_id;
        }

        public String getBookImage() {
            return bookImage;
        }

        public void setBookImage(String bookImage) {
            this.bookImage = bookImage;
        }

        public String getInsertedBy() {
            return InsertedBy;
        }

        public void setInsertedBy(String insertedBy) {
            InsertedBy = insertedBy;
        }

        public String getInsertedDate() {
            return InsertedDate;
        }

        public void setInsertedDate(String insertedDate) {
            InsertedDate = insertedDate;
        }

        public String getUpdatedBy() {
            return UpdatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            UpdatedBy = updatedBy;
        }

        public String getUpdatedDate() {
            return UpdatedDate;
        }

        public void setUpdatedDate(String updatedDate) {
            UpdatedDate = updatedDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLibrarian_id() {
            return librarian_id;
        }

        public void setLibrarian_id(String librarian_id) {
            this.librarian_id = librarian_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getBhandarName() {
            return BhandarName;
        }

        public void setBhandarName(String bhandarName) {
            BhandarName = bhandarName;
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

    public List<ImageListResponse.Datum> getData() {
        return data;
    }

    public void setData(List<ImageListResponse.Datum> data) {
        this.data = data;
    }
}
